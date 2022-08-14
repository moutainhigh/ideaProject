/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-26 15:51:49 
 */
package hry.cm.account.service;

import hry.core.mvc.service.base.BaseService;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.bean.FrontPage;
import hry.cm.account.model.CmAccountRecord;



/**
 * <p> CmAccountRecordService </p>
 * @author:         yaozh
 * @Date :          2019-07-26 15:51:49 
 */
public interface CmAccountRecordService  extends BaseService<CmAccountRecord, Long>{

	/**
	 * 获取用户收益统计信息	
	 * @param customerId
	 * @param coinCode
	 * @return
	 */
	public List<CmAccountRecord> getProfitStatistics(@Param("customerId") Long customerId,@Param("coinCode") String coinCode);
	
	FrontPage getAccountRecord (Map<String, String> params);

}
