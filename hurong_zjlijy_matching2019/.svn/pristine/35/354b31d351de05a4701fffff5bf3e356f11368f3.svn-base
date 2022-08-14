/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 09:59:44 
 */
package hry.cm4.customer.service;

import hry.cm4.customer.model.Cm4Customer;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> Cm4CustomerService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 09:59:44 
 */
public interface Cm4CustomerService  extends BaseService<Cm4Customer, Long>{

    /**
     * 主动更新等级,只更新自己的等级
     * @param messageStr
     */
    void updateGrade(String messageStr);

    /**
     * 矿机到期  购买矿机更新等级,更新自己和上级的等级
     * @param messageStr
     */
    void buyAndcloseMinerUpdateGrade(String messageStr);

    /**
     * 查询用户等级信息
     * @param customerId
     * @return
     */
    public Cm4Customer getCustomerByCustomerId(Long customerId);

}
