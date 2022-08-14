/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:45:27 
 */
package hry.admin.customer.controller;


import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.customer.service.AppPersonInfoService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.base.MethodName;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:45:27 
 */
@Controller
@RequestMapping("/customer/apppersoninfo")
public class AppPersonInfoController extends BaseController<AppPersonInfo, Long> {
	
	@Resource(name = "appPersonInfoService")
	@Override
	public void setService(BaseService<AppPersonInfo, Long> service) {
		super.service = service;
	}

	@Resource(name = "appCustomerService")
	public AppCustomerService appCustomerService;
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppPersonInfo appPersonInfo = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/customer/apppersoninfosee");
		mav.addObject("model", appPersonInfo);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppPersonInfo appPersonInfo){
		return super.save(appPersonInfo);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppPersonInfo appPersonInfo = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/customer/apppersoninfomodify");
		mav.addObject("model", appPersonInfo);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppPersonInfo appPersonInfo){
		return super.update(appPersonInfo);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppPersonInfo.class,request);
		return super.findPage(filter);
	}

	@MethodName(name = "发送消息 根据邮箱或手机号 查询userName")
	@RequestMapping("/finduserName")
	@ResponseBody
	public JsonResult finduserName(HttpServletRequest request){
		JsonResult result = new JsonResult();
		List<String> userNameList = new ArrayList<String>();

		String listIds  = request.getParameter("listIds");
		if(listIds.contains(",")){
			String[] split = listIds.split(",");
			for(String str : split){
				userNameList.add(str);
			}
		}else{
			userNameList.add(listIds);
		}
		List<AppPersonInfo> AppPersonInfoList = ((AppPersonInfoService)service).getAppPersonInfoByEmailPhone(userNameList);
		for(AppPersonInfo info : AppPersonInfoList){
			AppCustomer appCustomer = appCustomerService.get(info.getCustomerId());
			userNameList.add(appCustomer.getUsername());
		}
		result.setObj(userNameList);
		result.setSuccess(true);
		return result;
	}

	@MethodName(name = "发送消息 根据邮箱或手机号 查询userName")
	@RequestMapping("/finduserId")
	@ResponseBody
	public JsonResult finduserId(HttpServletRequest request){
		JsonResult result = new JsonResult();
		List<String> userNameList = new ArrayList<String>();

		String listIds  = request.getParameter("listIds");
		if(listIds.contains(",")){
			String[] split = listIds.split(",");
			for(String str : split){
				userNameList.add(str);
			}
		}else{
			userNameList.add(listIds);
		}
		List<AppPersonInfo> AppPersonInfoList = ((AppPersonInfoService)service).getAppPersonInfoByEmailPhone(userNameList);
		for(AppPersonInfo info : AppPersonInfoList){
			AppCustomer appCustomer = appCustomerService.get(info.getCustomerId());
			userNameList.add(appCustomer.getUsername());
		}
		result.setObj(userNameList);
		result.setSuccess(true);
		return result;
	}
	
}
