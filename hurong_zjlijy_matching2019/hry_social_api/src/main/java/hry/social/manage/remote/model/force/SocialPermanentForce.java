/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 14:42:13 
 */
package hry.social.manage.remote.model.force;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> SocialPermanentForce </p>
 * @author:         javalx
 * @Date :          2019-06-11 14:42:13  
 */
@Table(name="social_permanent_force")
public class SocialPermanentForce extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	@Column(name= "type")
	private Integer type;  //类型 1：任务
	
	@Column(name= "permanent")
	private BigDecimal permanent;  //永久算力值
	
	@Column(name= "remark")
	private String remark;  //获取途径备注
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 14:42:13    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:13   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 14:42:13    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:13   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>类型 1：任务</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 14:42:13    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型 1：任务</p>
	 * @author:  javalx
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:13   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>永久算力值</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 14:42:13    
	 */
	public BigDecimal getPermanent() {
		return permanent;
	}
	
	/**
	 * <p>永久算力值</p>
	 * @author:  javalx
	 * @param:   @param permanent
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:13   
	 */
	public void setPermanent(BigDecimal permanent) {
		this.permanent = permanent;
	}
	
	
	/**
	 * <p>获取途径备注</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 14:42:13    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>获取途径备注</p>
	 * @author:  javalx
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:13   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
