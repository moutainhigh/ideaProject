/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月16日 上午11:21:23
 */
package hry.account.fund.service.impl;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppAccountRecord;
import hry.account.fund.model.AppOurAccount;
import hry.account.fund.service.AppAccountRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.trade.entrust.model.ExOrderInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月16日 上午11:21:23 
 */       
@Service("appAccountRecordService")
public class AppAccountRecordServiceImpl extends BaseServiceImpl<AppAccountRecord, Long> implements
		AppAccountRecordService {
	private static Logger logger = Logger.getLogger(AppAccountRecordServiceImpl.class);
	@Override       
	@Resource(name="appAccountRecordDao")
	public void setDao(BaseDao<AppAccountRecord, Long> dao) {
		super.dao= dao;
	}
	
	
	/**
	 * 我方账号扣除一笔佣金明细   
	 * 
	 * type : 2 提现
	 * 
	 * ( 注 ：  此方法只适合扣除佣金明细  不适合其他交易的明细 )
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月5日 下午3:30:11
	 */
	@Override
	public void addOurRecord(BigDecimal money,AppOurAccount appOurAccount,AppAccount appAccount,int type,int states,String auditor){
		
		AppAccountRecord appAccountRecord = new AppAccountRecord();
		
		appAccountRecord.setAppAccountNum(appOurAccount.getAccountNumber());
		appAccountRecord.setAppAccountId(appAccount.getId());
		appAccountRecord.setCurrencyName(appAccount.getCurrencyType());
		appAccountRecord.setCustomerAccount(appAccount.getAccountNum());
		appAccountRecord.setCurrencyType(appAccount.getCurrencyType());
		appAccountRecord.setRecordType(type);
		appAccountRecord.setCustomerId(appAccount.getCustomerId());
		appAccountRecord.setCustomerName(appAccount.getUserName());
		appAccountRecord.setOperationTime(new Date());
		appAccountRecord.setRemark("给  "+appAccount.getAccountNum()+"转一笔佣金");		
		appAccountRecord.setSource(3);
		appAccountRecord.setStatus(states);
		appAccountRecord.setTransactionNum("000000000");
		appAccountRecord.setTransactionMoney(money);
		appAccountRecord.setWebsite(appAccount.getWebsite());
		// appAccountRecord.setFactorage(new BigDecimal().ZERO);
		appAccountRecord.setAuditor(auditor);
		super.save(appAccountRecord);
		
	}
	
	
//	Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	
	// int type,int source,int states,
	/**
	 * Map 里的值
	 * 			type :  1充值 2提现 3充值手续费 4提现手续费5表示卖方手续费6表示买方手续费
	 * 			source :  0 表示线下充值  1 表示线上充值 2表示 3表示交易手续费 4 表示充值手续费
	 * 			states :  0未审核 5已审核 10失败
	 *  
	 *  auditor : 操作人  
	 *  
	 *  remark : 描述 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月9日 上午9:26:03
	 */
	@Override
	public void addOurRecord(BigDecimal money,AppOurAccount appOurAccount,AppAccount appAccount,ExOrderInfo orderInfo,Map<String,Integer> map, String auditor,String remark){
		
		AppAccountRecord appAccountRecord = new AppAccountRecord();
		if(null!=appOurAccount){
			appAccountRecord.setAppAccountNum(appOurAccount.getAccountNumber());
			appAccountRecord.setAppAccountId(appOurAccount.getId());
			appAccountRecord.setCurrencyName(appAccount.getCurrencyType());
			appAccountRecord.setCustomerAccount(appAccount.getAccountNum());
			appAccountRecord.setCurrencyType(appAccount.getCurrencyType());
			
			// recordType  1充值 2提现 3充值手续费 4提现手续费5表示卖方手续费6表示买方手续费
			appAccountRecord.setRecordType(map.get("type"));
			// source  0 表示线下充值  1 表示线上充值 2表示 3表示交易手续费 4 表示充值手续费
			appAccountRecord.setSource(map.get("source"));
			
			appAccountRecord.setCustomerId(appAccount.getCustomerId());
			appAccountRecord.setCustomerName(appAccount.getUserName());
			appAccountRecord.setOperationTime(new Date());
			if(null != remark){
				appAccountRecord.setRemark(remark);	
			}
			appAccountRecord.setTransactionNum(orderInfo.getOrderNum());
		    appAccountRecord.setStatus(map.get("states"));
			appAccountRecord.setTransactionMoney(money);
			appAccountRecord.setWebsite(appAccount.getWebsite());
			appAccountRecord.setAuditor(auditor);
			super.save(appAccountRecord);
		}else{
			logger.error("我方账户为空");
		}
		
		
	}
	
	
	/**
	 * 
	 * 我方账户 增加或 减少币的时候 操作的流水 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午6:04:08
	 */
	@Override
	public void addRecordForBit(AppOurAccount ourAccount,String exDigNum,ExDmTransaction exDmTransaction,String auditor,String remark){
		
		if(null != ourAccount && null != exDmTransaction){
			AppAccountRecord appAccountRecord = new AppAccountRecord();
			appAccountRecord.setAppAccountNum(ourAccount.getAccountNumber());
			appAccountRecord.setAppAccountId(ourAccount.getId());
			appAccountRecord.setCustomerId(exDmTransaction.getCustomerId());
			appAccountRecord.setCustomerName(exDmTransaction.getCustomerName());
			Integer type = Integer.valueOf(ourAccount.getOpenAccountType());
			if(type == 0){
				appAccountRecord.setRecordType(1);
			}else if(type == 1){
				appAccountRecord.setRecordType(2);
			}else{
				appAccountRecord.setRecordType(type);
			}
			
			appAccountRecord.setSource(1);
			appAccountRecord.setTransactionMoney(exDmTransaction.getTransactionMoney());
			appAccountRecord.setTransactionNum(exDmTransaction.getTransactionNum());
			appAccountRecord.setStatus(5);
			appAccountRecord.setRemark(remark);
			appAccountRecord.setCurrencyName(exDmTransaction.getCoinCode());
			appAccountRecord.setCurrencyType(null);
			appAccountRecord.setAuditor(auditor);
			appAccountRecord.setOperationTime(new Date());
			appAccountRecord.setCustomerAccount(exDigNum);
			appAccountRecord.setWebsite(ourAccount.getWebsite());
			
			super.save(appAccountRecord);
			
		}
	}

	
	
	


}
