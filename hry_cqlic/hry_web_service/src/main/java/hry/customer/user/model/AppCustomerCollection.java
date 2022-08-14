/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-01-19 10:07:55 
 */
package hry.customer.user.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppCustomerCollection </p>
 * @author:         liushilei
 * @Date :          2018-01-19 10:07:55  
 */
@Table(name="app_customer_collection")
public class AppCustomerCollection extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "coincode")
	private String coincode;  //coincode
	
	@Column(name= "customerId")
	private Long customerId;  //customerId
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-01-19 10:07:55    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-01-19 10:07:55   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>coincode</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-01-19 10:07:55    
	 */
	public String getCoincode() {
		return coincode;
	}
	
	/**
	 * <p>coincode</p>
	 * @author:  liushilei
	 * @param:   @param coincode
	 * @return:  void 
	 * @Date :   2018-01-19 10:07:55   
	 */
	public void setCoincode(String coincode) {
		this.coincode = coincode;
	}
	
	
	/**
	 * <p>customerId</p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-01-19 10:07:55    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>customerId</p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-01-19 10:07:55   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	

}
