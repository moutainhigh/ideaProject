/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0 
 * @Date:        2017-07-28 15:16:24 
 */
package hry.manage.remote.model;

import hry.bean.BaseModel;

/**
 * <p> api接口调用日志 </p>
 * @author:         shangxl
 * @Date :          2017-07-28 15:16:24  
 */
public class AppLogThirdInterfaceDTO extends BaseModel {
	/**  
	 * @Fields : TODO   
	 */
	private static final long serialVersionUID = 1L;

	private Long id;  //id
	
	private String ip;  //ip
	
	private String url;  //url
	
	private String account;  //account
	
	private String params;  //params
	
	private String result;  //result
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  shangxl
	 * @return:  Long 
	 * @Date :   2017-07-28 15:16:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  shangxl
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-07-28 15:16:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>url</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-07-28 15:16:24    
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * <p>url</p>
	 * @author:  shangxl
	 * @param:   @param url
	 * @return:  void 
	 * @Date :   2017-07-28 15:16:24   
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * <p>params</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-07-28 15:16:24    
	 */
	public String getParams() {
		return params;
	}
	
	/**
	 * <p>params</p>
	 * @author:  shangxl
	 * @param:   @param params
	 * @return:  void 
	 * @Date :   2017-07-28 15:16:24   
	 */
	public void setParams(String params) {
		this.params = params;
	}
	
	
	/**
	 * <p>result</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-07-28 15:16:24    
	 */
	public String getResult() {
		return result;
	}
	
	/**
	 * <p>result</p>
	 * @author:  shangxl
	 * @param:   @param result
	 * @return:  void 
	 * @Date :   2017-07-28 15:16:24   
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	

}
