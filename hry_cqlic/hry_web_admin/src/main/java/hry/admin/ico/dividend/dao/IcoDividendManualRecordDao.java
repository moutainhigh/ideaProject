/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-22 11:33:10 
 */
package hry.admin.ico.dividend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.dividend.model.IcoDividendManualRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoDividendManualRecordDao </p>
 * @author:         lzy
 * @Date :          2019-03-22 11:33:10  
 */
public interface IcoDividendManualRecordDao extends  BaseDao<IcoDividendManualRecord, Long> {
    List<IcoDividendManualRecord> findPageBySql(Map<String, Object> map);
}
