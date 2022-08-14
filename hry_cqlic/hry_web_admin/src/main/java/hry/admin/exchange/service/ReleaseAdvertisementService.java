/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 13:36:05 
 */
package hry.admin.exchange.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ReleaseAdvertisement;
import hry.util.QueryFilter;


/**
 * <p> ReleaseAdvertisementService </p>
 * @author:         denghf
 * @Date :          2018-10-29 13:36:05 
 */
public interface ReleaseAdvertisementService  extends BaseService<ReleaseAdvertisement, Long>{

    PageResult findPageAll (QueryFilter filter);
}
