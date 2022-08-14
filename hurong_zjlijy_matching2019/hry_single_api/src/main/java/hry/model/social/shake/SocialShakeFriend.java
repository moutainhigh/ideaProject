/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-04-18 16:37:49
 */
package hry.model.social.shake;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * 抖人实体
 *
 * @author : javalx
 * @version : V1.0
 * @desc :
 * @date : 2019/6/3 11:40
 */
@Table(name = "social_shake_friend")
public class SocialShakeFriend extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "shakeId")
    private Long shakeId;  //用户ID

    @Column(name = "customerId")
    private Long customerId;  //抖到的人ID

    @Column(name = "states")
    private Integer states;  //标识，（0失败，1成功）

    @Column(name = "created")
    private Date created;  //时间

    @Transient
    private String nickName;  //用户昵称


    private String friendName;  //抖到的粉丝昵称

    @Transient
    private String headPortrait; //头像

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 16:37:49
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @param: @param id
     * @return: void
     * @Date :   2019-04-18 16:37:49
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>用户ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 16:37:49
     */
    public Long getShakeId() {
        return shakeId;
    }

    /**
     * <p>用户ID</p>
     *
     * @author: lixin
     * @param: @param shakeId
     * @return: void
     * @Date :   2019-04-18 16:37:49
     */
    public void setShakeId(Long shakeId) {
        this.shakeId = shakeId;
    }


    /**
     * <p>抖到的人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 16:37:49
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>抖到的人ID</p>
     *
     * @author: lixin
     * @param: @param customerId
     * @return: void
     * @Date :   2019-04-18 16:37:49
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>标识，（0失败，1成功）</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-04-18 16:37:49
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>标识，（0失败，1成功）</p>
     *
     * @author: lixin
     * @param: @param status
     * @return: void
     * @Date :   2019-04-18 16:37:49
     */
    public void setStates(Integer states) {
        this.states = states;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "SocialShakeFriend{" + "id=" + id + ", shakeId=" + shakeId + ", customerId=" + customerId + ", states=" + states + ", created=" + created + ", nickName='" + nickName + '\'' + ", friendName='" + friendName + '\'' + ", headPortrait='" + headPortrait + '\'' + '}';
    }
}
