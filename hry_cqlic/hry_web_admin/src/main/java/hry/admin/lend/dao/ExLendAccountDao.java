/**
 * Copyright:    
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:48:05 
 */
package hry.admin.lend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.lend.model.ExLendAccount;

import java.util.List;
import java.util.Map;


/**
 * <p> ExLendAccountDao </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:48:05  
 */
public interface ExLendAccountDao extends  BaseDao<ExLendAccount, Long> {

    List<ExLendAccount> findPageBySql(Map<String, Object> map);
}
