/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:31:26 
 */
package hry.admin.klg.prizedraw.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.klg.prizedraw.model.KlPrizedrawCustomer;
import hry.admin.klg.prizedraw.service.KlPrizedrawCustomerService;
import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:31:26 
 */
@Controller
@RequestMapping("/klg/prizedraw/klprizedrawcustomer")
public class KlPrizedrawCustomerController extends BaseController<KlPrizedrawCustomer, Long> {
	
	@Resource(name = "klPrizedrawCustomerService")
	@Override
	public void setService(BaseService<KlPrizedrawCustomer, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlPrizedrawCustomer klPrizedrawCustomer = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/prizedraw/klprizedrawcustomersee");
		mav.addObject("model", klPrizedrawCustomer);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlPrizedrawCustomer klPrizedrawCustomer){
		return super.save(klPrizedrawCustomer);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlPrizedrawCustomer klPrizedrawCustomer = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/prizedraw/klprizedrawcustomermodify");
		mav.addObject("model", klPrizedrawCustomer);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlPrizedrawCustomer klPrizedrawCustomer){
		return super.update(klPrizedrawCustomer);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		/*QueryFilter filter = new QueryFilter(KlPrizedrawCustomer.class,request);
		return super.findPage(filter);*/
		QueryFilter filter = new QueryFilter(KlgBuyTransaction.class, request);
        PageResult findPageBySql = ((KlPrizedrawCustomerService) service).findPageBySql(filter);
        return findPageBySql;
	}
	
	
	
	
}
