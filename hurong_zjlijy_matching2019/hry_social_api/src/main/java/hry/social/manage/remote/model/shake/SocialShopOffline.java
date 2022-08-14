/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-04-18 17:02:48
 */
package hry.social.manage.remote.model.shake;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 线下店铺实体
 *
 * @author : javalx
 * @version : V1.0
 * @desc :
 * @date : 2019/6/3 11:43
 */
@Table(name = "social_shop_offline")
public class SocialShopOffline extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "shopName")
    private String shopName;  //店铺名称

    @Column(name = "shopLogo")
    private String shopLogo;  //店铺logo

    @Column(name = "shopAddress")
    private String shopAddress;  //店铺地址

    @Column(name = "openTime")
    private String openTime;  //营业时间（开始）

    @Column(name = "closeTime")
    private String closeTime;  //营业时间（结束）

    @Column(name = "shopLink")
    private String shopLink;  //在线店铺链接

    @Column(name = "longitude")
    private BigDecimal longitude;  //经度

    @Column(name = "latitude")
    private BigDecimal latitude;  //纬度

    @Column(name = "shopCode")
    private String shopCode;  //店铺编码

    @Column(name = "phone")
    private Long phone;  //联系电话

    @Column(name = "customerId")
    private Long customerId;  //用户ID

    @Column(name = "status")
    private Integer status;  //状态，（0禁用，1启用）

    @Transient
    private String dm; //距离(米)

    @Transient
    private String dkm; //距离(千米)

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    @Transient
    List<String> pics = new ArrayList<>(); //店铺图片

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getDkm() {
        return dkm;
    }

    public void setDkm(String dkm) {
        this.dkm = dkm;
    }

    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:02:48
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
     * @Date :   2019-04-18 17:02:48
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>店铺名称</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-04-18 17:02:48
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * <p>店铺名称</p>
     *
     * @author: lixin
     * @param: @param shopName
     * @return: void
     * @Date :   2019-04-18 17:02:48
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    /**
     * <p>店铺logo</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-04-18 17:02:48
     */
    public String getShopLogo() {
        return shopLogo;
    }

    /**
     * <p>店铺logo</p>
     *
     * @author: lixin
     * @param: @param shopLogo
     * @return: void
     * @Date :   2019-04-18 17:02:48
     */
    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }


    /**
     * <p>营业时间（开始）</p>
     *
     * @author: lixin
     * @return: Date
     * @Date :   2019-04-18 17:02:48
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     * <p>营业时间（开始）</p>
     *
     * @author: lixin
     * @param: @param openTime
     * @return: void
     * @Date :   2019-04-18 17:02:48
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }


    /**
     * <p>营业时间（结束）</p>
     *
     * @author: lixin
     * @return: Date
     * @Date :   2019-04-18 17:02:48
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * <p>营业时间（结束）</p>
     *
     * @author: lixin
     * @param: @param closeTime
     * @return: void
     * @Date :   2019-04-18 17:02:48
     */
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }


    /**
     * <p>在线店铺链接</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-04-18 17:02:48
     */
    public String getShopLink() {
        return shopLink;
    }

    /**
     * <p>在线店铺链接</p>
     *
     * @author: lixin
     * @param: @param shopLink
     * @return: void
     * @Date :   2019-04-18 17:02:48
     */
    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }


    /**
     * <p>经度</p>
     *
     * @author: lixin
     * @return: BigDecimal
     * @Date :   2019-04-18 17:02:48
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * <p>经度</p>
     *
     * @author: lixin
     * @param: @param longitude
     * @return: void
     * @Date :   2019-04-18 17:02:48
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }


    /**
     * <p>纬度</p>
     *
     * @author: lixin
     * @return: BigDecimal
     * @Date :   2019-04-18 17:02:48
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * <p>纬度</p>
     *
     * @author: lixin
     * @param: @param latitude
     * @return: void
     * @Date :   2019-04-18 17:02:48
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }


    /**
     * <p>用户ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:02:48
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>用户ID</p>
     *
     * @author: lixin
     * @param: @param customerId
     * @return: void
     * @Date :   2019-04-18 17:02:48
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>状态，（0禁用，1启用）</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-04-18 17:02:48
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>状态，（0禁用，1启用）</p>
     *
     * @author: lixin
     * @param: @param status
     * @return: void
     * @Date :   2019-04-18 17:02:48
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


}
