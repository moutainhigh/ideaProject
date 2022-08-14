/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 14:27:40 
 */
package hry.licqb.record.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> UsdtTotal </p>
 * @author:         zhouming
 * @Date :          2020-06-11 14:27:40  
 */
@Table(name="lc_usdt_total")
public class UsdtTotal extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "payMoney")
	private BigDecimal payMoney;  //充值总数
	
	@Column(name= "extractMoney")
	private BigDecimal extractMoney;  //提取总数

	@Column(name= "surplusMoney")
	private BigDecimal surplusMoney; // 剩余数量

	@Column(name= "saasId")
	private String saasId;  //


	public BigDecimal getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-11 14:27:40    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-06-11 14:27:40   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-11 14:27:40    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2020-06-11 14:27:40   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>充值总数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-06-11 14:27:40    
	 */
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	
	/**
	 * <p>充值总数</p>
	 * @author:  zhouming
	 * @param:   @param payMoney
	 * @return:  void 
	 * @Date :   2020-06-11 14:27:40   
	 */
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	
	
	/**
	 * <p>提取总数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-06-11 14:27:40    
	 */
	public BigDecimal getExtractMoney() {
		return extractMoney;
	}
	
	/**
	 * <p>提取总数</p>
	 * @author:  zhouming
	 * @param:   @param extractMoney
	 * @return:  void 
	 * @Date :   2020-06-11 14:27:40   
	 */
	public void setExtractMoney(BigDecimal extractMoney) {
		this.extractMoney = extractMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-11 14:27:40    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-06-11 14:27:40   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
