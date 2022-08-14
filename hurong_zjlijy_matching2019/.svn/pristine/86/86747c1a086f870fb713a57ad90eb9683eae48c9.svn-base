/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 14:42:53 
 */
package hry.social.manage.remote.model.force;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> SocialTerminableForce </p>
 * @author:         javalx
 * @Date :          2019-06-11 14:42:53  
 */
@Table(name="social_terminable_force")
public class SocialTerminableForce extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	@Column(name= "type")
	private Integer type;  //类型 1：任务，2：矿机
	
	@Column(name= "terminable")
	private BigDecimal terminable;  //时效算力值
	
	@Column(name= "endTime")
	private Date endTime;  //到期时间
	
	@Column(name= "remark")
	private String remark;  //获取途径备注
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 14:42:53    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:53   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 14:42:53    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:53   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>类型 1：任务，2：矿机</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 14:42:53    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型 1：任务，2：矿机</p>
	 * @author:  javalx
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:53   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>时效算力值</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 14:42:53    
	 */
	public BigDecimal getTerminable() {
		return terminable;
	}
	
	/**
	 * <p>时效算力值</p>
	 * @author:  javalx
	 * @param:   @param terminable
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:53   
	 */
	public void setTerminable(BigDecimal terminable) {
		this.terminable = terminable;
	}
	
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @return:  Date 
	 * @Date :   2019-06-11 14:42:53    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:53   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	/**
	 * <p>获取途径备注</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 14:42:53    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>获取途径备注</p>
	 * @author:  javalx
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-06-11 14:42:53   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
