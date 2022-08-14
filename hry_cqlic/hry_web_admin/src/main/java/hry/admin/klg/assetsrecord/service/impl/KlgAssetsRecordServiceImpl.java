/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 15:54:03 
 */
package hry.admin.klg.assetsrecord.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;

import hry.admin.klg.assetsrecord.dao.KlgAssetsRecordDao;
import hry.admin.klg.assetsrecord.model.KlgAssetsRecord;
import hry.admin.klg.assetsrecord.model.vo.KlgAssetsRecordVo;
import hry.admin.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;

/**
 * <p> KlgAssetsRecordService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-29 15:54:03  
 */
@Service("klgAssetsRecordService")
public class KlgAssetsRecordServiceImpl extends BaseServiceImpl<KlgAssetsRecord, Long> implements KlgAssetsRecordService{
	
	@Resource(name="klgAssetsRecordDao")
	@Override
	public void setDao(BaseDao<KlgAssetsRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		//----------------------分页查询头部外壳------------------------------
		Page<KlgAssetsRecordVo> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------

		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String email = filter.getRequest().getParameter("email");
		String trueName = filter.getRequest().getParameter("trueName");
		String surName = filter.getRequest().getParameter("surName");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String customerId = filter.getRequest().getParameter("customerId");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		if(!StringUtils.isEmpty(customerId)){
			map.put("customerId", customerId);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(surName)){
			map.put("surName", "%"+surName+"%");
		}
		if(!StringUtils.isEmpty(modified_s)){
			map.put("modified_s", modified_s);
		}
		if(!StringUtils.isEmpty(modified_e)){
			map.put("modified_e", modified_e);
		}
		((KlgAssetsRecordDao)dao).findPageBySql(map);
		
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
	

}
