/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2018-12-18 15:46:48
 */
package hry.social.manage.remote.model.wishwall;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> SocialWishWall </p>
 * @author: lixin
 * @Date :          2018-12-18 15:46:48  
 */
@Table(name = "social_wish_wall")
public class SocialWishWall extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //序号

    @Column(name = "wishNum")
    private String wishNum;  //许愿ID

    @Column(name = "customerId")
    private Long customerId;  //许愿人ID

    @Column(name = "signature")
    private String signature;  //签名

    @Column(name = "type")
    private Integer type;  //许愿状态，0：未上链，1：已上连，2：上链中

    @Column(name = "coinCode")
    private String coinCode;  //缴费币种

    @Column(name = "fee")
    private BigDecimal fee;  //上链费用

    @Column(name = "cochainTime")
    private Date cochainTime;  //上链时间

    @Column(name = "hash")
    private String hash;  //Hash值

    @Column(name = "blockHeight")
    private String blockHeight;  //区块高度

    @Column(name = "content")
    private String content;  //许愿内容

    @Transient
    private String mobilePhone;  //手机号

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * <p>序号</p>
     * @author: lixin
     * @return: Long
     * @Date :   2018-12-18 15:46:48
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>序号</p>
     * @author: lixin
     * @param:   @param id
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>许愿ID</p>
     * @author: lixin
     * @return: String
     * @Date :   2018-12-18 15:46:48
     */
    public String getWishNum() {
        return wishNum;
    }

    /**
     * <p>许愿ID</p>
     * @author: lixin
     * @param:   @param wishNum
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setWishNum(String wishNum) {
        this.wishNum = wishNum;
    }


    /**
     * <p>许愿人ID</p>
     * @author: lixin
     * @return: Long
     * @Date :   2018-12-18 15:46:48
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>许愿人ID</p>
     * @author: lixin
     * @param:   @param customerId
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>签名</p>
     * @author: lixin
     * @return: String
     * @Date :   2018-12-18 15:46:48
     */
    public String getSignature() {
        return signature;
    }

    /**
     * <p>签名</p>
     * @author: lixin
     * @param:   @param signature
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }


    /**
     * <p>许愿状态，0：未上链，1：已上连</p>
     * @author: lixin
     * @return: Integer
     * @Date :   2018-12-18 15:46:48
     */
    public Integer getType() {
        return type;
    }

    /**
     * <p>许愿状态，0：未上链，1：已上连</p>
     * @author: lixin
     * @param:   @param type
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setType(Integer type) {
        this.type = type;
    }


    /**
     * <p>缴费币种</p>
     * @author: lixin
     * @return: String
     * @Date :   2018-12-18 15:46:48
     */
    public String getCoinCode() {
        return coinCode;
    }

    /**
     * <p>缴费币种</p>
     * @author: lixin
     * @param:   @param coinCode
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }


    /**
     * <p>上链费用</p>
     * @author: lixin
     * @return: BigDecimal
     * @Date :   2018-12-18 15:46:48
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * <p>上链费用</p>
     * @author: lixin
     * @param:   @param fee
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }


    /**
     * <p>上链时间</p>
     * @author: lixin
     * @return: Date
     * @Date :   2018-12-18 15:46:48
     */
    public Date getCochainTime() {
        return cochainTime;
    }

    /**
     * <p>上链时间</p>
     * @author: lixin
     * @param:   @param cochainTime
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setCochainTime(Date cochainTime) {
        this.cochainTime = cochainTime;
    }


    /**
     * <p>Hash值</p>
     * @author: lixin
     * @return: String
     * @Date :   2018-12-18 15:46:48
     */
    public String getHash() {
        return hash;
    }

    /**
     * <p>Hash值</p>
     * @author: lixin
     * @param:   @param hash
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setHash(String hash) {
        this.hash = hash;
    }


    /**
     * <p>区块高度</p>
     * @author: lixin
     * @return: String
     * @Date :   2018-12-18 15:46:48
     */
    public String getBlockHeight() {
        return blockHeight;
    }

    /**
     * <p>区块高度</p>
     * @author: lixin
     * @param:   @param blockHeight
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setBlockHeight(String blockHeight) {
        this.blockHeight = blockHeight;
    }


    /**
     * <p>许愿内容</p>
     * @author: lixin
     * @return: String
     * @Date :   2018-12-18 15:46:48
     */
    public String getContent() {
        return content;
    }

    /**
     * <p>许愿内容</p>
     * @author: lixin
     * @param:   @param content
     * @return: void
     * @Date :   2018-12-18 15:46:48
     */
    public void setContent(String content) {
        this.content = content;
    }


}
