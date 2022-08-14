/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-09-20 17:34:28 
 */
package hry.exchange.lock.service;

import hry.core.mvc.service.base.BaseService;
import hry.exchange.lock.model.ExDmUnlockHistory;


/**
 * <p> ExDmUnlockHistoryService </p>
 * @author:         liuchenghui
 * @Date :          2018-09-20 17:34:28 
 */
public interface ExDmUnlockHistoryService extends BaseService<ExDmUnlockHistory, Long>{
    void timingUnlockAccountCold ();
}
