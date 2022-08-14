/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:56:33 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.ExDigitalmoneyAccount;

import java.util.List;
import java.util.Map;


/**
 * <p> ExDigitalmoneyAccountDao </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:56:33  
 */
public interface ExDigitalmoneyAccountDao extends  BaseDao<ExDigitalmoneyAccount, Long> {
    List<ExDigitalmoneyAccount> findPageBySql(Map<String,Object> map);

    Long findPageBySqlCount(Map<String, Object> map);
}
