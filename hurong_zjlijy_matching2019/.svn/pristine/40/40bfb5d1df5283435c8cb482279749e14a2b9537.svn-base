/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-08 11:01:22 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> GrowthBuytask </p>
 * @author:         luyue
 * @Date :          2019-05-08 11:01:22  
 */
@Table(name="shop_growth_buytask")
public class GrowthBuytask extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "minMoney")
	private BigDecimal minMoney;  //订单金额最小值
	
	@Column(name= "maxMoney")
	private BigDecimal maxMoney;  //订单金额最大值
	
	@Column(name= "count")
	private BigDecimal count;  //奖励值
	
	@Column(name= "isLimit")
	private Integer isLimit;  //无上限：0为有上限，1为无上限
	
	@Column(name= "remark")
	private String remark;  //备注
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-08 11:01:22    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-08 11:01:22   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单金额最小值</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 11:01:22    
	 */
	public BigDecimal getMinMoney() {
		return minMoney;
	}
	
	/**
	 * <p>订单金额最小值</p>
	 * @author:  luyue
	 * @param:   @param minMoney
	 * @return:  void 
	 * @Date :   2019-05-08 11:01:22   
	 */
	public void setMinMoney(BigDecimal minMoney) {
		this.minMoney = minMoney;
	}
	
	
	/**
	 * <p>订单金额最大值</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 11:01:22    
	 */
	public BigDecimal getMaxMoney() {
		return maxMoney;
	}
	
	/**
	 * <p>订单金额最大值</p>
	 * @author:  luyue
	 * @param:   @param maxMoney
	 * @return:  void 
	 * @Date :   2019-05-08 11:01:22   
	 */
	public void setMaxMoney(BigDecimal maxMoney) {
		this.maxMoney = maxMoney;
	}
	
	
	/**
	 * <p>奖励值</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-08 11:01:22    
	 */
	public BigDecimal getCount() {
		return count;
	}
	
	/**
	 * <p>奖励值</p>
	 * @author:  luyue
	 * @param:   @param count
	 * @return:  void 
	 * @Date :   2019-05-08 11:01:22   
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	
	
	/**
	 * <p>无上限：0为有上限，1为无上限</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-08 11:01:22    
	 */
	public Integer getIsLimit() {
		return isLimit;
	}
	
	/**
	 * <p>无上限：0为有上限，1为无上限</p>
	 * @author:  luyue
	 * @param:   @param isLimit
	 * @return:  void 
	 * @Date :   2019-05-08 11:01:22   
	 */
	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-08 11:01:22    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-08 11:01:22   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
