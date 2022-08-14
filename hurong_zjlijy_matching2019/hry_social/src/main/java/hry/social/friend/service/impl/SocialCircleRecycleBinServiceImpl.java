/**
 * Copyright:   
 * @author:      yjl
 * @version:     V1.0 
 * @Date:        2018-12-10 18:05:54 
 */
package hry.social.friend.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.friend.dao.SocialCircleRecycleBinDao;
import hry.social.friend.service.SocialCircleRecycleBinService;
import hry.social.manage.remote.model.friend.SocialCircleRecycleBin;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> SocialCircleRecycleBinService </p>
 * @author:         yjl
 * @Date :          2018-12-10 18:05:54  
 */
@Service("socialCircleRecycleBinService")
public class SocialCircleRecycleBinServiceImpl extends BaseServiceImpl<SocialCircleRecycleBin, Long> implements SocialCircleRecycleBinService {
	
	@Resource(name="socialCircleRecycleBinDao")
	@Override
	public void setDao(BaseDao<SocialCircleRecycleBin, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		// 分页参数处理


		//创建PageResult对象
		Page<SocialCircleRecycleBin> page = PageFactory.getPage(filter);

		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();


		String id = filter.getRequest().getParameter("id");
		String customerId = filter.getRequest().getParameter("customerId");
		String email = filter.getRequest().getParameter("email");
		String phone = filter.getRequest().getParameter("phone");
		String name = filter.getRequest().getParameter("name");
		String keyword = filter.getRequest().getParameter("keyword");
		String start = filter.getRequest().getParameter("start");
		String end = filter.getRequest().getParameter("end");
		String startde = filter.getRequest().getParameter("startde");
		String ende = filter.getRequest().getParameter("ende");

		// 币的类型

		Map<String,Object> mapcustomer = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(id)){
			map.put("id",Long.valueOf(id));
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(phone)){
			map.put("phone", "%"+phone+"%");
		}
		if(!StringUtils.isEmpty(name)){
			map.put("name", "%"+name+"%");
		}
		if(!StringUtils.isEmpty(keyword)){
			map.put("keyword","%"+keyword+"%");
		}
		if(!StringUtils.isEmpty(start)){
			map.put("start", start);
		}
		if(!StringUtils.isEmpty(customerId)){
			map.put("customerId", customerId);
		}
		if(!StringUtils.isEmpty(end)){
			map.put("end", end);
		}
		if(!StringUtils.isEmpty(startde)){
			map.put("startde", startde);
		}
		if(!StringUtils.isEmpty(ende)){
			map.put("ende", ende);
		}



		((SocialCircleRecycleBinDao)dao).findPageBySql(map);

		//----------------------分页查询底部外壳------------------------------

		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}

	@Override
	public void open(Long ids) {
		((SocialCircleRecycleBinDao)dao).open(ids);
	}

	@Override
	public SocialCircleRecycleBin findOne(Long id) {
		return ((SocialCircleRecycleBinDao)dao).findOne(id);
	}
}
