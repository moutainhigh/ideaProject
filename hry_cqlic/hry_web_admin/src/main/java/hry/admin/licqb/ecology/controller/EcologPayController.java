/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:38:22 
 */
package hry.admin.licqb.ecology.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.ecology.model.EcologPay;

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
 * @Date:        2020-06-05 16:38:22 
 */
@Controller
@RequestMapping("/licqb/ecology/ecologpay")
public class EcologPayController extends BaseController<EcologPay, Long> {
	
	@Resource(name = "ecologPayService")
	@Override
	public void setService(BaseService<EcologPay, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		EcologPay ecologPay = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/ecology/ecologpaysee");
		mav.addObject("model", ecologPay);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,EcologPay ecologPay){
		return super.save(ecologPay);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		EcologPay ecologPay = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/ecology/ecologpaymodify");
		mav.addObject("model", ecologPay);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,EcologPay ecologPay){
		return super.update(ecologPay);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(EcologPay.class,request);
		String orderNum = request.getParameter("orderNum");
		filter.addFilter("orderNum=", orderNum);
		return super.findPage(filter);
	}
	
	
	
	
}
