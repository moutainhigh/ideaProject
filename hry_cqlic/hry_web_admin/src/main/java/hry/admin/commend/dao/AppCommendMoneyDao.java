/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:49:22 
 */
package hry.admin.commend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.commend.model.AppCommendMoney;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCommendMoneyDao </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:49:22  
 */
public interface AppCommendMoneyDao extends  BaseDao<AppCommendMoney, Long> {

    /**
     * sql 分页
     * @param map
     * @return
     */
    List<AppCommendMoney> findPageBySql(Map<String, Object> map);
}
