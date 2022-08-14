/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:48:57 
 */
package hry.admin.commend.controller;


import hry.admin.commend.model.AppCommendDeploy;
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
 * @Date:        2018-06-25 19:48:57 
 */
@Controller
@RequestMapping("/commend/appcommenddeploy")
public class AppCommendDeployController extends BaseController<AppCommendDeploy, Long> {
	
	@Resource(name = "appCommendDeployService")
	@Override
	public void setService(BaseService<AppCommendDeploy, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppCommendDeploy appCommendDeploy = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommenddeploysee");
		mav.addObject("model", appCommendDeploy);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppCommendDeploy appCommendDeploy){
		return super.save(appCommendDeploy);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppCommendDeploy appCommendDeploy = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommenddeploymodify");
		mav.addObject("model", appCommendDeploy);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppCommendDeploy appCommendDeploy){
		return super.update(appCommendDeploy);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCommendDeploy.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
