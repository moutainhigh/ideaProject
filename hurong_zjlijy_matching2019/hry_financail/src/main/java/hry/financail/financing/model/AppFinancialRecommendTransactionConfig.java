/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-05-08 17:34:32 
 */
package hry.financail.financing.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> AppFinancialRecommendTransactionConfig </p>
 * @author:         jidn
 * @Date :          2019-05-08 17:34:32  
 */
@Table(name="app_financial_recommend_config")
public class AppFinancialRecommendTransactionConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "vip_zero")
	private BigDecimal vip_zero;  //
	
	@Column(name= "vip_one")
	private BigDecimal vip_one;  //
	
	@Column(name= "vip_two")
	private BigDecimal vip_two;  //
	
	@Column(name= "vip_three")
	private BigDecimal vip_three;  //
	
	@Column(name= "vip_four")
	private BigDecimal vip_four;  //
	
	@Column(name= "vip_five")
	private BigDecimal vip_five;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-05-08 17:34:32    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-08 17:34:32   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-05-08 17:34:32    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-05-08 17:34:32   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 17:34:32    
	 */
	public BigDecimal getVip_zero() {
		return vip_zero;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param vip_zero
	 * @return:  void 
	 * @Date :   2019-05-08 17:34:32   
	 */
	public void setVip_zero(BigDecimal vip_zero) {
		this.vip_zero = vip_zero;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 17:34:32    
	 */
	public BigDecimal getVip_one() {
		return vip_one;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param vip_one
	 * @return:  void 
	 * @Date :   2019-05-08 17:34:32   
	 */
	public void setVip_one(BigDecimal vip_one) {
		this.vip_one = vip_one;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 17:34:32    
	 */
	public BigDecimal getVip_two() {
		return vip_two;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param vip_two
	 * @return:  void 
	 * @Date :   2019-05-08 17:34:32   
	 */
	public void setVip_two(BigDecimal vip_two) {
		this.vip_two = vip_two;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 17:34:32    
	 */
	public BigDecimal getVip_three() {
		return vip_three;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param vip_three
	 * @return:  void 
	 * @Date :   2019-05-08 17:34:32   
	 */
	public void setVip_three(BigDecimal vip_three) {
		this.vip_three = vip_three;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 17:34:32    
	 */
	public BigDecimal getVip_four() {
		return vip_four;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param vip_four
	 * @return:  void 
	 * @Date :   2019-05-08 17:34:32   
	 */
	public void setVip_four(BigDecimal vip_four) {
		this.vip_four = vip_four;
	}
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 17:34:32    
	 */
	public BigDecimal getVip_five() {
		return vip_five;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param vip_five
	 * @return:  void 
	 * @Date :   2019-05-08 17:34:32   
	 */
	public void setVip_five(BigDecimal vip_five) {
		this.vip_five = vip_five;
	}
	
	

}
