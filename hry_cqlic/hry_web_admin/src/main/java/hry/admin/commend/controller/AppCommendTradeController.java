/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:18 
 */
package hry.admin.commend.controller;


import hry.admin.commend.model.AppCommendTrade;
import hry.admin.commend.service.AppCommendTradeService;
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
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:18 
 */
@Controller
@RequestMapping("/commend/appcommendtrade")
public class AppCommendTradeController extends BaseController<AppCommendTrade, Long> {
	
	@Resource(name = "appCommendTradeService")
	@Override
	public void setService(BaseService<AppCommendTrade, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppCommendTrade appCommendTrade = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendtradesee");
		mav.addObject("model", appCommendTrade);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppCommendTrade appCommendTrade){
		return super.save(appCommendTrade);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppCommendTrade appCommendTrade = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendtrademodify");
		mav.addObject("model", appCommendTrade);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppCommendTrade appCommendTrade){
		return super.update(appCommendTrade);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCommendTrade.class, request);
		PageResult pageResult = ((AppCommendTradeService)service).findPageBySql(filter);
		return pageResult;
	}
	
	
	
	
}
