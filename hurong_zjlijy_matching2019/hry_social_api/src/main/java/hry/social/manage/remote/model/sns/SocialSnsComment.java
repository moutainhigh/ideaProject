/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:17:43
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsComment </p>
 * 帖子评论实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:17:43
 */
@Table(name = "social_sns_comment")
public class SocialSnsComment extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "postsId")
    private Long postsId;  //帖子ID

    @Column(name = "customerId")
    private Long customerId;  //发帖人ID

    @Column(name = "commentId")
    private Long commentId;  //评论人ID

    @Column(name = "parentId")
    private Long parentId;  //父ID

    @Column(name = "comments")
    private String comments;  //评论内容（只支持纯文本和表情）

    @Column(name = "states")
    private Integer states;  //标识，（0未删除，1已删除）


    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:17:43
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
     * @Date :   2019-05-13 10:17:43
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>帖子ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:17:43
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
     * @Date :   2019-05-13 10:17:43
     */
    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }


    /**
     * <p>发帖人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:17:43
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
     * @Date :   2019-05-13 10:17:43
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>评论人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:17:43
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * <p>评论人ID</p>
     *
     * @author: lixin
     * @param: @param commentId
     * @return: void
     * @Date :   2019-05-13 10:17:43
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }


    /**
     * <p>父ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:17:43
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * <p>父ID</p>
     *
     * @author: lixin
     * @param: @param parentId
     * @return: void
     * @Date :   2019-05-13 10:17:43
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    /**
     * <p>评论内容（只支持纯文本和表情）</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:17:43
     */
    public String getComments() {
        return comments;
    }

    /**
     * <p>评论内容（只支持纯文本和表情）</p>
     *
     * @author: lixin
     * @param: @param comments
     * @return: void
     * @Date :   2019-05-13 10:17:43
     */
    public void setComments(String comments) {
        this.comments = comments;
    }


    /**
     * <p>标识，（0未删除，1已删除）</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-13 10:17:43
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
     * @Date :   2019-05-13 10:17:43
     */
    public void setStates(Integer states) {
        this.states = states;
    }


}
