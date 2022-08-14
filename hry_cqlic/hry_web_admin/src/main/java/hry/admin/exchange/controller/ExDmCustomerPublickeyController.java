/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:54:15 
 */
package hry.admin.exchange.controller;


import hry.admin.exchange.service.ExDmCustomerPublickeyService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.ExDmCustomerPublickey;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:54:15 
 */
@Controller
@RequestMapping("/exchange/exdmcustomerpublickey")
public class ExDmCustomerPublickeyController extends BaseController<ExDmCustomerPublickey, Long> {
	private final static Logger log = Logger.getLogger(ExDmCustomerPublickeyController.class);
	@Resource(name = "exDmCustomerPublickeyService")
	@Override
	public void setService(BaseService<ExDmCustomerPublickey, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExDmCustomerPublickey exDmCustomerPublickey = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exdmcustomerpublickeysee");
		mav.addObject("model", exDmCustomerPublickey);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExDmCustomerPublickey exDmCustomerPublickey){
		return super.save(exDmCustomerPublickey);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExDmCustomerPublickey exDmCustomerPublickey = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exdmcustomerpublickeymodify");
		mav.addObject("model", exDmCustomerPublickey);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExDmCustomerPublickey exDmCustomerPublickey){
		return super.update(exDmCustomerPublickey);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		long s1=System.currentTimeMillis();
		QueryFilter filter = new QueryFilter(ExDmCustomerPublickey.class,request);
		PageResult page = ((ExDmCustomerPublickeyService) service).findPageBySql(filter);
		log.info("会员货币账户管理所用时间 ：" + (System.currentTimeMillis()-s1) + "毫秒");
		return page;
	}
	
	
	
	
}
