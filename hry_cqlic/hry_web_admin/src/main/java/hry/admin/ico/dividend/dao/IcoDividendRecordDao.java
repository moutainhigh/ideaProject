/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 20:56:02 
 */
package hry.admin.ico.dividend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.dividend.model.IcoDividendRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoDividendRecordDao </p>
 * @author:         houz
 * @Date :          2019-01-14 20:56:02  
 */
public interface IcoDividendRecordDao extends  BaseDao<IcoDividendRecord, Long> {
    List<IcoDividendRecord> findPageBySql(Map<String, Object> map);

    List<Map<String,Object>> expenditureAccount(Map<String, Object> map);
}
