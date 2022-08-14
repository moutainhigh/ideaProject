/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 11:08:54 
 */
package hry.social.feedback.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.feedback.dao.SocialFeedbackDao;
import hry.social.feedback.service.SocialFeedbackService;
import hry.social.manage.remote.model.feedback.SocialFeedback;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> SocialFeedbackService </p>
 * @author:         javalx
 * @Date :          2019-06-11 11:08:54  
 */
@Service("socialFeedbackService")
public class SocialFeedbackServiceImpl extends BaseServiceImpl<SocialFeedback, Long> implements SocialFeedbackService {
	
	@Resource(name="socialFeedbackDao")
	@Override
	public void setDao(BaseDao<SocialFeedback, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 分页查询
	 *
	 * @param filter
	 * @return
	 */
	@Override
	public PageResult findPageList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Page<SocialFeedback> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		//----------------------查询开始------------------------------
		String customerId = filter.getRequest().getParameter("customerId_EQ");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
		String orderNum = filter.getRequest().getParameter("orderNum_EQ");
		String createdGT = filter.getRequest().getParameter("created_GT");
		String createdLT = filter.getRequest().getParameter("created_LT");
		Map<String,Object> map = new HashMap<String,Object>();

		if (!StringUtils.isEmpty(customerId)) {
			map.put("customerId", customerId);
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		if (!StringUtils.isEmpty(orderNum)) {
			map.put("orderNum",orderNum);
		}
		if (!StringUtils.isEmpty(createdGT)) {
			map.put("createdGT", createdGT);
		}
		if (!StringUtils.isEmpty(createdLT)) {
			map.put("createdLT", createdLT);
		}
		((SocialFeedbackDao) dao).findPageList(map);
		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}


}
