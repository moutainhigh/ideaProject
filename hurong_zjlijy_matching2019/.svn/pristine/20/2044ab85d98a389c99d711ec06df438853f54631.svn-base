/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-03-19 15:25:34 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> IntegralLevel </p>
 * @author:         luyue
 * @Date :          2019-03-19 15:25:34  
 */
@Table(name="shop_integral_level")
public class IntegralLevel extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "number")
	private Integer number;  //排序号
	
	@Column(name= "name")
	private String name;  //等级名称
	
	@Column(name= "image")
	private String image;  //会员图标
	
	@Column(name= "recommendCOunt")
	private Integer recommendCOunt;  //推广人数
	
	@Column(name= "baseQuota")
	private BigDecimal baseQuota;  //基础额度
	
	@Column(name= "maxQuota")
	private BigDecimal maxQuota;  //额度上限
	
	@Column(name= "recommendQuota")
	private BigDecimal recommendQuota;  //推广额度
	
	@Column(name= "feeRate")
	private BigDecimal feeRate;  //转让手续费比例


	@Column(name= "price")
	private BigDecimal price;  //会员售价

	@Column(name= "validityPeriod")
	private Integer validityPeriod;  //会员年限

	@Column(name= "staticDividend")
	private BigDecimal staticDividend;  //静态分红

	@Column(name= "directReward")
	private BigDecimal directReward;  //直推奖励

	@Column(name= "expansionReward")
	private BigDecimal expansionReward;  //拓展奖励

	@Column(name= "description")
	private String description;  //等级说明
	
	@Transient
	private String nickNameOtc;//昵称
	@Transient
	private BigDecimal fuwuRate;  //服务比例
	@Transient
	private BigDecimal fuliRate;  //福利比例
	@Transient
	private String userFace;//用户头像
	@Transient
	private Date endDate;  //结束日期


	@Column(name= "lowestPrice")
	private BigDecimal lowestPrice;  //最低价
	@Column(name= "highestPrice")
	private BigDecimal highestPrice;  //最高价

	@Column(name= "expansionRewardThree")
	private BigDecimal expansionRewardThree;  //3级拓展奖励

	public BigDecimal getExpansionRewardThree() {
		return expansionRewardThree;
	}

	public void setExpansionRewardThree(BigDecimal expansionRewardThree) {
		this.expansionRewardThree = expansionRewardThree;
	}

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public BigDecimal getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserFace() {
		return userFace;
	}

	public void setUserFace(String userFace) {
		this.userFace = userFace;
	}

	public String getNickNameOtc() {
		return nickNameOtc;
	}

	public void setNickNameOtc(String nickNameOtc) {
		this.nickNameOtc = nickNameOtc;
	}

	public BigDecimal getFuwuRate() {
		return fuwuRate;
	}

	public void setFuwuRate(BigDecimal fuwuRate) {
		this.fuwuRate = fuwuRate;
	}

	public BigDecimal getFuliRate() {
		return fuliRate;
	}

	public void setFuliRate(BigDecimal fuliRate) {
		this.fuliRate = fuliRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(Integer validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public BigDecimal getStaticDividend() {
		return staticDividend;
	}

	public void setStaticDividend(BigDecimal staticDividend) {
		this.staticDividend = staticDividend;
	}

	public BigDecimal getDirectReward() {
		return directReward;
	}

	public void setDirectReward(BigDecimal directReward) {
		this.directReward = directReward;
	}

	public BigDecimal getExpansionReward() {
		return expansionReward;
	}

	public void setExpansionReward(BigDecimal expansionReward) {
		this.expansionReward = expansionReward;
	}


	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-03-19 15:25:34    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-03-19 15:25:34   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>排序号</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-03-19 15:25:34    
	 */
	public Integer getNumber() {
		return number;
	}
	
	/**
	 * <p>排序号</p>
	 * @author:  luyue
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-03-19 15:25:34   
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-03-19 15:25:34    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  luyue
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-03-19 15:25:34   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>会员图标</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-03-19 15:25:34    
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * <p>会员图标</p>
	 * @author:  luyue
	 * @param:   @param image
	 * @return:  void 
	 * @Date :   2019-03-19 15:25:34   
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	/**
	 * <p>推广人数</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-03-19 15:25:34    
	 */
	public Integer getRecommendCOunt() {
		return recommendCOunt;
	}
	
	/**
	 * <p>推广人数</p>
	 * @author:  luyue
	 * @param:   @param recommendCOunt
	 * @return:  void 
	 * @Date :   2019-03-19 15:25:34   
	 */
	public void setRecommendCOunt(Integer recommendCOunt) {
		this.recommendCOunt = recommendCOunt;
	}
	
	
	/**
	 * <p>基础额度</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-19 15:25:34    
	 */
	public BigDecimal getBaseQuota() {
		return baseQuota;
	}
	
	/**
	 * <p>基础额度</p>
	 * @author:  luyue
	 * @param:   @param baseQuota
	 * @return:  void 
	 * @Date :   2019-03-19 15:25:34   
	 */
	public void setBaseQuota(BigDecimal baseQuota) {
		this.baseQuota = baseQuota;
	}
	
	
	/**
	 * <p>额度上限</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-19 15:25:34    
	 */
	public BigDecimal getMaxQuota() {
		return maxQuota;
	}
	
	/**
	 * <p>额度上限</p>
	 * @author:  luyue
	 * @param:   @param maxQuota
	 * @return:  void 
	 * @Date :   2019-03-19 15:25:34   
	 */
	public void setMaxQuota(BigDecimal maxQuota) {
		this.maxQuota = maxQuota;
	}
	
	
	/**
	 * <p>推广额度</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-19 15:25:34    
	 */
	public BigDecimal getRecommendQuota() {
		return recommendQuota;
	}
	
	/**
	 * <p>推广额度</p>
	 * @author:  luyue
	 * @param:   @param recommendQuota
	 * @return:  void 
	 * @Date :   2019-03-19 15:25:34   
	 */
	public void setRecommendQuota(BigDecimal recommendQuota) {
		this.recommendQuota = recommendQuota;
	}
	
	
	/**
	 * <p>转让手续费比例</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-19 15:25:34    
	 */
	public BigDecimal getFeeRate() {
		return feeRate;
	}
	
	/**
	 * <p>转让手续费比例</p>
	 * @author:  luyue
	 * @param:   @param feeRate
	 * @return:  void 
	 * @Date :   2019-03-19 15:25:34   
	 */
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
	
	

}
