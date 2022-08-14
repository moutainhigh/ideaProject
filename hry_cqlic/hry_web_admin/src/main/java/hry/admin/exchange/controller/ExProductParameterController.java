/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 15:49:05 
 */
package hry.admin.exchange.controller;


import hry.admin.exchange.model.ExProductParameter;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
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
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 15:49:05 
 */
@Controller
@RequestMapping("/exchange/exproductparameter")
public class ExProductParameterController extends BaseController<ExProductParameter, Long> {
	
	@Resource(name = "exProductParameterService")
	@Override
	public void setService(BaseService<ExProductParameter, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExProductParameter exProductParameter = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exproductparametersee");
		mav.addObject("model", exProductParameter);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExProductParameter exProductParameter){
		return super.save(exProductParameter);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExProductParameter exProductParameter = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exproductconfigmodify");
		mav.addObject("model", exProductParameter);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	@CommonLog(name = "币种交易参数-修改")
	public JsonResult modify(HttpServletRequest request,ExProductParameter exProductParameter){
		return super.update(exProductParameter);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExProductParameter.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
