/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 10:10:45 
 */
package hry.admin.ico.experience.controller;


import hry.admin.ico.experience.service.IcoExperienceService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.experience.model.IcoExperience;

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
 * @Date:        2019-01-14 10:10:45 
 */
@Controller
@RequestMapping("/ico/experience/icoexperience")
public class IcoExperienceController extends BaseController<IcoExperience, Long> {
	
	@Resource(name = "icoExperienceService")
	@Override
	public void setService(BaseService<IcoExperience, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoExperience icoExperience = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/experience/icoexperiencesee");
		mav.addObject("model", icoExperience);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,IcoExperience icoExperience){
		return super.save(icoExperience);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		IcoExperience icoExperience = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/experience/icoexperiencemodify");
		mav.addObject("model", icoExperience);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,IcoExperience icoExperience){
		return super.update(icoExperience);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoExperience.class,request);
		return super.findPage(filter);
	}

	@RequestMapping(value="/findPageBySql")
	@ResponseBody
	public PageResult findPageBySql(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoExperience.class,request);
		PageResult findPageBySql = ((IcoExperienceService) service).findPageBySql(filter);
		return findPageBySql;
	}
	
}
