/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:42:58 
 */
package hry.admin.customer.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.customer.model.AppBankCard;
import hry.util.QueryFilter;


/**
 * <p> AppBankCardService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:42:58 
 */
public interface AppBankCardService  extends BaseService<AppBankCard, Long>{
    public PageResult findPageBySql(QueryFilter filter);

}
