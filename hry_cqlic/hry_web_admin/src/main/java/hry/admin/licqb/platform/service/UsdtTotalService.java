/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 12:01:59 
 */
package hry.admin.licqb.platform.service;

import hry.admin.licqb.platform.model.UsdtTotal;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;


/**
 * <p> UsdtTotalService </p>
 * @author:         zhouming
 * @Date :          2020-06-11 12:01:59 
 */
public interface UsdtTotalService  extends BaseService<UsdtTotal, Long>{

    public PageResult findPageUserTotalUsdt(QueryFilter filter);

    public PageResult findUserDetailPageList(QueryFilter filter);
}
