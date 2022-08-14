/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-22 11:39:46 
 */
package hry.otc.releaseAdvertisement.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.otc.releaseAdvertisement.model.ReleaseAdvertisement;

import java.util.List;
import java.util.Map;


/**
 * <p> ReleaseAdvertisementDao </p>
 * @author:         denghf
 * @Date :          2018-06-22 11:39:46  
 */
public interface ReleaseAdvertisementDao extends  BaseDao<ReleaseAdvertisement, Long> {
	
	String avgTime(Long customerId);

	List<ReleaseAdvertisement> findPageHall(Map<String, String> map);
	
	List<ReleaseAdvertisement> findPageBySql(Map<String, String> map);

	List<ReleaseAdvertisement> findPageByAll (Map<String,Object> map);
}
