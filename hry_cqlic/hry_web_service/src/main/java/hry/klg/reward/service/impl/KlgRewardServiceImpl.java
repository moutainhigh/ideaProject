/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-28 15:24:04 
 */
package hry.klg.reward.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.klg.assetsrecord.model.KlgAssetsRecord;
import hry.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.klg.enums.TriggerPointEnum;
import hry.klg.level.model.KlgCustomerLevel;
import hry.klg.level.service.KlgCustomerLevelService;
import hry.klg.reward.dao.KlgRewardDao;
import hry.klg.reward.model.KlgReward;
import hry.klg.reward.service.KlgRewardService;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import util.ToAccountUtil;

/**
 * <p> KlgRewardService </p>
 * @author:         lzy
 * @Date :          2019-04-28 15:24:04  
 */
@Service("klgRewardService")
public class KlgRewardServiceImpl extends BaseServiceImpl<KlgReward, Long> implements KlgRewardService{
	private  final static  String COINCODE="USDT";
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private KlgCustomerLevelService klgCustomerLevelService;
//	@Resource
//	private KlgTransactionRecordService klgTransactionRecordService;
	@Resource
	private KlgAssetsRecordService klgAssetsRecordService;
	@Resource(name="klgRewardDao")
	@Override
	public void setDao(BaseDao<KlgReward, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void saveKlgRewardRecord(Long foreignKey,String sellTransactionNum,Long customerId,Long accountId, BigDecimal money, Integer type) {

	//	RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
	//	UserRedis userRedis=redisUtil.get(customerId.toString());
		//ExDigitalmoneyAccountRedis toExaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(COINCODE).toString(), COINCODE);
		List<Accountadd> listLock=new ArrayList();
		KlgCustomerLevel levelConfig=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
		BigDecimal rewardNum=levelConfig.getRewardNum();//奖励额度
		if(rewardNum.compareTo(BigDecimal.ZERO)==0){
			return;
		}
		if(rewardNum.compareTo(money)==-1){//额度不够
			System.out.println(rewardNum+"奖励额度不足:"+customerId+",money:"+money);
			money=rewardNum;
		}
		JsonResult jsonResult=klgCustomerLevelService.reduceReward(customerId,money);
		if(!jsonResult.getSuccess()){
			System.out.println(rewardNum+"奖励额度不足:"+customerId+",money:"+money);
		}
		String  transactionNum= IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
		KlgReward klgReward=new KlgReward();
		klgReward.setCustomerId(customerId);
		klgReward.setSellTransactionNum(sellTransactionNum);//卖单流水
		klgReward.setTransactionNum(transactionNum);//流水
		klgReward.setAccountId(accountId);//数字货币账户id
		klgReward.setCoinCode(COINCODE);  //奖励币种
		klgReward.setRewardMoney(money);//奖励数量
		klgReward.setRewardType(type);//1见点奖 2级差奖

		KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//交易流水
		klgAssetsRecord.setCustomerId(customerId);//用户Id
		klgAssetsRecord.setAccountId(accountId);//币账户ID
		klgAssetsRecord.setSerialNumber(transactionNum);//流水号
		klgAssetsRecord.setAccountType(1);  //账户类型 1.热账户 2.冷账户
		klgAssetsRecord.setCoinCode(COINCODE);//币种Code
		klgAssetsRecord.setChangeCount(money); //交易量
		klgAssetsRecord.setChangeType(1);////变动类型 1加 2减
		TriggerPointEnum  triggerPoint=TriggerPointEnum.SeeRewards;//默认 见点
		if(2==type.intValue()){
			triggerPoint=TriggerPointEnum.GradedReward;
		}
		klgAssetsRecord.setTriggerPoint(triggerPoint.getKey());//触发点
		klgAssetsRecord.setTriggerSerialNumber(sellTransactionNum); //触发点流水号
		klgAssetsRecord.setRemark("奖励发放");
		klgAssetsRecordService.save(klgAssetsRecord);

	/*	KlgTransactionRecord transactionRecord=new KlgTransactionRecord();
		transactionRecord.setProjectNumber(transactionNum);//流水号
		transactionRecord.setType(type); //类型( )
		transactionRecord.setCoinCode(COINCODE);
		transactionRecord.setTransactionCount(money);  //交易量
		transactionRecord.setColdMoney(toExaccount.getColdMoney());  //冻结数量
		transactionRecord.setHotMoney(toExaccount.getHotMoney().add(money));  //可用数量
		transactionRecord.setCustomerId(customerId); //用户Id
		transactionRecord.setState(202);//状态类型(101冻结 默认102解冻201.支出202.收入)
		transactionRecord.setForeignKey(foreignKey);//外键Id
		transactionRecord.setRemark("奖励");
		klgTransactionRecordService.save(transactionRecord);*/
		super.save(klgReward);
		Accountadd accountadd= ToAccountUtil.ncomeAssets(accountId,money);//收入
		listLock.add(accountadd);
		messageProducer.toAccount(JSON.toJSONString(listLock));
	}

	@Override
	public Object saveKlgRewardRecordNew(Long foreignKey, String sellTransactionNum, Long customerId, Long accountId, BigDecimal money, Integer type) {
		List<Accountadd> listLock=new ArrayList();
		KlgCustomerLevel levelConfig=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
		BigDecimal rewardNum=levelConfig.getRewardNum();//奖励额度
		BigDecimal result=money;
		if(rewardNum.compareTo(BigDecimal.ZERO)==0){
			return money;
		}
		if(rewardNum.compareTo(money)==-1){//额度不够
			System.out.println(rewardNum+"奖励额度不足:"+customerId+",money:"+money);
			money=rewardNum;
			result=money.subtract(rewardNum);
		}else {
			result=new BigDecimal(0);
		}
		JsonResult jsonResult=klgCustomerLevelService.reduceReward(customerId,money);
		if(!jsonResult.getSuccess()){
			System.out.println(rewardNum+"奖励额度不足:"+customerId+",money:"+money);
			return result;
		}
		String  transactionNum= IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
		KlgReward klgReward=new KlgReward();
		klgReward.setCustomerId(customerId);
		klgReward.setSellTransactionNum(sellTransactionNum);//卖单流水
		klgReward.setTransactionNum(transactionNum);//流水
		klgReward.setAccountId(accountId);//数字货币账户id
		klgReward.setCoinCode(COINCODE);  //奖励币种
		klgReward.setRewardMoney(money);//奖励数量
		klgReward.setRewardType(type);//1见点奖 2级差奖

		KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//交易流水
		klgAssetsRecord.setCustomerId(customerId);//用户Id
		klgAssetsRecord.setAccountId(accountId);//币账户ID
		klgAssetsRecord.setSerialNumber(transactionNum);//流水号
		klgAssetsRecord.setAccountType(1);  //账户类型 1.热账户 2.冷账户
		klgAssetsRecord.setCoinCode(COINCODE);//币种Code
		klgAssetsRecord.setChangeCount(money); //交易量
		klgAssetsRecord.setChangeType(1);////变动类型 1加 2减
		TriggerPointEnum  triggerPoint=TriggerPointEnum.SeeRewards;//默认 见点
		if(2==type.intValue()){
			triggerPoint=TriggerPointEnum.GradedReward;
		}
		klgAssetsRecord.setTriggerPoint(triggerPoint.getKey());//触发点
		klgAssetsRecord.setTriggerSerialNumber(sellTransactionNum); //触发点流水号
		klgAssetsRecord.setRemark("奖励发放");
		klgAssetsRecordService.save(klgAssetsRecord);

		super.save(klgReward);
		Accountadd accountadd= ToAccountUtil.ncomeAssets(accountId,money);//收入
		listLock.add(accountadd);
		messageProducer.toAccount(JSON.toJSONString(listLock));
		return result;
	}

	@Override
	public BigDecimal getRewardSumByTypeAndCustomerId(Long customerId, Integer rewardType, String coinCode) {
		// TODO Auto-generated method stub
		Map<String , Object> map = new HashMap<>();
		map.put("customerId", customerId);
		map.put("rewardType", rewardType);
		map.put("coinCode", coinCode);
		return ((KlgRewardDao)dao).getRewardSumByTypeAndCustomerId(map);
	}
}
