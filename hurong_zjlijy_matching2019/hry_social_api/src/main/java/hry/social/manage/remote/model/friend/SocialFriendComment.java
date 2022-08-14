/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 10:25:33
 */
package hry.social.manage.remote.model.friend;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialFriendComment </p>
 *
 * @author: javalx
 * @Date : 2019-06-04 10:25:33
 */
@Table(name = "social_friend_comment")
public class SocialFriendComment extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "circleId")
    private Long circleId;  //主贴ID（social_friend_circle）

    @Column(name = "customerId")
    private Long customerId;  //评论人ID

    @Column(name = "parentId")
    private Long parentId;  //父ID

    @Column(name = "friendId")
    private Long friendId;  //被回复人ID

    @Column(name = "comments")
    private String comments;  //评论内容（只支持纯文本和表情）

    @Column(name = "states")
    private Integer states;  //标识，（0未删除，1已删除）

    @Transient
    private String nickName; //昵称

    @Transient
    private String headPortrait; //头像

    @Transient
    private String mobilePhone; //手机

    @Transient
    private String remark; //备注

    @Transient
    private String friendsNote; //昵称

    @Transient
    private String friendsPortrait; //头像

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getFriendsNote() {
        return friendsNote;
    }

    public void setFriendsNote(String friendsNote) {
        this.friendsNote = friendsNote;
    }

    public String getFriendsPortrait() {
        return friendsPortrait;
    }

    public void setFriendsPortrait(String friendsPortrait) {
        this.friendsPortrait = friendsPortrait;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 10:25:33
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
     * @Date :   2019-06-04 10:25:33
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>主贴ID（social_friend_circle）</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 10:25:33
     */
    public Long getCircleId() {
        return circleId;
    }

    /**
     * <p>主贴ID（social_friend_circle）</p>
     *
     * @author: javalx
     * @param: @param circleId
     * @return: void
     * @Date :   2019-06-04 10:25:33
     */
    public void setCircleId(Long circleId) {
        this.circleId = circleId;
    }


    /**
     * <p>评论人ID</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 10:25:33
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>评论人ID</p>
     *
     * @author: javalx
     * @param: @param customerId
     * @return: void
     * @Date :   2019-06-04 10:25:33
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>父ID</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 10:25:33
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * <p>父ID</p>
     *
     * @author: javalx
     * @param: @param parentId
     * @return: void
     * @Date :   2019-06-04 10:25:33
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    /**
     * <p>评论内容（只支持纯文本和表情）</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 10:25:33
     */
    public String getComments() {
        return comments;
    }

    /**
     * <p>评论内容（只支持纯文本和表情）</p>
     *
     * @author: javalx
     * @param: @param comments
     * @return: void
     * @Date :   2019-06-04 10:25:33
     */
    public void setComments(String comments) {
        this.comments = comments;
    }


    /**
     * <p>标识，（0未删除，1已删除）</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-04 10:25:33
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>标识，（0未删除，1已删除）</p>
     *
     * @author: javalx
     * @param: @param states
     * @return: void
     * @Date :   2019-06-04 10:25:33
     */
    public void setStates(Integer states) {
        this.states = states;
    }


}
