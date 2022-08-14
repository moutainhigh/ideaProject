/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 20:11:45
 */
package hry.social.manage.remote.model.friend;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p> SocialFriendRelation </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 20:11:45
 */
@Table(name = "social_friend_relation")
public class SocialFriendRelation extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "customerId")
    private Long customerId;  //跟id

    @Column(name = "friendId")
    private Long friendId;  //好友id

    @Column(name = "friendsNote")
    private String friendsNote;  //好友备注

    @Transient
    private int rankNum;  //个人算力排名

    @Transient
    private String nickName;  //昵称

    @Transient
    private String headPortrait;  //头像

    @Transient
    private String email;  //邮箱

    @Transient
    private String mobilePhone;  //手机

    @Transient
    private String accid;  //云信帐号

    @Transient
    private String mood;  //心情签名

    @Transient
    private BigDecimal totalForce;  //个人总算力值

    @Transient
    private Integer isfriend;  //是否已为好友 0:否 1:是

    @Transient
    private Integer collectionState;  //好友是否有可收取的果园奖励（0 ：否，1 ：是）

    @Transient
    private List<String> circlePictures;  //朋友圈图片集

    public List<String> getCirclePictures() {
        return circlePictures;
    }

    public void setCirclePictures(List<String> circlePictures) {
        this.circlePictures = circlePictures;
    }

    public Integer getCollectionState() {
        return collectionState;
    }

    public void setCollectionState(Integer collectionState) {
        this.collectionState = collectionState;
    }

    public Integer getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public BigDecimal getTotalForce() {
        return totalForce;
    }

    public void setTotalForce(BigDecimal totalForce) {
        this.totalForce = totalForce;
    }

    public int getRankNum() {
        return rankNum;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
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

    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-03 20:11:45
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
     * @Date :   2019-06-03 20:11:45
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>跟id</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-03 20:11:45
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>跟id</p>
     *
     * @author: javalx
     * @param: @param customerId
     * @return: void
     * @Date :   2019-06-03 20:11:45
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>好友id</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-03 20:11:45
     */
    public Long getFriendId() {
        return friendId;
    }

    /**
     * <p>好友id</p>
     *
     * @author: javalx
     * @param: @param friendId
     * @return: void
     * @Date :   2019-06-03 20:11:45
     */
    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }


    /**
     * <p>好友备注</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-03 20:11:45
     */
    public String getFriendsNote() {
        return friendsNote;
    }

    /**
     * <p>好友备注</p>
     *
     * @author: javalx
     * @param: @param friendsNote
     * @return: void
     * @Date :   2019-06-03 20:11:45
     */
    public void setFriendsNote(String friendsNote) {
        this.friendsNote = friendsNote;
    }


}
