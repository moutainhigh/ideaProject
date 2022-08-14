package hry.financail.remote;

import hry.bean.FrontPage;
import hry.financail.financing.model.AppFinancialRedAcount;
import hry.financail.financing.model.AppFinancialUser;
import hry.financail.financing.service.*;
import hry.redis.common.utils.RedisService;
import hry.remote.model.*;
import hry.remote.service.RemoteFinancailService;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/4/3 10:58
 * @Description:
 */
public class RemoteFinancailServiceImpl implements RemoteFinancailService {


    @Resource
    private AppFinancialProductsService appFinancialProductsService;
    @Resource
    private AppFinancialGiftRecordService appFinancialGiftRecordService;
    @Resource
    private AppFinancialUserService appFinancialUserService;
    @Resource
    private AppFinancialRedAcountService appFinancialRedAcountService;
    @Resource
    private AppFinancialRecommendService appFinancialRecommendService;
    @Resource
    private RedisService redisService;

    @Override
    public FrontPage findFrontPageUserRedAccountList(Map<String, String> paramMap) {
        return appFinancialGiftRecordService.findUserRedAccount(paramMap);
    }

    @Override
    public FrontPage findFrontPageUserFinancalList(Map<String, String> paramMap) {
        return appFinancialProductsService.findUserFinaningProductList(paramMap);
    }

    @Override
    public List findFronTPageFinancalList(Map<String, String> paramMap) {
        return null;
    }

    @Override
    public List<AppFinancialProducts> findFinaningProductList(Map<String, String> paramMap) {
        return appFinancialProductsService.findFinaningProductList(paramMap);
    }

    @Override
    public AppFinancialProducts findAppFinancialProductByMap(Map<String, String> paramMap) {
        return appFinancialProductsService.findOne(paramMap);
    }

