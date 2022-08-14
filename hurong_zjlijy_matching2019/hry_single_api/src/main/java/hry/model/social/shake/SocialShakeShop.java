/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-04-18 17:01:06
 */
package hry.model.social.shake;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 抖店实体
 *
 * @author : javalx
 * @version : V1.0
 * @desc :
 * @date : 2019/6/3 11:41
 */
@Table(name = "social_shake_shop")
public class SocialShakeShop extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "shakeId")
    private Long shakeId;  //用户ID

    @Column(name = "shopId")
    private Long shopId;  //店铺ID

    @Column(name = "states")
    private Integer states;  //标识，（0失败，1成功）

    @Column(name = "created")
    private Date created;  //使用时间

    @Transient
    private String nickName;  //用户昵称

    @Transient
    private String shopName;  //店铺名称

    @Transient
    private String shopLogo;  //店铺logo

    @Transient
    private String shopAddress;  //店铺地址

    @Transient
    private String shopLink;  //店铺链接

    @Transient
    private String openTime;  //营业时间（开始）

    @Transient
    private String closeTime;  //营业时间（结束）

    @Transient
    private String phone;  //联系电话

    @Transient
    List<String> pics = new ArrayList<>(); //店铺图片

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopLink() {
        return shopLink;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:01:06
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
     * @Date :   2019-04-18 17:01:06
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>用户ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:01:06
     */
    public Long getShakeId() {
        return shakeId;
    }

    /**
     * <p>用户ID</p>
     *
     * @author: lixin
     * @param: @param shakeId
     * @return: void
     * @Date :   2019-04-18 17:01:06
     */
    public void setShakeId(Long shakeId) {
        this.shakeId = shakeId;
    }


    /**
     * <p>店铺ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:01:06
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * <p>店铺ID</p>
     *
     * @author: lixin
     * @param: @param shopId
     * @return: void
     * @Date :   2019-04-18 17:01:06
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }


    /**
     * <p>标识，（0失败，1成功）</p>
     *
     * @author: lixin
     * @return: Integer
     * @Date :   2019-04-18 17:01:06
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>标识，（0失败，1成功）</p>
     *
     * @author: lixin
     * @param: @param status
     * @return: void
     * @Date :   2019-04-18 17:01:06
     */
    public void setStates(Integer states) {
        this.states = states;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

}
