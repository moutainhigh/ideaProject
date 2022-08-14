/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:26:42 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.ExOrderInfo;

import java.util.List;
import java.util.Map;


/**
 * <p> ExOrderInfoDao </p>
 * @author:         liushilei
 * @Date :          2018-06-13 11:26:42  
 */
public interface ExOrderInfoDao extends  BaseDao<ExOrderInfo, Long> {
    /**
     * 新sql分页
     * @param map
     * @return
     */
    List<ExOrderInfo> findPageBySql(Map<String, Object> map);

    /**
     * 新sql分页
     * @param map
     * @return
     */
    List<ExOrderInfo> findPageBySqlList(Map<String, Object> map);

    List<ExOrderInfo> getYesterdayTreads (Map<String, Object> map);
}
