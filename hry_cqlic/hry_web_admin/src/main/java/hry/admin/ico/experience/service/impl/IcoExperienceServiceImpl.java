/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 10:10:45 
 */
package hry.admin.ico.experience.service.impl;

import com.github.pagehelper.Page;
import hry.admin.ico.experience.dao.IcoExperienceDao;
import hry.admin.ico.experience.model.IcoExperience;
import hry.admin.ico.experience.service.IcoExperienceService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> IcoExperienceService </p>
 * @author:         houz
 * @Date :          2019-01-14 10:10:45  
 */
@Service("icoExperienceService")
public class IcoExperienceServiceImpl extends BaseServiceImpl<IcoExperience, Long> implements IcoExperienceService{
	
	@Resource(name="icoExperienceDao")
	@Override
	public void setDao(BaseDao<IcoExperience, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Page<IcoExperience> page = PageFactory.getPage(filter);
		Map<String,Object> map = new HashMap<String,Object>();

		String customer_email = filter.getRequest().getParameter("customer_email");
		String customer_mobile = filter.getRequest().getParameter("customer_mobile");
		String type = filter.getRequest().getParameter("type");
		String account_type = filter.getRequest().getParameter("account_type");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");

		if(!StringUtils.isEmpty(customer_email)){
			map.put("customer_email", "%"+customer_email+"%" );

		}
		if(!StringUtils.isEmpty(customer_mobile)){
			map.put("customer_mobile", "%"+customer_mobile+"%");
		}
		if(!StringUtils.isEmpty(type)){
			map.put("type",type);
		}
		if(!StringUtils.isEmpty(account_type)){
			map.put("account_type", account_type);
		}
		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}

		long s1=System.currentTimeMillis();
		((IcoExperienceDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}

}
