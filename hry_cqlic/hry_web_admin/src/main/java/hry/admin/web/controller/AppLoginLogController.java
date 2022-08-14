/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:43:26 
 */
package hry.admin.web.controller;


import hry.admin.web.model.AppLoginLog;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:43:26 
 */
@Controller
@RequestMapping("/web/apploginlog")
public class AppLoginLogController extends BaseController<AppLoginLog, Long> {
	
	@Resource(name = "appLoginLogService")
	@Override
	public void setService(BaseService<AppLoginLog, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppLoginLog appLoginLog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/apploginlogsee");
		mav.addObject("model", appLoginLog);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppLoginLog appLoginLog){
		return super.save(appLoginLog);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppLoginLog appLoginLog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/apploginlogmodify");
		mav.addObject("model", appLoginLog);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppLoginLog appLoginLog){
		return super.update(appLoginLog);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppLoginLog.class,request);
		filter.setOrderby("created desc");
		return super.findPage(filter);
	}
	
	
	
	
}
