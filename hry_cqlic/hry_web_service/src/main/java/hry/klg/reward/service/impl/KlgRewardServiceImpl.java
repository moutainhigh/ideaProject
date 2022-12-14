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
		BigDecimal rewardNum=levelConfig.getRewardNum();//????????????
		if(rewardNum.compareTo(BigDecimal.ZERO)==0){
			return;
		}
		if(rewardNum.compareTo(money)==-1){//????????????
			System.out.println(rewardNum+"??????????????????:"+customerId+",money:"+money);
			money=rewardNum;
		}
		JsonResult jsonResult=klgCustomerLevelService.reduceReward(customerId,money);
		if(!jsonResult.getSuccess()){
			System.out.println(rewardNum+"??????????????????:"+customerId+",money:"+money);
		}
		String  transactionNum= IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
		KlgReward klgReward=new KlgReward();
		klgReward.setCustomerId(customerId);
		klgReward.setSellTransactionNum(sellTransactionNum);//????????????
		klgReward.setTransactionNum(transactionNum);//??????
		klgReward.setAccountId(accountId);//??????????????????id
		klgReward.setCoinCode(COINCODE);  //????????????
		klgReward.setRewardMoney(money);//????????????
		klgReward.setRewardType(type);//1????????? 2?????????

		KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//????????????
		klgAssetsRecord.setCustomerId(customerId);//??????Id
		klgAssetsRecord.setAccountId(accountId);//?????????ID
		klgAssetsRecord.setSerialNumber(transactionNum);//?????????
		klgAssetsRecord.setAccountType(1);  //???????????? 1.????????? 2.?????????
		klgAssetsRecord.setCoinCode(COINCODE);//??????Code
		klgAssetsRecord.setChangeCount(money); //?????????
		klgAssetsRecord.setChangeType(1);////???????????? 1??? 2???
		TriggerPointEnum  triggerPoint=TriggerPointEnum.SeeRewards;//?????? ??????
		if(2==type.intValue()){
			triggerPoint=TriggerPointEnum.GradedReward;
		}
		klgAssetsRecord.setTriggerPoint(triggerPoint.getKey());//?????????
		klgAssetsRecord.setTriggerSerialNumber(sellTransactionNum); //??????????????????
		klgAssetsRecord.setRemark("????????????");
		klgAssetsRecordService.save(klgAssetsRecord);

	/*	KlgTransactionRecord transactionRecord=new KlgTransactionRecord();
		transactionRecord.setProjectNumber(transactionNum);//?????????
		transactionRecord.setType(type); //??????( )
		transactionRecord.setCoinCode(COINCODE);
		transactionRecord.setTransactionCount(money);  //?????????
		transactionRecord.setColdMoney(toExaccount.getColdMoney());  //????????????
		transactionRecord.setHotMoney(toExaccount.getHotMoney().add(money));  //????????????
		transactionRecord.setCustomerId(customerId); //??????Id
		transactionRecord.setState(202);//????????????(101?????? ??????102??????201.??????202.??????)
		transactionRecord.setForeignKey(foreignKey);//??????Id
		transactionRecord.setRemark("??????");
		klgTransactionRecordService.save(transactionRecord);*/
		super.save(klgReward);
		Accountadd accountadd= ToAccountUtil.ncomeAssets(accountId,money);//??????
		listLock.add(accountadd);
		messageProducer.toAccount(JSON.toJSONString(listLock));
	}

	@Override
	public Object saveKlgRewardRecordNew(Long foreignKey, String sellTransactionNum, Long customerId, Long accountId, BigDecimal money, Integer type) {
		List<Accountadd> listLock=new ArrayList();
		KlgCustomerLevel levelConfig=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
		BigDecimal rewardNum=levelConfig.getRewardNum();//????????????
		BigDecimal result=money;
		if(rewardNum.compareTo(BigDecimal.ZERO)==0){
			return money;
		}
		if(rewardNum.compareTo(money)==-1){//????????????
			System.out.println(rewardNum+"??????????????????:"+customerId+",money:"+money);
			money=rewardNum;
			result=money.subtract(rewardNum);
		}else {
			result=new BigDecimal(0);
		}
		JsonResult jsonResult=klgCustomerLevelService.reduceReward(customerId,money);
		if(!jsonResult.getSuccess()){
			System.out.println(rewardNum+"??????????????????:"+customerId+",money:"+money);
			return result;
		}
		String  transactionNum= IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
		KlgReward klgReward=new KlgReward();
		klgReward.setCustomerId(customerId);
		klgReward.setSellTransactionNum(sellTransactionNum);//????????????
		klgReward.setTransactionNum(transactionNum);//??????
		klgReward.setAccountId(accountId);//??????????????????id
		klgReward.setCoinCode(COINCODE);  //????????????
		klgReward.setRewardMoney(money);//????????????
		klgReward.setRewardType(type);//1????????? 2?????????

		KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//????????????
		klgAssetsRecord.setCustomerId(customerId);//??????Id
		klgAssetsRecord.setAccountId(accountId);//?????????ID
		klgAssetsRecord.setSerialNumber(transactionNum);//?????????
		klgAssetsRecord.setAccountType(1);  //???????????? 1.????????? 2.?????????
		klgAssetsRecord.setCoinCode(COINCODE);//??????Code
		klgAssetsRecord.setChangeCount(money); //?????????
		klgAssetsRecord.setChangeType(1);////???????????? 1??? 2???
		TriggerPointEnum  triggerPoint=TriggerPointEnum.SeeRewards;//?????? ??????
		if(2==type.intValue()){
			triggerPoint=TriggerPointEnum.GradedReward;
		}
		klgAssetsRecord.setTriggerPoint(triggerPoint.getKey());//?????????
		klgAssetsRecord.setTriggerSerialNumber(sellTransactionNum); //??????????????????
		klgAssetsRecord.setRemark("????????????");
		klgAssetsRecordService.save(klgAssetsRecord);

		super.save(klgReward);
		Accountadd accountadd= ToAccountUtil.ncomeAssets(accountId,money);//??????
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
