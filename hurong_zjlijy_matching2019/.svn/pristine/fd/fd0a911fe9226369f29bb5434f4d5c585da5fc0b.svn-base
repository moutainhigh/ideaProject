/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-17 18:36:28 
 */
package hry.mall.retailstore.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.ActivityTime;
import hry.mall.retailstore.service.ActivityTimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> ActivityTimeService </p>
 * @author:         zhouming
 * @Date :          2019-05-17 18:36:28  
 */
@Service("activityTimeService")
public class ActivityTimeServiceImpl extends BaseServiceImpl<ActivityTime, Long> implements ActivityTimeService {
	
	@Resource(name="activityTimeDao")
	@Override
	public void setDao(BaseDao<ActivityTime, Long> dao) {
		super.dao = dao;
	}
	

}
