/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:20:37
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsPraise </p>
 * 帖子点赞记录实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:20:37
 */
@Table(name = "social_sns_praise")
public class SocialSnsPraise extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //ID

    @Column(name = "postsId")
    private Long postsId;  //帖子ID

    @Column(name = "customerId")
    private Long customerId;  //发帖人ID

    @Column(name = "praiseId")
    private Long praiseId;  //点赞人ID


    /**
     * <p>ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:20:37
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
     * @Date :   2019-05-13 10:20:37
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>帖子ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:20:37
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
     * @Date :   2019-05-13 10:20:37
     */
    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }


    /**
     * <p>发帖人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:20:37
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
     * @Date :   2019-05-13 10:20:37
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>点赞人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:20:37
     */
    public Long getPraiseId() {
        return praiseId;
    }

    /**
     * <p>点赞人ID</p>
     *
     * @author: lixin
     * @param: @param praiseId
     * @return: void
     * @Date :   2019-05-13 10:20:37
     */
    public void setPraiseId(Long praiseId) {
        this.praiseId = praiseId;
    }

    @Override
    public String toString() {
        return "SocialSnsPraise{" + "id=" + id + ", postsId=" + postsId + ", customerId=" + customerId + ", praiseId=" + praiseId + '}';
    }
}
