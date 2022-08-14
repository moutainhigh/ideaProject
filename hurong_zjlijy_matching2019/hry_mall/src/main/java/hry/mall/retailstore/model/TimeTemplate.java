/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-21 16:26:01 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> TimeTemplate </p>
 * @author:         zhouming
 * @Date :          2019-05-21 16:26:01  
 */
@Table(name="shop_time_template")
public class TimeTemplate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //索引id
	
	@Column(name= "timeName")
	private String timeName;  //时间段名称
	
	@Column(name= "startTime")
	private String startTime;  //开始时间
	
	@Column(name= "endTime")
	private String endTime;  //结束时间
	
	
	
	
	/**
	 * <p>索引id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-21 16:26:01    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>索引id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-21 16:26:01   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>时间段名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-21 16:26:01    
	 */
	public String getTimeName() {
		return timeName;
	}
	
	/**
	 * <p>时间段名称</p>
	 * @author:  zhouming
	 * @param:   @param timeName
	 * @return:  void 
	 * @Date :   2019-05-21 16:26:01   
	 */
	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}
	
	
	/**
	 * <p>开始时间</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-21 16:26:01    
	 */
	public String getStartTime() {
		return startTime;
	}
	
	/**
	 * <p>开始时间</p>
	 * @author:  zhouming
	 * @param:   @param startTime
	 * @return:  void 
	 * @Date :   2019-05-21 16:26:01   
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	
	/**
	 * <p>结束时间</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-21 16:26:01    
	 */
	public String getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>结束时间</p>
	 * @author:  zhouming
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-05-21 16:26:01   
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	

}
