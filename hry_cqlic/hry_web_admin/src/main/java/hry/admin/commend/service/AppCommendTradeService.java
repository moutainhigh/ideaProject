/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:18 
 */
package hry.admin.commend.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.commend.model.AppCommendTrade;
import hry.util.QueryFilter;



/**
 * <p> AppCommendTradeService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:50:18 
 */
public interface AppCommendTradeService  extends BaseService<AppCommendTrade, Long>{

    /**
     * sql分页
     * @param filter
     * @return
     */
    PageResult findPageBySql(QueryFilter filter);
}
