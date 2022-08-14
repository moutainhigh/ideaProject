/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-07-31 15:43:26 
 */
package hry.cm.dividend.dao;

import hry.core.mvc.dao.base.BaseDao;

import java.math.BigDecimal;
import java.util.Map;

import hry.cm.dividend.model.CmFeeRecord;


/**
 * <p> cmFeeRecordDao </p>
 * @author:         zhouming
 * @Date :          2019-07-31 15:43:26  
 */
public interface CmFeeRecordDao extends  BaseDao<CmFeeRecord, Long> {
	
	/**
	 * 查询选中id中数据手续费总数
	 * @param map
	 * @return
	 */
	public BigDecimal findFeeSumByIds(Map<String,Object> map);

}
