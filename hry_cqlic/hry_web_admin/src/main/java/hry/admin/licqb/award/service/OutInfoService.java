/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:49:07 
 */
package hry.admin.licqb.award.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.licqb.award.model.OutInfo;
import hry.util.QueryFilter;


/**
 * <p> OutInfoService </p>
 * @author:         zhouming
 * @Date :          2019-08-13 13:49:07 
 */
public interface OutInfoService  extends BaseService<OutInfo, Long>{

    public PageResult findUserList(QueryFilter filter);

    public PageResult findSonUserList(QueryFilter filter);

    public PageResult findUserAsset(QueryFilter filter);

}
