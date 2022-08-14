/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-21 14:45:05 
 */
package hry.admin.exchange.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExDmPing;

import java.util.List;


/**
 * <p> ExDmPingService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-21 14:45:05 
 */
public interface ExDmPingService  extends BaseService<ExDmPing, Long>{

    public String[] checkPing(Long customerId);

    public Boolean isPinging(Long customerId, String userCode, String currencyType, String website);

    public List<ExDmPing> getBycustomerid(Long customerId, String userCode, Integer status, String currencyType, String website);
}
