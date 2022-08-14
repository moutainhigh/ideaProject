/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:31:26 
 */
package hry.admin.klg.prizedraw.service;

import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.prizedraw.model.KlPrizedrawCustomer;
import hry.bean.PageResult;



/**
 * <p> KlPrizedrawCustomerService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-06 11:31:26 
 */
public interface KlPrizedrawCustomerService  extends BaseService<KlPrizedrawCustomer, Long>{
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
	PageResult findPageBySql(QueryFilter filter);
	
	/**
	 * 根据期号更新用户抽奖数据为已开奖
	 * @param issueNumber
	 */
	void updateStatusByIssueNumber(Integer issueNumber);


}
