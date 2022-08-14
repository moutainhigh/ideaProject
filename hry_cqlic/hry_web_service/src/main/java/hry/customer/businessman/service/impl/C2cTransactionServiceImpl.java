/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: liushilei
 * @version: V1.0
 * @Date: 2017-12-07 14:06:38
 */
package hry.customer.businessman.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.account.fund.model.AppBankCard;
import hry.account.fund.service.AppBankCardService;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.util.PageFactory;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import hry.customer.businessman.dao.C2cTransactionDao;
import hry.customer.businessman.model.AppBusinessman;
import hry.customer.businessman.model.AppBusinessmanBank;
import hry.customer.businessman.model.C2cCoin;
import hry.customer.businessman.model.C2cTransaction;
import hry.customer.businessman.service.AppBusinessmanBankService;
import hry.customer.businessman.service.AppBusinessmanService;
import hry.customer.businessman.service.C2cTransactionService;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.model.base.FrontPage;
import hry.manage.remote.model.c2c.C2cOrder;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.web.AppMessageTemplate.model.AppMessageTemplate;
import hry.web.AppMessageTemplate.service.AppMessageTemplateService;
import hry.web.app.model.AppMessageCategory;
import hry.web.message.service.AppMessageCategoryService;
import hry.web.message.service.AppMessageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * C2cTransactionService
 * </p>
 *
 * @author: liushilei
 * @Date : 2017-12-07 14:06:38
 */
@Service("c2cTransactionService")
public class C2cTransactionServiceImpl extends BaseServiceImpl<C2cTransaction, Long> implements C2cTransactionService {

    private static Object lock = new Object();
    private static final Logger log = Logger.getLogger(C2cTransactionServiceImpl.class);

    @Resource(name = "c2cTransactionDao")
    @Override
    public void setDao(BaseDao<C2cTransaction, Long> dao) {
        super.dao = dao;
    }

    @Resource
    public AppMessageService appMessageService;

    @Resource
    public AppMessageCategoryService appMessageCategoryService;

    @Resource
    public AppMessageTemplateService appMessageTemplateService;

    @Resource
    private AppBusinessmanService appBusinessmanService;

    @Resource
    private AppBusinessmanBankService appBusinessmanBankService;

    @Resource
    private AppBankCardService appBankCardService;

    @Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;

    @Resource
    private MessageProducer messageProducer;

    @Resource
    private ExDmTransactionService exDmTransactionService;

