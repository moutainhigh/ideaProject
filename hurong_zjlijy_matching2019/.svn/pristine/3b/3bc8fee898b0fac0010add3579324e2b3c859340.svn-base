/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:04:26 
 */
package hry.cm4.profitone.service.impl;

import com.alibaba.fastjson.JSON;
import hry.cm4.customer.model.Cm4Customer;
import hry.cm4.customer.service.Cm4CustomerService;
import hry.cm4.customerminer.service.Cm4CustomerMinerProfitService;
import hry.cm4.customerminer.service.Cm4CustomerMinerService;
import hry.cm4.deal.CmDealUtil;
import hry.cm4.dto.Accountadd;
import hry.cm4.dto.CmAccountRedis;
import hry.cm4.enums.CmAccountRemarkEnum;
import hry.cm4.grade.model.Cm4GradeHierarchy;
import hry.cm4.grade.model.Cm4GradeMiner;
import hry.cm4.grade.service.Cm4GradeHierarchyService;
import hry.cm4.grade.service.Cm4GradeMinerService;
import hry.cm4.profitone.dao.Cm4ProfitOneDao;
import hry.cm4.profitone.model.Cm4ProfitOne;
import hry.cm4.profitone.service.Cm4ProfitOneService;
import hry.cm4.team.service.Cm4TeamlevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.mq.producer.DealMsgUtil;
import hry.mq.producer.service.MessageProducer;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.utils.BaseConfUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Cm4ProfitOneService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:04:26  
 */
@Service("cm4ProfitOneService")
public class Cm4ProfitOneServiceImpl extends BaseServiceImpl<Cm4ProfitOne, Long> implements Cm4ProfitOneService{
	
