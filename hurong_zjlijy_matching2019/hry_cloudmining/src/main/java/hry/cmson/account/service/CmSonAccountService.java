/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-04 10:12:14 
 */
package hry.cmson.account.service;

import hry.cmson.dto.Accountadd;
import hry.core.mvc.service.base.BaseService;
import hry.cmson.account.model.CmSonAccount;

import java.util.List;


/**
 * <p> CmSonAccountService </p>
 * @author:         yaozh
 * @Date :          2019-11-04 10:12:14 
 */
public interface CmSonAccountService  extends BaseService<CmSonAccount, Long>{
    Boolean accountaddQueue(String accoutadds);
    void saveAccount(List<Accountadd> accountaddlist);

}
