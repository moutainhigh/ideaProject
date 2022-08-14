/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月1日 上午11:18:14
 */
package hry.account.remote;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppTransaction;
import hry.account.fund.service.AppAccountService;
import hry.account.fund.service.AppTransactionService;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.rpc.RpcContext;
import com.github.pagehelper.Page;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年4月1日 上午11:18:14 
 */
public class RemoteAppTransactionServiceImpl implements  RemoteAppTransactionService {
	
	
	@Resource
	private AppTransactionService appTransactionService;
	
	@Resource
	private AppAccountService appAccountService;
	
	@Override
	public List<AppTransaction> findByCustomerIdAndType(Long customerId,Integer transactionType) {
		String saasId = RpcContext.getContext().getAttachment("saasId");
		QueryFilter filter = new QueryFilter(AppTransaction.class);
		filter.setSaasId(saasId);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("transactionType=", transactionType);
		return appTransactionService.find(filter);
	}

	@Override
	public void save(AppTransaction appTransaction) {
		appTransactionService.save(appTransaction);
	
	}

	@Override
	public PageResult findByQueryFilter(RemoteQueryFilter remoteQueryFilter) {
		QueryFilter filter = remoteQueryFilter.getQueryFilter();
		filter.setOrderby("created desc");
		return appTransactionService.findPageResult(filter);
		
	}
	
	@Override
	public Boolean passOrder(String accountNum,String userName ,Integer states){
		QueryFilter filter = new QueryFilter(AppTransaction.class);
		filter.addFilter("transactionNum=",accountNum);
		filter.addFilter("status=",4);//审核中
		AppTransaction transaction = appTransactionService.get(filter);
		if(null != transaction){
			Long id = transaction.getId();
			
			if( 1 == states){
				boolean b = appTransactionService.confirm(id, userName);
				return b;
			}if(0 == states){
				transaction.setStatus(3);
				appTransactionService.update(transaction);
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据订单号查询订单
	 * 
	 */
	@Override
	public AppTransaction createTranctonByOrderNum(String num) {
		QueryFilter filter = new QueryFilter(AppTransaction.class);
		filter.addFilter("transactionNum=",num);
		filter.addFilter("status=", 2);
		AppTransaction transaction = appTransactionService.get(filter);
		
		return transaction;
	}
	
	// 通过一个体现订单处理方法    id 为订单id   name 为处理人可以填 null 或者指定字符串     i为状态 0 表示失败 1 表示成功
	@Override
	public Boolean withdraw(Long id,String name,Integer i){
		QueryFilter filter = new QueryFilter(AppTransaction.class);
		filter.addFilter("transactionNum=",id);
		filter.addFilter("status=",4);
		AppTransaction transaction = appTransactionService.get(filter);
		if(null != transaction){
			Long tranId = transaction.getId();
		if(1==i){
			boolean b = appTransactionService.confirmwithdraw(tranId, name);
			return b;
		}else{
			return false;
		}
		
		}
		return false;
	}

	
	@Override
	public List<AppTransaction> record(Long id, String type,String status,String beginDate,String endDate,String currencyType,String website) {
		
		QueryFilter filter = new QueryFilter(AppTransaction.class);
		filter.addFilter("customerId=",id);
		
		if(null!=website){
			filter.addFilter("website=",website);
		}
		if(null!=currencyType){
			filter.addFilter("currencyType=",currencyType);
		}
		if(null!=status){
			filter.addFilter("status_in", status);
		}else{
			filter.addFilter("status_in", "2");  //默认查成功状态
		}
		if(null!=type){
			filter.addFilter("transactionType_in",type);
		}
		
		if(null!=beginDate){
			filter.addFilter("modified>",beginDate);
		}
		if(null!=endDate){
			filter.addFilter("modified<",endDate);
		}
		
		
		List<AppTransaction> list = appTransactionService.find(filter);
		return list;
	}

	
	
	@Override
	public BigDecimal countByDate(String[] type, String beginDate,
			String endDate, String[] status, String userName) {
		BigDecimal money=new BigDecimal(0);
		money=appTransactionService.countByDate(type, beginDate, endDate, status, userName);
		return money;
	}

	@Override
	public void rmbwithdraw(AppAccount appAccount,AppTransaction appTransaction) {
		appTransactionService.save(appTransaction);
        appAccountService.freezeAccountSelf(appAccount.getId(), appTransaction.getTransactionMoney().subtract(appTransaction.getFee()), appTransaction.getTransactionNum(), "人民币提现",null,null);
        appAccountService.freezeAccountSelf(appAccount.getId(), appTransaction.getFee(), appTransaction.getTransactionNum()+"-fee", "人民币提现手续费",null,null);
	
	}

	@Override
	public AppTransaction getByTransactionNum(String transactionNum) {
		QueryFilter filter = new QueryFilter(AppTransaction.class);
		filter.addFilter("transactionNum=", transactionNum);
		return appTransactionService.get(filter);
	}

	/* (non-Javadoc)
	 * @see hry.account.remote.RemoteAppTransactionService#update(hry.account.fund.model.AppTransaction)
	 */
	@Override
	public void update(AppTransaction appTransaction) {
		appTransactionService.update(appTransaction);
		
	}

	@Override
	public AppTransaction get(Long valueOf) {
		// TODO Auto-generated method stub
		return appTransactionService.get(valueOf);
	}
	

}
