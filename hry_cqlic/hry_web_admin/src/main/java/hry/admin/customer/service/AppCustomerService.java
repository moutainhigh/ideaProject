/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:43:00 
 */
package hry.admin.customer.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.customer.model.AppCustomer;
import hry.util.QueryFilter;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCustomerService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 09:43:00 
 */
public interface AppCustomerService  extends BaseService<AppCustomer, Long>{

    public List<AppCustomer> find(QueryFilter filter);

    public JsonResult storpCustomer(Long id, boolean type);

    public void giveCoin (Long id);

    public PageResult findPageBySql(QueryFilter filter);

    String getYesterdayCoutomers ();
    
    /**
     * 查询每天的注册人数
     * @param map
     * @return
     */
    PageResult findListGroupByDay(QueryFilter filter);

    /**
     * 开启关闭动态收益
     * */
    JsonResult stropUserDynamic(Long id, boolean type);
}
