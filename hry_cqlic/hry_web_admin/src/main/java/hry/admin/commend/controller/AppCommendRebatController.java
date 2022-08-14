/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:03 
 */
package hry.admin.commend.controller;


import hry.admin.commend.model.AppCommendRebat;
import hry.admin.commend.service.AppCommendRebatService;
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
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:03 
 */
@Controller
@RequestMapping("/commend/appcommendrebat")
public class AppCommendRebatController extends BaseController<AppCommendRebat, Long> {
	
	@Resource(name = "appCommendRebatService")
	@Override
	public void setService(BaseService<AppCommendRebat, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppCommendRebat appCommendRebat = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendrebatsee");
		mav.addObject("model", appCommendRebat);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppCommendRebat appCommendRebat){
		return super.save(appCommendRebat);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppCommendRebat appCommendRebat = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendrebatmodify");
		mav.addObject("model", appCommendRebat);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppCommendRebat appCommendRebat){
		return super.update(appCommendRebat);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCommendRebat.class, request);
		PageResult pageResult = ((AppCommendRebatService)service).findPageBySql(filter);
		return pageResult;
	}
	
	
	
	
}
