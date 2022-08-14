/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月21日 上午9:41:05
 */
package hry.oauth.user.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.bean.BaseModel;

/**
 * <p> TODO</p>     权限功能表
 * @author:         Liu Shilei 
 * @Date :          2015年9月21日 上午9:41:05 
 */
@Table(name = "new_app_resource")
public class AppResource extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="mkey")
	private String mkey;//自己KEY
	@Column(name="pkey")
	private String pkey;//父级KEY
	
	@Column(name="className")
	private String className; //所属的类名
	
	@Column(name="methodName")
	private String methodName;//所属方法的名称
	
	@Column(name="sysName")
	private String sysName; //系统级别名称
	
	@Column(name="isLock")
	private String isLock;//系统锁定,锁定表示其是系统初始化的不准删除与全属性编辑    0没锁  1锁定
	
	@Column(name="isDelete")
	private String isDelete;//是否删除 0没删除  1删除
	
	@Column(name="appName")
	private String appName; //应用系统名称
	
	@Column(name="name")
	private String name; //功能名称
	
	@Column(name="url")
	private String url; //菜单URL
	
	@Column(name="shiroUrl")
	private String shiroUrl;//权限RUL	
	
	@Column(name="type")
	private String type;//xml类型
	




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getMkey() {
		return mkey;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setMkey(String mkey) {
		this.mkey = mkey;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getPkey() {
		return pkey;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getAppName() {
		return appName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getName() {
		return name;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUrl() {
		return url;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getShiroUrl() {
		return shiroUrl;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setShiroUrl(String shiroUrl) {
		this.shiroUrl = shiroUrl;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getType() {
		return type;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getClassName() {
		return className;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getMethodName() {
		return methodName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSysName() {
		return sysName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getIsLock() {
		return isLock;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getIsDelete() {
		return isDelete;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	
	
	
	
}
