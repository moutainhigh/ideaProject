/**
 * 111
 */

package hry.klg.remote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.core.thread.ThreadPool;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.front.redis.model.UserRedis;
import hry.klg.level.model.KlgCustomerLevel;
import hry.klg.level.model.KlgTeamlevel;
import hry.klg.level.service.KlgCustomerLevelService;
import hry.klg.level.service.KlgTeamlevelService;
import hry.klg.model.KlgPlatformCurrency;
import hry.klg.model.PlatformAccountadd;
import hry.klg.platform.model.PlatformAccountUtil;
import hry.klg.remote.model.KlgRewardRemote;
import hry.klg.reward.model.KlgReward;
import hry.klg.reward.service.KlgRewardService;
import hry.klg.transaction.model.KlgSellTransaction;
import hry.klg.transaction.service.KlgBuyTransactionService;
import hry.klg.transaction.service.KlgSellTransactionService;
import hry.manage.remote.model.base.FrontPage;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import net.sf.json.JSONObject;

/**
 * 奖励
 */
public class RemoteKlgRewardServiceImpl implements  RemoteKlgRewardService {
    private  final static  String COINCODE="USDT";
    @Resource
    private KlgTeamlevelService teamlevelService;
    @Resource
    private KlgSellTransactionService sellTransactionService;
    @Resource
    private KlgBuyTransactionService klgBuyTransactionService;
    @Resource
    private KlgCustomerLevelService klgCustomerLevelService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private KlgRewardService klgRewardService;
    @Resource
    private ExDigitalmoneyAccountService accountService;
    /**
     * 卖出奖励发放   0星为预约不拿奖励
     * @param message
     * @return
     */
    @Override
    public JsonResult sellRewardMQ(String message) {
        //TODO 正式 message 为一个json对象
        KlgSellTransaction klgSellTransaction=JSON.parseObject(message, KlgSellTransaction.class);
       System.out.println(message);
   //     Long sellId=Long.parseLong(message);
     //   KlgSellTransaction klgSellTransaction=sellTransactionService.get(sellId);
        Long customerId=klgSellTransaction.getCustomerId();//用户Id
        BigDecimal smeusdtRate=klgSellTransaction.getSmeusdtRate();//当时卖出的单价
        BigDecimal candySmeMoney=klgSellTransaction.getCandySmeMoney();//当前应获糖果总金额（SEM）
        BigDecimal baseMoney=candySmeMoney.multiply(smeusdtRate);//基数  糖果数*单价
        BigDecimal platformRate=klgSellTransaction.getPlatformRate().divide(new BigDecimal(100));//平台获取糖果比例 10%
        BigDecimal oneselfRate=klgSellTransaction.getOneselfRate().divide(new BigDecimal(100));//本人获得糖果比率  60%
        BigDecimal seePointRate=klgSellTransaction.getSeePointRate().divide(new BigDecimal(100));//见点糖果比例 23%
        BigDecimal gradationRate=klgSellTransaction.getGradationRate().divide(new BigDecimal(100));//级差糖果比例 7%
        BigDecimal prizeRate=klgSellTransaction.getPrizeRate().divide(new BigDecimal(100));//周奖糖果比例 7%

        List<PlatformAccountadd> platformAccounts=new ArrayList<>();//平台账户

        //平台获得的10% 进入调控账户
        BigDecimal platformMoney=baseMoney.multiply(platformRate);//平台获得的SEM 折合USDT
        //糖果回收账户
        PlatformAccountadd platformadd= PlatformAccountUtil.accountAdd(KlgPlatformCurrency.CandyRecyclingAccount.getKey(),platformMoney.stripTrailingZeros().toPlainString(),"平台获得的10% ");
        platformAccounts.add(platformadd);

        //BigDecimal oneselfMoney=baseMoney.multiply(oneselfRate);//本人获得USDT
        BigDecimal seePointMoney=baseMoney.multiply(seePointRate);//见点奖励USDT
        BigDecimal gradationMoney=baseMoney.multiply(gradationRate);//级差奖励USDT
        BigDecimal prizeMoney=baseMoney.multiply(prizeRate);//周奖基金USDT
        List<KlgTeamlevel> teamlevelList= teamlevelService.getSuperiorLeveConfig(customerId);
        Integer size=teamlevelList.size();
        if(teamlevelList!=null&&size>0){//有上级 推荐关系 分销发放奖励
            int leve=10;//默认10代 // 获取用户见点代数
            //见点将每代拿的奖励个数
            computeReward(klgSellTransaction.getId(),klgSellTransaction.getTransactionNum(),leve,seePointMoney,gradationMoney,baseMoney,teamlevelList);
        }else{//无上级所有奖励进入 USDT奖励沉余账户
            BigDecimal notReward=seePointMoney.add(gradationMoney);//沉余奖励 //见点+级差
            // 发放致沉余账户
            //沉余账户
            PlatformAccountadd sinkadd= PlatformAccountUtil.accountAdd(KlgPlatformCurrency.USDTPrecipitationAccount.getKey(),notReward.stripTrailingZeros().toPlainString(),"沉余账户");
            platformAccounts.add(sinkadd);
        }
        // 大奖基金发发
         //prizeMoney
        PlatformAccountadd prizeadd= PlatformAccountUtil.accountAdd(KlgPlatformCurrency.USDTWeeklyAccount.getKey(),prizeMoney.stripTrailingZeros().toPlainString(),"大奖基金发发");
        //糖果账户减少
       // PlatformAccountadd candySme= PlatformAccountUtil.accountSub(KlgPlatformCurrency.SMECandyAccount.getKey(),candySmeMoney.stripTrailingZeros().toPlainString(),"糖果账户减少");
        platformAccounts.add(prizeadd);
       // platformAccounts.add(candySme);
        messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
        return new JsonResult().setSuccess(true).setMsg("success");
    }
    /**
     * 计算
     * @param level
     * @param seePointMoney
     * @param gradationMoney
     * @param teamlevels
     */
    private void computeReward(Long foreignKey,String sellTransactionNum,Integer level,BigDecimal seePointMoney,BigDecimal gradationMoney,BigDecimal baseMoney,List<KlgTeamlevel> teamlevels){
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        List<PlatformAccountadd> platformAccounts=new ArrayList<>();//平台账户
        BigDecimal decimal=seePointMoney.multiply(new BigDecimal(0.1));//没代10%
        int size=teamlevels.size();
        BigDecimal oldGradation=null;
//        String transactionNum= IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);//流水号
        BigDecimal surplusGradationMoney=gradationMoney;//剩余级差奖励
        BigDecimal surplusSeePointMoney=seePointMoney;//剩余见点奖励

        //见点
        for (int i=0;i<(size>=10?10:size);i++) { //最大拿10代 1见点奖 2级差奖
            KlgTeamlevel klgTeamlevel=teamlevels.get(i);
            Long parentId=klgTeamlevel.getParentId();//上级用户Id
            Integer p_level= klgTeamlevel.getLevel();//层级
            Integer integer= getCustomerIdCount(parentId);//拿几代
            if(p_level.intValue()<=integer.intValue()){
                surplusSeePointMoney=surplusSeePointMoney.subtract(decimal);
                // 发放见点奖
                BigDecimal bigDecimal2=saveKlgRewardRecordNew(redisUtil,sellTransactionNum,foreignKey,parentId,COINCODE,decimal,1);
                if(bigDecimal2!=null&&bigDecimal2.compareTo(BigDecimal.ZERO)>0){
                    surplusSeePointMoney=surplusSeePointMoney.add(bigDecimal2);
                }
            }
        }
        //级差
        for (int i=0;i<size;i++) {
            KlgTeamlevel klgTeamlevel=teamlevels.get(i);
            Long parentId=klgTeamlevel.getParentId();//上级用户Id
            BigDecimal money=new BigDecimal(0);
            BigDecimal gradation= new BigDecimal(0);
            if(klgTeamlevel.getGradation()!=null&&klgTeamlevel.getGradation().compareTo(new BigDecimal(0))!=0){
            	gradation= (klgTeamlevel.getFixedGradation()==null?klgTeamlevel.getGradation():klgTeamlevel.getFixedGradation()).divide(new BigDecimal(100));//级差
            }
            if(i==0){
                BigDecimal gMoney=baseMoney.multiply(gradation);
                money=money.add(gMoney);
            }else{
                BigDecimal subGradation=gradation.subtract(oldGradation);
                if(subGradation.compareTo(BigDecimal.ZERO)>0){
                    money=money.add(baseMoney.multiply(subGradation));
                }else{
                    continue;
                }
            }
            oldGradation=gradation;
            if(surplusGradationMoney.compareTo(money)==-1){//剩余奖励不足发放级差
                money=surplusGradationMoney;
            }
            surplusGradationMoney=surplusGradationMoney.subtract(money);
            // 发放级差奖励 money;
           if(money.compareTo(BigDecimal.ZERO)==1){
               BigDecimal bigDecimal=saveKlgRewardRecordNew(redisUtil,sellTransactionNum,foreignKey,parentId,COINCODE,money,2);
               if(bigDecimal!=null&&bigDecimal.compareTo(BigDecimal.ZERO)>0){
                   surplusGradationMoney=surplusGradationMoney.add(bigDecimal);
               }
           }
            if(surplusGradationMoney.compareTo(BigDecimal.ZERO)==0){
                break;
            }
        }
        //见点剩余+级差剩余为沉余账户
        BigDecimal bigDecimal=surplusGradationMoney.add(surplusSeePointMoney);
        if(bigDecimal.compareTo(BigDecimal.ZERO)>0){//有沉余
            //沉余账户
            PlatformAccountadd sinkadd= PlatformAccountUtil.accountAdd(KlgPlatformCurrency.USDTPrecipitationAccount.getKey(),bigDecimal.stripTrailingZeros().toPlainString(),"沉余账户");
            platformAccounts.add(sinkadd);
            messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));//沉余账户
        }
    }
    //获取见点代数
    private Integer getCustomerIdCount(Long customerId){
        KlgCustomerLevel klgCustomerLevel= klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
        Integer count=teamlevelService.getStarCount(customerId,10);
        Integer pointAlgebra=klgCustomerLevel.getPointAlgebra();//当前等级最高拿多少代
        if(count.intValue()>pointAlgebra.intValue()){
            return pointAlgebra;
        }
        return count;
    }

    private  BigDecimal saveKlgRewardRecordNew(RedisUtil<UserRedis> redisUtil,String transactionNum,Long foreignKey,Long customerId,String coinCode,BigDecimal money,Integer type){
        UserRedis userRedis = redisUtil.get(customerId.toString());
        Long accountId =null;
        if(userRedis==null){
            ExDigitalmoneyAccount digitalmoneyAccount=accountService.get(new QueryFilter(ExDigitalmoneyAccount.class).addFilter("customerId=",customerId).addFilter("coinCode=",coinCode));
            accountId=digitalmoneyAccount.getId();
        }else{
            accountId=userRedis.getDmAccountId(coinCode);
        }
        Object obj= klgRewardService.saveKlgRewardRecordNew(foreignKey,transactionNum,customerId,accountId,money,type);
        if(obj!=null){
            return  (BigDecimal)obj;
         }
        return null;
    }
	@Override
	public FrontPage findRewardList(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		Long customerId = Long.valueOf(params.get("customerId"));// 用户id
		QueryFilter filter = new QueryFilter(KlgReward.class);
		filter.addFilter("customerId=", customerId);
		filter.setOrderby("created desc");
		List<KlgReward> list = klgRewardService.find(filter);
		List<KlgRewardRemote> beanList = ObjectUtil.beanList(list, KlgRewardRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}
	@Override
	public JsonResult myReward(Map<String, String> params) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		Long customerId = Long.valueOf(params.get("customerId"));// 用户id
		QueryFilter filterKlgCustomerLevel = new QueryFilter(KlgCustomerLevel.class);
		filterKlgCustomerLevel.addFilter("customerId=", customerId);
		KlgCustomerLevel klgCustomerLevel = klgCustomerLevelService.get(filterKlgCustomerLevel);
		//剩余额度
		BigDecimal surplusBonusLimit = klgCustomerLevel==null?new BigDecimal(0):klgCustomerLevel.getRewardNum();
		if(surplusBonusLimit==null){
			surplusBonusLimit = new BigDecimal(0);
		}
		//级差等级
		BigDecimal gradationGrade = klgCustomerLevel==null?new BigDecimal(0):klgCustomerLevel.getGradation();
		if(gradationGrade==null){
			gradationGrade = new BigDecimal(0);
		}
		//查询用户获取见点奖总数
		BigDecimal pointReward = klgRewardService.getRewardSumByTypeAndCustomerId(customerId, 1, "USDT");
		if(pointReward==null){
			pointReward = new BigDecimal(0);
		}

		//查询用户获取级差奖总数
		BigDecimal gradationReward = klgRewardService.getRewardSumByTypeAndCustomerId(customerId, 2, "USDT");
		if(gradationReward==null){
			gradationReward = new BigDecimal(0);
		}
		//查询用户卖出总利润
		BigDecimal sellProfitSum = sellTransactionService.getSellProfitSum(customerId);
		if(sellProfitSum==null){
			sellProfitSum = new BigDecimal(0);
		}

		//查询用户延期利息总和
		BigDecimal buyInterestSum = klgBuyTransactionService.getBuyInterestSum(customerId);
		if(buyInterestSum==null){
			buyInterestSum = new BigDecimal(0);
		}

		Map<String , Object> map = new HashMap<>();
		map.put("useBonusLimit", sellProfitSum.add(gradationReward).add(pointReward).setScale(2, BigDecimal.ROUND_HALF_DOWN));//使用额度
		map.put("surplusBonusLimit", surplusBonusLimit.setScale(2, BigDecimal.ROUND_HALF_DOWN));//剩余额度
		map.put("gradationGrade", gradationGrade.setScale(2, BigDecimal.ROUND_HALF_DOWN));//级差等级
		map.put("pointReward", pointReward.setScale(2, BigDecimal.ROUND_HALF_DOWN));//见点奖
		map.put("gradationReward", gradationReward.setScale(2, BigDecimal.ROUND_HALF_DOWN));//级差奖
		map.put("sellProfitSum", sellProfitSum.setScale(2, BigDecimal.ROUND_HALF_DOWN));//卖出利润
		map.put("buyInterestSum", buyInterestSum.setScale(2, BigDecimal.ROUND_HALF_DOWN));//延期利息
		map.put("allAssets", sellProfitSum.add(gradationReward).add(pointReward).setScale(2, BigDecimal.ROUND_HALF_DOWN));//总奖励折合
		JSONObject json = JSONObject.fromObject(map);
		return jsonResult.setSuccess(true).setObj(json);
	}

}
