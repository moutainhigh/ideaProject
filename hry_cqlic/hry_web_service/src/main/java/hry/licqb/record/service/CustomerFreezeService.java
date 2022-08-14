/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-13 15:29:30 
 */
package hry.licqb.record.service;

import hry.core.mvc.service.base.BaseService;
import hry.licqb.record.model.CustomerFreeze;
import util.BigDecimalUtil;

import javax.json.Json;
import java.math.BigDecimal;


/**
 * <p> CustomerFreezeService </p>
 * @author:         zhouming
 * @Date :          2020-01-13 15:29:30 
 */
public interface CustomerFreezeService  extends BaseService<CustomerFreeze, Long>{

    public BigDecimal getFreezeMoney(Long customerId);

    public void updateFreezeMoney(Long customerId, BigDecimal freezeMoney);

    public void clearFreezeMoney(Long customerId);

    public CustomerFreeze getCustomerFreeze(Long customerId);

    public void againPutIntoFreeze(Long customerId);
}
