/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-29 10:05:55 
 */
package hry.customer.commend.service;

import java.math.BigDecimal;
import java.util.List;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.commend.model.AppCommendMoney;



/**
 * <p> AppCommendMoneyService </p>
 * @author:         menwei
 * @Date :          2017-11-29 10:05:55  
 */
public interface AppCommendMoneyService  extends BaseService<AppCommendMoney, Long>{

	List<AppCommendMoney> selectCommend();

	BigDecimal findOne(String userName, String fixPriceCoinCode);

	BigDecimal findTwo(String userName, String fixPriceCoinCode);

	BigDecimal findThree(String userName, String fixPriceCoinCode);

	BigDecimal findLater(String userName, String fixPriceCoinCode);

	JsonResult postMoneyById(Long id, BigDecimal money);

	 PageResult findPageBySqlList(String email, String mobilePhone, int start, int length);
	 /**
	  * 推荐派发分页
	  * @param filter
	  * @return
	  */
	 PageResult findPageBySql(QueryFilter filter);
}
