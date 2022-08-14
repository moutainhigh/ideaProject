/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 17:58:41 
 */
package hry.admin.ico.rulesconfig.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.rulesconfig.model.IcoUpgradeConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 17:58:41 
 */
@Controller
@RequestMapping("/ico/rulesconfig/icoupgradeconfig")
public class IcoUpgradeConfigController extends BaseController<IcoUpgradeConfig, Long> {
	
	@Resource(name = "icoUpgradeConfigService")
	@Override
	public void setService(BaseService<IcoUpgradeConfig, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoUpgradeConfig icoUpgradeConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/rulesconfig/icoupgradeconfigsee");
		mav.addObject("model", icoUpgradeConfig);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,IcoUpgradeConfig icoUpgradeConfig){
		return super.save(icoUpgradeConfig);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		IcoUpgradeConfig icoUpgradeConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/rulesconfig/icoupgradeconfigmodify");
		mav.addObject("model", icoUpgradeConfig);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,IcoUpgradeConfig icoUpgradeConfig){
		return super.update(icoUpgradeConfig);
	}
	

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoUpgradeConfig.class,request);
		return super.findPage(filter);
	}

	@RequestMapping(value = "/findAll")
	@ResponseBody
	public List<IcoUpgradeConfig> findAll(HttpServletRequest request) {
		return super.findAll();
	}
	
	
}
