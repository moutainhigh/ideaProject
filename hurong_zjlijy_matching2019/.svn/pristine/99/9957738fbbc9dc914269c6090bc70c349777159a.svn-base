/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:04:51 
 */
package hry.cm4.log.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm4TaskLog </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:04:51  
 */
@Table(name="cm4_task_log")
public class Cm4TaskLog extends BaseModel {
	
	
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
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:04:51    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-21 10:04:51   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>{name:'方法名称'}</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:04:51    
	 */
	public String getFunctionName() {
		return functionName;
	}
	
	/**
	 * <p>{name:'方法名称'}</p>
	 * @author:  yaozh
	 * @param:   @param functionName
	 * @return:  void 
	 * @Date :   2019-11-21 10:04:51   
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	
	/**
	 * <p>{name:'是否出现异常 0否 1是'}</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-11-21 10:04:51    
	 */
	public Integer getIsException() {
		return isException;
	}
	
	/**
	 * <p>{name:'是否出现异常 0否 1是'}</p>
	 * @author:  yaozh
	 * @param:   @param isException
	 * @return:  void 
	 * @Date :   2019-11-21 10:04:51   
	 */
	public void setIsException(Integer isException) {
		this.isException = isException;
	}
	
	
	/**
	 * <p>{name:'ip地址'}</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:04:51    
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	
	/**
	 * <p>{name:'ip地址'}</p>
	 * @author:  yaozh
	 * @param:   @param ipaddress
	 * @return:  void 
	 * @Date :   2019-11-21 10:04:51   
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
	/**
	 * <p>{name:'持续时间'}</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:04:51    
	 */
	public String getLasttime() {
		return lasttime;
	}
	
	/**
	 * <p>{name:'持续时间'}</p>
	 * @author:  yaozh
	 * @param:   @param lasttime
	 * @return:  void 
	 * @Date :   2019-11-21 10:04:51   
	 */
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	
	
	/**
	 * <p>{name:'开始时间'}</p>
	 * @author:  yaozh
	 * @return:  Date 
	 * @Date :   2019-11-21 10:04:51    
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>{name:'开始时间'}</p>
	 * @author:  yaozh
	 * @param:   @param startDate
	 * @return:  void 
	 * @Date :   2019-11-21 10:04:51   
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * <p>{name:'结束时间'}</p>
	 * @author:  yaozh
	 * @return:  Date 
	 * @Date :   2019-11-21 10:04:51    
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * <p>{name:'结束时间'}</p>
	 * @author:  yaozh
	 * @param:   @param endDate
	 * @return:  void 
	 * @Date :   2019-11-21 10:04:51   
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
