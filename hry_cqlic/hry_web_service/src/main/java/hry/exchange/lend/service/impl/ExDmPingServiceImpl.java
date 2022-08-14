/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Wu Shuiming
 * @version: V1.0
 * @Date: 2016年3月24日 下午2:04:29
 */
package hry.exchange.lend.service.impl;

import com.alibaba.fastjson.JSON;
import hry.account.fund.service.AppTransactionService;
import hry.core.constant.CodeConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.remote.RemoteAppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExAmineOrderService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.lend.dao.ExDmLendDao;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmPing;
import hry.exchange.lend.service.*;
import hry.exchange.product.model.ExCointoCoin;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExCointoCoinService;
import hry.exchange.product.service.ExProductService;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.RemoteManageService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.web.remote.RemoteAppConfigService;
import hry.web.util.EmailUtil;
import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Gao Mimi
 * @Date : 2016年4月12日 下午4:45:50
 */
@Service("exDmPingService")
public class ExDmPingServiceImpl extends BaseServiceImpl<ExDmPing, Long> implements ExDmPingService {
    private Logger logger = Logger.getLogger(ExDmPing.class);

    @Resource(name = "exDmPingDao")
    @Override
    public void setDao(BaseDao<ExDmPing, Long> dao) {
        super.dao = dao;
    }

    @Resource
    public RedisService redisService;
    @Resource
    public AppTransactionService appTransactionService;
    @Resource
    public ExAmineOrderService examineOrderService;
    @Resource
    private ExDmLendService exDmLendService;
    @Resource
    private ExDmLendIntentService exDmLendIntentService;
    @Resource
    private ExProductService exProductService;
    @Resource
    private ExEntrustService exEntrustService;
    @Resource
    private RemoteAppCustomerService remoteAppCustomerService;
    @Resource
    private LendCoinAccountService accountService;
    @Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
    @Resource
    private ExCointoCoinService exCointoCoinService;
    @Resource
    private RemoteManageService remoteManageService;
    @Resource
    private ExDmLendDao exDmLendDao;
    @Resource
    private AppCustomerService appCustomerService;
    @Resource
    private AppPersonInfoService appPersonInfoService;
    @Resource
    private LendCoinAccountService lendCoinAccountService;


    // 定时把平仓完成的设置成完成状态
    @Override
    public void endPing(ExDmPing l) {

        // 如果超过了安全区域就把正在平仓流程结束掉
        // 取消所有的委托
        EntrustTrade EntrustTrade = new EntrustTrade();
        EntrustTrade.setCustomerId(l.getCustomerId());
        cancelCustAllExEntrust(l.getCustomerId());
        l.setStatus(2);
        this.update(l);

    }


    @Override
    public void pingByCustomerId(Long customerId, String userCode, String currencyType, String website) {
        Integer isPingWarehouse = isPingWarehouse(customerId, userCode, currencyType, website);
        QueryFilter filter = new QueryFilter(AppCustomer.class);
        filter.addFilter("id=", customerId);
        AppCustomer customer = appCustomerService.get(filter);
        Locale locale = LocaleContextHolder.getLocale();
        if (isPingWarehouse.equals(2)) {
            String key = "pingTips:riskgmessage" + customerId;
            String ed = redisService.get(key);
            if (null == ed) {
                // 发短信提醒进入风险区
                AppPersonInfo personInfo = appPersonInfoService.getByCustomerId(customerId);
                String country = personInfo.getCountry();
                System.out.println("personInfo.getEmail()："+personInfo.getEmail());
                if (country.equals("+86")) {
                    EmailUtil.sendMail(personInfo.getEmail(), "平仓警告", "您的账户：" + personInfo.getEmail() + " 进入风险区，请尽快归还借款。");
                } else {
                    EmailUtil.sendMail(personInfo.getEmail(), "Trading warning", "Your account: " + personInfo.getEmail() + " enters the risk area, please return the loan as soon as possible.");
                }
                redisService.save(key, "ed", 86400);
            }
        } else if (isPingWarehouse.equals(1)) {

            pingMoneyFlow(customerId, userCode, currencyType, website);// 走平仓流程

            // 发短信提醒已被强制平仓
            String key = "pingTips:riskgmessagee" + customerId;
            String ed = redisService.get(key);
            if (null == ed) {
                AppPersonInfo personInfo = appPersonInfoService.getByCustomerId(customerId);
                String country = personInfo.getCountry();
                System.out.println("personInfo.getEmail()："+personInfo.getEmail());
                if (country.equals("+86")) {
                    EmailUtil.sendMail(personInfo.getEmail(), "平仓警告", "您的账户：" + personInfo.getEmail() + " 已被平仓，请尽快归还借款。");
                } else {
                    EmailUtil.sendMail(personInfo.getEmail(), "Trading warning", "Your account: " + personInfo.getEmail() + " Has been liquidated, please return the loan as soon as possible.");
                }
                redisService.save(key, "ed", 86400);
            }
        }

    }

