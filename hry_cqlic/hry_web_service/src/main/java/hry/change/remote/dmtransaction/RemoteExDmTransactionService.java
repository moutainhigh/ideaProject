/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月26日 上午10:29:10
 */
package hry.change.remote.dmtransaction;

import hry.util.RemoteQueryFilter;
import hry.exchange.transaction.model.ExDmTransaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wu shuiming
 * @date 2016年8月26日 上午10:29:10
 */
public interface RemoteExDmTransactionService {
	
	public String findLastTransactionNum();

	/**
	 * 返回用户的单日提现数量
	 * type 表示提现或  1充值  2 提现
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月31日 下午3:09:39
	 */
	public BigDecimal findTransactionNumForCustromer(Long custromerId, String coincode, String type);
    public  List<ExDmTransaction>  find(RemoteQueryFilter remoteQueryFilter);
    /**
     * 
     * <p> 保存交易数据</p>
     * @author:         Yuan Zhicheng
     * @param:    @param ts
     * @param:    @return
     * @return: String 
     * @Date :          2016年11月15日 下午9:49:43   
     * @throws:
     */
    public String saveTransaction(ExDmTransaction ts);
    
    /**
     * 
     * <p> 通过帐号 和 交易订单查询</p>
     * @author:         Yuan Zhicheng
     * @param:    @param account
     * @param:    @param txid
     * @param:    @return
     * @return: String 
     * @Date :          2016年11月15日 下午11:56:02   
     * @throws:
     */
    public ExDmTransaction getTransaction(String account, String txid);
}
