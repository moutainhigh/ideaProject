/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:33:04 
 */
package hry.admin.klg.prizedraw.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;

import hry.admin.klg.prizedraw.dao.KlPrizedrawSelectionDao;
import hry.admin.klg.prizedraw.model.KlPrizedrawSelection;
import hry.admin.klg.prizedraw.service.KlPrizedrawSelectionService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;

/**
 * <p> KlPrizedrawSelectionService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-06 11:33:04  
 */
@Service("klPrizedrawSelectionService")
public class KlPrizedrawSelectionServiceImpl extends BaseServiceImpl<KlPrizedrawSelection, Long> implements KlPrizedrawSelectionService{
	
	@Resource(name="klPrizedrawSelectionDao")
	@Override
	public void setDao(BaseDao<KlPrizedrawSelection, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		// ----------------------分页查询头部外壳------------------------------
		Page<KlPrizedrawSelection> page = PageFactory.getPage(filter);
		// ----------------------分页查询头部外壳------------------------------

		// ----------------------查询开始------------------------------

		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String email = filter.getRequest().getParameter("email");
		String trueName = filter.getRequest().getParameter("trueName");
		String surName = filter.getRequest().getParameter("surName");
		String issueNumber = filter.getRequest().getParameter("issueNumber");
		String prizedrawGrade = filter.getRequest().getParameter("prizedrawGrade");
		String status = filter.getRequest().getParameter("status");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		String modified_sm = filter.getRequest().getParameter("created_GTM");
		String modified_em = filter.getRequest().getParameter("created_LTM");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(issueNumber)) {
			map.put("issueNumber", issueNumber);
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
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
		if (!StringUtils.isEmpty(modified_s)) {
			map.put("modified_s", modified_s);
		}
		if (!StringUtils.isEmpty(modified_e)) {
			map.put("modified_e", modified_e);
		}
		if (!StringUtils.isEmpty(modified_sm)) {
			map.put("modified_sm", modified_sm);
		}
		if (!StringUtils.isEmpty(modified_em)) {
			map.put("modified_em", modified_em);
		}
		
		((KlPrizedrawSelectionDao) dao).findPageBySql(map);

		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}
	

}
