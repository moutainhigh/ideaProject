/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-15 14:53:32 
 */
package hry.licqb.record.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> TaskLog </p>
 * @author:         zhouming
 * @Date :          2019-08-15 14:53:32  
 */
@Table(name="lc_task_log")
public class TaskLog extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "functionName")
	private String functionName;  //方法名称
	
	@Column(name= "isException")
	private Integer isException;  //是否出现异常 0否 1是
	
	@Column(name= "ipaddress")
	private String ipaddress;  //ip地址
	
	@Column(name= "lasttime")
	private String lasttime;  //持续时间
	
	@Column(name= "startDate")
	private Date startDate;  //开始时间
	
	@Column(name= "endDate")
	private Date endDate;  //结束时间
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-15 14:53:32    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-15 14:53:32   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>方法名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-15 14:53:32    
	 */
	public String getFunctionName() {
		return functionName;
	}
	
	/**
	 * <p>方法名称</p>
	 * @author:  zhouming
	 * @param:   @param functionName
	 * @return:  void 
	 * @Date :   2019-08-15 14:53:32   
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	
	/**
	 * <p>是否出现异常 0否 1是</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-15 14:53:32    
	 */
	public Integer getIsException() {
		return isException;
	}
	
	/**
	 * <p>是否出现异常 0否 1是</p>
	 * @author:  zhouming
	 * @param:   @param isException
	 * @return:  void 
	 * @Date :   2019-08-15 14:53:32   
	 */
	public void setIsException(Integer isException) {
		this.isException = isException;
	}
	
	
	/**
	 * <p>ip地址</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-15 14:53:32    
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	
	/**
	 * <p>ip地址</p>
	 * @author:  zhouming
	 * @param:   @param ipaddress
	 * @return:  void 
	 * @Date :   2019-08-15 14:53:32   
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
	/**
	 * <p>持续时间</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-15 14:53:32    
	 */
	public String getLasttime() {
		return lasttime;
	}
	
	/**
	 * <p>持续时间</p>
	 * @author:  zhouming
	 * @param:   @param lasttime
	 * @return:  void 
	 * @Date :   2019-08-15 14:53:32   
	 */
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	
	
	/**
	 * <p>开始时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-08-15 14:53:32    
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>开始时间</p>
	 * @author:  zhouming
	 * @param:   @param startDate
	 * @return:  void 
	 * @Date :   2019-08-15 14:53:32   
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * <p>结束时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-08-15 14:53:32    
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * <p>结束时间</p>
	 * @author:  zhouming
	 * @param:   @param endDate
	 * @return:  void 
	 * @Date :   2019-08-15 14:53:32   
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
