/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年7月18日 上午10:53:57
 */
package hry.customer.agents.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.agents.model.CommissionDetail;
import hry.customer.agents.model.vo.CommissionForAgents;

import org.apache.ibatis.annotations.Param;

/**
 * @author Wu shuiming
 * @date 2016年7月18日 上午10:53:57
 */                                                 
public interface CommissionDetailDao extends BaseDao<CommissionDetail, Long> {

	
	/**
	 * 根据用户的名字查询出 用户的所有佣金包含 交易佣金 存放到CommissionForAgents 这个对象里
	 * 
	 * CommissionForAgents 里的每一个属性都有可能是用户的的所收的佣金数  
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月25日 上午11:14:39
	 */                                                        
	public CommissionForAgents findMoneyByCustromerName(@Param(value = "custromerName") String custromerName);
	
}
