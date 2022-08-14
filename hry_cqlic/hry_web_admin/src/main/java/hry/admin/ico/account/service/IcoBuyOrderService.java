/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 14:51:32 
 */
package hry.admin.ico.account.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.account.model.IcoBuyOrder;
import hry.util.QueryFilter;


/**
 * <p> IcoBuyOrderService </p>
 * @author:         lzy
 * @Date :          2019-01-14 14:51:32 
 */
public interface IcoBuyOrderService  extends BaseService<IcoBuyOrder, Long>{
    /**
     * sql分页查询
     * @param filter
     * @return
     */
    public PageResult findPageBySql(QueryFilter filter);
}
