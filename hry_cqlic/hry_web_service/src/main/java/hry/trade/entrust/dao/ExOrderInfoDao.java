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

import hry.core.mvc.dao.base.BaseDao;
import hry.manage.remote.model.Order;
import hry.trade.entrust.model.ExOrderInfo;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExOrderInfoDao extends BaseDao<ExOrderInfo, Long> {

	/**
	 * 获取一个用户的总买币额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年3月9日 上午10:29:32   
	 * @throws:
	 */
	BigDecimal getTotalBuyMoney(Long buyCustomId);

	/**
	  * 获取今天的最后一笔成交价
	  * <p> TODO</p>
	  * @author:         Zhang Lei
	  * @param:    @param buyCustomId
	  * @param:    @return
	  * @return: BigDecimal 
	  * @Date :          2017年3月9日 上午10:24:50   
	  * @throws:
	  */
	ExOrderInfo exAveragePrice(String coinCode, String fixPriceCoinCode);
	/**
	 * 获取昨天的最后一笔成交价
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param buyCustomId
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年3月9日 上午10:24:50   
	 * @throws:
	 */
	ExOrderInfo getAveragePriceYesterday(String coinCode);
	
	
	/**
	 * 前台分页查询
	 * @param params
	 * @return
	 */
	List<Order> findFrontPageBySql(Map<String, String> params);
	
	
	List<ExOrderInfo> selectFee(Map<String, String> params);
	
	/**
	 * 交易记录查询
	 * @param params
	 * @return
	 */
	List<Order> frontselectFee(Map<String, String> params);
	
	List<BigDecimal> yesterdayPrice();


	List<ExOrderInfo> selectTransaction(String username);

	int selectTransactionCount(String username);
	
	/**
	 * sql分页
	 * @param map
	 * @return
	 */
	List<ExOrderInfo> findPageBySql(Map<String, Object> map);
	
	
	/**
	 * 新sql分页
	 * @param map
	 * @return
	 */
	List<ExOrderInfo> findPageBySqlList(Map<String, Object> map);
	
	Long findPageBySqlCount(Map<String, Object> map);
	
	List<String> findPersonInfoByCondition(Map<String, Object> map);
	
	/**
	 * 统计指定用户指定时间指定交易币种的成交额
	 * @param map
	 * @return
	 */
	BigDecimal transactionAmount(Map<String, Object> map);
}
