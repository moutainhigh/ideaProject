/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-24 18:39:11 
 */
package hry.mall.retailstore.model;
import hry.bean.BaseModel;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> Coupon </p>
 * @author:         zhouming
 * @Date :          2019-05-24 18:39:11  
 */
@Table(name="shop_coupon")
public class Coupon extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "type")
	private String type;  //优惠券类型 1、手动领取 2、注册赠券 3、拉新赠券 4、邀请开店 5、新人专享
	
	@Column(name= "name")
	private String name;  //优惠券名称
	
	@Column(name= "totalCount")
	private Integer totalCount;  //总发行量
	
	@Column(name= "receiveCount")
	private Integer receiveCount;  //已领取量
	
	@Column(name= "usedCount")
	private Integer usedCount;  //已使用量
	
	@Column(name= "faceValue")
	private BigDecimal faceValue;  //面额
	
	@Column(name= "limitCount")
	private Integer limitCount;  //每人限领
	
	@Column(name= "useMoney")
	private BigDecimal useMoney;  //满多少元可用
	
	@Column(name= "days")
	private Integer days;  //固定天数
	
	@Column(name= "validType")
	private Integer validType;  //1日期范围，2固定天数
	
	@Column(name= "startDate")
	private Date startDate;  //开始日期
	
	@Column(name= "endDate")
	private Date endDate;  //结束日期
	
	@Column(name= "useType")
	private Integer useType;  //1、全场通用，2、指定商品，3指定分类
	
	@Column(name= "onegcId")
	private Long onegcId;  //商品一级分类id
	
	@Column(name= "onegcName")
	private String onegcName;  //商品一级分类名称
	
	@Column(name= "twogcId")
	private Long twogcId;  //商品二级分类id
	
	@Column(name= "twogcName")
	private String twogcName;  //商品二级分类名称
	
	@Column(name= "gcType")
	private Integer gcType;  //分类级别，1为一级分类，2为二级分类
	
	@Column(name= "remark")
	private String remark;  //活动规则

	@Column(name= "status")
	private Integer status; // 是否开启关闭 0 关闭 1 开启

	@Column(name= "isDel")
	private Integer isDel; // 是否删除 0 否 1 是

	@Transient
	private Integer isGet; // 是否领取 0 否 1 是

	public Integer getIsGet() {
		return isGet;
	}

	public void setIsGet(Integer isGet) {
		this.isGet = isGet;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>优惠券类型</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>优惠券类型</p>
	 * @author:  zhouming
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>优惠券名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>优惠券名称</p>
	 * @author:  zhouming
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>总发行量</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	
	/**
	 * <p>总发行量</p>
	 * @author:  zhouming
	 * @param:   @param totalCount
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	
	/**
	 * <p>已领取量</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Integer getReceiveCount() {
		return receiveCount;
	}
	
	/**
	 * <p>已领取量</p>
	 * @author:  zhouming
	 * @param:   @param receiveCount
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setReceiveCount(Integer receiveCount) {
		this.receiveCount = receiveCount;
	}
	
	
	/**
	 * <p>已使用量</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Integer getUsedCount() {
		return usedCount;
	}
	
	/**
	 * <p>已使用量</p>
	 * @author:  zhouming
	 * @param:   @param usedCount
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	
	
	/**
	 * <p>面额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public BigDecimal getFaceValue() {
		return faceValue;
	}
	
	/**
	 * <p>面额</p>
	 * @author:  zhouming
	 * @param:   @param faceValue
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}
	
	
	/**
	 * <p>每人限领</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Integer getLimitCount() {
		return limitCount;
	}
	
	/**
	 * <p>每人限领</p>
	 * @author:  zhouming
	 * @param:   @param limitCount
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}
	
	
	/**
	 * <p>满多少元可用</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public BigDecimal getUseMoney() {
		return useMoney;
	}
	
	/**
	 * <p>满多少元可用</p>
	 * @author:  zhouming
	 * @param:   @param useMoney
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	
	
	/**
	 * <p>固定天数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Integer getDays() {
		return days;
	}
	
	/**
	 * <p>固定天数</p>
	 * @author:  zhouming
	 * @param:   @param days
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setDays(Integer days) {
		this.days = days;
	}
	
	
	/**
	 * <p>1日期范围，2固定天数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Integer getValidType() {
		return validType;
	}
	
	/**
	 * <p>1日期范围，2固定天数</p>
	 * @author:  zhouming
	 * @param:   @param validType
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setValidType(Integer validType) {
		this.validType = validType;
	}
	
	
	/**
	 * <p>开始日期</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>开始日期</p>
	 * @author:  zhouming
	 * @param:   @param startDate
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * <p>结束日期</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * <p>结束日期</p>
	 * @author:  zhouming
	 * @param:   @param endDate
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	/**
	 * <p>1、全场通用，2、指定商品，3指定分类</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Integer getUseType() {
		return useType;
	}
	
	/**
	 * <p>1、全场通用，2、指定商品，3指定分类</p>
	 * @author:  zhouming
	 * @param:   @param useType
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	
	
	/**
	 * <p>商品一级分类id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Long getOnegcId() {
		return onegcId;
	}
	
	/**
	 * <p>商品一级分类id</p>
	 * @author:  zhouming
	 * @param:   @param onegcId
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setOnegcId(Long onegcId) {
		this.onegcId = onegcId;
	}
	
	
	/**
	 * <p>商品一级分类名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public String getOnegcName() {
		return onegcName;
	}
	
	/**
	 * <p>商品一级分类名称</p>
	 * @author:  zhouming
	 * @param:   @param onegcName
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setOnegcName(String onegcName) {
		this.onegcName = onegcName;
	}
	
	
	/**
	 * <p>商品二级分类id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Long getTwogcId() {
		return twogcId;
	}
	
	/**
	 * <p>商品二级分类id</p>
	 * @author:  zhouming
	 * @param:   @param twogcId
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setTwogcId(Long twogcId) {
		this.twogcId = twogcId;
	}
	
	
	/**
	 * <p>商品二级分类名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public String getTwogcName() {
		return twogcName;
	}
	
	/**
	 * <p>商品二级分类名称</p>
	 * @author:  zhouming
	 * @param:   @param twogcName
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setTwogcName(String twogcName) {
		this.twogcName = twogcName;
	}
	
	
	/**
	 * <p>分类级别，1为一级分类，2为二级分类</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public Integer getGcType() {
		return gcType;
	}
	
	/**
	 * <p>分类级别，1为一级分类，2为二级分类</p>
	 * @author:  zhouming
	 * @param:   @param gcType
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setGcType(Integer gcType) {
		this.gcType = gcType;
	}
	
	
	/**
	 * <p>活动规则</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-24 18:39:11    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>活动规则</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:11   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
