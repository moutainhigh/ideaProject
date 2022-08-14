/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-27 18:01:50 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExDmTransfColdDetail </p>
 * @author:         tianpengyu
 * @Date :          2018-06-27 18:01:50  
 */
@Table(name="ex_dm_transfcold_detail")
public class ExDmTransfColdDetail extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "fromAddress")
	private String fromAddress;  //
	
	@Column(name= "toAddress")
	private String toAddress;  //
	
	@Column(name= "amount")
	private BigDecimal amount;  //
	
	@Column(name= "operator")
	private String operator;  //
	
	@Column(name= "tx")
	private String tx;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-27 18:01:50    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-27 18:01:50   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-27 18:01:50    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-27 18:01:50   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-27 18:01:50    
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fromAddress
	 * @return:  void 
	 * @Date :   2018-06-27 18:01:50   
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-27 18:01:50    
	 */
	public String getToAddress() {
		return toAddress;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param toAddress
	 * @return:  void 
	 * @Date :   2018-06-27 18:01:50   
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-27 18:01:50    
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param amount
	 * @return:  void 
	 * @Date :   2018-06-27 18:01:50   
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-27 18:01:50    
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param operator
	 * @return:  void 
	 * @Date :   2018-06-27 18:01:50   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-27 18:01:50    
	 */
	public String getTx() {
		return tx;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param tx
	 * @return:  void 
	 * @Date :   2018-06-27 18:01:50   
	 */
	public void setTx(String tx) {
		this.tx = tx;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-27 18:01:50    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-27 18:01:50   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	

}