    @Override
    public synchronized JsonResult createC2cOrder(C2cOrder c2cOrder) {

        JsonResult jsonResult = new JsonResult();

        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        String c2cCoinList = redisService.get("cn:c2cCoinList");
        if (!StringUtils.isEmpty(c2cCoinList)) {
            List<C2cCoin> parseArray = JSON.parseArray(c2cCoinList, C2cCoin.class);
            BigDecimal min = new BigDecimal(1);
            BigDecimal max = new BigDecimal(100);
            for (C2cCoin c2cCoin : parseArray) {
                if (c2cOrder.getCoinCode().equals(c2cCoin.getCoinCode())) {
                    min = c2cCoin.getMinCount();
                    max = c2cCoin.getMaxCount();
                    break;
                }
            }

            if (c2cOrder.getTransactionCount().compareTo(min) < 0) {
                jsonResult.setMsg("最小下单数为:" + min.setScale(0, BigDecimal.ROUND_HALF_DOWN));
                return jsonResult;
            }
            if (c2cOrder.getTransactionCount().compareTo(max) > 0) {
                jsonResult.setMsg("最大下单数为:" + max.setScale(0, BigDecimal.ROUND_HALF_DOWN));
                return jsonResult;
            }

        } else {
            jsonResult.setMsg("c2c配置缓存错误");
            return jsonResult;
        }

        // 判断是否添加银行卡
        AppBankCard appBankCard = null;
        if (c2cOrder.getTransactionType().intValue() == 2) {
            QueryFilter filter = new QueryFilter(AppBankCard.class);
            filter.addFilter("customerId=", c2cOrder.getCustomerId());
            filter.addFilter("isDelete=", 0);
            filter.addFilter("type=", 1);
            filter.addFilter("isDefault=", 1);
            appBankCard = appBankCardService.get(filter);
            if (appBankCard == null) {
                jsonResult.setMsg("请到个人中心添加银行卡后再操作");
                return jsonResult;
            }

            // 判断账户是否大于要卖的币
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis userRedis = redisUtil.get(c2cOrder.getCustomerId().toString());
            Long dmAccountId = userRedis.getDmAccountId(c2cOrder.getCoinCode());
            ExDigitalmoneyAccountRedis dmAccount = UserRedisUtils.getAccount(dmAccountId.toString(), c2cOrder.getCoinCode());
            if (dmAccount.getHotMoney().compareTo(c2cOrder.getTransactionCount()) < 0) {
                jsonResult.setMsg(c2cOrder.getCoinCode() + "不足!");
                return jsonResult;
            }
        }

        // 订单
        C2cTransaction c2cTransaction = new C2cTransaction();

        String rulestr = redisService.get("cn:c2crule");
        if (!StringUtils.isEmpty(rulestr) && !"{}".equals(rulestr)) {

            JSONObject rule = JSON.parseObject(rulestr);
            if (rule != null && rule.size() > 0) {
                // 获得匹配模式
                String c2crule = rule.getString("c2crule");
                if ("0".equals(c2crule)) {// 指定匹配

                    String businessmanType = rule.getString("businessmanType");
                    if ("A".equals(businessmanType)) {// A类交易笔数低的
                        AppBusinessman appBusinessman = minOrderBusinessman(c2cOrder.getCoinCode(), 0);
                        if (appBusinessman != null) {
                            c2cTransaction = createC2cTransaction(c2cTransaction, c2cOrder, appBankCard);
                            // 交易商商户id
                            c2cTransaction.setBusinessmanId(appBusinessman.getId());
                            c2cTransaction.setBusinessman(appBusinessman.getTrueName());

                            QueryFilter bankFilter = new QueryFilter(AppBusinessmanBank.class);
                            bankFilter.addFilter("businessmanId=", appBusinessman.getId());
                            AppBusinessmanBank appBusinessmanBank = appBusinessmanBankService.get(bankFilter);
                            if (appBusinessmanBank != null) {
                                // 交易商商户银行卡id
                                c2cTransaction.setBusinessmanBankId(appBusinessmanBank.getId());
                                c2cTransaction.setRandomNum(RandomStringUtils.randomNumeric(4));
                                // 保存订单
                                super.save(c2cTransaction);
                                jsonResult.setObj(c2cTransaction.getTransactionNum());
                                jsonResult.setSuccess(true);
                            } else {
                                jsonResult.setMsg("交易商银行卡不能为空");
                            }

                        } else {
                            jsonResult.setMsg("交易商不能为空");
                        }

                    } else if ("B".equals(businessmanType)) {// A类交易笔数低的
                        // A类交易笔数低的
                        AppBusinessman appBusinessman = minOrderBusinessman(c2cOrder.getCoinCode(), 1);
                        if (appBusinessman != null) {
                            c2cTransaction = createC2cTransaction(c2cTransaction, c2cOrder, appBankCard);
                            // 交易商商户id
                            c2cTransaction.setBusinessmanId(appBusinessman.getId());
                            c2cTransaction.setBusinessman(appBusinessman.getTrueName());

                            QueryFilter bankFilter = new QueryFilter(AppBusinessmanBank.class);
                            bankFilter.addFilter("businessmanId=", appBusinessman.getId());
                            AppBusinessmanBank appBusinessmanBank = appBusinessmanBankService.get(bankFilter);
                            if (appBusinessmanBank != null) {
                                // 交易商商户银行卡id
                                c2cTransaction.setBusinessmanBankId(appBusinessmanBank.getId());
                                c2cTransaction.setRandomNum(RandomStringUtils.randomNumeric(4));
                                // 保存订单
                                super.save(c2cTransaction);
                                jsonResult.setObj(c2cTransaction.getTransactionNum());
                                jsonResult.setSuccess(true);
                            } else {
                                jsonResult.setMsg("交易商银行卡不能为空");
                            }

                        } else {
                            jsonResult.setMsg("交易商不能为空");
                        }
                    }

                } else if ("1".equals(c2crule)) {// 随机匹配

                    QueryFilter filter = new QueryFilter(AppBusinessman.class);
                    filter.addFilter("changeCoin=", c2cOrder.getCoinCode());
                    filter.addFilter("isLock=","0");  //0未禁用  1禁用
                    List<AppBusinessman> list = appBusinessmanService.find(filter);
                    if (list != null && list.size() > 0) {
                        // 随机打乱,选择商户
                        Collections.shuffle(list);
                        AppBusinessman appBusinessman = list.get(0);
                        c2cTransaction = createC2cTransaction(c2cTransaction, c2cOrder, appBankCard);
                        // 交易商商户id
                        c2cTransaction.setBusinessmanId(appBusinessman.getId());
                        c2cTransaction.setBusinessman(appBusinessman.getTrueName());

                        QueryFilter bankFilter = new QueryFilter(AppBusinessmanBank.class);
                        bankFilter.addFilter("businessmanId=", appBusinessman.getId());
                        AppBusinessmanBank appBusinessmanBank = appBusinessmanBankService.get(bankFilter);
                        if (appBusinessmanBank != null) {
                            // 交易商商户银行卡id
                            c2cTransaction.setBusinessmanBankId(appBusinessmanBank.getId());
                            c2cTransaction.setBusinessmanTrueName(appBusinessmanBank.getBusinessmanTrueName());
                            c2cTransaction.setRandomNum(RandomStringUtils.randomNumeric(4));
                            // 保存订单
                            super.save(c2cTransaction);
                            jsonResult.setObj(c2cTransaction.getTransactionNum());
                            jsonResult.setSuccess(true);
                        } else {
                            jsonResult.setMsg("交易商银行卡不能为空");
                        }

                    } else {
                        jsonResult.setMsg("交易商不能为空");
                    }
                } else if ("2".equals(c2crule)) {// 价格匹配
                    String businessmanType2 = rule.getString("businessmanType2");
                    if ("AB".equals(businessmanType2)) {// AB价格低的

                        // 查询这个币的商户按价格排序
                        QueryFilter filter = new QueryFilter(AppBusinessman.class);
                        filter.addFilter("changeCoin=", c2cOrder.getCoinCode());
                        filter.addFilter("isLock=","0");  //0未禁用  1禁用
                        filter.setOrderby("price");
                        List<AppBusinessman> list = appBusinessmanService.find(filter);

                        if (list != null && list.size() > 0) {

                            // newList 统计相同的最低价
                            List<AppBusinessman> newList = new ArrayList<AppBusinessman>();
                            AppBusinessman appBusinessman = list.get(0);
                            for (AppBusinessman ab : list) {
                                if (ab.getPrice().compareTo(appBusinessman.getPrice()) == 0) {
                                    newList.add(ab);
                                }
                            }
                            // 随机打乱,选择商户
                            Collections.shuffle(newList);
                            appBusinessman = newList.get(0);

                            c2cTransaction = createC2cTransaction(c2cTransaction, c2cOrder, appBankCard);
                            // 交易商商户id
                            c2cTransaction.setBusinessmanId(appBusinessman.getId());
                            c2cTransaction.setBusinessman(appBusinessman.getTrueName());

                            QueryFilter bankFilter = new QueryFilter(AppBusinessmanBank.class);
                            bankFilter.addFilter("businessmanId=", appBusinessman.getId());
                            AppBusinessmanBank appBusinessmanBank = appBusinessmanBankService.get(bankFilter);
                            if (appBusinessmanBank != null) {
                                // 交易商商户银行卡id
                                c2cTransaction.setBusinessmanBankId(appBusinessmanBank.getId());
                                c2cTransaction.setRandomNum(RandomStringUtils.randomNumeric(4));
                                // 保存订单
                                super.save(c2cTransaction);
                                jsonResult.setObj(c2cTransaction.getTransactionNum());
                                jsonResult.setSuccess(true);
                            } else {
                                jsonResult.setMsg("交易商银行卡不能为空");
                            }

                        } else {
                            jsonResult.setMsg("交易商不能为空");
                        }

                    } else if ("A".equals(businessmanType2)) {// A价格低的

                        // 查询这个币的A类商户按价格排序
                        QueryFilter filter = new QueryFilter(AppBusinessman.class);
                        filter.addFilter("changeCoin=", c2cOrder.getCoinCode());
                        filter.addFilter("type=", 0);
                        filter.addFilter("isLock=","0");  //0未禁用  1禁用
                        filter.setOrderby("price");
                        List<AppBusinessman> list = appBusinessmanService.find(filter);

                        if (list != null && list.size() > 0) {
                            // newList 统计相同的最低价
                            List<AppBusinessman> newList = new ArrayList<AppBusinessman>();
                            AppBusinessman appBusinessman = list.get(0);
                            for (AppBusinessman ab : list) {
                                if (ab.getPrice().compareTo(appBusinessman.getPrice()) == 0) {
                                    newList.add(ab);
                                }
                            }
                            // 随机打乱,选择商户
                            Collections.shuffle(newList);
                            appBusinessman = newList.get(0);

                            c2cTransaction = createC2cTransaction(c2cTransaction, c2cOrder, appBankCard);
                            // 交易商商户id
                            c2cTransaction.setBusinessmanId(appBusinessman.getId());
                            c2cTransaction.setBusinessman(appBusinessman.getTrueName());

                            QueryFilter bankFilter = new QueryFilter(AppBusinessmanBank.class);
                            bankFilter.addFilter("businessmanId=", appBusinessman.getId());
                            AppBusinessmanBank appBusinessmanBank = appBusinessmanBankService.get(bankFilter);
                            if (appBusinessmanBank != null) {
                                // 交易商商户银行卡id
                                c2cTransaction.setBusinessmanBankId(appBusinessmanBank.getId());
                                c2cTransaction.setRandomNum(RandomStringUtils.randomNumeric(4));
                                // 保存订单
                                super.save(c2cTransaction);
                                jsonResult.setObj(c2cTransaction.getTransactionNum());
                                jsonResult.setSuccess(true);
                            } else {
                                jsonResult.setMsg("交易商银行卡不能为空");
                            }

                        } else {
                            jsonResult.setMsg("交易商不能为空");
                        }

                    } else if ("B".equals(businessmanType2)) {// B价格低的

                        // 查询这个币的A类商户按价格排序
                        QueryFilter filter = new QueryFilter(AppBusinessman.class);
                        filter.addFilter("changeCoin=", c2cOrder.getCoinCode());
                        filter.addFilter("type=", 1);
                        filter.addFilter("isLock=","0");  //0未禁用  1禁用
                        filter.setOrderby("price");
                        List<AppBusinessman> list = appBusinessmanService.find(filter);

                        if (list != null && list.size() > 0) {

                            // newList 统计相同的最低价
                            List<AppBusinessman> newList = new ArrayList<AppBusinessman>();
                            AppBusinessman appBusinessman = list.get(0);
                            for (AppBusinessman ab : list) {
                                if (ab.getPrice().compareTo(appBusinessman.getPrice()) == 0) {
                                    newList.add(ab);
                                }
                            }
                            // 随机打乱,选择商户
                            Collections.shuffle(newList);
                            appBusinessman = newList.get(0);

                            c2cTransaction = createC2cTransaction(c2cTransaction, c2cOrder, appBankCard);
                            // 交易商商户id
                            c2cTransaction.setBusinessmanId(appBusinessman.getId());
                            c2cTransaction.setBusinessman(appBusinessman.getTrueName());

                            QueryFilter bankFilter = new QueryFilter(AppBusinessmanBank.class);
                            bankFilter.addFilter("businessmanId=", appBusinessman.getId());
                            AppBusinessmanBank appBusinessmanBank = appBusinessmanBankService.get(bankFilter);
                            if (appBusinessmanBank != null) {
                                // 交易商商户银行卡id
                                c2cTransaction.setBusinessmanBankId(appBusinessmanBank.getId());
                                c2cTransaction.setRandomNum(RandomStringUtils.randomNumeric(4));
                                // 保存订单
                                super.save(c2cTransaction);
                                jsonResult.setObj(c2cTransaction.getTransactionNum());
                                jsonResult.setSuccess(true);
                            } else {
                                jsonResult.setMsg("交易商银行卡不能为空");
                            }

                        } else {
                            jsonResult.setMsg("交易商不能为空");
                        }

                    }

                }
            } else {
                jsonResult.setMsg("请配置交易配置规则c2c");
            }
        } else {
            jsonResult.setMsg("请配置交易配置规则c2c");
        }

        /**
         * 如果为卖单生成成功，进行冻结
         */
        if (jsonResult.getSuccess() && c2cTransaction.getTransactionType().intValue() == 2) {
            sellCoin(c2cTransaction);
        }

        return jsonResult;

    }

