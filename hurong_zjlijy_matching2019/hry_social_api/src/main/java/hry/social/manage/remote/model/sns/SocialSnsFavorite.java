/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:19:34
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsFavorite </p>
 * 帖子收藏记录实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:19:34
 */
@Table(name = "social_sns_favorite")
public class SocialSnsFavorite extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //ID

    @Column(name = "postsId")
    private Long postsId;  //帖子ID

    @Column(name = "customerId")
    private Long customerId;  //发帖人ID

    @Column(name = "favoriteId")
    private Long favoriteId;  //收藏人ID

    @Column(name = "title")
    private String title;  //帖子标题

    @Column(name = "picture")
    private String picture;  //帖子图片

    @Column(name = "states")
    private Integer states;  //状态 0：已删除；1：未删除


    /**
     * <p>ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:19:34
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
     * @Date :   2019-05-13 10:19:34
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>帖子ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:19:34
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
     * @Date :   2019-05-13 10:19:34
     */
    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }


    /**
     * <p>发帖人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:19:34
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
     * @Date :   2019-05-13 10:19:34
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>收藏人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:19:34
     */
    public Long getFavoriteId() {
        return favoriteId;
    }

    /**
     * <p>收藏人ID</p>
     *
     * @author: lixin
     * @param: @param favoriteId
     * @return: void
     * @Date :   2019-05-13 10:19:34
     */
    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }


    /**
     * <p>帖子标题</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:19:34
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>帖子标题</p>
     *
     * @author: lixin
     * @param: @param title
     * @return: void
     * @Date :   2019-05-13 10:19:34
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * <p>帖子图片</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:19:34
     */
    public String getPicture() {
        return picture;
    }

    /**
     * <p>帖子图片</p>
     *
     * @author: lixin
     * @param: @param picture
     * @return: void
     * @Date :   2019-05-13 10:19:34
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }


    /**
     * <p>状态 0：已删除；1：未删除</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:19:34
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
     * @Date :   2019-05-13 10:19:34
     */
    public void setStates(Integer states) {
        this.states = states;
    }


}
