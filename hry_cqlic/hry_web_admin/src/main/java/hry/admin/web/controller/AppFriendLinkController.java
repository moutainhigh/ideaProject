/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 11:56:58 
 */
package hry.admin.web.controller;


import hry.admin.web.model.AppFriendLink;
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
 * @Date:        2018-06-20 11:56:58 
 */
@Controller
@RequestMapping("/web/appfriendlink")
public class AppFriendLinkController extends BaseController<AppFriendLink, Long> {
	
	@Resource(name = "appFriendLinkService")
	@Override
	public void setService(BaseService<AppFriendLink, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppFriendLink appFriendLink = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appfriendlinksee");
		mav.addObject("model", appFriendLink);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppFriendLink appFriendLink){
		return super.save(appFriendLink);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppFriendLink appFriendLink = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appfriendlinkmodify");
		mav.addObject("model", appFriendLink);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify (HttpServletRequest request, AppFriendLink appFriendLink) {
		JsonResult jsonResult = new JsonResult();
		try {
			service.updateNull(appFriendLink);
			jsonResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppFriendLink.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
