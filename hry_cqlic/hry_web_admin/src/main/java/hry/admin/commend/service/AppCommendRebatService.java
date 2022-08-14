/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:03 
 */
package hry.admin.commend.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.commend.model.AppCommendRebat;
import hry.util.QueryFilter;



/**
 * <p> AppCommendRebatService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:50:03 
 */
public interface AppCommendRebatService  extends BaseService<AppCommendRebat, Long>{


    PageResult findPageBySql(QueryFilter filter);
}
