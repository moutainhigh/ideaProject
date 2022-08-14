/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-20 15:43:03 
 */
package hry.cm4.account.dao;

import hry.cm4.account.model.Cm4AccountRecord;
import hry.core.mvc.dao.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> Cm4AccountRecordDao </p>
 * @author:         yaozh
 * @Date :          2019-11-20 15:43:03  
 */
public interface Cm4AccountRecordDao extends  BaseDao<Cm4AccountRecord, Long> {
    void insertRecord(List<Cm4AccountRecord> list);
    /**
     * 获取用户收益统计信息
     * @param customerId
     * @param coinCode
     * @return
     */
    public List<Cm4AccountRecord> getProfitStatistics(@Param("customerId") Long customerId, @Param("coinCode") String coinCode);

    List<Cm4AccountRecord> finePageAccountRecord (Map<String, String> params);
}
