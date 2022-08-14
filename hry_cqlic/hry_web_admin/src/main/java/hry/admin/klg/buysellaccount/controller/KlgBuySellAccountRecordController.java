/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-22 17:02:55 
 */
package hry.admin.klg.buysellaccount.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.klg.buysellaccount.model.KlgBuySellAccountRecord;
import hry.admin.klg.buysellaccount.service.KlgBuySellAccountRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-22 17:02:55 
 */
@Controller
@RequestMapping("/klg/buysellaccount/klgbuysellaccountrecord")
public class KlgBuySellAccountRecordController extends BaseController<KlgBuySellAccountRecord, Long> {
	
	@Resource(name = "klgBuySellAccountRecordService")
	@Override
	public void setService(BaseService<KlgBuySellAccountRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgBuySellAccountRecord klgBuySellAccountRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/buysellaccount/klgbuysellaccountrecordsee");
		mav.addObject("model", klgBuySellAccountRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgBuySellAccountRecord klgBuySellAccountRecord){
		return super.save(klgBuySellAccountRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgBuySellAccountRecord klgBuySellAccountRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/buysellaccount/klgbuysellaccountrecordmodify");
		mav.addObject("model", klgBuySellAccountRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgBuySellAccountRecord klgBuySellAccountRecord){
		return super.update(klgBuySellAccountRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		/*QueryFilter filter = new QueryFilter(KlgBuySellAccountRecord.class,request);
		return super.findPage(filter);*/
		QueryFilter filter = new QueryFilter(KlgBuySellAccountRecord.class, request);
        PageResult findPageBySql = ((KlgBuySellAccountRecordService) service).findPageBySql(filter);
        return findPageBySql;
	}
	
	
	
	
}
