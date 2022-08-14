/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 15:47:20 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppCategoryLangname </p>
 * @author:         liuchenghui
 * @Date :          2018-06-26 15:47:20  
 */
@Table(name="app_article_category_langname")
public class AppCategoryLangname extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "categoryId")
	private Long categoryId;  //
	
	@Column(name= "langType")
	private String langType;  //
	
	@Column(name= "dicKey")
	private String dicKey;  //
	
	@Column(name= "langName")
	private String langName;  //
	
	@Column(name= "langContent")
	private String langContent;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "dicId")
	private Long dicId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-06-26 15:47:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-26 15:47:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-06-26 15:47:20    
	 */
	public Long getCategoryId() {
		return categoryId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param categoryId
	 * @return:  void 
	 * @Date :   2018-06-26 15:47:20   
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 15:47:20    
	 */
	public String getLangType() {
		return langType;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langType
	 * @return:  void 
	 * @Date :   2018-06-26 15:47:20   
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 15:47:20    
	 */
	public String getDicKey() {
		return dicKey;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param dicKey
	 * @return:  void 
	 * @Date :   2018-06-26 15:47:20   
	 */
	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 15:47:20    
	 */
	public String getLangName() {
		return langName;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langName
	 * @return:  void 
	 * @Date :   2018-06-26 15:47:20   
	 */
	public void setLangName(String langName) {
		this.langName = langName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 15:47:20    
	 */
	public String getLangContent() {
		return langContent;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langContent
	 * @return:  void 
	 * @Date :   2018-06-26 15:47:20   
	 */
	public void setLangContent(String langContent) {
		this.langContent = langContent;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 15:47:20    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-26 15:47:20   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-06-26 15:47:20    
	 */
	public Long getDicId() {
		return dicId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param dicId
	 * @return:  void 
	 * @Date :   2018-06-26 15:47:20   
	 */
	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}
	
	

}
