/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:31:15 
 */
package hry.admin.klg.level.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.level.model.KlgTeamlevel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:31:15 
 */
@Controller
@RequestMapping("/klg/level/klgteamlevel")
public class KlgTeamlevelController extends BaseController<KlgTeamlevel, Long> {
	
	@Resource(name = "klgTeamlevelService")
	@Override
	public void setService(BaseService<KlgTeamlevel, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgTeamlevel klgTeamlevel = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klgteamlevelsee");
		mav.addObject("model", klgTeamlevel);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgTeamlevel klgTeamlevel){
		return super.save(klgTeamlevel);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgTeamlevel klgTeamlevel = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klgteamlevelmodify");
		mav.addObject("model", klgTeamlevel);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgTeamlevel klgTeamlevel){
		return super.update(klgTeamlevel);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgTeamlevel.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
