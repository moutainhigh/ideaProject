/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-17 18:36:28 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> ActivityTime </p>
 * @author:         zhouming
 * @Date :          2019-05-17 18:36:28  
 */
@Table(name="shop_activity_time")
public class ActivityTime extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //索引id
	
	@Column(name= "activityId")
	private Long activityId;  //活动id
	
	@Column(name= "timeName")
	private String timeName;  //时间段名称
	
	@Column(name= "startTime")
	private String startTime;  //开始时间
	
	@Column(name= "endTime")
	private String endTime;  //结束时间

	@Column(name= "status")
	private Integer status; //'启动状态0 未启动 1已启动',

	@Column(name= "timeTempId")
	private Long timeTempId; // 时间段模板id

	public Long getTimeTempId() {
		return timeTempId;
	}

	public void setTimeTempId(Long timeTempId) {
		this.timeTempId = timeTempId;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * <p>索引id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-17 18:36:28    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>索引id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-17 18:36:28   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>活动id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-17 18:36:28    
	 */
	public Long getActivityId() {
		return activityId;
	}
	
	/**
	 * <p>活动id</p>
	 * @author:  zhouming
	 * @param:   @param activityId
	 * @return:  void 
	 * @Date :   2019-05-17 18:36:28   
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	
	/**
	 * <p>时间段名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-17 18:36:28    
	 */
	public String getTimeName() {
		return timeName;
	}
	
	/**
	 * <p>时间段名称</p>
	 * @author:  zhouming
	 * @param:   @param timeName
	 * @return:  void 
	 * @Date :   2019-05-17 18:36:28   
	 */
	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
