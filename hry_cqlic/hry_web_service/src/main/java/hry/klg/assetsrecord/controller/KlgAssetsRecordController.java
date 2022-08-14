/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 16:16:58 
 */
package hry.klg.assetsrecord.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.klg.assetsrecord.model.KlgAssetsRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 16:16:58 
 */
@Controller
@RequestMapping("/assetsrecord/klgassetsrecord")
public class KlgAssetsRecordController extends BaseController<KlgAssetsRecord, Long> {
	
	@Resource(name = "klgAssetsRecordService")
	@Override
	public void setService(BaseService<KlgAssetsRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgAssetsRecord klgAssetsRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/klg/assetsrecord/klgassetsrecordsee");
		mav.addObject("model", klgAssetsRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgAssetsRecord klgAssetsRecord){
		return super.save(klgAssetsRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgAssetsRecord klgAssetsRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/klg/assetsrecord/klgassetsrecordmodify");
		mav.addObject("model", klgAssetsRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgAssetsRecord klgAssetsRecord){
		return super.update(klgAssetsRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgAssetsRecord.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
