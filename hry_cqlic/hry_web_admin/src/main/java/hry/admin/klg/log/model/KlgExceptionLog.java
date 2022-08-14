/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-06 15:31:10 
 */
package hry.admin.klg.log.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgExceptionLog </p>
 * @author:         yaozhuo
 * @Date :          2019-05-06 15:31:10  
 */
@Table(name="klg_exception_log")
public class KlgExceptionLog extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "functionName")
	private String functionName;  //{name:'方法名称'}
	
	@Column(name= "exceptionText")
	private String exceptionText;  //{name:'异常原因'}
	
	@Column(name= "ipaddress")
	private String ipaddress;  //{name:'ip地址'}
	
	@Column(name= "customerId")
	private String customerId;  //{name:'ID'}
	
	@Column(name= "mobile")
	private String mobile;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-05-06 15:31:10    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-06 15:31:10   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>{name:'方法名称'}</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-06 15:31:10    
	 */
	public String getFunctionName() {
		return functionName;
	}
	
	/**
	 * <p>{name:'方法名称'}</p>
	 * @author:  yaozhuo
	 * @param:   @param functionName
	 * @return:  void 
	 * @Date :   2019-05-06 15:31:10   
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	
	/**
	 * <p>{name:'异常原因'}</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-06 15:31:10    
	 */
	public String getExceptionText() {
		return exceptionText;
	}
	
	/**
	 * <p>{name:'异常原因'}</p>
	 * @author:  yaozhuo
	 * @param:   @param exceptionText
	 * @return:  void 
	 * @Date :   2019-05-06 15:31:10   
	 */
	public void setExceptionText(String exceptionText) {
		this.exceptionText = exceptionText;
	}
	
	
	/**
	 * <p>{name:'ip地址'}</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-06 15:31:10    
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	
	/**
	 * <p>{name:'ip地址'}</p>
	 * @author:  yaozhuo
	 * @param:   @param ipaddress
	 * @return:  void 
	 * @Date :   2019-05-06 15:31:10   
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
	/**
	 * <p>{name:'ID'}</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-06 15:31:10    
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>{name:'ID'}</p>
	 * @author:  yaozhuo
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-05-06 15:31:10   
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-06 15:31:10    
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param mobile
	 * @return:  void 
	 * @Date :   2019-05-06 15:31:10   
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	

}
