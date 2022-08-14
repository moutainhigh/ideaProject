/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 14:34:44 
 */
package hry.admin.web.service;

import hry.admin.web.model.AppBanner;
import hry.core.mvc.service.base.BaseService;


/**
 * <p> AppBannerService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 14:34:44 
 */
public interface AppBannerService  extends BaseService<AppBanner, Long>{
    
    public void initCache();

}
