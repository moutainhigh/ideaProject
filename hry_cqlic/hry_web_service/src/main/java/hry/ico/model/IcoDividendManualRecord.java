/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-22 11:32:20 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoDividendManualRecord </p>
 * @author:         lzy
 * @Date :          2019-03-22 11:32:20  
 */
@Table(name="ico_dividend_manual_record")
public class IcoDividendManualRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种Code
	
	@Column(name= "reason")
	private String reason;  //分红原因
	
	@Column(name= "dividendNum")
	private BigDecimal dividendNum;  //分红总基数
	
	@Column(name= "number")
	private BigDecimal number;  //获得数量
	
	@Column(name= "accountAtio")
	private BigDecimal accountAtio;  //自身占比
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-22 11:32:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-03-22 11:32:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-22 11:32:20    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-03-22 11:32:20   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种Code</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-03-22 11:32:20    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种Code</p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-03-22 11:32:20   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>分红原因</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-03-22 11:32:20    
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * <p>分红原因</p>
	 * @author:  lzy
	 * @param:   @param reason
	 * @return:  void 
	 * @Date :   2019-03-22 11:32:20   
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	/**
	 * <p>分红总基数</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-03-22 11:32:20    
	 */
	public BigDecimal getDividendNum() {
		return dividendNum;
	}
	
	/**
	 * <p>分红总基数</p>
	 * @author:  lzy
	 * @param:   @param dividendNum
	 * @return:  void 
	 * @Date :   2019-03-22 11:32:20   
	 */
	public void setDividendNum(BigDecimal dividendNum) {
		this.dividendNum = dividendNum;
	}
	
	
	/**
	 * <p>获得数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-03-22 11:32:20    
	 */
	public BigDecimal getNumber() {
		return number;
	}
	
	/**
	 * <p>获得数量</p>
	 * @author:  lzy
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-03-22 11:32:20   
	 */
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	
	
	/**
	 * <p>自身占比</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-03-22 11:32:20    
	 */
	public BigDecimal getAccountAtio() {
		return accountAtio;
	}
	
	/**
	 * <p>自身占比</p>
	 * @author:  lzy
	 * @param:   @param accountAtio
	 * @return:  void 
	 * @Date :   2019-03-22 11:32:20   
	 */
	public void setAccountAtio(BigDecimal accountAtio) {
		this.accountAtio = accountAtio;
	}
	
	

}
