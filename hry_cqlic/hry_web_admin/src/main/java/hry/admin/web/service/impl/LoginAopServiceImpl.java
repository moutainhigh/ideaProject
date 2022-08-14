/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:28 
 */
package hry.admin.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.admin.customer.model.AppCustomer;
import hry.admin.web.dao.LoginAopDao;
import hry.admin.web.model.LoginAop;
import hry.admin.web.service.LoginAopService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> LoginAopService </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:42:28  
 */
@Service("loginAopService")
public class LoginAopServiceImpl extends BaseServiceImpl<LoginAop, Long> implements LoginAopService{
	
	@Resource(name="loginAopDao")
	@Override
	public void setDao(BaseDao<LoginAop, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 加入/移除黑名单
	 * @param userId      用户id
	 * @param blackStatus 修改状态
	 * @return
	 */
	public JsonResult updateBlackStatus(String userId, Integer blackStatus) {
		JsonResult jsonResult = new JsonResult();
		try {
			((LoginAopDao) dao).updateBlackStatus(userId, blackStatus);
			jsonResult.setSuccess(true);
			jsonResult.setMsg("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg("操作失败");
		}
		return jsonResult;
	}


	/**
	 * 黑名单分页查询
	 * @param filter
	 * @return
	 */
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Page<LoginAop> page = PageFactory.getPage(filter);

		String userName = filter.getRequest().getParameter("userName");
		String trueName = filter.getRequest().getParameter("trueName");
		String ip = filter.getRequest().getParameter("ip");

		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", "%"+userName+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(ip)){
			map.put("ip", "%"+ip+"%");
		}

		((LoginAopDao) dao).findPageBySql(map);

		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

}
