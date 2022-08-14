/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-23 16:57:20 
 */
package hry.admin.licqb.record.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ReleaseRuleDetails </p>
 * @author:         zhouming
 * @Date :          2019-08-23 16:57:20  
 */
@Table(name="lc_release_rule_details")
public class ReleaseRuleDetails extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "customerLevelId")
	private Long customerLevelId;  //用户等级Id
	
	@Column(name= "teamLevelId")
	private Long teamLevelId;  //社区等级Id
	
	@Column(name= "teamLevelName")
	private String teamLevelName;  //社区等级名称
	
	@Column(name= "teamSort")
	private Integer teamSort;  //社区等级排序
	
	@Column(name= "startTeamAward")
	private BigDecimal startTeamAward;  //起始团队奖励
	
	@Column(name= "startTime")
	private Date startTime;  //奖励开始时间
	
	@Column(name= "endTime")
	private Date endTime;  //奖励结束时间
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>用户等级Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public Long getCustomerLevelId() {
		return customerLevelId;
	}
	
	/**
	 * <p>用户等级Id</p>
	 * @author:  zhouming
	 * @param:   @param customerLevelId
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setCustomerLevelId(Long customerLevelId) {
		this.customerLevelId = customerLevelId;
	}
	
	
	/**
	 * <p>社区等级Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public Long getTeamLevelId() {
		return teamLevelId;
	}
	
	/**
	 * <p>社区等级Id</p>
	 * @author:  zhouming
	 * @param:   @param teamLevelId
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setTeamLevelId(Long teamLevelId) {
		this.teamLevelId = teamLevelId;
	}
	
	
	/**
	 * <p>社区等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public String getTeamLevelName() {
		return teamLevelName;
	}
	
	/**
	 * <p>社区等级名称</p>
	 * @author:  zhouming
	 * @param:   @param teamLevelName
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setTeamLevelName(String teamLevelName) {
		this.teamLevelName = teamLevelName;
	}
	
	
	/**
	 * <p>社区等级排序</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public Integer getTeamSort() {
		return teamSort;
	}
	
	/**
	 * <p>社区等级排序</p>
	 * @author:  zhouming
	 * @param:   @param teamSort
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setTeamSort(Integer teamSort) {
		this.teamSort = teamSort;
	}
	
	
	/**
	 * <p>起始团队奖励</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public BigDecimal getStartTeamAward() {
		return startTeamAward;
	}
	
	/**
	 * <p>起始团队奖励</p>
	 * @author:  zhouming
	 * @param:   @param startTeamAward
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setStartTeamAward(BigDecimal startTeamAward) {
		this.startTeamAward = startTeamAward;
	}
	
	
	/**
	 * <p>奖励开始时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * <p>奖励开始时间</p>
	 * @author:  zhouming
	 * @param:   @param startTime
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	
	/**
	 * <p>奖励结束时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>奖励结束时间</p>
	 * @author:  zhouming
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-23 16:57:20    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-08-23 16:57:20   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
