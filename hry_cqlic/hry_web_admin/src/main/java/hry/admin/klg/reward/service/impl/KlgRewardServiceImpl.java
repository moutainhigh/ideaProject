/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-24 18:13:04 
 */
package hry.admin.klg.reward.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;

import hry.admin.klg.reward.dao.KlgRewardDao;
import hry.admin.klg.reward.model.KlgReward;
import hry.admin.klg.reward.model.vo.KlgRewardVo;
import hry.admin.klg.reward.service.KlgRewardService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;

/**
 * <p> KlgRewardService </p>
 * @author:         yaozhuo
 * @Date :          2019-05-24 18:13:04  
 */
@Service("klgRewardService")
public class KlgRewardServiceImpl extends BaseServiceImpl<KlgReward, Long> implements KlgRewardService{
	
	@Resource(name="klgRewardDao")
	@Override
	public void setDao(BaseDao<KlgReward, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		// ----------------------分页查询头部外壳------------------------------
		Page<KlgRewardVo> page = PageFactory.getPage(filter);
		// ----------------------分页查询头部外壳------------------------------

		// ----------------------查询开始------------------------------

		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String email = filter.getRequest().getParameter("email");
		String trueName = filter.getRequest().getParameter("trueName");
		String surName = filter.getRequest().getParameter("surName");
		String rewardType = filter.getRequest().getParameter("rewardType");
		String status = filter.getRequest().getParameter("status");
		String transactionNum = filter.getRequest().getParameter("transactionNum");
		String sellTransactionNum = filter.getRequest().getParameter("sellTransactionNum");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");

		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(transactionNum)) {
			map.put("transactionNum", "%" + transactionNum + "%");
		}
		if (!StringUtils.isEmpty(sellTransactionNum)) {
			map.put("sellTransactionNum", "%" + sellTransactionNum + "%");
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}
		if (!StringUtils.isEmpty(surName)) {
			map.put("surName", "%" + surName + "%");
		}
		if (!StringUtils.isEmpty(status)) {
			map.put("status", Integer.valueOf(status));
		}
		if (!StringUtils.isEmpty(rewardType)) {
			map.put("rewardType", Integer.valueOf(rewardType));
		}
		if (!StringUtils.isEmpty(modified_s)) {
			map.put("modified_s", modified_s);
		}
		if (!StringUtils.isEmpty(modified_e)) {
			map.put("modified_e", modified_e);
		}
		((KlgRewardDao) dao).findPageBySql(map);

		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}
	

}
