/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 17:40:59 
 */
package hry.customer.commend.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.commend.model.AppCommendTrade;
import hry.customer.commend.model.AppCommendUser;
import hry.customer.user.model.AppCustomer;
import hry.trade.entrust.model.ExOrderInfo;



/**
 * <p> AppCommendTradeService </p>
 * @author:         menwei
 * @Date :          2017-11-28 17:40:59  
 */
public interface AppCommendTradeService  extends BaseService<AppCommendTrade, Long>{

	Boolean dealCommission(String orderNum);


	BigDecimal selectCommissionByMoney(BigDecimal transactionBuyFee);

	BigDecimal findOne(String userName, String fixPriceCoinCode);

	BigDecimal findTwo(String userName, String fixPriceCoinCode);

	BigDecimal findThree(String userName, String fixPriceCoinCode);

	BigDecimal findLater(String userName, String fixPriceCoinCode);

	List<AppCommendTrade> selectCommendTrade(String username);


    List<AppCommendTrade> findByUids(List<Long> pids);

    List<AppCommendTrade> findByUsername(String username);
    
 
    PageResult findPageBySqlList(String email, String mobilePhone, String deliveryEmail, String deliveryMobilePhone, int start, int length);
    /**
     * 推荐明细分页
     * @param filter
     * @return
     */
    PageResult findPageBySql(QueryFilter filter);

    /**
	 *东方城项目佣金派发
	 * @param map
	 * @return
	 */
	JsonResult payCommissionsMoney(Map<String, Object> map);
}
