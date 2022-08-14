/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-10-08 13:45:53 
 */
package hry.admin.index.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.index.model.AppYesterdayData;



/**
 * <p> AppYesterdayDataService </p>
 * @author:         liuchenghui
 * @Date :          2018-10-08 13:45:53 
 */
public interface AppYesterdayDataService  extends BaseService<AppYesterdayData, Long>{


    void initRedis ();
}
