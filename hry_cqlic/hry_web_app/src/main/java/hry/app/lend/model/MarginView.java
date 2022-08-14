package hry.app.lend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/11/2 16:19
 * @Description:
 */
@ApiModel(value = "首次访问杠杆交易大厅")
public class MarginView {

    @ApiModelProperty(value = "用户唯一标识，需设置cookie，key值为marginToken")
    private String marginToken;

    @ApiModelProperty(value = "交易对")
    private String coinCode;

    @ApiModelProperty(value = "最新价")
    private String newPrice;

    @ApiModelProperty(value = "最小下单量")
    private BigDecimal coinMinCount;	//最小下单量

    @ApiModelProperty(value = "最大下单量")
    private BigDecimal coinMaxCount;	//最大下单量

    @ApiModelProperty(value = "买方手续费率")
    private BigDecimal buyFeeRate;  //买方手续费率

    @ApiModelProperty(value = "卖方手续费率")
    private BigDecimal sellFeeRate;  //卖方手续费率

    @ApiModelProperty(value = "k线展示的时间线")
    private List<TimesConfig.Times> timesConfig;

    public List<TimesConfig.Times> getTimesConfig() {
        return timesConfig;
    }

    public void setTimesConfig(List<TimesConfig.Times> timesConfig) {
        this.timesConfig = timesConfig;
    }

    public String getMarginToken() {
        return marginToken;
    }

    public void setMarginToken(String marginToken) {
        this.marginToken = marginToken;
    }

    public BigDecimal getBuyFeeRate() {
        return buyFeeRate;
    }

    public void setBuyFeeRate(BigDecimal buyFeeRate) {
        this.buyFeeRate = buyFeeRate;
    }

    public BigDecimal getSellFeeRate() {
        return sellFeeRate;
    }

    public void setSellFeeRate(BigDecimal sellFeeRate) {
        this.sellFeeRate = sellFeeRate;
    }

    public BigDecimal getCoinMinCount() {
        return coinMinCount;
    }

    public void setCoinMinCount(BigDecimal coinMinCount) {
        this.coinMinCount = coinMinCount;
    }

    public BigDecimal getCoinMaxCount() {
        return coinMaxCount;
    }

    public void setCoinMaxCount(BigDecimal coinMaxCount) {
        this.coinMaxCount = coinMaxCount;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

}