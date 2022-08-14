/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月3日 下午3:06:14
 */
package hry.exchange.kline.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月3日 下午3:06:14 
 */
@Table(name = "SystemRunTime")
public class SystemRunTime extends BaseModel{
	
	@Id
	@Column(name="id")
	private String id ;
	
	@Column(name="systemName")
	private String systemName;
	
	@Column(name="runTime")
	private String runTime;

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

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSystemName() {
		return systemName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRunTime() {
		return runTime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	
	
	
	
}
