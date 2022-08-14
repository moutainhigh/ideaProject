/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-30 11:33:21 
 */
package hry.cm.customerminer.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import hry.cm.customer.model.CmCustomer;
import hry.cm.customerminer.dao.CmCustomerMinerDao;
import hry.cm.customerminer.model.CmCustomerMiner;
import hry.cm.customerminer.service.CmCustomerMinerProfitService;
import hry.cm.customerminer.service.CmCustomerMinerService;
import hry.cm.deal.CmDealUtil;
import hry.cm.dto.Accountadd;
import hry.cm.dto.CmAccountRedis;
import hry.cm.enums.CmAccountRemarkEnum;
import hry.cm.log.service.CmExceptionLogService;
import hry.cm.mq.model.CoinRewardMessage;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mq.producer.DealMsgUtil;
import hry.mq.producer.service.MessageProducer;

/**
 * <p> CmCustomerMinerService </p>
 * @author:         yaozh
 * @Date :          2019-07-30 11:33:21  
 */
@Service("cmCustomerMinerService")
public class CmCustomerMinerServiceImpl extends BaseServiceImpl<CmCustomerMiner, Long> implements CmCustomerMinerService{
	
	@Resource(name="cmCustomerMinerDao")
	@Override
	public void setDao(BaseDao<CmCustomerMiner, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private CmExceptionLogService cmExceptionLogService;
	@Resource
	private CmCustomerMinerProfitService cmCustomerMinerProfitService;
	
	@Resource
	private MessageProducer messageProducer;

	@Override
	public void minerCoinageByDay(String message) {
		// TODO Auto-generated method stub
		try {
			CmCustomerMiner cmCustomerMiner = JSON.parseObject(message, CmCustomerMiner.class);
			/**判断收益领取类型minerProfitType1自动发放 2果树领取
			 * 1.插入产币记录，状态为已领取，
			 * 		矿机profit1 profit3增加
			 * 		币账户增加
			 * 2.插入产币记录，状态为已领取，发送mq消息到果树
			 * 		矿机profit2 profit3增加
			 * */
			if(cmCustomerMiner.getMinerProfitType()==1){
				/**自动发放*/
				BigDecimal dayProfit = cmCustomerMiner.getDayProfit();
				//1.更新矿机收益
				this.updateMinerProfit(cmCustomerMiner.getId(),dayProfit , null, dayProfit);
				
				//2.插入收益记录
				cmCustomerMinerProfitService.insertProit(cmCustomerMiner, 2);
				
				//3.币账户加币
				/** 查询用户平台币账户信息 */
				CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(cmCustomerMiner.getCustomerId(), cmCustomerMiner.getProfitCoinCode());

				List<Accountadd> accountaddList = new ArrayList<>();
				Accountadd accountadd = new Accountadd(cmCustomerMiner.getCustomerId(), cmCustomerMiner.getProfitCoinCode(), cmAccountRedis.getId(),
						dayProfit, 1, CmAccountRemarkEnum.TYPE7.getIndex(),
						cmCustomerMiner.getTransactionNum());
				accountaddList.add(accountadd);
				DealMsgUtil.sendAccountaddList(accountaddList);
				
			}else if(cmCustomerMiner.getMinerProfitType()==2){
				/**果树领取*/
				BigDecimal dayProfit = cmCustomerMiner.getDayProfit();
				//1.更新矿机收益
				this.updateMinerProfit(cmCustomerMiner.getId(), dayProfit, null, dayProfit);
				
				//2.插入收益记录
				cmCustomerMinerProfitService.insertProit(cmCustomerMiner, 3);//果树领取
				
				//3.发送消息到果树
				CoinRewardMessage coinRewardMessage = new CoinRewardMessage();
				coinRewardMessage.setBusinessNum("");
				coinRewardMessage.setBusinessType("");
				coinRewardMessage.setCoinCode(cmCustomerMiner.getProfitCoinCode());
				coinRewardMessage.setCustomerId(cmCustomerMiner.getCustomerId());
				coinRewardMessage.setRewardNum(dayProfit);
				coinRewardMessage.setRewardType(10);
				messageProducer.toSocialRewardKey(JSON.toJSONString(coinRewardMessage));
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//插入异常日志
			cmExceptionLogService.insertlog(e, "CmCustomerMinerServiceImpl.minerCoinageByDay", message);
		}
		
		
	}

	@Override
	public void updateMinerProfit(Long id, BigDecimal profit1, BigDecimal profit2, BigDecimal profit3) {
		// TODO Auto-generated method stub
		if(profit1==null){
			profit1 = new BigDecimal(0);
		}
		if(profit2==null){
			profit2 = new BigDecimal(0);
		}
		if(profit3==null){
			profit3 = new BigDecimal(0);
		}
		((CmCustomerMinerDao)dao).updateMinerProfit(id, profit1, profit2, profit3);
	}

	@Override
	public BigDecimal getMinerPriceSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerDao)dao).getMinerPriceSumByCustomerId(customerId);
	}

	@Override
	public BigDecimal getTeamMinerPriceSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerDao)dao).getTeamMinerPriceSumByCustomerId(customerId);
	}

	@Override
	public CmCustomer getTeamMaxAndMinByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerDao)dao).getTeamMaxAndMinByCustomerId(customerId);
	}

	@Override
	public Integer getTeamNumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerDao)dao).getTeamNumByCustomerId(customerId);
	}

	@Override
	public void updateCloseMiner() {
		// TODO Auto-generated method stub
		((CmCustomerMinerDao)dao).updateCloseMiner();
	}

	@Override
	public void updateWaitMiner() {
		// TODO Auto-generated method stub
		((CmCustomerMinerDao)dao).updateWaitMiner();
	}

	@Override
	public List<CmCustomerMiner> getMyMinerCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerDao)dao).getMyMinerCustomerId(customerId);
	}

	@Override
	public Integer getMinigNumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerDao)dao).getMinigNumByCustomerId(customerId);
	}

	@Override
	public BigDecimal getMinigProFitByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerDao)dao).getMinigProFitByCustomerId(customerId);
	}

	@Override
	public Integer getMinigNumByCustomerIdAndMinerId(Long customerId, Long minerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerDao)dao).getMinigNumByCustomerIdAndMinerId(customerId,minerId);
	}
	

}
