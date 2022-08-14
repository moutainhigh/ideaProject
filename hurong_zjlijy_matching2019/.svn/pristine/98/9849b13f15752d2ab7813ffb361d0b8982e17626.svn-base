/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 17:50:20 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IntegralTaskMining </p>
 * @author:         kongdb
 * @Date :          2019-01-07 17:50:20  
 */
@Table(name="shop_integral_task_mining")
public class IntegralTaskMining extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id 任务挖矿设置Id
	
	@Column(name= "taskName")
	private String taskName;  //任务名称
	
	@Column(name= "taskImg")
	private String taskImg;  //任务图标
	
	@Column(name= "taskKey")
	private String taskKey;  //任务KEY

	@Column(name= "miningType")
	private Integer miningType;  //类型：0系统，1后台添加
	
	@Column(name= "taskType")
	private Integer taskType;  //类型.0单次 1循环
	
	@Column(name= "dayLimit")
	private Integer dayLimit;  //单日限制
	
	@Column(name= "accumulativeTimes")
	private Integer accumulativeTimes;  //累计完成次数
	
	@Column(name= "computingCount")
	private BigDecimal computingCount;  //算力值
	
	@Column(name= "integralCount")
	private BigDecimal integralCount;  //积分值
	
	@Column(name= "additionalRewards")
	private BigDecimal additionalRewards;  //额外奖励
	
	@Column(name= "taskStatus")
	private Integer taskStatus;  //任务状态0关闭 1启用
	
	@Column(name= "accumulativeStatus")
	private Integer accumulativeStatus;  //开启累计0关闭 1启用
	
	@Column(name= "isDel")
	private Integer isDel;  //是否删除 0未删除 1删除
	
	@Column(name= "remark")
	private String remark;  //备注


	public Integer getMiningType() {
		return miningType;
	}

	public void setMiningType(Integer miningType) {
		this.miningType = miningType;
	}
	
	/**
	 * <p>id 任务挖矿设置Id</p>
	 * @author:  kongdb
	 * @return:  Long 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id 任务挖矿设置Id</p>
	 * @author:  kongdb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>任务名称</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * <p>任务名称</p>
	 * @author:  kongdb
	 * @param:   @param taskName
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	
	/**
	 * <p>任务图标</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public String getTaskImg() {
		return taskImg;
	}
	
	/**
	 * <p>任务图标</p>
	 * @author:  kongdb
	 * @param:   @param taskImg
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setTaskImg(String taskImg) {
		this.taskImg = taskImg;
	}
	
	
	/**
	 * <p>任务KEY</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public String getTaskKey() {
		return taskKey;
	}
	
	/**
	 * <p>任务KEY</p>
	 * @author:  kongdb
	 * @param:   @param taskKey
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	
	
	/**
	 * <p>类型.0单次 1循环</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public Integer getTaskType() {
		return taskType;
	}
	
	/**
	 * <p>类型.0单次 1循环</p>
	 * @author:  kongdb
	 * @param:   @param taskType
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	
	
	/**
	 * <p>单日限制</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public Integer getDayLimit() {
		return dayLimit;
	}
	
	/**
	 * <p>单日限制</p>
	 * @author:  kongdb
	 * @param:   @param dayLimit
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setDayLimit(Integer dayLimit) {
		this.dayLimit = dayLimit;
	}
	
	
	/**
	 * <p>累计完成次数</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public Integer getAccumulativeTimes() {
		return accumulativeTimes;
	}
	
	/**
	 * <p>累计完成次数</p>
	 * @author:  kongdb
	 * @param:   @param accumulativeTimes
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setAccumulativeTimes(Integer accumulativeTimes) {
		this.accumulativeTimes = accumulativeTimes;
	}
	
	
	/**
	 * <p>算力值</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public BigDecimal getComputingCount() {
		return computingCount;
	}
	
	/**
	 * <p>算力值</p>
	 * @author:  kongdb
	 * @param:   @param computingCount
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setComputingCount(BigDecimal computingCount) {
		this.computingCount = computingCount;
	}
	
	
	/**
	 * <p>积分值</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public BigDecimal getIntegralCount() {
		return integralCount;
	}
	
	/**
	 * <p>积分值</p>
	 * @author:  kongdb
	 * @param:   @param integralCount
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setIntegralCount(BigDecimal integralCount) {
		this.integralCount = integralCount;
	}
	
	
	/**
	 * <p>额外奖励</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public BigDecimal getAdditionalRewards() {
		return additionalRewards;
	}
	
	/**
	 * <p>额外奖励</p>
	 * @author:  kongdb
	 * @param:   @param additionalRewards
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setAdditionalRewards(BigDecimal additionalRewards) {
		this.additionalRewards = additionalRewards;
	}
	
	
	/**
	 * <p>任务状态0关闭 1启用</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public Integer getTaskStatus() {
		return taskStatus;
	}
	
	/**
	 * <p>任务状态0关闭 1启用</p>
	 * @author:  kongdb
	 * @param:   @param taskStatus
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
	/**
	 * <p>开启累计0关闭 1启用</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public Integer getAccumulativeStatus() {
		return accumulativeStatus;
	}
	
	/**
	 * <p>开启累计0关闭 1启用</p>
	 * @author:  kongdb
	 * @param:   @param accumulativeStatus
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setAccumulativeStatus(Integer accumulativeStatus) {
		this.accumulativeStatus = accumulativeStatus;
	}
	
	
	/**
	 * <p>是否删除 0未删除 1删除</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public Integer getIsDel() {
		return isDel;
	}
	
	/**
	 * <p>是否删除 0未删除 1删除</p>
	 * @author:  kongdb
	 * @param:   @param isDel
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 17:50:20    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  kongdb
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-01-07 17:50:20   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
