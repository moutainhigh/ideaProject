/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-16 21:24:40 
 */
package hry.admin.ico.level.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.level.model.IcoCustomerLevel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-16 21:24:40 
 */
@Controller
@RequestMapping("/ico/level/icocustomerlevel")
public class IcoCustomerLevelController extends BaseController<IcoCustomerLevel, Long> {
	
	@Resource(name = "icoCustomerLevelService")
	@Override
	public void setService(BaseService<IcoCustomerLevel, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoCustomerLevel icoCustomerLevel = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/level/icocustomerlevelsee");
		mav.addObject("model", icoCustomerLevel);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,IcoCustomerLevel icoCustomerLevel){
		return super.save(icoCustomerLevel);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		IcoCustomerLevel icoCustomerLevel = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/level/icocustomerlevelmodify");
		mav.addObject("model", icoCustomerLevel);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,IcoCustomerLevel icoCustomerLevel){
		return super.update(icoCustomerLevel);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoCustomerLevel.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
