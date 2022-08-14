/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:18:05
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsCommunity </p>
 * 社区信息实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:18:05
 */
@Table(name = "social_sns_community")
public class SocialSnsCommunity extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //ID

    @Column(name = "subjKey")
    private String subjKey;  //分类key

    @Column(name = "commKey")
    private String commKey;  //社区KEY

    @Column(name = "name")
    private String name;  //社区名称

    @Column(name = "sortNum")
    private Integer sortNum;  //排序

    @Column(name = "langKey")
    private String langKey;  //语种KEY

    @Column(name = "states")
    private Integer states;  //状态 0：关闭；1：开启

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getCommKey() {
        return commKey;
    }

    public void setCommKey(String commKey) {
        this.commKey = commKey;
    }

    /**
     * <p>ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:18:05
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
     * @Date :   2019-05-13 10:18:05
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>分类key</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:18:05
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
     * @Date :   2019-05-13 10:18:05
     */
    public void setSubjKey(String subjKey) {
        this.subjKey = subjKey;
    }


    /**
     * <p>社区名称</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:18:05
     */
    public String getName() {
        return name;
    }

    /**
     * <p>社区名称</p>
     *
     * @author: lixin
     * @param: @param name
     * @return: void
     * @Date :   2019-05-13 10:18:05
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * <p>排序</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:18:05
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
     * @Date :   2019-05-13 10:18:05
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }


    /**
     * <p>状态 0：关闭；1：开启</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:18:05
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>状态 0：关闭；1：开启</p>
     *
     * @author: lixin
     * @param: @param states
     * @return: void
     * @Date :   2019-05-13 10:18:05
     */
    public void setStates(Integer states) {
        this.states = states;
    }


}
