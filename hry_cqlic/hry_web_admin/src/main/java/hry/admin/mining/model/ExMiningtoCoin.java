/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2018-07-30 17:53:34 
 */
package hry.admin.mining.model;

import hry.bean.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p> ExMiningtoCoin </p>
 * @author:         jidn
 * @Date :          2018-07-30 17:53:34  
 */
@Table(name="ex_miningto_coin")
public class ExMiningtoCoin extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "message")
	private String message;  //message
	
	@Column(name= "success")
	private String success;  //success
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2018-07-30 17:53:34    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-30 17:53:34   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>message</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-07-30 17:53:34    
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * <p>message</p>
	 * @author:  jidn
	 * @param:   @param message
	 * @return:  void 
	 * @Date :   2018-07-30 17:53:34   
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	/**
	 * <p>success</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-07-30 17:53:34    
	 */
	public String getSuccess() {
		return success;
	}
	
	/**
	 * <p>success</p>
	 * @author:  jidn
	 * @param:   @param success
	 * @return:  void 
	 * @Date :   2018-07-30 17:53:34   
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	
	

}
