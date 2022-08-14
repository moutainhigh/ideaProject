/**
 * Copyright:    
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 14:53:24 
 */
package hry.mall.platform.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.mall.platform.model.Daddress;
import java.util.List;
import java.util.Map;

/**
 * <p> DaddressDao </p>
 * @author:         luyue
 * @Date :          2018-11-16 14:53:24  
 */
public interface DaddressDao extends BaseDao<Daddress, Long> {
	public List<Daddress> findPageBySql(Map<String, Object> map);
}
