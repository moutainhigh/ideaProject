/**
 * Copyright:    
 * @author:      hec
 * @version:     V1.0 
 * @Date:        2018-12-27 15:00:02 
 */
package hry.admin.contract.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.contract.model.contractCoinConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      hec
 * @version:     V1.0 
 * @Date:        2018-12-27 15:00:02 
 */
@Controller
@RequestMapping("/contract/contractcoinconfig")
public class contractCoinConfigController extends BaseController<contractCoinConfig, Long> {
	
	@Resource(name = "contractCoinConfigService")
	@Override
	public void setService(BaseService<contractCoinConfig, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		contractCoinConfig contractCoinConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/contract/contractcoinconfigsee");
		mav.addObject("model", contractCoinConfig);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,contractCoinConfig contractCoinConfig){
		return super.save(contractCoinConfig);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		contractCoinConfig contractCoinConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/contract/contractcoinconfigmodify");
		mav.addObject("model", contractCoinConfig);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,contractCoinConfig contractCoinConfig){
		return super.update(contractCoinConfig);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(contractCoinConfig.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
