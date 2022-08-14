/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:31:15 
 */
package hry.admin.klg.level.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgTeamlevel </p>
 * @author:         lzy
 * @Date :          2019-04-11 17:31:15  
 */
@Table(name="klg_teamlevel")
public class KlgTeamlevel extends BaseModel {
	
	
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
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-11 17:31:15    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-11 17:31:15   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-11 17:31:15    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-11 17:31:15   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>推荐人Id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-11 17:31:15    
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * <p>推荐人Id</p>
	 * @author:  lzy
	 * @param:   @param parentId
	 * @return:  void 
	 * @Date :   2019-04-11 17:31:15   
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
	/**
	 * <p>层级</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:31:15    
	 */
	public Integer getLevel() {
		return level;
	}
	
	/**
	 * <p>层级</p>
	 * @author:  lzy
	 * @param:   @param level
	 * @return:  void 
	 * @Date :   2019-04-11 17:31:15   
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-11 17:31:15    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-04-11 17:31:15   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
