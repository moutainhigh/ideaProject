/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-15 13:08:06 
 */
package hry.admin.customer.controller;


import hry.admin.customer.model.AppAccount;
import hry.admin.customer.service.AppAccountService;
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
 * @Date:        2018-06-15 13:08:06 
 */
@Controller
@RequestMapping("/customer/appaccount")
public class AppAccountController extends BaseController<AppAccount, Long> {
	
	@Resource(name = "appAccountService")
	@Override
	public void setService(BaseService<AppAccount, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppAccount appAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/customer/appaccountsee");
		mav.addObject("model", appAccount);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppAccount appAccount){
		return super.save(appAccount);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppAccount appAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/customer/appaccountmodify");
		mav.addObject("model", appAccount);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppAccount appAccount){
		return super.update(appAccount);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppAccount.class,request);
		return super.findPage(filter);
	}


	@RequestMapping(value="/checklist")
	@ResponseBody
	public PageResult checklist(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppAccount.class,request);
		return ((AppAccountService) service).findPageBySqlList(filter);
	}





}
