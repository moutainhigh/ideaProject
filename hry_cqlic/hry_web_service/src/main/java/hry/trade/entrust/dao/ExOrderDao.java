/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2015年11月06日  14:57:13
 */
package hry.trade.entrust.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.trade.entrust.model.ExOrder;
import hry.util.klinedata.TransactionOrder;

import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExOrderDao extends BaseDao<ExOrder, Long> {

	/**
	 * 
	 * <p>查询两个时间段之前的k线数据</p>
	 * @author:         Liu Shilei
	 * @param:    @param minDate
	 * @param:    @param maxDate
	 * @param:    @return
	 * @return: List<ExOrder> 
	 * @Date :          2016年4月20日 上午10:47:58   
	 * @throws:
	 */
	List<TransactionOrder> findOrder(Map<String, Object> map);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param minDate
	 * @param:    @param maxDate
	 * @param:    @return
	 * @return: List<TransactionOrder> 
	 * @Date :          2016年4月27日 下午7:28:56   
	 * @throws:
	 */
	List<TransactionOrder> getTotalData(Map<String, Object> map);
	/**
	 * 
	 * <p>得到最后一条成交的记录</p>
	 * @author:         Gao Mimi
	 * @param:    
	 * @return: void 
	 * @Date :          2016年4月26日 下午6:48:58   
	 * @throws:
	 */
	public List<ExOrder> getCurrentExchangPrice(Map<String, Object> map);
	/**
	 * 
	 * <p> 得到凌晨之后的第一天记录</p>
	 * @author:         Gao Mimi
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: List<ExOrder> 
	 * @Date :          2016年5月3日 上午11:30:56   
	 * @throws:
	 */
	public List<ExOrder> getOpenExchangPrice(Map<String, Object> map);
	/**
	 * 
	 * <p> 得到倒数第二条成交记录</p>
	 * @author:         Gao Mimi
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: List<ExOrder> 
	 * @Date :          2016年5月3日 上午11:31:02   
	 * @throws:
	 */
	public List<ExOrder> getLastExchangPrice(Map<String, Object> map);
	public List<ExOrder> findNewList(Map<String, Object> map);
	public List<ExOrder> findNewListDesc(Map<String, Object> map);
	public List<ExOrder> findNewListnewAdd(Map<String, Object> map);
	public List<ExOrder> exAveragePrice(String coinCode) ;
}
