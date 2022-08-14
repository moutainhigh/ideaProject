/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 14:46:47 
 */
package hry.admin.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.HryCache;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.web.model.AppArticle;
import hry.admin.web.model.AppCategory;
import hry.admin.web.model.AppCategoryLangname;
import hry.admin.web.service.AppArticleService;
import hry.admin.web.service.AppCategoryLangnameService;
import hry.admin.web.service.AppCategoryService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.base.MethodName;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Copyright:   互融云
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 14:46:47 
 */
@Controller
@RequestMapping("/web/appcategory")
public class AppCategoryController extends BaseController<AppCategory, Long> {
	
	@Resource(name = "appCategoryService")
	@Override
	public void setService(BaseService<AppCategory, Long> service) {
		super.service = service;
	}
	
	@Resource
	private AppDicService appDicService;

	@Resource
	private AppCategoryLangnameService langnameService;

	@Resource
	private AppArticleService appArticleService;


	/**
	 * 跳转到单页面内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toSinglePage/{id}")
	public ModelAndView toSinglePage(@PathVariable Long id){
		ModelAndView mav = new ModelAndView("/admin/web/appcategorysinglepagemodify");
		// 查询该单页面的基本信息
		QueryFilter filter = new QueryFilter(AppCategoryLangname.class);
		filter.addFilter("categoryId=", id);
		filter.addFilter("dicKey=", "zh_CN");
		AppCategoryLangname langname = langnameService.get(filter);
		mav.addObject("categoryId", langname.getCategoryId());
		mav.addObject("langName", langname.getLangName());
		// 查询该单页面对应的语种信息内容
		QueryFilter lfilter = new QueryFilter(AppCategoryLangname.class);
		lfilter.addFilter("categoryId=", id);
		List<AppCategoryLangname> langnameList = langnameService.find(lfilter);
		mav.addObject("content", null);
		if (langnameList != null && langnameList.size() > 0) {
			mav.addObject("content", langnameList);
		}
		return mav;
	}

	@MethodName(name = "保存单页面内容数据")
	@RequestMapping("/saveLangName")
	@ResponseBody
	public JsonResult saveLangName(HttpServletRequest request){
		String categoryId = request.getParameter("categoryId");
		String jsonData = request.getParameter("jsonData");
		JsonResult result = new JsonResult();
		try {
			if (!StringUtils.isEmpty(jsonData) && !StringUtils.isEmpty(categoryId)) {
				// 根据类别查询语种信息
				QueryFilter queryFilter = new QueryFilter(AppCategoryLangname.class);
				queryFilter.addFilter("categoryId=",categoryId);
				List<AppCategoryLangname> list = langnameService.find(queryFilter);
				// 保存单页面内容
				JSONObject jsonObject = JSON.parseObject(jsonData);
				for (AppCategoryLangname langname : list) {
					Set<String> keys = jsonObject.keySet();
					Iterator<String> it = keys.iterator();
					while (it.hasNext()) {
						String next = it.next();
						if (langname.getDicId().toString().equals(next.split("_")[1])) {
							langname.setLangContent(jsonObject.getString(next));
							langnameService.update(langname);
						}
					}
				}
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@MethodName(name = "增加一条文章类型数据")
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppCategory appCategory){
		String text = request.getParameter("sysLanguages");
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		// 设置父类信息
		if (appCategory != null) {
			String pid = request.getParameter("preateId");
			if (!StringUtils.isEmpty(pid)) {
				appCategory.setPreateId(new Long(pid));
			}
			String pName = request.getParameter("preateName");
			if (!StringUtils.isEmpty(pName)) {
				if ("文章分类管理".equals(pName)) {
					pName = "no";
				}
				appCategory.setPreateName(pName);
			}
			appCategory.setSeoFication(appCategory.getName());
        	result = super.save(appCategory);
		}

		if (result.getSuccess()) {
			// 解析文章类别字符串
			List<JSONObject> list = JSONObject.parseArray(text,JSONObject.class);
			for (JSONObject json : list){
				AppCategoryLangname langname = new AppCategoryLangname();
				langname.setCategoryId(appCategory.getId());
				QueryFilter filter = new QueryFilter(AppDic.class);
				filter.addFilter("pkey=", "sysLanguage");
				filter.addFilter("value=", json.getString("id"));
				filter.setOrderby("created");
				AppDic langDic = appDicService.get(filter);
				langname.setLangName(json.getString("text"));
				langname.setLangContent(null);
				if (langDic != null) {
					langname.setDicKey(langDic.getValue());
					langname.setLangType(langDic.getName());
					langname.setDicId(langDic.getId());
				}
				langnameService.save(langname);
			}
		}
		return result;
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ModelAndView mav = new ModelAndView("/admin/web/appcategorymodify");
		Map<String, Object> appCategory = ((AppCategoryService)service).getCategoryById(id);
		mav.addObject("model", appCategory);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppCategory appCategory){
		String sysLanguages = request.getParameter("sysLanguages");
		JsonResult result = new JsonResult();
		if (!StringUtils.isEmpty(sysLanguages)) {
			List<JSONObject> list = JSONObject.parseArray(sysLanguages,JSONObject.class);
			for (JSONObject json : list){
				QueryFilter langNameFilter = new QueryFilter(AppCategoryLangname.class);
				langNameFilter.addFilter("categoryId=", appCategory.getId());
				langNameFilter.addFilter("dicKey=", json.getString("id"));
				AppCategoryLangname langname = langnameService.get(langNameFilter);
				if (langname != null) {
					langname.setLangName(json.getString("text"));
					langnameService.update(langname);
				} else {
					langname = new AppCategoryLangname();
					langname.setCategoryId(appCategory.getId());
					QueryFilter dicFilter = new QueryFilter(AppDic.class);
					dicFilter.addFilter("pkey=", "sysLanguage");
					dicFilter.addFilter("value=", json.getString("id"));
					dicFilter.setOrderby("created");
					AppDic appDic = appDicService.get(dicFilter);
					langname.setLangName(json.getString("text"));
					langname.setLangContent(null);
					if (appDic != null) {
						langname.setDicKey(appDic.getValue());
						langname.setLangType(appDic.getName());
						langname.setDicId(appDic.getId());
					}
					langnameService.save(langname);
				}
			}
			result = super.update(appCategory);
		}
		return result;
	}


	@MethodName(name = "删除一条文章类型数据")
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public JsonResult remove(@PathVariable String id){
		JsonResult result = new JsonResult();
		AppCategoryService aservice = (AppCategoryService) service;
		AppCategory category = aservice.get(Long.valueOf(id));
		// 获取该文章类型的子类
		QueryFilter filter = new QueryFilter(AppCategory.class);
		filter.addFilter("preateId=", category.getId());
		List<AppCategory> childList = aservice.find(filter);
		if (childList != null && childList.size() > 0) {
			result.setSuccess(false);
			result.setMsg("文章分类下有子类别，请先删除子类别");
		} else {
			// 查询该类下是否有文章
			QueryFilter content = new QueryFilter(AppArticle.class);
			content.addFilter("categoryId=", category.getId());
			List<AppArticle> contList = appArticleService.find(content);
			if (contList != null && contList.size() > 0){
				result.setSuccess(false);
				result.setMsg("文章分类下有内容，不能删除");
			}else{
				result = super.delete(category.getId());
				if (result.getSuccess()) {
					QueryFilter delLang = new QueryFilter(AppCategoryLangname.class);
					delLang.addFilter("categoryId=",category.getId());
					boolean b = langnameService.delete(delLang);
					if (!b){
						result.setSuccess(false);
						result.setMsg("文章语种类别删除失败");
					}
				}
			}
		}
		return result;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		return ((AppCategoryService)service).findPageBySql(request);
	}

	@MethodName(name = "加载系统语言")
	@RequestMapping("/getSysLanguage")
	@ResponseBody
	public List<AppDic> getSysLanguage(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppDic.class, request);
		filter.addFilter("pkey=", "sysLanguage");
		filter.setOrderby("created");
		List<AppDic> list = appDicService.find(filter);
		return list;
	}

	@MethodName(name = "加载文章类型列表数据-暂未用")
	@RequestMapping("/selectlist")
	@ResponseBody
	public List<Map<String, Object>> selectlist(HttpServletRequest request) {
		List<Map<String, Object>> list = ((AppCategoryService)service).selectlist(request);
		return list;
	}

	@MethodName(name = "根据分类加载文章类型列表数据")
	@RequestMapping("/selectlistByCategory")
	@ResponseBody
	public List<Map<String, Object>> selectlistByCategory(HttpServletRequest request) {
		List<Map<String, Object>> list = ((AppCategoryService)service).selectlistByCategory(request);
		return list;
	}

	@RequestMapping("/toAdd")
	public ModelAndView toAdd(){
		ModelAndView mav = new ModelAndView("/admin/web/appcategoryadd");
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		filter.setOrderby("created");
		List<AppDic> list = appDicService.find(filter);
		mav.addObject("langList", list);
		return mav;
	}

	@RequestMapping("/validSort")
	@ResponseBody
	public JsonResult validSort (HttpServletRequest request) {
		String sort = request.getParameter("sort");
		String categoryPid = request.getParameter("categoryPid");
		QueryFilter filter = new QueryFilter(AppCategory.class);
		if (StringUtils.isEmpty(categoryPid)) {
			filter.addFilter("preateId=", 0);
		} else {
			filter.addFilter("preateId=", categoryPid);
		}
		filter.addFilter("sort=", sort);
		List<AppCategory> categoryList = ((AppCategoryService)service).find(filter);
		if (categoryList != null && categoryList.size() > 0) {
			return new JsonResult().setSuccess(false).setMsg("排序已存在，请重新输入！");
		}
		return new JsonResult().setSuccess(true);
	}

	@MethodName(name = "加载文章分类树")
	@RequestMapping("/loadtree")
	@ResponseBody
	public List<AppCategory> loadTree(){
		QueryFilter filter = new QueryFilter(AppCategory.class);
		filter.setOrderby("sort asc");
		return service.find(filter);
	}

	@MethodName(name = "加载页面类别-暂不用")
	@RequestMapping("/loadCategory")
	@ResponseBody
	public List<AppDic> loadCategory(HttpServletRequest request){
		String key = request.getParameter("key");
		List<AppDic> list = HryCache.cache_appdic.get(key);
		return list;
	}
}
