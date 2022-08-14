/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-20 09:55:15 
 */
package hry.admin.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.AppAccountRecord;

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
 * @Date:        2018-07-20 09:55:15 
 */
@Controller
@RequestMapping("/exchange/appaccountrecord")
public class AppAccountRecordController extends BaseController<AppAccountRecord, Long> {
	
	@Resource(name = "appAccountRecordService")
	@Override
	public void setService(BaseService<AppAccountRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppAccountRecord appAccountRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/appaccountrecordsee");
		mav.addObject("model", appAccountRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppAccountRecord appAccountRecord){
		return super.save(appAccountRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppAccountRecord appAccountRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/appaccountrecordmodify");
		mav.addObject("model", appAccountRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppAccountRecord appAccountRecord){
		return super.update(appAccountRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppAccountRecord.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
