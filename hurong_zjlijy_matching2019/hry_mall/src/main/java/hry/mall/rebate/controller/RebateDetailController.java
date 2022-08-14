/**
 * Copyright:    
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-11-28 17:24:59 
 */
package hry.mall.rebate.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.mall.rebate.model.RebateDetail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-11-28 17:24:59 
 */
@Controller
@RequestMapping("/rebate/rebatedetail")
public class RebateDetailController extends BaseController<RebateDetail, Long> {
	
	@Resource(name = "rebateDetailService")
	@Override
	public void setService(BaseService<RebateDetail, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		RebateDetail rebateDetail = service.get(id);
		ModelAndView mav = new ModelAndView("/mall/rebate/rebatedetailsee");
		mav.addObject("model", rebateDetail);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,RebateDetail rebateDetail){
		return super.save(rebateDetail);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		RebateDetail rebateDetail = service.get(id);
		ModelAndView mav = new ModelAndView("/mall/rebate/rebatedetailmodify");
		mav.addObject("model", rebateDetail);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,RebateDetail rebateDetail){
		return super.update(rebateDetail);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(RebateDetail.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
