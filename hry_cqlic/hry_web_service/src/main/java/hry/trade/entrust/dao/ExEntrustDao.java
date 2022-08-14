/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2015年11月06日  14:57:13
 */
package hry.trade.entrust.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.manage.remote.model.Entrust;
import hry.trade.entrust.model.ExEntrust;

/**
 * 
 * <p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午1:34:18
 */
public interface ExEntrustDao extends BaseDao<ExEntrust, Long> {
	List<ExEntrust> getbuyExEntrustChange(Map<String, Object> map);
	List<ExEntrust> getsellExEntrustChange(Map<String, Object> map);
	List<ExEntrust>listMatchBySellLimitedPrice(Map<String, Object> map);
	List<ExEntrust> listMatchByBuyLimitedPrice(Map<String, Object> map);
	List<ExEntrust> getExEntrustBuyDeph(Map<String, Object> map);
	List<ExEntrust> getExEntrustSellDeph(Map<String, Object> map);


	public Map<String,BigDecimal> getExEntrustmMostPrice(Map<String, Object> map);

	//查找货币冠军                                       
	public List<ExEntrust> getFirstCoin();
	public List<String> getFirstCoinNum();
	//前台分页查询
	public List<Entrust> findFrontPageBySql(Map<String, String> params);
	/***
	 * 通过id得到几个列
	 * @return
	 */
	public ExEntrust getById(@Param(value = "id") Long id);

	List<ExEntrust> getExEdBycustomerId(Map<String, Object> map);
	List<ExEntrust> getExIngBycustomerId(Map<String, Object> map);
	
	/**
	 * sql分页
	 * @param map
	 * @return
	 */
	List<ExEntrust> findPageBySql(Map<String, Object> map);
	
	/**
	 * 新sql分页
	 * @param map
	 * @return
	 */
	List<ExEntrust> findPageBySqlList(Map<String, Object> map);
	/**
	 * 新分页统计总数
	 * @param map
	 * @return
	 */
	Long findPageBySqlCount(Map<String, Object> map);
	
	/**
	 * 根据条件查询客户信息
	 * @param customerMap
	 * @return
	 */
	List<String> findCustomerByCondition(Map<String, Object> customerMap);
}
