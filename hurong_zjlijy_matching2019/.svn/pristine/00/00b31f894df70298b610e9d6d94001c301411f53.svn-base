/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:21:57
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsSubject </p>
 * 社区&话题分类实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:21:57
 */
@Table(name = "social_sns_subject")
public class SocialSnsSubject extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "subjDesc")
    private String subjDesc;  //分类描述

    @Column(name = "subjKey")
    private String subjKey;  //分类key

    @Column(name = "subjVal")
    private String subjVal;  //分类名称

    @Column(name = "subjLevel")
    private Integer subjLevel;  //级别

    @Column(name = "parentKey")
    private String parentKey;  //父类key

    @Column(name = "langKey")
    private String langKey;  //语种key

    @Column(name = "states")
    private Integer states;  //状态 0：禁用；1：启用

    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:57
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
     * @Date :   2019-05-13 10:21:57
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>分类描述</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:21:57
     */
    public String getDesc() {
        return subjDesc;
    }

    /**
     * <p>分类描述</p>
     *
     * @author: lixin
     * @param: @param desc
     * @return: void
     * @Date :   2019-05-13 10:21:57
     */
    public void setDesc(String subjDesc) {
        this.subjDesc = subjDesc;
    }


    /**
     * <p>分类key</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:21:57
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
     * @Date :   2019-05-13 10:21:57
     */
    public void setSubjKey(String subjKey) {
        this.subjKey = subjKey;
    }


    /**
     * <p>分类名称</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:21:57
     */
    public String getSubjVal() {
        return subjVal;
    }

    /**
     * <p>分类名称</p>
     *
     * @author: lixin
     * @param: @param subjVal
     * @return: void
     * @Date :   2019-05-13 10:21:57
     */
    public void setSubjVal(String subjVal) {
        this.subjVal = subjVal;
    }


    /**
     * <p>级别</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:21:57
     */
    public Integer getLevel() {
        return subjLevel;
    }

    /**
     * <p>级别</p>
     *
     * @author: lixin
     * @param: @param level
     * @return: void
     * @Date :   2019-05-13 10:21:57
     */
    public void setLevel(Integer level) {
        this.subjLevel = subjLevel;
    }


    /**
     * <p>父类key</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:21:57
     */
    public String getParentKey() {
        return parentKey;
    }

    /**
     * <p>父类key</p>
     *
     * @author: lixin
     * @param: @param parentKey
     * @return: void
     * @Date :   2019-05-13 10:21:57
     */
    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }


    /**
     * <p>语种key</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:21:57
     */
    public String getLangKey() {
        return langKey;
    }

    /**
     * <p>语种key</p>
     *
     * @author: lixin
     * @param: @param langKey
     * @return: void
     * @Date :   2019-05-13 10:21:57
     */
    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }


    /**
     * <p>状态 0：禁用；1：启用</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:21:57
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>状态 0：禁用；1：启用</p>
     *
     * @author: lixin
     * @param: @param states
     * @return: void
     * @Date :   2019-05-13 10:21:57
     */
    public void setStates(Integer states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "SocialSnsSubject{" + "id=" + id + ", subjDesc='" + subjDesc + '\'' + ", subjKey='" + subjKey + '\'' + ", subjVal='" + subjVal + '\'' + ", subjLevel=" + subjLevel + ", parentKey='" + parentKey + '\'' + ", langKey='" + langKey + '\'' + ", states=" + states + '}';
    }
}
