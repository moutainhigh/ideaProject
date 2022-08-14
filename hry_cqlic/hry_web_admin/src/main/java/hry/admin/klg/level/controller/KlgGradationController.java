/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:24 
 */
package hry.admin.klg.level.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.level.model.KlgGradation;
import hry.admin.klg.level.model.KlgLevelConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:24 
 */
@Controller
@RequestMapping("/klg/level/klggradation")
public class KlgGradationController extends BaseController<KlgGradation, Long> {
	
	@Resource(name = "klgGradationService")
	@Override
	public void setService(BaseService<KlgGradation, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgGradation klgGradation = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klggradationsee");
		mav.addObject("model", klgGradation);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgGradation klgGradation){
		QueryFilter filter = new QueryFilter(KlgGradation.class);
		filter.addFilter("gradation=", klgGradation.getGradation());
		KlgGradation klgL = service.get(filter);
		if(klgL!=null){
			return new JsonResult().setSuccess(false).setMsg("级差数不能重复");
		}
		return super.save(klgGradation);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgGradation klgGradation = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klggradationmodify");
		mav.addObject("model", klgGradation);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgGradation klgGradation){
		
		KlgGradation klgGrad = service.get(klgGradation.getId());
		if(klgGrad.getGradation().compareTo(klgGradation.getGradation())!=0){
			QueryFilter filter = new QueryFilter(KlgGradation.class);
			filter.addFilter("gradation=", klgGradation.getGradation());
			KlgGradation klgL = service.get(filter);
			if(klgL!=null){
				return new JsonResult().setSuccess(false).setMsg("级差数不能重复");
			}
		}
		
		return super.update(klgGradation);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgGradation.class,request);
		return super.findPage(filter);
	}

	@RequestMapping(value="/findAll")
	@ResponseBody
	public List<KlgGradation> findAll(HttpServletRequest request){
		List<KlgGradation> ls= super.findAll();
		return ls;
	}
	
	
}
