/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 14:22:05 
 */
package hry.otc.releaseAdvertisement.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppOrderSpeak </p>
 * @author:         denghf
 * @Date :          2018-06-29 14:22:05  
 */
@Table(name="app_order_speak")
public class AppOrderSpeak extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "buyId")
	private Long buyId;  //{name:'买方id'}
	
	@Column(name= "sellId")
	private Long sellId;  //{name:'卖方id'}
	
	@Column(name= "buySpeak")
	private String buySpeak;  //{name:'买方聊天记录'}
	
	@Column(name= "sellSpeak")
	private String sellSpeak;  //{name:'卖方聊天记录'}
	
	@Column(name= "buyTime")
	private Date buyTime;  //buyTime
	
	@Column(name= "sellTime")
	private Date sellTime;  //sellTime
	
	@Column(name= "orderId")
	private String orderId;  //{name:'订单id'}
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-29 14:22:05    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-29 14:22:05   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>{name:'买方id'}</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-29 14:22:05    
	 */
	public Long getBuyId() {
		return buyId;
	}
	
	/**
	 * <p>{name:'买方id'}</p>
	 * @author:  denghf
	 * @param:   @param buyId
	 * @return:  void 
	 * @Date :   2018-06-29 14:22:05   
	 */
	public void setBuyId(Long buyId) {
		this.buyId = buyId;
	}
	
	
	/**
	 * <p>{name:'卖方id'}</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-29 14:22:05    
	 */
	public Long getSellId() {
		return sellId;
	}
	
	/**
	 * <p>{name:'卖方id'}</p>
	 * @author:  denghf
	 * @param:   @param sellId
	 * @return:  void 
	 * @Date :   2018-06-29 14:22:05   
	 */
	public void setSellId(Long sellId) {
		this.sellId = sellId;
	}
	
	
	/**
	 * <p>{name:'买方聊天记录'}</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-29 14:22:05    
	 */
	public String getBuySpeak() {
		return buySpeak;
	}
	
	/**
	 * <p>{name:'买方聊天记录'}</p>
	 * @author:  denghf
	 * @param:   @param buySpeak
	 * @return:  void 
	 * @Date :   2018-06-29 14:22:05   
	 */
	public void setBuySpeak(String buySpeak) {
		this.buySpeak = buySpeak;
	}
	
	
	/**
	 * <p>{name:'卖方聊天记录'}</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-29 14:22:05    
	 */
	public String getSellSpeak() {
		return sellSpeak;
	}
	
	/**
	 * <p>{name:'卖方聊天记录'}</p>
	 * @author:  denghf
	 * @param:   @param sellSpeak
	 * @return:  void 
	 * @Date :   2018-06-29 14:22:05   
	 */
	public void setSellSpeak(String sellSpeak) {
		this.sellSpeak = sellSpeak;
	}
	
	
	/**
	 * <p>buyTime</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-06-29 14:22:05    
	 */
	public Date getBuyTime() {
		return buyTime;
	}
	
	/**
	 * <p>buyTime</p>
	 * @author:  denghf
	 * @param:   @param buyTime
	 * @return:  void 
	 * @Date :   2018-06-29 14:22:05   
	 */
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	
	
	/**
	 * <p>sellTime</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-06-29 14:22:05    
	 */
	public Date getSellTime() {
		return sellTime;
	}
	
	/**
	 * <p>sellTime</p>
	 * @author:  denghf
	 * @param:   @param sellTime
	 * @return:  void 
	 * @Date :   2018-06-29 14:22:05   
	 */
	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}
	
	
	/**
	 * <p>{name:'订单id'}</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-29 14:22:05    
	 */
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>{name:'订单id'}</p>
	 * @author:  denghf
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-06-29 14:22:05   
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	

}