    private synchronized void sellCoin(C2cTransaction c2cTransaction) {

        ExDigitalmoneyAccount account = exDigitalmoneyAccountService.get(c2cTransaction.getAccountId());
        ExDmTransaction exDmTransaction = new ExDmTransaction();
        exDmTransaction.setAccountId(account.getId());
        exDmTransaction.setCoinCode(account.getCoinCode());
        exDmTransaction.setCreated(new Date());
        exDmTransaction.setCurrencyType(account.getCurrencyType());
        exDmTransaction.setWebsite(account.getWebsite());
        exDmTransaction.setCustomerId(account.getCustomerId());
        exDmTransaction.setCustomerName(account.getUserName());
        exDmTransaction.setFee(new BigDecimal(0));
        exDmTransaction.setTransactionMoney(c2cTransaction.getTransactionCount());
        exDmTransaction.setStatus(1);
        exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
        // 提币
        exDmTransaction.setTransactionType(2);
        exDmTransaction.setUserId(ContextUtil.getCurrentUserId());
        exDmTransaction.setRemark("C2C卖币");
        exDmTransaction.setOptType(9);
        exDmTransactionService.save(exDmTransaction);

        // 保存关联关系
        c2cTransaction.setTransactionId(exDmTransaction.getId());
        ;
        super.update(c2cTransaction);

        // 热账户减少
        Accountadd accountadd = new Accountadd();
        accountadd.setAccountId(c2cTransaction.getAccountId());
        accountadd.setMoney(c2cTransaction.getTransactionCount().multiply(new BigDecimal(-1)));
        accountadd.setMonteyType(1);
        accountadd.setAcccountType(1);
        accountadd.setRemarks(50);
        accountadd.setTransactionNum(c2cTransaction.getTransactionNum());

        // 冷账户增加
        Accountadd accountadd2 = new Accountadd();
        accountadd2.setAccountId(c2cTransaction.getAccountId());
        accountadd2.setMoney(c2cTransaction.getTransactionCount());
        accountadd2.setMonteyType(2);
        accountadd2.setAcccountType(1);
        accountadd2.setRemarks(50);
        accountadd2.setTransactionNum(c2cTransaction.getTransactionNum());

        List<Accountadd> list = new ArrayList<Accountadd>();
        list.add(accountadd);
        list.add(accountadd2);
        messageProducer.toAccount(JSON.toJSONString(list));
    }

