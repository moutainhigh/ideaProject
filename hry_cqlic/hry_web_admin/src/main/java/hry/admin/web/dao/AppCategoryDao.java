/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 14:46:47 
 */
package hry.admin.web.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.web.model.AppCategory;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCategoryDao </p>
 * @author:         liuchenghui
 * @Date :          2018-06-26 14:46:47  
 */
public interface AppCategoryDao extends  BaseDao<AppCategory, Long> {

    List<Map<String, Object>> findPageBySql(Map<String, Object> map);

    Map<String, Object> getCategoryById(Map<String, String> map);

    List<Map<String, Object>> getCategoryByCondition(Map<String, Object> map);
}
