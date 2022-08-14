/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:35:23 
 */
package hry.mall.order.service;

import hry.core.mvc.service.base.BaseService;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.GoodsSpec;
import hry.mall.order.model.Order;
import hry.mall.order.model.OrderGoods;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;



/**
 * <p> OrderGoodsService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:35:23 
 */
public interface OrderGoodsService  extends BaseService<OrderGoods, Long> {

	public OrderGoods saveOrderGoods(Goods goods, GoodsSpec spec, Order order, Integer count, BigDecimal price);
	
	public String specInfo(String specName, String specNameValue);
	
    /**
     * sql查询
     * @param map
     * @return
     */
    List<OrderGoods> findBySql(Map<String, String> map);
    
    /**
     * 判断订单是否有店主升级产品，如有，则返成长值
     */
    public Boolean isPromote(Long orderId, Long memberId);
}
