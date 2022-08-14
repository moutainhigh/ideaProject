/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 11:56:58 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppFriendLink </p>
 * @author:         liushilei
 * @Date :          2018-06-20 11:56:58  
 */
@Table(name="app_friendlink")
public class AppFriendLink extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "linkUrl")
	private String linkUrl;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "isPicture")
	private Integer isPicture;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "picturePath")
	private String picturePath;  //
	
	@Column(name= "website")
	private String website;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-20 11:56:58    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-20 11:56:58   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 11:56:58    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-06-20 11:56:58   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 11:56:58    
	 */
	public String getLinkUrl() {
		return linkUrl;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param linkUrl
	 * @return:  void 
	 * @Date :   2018-06-20 11:56:58   
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 11:56:58    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-20 11:56:58   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 11:56:58    
	 */
	public Integer getIsPicture() {
		return isPicture;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isPicture
	 * @return:  void 
	 * @Date :   2018-06-20 11:56:58   
	 */
	public void setIsPicture(Integer isPicture) {
		this.isPicture = isPicture;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 11:56:58    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-20 11:56:58   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 11:56:58    
	 */
	public String getPicturePath() {
		return picturePath;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param picturePath
	 * @return:  void 
	 * @Date :   2018-06-20 11:56:58   
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 11:56:58    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-20 11:56:58   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	

}
