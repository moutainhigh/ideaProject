/**
 * Copyright:    
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-04 10:25:33 
 */
package hry.social.friend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.friend.SocialFriendComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialFriendCommentDao </p>
 * @author:         javalx
 * @Date :          2019-06-04 10:25:33  
 */
public interface SocialFriendCommentDao extends  BaseDao<SocialFriendComment, Long> {

    /**
     * 根据帖子ID查询对应的评论
     * @param customerId
     * @param sfcId
     * @return
     */
    List<SocialFriendComment> getComments(@Param("customerId") Long customerId, @Param("sfcId") Long sfcId);
}
