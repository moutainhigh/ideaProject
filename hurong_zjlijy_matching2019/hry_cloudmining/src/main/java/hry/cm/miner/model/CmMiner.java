/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-26 17:32:36 
 */
package hry.cm.miner.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> CmMiner </p>
 * @author:         yaozh
 * @Date :          2019-07-26 17:32:36  
 */
@Table(name="cm_miner")
public class CmMiner extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "labelId")
	private Long labelId;  //标签ID
	
	@Column(name= "status")
	private Integer status;  //矿机状态 1：上架中 2：已下架 3:已售罄
	
	@Column(name= "minerName")
	private String minerName;  //矿机名称
	
	@Column(name= "minerCode")
	private String minerCode;  //矿机编号
	
	@Column(name= "minerPrice")
	private BigDecimal minerPrice;  //矿机价钱
	
	@Column(name= "minerProfitType")
	private Integer minerProfitType;  //收益领取类型：1自动发放 2果树领取
	
	@Column(name= "payCoinCode")
	private String payCoinCode;  //支付币种
	
	@Column(name= "profitCoinCode")
	private String profitCoinCode;  //收益币种
	
	@Column(name= "profitRate")
	private BigDecimal profitRate;  //收益倍数
	
	@Column(name= "totalProfit")
	private BigDecimal totalProfit;  //总收益
	
	@Column(name= "dayProfit")
	private BigDecimal dayProfit;  //日收益
	
	@Column(name= "effectiveTimeLength")
	private Integer effectiveTimeLength;  //有效时长
	
	@Column(name= "timeLengthCompany")
	private Integer timeLengthCompany;  //有效时长单位 1：年 ，2：天
	
	@Column(name= "generateCycle")
	private Integer generateCycle;  //产币周期 1:天  2:小时
	
	@Column(name= "pictureUrl")
	private String pictureUrl;  //矿机图片路径
	
	@Column(name= "totalNum")
	private Integer totalNum;  //矿机总数
	
	@Column(name= "surplusNum")
	private Integer surplusNum;  //矿机剩余数量
	
	@Column(name= "coldNum")
	private Integer coldNum;  //购买冻结数量
	
	@Column(name= "minNum")
	private Integer minNum;  //最小购买数量
	
	@Column(name= "maxNum")
	private Integer maxNum;  //最大购买数量
	
	@Column(name= "progressProportion")
	private Integer progressProportion;  //进度条显示比例
	
	@Column(name= "isDisplay")
	private Integer isDisplay;  //是否显示进度条(1.不显示  2.显示)
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>标签ID</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Long getLabelId() {
		return labelId;
	}
	
	/**
	 * <p>标签ID</p>
	 * @author:  yaozh
	 * @param:   @param labelId
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}
	
	
	/**
	 * <p>矿机状态 1：上架中 2：已下架 3:已售罄</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>矿机状态 1：上架中 2：已下架 3:已售罄</p>
	 * @author:  yaozh
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>矿机名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public String getMinerName() {
		return minerName;
	}
	
	/**
	 * <p>矿机名称</p>
	 * @author:  yaozh
	 * @param:   @param minerName
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setMinerName(String minerName) {
		this.minerName = minerName;
	}
	
	
	/**
	 * <p>矿机编号</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public String getMinerCode() {
		return minerCode;
	}
	
	/**
	 * <p>矿机编号</p>
	 * @author:  yaozh
	 * @param:   @param minerCode
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setMinerCode(String minerCode) {
		this.minerCode = minerCode;
	}
	
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public BigDecimal getMinerPrice() {
		return minerPrice;
	}
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  yaozh
	 * @param:   @param minerPrice
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setMinerPrice(BigDecimal minerPrice) {
		this.minerPrice = minerPrice;
	}
	
	
	/**
	 * <p>收益领取类型：1自动发放 2果树领取</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getMinerProfitType() {
		return minerProfitType;
	}
	
	/**
	 * <p>收益领取类型：1自动发放 2果树领取</p>
	 * @author:  yaozh
	 * @param:   @param minerProfitType
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setMinerProfitType(Integer minerProfitType) {
		this.minerProfitType = minerProfitType;
	}
	
	
	/**
	 * <p>支付币种</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public String getPayCoinCode() {
		return payCoinCode;
	}
	
	/**
	 * <p>支付币种</p>
	 * @author:  yaozh
	 * @param:   @param payCoinCode
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setPayCoinCode(String payCoinCode) {
		this.payCoinCode = payCoinCode;
	}
	
	
	/**
	 * <p>收益币种</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public String getProfitCoinCode() {
		return profitCoinCode;
	}
	
	/**
	 * <p>收益币种</p>
	 * @author:  yaozh
	 * @param:   @param profitCoinCode
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setProfitCoinCode(String profitCoinCode) {
		this.profitCoinCode = profitCoinCode;
	}
	
	
	/**
	 * <p>收益倍数</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public BigDecimal getProfitRate() {
		return profitRate;
	}
	
	/**
	 * <p>收益倍数</p>
	 * @author:  yaozh
	 * @param:   @param profitRate
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}
	
	
	/**
	 * <p>总收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	
	/**
	 * <p>总收益</p>
	 * @author:  yaozh
	 * @param:   @param totalProfit
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	
	
	/**
	 * <p>日收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public BigDecimal getDayProfit() {
		return dayProfit;
	}
	
	/**
	 * <p>日收益</p>
	 * @author:  yaozh
	 * @param:   @param dayProfit
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setDayProfit(BigDecimal dayProfit) {
		this.dayProfit = dayProfit;
	}
	
	
	/**
	 * <p>有效时长</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getEffectiveTimeLength() {
		return effectiveTimeLength;
	}
	
	/**
	 * <p>有效时长</p>
	 * @author:  yaozh
	 * @param:   @param effectiveTimeLength
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setEffectiveTimeLength(Integer effectiveTimeLength) {
		this.effectiveTimeLength = effectiveTimeLength;
	}
	
	
	/**
	 * <p>有效时长单位 1：年 ，2：天</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getTimeLengthCompany() {
		return timeLengthCompany;
	}
	
	/**
	 * <p>有效时长单位 1：年 ，2：天</p>
	 * @author:  yaozh
	 * @param:   @param timeLengthCompany
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setTimeLengthCompany(Integer timeLengthCompany) {
		this.timeLengthCompany = timeLengthCompany;
	}
	
	
	/**
	 * <p>产币周期 1:天  2:小时</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getGenerateCycle() {
		return generateCycle;
	}
	
	/**
	 * <p>产币周期 1:天  2:小时</p>
	 * @author:  yaozh
	 * @param:   @param generateCycle
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setGenerateCycle(Integer generateCycle) {
		this.generateCycle = generateCycle;
	}
	
	
	/**
	 * <p>矿机图片路径</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}
	
	/**
	 * <p>矿机图片路径</p>
	 * @author:  yaozh
	 * @param:   @param pictureUrl
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	
	/**
	 * <p>矿机总数</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getTotalNum() {
		return totalNum;
	}
	
	/**
	 * <p>矿机总数</p>
	 * @author:  yaozh
	 * @param:   @param totalNum
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	
	/**
	 * <p>矿机剩余数量</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getSurplusNum() {
		return surplusNum;
	}
	
	/**
	 * <p>矿机剩余数量</p>
	 * @author:  yaozh
	 * @param:   @param surplusNum
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setSurplusNum(Integer surplusNum) {
		this.surplusNum = surplusNum;
	}
	
	
	/**
	 * <p>购买冻结数量</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getColdNum() {
		return coldNum;
	}
	
	/**
	 * <p>购买冻结数量</p>
	 * @author:  yaozh
	 * @param:   @param coldNum
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setColdNum(Integer coldNum) {
		this.coldNum = coldNum;
	}
	
	
	/**
	 * <p>最小购买数量</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getMinNum() {
		return minNum;
	}
	
	/**
	 * <p>最小购买数量</p>
	 * @author:  yaozh
	 * @param:   @param minNum
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}
	
	
	/**
	 * <p>最大购买数量</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getMaxNum() {
		return maxNum;
	}
	
	/**
	 * <p>最大购买数量</p>
	 * @author:  yaozh
	 * @param:   @param maxNum
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	
	
	/**
	 * <p>进度条显示比例</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getProgressProportion() {
		return progressProportion;
	}
	
	/**
	 * <p>进度条显示比例</p>
	 * @author:  yaozh
	 * @param:   @param progressProportion
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setProgressProportion(Integer progressProportion) {
		this.progressProportion = progressProportion;
	}
	
	
	/**
	 * <p>是否显示进度条(1.不显示  2.显示)</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public Integer getIsDisplay() {
		return isDisplay;
	}
	
	/**
	 * <p>是否显示进度条(1.不显示  2.显示)</p>
	 * @author:  yaozh
	 * @param:   @param isDisplay
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-26 17:32:36    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-07-26 17:32:36   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
