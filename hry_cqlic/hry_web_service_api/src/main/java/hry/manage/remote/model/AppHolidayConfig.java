/**
 * 
 */
package hry.manage.remote.model;

import hry.bean.BaseModel;

import java.util.Date;

/**
 * @author lvna
 *
 */

public class AppHolidayConfig extends BaseModel {

	/**  
	 * @Fields : TODO   
	 */
	private static final long serialVersionUID = 2011916866153525663L;

	private Long id;
	
	private String name; // 节日名称
	
	private Date beginDate; // 开始时间
	 
	private Date endDate; // 结束时间
	 
	private Integer states;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	

}
