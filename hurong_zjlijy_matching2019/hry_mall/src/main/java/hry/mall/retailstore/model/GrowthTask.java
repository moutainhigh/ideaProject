/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-07 11:11:08 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> GrowthTask </p>
 * @author:         luyue
 * @Date :          2019-05-07 11:11:08  
 */
@Table(name="shop_growth_task")
public class GrowthTask extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "addType")
	private Integer addType;  //类型：0系统，1数据库直接添加
	
	@Column(name= "taskName")
	private String taskName;  //任务名称
	
	@Column(name= "taskKey")
	private String taskKey;  //任务KEY
	
	@Column(name= "taskType")
	private Integer taskType;  //类型.0单次 1循环
	
	@Column(name= "count")
	private BigDecimal count;  //奖励值
	
	@Column(name= "taskStatus")
	private Integer taskStatus;  //是否启用0关闭 1启用
	
	@Column(name= "dayLimit")
	private Integer dayLimit;  //单日限制次数
	
	@Column(name= "accumulativeDays")
	private Integer accumulativeDays;  //累计完成次数
	
	@Column(name= "additionalRewards")
	private BigDecimal additionalRewards;  //额外奖励
	
	@Column(name= "accumulativeStatus")
	private Integer accumulativeStatus;  //开启累计0关闭 1启用
	
	@Column(name= "remark")
	private String remark;  //备注
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>类型：0系统，1数据库直接添加</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public Integer getAddType() {
		return addType;
	}
	
	/**
	 * <p>类型：0系统，1数据库直接添加</p>
	 * @author:  luyue
	 * @param:   @param addType
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setAddType(Integer addType) {
		this.addType = addType;
	}
	
	
	/**
	 * <p>任务名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * <p>任务名称</p>
	 * @author:  luyue
	 * @param:   @param taskName
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	
	/**
	 * <p>任务KEY</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public String getTaskKey() {
		return taskKey;
	}
	
	/**
	 * <p>任务KEY</p>
	 * @author:  luyue
	 * @param:   @param taskKey
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	
	
	/**
	 * <p>类型.0单次 1循环</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public Integer getTaskType() {
		return taskType;
	}
	
	/**
	 * <p>类型.0单次 1循环</p>
	 * @author:  luyue
	 * @param:   @param taskType
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	
	
	/**
	 * <p>奖励值</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public BigDecimal getCount() {
		return count;
	}
	
	/**
	 * <p>奖励值</p>
	 * @author:  luyue
	 * @param:   @param count
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	
	
	/**
	 * <p>是否启用0关闭 1启用</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public Integer getTaskStatus() {
		return taskStatus;
	}
	
	/**
	 * <p>是否启用0关闭 1启用</p>
	 * @author:  luyue
	 * @param:   @param taskStatus
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
	/**
	 * <p>单日限制次数</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public Integer getDayLimit() {
		return dayLimit;
	}
	
	/**
	 * <p>单日限制次数</p>
	 * @author:  luyue
	 * @param:   @param dayLimit
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setDayLimit(Integer dayLimit) {
		this.dayLimit = dayLimit;
	}
	
	
	/**
	 * <p>累计完成次数</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public Integer getAccumulativeDays() {
		return accumulativeDays;
	}
	
	/**
	 * <p>累计完成次数</p>
	 * @author:  luyue
	 * @param:   @param accumulativeDays
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setAccumulativeDays(Integer accumulativeDays) {
		this.accumulativeDays = accumulativeDays;
	}
	
	
	/**
	 * <p>额外奖励</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public BigDecimal getAdditionalRewards() {
		return additionalRewards;
	}
	
	/**
	 * <p>额外奖励</p>
	 * @author:  luyue
	 * @param:   @param additionalRewards
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setAdditionalRewards(BigDecimal additionalRewards) {
		this.additionalRewards = additionalRewards;
	}
	
	
	/**
	 * <p>开启累计0关闭 1启用</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public Integer getAccumulativeStatus() {
		return accumulativeStatus;
	}
	
	/**
	 * <p>开启累计0关闭 1启用</p>
	 * @author:  luyue
	 * @param:   @param accumulativeStatus
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setAccumulativeStatus(Integer accumulativeStatus) {
		this.accumulativeStatus = accumulativeStatus;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-07 11:11:08    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-07 11:11:08   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
