/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
package hry.exchange.lend.service;


import java.math.BigDecimal;
import java.util.Map;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmLendIntent;
import hry.manage.remote.model.base.FrontPage;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExDmLendIntentService extends BaseService<ExDmLendIntent, Long> {
	public ExDmLendIntent create(ExDmLend exDmLend, BigDecimal repayCount, String intentType);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param filter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年6月28日 下午3:44:47   
	 * @throws:
	 */
	public FrontPage listIntentPage(Map<String, String> params);
	
	
	/**
	 * 
	 * map里传的参数为  用户的名  转入开始的时间  结束时间  申请类型
	 * 
	 * @param map
	 * @return
	 */
	public PageResult findPageBySql(QueryFilter filter);
	
	
	
	
	
	
	
	

}
