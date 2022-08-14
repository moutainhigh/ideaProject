/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Yuan Zhicheng
 * @version:      V1.0 
 * @Date:        2015年9月16日 上午11:04:39
 */
package hry.manage.remote.model;

import java.io.Serializable;

/**
 * 封装json结果集
 * @author CHINA_LSL
 *
 */
public class RemoteResult implements Serializable{

	private Boolean success = false;// 返回是否成功
	private String msg = "";// 返回信息
	private Object obj = null;// 返回其他对象信息
	private String code = "";//提示代码

	public Boolean getSuccess() {
		return success;
	}

	public RemoteResult setSuccess(Boolean success) {
		this.success = success;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public RemoteResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getObj() {
		return obj;
	}

	public RemoteResult setObj(Object obj) {
		this.obj = obj;
		return this;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCode() {
		return code;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public RemoteResult setCode(String code) {
		this.code = code;
		return this;
	}
	
	
}
