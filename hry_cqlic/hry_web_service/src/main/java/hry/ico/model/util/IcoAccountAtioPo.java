package hry.ico.model.util;

public class IcoAccountAtioPo {
    private  String  accountAtio; //锁仓占比

    private  Long customerId;//用户Id

    private  Long  level_id ; //等级Id

    private Integer sort;//等级排序

    private String additionRatio;// 等级加成比率

    public String getAdditionRatio() {
        return additionRatio;
    }

    public void setAdditionRatio(String additionRatio) {
        this.additionRatio = additionRatio;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
}
