/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-27 18:13:22 
 */
package hry.chat.chat.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ChatSensitive </p>
 * @author:         sunyujie
 * @Date :          2018-04-27 18:13:22  
 */
@Table(name="chat_sensitive")
public class ChatSensitive extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "sensitiveWords")
	private String sensitiveWords;  //敏感词
	
	@Column(name= "saasid")
	private String saasid;  //saasid
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-04-27 18:13:22    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-04-27 18:13:22   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>敏感词</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-27 18:13:22    
	 */
	public String getSensitiveWords() {
		return sensitiveWords;
	}
	
	/**
	 * <p>敏感词</p>
	 * @author:  sunyujie
	 * @param:   @param sensitiveWords
	 * @return:  void 
	 * @Date :   2018-04-27 18:13:22   
	 */
	public void setSensitiveWords(String sensitiveWords) {
		this.sensitiveWords = sensitiveWords;
	}
	
	
	/**
	 * <p>saasid</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-27 18:13:22    
	 */
	public String getSaasid() {
		return saasid;
	}
	
	/**
	 * <p>saasid</p>
	 * @author:  sunyujie
	 * @param:   @param saasid
	 * @return:  void 
	 * @Date :   2018-04-27 18:13:22   
	 */
	public void setSaasid(String saasid) {
		this.saasid = saasid;
	}
	
	

}