    /**
     * 查询成交订单最少的商户
     *
     * @param coinCode
     * @param businessmanType
     * @return
     */
    private AppBusinessman minOrderBusinessman(String coinCode, int businessmanType) {

        // 一个币种所有商户
        QueryFilter filter = new QueryFilter(AppBusinessman.class);
        filter.addFilter("changeCoin=", coinCode);
        filter.addFilter("type=", businessmanType);
        filter.addFilter("isLock=","0");  //0未禁用  1禁用
        List<AppBusinessman> list1 = appBusinessmanService.find(filter);
        if (list1 == null || list1.size() < 1) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("coinCode", coinCode);
        // 有成交的商户列表，成交数量从小到大排序
        List<C2cTransaction> list2 = ((C2cTransactionDao) dao).groupByBusinessmanId(map);
        for (C2cTransaction c2cTransaction : list2) {
            AppBusinessman appBusinessman = appBusinessmanService.get(c2cTransaction.getBusinessmanId());
            if (appBusinessman == null) {
                list2.remove(c2cTransaction);
            }
        }

        List<AppBusinessman> hasOrder = new ArrayList<AppBusinessman>();

        for (AppBusinessman appBusinessman : list1) {
            for (C2cTransaction c2cTransaction : list2) {
                if (appBusinessman.getId().compareTo(c2cTransaction.getBusinessmanId()) == 0) {
                    hasOrder.add(appBusinessman);
                }
            }
        }

        // 得到差集
        list1.removeAll(hasOrder);
        // 如果有商户没有订单的，则随机取一个
        if (list1.size() > 0) {
            Collections.shuffle(list1);
            for (AppBusinessman appBusinessman : list1) {
                if (appBusinessman.getType() == businessmanType) {
                    return appBusinessman;
                }
            }
            return list1.get(0);
        } else {// 所有商户都有订单,返回订单数最小的一个商户，订单数相同返回前一个
            for (C2cTransaction c2cTransaction : list2) {
                AppBusinessman appBusinessman = appBusinessmanService.get(c2cTransaction.getBusinessmanId());
                if (appBusinessman.getType() == businessmanType) {
                    return appBusinessman;
                }
            }
            return null;
        }

    }

