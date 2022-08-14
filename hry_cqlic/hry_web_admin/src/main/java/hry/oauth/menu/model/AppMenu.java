/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-06-01 19:44:41 
 */
package hry.oauth.menu.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.bean.BaseModel;

/**
 * <p> AppMenu </p>
 * @author:         liushilei
 * @Date :          2017-06-01 19:44:41  
 */
@Table(name="new_app_menu")
public class AppMenu extends BaseModel {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "name")
	private String name;  //name
	
	@Column(name= "shirourl")
	private String shirourl;  //shiroUrl
	
	@Column(name= "url")
	private String url;  //url
	
	@Column(name= "orderno")
	private Integer orderno;  //orderNo
	
	@Column(name= "appname")
	private String appname;  //appName
	
	@Column(name= "created")
	private Date created;  //created
	
	@Column(name= "modified")
	private Date modified;  //modified
	
	@Column(name= "mkey")
	private String mkey;  //mkey
	
	@Column(name= "pkey")
	private String pkey;  //pkey
	
	@Column(name= "type")
	private String type;  //type

	@Column(name= "icon")
	private String icon;  //type

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShirourl() {
		return shirourl;
	}

	public void setShirourl(String shirourl) {
		this.shirourl = shirourl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getMkey() {
		return mkey;
	}

	public void setMkey(String mkey) {
		this.mkey = mkey;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
