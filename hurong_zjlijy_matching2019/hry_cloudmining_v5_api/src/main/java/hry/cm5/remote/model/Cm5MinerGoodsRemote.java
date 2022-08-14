package hry.cm5.remote.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhouming
 * @date 2020/1/9 10:38
 * @Version 1.0
 */
public class Cm5MinerGoodsRemote implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value ="矿机id")
    private Long id;

    @ApiModelProperty(value ="矿机编号")
    private String minerCode;  //矿机编号

    @ApiModelProperty(value ="矿机名称")
    private String minerName;  //矿机名称

    @ApiModelProperty(value ="标签ID")
    private Long labelId;  //标签ID

    @ApiModelProperty(value ="定价币种")
    private String pricingCoinCode;  //定价币种

    @ApiModelProperty(value ="矿机价钱")
    private BigDecimal minerPrice;  //矿机价钱

    @ApiModelProperty(value ="预计收益倍数")
    private BigDecimal profitRate;  //预计收益倍数

    @ApiModelProperty(value ="矿机图片")
    private String minerImg;  //矿机图片

    @ApiModelProperty(value ="预计总收益")
    private BigDecimal totalProfit;  //预计总收益

    @ApiModelProperty(value ="日收益")
    private BigDecimal dayProfit;  //日收益

    @ApiModelProperty(value ="1 待激活 2 已升级 3 运行中")
    private Integer minerStates; // 1 待激活 2 已升级 3 运行中

    @ApiModelProperty(value ="累计收益")
    private BigDecimal totalAward; // 累计收益

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getPricingCoinCode() {
        return pricingCoinCode;
    }

    public void setPricingCoinCode(String pricingCoinCode) {
        this.pricingCoinCode = pricingCoinCode;
    }

    public BigDecimal getMinerPrice() {
        return minerPrice;
    }

    public void setMinerPrice(BigDecimal minerPrice) {
        this.minerPrice = minerPrice;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public String getMinerImg() {
        return minerImg;
    }

    public void setMinerImg(String minerImg) {
        this.minerImg = minerImg;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getDayProfit() {
        return dayProfit;
    }

    public void setDayProfit(BigDecimal dayProfit) {
        this.dayProfit = dayProfit;
    }

    public Integer getMinerStates() {
        return minerStates;
    }

    public void setMinerStates(Integer minerStates) {
        this.minerStates = minerStates;
    }

    public BigDecimal getTotalAward() {
        return totalAward;
    }

    public void setTotalAward(BigDecimal totalAward) {
        this.totalAward = totalAward;
    }
}
