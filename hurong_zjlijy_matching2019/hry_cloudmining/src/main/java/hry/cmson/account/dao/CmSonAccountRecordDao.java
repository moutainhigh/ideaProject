/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-04 10:12:46 
 */
package hry.cmson.account.dao;

import hry.cmson.account.model.CmSonAccountRecord;
import hry.core.mvc.dao.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> CmSonAccountRecordDao </p>
 * @author:         yaozh
 * @Date :          2019-11-04 10:12:46  
 */
public interface CmSonAccountRecordDao extends  BaseDao<CmSonAccountRecord, Long> {
    void insertRecord(List<CmSonAccountRecord> list);
    /**
     * 获取用户收益统计信息
     * @param customerId
     * @param coinCode
     * @return
     */
    public List<CmSonAccountRecord> getProfitStatistics(@Param("customerId") Long customerId, @Param("coinCode") String coinCode);

    List<CmSonAccountRecord> finePageAccountRecord (Map<String, String> params);
}
