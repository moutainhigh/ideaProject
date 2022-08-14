/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:41:50 
 */
package hry.admin.licqb.platform.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.platform.model.CustomerLevel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:41:50 
 */
@Controller
@RequestMapping("/licqb/platform/customerlevel")
public class CustomerLevelController extends BaseController<CustomerLevel, Long> {
	
	@Resource(name = "customerLevelService")
	@Override
	public void setService(BaseService<CustomerLevel, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		CustomerLevel customerLevel = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/customerlevelsee");
		mav.addObject("model", customerLevel);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,CustomerLevel customerLevel){
		return super.save(customerLevel);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		CustomerLevel customerLevel = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/customerlevelmodify");
		mav.addObject("model", customerLevel);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,CustomerLevel customerLevel){
		return super.update(customerLevel);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(CustomerLevel.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
