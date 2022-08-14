/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:45:09 
 */
package hry.mall.goods.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.GoodsClass;
import hry.mall.goods.service.GoodsClassService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p> GoodsClassService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:45:09  
 */
@Service("goodsClassService")
public class GoodsClassServiceImpl extends BaseServiceImpl<GoodsClass, Long> implements GoodsClassService {
	
	@Resource(name="goodsClassDao")
	@Override
	public void setDao(BaseDao<GoodsClass, Long> dao) {
		super.dao = dao;
	}


	@Override
	public List<GoodsClass> findGoodsClassList(String gcParentId) {
		QueryFilter filter = new QueryFilter(GoodsClass.class);
		if (gcParentId != null && !"".equals(gcParentId)) {
			filter.addFilter("gcParentId=",gcParentId);
			filter.addFilter("gcShow=","1");
			filter.setOrderby("sort asc");
			List<GoodsClass> goodsClassList = super.find(filter);
			if("0".equals(gcParentId)){
				if(goodsClassList != null && goodsClassList.size() > 0){
					for(int i = 0; i < goodsClassList.size(); i++){
						GoodsClass goodsClass = goodsClassList.get(i);
						filter = new QueryFilter(GoodsClass.class);
						filter.addFilter("gcParentId=",goodsClass.getId());
						List<GoodsClass> goodsClasses = super.find(filter);
						if (goodsClasses == null || goodsClasses.size() == 0) {
							goodsClassList.remove(i);
						}
					}
				}
			}
			return goodsClassList;
		}
		return null;
	}
}
