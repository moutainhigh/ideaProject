/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 09:59:44 
 */
package hry.cm4.customer.dao;

import hry.cm4.customer.model.Cm4Customer;
import hry.core.mvc.dao.base.BaseDao;
import org.apache.ibatis.annotations.Param;


/**
 * <p> Cm4CustomerDao </p>
 * @author:         yaozh
 * @Date :          2019-11-21 09:59:44  
 */
public interface Cm4CustomerDao extends  BaseDao<Cm4Customer, Long> {
    /**
     * 查询用户等级信息
     * @param customerId
     * @return
     */
    public Cm4Customer getCustomerByCustomerId(@Param("customerId") Long customerId);

}
