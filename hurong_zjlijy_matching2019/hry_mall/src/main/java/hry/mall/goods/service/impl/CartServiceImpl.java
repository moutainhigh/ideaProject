/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-12-20 10:54:36 
 */
package hry.mall.goods.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.Cart;
import hry.mall.goods.service.CartService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * <p> CartService </p>
 * @author:         zhouming
 * @Date :          2018-12-20 10:54:36  
 */
@Service("cartService")
public class CartServiceImpl extends BaseServiceImpl<Cart, Long> implements CartService {
	
	@Resource(name="cartDao")
	@Override
	public void setDao(BaseDao<Cart, Long> dao) {
		super.dao = dao;
	}

}
