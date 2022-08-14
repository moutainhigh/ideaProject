package hry.mall.integral.remote.model;
import hry.lend.enums.LendRemark;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/6/29 16:10
 * @Description: 杠杆账户操作 数据传递类型为 List<LendAccountAdd>
 */
public class LendAccountAdd implements Serializable {

    private Long customerId;     //用户id

    /**
     * 杠杆账户交易对
     * 如：BTC_USDT
     */
    private String coinCode;

    private BigDecimal count;   //交易量

    private TYPE type;  //账户操作

    private CodeType codeType;  //交易币 or 定价币

    private LendRemark remark;  //备注    参考LendRemark.java

    private String orderNum;    //触发单号

    /**
     * 杠杆账户与普通账户交互时需要指定普通账户id
     */
    private Long exAccount;

    /**
     * 杠杆与普通账户交互字段
     */
    private AccountType accountType;

    public enum TYPE {
        COLD_ADD("冷账户加","1"),
        COLD_SUB("冷账户减","2"),
        HOT_ADD("热账户加","3"),
        HOT_SUB("热账户减","4"),
        COLD_TO_HOT("冷账户到热账户","5"),
        HOT_TO_COLD("热账户到冷账户","6"),
        ;
        private String type;
        TYPE(String name,String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }

    public enum AccountType {
        HOT_TO_ACCOUNT("杠杆热账户到普通热账户","7"),
        ACCOUNT_TO_HOT("普通热账户到杠杆热账户","8"),
        ;
        private String type;
        AccountType(String name,String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }

    public enum CodeType {
        FIXPRICE_COINCODE("定价币"),
        TRANSACTION_COINCODE("交易币"),
        ;
        CodeType(String name) {}
    }

    /**
     * 获得操作币种
     */
    public String getCodeByCoinCode(){
        if(codeType.equals(CodeType.FIXPRICE_COINCODE)){
            return coinCode.split("_")[1];
        }else{
            return coinCode.split("_")[0];
        }
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Long getExAccount() {
        return exAccount;
    }

    public void setExAccount(Long exAccount) {
        this.exAccount = exAccount;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public LendRemark getRemark() {
        return remark;
    }

    public void setRemark(LendRemark remark) {
        this.remark = remark;
    }
}