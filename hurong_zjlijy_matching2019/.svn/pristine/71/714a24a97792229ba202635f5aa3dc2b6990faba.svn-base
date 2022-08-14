/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 19:58:03
 */
package hry.social.task.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.manage.remote.model.AppDic;
import hry.social.manage.remote.model.task.SocialTaskItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialTaskItemDao </p>
 * @author: javalx
 * @Date :          2019-06-03 19:58:03  
 */
public interface SocialTaskItemDao extends BaseDao<SocialTaskItem,Long> {

    List<AppDic> categoryList(@Param("typeKey") String typeKey);

    /**
     * 根据任务标识查询任务
     * @param taskMark
     * @return
     */
    SocialTaskItem getByTaskMark(@Param("taskMark") String taskMark);

    /**
     * 根据邀请码查询邀请数
     * @param inviteCode
     * @return
     */
    int getInviteNum(@Param("inviteCode")String inviteCode);

}
