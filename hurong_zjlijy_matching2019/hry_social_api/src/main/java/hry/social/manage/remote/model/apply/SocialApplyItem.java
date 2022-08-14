/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-03 17:49:59 
 */
package hry.social.manage.remote.model.apply;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> SocialApplyItem </p>
 * @author:         javalx
 * @Date :          2019-06-03 17:49:59  
 */
@Table(name="social_apply_item")
public class SocialApplyItem extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "applyName")
	private String applyName;  //应用项名称
	
	@Column(name= "dockingType")
	private Integer dockingType;  //对接类型（0：内部，1：链接，2：接口）
	
	@Column(name= "applyDesc")
	private String applyDesc;  //描述
	
	@Column(name= "appLink")
	private String appLink;  //链接地址
	
	@Column(name= "iosLink")
	private String iosLink;  //IOS链接
	
	@Column(name= "typeKey")
	private String typeKey;  //应用类型key
	
	@Column(name= "applyImg")
	private String applyImg;  //应用图片
	
	@Column(name= "states")
	private Integer states;  //状态（0：关闭，1：开启）
	
	@Column(name= "packageName")
	private String packageName;  //包名
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>应用项名称</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public String getApplyName() {
		return applyName;
	}
	
	/**
	 * <p>应用项名称</p>
	 * @author:  javalx
	 * @param:   @param applyName
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	
	
	/**
	 * <p>对接类型（0：内部，1：链接，2：接口）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public Integer getDockingType() {
		return dockingType;
	}
	
	/**
	 * <p>对接类型（0：内部，1：链接，2：接口）</p>
	 * @author:  javalx
	 * @param:   @param dockingType
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setDockingType(Integer dockingType) {
		this.dockingType = dockingType;
	}
	
	
	/**
	 * <p>描述</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public String getApplyDesc() {
		return applyDesc;
	}
	
	/**
	 * <p>描述</p>
	 * @author:  javalx
	 * @param:   @param applyDesc
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}
	
	
	/**
	 * <p>链接地址</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public String getAppLink() {
		return appLink;
	}
	
	/**
	 * <p>链接地址</p>
	 * @author:  javalx
	 * @param:   @param appLink
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setAppLink(String appLink) {
		this.appLink = appLink;
	}
	
	
	/**
	 * <p>IOS链接</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public String getIosLink() {
		return iosLink;
	}
	
	/**
	 * <p>IOS链接</p>
	 * @author:  javalx
	 * @param:   @param iosLink
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setIosLink(String iosLink) {
		this.iosLink = iosLink;
	}
	
	
	/**
	 * <p>应用类型key</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public String getTypeKey() {
		return typeKey;
	}
	
	/**
	 * <p>应用类型key</p>
	 * @author:  javalx
	 * @param:   @param typeKey
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}
	
	
	/**
	 * <p>应用图片</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public String getApplyImg() {
		return applyImg;
	}
	
	/**
	 * <p>应用图片</p>
	 * @author:  javalx
	 * @param:   @param applyImg
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setApplyImg(String applyImg) {
		this.applyImg = applyImg;
	}
	
	
	/**
	 * <p>状态（0：关闭，1：开启）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>状态（0：关闭，1：开启）</p>
	 * @author:  javalx
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	/**
	 * <p>包名</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 17:49:59    
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * <p>包名</p>
	 * @author:  javalx
	 * @param:   @param packageName
	 * @return:  void 
	 * @Date :   2019-06-03 17:49:59   
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	

}
