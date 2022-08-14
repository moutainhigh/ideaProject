/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:17:25
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsAttention </p>
 * 关注&粉丝记录实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:17:25
 */
@Table(name = "social_sns_attention")
public class SocialSnsAttention extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "customerId")
    private Long customerId;  //被关注人ID

    @Column(name = "attentionId")
    private Long attentionId;  //关注人ID

    @Column(name = "states")
    private Integer states;  //标识，（0未删除，1已删除）


    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:17:25
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
     * @Date :   2019-05-13 10:17:25
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>被关注人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:17:25
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>被关注人ID</p>
     *
     * @author: lixin
     * @param: @param customerId
     * @return: void
     * @Date :   2019-05-13 10:17:25
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>关注人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:17:25
     */
    public Long getAttentionId() {
        return attentionId;
    }

    /**
     * <p>关注人ID</p>
     *
     * @author: lixin
     * @param: @param attentionId
     * @return: void
     * @Date :   2019-05-13 10:17:25
     */
    public void setAttentionId(Long attentionId) {
        this.attentionId = attentionId;
    }


    /**
     * <p>标识，（0未删除，1已删除）</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:17:25
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>标识，（0未删除，1已删除）</p>
     *
     * @author: lixin
     * @param: @param states
     * @return: void
     * @Date :   2019-05-13 10:17:25
     */
    public void setStates(Integer states) {
        this.states = states;
    }


}
