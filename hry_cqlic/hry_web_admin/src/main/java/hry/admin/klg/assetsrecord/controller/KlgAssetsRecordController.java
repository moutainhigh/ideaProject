/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 15:54:03 
 */
package hry.admin.klg.assetsrecord.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.klg.assetsrecord.model.KlgAssetsRecord;
import hry.admin.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 15:54:03 
 */
@Controller
@RequestMapping("/klg/assetsrecord/klgassetsrecord")
public class KlgAssetsRecordController extends BaseController<KlgAssetsRecord, Long> {
	
	@Resource(name = "klgAssetsRecordService")
	@Override
	public void setService(BaseService<KlgAssetsRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgAssetsRecord klgAssetsRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/assetsrecord/klgassetsrecordsee");
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
		ModelAndView mav = new ModelAndView("/admin/klg/assetsrecord/klgassetsrecordmodify");
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
		/*QueryFilter filter = new QueryFilter(KlgAssetsRecord.class,request);
		return super.findPage(filter);*/
		QueryFilter filter = new QueryFilter(KlgBuyTransaction.class, request);
        PageResult findPageBySql = ((KlgAssetsRecordService) service).findPageBySql(filter);
        return findPageBySql;
	}
	
	
	
	
}
