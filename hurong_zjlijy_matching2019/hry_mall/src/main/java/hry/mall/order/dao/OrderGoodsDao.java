/**
 * Copyright:    
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:35:23 
 */
package hry.mall.order.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.mall.order.model.OrderGoods;
import java.util.List;
import java.util.Map;

/**
 * <p> OrderGoodsDao </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:35:23  
 */
public interface OrderGoodsDao extends BaseDao<OrderGoods, Long> {
	
	 List<OrderGoods> findBySql(Map<String, String> map);

}
