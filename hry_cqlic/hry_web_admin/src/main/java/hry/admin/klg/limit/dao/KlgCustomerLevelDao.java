/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-16 19:48:29 
 */
package hry.admin.klg.limit.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.klg.limit.model.KlgCustomerLevel;

import java.util.List;
import java.util.Map;


/**
 * <p> KlgCustomerLevelDao </p>
 * @author:         lzy
 * @Date :          2019-04-16 19:48:29  
 */
public interface KlgCustomerLevelDao extends  BaseDao<KlgCustomerLevel, Long> {

    public int customerRewardAdd(KlgCustomerLevel customerLevel);

    List<KlgCustomerLevel> findPageBySql(Map<String, Object> map);
}
