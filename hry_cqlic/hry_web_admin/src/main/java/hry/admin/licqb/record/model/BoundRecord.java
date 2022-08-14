/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-04-02 10:43:23 
 */
package hry.admin.licqb.record.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> BoundRecord </p>
 * @author:         zhouming
 * @Date :          2020-04-02 10:43:23  
 */
@Table(name="lc_bound_record")
public class BoundRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "oAccountNum")
	private String oAccountNum;  //用户原账号
	
	@Column(name= "nAccountNum")
	private String nAccountNum;  //用户新账号
	
	@Column(name= "accountNumType")
	private Integer accountNumType;  //账号类型: 1 手机号 2 邮箱
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-04-02 10:43:23    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-04-02 10:43:23   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-04-02 10:43:23    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2020-04-02 10:43:23   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>用户原账号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-04-02 10:43:23    
	 */
	public String getOAccountNum() {
		return oAccountNum;
	}
	
	/**
	 * <p>用户原账号</p>
	 * @author:  zhouming
	 * @param:   @param oAccountNum
	 * @return:  void 
	 * @Date :   2020-04-02 10:43:23   
	 */
	public void setOAccountNum(String oAccountNum) {
		this.oAccountNum = oAccountNum;
	}
	
	
	/**
	 * <p>用户新账号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-04-02 10:43:23    
	 */
	public String getNAccountNum() {
		return nAccountNum;
	}
	
	/**
	 * <p>用户新账号</p>
	 * @author:  zhouming
	 * @param:   @param nAccountNum
	 * @return:  void 
	 * @Date :   2020-04-02 10:43:23   
	 */
	public void setNAccountNum(String nAccountNum) {
		this.nAccountNum = nAccountNum;
	}
	
	
	/**
	 * <p>账号类型: 1 手机号 2 邮箱</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-04-02 10:43:23    
	 */
	public Integer getAccountNumType() {
		return accountNumType;
	}
	
	/**
	 * <p>账号类型: 1 手机号 2 邮箱</p>
	 * @author:  zhouming
	 * @param:   @param accountNumType
	 * @return:  void 
	 * @Date :   2020-04-02 10:43:23   
	 */
	public void setAccountNumType(Integer accountNumType) {
		this.accountNumType = accountNumType;
	}
	
	

}
