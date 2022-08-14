/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-13 15:29:30 
 */
package hry.licqb.record.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.licqb.record.model.CustomerFreeze;


/**
 * <p> CustomerFreezeDao </p>
 * @author:         zhouming
 * @Date :          2020-01-13 15:29:30  
 */
public interface CustomerFreezeDao extends  BaseDao<CustomerFreeze, Long> {
    public CustomerFreeze getCustomerFreeze(Long customerId);
}
