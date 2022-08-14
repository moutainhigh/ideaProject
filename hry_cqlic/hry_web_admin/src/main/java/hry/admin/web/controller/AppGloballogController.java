/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-20 17:47:17 
 */
package hry.admin.web.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.web.model.AppGloballog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-20 17:47:17 
 */
@Controller
@RequestMapping("/web/appgloballog")
public class AppGloballogController extends BaseController<AppGloballog, Long> {
	
	@Resource(name = "appGloballogService")
	@Override
	public void setService(BaseService<AppGloballog, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppGloballog appGloballog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appgloballogsee");
		mav.addObject("model", appGloballog);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppGloballog appGloballog){
		return super.save(appGloballog);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppGloballog appGloballog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appgloballogmodify");
		mav.addObject("model", appGloballog);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppGloballog appGloballog){
		return super.update(appGloballog);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		String appName = request.getParameter("appName");
		QueryFilter filter = new QueryFilter(AppGloballog.class,request);
		if(!StringUtils.isEmpty(appName)){
			filter.addFilter("app_name=",appName);
		}
		filter.setOrderby("log_time desc");
		return super.findPage(filter);
	}
	
	
	
	
}
