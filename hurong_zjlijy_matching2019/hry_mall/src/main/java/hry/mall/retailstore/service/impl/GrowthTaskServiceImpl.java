/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-07 11:11:08 
 */
package hry.mall.retailstore.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.GrowthTask;
import hry.mall.retailstore.service.GrowthTaskService;
import org.springframework.stereotype.Service;

/**
 * <p> GrowthTaskService </p>
 * @author:         luyue
 * @Date :          2019-05-07 11:11:08  
 */
@Service("growthTaskService")
public class GrowthTaskServiceImpl extends BaseServiceImpl<GrowthTask, Long> implements GrowthTaskService {
	
	@Resource(name="growthTaskDao")
	@Override
	public void setDao(BaseDao<GrowthTask, Long> dao) {
		super.dao = dao;
	}
	

}
