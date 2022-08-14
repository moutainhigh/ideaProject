/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 20:10:54 
 */
package hry.admin.commend.controller;


import hry.admin.commend.model.AppCommendUser;
import hry.admin.commend.service.AppCommendUserService;
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
 * @Date:        2018-06-25 20:10:54 
 */
@Controller
@RequestMapping("/commend/appcommenduser")
public class AppCommendUserController extends BaseController<AppCommendUser, Long> {
	
	@Resource(name = "appCommendUserService")
	@Override
	public void setService(BaseService<AppCommendUser, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppCommendUser appCommendUser = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendusersee");
		mav.addObject("model", appCommendUser);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppCommendUser appCommendUser){
		return super.save(appCommendUser);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppCommendUser appCommendUser = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendusermodify");
		mav.addObject("model", appCommendUser);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppCommendUser appCommendUser){
		return super.update(appCommendUser);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCommendUser.class,request);
		PageResult findPageBySql = ((AppCommendUserService) service).findPageBySql(filter);
		return findPageBySql;
	}


	@RequestMapping("/commendList")
	@ResponseBody
	public PageResult commendList(HttpServletRequest request, Long id ,Integer number){
		QueryFilter filter = new QueryFilter(AppCommendUser.class, request);
        PageResult pg = ((AppCommendUserService) service).findConmmendPageBySql(filter);
        return  pg;
    }

	/**
	 * 分页查询所有用户的业绩
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listByIco")
	@ResponseBody
	public PageResult listByIco(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCommendUser.class,request);
		PageResult findPageBySql = ((AppCommendUserService) service).icoFindPageBySql(filter);
		return findPageBySql;
	}

	/**
	 * 查询一个用户下级的昨日新增记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/newResultsList")
	@ResponseBody
	public PageResult newResultsList(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCommendUser.class,request);
		PageResult findPageBySql = ((AppCommendUserService) service).newResultsList(filter);
		return findPageBySql;
	}


	@RequestMapping(value="/icoSee/{id}")
	public ModelAndView icoSee(@PathVariable Long id){
		ModelAndView mav = new ModelAndView("/admin/commend/icoappcommendusersee");
		mav.addObject("customerId", id);
		return mav;
	}


	/**
	 * 查询一个用户下级的资产信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/assetsList")
	@ResponseBody
	public PageResult assetsList(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCommendUser.class,request);
		PageResult findPageBySql = ((AppCommendUserService) service).assetsList(filter);
		return findPageBySql;
	}
	
	
}
