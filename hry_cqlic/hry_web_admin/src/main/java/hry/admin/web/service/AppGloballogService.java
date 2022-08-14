/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-20 17:47:17 
 */
package hry.admin.web.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.web.model.AppGloballog;



/**
 * <p> AppGloballogService </p>
 * @author:         tianpengyu
 * @Date :          2018-09-20 17:47:17 
 */
public interface AppGloballogService  extends BaseService<AppGloballog, Long>{

    //定时清除无用日志
    void deleteOnTime();
}
