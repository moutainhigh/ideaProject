/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-03-19 15:31:58 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> RechargeConfig </p>
 * @author:         luyue
 * @Date :          2019-03-19 15:31:58  
 */
@Table(name="shop_recharge_config")
public class RechargeConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "money")
	private BigDecimal money;  //充值金额
	
	@Column(name= "integralCount")
	private BigDecimal integralCount;  //电子券数量
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-03-19 15:31:58    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-03-19 15:31:58   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>充值金额</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-19 15:31:58    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>充值金额</p>
	 * @author:  luyue
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-03-19 15:31:58   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p>电子券数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-19 15:31:58    
	 */
	public BigDecimal getIntegralCount() {
		return integralCount;
	}
	
	/**
	 * <p>电子券数量</p>
	 * @author:  luyue
	 * @param:   @param integralCount
	 * @return:  void 
	 * @Date :   2019-03-19 15:31:58   
	 */
	public void setIntegralCount(BigDecimal integralCount) {
		this.integralCount = integralCount;
	}
	
	

}
