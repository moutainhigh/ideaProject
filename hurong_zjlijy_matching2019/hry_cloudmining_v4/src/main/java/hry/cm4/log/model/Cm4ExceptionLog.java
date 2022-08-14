/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:01:39 
 */
package hry.cm4.log.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm4ExceptionLog </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:01:39  
 */
@Table(name="cm4_exception_log")
public class Cm4ExceptionLog extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "functionName")
	private String functionName;  //方法名称
	
	@Column(name= "exceptionText")
	private String exceptionText;  //异常原因
	
	@Column(name= "ipaddress")
	private String ipaddress;  //ip地址
	
	@Column(name= "customerId")
	private String customerId;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:01:39    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-21 10:01:39   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>方法名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:01:39    
	 */
	public String getFunctionName() {
		return functionName;
	}
	
	/**
	 * <p>方法名称</p>
	 * @author:  yaozh
	 * @param:   @param functionName
	 * @return:  void 
	 * @Date :   2019-11-21 10:01:39   
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	
	/**
	 * <p>异常原因</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:01:39    
	 */
	public String getExceptionText() {
		return exceptionText;
	}
	
	/**
	 * <p>异常原因</p>
	 * @author:  yaozh
	 * @param:   @param exceptionText
	 * @return:  void 
	 * @Date :   2019-11-21 10:01:39   
	 */
	public void setExceptionText(String exceptionText) {
		this.exceptionText = exceptionText;
	}
	
	
	/**
	 * <p>ip地址</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:01:39    
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	
	/**
	 * <p>ip地址</p>
	 * @author:  yaozh
	 * @param:   @param ipaddress
	 * @return:  void 
	 * @Date :   2019-11-21 10:01:39   
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:01:39    
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-11-21 10:01:39   
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:01:39    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-11-21 10:01:39   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:01:39    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-11-21 10:01:39   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
