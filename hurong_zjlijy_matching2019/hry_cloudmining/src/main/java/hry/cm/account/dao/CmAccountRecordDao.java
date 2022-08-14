/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-26 15:51:49 
 */
package hry.cm.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.cm.account.model.CmAccountRecord;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> CmAccountRecordDao </p>
 * @author:         yaozh
 * @Date :          2019-07-26 15:51:49  
 */
public interface CmAccountRecordDao extends  BaseDao<CmAccountRecord, Long> {
	void insertRecord(List<CmAccountRecord> list);
	/**
	 * 获取用户收益统计信息	
	 * @param customerId
	 * @param coinCode
	 * @return
	 */
	public List<CmAccountRecord> getProfitStatistics(@Param("customerId") Long customerId,@Param("coinCode") String coinCode);
	
	 List<CmAccountRecord> finePageAccountRecord (Map<String, String> params);
}
