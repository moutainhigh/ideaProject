/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:35:32 
 */
package hry.admin.licqb.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.exchange.model.ExchangeItem;

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
 * @Date:        2020-12-17 16:35:32 
 */
@Controller
@RequestMapping("/licqb/exchange/exchangeitem")
public class ExchangeItemController extends BaseController<ExchangeItem, Long> {
	
	@Resource(name = "exchangeItemService")
	@Override
	public void setService(BaseService<ExchangeItem, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExchangeItem exchangeItem = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/exchange/exchangeitemsee");
		mav.addObject("model", exchangeItem);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExchangeItem exchangeItem){
		return super.save(exchangeItem);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExchangeItem exchangeItem = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/exchange/exchangeitemmodify");
		mav.addObject("model", exchangeItem);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExchangeItem exchangeItem){
		return super.update(exchangeItem);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExchangeItem.class,request);
		filter.setOrderby("status ASC");
		return super.findPage(filter);
	}
	
	
	
	
}
