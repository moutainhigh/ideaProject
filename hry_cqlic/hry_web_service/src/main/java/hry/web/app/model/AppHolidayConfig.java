/**
 * 
 */
package hry.web.app.model;

import hry.core.mvc.model.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lvna
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name="app_holiday_config")
public class AppHolidayConfig extends BaseModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name; // 节日名称
	
	@Column(name="beginDate")
	private Date beginDate; // 开始时间
	 
	@Column(name="endDate")
	private Date endDate; // 结束时间
	 
	@Column(name="states")  // 状态  0表示删除   1 表示有用
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
