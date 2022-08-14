/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Liu Shilei
 * @version: V1.0
 * @Date: 2016年3月24日 下午2:59:48
 */
package hry.social.manage.remote.model.user;

import hry.bean.BaseModel;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 客户基础表---登录账号信息
 * </p>
 *
 * @author: Liu Shilei
 * @Date : 2016年3月24日 下午2:59:48
 */
@Table(name = "app_customer")
public class AppCustomer extends BaseModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username")
    private String userName; // 用户名

    @Column(name = "passWord")
    private String passWord; // 密码

    @Column(name = "accountPassWord")
    private String accountPassWord; // 交易密码

    @Column(name = "salt")
    private String salt; // 盐

    @Column(name = "isLock")
    private Integer isLock; // 是否锁定 0没锁定 1锁定

    @Column(name = "lockTime")
    private Date lockTime; // 锁定时间

    @Column(name = "isChange")
    private Integer isChange; // 是否能交易 0可以交易1不能交易

    @Column(name = "isDelete")
    private Integer isDelete; // 是否禁用 0没有禁用 1禁用

    @Column(name = "isReal")
    private Integer isReal; // 是否实名 0没有实名 1实名

    @Column(name = "userCode")
    private String userCode; // 用户唯一ID标识 系统生成

    @Column(name = "integral")
    private Integer integral; // 积分

    @Column(name = "type")
    private String type; // integral 积分用户

    @Transient // 不与数据库映射的字段
    private Object appPersonInfo;

    // 注册码
    @Column(name = "referralCode") // 自己得注册推荐码
    private String referralCode;

    // 用户是否邮箱激活
    @Column(name = "hasEmail")
    public Integer hasEmail; // 0未激活,1激活

    @Column(name = "googleKey")
    private String googleKey;

    @Column(name = "googleState")
    private Integer googleState;// 谷歌认证状态(0否，1是)

    @Column(name = "googleDate")
    private Date googleDate;// 停用时间

    @Column(name = "isProving")
    private Integer isProving;// 二次验证是否开启（0 否,1是）

    @Column(name = "messIp")
    private String messIp;// 二次验证是否开启（0 否,1是）

    @Column(name = "passDate")
    private Date passDate;// 二次验证是否开启（0 否,1是）

    @Column(name = "phoneState")
    private Integer phoneState;// 手机认证状态(0否，1是)

    @Column(name = "openPhone")
    private Integer openPhone;// 谷歌认证状态(0否，1是)

    // 状态( 0 未实名 1 待审核 2 已实名 3 已拒绝)
    @Column(name = "states")
    private Integer states;

    @Column(name = "commendCode") // 推荐人的推荐码
    private String commendCode;


    @Column(name = "agentCommendCode") // 代理的推荐码
    private String agentCommendCode;

    @Column(name = "safeLoginType") // 登录安全策略
    private Integer safeLoginType; // 1登录密码,2登录密码加手机，3登录密码加google或者手机 ,默认为1

    @Column(name = "safeTixianType") // 提现安全策略
    private Integer safeTixianType; // 1登录密码,2登录密码加手机，3登录密码加google或者手机 ,默认为1

    @Column(name = "safeTradeType") // 交易安全策略
    private Integer safeTradeType; // 1登录密码,2登录密码加手机，3登录密码加google或者手机 ,默认为1

    @Column(name = "isBlacklist") // 是否黑名单
    private Integer isBlacklist; // 0否,1是,默认为0 2为白名单
    @Column(name = "isGag") // 是否禁言
    private Integer isGag; // 0否 1是
    @Column(name = "gagDate") // 禁言开始时间
    private Date gagDate;
    @Column(name = "gagTime") // 禁言时长
    private Integer gagTime;
    @Column(name = "isAdmin") // 是否管理员
    private Integer isAdmin; // 0否 1是

    @Column(name = "uuid") // uuid
    private String uuid;

    @Column(name = "ssoId") // ssoId
    private String ssoId;

    @Column(name = "shopCommendCode") // 商铺掌柜邀请码（被掌柜邀请的邀请码）
    private String shopCommendCode;

    @Column(name = "shopInvitationCode") // 商城邀请码
    private String shopInvitationCode;

    @Column(name = "fansGroupId") // 最新粉丝群ID
    private String fansGroupId;

    @Transient
    private int oneNumber; // sid

    @Transient
    private int twoNumber; // sid

    @Transient
    private int threeNumber; // sid

    @Transient
    private int laterNumber; // sid

    @Transient
    private String trueName; // 真实名

    @Transient
    private String surname; // 真实姓
    @Column(name = "trustNum", columnDefinition = "bigint(20) DEFAULT '0' COMMENT '信任次数'")
    private Integer trustNum;

    @Column(name = "shieldNum", columnDefinition = "bigint(20) DEFAULT '0' COMMENT '屏蔽次数'")
    private Integer shieldNum;

    @Column(name = "nickNameOtc", columnDefinition = "varchar(255) DEFAULT null COMMENT 'otc昵称'")
    private String nickNameOtc;
    @Column(name = "commonLanguage")
    private String commonLanguage;

    @Transient
    private String headPortrait;// 头像

    @Transient
    private String nickName; // 昵称

    @Transient
    private String accid; //网易云信ID
    //总线编号
    @Column(name = "busNumber")
    private String busNumber;

    @Column(name = "isMarketTransactions")
    private Integer isMarketTransactions;//是否开启市价交易  0 未开启  1已开启

    public Integer getTrustNum() {
        return trustNum;
    }

    public void setTrustNum(Integer trustNum) {
        this.trustNum = trustNum;
    }

    public Integer getShieldNum() {
        return shieldNum;
    }

    public void setShieldNum(Integer shieldNum) {
        this.shieldNum = shieldNum;
    }

    public String getNickNameOtc() {
        return nickNameOtc;
    }

    public void setNickNameOtc(String nickNameOtc) {
        this.nickNameOtc = nickNameOtc;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public Integer getIsMarketTransactions() {
        return isMarketTransactions;
    }

    public void setIsMarketTransactions(Integer isMarketTransactions) {
        this.isMarketTransactions = isMarketTransactions;
    }

    public String getFansGroupId() {
        return fansGroupId;
    }

    public void setFansGroupId(String fansGroupId) {
        this.fansGroupId = fansGroupId;
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

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getShopCommendCode() {
        return shopCommendCode;
    }

    public void setShopCommendCode(String shopCommendCode) {
        this.shopCommendCode = shopCommendCode;
    }

    public String getShopInvitationCode() {
        return shopInvitationCode;
    }

    public void setShopInvitationCode(String shopInvitationCode) {
        this.shopInvitationCode = shopInvitationCode;
    }

    public String getAgentCommendCode() {
        return agentCommendCode;
    }

    public void setAgentCommendCode(String agentCommendCode) {
        this.agentCommendCode = agentCommendCode;
    }

    public String getCommonLanguage() {
        return commonLanguage;
    }

    public void setCommonLanguage(String commonLanguage) {
        this.commonLanguage = commonLanguage;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getSafeLoginType() {
        return safeLoginType;
    }

    public void setSafeLoginType(Integer safeLoginType) {
        this.safeLoginType = safeLoginType;
    }

    public Integer getSafeTixianType() {
        return safeTixianType;
    }

    public void setSafeTixianType(Integer safeTixianType) {
        this.safeTixianType = safeTixianType;
    }

    public Integer getSafeTradeType() {
        return safeTradeType;
    }

    public void setSafeTradeType(Integer safeTradeType) {
        this.safeTradeType = safeTradeType;
    }

    public String getCommendCode() {
        return commendCode;
    }

    public void setCommendCode(String commendCode) {
        this.commendCode = commendCode;
    }

    public int getOneNumber() {
        return oneNumber;
    }

    public void setOneNumber(int oneNumber) {
        this.oneNumber = oneNumber;
    }

    public int getTwoNumber() {
        return twoNumber;
    }

    public void setTwoNumber(int twoNumber) {
        this.twoNumber = twoNumber;
    }

    public int getThreeNumber() {
        return threeNumber;
    }

    public void setThreeNumber(int threeNumber) {
        this.threeNumber = threeNumber;
    }

    public int getLaterNumber() {
        return laterNumber;
    }

    public void setLaterNumber(int laterNumber) {
        this.laterNumber = laterNumber;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public Integer getOpenPhone() {
        return openPhone;
    }

    public void setOpenPhone(Integer openPhone) {
        this.openPhone = openPhone;
    }

    public Integer getPhoneState() {
        return phoneState;
    }

    public void setPhoneState(Integer phoneState) {
        this.phoneState = phoneState;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public String getMessIp() {
        return messIp;
    }

    public void setMessIp(String messIp) {
        this.messIp = messIp;
    }

    public Integer getGoogleState() {
        return googleState;
    }

    public void setGoogleState(Integer googleState) {
        this.googleState = googleState;
    }

    public Date getGoogleDate() {
        return googleDate;
    }

    public void setGoogleDate(Date googleDate) {
        this.googleDate = googleDate;
    }

    public Integer getIsProving() {
        return isProving;
    }

    public void setIsProving(Integer isProving) {
        this.isProving = isProving;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public Integer getIsChange() {
        return isChange;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }

    public Integer getHasEmail() {
        return hasEmail;
    }

    public void setHasEmail(Integer hasEmail) {
        this.hasEmail = hasEmail;
    }

    /**
     * @return the referralCode
     */
    public String getReferralCode() {
        return referralCode;
    }

    /**
     * @param referralCode the referralCode to set
     */
    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Long
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public String getAccountPassWord() {
        return accountPassWord;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public void setAccountPassWord(String accountPassWord) {
        this.accountPassWord = accountPassWord;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public Integer getIsLock() {
        return isLock;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Date
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Date
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public Integer getIsReal() {
        return isReal;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public String getSalt() {
        return salt;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Object
     */
    public Object getAppPersonInfo() {
        return appPersonInfo;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Object
     */
    public void setAppPersonInfo(Object appPersonInfo) {
        this.appPersonInfo = appPersonInfo;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: Integer
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public String getType() {
        return type;
    }

    /**
     * <p>
     * TODO
     * </p>
     *
     * @return: String
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getGoogleKey() {
        return googleKey;
    }

    public void setGoogleKey(String googleKey) {
        this.googleKey = googleKey;
    }

    public Integer getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Integer isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public Integer getIsGag() {
        return isGag;
    }

    public void setIsGag(Integer isGag) {
        this.isGag = isGag;
    }

    public Date getGagDate() {
        return gagDate;
    }

    public void setGagDate(Date gagDate) {
        this.gagDate = gagDate;
    }

    public Integer getGagTime() {
        return gagTime;
    }

    public void setGagTime(Integer gagTime) {
        this.gagTime = gagTime;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }
}
