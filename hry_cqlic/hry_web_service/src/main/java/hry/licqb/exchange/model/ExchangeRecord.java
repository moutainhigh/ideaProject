/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:36:33 
 */
package hry.licqb.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExchangeRecord </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:36:33  
 */
@Table(name="lc_exchange_record")
public class ExchangeRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "exchangeSn")
	private String exchangeSn; // 兑换订单号

	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "itemId")
	private Long itemId;  //项目id
	
	@Column(name= "itemName")
	private String itemName;  //项目名称
	
	@Column(name= "ratio")
	private BigDecimal ratio;  //兑换汇率
	
	@Column(name= "payCoinCode")
	private String payCoinCode;  //支付币种--平台币
	
	@Column(name= "gainCoinCode")
	private String gainCoinCode;  //兑换币种
	
	@Column(name= "payNum")
	private BigDecimal payNum;  //支付数量
	
	@Column(name= "gainNum")
	private BigDecimal gainNum;  //兑换数量
	
	@Column(name= "periodsNum")
	private Integer periodsNum;  //项目期数
	
	@Column(name= "saasId")
	private String saasId;  //


	public String getExchangeSn() {
		return exchangeSn;
	}

	public void setExchangeSn(String exchangeSn) {
		this.exchangeSn = exchangeSn;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>项目id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public Long getItemId() {
		return itemId;
	}
	
	/**
	 * <p>项目id</p>
	 * @author:  zhouming
	 * @param:   @param itemId
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	
	/**
	 * <p>项目名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * <p>项目名称</p>
	 * @author:  zhouming
	 * @param:   @param itemName
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	/**
	 * <p>兑换汇率</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public BigDecimal getRatio() {
		return ratio;
	}
	
	/**
	 * <p>兑换汇率</p>
	 * @author:  zhouming
	 * @param:   @param ratio
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	
	
	/**
	 * <p>支付币种--平台币</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public String getPayCoinCode() {
		return payCoinCode;
	}
	
	/**
	 * <p>支付币种--平台币</p>
	 * @author:  zhouming
	 * @param:   @param payCoinCode
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setPayCoinCode(String payCoinCode) {
		this.payCoinCode = payCoinCode;
	}
	
	
	/**
	 * <p>兑换币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public String getGainCoinCode() {
		return gainCoinCode;
	}
	
	/**
	 * <p>兑换币种</p>
	 * @author:  zhouming
	 * @param:   @param gainCoinCode
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setGainCoinCode(String gainCoinCode) {
		this.gainCoinCode = gainCoinCode;
	}
	
	
	/**
	 * <p>支付数量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public BigDecimal getPayNum() {
		return payNum;
	}
	
	/**
	 * <p>支付数量</p>
	 * @author:  zhouming
	 * @param:   @param payNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setPayNum(BigDecimal payNum) {
		this.payNum = payNum;
	}
	
	
	/**
	 * <p>兑换数量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public BigDecimal getGainNum() {
		return gainNum;
	}
	
	/**
	 * <p>兑换数量</p>
	 * @author:  zhouming
	 * @param:   @param gainNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setGainNum(BigDecimal gainNum) {
		this.gainNum = gainNum;
	}
	
	
	/**
	 * <p>项目期数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public Integer getPeriodsNum() {
		return periodsNum;
	}
	
	/**
	 * <p>项目期数</p>
	 * @author:  zhouming
	 * @param:   @param periodsNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setPeriodsNum(Integer periodsNum) {
		this.periodsNum = periodsNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:36:33    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-12-17 16:36:33   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
