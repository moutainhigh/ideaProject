package hry.social.manage.remote.model.base;

import hry.bean.BaseModel;

import java.util.List;

/**
 * @author : javalx
 * @version : V1.0
 * @desc : APP前端用户信息
 * @date : 2019/5/31 18:36
 */
public class AppUserInfo extends BaseModel {

    //用户名
    private String userName;

    //密码
    private String passWord;

    //交易密码
    private String accountPassWord;

    //是否实名0没有实名，1实名
    private Integer isReal;

    //实名状态，( 0 未实名 1 待审核 2 已实名 3 已拒绝)
    private Integer states;

    //是否能交易  0可以交易  1不能交易
    private Integer isChange;

    //是否禁用  0没有禁用 1禁用
    private Integer isDelete;

    //是否设置了交易密码（0没有，1设置了）
    private Integer hasAcPwd;

    //用户编码
    private String userCode;

    //当前登录用户标识
    private String uuid;

    //前台用户id
    private Long customerId;

    //手机号
    private String mobilePhone;

    //国家
    private String country;

    //证件类型
    private Integer cardType;

    //身份证号
    private String cardId;

    //邮箱
    private String email;

    //邮箱验证码
    private String emailCode;

    // 用户是否邮箱激活
    public Integer hasEmail; // 0未激活,1激活

    // 手机认证状态
    private Integer phoneState;// (0否，1是)

    //真实名
    private String truename;

    //真实姓
    private String surname;

    //网易云信ID
    private String accid;

    //网易云信token（密码）
    private String token;

    //语言
    private String commonLanguage;

    //昵称
    private String nickName;

    //头像
    private String headPortrait;

    //用户二维码
    private String quickMark;

    //性别  0男  1女
    private Integer sex;

    //生日
    private String birthday;

    //朋友圈主题图片
    private String themeImg;

    //心情
    private String mood;

    //上次登录用户标识
    private String oldUuid;

    //自己的邀请码
    private String referralCode;

    //邀请二维码(二码合一)
    private String inviteCode;

    //推荐人ID
    private Long commendId;

    //商城邀请码二维码
    private String shopInviteCode;

    //商铺掌柜邀请码（被掌柜邀请的邀请码）
    private String shopCommendCode;

    //商城掌柜的邀请码（非二维码）
    private String shopInvitationCode;

    //商城用户标识
    private String ssoId;

    //shopId
    private Long shopId;

    // 最新粉丝群ID
    private String fansGroupId;

    // 推荐人的推荐码
    private String commendCode;

    private String countryArea; //国家及地区

    private String regModify; //注册协议对比

//    private List<SocialAppModuleConfig> moduleConfigs;

    private String nickNameOtc; // otc昵称

    public String getNickNameOtc() {
        return nickNameOtc;
    }

    public void setNickNameOtc(String nickNameOtc) {
        this.nickNameOtc = nickNameOtc;
    }

    public String getRegModify() {
        return regModify;
    }

    public void setRegModify(String regModify) {
        this.regModify = regModify;
    }

//    public List<SocialAppModuleConfig> getModuleConfigs() {
//        return moduleConfigs;
//    }
//
//    public void setModuleConfigs(List<SocialAppModuleConfig> moduleConfigs) {
//        this.moduleConfigs = moduleConfigs;
//    }

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public String getCommendCode() {
        return commendCode;
    }

    public void setCommendCode(String commendCode) {
        this.commendCode = commendCode;
    }

    public String getFansGroupId() {
        return fansGroupId;
    }

    public void setFansGroupId(String fansGroupId) {
        this.fansGroupId = fansGroupId;
    }

    public String getSsoId() {
        return ssoId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public Integer getHasEmail() {
        return hasEmail;
    }

    public void setHasEmail(Integer hasEmail) {
        this.hasEmail = hasEmail;
    }

    public Integer getPhoneState() {
        return phoneState;
    }

    public void setPhoneState(Integer phoneState) {
        this.phoneState = phoneState;
    }

    public String getShopInviteCode() {
        return shopInviteCode;
    }

    public void setShopInviteCode(String shopInviteCode) {
        this.shopInviteCode = shopInviteCode;
    }

    public Long getCommendId() {
        return commendId;
    }

    public void setCommendId(Long commendId) {
        this.commendId = commendId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAccountPassWord() {
        return accountPassWord;
    }

    public void setAccountPassWord(String accountPassWord) {
        this.accountPassWord = accountPassWord;
    }

    public Integer getIsReal() {
        return isReal;
    }

    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public Integer getIsChange() {
        return isChange;
    }

    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getHasAcPwd() {
        return hasAcPwd;
    }

    public void setHasAcPwd(Integer hasAcPwd) {
        this.hasAcPwd = hasAcPwd;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCommonLanguage() {
        return commonLanguage;
    }

    public void setCommonLanguage(String commonLanguage) {
        this.commonLanguage = commonLanguage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getQuickMark() {
        return quickMark;
    }

    public void setQuickMark(String quickMark) {
        this.quickMark = quickMark;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getThemeImg() {
        return themeImg;
    }

    public void setThemeImg(String themeImg) {
        this.themeImg = themeImg;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getOldUuid() {
        return oldUuid;
    }

    public void setOldUuid(String oldUuid) {
        this.oldUuid = oldUuid;
    }
}
