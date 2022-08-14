/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-02 17:47:13 
 */
package hry.admin.web.controller;


import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.web.model.AppLanguage;
import hry.admin.web.service.AppLanguageService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.redis.common.utils.RedisService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-02 17:47:13 
 */
@Controller
@RequestMapping("/web/applanguage")
public class AppLanguageController extends BaseController<AppLanguage, Long> {
	
	@Resource(name = "appLanguageService")
	@Override
	public void setService(BaseService<AppLanguage, Long> service) {
		super.service = service;
	}

	@Resource
	private AppDicService appDicService;
	@Resource
	private RedisService redisServer;

	public static List<String> readTxt(String filePath) {

		try {
			List<String> list = new ArrayList<String>();

			File file = new File(filePath);
			if(file.isFile() && file.exists()) {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String lineTxt = null;
				while ((lineTxt = br.readLine()) != null) {
					list.add(lineTxt);
					System.out.println(lineTxt);
				}
				br.close();
				return list;
			} else {
				System.out.println("文件不存在!");
			}
		} catch (Exception e) {
			System.out.println("文件读取错误!");
		}
		return null;
	}

	public static void main(String[] args) {
		List<String> list = readTxt("C:\\Users\\yanfeng\\Desktop\\otc词条.txt");
		System.out.println();
	}

	public void insert(String key){
		String[] split = key.split("=");

		QueryFilter qf = new QueryFilter(AppLanguage.class);
		qf.addFilter("langType=", "otc");
		qf.addFilter("langKey=", split[0]);
		AppLanguage appLanguage = super.service.get(qf);
		if(appLanguage == null){
			for (int i = 0; i < 7; i++) {
				AppLanguage app = new AppLanguage();
				app.setLangKey(split[0]);
				if(i == 0){
					app.setLangVal(split[1]);
					app.setLangCode("zh_CN");
				}
				app.setLangType("otc");
				if(i == 1){
					app.setLangCode("en");
				}else if(i == 2){
					app.setLangCode("kor");
				}else if(i == 3){
					app.setLangCode("jp");
				}else if(i == 4){
					app.setLangCode("fr");
				}else if(i == 5){
					app.setLangCode("es");
				}else if(i == 6){
					app.setLangCode("zh_TW");
				}
				app.setCreated(new Date());
				app.setModified(new Date());
				app.setSaasId("hurong_system");
				app.setWordSource("pc");

				super.service.save(app);
			}
		}
	}

	@RequestMapping("/export")
	@ResponseBody
	public JsonResult export(Long a) {
		List<String> list = readTxt("C:\\Users\\yanfeng\\Desktop\\otc词条.txt");

		list.forEach(str -> {
			insert(str);
		});
		return new JsonResult().setSuccess(true);
	}

	@RequestMapping("/toPage/{langType}/{wordSource}")
	public ModelAndView toPage(@PathVariable String langType, @PathVariable String wordSource){
		ModelAndView mav = new ModelAndView("/admin/web/applanguagepage");
		// 获取系统语种信息
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		filter.setOrderby("created");
		List<AppDic> langList = appDicService.find(filter);
		mav.addObject("langList", langList);
		mav.addObject("langType", langType);
		mav.addObject("wordSource", wordSource);
		return mav;
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		String langCode = request.getParameter("languageDic");
		String langType = request.getParameter("langType");
		String wordSource = request.getParameter("wordSource");
		if (StringUtils.isEmpty(langCode)) {
			langCode = "zh_CN";
		}
		QueryFilter filter = new QueryFilter(AppLanguage.class,request);
		filter.addFilter("langCode=", langCode);
		if ("all".equals(langType)) {
			filter.addFilter("wordSource=", "pc");
		} else if("appAll".equals(langType)) {
			filter.addFilter("wordSource=", "app");
		} else {
			filter.addFilter("langType=", langType);
		}
		filter.addFilter("wordSource=", wordSource);
		filter.setOrderby("langKey asc");
		return super.findPage(filter);
	}

	@RequestMapping(value="/save/{id}")
	@ResponseBody
	public JsonResult save(@PathVariable Long id, HttpServletRequest request){
		String langVal = request.getParameter("langValCell");
		String langType = request.getParameter("langTypeCell");
		AppLanguage language = service.get(id);
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		if (language != null) {
			// 转换非法字符
			langVal = HtmlUtils.htmlEscape(langVal);
			language.setLangVal(langVal);
			result = super.update(language);
			if (result.getSuccess()) {
				QueryFilter filter = new QueryFilter(AppLanguage.class,request);
				filter.addFilter("langKey=", language.getLangKey());
				List<AppLanguage> languageList = super.find(filter);
				if (languageList != null && languageList.size() > 0) {
					for (AppLanguage lang : languageList) {
						lang.setLangType(langType);
						result = super.update(lang);
					}
				}
			}
		}
		if (result.getSuccess()) {
			// 更新字典缓存
			((AppLanguageService)service).initCache();
			redisServer.publish("cacheLanguage",language.getLangKey()+","+language.getLangCode());
		}
		return result;
	}

