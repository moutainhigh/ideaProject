/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-09-11 14:46:04 
 */
package hry.admin.exchange.controller;


import hry.admin.exchange.model.KlineData;
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
 * @Date:        2018-09-11 14:46:04 
 */
@Controller
@RequestMapping("/exchange/klinedata")
public class KlineDataController extends BaseController<KlineData, Long> {
	
	@Resource(name = "klineDataService")
	@Override
	public void setService(BaseService<KlineData, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlineData klineData = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/klinedatasee");
		mav.addObject("model", klineData);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlineData klineData){
		return super.save(klineData);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlineData klineData = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/klinedatamodify");
		mav.addObject("model", klineData);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlineData klineData){
		return super.update(klineData);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlineData.class,request);
		filter.addFilter("coinCode_like","B");

		return super.findPage(filter);
	}
	
	
	
	
}
