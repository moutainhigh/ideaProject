/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-04 11:06:01 
 */
package hry.admin.licqb.ecology.service.impl;

import com.github.pagehelper.Page;
import hry.admin.licqb.ecology.dao.EcofundDao;
import hry.admin.licqb.ecology.model.Ecofund;
import hry.admin.licqb.ecology.service.EcofundService;
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
 * <p> EcofundService </p>
 * @author:         zhouming
 * @Date :          2020-06-04 11:06:01  
 */
@Service("ecofundService")
public class EcofundServiceImpl extends BaseServiceImpl<Ecofund, Long> implements EcofundService{
	
	@Resource(name="ecofundDao")
	@Override
	public void setDao(BaseDao<Ecofund, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageEcofundList(QueryFilter filter) {
		Page<Ecofund> page = PageFactory.getPage(filter);
		//参数集合
		Map<String,Object> map = new HashMap<>();
		String orderNum = filter.getRequest().getParameter("orderNum");
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");
		String status = filter.getRequest().getParameter("status");
		String activityStatus = filter.getRequest().getParameter("activityStatus");
		if(!StringUtils.isEmpty(orderNum)){
			map.put("orderNum", "%"+orderNum+"%");
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}
		if(!StringUtils.isEmpty(status)){
			if ("1".equals(status)) {
				map.put("activityStatus", 1);
			} else if ("6".equals(status)) {
				map.put("activityStatus", 6);
			} else {
				if (!StringUtils.isEmpty(activityStatus)) {
					if ("9".equals(activityStatus)) {
						map.put("activityStatus", 3);
						map.put("itAgain", 1);
					} else {
						map.put("activityStatus", activityStatus);
					}
				}
			}
		}
		((EcofundDao)dao).findEcofundList(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public Ecofund getEcofund(Long id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		List<Ecofund> ecofundList = ((EcofundDao)dao).findEcofundList(map);
		return ecofundList != null && ecofundList.size() > 0 ? ecofundList.get(0) : null;
	}
}
