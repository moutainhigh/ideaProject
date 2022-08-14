/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 20:10:54 
 */
package hry.admin.commend.dao;

import hry.admin.commend.model.AppCommendUser;
import hry.core.mvc.dao.base.BaseDao;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCommendUserDao </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 20:10:54  
 */
public interface AppCommendUserDao extends  BaseDao<AppCommendUser, Long> {

    List<AppCommendUser> findPageBySql(Map<String, Object> map);

    List<AppCommendUser> findConmmendPageBySql(Map<String, Object> map);

    List<String> findSid(String str);

    List<AppCommendUser> findConmmendPageByArray(String[] param);


    List<Map<String, Object>> icoFindPageBySql(Map<String, Object> map);

    List<Map<String, Object>> newResultsList(Map<String, Object> map);

    List<Map<String, Object>> assetsList(Map<String, Object> map);

}
