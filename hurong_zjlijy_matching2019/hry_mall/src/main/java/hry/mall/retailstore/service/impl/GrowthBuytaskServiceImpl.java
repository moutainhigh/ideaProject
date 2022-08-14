/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-08 11:01:22 
 */
package hry.mall.retailstore.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.GrowthBuytask;
import hry.mall.retailstore.service.GrowthBuytaskService;
import org.springframework.stereotype.Service;

/**
 * <p> GrowthBuytaskService </p>
 * @author:         luyue
 * @Date :          2019-05-08 11:01:22  
 */
@Service("growthBuytaskService")
public class GrowthBuytaskServiceImpl extends BaseServiceImpl<GrowthBuytask, Long> implements GrowthBuytaskService {
	
	@Resource(name="growthBuytaskDao")
	@Override
	public void setDao(BaseDao<GrowthBuytask, Long> dao) {
		super.dao = dao;
	}
	

}
