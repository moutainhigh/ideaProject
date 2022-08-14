/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-12 17:53:39 
 */
package hry.web.app.service;

import hry.core.mvc.service.base.BaseService;
import hry.web.app.model.AppCategoryLangname;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCategoryLangnameService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-12 17:53:39  
 */
public interface AppCategoryLangnameService  extends BaseService<AppCategoryLangname, Long>{

    List<Map<String, Object>> loadNewsChildsMenu(String lang);

    List<Map<String, Object>> loadSiteMapInfo(String lang);

    List<Map<String,Object>> loadNoticeInfo(String lang, String categoryName);

    List<Map<String,Object>> findArticCategoryListById(String lang, Long categoryid);

    Map<String, Object> findCategoryById(String lang, Long categoryId);
}
