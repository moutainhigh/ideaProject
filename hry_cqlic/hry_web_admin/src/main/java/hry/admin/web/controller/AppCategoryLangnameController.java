/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 15:47:20 
 */
package hry.admin.web.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.web.model.AppCategoryLangname;

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
 * @Date:        2018-06-26 15:47:20 
 */
@Controller
@RequestMapping("/web/appcategorylangname")
public class AppCategoryLangnameController extends BaseController<AppCategoryLangname, Long> {
	
	@Resource(name = "appCategoryLangnameService")
	@Override
	public void setService(BaseService<AppCategoryLangname, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppCategoryLangname appCategoryLangname = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appcategorylangnamesee");
		mav.addObject("model", appCategoryLangname);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppCategoryLangname appCategoryLangname){
		return super.save(appCategoryLangname);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppCategoryLangname appCategoryLangname = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appcategorylangnamemodify");
		mav.addObject("model", appCategoryLangname);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppCategoryLangname appCategoryLangname){
		return super.update(appCategoryLangname);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCategoryLangname.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
