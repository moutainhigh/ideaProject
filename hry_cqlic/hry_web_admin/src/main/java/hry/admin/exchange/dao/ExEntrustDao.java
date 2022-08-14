/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:12:40 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.ExEntrust;

import java.util.List;
import java.util.Map;


/**
 * <p> ExEntrustDao </p>
 * @author:         liushilei
 * @Date :          2018-06-13 11:12:40  
 */
public interface ExEntrustDao extends  BaseDao<ExEntrust, Long> {
    /**
     * 新sql分页
     * @param map
     * @return
     */
    List<ExEntrust> findPageBySql(Map<String, Object> map);

    List<ExEntrust> findLendPageBySql(Map<String, Object> map);
}