    /**
     * 封装参数
     *
     * @param c2cOrder
     * @return
     */
    private C2cTransaction createC2cTransaction(C2cTransaction c2cTransaction, C2cOrder c2cOrder, AppBankCard appBankCard) {
        C2cTransaction ct = new C2cTransaction();
        if (appBankCard != null) {
            ct.setCustomerBankId(appBankCard.getId());
        }
        // 币种
        ct.setCoinCode(c2cOrder.getCoinCode());
        // 订单号
        ct.setTransactionNum(c2cOrder.getTransactionNum());
        // 交易类型
        ct.setTransactionType(c2cOrder.getTransactionType());
        // 交易数量
        ct.setTransactionCount(c2cOrder.getTransactionCount());
        // 交易价格
        ct.setTransactionPrice(c2cOrder.getTransactionPrice());
        // 交易金额
        ct.setTransactionMoney(c2cOrder.getTransactionCount().multiply(c2cOrder.getTransactionPrice()).setScale(2, BigDecimal.ROUND_DOWN));
        // 待审核
        ct.setStatus(Integer.valueOf(1));
        // 未支付
        ct.setStatus2(Integer.valueOf(1));
        // 用户名
        ct.setUserName(c2cOrder.getUserName());
        // 用户id
        ct.setCustomerId(c2cOrder.getCustomerId());
        // accountId
        ct.setAccountId(c2cOrder.getAccountId());
        return ct;
    }

