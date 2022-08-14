/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 14:34:44 
 */
package hry.admin.web.controller;


import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.web.model.AppBanner;
import hry.admin.web.service.AppBannerService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 14:34:44 
 */
@Controller
@RequestMapping("/web/appbanner")
public class AppBannerController extends BaseController<AppBanner, Long> {
	
	@Resource(name = "appBannerService")
	@Override
	public void setService(BaseService<AppBanner, Long> service) {
		super.service = service;
	}

	@Resource
	private AppDicService appDicService;
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppBanner appBanner = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appbannersee");
		mav.addObject("model", appBanner);
		return mav;
	}

	@RequestMapping(value="/toAdd/{type}")
	public ModelAndView toAdd(@PathVariable String type){
		ModelAndView mav = new ModelAndView("/admin/web/appbanneradd");
		mav.addObject("toFlag", type);
		return mav;
	}

	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(AppBanner appBanner,HttpServletRequest request){
		String picType = request.getParameter("whereUse1");
		if(!StringUtils.isEmpty(picType)){
			appBanner.setWhereUse(picType);
		}
		if(appBanner.getWhereUse().equals("banner")){
			appBanner.setWhereUse("1");
		}
		if(!("1".equals(appBanner.getWhereUse()) ||  "6".equals(appBanner.getWhereUse()))){
			QueryFilter queryFilter = new QueryFilter(AppBanner.class);
			queryFilter.addFilter("whereUse=",appBanner.getWhereUse());
			queryFilter.addFilter("langCode=",appBanner.getLangCode());
			List<AppBanner> list = service.find(queryFilter);
			if(null != list && list.size()>0){
				return new JsonResult().setSuccess(false).setMsg("该类型不可重复添加");
			}
		}


		JsonResult jsonResult = super.save(appBanner);
		((AppBannerService)service).initCache();
		return jsonResult;
	}



	@RequestMapping(value="/modifysee/{type}/{id}")
	public ModelAndView modifysee(@PathVariable String type, @PathVariable Long id){
		AppBanner appBanner = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appbannermodify");
		mav.addObject("toFlag", type);
		mav.addObject("model", appBanner);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppBanner appBanner){
		String picType = request.getParameter("whereUse1");
		if(!StringUtils.isEmpty(picType)){
			appBanner.setWhereUse(picType);
		}
		if(appBanner.getWhereUse().equals("banner")){
			appBanner.setWhereUse("1");
		}
		JsonResult jsonResult = super.update(appBanner);
		((AppBannerService)service).initCache();
		return  jsonResult;
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		JsonResult jsonResult = super.deleteBatch(ids);
		((AppBannerService)service).initCache();
		return jsonResult;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		String langCode = request.getParameter("langCode");
		String whereUse = request.getParameter("whereUse");
		QueryFilter filter = new QueryFilter(AppBanner.class,request);
		filter.addFilter("langCode=", langCode);
		if ("banner".equals(whereUse)) {
			filter.addFilter("whereUse=", "1");
		} else {
			filter.addFilter("whereUse_NEQ", "1");
		}
		filter.setOrderby("sort");
		return super.findPage(filter);
	}

	@RequestMapping(value="/toList/{type}")
	public ModelAndView toList(@PathVariable String type) {
		ModelAndView mav = new ModelAndView("/admin/web/appbannerlist");
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		List<AppDic> keyList = appDicService.find(filter);
		mav.addObject("keyList", keyList);
		mav.addObject("toFlag", type);
		return mav;
	}

	@RequestMapping(value="/whereUseList/{type}")
	@ResponseBody
	public List<AppDic> whereUseList(@PathVariable String type){
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "whereuse");
		if ("banner".equals(type)) {
			filter.addFilter("value=", 1);
		} else {
			filter.addFilter("value_NEQ", 1);
		}
		return appDicService.find(filter);
	}

}
