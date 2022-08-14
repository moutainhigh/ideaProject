/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月10日  18:41:55
 */
package hry.manage.remote.model;


import hry.bean.BaseModel;

/**
 * 
 * <p> TODO</p>
 * @author:         Shangxl 
 * @Date :          2017年7月13日 上午10:41:46
 */
public class AppConfig  extends BaseModel {
	/**  
	 * @Fields : TODO   
	 */
	private static final long serialVersionUID = 1L;
	private String configkey;
	private String value;
	public String getConfigkey() {
		return configkey;
	}
	public void setConfigkey(String configkey) {
		this.configkey = configkey;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


}
