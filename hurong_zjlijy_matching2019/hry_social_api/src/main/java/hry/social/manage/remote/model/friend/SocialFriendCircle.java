/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 09:42:35
 */
package hry.social.manage.remote.model.friend;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p> SocialFriendCircle </p>
 *
 * @author: javalx
 * @Date : 2019-06-04 09:42:35
 */
@Table(name = "social_friend_circle")
public class SocialFriendCircle extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "content")
    private String content;  //文章正文

    @Column(name = "link")
    private String link;  //链接（只能存在一个）

    @Column(name = "linkTitle")
    private String linkTitle;  //链接标题

    @Column(name = "linkImage")
    private String linkImage;  //链接配图

    @Column(name = "picture")
    private String picture;  //图片（图片之间用竖杠隔开）

    @Column(name = "video")
    private String video;  //视频

    @Column(name = "music")
    private String music;  //音乐

    @Column(name = "customerId")
    private Long customerId;  //发布人ID

    @Column(name = "device")
    private String device;  //发布设备

    @Column(name = "site")
    private String site;  //发布地点（位置）

    @Column(name = "likeCustomerIds")
    private String likeCustomerIds;  //点赞人

    @Column(name = "issued")
    private Integer issued;  //发布状态（1，已发布；2，已删除；）

    @Column(name = "issuedTime")
    private Date issuedTime;  //发布时间

    @Column(name = "deleteTime")
    private Date deleteTime;  //删除时间

    @Transient
    private String nickName;  //昵称

    @Transient
    private String headPortrait;  //头像

    @Transient
    private String mobilePhone;  //手机号

    @Transient
    private String likeNum;  //点赞数

    @Transient
    private List<String> imageUrls;  //图片路径

    @Transient
    private List<SocialFriendPicture> pictures;  //图片

    @Transient
    private List<SocialFriendComment> comments;  //评论

    @Transient
    private List<SocialFriendReward> rewards;  //点赞

    @Transient
    private BigDecimal totalRewards;  //总打赏数量

    @Transient
    private String time;  //app时间
//
//    @Transient
//    private Integer isReal;  //是否实名

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<SocialFriendComment> getComments() {
        return comments;
    }

    public void setComments(List<SocialFriendComment> comments) {
        this.comments = comments;
    }

    public List<SocialFriendReward> getRewards() {
        return rewards;
    }

    public void setRewards(List<SocialFriendReward> rewards) {
        this.rewards = rewards;
    }

    public BigDecimal getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(BigDecimal totalRewards) {
        this.totalRewards = totalRewards;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<SocialFriendPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<SocialFriendPicture> pictures) {
        this.pictures = pictures;
    }

    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 09:42:35
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @param: @param id
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>文章正文</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getContent() {
        return content;
    }

    /**
     * <p>文章正文</p>
     *
     * @author: javalx
     * @param: @param content
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * <p>链接（只能存在一个）</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getLink() {
        return link;
    }

    /**
     * <p>链接（只能存在一个）</p>
     *
     * @author: javalx
     * @param: @param link
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setLink(String link) {
        this.link = link;
    }


    /**
     * <p>链接标题</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getLinkTitle() {
        return linkTitle;
    }

    /**
     * <p>链接标题</p>
     *
     * @author: javalx
     * @param: @param linkTitle
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }


    /**
     * <p>链接配图</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getLinkImage() {
        return linkImage;
    }

    /**
     * <p>链接配图</p>
     *
     * @author: javalx
     * @param: @param linkImage
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }


    /**
     * <p>图片（图片之间用竖杠隔开）</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getPicture() {
        return picture;
    }

    /**
     * <p>图片（图片之间用竖杠隔开）</p>
     *
     * @author: javalx
     * @param: @param picture
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }


    /**
     * <p>视频</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getVideo() {
        return video;
    }

    /**
     * <p>视频</p>
     *
     * @author: javalx
     * @param: @param video
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setVideo(String video) {
        this.video = video;
    }


    /**
     * <p>音乐</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getMusic() {
        return music;
    }

    /**
     * <p>音乐</p>
     *
     * @author: javalx
     * @param: @param music
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setMusic(String music) {
        this.music = music;
    }


    /**
     * <p>发布人ID</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 09:42:35
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>发布人ID</p>
     *
     * @author: javalx
     * @param: @param customerId
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>发布设备</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getDevice() {
        return device;
    }

    /**
     * <p>发布设备</p>
     *
     * @author: javalx
     * @param: @param device
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setDevice(String device) {
        this.device = device;
    }


    /**
     * <p>发布地点（位置）</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getSite() {
        return site;
    }

    /**
     * <p>发布地点（位置）</p>
     *
     * @author: javalx
     * @param: @param site
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setSite(String site) {
        this.site = site;
    }


    /**
     * <p>点赞人</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 09:42:35
     */
    public String getLikeCustomerIds() {
        return likeCustomerIds;
    }

    /**
     * <p>点赞人</p>
     *
     * @author: javalx
     * @param: @param likeCustomerIds
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setLikeCustomerIds(String likeCustomerIds) {
        this.likeCustomerIds = likeCustomerIds;
    }


    /**
     * <p>发布状态（1，已发布；2，已删除；）</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-04 09:42:35
     */
    public Integer getIssued() {
        return issued;
    }

    /**
     * <p>发布状态（1，已发布；2，已删除；）</p>
     *
     * @author: javalx
     * @param: @param issued
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setIssued(Integer issued) {
        this.issued = issued;
    }


    /**
     * <p>发布时间</p>
     *
     * @author: javalx
     * @return: Date
     * @Date :   2019-06-04 09:42:35
     */
    public Date getIssuedTime() {
        return issuedTime;
    }

    /**
     * <p>发布时间</p>
     *
     * @author: javalx
     * @param: @param issuedTime
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setIssuedTime(Date issuedTime) {
        this.issuedTime = issuedTime;
    }


    /**
     * <p>删除时间</p>
     *
     * @author: javalx
     * @return: Date
     * @Date :   2019-06-04 09:42:35
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * <p>删除时间</p>
     *
     * @author: javalx
     * @param: @param deleteTime
     * @return: void
     * @Date :   2019-06-04 09:42:35
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }


}
