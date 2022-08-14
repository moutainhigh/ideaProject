/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:35:32 
 */
package hry.admin.licqb.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExchangeItem </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:35:32  
 */
@Table(name="lc_exchange_item")
public class ExchangeItem extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "itemConfigId")
	private Long itemConfigId; // 项目配置Id

	@Column(name= "itemName")
	private String itemName;  //项目名称
	
	@Column(name= "payCoinCode")
	private String payCoinCode;  //支付币种--平台币
	
	@Column(name= "gainCoinCode")
	private String gainCoinCode;  //兑换币种
	
	@Column(name= "ratio")
	private BigDecimal ratio;  //兑换汇率
	
	@Column(name= "totalNum")
	private BigDecimal totalNum;  //本局项目兑换总数
	
	@Column(name= "singleMinNum")
	private BigDecimal singleMinNum;  //单次购买最低数量
	
	@Column(name= "soloMaxNum")
	private BigDecimal soloMaxNum;  //单人本局最大购买值
	
	@Column(name= "hasBuyNum")
	private BigDecimal hasBuyNum;  //已购买数量
	
	@Column(name= "itemStartDate")
	private Date itemStartDate;  //项目启动时间
	
	@Column(name= "itemEndDate")
	private Date itemEndDate;  //项目结束时间
	
	@Column(name= "periodsNum")
	private Integer periodsNum;  //项目期数
	
	@Column(name= "status")
	private Integer status;  //是否开启 0 进行中 1 结束
	
	@Column(name= "saasId")
	private String saasId;  //


	public Long getItemConfigId() {
		return itemConfigId;
	}

	public void setItemConfigId(Long itemConfigId) {
		this.itemConfigId = itemConfigId;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>项目名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * <p>项目名称</p>
	 * @author:  zhouming
	 * @param:   @param itemName
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	/**
	 * <p>支付币种--平台币</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public String getPayCoinCode() {
		return payCoinCode;
	}
	
	/**
	 * <p>支付币种--平台币</p>
	 * @author:  zhouming
	 * @param:   @param payCoinCode
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setPayCoinCode(String payCoinCode) {
		this.payCoinCode = payCoinCode;
	}
	
	
	/**
	 * <p>兑换币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public String getGainCoinCode() {
		return gainCoinCode;
	}
	
	/**
	 * <p>兑换币种</p>
	 * @author:  zhouming
	 * @param:   @param gainCoinCode
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setGainCoinCode(String gainCoinCode) {
		this.gainCoinCode = gainCoinCode;
	}
	
	
	/**
	 * <p>兑换汇率</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public BigDecimal getRatio() {
		return ratio;
	}
	
	/**
	 * <p>兑换汇率</p>
	 * @author:  zhouming
	 * @param:   @param ratio
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	
	
	/**
	 * <p>本局项目兑换总数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public BigDecimal getTotalNum() {
		return totalNum;
	}
	
	/**
	 * <p>本局项目兑换总数</p>
	 * @author:  zhouming
	 * @param:   @param totalNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	
	
	/**
	 * <p>单次购买最低数量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public BigDecimal getSingleMinNum() {
		return singleMinNum;
	}
	
	/**
	 * <p>单次购买最低数量</p>
	 * @author:  zhouming
	 * @param:   @param singleMinNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setSingleMinNum(BigDecimal singleMinNum) {
		this.singleMinNum = singleMinNum;
	}
	
	
	/**
	 * <p>单人本局最大购买值</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public BigDecimal getSoloMaxNum() {
		return soloMaxNum;
	}
	
	/**
	 * <p>单人本局最大购买值</p>
	 * @author:  zhouming
	 * @param:   @param soloMaxNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setSoloMaxNum(BigDecimal soloMaxNum) {
		this.soloMaxNum = soloMaxNum;
	}
	
	
	/**
	 * <p>已购买数量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public BigDecimal getHasBuyNum() {
		return hasBuyNum;
	}
	
	/**
	 * <p>已购买数量</p>
	 * @author:  zhouming
	 * @param:   @param hasBuyNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setHasBuyNum(BigDecimal hasBuyNum) {
		this.hasBuyNum = hasBuyNum;
	}
	
	
	/**
	 * <p>项目启动时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public Date getItemStartDate() {
		return itemStartDate;
	}
	
	/**
	 * <p>项目启动时间</p>
	 * @author:  zhouming
	 * @param:   @param itemStartDate
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setItemStartDate(Date itemStartDate) {
		this.itemStartDate = itemStartDate;
	}
	
	
	/**
	 * <p>项目结束时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public Date getItemEndDate() {
		return itemEndDate;
	}
	
	/**
	 * <p>项目结束时间</p>
	 * @author:  zhouming
	 * @param:   @param itemEndDate
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setItemEndDate(Date itemEndDate) {
		this.itemEndDate = itemEndDate;
	}
	
	
	/**
	 * <p>项目期数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public Integer getPeriodsNum() {
		return periodsNum;
	}
	
	/**
	 * <p>项目期数</p>
	 * @author:  zhouming
	 * @param:   @param periodsNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setPeriodsNum(Integer periodsNum) {
		this.periodsNum = periodsNum;
	}
	
	
	/**
	 * <p>是否开启 0 进行中 1 结束</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>是否开启 0 进行中 1 结束</p>
	 * @author:  zhouming
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:35:32    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-12-17 16:35:32   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
