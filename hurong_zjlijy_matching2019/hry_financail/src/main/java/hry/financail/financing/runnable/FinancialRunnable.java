package hry.financail.financing.runnable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.financail.account.dao.ExDmTransactionDao;
import hry.financail.account.model.ExDigitalmoneyAccount;
import hry.financail.account.model.ExDmTransaction;
import hry.financail.account.service.ExDigitalmoneyAccountService;
import hry.financail.customer.model.AppPersonInfo;
import hry.financail.customer.service.AppPersonInfoService;
import hry.financail.financing.dao.AppFinancialActiveDao;
import hry.financail.financing.dao.AppFinancialGiftRecordDao;
import hry.financail.financing.dao.AppFinancialRedAcountDao;
import hry.financail.financing.dao.AppFinancialUserDao;
import hry.financail.financing.model.*;
import hry.financail.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.remote.model.FinancialUser;
import hry.remote.model.RemoteResult;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.*;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/11 15:36
 * @Description:
 */
public class FinancialRunnable implements Runnable{

    private static Logger logger = Logger.getLogger(FinancialRunnable.class);

    private AppFinancialUserDao appFinancialUserDao;
    private AppFinancialRedAcountDao appFinancialRedAcountDao;
    private AppFinancialActiveDao appFinancialActiveDao;
    private AppFinancialGiftRecordDao appFinancialGiftRecordDao;
    private MessageProducer messageProducer;
    private AppPersonInfoService appPersonInfoService;
    private ExDmTransactionDao exDmTransactionDao;
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
    private AppFinancialProducts afp;
    private String customerId;
    private BigDecimal buyNumber;
    private BigDecimal redPacketsMoney;
    private BigDecimal expectedIncome;


    public FinancialRunnable(AppFinancialProducts afp,String customerId,BigDecimal buyNumber,BigDecimal redPacketsMoney,BigDecimal expectedIncome){
     this.appFinancialUserDao = SpringUtil.getBean("appFinancialUserDao");
     this.appFinancialRedAcountDao = SpringUtil.getBean("appFinancialRedAcountDao");
     this.appFinancialActiveDao = SpringUtil.getBean("appFinancialActiveDao");
     this.appFinancialGiftRecordDao = SpringUtil.getBean("appFinancialGiftRecordDao");
     this.messageProducer = SpringUtil.getBean("messageProducer");
     this.appPersonInfoService = SpringUtil.getBean("appPersonInfoService");
     this.exDmTransactionDao = SpringUtil.getBean("exDmTransactionDao");
     this.exDigitalmoneyAccountService = SpringUtil.getBean("exDigitalmoneyAccountService");
     this.afp = afp;
     this.customerId = customerId;
     this.buyNumber = buyNumber;
     this.redPacketsMoney = redPacketsMoney;
     this.expectedIncome = expectedIncome;
    }

