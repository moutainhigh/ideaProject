/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-29 11:44:56 
 */
package hry.admin.lock.service;

import hry.admin.lock.model.ExDmLock;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;


/**
 * <p> ExDmLockService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-29 11:44:56 
 */
public interface ExDmLockService extends BaseService<ExDmLock, Long>{


    PageResult findPageBySql (QueryFilter filter);

    boolean isExistCoinCode (String coinCode);

    ExDmLock findByCoinCodeInfo (String coinCode);
}
