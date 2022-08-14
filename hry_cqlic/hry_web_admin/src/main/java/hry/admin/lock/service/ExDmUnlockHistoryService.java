/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-09-20 17:34:28 
 */
package hry.admin.lock.service;

import hry.admin.lock.model.ExDmUnlockHistory;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;


/**
 * <p> ExDmUnlockHistoryService </p>
 * @author:         liuchenghui
 * @Date :          2018-09-20 17:34:28 
 */
public interface ExDmUnlockHistoryService  extends BaseService<ExDmUnlockHistory, Long>{

    PageResult findPageBySql (QueryFilter filter);

    void timingUnlockAccountCold ();
}
