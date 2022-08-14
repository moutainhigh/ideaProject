/**
 * Copyright:   
 * @author:      xubb
 * @version:     V1.0 
 * @Date:        2019-07-04 10:00:54 
 */
package hry.otc.manage.remote.model.account;


import hry.otc.manage.remote.model.core.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> OtcAccount </p>
 * @author:         xubb
 * @Date :          2019-07-04 10:00:54  
 */
@Table(name="otc_account")
public class OtcAccount extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "hotMoney")
	private BigDecimal hotMoney;  //可用
	
	@Column(name= "coldMoney")
	private BigDecimal coldMoney;  //冻结
	
	
	
	
	/**
	 * <p></p>
	 * @author:  xubb
	 * @return:  Long 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  xubb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  xubb
	 * @return:  Long 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  xubb
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  xubb
	 * @return:  String 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  xubb
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>可用</p>
	 * @author:  xubb
	 * @return:  BigDecimal 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p>可用</p>
	 * @author:  xubb
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p>冻结</p>
	 * @author:  xubb
	 * @return:  BigDecimal 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p>冻结</p>
	 * @author:  xubb
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	

}