    @Override
    public String getC2cOrderDetail(String transactionNum) {

        QueryFilter filter = new QueryFilter(C2cTransaction.class);
        filter.addFilter("transactionNum=", transactionNum);
        C2cTransaction c2cTransaction = super.get(filter);

        JSONObject obj = new JSONObject();
        // 汇款随机码
        obj.put("randomNum", c2cTransaction.getRandomNum());
        // 订单状态
        obj.put("status", c2cTransaction.getStatus());
        // 转账金额
        obj.put("transactionMoney", c2cTransaction.getTransactionMoney());
        // 交易币种
        obj.put("coinCode", c2cTransaction.getCoinCode());
        // 交易状态
        obj.put("status2", c2cTransaction.getStatus2());
        // 订单类型
        obj.put("transactionType", c2cTransaction.getTransactionType());
        // 订单号
        obj.put("transactionNum", c2cTransaction.getTransactionNum());
        // 用户登录名
        obj.put("userName", c2cTransaction.getUserName());
        // 交易数量
        obj.put("transactionCount", c2cTransaction.getTransactionCount());
        // 交易单价
        obj.put("transactionPrice", c2cTransaction.getTransactionPrice());

        if (c2cTransaction.getTransactionType() == 1) {// 买
            AppBusinessman appBusinessman = appBusinessmanService.get(c2cTransaction.getBusinessmanId());
            if (appBusinessman != null) {
                obj.put("businessmanTrueName", appBusinessman.getTrueName());
            }

            AppBusinessmanBank appBusinessmanBank = appBusinessmanBankService.get(c2cTransaction.getBusinessmanBankId());
            if (appBusinessmanBank != null) {
                // 开户行名称
                obj.put("bankname", appBusinessmanBank.getBankname());
                // 卡号
                obj.put("bankcard", appBusinessmanBank.getBankcard());
                // 持卡人
                obj.put("bankowner", appBusinessmanBank.getBankowner());
            }
        } else if (c2cTransaction.getTransactionType() == 2) {// 卖
            AppBankCard appBankCard = appBankCardService.get(c2cTransaction.getCustomerBankId());
            if (appBankCard != null) {

                // 开户行名称
                obj.put("bankname", appBankCard.getCardBank() + appBankCard.getSubBank());
                // 卡号
                obj.put("bankcard", appBankCard.getCardNumber());
                // 银行卡所在地
                obj.put("bankAddress", appBankCard.getBankProvince() + appBankCard.getBankAddress());
                // 持卡人
                obj.put("bankowner", appBankCard.getSurname() + appBankCard.getTrueName());

            }
        }

        return obj.toJSONString();
    }

    @Override
    public JsonResult confirm(Long id) {

        JsonResult jsonResult = new JsonResult();

        synchronized (lock) {
            C2cTransaction c2cTransaction = super.get(id);

            if (c2cTransaction.getStatus().intValue() == 3) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("交易已取消");
                return jsonResult;
            }
            if (c2cTransaction.getStatus().intValue() == 2) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("交易已成功");
                return jsonResult;
            }

            if (c2cTransaction.getTransactionType() == 1) {// 买确认,充币,可用增加

                ExDigitalmoneyAccount account = exDigitalmoneyAccountService.get(c2cTransaction.getAccountId());
                ExDmTransaction exDmTransaction = new ExDmTransaction();
                exDmTransaction.setAccountId(account.getId());
                exDmTransaction.setCoinCode(account.getCoinCode());
                exDmTransaction.setCreated(new Date());
                exDmTransaction.setCurrencyType(account.getCurrencyType());
                exDmTransaction.setWebsite(account.getWebsite());
                exDmTransaction.setCustomerId(account.getCustomerId());
                exDmTransaction.setCustomerName(account.getUserName());
                exDmTransaction.setFee(new BigDecimal(0));
                exDmTransaction.setTransactionMoney(c2cTransaction.getTransactionCount().subtract(c2cTransaction.getFee()));
                exDmTransaction.setStatus(2);
                exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
                // 充币
                exDmTransaction.setTransactionType(1);
                exDmTransaction.setUserId(ContextUtil.getCurrentUserId());
                exDmTransaction.setRemark("C2C买币");
                exDmTransaction.setOptType(9);
                exDmTransactionService.save(exDmTransaction);

                // 保存关联关系
                c2cTransaction.setTransactionId(exDmTransaction.getId());
                ;
                super.update(c2cTransaction);

                // 发送mq消息
                Accountadd accountadd = new Accountadd();
                accountadd.setAccountId(c2cTransaction.getAccountId());
                accountadd.setMoney(c2cTransaction.getTransactionCount().subtract(c2cTransaction.getFee()));
                accountadd.setMonteyType(1);
                accountadd.setAcccountType(1);
                accountadd.setRemarks(49);
                accountadd.setTransactionNum(c2cTransaction.getTransactionNum());
                List<Accountadd> list = new ArrayList<Accountadd>();
                list.add(accountadd);
                messageProducer.toAccount(JSON.toJSONString(list));

            } else if (c2cTransaction.getTransactionType() == 2) {// 卖确认,扣币,冻结减少

                // 币订单状态改为成功
                ExDmTransaction exDmTransaction = exDmTransactionService.get(c2cTransaction.getTransactionId());
                exDmTransaction.setStatus(2);
                exDmTransactionService.update(exDmTransaction);

                Accountadd accountadd = new Accountadd();
                accountadd.setAccountId(c2cTransaction.getAccountId());
                accountadd.setMoney(c2cTransaction.getTransactionCount().multiply(new BigDecimal(-1)));
                accountadd.setMonteyType(2);
                accountadd.setAcccountType(1);
                accountadd.setRemarks(51);
                accountadd.setTransactionNum(c2cTransaction.getTransactionNum());
                List<Accountadd> list = new ArrayList<Accountadd>();
                list.add(accountadd);
                messageProducer.toAccount(JSON.toJSONString(list));

            }
            c2cTransaction.setCheckUser(ContextUtil.getCurrentUserName());
            c2cTransaction.setStatus(2);
            super.update(c2cTransaction);

