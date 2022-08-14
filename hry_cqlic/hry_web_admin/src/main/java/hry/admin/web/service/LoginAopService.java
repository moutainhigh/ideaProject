/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:28 
 */
package hry.admin.web.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.web.model.LoginAop;
import hry.util.QueryFilter;


/**
 * <p> LoginAopService </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:42:28 
 */
public interface LoginAopService  extends BaseService<LoginAop, Long>{

    public PageResult findPageBySql(QueryFilter filter);

    /**
     * 加入/移除黑名单
     * @param userId      用户id
     * @param blackStatus 修改状态
     * @return
     */
    public JsonResult updateBlackStatus(String userId, Integer blackStatus);
}
