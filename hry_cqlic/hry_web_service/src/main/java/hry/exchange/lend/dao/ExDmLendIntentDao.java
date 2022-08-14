/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2015年11月06日  14:57:13
 */
package hry.exchange.lend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.lend.model.ExDmLendIntent;
import hry.exchange.lend.model.vo.ExDmLendIntentAndCustomer;
import hry.manage.remote.model.LendIntent;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * 
 * @author: Gao Mimi 
 * @Date : 2 2016年5月26日 下午2:46:37
 */
public interface ExDmLendIntentDao extends BaseDao<ExDmLendIntent, Long> {
	
	public List<ExDmLendIntentAndCustomer> findPageBySql(Map<String, String> map);
	public List<LendIntent>findLendIntentList(Map<String, String> map);
	
}
