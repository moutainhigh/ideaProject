/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-31 20:25:57 
 */
package hry.cm.profitone.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import hry.cm.customer.model.CmCustomer;
import hry.cm.customer.service.CmCustomerService;
import hry.cm.customerminer.service.CmCustomerMinerProfitService;
import hry.cm.deal.CmDealUtil;
import hry.cm.dto.Accountadd;
import hry.cm.dto.CmAccountRedis;
import hry.cm.enums.CmAccountRemarkEnum;
import hry.cm.grade.model.CmGradeMiner;
import hry.cm.grade.model.CmGradeMinercamps;
import hry.cm.grade.service.CmGradeMinerService;
import hry.cm.profitone.dao.CmProfitOneDao;
import hry.cm.profitone.model.CmProfitOne;
import hry.cm.profitone.service.CmProfitOneService;
import hry.cm.team.model.CmTeamlevel;
import hry.cm.team.service.CmTeamlevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mq.producer.DealMsgUtil;
import hry.mq.producer.service.MessageProducer;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.utils.BaseConfUtil;

/**
 * <p>
 * CmProfitOneService
 * </p>
 * 
 * @author: yaozh
 * @Date : 2019-07-31 20:25:57
 */
@Service("cmProfitOneService")
public class CmProfitOneServiceImpl extends BaseServiceImpl<CmProfitOne, Long> implements CmProfitOneService {