    @Override
    public void run() {
        try (Jedis jedis = FinancialRedis.JEDIS_POOL.getResource()){
            Long setnx = jedis.setnx("financial:" + customerId, "1");
            if (setnx != null && setnx == 1L) {
                jedis.expire("financial:" + customerId,2);
                dealWithBuyProduct();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dealWithBuyProduct(){
        try {
            ExDigitalmoneyAccountRedis accountRedis = UserRedisUtils.getAccountRedis(customerId, afp.getCoinCode());
            //手续费
            BigDecimal poundage=buyNumber.multiply(new BigDecimal(afp.getBuyRate())).divide(new BigDecimal(100));
            //投资金额
            BigDecimal  coinMoney=(buyNumber.add(poundage)).subtract(redPacketsMoney);


            //发送修改币账户的mq
            Map<String,String> paramMap1 = new HashMap<>(4);
			paramMap1.put("coinCode",afp.getCoinCode());
			paramMap1.put("isRedeem","0");
			paramMap1.put("productId",afp.getId().toString());
            sendBuyProductsMq(coinMoney, accountRedis.getId());
            // 假如用户之前购买过，就在购买记录中加上对应的值
            paramMap1.put("customerId",customerId);
            List<FinancialUser> financialUsersList = appFinancialUserDao.findFinancialUserList(paramMap1);
            if (financialUsersList.size() > 0) {
                FinancialUser financialUser = financialUsersList.get(0);
                AppFinancialUser appFinancialUserUpdate = new AppFinancialUser();
                appFinancialUserUpdate.setId(financialUser.getId());
                appFinancialUserUpdate.setCoinMoney(financialUser.getCoinMoney().add(buyNumber));
                appFinancialUserUpdate.setRedPacketsMoney(financialUser.getRedPacketsMoney().add(redPacketsMoney));
                appFinancialUserUpdate.setExpectedIncome(financialUser.getExpectedIncome().add(expectedIncome));
                appFinancialUserUpdate.setPoundage(financialUser.getPoundage().add(poundage));
                appFinancialUserUpdate.setModified(new Date());
                appFinancialUserUpdate.setEndTime(afp.getReturnOfPrincipalTime());
                appFinancialUserDao.updateByPrimaryKeySelective(appFinancialUserUpdate);
            } else {
                //否则就创建一条记录
                AppFinancialUser appFinancialUserAdd = new AppFinancialUser();
                appFinancialUserAdd.setCoinCode(afp.getCoinCode());
                appFinancialUserAdd.setProductId(afp.getId());
                appFinancialUserAdd.setCoinMoney(buyNumber);
                appFinancialUserAdd.setRedPacketsMoney(redPacketsMoney);
                appFinancialUserAdd.setCustomerId(Long.valueOf(customerId));
                appFinancialUserAdd.setExpectedIncome(expectedIncome);
                appFinancialUserAdd.setPoundage(poundage);
                appFinancialUserAdd.setCreated(new Date());
                appFinancialUserAdd.setModified(new Date());
                appFinancialUserAdd.setEndTime(afp.getReturnOfPrincipalTime());
                appFinancialUserDao.insertSelective(appFinancialUserAdd);
            }

            //如果有理财红包  给用户红包
            dealFinancialAcitiveNew(customerId,afp, buyNumber);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 处理红包
     * @param customerId
     * @param buyMoney
     */
    public void dealFinancialAcitiveNew(String customerId, AppFinancialProducts afp ,BigDecimal buyMoney) {
        try {
            QueryFilter perFilter = new QueryFilter(AppPersonInfo.class);
            perFilter.addFilter("customerId_EQ",customerId);
            AppPersonInfo appPersonInfo = appPersonInfoService.get(perFilter);
            if(appPersonInfo!=null){
                Map<String,String> paramMap1 = new HashMap<String,String>();
                //查询红包活动为 理财有礼 未过期 币种一样
                paramMap1.put("coinCode",afp.getCoinCode());
                paramMap1.put("status","1"); //状态开启
                paramMap1.put("giftType","2"); //理财有礼类型
                paramMap1.put("endTime", DateUtil.getNewDate());
                AppFinancialActive appFinancialActive = appFinancialActiveDao.findFinancialActive(paramMap1);
                if(appFinancialActive != null){
                    AppFinancialGiftRecord appFinancialGiftRecord=new AppFinancialGiftRecord();
                    appFinancialGiftRecord.setCoinCode(appFinancialActive.getCoinCode());
                    appFinancialGiftRecord.setCustomerId(appPersonInfo.getCustomerId());
                    appFinancialGiftRecord.setMobile(appPersonInfo.getMobilePhone());
                    appFinancialGiftRecord.setGiftType(appFinancialActive.getGiftType());
                    appFinancialGiftRecord.setOverTime(DateUtil.addDay(new Date(),appFinancialActive.getInvalidDay()));
                    appFinancialGiftRecord.setCreated(new Date());
                    appFinancialGiftRecord.setModified(new Date());
                    String  regex = "[A-Za-z]";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(appPersonInfo.getTrueName());
                    if(matcher.matches()){
                        appFinancialGiftRecord.setUserName(appPersonInfo.getTrueName()+" "+appPersonInfo.getSurname());
                    }else{
                        appFinancialGiftRecord.setUserName(appPersonInfo.getSurname()+appPersonInfo.getTrueName());
                    }

                    //获得红包信息
                    String nowDate=DateUtil.getNewDate();
                    String StartTimeStr= nowDate.split(" ")[0]+" 00:00:00";
                    Map<String,Object> paramMap = new HashMap<>();
                    paramMap.put("coinCode",appFinancialActive.getCoinCode());
                    paramMap.put("giftType",appFinancialActive.getGiftType());
                    paramMap.put("created_LT",StartTimeStr);
                    //获得当日的赠送红包
                    List<AppFinancialGiftRecord> appFinancialGiftRecordList = appFinancialGiftRecordDao.findUserGiftRecordList(paramMap);
                    //获取的红包还没有到达每日最大限制
                    BigDecimal haveGiftMoney =new BigDecimal(0);
                    if(appFinancialGiftRecordList != null && appFinancialGiftRecordList.size() > 0) { //今天的赠送红包
                        for(AppFinancialGiftRecord giftRecordInFor : appFinancialGiftRecordList){
                            haveGiftMoney = haveGiftMoney.add(giftRecordInFor.getGiftMoney());
                        }
                    }
                    //判断是否可多次送，0-否 1-是 （与字典中的yesno绑定）
                    if(appFinancialActive.getActiveType()==0){
                        Map<String,Object> pMap = new HashMap<>();
                        //不可多次送，查询当前用户是否已经送过红包了
                        pMap.put("customerId",appPersonInfo.getCustomerId());
                        pMap.put("coinCode",appFinancialActive.getCoinCode());
                        pMap.put("giftType",appFinancialActive.getGiftType());
                        List<AppFinancialGiftRecord> list1 = appFinancialGiftRecordDao.findUserGiftRecordList(pMap);
                        //已经送过了
                        if(list1 != null && list1.size() > 0){

                        }else{//没有送
                            //判断是否达到上限
                            //获取的红包还没有到达每日最大限制
                            if(appFinancialActive.getMaxNum().compareTo(haveGiftMoney) >= 0){
                                appFinancialGiftRecord.setGiftMoney(appFinancialActive.getGiftNum());
                                appFinancialGiftRecordDao.insertSelective(appFinancialGiftRecord);
                            }
                        }
                    }else{ //可多次送
                        //判断是否达到上限
                        if(appFinancialActive.getGiftWay()==1){//固定数量
                            //获取的红包还没有到达每日最大限制
                            if(appFinancialActive.getMaxNum().compareTo(haveGiftMoney) >= 0){
                                appFinancialGiftRecord.setGiftMoney(appFinancialActive.getGiftNum());
                                appFinancialGiftRecordDao.insertSelective(appFinancialGiftRecord);
                            }
                        }else if(appFinancialActive.getGiftWay()==2){//固定百分比
                            if(appFinancialActive.getMaxNum().compareTo(haveGiftMoney) == 1){
                                //实名认证和分享有礼不能用百分比
                                if(!"1".equals(appFinancialActive.getGiftType())&& !"4".equals(appFinancialActive.getGiftType())){
                                    BigDecimal thisNumber = buyMoney.multiply(appFinancialActive.getGiftNum()).divide(new BigDecimal(100));
                                    if(appFinancialActive.getMaxNum().compareTo(haveGiftMoney.add(thisNumber))==1){
                                        appFinancialGiftRecord.setGiftMoney(thisNumber);
                                    }else{
                                        //假如剩余的额度比这次获取的少，那么就用剩余的
                                        appFinancialGiftRecord.setGiftMoney(appFinancialActive.getMaxNum().subtract(haveGiftMoney));
                                    }
                                    appFinancialGiftRecordDao.insertSelective(appFinancialGiftRecord);
                                }
                            }
                        }
                    }

                    logger.info("理财活动customerId = [" + appPersonInfo.getCustomerId() + "], appfinancialactive = [" + JSONObject.toJSONString(appFinancialActive) + "], buyMoney = [" + buyMoney + "]");
                }else{
                    logger.info("理财活动customerId = [" + appPersonInfo.getCustomerId() + "], appfinancialactive 为null, buyMoney = [" + buyMoney + "]");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendBuyProductsMq(BigDecimal coinMoney, Long accountId) {
        ExDmTransaction exDmTransaction = new ExDmTransaction();
        exDmTransaction.setAccountId(accountId);
        exDmTransaction.setCoinCode(afp.getCoinCode());
        exDmTransaction.setCreated(new Date());
        exDmTransaction.setCurrencyType("USD");
        exDmTransaction.setWebsite("zh_CN");
        exDmTransaction.setCustomerId(Long.valueOf(customerId));
        exDmTransaction.setCustomerName("");
        exDmTransaction.setFee(BigDecimal.ZERO);
        exDmTransaction.setTransactionMoney(coinMoney);
        exDmTransaction.setStatus(2);
        exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
        exDmTransaction.setTransactionType(2);
        exDmTransaction.setRemark("理财本金冻结" + exDmTransaction.getTransactionMoney() + "个币");
        exDmTransaction.setOptType(11);
        exDmTransaction.setUserId(ContextUtil.getCurrentUserId());
        exDmTransaction.setSaasId("");
        exDmTransactionDao.insert(exDmTransaction);

        // 热账户减少
        Accountadd accountadd = new Accountadd();
        accountadd.setAccountId(exDmTransaction.getAccountId());
        accountadd.setMoney(coinMoney.multiply(new BigDecimal(-1)));
        accountadd.setMonteyType(1);
        accountadd.setAcccountType(1);
        accountadd.setRemarks(102);
        accountadd.setTransactionNum(exDmTransaction.getTransactionNum());

        List<Accountadd> list = new ArrayList<>();
        list.add(accountadd);
        messageProducer.toAccount(JSON.toJSONString(list));
    }


}
