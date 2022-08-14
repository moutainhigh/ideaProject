/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 13:36:05 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.ReleaseAdvertisement;

import java.util.List;
import java.util.Map;


/**
 * <p> ReleaseAdvertisementDao </p>
 * @author:         denghf
 * @Date :          2018-10-29 13:36:05  
 */
public interface ReleaseAdvertisementDao extends  BaseDao<ReleaseAdvertisement, Long> {

    List<ReleaseAdvertisement> findPageByAll(Map<String,Object> map);
}
