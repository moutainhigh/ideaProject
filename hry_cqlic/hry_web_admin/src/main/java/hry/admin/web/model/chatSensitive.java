/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-29 11:30:50 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> chatSensitive </p>
 * @author:         sunyujie
 * @Date :          2018-06-29 11:30:50  
 */
@Table(name="chat_sensitive")
public class chatSensitive extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "sensitiveWords")
	private String sensitiveWords;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-06-29 11:30:50    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-29 11:30:50   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-29 11:30:50    
	 */
	public String getSensitiveWords() {
		return sensitiveWords;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param sensitiveWords
	 * @return:  void 
	 * @Date :   2018-06-29 11:30:50   
	 */
	public void setSensitiveWords(String sensitiveWords) {
		this.sensitiveWords = sensitiveWords;
	}
	
	

}
