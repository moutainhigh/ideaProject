/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:35:02 
 */
package hry.cm2.customer.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm2.customer.model.Cm2Customer;



/**
 * <p> Cm2CustomerService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:35:02 
 */
public interface Cm2CustomerService  extends BaseService<Cm2Customer, Long>{

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
    public Cm2Customer getCustomerByCustomerId(Long customerId);




}
