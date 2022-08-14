/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-05-08 11:13:13 
 */
package hry.financail.financing.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> AppFinancialRecommendTransaction </p>
 * @author:         jidn
 * @Date :          2019-05-08 11:13:13  
 */
@Table(name="app_financial_recommend_transaction")
public class AppFinancialRecommendTransaction extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "money")
	private BigDecimal money;  //待发放金额

	@Column(name= "issuedMoney")
	private BigDecimal issuedMoney;//已发放金额
	
	@Column(name= "remark")
	private String remark;  //


	public BigDecimal getIssuedMoney() {
		return issuedMoney;
	}

	public void setIssuedMoney(BigDecimal issuedMoney) {
		this.issuedMoney = issuedMoney;
	}

	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-05-08 11:13:13    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-08 11:13:13   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-05-08 11:13:13    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-05-08 11:13:13   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-05-08 11:13:13    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-05-08 11:13:13   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 11:13:13    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-05-08 11:13:13   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-05-08 11:13:13    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-08 11:13:13   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
