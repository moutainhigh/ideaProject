/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao mimi
 * @version:     V1.0 
 * @Date:        2016-12-12 19:39:38 
 */
package hry.exchange.account.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.exchange.account.model.AppAccountDisable;



/**
 * <p> AppAccountDisableService </p>
 * @author:         Gao mimi
 * @Date :          2016-12-12 19:39:38  
 */
public interface AppAccountDisableService  extends BaseService<AppAccountDisable, Long>{

	  public JsonResult  coindisable(String transactionCount, String id, String remark);
	  public JsonResult  encoindisable(String ids);

}
