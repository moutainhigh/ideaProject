/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 14:55:28 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppArticle </p>
 * @author:         liuchenghui
 * @Date :          2018-06-26 14:55:28  
 */
@Table(name="app_article")
public class AppArticle extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "title")
	private String title;  //
	
	@Column(name= "categoryName")
	private String categoryName;  //
	
	@Column(name= "categoryId")
	private Long categoryId;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "seoTitle")
	private String seoTitle;  //
	
	@Column(name= "seoKeyword")
	private String seoKeyword;  //
	
	@Column(name= "seoDescribe")
	private String seoDescribe;  //
	
	@Column(name= "sort")
	private Integer sort;  //
	
	@Column(name= "isStick")
	private Integer isStick;  //
	
	@Column(name= "content")
	private String content;  //
	
	@Column(name= "isOutLink")
	private Integer isOutLink;  //
	
	@Column(name= "outLink")
	private String outLink;  //
	
	@Column(name= "titleImage")
	private String titleImage;  //
	
	@Column(name= "shortContent")
	private String shortContent;  //
	
	@Column(name= "writer")
	private String writer;  //
	
	@Column(name= "source")
	private String source;  //
	
	@Column(name= "hits")
	private Integer hits;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "langType")
	private String langType;  //
	
	@Column(name= "langName")
	private String langName;  //
	
	@Column(name= "langId")
	private Long langId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param categoryName
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public Long getCategoryId() {
		return categoryId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param categoryId
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getSeoTitle() {
		return seoTitle;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param seoTitle
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getSeoKeyword() {
		return seoKeyword;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param seoKeyword
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getSeoDescribe() {
		return seoDescribe;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param seoDescribe
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setSeoDescribe(String seoDescribe) {
		this.seoDescribe = seoDescribe;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public Integer getIsStick() {
		return isStick;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param isStick
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setIsStick(Integer isStick) {
		this.isStick = isStick;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public Integer getIsOutLink() {
		return isOutLink;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param isOutLink
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setIsOutLink(Integer isOutLink) {
		this.isOutLink = isOutLink;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getOutLink() {
		return outLink;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param outLink
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setOutLink(String outLink) {
		this.outLink = outLink;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getTitleImage() {
		return titleImage;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param titleImage
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getShortContent() {
		return shortContent;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param shortContent
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getWriter() {
		return writer;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param writer
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param source
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public Integer getHits() {
		return hits;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param hits
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getLangType() {
		return langType;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langType
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public String getLangName() {
		return langName;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langName
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setLangName(String langName) {
		this.langName = langName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-06-26 14:55:28    
	 */
	public Long getLangId() {
		return langId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param langId
	 * @return:  void 
	 * @Date :   2018-06-26 14:55:28   
	 */
	public void setLangId(Long langId) {
		this.langId = langId;
	}
	
	

}
