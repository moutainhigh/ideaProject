/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-02 17:47:13 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppLanguage </p>
 * @author:         liuchenghui
 * @Date :          2018-07-02 17:47:13  
 */
@Table(name="app_language")
public class AppLanguage extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "langKey")
	private String langKey;  //
	
	@Column(name= "langVal")
	private String langVal;  //
	
	@Column(name= "langType")
	private String langType;  //
	
	@Column(name= "langCode")
	private String langCode;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name = "wordSource")
	private String wordSource;

	public String getWordSource () {
		return wordSource;
	}

	public void setWordSource (String wordSource) {
		this.wordSource = wordSource;
	}

	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-07-02 17:47:13    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-02 17:47:13   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-02 17:47:13    
	 */
	public String getLangKey() {
		return langKey;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langKey
	 * @return:  void 
	 * @Date :   2018-07-02 17:47:13   
	 */
	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-02 17:47:13    
	 */
	public String getLangVal() {
		return langVal;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langVal
	 * @return:  void 
	 * @Date :   2018-07-02 17:47:13   
	 */
	public void setLangVal(String langVal) {
		this.langVal = langVal;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-02 17:47:13    
	 */
	public String getLangType() {
		return langType;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langType
	 * @return:  void 
	 * @Date :   2018-07-02 17:47:13   
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-02 17:47:13    
	 */
	public String getLangCode() {
		return langCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langCode
	 * @return:  void 
	 * @Date :   2018-07-02 17:47:13   
	 */
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-02 17:47:13    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-02 17:47:13   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
