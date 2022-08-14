/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-11-01 18:38:24 
 */
package hry.admin.lock.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.lock.model.ExDmLockReleaseTime;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-11-01 18:38:24 
 */
@Controller
@RequestMapping("/lock/exdmlockreleasetime")
public class ExDmLockReleaseTimeController extends BaseController<ExDmLockReleaseTime, Long> {
	
	@Resource(name = "exDmLockReleaseTimeService")
	@Override
	public void setService(BaseService<ExDmLockReleaseTime, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExDmLockReleaseTime exDmLockReleaseTime = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lock/exdmlockreleasetimesee");
		mav.addObject("model", exDmLockReleaseTime);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExDmLockReleaseTime exDmLockReleaseTime){
		return super.save(exDmLockReleaseTime);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExDmLockReleaseTime exDmLockReleaseTime = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lock/exdmlockreleasetimemodify");
		mav.addObject("model", exDmLockReleaseTime);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExDmLockReleaseTime exDmLockReleaseTime){
		return super.update(exDmLockReleaseTime);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExDmLockReleaseTime.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
