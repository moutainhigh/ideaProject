/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-15 14:33:22 
 */
package hry.ico.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.ico.model.IcoTransactionRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoTransactionRecordDao </p>
 * @author:         denghf
 * @Date :          2019-01-15 14:33:22  
 */
public interface IcoTransactionRecordDao extends  BaseDao<IcoTransactionRecord, Long> {

    List<IcoTransactionRecord> findPageBySql(Map<String ,String> map);

    IcoTransactionRecord getCBTransaction(Map<String,String> map);
}
