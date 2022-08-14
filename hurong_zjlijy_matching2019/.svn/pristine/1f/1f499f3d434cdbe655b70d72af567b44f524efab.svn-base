/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:05:01 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Activity </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:05:01  
 */
@Table(name="shop_activity")
public class Activity extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "name")
	private String name;  //活动名称
	
	@Column(name= "number")
	private String number;  //活动编号
	
	@Column(name= "startTime")
	private Date startTime;  //开始时间
	
	@Column(name= "endTime")
	private Date endTime;  //结束时间
	
	@Column(name= "images")
	private String images;  //首页轮播图
	
	@Column(name= "remark")
	private String remark;  //活动规则
	
	@Column(name= "type")
	private Integer type;  //1拼团、2抢购
	
	@Column(name= "goodsKindCount")
	private Integer goodsKindCount;  //参加活动商品种类数量


	@Column(name = "status")
	private Integer status; // 活动是否启动 0未启动、1已启动

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:05:01    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-09 17:05:01   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>活动名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-09 17:05:01    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>活动名称</p>
	 * @author:  zhouming
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-05-09 17:05:01   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>活动编号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-09 17:05:01    
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * <p>活动编号</p>
	 * @author:  zhouming
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-05-09 17:05:01   
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	/**
	 * <p>开始时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-05-09 17:05:01    
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * <p>开始时间</p>
	 * @author:  zhouming
	 * @param:   @param startTime
	 * @return:  void 
	 * @Date :   2019-05-09 17:05:01   
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	
	/**
	 * <p>结束时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-05-09 17:05:01    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>结束时间</p>
	 * @author:  zhouming
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-05-09 17:05:01   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	/**
	 * <p>首页轮播图</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-09 17:05:01    
	 */
	public String getImages() {
		return images;
	}
	
	/**
	 * <p>首页轮播图</p>
	 * @author:  zhouming
	 * @param:   @param images
	 * @return:  void 
	 * @Date :   2019-05-09 17:05:01   
	 */
	public void setImages(String images) {
		this.images = images;
	}
	
	
	/**
	 * <p>活动规则</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-09 17:05:01    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>活动规则</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-09 17:05:01   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>1拼团、2抢购</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:05:01    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>1拼团、2抢购</p>
	 * @author:  zhouming
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-05-09 17:05:01   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>参加活动商品种类数量</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:05:01    
	 */
	public Integer getGoodsKindCount() {
		return goodsKindCount;
	}
	
	/**
	 * <p>参加活动商品种类数量</p>
	 * @author:  zhouming
	 * @param:   @param goodsKindCount
	 * @return:  void 
	 * @Date :   2019-05-09 17:05:01   
	 */
	public void setGoodsKindCount(Integer goodsKindCount) {
		this.goodsKindCount = goodsKindCount;
	}
	
	

}
