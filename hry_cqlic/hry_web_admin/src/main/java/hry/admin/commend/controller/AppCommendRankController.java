/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:49:45 
 */
package hry.admin.commend.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.commend.model.AppCommendRank;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:49:45 
 */
@Controller
@RequestMapping("/commend/appcommendrank")
public class AppCommendRankController extends BaseController<AppCommendRank, Long> {
	
	@Resource(name = "appCommendRankService")
	@Override
	public void setService(BaseService<AppCommendRank, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppCommendRank appCommendRank = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendranksee");
		mav.addObject("model", appCommendRank);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppCommendRank appCommendRank){
		return super.save(appCommendRank);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppCommendRank appCommendRank = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendrankmodify");
		mav.addObject("model", appCommendRank);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppCommendRank appCommendRank){
		return super.update(appCommendRank);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCommendRank.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
