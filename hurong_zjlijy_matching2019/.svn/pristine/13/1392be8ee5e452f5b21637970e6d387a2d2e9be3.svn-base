/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-07-31 11:49:55 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> Advertisement </p>
 * @author:         luyue
 * @Date :          2019-07-31 11:49:55  
 */
@Table(name="shop_advertisement")
public class Advertisement extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "name")
	private String name;  //广告名称
	
	@Column(name= "remark")
	private String remark;  //描述
	
	@Column(name= "imageurl")
	private String imageurl;  //广告图片url
	
	@Column(name= "startTime")
	private Date startTime;  //投放开始时间
	
	@Column(name= "endTime")
	private Date endTime;  //投放结束时间
	
	@Column(name= "advertisurl")
	private String advertisurl;  //广告链接
	
	@Column(name= "putName")
	private String putName;  //投放人姓名
	
	@Column(name= "putMobile")
	private String putMobile;  //联系方式
	
	@Column(name= "clickCount")
	private BigDecimal clickCount;  //点击奖励
	
	@Column(name= "browseCount")
	private BigDecimal browseCount;  //浏览奖励
	
	@Column(name= "coinCode")
	private String coinCode;  //虚拟币币种
	
	@Column(name= "browseTime")
	private Integer browseTime;  //浏览时长
	
	@Column(name= "status")
	private Integer status;  //是否有效，1是0否
	
	 @Transient
	private BigDecimal hcCount;  //已获得点击奖励
	
	 @Transient
	private BigDecimal hbCount;  //已获得浏览奖励
	 
	 @Transient
	 private Short isClick;//是否已点击
	
	 @Transient
	 private Short isBrowse;//是否已浏览
	
	
	public BigDecimal getHcCount() {
		return hcCount;
	}

	public void setHcCount(BigDecimal hcCount) {
		this.hcCount = hcCount;
	}

	public BigDecimal getHbCount() {
		return hbCount;
	}

	public void setHbCount(BigDecimal hbCount) {
		this.hbCount = hbCount;
	}

	public Short getIsClick() {
		return isClick;
	}

	public void setIsClick(Short isClick) {
		this.isClick = isClick;
	}

	public Short getIsBrowse() {
		return isBrowse;
	}

	public void setIsBrowse(Short isBrowse) {
		this.isBrowse = isBrowse;
	}

	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>广告名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>广告名称</p>
	 * @author:  luyue
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>描述</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>描述</p>
	 * @author:  luyue
	 * @param:   @param describe
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>广告图片url</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public String getImageurl() {
		return imageurl;
	}
	
	/**
	 * <p>广告图片url</p>
	 * @author:  luyue
	 * @param:   @param imageurl
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	
	/**
	 * <p>投放开始时间</p>
	 * @author:  luyue
	 * @return:  Date 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * <p>投放开始时间</p>
	 * @author:  luyue
	 * @param:   @param startTime
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	
	/**
	 * <p>投放结束时间</p>
	 * @author:  luyue
	 * @return:  Date 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>投放结束时间</p>
	 * @author:  luyue
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	/**
	 * <p>广告链接</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public String getAdvertisurl() {
		return advertisurl;
	}
	
	/**
	 * <p>广告链接</p>
	 * @author:  luyue
	 * @param:   @param advertisurl
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setAdvertisurl(String advertisurl) {
		this.advertisurl = advertisurl;
	}
	
	
	/**
	 * <p>投放人姓名</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public String getPutName() {
		return putName;
	}
	
	/**
	 * <p>投放人姓名</p>
	 * @author:  luyue
	 * @param:   @param putName
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setPutName(String putName) {
		this.putName = putName;
	}
	
	
	/**
	 * <p>联系方式</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public String getPutMobile() {
		return putMobile;
	}
	
	/**
	 * <p>联系方式</p>
	 * @author:  luyue
	 * @param:   @param putMobile
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setPutMobile(String putMobile) {
		this.putMobile = putMobile;
	}
	
	
	/**
	 * <p>点击奖励</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public BigDecimal getClickCount() {
		return clickCount;
	}
	
	/**
	 * <p>点击奖励</p>
	 * @author:  luyue
	 * @param:   @param clickCount
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setClickCount(BigDecimal clickCount) {
		this.clickCount = clickCount;
	}
	
	
	/**
	 * <p>浏览奖励</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public BigDecimal getBrowseCount() {
		return browseCount;
	}
	
	/**
	 * <p>浏览奖励</p>
	 * @author:  luyue
	 * @param:   @param browseCount
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setBrowseCount(BigDecimal browseCount) {
		this.browseCount = browseCount;
	}
	
	
	/**
	 * <p>虚拟币币种</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>虚拟币币种</p>
	 * @author:  luyue
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>浏览时长</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public Integer getBrowseTime() {
		return browseTime;
	}
	
	/**
	 * <p>浏览时长</p>
	 * @author:  luyue
	 * @param:   @param browseTime
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setBrowseTime(Integer browseTime) {
		this.browseTime = browseTime;
	}
	
	
	/**
	 * <p>是否有效，1是0否</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-07-31 11:49:55    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>是否有效，1是0否</p>
	 * @author:  luyue
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-07-31 11:49:55   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
