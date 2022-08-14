/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2015年11月06日  14:57:13
 */
package hry.trade.entrust.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.trade.entrust.model.ExOrder;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExOrderDao extends BaseDao<ExOrder, Long> {

	/**
	 * 
	 * <p>得到最后一条成交的记录</p>
	 * @author:         Gao Mimi
	 * @param:    
	 * @return: void 
	 * @Date :          2016年4月26日 下午6:48:58   
	 * @throws:
	 */
	public List<ExOrder> getCurrentExchangPrice(Map<String,Object> map);
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
	public List<ExOrder> getOpenExchangPrice(Map<String,Object> map);
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
	public List<ExOrder> getLastExchangPrice(Map<String,Object> map);
	public List<ExOrder> findNewList(Map<String,Object> map);
	public List<ExOrder> findNewListDesc(Map<String,Object> map);
	public List<ExOrder> findNewListnewAdd(Map<String,Object> map);
	public List<ExOrder> exAveragePrice(String coinCode) ;
}
