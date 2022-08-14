/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-08-22 13:39:20 
 */
package hry.admin.web.controller;


import hry.admin.web.model.LogCommonAop;
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
 * @Date:        2018-08-22 13:39:20 
 */
@Controller
@RequestMapping("/web/logcommonaop")
public class LogCommonAopController extends BaseController<LogCommonAop, Long> {
	
	@Resource(name = "logCommonAopService")
	@Override
	public void setService(BaseService<LogCommonAop, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		LogCommonAop logCommonAop = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/logcommonaopsee");
		mav.addObject("model", logCommonAop);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,LogCommonAop logCommonAop){
		return super.save(logCommonAop);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		LogCommonAop logCommonAop = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/logcommonaopmodify");
		mav.addObject("model", logCommonAop);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,LogCommonAop logCommonAop){
		return super.update(logCommonAop);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		String type = request.getParameter("type");
		QueryFilter filter = new QueryFilter(LogCommonAop.class,request);
		if ("front".equals(type)) {
			filter.addFilter("target_like", "%hry.front%");
		} else if ("back".equals(type)) {
			filter.addFilter("target_like", "%hry.admin%");
		}
		filter.setOrderby("created desc");
		return super.findPage(filter);
	}
	
	
	
	
}