    @Override
    public RemoteResult createFinancialOrder(Map<String, String> paramMap) {
        RemoteResult remoteResult = new RemoteResult();
        String lock = "financialOrder:" + paramMap.get("memberId");
        if (!redisService.lock(lock)) {
            remoteResult.setSuccess(false).setMsg("req_error"); //操作失败
            return remoteResult;
        }
        try {
            String productId = paramMap.get("productId");
            String customerId = paramMap.get("customerId");
            String buyNumber = paramMap.get("buyNumber");
            String redPacketsMoney = paramMap.get("redPacketsMoney") == null ? "0" : paramMap.get("redPacketsMoney");

            QueryFilter filter = new QueryFilter(hry.financail.financing.model.AppFinancialProducts.class);
            filter.addFilter("id_EQ",productId);
            hry.financail.financing.model.AppFinancialProducts afp = appFinancialProductsService.get(filter);

            if(afp == null){
                remoteResult.setMsg("financial_isNotExitProduct");
                return remoteResult;
            }

            BigDecimal incrementalMoney = afp.getIncrementalMoney(); //递增金额
            BigDecimal minimumMoney = afp.getMinimumMoney(); //起始金额
            if(incrementalMoney.compareTo(BigDecimal.ZERO) == 1 && new BigDecimal(buyNumber).subtract(minimumMoney).divideAndRemainder(incrementalMoney)[1].intValue() != 0){
                remoteResult.setMsg("financial_buyPriceMultipleAdd");
                remoteResult.setObj(incrementalMoney);
                return remoteResult;
            }

            if (afp.getEndTime().before(new Date()) || afp.getStartTime().after(new Date())) {
                remoteResult.setMsg("financial_isNotAllowTime");
                return remoteResult;
            }

            //购买金额不能小于起入金额
            if(new BigDecimal(buyNumber).compareTo(afp.getMinimumMoney()) == -1 ){
                remoteResult.setMsg("financial_isAllowPriceStartPrice");
                return remoteResult;
            }

            //购买金额不能大于总金额
            if(new BigDecimal(buyNumber).compareTo(afp.getTotalMoney()) == 1 ){
                remoteResult.setMsg("financial_isAllowPriceAllPrice");
                return remoteResult;
            }

            remoteResult = appFinancialUserService.createFinancialOrder(afp, customerId, new BigDecimal(buyNumber), new BigDecimal(redPacketsMoney));

            if(remoteResult.getSuccess()){
                remoteResult.setSuccess(true);
                remoteResult.setMsg("");
                return remoteResult;
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            redisService.unLock(lock);
        }
        return remoteResult;
    }

    @Override
    public RemoteResult receiveAllRedAccount(String customerId, String[] coinCodes) {
        RemoteResult remoteResult = new RemoteResult();
        for(String coinCode : coinCodes){
            remoteResult = appFinancialGiftRecordService.receiveAllGiftRecord(customerId, coinCode);
        }
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    @Override
    public RemoteResult cancelFinancialOrder(String productId,String customerId) {
        QueryFilter filter = new QueryFilter(AppFinancialUser.class);
        filter.addFilter("customerId_EQ",customerId);
        filter.addFilter("productId_EQ",productId);
        AppFinancialUser appFinancialUser = appFinancialUserService.get(filter);
        QueryFilter qf = new QueryFilter(hry.financail.financing.model.AppFinancialProducts.class);
        qf.addFilter("id=",productId);
        hry.financail.financing.model.AppFinancialProducts financialProducts = appFinancialProductsService.get(qf);
        if (appFinancialUser == null) {
            return new RemoteResult().setSuccess(false).setMsg("financial_notHasRecord");
        }
        if (financialProducts.getEarlyRedemption() == 0) {
            return new RemoteResult().setSuccess(false).setMsg("financial_notAllowEarlyRedemption");
        }
        if (DateUtil.addDaysToDate(financialProducts.getStartingInterestTime(), financialProducts.getLockUpPeriod()).after(new Date())) {
            return new RemoteResult().setSuccess(false).setMsg("financial_LockingNotRedemption");
        }
        AppFinancialUser appFinancialUserUpdate = new AppFinancialUser();
        appFinancialUserUpdate.setId(appFinancialUser.getId());
        appFinancialUserUpdate.setIsRedeem(1);
        appFinancialUserService.update(appFinancialUserUpdate);
        return new RemoteResult().setSuccess(true).setMsg("financial_redemptionSuccess");
    }

    @Override
    public RemoteResult getUserRedAccount(String customerId, String coinCode) {
        BigDecimal redNumber = BigDecimal.ZERO;
        try {
            QueryFilter filter = new QueryFilter(AppFinancialRedAcount.class);
            filter.addFilter("customerId_EQ",customerId);
            filter.addFilter("coinCode_EQ",coinCode);
            AppFinancialRedAcount appFinancialRedAcount = appFinancialRedAcountService.get(filter);
            if(appFinancialRedAcount != null){
                redNumber = appFinancialRedAcount.getMoney();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return new RemoteResult().setSuccess(true).setObj(redNumber);
    }

    /**
     * 获得已投资人数
     * @return
     */
    @Override
    public Integer findInvestmentedPersonCount(Map<String, Object> pMap) {
        return appFinancialUserService.findInvestmentedPersonCount(pMap);
    }

    @Override
    public FrontPage findFinancialRecommendList(Map<String, String> pMap) {
        return appFinancialRecommendService.findFinancialRecommendList(pMap);
    }

    @Override
    public List<AppFinancialRecommendTransaction> findFinancialRecommendTransactionList(Map<String, String> pMap) {
        return appFinancialRecommendService.findFinancialRecommendTransactionList(pMap);
    }

    @Override
    public AppFinancialRecommend findOne(Long customerId) {
        return appFinancialRecommendService.findOne(customerId);
    }

    @Override
    public List<AppFinancialRecommendVo> findTeamFinancialList(Long customerId) {
        return appFinancialRecommendService.findTeamFinancialList(customerId);
    }

    @Override
    public AppFinancialProducts findAppFinancialProductByMap_CY(Map<String,String> paramMap){
        return appFinancialProductsService.findOne_cy(paramMap);
    }

    @Override
    public RemoteResult addFinancialRecommendShip(Long customerId,String code){
        return appFinancialRecommendService.addFinancialRecommendShip(customerId,code);
    }

    @Override
    public int findShipCount(Long customerId){
        return appFinancialRecommendService.findShipCount(customerId);
    }
}
