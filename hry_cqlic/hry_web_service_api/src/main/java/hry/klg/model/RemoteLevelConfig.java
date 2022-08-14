/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-17 14:31:17 
 */
package hry.klg.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

public class RemoteLevelConfig extends BaseModel {
	
	
	private Long id;  //
	
	private String levelName;  //等级名称
	
	private Integer sort;  //等级排序
	
	private BigDecimal buyNum;  //购买限制

	private String buyNums;  //购买限制

	private BigDecimal bonusMultiple;  //奖金倍数
	
	private Integer pointAlgebra;  //见点代数
	
	private Integer sellTime;  //基础卖出时长
	
	private BigDecimal candyNum;  //基础糖果比率
	
	private BigDecimal addCandyNum;  //递增糖果比率
	
	private Integer maxSellTime;  //最高卖出时长

	private Integer maxRewardTime;  //最高奖励时长

	private Integer recommendNum;  //推荐人数
	
	private Integer recommendSort;  //推荐星级别

	public String getBuyNums() {
		return buyNums;
	}

	public Integer getMaxRewardTime() {
		return maxRewardTime;
	}

	public void setMaxRewardTime(Integer maxRewardTime) {
		this.maxRewardTime = maxRewardTime;
	}

	public void setBuyNums(String buyNums) {
		this.buyNums = buyNums;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public BigDecimal getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(BigDecimal buyNum) {
		this.buyNum = buyNum;
	}

	public BigDecimal getBonusMultiple() {
		return bonusMultiple;
	}

	public void setBonusMultiple(BigDecimal bonusMultiple) {
		this.bonusMultiple = bonusMultiple;
	}

	public Integer getPointAlgebra() {
		return pointAlgebra;
	}

	public void setPointAlgebra(Integer pointAlgebra) {
		this.pointAlgebra = pointAlgebra;
	}

	public Integer getSellTime() {
		return sellTime;
	}

	public void setSellTime(Integer sellTime) {
		this.sellTime = sellTime;
	}

	public BigDecimal getCandyNum() {
		return candyNum;
	}

	public void setCandyNum(BigDecimal candyNum) {
		this.candyNum = candyNum;
	}

	public BigDecimal getAddCandyNum() {
		return addCandyNum;
	}

	public void setAddCandyNum(BigDecimal addCandyNum) {
		this.addCandyNum = addCandyNum;
	}

	public Integer getMaxSellTime() {
		return maxSellTime;
	}

	public void setMaxSellTime(Integer maxSellTime) {
		this.maxSellTime = maxSellTime;
	}

	public Integer getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(Integer recommendNum) {
		this.recommendNum = recommendNum;
	}

	public Integer getRecommendSort() {
		return recommendSort;
	}

	public void setRecommendSort(Integer recommendSort) {
		this.recommendSort = recommendSort;
	}
}
