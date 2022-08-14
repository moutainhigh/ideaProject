/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:03 
 */
package hry.admin.commend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.commend.model.AppCommendRebat;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCommendRebatDao </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:50:03  
 */
public interface AppCommendRebatDao extends  BaseDao<AppCommendRebat, Long> {

    List<AppCommendRebat> findPageBySql(Map<String, Object> map);
}
