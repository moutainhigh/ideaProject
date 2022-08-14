/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:46:50 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> appWorkOrderCategory </p>
 * @author:         sunyujie
 * @Date :          2018-07-02 09:46:50  
 */
@Table(name="app_workorder_category")
public class AppWorkOrderCategory extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "typeName")
	private String typeName;  //
	
	@Column(name= "describes")
	private String describes;  //
	
	@Column(name= "sort")
	private Integer sort;  //
	
	@Column(name= "state")
	private Integer state;  //
	
	@Column(name= "language")
	private Integer language;  //
	
	@Column(name= "languageDic")
	private String languageDic;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-07-02 09:46:50    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-02 09:46:50   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:46:50    
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param typeName
	 * @return:  void 
	 * @Date :   2018-07-02 09:46:50   
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:46:50    
	 */
	public String getDescribes() {
		return describes;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param describes
	 * @return:  void 
	 * @Date :   2018-07-02 09:46:50   
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-02 09:46:50    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-07-02 09:46:50   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-02 09:46:50    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-07-02 09:46:50   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-02 09:46:50    
	 */
	public Integer getLanguage() {
		return language;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param language
	 * @return:  void 
	 * @Date :   2018-07-02 09:46:50   
	 */
	public void setLanguage(Integer language) {
		this.language = language;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:46:50    
	 */
	public String getLanguageDic() {
		return languageDic;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param languageDic
	 * @return:  void 
	 * @Date :   2018-07-02 09:46:50   
	 */
	public void setLanguageDic(String languageDic) {
		this.languageDic = languageDic;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:46:50    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-02 09:46:50   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
