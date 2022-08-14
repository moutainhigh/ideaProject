/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:22:18
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsTopic </p>
 * 话题实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:22:18
 */
@Table(name = "social_sns_topic")
public class SocialSnsTopic extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //ID

    @Column(name = "customerId")
    private Long customerId;  //创建人ID

    @Column(name = "subjKey")
    private String subjKey;  //分类key

    @Column(name = "name")
    private String name;  //话题名称

    @Column(name = "sortNum")
    private Integer sortNum;  //排序

    @Column(name = "states")
    private Integer states;  //状态 0：审核中；1：已开启；2：已驳回


    /**
     * <p>ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:22:18
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
     * @Date :   2019-05-13 10:22:18
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>创建人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:22:18
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>创建人ID</p>
     *
     * @author: lixin
     * @param: @param customerId
     * @return: void
     * @Date :   2019-05-13 10:22:18
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>分类key</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:22:18
     */
    public String getSubjKey() {
        return subjKey;
    }

    /**
     * <p>分类key</p>
     *
     * @author: lixin
     * @param: @param subjKey
     * @return: void
     * @Date :   2019-05-13 10:22:18
     */
    public void setSubjKey(String subjKey) {
        this.subjKey = subjKey;
    }


    /**
     * <p>话题名称</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:22:18
     */
    public String getName() {
        return name;
    }

    /**
     * <p>话题名称</p>
     *
     * @author: lixin
     * @param: @param name
     * @return: void
     * @Date :   2019-05-13 10:22:18
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * <p>排序</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:22:18
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * <p>排序</p>
     *
     * @author: lixin
     * @param: @param sortNum
     * @return: void
     * @Date :   2019-05-13 10:22:18
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }


    /**
     * <p>状态 0：审核中；1：已开启；2：已驳回</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:22:18
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>状态 0：审核中；1：已开启；2：已驳回</p>
     *
     * @author: lixin
     * @param: @param states
     * @return: void
     * @Date :   2019-05-13 10:22:18
     */
    public void setStates(Integer states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "SocialSnsTopic{" + "id=" + id + ", customerId=" + customerId + ", subjKey='" + subjKey + '\'' + ", name='" + name + '\'' + ", sortNum=" + sortNum + ", states=" + states + '}';
    }
}
