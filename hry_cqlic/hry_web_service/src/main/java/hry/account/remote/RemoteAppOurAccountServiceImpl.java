/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月17日 上午10:27:42
 */
package hry.account.remote;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppAccountRecord;
import hry.account.fund.model.AppOurAccount;
import hry.account.fund.service.AppAccountRecordService;
import hry.account.fund.service.AppOurAccountService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月17日 上午10:27:42 
 */
public class RemoteAppOurAccountServiceImpl implements
		RemoteAppOurAccountService {
	private static Logger logger = Logger.getLogger(RemoteAppOurAccountServiceImpl.class);
	@Resource(name="appOurAccountService")
	public AppOurAccountService appOurAccountService;
	
	@Resource(name="appAccountRecordService")
	public AppAccountRecordService appAccountRecordService;
	
	@Resource(name="appPersonInfoService")
	public AppPersonInfoService appPersonInfoService;
	
	/**
	 * acounttype   0 表示银行卡   1表示支付宝
	 *       
	 */
	@Override
	public AppOurAccount getOurAccount(String website,String currencyType,String accountType) {
			QueryFilter filter = new QueryFilter(AppAccountRecord.class);
			filter.addFilter("website=", website);
			filter.addFilter("currencyType=", currencyType);
			filter.addFilter("isShow=", "1");
			filter.addFilter("accountType=",accountType);
			AppOurAccount ourAccount = appOurAccountService.get(filter);
			return ourAccount;
	}
	
	
	/**
	 *  Map 里的值
	 * 			type :  1充值 2提现 3充值手续费 4提现手续费5表示卖方手续费6表示买方手续费
	 * 			source :  0 表示线下充值  1 表示线上充值 2表示 3表示交易手续费 4 表示充值手续费
	 * 			states :  0未审核 5已审核 10失败
	 *  
	 *  auditor : 操作人  
	 *  remark : 描述 
	 * 给我方账号增加一笔钱  并保存流水 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月9日 上午11:54:40
	 */
	@Override
	public void addMoneyForOurAccount(ExOrderInfo orderInfo){
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService)ContextUtil.getBean("remoteAppAccountService");
		AppOurAccount appOurAccount = appOurAccountService.getAppOurAccount(orderInfo.getWebsite(), orderInfo.getCurrencyType());                   
		
		//获取买卖双方的personinfo对象
		AppPersonInfo buyPerson=appPersonInfoService.getByCustomerId(orderInfo.getBuyCustomId());

	/*	//给推荐人派发推荐奖励
		//先判断买方又没有推荐人,没有推荐人就不需要返还推荐奖励,有直接返回推荐人appperson对象
		AppPersonInfo buyAgentPerson=isHaveRecommendAgent(orderInfo.getBuyCustomId());
		if(buyAgentPerson!=null){//存在推荐关系
			//返还推荐奖励
			payRecommendToAgent(appPersonInfoService,orderInfo,buyPerson,buyAgentPerson);
		}
		*/
		
		//卖币手续费大于0
		if(orderInfo.getTransactionSellFee().compareTo(BigDecimal.ZERO)>0){
			//	 type   :  1充值 2提现 3充值手续费 4提现手续费5表示卖方手续费6表示买方手续费
			//	 source :  0 表示线下充值  1 表示线上充值 2表示提现手续费 3表示交易手续费 4 表示充值手续费
			//	 states :  0未审核 5已审核 10失败
			Map<String,Integer> map = new HashMap<String, Integer>();
			map.put("type", 5);
			map.put("source", 3);
			map.put("states", 5);
			
			//获取卖方的人民币账户
			AppAccount appAccount2 = remoteAppAccountService.getByCustomerIdAndType(orderInfo.getSellCustomId(), orderInfo.getCurrencyType(), orderInfo.getWebsite());
			
			//交易手续费
			//没有推荐人  都给平台
			//卖方推荐人
			AppPersonInfo sellAgentPerson=isHaveRecommendAgent(orderInfo.getSellCustomId());
			if(sellAgentPerson==null){
				logger.error("卖方无推荐人！！！手续费都由平台收取！！！");
				//给我方账户增加一笔钱(平台收取手续费)
				appOurAccountService.setMoneyToAgent(orderInfo.getTransactionSellFee(), orderInfo.getWebsite(), orderInfo.getCurrencyType());
				//增加一条手续费收取记录
				appAccountRecordService.addOurRecord(orderInfo.getTransactionSellFee(), appOurAccount, appAccount2, orderInfo, map, "RabbitMq", "收取卖方的手续费");
			}
		}
		//买方交易手续费
		if(orderInfo.getTransactionBuyFee().compareTo(BigDecimal.ZERO)>0){
				Map<String,Integer> map = new HashMap<String, Integer>();
		
				map.put("type", 6);
				map.put("source", 3);
				map.put("states", 5);
				
				Long id = orderInfo.getBuyCustomId();
				
				AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(id, orderInfo.getCurrencyType(), orderInfo.getWebsite());
				
				appOurAccountService.setMoneyToAgent(orderInfo.getTransactionBuyFee(), orderInfo.getWebsite(), orderInfo.getCurrencyType());
				
				appAccountRecordService.addOurRecord(orderInfo.getTransactionBuyFee(), appOurAccount, appAccount, orderInfo, map, "RabbitMq", "收取买方的手续费");
			
		}
		
	}


	
	/**
	 * 获取一个用户的总买币额，查询成交记录exorderinfo表
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param buyCustomId
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年3月9日 上午10:21:52   
	 * @throws:
	 */
	private BigDecimal getTotalBuyMoney(Long buyCustomId) {
		ExOrderInfoService exOrderInfoService  = (ExOrderInfoService) ContextUtil.getBean("exOrderInfoService");
		return exOrderInfoService.getTotalBuyMoney(buyCustomId);
	}


	/**
	 * 判断一个用户是否有推荐人
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerId
	 * @param:    @return
	 * @return: CustomerAsAgents 
	 * @Date :          2017年3月8日 下午2:44:33   
	 * @throws:
	 */
	private AppPersonInfo isHaveRecommendAgent(Long customerId){
		//查询appcustomer表吧，referralCode推荐人手机号不为空
		AppCustomerService appCustomerService = (AppCustomerService)ContextUtil.getBean("appCustomerService");
		AppPersonInfoService appPersonInfoService = (AppPersonInfoService)ContextUtil.getBean("appPersonInfoService");
		AppCustomer customer=appCustomerService.get(customerId);
		if(customer.getReferralCode()!=null){
			//有推荐人，返回推荐人对象
			QueryFilter qf=new QueryFilter(AppPersonInfo.class);
			qf.addFilter("mobilePhone=", customer.getReferralCode());
			AppPersonInfo appPersonInfo=appPersonInfoService.get(qf);
			return appPersonInfo;
		}else{
			return null;
		}
	}

	/**
	 * 
	 * 给我方账户 增加或 减少一笔钱 
	 * 
	 * ourAccount   我方 对应 的币种账号(区分 充值提现 )
	 * ExDmTransaction  充值提现单
	 * digAccountNum  用户 账号 
	 * remark 备注 
	 * auditor  操作 人 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午6:09:51
	 */
	@Override
	public void changeCountToOurAccoun(AppOurAccount ourAccount,ExDmTransaction dmTransaction,String digAccountNum,String remark,String auditor){
		
		if(null != ourAccount && null != dmTransaction){
			String types = ourAccount.getOpenAccountType();
			Integer type = Integer.valueOf(types);
			Boolean b=false;
			if("fee".equals(auditor)){//手续费
			    b = appOurAccountService.changeBitForOurAccount(dmTransaction.getFee(), ourAccount);
			}
			if(type==0){//充币
				b = appOurAccountService.changeBitForOurAccount(dmTransaction.getTransactionMoney().subtract(dmTransaction.getFee()), ourAccount);
			}
			if(type==1){//提币
				b = appOurAccountService.changeBitForOurAccount(dmTransaction.getTransactionMoney(), ourAccount);
			}
			if(b){
				appAccountRecordService.addRecordForBit(ourAccount, digAccountNum, dmTransaction, auditor, remark);
			}
		}
	
	}

	/**
	 * 
	 * type  0 充币   1 提币
	 * currencyType  BTC  LTC  
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * 
	 * 
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午7:24:42
	 */
	@Override
	public AppOurAccount findAppOurAccount(String website, String currencyType, Integer type) {
		return appOurAccountService.getAppOurAccount(website, currencyType, type);
	}


	
	
	@Override
	public List<AppOurAccount> getOurAccounts(String website, String currencyType) {
		QueryFilter filter = new QueryFilter(AppAccountRecord.class);
		filter.addFilter("website=", website);
		filter.addFilter("isShow=", "1");
		filter.addFilter("currencyType=", currencyType);
		List<AppOurAccount> ourAccount = appOurAccountService.find(filter);
		
		return ourAccount;
	}


	
	@Override
	public boolean updateAccountBalance(AppOurAccount appOurAccount,
			AppAccountRecord appAccountRecord) {
		
		boolean b=appOurAccountService.changeBitForOurAccount(appAccountRecord.getTransactionMoney(), appOurAccount);
		if (b) {
			appAccountRecordService.save(appAccountRecord);
			return true;
		}
		return false;
	}

	
	@Override
	public boolean updateWithdrawAccountBalance(AppOurAccount appOurAccount,
			AppAccountRecord appAccountRecord) {
		
		boolean b=appOurAccountService.changeBitForOurWithdrawAccount(appAccountRecord.getTransactionMoney(), appOurAccount);
		if (b) {
			appAccountRecordService.save(appAccountRecord);
			return true;
		}
		return false;
	}


	
	@Override
	public List<AppAccountRecord> getAccountRecord(String txid) {
		QueryFilter filter=new  QueryFilter(AppAccountRecord.class);
		filter.addFilter("remark=", txid);
	    List<AppAccountRecord>	l=appAccountRecordService.find(filter);
		return l;
	}


	/**
	 * 从我方账户里减少一笔钱到用户账户
	 * 
	 * @param transactionMoney
	 * @param appOurAccount
	 */
	@Override
	public void changeBitForOurAccount(BigDecimal transactionMoney,
			AppOurAccount appOurAccount) {
		appOurAccountService.changeBitForOurAccount(transactionMoney, appOurAccount);
	}


	@Override
	public AppOurAccount findOurCoinAccountInfo(String coinCode,String accountType,
			String openAccountType, String isShow,String website) {
		QueryFilter filter=new  QueryFilter(AppAccountRecord.class);
		filter.addFilter("accountType=", accountType);
		filter.addFilter("currencyType=", coinCode);
		filter.addFilter("openAccountType=",openAccountType);
		filter.addFilter("website=",website);
		filter.addFilter("isShow=", isShow);
		return	appOurAccountService.get(filter);
	}


	/**
	 * 用户注册实名后给用户奖励一个币
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerId
	 * @return: void 
	 * @Date :          2017年3月9日 下午3:10:25   
	 * @throws:
	 */
	@Override
	public void sendAuthRewardToCustomer(Long customerId,String coinCode) {
		appOurAccountService.sendAuthRewardToCustomer(customerId,coinCode);
	}

	

	/**
	 * 金科比地址生成成功后，再判断一下实名返币是否成功，没成功继续返
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    
	 * @return: void 
	 * @Date :          2017年3月23日 下午7:39:13   
	 * @throws:
	 */
	@Override
	public void judgeAuthRewardState(String userName,Long exdmaccountId) {
		appOurAccountService.judgeAuthRewardState(userName,exdmaccountId);
	}
	

	

}
