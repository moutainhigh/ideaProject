/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:22:31
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsVideo </p>
 * 帖子视频实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:22:31
 */
@Table(name = "social_sns_video")
public class SocialSnsVideo extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "postsId")
    private Long postsId;  //帖子ID

    @Column(name = "customerId")
    private Long customerId;  //发帖人ID

    @Column(name = "name")
    private String name;  //视频名称

    @Column(name = "path")
    private String path;  //视频路径

    @Column(name = "states")
    private Integer states;  //状态 0：已删除；1：未删除


    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:22:31
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
     * @Date :   2019-05-13 10:22:31
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>帖子ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:22:31
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
     * @Date :   2019-05-13 10:22:31
     */
    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }


    /**
     * <p>发帖人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:22:31
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
     * @Date :   2019-05-13 10:22:31
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>视频名称</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:22:31
     */
    public String getName() {
        return name;
    }

    /**
     * <p>视频名称</p>
     *
     * @author: lixin
     * @param: @param name
     * @return: void
     * @Date :   2019-05-13 10:22:31
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * <p>视频路径</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:22:31
     */
    public String getPath() {
        return path;
    }

    /**
     * <p>视频路径</p>
     *
     * @author: lixin
     * @param: @param path
     * @return: void
     * @Date :   2019-05-13 10:22:31
     */
    public void setPath(String path) {
        this.path = path;
    }


    /**
     * <p>状态 0：已删除；1：未删除</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:22:31
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
     * @Date :   2019-05-13 10:22:31
     */
    public void setStates(Integer states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "SocialSnsVideo{" + "id=" + id + ", postsId=" + postsId + ", customerId=" + customerId + ", name='" + name + '\'' + ", path='" + path + '\'' + ", states=" + states + '}';
    }
}
