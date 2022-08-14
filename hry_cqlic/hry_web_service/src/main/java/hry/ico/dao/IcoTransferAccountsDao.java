/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-17 15:10:57 
 */
package hry.ico.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.ico.model.IcoTransferAccounts;
import org.apache.ibatis.annotations.Param;


/**
 * <p> IcoTransferAccountsDao </p>
 * @author:         lzy
 * @Date :          2019-01-17 15:10:57  
 */
public interface IcoTransferAccountsDao extends  BaseDao<IcoTransferAccounts, Long> {

    public IcoTransferAccounts getTransferAccountsInfo(@Param("keyId")Long keyId);
}
