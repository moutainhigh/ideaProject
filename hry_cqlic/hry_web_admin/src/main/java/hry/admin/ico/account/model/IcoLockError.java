/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-05 10:03:27 
 */
package hry.admin.ico.account.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoLockError </p>
 * @author:         lzy
 * @Date :          2019-03-05 10:03:27  
 */
@Table(name="ico_lock_error")
public class IcoLockError extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "count")
	private Integer count;  //次数
	
	@Column(name= "state")
	private Integer state;  //0等待，1完成
	
	@Column(name= "mqMessage")
	private String mqMessage;  //消息体
	
	@Column(name= "errorInfo")
	private String errorInfo;  //错误信息

	@Column(name= "orderBuyId")
	private Long orderBuyId;  //

	public Long getOrderBuyId() {
		return orderBuyId;
	}

	public void setOrderBuyId(Long orderBuyId) {
		this.orderBuyId = orderBuyId;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-05 10:03:27    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-03-05 10:03:27   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-05 10:03:27    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-03-05 10:03:27   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>次数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-03-05 10:03:27    
	 */
	public Integer getCount() {
		return count;
	}
	
	/**
	 * <p>次数</p>
	 * @author:  lzy
	 * @param:   @param count
	 * @return:  void 
	 * @Date :   2019-03-05 10:03:27   
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	/**
	 * <p>0等待，1完成</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-03-05 10:03:27    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>0等待，1完成</p>
	 * @author:  lzy
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-03-05 10:03:27   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>消息体</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-03-05 10:03:27    
	 */
	public String getMqMessage() {
		return mqMessage;
	}
	
	/**
	 * <p>消息体</p>
	 * @author:  lzy
	 * @param:   @param mqMessage
	 * @return:  void 
	 * @Date :   2019-03-05 10:03:27   
	 */
	public void setMqMessage(String mqMessage) {
		this.mqMessage = mqMessage;
	}
	
	
	/**
	 * <p>错误信息</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-03-05 10:03:27    
	 */
	public String getErrorInfo() {
		return errorInfo;
	}
	
	/**
	 * <p>错误信息</p>
	 * @author:  lzy
	 * @param:   @param errorInfo
	 * @return:  void 
	 * @Date :   2019-03-05 10:03:27   
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	

}
