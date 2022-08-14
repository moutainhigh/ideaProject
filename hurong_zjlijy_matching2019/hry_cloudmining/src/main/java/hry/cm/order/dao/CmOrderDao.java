/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-30 10:08:52 
 */
package hry.cm.order.dao;

import java.util.List;

import hry.cm.order.model.CmOrder;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> CmOrderDao </p>
 * @author:         yaozh
 * @Date :          2019-07-30 10:08:52  
 */
public interface CmOrderDao extends  BaseDao<CmOrder, Long> {
	
	/**
	 * 查询持有到期的矿机的用户
	 * @return
	 */
	public List<Long> findCloseMinerCustomerId();
	
	/**
	 * 查询持有待运行状态矿机的用户
	 * @return
	 */
	public List<Long> findWaitMinerCustomerId();
	/**
	 * 更新到期订单状态
	 */
	void updateCloseOrder();
	/**
	 * 更新待运行订单状态
	 */
	void updateWaitOrder();

}
