/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-17 15:10:57 
 */
package hry.ico.service;

import hry.core.mvc.service.base.BaseService;
import hry.ico.model.IcoTransferAccounts;



/**
 * <p> IcoTransferAccountsService </p>
 * @author:         lzy
 * @Date :          2019-01-17 15:10:57 
 */
public interface IcoTransferAccountsService  extends BaseService<IcoTransferAccounts, Long>{

    public IcoTransferAccounts getTransferAccountsInfo(Long keyId);
}
