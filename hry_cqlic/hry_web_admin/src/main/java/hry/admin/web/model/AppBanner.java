/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 14:34:44 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppBanner </p>
 * @author:         liushilei
 * @Date :          2018-06-13 14:34:44  
 */
@Table(name="app_banner")
public class AppBanner extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "picturePath")
	private String picturePath;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "isTop")
	private Integer isTop;  //
	
	@Column(name= "isShow")
	private Integer isShow;  //
	
	@Column(name= "sort")
	private Integer sort;  //
	
	@Column(name= "remark1")
	private String remark1;  //
	
	@Column(name= "remark2")
	private String remark2;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "remark3")
	private String remark3;  //
	
	@Column(name= "applicationType")
	private Integer applicationType;  //
	
	@Column(name = "langCode")
	private String langCode;

	@Column(name = "whereUse") //用在什么地方
	private String whereUse;

	public String getWhereUse() {
		return whereUse;
	}

	public void setWhereUse(String whereUse) {
		this.whereUse = whereUse;
	}

	public String getLangCode () {
		return langCode;
	}

	public void setLangCode (String langCode) {
		this.langCode = langCode;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public String getPicturePath() {
		return picturePath;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param picturePath
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public Integer getIsTop() {
		return isTop;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isTop
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public Integer getIsShow() {
		return isShow;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isShow
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public String getRemark1() {
		return remark1;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param remark1
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public String getRemark2() {
		return remark2;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param remark2
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public String getRemark3() {
		return remark3;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param remark3
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 14:34:44    
	 */
	public Integer getApplicationType() {
		return applicationType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param applicationType
	 * @return:  void 
	 * @Date :   2018-06-13 14:34:44   
	 */
	public void setApplicationType(Integer applicationType) {
		this.applicationType = applicationType;
	}
	
	

}
