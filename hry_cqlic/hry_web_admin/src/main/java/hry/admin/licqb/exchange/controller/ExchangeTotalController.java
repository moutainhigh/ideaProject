/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:37:15 
 */
package hry.admin.licqb.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.exchange.model.ExchangeTotal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:37:15 
 */
@Controller
@RequestMapping("/licqb/exchange/exchangetotal")
public class ExchangeTotalController extends BaseController<ExchangeTotal, Long> {
	
	@Resource(name = "exchangeTotalService")
	@Override
	public void setService(BaseService<ExchangeTotal, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExchangeTotal exchangeTotal = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/exchange/exchangetotalsee");
		mav.addObject("model", exchangeTotal);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExchangeTotal exchangeTotal){
		return super.save(exchangeTotal);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExchangeTotal exchangeTotal = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/exchange/exchangetotalmodify");
		mav.addObject("model", exchangeTotal);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExchangeTotal exchangeTotal){
		return super.update(exchangeTotal);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExchangeTotal.class,request);
		filter.setOrderby("id DESC");
		return super.findPage(filter);
	}
	
	
	
	
}
