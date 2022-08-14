/**
 * Copyright:  北京互融时代软件有限公司
 * @author:   Gao Mimi 
 * @version:   V1.0 
 * @Date:      2016年5月26日 下午2:46:37
 */
package hry.exchange.lend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.lend.model.ExDmLend;
import hry.manage.remote.model.Lend;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * 
 * @author: Gao Mimi 
 * @Date :  2016年5月26日 下午2:46:37
 */
public interface ExDmLendDao extends BaseDao<ExDmLend, Long> {
	public List<ExDmLend> getMayPingCustomer(Map<String, String> map);
	public List<ExDmLend> getLendBycustmer(String lendcoin, Long customerId, String currencyType, String website);
	public List<Lend> see(Map<String, String> map) ;
	public List<ExDmLend>  getLending(Map<String, String> map);
	public List<ExDmLend> getLendingByGroupCustomerId(Map<String, Object> map);
}
