/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:37:17 
 */
package hry.admin.licqb.platform.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.platform.model.LevelConfig;

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
 * @Date:        2019-08-12 17:37:17 
 */
@Controller
@RequestMapping("/licqb/platform/levelconfig")
public class LevelConfigController extends BaseController<LevelConfig, Long> {
	
	@Resource(name = "levelConfigService")
	@Override
	public void setService(BaseService<LevelConfig, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		LevelConfig levelConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/levelconfigsee");
		mav.addObject("model", levelConfig);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,LevelConfig levelConfig){
		return super.save(levelConfig);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		LevelConfig levelConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/levelconfigmodify");
		mav.addObject("model", levelConfig);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,LevelConfig levelConfig){
		return super.update(levelConfig);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(LevelConfig.class,request);
		filter.setOrderby("sort asc");
		return super.findPage(filter);
	}
	
	
	
	
}
