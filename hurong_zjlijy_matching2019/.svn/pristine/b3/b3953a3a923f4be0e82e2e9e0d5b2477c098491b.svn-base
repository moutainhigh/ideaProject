/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Yuan Zhicheng
 * @version:      V1.0 
 * @Date:        2015年9月16日 上午11:04:39
 */
package hry.core.mvc.model.log;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 记录所有Controller访问日志
 * 
 * 
 * 
 * @author Yuan Zhicheng
 *
 */
@Table(name = "app_log")
public class AppLog extends BaseModel {

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name="className")
	private String className;// 类名
	
	@Column(name="methodName")
	private String methodName;// 方法名

	@Column(name="methodFullName")
	private String methodFullName;// 方法全名
	
	@Column(name="methodCnName")
	private String methodCnName;// 中文方法名

	@Column(name="argsContent")
	private String argsContent;// 参数内容

	@Column(name="returnValue")
	private String returnValue;// 返回值
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="userName")
	private String userName;// 操作用户
	
	@Column(name="appName")
	private String appName;//应用名称
	
	@Column(name="requestUrl")
	private String requestUrl;//请求地址
	
	@Column(name="systemTime")
	private String systemTime;  //生成日志的时间
	
	
	
	


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSystemTime() {
		return systemTime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getId() {
		return id;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodFullName() {
		return methodFullName;
	}

	public void setMethodFullName(String methodFullName) {
		this.methodFullName = methodFullName;
	}

	public String getMethodCnName() {
		return methodCnName;
	}

	public void setMethodCnName(String methodCnName) {
		this.methodCnName = methodCnName;
	}

	public String getArgsContent() {
		return argsContent;
	}

	public void setArgsContent(String argsContent) {
		this.argsContent = argsContent;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRequestUrl() {
		return requestUrl;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
		
	
	

}
