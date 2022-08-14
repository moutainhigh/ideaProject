/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:49:07 
 */
package hry.admin.licqb.award.service.impl;

import com.github.pagehelper.Page;
import hry.admin.licqb.award.dao.OutInfoDao;
import hry.admin.licqb.award.model.OutInfo;
import hry.admin.licqb.award.model.UserCommendAsset;
import hry.admin.licqb.award.service.OutInfoService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> OutInfoService </p>
 * @author:         zhouming
 * @Date :          2019-08-13 13:49:07  
 */
@Service("outInfoService")
public class OutInfoServiceImpl extends BaseServiceImpl<OutInfo, Long> implements OutInfoService{
	private final static Logger log = Logger.getLogger(OutInfoServiceImpl.class);

	@Resource(name="outInfoDao")
	@Override
	public void setDao(BaseDao<OutInfo, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findUserList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<OutInfo> page = PageFactory.getPage(filter);
		//参数集合
		Map<String,Object> map = new HashMap<>();
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String receCode = filter.getRequest().getParameter("receCode");
		if(!StringUtils.isEmpty(receCode)){
			map.put("receCode", "%"+receCode+"%");
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		long s1=System.currentTimeMillis();
		List<OutInfo> list = ((OutInfoDao)dao).findPageBySql(map);
		log.info("~~~~~推荐人信息查询sql所用时间 ：" + (System.currentTimeMillis()-s1) + "毫秒");
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public PageResult findSonUserList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<OutInfo> page = PageFactory.getPage(filter);
		//参数集合
		Map<String,Object> map = new HashMap<>();
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String level = filter.getRequest().getParameter("level");
		String sort = filter.getRequest().getParameter("sort");
		String customerId = filter.getRequest().getParameter("customerId");
		if(!StringUtils.isEmpty(customerId)){
			map.put("customerId", customerId);
		}
		if(!StringUtils.isEmpty(sort)){
			map.put("sort", sort);
		}
		if(!StringUtils.isEmpty(level)){
			if ("11".equals(level)) {
				map.put("default", "false");
			} else {
				map.put("level", level);
			}
		} else {
			map.put("default", "true");
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		long s1=System.currentTimeMillis();
		((OutInfoDao)dao).findSonPageBySql(map);
		log.info("~~~~~用户伞下用户信息查询sql所用时间 ：" + (System.currentTimeMillis()-s1) + "毫秒");
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}


	@Override
	public PageResult findUserAsset(QueryFilter filter) {
		//创建PageResult对象
		Page<UserCommendAsset> page = PageFactory.getPage(filter);
		String customerId = filter.getRequest().getParameter("customerId");
		long s1 = System.currentTimeMillis();
		((OutInfoDao)dao).findUserPerformance(customerId);
		log.info("~~~~~个人资产sql所用时间 ：" + (System.currentTimeMillis()-s1) + "毫秒");
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}
