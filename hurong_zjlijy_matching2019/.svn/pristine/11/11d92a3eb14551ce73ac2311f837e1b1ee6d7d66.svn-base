/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:02:38 
 */
package hry.cm5.miner.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> Cm5MinerGoods </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:02:38  
 */
@Table(name="cm5_miner_goods")
public class Cm5MinerGoods extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "minerCode")
	private String minerCode;  //矿机编号
	
	@Column(name= "minerName")
	private String minerName;  //矿机名称
	
	@Column(name= "labelId")
	private Long labelId;  //标签ID
	
	@Column(name= "pricingCoinCode")
	private String pricingCoinCode;  //定价币种
	
	@Column(name= "minerPrice")
	private BigDecimal minerPrice;  //矿机价钱
	
	@Column(name= "profitRate")
	private BigDecimal profitRate;  //预计收益倍数
	
	@Column(name= "minerImg")
	private String minerImg;  //矿机图片
	
	@Column(name= "usRatio")
	private String usRatio;  //us算力奖励比例 %/天
	
	@Column(name= "uskcRatio")
	private String uskcRatio;  //uskc算力奖励比例 %/天
	
	@Column(name= "pipeRatio")
	private String pipeRatio;  //管道算力奖励比例
	
	@Column(name= "totalProfit")
	private BigDecimal totalProfit;  //预计总收益
	
	@Column(name= "dayProfit")
	private BigDecimal dayProfit;  //日收益
	
	@Column(name= "states")
	private Integer states;  //是否上架 0 上架 1下架
	
	@Column(name= "saasId")
	private String saasId;  //

	@Transient
	private Integer minerStates; // 1 待激活 2 已升级 3 运行中

	@Transient
	private BigDecimal totalAward; // 累计收益

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

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>矿机编号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public String getMinerCode() {
		return minerCode;
	}
	
	/**
	 * <p>矿机编号</p>
	 * @author:  zhouming
	 * @param:   @param minerCode
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setMinerCode(String minerCode) {
		this.minerCode = minerCode;
	}
	
	
	/**
	 * <p>矿机名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public String getMinerName() {
		return minerName;
	}
	
	/**
	 * <p>矿机名称</p>
	 * @author:  zhouming
	 * @param:   @param minerName
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setMinerName(String minerName) {
		this.minerName = minerName;
	}
	
	
	/**
	 * <p>标签ID</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public Long getLabelId() {
		return labelId;
	}
	
	/**
	 * <p>标签ID</p>
	 * @author:  zhouming
	 * @param:   @param labelId
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}
	
	
	/**
	 * <p>定价币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public String getPricingCoinCode() {
		return pricingCoinCode;
	}
	
	/**
	 * <p>定价币种</p>
	 * @author:  zhouming
	 * @param:   @param pricingCoinCode
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setPricingCoinCode(String pricingCoinCode) {
		this.pricingCoinCode = pricingCoinCode;
	}
	
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public BigDecimal getMinerPrice() {
		return minerPrice;
	}
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  zhouming
	 * @param:   @param minerPrice
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setMinerPrice(BigDecimal minerPrice) {
		this.minerPrice = minerPrice;
	}
	
	
	/**
	 * <p>预计收益倍数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public BigDecimal getProfitRate() {
		return profitRate;
	}
	
	/**
	 * <p>预计收益倍数</p>
	 * @author:  zhouming
	 * @param:   @param profitRate
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}
	
	
	/**
	 * <p>矿机图片</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public String getMinerImg() {
		return minerImg;
	}
	
	/**
	 * <p>矿机图片</p>
	 * @author:  zhouming
	 * @param:   @param minerImg
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setMinerImg(String minerImg) {
		this.minerImg = minerImg;
	}
	
	
	/**
	 * <p>us算力奖励比例 %/天</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public String getUsRatio() {
		return usRatio;
	}
	
	/**
	 * <p>us算力奖励比例 %/天</p>
	 * @author:  zhouming
	 * @param:   @param usRatio
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setUsRatio(String usRatio) {
		this.usRatio = usRatio;
	}
	
	
	/**
	 * <p>uskc算力奖励比例 %/天</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public String getUskcRatio() {
		return uskcRatio;
	}
	
	/**
	 * <p>uskc算力奖励比例 %/天</p>
	 * @author:  zhouming
	 * @param:   @param uskcRatio
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setUskcRatio(String uskcRatio) {
		this.uskcRatio = uskcRatio;
	}
	
	
	/**
	 * <p>管道算力奖励比例</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public String getPipeRatio() {
		return pipeRatio;
	}
	
	/**
	 * <p>管道算力奖励比例</p>
	 * @author:  zhouming
	 * @param:   @param pipeRatio
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setPipeRatio(String pipeRatio) {
		this.pipeRatio = pipeRatio;
	}
	
	
	/**
	 * <p>预计总收益</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	
	/**
	 * <p>预计总收益</p>
	 * @author:  zhouming
	 * @param:   @param totalProfit
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	
	
	/**
	 * <p>日收益</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public BigDecimal getDayProfit() {
		return dayProfit;
	}
	
	/**
	 * <p>日收益</p>
	 * @author:  zhouming
	 * @param:   @param dayProfit
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setDayProfit(BigDecimal dayProfit) {
		this.dayProfit = dayProfit;
	}
	
	
	/**
	 * <p>是否上架 0 上架 1下架</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>是否上架 0 上架 1下架</p>
	 * @author:  zhouming
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:02:38    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-01-08 14:02:38   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
