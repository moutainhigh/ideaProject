/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-12-21 18:25:14 
 */
package hry.otc.releaseAdvertisement.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.otc.releaseAdvertisement.model.OtcCompletionRate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-12-21 18:25:14 
 */
@Controller
@RequestMapping("/releaseAdvertisement/otccompletionrate")
public class OtcCompletionRateController extends BaseController<OtcCompletionRate, Long> {
	
	@Resource(name = "otcCompletionRateService")
	@Override
	public void setService(BaseService<OtcCompletionRate, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		OtcCompletionRate otcCompletionRate = service.get(id);
		ModelAndView mav = new ModelAndView("/otc/releaseAdvertisement/otccompletionratesee");
		mav.addObject("model", otcCompletionRate);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,OtcCompletionRate otcCompletionRate){
		return super.save(otcCompletionRate);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		OtcCompletionRate otcCompletionRate = service.get(id);
		ModelAndView mav = new ModelAndView("/otc/releaseAdvertisement/otccompletionratemodify");
		mav.addObject("model", otcCompletionRate);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,OtcCompletionRate otcCompletionRate){
		return super.update(otcCompletionRate);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OtcCompletionRate.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
