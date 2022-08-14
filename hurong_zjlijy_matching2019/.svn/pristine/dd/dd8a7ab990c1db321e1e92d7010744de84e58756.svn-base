package hry.app.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "测试添加用户")
public class TestAddUser implements Serializable {


    @ApiModelProperty(value = "用户名",example="用户名",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",example="密码",required = true)
    private String passWord;

    @ApiModelProperty(value = "邀请码",example="邀请码",required = false)
    private String referralCode ;

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

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }
}

