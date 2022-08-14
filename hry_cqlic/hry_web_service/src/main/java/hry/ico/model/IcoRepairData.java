/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-01 20:14:58 
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
 * <p> IcoRepairData </p>
 * @author:         lzy
 * @Date :          2019-04-01 20:14:58  
 */
@Table(name="ico_repair_data")
public class IcoRepairData extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "number")
	private BigDecimal number;  //
	
	@Column(name= "sum")
	private BigDecimal sum;  //
	
	@Column(name= "state")
	private Integer state;  //

	@Column(name= "coinCode")
	private String coinCode;  //

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-01 20:14:58    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-01 20:14:58   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-01 20:14:58    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-01 20:14:58   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-01 20:14:58    
	 */
	public BigDecimal getNumber() {
		return number;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-04-01 20:14:58   
	 */
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-01 20:14:58    
	 */
	public BigDecimal getSum() {
		return sum;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param sum
	 * @return:  void 
	 * @Date :   2019-04-01 20:14:58   
	 */
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-01 20:14:58    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-04-01 20:14:58   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	

}
