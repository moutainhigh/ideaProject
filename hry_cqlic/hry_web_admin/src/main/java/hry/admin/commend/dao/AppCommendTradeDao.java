/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:18 
 */
package hry.admin.commend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.commend.model.AppCommendTrade;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCommendTradeDao </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:50:18  
 */
public interface AppCommendTradeDao extends  BaseDao<AppCommendTrade, Long> {

    /**
     * sql 分页
     * @param map
     * @return
     */
    List<AppCommendTrade> findPageBySql(Map<String, Object> map);
}
