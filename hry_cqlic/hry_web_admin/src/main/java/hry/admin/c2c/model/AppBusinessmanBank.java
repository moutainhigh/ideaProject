/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:09 
 */
package hry.admin.c2c.model;


import hry.bean.BaseModel;
import org.hibernate.annotations.Target;


import javax.persistence.*;

/**
 * <p> appBusinessmanBank </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:34:09  
 */
@Table(name="app_businessman_bank")
public class AppBusinessmanBank extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "bankcard")
	private String bankcard;  //
	
	@Column(name= "bankname")
	private String bankname;  //
	
	@Column(name= "bankowner")
	private String bankowner;  //
	
	@Column(name= "businessmanId")
	private Long businessmanId;  //
	
	@Column(name= "isLock")
	private Integer isLock;  //

	@Transient
	private String businessName;


	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 13:34:09    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 13:34:09   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:34:09    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 13:34:09   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:34:09    
	 */
	public String getBankcard() {
		return bankcard;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param bankcard
	 * @return:  void 
	 * @Date :   2018-06-13 13:34:09   
	 */
	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:34:09    
	 */
	public String getBankname() {
		return bankname;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param bankname
	 * @return:  void 
	 * @Date :   2018-06-13 13:34:09   
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:34:09    
	 */
	public String getBankowner() {
		return bankowner;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param bankowner
	 * @return:  void 
	 * @Date :   2018-06-13 13:34:09   
	 */
	public void setBankowner(String bankowner) {
		this.bankowner = bankowner;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 13:34:09    
	 */
	public Long getBusinessmanId() {
		return businessmanId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param businessmanId
	 * @return:  void 
	 * @Date :   2018-06-13 13:34:09   
	 */
	public void setBusinessmanId(Long businessmanId) {
		this.businessmanId = businessmanId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 13:34:09    
	 */
	public Integer getIsLock() {
		return isLock;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isLock
	 * @return:  void 
	 * @Date :   2018-06-13 13:34:09   
	 */
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	
	

}
