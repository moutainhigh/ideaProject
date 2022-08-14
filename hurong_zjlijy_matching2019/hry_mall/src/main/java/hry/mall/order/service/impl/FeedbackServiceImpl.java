/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2018-12-24 19:50:50 
 */
package hry.mall.order.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.order.model.Feedback;
import hry.mall.order.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> FeedbackService </p>
 * @author:         kongdb
 * @Date :          2018-12-24 19:50:50  
 */
@Service("feedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback, Long> implements FeedbackService {
	
	@Resource(name="feedbackDao")
	@Override
	public void setDao(BaseDao<Feedback, Long> dao) {
		super.dao = dao;
	}
	

}
