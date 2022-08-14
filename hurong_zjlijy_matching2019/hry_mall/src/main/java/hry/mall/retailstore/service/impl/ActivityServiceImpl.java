/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:05:01 
 */
package hry.mall.retailstore.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.Activity;
import hry.mall.retailstore.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * <p> ActivityService </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:05:01  
 */
@Service("activityService")
public class ActivityServiceImpl extends BaseServiceImpl<Activity, Long> implements ActivityService {
	
	@Resource(name="activityDao")
	@Override
	public void setDao(BaseDao<Activity, Long> dao) {
		super.dao = dao;
	}
	

}
