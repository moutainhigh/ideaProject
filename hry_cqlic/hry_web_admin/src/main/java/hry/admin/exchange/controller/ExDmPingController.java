/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-21 14:45:05 
 */
package hry.admin.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.ExDmPing;

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
 * @Date:        2018-06-21 14:45:05 
 */
@Controller
@RequestMapping("/exchange/exdmping")
public class ExDmPingController extends BaseController<ExDmPing, Long> {
	
	@Resource(name = "exDmPingService")
	@Override
	public void setService(BaseService<ExDmPing, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExDmPing exDmPing = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exdmpingsee");
		mav.addObject("model", exDmPing);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExDmPing exDmPing){
		return super.save(exDmPing);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExDmPing exDmPing = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exdmpingmodify");
		mav.addObject("model", exDmPing);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExDmPing exDmPing){
		return super.update(exDmPing);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExDmPing.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
