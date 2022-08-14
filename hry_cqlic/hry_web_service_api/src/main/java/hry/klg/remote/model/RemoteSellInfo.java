/**
 * 111
 */

package hry.klg.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RemoteSellInfo implements Serializable {

    private String sellPrice;//卖出价

    private String sellNum;//卖出量

    private String sellMoney;//卖出金额
    
    private BigDecimal candyProportion;//基础糖果比例
    
    private BigDecimal profitProportion;//本人获取糖果比例

    private BigDecimal addCandyNum;//递增比率

    private Integer sellMinDay;//卖出最小时长

    private Integer sellMaxDay;//卖出最大时长


    public BigDecimal getAddCandyNum() {
        return addCandyNum;
    }

    public void setAddCandyNum(BigDecimal addCandyNum) {
        this.addCandyNum = addCandyNum;
    }

    public Integer getSellMinDay() {
        return sellMinDay;
    }

    public void setSellMinDay(Integer sellMinDay) {
        this.sellMinDay = sellMinDay;
    }

    public Integer getSellMaxDay() {
        return sellMaxDay;
    }

    public void setSellMaxDay(Integer sellMaxDay) {
        this.sellMaxDay = sellMaxDay;
    }

    public BigDecimal getCandyProportion() {
		return candyProportion;
	}

	public void setCandyProportion(BigDecimal candyProportion) {
		this.candyProportion = candyProportion;
	}

	public BigDecimal getProfitProportion() {
		return profitProportion;
	}

	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
	}

	public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getSellNum() {
        return sellNum;
    }

    public void setSellNum(String sellNum) {
        this.sellNum = sellNum;
    }

    public String getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(String sellMoney) {
        this.sellMoney = sellMoney;
    }
}
