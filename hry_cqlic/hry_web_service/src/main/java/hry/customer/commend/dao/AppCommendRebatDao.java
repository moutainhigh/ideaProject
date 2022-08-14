/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 17:40:59 
 */
package hry.customer.commend.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.commend.model.AppCommendRebat;
import hry.customer.commend.model.AppCommendTrade;


/**
 * <p> AppCommendTradeDao </p>
 * @author:         menwei
 * @Date :          2017-11-28 17:40:59  
 */
public interface AppCommendRebatDao extends  BaseDao<AppCommendRebat, Long> {
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
	 * 返佣派发记录分页
	 * @param map
	 * @return
	 */
	List<AppCommendRebat> findPageBySql(Map<String, Object> map);
}
