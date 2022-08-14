/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-12-07 14:06:38 
 */
package hry.customer.businessman.service;

import java.util.Map;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.businessman.model.C2cTransaction;
import hry.manage.remote.model.base.FrontPage;
import hry.manage.remote.model.c2c.C2cOrder;



/**
 * <p> C2cTransactionService </p>
 * @author:         liushilei
 * @Date :          2017-12-07 14:06:38  
 */
public interface C2cTransactionService  extends BaseService<C2cTransaction, Long>{
	
	
	/**
	 * 生成与商户匹配的订单
	 * @param c2cOrder
	 */
	JsonResult createC2cOrder(C2cOrder c2cOrder);
	
	/**
	 * 获得订单汇款信息
	 * @param transactionNum
	 * @return
	 */
	String getC2cOrderDetail(String transactionNum);
	
	/**
	 * 确认订单
	 * @param valueOf
	 * @return
	 */
	JsonResult confirm(Long valueOf);
	
	/**
	 * 关闭订单
	 * @param valueOf
	 * @return
	 */
	JsonResult close(Long valueOf);
	
	/**
	 * 个人中心查询c2c订单
	 * @param params
	 * @return
	 */
	FrontPage c2clist(Map<String, String> params);
	
	/**
	 * 订单超时
	 * @param valueOf
	 * @return
	 */
	void timeout();
	
	/**
	 * sql分页
	 * @param filter
	 * @return
	 */
	PageResult findPageBySql(QueryFilter filter);


}
