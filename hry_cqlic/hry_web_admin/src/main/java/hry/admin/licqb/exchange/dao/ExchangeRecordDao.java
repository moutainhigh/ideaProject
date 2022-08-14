/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:36:32 
 */
package hry.admin.licqb.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.licqb.exchange.model.ExchangeRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> ExchangeRecordDao </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:36:32  
 */
public interface ExchangeRecordDao extends  BaseDao<ExchangeRecord, Long> {

    List<ExchangeRecord> findPageBySql(Map<String, Object> map);
}
