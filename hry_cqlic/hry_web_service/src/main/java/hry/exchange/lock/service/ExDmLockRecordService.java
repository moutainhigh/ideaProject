/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-04-27 16:33:26 
 */
package hry.exchange.lock.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.exchange.lock.model.ExDmLockRecord;
import hry.util.QueryFilter;

import java.util.List;


/**
 * <p> ExDmLockRecordService </p>
 * @author:         liuchenghui
 * @Date :          2018-04-27 16:33:26  
 */
public interface ExDmLockRecordService  extends BaseService<ExDmLockRecord, Long>{

	public PageResult findPageBySql(QueryFilter filter);

	List<ExDmLockRecord> getRecordBycurdate ();
}
