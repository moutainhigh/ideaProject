/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-14 11:18:59 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppConfig </p>
 * @author:         liushilei
 * @Date :          2018-06-14 11:18:59  
 */
@Table(name="app_config")
public class AppConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "configid", unique = true, nullable = false)
	private Long configid;  //
	
	@Column(name= "configkey")
	private String configkey;  //
	
	@Column(name= "configname")
	private String configname;  //
	
	@Column(name= "configdescription")
	private String configdescription;  //
	
	@Column(name= "datatype")
	private Integer datatype;  // 1为输入框2为下拉 3为图片4为 文本编辑器 5为时间

	@Column(name= "value")
	private String value;  //
	
	@Column(name= "typekey")
	private String typekey;  //
	
	@Column(name= "typename")
	private String typename;  //
	
	@Column(name= "description")
	private String description;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "ishidden")
	private Integer ishidden;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public Long getConfigid() {
		return configid;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param configid
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setConfigid(Long configid) {
		this.configid = configid;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public String getConfigkey() {
		return configkey;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param configkey
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setConfigkey(String configkey) {
		this.configkey = configkey;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public String getConfigname() {
		return configname;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param configname
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setConfigname(String configname) {
		this.configname = configname;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public String getConfigdescription() {
		return configdescription;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param configdescription
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setConfigdescription(String configdescription) {
		this.configdescription = configdescription;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public Integer getDatatype() {
		return datatype;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param datatype
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setDatatype(Integer datatype) {
		this.datatype = datatype;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param value
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public String getTypekey() {
		return typekey;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param typekey
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setTypekey(String typekey) {
		this.typekey = typekey;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public String getTypename() {
		return typename;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param typename
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param description
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-14 11:18:59    
	 */
	public Integer getIshidden() {
		return ishidden;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param ishidden
	 * @return:  void 
	 * @Date :   2018-06-14 11:18:59   
	 */
	public void setIshidden(Integer ishidden) {
		this.ishidden = ishidden;
	}
	
	

}
