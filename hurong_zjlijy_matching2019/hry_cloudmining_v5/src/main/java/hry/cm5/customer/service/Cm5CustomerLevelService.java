/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:12:12 
 */
package hry.cm5.customer.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm5.customer.model.Cm5CustomerLevel;

import java.util.List;


/**
 * <p> Cm5CustomerLevelService </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:12:12 
 */
public interface Cm5CustomerLevelService  extends BaseService<Cm5CustomerLevel, Long>{

    public String getCustomerAddRatio(Long customerId);

    public List<Cm5CustomerLevel> findNextCustomerLevel(Long customerId);



}
