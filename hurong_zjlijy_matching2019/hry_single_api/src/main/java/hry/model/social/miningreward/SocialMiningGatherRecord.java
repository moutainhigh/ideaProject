/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:13:59
 */
package hry.model.social.miningreward;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> SocialMiningGatherRecord </p>
 * 挖矿奖励记录表
 *
 * @author: javalx
 * @Date :          2019-06-10 11:13:59
 */
@Table(name = "social_mining_gather_record")
public class SocialMiningGatherRecord extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "coinCode")
    private String coinCode;  //奖励币种

    @Column(name = "rewardId")
    private Long rewardId;  //奖励记录ID

    @Column(name = "gatherNum")
    private BigDecimal gatherNum;  //本人收取量

    @Column(name = "gatherId")
    private Long gatherId;  //收取人ID

    @Column(name = "gatherType")
    private Integer gatherType;  //收取状态 0:好友收取 1:本人收取

    @Column(name = "gatherTime")
    private Date gatherTime;  //本人收取时间

    @Column(name = "created")
    private Date created;  //收取时间

    @Column(name = "customerId")
    private Long customerId;  //奖励用户ID

    @Transient
    private String name;//用户昵称

    @Transient
    private String mobilePhone;//用户手机号

    @Transient
    private String surname;//用户姓氏

    @Transient
    private String trueName;//用户名字

    @Transient
    private String nickName;//收取人昵称

    @Transient
    private String gatedName;//被收取人昵称

    @Transient
    private Integer states;//状态 0：自己收自己，1：自己收别人，2：自己被别人收

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGatedName() {
        return gatedName;
    }

    public void setGatedName(String gatedName) {
        this.gatedName = gatedName;
    }

    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-10 11:13:59
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @param: @param id
     * @return: void
     * @Date :   2019-06-10 11:13:59
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>奖励币种</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-10 11:13:59
     */
    public String getCoinCode() {
        return coinCode;
    }

    /**
     * <p>奖励币种</p>
     *
     * @author: javalx
     * @param: @param coinCode
     * @return: void
     * @Date :   2019-06-10 11:13:59
     */
    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }


    /**
     * <p>奖励记录ID</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-10 11:13:59
     */
    public Long getRewardId() {
        return rewardId;
    }

    /**
     * <p>奖励记录ID</p>
     *
     * @author: javalx
     * @param: @param rewardId
     * @return: void
     * @Date :   2019-06-10 11:13:59
     */
    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }


    /**
     * <p>本人收取量</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-10 11:13:59
     */
    public BigDecimal getGatherNum() {
        return gatherNum;
    }

    /**
     * <p>本人收取量</p>
     *
     * @author: javalx
     * @param: @param gatherNum
     * @return: void
     * @Date :   2019-06-10 11:13:59
     */
    public void setGatherNum(BigDecimal gatherNum) {
        this.gatherNum = gatherNum;
    }


    /**
     * <p>收取人ID</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-10 11:13:59
     */
    public Long getGatherId() {
        return gatherId;
    }

    /**
     * <p>收取人ID</p>
     *
     * @author: javalx
     * @param: @param gatherId
     * @return: void
     * @Date :   2019-06-10 11:13:59
     */
    public void setGatherId(Long gatherId) {
        this.gatherId = gatherId;
    }


    /**
     * <p>收取状态 0:好友收取 1:本人收取</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-10 11:13:59
     */
    public Integer getGatherType() {
        return gatherType;
    }

    /**
     * <p>收取状态 0:好友收取 1:本人收取</p>
     *
     * @author: javalx
     * @param: @param gatherType
     * @return: void
     * @Date :   2019-06-10 11:13:59
     */
    public void setGatherType(Integer gatherType) {
        this.gatherType = gatherType;
    }


    /**
     * <p>本人收取时间</p>
     *
     * @author: javalx
     * @return: Date
     * @Date :   2019-06-10 11:13:59
     */
    public Date getGatherTime() {
        return gatherTime;
    }

    /**
     * <p>本人收取时间</p>
     *
     * @author: javalx
     * @param: @param gatherTime
     * @return: void
     * @Date :   2019-06-10 11:13:59
     */
    public void setGatherTime(Date gatherTime) {
        this.gatherTime = gatherTime;
    }


    /**
     * <p>奖励用户ID</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-10 11:13:59
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>奖励用户ID</p>
     *
     * @author: javalx
     * @param: @param customerId
     * @return: void
     * @Date :   2019-06-10 11:13:59
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
}
