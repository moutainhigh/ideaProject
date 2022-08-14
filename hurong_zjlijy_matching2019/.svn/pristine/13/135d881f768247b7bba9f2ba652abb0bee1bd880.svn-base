package hry.cm5.remote.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhouming
 * @date 2020/1/15 13:57
 * @Version 1.0
 */
public class Cm5MinerOrderRemote implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value ="订单id")
    private Long id;

    @ApiModelProperty(value ="矿机id")
    private Long minerId; // 矿机id

    @ApiModelProperty(value ="矿机编号")
    private String minerCode;  //矿机编号

    @ApiModelProperty(value ="矿机名称")
    private String minerName;  //矿机名称

    @ApiModelProperty(value ="订单编号")
    private String orderNum;  //订单编号

    @ApiModelProperty(value ="订单金额")
    private BigDecimal orderActualPrice; //订单金额

    @ApiModelProperty(value ="支付币种")
    private String payCoinCode; // 支付币种

    @ApiModelProperty(value ="购买时间")
    private Date created; // 购买时间

    @ApiModelProperty(value ="订单类型 1 购买 2 升级")
    private Integer orderType; // 订单类型 1 购买 2 升级

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMinerId() {
        return minerId;
    }

    public void setMinerId(Long minerId) {
        this.minerId = minerId;
    }

    public String getMinerCode() {
        return minerCode;
    }

    public void setMinerCode(String minerCode) {
        this.minerCode = minerCode;
    }

    public String getMinerName() {
        return minerName;
    }

    public void setMinerName(String minerName) {
        this.minerName = minerName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getOrderActualPrice() {
        return orderActualPrice;
    }

    public void setOrderActualPrice(BigDecimal orderActualPrice) {
        this.orderActualPrice = orderActualPrice;
    }

    public String getPayCoinCode() {
        return payCoinCode;
    }

    public void setPayCoinCode(String payCoinCode) {
        this.payCoinCode = payCoinCode;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
