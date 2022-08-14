/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-10-08 13:45:53 
 */
package hry.admin.index.controller;


import hry.admin.index.model.AppYesterdayData;
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
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-10-08 13:45:53 
 */
@Controller
@RequestMapping("/index/appyesterdaydata")
public class AppYesterdayDataController extends BaseController<AppYesterdayData, Long> {
	
	@Resource(name = "appYesterdayDataService")
	@Override
	public void setService(BaseService<AppYesterdayData, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppYesterdayData appYesterdayData = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/index/appyesterdaydatasee");
		mav.addObject("model", appYesterdayData);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppYesterdayData appYesterdayData){
		return super.save(appYesterdayData);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppYesterdayData appYesterdayData = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/index/appyesterdaydatamodify");
		mav.addObject("model", appYesterdayData);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppYesterdayData appYesterdayData){
		return super.update(appYesterdayData);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppYesterdayData.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
