/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 14:43:35 
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
 * <p> SocialForceCoinAddition </p>
 * @author:         javalx
 * @Date :          2019-06-11 14:43:35  
 */
@Table(name="social_force_coin_addition")
public class SocialForceCoinAddition extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	@Column(name= "sourceType")
	private Integer sourceType;  //来源类型 1：任务，2：会员
	
	@Column(name= "additionType")
	private Integer additionType;  //加成类型 1：算力，2：数币
	
	@Column(name= "addition")
	private BigDecimal addition;  //加成值
	
	@Column(name= "endTime")
	private Date endTime;  //到期时间
	
	@Column(name= "remark")
	private String remark;  //获取途径备注


	/**
	 * <p></p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 14:43:35    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 14:43:35   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 14:43:35    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-11 14:43:35   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>来源类型 1：任务，2：会员</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 14:43:35    
	 */
	public Integer getSourceType() {
		return sourceType;
	}
	
	/**
	 * <p>来源类型 1：任务，2：会员</p>
	 * @author:  javalx
	 * @param:   @param sourceType
	 * @return:  void 
	 * @Date :   2019-06-11 14:43:35   
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	
	
	/**
	 * <p>加成类型</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 14:43:35    
	 */
	public Integer getAdditionType() {
		return additionType;
	}
	
	/**
	 * <p>1：算力，2：数币</p>
	 * @author:  javalx
	 * @param:   @param additionType
	 * @return:  void 
	 * @Date :   2019-06-11 14:43:35   
	 */
	public void setAdditionType(Integer additionType) {
		this.additionType = additionType;
	}
	
	
	/**
	 * <p>加成值</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 14:43:35    
	 */
	public BigDecimal getAddition() {
		return addition;
	}
	
	/**
	 * <p>加成值</p>
	 * @author:  javalx
	 * @param:   @param addition
	 * @return:  void 
	 * @Date :   2019-06-11 14:43:35   
	 */
	public void setAddition(BigDecimal addition) {
		this.addition = addition;
	}
	
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @return:  Date 
	 * @Date :   2019-06-11 14:43:35    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-06-11 14:43:35   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	/**
	 * <p>获取途径备注</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 14:43:35    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>获取途径备注</p>
	 * @author:  javalx
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-06-11 14:43:35   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
