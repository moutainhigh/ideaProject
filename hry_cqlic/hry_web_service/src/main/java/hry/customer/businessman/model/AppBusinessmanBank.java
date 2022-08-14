/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-12-18 16:55:04 
 */
package hry.customer.businessman.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> AppBusinessmanBank </p>
 * @author:         liushilei
 * @Date :          2017-12-18 16:55:04  
 */
@Table(name="app_businessman_bank")
public class AppBusinessmanBank extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "bankcard")
	private String bankcard;  //bankcard
	
	@Column(name= "bankname")
	private String bankname;  //bankname
	
	@Column(name= "bankowner")
	private String bankowner;  //bankowner
	
	@Column(name= "businessmanId")
	private Long businessmanId;  //businessmanId
	
	@Transient
	private String businessmanTrueName;//交易商名称
	
	
	
	
	
	
	public String getBusinessmanTrueName() {
		return businessmanTrueName;
	}

	public void setBusinessmanTrueName(String businessmanTrueName) {
		this.businessmanTrueName = businessmanTrueName;
	}

	/**
	 * <p>id</p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2017-12-18 16:55:04    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-12-18 16:55:04   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>bankcard</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-12-18 16:55:04    
	 */
	public String getBankcard() {
		return bankcard;
	}
	
	/**
	 * <p>bankcard</p>
	 * @author:  liushilei
	 * @param:   @param bankcard
	 * @return:  void 
	 * @Date :   2017-12-18 16:55:04   
	 */
	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}
	
	
	/**
	 * <p>bankname</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-12-18 16:55:04    
	 */
	public String getBankname() {
		return bankname;
	}
	
	/**
	 * <p>bankname</p>
	 * @author:  liushilei
	 * @param:   @param bankname
	 * @return:  void 
	 * @Date :   2017-12-18 16:55:04   
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	
	
	/**
	 * <p>bankowner</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-12-18 16:55:04    
	 */
	public String getBankowner() {
		return bankowner;
	}
	
	/**
	 * <p>bankowner</p>
	 * @author:  liushilei
	 * @param:   @param bankowner
	 * @return:  void 
	 * @Date :   2017-12-18 16:55:04   
	 */
	public void setBankowner(String bankowner) {
		this.bankowner = bankowner;
	}

	public Long getBusinessmanId() {
		return businessmanId;
	}

	public void setBusinessmanId(Long businessmanId) {
		this.businessmanId = businessmanId;
	}
	
	
	
	
	

}
