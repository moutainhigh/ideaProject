/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:03 
 */
package hry.admin.commend.service.impl;

import com.github.pagehelper.Page;
import hry.admin.commend.dao.AppCommendRebatDao;
import hry.admin.commend.model.AppCommendRebat;
import hry.admin.commend.service.AppCommendRebatService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppCommendRebatService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:50:03  
 */
@Service("appCommendRebatService")
public class AppCommendRebatServiceImpl extends BaseServiceImpl<AppCommendRebat, Long> implements AppCommendRebatService{
	
	@Resource(name="appCommendRebatDao")
	@Override
	public void setDao(BaseDao<AppCommendRebat, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		//----------------------分页查询头部外壳------------------------------
		Page<AppCommendRebat> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------
		Map<String, Object> map = new HashMap<String, Object>();

		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String created_LT = filter.getRequest().getParameter("created_LT");


		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		if(!StringUtils.isEmpty(created_LT)){
			map.put("created_LT", created_LT);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		List<AppCommendRebat> pageBySql = ((AppCommendRebatDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------

		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}
}
