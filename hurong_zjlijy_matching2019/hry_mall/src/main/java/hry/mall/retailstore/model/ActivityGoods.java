/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:38:17 
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
 * <p> ActivityGoods </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:38:17  
 */
@Table(name="shop_activity_goods")
public class ActivityGoods extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "activityId")
	private Long activityId;  //活动id
	
	@Column(name= "goodsId")
	private Long goodsId;  //商品id
	
	@Column(name= "goodsName")
	private String goodsName;  //商品名称
	
	@Column(name= "goodsSpecId")
	private Long goodsSpecId;  //商品规格id
	
	@Column(name= "activityCount")
	private Integer activityCount;  //参加活动数量
	
	@Column(name= "activityStorage")
	private Integer activityStorage; //活动商品库存
	
	@Column(name= "limitCount")
	private Integer limitCount;  //限购数量
	
	@Column(name= "limitHour")
	private Integer limitHour;  //成团限时
	
	@Column(name= "limitPayMinute")
	private Integer limitPayMinute;  //支付实效
	
	@Column(name= "transportType")
	private Integer transportType;  //1、根据商品，2、平台包邮、3买家承担
	
	@Column(name= "transportId")
	private Long transportId;  //运费模板ID，不使用运费模板值为0
	
	@Column(name= "transportName")
	private String transportName;  //运费模板名称，不使用运费模板值为0
	
	@Column(name= "commissionType")
	private Integer commissionType;  //1、跟随系统，2、佣金折扣
	
	@Column(name= "commissionDiscount")
	private BigDecimal commissionDiscount;  //佣金折扣值
	
	@Column(name= "activityPrice")
	private BigDecimal activityPrice;  //活动商品价格
	
	@Column(name= "specGoodsPrice")
	private BigDecimal specGoodsPrice;  //规格商品价格-商品原价
	
	@Column(name= "peopleCount")
	private Integer peopleCount;  //成团人数量
	
	@Column(name= "activityType")
	private Integer activityType;  //活动类型 1、团购活动 2、抢购活动

	@Column(name= "isRobot")
	private Integer isRobot; // 是否参与机器人 0:不参与 1：参与

	@Column(name= "activityTimeId")
	private Long activityTimeId;// 活动时段表id

	public Long getActivityTimeId() {
		return activityTimeId;
	}

	public void setActivityTimeId(Long activityTimeId) {
		this.activityTimeId = activityTimeId;
	}

	public Integer getIsRobot() {
		return isRobot;
	}

	public void setIsRobot(Integer isRobot) {
		this.isRobot = isRobot;
	}
	
	
	public Integer getActivityStorage() {
		return activityStorage;
	}

	public void setActivityStorage(Integer activityStorage) {
		this.activityStorage = activityStorage;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>活动id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Long getActivityId() {
		return activityId;
	}
	
	/**
	 * <p>活动id</p>
	 * @author:  zhouming
	 * @param:   @param activityId
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	
	/**
	 * <p>商品id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品id</p>
	 * @author:  zhouming
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>商品名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * <p>商品名称</p>
	 * @author:  zhouming
	 * @param:   @param goodsName
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	/**
	 * <p>商品规格id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Long getGoodsSpecId() {
		return goodsSpecId;
	}
	
	/**
	 * <p>商品规格id</p>
	 * @author:  zhouming
	 * @param:   @param goodsSpecId
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setGoodsSpecId(Long goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}
	
	
	/**
	 * <p>参加活动数量</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Integer getActivityCount() {
		return activityCount;
	}
	
	/**
	 * <p>参加活动数量</p>
	 * @author:  zhouming
	 * @param:   @param activityCount
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}
	
	
	/**
	 * <p>限购数量</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Integer getLimitCount() {
		return limitCount;
	}
	
	/**
	 * <p>限购数量</p>
	 * @author:  zhouming
	 * @param:   @param limitCount
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}
	
	
	/**
	 * <p>成团限时</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Integer getLimitHour() {
		return limitHour;
	}
	
	/**
	 * <p>成团限时</p>
	 * @author:  zhouming
	 * @param:   @param limitHour
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setLimitHour(Integer limitHour) {
		this.limitHour = limitHour;
	}
	
	
	/**
	 * <p>支付实效</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Integer getLimitPayMinute() {
		return limitPayMinute;
	}
	
	/**
	 * <p>支付实效</p>
	 * @author:  zhouming
	 * @param:   @param limitPayMinute
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setLimitPayMinute(Integer limitPayMinute) {
		this.limitPayMinute = limitPayMinute;
	}
	
	
	/**
	 * <p>1、根据商品，2、平台包邮、3买家承担</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Integer getTransportType() {
		return transportType;
	}
	
	/**
	 * <p>1、根据商品，2、平台包邮、3买家承担</p>
	 * @author:  zhouming
	 * @param:   @param transportType
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setTransportType(Integer transportType) {
		this.transportType = transportType;
	}
	
	
	/**
	 * <p>运费模板ID，不使用运费模板值为0</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Long getTransportId() {
		return transportId;
	}
	
	/**
	 * <p>运费模板ID，不使用运费模板值为0</p>
	 * @author:  zhouming
	 * @param:   @param transportId
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}
	
	
	/**
	 * <p>运费模板名称，不使用运费模板值为0</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public String getTransportName() {
		return transportName;
	}
	
	/**
	 * <p>运费模板名称，不使用运费模板值为0</p>
	 * @author:  zhouming
	 * @param:   @param transportName
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}
	
	
	/**
	 * <p>1、跟随系统，2、佣金折扣</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Integer getCommissionType() {
		return commissionType;
	}
	
	/**
	 * <p>1、跟随系统，2、佣金折扣</p>
	 * @author:  zhouming
	 * @param:   @param commissionType
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setCommissionType(Integer commissionType) {
		this.commissionType = commissionType;
	}
	
	
	/**
	 * <p>佣金折扣值</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public BigDecimal getCommissionDiscount() {
		return commissionDiscount;
	}
	
	/**
	 * <p>佣金折扣值</p>
	 * @author:  zhouming
	 * @param:   @param commissionDiscount
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setCommissionDiscount(BigDecimal commissionDiscount) {
		this.commissionDiscount = commissionDiscount;
	}
	
	
	/**
	 * <p>活动商品价格</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public BigDecimal getActivityPrice() {
		return activityPrice;
	}
	
	/**
	 * <p>活动商品价格</p>
	 * @author:  zhouming
	 * @param:   @param activityPrice
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}
	
	
	/**
	 * <p>规格商品价格-商品原价</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public BigDecimal getSpecGoodsPrice() {
		return specGoodsPrice;
	}
	
	/**
	 * <p>规格商品价格-商品原价</p>
	 * @author:  zhouming
	 * @param:   @param specGoodsPrice
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setSpecGoodsPrice(BigDecimal specGoodsPrice) {
		this.specGoodsPrice = specGoodsPrice;
	}
	
	
	/**
	 * <p>成团人数量</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Integer getPeopleCount() {
		return peopleCount;
	}
	
	/**
	 * <p>成团人数量</p>
	 * @author:  zhouming
	 * @param:   @param peopleCount
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	
	
	/**
	 * <p>活动类型 1、团购活动 2、抢购活动</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:38:17    
	 */
	public Integer getActivityType() {
		return activityType;
	}
	
	/**
	 * <p>活动类型 1、团购活动 2、抢购活动</p>
	 * @author:  zhouming
	 * @param:   @param activityType
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:17   
	 */
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	
	

}
