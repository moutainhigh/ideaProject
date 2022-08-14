/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 17:40:59 
 */
package hry.customer.commend.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.commend.model.AppCommendRebat;
import hry.customer.commend.model.AppCommendUser;
import hry.trade.entrust.model.ExOrderInfo;

import java.math.BigDecimal;
import java.util.List;



/**
 * <p> AppCommendTradeService </p>
 * @author:         menwei
 * @Date :          2017-11-28 17:40:59  
 */
public interface AppCommendRebatService extends BaseService<AppCommendRebat, Long>{
	 PageResult findPageBySqlList(String email, String mobilePhone, int start, int length);
	 /**
	  * 返佣记录分页
	  * @param filter
	  * @return
	  */
	 PageResult findPageBySql(QueryFilter filter);
}
