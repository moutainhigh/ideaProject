/**
 * Copyright:    
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-31 14:33:31 
 */
package hry.mall.integral.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.mall.integral.model.IntegralCommission;

import java.util.List;
import java.util.Map;


/**
 * <p> IntegralCommissionDao </p>
 * @author:         luyue
 * @Date :          2019-05-31 14:33:31  
 */
public interface IntegralCommissionDao extends  BaseDao<IntegralCommission, Long> {
	public List<IntegralCommission>  findRecord(Map<String, Object> map);
}
