/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-20 10:19:20 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppLogThirdInterface </p>
 * @author:         tianpengyu
 * @Date :          2018-07-20 10:19:20  
 */
@Table(name="app_log_thirdInterface")
public class AppLogThirdInterface extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "ip")
	private String ip;  //
	
	@Column(name= "url")
	private String url;  //
	
	@Column(name= "account")
	private String account;  //
	
	@Column(name= "params")
	private String params;  //
	
	@Column(name= "result")
	private String result;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-20 10:19:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-20 10:19:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 10:19:20    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-20 10:19:20   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 10:19:20    
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param ip
	 * @return:  void 
	 * @Date :   2018-07-20 10:19:20   
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 10:19:20    
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param url
	 * @return:  void 
	 * @Date :   2018-07-20 10:19:20   
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 10:19:20    
	 */
	public String getAccount() {
		return account;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param account
	 * @return:  void 
	 * @Date :   2018-07-20 10:19:20   
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 10:19:20    
	 */
	public String getParams() {
		return params;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param params
	 * @return:  void 
	 * @Date :   2018-07-20 10:19:20   
	 */
	public void setParams(String params) {
		this.params = params;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 10:19:20    
	 */
	public String getResult() {
		return result;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param result
	 * @return:  void 
	 * @Date :   2018-07-20 10:19:20   
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	

}
