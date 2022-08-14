/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-28 15:48:48 
 */
package hry.web.dictionary.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppDic </p>
 * @author:         liuchenghui
 * @Date :          2018-06-28 15:48:48  
 */
@Table(name="new_app_dic")
public class AppDic extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "mkey")
	private String mkey;  //mkey
	
	@Column(name= "pkey")
	private String pkey;  //pkey
	
	@Column(name= "name")
	private String name;  //name
	
	@Column(name= "value")
	private String value;  //value
	
	@Column(name= "type")
	private String type;  //type
	
	@Column(name= "remark1")
	private String remark1;  //remark1
	
	@Column(name= "remark2")
	private String remark2;  //remark2
	
	@Column(name= "remark3")
	private String remark3;  //remark3
	
	@Column(name= "creator")
	private String creator;  //creator
	
	@Column(name= "editor")
	private String editor;  //editor
	
	@Column(name= "saasid")
	private String saasid;  //saasid
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>mkey</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getMkey() {
		return mkey;
	}
	
	/**
	 * <p>mkey</p>
	 * @author:  liuchenghui
	 * @param:   @param mkey
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setMkey(String mkey) {
		this.mkey = mkey;
	}
	
	
	/**
	 * <p>pkey</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getPkey() {
		return pkey;
	}
	
	/**
	 * <p>pkey</p>
	 * @author:  liuchenghui
	 * @param:   @param pkey
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	
	
	/**
	 * <p>name</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>name</p>
	 * @author:  liuchenghui
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>value</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * <p>value</p>
	 * @author:  liuchenghui
	 * @param:   @param value
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	/**
	 * <p>type</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>type</p>
	 * @author:  liuchenghui
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>remark1</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getRemark1() {
		return remark1;
	}
	
	/**
	 * <p>remark1</p>
	 * @author:  liuchenghui
	 * @param:   @param remark1
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	
	/**
	 * <p>remark2</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getRemark2() {
		return remark2;
	}
	
	/**
	 * <p>remark2</p>
	 * @author:  liuchenghui
	 * @param:   @param remark2
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
	
	/**
	 * <p>remark3</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getRemark3() {
		return remark3;
	}
	
	/**
	 * <p>remark3</p>
	 * @author:  liuchenghui
	 * @param:   @param remark3
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	
	
	/**
	 * <p>creator</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * <p>creator</p>
	 * @author:  liuchenghui
	 * @param:   @param creator
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	/**
	 * <p>editor</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getEditor() {
		return editor;
	}
	
	/**
	 * <p>editor</p>
	 * @author:  liuchenghui
	 * @param:   @param editor
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	
	/**
	 * <p>saasid</p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-06-28 15:48:48    
	 */
	public String getSaasid() {
		return saasid;
	}
	
	/**
	 * <p>saasid</p>
	 * @author:  liuchenghui
	 * @param:   @param saasid
	 * @return:  void 
	 * @Date :   2018-06-28 15:48:48   
	 */
	public void setSaasid(String saasid) {
		this.saasid = saasid;
	}
	
	

}
