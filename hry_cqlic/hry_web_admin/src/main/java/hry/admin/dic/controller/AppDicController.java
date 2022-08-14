/**
 * Copyright:    互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-03-01 14:17:02 
 */
package hry.admin.dic.controller;


import com.alibaba.fastjson.JSONObject;
import hry.admin.HryCache;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.web.service.AppLanguageService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-03-01 14:17:02 
 */
@Controller
@RequestMapping("/dic/appdic")
public class AppDicController extends BaseController<AppDic, Long> {
	
	@Resource(name = "appDicService")
	@Override
	public void setService(BaseService<AppDic, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	
	public ModelAndView see(@PathVariable Long id){
		AppDic sysDic = service.get(id);
		ModelAndView mav = new ModelAndView("admin/dic/appdicsee");
		mav.addObject("model", sysDic);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	
	public JsonResult add(HttpServletRequest request,AppDic sysDic){
		
		QueryFilter queryFilter = new QueryFilter(AppDic.class);
		queryFilter.addFilter("mkey=", sysDic.getMkey());
		AppDic sysDic2 = service.get(queryFilter);
		if(sysDic2!=null){
			return new JsonResult().setMsg("标识已存在！");
		}
		//如果value 为空  设置value=mkey
		if(StringUtils.isEmpty(sysDic.getValue())){
			sysDic.setValue(sysDic.getMkey());
		}

		return super.save(sysDic);
	}
	
	@RequestMapping(value="/addItem")
	@ResponseBody
	
	public JsonResult addItem(HttpServletRequest request,AppDic sysDic){
		String pkey = request.getParameter("pkey");
		QueryFilter queryFilter = new QueryFilter(AppDic.class);
		queryFilter.addFilter("mkey=", sysDic.getMkey());
		AppDic sysDic2 = service.get(queryFilter);
		if(sysDic2!=null){
			return new JsonResult().setMsg("标识已存在！");
		}
		
		//生成mkey
		sysDic.setMkey(sysDic.getPkey()+"_"+RandomStringUtils.random(6, false, true));

		//删除缓存
		HryCache.cache_appdic.remove(sysDic.getPkey());
		//更新redis缓存
		((AppDicService)service).flushRedis();
		if ("sysLanguage".equals(pkey)) {
			JsonResult result = super.save(sysDic);
			if (result.getSuccess()) {
				// 多语种操作
				AppLanguageService appLanguageService = SpringUtil.getBean("appLanguageService");
				// 初始化多语种-以简体中文为基础，向其他语种同步数据
				appLanguageService.synchronizedLangData();
			}
			return result;
		} else {
			return super.save(sysDic);
		}
	}
	
	@RequestMapping(value="/modifysee/{id}")
	
	public ModelAndView modifysee(@PathVariable Long id){
		AppDic sysDic = service.get(id);
		ModelAndView mav = new ModelAndView("admin/dic/appdicmodify");
		mav.addObject("model", sysDic);
		return mav;
	}
	
	
	@RequestMapping(value="/modifyGroupSee/{id}")
	
	public ModelAndView modifyGroupSee(@PathVariable Long id){
		AppDic sysDic = service.get(id);
		ModelAndView mav = new ModelAndView("admin/dic/appdicmodify2group");
		mav.addObject("model", sysDic);
		return mav;
	}
	
	@RequestMapping(value="/modifyItemSee/{id}")
	
	public ModelAndView modifyItemSee(@PathVariable Long id){
		AppDic sysDic = service.get(id);
		ModelAndView mav = new ModelAndView("admin/dic/appdicmodify2item");
		mav.addObject("model", sysDic);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	
	public JsonResult modify(HttpServletRequest request,AppDic appDic){

		JsonResult update = super.update(appDic);
		AppDic sysDic = service.get(appDic.getId());
		//删除缓存
		HryCache.cache_appdic.remove(sysDic.getPkey());
		//更新redis缓存
		((AppDicService)service).flushRedis();
		return  update;
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		JsonResult jsonResult = new JsonResult();
		if(!StringUtils.isEmpty(ids)){
			String[] split = org.apache.commons.lang3.StringUtils.split(ids, ",");
			AppDic appDic = null;
			for(String id  : split){
				appDic = service.get(Long.valueOf(id));
				super.delete(Long.valueOf(id));
			}
			//删除缓存
			HryCache.cache_appdic.remove(appDic.getPkey());
			//更新redis缓存
			((AppDicService)service).flushRedis();
			jsonResult.setSuccess(true);
		}

		return jsonResult;
	}
	
	@RequestMapping(value="/removeGroup")
	@ResponseBody
	
	public JsonResult removeGroup(Long id){
		AppDic sysDic = service.get(id);
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", sysDic.getMkey());
		AppDic sysDic2 = service.get(filter);
		
		if(sysDic2!=null){
			return new JsonResult().setSuccess(false).setMsg("有子项不能删除!");
		}
		
		return super.delete(id);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppDic.class,request);
		String pkey = request.getParameter("pkey") ;
		if(!StringUtils.isEmpty(pkey)){
			filter.addFilter("pkey=", pkey);
		}

		PageResult page = super.findPage(filter);
		return super.findPage(filter);
	}

	@RequestMapping(value="/findkeyAndVal")
	@ResponseBody

	public List<AppDic> findkeyAndVal(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppDic.class,request);
		String pkey = request.getParameter("pkey") ;
		if(!StringUtils.isEmpty(pkey)){
			filter.addFilter("pkey=", pkey);
		}

		List<AppDic> appDics = service.find(filter);
		return appDics;
	}
	
	@RequestMapping(value="/listTree")
	@ResponseBody
	public List<AppDic> listTree(HttpServletRequest request){
		return super.findAll();
	}
	
	@RequestMapping(value="/getKey")
	@ResponseBody
	public List<JSONObject> getKey(String key){
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", key);
		List<AppDic> find = find(filter);
		List<JSONObject> list = new ArrayList<JSONObject>();
		if(find!=null&&find.size()>0){
			for(AppDic dic : find){
				JSONObject object = new JSONObject();
				object.put("value", dic.getValue());
				object.put("text", dic.getName());
				list.add(object);
			}
		}
		return list;
	}
	
	
}
