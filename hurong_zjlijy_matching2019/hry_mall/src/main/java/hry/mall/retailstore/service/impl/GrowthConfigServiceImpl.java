/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-07 11:07:06 
 */
package hry.mall.retailstore.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.GrowthConfig;
import hry.mall.retailstore.service.GrowthConfigService;
import org.springframework.stereotype.Service;

/**
 * <p> GrowthConfigService </p>
 * @author:         luyue
 * @Date :          2019-05-07 11:07:06  
 */
@Service("growthConfigService")
public class GrowthConfigServiceImpl extends BaseServiceImpl<GrowthConfig, Long> implements GrowthConfigService {
	
	@Resource(name="growthConfigDao")
	@Override
	public void setDao(BaseDao<GrowthConfig, Long> dao) {
		super.dao = dao;
	}
	

}