	@Resource(name="cm4ProfitOneDao")
	@Override
	public void setDao(BaseDao<Cm4ProfitOne, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private Cm4CustomerMinerProfitService cmCustomerMinerProfitService;
	@Resource
	private Cm4CustomerMinerService cmCustomerMinerService;
	@Resource
	private Cm4GradeMinerService cmGradeMinerService;
	@Resource
	private Cm4TeamlevelService cmTeamlevelService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private Cm4CustomerService cmCustomerService;
	@Resource
	private Cm4GradeHierarchyService cmGradeHierarchyService;

	@Override
	public void profitOne(String messageStr) {
		// TODO Auto-generated method stub
		Cm4Customer cmCustomer = JSON.parseObject(messageStr, Cm4Customer.class);
		Long customerId = cmCustomer.getCustomerId();
		/**
		 * 功能描述:
		 * 1.查询用户当前等级奖励层级
		 * 2.根据获奖励得层级数查询每一层级所有用户今天产币总和
		 * 3.发放收益
		 * @auther: yaozh
		 * @date: 2019/10/17 16:24
		 */
		//查询用户今天产币
		BigDecimal profitSum = cmCustomerMinerProfitService.getProfitSumByCustomerId(customerId);
		if (profitSum == null) {
			return;
		}

		//1.查询用户当前等级奖励层级
		Cm4GradeMiner cmGradeMiner = cmGradeMinerService.getCmGradeMiner(cmCustomer.getGrade1());
		if (cmGradeMiner == null) {
			return;
		}

		// 计算用户动态收益封顶
		BigDecimal maxprofit = cmGradeMiner.getCappingMultiple().multiply(profitSum).setScale(8,
				BigDecimal.ROUND_HALF_UP);
		if (maxprofit == null) {
			return;
		}

		//2.根据获奖励得层级数查询每一层级所有用户今天产币总和
		for (int i = 1; i <= cmGradeMiner.getHierarchy(); i++) {
			//3.计算发放收益
			maxprofit = this.fafang(customerId,i,maxprofit,cmGradeMiner,profitSum);
		}

	}

	@Override
	public BigDecimal getTeamProfitSumByCustomerId(Long customerId, Integer type) {
		// TODO Auto-generated method stub
		return ((Cm4ProfitOneDao) dao).getTeamProfitSumByCustomerId(customerId, type);
	}

	@Override
	public BigDecimal getProfitSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm4ProfitOneDao) dao).getProfitSumByCustomerId(customerId);
	}

	/**
	 * 发放收益
	 * @param customerId 用户Id
	 * @param hierarchy 层级
	 * @param maxprofit 剩余封顶额度
	 * @param cmGradeMiner 等级信息
	 * @param profitSum 本人今天产币
	 */
	private BigDecimal fafang(Long customerId ,Integer hierarchy,BigDecimal maxprofit,Cm4GradeMiner cmGradeMiner,BigDecimal profitSum){
		//查询当前层级用户今天产币总和
		BigDecimal profit = cmCustomerMinerService.getProfitSumByCustomerIdAndHierarchy(customerId, hierarchy);
		if (profit == null) {
			return maxprofit;
		}
		//查询当前层级得奖励比例
		QueryFilter filtertHierarchy = new QueryFilter(Cm4GradeHierarchy.class);
		filtertHierarchy.addFilter("hierarchy=", hierarchy);
		Cm4GradeHierarchy cm2GradeHierarchy = cmGradeHierarchyService.get(filtertHierarchy);
		if (cm2GradeHierarchy == null) {
			return maxprofit;
		}

		//计算应发放奖励
		BigDecimal yingfaProfit = profit.multiply(cm2GradeHierarchy.getProfitProportion()
				.multiply(new BigDecimal(0.01))).setScale(8, BigDecimal.ROUND_HALF_UP);
		if (yingfaProfit.compareTo(new BigDecimal(0)) <= 0) {
			return maxprofit;
		}
		//计算实发奖励
		BigDecimal shifaProfit = new BigDecimal(0);
		if (maxprofit.compareTo(yingfaProfit) >= 0) {
			shifaProfit = yingfaProfit;
		}else{
			shifaProfit = maxprofit;
		}
		//剩余封顶额度
		maxprofit = maxprofit.subtract(shifaProfit);

		// 插入收益1记录
		this.insertProfit(cmGradeMiner.getCappingMultiple(), customerId, profitSum, 0L, cmGradeMiner.getGrade(),
				cmGradeMiner.getGradeName(), yingfaProfit, shifaProfit, cm2GradeHierarchy.getProfitProportion(),
				null, profit, 1,hierarchy);
		return maxprofit;
	}


	/**
	 * 插入收益记录
	 *
	 * @param cappingMultiple  封顶倍数
	 * @param customerId       收益人
	 * @param profitSum        收益人当天产出总数
	 * @param customerTeamId   产币层级
	 * @param grade            等级
	 * @param gradeName        等级名称
	 * @param yingfaprofit     应发收益
	 * @param shifaprofit      实发收益
	 * @param profitProportion 收益比例
	 * @param profitMoveSum    下级当天动态收益
	 * @param profitStaticSum  下级当天产出总数
	 * @param type             收益类型 1.拿一代静态收益 2拿一代动态收益
	 * @param hierarchy        层级
	 */
	private void insertProfit(BigDecimal cappingMultiple, Long customerId, BigDecimal profitSum, Long customerTeamId,
							  Integer grade, String gradeName, BigDecimal yingfaprofit, BigDecimal shifaprofit,
							  BigDecimal profitProportion, BigDecimal profitMoveSum, BigDecimal profitStaticSum,
							  Integer type,Integer hierarchy) {
		// 插入收益记录
		Cm4ProfitOne cmProfitOne = new Cm4ProfitOne();
		cmProfitOne.setCappingMultiple(cappingMultiple);
		cmProfitOne.setCustomerId(customerId);
		cmProfitOne.setCustomerProduce(profitSum);
		cmProfitOne.setCustomerTeamId(customerTeamId);
		cmProfitOne.setGrade(grade);
		cmProfitOne.setGradeName(gradeName);
		cmProfitOne.setProfit1(yingfaprofit);
		cmProfitOne.setProfit2(shifaprofit);
		cmProfitOne.setProfit3(yingfaprofit.subtract(shifaprofit));
		cmProfitOne.setProfitProportion(profitProportion);
		cmProfitOne.setStatus(2);
		cmProfitOne.setSubordinateDynamicProfit(profitMoveSum);// 下级当天动态收益
		cmProfitOne.setSubordinateProduce(profitStaticSum);
		cmProfitOne.setTransactionNum(IdGenerate.transactionNum("CM"));
		cmProfitOne.setType(type);// 收益类型 1.拿一代静态收益 2拿一代动态收益
		cmProfitOne.setHierarchy(hierarchy);
		int i = (int) super.save(cmProfitOne);

		/** 查询平台币Code */
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
		// 币账户加币
		/** 查询用户平台币账户信息 */
		CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(customerId, platCoin);

		List<Accountadd> accountaddList = new ArrayList<>();
		int remarkEnum = CmAccountRemarkEnum.TYPE8.getIndex();
		if (type == 2) {
			remarkEnum = CmAccountRemarkEnum.TYPE10.getIndex();
		}
		Accountadd accountadd = new Accountadd(customerId, platCoin, cmAccountRedis.getId(), shifaprofit, 1,
				remarkEnum, cmProfitOne.getTransactionNum());
		accountaddList.add(accountadd);
		DealMsgUtil.sendAccountaddList(accountaddList);
	}

	@Override
	public BigDecimal getProfit3SumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm4ProfitOneDao) dao).getProfit3SumByCustomerId(customerId);
	}

	@Override
	public BigDecimal getProfitAllByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm4ProfitOneDao) dao).getProfitAllByCustomerId(customerId);
	}



}
