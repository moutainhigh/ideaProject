/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zenghao
 * @version:     V1.0 
 * @Date:        2016-11-21 15:48:49 
 */
package hry.exchange.subscription.service.impl;

import hry.exchange.subscription.model.ExSubscriptionPlan;
import hry.exchange.subscription.service.ExSubscriptionPlanService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExSubscriptionPlanService </p>
 * @author:         zenghao
 * @Date :          2016-11-21 15:48:49  
 */
@Service("exSubscriptionPlanService")
public class ExSubscriptionPlanServiceImpl extends BaseServiceImpl<ExSubscriptionPlan, Long> implements ExSubscriptionPlanService{
	
	@Resource(name="exSubscriptionPlanDao")
	@Override
	public void setDao(BaseDao<ExSubscriptionPlan, Long> dao) {
		super.dao = dao;
	}
	

}
