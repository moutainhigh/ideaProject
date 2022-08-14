/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月1日 上午11:17:16
 */
package hry.account.remote;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppTransaction;
import hry.bean.PageResult;
import hry.util.RemoteQueryFilter;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Liu Shilei
 * @Date : 2016年4月1日 上午11:17:16
 */
public interface RemoteAppTransactionService {

	/**
	 * 根据customerId,transactionType查询交易记录表
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             customerId
	 * @param: @param
	 *             transactionType
	 * @param: @return
	 * @return: List<AppTransaction>
	 * @Date : 2016年4月5日 下午3:18:59
	 * @throws:
	 */
	public List<AppTransaction> findByCustomerIdAndType(Long customerId, Integer transactionType);

	/**
	 * <p>
	 * 保存订单
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             appTransaction
	 * @return: void
	 * @Date : 2016年4月5日 下午4:06:10
	 * @throws:
	 */
	public void save(AppTransaction appTransaction);

	/**
	 * <p>
	 * 保存订单
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             appTransaction
	 * @return: void
	 * @Date : 2016年4月5日 下午4:06:10
	 * @throws:
	 */
	public void update(AppTransaction appTransaction);

	/**
	 * 生成提现订单
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             appTransaction
	 * @return: void
	 * @Date : 2016年10月10日 下午2:44:34
	 * @throws:
	 */
	public void rmbwithdraw(AppAccount appAccount, AppTransaction appTransaction);

	/**
	 * <p>
	 * 分页查询
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             filter
	 * @return: void
	 * @Date : 2016年5月9日 下午3:39:41
	 * @throws:
	 */
	public PageResult findByQueryFilter(RemoteQueryFilter remoteQueryFilter);

	/**
	 * 
	 * 订单充值成功调用的方法
	 * 
	 * @param accountNum
	 *            订单流水号
	 * @param UserName
	 *            操作人名
	 * @param states
	 *            0 表示失败 1 表示成功
	 * @return
	 */
	public Boolean passOrder(String accountNum, String userName, Integer states);

	/**
	 * 通过订单号查询订单
	 * 
	 * @author: Wu Shuiming
	 * @version: V1.0
	 * @date: 2016年7月18日 下午3:31:36
	 */
	public AppTransaction createTranctonByOrderNum(String num);

	/**
	 * 
	 * 订单id 第二个参数为处理人 第三个状态为 成功或失败
	 * 
	 * @author: Wu Shuiming
	 * @version: V1.0
	 * @date: 2016年7月19日 下午3:52:32
	 */
	public Boolean withdraw(Long id, String name, Integer i);

	/**
	 * 
	 * <p>
	 * 根据用户id 交易类型 获取交易记录
	 * </p>
	 * 
	 * @author: Zhang Xiaofang
	 * @param: @return
	 * @return: List<AppTransaction>
	 * @Date : 2016年8月11日 上午11:16:04
	 * @throws:
	 */
	public List<AppTransaction> record(Long id, String type, String status, String beginDate, String endDate,
                                       String currencyType, String website);

	/**
	 * 
	 * 查询充值 提现金额
	 * 
	 * @author: Zhang Xiaofang
	 * @param: @param
	 *             type
	 * @param: @param
	 *             beginDate
	 * @param: @param
	 *             endDate
	 * @param: @param
	 *             status
	 * @param: @param
	 *             userName
	 * @param: @return
	 * @return: BigDecimal
	 * @Date : 2016年9月1日 下午12:51:35
	 * @throws:
	 */
	BigDecimal countByDate(String[] type, String beginDate, String endDate, String[] status, String userName);

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             billNo
	 * @return: void
	 * @Date : 2016年10月13日 下午7:05:21
	 * @throws:
	 */
	public AppTransaction getByTransactionNum(String transactionNum);

	/**
	 * 通过id获取对象
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param valueOf
	 * @param:    @return
	 * @return: AppTransaction 
	 * @Date :          2017年4月18日 上午11:15:14   
	 * @throws:
	 */
	public AppTransaction get(Long valueOf);

}
