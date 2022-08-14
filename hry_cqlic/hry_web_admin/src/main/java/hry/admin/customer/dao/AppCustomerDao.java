/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:43:00 
 */
package hry.admin.customer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.customer.model.AppCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCustomerDao </p>
 * @author:         liushilei
 * @Date :          2018-06-13 09:43:00  
 */
public interface AppCustomerDao extends  BaseDao<AppCustomer, Long> {
    /**
     * 新sql分页查询
     * @param map
     * @return
     */
    List<AppCustomer> findPageBySql(Map<String,Object> map);

    Long getYesterdayCoutomers (Map<String, Object> map);


    /**
     * 查询总线ID
     * @param id
     * @return
     */
    String getBusNumber(@Param("id") Long id);
    
    
    /**
     * 查询每天的注册人数
     * @param map
     * @return
     */
    List<AppCustomer> findListGroupByDay(Map<String,Object> map);
}
