/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:40:23 
 */
package hry.mall.retailstore.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.SearchGoods;
import hry.mall.retailstore.dao.StoreGoodsDao;
import hry.mall.retailstore.model.StoreGoods;
import hry.mall.retailstore.service.StoreGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * <p> StoreGoodsService </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:40:23  
 */
@Service("storeGoodsService")
public class StoreGoodsServiceImpl extends BaseServiceImpl<StoreGoods, Long> implements StoreGoodsService {
	
	@Resource(name="storeGoodsDao")
	@Override
	public void setDao(BaseDao<StoreGoods, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<SearchGoods> findStoreGoodsList(Map<String, String> params) {
		return ((StoreGoodsDao)dao).findStoreGoodsList(params);
	}
}
