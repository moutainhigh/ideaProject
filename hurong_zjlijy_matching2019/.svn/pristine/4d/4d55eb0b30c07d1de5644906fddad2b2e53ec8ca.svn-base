/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-24 15:03:18 
 */
package hry.mall.goods.service.impl;

import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.Favorite;
import hry.mall.goods.service.FavoriteService;
import org.springframework.stereotype.Service;


/**
 * <p> FavoriteService </p>
 * @author:         luyue
 * @Date :          2018-12-24 15:03:18  
 */
@Service("favoriteService")
public class FavoriteServiceImpl extends BaseServiceImpl<Favorite, Long> implements FavoriteService {
	
	@Resource(name="favoriteDao")
	@Override
	public void setDao(BaseDao<Favorite, Long> dao) {
		super.dao = dao;
	}
	

}
