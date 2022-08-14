/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-12-24 11:01:15 
 */
package hry.cm5.account.service;

import hry.cm5.dto.Accountadd;
import hry.core.mvc.service.base.BaseService;
import hry.cm5.account.model.Cm5Account;
import java.util.List;
/**
 * <p> Cm5AccountService </p>
 * @author:         zhouming
 * @Date :          2019-12-24 11:01:15 
 */
public interface Cm5AccountService  extends BaseService<Cm5Account, Long>{

    Boolean accountaddQueue(String accoutadds);
    void saveAccount(List<Accountadd> accountaddlist);
}
