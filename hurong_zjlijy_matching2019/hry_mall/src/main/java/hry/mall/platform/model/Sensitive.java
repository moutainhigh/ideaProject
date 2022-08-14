/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-04 19:06:42 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Sensitive </p>
 * @author:         luyue
 * @Date :          2018-12-04 19:06:42  
 */
@Table(name="shop_sensitive")
public class Sensitive extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "words")
	private String words;  //敏感词总集
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-12-04 19:06:42    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-04 19:06:42   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>敏感词总集</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-04 19:06:42    
	 */
	public String getWords() {
		return words;
	}
	
	/**
	 * <p>敏感词总集</p>
	 * @author:  luyue
	 * @param:   @param words
	 * @return:  void 
	 * @Date :   2018-12-04 19:06:42   
	 */
	public void setWords(String words) {
		this.words = words;
	}
	
	

}