            //发送前台的消息通知
            RemoteAppCustomerService appCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
            AppCustomer customer = appCustomerService.getById(c2cTransaction.getCustomerId());
            this.sendFrontMessage(customer, c2cTransaction.getTransactionNum());
        }
        jsonResult.setSuccess(true);

        return jsonResult;
    }

    private void sendFrontMessage(AppCustomer customer, String transactionNum) {
        //获取提币模板消息
        QueryFilter amcQueryFilter = new QueryFilter(AppMessageCategory.class);
        amcQueryFilter.addFilter("keywords=", "2");//2
        AppMessageCategory appMessageCategory = appMessageCategoryService.get(amcQueryFilter);
        if (appMessageCategory == null) {
            return;
        }

        QueryFilter amtQueryFilter = new QueryFilter(AppMessageTemplate.class);
        amtQueryFilter.addFilter("templateId=", appMessageCategory.getId());//5为提币
        AppMessageTemplate appMessageTemplate = appMessageTemplateService.get(amtQueryFilter);
        if (appMessageTemplate == null) {
            return;
        }
        //封装通知的消息
        appMessageService.saveC2CMessage(customer, appMessageCategory, appMessageTemplate, transactionNum);

    }

    @Override
    public JsonResult close(Long id) {

        JsonResult jsonResult = new JsonResult();

        synchronized (lock) {
            C2cTransaction c2cTransaction = super.get(id);

            if (c2cTransaction.getStatus().intValue() == 3) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("交易已关闭");
                return jsonResult;
            }
            if (c2cTransaction.getStatus().intValue() == 2) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("交易已成功");
                return jsonResult;
            }

            if (c2cTransaction.getTransactionType() == 1) {// 买确认
                c2cTransaction.setStatus(3);
                super.update(c2cTransaction);
                jsonResult.setSuccess(true);

            } else if (c2cTransaction.getTransactionType() == 2) {// 卖确认

                // 订单改为撤销
                ExDmTransaction exDmTransaction = exDmTransactionService.get(c2cTransaction.getTransactionId());
                exDmTransaction.setStatus(3);
                exDmTransactionService.update(exDmTransaction);

                List<Accountadd> list = new ArrayList<Accountadd>();
                // 减少冻结，
                Accountadd accountadd = new Accountadd();
                accountadd.setAccountId(c2cTransaction.getAccountId());
                accountadd.setMoney(c2cTransaction.getTransactionCount().multiply(new BigDecimal(-1)));
                accountadd.setMonteyType(2);
                accountadd.setAcccountType(1);
                accountadd.setRemarks(53);
                accountadd.setTransactionNum(c2cTransaction.getTransactionNum());

                // 增加可用
                Accountadd accountadd2 = new Accountadd();
                accountadd2.setAccountId(c2cTransaction.getAccountId());
                accountadd2.setMoney(c2cTransaction.getTransactionCount().subtract(c2cTransaction.getFee()));
                accountadd2.setMonteyType(1);
                accountadd2.setAcccountType(1);
                accountadd2.setRemarks(53);
                accountadd2.setTransactionNum(c2cTransaction.getTransactionNum());
                list.add(accountadd);
                list.add(accountadd2);
                messageProducer.toAccount(JSON.toJSONString(list));

                c2cTransaction.setStatus(3);
                super.update(c2cTransaction);
                jsonResult.setSuccess(true);

            }
        }

        return jsonResult;
    }

    @Override
    public FrontPage c2clist(Map<String, String> params) {
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<C2cTransaction> list = ((C2cTransactionDao) dao).c2clist(params);
        ArrayList<C2cOrder> resultList = new ArrayList<C2cOrder>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                C2cTransaction c2cTransaction = list.get(i);
                C2cOrder c2cOrder = new C2cOrder();
                c2cOrder.setCoinCode(c2cTransaction.getCoinCode());
                c2cOrder.setTransactionPrice(c2cTransaction.getTransactionPrice());
                c2cOrder.setTransactionMoney(c2cTransaction.getTransactionMoney());
                c2cOrder.setTransactionCount(c2cTransaction.getTransactionCount());
                c2cOrder.setTransactionType(c2cTransaction.getTransactionType());
                c2cOrder.setTransactionTime(c2cTransaction.getCreated());
                c2cOrder.setTransactionNum(c2cTransaction.getTransactionNum());
                c2cOrder.setStatus(c2cTransaction.getStatus());
                c2cOrder.setTransactionNum(c2cTransaction.getTransactionNum());
                resultList.add(c2cOrder);
            }
        }
        return new FrontPage(resultList, page.getTotal(), page.getPages(), page.getPageSize());

    }

    @Override
    public void timeout() {
        log.info("定时设置c2c超时");
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        String timeOut = redisService.get("cn:c2cTimeOut");
        int time = 30;
        if (!StringUtils.isEmpty(timeOut)) {
            time = Integer.valueOf(timeOut);
        }

        Date date = new Date();
        Date startDate = DateUtil.addMinToDate(date, -time);
        String startDateString = DateUtil.dateToString(startDate, "yyyy-MM-dd HH:mm");

        QueryFilter filter = new QueryFilter(C2cTransaction.class);
        filter.addFilter("created<", startDateString);
        filter.addFilter("status=", 1);
        List<C2cTransaction> list = super.find(filter);
        log.info("c2c超时" + list.size() + "条,超时界线" + startDateString);
        if (list != null && list.size() > 0) {
            for (C2cTransaction c2cTransaction : list) {
                close(c2cTransaction.getId());
            }
        }

    }

    @Override
    public PageResult findPageBySql(QueryFilter filter) {


        //----------------------分页查询头部外壳------------------------------
        //创建PageResult对象
        PageResult pageResult = new PageResult();
        Page<AppCustomer> page = null;
        if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
            //pageSize = -1 时
            //pageHelper传pageSize参数传0查询全部
            page = PageHelper.startPage(filter.getPage(), 0);
        } else {
            page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
        }
        //----------------------分页查询头部外壳------------------------------

        //----------------------查询开始------------------------------

        String email = filter.getRequest().getParameter("email");
        String mobilePhone = filter.getRequest().getParameter("mobilePhone");

        String coinCode = (String) filter.getRequest().getParameter("coinCode");
        String randomNum = (String) filter.getRequest().getParameter("randomNum");

        String businessmanId = filter.getRequest().getParameter("businessmanId");
        String transactionType = filter.getRequest().getParameter("transactionType");
        String status = filter.getRequest().getParameter("status");
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(email)) {
            map.put("email", "%" + email + "%");
        }
        if (!StringUtils.isEmpty(mobilePhone)) {
            map.put("mobilePhone", "%" + mobilePhone + "%");
        }
        if (!StringUtils.isEmpty(coinCode)) {
            map.put("coinCode", "%" + coinCode + "%");
        }
        if (!StringUtils.isEmpty(randomNum)) {
            map.put("randomNum", "%" + randomNum + "%");
        }
        if (!StringUtils.isEmpty(businessmanId)) {
            map.put("businessmanId", businessmanId);
        }
        if (!StringUtils.isEmpty(transactionType)) {
            map.put("transactionType", transactionType);
        }
        if (!StringUtils.isEmpty(status)) {
            map.put("status", status);
        }

        ((C2cTransactionDao) dao).findPageBySql(map);
        //----------------------查询结束------------------------------

        //----------------------分页查询底部外壳------------------------------
        //设置分页数据
        pageResult.setRows(page.getResult());
        //设置总记录数
        pageResult.setRecordsTotal(page.getTotal());
        pageResult.setDraw(filter.getDraw());
        pageResult.setPage(filter.getPage());
        pageResult.setPageSize(filter.getPageSize());
        //----------------------分页查询底部外壳------------------------------

        return pageResult;

    }

}
