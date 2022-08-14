/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-03 16:36:19 
 */
package hry.admin.klg.forecast.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.forecast.model.KlgForecast;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-03 16:36:19 
 */
@Controller
@RequestMapping("/klg/forecast/klgforecast")
public class KlgForecastController extends BaseController<KlgForecast, Long> {
	
	@Resource(name = "klgForecastService")
	@Override
	public void setService(BaseService<KlgForecast, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgForecast klgForecast = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/forecast/klgforecastsee");
		mav.addObject("model", klgForecast);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgForecast klgForecast){
		return super.save(klgForecast);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgForecast klgForecast = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/forecast/klgforecastmodify");
		mav.addObject("model", klgForecast);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgForecast klgForecast){
		return super.update(klgForecast);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgForecast.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
