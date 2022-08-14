/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2022-05-19 10:31:38 
 */
package hry.admin.xzsn.task.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Task </p>
 * @author:         zhouming
 * @Date :          2022-05-19 10:31:38  
 */
@Table(name="sn_task")
public class Task extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "taskName")
	private String taskName;  //任务名称
	
	@Column(name= "taskTypeId")
	private Long taskTypeId;  //任务属性id
	
	@Column(name= "taskTypeName")
	private String taskTypeName;  //任务属性名称
	
	@Column(name= "isOpen")
	private Integer isOpen;  //是否公开 1 是，2 否
	
	@Column(name= "taskGrade")
	private Integer taskGrade;  //任务级别 1 一般，2 紧急
	
	@Column(name= "initiatorId")
	private Long initiatorId;  //发起人id
	
	@Column(name= "initiatorName")
	private String initiatorName;  //发起人姓名
	
	@Column(name= "undertakerIds")
	private String undertakerIds;  //承办人id集合（用逗号分隔）
	
	@Column(name= "undertakerNames")
	private String undertakerNames;  //承办人姓名（用逗号分隔）
	
	@Column(name= "copyIds")
	private String copyIds;  //抄送人id集合（用逗号分隔）
	
	@Column(name= "copyNames")
	private String copyNames;  //抄送人姓名集合（用逗号分隔）
	
	@Column(name= "taskStartDate")
	private Date taskStartDate;  //任务计划开始时间
	
	@Column(name= "taskEndDate")
	private Date taskEndDate;  //任务计划结束时间
	
	@Column(name= "taskContent")
	private String taskContent;  //任务内容
	
	@Column(name= "taskAffix")
	private String taskAffix;  //任务附件
	
	@Column(name= "taskPlanContent")
	private String taskPlanContent;  //任务完成进度内容
	
	@Column(name= "taskPlanAffix")
	private String taskPlanAffix;  //任务完成进度附件
	
	@Column(name= "taskState")
	private Integer taskState;  //任务状态 0 未分配 1 待办 2 承办 3 已办（未确认）4 任务结束（已确认）
	
	@Column(name= "isDel")
	private Integer isDel;  //是否删除 0 否 1 是
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>任务名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * <p>任务名称</p>
	 * @author:  zhouming
	 * @param:   @param taskName
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	
	/**
	 * <p>任务属性id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public Long getTaskTypeId() {
		return taskTypeId;
	}
	
	/**
	 * <p>任务属性id</p>
	 * @author:  zhouming
	 * @param:   @param taskTypeId
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskTypeId(Long taskTypeId) {
		this.taskTypeId = taskTypeId;
	}
	
	
	/**
	 * <p>任务属性名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getTaskTypeName() {
		return taskTypeName;
	}
	
	/**
	 * <p>任务属性名称</p>
	 * @author:  zhouming
	 * @param:   @param taskTypeName
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}
	
	
	/**
	 * <p>是否公开 1 是，2 否</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public Integer getIsOpen() {
		return isOpen;
	}
	
	/**
	 * <p>是否公开 1 是，2 否</p>
	 * @author:  zhouming
	 * @param:   @param isOpen
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	
	
	/**
	 * <p>任务级别 1 一般，2 紧急</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public Integer getTaskGrade() {
		return taskGrade;
	}
	
	/**
	 * <p>任务级别 1 一般，2 紧急</p>
	 * @author:  zhouming
	 * @param:   @param taskGrade
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskGrade(Integer taskGrade) {
		this.taskGrade = taskGrade;
	}
	
	
	/**
	 * <p>发起人id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public Long getInitiatorId() {
		return initiatorId;
	}
	
	/**
	 * <p>发起人id</p>
	 * @author:  zhouming
	 * @param:   @param initiatorId
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setInitiatorId(Long initiatorId) {
		this.initiatorId = initiatorId;
	}
	
	
	/**
	 * <p>发起人姓名</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getInitiatorName() {
		return initiatorName;
	}
	
	/**
	 * <p>发起人姓名</p>
	 * @author:  zhouming
	 * @param:   @param initiatorName
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}
	
	
	/**
	 * <p>承办人id集合（用逗号分隔）</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getUndertakerIds() {
		return undertakerIds;
	}
	
	/**
	 * <p>承办人id集合（用逗号分隔）</p>
	 * @author:  zhouming
	 * @param:   @param undertakerIds
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setUndertakerIds(String undertakerIds) {
		this.undertakerIds = undertakerIds;
	}
	
	
	/**
	 * <p>承办人姓名（用逗号分隔）</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getUndertakerNames() {
		return undertakerNames;
	}
	
	/**
	 * <p>承办人姓名（用逗号分隔）</p>
	 * @author:  zhouming
	 * @param:   @param undertakerNames
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setUndertakerNames(String undertakerNames) {
		this.undertakerNames = undertakerNames;
	}
	
	
	/**
	 * <p>抄送人id集合（用逗号分隔）</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getCopyIds() {
		return copyIds;
	}
	
	/**
	 * <p>抄送人id集合（用逗号分隔）</p>
	 * @author:  zhouming
	 * @param:   @param copyIds
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setCopyIds(String copyIds) {
		this.copyIds = copyIds;
	}
	
	
	/**
	 * <p>抄送人姓名集合（用逗号分隔）</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getCopyNames() {
		return copyNames;
	}
	
	/**
	 * <p>抄送人姓名集合（用逗号分隔）</p>
	 * @author:  zhouming
	 * @param:   @param copyNames
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setCopyNames(String copyNames) {
		this.copyNames = copyNames;
	}
	
	
	/**
	 * <p>任务计划开始时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public Date getTaskStartDate() {
		return taskStartDate;
	}
	
	/**
	 * <p>任务计划开始时间</p>
	 * @author:  zhouming
	 * @param:   @param taskStartDate
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}
	
	
	/**
	 * <p>任务计划结束时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public Date getTaskEndDate() {
		return taskEndDate;
	}
	
	/**
	 * <p>任务计划结束时间</p>
	 * @author:  zhouming
	 * @param:   @param taskEndDate
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
	
	
	/**
	 * <p>任务内容</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getTaskContent() {
		return taskContent;
	}
	
	/**
	 * <p>任务内容</p>
	 * @author:  zhouming
	 * @param:   @param taskContent
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	
	
	/**
	 * <p>任务附件</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getTaskAffix() {
		return taskAffix;
	}
	
	/**
	 * <p>任务附件</p>
	 * @author:  zhouming
	 * @param:   @param taskAffix
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskAffix(String taskAffix) {
		this.taskAffix = taskAffix;
	}
	
	
	/**
	 * <p>任务完成进度内容</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getTaskPlanContent() {
		return taskPlanContent;
	}
	
	/**
	 * <p>任务完成进度内容</p>
	 * @author:  zhouming
	 * @param:   @param taskPlanContent
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskPlanContent(String taskPlanContent) {
		this.taskPlanContent = taskPlanContent;
	}
	
	
	/**
	 * <p>任务完成进度附件</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public String getTaskPlanAffix() {
		return taskPlanAffix;
	}
	
	/**
	 * <p>任务完成进度附件</p>
	 * @author:  zhouming
	 * @param:   @param taskPlanAffix
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskPlanAffix(String taskPlanAffix) {
		this.taskPlanAffix = taskPlanAffix;
	}
	
	
	/**
	 * <p>任务状态 0 未分配 1 待办 2 承办 3 已办（未确认）4 任务结束（已确认）</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public Integer getTaskState() {
		return taskState;
	}
	
	/**
	 * <p>任务状态 0 未分配 1 待办 2 承办 3 已办（未确认）4 任务结束（已确认）</p>
	 * @author:  zhouming
	 * @param:   @param taskState
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setTaskState(Integer taskState) {
		this.taskState = taskState;
	}
	
	
	/**
	 * <p>是否删除 0 否 1 是</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2022-05-19 10:31:38    
	 */
	public Integer getIsDel() {
		return isDel;
	}
	
	/**
	 * <p>是否删除 0 否 1 是</p>
	 * @author:  zhouming
	 * @param:   @param isDel
	 * @return:  void 
	 * @Date :   2022-05-19 10:31:38   
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	

}
