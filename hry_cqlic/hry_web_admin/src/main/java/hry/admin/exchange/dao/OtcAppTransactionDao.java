/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 18:12:38 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.OtcAppTransaction;

import java.util.List;
import java.util.Map;


/**
 * <p> OtcAppTransactionDao </p>
 * @author:         denghf
 * @Date :          2018-10-26 18:12:38  
 */
public interface OtcAppTransactionDao extends  BaseDao<OtcAppTransaction, Long> {
    List<OtcAppTransaction> findPageBySql(Map<String,Object> map);
}
