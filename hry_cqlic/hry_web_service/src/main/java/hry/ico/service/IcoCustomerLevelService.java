/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-16 21:09:20 
 */
package hry.ico.service;

import hry.core.mvc.service.base.BaseService;
import hry.ico.model.IcoCustomerLevel;

import java.util.List;


/**
 * <p> IcoCustomerLevelService </p>
 * @author:         houz
 * @Date :          2019-01-16 21:09:20 
 */
public interface IcoCustomerLevelService extends BaseService<IcoCustomerLevel, Long>{
    /**
     * 月末扣除用户当前经验10%
     * Month-end deduction experience
     */
    public void deductionExperience();

}
