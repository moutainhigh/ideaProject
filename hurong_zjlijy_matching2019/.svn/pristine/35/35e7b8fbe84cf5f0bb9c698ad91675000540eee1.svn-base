/**
 * Copyright:    
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:31:38 
 */
package hry.mall.order.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.mall.order.model.Order;
import hry.mall.order.model.vo.OrderBalanceVo;
import hry.mall.order.model.vo.OrderRefundVo;
import java.util.List;
import java.util.Map;


/**
 * <p> OrderDao </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:31:38  
 */
public interface OrderDao extends BaseDao<Order, Long> {

    List<Order> findPageBySql(Map<String, Object> map);
    List<Order> findByMemberIdAndStatus(Map<String, String> map);
	public List<OrderRefundVo> findUnionBySql(Map<String, String> map);

    /**
     * 商户结算订单查询
     * @param map
     * @return
     */
	List<OrderBalanceVo> findBalanceBySql(Map<String, String> map);
}
