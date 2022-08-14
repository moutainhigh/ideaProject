/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-03 19:58:03 
 */
package hry.social.manage.remote.model.task;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> SocialTaskItem </p>
 * @author:         javalx
 * @Date :          2019-06-03 19:58:03  
 */
@Table(name="social_task_item")
public class SocialTaskItem extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "categoryKey")
	private String categoryKey;  //任务类型key
	
	@Column(name= "itemName")
	private String itemName;  //任务项名称
	
	@Column(name= "itemSort")
	private Integer itemSort;  //排序：根据每种任务类型从1开始排序
	
	@Column(name= "states")
	private Integer states;  //状态（0：关闭，1:开启）
	
	@Column(name= "rewardWay")
	private Integer rewardWay;  //奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)
	
	@Column(name= "rewardType")
	private Integer rewardType;  //奖励类型(0:一次性奖励，1：按次数奖励，2：按时间奖励)
	
	@Column(name= "rewardNum")
	private BigDecimal rewardNum;  //每次奖励数量
	
	@Column(name= "dailyUpper")
	private BigDecimal dailyUpper;  //每天奖励次数上限
	
	@Column(name= "numberCaps")
	private Integer numberCaps;  //奖励总次数上限（-1：无限制）
	
	@Column(name= "rewardCoin")
	private String rewardCoin;  //奖励币种
	
	@Column(name= "remark")
	private String remark;  //奖励规则说明
	
	@Column(name= "aging")
	private Integer aging;  //时效数量
	
	@Column(name= "timeUnit")
	private Integer timeUnit;  //时效单位（1：日，2：周，3：月，4：年）
	
	@Column(name= "percent")
	private BigDecimal percent;  //加成百分比
	
	@Column(name= "intervalTime")
	private Integer intervalTime;  //奖励间隔(分钟，限按时间奖励时）
	
	@Column(name= "dockingType")
	private Integer dockingType;  //对接类型（0：内部，1：链接，2：接口）
	
	@Column(name= "taskLink")
	private String taskLink;  //链接地址
	
	@Column(name= "taskDesc")
	private String taskDesc;  //对接描述
	
	@Column(name= "taskMark")
	private String taskMark;  //任务标识
	
	@Column(name= "taskImg")
	private String taskImg;  //任务项图片

	@Transient
	private Integer hasFinish;  //是否完成过（0否，1是）


	public Integer getHasFinish() {
		return hasFinish;
	}

	public void setHasFinish(Integer hasFinish) {
		this.hasFinish = hasFinish;
	}

	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>任务类型key</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public String getCategoryKey() {
		return categoryKey;
	}
	
	/**
	 * <p>任务类型key</p>
	 * @author:  javalx
	 * @param:   @param categoryKey
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}
	
	
	/**
	 * <p>任务项名称</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * <p>任务项名称</p>
	 * @author:  javalx
	 * @param:   @param itemName
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	/**
	 * <p>排序：根据每种任务类型从1开始排序</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Integer getItemSort() {
		return itemSort;
	}
	
	/**
	 * <p>排序：根据每种任务类型从1开始排序</p>
	 * @author:  javalx
	 * @param:   @param itemSort
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setItemSort(Integer itemSort) {
		this.itemSort = itemSort;
	}
	
	
	/**
	 * <p>状态（0：关闭，1:开启）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>状态（0：关闭，1:开启）</p>
	 * @author:  javalx
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	/**
	 * <p>奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Integer getRewardWay() {
		return rewardWay;
	}
	
	/**
	 * <p>奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)</p>
	 * @author:  javalx
	 * @param:   @param rewardWay
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setRewardWay(Integer rewardWay) {
		this.rewardWay = rewardWay;
	}
	
	
	/**
	 * <p>奖励类型(0:一次性奖励，1：按次数奖励，2：按时间奖励)</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Integer getRewardType() {
		return rewardType;
	}
	
	/**
	 * <p>奖励类型(0:一次性奖励，1：按次数奖励，2：按时间奖励)</p>
	 * @author:  javalx
	 * @param:   @param rewardType
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	
	
	/**
	 * <p>每次奖励数量</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public BigDecimal getRewardNum() {
		return rewardNum;
	}
	
	/**
	 * <p>每次奖励数量</p>
	 * @author:  javalx
	 * @param:   @param rewardNum
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}
	
	
	/**
	 * <p>每天奖励次数上限</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public BigDecimal getDailyUpper() {
		return dailyUpper;
	}
	
	/**
	 * <p>每天奖励次数上限</p>
	 * @author:  javalx
	 * @param:   @param dailyUpper
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setDailyUpper(BigDecimal dailyUpper) {
		this.dailyUpper = dailyUpper;
	}
	
	
	/**
	 * <p>奖励总次数上限（-1：无限制）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Integer getNumberCaps() {
		return numberCaps;
	}
	
	/**
	 * <p>奖励总次数上限（-1：无限制）</p>
	 * @author:  javalx
	 * @param:   @param numberCaps
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setNumberCaps(Integer numberCaps) {
		this.numberCaps = numberCaps;
	}
	
	
	/**
	 * <p>奖励币种</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public String getRewardCoin() {
		return rewardCoin;
	}
	
	/**
	 * <p>奖励币种</p>
	 * @author:  javalx
	 * @param:   @param rewardCoin
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setRewardCoin(String rewardCoin) {
		this.rewardCoin = rewardCoin;
	}
	
	
	/**
	 * <p>奖励规则说明</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>奖励规则说明</p>
	 * @author:  javalx
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>时效数量</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Integer getAging() {
		return aging;
	}
	
	/**
	 * <p>时效数量</p>
	 * @author:  javalx
	 * @param:   @param aging
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setAging(Integer aging) {
		this.aging = aging;
	}
	
	
	/**
	 * <p>时效单位（1：日，2：周，3：月，4：年）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Integer getTimeUnit() {
		return timeUnit;
	}
	
	/**
	 * <p>时效单位（1：日，2：周，3：月，4：年）</p>
	 * @author:  javalx
	 * @param:   @param timeUnit
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setTimeUnit(Integer timeUnit) {
		this.timeUnit = timeUnit;
	}
	
	
	/**
	 * <p>加成百分比</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public BigDecimal getPercent() {
		return percent;
	}
	
	/**
	 * <p>加成百分比</p>
	 * @author:  javalx
	 * @param:   @param percent
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}
	
	
	/**
	 * <p>奖励间隔(分钟，限按时间奖励时）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Integer getIntervalTime() {
		return intervalTime;
	}
	
	/**
	 * <p>奖励间隔(分钟，限按时间奖励时）</p>
	 * @author:  javalx
	 * @param:   @param intervalTime
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setIntervalTime(Integer intervalTime) {
		this.intervalTime = intervalTime;
	}
	
	
	/**
	 * <p>对接类型（0：内部，1：链接，2：接口）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public Integer getDockingType() {
		return dockingType;
	}
	
	/**
	 * <p>对接类型（0：内部，1：链接，2：接口）</p>
	 * @author:  javalx
	 * @param:   @param dockingType
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setDockingType(Integer dockingType) {
		this.dockingType = dockingType;
	}
	
	
	/**
	 * <p>链接地址</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public String getTaskLink() {
		return taskLink;
	}
	
	/**
	 * <p>链接地址</p>
	 * @author:  javalx
	 * @param:   @param taskLink
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setTaskLink(String taskLink) {
		this.taskLink = taskLink;
	}
	
	
	/**
	 * <p>对接描述</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public String getTaskDesc() {
		return taskDesc;
	}
	
	/**
	 * <p>对接描述</p>
	 * @author:  javalx
	 * @param:   @param taskDesc
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	
	
	/**
	 * <p>任务标识</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public String getTaskMark() {
		return taskMark;
	}
	
	/**
	 * <p>任务标识</p>
	 * @author:  javalx
	 * @param:   @param taskMark
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setTaskMark(String taskMark) {
		this.taskMark = taskMark;
	}
	
	
	/**
	 * <p>任务项图片</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:58:03    
	 */
	public String getTaskImg() {
		return taskImg;
	}
	
	/**
	 * <p>任务项图片</p>
	 * @author:  javalx
	 * @param:   @param taskImg
	 * @return:  void 
	 * @Date :   2019-06-03 19:58:03   
	 */
	public void setTaskImg(String taskImg) {
		this.taskImg = taskImg;
	}
	
	

}
