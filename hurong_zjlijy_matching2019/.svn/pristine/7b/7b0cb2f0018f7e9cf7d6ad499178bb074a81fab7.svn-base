/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 17:20:52 
 */
package hry.cm2.order.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.order.model.Cm2Order;

import java.util.List;


/**
 * <p> Cm2OrderDao </p>
 * @author:         yaozh
 * @Date :          2019-10-14 17:20:52  
 */
public interface Cm2OrderDao extends  BaseDao<Cm2Order, Long> {

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
