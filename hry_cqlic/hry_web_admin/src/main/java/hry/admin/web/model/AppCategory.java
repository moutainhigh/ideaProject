/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 14:46:47 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppCategory </p>
 * @author:         liuchenghui
 * @Date :          2018-06-26 14:46:47  
 */
@Table(name="app_artic_category")
public class AppCategory extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "preateId")
	private Long preateId;  //
	
	@Column(name= "preateName")
	private String preateName;  //
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "type")
	private String type;  //
	
	@Column(name= "footerUrl")
	private String footerUrl;  //
	
	@Column(name= "seoFication")
	private String seoFication;  //
	
	@Column(name= "state")
	private Integer state;  //
	
	@Column(name= "isShow")
	private Integer isShow;  //
	
	@Column(name= "sort")
	private Integer sort;  //
	
	@Column(name= "describes")
	private String describes;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "isPage")
	private Integer isPage;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public Long getPreateId() {
		return preateId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param preateId
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setPreateId(Long preateId) {
		this.preateId = preateId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public String getPreateName() {
		return preateName;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param preateName
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setPreateName(String preateName) {
		this.preateName = preateName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public String getFooterUrl() {
		return footerUrl;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param footerUrl
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setFooterUrl(String footerUrl) {
		this.footerUrl = footerUrl;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public String getSeoFication() {
		return seoFication;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param seoFication
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setSeoFication(String seoFication) {
		this.seoFication = seoFication;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public Integer getIsShow() {
		return isShow;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param isShow
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public String getDescribes() {
		return describes;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param describes
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-06-26 14:46:47    
	 */
	public Integer getIsPage() {
		return isPage;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param isPage
	 * @return:  void 
	 * @Date :   2018-06-26 14:46:47   
	 */
	public void setIsPage(Integer isPage) {
		this.isPage = isPage;
	}
	
	

}