	@Resource(name = "cmProfitOneDao")
	@Override
	public void setDao(BaseDao<CmProfitOne, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private CmCustomerMinerProfitService cmCustomerMinerProfitService;
	@Resource
	private CmGradeMinerService cmGradeMinerService;
	@Resource
	private CmTeamlevelService cmTeamlevelService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private CmCustomerService cmCustomerService;

	@Override
	public void profitOne(String messageStr) {
		// TODO Auto-generated method stub
		CmCustomer cmCustomer = JSON.parseObject(messageStr, CmCustomer.class);
		Long customerId = cmCustomer.getCustomerId();
		/**
		 * 1.查询直推用户今天静态收益总数，用户矿机产出即为静态收益 2.查询直推用户今天动态收益总和 3.计算当前用户收益信息 4.插入收益记录
		 * 5.发送mq消息计算矿场收益
		 */
		// 1.查询直推用户今天静态收益总数
		BigDecimal profitMyStaticSum = cmCustomerMinerProfitService.getTeamProfitSumByCustomerId(customerId);
		if (profitMyStaticSum == null) {
			profitMyStaticSum = new BigDecimal(0);
		}

		// 2.查询直推用户今天动态收益总和
		BigDecimal profitMyMoveSum = this.getTeamProfitSumByCustomerId(customerId, null);
		if (profitMyMoveSum == null) {
			profitMyMoveSum = new BigDecimal(0);
		}
		// 如果动态收益加静态收益等于0 说明没有收益， 直接结束
		if (profitMyMoveSum.add(profitMyStaticSum).compareTo(new BigDecimal(0)) <= 0) {
			return;
		}

		// 3.计算当前用户收益信息
		// 查询用户今天产币信息
		BigDecimal profitSum = cmCustomerMinerProfitService.getProfitSumByCustomerId(customerId);
		if (profitSum == null) {
			profitSum = new BigDecimal(0);
		}
		// 用户产币为0 按照紧缩计算法 将收益发放到上一级，依次类推
		if (profitSum.compareTo(new BigDecimal(0)) <= 0) {
			// 查询用户所有上级
			QueryFilter filtertTeam = new QueryFilter(CmTeamlevel.class);
			filtertTeam.addFilter("customerId=", customerId);
			filtertTeam.setOrderby("level asc");
			List<CmTeamlevel> listTeam = cmTeamlevelService.find(filtertTeam);
			if (listTeam != null && listTeam.size() > 0) {
				for (CmTeamlevel cmTeamlevel : listTeam) {
					//查询用户矿工等级
					QueryFilter filtertTeamCustomer = new QueryFilter(CmTeamlevel.class);
					filtertTeamCustomer.addFilter("customerId=", cmTeamlevel.getParentId());
					CmCustomer cmCustomerTeam = cmCustomerService.get(filtertTeamCustomer);
					if(cmCustomerTeam==null){
						continue;
					}
					// 3.计算当前用户收益信息
					// 查询用户今天产币信息
					profitSum = cmCustomerMinerProfitService.getProfitSumByCustomerId(cmTeamlevel.getParentId());
					if (profitSum != null) {
						this.faFangProfig(cmTeamlevel.getParentId(), customerId, cmCustomerTeam.getGrade1(), profitSum,
								profitMyMoveSum, profitMyStaticSum);
						break;
					}
					
				}
			}
		} else {
			this.faFangProfig(customerId, customerId, cmCustomer.getGrade1(), profitSum, profitMyMoveSum,
					profitMyStaticSum);
		}

		/** 发送mq消息，发放矿场动态收益 */
		messageProducer.toCmProfit2Key(JSON.toJSONString(cmCustomer));

	}

	@Override
	public BigDecimal getTeamProfitSumByCustomerId(Long customerId, Integer type) {
		// TODO Auto-generated method stub
		return ((CmProfitOneDao) dao).getTeamProfitSumByCustomerId(customerId, type);
	}

	@Override
	public BigDecimal getProfitSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmProfitOneDao) dao).getProfitSumByCustomerId(customerId);
	}

	/**
	 * 发放收益
	 * 
	 * @param customerId
	 *            收益用户ID
	 * @param customerTeamId
	 *            产出币层级的推荐人id
	 * @param grade
	 *            等级
	 * @param profitSum
	 *            用户今天产币
	 * @param profitMoveSum
	 *            直推用户今天动态收益总数
	 * @param profitStaticSum
	 *            直推用户今天静态收益总数
	 * @return
	 */
	private void faFangProfig(Long customerId, Long customerTeamId, int grade, BigDecimal profitSum,
			BigDecimal profitMoveSum, BigDecimal profitStaticSum) {
		/**计算动态收益1**/
		// 查询用户矿工等级信息
		CmGradeMiner cmGradeMiner = cmGradeMinerService.getCmGradeMiner(grade);
		if (cmGradeMiner == null) {
			return;
		}

		// 查询用户今天动态收益总和
		BigDecimal thisProfitSum = getProfitSumByCustomerId(customerId);
		if (thisProfitSum == null) {
			thisProfitSum = new BigDecimal(0);
		}
		// 计算用户动态收益封顶
		BigDecimal maxprofit = cmGradeMiner.getCappingMultiple().multiply(profitSum).setScale(8,
				BigDecimal.ROUND_HALF_UP);
		if (maxprofit == null) {
			maxprofit = new BigDecimal(0);
		}
		// 计算可用封顶收益
		BigDecimal keyongprofit = maxprofit.subtract(thisProfitSum);
		// 计算应发收益 = (profitMoveSum+profitStaticSum)* 动态收益比例
		BigDecimal yingfaprofit = profitStaticSum.multiply(cmGradeMiner.getProfitProportion())
				.multiply(new BigDecimal(0.01)).setScale(8, BigDecimal.ROUND_HALF_UP);
		// 计算实发收益 ,如果keyongprofit>=yingfaprofit,那么 实发收益 = 应发收益
		// 如果keyongprofit<yingfaprofit,那么 实发收益 = 可用封顶收益
		BigDecimal shifaprofit = new BigDecimal(0);
		if (keyongprofit.compareTo(yingfaprofit) >= 0) {
			shifaprofit = yingfaprofit;
		} else {
			shifaprofit = keyongprofit;
		}
		if(shifaprofit.compareTo(new BigDecimal(0))>0){
			// 插入收益1记录
			this.insertProfit(cmGradeMiner.getCappingMultiple(), customerId, thisProfitSum, customerTeamId, grade,
					cmGradeMiner.getGradeName(), yingfaprofit, shifaprofit, cmGradeMiner.getProfitProportion(),
					profitMoveSum, profitStaticSum, 1);
		}
		
		/**2.计算动态收益2*/
		//收益比例
		BigDecimal profitProportion = cmGradeMiner.getProfitProportion2();
		if(profitMoveSum.compareTo(new BigDecimal(0))>0){
			BigDecimal profit = profitMoveSum.multiply(profitProportion).multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_HALF_UP);
			if(profit.compareTo(new BigDecimal(0))>0){
				//插入收益2记录
				this.insertProfit(cmGradeMiner.getCappingMultiple(), customerId, thisProfitSum, customerTeamId, grade,
						cmGradeMiner.getGradeName(), profit, profit, profitProportion,
						profitMoveSum, profitStaticSum, 2);
			}
			
		}
		
	}

	/**
	 * 插入收益记录
	 * @param cappingMultiple 封顶倍数
	 * @param customerId 收益人
	 * @param profitSum 收益人当天产出总数
	 * @param customerTeamId 产币层级
	 * @param grade 等级
	 * @param gradeName 等级名称
	 * @param yingfaprofit 应发收益
	 * @param shifaprofit 实发收益
	 * @param profitProportion 收益比例
	 * @param profitMoveSum 下级当天动态收益
	 * @param profitStaticSum 下级当天产出总数
	 * @param type 收益类型 1.拿一代静态收益 2拿一代动态收益
	 */
	private void insertProfit(BigDecimal cappingMultiple, Long customerId, BigDecimal profitSum, Long customerTeamId,
			Integer grade, String gradeName, BigDecimal yingfaprofit, BigDecimal shifaprofit,
			BigDecimal profitProportion, BigDecimal profitMoveSum, BigDecimal profitStaticSum, Integer type) {
		// 插入收益记录
		CmProfitOne cmProfitOne = new CmProfitOne();
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
		int i = (int) super.save(cmProfitOne);
		System.out.println("矿工收益插入数据："+i+"==="+JSON.toJSONString(cmProfitOne));
		
		/** 查询平台币Code */
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
		// 币账户加币

		int remarkEnum = CmAccountRemarkEnum.TYPE8.getIndex();
		if(type==2){
			remarkEnum = CmAccountRemarkEnum.TYPE10.getIndex();
		}
		/**
		 * 功能描述: 需求变动，收益百分之30进入到冻结账户，等后期功能开发好后再修改回来，然后百分之30进到消费子账户
		 * Accountadd accountadd = new Accountadd(customerId, platCoin, cmAccountRedis.getId(), shifaprofit, 1,
		 * 				remarkEnum, cmProfitOne.getTransactionNum());
		 * accountaddList.add(accountadd);
		 * @auther: yaozh
		 * @date: 2019/11/1 16:16
		 */
		/** 查询进入冻结的金额的比例 */
		String cloudminingP = BaseConfUtil.getConfigSingle("cloudminingCProportion", "configCache:cloudminingConfig");
		BigDecimal cp = new BigDecimal(cloudminingP).multiply(new BigDecimal(0.01));
		//计算进入冻结的金额
		BigDecimal jinDongjie = shifaprofit.multiply(cp);
		//计算进可用账户
		BigDecimal jinKeyong = shifaprofit.subtract(jinDongjie);

		/** 查询用户平台币账户信息 */
		CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(customerId, platCoin);
		List<Accountadd> accountaddList = new ArrayList<>();
		//进入可用账户
		Accountadd accountaddHot = new Accountadd(customerId, platCoin, cmAccountRedis.getId(), jinKeyong, 1,
				remarkEnum, cmProfitOne.getTransactionNum());
		accountaddList.add(accountaddHot);

		/**查询用户平台币消费账户信息**/
		hry.cmson.dto.CmAccountRedis cmSonAccountRedis = hry.cmson.deal.CmDealUtil.getCmAccount(customerId, platCoin);
		List<hry.cmson.dto.Accountadd> accountSonaddList = new ArrayList<>();
		//进入消费账户
		hry.cmson.dto.Accountadd accountaddCold = new hry.cmson.dto.Accountadd(customerId, platCoin, cmSonAccountRedis.getId(), jinDongjie, 1,
				remarkEnum, cmProfitOne.getTransactionNum());
		accountSonaddList.add(accountaddCold);

		//发送消息
		DealMsgUtil.sendAccountaddList(accountaddList);
		DealMsgUtil.sendSonAccountaddList(accountSonaddList);
	}

	@Override
	public BigDecimal getProfit3SumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmProfitOneDao) dao).getProfit3SumByCustomerId(customerId);
	}

	@Override
	public BigDecimal getProfitAllByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmProfitOneDao) dao).getProfitAllByCustomerId(customerId);
	}


}
