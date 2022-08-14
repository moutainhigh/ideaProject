/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 17:55:29 
 */
package hry.social.task.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.manage.remote.model.task.SocialTaskReward;
import hry.social.task.dao.SocialTaskRewardDao;
import hry.social.task.service.SocialTaskRewardService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> SocialTaskRewardService </p>
 * @author:         javalx
 * @Date :          2019-06-11 17:55:29  
 */
@Service("socialTaskRewardService")
public class SocialTaskRewardServiceImpl extends BaseServiceImpl<SocialTaskReward, Long> implements SocialTaskRewardService {
	
	@Resource(name="socialTaskRewardDao")
	@Override
	public void setDao(BaseDao<SocialTaskReward, Long> dao) {
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
		Page<SocialTaskReward> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		//----------------------查询开始------------------------------
		String customerId = filter.getRequest().getParameter("customerId_EQ");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
		String surname = filter.getRequest().getParameter("surname_like");
		String trueName = filter.getRequest().getParameter("trueName_like");
		String categoryKey = filter.getRequest().getParameter("categoryKey_EQ");
		String itemName = filter.getRequest().getParameter("itemName_EQ");
		String rewardType = filter.getRequest().getParameter("rewardType_EQ");
		String rewardWay = filter.getRequest().getParameter("rewardWay_EQ");
		String coinCode = filter.getRequest().getParameter("coinCode_EQ");
		String createdGT = filter.getRequest().getParameter("created_GT");
		String createdLT = filter.getRequest().getParameter("created_LT");
		Map<String,Object> map = new HashMap<String,Object>();

		if (!StringUtils.isEmpty(customerId)) {
			map.put("customerId", customerId);
		}
		if (!StringUtils.isEmpty(categoryKey)) {
			map.put("categoryKey", categoryKey);
		}
		if (!StringUtils.isEmpty(itemName)) {
			map.put("itemName", itemName);
		}
		if (!StringUtils.isEmpty(rewardType)) {
			map.put("rewardType", rewardType);
		}
		if (!StringUtils.isEmpty(rewardWay)) {
			map.put("rewardWay", rewardWay);
		}
		if (!StringUtils.isEmpty(coinCode)) {
			map.put("coinCode", coinCode);
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		if (!StringUtils.isEmpty(surname)) {
			map.put("surname", "%" + surname + "%");
		}
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}
		if (!StringUtils.isEmpty(createdGT)) {
			map.put("createdGT", createdGT);
		}
		if (!StringUtils.isEmpty(createdLT)) {
			map.put("createdLT", createdLT);
		}
		((SocialTaskRewardDao) dao).findPageList(map);
		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}

	@Override
	public SocialTaskReward footing(QueryFilter filter) {
		SocialTaskReward str = null;
		//----------------------查询开始------------------------------
		String customerId = filter.getRequest().getParameter("customerId_EQ");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
		String surname = filter.getRequest().getParameter("surname_like");
		String trueName = filter.getRequest().getParameter("trueName_like");
		String categoryKey = filter.getRequest().getParameter("categoryKey_EQ");
		String itemName = filter.getRequest().getParameter("itemName_EQ");
		String rewardType = filter.getRequest().getParameter("rewardType_EQ");
		String rewardWay = filter.getRequest().getParameter("rewardWay_EQ");
		String coinCode = filter.getRequest().getParameter("coinCode_EQ");
		String createdGT = filter.getRequest().getParameter("created_GT");
		String createdLT = filter.getRequest().getParameter("created_LT");
		Map<String,Object> map = new HashMap<String,Object>();

		if (!StringUtils.isEmpty(customerId)) {
			map.put("customerId", customerId);
		}
		if (!StringUtils.isEmpty(categoryKey)) {
			map.put("categoryKey", categoryKey);
		}
		if (!StringUtils.isEmpty(itemName)) {
			map.put("itemName", itemName);
		}
		if (!StringUtils.isEmpty(rewardType)) {
			map.put("rewardType", rewardType);
		}
		if (!StringUtils.isEmpty(rewardWay)) {
			map.put("rewardWay", rewardWay);
		}
		if (!StringUtils.isEmpty(coinCode)) {
			map.put("coinCode", coinCode);
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		if (!StringUtils.isEmpty(surname)) {
			map.put("surname", "%" + surname + "%");
		}
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}
		if (!StringUtils.isEmpty(createdGT)) {
			map.put("createdGT", createdGT);
		}
		if (!StringUtils.isEmpty(createdLT)) {
			map.put("createdLT", createdLT);
		}
		str = ((SocialTaskRewardDao) dao).footing(map);
		return str;
	}
}
