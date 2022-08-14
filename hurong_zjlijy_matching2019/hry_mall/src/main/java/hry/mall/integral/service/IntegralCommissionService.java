/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-31 14:33:31 
 */
package hry.mall.integral.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.IntegralCommission;

import java.util.List;


/**
 * <p> IntegralCommissionService </p>
 * @author:         luyue
 * @Date :          2019-05-31 14:33:31 
 */
public interface IntegralCommissionService  extends BaseService<IntegralCommission, Long>{

	public List<IntegralCommission>  findRecord(Long memberId, Integer status);

	/**
	 * 根据推荐奖励记录发放团队分红
	 * @return
	 */
	JsonResult grantTeamDividend(IntegralCommission integralCommission);

}
