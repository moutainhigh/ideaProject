/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-04-25 11:46:57 
 */
package hry.exchange.lock.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.exchange.lock.model.ExDmLock;



/**
 * <p> ExDmLockService </p>
 * @author:         liuchenghui
 * @Date :          2018-04-25 11:46:57  
 */
public interface ExDmLockService  extends BaseService<ExDmLock, Long>{

	public PageResult findPageBySql(QueryFilter filter);

	public boolean findByCoinCode(String coinCode);
	
	public ExDmLock findByCoinCodeInfo(String coinCode);

}
