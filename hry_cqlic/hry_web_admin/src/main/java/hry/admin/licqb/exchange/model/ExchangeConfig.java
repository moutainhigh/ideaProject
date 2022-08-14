/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:32:12 
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
 * <p> ExchangeConfig </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:32:12  
 */
@Table(name="lc_exchange_config")
public class ExchangeConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
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
	
	@Column(name= "itemStartDate")
	private Date itemStartDate;  //项目启动时间
	
	@Column(name= "gainStartDate")
	private String gainStartDate;  //兑换开始时间
	
	@Column(name= "gainEndDate")
	private String gainEndDate;  //兑换结束时间
	
	@Column(name= "hasChangeRatio")
	private BigDecimal hasChangeRatio;  //已兑换比例
	
	@Column(name= "status")
	private Integer status;  //是否开启 0 未开启 1 开启

	@Column(name= "sort")
	private Integer sort; // 排序字段 越大越靠前
	
	@Column(name= "saasId")
	private String saasId;  //


	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>项目名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * <p>项目名称</p>
	 * @author:  zhouming
	 * @param:   @param itemName
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	/**
	 * <p>支付币种--平台币</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public String getPayCoinCode() {
		return payCoinCode;
	}
	
	/**
	 * <p>支付币种--平台币</p>
	 * @author:  zhouming
	 * @param:   @param payCoinCode
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setPayCoinCode(String payCoinCode) {
		this.payCoinCode = payCoinCode;
	}
	
	
	/**
	 * <p>兑换币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public String getGainCoinCode() {
		return gainCoinCode;
	}
	
	/**
	 * <p>兑换币种</p>
	 * @author:  zhouming
	 * @param:   @param gainCoinCode
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setGainCoinCode(String gainCoinCode) {
		this.gainCoinCode = gainCoinCode;
	}
	
	
	/**
	 * <p>兑换汇率</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public BigDecimal getRatio() {
		return ratio;
	}
	
	/**
	 * <p>兑换汇率</p>
	 * @author:  zhouming
	 * @param:   @param ratio
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	
	
	/**
	 * <p>本局项目兑换总数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public BigDecimal getTotalNum() {
		return totalNum;
	}
	
	/**
	 * <p>本局项目兑换总数</p>
	 * @author:  zhouming
	 * @param:   @param totalNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	
	
	/**
	 * <p>单次购买最低数量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public BigDecimal getSingleMinNum() {
		return singleMinNum;
	}
	
	/**
	 * <p>单次购买最低数量</p>
	 * @author:  zhouming
	 * @param:   @param singleMinNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setSingleMinNum(BigDecimal singleMinNum) {
		this.singleMinNum = singleMinNum;
	}
	
	
	/**
	 * <p>单人本局最大购买值</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public BigDecimal getSoloMaxNum() {
		return soloMaxNum;
	}
	
	/**
	 * <p>单人本局最大购买值</p>
	 * @author:  zhouming
	 * @param:   @param soloMaxNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setSoloMaxNum(BigDecimal soloMaxNum) {
		this.soloMaxNum = soloMaxNum;
	}
	
	
	/**
	 * <p>项目启动时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public Date getItemStartDate() {
		return itemStartDate;
	}
	
	/**
	 * <p>项目启动时间</p>
	 * @author:  zhouming
	 * @param:   @param itemStartDate
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setItemStartDate(Date itemStartDate) {
		this.itemStartDate = itemStartDate;
	}
	
	
	/**
	 * <p>兑换开始时间</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public String getGainStartDate() {
		return gainStartDate;
	}
	
	/**
	 * <p>兑换开始时间</p>
	 * @author:  zhouming
	 * @param:   @param gainStartDate
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setGainStartDate(String gainStartDate) {
		this.gainStartDate = gainStartDate;
	}
	
	
	/**
	 * <p>兑换结束时间</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public String getGainEndDate() {
		return gainEndDate;
	}
	
	/**
	 * <p>兑换结束时间</p>
	 * @author:  zhouming
	 * @param:   @param gainEndDate
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setGainEndDate(String gainEndDate) {
		this.gainEndDate = gainEndDate;
	}
	
	
	/**
	 * <p>已兑换比例</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public BigDecimal getHasChangeRatio() {
		return hasChangeRatio;
	}
	
	/**
	 * <p>已兑换比例</p>
	 * @author:  zhouming
	 * @param:   @param hasChangeRatio
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setHasChangeRatio(BigDecimal hasChangeRatio) {
		this.hasChangeRatio = hasChangeRatio;
	}
	
	
	/**
	 * <p>是否开启 0 未开启 1 开启</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>是否开启 0 未开启 1 开启</p>
	 * @author:  zhouming
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:32:12    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-12-17 16:32:12   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
