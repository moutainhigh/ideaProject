/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-07 13:48:30
 */
package hry.social.manage.remote.model.shake;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * 抖购商品实体
 *
 * @author : javalx
 * @version : V1.0
 * @desc :
 * @date : 2019/6/3 11:43
 */
@Table(name = "social_shop_product")
public class SocialShopProduct extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "shopId")
    private String shopId;  //商品ID

    @Column(name = "adSrc")
    private String adSrc;  //商品图片URL

    @Column(name = "title")
    private String title;  //标题

    @Column(name = "adURL")
    private String adURL;  //商品跳转链接

    @Column(name = "sortNumber")
    private Integer sortNumber;  //排序号

    @Column(name = "status")
    private Integer status;  //是否展示，（0否，1是）

    @Column(name = "adType")
    private Integer adType;  //类型，（6热销推荐，9每日精选）


    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-07 13:48:30
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
     * @Date :   2019-05-07 13:48:30
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>商品ID</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-07 13:48:30
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * <p>商品ID</p>
     *
     * @author: lixin
     * @param: @param shopId
     * @return: void
     * @Date :   2019-05-07 13:48:30
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }


    /**
     * <p>商品图片URL</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-07 13:48:30
     */
    public String getAdSrc() {
        return adSrc;
    }

    /**
     * <p>商品图片URL</p>
     *
     * @author: lixin
     * @param: @param adSrc
     * @return: void
     * @Date :   2019-05-07 13:48:30
     */
    public void setAdSrc(String adSrc) {
        this.adSrc = adSrc;
    }


    /**
     * <p>标题</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-07 13:48:30
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>标题</p>
     *
     * @author: lixin
     * @param: @param title
     * @return: void
     * @Date :   2019-05-07 13:48:30
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * <p>商品跳转链接</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-07 13:48:30
     */
    public String getAdURL() {
        return adURL;
    }

    /**
     * <p>商品跳转链接</p>
     *
     * @author: lixin
     * @param: @param adURL
     * @return: void
     * @Date :   2019-05-07 13:48:30
     */
    public void setAdURL(String adURL) {
        this.adURL = adURL;
    }


    /**
     * <p>排序号</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-07 13:48:30
     */
    public Integer getSortNumber() {
        return sortNumber;
    }

    /**
     * <p>排序号</p>
     *
     * @author: lixin
     * @param: @param sortNumber
     * @return: void
     * @Date :   2019-05-07 13:48:30
     */
    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }


    /**
     * <p>是否展示，（0否，1是）</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-07 13:48:30
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>是否展示，（0否，1是）</p>
     *
     * @author: lixin
     * @param: @param status
     * @return: void
     * @Date :   2019-05-07 13:48:30
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    /**
     * <p>类型，（6热销推荐，9每日精选）</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-05-07 13:48:30
     */
    public Integer getAdType() {
        return adType;
    }

    /**
     * <p>类型，（6热销推荐，9每日精选）</p>
     *
     * @author: lixin
     * @param: @param adType
     * @return: void
     * @Date :   2019-05-07 13:48:30
     */
    public void setAdType(Integer adType) {
        this.adType = adType;
    }


}
