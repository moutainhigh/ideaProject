/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:21:43
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialSnsShare </p>
 * 帖子分享记录实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:21:43
 */
@Table(name = "social_sns_share")
public class SocialSnsShare extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //ID

    @Column(name = "postsId")
    private Long postsId;  //帖子ID

    @Column(name = "customerId")
    private Long customerId;  //发帖人ID

    @Column(name = "shareId")
    private Long shareId;  //分享人ID


    /**
     * <p>ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:43
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
     * @Date :   2019-05-13 10:21:43
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>帖子ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:43
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
     * @Date :   2019-05-13 10:21:43
     */
    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }


    /**
     * <p>发帖人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:43
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
     * @Date :   2019-05-13 10:21:43
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>分享人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:43
     */
    public Long getShareId() {
        return shareId;
    }

    /**
     * <p>分享人ID</p>
     *
     * @author: lixin
     * @param: @param shareId
     * @return: void
     * @Date :   2019-05-13 10:21:43
     */
    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    @Override
    public String toString() {
        return "SocialSnsShare{" + "id=" + id + ", postsId=" + postsId + ", customerId=" + customerId + ", shareId=" + shareId + '}';
    }
}
