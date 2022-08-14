/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-02 11:44:17 
 */
package hry.admin.exchange.controller;


import hry.admin.customer.model.AppCustomer;
import hry.admin.exchange.service.AppCoinRewardRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.AppCoinRewardRecord;

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
 * @Date:        2018-07-02 11:44:17 
 */
@Controller
@RequestMapping("/exchange/appcoinrewardrecord")
public class AppCoinRewardRecordController extends BaseController<AppCoinRewardRecord, Long> {
	
	@Resource(name = "appCoinRewardRecordService")
	@Override
	public void setService(BaseService<AppCoinRewardRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppCoinRewardRecord appCoinRewardRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/appcoinrewardrecordsee");
		mav.addObject("model", appCoinRewardRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppCoinRewardRecord appCoinRewardRecord){
		return super.save(appCoinRewardRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppCoinRewardRecord appCoinRewardRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/appcoinrewardrecordmodify");
		mav.addObject("model", appCoinRewardRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppCoinRewardRecord appCoinRewardRecord){
		return super.update(appCoinRewardRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCustomer.class, request);
		PageResult findPageBySql = ((AppCoinRewardRecordService)service).findPageBySql(filter);
		return findPageBySql;
	}
	
	
	
	
}
