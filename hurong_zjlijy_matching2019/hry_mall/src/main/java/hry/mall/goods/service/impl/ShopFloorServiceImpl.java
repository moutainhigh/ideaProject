/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-12-14 11:32:11 
 */
package hry.mall.goods.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.ShopFloor;
import hry.mall.goods.service.ShopFloorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p> ShopFloorService </p>
 * @author:         zhouming
 * @Date :          2018-12-14 11:32:11  
 */
@Service("shopFloorService")
public class ShopFloorServiceImpl extends BaseServiceImpl<ShopFloor, Long> implements ShopFloorService {
	
	@Resource(name="shopFloorDao")
	@Override
	public void setDao(BaseDao<ShopFloor, Long> dao) {
		super.dao = dao;
	}
	

}
