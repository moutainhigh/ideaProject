/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-17 10:48:13 
 */
package hry.admin.ico.upgraderecord.controller;


import hry.admin.ico.upgraderecord.service.IcoUpgradeRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.upgraderecord.model.IcoUpgradeRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-17 10:48:13 
 */
@Controller
@RequestMapping("/ico/upgraderecord/icoupgraderecord")
public class IcoUpgradeRecordController extends BaseController<IcoUpgradeRecord, Long> {
	
	@Resource(name = "icoUpgradeRecordService")
	@Override
	public void setService(BaseService<IcoUpgradeRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoUpgradeRecord icoUpgradeRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/upgraderecord/icoupgraderecordsee");
		mav.addObject("model", icoUpgradeRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,IcoUpgradeRecord icoUpgradeRecord){
		return super.save(icoUpgradeRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		IcoUpgradeRecord icoUpgradeRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/upgraderecord/icoupgraderecordmodify");
		mav.addObject("model", icoUpgradeRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,IcoUpgradeRecord icoUpgradeRecord){
		return super.update(icoUpgradeRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoUpgradeRecord.class,request);
		return super.findPage(filter);
	}

	@RequestMapping(value="/findPageBySql")
	@ResponseBody
	public PageResult findPageBySql(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoUpgradeRecord.class,request);
		PageResult findPageBySql = ((IcoUpgradeRecordService) service).findPageBySql(filter);
		return findPageBySql;
	}
	
}
