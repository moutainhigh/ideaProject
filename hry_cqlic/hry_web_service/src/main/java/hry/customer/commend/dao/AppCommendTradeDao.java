/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 17:40:59 
 */
package hry.customer.commend.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.commend.model.AppCommendTrade;
import hry.customer.commend.model.AppCommendUser;
import hry.customer.person.model.AppPersonInfo;
import hry.trade.entrust.model.ExOrderInfo;


/**
 * <p> AppCommendTradeDao </p>
 * @author:         menwei
 * @Date :          2017-11-28 17:40:59  
 */
public interface AppCommendTradeDao extends  BaseDao<AppCommendTrade, Long> {

	BigDecimal findOne(@Param("userName") String userName,@Param("fixPriceCoinCode") String fixPriceCoinCode);

	BigDecimal findTwo(@Param("userName") String userName,@Param("fixPriceCoinCode") String fixPriceCoinCode);


	BigDecimal findThree(@Param("userName") String userName, @Param("fixPriceCoinCode") String fixPriceCoinCode);

	BigDecimal findLater(@Param("userName") String userName,@Param("fixPriceCoinCode") String fixPriceCoinCode);

	List<AppCommendTrade> selectCommendTrade(@Param("custromerName") String custromerName);

    List<AppCommendTrade> findByUids(List<Long> pids);

    List<AppCommendTrade> findByUsername(@Param("username") String username);
   	 /**
     * sql分页总条数
     * @param map
     * @return
     */
    Long  findPageBySqlCount(Map<String, Object> map);
	 /**
	     * sql分页
	     * @param map
	     * @return
	 */
	List<AppCommendTrade> findPageBySqlList(Map<String, Object> map);
	/**
	 * 
	 * @param map
	 * @return
	 */
	List<AppPersonInfo>  findCustomerIdByemailAndphone(Map<String, Object> map);
	
	List<ExOrderInfo> findExOrderInfos(Map<String, Object> map);
	void updateIsCulStatus();
	
	/**
	 * 推荐返佣明细分页
	 * @param map
	 * @return
	 */
	List<AppCommendTrade> findPageBySql(Map<String, Object> map);
	
	/**
	 * 统计用户指定时间段的佣金
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> countCommendMoney(Map<String, Object> map);
	
	/**
	 * 查询用户指定时间段的佣金记录
	 * @param map
	 * @return
	 */
	List<AppCommendTrade> findByCustromerIdList(Map<String, Object> newMap);
}
