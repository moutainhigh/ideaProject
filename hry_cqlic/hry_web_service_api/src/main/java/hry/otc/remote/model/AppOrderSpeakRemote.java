/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-28 19:53:43 
 */
package hry.otc.remote.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> AppOrderSpeak </p>
 * @author:         denghf
 * @Date :          2018-06-28 19:53:43  
 */
public class AppOrderSpeakRemote implements Serializable {
	
	
	private Long id;  //id
	
	private String buyId;  //{name:'买方id'}
	
	private String sellId;  //{name:'卖方id'}
	
	private String buySpeak;  //{name:'买方聊天记录'}
	
	private String sellSpeak;  //{name:'卖方聊天记录'}
	
	private Date buyTime;  //buyTime
	
	private Date sellTime;  //sellTime
	
	private String orderId;  //{name:'订单id'}
	
	
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-28 19:53:43    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-28 19:53:43   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>{name:'买方id'}</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-28 19:53:43    
	 */
	public String getBuyId() {
		return buyId;
	}
	
	/**
	 * <p>{name:'买方id'}</p>
	 * @author:  denghf
	 * @param:   @param buyId
	 * @return:  void 
	 * @Date :   2018-06-28 19:53:43   
	 */
	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}
	
	
	/**
	 * <p>{name:'卖方id'}</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-28 19:53:43    
	 */
	public String getSellId() {
		return sellId;
	}
	
	/**
	 * <p>{name:'卖方id'}</p>
	 * @author:  denghf
	 * @param:   @param sellId
	 * @return:  void 
	 * @Date :   2018-06-28 19:53:43   
	 */
	public void setSellId(String sellId) {
		this.sellId = sellId;
	}
	
	
	/**
	 * <p>{name:'买方聊天记录'}</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-28 19:53:43    
	 */
	public String getBuySpeak() {
		return buySpeak;
	}
	
	/**
	 * <p>{name:'买方聊天记录'}</p>
	 * @author:  denghf
	 * @param:   @param buySpeak
	 * @return:  void 
	 * @Date :   2018-06-28 19:53:43   
	 */
	public void setBuySpeak(String buySpeak) {
		this.buySpeak = buySpeak;
	}
	
	
	/**
	 * <p>{name:'卖方聊天记录'}</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-28 19:53:43    
	 */
	public String getSellSpeak() {
		return sellSpeak;
	}
	
	/**
	 * <p>{name:'卖方聊天记录'}</p>
	 * @author:  denghf
	 * @param:   @param sellSpeak
	 * @return:  void 
	 * @Date :   2018-06-28 19:53:43   
	 */
	public void setSellSpeak(String sellSpeak) {
		this.sellSpeak = sellSpeak;
	}
	
	
	/**
	 * <p>buyTime</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-06-28 19:53:43    
	 */
	public Date getBuyTime() {
		return buyTime;
	}
	
	/**
	 * <p>buyTime</p>
	 * @author:  denghf
	 * @param:   @param buyTime
	 * @return:  void 
	 * @Date :   2018-06-28 19:53:43   
	 */
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	
	
	/**
	 * <p>sellTime</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-06-28 19:53:43    
	 */
	public Date getSellTime() {
		return sellTime;
	}
	
	/**
	 * <p>sellTime</p>
	 * @author:  denghf
	 * @param:   @param sellTime
	 * @return:  void 
	 * @Date :   2018-06-28 19:53:43   
	 */
	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}
	
	
	/**
	 * <p>{name:'订单id'}</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-28 19:53:43    
	 */
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>{name:'订单id'}</p>
	 * @author:  denghf
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-06-28 19:53:43   
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
