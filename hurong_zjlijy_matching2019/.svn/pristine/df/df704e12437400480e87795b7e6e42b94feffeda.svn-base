/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-06-28 11:14:53 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> ShopMqExceptionLog </p>
 * @author:         houzhen
 * @Date :          2019-06-28 11:14:53  
 */
@Table(name="shop_mqexception_log")
public class ShopMqExceptionLog extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "functionName")
	private String functionName;  //方法名称
	
	@Column(name= "exceptionText")
	private String exceptionText;  //异常原因
	
	@Column(name= "param")
	private String param;  //参数
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "customerId")
	private String customerId;  //用户id
	
	
	
	
	/**
	 * <p></p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-06-28 11:14:53    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  houzhen
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-28 11:14:53   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>方法名称</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-28 11:14:53    
	 */
	public String getFunctionName() {
		return functionName;
	}
	
	/**
	 * <p>方法名称</p>
	 * @author:  houzhen
	 * @param:   @param functionName
	 * @return:  void 
	 * @Date :   2019-06-28 11:14:53   
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	
	/**
	 * <p>异常原因</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-28 11:14:53    
	 */
	public String getExceptionText() {
		return exceptionText;
	}
	
	/**
	 * <p>异常原因</p>
	 * @author:  houzhen
	 * @param:   @param exceptionText
	 * @return:  void 
	 * @Date :   2019-06-28 11:14:53   
	 */
	public void setExceptionText(String exceptionText) {
		this.exceptionText = exceptionText;
	}
	
	
	/**
	 * <p>参数</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-28 11:14:53    
	 */
	public String getParam() {
		return param;
	}
	
	/**
	 * <p>参数</p>
	 * @author:  houzhen
	 * @param:   @param param
	 * @return:  void 
	 * @Date :   2019-06-28 11:14:53   
	 */
	public void setParam(String param) {
		this.param = param;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-28 11:14:53    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  houzhen
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-06-28 11:14:53   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>持续时间</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-28 11:14:53    
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>持续时间</p>
	 * @author:  houzhen
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-28 11:14:53   
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	

}
