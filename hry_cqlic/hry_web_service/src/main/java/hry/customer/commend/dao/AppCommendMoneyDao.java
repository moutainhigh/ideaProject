/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-29 10:05:55 
 */
package hry.customer.commend.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.agents.model.vo.AgentsForMoney;
import hry.customer.commend.model.AppCommendMoney;
import hry.customer.commend.model.AppCommendTrade;
import org.apache.ibatis.annotations.Param;


/**
 * <p> AppCommendMoneyDao </p>
 * @author:         menwei
 * @Date :          2017-11-29 10:05:55  
 */
public interface AppCommendMoneyDao extends  BaseDao<AppCommendMoney, Long> {

	List<AppCommendMoney> findAgentsForMoney(@Param("custromerId") Long custromerId, @Param("fixPriceCoinCode") String fixPriceCoinCode);
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
	 * 推荐派发分页
	 * @param map
	 * @return
	 */
	List<AppCommendMoney> findPageBySql(Map<String, Object> map);
}
