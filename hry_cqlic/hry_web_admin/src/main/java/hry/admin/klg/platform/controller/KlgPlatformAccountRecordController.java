/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:36:13 
 */
package hry.admin.klg.platform.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.platform.model.KlgPlatformAccountRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:36:13 
 */
@Controller
@RequestMapping("/klg/platform/klgplatformaccountrecord")
public class KlgPlatformAccountRecordController extends BaseController<KlgPlatformAccountRecord, Long> {
	
	@Resource(name = "klgPlatformAccountRecordService")
	@Override
	public void setService(BaseService<KlgPlatformAccountRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgPlatformAccountRecord klgPlatformAccountRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/platform/klgplatformaccountrecordsee");
		mav.addObject("model", klgPlatformAccountRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgPlatformAccountRecord klgPlatformAccountRecord){
		return super.save(klgPlatformAccountRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgPlatformAccountRecord klgPlatformAccountRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/platform/klgplatformaccountrecordmodify");
		mav.addObject("model", klgPlatformAccountRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgPlatformAccountRecord klgPlatformAccountRecord){
		return super.update(klgPlatformAccountRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request,String startTime,String endTime,
						   String accountType,String serialNumber,String remark){
		QueryFilter filter = new QueryFilter(KlgPlatformAccountRecord.class,request);
		if(StringUtil.isNull(startTime)){
			filter.addFilter("created>=",startTime);
		}if(StringUtil.isNull(endTime)){
			filter.addFilter("created<=",endTime);
		}if(StringUtil.isNull(accountType)){
			filter.addFilter("accountId=",accountType);
		}if(StringUtil.isNull(serialNumber)){
			filter.addFilter("serialNumber=",serialNumber);
		}if(StringUtil.isNull(remark)){
			filter.addFilter("remark_like","%"+remark+"%");
		}
		filter.setOrderby("created desc");
		return super.findPage(filter);
	}
	
	
	
	
}
