/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 19:24:34 
 */
package hry.admin.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.ExCoinFee;

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
 * @Date:        2018-10-29 19:24:34 
 */
@Controller
@RequestMapping("/exchange/excoinfee")
public class ExCoinFeeController extends BaseController<ExCoinFee, Long> {
	
	@Resource(name = "exCoinFeeService")
	@Override
	public void setService(BaseService<ExCoinFee, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExCoinFee exCoinFee = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/excoinfeesee");
		mav.addObject("model", exCoinFee);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExCoinFee exCoinFee){
		return super.save(exCoinFee);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExCoinFee exCoinFee = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/excoinfeemodify");
		mav.addObject("model", exCoinFee);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExCoinFee exCoinFee){
		return super.update(exCoinFee);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExCoinFee.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
