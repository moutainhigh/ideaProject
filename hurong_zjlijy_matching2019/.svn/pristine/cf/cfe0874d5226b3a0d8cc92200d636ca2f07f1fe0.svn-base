/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-30 13:39:20 
 */
package hry.mall.team.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppTeamlevel </p>
 * @author:         yaozh
 * @Date :          2019-07-30 13:39:20  
 */
@Table(name="app_teamlevel")
public class AppTeamlevel extends BaseModel {
	
	
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
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-07-30 13:39:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-30 13:39:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-07-30 13:39:20    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  yaozh
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-07-30 13:39:20   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>推荐人Id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-07-30 13:39:20    
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * <p>推荐人Id</p>
	 * @author:  yaozh
	 * @param:   @param parentId
	 * @return:  void 
	 * @Date :   2019-07-30 13:39:20   
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
	/**
	 * <p>层级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-30 13:39:20    
	 */
	public Integer getLevel() {
		return level;
	}
	
	/**
	 * <p>层级</p>
	 * @author:  yaozh
	 * @param:   @param level
	 * @return:  void 
	 * @Date :   2019-07-30 13:39:20   
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 13:39:20    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-07-30 13:39:20   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>推荐人邀请码</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 13:39:20    
	 */
	public String getParentInvitationCode() {
		return parentInvitationCode;
	}
	
	/**
	 * <p>推荐人邀请码</p>
	 * @author:  yaozh
	 * @param:   @param parentInvitationCode
	 * @return:  void 
	 * @Date :   2019-07-30 13:39:20   
	 */
	public void setParentInvitationCode(String parentInvitationCode) {
		this.parentInvitationCode = parentInvitationCode;
	}
	
	
	/**
	 * <p>用户邀请码</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 13:39:20    
	 */
	public String getCustomerInvitationCode() {
		return customerInvitationCode;
	}
	
	/**
	 * <p>用户邀请码</p>
	 * @author:  yaozh
	 * @param:   @param customerInvitationCode
	 * @return:  void 
	 * @Date :   2019-07-30 13:39:20   
	 */
	public void setCustomerInvitationCode(String customerInvitationCode) {
		this.customerInvitationCode = customerInvitationCode;
	}
	
	

}
