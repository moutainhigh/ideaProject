/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-11-01 18:38:24 
 */
package hry.admin.lock.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.lock.model.ExDmLockReleaseTime;

import java.util.List;
import java.util.Map;


/**
 * <p> ExDmLockReleaseTimeDao </p>
 * @author:         liuchenghui
 * @Date :          2018-11-01 18:38:24  
 */
public interface ExDmLockReleaseTimeDao extends  BaseDao<ExDmLockReleaseTime, Long> {

    List<ExDmLockReleaseTime> getReleaseTime (Map<String, Object> map);

    void updReleaseTimeForAuto (Long recordId);

    void updReleaseTimeForManual (Long recordId);
}
