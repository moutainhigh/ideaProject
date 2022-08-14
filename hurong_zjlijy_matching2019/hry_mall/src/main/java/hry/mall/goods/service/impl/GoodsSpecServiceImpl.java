/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:45:45 
 */
package hry.mall.goods.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.dao.GoodsSpecDao;
import hry.mall.goods.model.GoodsSpec;
import hry.mall.goods.service.GoodsSpecService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> GoodsSpecService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:45:45  
 */
@Service("goodsSpecService")
public class GoodsSpecServiceImpl extends BaseServiceImpl<GoodsSpec, Long> implements GoodsSpecService {
	
	@Resource(name="goodsSpecDao")
	@Override
	public void setDao(BaseDao<GoodsSpec, Long> dao) {
		super.dao = dao;
	}


	@Override
	public List<GoodsSpec> getGoodsSpec(Map<String, Object> map) {
		return ((GoodsSpecDao)dao).getGoodsSpec(map);
	}
}
