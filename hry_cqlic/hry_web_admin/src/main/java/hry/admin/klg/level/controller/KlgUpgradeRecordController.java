/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-05-17 13:37:26 
 */
package hry.admin.klg.level.controller;


import hry.admin.klg.level.service.KlgUpgradeRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.level.model.KlgUpgradeRecord;

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
 * @Date:        2019-05-17 13:37:26 
 */
@Controller
@RequestMapping("/klg/level/klgupgraderecord")
public class KlgUpgradeRecordController extends BaseController<KlgUpgradeRecord, Long> {
	
	@Resource(name = "klgUpgradeRecordService")
	@Override
	public void setService(BaseService<KlgUpgradeRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgUpgradeRecord klgUpgradeRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klgupgraderecordsee");
		mav.addObject("model", klgUpgradeRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgUpgradeRecord klgUpgradeRecord){
		return super.save(klgUpgradeRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgUpgradeRecord klgUpgradeRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klgupgraderecordmodify");
		mav.addObject("model", klgUpgradeRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgUpgradeRecord klgUpgradeRecord){
		return super.update(klgUpgradeRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgUpgradeRecord.class,request);
		PageResult findPageBySql = ((KlgUpgradeRecordService) service).findPageBySql(filter);
		return findPageBySql;
	}

}
