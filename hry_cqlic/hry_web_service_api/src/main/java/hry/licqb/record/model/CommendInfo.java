package hry.licqb.record.model;

import hry.bean.BaseModel;

import java.math.BigDecimal;

/**
 * @author zhouming
 * @date 2019/8/29 16:57
 * @Version 1.0
 */
public class CommendInfo extends BaseModel {

    public Long customerId; // 用户Id

    public String email; // 用户邮箱

    public String mobilePhone; // 手机号

    public String levelName; // 个人等级名称

    public String sort; // 个人等级排序

    public BigDecimal investMoney; //投资金额

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }
}
