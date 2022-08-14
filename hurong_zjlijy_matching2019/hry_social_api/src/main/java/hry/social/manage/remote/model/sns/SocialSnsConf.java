/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:19:16
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsConf </p>
 * sns配置表
 *
 * @author: lixin
 * @Date : 2019-05-13 10:19:16
 */
@Table(name = "social_sns_conf")
public class SocialSnsConf extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "confDes")
    private String confDes;  //描述

    @Column(name = "confKey")
    private String confKey;  //键

    @Column(name = "confVal")
    private String confVal;  //值

    @Column(name = "remark")
    private String remark;  //备注

    @Column(name = "confType")
    private String confType;  //

    @Column(name = "states")
    private Integer states;  //状态 0：禁用；1：启用


    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:19:16
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
     * @Date :   2019-05-13 10:19:16
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>描述</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:19:16
     */
    public String getConfDes() {
        return confDes;
    }

    /**
     * <p>描述</p>
     *
     * @author: lixin
     * @param: @param confDes
     * @return: void
     * @Date :   2019-05-13 10:19:16
     */
    public void setConfDes(String confDes) {
        this.confDes = confDes;
    }


    /**
     * <p>键</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:19:16
     */
    public String getConfKey() {
        return confKey;
    }

    /**
     * <p>键</p>
     *
     * @author: lixin
     * @param: @param confKey
     * @return: void
     * @Date :   2019-05-13 10:19:16
     */
    public void setConfKey(String confKey) {
        this.confKey = confKey;
    }


    /**
     * <p>值</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:19:16
     */
    public String getConfVal() {
        return confVal;
    }

    /**
     * <p>值</p>
     *
     * @author: lixin
     * @param: @param confVal
     * @return: void
     * @Date :   2019-05-13 10:19:16
     */
    public void setConfVal(String confVal) {
        this.confVal = confVal;
    }


    /**
     * <p>备注</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:19:16
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>备注</p>
     *
     * @author: lixin
     * @param: @param remark
     * @return: void
     * @Date :   2019-05-13 10:19:16
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * <p></p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:19:16
     */
    public String getConfType() {
        return confType;
    }

    /**
     * <p></p>
     *
     * @author: lixin
     * @param: @param confType
     * @return: void
     * @Date :   2019-05-13 10:19:16
     */
    public void setConfType(String confType) {
        this.confType = confType;
    }


    /**
     * <p>状态 0：禁用；1：启用</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:19:16
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
     * @Date :   2019-05-13 10:19:16
     */
    public void setStates(Integer states) {
        this.states = states;
    }


}
