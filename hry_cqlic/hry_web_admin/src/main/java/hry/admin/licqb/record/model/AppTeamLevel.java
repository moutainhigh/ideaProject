/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-15 15:30:24 
 */
package hry.admin.licqb.record.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> AppTeamLevel </p>
 * @author:         zhouming
 * @Date :          2019-08-15 15:30:24  
 */
@Table(name="app_team_level")
public class AppTeamLevel extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "parentId")
	private Long parentId;  //推荐人Id
	
	@Column(name= "level")
	private Integer level;  //层级
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "parentInvitationCode")
	private String parentInvitationCode;  //推荐人邀请码
	
	@Column(name= "customerInvitationCode")
	private String customerInvitationCode;  //用户邀请码
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-15 15:30:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-15 15:30:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-15 15:30:24    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-15 15:30:24   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>推荐人Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-15 15:30:24    
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * <p>推荐人Id</p>
	 * @author:  zhouming
	 * @param:   @param parentId
	 * @return:  void 
	 * @Date :   2019-08-15 15:30:24   
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
	/**
	 * <p>层级</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-15 15:30:24    
	 */
	public Integer getLevel() {
		return level;
	}
	
	/**
	 * <p>层级</p>
	 * @author:  zhouming
	 * @param:   @param level
	 * @return:  void 
	 * @Date :   2019-08-15 15:30:24   
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-15 15:30:24    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-08-15 15:30:24   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>推荐人邀请码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-15 15:30:24    
	 */
	public String getParentInvitationCode() {
		return parentInvitationCode;
	}
	
	/**
	 * <p>推荐人邀请码</p>
	 * @author:  zhouming
	 * @param:   @param parentInvitationCode
	 * @return:  void 
	 * @Date :   2019-08-15 15:30:24   
	 */
	public void setParentInvitationCode(String parentInvitationCode) {
		this.parentInvitationCode = parentInvitationCode;
	}
	
	
	/**
	 * <p>用户邀请码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-15 15:30:24    
	 */
	public String getCustomerInvitationCode() {
		return customerInvitationCode;
	}
	
	/**
	 * <p>用户邀请码</p>
	 * @author:  zhouming
	 * @param:   @param customerInvitationCode
	 * @return:  void 
	 * @Date :   2019-08-15 15:30:24   
	 */
	public void setCustomerInvitationCode(String customerInvitationCode) {
		this.customerInvitationCode = customerInvitationCode;
	}
	
	

}
