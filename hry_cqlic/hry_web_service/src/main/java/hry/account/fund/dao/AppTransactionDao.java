/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:50:05
 */
package hry.account.fund.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.account.fund.model.AppTransaction;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.manage.remote.model.AppTransactionManage;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:50:05 
 */
public interface AppTransactionDao extends BaseDao<AppTransaction, Long> {

	List<AppTransaction> findPageBySql(Map<String, Object> map);
	/**
	 * 
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Shangxl
	 * @param: @param filter
	 * @param: @return
	 * @return: PageResult
	 * @Date : 2017年7月10日 上午9:38:29
	 * @throws:
	 */
	List<AppTransaction> listPageBySql(Map<String, Object> map);

	BigDecimal countByDate(Map<String, Object> map);
	  
	List<AppTransactionManage> findTransaction(Map<String, String> map);
}
