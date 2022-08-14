/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-14 11:18:59 
 */
package hry.admin.web.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.web.model.AppConfig;

import java.util.List;


/**
 * <p> AppConfigDao </p>
 * @author:         liushilei
 * @Date :          2018-06-14 11:18:59  
 */
public interface AppConfigDao extends  BaseDao<AppConfig, Long> {

    List<AppConfig> findType();
}
