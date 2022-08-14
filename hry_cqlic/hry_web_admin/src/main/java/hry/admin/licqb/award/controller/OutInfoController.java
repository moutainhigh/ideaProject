/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:49:07 
 */
package hry.admin.licqb.award.controller;


import hry.admin.licqb.award.service.OutInfoService;
import hry.admin.licqb.platform.model.LevelConfig;
import hry.admin.licqb.platform.service.LevelConfigService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.award.model.OutInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:49:07 
 */
@Controller
@RequestMapping("/licqb/award/outinfo")
public class OutInfoController extends BaseController<OutInfo, Long> {

	@Resource
	private LevelConfigService levelConfigService;

	@Resource(name = "outInfoService")
	@Override
	public void setService(BaseService<OutInfo, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		OutInfo outInfo = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/award/outinfosee");
		mav.addObject("model", outInfo);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,OutInfo outInfo){
		return super.save(outInfo);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		OutInfo outInfo = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/award/outinfomodify");
		mav.addObject("model", outInfo);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,OutInfo outInfo){
		return super.update(outInfo);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OutInfo.class,request);
		return super.findPage(filter);
	}
	
	/**
	 * 查询有效用户业绩
	 * */
	@RequestMapping(value="/findUserList")
	@ResponseBody
	public PageResult findUserList(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OutInfo.class,request);
		return ((OutInfoService)service).findUserList(filter);
	}

	@RequestMapping(value="/toSonUserFtl")
	public ModelAndView toSonUserFtl(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/admin/licqb/award/sonUserList");
		String customerId = request.getParameter("customerId");
		// 查询等级
		List<LevelConfig> configList = levelConfigService.findAll();
		view.addObject("customerId",customerId);
		view.addObject("configList",configList);
		return view;
	}

	@RequestMapping(value="/toUserAssetFtl")
	public ModelAndView toUserAssetFtl(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/admin/licqb/award/userAssetFtl");
		String customerId = request.getParameter("customerId");
		view.addObject("customerId", customerId);
		return view;
	}

	/**
	 * 查询用户资产
	 * */
	@RequestMapping(value="/findUserAsset")
	@ResponseBody
	public PageResult findUserAsset(HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(OutInfo.class,request);
		return ((OutInfoService)service).findUserAsset(filter);
	}


	/**
	 * 查询用户的伞下用户数
	 * */
	@RequestMapping(value="/findSonUserList")
	@ResponseBody
	public PageResult findSonUserList(HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(OutInfo.class,request);
		return ((OutInfoService)service).findSonUserList(filter);
	}

}
