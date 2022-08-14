/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:45:27 
 */
package hry.admin.customer.service.impl;

import hry.admin.customer.dao.AppPersonInfoDao;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppPersonInfoService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppPersonInfoService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 09:45:27  
 */
@Service("appPersonInfoService")
public class AppPersonInfoServiceImpl extends BaseServiceImpl<AppPersonInfo, Long> implements AppPersonInfoService{
	
	@Resource(name="appPersonInfoDao")
	@Override
	public void setDao(BaseDao<AppPersonInfo, Long> dao) {
		super.dao = dao;
	}


	@Override
	public List<AppPersonInfo> getAppPersonInfoByEmailPhone (List<String> listId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("listId", listId);
		return ((AppPersonInfoDao)dao).getAppPersonInfoByEmailPhone(paramMap);
	}

	public AppPersonInfo getByCustomerId(Long id){
		{
			if(null!=id){
				QueryFilter filter = new QueryFilter(AppPersonInfo.class);
				filter.addFilter("customerId=", id);
				return get(filter);
			}
			return null;
		}
	}
}
