/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 20:10:54 
 */
package hry.admin.commend.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.commend.model.AppCommendUser;
import hry.util.QueryFilter;



/**
 * <p> AppCommendUserService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 20:10:54 
 */
public interface AppCommendUserService  extends BaseService<AppCommendUser, Long>{

    /**
     * sql分页
     * @param filter
     * @return
     */
    PageResult findPageBySql(QueryFilter filter);

    /**
     *
     * @param filter
     * @return
     */
    PageResult findConmmendPageBySql(QueryFilter filter);

    /**
     * 总业绩
     * @param filter
     * @return
     */
    PageResult icoFindPageBySql(QueryFilter filter);
    /**
     * 新增业绩
     * @param filter
     * @return
     */
    PageResult newResultsList(QueryFilter filter);
    /**
     * 业绩明细
     * @param filter
     * @return
     */
    PageResult assetsList(QueryFilter filter);
}
