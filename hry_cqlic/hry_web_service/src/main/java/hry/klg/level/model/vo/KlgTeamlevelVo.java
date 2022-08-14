package hry.klg.level.model.vo;

import java.math.BigDecimal;

import hry.klg.level.model.KlgTeamlevel;

public class KlgTeamlevelVo {
	
	private Integer level;//层级
	
	private Integer startNum;//星级数量
	
	private Integer noStartNum;//非星级数量
	
	private BigDecimal buySum;//购买总数
	
	private BigDecimal weekBuySum;//周购买总数

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}

	public Integer getNoStartNum() {
		return noStartNum;
	}

	public void setNoStartNum(Integer noStartNum) {
		this.noStartNum = noStartNum;
	}

	public BigDecimal getBuySum() {
		return buySum;
	}

	public void setBuySum(BigDecimal buySum) {
		this.buySum = buySum;
	}

	public BigDecimal getWeekBuySum() {
		return weekBuySum;
	}

	public void setWeekBuySum(BigDecimal weekBuySum) {
		this.weekBuySum = weekBuySum;
	}
	
	

}
