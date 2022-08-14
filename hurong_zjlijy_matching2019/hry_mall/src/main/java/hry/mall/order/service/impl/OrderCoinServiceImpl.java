/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-11-26 17:36:28 
 */
package hry.mall.order.service.impl;

import hry.mall.order.model.OrderCoin;
import hry.mall.order.service.OrderCoinService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> OrderCoinService </p>
 * @author:         luyue
 * @Date :          2019-11-26 17:36:28  
 */
@Service("orderCoinService")
public class OrderCoinServiceImpl extends BaseServiceImpl<OrderCoin, Long> implements OrderCoinService{
	
	@Resource(name="orderCoinDao")
	@Override
	public void setDao(BaseDao<OrderCoin, Long> dao) {
		super.dao = dao;
	}
	

}
