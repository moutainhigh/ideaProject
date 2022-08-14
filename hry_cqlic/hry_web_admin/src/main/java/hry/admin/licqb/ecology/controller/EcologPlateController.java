/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:37:08 
 */
package hry.admin.licqb.ecology.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.ecology.model.EcologPlate;

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
 * @Date:        2020-06-05 16:37:08 
 */
@Controller
@RequestMapping("/licqb/ecology/ecologplate")
public class EcologPlateController extends BaseController<EcologPlate, Long> {
	
	@Resource(name = "ecologPlateService")
	@Override
	public void setService(BaseService<EcologPlate, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		EcologPlate ecologPlate = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/ecology/ecologplatesee");
		mav.addObject("model", ecologPlate);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,EcologPlate ecologPlate){
		return super.save(ecologPlate);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		EcologPlate ecologPlate = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/ecology/ecologplatemodify");
		mav.addObject("model", ecologPlate);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,EcologPlate ecologPlate){
		return super.update(ecologPlate);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(EcologPlate.class,request);
		filter.setOrderby("sort asc");
		return super.findPage(filter);
	}
	
	
	
	
}
