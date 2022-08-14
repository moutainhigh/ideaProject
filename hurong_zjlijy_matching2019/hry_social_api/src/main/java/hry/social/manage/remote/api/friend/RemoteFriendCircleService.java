/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2018-12-10 15:35:01
 */
package hry.social.manage.remote.api.friend;

import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;
import hry.social.manage.remote.model.friend.SocialFriendCircle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> SocialFriendCircleService </p>
 *
 * @author: lixin
 * @Date :          2018-12-10 15:35:01
 */
public interface RemoteFriendCircleService {

    /**
     * 分页查询朋友圈动态
     *
     * @param params
     * @return
     */
    FrontPage findPageList(Map<String,String> params);

    /**
     * 删除动态
     *
     * @param id
     * @return
     */
    RemoteResult delet(Long id);

    /**
     * 发朋友圈
     *
     * @return
     */
    RemoteResult issueCircle();

    /**
     * 查询评论（更多）
     *
     * @param sfcId      帖子ID
     * @param customerId 当前登录人
     * @return
     */
    RemoteResult findComments(Long sfcId, Long customerId);

    /**
     * 评论帖子
     *
     * @param sfcId      帖子ID
     * @param customerId 评论人
     * @param content    评论内容
     * @return
     */
    RemoteResult comments(Long sfcId, Long customerId, String content, Long contentId, Long friendId);


    /**
     * 点赞（打赏）
     *
     * @param sfcId      帖子ID
     * @param rewardNum  打赏金额
     * @param customerId 打赏人
     * @param acpwd      资金密码
     * @return
     */
    RemoteResult reward(Long sfcId, String rewardNum, Long customerId, String acpwd);

    /**
     * 发帖
     *
     * @param
     * @param customerId
     * @return
     */
    void friendCircle(Long customerId, String content, String link, String linkTitle, String linkImage, String device, String site, String[] pathImg);

    /**
     * 设置朋友圈个人主题
     *
     * @param customerId
     * @param themeImg
     * @return
     */
    RemoteResult theme(Long customerId, String[] themeImg);

    FrontPage findRewardPage(Map<String,String> params);

    /**
     * 周榜
     *
     * @param customerId
     * @return
     */
    List<SocialFriendCircle> weeklyRank(Long customerId);

    /**
     * 月榜
     *
     * @param customerId
     * @return
     */
    List<SocialFriendCircle> monthRank(Long customerId);

    /**
     * 查询总打赏值
     *
     * @param customerId
     * @return
     */
    BigDecimal friendReward(Long customerId);
}
