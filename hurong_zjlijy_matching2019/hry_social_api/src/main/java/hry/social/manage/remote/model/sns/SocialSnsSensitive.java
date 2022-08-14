/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:21:24
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsSensitive </p>
 * 敏感词实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:21:24
 */
@Table(name = "social_sns_sensitive")
public class SocialSnsSensitive extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "word")
    private String word;  //敏感词

    @Column(name = "type")
    private Integer type;  //类型 0：删除；1：替换

    @Column(name = "repWord")
    private String repWord;  //替换词

    @Column(name = "states")
    private Integer states;  //状态 0：禁用；1：启用


    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:24
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
     * @Date :   2019-05-13 10:21:24
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>敏感词</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:21:24
     */
    public String getWord() {
        return word;
    }

    /**
     * <p>敏感词</p>
     *
     * @author: lixin
     * @param: @param word
     * @return: void
     * @Date :   2019-05-13 10:21:24
     */
    public void setWord(String word) {
        this.word = word;
    }


    /**
     * <p>类型 0：删除；1：替换</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:21:24
     */
    public Integer getType() {
        return type;
    }

    /**
     * <p>类型 0：删除；1：替换</p>
     *
     * @author: lixin
     * @param: @param type
     * @return: void
     * @Date :   2019-05-13 10:21:24
     */
    public void setType(Integer type) {
        this.type = type;
    }


    /**
     * <p>替换词</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:21:24
     */
    public String getRepWord() {
        return repWord;
    }

    /**
     * <p>替换词</p>
     *
     * @author: lixin
     * @param: @param repWord
     * @return: void
     * @Date :   2019-05-13 10:21:24
     */
    public void setRepWord(String repWord) {
        this.repWord = repWord;
    }


    /**
     * <p>状态 0：禁用；1：启用</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:21:24
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
     * @Date :   2019-05-13 10:21:24
     */
    public void setStates(Integer states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "SocialSnsSensitive{" + "id=" + id + ", word='" + word + '\'' + ", type=" + type + ", repWord='" + repWord + '\'' + ", states=" + states + '}';
    }
}
