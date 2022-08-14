/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-25 18:06:51 
 */
package hry.otc.releaseAdvertisement.service;

import java.math.BigDecimal;
import java.util.Map;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.otc.releaseAdvertisement.model.OtcAppTransaction;
import hry.util.QueryFilter;



/**
 * <p> OtcAppTransactionService </p>
 * @author:         denghf
 * @Date :          2018-06-25 18:06:51  
 */
public interface OtcAppTransactionService  extends BaseService<OtcAppTransaction, Long>{

	public BigDecimal adUserBy30(Long id,String coinCode);
	
	public BigDecimal adUserAll(Long id,String coinCode);
	
	public Integer allTradeCount(Map<String, Object> map);

	PageResult findPageBySql (QueryFilter filter, Integer type);
}
