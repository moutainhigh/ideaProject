/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-07-11 14:27:21 
 */
package hry.admin.web.dao;

import hry.admin.web.model.WhiteList;
import hry.core.mvc.dao.base.BaseDao;

import java.util.List;
import java.util.Map;


/**
 * <p> WhiteListDao </p>
 * @author:         liushilei
 * @Date :          2018-07-11 14:27:21  
 */
public interface WhiteListDao extends  BaseDao<WhiteList, Long> {

    List<Map<String, Object>> findCustomListByPage (Map<String, String> map);

    List<Map<String, Object>> findWhiteListBySql (Map<String, String> paraMap);
}
