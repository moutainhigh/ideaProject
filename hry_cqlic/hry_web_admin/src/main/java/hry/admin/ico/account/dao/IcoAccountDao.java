/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 12:02:43 
 */
package hry.admin.ico.account.dao;

import hry.admin.ico.account.model.IcoAccountAtioPo;
import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.account.model.IcoAccount;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoAccountDao </p>
 * @author:         lzy
 * @Date :          2019-01-14 12:02:43  
 */
public interface IcoAccountDao extends  BaseDao<IcoAccount, Long> {


    List<IcoAccountAtioPo> getAccountAtioBylevelSort(Map<String, Object> map);
}
