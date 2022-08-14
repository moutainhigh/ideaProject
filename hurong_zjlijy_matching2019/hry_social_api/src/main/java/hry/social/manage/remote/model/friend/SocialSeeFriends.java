/**
 * Copyright:
 *
 * @author: qiuf
 * @version: V1.0
 * @Date: 2018-10-16 11:23:08
 */
package hry.social.manage.remote.model.friend;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.util.List;

/**
 * 好友关系实体
 *
 * @author : javalx
 * @version : V1.0
 * @desc :
 * @date : 2019/6/3 13:47
 */
@Table(name = "social_see_friends")
public class SocialSeeFriends extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "nickNames")
    private String nickNames;  //昵称

    @Transient
    private String trueName;  //姓
    @Transient
    private String surname; //名

    @Column(name = "phone")
    private String phone;  //手机号

    @Column(name = "sid")
    private Long sid;  //跟id

    @Column(name = "uid")
    private Long uid;  //好友id

    @Column(name = "headPortrait")
    private String headPortrait;  //头像

    @Column(name = "states")
    private Integer states;  //状态（0：未验证，1：接受）

    @Column(name = "collectionState")
    private Integer collectionState;  //好友果实是，否可收取（0 ：否，1 ：是）

    @Column(name = "name")
    private String name;  //

    @Transient
    private double calculationForce;

    @Transient
    private String nickName;  //昵称
    @Transient
    private String mobilePhone;  //手机
    @Transient
    private String remark;  //备注
    @Transient
    private List<String> images;  //图片
    @Transient
    private String themeImg;  //朋友圈主题
    @Transient
    private String mood;  //发表心情

    @Transient
    private String email;  //邮箱

    @Transient
    private String userName;  //用户名

    @Transient
    private Integer isFriend;  //是否为好友,0：否 1：是

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Integer getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Integer isFriend) {
        this.isFriend = isFriend;
    }

    public String getThemeImg() {
        return themeImg;
    }

    public void setThemeImg(String themeImg) {
        this.themeImg = themeImg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickNames() {
        return nickNames;
    }

    public void setNickNames(String nickNames) {
        this.nickNames = nickNames;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public Integer getCollectionState() {
        return collectionState;
    }

    public void setCollectionState(Integer collectionState) {
        this.collectionState = collectionState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalculationForce() {
        return calculationForce;
    }

    public void setCalculationForce(double calculationForce) {
        this.calculationForce = calculationForce;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
