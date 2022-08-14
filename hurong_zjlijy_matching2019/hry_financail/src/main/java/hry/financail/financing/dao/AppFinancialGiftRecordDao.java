/**
 * Copyright:    
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:06:06 
 */
package hry.financail.financing.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.financail.financing.model.AppFinancialGiftRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> AppFinancialGiftRecordDao </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:06:06  
 */
public interface AppFinancialGiftRecordDao extends  BaseDao<AppFinancialGiftRecord, String> {

    /**
     * 查询用户红包
     * @param map
     * @return
     */
    List<hry.remote.model.AppFinancialGiftRecord> findUserRedAccount(Map<String,Object> map);

    /**
     *
     * @param paramMap
     * @return
     */
    List<AppFinancialGiftRecord> findUserGiftRecordList(Map<String,Object> paramMap);

    List<AppFinancialGiftRecord> selectAllUnreceived(Map<String,Object> map);
}
