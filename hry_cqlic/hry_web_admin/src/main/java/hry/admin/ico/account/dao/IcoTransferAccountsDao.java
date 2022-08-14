/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-02-26 13:46:47 
 */
package hry.admin.ico.account.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.account.model.IcoTransferAccounts;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoTransferAccountsDao </p>
 * @author:         lzy
 * @Date :          2019-02-26 13:46:47  
 */
public interface IcoTransferAccountsDao extends  BaseDao<IcoTransferAccounts, Long> {

    List<IcoTransferAccounts> findPageBySql(Map<String, Object> map);
}
