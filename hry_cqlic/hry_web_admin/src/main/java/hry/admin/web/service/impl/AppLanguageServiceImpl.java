/**
 * Copyright:
 * @author:      liuchenghui
 * @version:     V1.0
 * @Date:        2018-07-02 17:47:13
 */
package hry.admin.web.service.impl;

import hry.admin.baidu.translate.TranslateApi;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.web.dao.AppLanguageDao;
import hry.admin.web.model.AppLanguage;
import hry.admin.web.service.AppLanguageService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.listener.ServerManagement;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppLanguageService </p>
 * @author:         liuchenghui
 * @Date :          2018-07-02 17:47:13
 */
@Service("appLanguageService")
public class AppLanguageServiceImpl extends BaseServiceImpl<AppLanguage, Long> implements AppLanguageService{
	private static Logger logger = Logger.getLogger(AppLanguageServiceImpl.class);
	@Resource(name="appLanguageDao")
	@Override
	public void setDao(BaseDao<AppLanguage, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private AppDicService appDicService;
	@Resource
	private RedisService redisService;

	/**
	 * 启动初始化同步语种数据-以简体中文为基础
	 */
	@Override
	public void synchronizedLangData () {
		List<Map<String, Object>> insertList = new ArrayList<>();
		Map<String, Object> insertMap = null;
		// 1、查询所有的简体中文数据
		QueryFilter qf_cn = new QueryFilter(AppLanguage.class);
		qf_cn.addFilter("langCode=", "zh_CN");
		List<AppLanguage> langList = this.find(qf_cn);
		if (langList != null && langList.size() > 0) {
			// 2、查询其他系统语种，遍历比较插入完成同步操作
			QueryFilter filter = new QueryFilter(AppDic.class);
			filter.addFilter("pkey=", "sysLanguage");
			filter.addFilter("value_NEQ", "zh_CN");
			List<AppDic> dicList = appDicService.find(filter);
			// 3、循环系统语种
			if (dicList != null && dicList.size() > 0) {
				for (AppDic dic : dicList) {
					Map<String, String> paraMap = new HashMap<>();
					paraMap.put("langCode", dic.getValue());
					List<String> langs = ((AppLanguageDao)dao).findLangKeyByLangCode(paraMap);
					for (AppLanguage langCN : langList) {
						String langKey = langCN.getLangKey();
						if ((langs == null || langs.size() == 0 || !langs.contains(langKey))) {
							insertMap = new HashMap<>();
							insertMap.put("langKey", langKey);
							insertMap.put("langVal", langCN.getLangVal());
							insertMap.put("langType", langCN.getLangType());
							insertMap.put("langCode", dic.getValue());
							insertMap.put("wordSource", langCN.getWordSource());
							insertList.add(insertMap);
						}
					}
				}
			}
			if (insertList != null && insertList.size() > 0) {
				((AppLanguageDao)dao).insertByBatch(insertList);
			}
		}
	}

	@Override
	public void initCache() {
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		List<AppDic> langList = appDicService.find(filter);
		if (langList != null && !langList.isEmpty()) {
			for (AppDic appDic : langList) {
				// 先移除相应的key然后再添加
				redisService.delete("language:" + appDic.getValue());
				redisService.delete("app_language:" + appDic.getValue());

				QueryFilter queryFilter = new QueryFilter(AppLanguage.class);
				queryFilter.addFilter("langCode=", appDic.getValue());
				List<AppLanguage> list = find(queryFilter);
				if (list != null && !list.isEmpty()) {
					for (AppLanguage appLanguage : list) {
						if(StringUtils.isEmpty(appLanguage.getLangKey())){
							logger.error(appLanguage.getId());
							continue;
						}
						//保存到redis中
						String langVal = appLanguage.getLangVal();
						if (StringUtils.isEmpty(langVal)) {
							langVal = "";
						}
						if ("pc".equals(appLanguage.getWordSource())) {
							redisService.hset("language:" + appLanguage.getLangCode(), appLanguage.getLangKey(), langVal);
						} else {
							redisService.hset("app_language:" + appLanguage.getLangCode(), appLanguage.getLangKey(), langVal);
						}
					}
				}

			}
		}
	}

	@Override
	public void saveTranslate(AppLanguage appLanguage, HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			String langVal = "";
			TranslateApi translateApi = new TranslateApi();
			if(StringUtils.isEmpty(appLanguage.getLangVal())){
				langVal = request.getParameter("langVal_zh_CN");
			}else{
				langVal = appLanguage.getLangVal();
			}
			String tranResult = translateApi.getTransResult_HRY(langVal, appLanguage.getLangCode());
			appLanguage.setLangVal(tranResult);
			((AppLanguageDao)dao).insert(appLanguage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	@Override
	public void updateTranslate(AppLanguage language, String langVal) {
		try {
			TranslateApi translateApi = new TranslateApi();
			String transResult = translateApi.getTransResult_HRY(langVal, language.getLangCode());
			language.setLangVal(transResult);
			super.update(language);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
