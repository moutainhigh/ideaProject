/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月26日 上午10:31:36
 */
package hry.exchange.remote.dmtransaction;

import hry.change.remote.dmtransaction.RemoteExDmTransactionService;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author Wu shuiming
 * @date 2016年8月26日 上午10:31:36
 */
public class RemoteExDmTransactionServiceImpl implements
		RemoteExDmTransactionService {
	  
	@Resource(name="exDmTransactionService")
	public ExDmTransactionService exDmTransactionService;
	
	
	@Override
	public String findLastTransactionNum() {
		ExDmTransaction lastTrasaction = exDmTransactionService.findLastTrasaction();
		if(null != lastTrasaction){
			String transactionNum = lastTrasaction.getTransactionNum();
			return transactionNum;
		}
		return null;
	}
	
	/**
	 * 
	 * 返回用户的单日提现的数量
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月31日 下午3:07:36
	 */
	@Override
	public BigDecimal findTransactionNumForCustromer(Long custromerId,String coinCode,String type){
		
		BigDecimal totalNum = exDmTransactionService.findTransactionByCustomer(custromerId, coinCode, type);
		
		return totalNum;
	}

	@Override
	public List<ExDmTransaction> find(RemoteQueryFilter remoteQueryFilter) {
		// TODO Auto-generated method stub
		return exDmTransactionService.find(remoteQueryFilter.getQueryFilter());
	}

	/* (non-Javadoc)
	 * @see hry.change.remote.dmtransaction.RemoteExDmTransactionService#saveTransaction(hry.exchange.transaction.model.ExDmTransaction)
	 */
	@Override
	public String saveTransaction(ExDmTransaction ts) {
		StringBuffer ret=new StringBuffer("{\"success\":\"true\",\"msg\":");
		try {
		    exDmTransactionService.save(ts);
			ret.append("\"保存成功 \" ");
		} catch (Exception e) {
			ret.append("\"异常\"："+e.getMessage()+"\"");
		}finally{
			ret.append("}");
		}
		
		return ret.toString();
	}

	/* (non-Javadoc)
	 * @see hry.change.remote.dmtransaction.RemoteExDmTransactionService#getTransaction(java.lang.String, java.lang.String)
	 */
	@Override
	public ExDmTransaction getTransaction(String account, String txid) {
		QueryFilter fil = new QueryFilter(ExDmTransaction.class);
		fil.addFilter("orderNo=", txid);
		fil.addFilter("customerName=", account);
		ExDmTransaction tran = exDmTransactionService.get(fil);
		return tran;
	}

}
