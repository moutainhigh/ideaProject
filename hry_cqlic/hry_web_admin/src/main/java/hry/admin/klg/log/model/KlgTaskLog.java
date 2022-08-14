/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-06 15:30:25 
 */
package hry.admin.klg.log.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgTaskLog </p>
 * @author:         yaozhuo
 * @Date :          2019-05-06 15:30:25  
 */
@Table(name="klg_task_log")
public class KlgTaskLog extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "functionName")
	private String functionName;  //{name:'方法名称'}
	
	@Column(name= "isException")
	private Integer isException;  //{name:'是否出现异常 0否 1是'}
	
	@Column(name= "ipaddress")
	private String ipaddress;  //{name:'ip地址'}
	
	@Column(name= "lasttime")
	private String lasttime;  //{name:'持续时间'}
	
	@Column(name= "startDate")
	private Date startDate;  //{name:'开始时间'}
	
	@Column(name= "endDate")
	private Date endDate;  //{name:'结束时间'}
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-05-06 15:30:25    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-06 15:30:25   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>{name:'方法名称'}</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-06 15:30:25    
	 */
	public String getFunctionName() {
		return functionName;
	}
	
	/**
	 * <p>{name:'方法名称'}</p>
	 * @author:  yaozhuo
	 * @param:   @param functionName
	 * @return:  void 
	 * @Date :   2019-05-06 15:30:25   
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	
	/**
	 * <p>{name:'是否出现异常 0否 1是'}</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-05-06 15:30:25    
	 */
	public Integer getIsException() {
		return isException;
	}
	
	/**
	 * <p>{name:'是否出现异常 0否 1是'}</p>
	 * @author:  yaozhuo
	 * @param:   @param isException
	 * @return:  void 
	 * @Date :   2019-05-06 15:30:25   
	 */
	public void setIsException(Integer isException) {
		this.isException = isException;
	}
	
	
	/**
	 * <p>{name:'ip地址'}</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-06 15:30:25    
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	
	/**
	 * <p>{name:'ip地址'}</p>
	 * @author:  yaozhuo
	 * @param:   @param ipaddress
	 * @return:  void 
	 * @Date :   2019-05-06 15:30:25   
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
	/**
	 * <p>{name:'持续时间'}</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-06 15:30:25    
	 */
	public String getLasttime() {
		return lasttime;
	}
	
	/**
	 * <p>{name:'持续时间'}</p>
	 * @author:  yaozhuo
	 * @param:   @param lasttime
	 * @return:  void 
	 * @Date :   2019-05-06 15:30:25   
	 */
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	
	
	/**
	 * <p>{name:'开始时间'}</p>
	 * @author:  yaozhuo
	 * @return:  Date 
	 * @Date :   2019-05-06 15:30:25    
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>{name:'开始时间'}</p>
	 * @author:  yaozhuo
	 * @param:   @param startDate
	 * @return:  void 
	 * @Date :   2019-05-06 15:30:25   
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * <p>{name:'结束时间'}</p>
	 * @author:  yaozhuo
	 * @return:  Date 
	 * @Date :   2019-05-06 15:30:25    
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * <p>{name:'结束时间'}</p>
	 * @author:  yaozhuo
	 * @param:   @param endDate
	 * @return:  void 
	 * @Date :   2019-05-06 15:30:25   
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
