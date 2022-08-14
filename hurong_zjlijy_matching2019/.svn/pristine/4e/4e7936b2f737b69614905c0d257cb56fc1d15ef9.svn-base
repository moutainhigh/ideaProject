/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-25 18:06:51 
 */
package hry.app.otc.releaseAdvertisement.service;

import hry.bean.FrontPage;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.otc.manage.remote.model.releaseAdvertisement.OtcAppTransaction;
import hry.util.QueryFilter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;



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

	/**
	 * 前台分页查询
	 * @param map
	 * @return
	 */
	public FrontPage findFrontPageBySql(Map<String, String> map);

	/**
	 * 统计广告下各个状态下订单的tradeMoney总数量
	 */
	public Map<String,BigDecimal> getTransactionSumById(Long advertisementId);

	List<OtcAppTransaction> findOtcAppTransactionListBySql (Map<String, String> map);

    void saveOtcLog (Long id);

	public int getOrderSumBy30(Long id,String coinCode);
}
