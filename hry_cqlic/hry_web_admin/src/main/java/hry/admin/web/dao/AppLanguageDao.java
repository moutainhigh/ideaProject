/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-02 17:47:13 
 */
package hry.admin.web.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.web.model.AppLanguage;

import java.util.List;
import java.util.Map;


/**
 * <p> AppLanguageDao </p>
 * @author:         liuchenghui
 * @Date :          2018-07-02 17:47:13  
 */
public interface AppLanguageDao extends  BaseDao<AppLanguage, Long> {

    void insertByBatch(List<Map<String, Object>> sqlMap);

    List<String> findLangKeyByLangCode(Map<String, String> paraMap);
}
