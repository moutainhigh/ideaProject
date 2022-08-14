/**
 * Copyright:   北京互融时代软件有限公司
  * @author:         Gao Mimi 
 * @Date :          2016年8月25日 下午4:15:59
 * @version:      V1.0 
 */
package hry.exchange.product.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * 
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年8月25日 下午4:15:59
 */

public class ProductCommon implements Serializable {
	 private static final long serialVersionUID = -4825890686624512635L;
	
	
	// 最小拆分的数量
	private BigDecimal splitMinCoin;
	
	//1竞价（也就是正常的）2，坐市
	private Integer transactionType;
	//图片路径
	private String picturePath; 
	// 是否置顶显示
	private Integer isRecommend;
	// 产品的名称
	private String name;
	// 产品的排序字段
	private Integer sort;

//  '1表示开市   2表示闭市'
	private Integer openBell;
/*	private Integer belongType;  //所属类（1表示pc交易   2表示微盘交易）
	// 融资手续费率
	private BigDecimal circulation;
	private BigDecimal delayFeeRate;  //延期收费 （手/天）
	// 保证金费率 
	private BigDecimal depositRate;
	*/
	// 是否开启提币
	private String openTibi;
	
	
	// 币的代码 (股票的代码) 不能为空
	private String coinCode;
	private String fixPriceCoinCode;  //定价币种

	private Integer keepDecimalFixPrice;  //定价币的保留几位小数
	private Integer keepDecimalForCoin;   //交易币

	// 买方手续费 率
	private BigDecimal buyFeeRate;

	// 卖方手续费 率
	private BigDecimal sellFeeRate;

	// 市价下最小下单定价币
	private BigDecimal buyMinMoney;

	// 每次下单的最小数据
	private BigDecimal oneTimeOrderNumMin;
	//每次下单的最大数量
	private BigDecimal oneTimeOrderNum;
	// 价格限制 根据当前价格的涨幅
	private BigDecimal priceLimits;
	
	//涨幅 针对开盘价的涨幅
	private BigDecimal rose;
	
	//跌幅
	private BigDecimal decline;
	
	//均价（收盘价）
	private BigDecimal averagePrice;
			

	public Integer getKeepDecimalFixPrice() {
		return keepDecimalFixPrice;
	}

	public void setKeepDecimalFixPrice(Integer keepDecimalFixPrice) {
		this.keepDecimalFixPrice = keepDecimalFixPrice;
	}

	public BigDecimal getOneTimeOrderNum() {
		return oneTimeOrderNum;
	}

	public void setOneTimeOrderNum(BigDecimal oneTimeOrderNum) {
		this.oneTimeOrderNum = oneTimeOrderNum;
	}

	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}

	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getOpenBell() {
		return openBell;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setOpenBell(Integer openBell) {
		this.openBell = openBell;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getKeepDecimalForCoin() {
		return keepDecimalForCoin;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setKeepDecimalForCoin(Integer keepDecimalForCoin) {
		this.keepDecimalForCoin = keepDecimalForCoin;
	}


	public String getOpenTibi () {
		return openTibi;
	}

	public void setOpenTibi (String openTibi) {
		this.openTibi = openTibi;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getPriceLimits() {
		return priceLimits;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setPriceLimits(BigDecimal priceLimits) {
		this.priceLimits = priceLimits;
	}




	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getName() {
		return name;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getSort() {
		return sort;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getTransactionType() {
		return transactionType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getPicturePath() {
		return picturePath;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsRecommend() {
		return isRecommend;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getBuyFeeRate() {
		return buyFeeRate;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setBuyFeeRate(BigDecimal buyFeeRate) {
		this.buyFeeRate = buyFeeRate;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getSellFeeRate() {
		return sellFeeRate;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setSellFeeRate(BigDecimal sellFeeRate) {
		this.sellFeeRate = sellFeeRate;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getBuyMinMoney() {
		return buyMinMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setBuyMinMoney(BigDecimal buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}


	public BigDecimal getOneTimeOrderNumMin() {
		return oneTimeOrderNumMin;
	}

	public void setOneTimeOrderNumMin(BigDecimal oneTimeOrderNumMin) {
		this.oneTimeOrderNumMin = oneTimeOrderNumMin;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getSplitMinCoin() {
		return splitMinCoin;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setSplitMinCoin(BigDecimal splitMinCoin) {
		this.splitMinCoin = splitMinCoin;
	}

	public BigDecimal getRose() {
		return rose;
	}

	public void setRose(BigDecimal rose) {
		this.rose = rose;
	}

	public BigDecimal getDecline() {
		return decline;
	}

	public void setDecline(BigDecimal decline) {
		this.decline = decline;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}
	



}
