/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:03:56 
 */
package hry.cm4.order.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm4.order.model.Cm4Order;

import java.util.List;


/**
 * <p> Cm4OrderDao </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:03:56  
 */
public interface Cm4OrderDao extends  BaseDao<Cm4Order, Long> {
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
