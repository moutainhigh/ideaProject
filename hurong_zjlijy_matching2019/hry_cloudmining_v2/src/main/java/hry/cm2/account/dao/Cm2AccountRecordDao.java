/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 13:57:48 
 */
package hry.cm2.account.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.account.model.Cm2AccountRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> Cm2AccountRecordDao </p>
 * @author:         yaozh
 * @Date :          2019-10-14 13:57:48  
 */
public interface Cm2AccountRecordDao extends  BaseDao<Cm2AccountRecord, Long> {

    void insertRecord(List<Cm2AccountRecord> list);
    /**
     * 获取用户收益统计信息
     * @param customerId
     * @param coinCode
     * @return
     */
    public List<Cm2AccountRecord> getProfitStatistics(@Param("customerId") Long customerId, @Param("coinCode") String coinCode);

    List<Cm2AccountRecord> finePageAccountRecord (Map<String, String> params);

}
