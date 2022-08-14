/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:28 
 */
package hry.admin.web.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.web.model.LoginAop;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> LoginAopDao </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:42:28  
 */
public interface LoginAopDao extends  BaseDao<LoginAop, Long> {
    /**
     * <p>黑名单分页查询</p>
     * @author:   zhangy
     * @param:    @param map
     * @Date :    2018-4-25 19:40:22
     * @throws:
     */
    List<LoginAop> findPageBySql(Map<String,Object> map);

    /**
     * 加入/移除黑名单
     * @param userId      用户id
     * @param blackStatus 修改状态
     * @return
     */
    void updateBlackStatus(@Param("userId") String userId, @Param("blackStatus") Integer blackStatus);
}
