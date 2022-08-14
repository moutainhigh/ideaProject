/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:35:02 
 */
package hry.cm2.customer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.customer.model.Cm2Customer;
import org.apache.ibatis.annotations.Param;


/**
 * <p> Cm2CustomerDao </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:35:02  
 */
public interface Cm2CustomerDao extends  BaseDao<Cm2Customer, Long> {

    /**
     * 查询用户等级信息
     * @param customerId
     * @return
     */
    public Cm2Customer getCustomerByCustomerId(@Param("customerId") Long customerId);

}