    @Override
    public Integer isPingWarehouse(Long customerId, String userCode, String currencyType, String website) {
        BigDecimal netAsseToLend = accountService.netAsseToLend(customerId, null, null, null);
        // 杠杆的比例1.1，放文件
        RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
        String lengPings = remoteAppConfigService.getFinanceLendByKey("lengPing");
        BigDecimal lengPing = new BigDecimal(StringUtil.isEmpty(lengPings) ? "0" : lengPings); // 110
        if (netAsseToLend.compareTo(lengPing) < 1) {
            return 1;
        }
        String lengRiskRates = remoteAppConfigService.getFinanceLendByKey("lengRiskRate");
        BigDecimal lengRiskRate = new BigDecimal(StringUtil.isEmpty(lengRiskRates) ? "0" : lengRiskRates); // 120
        if (netAsseToLend.compareTo(lengRiskRate) < 1) {
            return 2;
        }

        return 0;

    }

    public void pingCoinFlow(Long customerId, String userCode) {
        ExDmPing exDmPing = new ExDmPing();
        exDmPing.setCustomerId(customerId);
        exDmPing.setUserCode(userCode);
        exDmPing.setStatus(1);
        exDmPing.setPingNum(IdGenerate.transactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Ping)));
        this.save(exDmPing);

        QueryFilter filter = new QueryFilter(ExDmLend.class);
        filter.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
        filter.addFilter("customerId=", customerId);
        filter.addFilter("status<", 3);
        List<ExDmLend> list = exDmLendService.find(filter);
        for (ExDmLend l : list) {
            ExDigitalmoneyAccount eda = exDigitalmoneyAccountService.getByCustomerIdAndType(customerId, null, l.getCurrencyType(), l.getWebsite());
            // eda.getHotMoney().add()
        }

    }

    public void pingMoneyFlow(Long customerId, String userCode, String currencyType, String website) {
        List<ExDmPing> pl = getBycustomerid(customerId, userCode, 1, currencyType, website);// 已进入平仓流程的
        System.out.println("pingMoneyFlow");
        if (null == pl || pl.size() == 0) {
            ExDmPing exDmPing = create(customerId, userCode, currencyType, website);
            System.out.println(" this.save(exDmPing)");
            this.save(exDmPing);

            // 1所有委托全部取消
            this.cancelCustAllExEntrust(customerId);
            ExDmLendPingService exDmLendPingService = (ExDmLendPingService) ContextUtil.getBean("exDmLendPingService");
            // 2驳回所有的提币申请
            boolean stopCoinFlag = exDmLendPingService.stopeAlllistByapply(customerId, currencyType, website);
            //3 驳回所有的提现申请
            boolean stopMoneyFlag = exDmLendPingService.stopeMoneylistByapply(customerId, currencyType, website);
            //4还钱
//            pingRepayflow(customerId);


        }

    }

    /**
     * 开始还钱
     *
     * @param customerId
     */
    @Override
    public void pingRepayflow(Long customerId) {
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("customerId", customerId);
        List<ExDmLend> listgroupDmLend = exDmLendDao.getLendingByGroupCustomerId(map1);
        if (null == listgroupDmLend || listgroupDmLend.size() == 0) {
            //已经还完了，就不往下走了
            return;
        }

        //撤消所有委托单
        this.cancelCustAllExEntrust(customerId);

        // 1直接从热账户还钱
        QueryFilter filter = new QueryFilter(ExDmLend.class);
        filter.addFilter("status<", 3);
        filter.addFilter("customerId=", customerId);
        ExDmLendService exDmLendService = (ExDmLendService) ContextUtil.getBean("exDmLendService");
        List<ExDmLend> listExDmLend = exDmLendService.find(filter);
        for (ExDmLend ld : listExDmLend) {
            exDmLendService.repayment(ld.getId(), "all", null);
        }

        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId.toString());
        if (null == userRedis) {
            return;
        }
        Map<String, Long> map = userRedis.getDmAccountId();
        Iterator<Map.Entry<String, Long>> it = map.entrySet().iterator();
        //所有币的币账户
        List<ExDigitalmoneyAccountRedis> listdmredis = new ArrayList<ExDigitalmoneyAccountRedis>();
        while (it.hasNext()) {
            Map.Entry<String, Long> entry = it.next();
            String coinCode = entry.getKey();
            ExDigitalmoneyAccountRedis exDigitalmoneyAccountRedis = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
            listdmredis.add(exDigitalmoneyAccountRedis);
        }

        AppAccountRedis appAccountredis = UserRedisUtils.getAccount(userRedis.getAccountId().toString());
        if (null == appAccountredis) {
            logger.info(customerId + "--pingRepayflow--appAccountredis为空");

            //todo没有就去数据库查
        } else {
            ExDigitalmoneyAccountRedis exDigitalmoneyAccountRedis = new ExDigitalmoneyAccountRedis();
            String rmb = accountService.getRMBCode();
            exDigitalmoneyAccountRedis.setCoinCode(rmb);
            exDigitalmoneyAccountRedis.setHotMoney(appAccountredis.getHotMoney());
            listdmredis.add(exDigitalmoneyAccountRedis);
        }

        //当前交易区
        List<ExCointoCoin> find = exCointoCoinService.find(new QueryFilter(ExCointoCoin.class).addFilter("state=", 1));
        //1把能直接交易的通过一次性交易直接还
        deal(customerId, listgroupDmLend, listdmredis, appAccountredis, find);
        //2先把币转成基础币（btc_cny,lct_cny,有btc，但借了lct）
        toBaseCoin(customerId, listgroupDmLend, listdmredis, appAccountredis, find);
    }

    public void toBaseCoin(Long customerId, List<ExDmLend> listgroupDmLend,
                           List<ExDigitalmoneyAccountRedis> listdmredis,
                           AppAccountRedis appAccountredis, List<ExCointoCoin> find) {
        int i = 0;
        String username = "";
        for (ExDmLend ld : listgroupDmLend) {
            if (ld.getLendCount().compareTo(BigDecimal.ZERO) == 1) {
                username = ld.getUserName();
                i++;
            }
        }
        RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
        String coinCodeForRmb = remoteAppConfigService.getFinanceByKey("coinCodeForRmb");
        //2先把币转成基础币（btc_cny,lct_cny,有btc，但借了lct）
        if (i > 0) {
            for (ExDigitalmoneyAccountRedis dmredis : listdmredis) {
                if (dmredis.getHotMoney().compareTo(BigDecimal.ZERO) == 1) {
                    BigDecimal entrustPrice = accountService.getCurrentExchangPrice(coinCodeForRmb, dmredis.getCoinCode());// 高于当前成交价配置5%
                    if (null != entrustPrice) {
                        BigDecimal entrustCount = dmredis.getHotMoney();
                        if (entrustCount.compareTo(dmredis.getHotMoney()) == 1) {
                            entrustCount = dmredis.getHotMoney();// 高于当前成交价配置5%
                        }
                        // 生成一条委托单
                        if (entrustCount != null) {
                            addExEntrust(2, 2, customerId, username, coinCodeForRmb, dmredis.getCoinCode(), entrustPrice, entrustCount);
                        }

                    }

                }


            }
        }
    }

    /**
     * 挂出能交易的单
     *
     * @param customerId
     * @param listgroupDmLend 借款单
     * @param listdmredis     当前用户的币账户
     * @param appAccountredis 冷热账户
     * @param find            当前所有的交易区
     */
    public void deal(Long customerId, List<ExDmLend> listgroupDmLend,
                     List<ExDigitalmoneyAccountRedis> listdmredis, AppAccountRedis appAccountredis,
                     List<ExCointoCoin> find) {
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId.toString());
        // 3全部币都卖出去，新建市价委托，并且优先级最高
        for (ExDmLend ld : listgroupDmLend) {
            for (ExDigitalmoneyAccountRedis dmredis : listdmredis) {
                if (dmredis.getHotMoney().compareTo(BigDecimal.ZERO) == 1) {
                    for (ExCointoCoin ecc : find) {
                        if (ecc.getFixPriceCoinCode().equals(dmredis.getCoinCode()) && ecc.getCoinCode().equals(ld.getLendCoin())) {
//                            Integer keepDecimalForRmb = keepdecimal(ecc.getFixPriceCoinCode());
                            Integer keepDecimalForCoin = keepdecimal(ecc.getCoinCode());
                            // 高于当前成交价配置5%
                            BigDecimal entrustPrice = accountService.getCurrentExchangPrice(ecc.getCoinCode(), ecc.getFixPriceCoinCode());
                            if (null != entrustPrice) {
                                BigDecimal entrustCount = ld.getLendCount();
                                BigDecimal entrustSum = entrustPrice.multiply(entrustCount);
                                if (entrustSum.compareTo(dmredis.getHotMoney()) == 1) {
                                    // 高于当前成交价配置5%
                                    entrustCount = dmredis.getHotMoney().divide(entrustPrice, keepDecimalForCoin, BigDecimal.ROUND_HALF_DOWN);
                                }
                                entrustSum = entrustPrice.multiply(entrustCount);
                                // 生成一条委托单
                                ld.setLendCount(ld.getLendCount().subtract(entrustCount));
                                dmredis.setHotMoney(dmredis.getHotMoney().subtract(entrustSum));
                                if (entrustCount != null) {
                                    addExEntrust(2, 1, customerId, ld.getUserName(), ecc.getFixPriceCoinCode(), ecc.getCoinCode(), entrustPrice, entrustCount);
                                }
                            }
                        } else if (ecc.getFixPriceCoinCode().equals(ld.getLendCoin()) && ecc.getCoinCode().equals(dmredis.getCoinCode())) {
//                            Integer keepDecimalForRmb = keepdecimal(ecc.getFixPriceCoinCode());
                            Integer keepDecimalForCoin = keepdecimal(ecc.getCoinCode());
                            BigDecimal entrustPrice = accountService.getCurrentExchangPrice(ecc.getCoinCode(), ecc.getFixPriceCoinCode());
                            if (null != entrustPrice) {
                                // 高于当前成交价配置5%
                                BigDecimal entrustCount = ld.getLendCount().divide(entrustPrice, keepDecimalForCoin, BigDecimal.ROUND_HALF_DOWN);
                                if (entrustCount.compareTo(dmredis.getHotMoney()) == 1) {
                                    // 高于当前成交价配置5%
                                    entrustCount = dmredis.getHotMoney();
                                }
                                BigDecimal entrustSum = entrustPrice.multiply(entrustCount);
                                // 生成一条委托单
                                ld.setLendCount(ld.getLendCount().subtract(entrustSum));
                                dmredis.setHotMoney(dmredis.getHotMoney().subtract(entrustCount));
                                if (entrustCount != null) {
                                    addExEntrust(2, 2, customerId, ld.getUserName(), ecc.getFixPriceCoinCode(), ecc.getCoinCode(), entrustPrice, entrustCount);
                                }

                            }

                        }
                    }
                }

            }

        }

    }

    public Integer keepdecimal(String coinCode) {
        RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
        ExProduct ex1 = exProductService.findByallCoinCode(coinCode);
        if (null != ex1) {
            Integer keepDecimalForCoin = ex1.getKeepDecimalForCoin();
            if (null == keepDecimalForCoin)
                keepDecimalForCoin = 4;
            return keepDecimalForCoin;
        } else {
            String keepDecimalForRmbs = remoteAppConfigService.getFinanceByKey("keepDecimalForRmb");
            Integer keepDecimalForRmb = Integer.valueOf(keepDecimalForRmbs);
            if (null == keepDecimalForRmb)
                keepDecimalForRmb = 4;
            return keepDecimalForRmb;
        }


    }

    public void addExEntrust(Integer fixPriceType, Integer type, Long customerId, String userName, String fixPriceCoinCode, String coinCode, BigDecimal entrustPrice, BigDecimal entrustCount) {
        EntrustTrade exEntrust = new EntrustTrade();
        exEntrust.setFixPriceCoinCode(fixPriceCoinCode);
        exEntrust.setCoinCode(coinCode);
        exEntrust.setFixPriceType(fixPriceType);
        exEntrust.setType(type);
        exEntrust.setEntrustPrice(entrustPrice);
        exEntrust.setEntrustCount(entrustCount);
        exEntrust.setEntrustWay(1);// 1.限价--> 表示以固定的价格 , 2.市价--->
        exEntrust.setCustomerId(customerId);
        exEntrust.setSource(4);
        exEntrust.setUserName(userName);
        if (null == exEntrust.getEntrustCount() || null == exEntrust.getEntrustPrice()) {
            return;
        }
        if (exEntrust.getEntrustPrice().compareTo(new BigDecimal(0)) <= 0 || exEntrust.getEntrustCount().compareTo(new BigDecimal(0)) <= 0) {
            return;
        }
        System.out.println("exEntrust.getEntrustCount()=" + exEntrust.getEntrustCount());
        System.out.println("exEntrust.getEntrustPrice()=" + exEntrust.getEntrustPrice());
        remoteManageService.addEntrust(exEntrust);
        // System.out.println("业务逻辑222：" + (end1 - start1) + "毫秒");
    }

    @Override
    public ExDmPing create(Long customerId, String userCode, String currencyType, String website) {
        ExDmPing exDmPing = new ExDmPing();
        exDmPing.setStatus(1);
        exDmPing.setPingNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Ping));
        exDmPing.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
        exDmPing.setCustomerId(customerId);
        exDmPing.setUserCode(userCode);
        exDmPing.setCurrencyType(currencyType);
        RemoteAppPersonInfoService remoteAppPersonInfoService = (RemoteAppPersonInfoService) ContextUtil.getBean("remoteAppPersonInfoService");
        AppPersonInfo appPersonInfo1 = remoteAppPersonInfoService.getByCustomerId(customerId);
        exDmPing.setTrueName(appPersonInfo1.getTrueName());
        exDmPing.setWebsite(website);
        return exDmPing;
    }

    @Override
    public List<ExDmPing> getBycustomerid(Long customerId, String userCode, Integer status, String currencyType, String website) {
        QueryFilter filter = new QueryFilter(ExDmPing.class);
        filter.addFilter("customerId=", customerId);
        filter.addFilter("status=", status);
        filter.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
        return this.find(filter);
    }

    @Override
    public List<ExDmPing> getPingBystatus(Integer status, String currencyType, String website) {
        QueryFilter filter = new QueryFilter(ExDmPing.class);
        filter.addFilter("status=", status);
        if (null != currencyType) {
            filter.addFilter("currencyType=", currencyType);
            filter.addFilter("website=", website);
        }

        return this.find(filter);
    }

    @Override
    public void cancelCustAllExEntrust(Long customerId) {
        String saasId = PropertiesUtils.APP.getProperty("app.saasId");
        QueryFilter queryfilter = new QueryFilter(ExEntrust.class);
        queryfilter.addFilter("status<", 2);
        queryfilter.addFilter("customerId=", customerId);
        queryfilter.setSaasId(saasId);
        List<ExEntrust> list = exEntrustService.find(queryfilter);
        for (ExEntrust l : list) {
            EntrustTrade entrustTrade = new EntrustTrade();
            entrustTrade.setEntrustNum(l.getEntrustNum());
            entrustTrade.setCoinCode(l.getCoinCode());
            entrustTrade.setType(l.getType());
            entrustTrade.setFixPriceCoinCode(l.getFixPriceCoinCode());
            entrustTrade.setEntrustPrice(BigDecimal.valueOf(Double.parseDouble(l.getEntrustPrice().toString())).stripTrailingZeros());
            // 序列化
            String str = JSON.toJSONString(entrustTrade);
            // 发送消息
            MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
            messageProducer.toTrade(str);
        }

    }

    @Override
    public Boolean isPinging(Long customerId, String userCode, String currencyType, String website) {

        String isLend = PropertiesUtils.APP.getProperty("app.isLend");//做成配置文件,如果是坐市商的话就用theSeat
        if (isLend != null && !"".equals(isLend) && isLend.equals("true")) {
            List<ExDmPing> list = getBycustomerid(customerId, userCode, 1, currencyType, website);
            if (null != list && list.size() != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public Boolean isLend() {

        String isLend = PropertiesUtils.APP.getProperty("app.isLend");//做成配置文件,如果是坐市商的话就用theSeat
        if (isLend != null && !"".equals(isLend) && isLend.equals("true")) {
            return true;
        } else {
            return false;
        }

    }


    @Override
    public String[] checkPing(Long customerId) {
        String[] rt = new String[2];
        Boolean isPinging = this.isPinging(customerId, null, null, null);
        if (isPinging) {
            rt[0] = CodeConstant.CODE_FAILED;
            rt[1] = "goingPing";
            return rt;
        } else {
            rt[0] = CodeConstant.CODE_SUCCESS;
            return rt;

        }
    }

    @Override
    public String[] checkLending(Long customerId) {
        String[] rt = new String[2];
        QueryFilter outfilter = new QueryFilter(ExDmLend.class);
        outfilter.addFilter("status<", 3);
        outfilter.addFilter("customerId=", customerId);
        List<ExDmLend> exDmLends = exDmLendService.find(outfilter);
        if (exDmLends != null && exDmLends.size() > 0) {
            rt[0] = CodeConstant.CODE_FAILED;
        } else {
            rt[0] = CodeConstant.CODE_SUCCESS;
        }
        return rt;
    }

}