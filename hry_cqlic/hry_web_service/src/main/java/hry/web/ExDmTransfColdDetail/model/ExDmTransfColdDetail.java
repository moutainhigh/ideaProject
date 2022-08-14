/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0 
 * @Date:        2017-11-13 19:17:02 
 */
package hry.web.ExDmTransfColdDetail.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExDmTransfColdDetail </p>
 * @author:         shangxl
 * @Date :          2017-11-13 19:17:02  
 */
@Table(name="ex_dm_transfcold_detail")
public class ExDmTransfColdDetail extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "fromAddress")
	private String fromAddress;  //fromAddress
	
	@Column(name= "toAddress")
	private String toAddress;  //toAddress
	
	@Column(name= "amount")
	private BigDecimal amount;  //amount
	
	@Column(name= "operator")
	private String operator;  //操作人员
	
	@Column(name= "tx")
	private String tx;  //流水号
	
	@Column(name="coinCode")
	private String coinCode;
	
	
	/**
	 * <p>id</p>
	 * @author:  shangxl
	 * @return:  Long 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  shangxl
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>fromAddress</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	
	/**
	 * <p>fromAddress</p>
	 * @author:  shangxl
	 * @param:   @param fromAddress
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	
	/**
	 * <p>toAddress</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public String getToAddress() {
		return toAddress;
	}
	
	/**
	 * <p>toAddress</p>
	 * @author:  shangxl
	 * @param:   @param toAddress
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	
	
	/**
	 * <p>amount</p>
	 * @author:  shangxl
	 * @return:  BigDecimal 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * <p>amount</p>
	 * @author:  shangxl
	 * @param:   @param amount
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	/**
	 * <p>操作人员</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * <p>操作人员</p>
	 * @author:  shangxl
	 * @param:   @param operator
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public String getTx() {
		return tx;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  shangxl
	 * @param:   @param tx
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setTx(String tx) {
		this.tx = tx;
	}
	
	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}


}
