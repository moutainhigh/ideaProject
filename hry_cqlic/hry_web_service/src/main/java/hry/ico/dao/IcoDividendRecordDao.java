/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 20:56:02 
 */
package hry.ico.dao;


import hry.core.mvc.dao.base.BaseDao;
import hry.ico.model.IcoDividendRecord;

import java.util.List;
import java.util.Map;

/**
 * <p> IcoDividendRecordDao </p>
 * @author:         houz
 * @Date :          2019-01-14 20:56:02  
 */
public interface IcoDividendRecordDao extends BaseDao<IcoDividendRecord, Long> {
    //获取用户分红记录
    List<IcoDividendRecord> queryDividendRecord(Map<String ,String> map);
}
