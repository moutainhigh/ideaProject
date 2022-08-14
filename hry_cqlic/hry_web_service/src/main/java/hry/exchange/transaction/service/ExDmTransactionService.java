/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:57:19
 */
package hry.exchange.transaction.service;



import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;
import hry.util.QueryFilter;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:57:19
 */
public interface ExDmTransactionService extends
		BaseService<ExDmTransaction, Long> {
	
	PageResult	findPageBySql(QueryFilter filter);
	
	
	public ExDmTransaction findLastTrasaction();
	
	/**
	 * 使用用户名以及币的code 查询用户当天提现或充值的总数 
	 * 
	 * type  1表示充值  2 表示提现
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月31日 下午7:00:28
	 */
	public BigDecimal findTransactionByCustomer(Long customerid, String coinCode, String type);
	
	
	/**
	 * 
	 * 查询钱包记录并保存流水以及更新币种账户余额
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: Map<String,String> 
	 * @Date :          2016年9月5日 下午3:56:57   
	 * @throws:
	 */
	public Map<String ,String>  record();
	
	/**
	 * 
	 * 把提币订单为审核状态的记录重新调用钱包查询接口提币结果。 
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: Map<String,String> 
	 * @Date :          2016年9月5日 下午4:37:23   
	 * @throws:
	 */
	public Map<String ,String>  updateStatus();
	
	
	
	 /**
     * 从钱包转币到我方充币账户
     * <p> TODO</p>
     * @author:         Zhang Xiaofang
     * @param:    @return
     * @return: String 
     * @Date :          2016年9月6日 上午11:11:57   
     * @throws:
     */
	public JsonResult  sendToOurRecharge();
	
	
	
	
	
	/**
	 * 
	 * 查询钱包记录并保存流水以及更新币种账户余额
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: Map<String,String> 
	 * @Date :          2016年9月5日 下午3:56:57   
	 * @throws:
	 */
	public Map<String ,String>  recordAll();
	
	
	
	
	/**
	 * 
	 *  查询钱包记录(提币记录)并保存流水以及更新币种账户余额
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: Map<String,String> 
	 * @Date :          2016年9月18日 下午3:21:43   
	 * @throws:
	 */
	public Map<String ,String>  recordAllWithdraw();
	
	/**
	 * 撤销成功记录
	 * 
	 * @param id
	 * @return
	 */
	public JsonResult cancelTransaction(Long id);
	
	public FrontPage findExdmtransaction(Map<String, String> params);

    void lockManagementHandler (Map<String, Object> paraMap);

    void sendOurCustomer(ExDmTransaction t, ExDigitalmoneyAccount exDigitalmoneyAccount);

	FrontPage findFrontPage(Map<String, String> params);

	RemoteResult checkPass(Map<String, String> params);

	RemoteResult checkReject(Map<String, String> params);


	/**
	 * 通知前台提币成功还是失败
	 * @param customer
	 * @param flag "0":失败  "1":成功
	 */
	void sendFrontMessage(AppCustomer customer, Boolean flag);

	FrontPage findExdmtransactionRecord (Map<String, String> params);
}
