/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-04-18 17:00:13
 */
package hry.model.social.shake;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * 抖粉实体
 *
 * @author : javalx
 * @version : V1.0
 * @desc :
 * @date : 2019/6/3 11:40
 */
@Table(name = "social_shake_fans")
public class SocialShakeFans extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "shakeId")
    private Long shakeId;  //用户ID

    @Column(name = "fansId")
    private Long fansId;  //抖到的粉丝ID

    @Column(name = "states")
    private Integer states;  //标识，（0失败，1成功）

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "created")
    private Date created;  //时间

    @Transient
    private String nickName;  //用户昵称

    @Transient
    private String fansName;  //抖到的粉丝昵称

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

    public String getFansName() {
        return fansName;
    }

    public void setFansName(String fansName) {
        this.fansName = fansName;
    }

    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:00:13
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
     * @Date :   2019-04-18 17:00:13
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>用户ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:00:13
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
     * @Date :   2019-04-18 17:00:13
     */
    public void setShakeId(Long shakeId) {
        this.shakeId = shakeId;
    }


    /**
     * <p>抖到的粉丝ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:00:13
     */
    public Long getFansId() {
        return fansId;
    }

    /**
     * <p>抖到的粉丝ID</p>
     *
     * @author: lixin
     * @param: @param fansId
     * @return: void
     * @Date :   2019-04-18 17:00:13
     */
    public void setFansId(Long fansId) {
        this.fansId = fansId;
    }


    /**
     * <p>标识，（0失败，1成功）</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-04-18 17:00:13
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
     * @Date :   2019-04-18 17:00:13
     */
    public void setStates(Integer states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "SocialShakeFans{" + "id=" + id + ", shakeId=" + shakeId + ", fansId=" + fansId + ", states=" + states + ", created=" + created + ", nickName='" + nickName + '\'' + ", fansName='" + fansName + '\'' + ", headPortrait='" + headPortrait + '\'' + '}';
    }
}