	@RequestMapping("/toAdd/{langType}/{wordSource}")
	public ModelAndView toAdd(@PathVariable String langType, @PathVariable String wordSource, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/web/applanguageadd");
        QueryFilter filter = new QueryFilter(AppDic.class);
        filter.addFilter("pkey=", "sysLanguage");
        filter.setOrderby("created");
        List<AppDic> langList = appDicService.find(filter);
        mav.addObject("langList", langList);
        mav.addObject("langType", langType);
        mav.addObject("wordSource", wordSource);
		return mav;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request){
        JsonResult result = new JsonResult();
        String langKey = request.getParameter("langKey");
        String wordSource = request.getParameter("wordSource");
        QueryFilter qf = new QueryFilter(AppLanguage.class);
		qf.addFilter("langKey=", langKey);
		qf.addFilter("wordSource=", wordSource);
		List<AppLanguage> applangList = service.find(qf);
		if (applangList.isEmpty()) {
			String langType = request.getParameter("langType");
			if ("all".equals(langType)) {
				langType = "other";
			} else if("appAll".equals(langType)) {
				langType = "appOtherWord";
			}
			// 获取系统语种
			QueryFilter filter = new QueryFilter(AppDic.class);
			filter.addFilter("pkey=", "sysLanguage");
			filter.setOrderby("created");
			List<AppDic> langList = appDicService.find(filter);
			try {
				result.setSuccess(false);
				if (langList != null && langList.size() > 0) {
					for (AppDic dic : langList) {
						AppLanguage language = new AppLanguage();
						String langVal = request.getParameter("langVal_" + dic.getValue());
						// 转换非法字符
						langVal = HtmlUtils.htmlEscape(langVal);
						language.setLangKey(langKey);
						language.setLangType(langType);
						language.setLangVal(langVal);
						language.setLangCode(dic.getValue());
						language.setWordSource(wordSource);
						service.save(language);
						//((AppLanguageService)service).saveTranslate(language, request);
						redisServer.publish("cacheLanguage",language.getLangKey()+","+language.getLangCode());
					}
					result.setSuccess(true);
					// 更新字典缓存
					((AppLanguageService)service).initCache();
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}
		} else {
			result.setSuccess(false).setMsg("langKey已经存在！");
		}
        return result;
	}

	@RequestMapping("/remove")
	@ResponseBody
	public JsonResult remove(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		JsonResult jsonResult = new JsonResult();
		if (StringUtils.isEmpty(ids)) {
			jsonResult.setSuccess(false);
		} else {
			List <Long> idList = new ArrayList<>();
			String[] idStrs = ids.split(",");
			for (String idstr : idStrs) {
				idList.add(new Long(idstr));
			}
 			jsonResult = super.deleteBatch(idList);
		}
		if (jsonResult.getSuccess()) {
			// 更新字典缓存
			((AppLanguageService)service).initCache();
		}
		return jsonResult;
	}

	@RequestMapping("/getCNData")
    @ResponseBody
    public List<AppLanguage> getCNData(HttpServletRequest request){
		String wordSource = request.getParameter("wordSource");
        QueryFilter filter = new QueryFilter(AppLanguage.class, request);
        filter.addFilter("langCode=", "zh_CN");
        filter.addFilter("wordSource=", wordSource);
        return super.find(filter);
    }


	@RequestMapping(value="/translate/{id}")
	@ResponseBody
	public JsonResult translate(@PathVariable Long id, HttpServletRequest request){
		AppLanguage language = service.get(id);
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		if (language != null) {
			QueryFilter filter = new QueryFilter(AppLanguage.class,request);
			filter.addFilter("langKey=", language.getLangKey());
			// 如不加 则app单独保存词条时 会把pc相同key也翻译出来 （注：app中key有能和pc的key相同）
			filter.addFilter("wordSource=", language.getWordSource());
			List<AppLanguage> languageList = super.find(filter);
			if (languageList != null && languageList.size() > 0) {
				for (AppLanguage lang : languageList) {
					((AppLanguageService)service).updateTranslate(lang,language.getLangVal());
				}
				result.setSuccess(true);
			}
		}
		if (result.getSuccess()) {
			// 更新字典缓存
			((AppLanguageService)service).initCache();
		}
		return result;
	}
}
