/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 09:42:35
 */
package hry.social.friend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.friend.SocialFriendCircle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialFriendCircleDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-04 09:42:35
 */
public interface SocialFriendCircleDao extends BaseDao<SocialFriendCircle,Long> {

    /**
     * 分页查询
     *
     * @param map
     */
    List<SocialFriendCircle> findPageList(Map<String,String> map);

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    SocialFriendCircle see(@Param("id") Long id);

    //发帖
    Long friendCircle(@Param("socialFriendCircle") SocialFriendCircle socialFriendCircle);

    //保存图片
    void saveImg(@Param("sfcId") Long sfcId, @Param("name") String name, @Param("path") String path);

    /**
     * 查询好友最近的帖子
     *
     * @param customerId
     * @param palId
     * @return
     */
    List<String> findByCustomerId(@Param("customerId") Long customerId, @Param("palId") Long palId);

    /**
     * 查询用户最近的帖子
     *
     * @param palId
     * @return
     */
    List<String> findPersonCircle(@Param("palId") Long palId);

    /**
     * 周榜
     *
     * @return
     */
    List<SocialFriendCircle> weeklyRank();

    /**
     * 月榜
     *
     * @return
     */
    List<SocialFriendCircle> monthRank();

    /**
     * 更新朋友圈主题图片
     *
     * @param customerId
     * @param themeImg
     */
    void updateTheme(@Param("customerId") Long customerId, @Param("themeImg") String themeImg);
}
