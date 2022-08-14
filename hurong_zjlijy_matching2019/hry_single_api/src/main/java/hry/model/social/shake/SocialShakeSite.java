/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-04-18 17:01:54
 */
package hry.model.social.shake;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 抖一抖用户位置信息实体
 *
 * @author : javalx
 * @version : V1.0
 * @desc :
 * @date : 2019/6/3 11:41
 */
@Table(name = "social_shake_site")
public class SocialShakeSite extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "shakeId")
    private Long shakeId;  //用户ID

    @Column(name = "longitude")
    private BigDecimal longitude;  //经度

    @Column(name = "latitude")
    private BigDecimal latitude;  //纬度

    @Transient
    private String nickName;  //用户昵称

    @Transient
    private String mobilePhone;  //用户手机

    @Transient
    private String email;  //用户邮箱

    @Transient
    private String headPortrait; //头像

    @Transient
    private String dm; //距离(米)

    @Transient
    private String dkm; //距离(千米)

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

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <p>主键</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:01:54
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
     * @Date :   2019-04-18 17:01:54
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>用户ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-04-18 17:01:54
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
     * @Date :   2019-04-18 17:01:54
     */
    public void setShakeId(Long shakeId) {
        this.shakeId = shakeId;
    }


    /**
     * <p>经度</p>
     *
     * @author: lixin
     * @return: BigDecimal
     * @Date :   2019-04-18 17:01:54
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
     * @Date :   2019-04-18 17:01:54
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }


    /**
     * <p>纬度</p>
     *
     * @author: lixin
     * @return: BigDecimal
     * @Date :   2019-04-18 17:01:54
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
     * @Date :   2019-04-18 17:01:54
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }


}
