package hry.customer.agents.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import hry.account.fund.model.AppTransaction;
import hry.account.remote.RemoteAppTransactionService;
import hry.change.remote.exEntrust.RemoteExExOrderService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.AppTradeFactorage;
import hry.customer.agents.model.CommissionDeploy;
import hry.customer.agents.service.AppAgentsCustromerService;
import hry.customer.agents.service.AppTradeFactorageService;
import hry.customer.agents.service.CommissionDeployService;
import hry.customer.agents.service.CommissionDetailService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.subscription.model.ExSubscriptionPlan;
import hry.trade.entrust.model.ExOrderInfo;

@Service("appTradeFactorageService")
public class AppTradeFactorageServiceImpl extends BaseServiceImpl<AppTradeFactorage,Long> implements AppTradeFactorageService {
	private static Logger logger = Logger.getLogger(AppTradeFactorageServiceImpl.class);
	@Resource(name="appTradeFactorageDao")
	@Override
	public void setDao(BaseDao<AppTradeFactorage, Long> dao) {
		super.dao = dao;
	}

	@Resource(name="commissionDeployService")
	private CommissionDeployService commissionDeployService;
	
	
	@Resource(name="appCustomerService")
	private AppCustomerService appCustomerService;
	
	@Resource(name="commissionDetailService")
	public CommissionDetailService commissionDetailService;
	
	@Resource(name="appAgentsCustromerService")
	public AppAgentsCustromerService appAgentsCustromerService;
	
	/**
	 * 传入一个成交单  计算给卖家应交的钱 并保存
	 * 
	 * 如果返回 false 可能是用户的卖方id查不到用户  或者该用不存在父推荐人 不需要交佣金
	 * 
	 */
	@Override
	public Boolean dealCommission(String orderNum,Integer type) {
	RemoteExExOrderService remoteOrderServiceService = (RemoteExExOrderService)ContextUtil.getBean("remoteExExOrderService");
		
		List<ExOrderInfo> list = remoteOrderServiceService.findByOrderNum(orderNum);
		
		if(null != list && list.size()>0 ){
			for (int i= 0 ;i<list.size();i++){
			
			ExOrderInfo orderInfo = list.get(i);
			/*if(2==orderInfo.getType()){
	            // 用户交的手续费   根据手续费收取一定的佣金      
				BigDecimal transactionSellFee = orderInfo.getTransactionSellFee();
				//String fixPriceCoinCode = orderInfo.getFixPriceCoinCode();
				String fixPriceCoinCode = orderInfo.getCoinCode();
				Integer fixPriceType = orderInfo.getFixPriceType();
				if(transactionSellFee.compareTo(BigDecimal.ZERO)>0){
					AppCustomer sellCustomer = appCustomerService.get(orderInfo.getSellCustomId());
					List<AppAgentsCustromer> list2 = this.saveAppTradeFactorage1(sellCustomer, transactionSellFee, fixPriceCoinCode,fixPriceType, type);
					if(list2.size()>0){
						for(int j =0;j<list2.size();j++){
							if(null !=list2.get(j)){
								commissionDetailService.saveCommissionDetailForOrder(orderInfo, list2.get(j), Integer.valueOf( list2.get(j).getFrontBank()));
							}
						}
					}
					return true;
				}
				}if(1 == orderInfo.getType()){	*/
					BigDecimal transactionBuyFee = orderInfo.getTransactionBuyFee();
					//String fixPriceCoinCode = orderInfo.getFixPriceCoinCode();
					Integer fixPriceType = orderInfo.getFixPriceType();
					String fixPriceCoinCode = orderInfo.getCoinCode();
					if(transactionBuyFee.compareTo(BigDecimal.ZERO)>0){
						AppCustomer buyCustomer = appCustomerService.get(orderInfo.getBuyCustomId());
						List<AppAgentsCustromer> list2 = this.saveAppTradeFactorage1(buyCustomer, transactionBuyFee,fixPriceCoinCode,fixPriceType, type);
						if(list2.size()>0){
							for(int j =0;j<list2.size();j++){
								if(list2.get(j) != null){
									commissionDetailService.saveCommissionDetailForOrder(orderInfo, list2.get(j), Integer.valueOf( list2.get(j).getFrontBank()));
								}
							}
						}
						return true;
					}
			//	}
			}
		}
		return false;
		}
	
	

