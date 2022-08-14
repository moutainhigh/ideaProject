package hry.lend.enums;

public enum LendRemark {
    /**
     * 交易账户日志相关
     */
    TYPE1(1,"杠杆交易：卖方加"),
    TYPE2(2,"杠杆交易：卖方减"),
    TYPE3(3,"杠杆交易：买方加"),
    TYPE4(4,"杠杆交易：买方减"),
    TYPE5(5,"杠杆交易：买方冻结"),
    TYPE6(6,"杠杆交易：卖方冻结"),
    TYPE7(7,"杠杆交易：委托撤消"),
    /**
     * 委托相关
     */
    TYPE10(10,"杠杆买入"),
    TYPE11(11,"杠杆卖出"),
    /**
     * 杠杆相关
     */
    TYPE21(21,"杠杆操作：申请借款"),
    TYPE22(22,"杠杆操作：传出到交易账户"),
    TYPE23(23,"杠杆操作：从交易账户转入"),
    TYPE24(24,"杠杆操作：归还本金"),
    TYPE25(25,"杠杆操作：归还利息"),
    ;

    private Integer code;
    private String remark;
    LendRemark(Integer code,String remark) {
        this.remark = remark;
        this.code = code;
    }
    public String getRemark() {
        return remark;
    }

    public Integer getCode() {
        return code;
    }
}

