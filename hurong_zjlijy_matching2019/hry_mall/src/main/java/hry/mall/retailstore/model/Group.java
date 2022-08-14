/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-09 17:54:25 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> Group </p>
 * @author:         luyue
 * @Date :          2019-05-09 17:54:25  
 */
@Table(name="shop_group")
public class Group extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //订单索引id
	
	@Column(name= "activityId")
	private Long activityId;  //活动id
	
	@Column(name= "activityGoodsId")
	private Long activityGoodsId;  //活动商品id
	
	@Column(name= "goodsId")
	private Long goodsId;  //商品id
	
	@Column(name= "goodsName")
	private String goodsName;  //商品名称
	
	@Column(name= "goodsSpecId")
	private Long goodsSpecId;  //商品规格id
	
	@Column(name= "founderId")
	private Long founderId;  //团长id
	
	@Column(name= "founderName")
	private String founderName;  //团长姓名
	
	@Column(name= "count")
	private Integer count;  //成团数量
	
	@Column(name= "alreadyCount")
	private Integer alreadyCount;  //已参团数量
	
	@Column(name= "image")
	private String image;  //团长头像图片
	
	@Column(name= "status")
	private Integer status;  //团状态：0 进行中，1 成功，3失败
	
	@Column(name= "limitHour")
	private Integer limitHour;  //成团限时
	
	@Column(name= "goodsPrice")
	private BigDecimal goodsPrice;  //商品原价
	
	@Column(name= "activityPrice")
	private BigDecimal activityPrice;  //活动商品价格
	
	@Column(name= "remark")
	private String remark;  //备注信息
	
    @Transient
     private Integer unCount; //成团剩余人数
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUnCount() {
		return unCount;
	}

	public void setUnCount(Integer unCount) {
		this.unCount = unCount;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Transient
    private Date endTime;  //此团截止时间
	
	/**
	 * <p>订单索引id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>订单索引id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>活动id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Long getActivityId() {
		return activityId;
	}
	
	/**
	 * <p>活动id</p>
	 * @author:  luyue
	 * @param:   @param activityId
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	
	/**
	 * <p>活动商品id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Long getActivityGoodsId() {
		return activityGoodsId;
	}
	
	/**
	 * <p>活动商品id</p>
	 * @author:  luyue
	 * @param:   @param activityGoodsId
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setActivityGoodsId(Long activityGoodsId) {
		this.activityGoodsId = activityGoodsId;
	}
	
	
	/**
	 * <p>商品id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品id</p>
	 * @author:  luyue
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>商品名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * <p>商品名称</p>
	 * @author:  luyue
	 * @param:   @param goodsName
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	/**
	 * <p>商品规格id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Long getGoodsSpecId() {
		return goodsSpecId;
	}
	
	/**
	 * <p>商品规格id</p>
	 * @author:  luyue
	 * @param:   @param goodsSpecId
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setGoodsSpecId(Long goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}
	
	
	/**
	 * <p>团长id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Long getFounderId() {
		return founderId;
	}
	
	/**
	 * <p>团长id</p>
	 * @author:  luyue
	 * @param:   @param founderId
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setFounderId(Long founderId) {
		this.founderId = founderId;
	}
	
	
	/**
	 * <p>团长姓名</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public String getFounderName() {
		return founderName;
	}
	
	/**
	 * <p>团长姓名</p>
	 * @author:  luyue
	 * @param:   @param founderName
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}
	
	
	/**
	 * <p>成团数量</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Integer getCount() {
		return count;
	}
	
	/**
	 * <p>成团数量</p>
	 * @author:  luyue
	 * @param:   @param count
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	/**
	 * <p>已参团数量</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Integer getAlreadyCount() {
		return alreadyCount;
	}
	
	/**
	 * <p>已参团数量</p>
	 * @author:  luyue
	 * @param:   @param alreadyCount
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setAlreadyCount(Integer alreadyCount) {
		this.alreadyCount = alreadyCount;
	}
	
	
	/**
	 * <p>团长头像图片</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * <p>团长头像图片</p>
	 * @author:  luyue
	 * @param:   @param image
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	/**
	 * <p>团状态：0 进行中，1 成功，3失败</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>团状态：0 进行中，1 成功，3失败</p>
	 * @author:  luyue
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>成团限时</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public Integer getLimitHour() {
		return limitHour;
	}
	
	/**
	 * <p>成团限时</p>
	 * @author:  luyue
	 * @param:   @param limitHour
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setLimitHour(Integer limitHour) {
		this.limitHour = limitHour;
	}
	
	
	/**
	 * <p>商品原价</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	
	/**
	 * <p>商品原价</p>
	 * @author:  luyue
	 * @param:   @param goodsPrice
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	
	/**
	 * <p>活动商品价格</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-09 17:54:25    
	 */
	public BigDecimal getActivityPrice() {
		return activityPrice;
	}
	
	/**
	 * <p>活动商品价格</p>
	 * @author:  luyue
	 * @param:   @param activityPrice
	 * @return:  void 
	 * @Date :   2019-05-09 17:54:25   
	 */
	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}
	
	

}
