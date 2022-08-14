/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:42:58 
 */
package hry.admin.customer.controller;


import hry.admin.customer.service.AppBankCardService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.customer.model.AppBankCard;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:42:58 
 */
@Controller
@RequestMapping("/customer/appbankcard")
public class AppBankCardController extends BaseController<AppBankCard, Long> {
	
	@Resource(name = "appBankCardService")
	@Override
	public void setService(BaseService<AppBankCard, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppBankCard appBankCard = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/customer/appbankcardsee");
		mav.addObject("model", appBankCard);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppBankCard appBankCard){
		return super.save(appBankCard);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppBankCard appBankCard = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/customer/appbankcardmodify");
		mav.addObject("model", appBankCard);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppBankCard appBankCard){
		return super.update(appBankCard);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppBankCard.class, request);
		PageResult page = ((AppBankCardService) service).findPageBySql(filter);
		return page;
	}
	
	
	
	
}
