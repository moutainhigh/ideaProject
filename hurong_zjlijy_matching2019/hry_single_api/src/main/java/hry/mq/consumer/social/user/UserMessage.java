package hry.mq.consumer.social.user;

import java.io.Serializable;

/**
 * 用户处理（注册）消息载体
 *
 * @author javal
 * @title: UserMessage
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/8/610:20
 */
public class UserMessage implements Serializable {

    private String username; // 用户标识

    private Long customerId; // 用户ID

    private String mobile; // 用户手机

    private String email; // 用户邮箱

    private String country; // 国家地址字段

    private String referralCode; // 自己的注册推荐码

    private String commendCode;    //推荐人的推荐码

    private String headPortrait;// 用户头像

    private String nickName; // 用户昵称

    private String accid; //网易云信帐号

    private String token; //网易云信密码

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getCommendCode() {
        return commendCode;
    }

    public void setCommendCode(String commendCode) {
        this.commendCode = commendCode;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserMessage{" + "username='" + username + '\'' + ", customerId=" + customerId + ", mobile='" + mobile + '\'' + ", email='" + email + '\'' + ", country='" + country + '\'' + ", referralCode='" + referralCode + '\'' + ", commendCode='" + commendCode + '\'' + ", headPortrait='" + headPortrait + '\'' + ", nickName='" + nickName + '\'' + ", accid='" + accid + '\'' + ", token='" + token + '\'' + '}';
    }
}
