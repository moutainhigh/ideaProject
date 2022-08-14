/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:54:15 
 */
package hry.admin.exchange.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExDmCustomerPublickey;
import hry.util.QueryFilter;


/**
 * <p> ExDmCustomerPublickeyService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:54:15 
 */
public interface ExDmCustomerPublickeyService  extends BaseService<ExDmCustomerPublickey, Long>{

    public PageResult findPageBySql(QueryFilter filter);
}
