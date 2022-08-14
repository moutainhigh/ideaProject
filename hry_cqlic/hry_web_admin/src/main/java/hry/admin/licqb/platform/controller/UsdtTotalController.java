/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 12:01:59 
 */
package hry.admin.licqb.platform.controller;


import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.licqb.platform.model.UsdtTotal;
import hry.admin.licqb.platform.service.UsdtTotalService;
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
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 12:01:59 
 */
@Controller
@RequestMapping("/licqb/platform/usdttotal")
public class UsdtTotalController extends BaseController<UsdtTotal, Long> {

	@Resource
	private ExDmTransactionService exDmTransactionService;

	@Resource(name = "usdtTotalService")
	@Override
	public void setService(BaseService<UsdtTotal, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		UsdtTotal usdtTotal = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/usdttotalsee");
		mav.addObject("model", usdtTotal);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,UsdtTotal usdtTotal){
		return super.save(usdtTotal);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		UsdtTotal usdtTotal = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/usdttotalmodify");
		mav.addObject("model", usdtTotal);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,UsdtTotal usdtTotal){
		return super.update(usdtTotal);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(UsdtTotal.class,request);
		filter.setOrderby("id desc");
		return super.findPage(filter);
	}


	@RequestMapping(value="/userTotalList")
	@ResponseBody
	public PageResult userTotalList(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(UsdtTotal.class,request);
		return ((UsdtTotalService)service).findPageUserTotalUsdt(filter);
	}

	@RequestMapping(value= "/toUserUsdtDetail")
	@ResponseBody
	public ModelAndView toUserUsdtDetail(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/admin/licqb/platform/userusdtdetail");
		String customerId = request.getParameter("customerId");
		view.addObject("customerId", customerId);
		return view;
	}

	@RequestMapping(value= "/userDetailList")
	@ResponseBody
	public PageResult userDetailList(HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(ExDmTransaction.class,request);
		return ((UsdtTotalService)service).findUserDetailPageList(filter);
	}
}
