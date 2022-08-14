package hry.ico.model.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class RecommenderOrder implements Serializable{

    private Long customerId;//我的Id

    private Integer myCount;//我购买的条数

    private Long superiorCustomerId;//上级Id

    private Integer superiorCount;//上级购买条数

    private BigDecimal number;//交易量

    public Integer getMyCount() {
        return myCount;
    }

    public void setMyCount(Integer myCount) {
        this.myCount = myCount;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSuperiorCustomerId() {
        return superiorCustomerId;
    }

    public void setSuperiorCustomerId(Long superiorCustomerId) {
        this.superiorCustomerId = superiorCustomerId;
    }

    public Integer getSuperiorCount() {
        return superiorCount;
    }

    public void setSuperiorCount(Integer superiorCount) {
        this.superiorCount = superiorCount;
    }
}
