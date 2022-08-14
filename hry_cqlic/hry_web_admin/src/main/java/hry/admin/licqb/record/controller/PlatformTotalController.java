/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-04-02 11:10:41 
 */
package hry.admin.licqb.record.controller;


import hry.admin.licqb.record.model.PlatformTotal;
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
 * @Date:        2020-04-02 11:10:41 
 */
@Controller
@RequestMapping("/licqb/record/platformtotal")
public class PlatformTotalController extends BaseController<PlatformTotal, Long> {
	
	@Resource(name = "platformTotalService")
	@Override
	public void setService(BaseService<PlatformTotal, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		PlatformTotal platformTotal = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/record/platformtotalsee");
		mav.addObject("model", platformTotal);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request, PlatformTotal platformTotal){
		return super.save(platformTotal);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		PlatformTotal platformTotal = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/record/platformtotalmodify");
		mav.addObject("model", platformTotal);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request, PlatformTotal platformTotal){
		return super.update(platformTotal);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(PlatformTotal.class,request);
		filter.setOrderby("id DESC");
		return super.findPage(filter);
	}
	
	
	
	
}
