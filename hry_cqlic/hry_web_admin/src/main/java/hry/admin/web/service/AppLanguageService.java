/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-02 17:47:13 
 */
package hry.admin.web.service;

import hry.core.mvc.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;

import hry.admin.web.model.AppLanguage;



/**
 * <p> AppLanguageService </p>
 * @author:         liuchenghui
 * @Date :          2018-07-02 17:47:13 
 */
public interface AppLanguageService  extends BaseService<AppLanguage, Long>{

    /**
     * 启动初始化同步语种数据-以简体中文为基础
     */
    void synchronizedLangData();

    /**
     * 启动初始化缓存
     */
    void initCache();
    
    /**
     * 词条自动翻译
     * @param appLanguage
     * @param request
     */
    void saveTranslate(AppLanguage appLanguage,HttpServletRequest request);

    /**
     * 修改词条自动翻译
     * @param appLanguage
     * @param request
     */
    void updateTranslate(AppLanguage appLanguage,String langVal);
}
