/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:04:18 
 */
package hry.financail.financing.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppFinancialActive </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:04:18  
 */
@Table(name="app_financial_active")
public class AppFinancialActive extends BaseModel implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //营销活动表id
	
	@Column(name= "type")
	private Integer type;  //类型   1 主流币活动    2平台币活动
	
	@Column(name= "activeType")
	private Integer activeType;  //活动类型   1 单次礼包任务    2循环礼包任务
	
	@Column(name= "giftName")
	private String giftName;  //礼包名称
	
	@Column(name= "giftType")
	private String giftType;  //任务key值
	
	@Column(name= "coinCode")
	private String coinCode;  //赠送币种
	
	@Column(name= "giftNum")
	private BigDecimal giftNum;  //赠送数量
	
	@Column(name= "maxNum")
	private BigDecimal maxNum;  //每日赠送上限
	
	@Column(name= "giftWay")
	private Integer giftWay;  //赠送方式  1固定数量  2 百分比
	
	@Column(name= "startTime")
	private Date startTime;  //开始时间
	
	@Column(name= "endTime")
	private Date endTime;  //结束时间
	
	@Column(name= "invalidDay")
	private Integer invalidDay;  //红包过期时间
	
	@Column(name= "picture")
	private String picture;  //图片
	
	@Column(name= "remark")
	private String remark;  //礼包说明
	
	@Column(name= "operator")
	private String operator;  //操作人
	
	@Column(name= "status")
	private Integer status;  //状态
	
	
	
	
	/**
	 * <p>营销活动表id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>营销活动表id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>类型   1 主流币活动    2平台币活动</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型   1 主流币活动    2平台币活动</p>
	 * @author:  jidn
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>活动类型   1 单次礼包任务    2循环礼包任务</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public Integer getActiveType() {
		return activeType;
	}
	
	/**
	 * <p>活动类型   1 单次礼包任务    2循环礼包任务</p>
	 * @author:  jidn
	 * @param:   @param activeType
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setActiveType(Integer activeType) {
		this.activeType = activeType;
	}
	
	
	/**
	 * <p>礼包名称</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public String getGiftName() {
		return giftName;
	}
	
	/**
	 * <p>礼包名称</p>
	 * @author:  jidn
	 * @param:   @param giftName
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	
	
	/**
	 * <p>任务key值</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public String getGiftType() {
		return giftType;
	}
	
	/**
	 * <p>任务key值</p>
	 * @author:  jidn
	 * @param:   @param giftType
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}
	
	
	/**
	 * <p>赠送币种</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>赠送币种</p>
	 * @author:  jidn
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>赠送数量</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public BigDecimal getGiftNum() {
		return giftNum;
	}
	
	/**
	 * <p>赠送数量</p>
	 * @author:  jidn
	 * @param:   @param giftNum
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setGiftNum(BigDecimal giftNum) {
		this.giftNum = giftNum;
	}
	
	
	/**
	 * <p>每日赠送上限</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public BigDecimal getMaxNum() {
		return maxNum;
	}
	
	/**
	 * <p>每日赠送上限</p>
	 * @author:  jidn
	 * @param:   @param maxNum
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setMaxNum(BigDecimal maxNum) {
		this.maxNum = maxNum;
	}
	
	
	/**
	 * <p>赠送方式  1固定数量  2 百分比</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public Integer getGiftWay() {
		return giftWay;
	}
	
	/**
	 * <p>赠送方式  1固定数量  2 百分比</p>
	 * @author:  jidn
	 * @param:   @param giftWay
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setGiftWay(Integer giftWay) {
		this.giftWay = giftWay;
	}
	
	
	/**
	 * <p>开始时间</p>
	 * @author:  jidn
	 * @return:  Date 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * <p>开始时间</p>
	 * @author:  jidn
	 * @param:   @param startTime
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	
	/**
	 * <p>结束时间</p>
	 * @author:  jidn
	 * @return:  Date 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>结束时间</p>
	 * @author:  jidn
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	/**
	 * <p>红包过期时间</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public Integer getInvalidDay() {
		return invalidDay;
	}
	
	/**
	 * <p>红包过期时间</p>
	 * @author:  jidn
	 * @param:   @param invalidDay
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setInvalidDay(Integer invalidDay) {
		this.invalidDay = invalidDay;
	}
	
	
	/**
	 * <p>图片</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public String getPicture() {
		return picture;
	}
	
	/**
	 * <p>图片</p>
	 * @author:  jidn
	 * @param:   @param picture
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	/**
	 * <p>礼包说明</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>礼包说明</p>
	 * @author:  jidn
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>操作人</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * <p>操作人</p>
	 * @author:  jidn
	 * @param:   @param operator
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	/**
	 * <p>状态</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:04:18    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>状态</p>
	 * @author:  jidn
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-04-03 11:04:18   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
