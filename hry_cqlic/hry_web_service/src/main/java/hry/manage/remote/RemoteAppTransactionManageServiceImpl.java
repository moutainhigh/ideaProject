/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Liu Shilei
 * @version: V1.0
 * @Date: 2016年4月1日 上午11:18:14
 */
package hry.manage.remote;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.account.fund.model.AppBankCard;
import hry.account.fund.model.AppOurAccount;
import hry.account.fund.model.AppTransaction;
import hry.account.fund.service.AppAccountService;
import hry.account.fund.service.AppBankCardService;
import hry.account.fund.service.AppOurAccountService;
import hry.account.fund.service.AppTransactionService;
import hry.bean.JsonResult;
import hry.bus.BusRequestUtil;
import hry.bus.model.BusJsonResult;
import hry.change.remote.account.RemoteExAmineOrderService;
import hry.change.remote.account.RemoteExDigitalmoneyAccountService;
import hry.change.remote.dmtransaction.RemoteExDmTransactionService;
import hry.core.constant.CodeConstant;
import hry.core.mvc.model.AppConfig;
import hry.core.thread.ThreadPool;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.lend.model.ExDmPing;
import hry.exchange.lend.service.ExDmLendService;
import hry.exchange.lend.service.ExDmPingService;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExProductService;
import hry.exchange.remote.account.RemoteExProductService;
import hry.exchange.transaction.model.ExDmCustomerPublicKey;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmCustomerPublicKeyService;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.front.redis.model.UserRedis;
import hry.klg.assetsrecord.model.KlgAssetsRecord;
import hry.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.klg.enums.TriggerPointEnum;
import hry.licqb.record.model.CustomerFreeze;
import hry.licqb.record.service.CustomerFreezeService;
import hry.licqb.record.service.DealRecordService;
import hry.licqb.thread.AgainPutIntoRunnable;
import hry.manage.remote.model.*;
import hry.manage.remote.model.base.FrontPage;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.mq.producer.service.MessageProducer;
import hry.pakgclass.OrderParameter;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.DifCustomer;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.serialize.ObjectUtil;
import hry.util.sys.ContextUtil;
import hry.web.remote.RemoteAppConfigService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class RemoteAppTransactionManageServiceImpl implements RemoteAppTransactionManageService {
    private static Logger logger = Logger.getLogger(RemoteAppTransactionManageServiceImpl.class);
    @Resource
    private ExDmPingService exDmPingService;

    @Resource
    private AppTransactionService appTransactionService;

    @Resource
    private AppAccountService appAccountService;

    @Resource
    private RemoteManageService remoteManageService;

    @Resource
    private ExDmTransactionService exDmTransactionService;

    @Resource
    private ExDmCustomerPublicKeyService exDmCustomerPublicKeyService;

    @Resource
    private ExProductService exProductService;

    @Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;

    @Resource
    private RemoteExProductService remoteExProductService;

    @Resource
    private RemoteExDmTransactionService remoteExDmTransactionService;
    @Resource
    private RemoteExDigitalmoneyAccountService remoteExDigitalmoneyAccountService;
    @Resource
    private AppPersonInfoService appPersonInfoService;
    @Resource
    private AppCustomerService appCustomerService;

    @Resource
    private AppOurAccountService appOurAccountService;

    @Resource
    private MessageProducer messageProducer;

    @Resource
    private ExOrderInfoService exOrderInfoService;

    @Resource
    private ExDmLendService exDmLendService;

    @Resource
    private RedisService redisService;
    @Resource
    private KlgAssetsRecordService klgAssetsRecordService;

    @Resource
    private DealRecordService dealRecordService;

    @Resource
    private CustomerFreezeService customerFreezeService;

    @Override
    public FrontPage findTransaction(Map<String, String> params) {
        return appTransactionService.findTransaction(params);
    }

    @Override
    public void save(AppTransactionManage appTransactionManage) {
        AppTransaction appTransaction = common(appTransactionManage);
        appTransactionService.save(appTransaction);
    }


    //*********************************************银行卡*********************************************
    @Resource
    private AppBankCardService appBankCardService;

    @Override
    public void save(AppBankCardManage appBankCardManage) {
        AppBankCard appBankCard = common(appBankCardManage);
        appBankCardService.save(appBankCard);
    }

    @Override
    public List<AppBankCardManage> findByCustomerId(Long id) {
        String saasId = RpcContext.getContext().getAttachment("saasId");
        QueryFilter queryFilter = new QueryFilter(AppBankCard.class);
        queryFilter.setSaasId(saasId);
        queryFilter.addFilter("customerId=", id);
        queryFilter.addFilter("isDelete=", 0);
		/*queryFilter.addFilter("currencyType=", ContextUtil.getRemoteCurrencyType());
		queryFilter.addFilter("website=", ContextUtil.getRemoteWebsite());*/
        List<AppBankCard> find = appBankCardService.find(queryFilter);
        List<AppBankCardManage> list = new ArrayList<AppBankCardManage>();
        for (int i = 0; i < find.size(); i++) {
            AppBankCardManage appBankCardManage = common(find.get(i));
            list.add(appBankCardManage);
        }
        return list;
    }

    @Override
    public RemoteResult delete(Long id) {
        try {
            AppBankCard appBankCard = appBankCardService.get(id);
            QueryFilter queryFilter = new QueryFilter(AppTransaction.class);
            QueryFilter addFilter = queryFilter.addFilter("custromerAccountNumber=", appBankCard.getCardNumber());
            List<AppTransaction> listAppTransaction = appTransactionService.find(addFilter);
            for (int i = 0; i < listAppTransaction.size(); i++) {
                Integer status = listAppTransaction.get(i).getStatus();
                if (status == 1) {
                    RemoteResult remoteResult = new RemoteResult();

                    return remoteResult.setSuccess(false).setMsg("inUsing_card_no_delete");
                }
            }
            appBankCard.setIsDelete(1);
            appBankCardService.update(appBankCard);
            return new RemoteResult().setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new RemoteResult().setSuccess(false);
        }
    }

    @Override
    public RemoteResult delete(Long id, String cusId) {
        try {
            AppBankCard appBankCard = appBankCardService.get(id);
            if (!appBankCard.getCustomerId().toString().equals(cusId)) {
                logger.error(!appBankCard.getCustomerId().toString().equals(cusId));
                return new RemoteResult().setSuccess(false).setMsg("不能删除他人银行卡");
            }

            QueryFilter queryFilter = new QueryFilter(AppTransaction.class);
            QueryFilter addFilter = queryFilter.addFilter("custromerAccountNumber=", appBankCard.getCardNumber());
            List<AppTransaction> listAppTransaction = appTransactionService.find(addFilter);
            for (int i = 0; i < listAppTransaction.size(); i++) {
                Integer status = listAppTransaction.get(i).getStatus();
                if (status == 1) {
                    RemoteResult remoteResult = new RemoteResult();

                    return remoteResult.setSuccess(false).setMsg("inUsing_card_no_delete");
                }
            }
            appBankCard.setIsDelete(1);
            appBankCardService.update(appBankCard);
            return new RemoteResult().setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new RemoteResult().setSuccess(false);
        }
    }


    @Override
    public AppBankCardManage get(String cardname) {
        AppBankCardManage appBankCardManage = null;
        QueryFilter qf = new QueryFilter(AppBankCard.class);
        qf.addFilter("cardNumber=", cardname);
        if (ContextUtil.EN.equals(ContextUtil.getWebsite())) {
            qf.addFilter("website=", ContextUtil.EN);
        } else {
            qf.addFilter("website=", ContextUtil.CN);
        }
        AppBankCard _appBankCard = appBankCardService.get(qf);
        if (_appBankCard != null) {
            appBankCardManage = common(_appBankCard);
        }

        return appBankCardManage;
    }

    @Override
    public AppBankCardManage get(Long cardId) {
        AppBankCardManage appBankCardManage = null;
        QueryFilter qf = new QueryFilter(AppBankCard.class);
        qf.addFilter("id=", cardId);
		/*if(ContextUtil.EN.equals(ContextUtil.getWebsite())){
			qf.addFilter("website=", ContextUtil.EN);
		}else{
			qf.addFilter("website=", ContextUtil.CN);
		}*/
        AppBankCard _appBankCard = appBankCardService.get(qf);
        if (_appBankCard != null) {
            appBankCardManage = common(_appBankCard);
        }

        return appBankCardManage;
    }

    @Override
    public RemoteResult saveBankCard(User user, AppBankCardManage appBankCardManage) {
        RemoteResult remoteResult = new RemoteResult();
        if (user != null) {
            Boolean flag = redisService.lock(user.getUsername()+"saveBankCard");
            if(flag){
                appBankCardManage.setCustomerId(user.getCustomerId());
                appBankCardManage.setCardName(appBankCardManage.getCardName());
                appBankCardManage.setSurName(appBankCardManage.getSurName());
                appBankCardManage.setTrueName(appBankCardManage.getTrueName());

                AppBankCardManage _appBankCard = this.get(appBankCardManage.getCardNumber());
                if (_appBankCard != null && _appBankCard.getId() != null && appBankCardManage.getType() == _appBankCard.getType()) {
                    remoteResult.setSuccess(false);
                    remoteResult.setMsg("zhanghaobunengchongfu");
                    return remoteResult;
                }

                AppAccountManage appAccount = remoteManageService.getByCustomerIdAndType(user.getCustomerId(), ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
                if (null != appAccount) {
                    appBankCardManage.setAccountId(appAccount.getId());
                    appBankCardManage.setUserName(user.getUsername());
                    appBankCardManage.setCurrencyType(appAccount.getCurrencyType());
                    appBankCardManage.setWebsite(appAccount.getWebsite());

                    this.save(appBankCardManage);
                    remoteResult.setSuccess(true);
                    remoteResult.setObj(appBankCardManage);
                    remoteResult.setMsg("success");
                } else {
                    remoteResult.setSuccess(false);
                    remoteResult.setMsg("xnzh_no_exist");
                }
                redisService.unLock(user.getUsername()+"saveBankCard");
            }


        } else {
            remoteResult.setSuccess(false);
            remoteResult.setMsg("user_no_exist");
        }
        return remoteResult;
    }

    //************************************************人民币提现*************************************************************

    @Override
    public BigDecimal countByDate(String[] type, String beginDate,
                                  String endDate, String[] status, String userName) {
        BigDecimal money = new BigDecimal(0);
        money = appTransactionService.countByDate(type, beginDate, endDate, status, userName);
        return money;
    }

    @Override
    public void rmbwithdraw(AppAccountManage appAccount, AppTransactionManage appTransactionManage) {
        AppTransaction appTransaction = common(appTransactionManage);
        appTransactionService.save(appTransaction);
        appAccountService.freezeAccountSelf(appAccount.getId(), appTransaction.getTransactionMoney().subtract(appTransaction.getFee()), appTransaction.getTransactionNum(), "人民币提现", null, null);
        appAccountService.freezeAccountSelf(appAccount.getId(), appTransaction.getFee(), appTransaction.getTransactionNum() + "-fee", "人民币提现手续费", null, null);

    }

    //提现 手续费
    public BigDecimal withdrawFee(BigDecimal amount, String type) {

        BigDecimal withdrawFeeRate = new BigDecimal(0);
        BigDecimal withdrawAmount = new BigDecimal(0);
        BigDecimal fee = new BigDecimal(0);
        RemoteAppConfigService service = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");

        String id2 = ContextUtil.getSaasId();
        RpcContext.getContext().setAttachment("saasId", id2);

        if (null != type && !"".equals(type)) {
            if (type.equals("1")) {
                type = "onlineWithdrawFeeRate";
            }
            List<AppConfig> list = service.getConfigInfo(type);
            if (list.size() > 0) {
                withdrawFeeRate = new BigDecimal(list.get(0).getValue());
            }
            if (null != amount && !"".equals(amount)) {
                withdrawAmount = amount;
            }
            fee = withdrawAmount.multiply(withdrawFeeRate).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        } else {

        }

        return fee;
    }

    /**
     * 提现记录保存
     * @param accountPassWord 交易密码
     * @param bankCardId 银行卡ID
     * @param withdrawCode 短信验证码
     * @param accountpwSmsCode
     * @param user
     * @return
     */
    @Override
    public RemoteResult rmbwithdraw(String accountPassWord, String bankCardId, String withdrawCode, String accountpwSmsCode, User user, String encryString, AppTransactionManage appTransaction) {

        if (DifCustomer.isInClosePlateAndClose()) {
            return new RemoteResult().setMsg("js_no_recharge");
        }

        String[] rt = exDmPingService.checkLending(user.getCustomerId());
        if (rt[0].equals(CodeConstant.CODE_FAILED)) {
            return new RemoteResult().setMsg("warning_money_ping");
        }

        AppBankCardManage bankCard = this.get(Long.valueOf(bankCardId));
        appTransaction.setCustromerAccountNumber(bankCard.getCardNumber());
        appTransaction.setBankNum(bankCard.getCardBank());
        RemoteResult remoteResult = new RemoteResult();

        String currencyType = ContextUtil.getCurrencyType();
        String website = ContextUtil.getWebsite();
        AppOurAccountManage ourAccount = remoteManageService.getOurAccount(website, currencyType, "0");
        if (user != null) {
            //判断用户是否低于平仓线
            QueryFilter PFilter = new QueryFilter(ExDmPing.class);
            PFilter.addFilter("customerId=", user.getCustomerId());
            PFilter.addFilter("status=", 1);
            List<ExDmPing> exDmPings = exDmPingService.find(PFilter);
            if (exDmPings != null && exDmPings.size() > 0) {
                remoteResult.setMsg("warning_ping");
                return remoteResult;
            }


            Integer customerType = user.getCustomerType();
            if (customerType.compareTo(Integer.valueOf(2)) != 0) {
                ContextUtil.setRemoteCurrencyType();
                ContextUtil.setRemoteWebsite();

				/*if (!accountpwSmsCode.equals(encryString)) {
					remoteResult.setSuccess(false);
					remoteResult.setMsg("交易密码不正确");
					return remoteResult;
				}*/

                AppAccountManage appAccount = remoteManageService.getByCustomerIdAndType(user.getCustomerId(), ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
                if (appAccount == null) {
                    remoteResult.setMsg("xlzh_is_null");
                    return remoteResult;
                }
                if (appTransaction.getTransactionMoney() == null) {
                    remoteResult.setSuccess(false);
                    remoteResult.setMsg("jymoney_is_null");
                    return remoteResult;
                }
                if (appTransaction.getTransactionMoney().compareTo(appAccount.getHotMoney()) > 0) {
                    remoteResult.setSuccess(false);
                    remoteResult.setMsg("jym_gt_xnzhm");
                    return remoteResult;
                }

                //判断最大提现额度
                String beginDate = DateUtil.dateToString(new Date(), "yyyy-MM-dd 00:00:00");
                String endDate = DateUtil.dateToString(DateUtil.addDay(new Date(), 1), "yyyy-MM-dd 00:00:00");
                BigDecimal allMoney = countByDate(new String[]{"4", "2"}, beginDate, endDate, new String[]{"1", "2"}, user.getMobile());
                BigDecimal maxMoney = null;

                RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
                String config = redisService.get("configCache:all");
                JSONObject parseObject = JSONObject.parseObject(config);
                maxMoney = new BigDecimal(parseObject.get("maxWithdrawMoney").toString());
                logger.error("today_max" + ":" + allMoney + "      " + maxMoney);
                if (allMoney.add(appTransaction.getTransactionMoney()).compareTo(maxMoney) > 0) {
                    return new RemoteResult().setMsg("tx_gt_today_max");
                }

                BigDecimal maxWithdrawMoneyOneTime = new BigDecimal(parseObject.get("maxWithdrawMoneyOneTime").toString());
                if (appTransaction.getTransactionMoney().compareTo(maxWithdrawMoneyOneTime) > 0) {
                    return new RemoteResult().setMsg("tx_gt_one!");
                }


                // 交易类型 线下提现
                appTransaction.setTransactionType(4);
                // 设置备注
                appTransaction.setRemark(RandomStringUtils.random(8, false, true));
                //设置我方账号
                appTransaction.setOurAccountNumber(ourAccount.getAccountNumber());
                // 设置用户id
                appTransaction.setCustomerId(user.getCustomerId());
                // 用户登录名
                appTransaction.setUserName(user.getMobile());
                // 真实姓名
                appTransaction.setTrueName(user.getTruename());
                //姓氏
                appTransaction.setSurname(user.getSurname());
                // 交易订单号
                appTransaction.setTransactionNum(transactionNum("01"));
                //手续费
                BigDecimal fee = withdrawFee(appTransaction.getTransactionMoney(), "1");
                appTransaction.setFee(fee);
                //实际到账金额
                appTransaction.setTransactionMoney(appTransaction.getTransactionMoney());
                // 设置账户ID
                appTransaction.setAccountId(appAccount.getId());
                appTransaction.setCurrencyType(appAccount.getCurrencyType());
                appTransaction.setWebsite(appAccount.getWebsite());
                appTransaction.setStyle(0);


                //判断提现金额是否超过该用户的提现审核额度
                //当提现接口没有开启或者接口开启但提现超过审核额度都进入后台审核
                //进行审核 状态 待审核
                appTransaction.setStatus(1);


                //两次调用改成一次调用(保存流水，冻结金额)
//				RpcContext.getContext().setAttachment("saasId", ContextUtil.getSaasId());
//				this.rmbwithdraw(appAccount,appTransaction);


                //保存提现记录
                AppTransaction at = common(appTransaction);
                appTransactionService.save(at);

                //----发送mq消息----start
                //热账户减少
                Accountadd accountadd = new Accountadd();
                accountadd.setAccountId(appTransaction.getAccountId());
                accountadd.setMoney(appTransaction.getTransactionMoney().multiply(new BigDecimal(-1)));
                accountadd.setMonteyType(1);
                accountadd.setRemarks(32);
                accountadd.setAcccountType(0);
                accountadd.setTransactionNum(appTransaction.getTransactionNum());

                //冷账户增加
                Accountadd accountadd2 = new Accountadd();
                accountadd2.setAccountId(appTransaction.getAccountId());
                accountadd2.setMoney(appTransaction.getTransactionMoney());
                accountadd2.setMonteyType(2);
                accountadd2.setAcccountType(0);
                accountadd2.setRemarks(32);
                accountadd2.setTransactionNum(appTransaction.getTransactionNum());
                List<Accountadd> list = new ArrayList<Accountadd>();
                list.add(accountadd);
                list.add(accountadd2);
                messageProducer.toAccount(JSON.toJSONString(list));
                //----发送mq消息----end


                remoteResult.setSuccess(true);
                remoteResult.setObj(appTransaction);
                remoteResult.setMsg("tx_success");

            } else {
                remoteResult.setSuccess(false);
                remoteResult.setMsg("yi_no_tx");
            }
            return remoteResult;
        } else {
            remoteResult.setSuccess(false);
            remoteResult.setMsg("before_login");
        }
        return remoteResult;
    }

    //********************************************************虚拟货币*******************************************************************

    @Override
    public FrontPage findExdmtransaction(Map<String, String> map) {
        return exDmTransactionService.findExdmtransaction(map);
    }

    /**
     * 7.0 个人中心-我要充币/提币-充币/提币记录查询方法
     * @param params
     * @return
     */
    @Override
    public FrontPage findExdmtransactionRecord (Map<String, String> params) {
        return exDmTransactionService.findExdmtransactionRecord(params);
    }

    @Override
    public List<ExDmCustomerPublicKeyManage> listPublic(Long id) {
        QueryFilter qf = new QueryFilter(ExDmCustomerPublicKey.class);
        qf.addFilter("customerId=", id);
        List<ExDmCustomerPublicKey> list = exDmCustomerPublicKeyService.find(qf);
        List<ExDmCustomerPublicKeyManage> listmanage = new ArrayList<ExDmCustomerPublicKeyManage>();
        for (int i = 0; i < list.size(); i++) {
            ExDmCustomerPublicKeyManage ex = common(list.get(i));
            listmanage.add(ex);
        }
        return listmanage;
    }

    @Override
    public List<ExProductManage> listProduct() {
        QueryFilter qf = new QueryFilter(ExProduct.class);
        qf.addFilter("issueState=", 1);
        List<ExProduct> list = exProductService.find(qf);
        List<ExProductManage> listmanage = new ArrayList<ExProductManage>();
        for (int i = 0; i < list.size(); i++) {
            ExProductManage ex = common(list.get(i));
            listmanage.add(ex);
        }
        return listmanage;
    }


    @Override
    public void deletePublieKey(Long id) {
        exDmCustomerPublicKeyService.delete(id);
    }

    @Override
    public RemoteResult deletePublieKey(Long id, String cusId) {
        ExDmCustomerPublicKey exDmCustomerPublicKey = exDmCustomerPublicKeyService.get(id);
        if (exDmCustomerPublicKey != null && (!exDmCustomerPublicKey.getCustomerId().toString().equals(cusId))) {
            return new RemoteResult().setSuccess(false).setMsg("不可删除他人币账户");
        }
        exDmCustomerPublicKeyService.delete(id);
        return new RemoteResult().setSuccess(true);
    }


    //币账户查询
    @Override
    public List<ExDigitalmoneyAccountManage> listexd(Long id, String language) {

        AppCustomer appCustomer = appCustomerService.get(id);
        AppPersonInfo appPersonInfo = appPersonInfoService.get(new QueryFilter(AppPersonInfo.class).addFilter("customerId=", appCustomer.getId()));
        //开通虚拟币账户
//		RemoteExProductService remoteExProductService = (RemoteExProductService) ContextUtil.getBean("remoteExProductService");
//		remoteExProductService.openDmAccount(appCustomer,appPersonInfo,null,ContextUtil.getWebsite(),language);
        // 获取下架币种
        List<ExProduct> plist = exProductService.findByIssueState(2,"");
        QueryFilter qf = new QueryFilter(ExDigitalmoneyAccount.class);
        qf.addFilter("customerId=", id);
        if (plist != null && plist.size() > 0) {
            String str = "";
            for (ExProduct ex : plist) {
                str += "," + ex.getCoinCode();
            }
            qf.addFilter("coinCode_notin", str.substring(1));
        }
        List<ExDigitalmoneyAccount> list = exDigitalmoneyAccountService.find(qf);
        List<ExDigitalmoneyAccountManage> listex = new ArrayList<ExDigitalmoneyAccountManage>();
        for (int i = 0; i < list.size(); i++) {
            listex.add(common(list.get(i)));
        }

        for (ExDigitalmoneyAccountManage obj : listex) {
            logger.error("obj.getCoinCode() = [" + obj.getCoinCode() + "]");
            ExProduct exProduct = exProductService.get(new QueryFilter(ExProduct.class).addFilter("coinCode=", obj.getCoinCode()));
            if (exProduct == null) {
                continue;
            }
            //提币手续费
            //obj.setPaceFeeRate(exProduct.getPaceFeeRate().divide(new BigDecimal(100),8,BigDecimal.ROUND_HALF_DOWN));
            String currecy = (null == exProduct.getPaceCurrecy()) ? "0" : exProduct.getPaceCurrecy();
            if(currecy.contains(",")){
                currecy = currecy.split(",")[0];
            }
            obj.setPaceCurrecy(new BigDecimal(currecy).divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_DOWN));
            //单次最小提币
            obj.setLeastPaceNum(exProduct.getLeastPaceNum() == null ? new BigDecimal(0) : exProduct.getLeastPaceNum());
            //一日最大提币
            obj.setOneDayPaceNum(exProduct.getOneDayPaceNum() == null ? new BigDecimal(0) : exProduct.getOneDayPaceNum());
        }


        return listex;
    }


    //币账户查询
    @Override
    public List<ExDigitalmoneyAccountManage> listcoin(String coinCode) {

        List<ExDigitalmoneyAccountManage> listex = new ArrayList<ExDigitalmoneyAccountManage>();

        //提币手续费
        for (ExDigitalmoneyAccountManage obj : listex) {
            ExProduct exProduct = exProductService.get(new QueryFilter(ExProduct.class).addFilter("coinCode=", coinCode));
            //提币手续费
            obj.setPaceFeeRate(exProduct.getPaceFeeRate());
            //单次最小提币
            obj.setLeastPaceNum(exProduct.getLeastPaceNum() == null ? new BigDecimal(0) : exProduct.getLeastPaceNum());
            //一日最大提币
            obj.setOneDayPaceNum(exProduct.getOneDayPaceNum() == null ? new BigDecimal(0) : exProduct.getOneDayPaceNum());
        }
        return listex;
    }

    @Override
    public String getRetentionNumberByProduct(String coinCode) {
        QueryFilter productFilter = new QueryFilter(ExProduct.class);
        productFilter.addFilter("coinCode=", coinCode);
        ExProduct exProduct = exProductService.get(productFilter);
        return exProduct.getKeepDecimalForCoin().toString();
    }

    @Override
    public int findSaveFlag(String cardNumber, String s, Integer type) {
        return appBankCardService.findSaveFlag(cardNumber, type);
    }

    @Override
    public List<ExDmCustomerPublicKeyManage> getList(Long id) {
        QueryFilter qf = new QueryFilter(ExDmCustomerPublicKey.class);
        qf.addFilter("customerId=", id);
        List<ExDmCustomerPublicKey> list = exDmCustomerPublicKeyService.find(qf);
        List<ExDmCustomerPublicKeyManage> listex = new ArrayList<ExDmCustomerPublicKeyManage>();
        for (int i = 0; i < list.size(); i++) {
            listex.add(common(list.get(i)));
        }
        return listex;
    }

    @Override
    public void save(ExDmCustomerPublicKeyManage exDmCustomerPublicKeyManage, User user) {
        exDmCustomerPublicKeyManage.setCreated(new Date());
        exDmCustomerPublicKeyManage.setPublicKeyName(user.getUsername());
        exDmCustomerPublicKeyManage.setCustomerId(user.getCustomerId());
        exDmCustomerPublicKeyManage.setModified(new Date());
        exDmCustomerPublicKeyManage.setTrueName(user.getTruename());
        exDmCustomerPublicKeyManage.setSurname(user.getSurname());
        ExDmCustomerPublicKey e = common(exDmCustomerPublicKeyManage);
        exDmCustomerPublicKeyService.save(e);
    }

    public BigDecimal productWithdrawFeeRate(String coinCode) {
        BigDecimal rate = new BigDecimal(0);
        RemoteQueryFilter filter = new RemoteQueryFilter(ExProduct.class);
        filter.addFilter("coinCode=", coinCode);
        filter.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
        ExProduct product = remoteExProductService.findByCoinCode(filter);
        rate = product.getPaceFeeRate();
        return rate;
    }


    //*************************************************************生成充值汇款单*********************************************************
    @Override
    public RemoteResult generateRmbdeposit(String surname, String remitter, String bankCode, String bankAmount, String bankName, AppTransactionManage appTransaction, User user) {

        if (DifCustomer.isInClosePlateAndClose()) {
            return new RemoteResult().setMsg("cz_no_allow");
        }

        appTransaction.setUserName(user.getUsername());
        // saasId
        //appTransaction.setSaasId(appCustomer.getSaasId());
        // 产生一个8位随机数
        appTransaction.setRemark(RandomStringUtils.random(4, false, true));
        // 设置用户id
        appTransaction.setCustomerId(user.getCustomerId());
        // 用户登录名
        appTransaction.setUserName(user.getUsername());
        appTransaction.setTransactionNum(transactionNum("01"));//成功
        appTransaction.setTransactionType(3);//线下充值
        //金额
        appTransaction.setTransactionMoney(new BigDecimal(bankAmount));
        appTransaction.setStatus(1);//待审核
        appTransaction.setBankNum(bankName);
        appTransaction.setStyle(0);//银行卡充值
        appTransaction.setSurname(remitter);
        appTransaction.setTrueName(surname);
        appTransaction.setCustromerAccountNumber(bankCode);//卡号

        appTransaction.setCardHolder(user.getTruename());
        appTransaction.setFee(new BigDecimal(0));//手续费
        AppAccountManage appAccount = remoteManageService.getByCustomerIdAndType(user.getCustomerId(), ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
        QueryFilter filter = new QueryFilter(AppOurAccount.class);
        filter.addFilter("isShow=", "1");
        filter.addFilter("currencyType=", "cny");
        List<AppOurAccount> listout = appOurAccountService.find(filter);
        if (listout != null && listout.size() > 0) {
            if (appAccount != null) {

                // 设置账户ID -- 虚拟账户ID
                appTransaction.setAccountId(appAccount.getId());
                Date date = new Date();
                appTransaction.setCreated(date);
                //设置默认的账户类型
                appTransaction.setCurrencyType(appAccount.getCurrencyType());
                //设置默认站点
                appTransaction.setWebsite(appAccount.getWebsite());

                appTransaction.setBankAddress(listout.get(0).getBankAddress());
                appTransaction.setAccountName(listout.get(0).getAccountName());
                appTransaction.setBankName(listout.get(0).getBankName());
                appTransaction.setAccountNumber(listout.get(0).getAccountNumber());
                appTransaction.setOurAccountNumber(listout.get(0).getAccountNumber());

                RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
                String config = redisService.get("configCache:all");
                if (!StringUtils.isEmpty(config)) {
                    JSONObject parseObject = JSONObject.parseObject(config);
                    String object = (String) parseObject.get("rechargeFeeRate");//线下充值手续费
                    BigDecimal fee = new BigDecimal(object).multiply(appTransaction.getTransactionMoney()).divide(new BigDecimal(100), 2);
                    appTransaction.setFee(fee);
                }
                this.save(appTransaction);

                return new RemoteResult().setSuccess(true).setObj(appTransaction).setMsg(appTransaction.getTransactionNum() + "," + appTransaction.getUserName());
            }
            return new RemoteResult().setSuccess(false).setMsg("xnzh_no_exist");
        }
        return new RemoteResult().setSuccess(false).setMsg("our_no_exist");

    }

    //****************************************************************************************************************************

    public ExDigitalmoneyAccountManage common(ExDigitalmoneyAccount e) {
        ExDigitalmoneyAccountManage ex = new ExDigitalmoneyAccountManage();
        ex.setId(e.getId());
        ex.setHotMoney(e.getHotMoney());
        ex.setColdMoney(e.getColdMoney());
        ex.setPublicKey(e.getPublicKey());
        ex.setCreated(e.getCreated());
        ex.setCoinName(e.getCoinName());
        ex.setCoinCode(e.getCoinCode());
        ex.setCurrencyType(e.getCurrencyType());
        return ex;
    }

    public ExDmCustomerPublicKey common(ExDmCustomerPublicKeyManage e) {
        ExDmCustomerPublicKey ex = new ExDmCustomerPublicKey();
        ex.setCreated(e.getCreated());
        ex.setCurrencyType(e.getCurrencyType());
        ex.setCustomerId(e.getCustomerId());
        ex.setId(e.getId());
        ex.setModified(e.getModified());
        ex.setPublicKey(e.getPublicKey());
        ex.setPublicKeyName(e.getPublicKeyName());
        ex.setRemark(e.getRemark());
        return ex;
    }

    public ExProductManage common(ExProduct e) {
        ExProductManage ex = new ExProductManage();
        ex.setCoinCode(e.getCoinCode());
        return ex;
    }

    public ExDmCustomerPublicKeyManage common(ExDmCustomerPublicKey e) {
        ExDmCustomerPublicKeyManage ex = new ExDmCustomerPublicKeyManage();
        ex.setCreated(e.getCreated());
        ex.setCurrencyType(e.getCurrencyType());
        ex.setCustomerId(e.getCustomerId());
        ex.setId(e.getId());
        ex.setModified(e.getModified());
        ex.setPublicKey(e.getPublicKey());
        ex.setPublicKeyName(e.getPublicKeyName());
        ex.setRemark(e.getRemark());
        return ex;
    }

    public AppTransaction common(AppTransactionManage appTransactionManage) {
        AppTransaction appTransaction = new AppTransaction();
        appTransaction.setAccountId(appTransactionManage.getAccountId());
        appTransaction.setBankAddress(appTransactionManage.getBankAddress());
        appTransaction.setBankName(appTransactionManage.getBankName());
        appTransaction.setBankNum(appTransactionManage.getBankNum());
        appTransaction.setBankProvince(appTransactionManage.getBankProvince());
        appTransaction.setCardHolder(appTransactionManage.getCardHolder());
        appTransaction.setCreated(appTransactionManage.getCreated());
        appTransaction.setCurrencyType(appTransactionManage.getCurrencyType());
        appTransaction.setCustomerId(appTransactionManage.getCustomerId());
        appTransaction.setCustromerAccountNumber(appTransactionManage.getCustromerAccountNumber());
        appTransaction.setFee(appTransactionManage.getFee());
        appTransaction.setId(appTransactionManage.getId());
        appTransaction.setModified(appTransactionManage.getModified());
        appTransaction.setOurAccountNumber(appTransactionManage.getOurAccountNumber());
        appTransaction.setReadyStates(appTransactionManage.getReadyStates());
        appTransaction.setRejectionReason(appTransactionManage.getRejectionReason());
        appTransaction.setRemark(appTransactionManage.getRemark());
        appTransaction.setStatus(appTransactionManage.getStatus());
        appTransaction.setStyle(appTransactionManage.getStyle());
        appTransaction.setSubBank(appTransactionManage.getSubBank());
        appTransaction.setSubBankNum(appTransactionManage.getSubBankNum());
        appTransaction.setThirdPayName(appTransactionManage.getThirdPayName());
        appTransaction.setTransactionMoney(appTransactionManage.getTransactionMoney());
        appTransaction.setTransactionNum(appTransactionManage.getTransactionNum());
        appTransaction.setTransactionType(appTransactionManage.getTransactionType());
        appTransaction.setTrueName(appTransactionManage.getTrueName());
        appTransaction.setUserId(appTransactionManage.getUserId());
        appTransaction.setUserName(appTransactionManage.getUserName());
        appTransaction.setWebsite(appTransactionManage.getWebsite());
        appTransaction.setSurname(appTransactionManage.getSurname());
        return appTransaction;
    }


    public AppBankCardManage common(AppBankCard _appBankCard) {
        AppBankCardManage appBankCardManage = new AppBankCardManage();
        appBankCardManage.setId(_appBankCard.getId());
        appBankCardManage.setAccountId(_appBankCard.getAccountId());
        appBankCardManage.setBankAddress(_appBankCard.getBankAddress());
        appBankCardManage.setBankProvince(_appBankCard.getBankProvince());
        appBankCardManage.setCardBank(_appBankCard.getCardBank());
        appBankCardManage.setCardName(_appBankCard.getCardName());
        appBankCardManage.setCardNumber(_appBankCard.getCardNumber());
        appBankCardManage.setCreated(_appBankCard.getCreated());
        appBankCardManage.setCurrencyType(_appBankCard.getCurrencyType());
        appBankCardManage.setCustomerId(_appBankCard.getCustomerId());
        appBankCardManage.setModified(_appBankCard.getModified());
        appBankCardManage.setSignBank(_appBankCard.getSignBank());
        appBankCardManage.setSubBank(_appBankCard.getSubBank());
        appBankCardManage.setSubBankNum(_appBankCard.getSubBankNum());
        appBankCardManage.setTrueName(_appBankCard.getTrueName());
        appBankCardManage.setUserName(_appBankCard.getUserName());
        appBankCardManage.setWebsite(_appBankCard.getWebsite());
        appBankCardManage.setSurName(_appBankCard.getSurname());

        return appBankCardManage;
    }

    public AppBankCard common(AppBankCardManage appBankCardManage) {
        AppBankCard appBankCard = new AppBankCard();
        appBankCard.setId(appBankCardManage.getId());
        appBankCard.setAccountId(appBankCardManage.getAccountId());
        appBankCard.setBankAddress(appBankCardManage.getBankAddress());
        appBankCard.setBankProvince(appBankCardManage.getBankProvince());
        appBankCard.setCardBank(appBankCardManage.getCardBank());
        appBankCard.setCardName(appBankCardManage.getCardName());
        appBankCard.setCardNumber(appBankCardManage.getCardNumber());
        appBankCard.setCreated(appBankCardManage.getCreated());
        appBankCard.setCurrencyType(appBankCardManage.getCurrencyType());
        appBankCard.setCustomerId(appBankCardManage.getCustomerId());
        appBankCard.setModified(appBankCardManage.getModified());
        appBankCard.setSignBank(appBankCardManage.getSignBank());
        appBankCard.setSubBank(appBankCardManage.getSubBank());
        appBankCard.setSubBankNum(appBankCardManage.getSubBankNum());
        appBankCard.setTrueName(appBankCardManage.getTrueName());
        appBankCard.setSurname(appBankCardManage.getSurName());
        appBankCard.setUserName(appBankCardManage.getUserName());
        appBankCard.setWebsite(appBankCardManage.getWebsite());

        return appBankCard;
    }

    public static String transactionNum(String bussType) {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        String time = format.format(new Date(System.currentTimeMillis()));
        String randomStr = RandomStringUtils.random(4, false, true);
        if (null == bussType) {
            bussType = "00";
        }
        return bussType + time + randomStr;
    }


    @Override
    public String[] testconfirmRmbRecharge(String surname, String remitter, String bankCode, String bankAmount, String bankName, AppTransactionManage appTransaction, User user) {
        String[] rt = new String[2];
        if (StringUtils.isEmpty(bankCode)) {
            bankCode = "testcode";
        }
        if (StringUtils.isEmpty(bankName)) {
            bankCode = "testbankName";
        }
        RemoteResult remoteResult = this.generateRmbdeposit(surname, remitter, bankCode, bankAmount, bankName, appTransaction, user);
        if (remoteResult.getObj() != null) {
            String[] orderuse = remoteResult.getMsg().split(",");
            QueryFilter fil = new QueryFilter(AppTransaction.class);
            fil.addFilter("transactionNum=", orderuse[0]);
            fil.addFilter("userName=", orderuse[1]);
            List<AppTransaction> list = appTransactionService.find(fil);
            if (null != list && list.size() > 0) {
                AppTransaction a = list.get(0);
                boolean confirm = appTransactionService.confirm(a.getId(), user.getUsername());
                if (confirm) {
                    rt[0] = "true";
                    rt[1] = "success";
                } else {
                    rt[0] = "false";
                    rt[1] = "error";
                }
            } else {
                rt[0] = "false";
                rt[1] = "error";
            }

        } else {
            rt[0] = "false";
            rt[1] = "error";
        }

        return rt;
    }

    @Override
    public String[] testCoinRecharge(String coinCode, String amount, User user) {
        String[] rt = new String[2];
        String account = "";
        try {
            account = user.getUsername();
            RemoteExAmineOrderService remoteExAmineOrderService = (RemoteExAmineOrderService) ContextUtil.getBean("remoteExAmineOrderService");
            RemoteExDmTransactionService remoteExDmTxService = (RemoteExDmTransactionService) ContextUtil.getBean("remoteExDmTransactionService");
            RemoteExDigitalmoneyAccountService remoteExDigService = (RemoteExDigitalmoneyAccountService) ContextUtil.getBean("remoteExDigitalmoneyAccountService");
            ExDigitalmoneyAccount exDigitalmoneyAccount = remoteExDigService.findByCustomerType(user.getCustomerId(), coinCode, "cny", "cn");

            if (null != exDigitalmoneyAccount) {
                ExDmTransaction exDmTransaction = new ExDmTransaction();
                exDmTransaction.setAccountId(exDigitalmoneyAccount.getId());
                exDmTransaction.setCoinCode(coinCode);
                exDmTransaction.setCreated(new Date());
                exDmTransaction.setCurrencyType(exDigitalmoneyAccount.getCurrencyType());
                exDmTransaction.setCustomerId(exDigitalmoneyAccount.getCustomerId());
                exDmTransaction.setCustomerName(exDigitalmoneyAccount.getUserName());
                exDmTransaction.setTime("test");
                exDmTransaction.setTimereceived("test");
                exDmTransaction.setInAddress(exDigitalmoneyAccount.getPublicKey());
                exDmTransaction.setConfirmations("test");
                exDmTransaction.setBlocktime("test");
                exDmTransaction.setFee(new BigDecimal("0"));
                exDmTransaction.setTransactionMoney(new BigDecimal(amount));
                exDmTransaction.setStatus(2);
                exDmTransaction.setOrderNo("test" + IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
                exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
                // 充币
                exDmTransaction.setTransactionType(1);
                exDmTransaction.setOptType(1);
                exDmTransaction.setUserId(exDigitalmoneyAccount.getCustomerId());
                exDmTransaction.setWebsite(exDigitalmoneyAccount.getWebsite());

                ExDmTransaction txs = null;

                txs = remoteExDmTxService.getTransaction(exDmTransaction.getCustomerName(), exDmTransaction.getOrderNo());
                if (null == txs) {
                    logger.error("保存交易订单！！！");
                    //保存交易订单
                    remoteExDmTxService.saveTransaction(exDmTransaction);
                    txs = remoteExDmTxService.getTransaction(exDmTransaction.getCustomerName(), exDmTransaction.getOrderNo());
                    //修改资产信息
                    remoteExAmineOrderService.chargeAccount(txs);

                    txs = null;
                }
                rt[0] = "true";
                return rt;
            } else {
                rt[0] = "false";
                rt[1] = "虚拟币账户为空";
                return rt;
            }
        } catch (Exception e) {
            logger.error("异常:" + e.getMessage());
            e.printStackTrace();
        }
        rt[0] = "false";
        return rt;
    }

    @Override
    public BigDecimal witfee() {
        RemoteAppConfigService service = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
        List<AppConfig> list = service.getConfigInfo("onlineWithdrawFeeRate");
        if (list.size() > 0) {
            return new BigDecimal(list.get(0).getValue());
        }
        return new BigDecimal(0);
    }

    @Override
    public String[] lmcTransfer(LmcTransfer lmcTransfer) {
        return null;
    }

    @Override
    public String[] walletTransferSum(LmcTransfer transfer) {
        return null;
    }

    @Override
    public String[] listwalletTransfer(LmcTransfer transfer) {
        return null;
    }

    @Override
    public List<ExDmCustomerPublicKeyManage> getList(Long customerId, String coinCode) {

        QueryFilter qf = new QueryFilter(ExDmCustomerPublicKey.class);
        qf.addFilter("customerId=", customerId);
        qf.addFilter("currencyType=", coinCode);
        List<ExDmCustomerPublicKey> list = exDmCustomerPublicKeyService.find(qf);
        List<ExDmCustomerPublicKeyManage> listex = new ArrayList<ExDmCustomerPublicKeyManage>();
        for (int i = 0; i < list.size(); i++) {
            listex.add(common(list.get(i)));
        }
        return listex;

    }

    @Override
    public RemoteResult getOrdervail(User user, String code, String accountpwSmsCode, String sessionAccountpwSmsCode, String btcNum, String type, String username, String btcKey) {
        RemoteResult remoteResult = new RemoteResult();
        ExProduct product = remoteExProductService.findByCoinCode(code, "");
        ContextUtil.setRemoteCurrencyType();
        ContextUtil.setRemoteWebsite();
        remoteResult.setSuccess(false);
        // 判断当前用户是否已经登录
        if (user == null) {
            remoteResult.setMsg("before_login");
            return remoteResult;
        }

        String[] rt = exDmPingService.checkLending(user.getCustomerId());
        if (rt[0].equals(CodeConstant.CODE_FAILED)) {
            return new RemoteResult().setMsg("warning_money_ping");
        }


        //AppPersonInfo appPersonInfo = appPersonInfoService.get(user.getCustomerId());
        AppPersonInfo appPersonInfo = appPersonInfoService.getByCustomerId(user.getCustomerId());
        int t = appPersonInfo.getCustomerType();
        if (t != 2) {
            RpcContext.getContext().setAttachment("saasId", ContextUtil.getSaasId());
            AppAccountManage appAccount = remoteManageService.getByCustomerIdAndType(user.getCustomerId(), ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
            if (appAccount.getHotMoney().compareTo(new BigDecimal("0.00")) >= 0) {
                // 判断该用户是否已经有虚拟账户
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(user.getCustomerId().toString());
                ExDigitalmoneyAccountRedis digAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(code).toString(), code);

                if (digAccount == null) {
                    remoteResult.setMsg("xnzh_no_exist");// 虚拟账户不存在
                    return remoteResult;
                }
                // 获取前台传的num数值 小数精确到小数点后5位
                // 判断可用余额是否大于转账的金额
                BigDecimal bigBtcNum = new BigDecimal(btcNum);
                // 可用的总数量
                BigDecimal hotMoney = digAccount.getHotMoney();
                // 每次提币的最小数量
                BigDecimal leastPaceNum = product.getLeastPaceNum();
                // 每日最大提币数量
                BigDecimal oneDayPaceNum = product.getOneDayPaceNum();
                BigDecimal todayGetNum = exDmTransactionService.findTransactionByCustomer(user.getCustomerId(), code, "2");
                // 判断是否小于可用余额
                int i2 = hotMoney.compareTo(bigBtcNum);
                // 判断是否大于单笔最小提现数量
                int i3 = leastPaceNum.compareTo(bigBtcNum);
                // 判断是否小于单日提现数量
                int i4 = (todayGetNum.add(bigBtcNum)).compareTo(oneDayPaceNum);

                if (i2 == -1) {
                    remoteResult.setMsg("tb_gt_maxavailable");// 提现的金额超出了最大可用余额
                    return remoteResult;
                }
                if (i3 == 1) {//提币小于单笔提币数量
                    remoteResult.setMsg("tb_nlt_timemin");
                    return remoteResult;
                }
                if (i4 == 1) {
                    remoteResult.setMsg("tb_ngt_todaymax");
                    return remoteResult;
                }
                ContextUtil.setRemoteCurrencyType();
                ContextUtil.setRemoteWebsite();


            } else {
                remoteResult.setMsg("tixianshuliangbunengweikong");//提现数量不能为空
                return remoteResult;
            }
        } else {
            // 返回false
            remoteResult.setMsg("yileizhanghubunengtibi");//乙类账户不允许提币
            return remoteResult;
        }
        return remoteResult.setSuccess(true);
    }


    @Override
    public FrontPage selectFee(Map<String, String> params) {
        return exOrderInfoService.selectFee(params);
    }

    @Override
    public FrontPage frontselectFee(Map<String, String> params) {
        return exOrderInfoService.frontselectFee(params);
    }

    /**
     * 用户提币
     * @param user
     * @param code    coinCode
     * @param btcNum
     * @param type
     * @param username
     * @param btcKey
     * @param pacecurrecy    手续费/费率
     * @return
     */
    @Override
    public RemoteResult getOrder(User user, String code,
                                 String btcNum, String type, String username, String btcKey, String pacecurrecy) {
        //手动配置手续费参数校验
        QueryFilter productFilter = new QueryFilter(ExProduct.class);
        productFilter.addFilter("coinCode=", code);
        ExProduct exProduct = exProductService.get(productFilter);
        String currecy = (null == exProduct.getPaceCurrecy()) ? "0" : exProduct.getPaceCurrecy();
        //提币数量
        BigDecimal bigBtcNum = new BigDecimal(btcNum).setScale(exProduct.getKeepDecimalForCoin(), BigDecimal.ROUND_DOWN);
        if ("2".equals(exProduct.getPaceType())) {
            List<String> currecys = Arrays.asList(currecy.split(","));
            if (!currecys.contains(pacecurrecy)) {
                return new RemoteResult().setSuccess(false).setMsg("feifacaozuo");
            }
        } else if ("1".equals(exProduct.getPaceType())) {
            BigDecimal fee = new BigDecimal(exProduct.getPaceCurrecy()).divide(new BigDecimal(100)).multiply(bigBtcNum).setScale(exProduct.getKeepDecimalForCoin(), BigDecimal.ROUND_DOWN);
            if (fee.compareTo(new BigDecimal(pacecurrecy)) != 0) {
                return new RemoteResult().setSuccess(false).setMsg("feifacaozuo");
            }
        }
        RemoteResult remoteResult = new RemoteResult();
        ExProduct product = remoteExProductService.findByCoinCode(code, "");

        // 判断当前用户是否已经登录
        if (user == null) {
            remoteResult.setMsg("before_login");
            return remoteResult;
        }

        AppPersonInfo appPersonInfo = appPersonInfoService.getByCustomerId(user.getCustomerId());
        int t = appPersonInfo.getCustomerType();
        if (t != 2) {
            // 获得人民币账户
            RpcContext.getContext().setAttachment("saasId", ContextUtil.getSaasId());
            AppAccountManage appAccount = remoteManageService.getByCustomerIdAndType(user.getCustomerId(), ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
            if (appAccount.getHotMoney().compareTo(BigDecimal.ZERO) >= 0) {
                // 判断该用户是否已经有虚拟账户
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(user.getCustomerId().toString());
                ExDigitalmoneyAccountRedis digAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(code).toString(), code);
                if (digAccount == null) {
                    // 虚拟账户不存在
                    remoteResult.setMsg("xnzh_no_exist");
                    return remoteResult;
                }

                // 可用的总数量
                BigDecimal hotMoney = digAccount.getHotMoney();
                // 每次提币的最小数量
                BigDecimal leastPaceNum = product.getLeastPaceNum();
                // 每日最大提币数量
                BigDecimal oneDayPaceNum = product.getOneDayPaceNum();

                BigDecimal todayGetNum = exDmTransactionService.findTransactionByCustomer(user.getCustomerId(), code, "2");

                // 判断是否小于可用余额
                if (hotMoney.compareTo(bigBtcNum) == -1) {
                    remoteResult.setMsg("tb_gt_maxavailable");// 提现的金额超出了最大可用余额
                    return remoteResult;
                }
                // 判断是否大于单笔最小提现数量
                if (leastPaceNum.compareTo(bigBtcNum) == 1) {//提币小于单笔提币数量
                    remoteResult.setMsg("tb_nlt_timemin");
                    return remoteResult;
                }
                // 判断是否小于单日提现数量
                if ((todayGetNum.add(bigBtcNum)).compareTo(oneDayPaceNum) == 1) {
                    remoteResult.setMsg("tb_ngt_todaymax");
                    return remoteResult;
                }

                /*~~~~~~~~~李超业务---提币数量是否是最小提币的倍数~~~start~~~~~~~*/
                BigDecimal multiple = bigBtcNum.divide(leastPaceNum,8,BigDecimal.ROUND_HALF_UP);
                if (new BigDecimal(multiple.intValue()).compareTo(multiple) !=0 ) {
                    remoteResult.setMsg("tibibeishu");
                    return remoteResult;
                }
                /*~~~~~~~~~李超业务---提币数量是否是最小提币的倍数~~~end~~~~~~~*/

                /*~~~~~~~~~李超业务---校验账户金额是否异常~~~start~~~~~~~*/
                CustomerFreeze freeze = customerFreezeService.getCustomerFreeze(user.getCustomerId());
                if (freeze != null) {
                    // 异步平账
                    ThreadPool.exe(new AgainPutIntoRunnable(user.getCustomerId()));
                    remoteResult.setMsg("zhanghuzijinyichang");
                    return remoteResult;
                }
                /*~~~~~~~~~李超业务---校验账户金额是否异常~~~end~~~~~~~*/



                // 创建包装类
                OrderParameter orderParameter = new OrderParameter();
                //手续费
                BigDecimal fee;
                if ("1".equals(exProduct.getPaceType())) {    //固定费率
                    BigDecimal bd = new BigDecimal(pacecurrecy);
                    fee = bd;
                    bigBtcNum = bigBtcNum.subtract(fee);
                } else if ("2".equals(exProduct.getPaceType())) {    //手动配置
                    BigDecimal bd = new BigDecimal(pacecurrecy);
                    bigBtcNum = bigBtcNum.subtract(bd);
                    fee = bd;
                } else {
                    fee = BigDecimal.ZERO;
                }
                if (bigBtcNum.compareTo(BigDecimal.ZERO) < 1) {
                    remoteResult.setMsg("tb_gt_minZero");// 提币数量减去手续费后是否为负数
                    return remoteResult;
                }

                orderParameter.setCurrencyId(digAccount.getId());
                orderParameter.setCustomerId(user.getCustomerId());
                orderParameter.setTrueName(appPersonInfo.getTrueName());
                orderParameter.setSurname(appPersonInfo.getSurname());
                orderParameter.setCurrencyNum(bigBtcNum);
                orderParameter.setType(type);
                orderParameter.setCustomerName(username);
                orderParameter.setFee(fee);

                //提币地址
                orderParameter.setCurrencyKey(type);
                orderParameter.setCurrencyType(code);
                orderParameter.setWebsite(ContextUtil.getWebsite());
                orderParameter.setInAddress(btcKey);

                ContextUtil.setRemoteCurrencyType();
                ContextUtil.setRemoteWebsite();

                String s = "NO";
                //----------------对接总线-----------
                if ("true".equals(PropertiesUtils.APP.getProperty("app.blockbus"))) {
                   // System.out.println("提币总线开始==="+s);

                    BusJsonResult busJsonResult = BusRequestUtil.getCoinAddress(orderParameter.getInAddress());
                    if(busJsonResult!=null&&busJsonResult.getSuccess()) {
                        setBusOrder(orderParameter,new BigDecimal(btcNum));
                        remoteResult.setSuccess(true);
                        s = "OK";
                    }
                  //  System.out.println("提币总线结束==="+s+"====="+JSON.toJSONString(busJsonResult));
                 }
                //----------------对接总线-----------   //如果总线存在地址，则设置手续费为0
                try {
                    //已经通过总线处理完成时，不用再走此业务代码
                    if(!"OK".equals(s)){
                        s = remoteExDigitalmoneyAccountService.setOrder(orderParameter);
                  //          System.out.println(s+"====已经通过总线处理完成时，不用再走此业务代码");
                    }
                    if ("OK".equals(s)) {

                        remoteResult.setSuccess(true);
                        Map<String, String> paramM = new HashMap<>();
                        paramM.put("${number}", btcNum);//金额
                        RemoteMessage message=new RemoteMessage();
                        message.setParam(paramM);
                        message.setMsgKey(MessageType.MESSAGE_KLG_ACCOUNT_OUT_APPLY_SUCCESS.getKey());//消息类型 模板KEY//
                        message.setMsgType("1,2");// 1.站内信，2.短信,
                        message.setUserId(user.getCustomerId().toString());
                        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒

                    } else {
                        remoteResult.setSuccess(false);
                        remoteResult.setMsg("our_no_exist");
                    }
                } catch (Exception e) {
                    remoteResult.setMsg("saveorder_exception");
                    e.printStackTrace();
                }
            } else {
                remoteResult.setMsg("tixianshuliangbunengweikong");//提现数量不能为空
            }
        } else {
            remoteResult.setMsg("yileizhanghubunengtibi");       //乙类账户不允许提币
        }
        return remoteResult;
    }

    /**
     * 总线转账无手续费
     * @param order
     * @param bigBtcNum
     */
    private void setBusOrder(OrderParameter order,BigDecimal bigBtcNum ){

       // System.out.println("总线转账无手续费=="+JSON.toJSONString(order));
       // System.out.println("总线转账无手续费==bigBtcNum"+bigBtcNum);
        QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
        filter.addFilter("customerId=", order.getCustomerId());
        filter.addFilter("coinCode=", order.getCurrencyType());
        filter.addFilter("status=",1);
        ExDigitalmoneyAccount digitalmoneyAccount = exDigitalmoneyAccountService.get(filter);
       // System.out.println("==digitalmoneyAccount"+digitalmoneyAccount);
        if (null == digitalmoneyAccount) {
            return ;
        }

        BigDecimal hotMoney = digitalmoneyAccount.getHotMoney();
        BigDecimal coldMoney = digitalmoneyAccount.getColdMoney();
        BigDecimal num = order.getCurrencyNum().add(order.getFee());
        int i = hotMoney.compareTo(num);
    //    System.out.println("==hotMoney.compareTo(num)"+i);
        if (i == -1) {
            return ;
        }
        //System.out.println("setBusOrder 继续");
        // 冻结虚拟账户的钱
        BigDecimal cNowMoney = hotMoney.subtract(num);
        BigDecimal hNowMoney = coldMoney.add(num);

        digitalmoneyAccount.setHotMoney(cNowMoney);
        digitalmoneyAccount.setColdMoney(hNowMoney);
        exDigitalmoneyAccountService.update(digitalmoneyAccount);


        //对接总线无手续费
        order.setCurrencyNum(bigBtcNum);
        //手续费为0
        order.setFee(BigDecimal.ZERO);


        // 生成订单
        ExDmTransaction exDmTransaction = new ExDmTransaction();

        exDmTransaction.setCustomerId(order.getCustomerId());
        String transactionNum = NumConstant.Ex_Dm_Transaction;
        transactionNum = IdGenerate.transactionNum(transactionNum);
        order.setTransactionNum(transactionNum);
        exDmTransaction.setTransactionNum(transactionNum);
        exDmTransaction.setAccountId(order.getCurrencyId());
        exDmTransaction.setTransactionType(2);
        exDmTransaction.setTransactionMoney(order.getCurrencyNum().add(order.getFee()));
        exDmTransaction.setCustomerName(order.getCustomerName());
        // 2表示提现
        exDmTransaction.setStatus(1);
        exDmTransaction.setCoinCode(order.getCurrencyType());
        exDmTransaction.setCurrencyType(order.getType());
        exDmTransaction.setWebsite(order.getWebsite());


        exDmTransaction.setFee(order.getFee());
        exDmTransaction.setOurAccountNumber(order.getOurAccountNumber());
        exDmTransaction.setInAddress(order.getInAddress());
        exDmTransaction.setOutAddress(order.getOurAccountNumber());
        exDmTransaction.setOptType(2);
        exDmTransaction.setRemark("总线提币到联盟系统");
        /**
         * 交易所转快乐购：  提币叫：划转至快乐购   充币：划转至交易所 
         * 快乐购转交易所：提币叫：划转至交易所  充币：划转至快乐购
         */
        exDmTransaction.setRemark2("划转至交易所");// 交易所的叫：快乐购划转  快乐购：交易所划转
        
        
        // 保存订单
        exDmTransactionService.save(exDmTransaction);

        //----发送mq消息----start
        //热账户减少
        logger.error(digitalmoneyAccount.getId()+"：提币消息生产者开始");
        Accountadd accountadd = new Accountadd();
        accountadd.setAccountId(digitalmoneyAccount.getId());
        accountadd.setMoney(order.getCurrencyNum().multiply(new BigDecimal(-1)));
        accountadd.setMonteyType(1);
        accountadd.setAcccountType(1);
        accountadd.setRemarks(35);
        accountadd.setTransactionNum(exDmTransaction.getTransactionNum());

        //冷账户增加
        Accountadd accountadd2 = new Accountadd();
        accountadd2.setAccountId(digitalmoneyAccount.getId());
        accountadd2.setMoney(order.getCurrencyNum());
        accountadd2.setMonteyType(2);
        accountadd2.setAcccountType(1);
        accountadd2.setRemarks(35);
        accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());

        String serialNumber=IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
        KlgAssetsRecord klgAssetsRecord2=new KlgAssetsRecord();//交易流水
        klgAssetsRecord2.setCustomerId(order.getCustomerId());//用户Id
        klgAssetsRecord2.setAccountId(digitalmoneyAccount.getId());//币账户ID
        klgAssetsRecord2.setSerialNumber(serialNumber);//流水号
        klgAssetsRecord2.setAccountType(1);  //账户类型 1.热账户 2.冷账户
        klgAssetsRecord2.setCoinCode(digitalmoneyAccount.getCoinCode());//币种Code
        klgAssetsRecord2.setChangeCount(exDmTransaction.getTransactionMoney()); //交易量
        klgAssetsRecord2.setChangeType(2);////变动类型 1加 2减
        klgAssetsRecord2.setTriggerPoint(TriggerPointEnum.TransferToExchange.getKey());//触发点
        klgAssetsRecord2.setTriggerSerialNumber(exDmTransaction.getOrderNo()); //触发点流水号
        klgAssetsRecord2.setRemark("转出到交易所冻结");
        klgAssetsRecordService.save(klgAssetsRecord2);
        klgAssetsRecord2.setId(null);
        klgAssetsRecord2.setChangeType(1);////变动类型 1加 2减
        klgAssetsRecord2.setAccountType(2);  //账户类型 1.热账户 2.冷账户
        klgAssetsRecordService.save(klgAssetsRecord2);
        List<Accountadd> list = new ArrayList<Accountadd>();
        list.add(accountadd);
        list.add(accountadd2);
        messageProducer.toAccount(JSON.toJSONString(list));
    }


    /**
     * 金如悦 -- 是否开启第三方充值接口
     * @param type
     * @return
     */
    @Override
    public List<hry.manage.remote.model.AppConfig> getConfigInfo(String type) {
        RemoteAppConfigService service = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
        List<AppConfig> configInfo = service.getConfigInfo(type);
        List<hry.manage.remote.model.AppConfig> beanList = ObjectUtil.beanList(configInfo, hry.manage.remote.model.AppConfig.class);
        return beanList;
    }

    @Override
	public RemoteResult setDefaultBankCard(Long id) {
		try {
			AppBankCard appBankCard = appBankCardService.get(id);

			QueryFilter filter = new QueryFilter(AppBankCard.class);
			filter.addFilter("isDefault=",1);
			filter.addFilter("customerId=",appBankCard.getCustomerId());
			List<AppBankCard> appBankCards = appBankCardService.find(filter);
			if( appBankCards != null && appBankCards.size() >0 ){
				for(AppBankCard bankCard : appBankCards ){
					bankCard.setIsDefault(0);
					appBankCardService.update(bankCard);
				}
			}

			appBankCard.setIsDefault(1);
			appBankCardService.update(appBankCard);
			return new RemoteResult().setSuccess(true).setMsg("shezhichenggong");
		} catch (Exception e) {
			e.printStackTrace();
			return new RemoteResult().setSuccess(false);
		}
	}

}
