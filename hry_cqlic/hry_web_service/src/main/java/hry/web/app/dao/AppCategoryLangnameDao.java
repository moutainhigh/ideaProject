/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-12 17:53:39 
 */
package hry.web.app.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.web.app.model.AppCategoryLangname;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCategoryLangnameDao </p>
 * @author:         liuchenghui
 * @Date :          2018-06-12 17:53:39  
 */
public interface AppCategoryLangnameDao extends  BaseDao<AppCategoryLangname, Long> {

    List<Map<String, Object>> loadNewsChildsMenu(Map<String, Object> paraMap);

    List<Map<String, Object>> getNoticeInfo(Map<String, Object> paraMap);

    List<Map<String, Object>> findArticCategoryListById(Map<String, Object> paraMap);

}
