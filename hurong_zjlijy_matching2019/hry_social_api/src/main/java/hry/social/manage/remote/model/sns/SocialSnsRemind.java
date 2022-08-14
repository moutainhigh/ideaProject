/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:20:50
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsRemind </p>
 * 帖子@记录实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:20:50
 */
@Table(name = "social_sns_remind")
public class SocialSnsRemind extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //ID

    @Column(name = "postsId")
    private Long postsId;  //帖子ID

    @Column(name = "customerId")
    private Long customerId;  //发帖人ID

    @Column(name = "remindId")
    private Long remindId;  //被提醒人ID

    @Column(name = "states")
    private Integer states;  //状态 0：已删除；1：未删除


    /**
     * <p>ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:20:50
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @author: lixin
     * @param: @param id
     * @return: void
     * @Date :   2019-05-13 10:20:50
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>帖子ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:20:50
     */
    public Long getPostsId() {
        return postsId;
    }

    /**
     * <p>帖子ID</p>
     *
     * @author: lixin
     * @param: @param postsId
     * @return: void
     * @Date :   2019-05-13 10:20:50
     */
    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }


    /**
     * <p>发帖人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:20:50
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>发帖人ID</p>
     *
     * @author: lixin
     * @param: @param customerId
     * @return: void
     * @Date :   2019-05-13 10:20:50
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>被提醒人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:20:50
     */
    public Long getRemindId() {
        return remindId;
    }

    /**
     * <p>被提醒人ID</p>
     *
     * @author: lixin
     * @param: @param remindId
     * @return: void
     * @Date :   2019-05-13 10:20:50
     */
    public void setRemindId(Long remindId) {
        this.remindId = remindId;
    }


    /**
     * <p>状态 0：已删除；1：未删除</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:20:50
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>状态 0：已删除；1：未删除</p>
     *
     * @author: lixin
     * @param: @param states
     * @return: void
     * @Date :   2019-05-13 10:20:50
     */
    public void setStates(Integer states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "SocialSnsRemind{" + "id=" + id + ", postsId=" + postsId + ", customerId=" + customerId + ", remindId=" + remindId + ", states=" + states + '}';
    }
}
