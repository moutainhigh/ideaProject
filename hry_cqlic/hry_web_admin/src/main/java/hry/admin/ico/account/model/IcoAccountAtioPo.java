package hry.admin.ico.account.model;

import java.io.Serializable;

public class IcoAccountAtioPo implements Serializable {
    private String accountAtio; //占比
    private String additionRatio; //等级加成
    private Long customerId; //用户Id
    private Long level_id; //等级ID
    private Integer sort; //等级排序
    private String email; //用户邮箱
    private String mobilePhone; //用户手机
    private String gradeName; //等级名称
    private String storageMoney; //锁仓量

    public String getStorageMoney() {
        return storageMoney;
    }

    public void setStorageMoney(String storageMoney) {
        this.storageMoney = storageMoney;
    }

    public String getAdditionRatio() {
        return additionRatio;
    }

    public void setAdditionRatio(String additionRatio) {
        this.additionRatio = additionRatio;
    }

    public String getAccountAtio() {
        return accountAtio;
    }

    public void setAccountAtio(String accountAtio) {
        this.accountAtio = accountAtio;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Long level_id) {
        this.level_id = level_id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
