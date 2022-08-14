package hry.social.manage.remote.api.sns;


import hry.bean.FrontPage;
import hry.social.manage.remote.model.base.RemoteResult;
import hry.social.manage.remote.model.sns.SocialSnsCommunity;
import hry.social.manage.remote.model.sns.SocialSnsSubject;
import hry.social.manage.remote.model.sns.SocialSnsTopic;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author : javalx
 * @version : V1.0
 * @desc : SNS帖子社交API
 * @date : 2019/5/31 17:23
 */
public interface RemoteSnsService {

    /**
     * 分页查询用户关注的帖子
     *
     * @param params 分页查询参数
     * @return
     */
    FrontPage concernedPostPage(Map<String,String> params);

    /**
     * 发现频道的帖子分页查询
     *
     * @param params 分页查询参数
     * @return
     */
    FrontPage discoverPost(Map<String,String> params);

    /**
     * 最近(新)的帖子分页查询
     *
     * @param params 分页查询参数
     * @return
     */
    FrontPage recentPost(Map<String,String> params);

    /**
     * 社区&话题分类信息查询
     *
     * @param language 语种Key
     * @return
     */
    List<SocialSnsSubject> subjectInfo(String language);

    /**
     * 社区信息查询
     *
     * @param language 语种Key
     * @return
     */
    List<SocialSnsCommunity> communityInfo(String language);

    /**
     * 添加帖子提交
     *
     * @param customerId   发帖人ID
     * @param content      内容
     * @param title        标题
     * @param communityKey 社区KEY
     * @param topicId      话题ID
     * @param warns        @的用户的ID
     * @param price        收费帖子价格
     * @param device       设备
     * @param site         位置
     * @param fileMap      上传文件集合
     * @return
     */
    RemoteResult addPost(Long customerId, String content, String title, String communityKey, Long topicId, Long[] warns, BigDecimal price, String device, String site, Map<String,MultipartFile> fileMap);

    /**
     * 话题信息查询
     *
     * @param type       查询类型(0 : 所有; 1 : 自建)
     * @param customerId 用户ID
     * @return
     */
    List<SocialSnsTopic> topicInfo(String type, Long customerId);

    /**
     * 话题信息添加
     *
     * @param customerId 用户ID
     * @param name       话题名称
     * @param typeKey    分类key
     * @return
     */
    RemoteResult addTopic(Long customerId, String name, String typeKey);

    /**
     * 付费配置信息
     *
     * @return
     */
    RemoteResult payConfig();

    /**
     * 付费帖子购买
     *
     * @param postsId    帖子ID
     * @param customerId 付费人ID
     * @return
     */
    RemoteResult payPost(Long postsId, Long customerId);

    /**
     * 帖子打赏
     *
     * @param postsId    帖子ID
     * @param customerId 人ID
     * @return
     */
    RemoteResult rewardPost(Long postsId, Long customerId);

    /**
     * 帖子收藏
     *
     * @param postsId    帖子ID
     * @param customerId 收藏人ID
     * @return
     */
    RemoteResult favoritePost(Long postsId, Long customerId);

    /**
     * 帖子点赞
     *
     * @param postsId    帖子ID
     * @param customerId 点赞人ID
     * @return
     */
    RemoteResult praisePost(Long postsId, Long customerId);

    /**
     * 帖子分享
     *
     * @param postsId    帖子ID
     * @param customerId 分享人ID
     * @return
     */
    RemoteResult sharePost(Long postsId, Long customerId);

    /**
     * 帖子评论
     *
     * @param postsId    帖子ID
     * @param commId     评论ID
     * @param customerId 评论人ID
     * @param comment    评论内容
     * @return
     */
    RemoteResult commentPost(Long postsId, Long commId, Long customerId, String comment);

}
