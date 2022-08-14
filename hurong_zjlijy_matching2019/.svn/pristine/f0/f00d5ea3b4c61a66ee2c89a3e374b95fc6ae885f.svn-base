/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-10 15:08:37
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p> SocialSnsPosts </p>
 * 帖子实体
 *
 * @author: lixin
 * @Date : 2019-05-10 15:08:37
 */
@Table(name = "social_sns_posts")
public class SocialSnsPosts extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //ID

    @Column(name = "customerId")
    private Long customerId;  //用户ID

    @Column(name = "title")
    private String title;  //标题

    @Column(name = "content")
    private String content;  //内容

    @Column(name = "hasPicture")
    private Integer hasPicture;  //是否有图片 0：否；1：是

    @Column(name = "hasVideo")
    private Integer hasVideo;  //是否有视频 0：否；1：是

    @Column(name = "communityKey")
    private String communityKey;  //社区KEY

    @Column(name = "communityId")
    private Long communityId;  //社区

    @Column(name = "topicId")
    private Long topicId;  //话题

    @Column(name = "hasWarn")
    private Integer hasWarn;  //提醒查看 0：否；1：是

    @Column(name = "isPaid")
    private Integer isPaid;  //是否付费 0：否；1：是

    @Column(name = "price")
    private BigDecimal price;  //付费帖子价格

    @Column(name = "isRecommend")
    private Integer isRecommend;  //是否推荐 0：否；1：是

    @Column(name = "states")
    private Integer states;  //状态 0：已删除；1：待审核；2：已发布

    @Column(name = "device")
    private String device;  //发布设备

    @Column(name = "site")
    private String site;  //位置

    @Transient
    private String nickName;  //昵称

    @Transient
    private String headPortrait;  //头像

    @Transient
    private String accid;  //accid OR username

    @Transient
    private List<String> pics; // 帖子图片

    @Transient
    private List<String> videos; // 帖子视频

    @Transient
    private Integer countPraise; // 帖子点赞数

    @Transient
    private BigDecimal countReward; // 帖子打赏数

    @Transient
    private Integer countComment; // 帖子评论数

    @Transient
    private Integer countFavorite; // 帖子收藏数

    @Transient
    private Integer countShare; // 帖子分享数

    @Transient
    private Integer hasPraise; // 帖子是否被当前用户点赞（0：否；1：是）

    @Transient
    private Integer hasReward; // 帖子是否被当前用户打赏（0：否；1：是）

    @Transient
    private Integer hasComment; // 帖子是否被当前用户评论（0：否；1：是）

    @Transient
    private Integer hasFavorite; // 帖子是否被当前用户收藏（0：否；1：是）

    @Transient
    private Integer hasShare; // 帖子是否被当前用户分享（0：否；1：是）

    @Transient
    private String releaseTime; // 帖子发布时间

    @Override
    public String toString() {
        return "SocialSnsPosts{" + "id=" + id + ", customerId=" + customerId + ", title='" + title + '\'' + ", content='" + content + '\'' + ", hasPicture=" + hasPicture + ", hasVideo=" + hasVideo + ", communityKey='" + communityKey + '\'' + ", communityId=" + communityId + ", topicId=" + topicId + ", hasWarn=" + hasWarn + ", isPaid=" + isPaid + ", price=" + price + ", isRecommend=" + isRecommend + ", states=" + states + ", device='" + device + '\'' + ", site='" + site + '\'' + ", nickName='" + nickName + '\'' + ", headPortrait='" + headPortrait + '\'' + ", accid='" + accid + '\'' + ", pics=" + pics + ", videos=" + videos + ", countPraise=" + countPraise + ", countReward=" + countReward + ", countComment=" + countComment + ", countFavorite=" + countFavorite + ", countShare=" + countShare + ", hasPraise=" + hasPraise + ", hasReward=" + hasReward + ", hasComment=" + hasComment + ", hasFavorite=" + hasFavorite + ", hasShare=" + hasShare + ", releaseTime='" + releaseTime + '\'' + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHasPicture() {
        return hasPicture;
    }

    public void setHasPicture(Integer hasPicture) {
        this.hasPicture = hasPicture;
    }

    public Integer getHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(Integer hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getCommunityKey() {
        return communityKey;
    }

    public void setCommunityKey(String communityKey) {
        this.communityKey = communityKey;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Integer getHasWarn() {
        return hasWarn;
    }

    public void setHasWarn(Integer hasWarn) {
        this.hasWarn = hasWarn;
    }

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public Integer getCountPraise() {
        return countPraise;
    }

    public void setCountPraise(Integer countPraise) {
        this.countPraise = countPraise;
    }

    public BigDecimal getCountReward() {
        return countReward;
    }

    public void setCountReward(BigDecimal countReward) {
        this.countReward = countReward;
    }

    public Integer getCountComment() {
        return countComment;
    }

    public void setCountComment(Integer countComment) {
        this.countComment = countComment;
    }

    public Integer getCountFavorite() {
        return countFavorite;
    }

    public void setCountFavorite(Integer countFavorite) {
        this.countFavorite = countFavorite;
    }

    public Integer getCountShare() {
        return countShare;
    }

    public void setCountShare(Integer countShare) {
        this.countShare = countShare;
    }

    public Integer getHasPraise() {
        return hasPraise;
    }

    public void setHasPraise(Integer hasPraise) {
        this.hasPraise = hasPraise;
    }

    public Integer getHasReward() {
        return hasReward;
    }

    public void setHasReward(Integer hasReward) {
        this.hasReward = hasReward;
    }

    public Integer getHasComment() {
        return hasComment;
    }

    public void setHasComment(Integer hasComment) {
        this.hasComment = hasComment;
    }

    public Integer getHasFavorite() {
        return hasFavorite;
    }

    public void setHasFavorite(Integer hasFavorite) {
        this.hasFavorite = hasFavorite;
    }

    public Integer getHasShare() {
        return hasShare;
    }

    public void setHasShare(Integer hasShare) {
        this.hasShare = hasShare;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
    
}
