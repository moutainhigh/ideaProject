/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 20:17:08
 */
package hry.social.friend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.friend.SocialFriendApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialFriendApplyDao </p>
 * @author: javalx
 * @Date :          2019-06-03 20:17:08  
 */
public interface SocialFriendApplyDao extends BaseDao<SocialFriendApply,Long> {

    /**
     * 查看好友申请记录列表
     *
     * @param customerId
     * @return
     */
    List<SocialFriendApply> findApplyList(@Param("customerId") Long customerId);

    /**
     * 查看未处理的好友申请记录数
     *
     * @param customerId
     * @return
     */
    int hasNotDeal(@Param("customerId") Long customerId);

    /**
     * 拒绝好友申请
     *
     * @param id
     */
    void refuseApply(@Param("id") Long id);

    /**
     * 接受好友申请
     *
     * @param id
     */
    void acceptApply(@Param("id") Long id);
}
