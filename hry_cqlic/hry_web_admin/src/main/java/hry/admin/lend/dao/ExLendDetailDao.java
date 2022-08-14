/**
 * Copyright:    
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 17:58:04 
 */
package hry.admin.lend.dao;

import hry.admin.lend.model.ExLendAccount;
import hry.core.mvc.dao.base.BaseDao;
import hry.admin.lend.model.ExLendDetail;

import java.util.List;
import java.util.Map;


/**
 * <p> ExLendDetailDao </p>
 * @author:         HeC
 * @Date :          2018-10-18 17:58:04  
 */
public interface ExLendDetailDao extends  BaseDao<ExLendDetail, Long> {

    List<ExLendAccount> findPageBySql(Map<String, String> map);
}