	/**
	 * 保存订单的佣金   包括明细  
	 * 
	 */
	@Override
	public Boolean dealCommissionByTraction(String tractionNum,Integer type) {
		
		RemoteAppTransactionService remoteAppTransactionService = (RemoteAppTransactionService)ContextUtil.getBean("remoteAppTransactionService");
		
		// 根据订单号 查询订单
		AppTransaction appTransaction = remoteAppTransactionService.createTranctonByOrderNum(tractionNum);
		
		if(null != appTransaction){
			// 用户交的手续费 
			BigDecimal commissionMoney = appTransaction.getFee();
			// 查询用户
			AppCustomer customer = appCustomerService.get(appTransaction.getCustomerId());
			//  传入一个钱  交款人 类型  保存佣金
			List<AppAgentsCustromer> list = this.saveAppTradeFactorage(customer, commissionMoney, type);
			
			// 保存  订单佣金明细
			if(list.size()>0){
				for(int i =0;i<list.size();i++){
					if(list.get(i) != null){
						commissionDetailService.saveCommissionDetail(appTransaction,list.get(i),i+1);
					}
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * 保存认购返佣金额
	 */
	@Override
	public Boolean dealCommissionBySubscription(ExSubscriptionPlan plan,Long customerId,BigDecimal buyTotalNum,String transactionNum) {
		AppCustomer customer = appCustomerService.get(customerId);
		//查询是否有一级推荐人信息，是否设置了一级推荐奖励
		AppAgentsCustromer agentsCustromerOne = appAgentsCustromerService.findAgentsByCustromer(customer.getUserName(), 1);
		if(null!=agentsCustromerOne&&null!=plan.getSratioOne()){
			//认购返佣比例一级
			BigDecimal sration = plan.getSratioOne();
			if(sration.compareTo(new BigDecimal("0"))>0){
				BigDecimal srationMoney = buyTotalNum.multiply(sration.divide(new BigDecimal("100"),4,RoundingMode.DOWN));
				//每个用户给他的父交的钱
				AppCustomer appCustomer = appCustomerService.get(agentsCustromerOne.getCustomerId());
				this.saveSubscriptionAppTradeFactorage(agentsCustromerOne, srationMoney, 1);
				//佣金明细
				commissionDetailService.saveCommissionDetailSubscription(customer, agentsCustromerOne, srationMoney,
						transactionNum, 1, sration);
			}
		}
		//查询是否有二级推荐人信息，是否设置了二级推荐奖励
		AppAgentsCustromer agentsCustromerTwo = appAgentsCustromerService.findAgentsByCustromer(customer.getUserName(), 2);
		if(null!=agentsCustromerTwo&&null!=plan.getSratioTwo()){
			//认购返佣比例一级
			BigDecimal sration = plan.getSratioTwo();
			if(sration.compareTo(new BigDecimal("0"))>0){
				BigDecimal srationMoney = buyTotalNum.multiply(sration.divide(new BigDecimal("100"),4,RoundingMode.DOWN));
				//每个用户给他的父交的钱
				AppCustomer appCustomer = appCustomerService.get(agentsCustromerTwo.getCustomerId());
				this.saveSubscriptionAppTradeFactorage(agentsCustromerTwo, srationMoney, 2);
				//佣金明细
				commissionDetailService.saveCommissionDetailSubscription(customer, agentsCustromerTwo, srationMoney,
						transactionNum, 2, sration);
			}
		}
		//查询是否有三级推荐人信息，是否设置了三级推荐奖励
		AppAgentsCustromer agentsCustromerTheree = appAgentsCustromerService.findAgentsByCustromer(customer.getUserName(), 3);
		if(null!=agentsCustromerTheree&&null!=plan.getSratioTheree()){
			//认购返佣比例一级
			BigDecimal sration = plan.getSratioTheree();
			if(sration.compareTo(new BigDecimal("0"))>0){
				BigDecimal srationMoney = buyTotalNum.multiply(sration.divide(new BigDecimal("100"),4,RoundingMode.DOWN));
				//每个用户给他的父交的钱
				AppCustomer appCustomer = appCustomerService.get(agentsCustromerTheree.getCustomerId());
				this.saveSubscriptionAppTradeFactorage(agentsCustromerTheree, srationMoney, 3);
				//佣金明细
				commissionDetailService.saveCommissionDetailSubscription(customer, agentsCustromerTheree, srationMoney,
						transactionNum, 3, sration);
			}
		}
		return true;
	}
	/**
	 * 每个用户给他的父交的钱
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    
	 * @return: void 
	 * @Date :          2016年12月12日 下午6:17:38   
	 * @throws:
	 */
	public void saveSubscriptionAppTradeFactorage(AppAgentsCustromer customer,BigDecimal srationMoney,Integer sration){
		AppTradeFactorage factorage = this.findFactorageByCustromerId(customer.getId());
		if(null == factorage){
			AppTradeFactorage appTradeFactorage = new AppTradeFactorage();
			appTradeFactorage.setCustromerId(customer.getId());
			appTradeFactorage.setCustromerName(customer.getCustomerName());
			appTradeFactorage.setOneParentMoney(BigDecimal.ZERO);
			appTradeFactorage.setTwoParentMoney(BigDecimal.ZERO);
			appTradeFactorage.setThreeParentMoney(BigDecimal.ZERO);
			super.save(appTradeFactorage);
			factorage = appTradeFactorage;
		}
		if(sration == 1){//一级推荐
			factorage.setOneParentName(customer.getCustomerName());
			BigDecimal money = factorage.getOneParentMoney();
			factorage.setOneParentMoney(srationMoney.add(money));
			factorage.setOneCommissionRate(BigDecimal.ZERO);
		}else if (sration == 2){//二级推荐
			factorage.setTwoParentName(customer.getCustomerName());
			BigDecimal money = factorage.getTwoParentMoney();
			factorage.setTwoParentMoney(srationMoney.add(money));
			factorage.setTwoCommissionRate(BigDecimal.ZERO);
		}else if (sration == 3){//三级推荐
			factorage.setThreeParentName(customer.getCustomerName());
			BigDecimal money = factorage.getThreeParentMoney();
			factorage.setThreeParentMoney(srationMoney.add(money));
			factorage.setThreeCommissionRate(BigDecimal.ZERO);
		}
		super.update(factorage);
	}
	/**
	 * 
	 * 保存佣金都使用这个方法
	 * 
	 * 传入一个钱  交款人 类型  保存佣金
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月3日 下午2:35:10
	 * 
	 * customer 用户
	 * commissionMoney 计算佣金的钱数
	 * type  2 表示交易  1 表示提现
	 * 
	 */
	public List<AppAgentsCustromer> saveAppTradeFactorage1(AppCustomer customer ,BigDecimal commissionMoney,String fixPriceCoinCode,int fixPriceType,int type){
		
		AppTradeFactorage factorage = null;
		
		List<AppAgentsCustromer> list2 = new ArrayList<AppAgentsCustromer>();
		
		List<AppAgentsCustromer> list = commissionDeployService.findByTransaction(customer.getUserName());
		logger.error("传入username="+customer.getUserName());
		
		if(list.size()>0){
			logger.error("有父节点会进来");
			AppTradeFactorage factorage1 = this.findFactorageByCustromerId1(customer.getId(),fixPriceCoinCode);
		
			// 根据收的手续费 返回一个钱数  第二个参数为费率类型   第三个参数的费率类型为   几级父     
			//if(factorage.getFixPriceCoinCode().equals(fixPriceCoinCode)){
				if(factorage1==null){
					AppTradeFactorage appTradeFactorage = new AppTradeFactorage();
					appTradeFactorage.setCustromerId(customer.getId());
					appTradeFactorage.setCustromerName(customer.getUserName());
					appTradeFactorage.setOneParentMoney(BigDecimal.ZERO);
					appTradeFactorage.setTwoParentMoney(BigDecimal.ZERO);
					appTradeFactorage.setThreeParentMoney(BigDecimal.ZERO);
					appTradeFactorage.setFixPriceCoinCode(fixPriceCoinCode);
					appTradeFactorage.setFixPriceType(fixPriceType);
					super.save(appTradeFactorage);
					factorage = appTradeFactorage;
				}else if(factorage1!=null){
					if(!factorage1.getFixPriceCoinCode().equals(fixPriceCoinCode)){
					AppTradeFactorage appTradeFactorage = new AppTradeFactorage();
					appTradeFactorage.setCustromerId(customer.getId());
					appTradeFactorage.setCustromerName(customer.getUserName());
					appTradeFactorage.setOneParentMoney(BigDecimal.ZERO);
					appTradeFactorage.setTwoParentMoney(BigDecimal.ZERO);
					appTradeFactorage.setThreeParentMoney(BigDecimal.ZERO);
					appTradeFactorage.setFixPriceCoinCode(fixPriceCoinCode);
					appTradeFactorage.setFixPriceType(fixPriceType);
					super.save(appTradeFactorage);
					factorage = appTradeFactorage;
					}else{
						factorage = factorage1;
					}
				}else{
					factorage = factorage1;
				}
			
			
			// 通过一个佣金类型 和几级代理商 返回一个佣金配置对象
			CommissionDeploy commissionDeploy = commissionDeployService.findCommissionDeploy(type);
			if(null != commissionDeploy){
			
				BigDecimal bigDecimalOneRate = commissionDeploy.getOneRankRatio().divide(new BigDecimal("100"));
				
				// 判断用户的手续费是否大于后台配置的佣金标准
				if(commissionDeploy.getOneStandardValue().compareTo(commissionMoney) <= 0){
					// 计算佣金
					BigDecimal oneMoney = commissionMoney.multiply(bigDecimalOneRate);
					factorage.setOneParentName(list.get(0).getCustomerName());
					BigDecimal oldOneMoney = factorage.getOneParentMoney();
					factorage.setOneParentMoney(oneMoney.add(oldOneMoney));
					// 保存费率
					factorage.setOneCommissionRate(bigDecimalOneRate);
					AppAgentsCustromer aa=new AppAgentsCustromer();
					aa.setCustomerName(list.get(0).getCustomerName());
					aa.setFrontBank("1");
					aa.setId(list.get(0).getId());
					list.set(0, aa);
					list2.add(list.get(0));
				}else{
					//list2.add(null);
				}
				// 判断用户的手续费是否大于后台配置的佣金标准
				if(list.size()>1 && commissionDeploy.getTwoStandardValue().compareTo(commissionMoney) <= 0){
					BigDecimal bigDecimalTwoRate = commissionDeploy.getTwoRankRatio().divide(new BigDecimal("100"));
					BigDecimal twoMoney = commissionMoney.multiply(bigDecimalTwoRate);
					factorage.setTwoParentName(list.get(1).getCustomerName());
					BigDecimal oldtwoMoney = factorage.getTwoParentMoney();
					factorage.setTwoParentMoney(twoMoney.add(oldtwoMoney));
					factorage.setTwoCommissionRate(bigDecimalTwoRate);
					AppAgentsCustromer aa=new AppAgentsCustromer();
					aa.setCustomerName(list.get(1).getCustomerName());
					aa.setId(list.get(1).getId());
					aa.setFrontBank("2");
					list.set(1, aa);
					list2.add(list.get(1));
				}else{
					//list2.add(null);
				}
				// 判断用户的手续费是否大于后台配置的佣金标准
				if(list.size()>2 && commissionDeploy.getThreeStandardValue().compareTo(commissionMoney) <= 0){
					BigDecimal bigDecimalTwoRate = commissionDeploy.getThreeRankRatio().divide(new BigDecimal("100"));
					BigDecimal threeMoney = bigDecimalTwoRate.multiply(commissionMoney);
					factorage.setThreeParentName(list.get(2).getCustomerName());
					BigDecimal oldtwoMoney = factorage.getThreeParentMoney();
					factorage.setThreeParentMoney(threeMoney.add(oldtwoMoney));
					factorage.setThreeCommissionRate(bigDecimalTwoRate);
					AppAgentsCustromer aa=new AppAgentsCustromer();
					aa.setCustomerName(list.get(2).getCustomerName());
					aa.setId(list.get(2).getId());
					aa.setFrontBank("3");
					list.set(2, aa);
					list2.add(list.get(2));
				}else{
					//list2.add(null);
				}
			}
				
				super.update(factorage);
			

		}else{
			logger.error("代理商为空");
		}
	return list2;
		
	}
	
	
	/**
	 * 
	 * 保存佣金都使用这个方法
	 * 
	 * 传入一个钱  交款人 类型  保存佣金
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月3日 下午2:35:10
	 * 
	 * customer 用户
	 * commissionMoney 计算佣金的钱数
	 * type  2 表示交易  1 表示提现
	 * 
	 */
	public List<AppAgentsCustromer> saveAppTradeFactorage(AppCustomer customer ,BigDecimal commissionMoney,int type){
		
		AppTradeFactorage factorage = null;
		
		List<AppAgentsCustromer> list2 = new ArrayList<AppAgentsCustromer>();
		
		List<AppAgentsCustromer> list = commissionDeployService.findByTransaction(customer.getUserName());
		logger.error("传入username="+customer.getUserName());
		
		if(list.size()>0){
			logger.error("有父节点会进来");
			AppTradeFactorage factorage1 = this.findFactorageByCustromerId(customer.getId());
			// 根据收的手续费 返回一个钱数  第二个参数为费率类型   第三个参数的费率类型为   几级父     
			if(null == factorage1){
				AppTradeFactorage appTradeFactorage = new AppTradeFactorage();
				appTradeFactorage.setCustromerId(customer.getId());
				appTradeFactorage.setCustromerName(customer.getUserName());
				appTradeFactorage.setOneParentMoney(BigDecimal.ZERO);
				appTradeFactorage.setTwoParentMoney(BigDecimal.ZERO);
				appTradeFactorage.setThreeParentMoney(BigDecimal.ZERO);
				appTradeFactorage.setFixPriceType(2);
				appTradeFactorage.setFixPriceCoinCode("CNY");
				super.save(appTradeFactorage);
				factorage = appTradeFactorage;
			}else{
				factorage = factorage1;
			}
			
			// 通过一个佣金类型 和几级代理商 返回一个佣金配置对象
			CommissionDeploy commissionDeploy = commissionDeployService.findCommissionDeploy(type);
			if(null != commissionDeploy){
			
				BigDecimal bigDecimalOneRate = commissionDeploy.getOneRankRatio().divide(new BigDecimal("100"));
				
				// 判断用户的手续费是否大于后台配置的佣金标准
				if(commissionDeploy.getOneStandardValue().compareTo(commissionMoney) <= 0){
					// 计算佣金
					BigDecimal oneMoney = commissionMoney.multiply(bigDecimalOneRate);
					factorage.setOneParentName(list.get(0).getCustomerName());
					BigDecimal oldOneMoney = factorage.getOneParentMoney();
					factorage.setOneParentMoney(oneMoney.add(oldOneMoney));
					// 保存费率
					factorage.setOneCommissionRate(bigDecimalOneRate);
					AppAgentsCustromer aa=new AppAgentsCustromer();
					aa.setCustomerName(list.get(0).getCustomerName());
					aa.setFrontBank("1");
					aa.setId(list.get(0).getId());
					list.set(0, aa);
					list2.add(list.get(0));
				}else{
					//list2.add(null);
				}
				// 判断用户的手续费是否大于后台配置的佣金标准
				if(list.size()>1 && commissionDeploy.getTwoStandardValue().compareTo(commissionMoney) <= 0){
					BigDecimal bigDecimalTwoRate = commissionDeploy.getTwoRankRatio().divide(new BigDecimal("100"));
					BigDecimal twoMoney = commissionMoney.multiply(bigDecimalTwoRate);
					factorage.setTwoParentName(list.get(1).getCustomerName());
					BigDecimal oldtwoMoney = factorage.getTwoParentMoney();
					factorage.setTwoParentMoney(twoMoney.add(oldtwoMoney));
					factorage.setTwoCommissionRate(bigDecimalTwoRate);
					AppAgentsCustromer aa=new AppAgentsCustromer();
					aa.setCustomerName(list.get(1).getCustomerName());
					aa.setId(list.get(1).getId());
					aa.setFrontBank("2");
					list.set(1, aa);
					list2.add(list.get(1));
				}else{
					//list2.add(null);
				}
				// 判断用户的手续费是否大于后台配置的佣金标准
				if(list.size()>2 && commissionDeploy.getThreeStandardValue().compareTo(commissionMoney) <= 0){
					BigDecimal bigDecimalTwoRate = commissionDeploy.getThreeRankRatio().divide(new BigDecimal("100"));
					BigDecimal threeMoney = bigDecimalTwoRate.multiply(commissionMoney);
					factorage.setThreeParentName(list.get(2).getCustomerName());
					BigDecimal oldtwoMoney = factorage.getThreeParentMoney();
					factorage.setThreeParentMoney(threeMoney.add(oldtwoMoney));
					factorage.setThreeCommissionRate(bigDecimalTwoRate);
					AppAgentsCustromer aa=new AppAgentsCustromer();
					aa.setCustomerName(list.get(2).getCustomerName());
					aa.setId(list.get(2).getId());
					aa.setFrontBank("3");
					list.set(2, aa);
					list2.add(list.get(2));
				}else{
					//list2.add(null);
				}
			}
			super.update(factorage);
		}else{
			logger.error("代理商为空");
		}
	return list2;
		
	}
	/**
	 * 据用户名返回自己的所有父的表
	 */
	@Override
	public AppTradeFactorage findFactorageByCustromerId(Long id){
		QueryFilter filter = new QueryFilter(AppTradeFactorage.class);
		filter.addFilter("custromerId=", id);
		AppTradeFactorage factorage = super.get(filter);
		return factorage;
	}
	
	
	
	
	/**
	 * 据用户名返回自己的所有父的表
	 */
	@Override
	public AppTradeFactorage findFactorageByCustromerId1(Long id,String fixPriceCoinCode){
		QueryFilter filter = new QueryFilter(AppTradeFactorage.class);
		filter.addFilter("custromerId=", id);
		filter.addFilter("fixPriceCoinCode=", fixPriceCoinCode);
		AppTradeFactorage factorage = super.get(filter);
		return factorage;
	}
}






