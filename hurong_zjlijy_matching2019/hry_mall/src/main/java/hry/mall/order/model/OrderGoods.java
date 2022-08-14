/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:35:23 
 */
package hry.mall.order.model;
import hry.bean.BaseModel;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> OrderGoods </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:35:23  
 */
@Table(name="shop_order_goods")
public class OrderGoods extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //订单商品表索引id
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
	@Column(name= "orderSn")
	private String orderSn;  //订单编号
	
	@Column(name= "goodsId")
	private Long goodsId;  //商品id
	
	@Column(name= "goodsName")
	private String goodsName;  //商品名称
	
	@Column(name= "brandId")
	private Long brandId;  //品牌id
	
	@Column(name= "brandName")
	private String brandName;  //品牌名称
	
	@Column(name= "goodsSpecId")
	private Long goodsSpecId;  //商品规格id
	
	@Column(name= "specInfo")
	private String specInfo;  //规格描述
	
	@Column(name= "goodsPrice")
	private BigDecimal goodsPrice;  //商品价格
	
	@Column(name= "goodsNum")
	private Integer goodsNum;  //商品数量
	
	@Column(name= "goodsImage")
	private String goodsImage;  //商品图片
	
	@Column(name= "evaluationStatus")
	private Integer evaluationStatus;  //评价状态 0为评价，1已评价

	 @Column(name= "returnStatus")
	private Integer  returnStatus; //是否退货 ：0未退货，1已退货;
	
	@Column(name= "evaluationTime")
	private Date evaluationTime;  //评价时间
	
	@Column(name= "goodsPayPrice")
	private BigDecimal goodsPayPrice;  //商品成交价
	
	@Column(name= "memberId")
	private Long memberId;  //买家ID
	
	@Column(name= "goodsAllPrice")
	private BigDecimal goodsAllPrice;  //商品小计价格

	@Column(name= "specGoodsSerial")
	private String specGoodsSerial;  //规格商品虚列号
	
	@Column(name= "integralCount")
	private BigDecimal integralCount;  //积分兑换数量
	
	@Column(name= "activityGoodsId")
	private Long activityGoodsId;  //活动商品id
	
	@Column(name= "remark")
	private String remark; //备注
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getActivityGoodsId() {
		return activityGoodsId;
	}

	public void setActivityGoodsId(Long activityGoodsId) {
		this.activityGoodsId = activityGoodsId;
	}

	public BigDecimal getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(BigDecimal integralCount) {
		this.integralCount = integralCount;
	}

	public Integer getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(Integer returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getSpecGoodsSerial() {
		return specGoodsSerial;
	}

	public void setSpecGoodsSerial(String specGoodsSerial) {
		this.specGoodsSerial = specGoodsSerial;
	}

	/**
	 * <p>订单商品表索引id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>订单商品表索引id</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  kongdebiao
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>订单编号</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public String getOrderSn() {
		return orderSn;
	}
	
	/**
	 * <p>订单编号</p>
	 * @author:  kongdebiao
	 * @param:   @param orderSn
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	
	/**
	 * <p>商品id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品id</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>商品名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * <p>商品名称</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsName
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	/**
	 * <p>品牌id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public Long getBrandId() {
		return brandId;
	}
	
	/**
	 * <p>品牌id</p>
	 * @author:  kongdebiao
	 * @param:   @param brandId
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	
	
	/**
	 * <p>品牌名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public String getBrandName() {
		return brandName;
	}
	
	/**
	 * <p>品牌名称</p>
	 * @author:  kongdebiao
	 * @param:   @param brandName
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
	/**
	 * <p>商品规格id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public Long getGoodsSpecId() {
		return goodsSpecId;
	}
	
	/**
	 * <p>商品规格id</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsSpecId
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setGoodsSpecId(Long goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}
	
	
	/**
	 * <p>规格描述</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public String getSpecInfo() {
		return specInfo;
	}
	
	/**
	 * <p>规格描述</p>
	 * @author:  kongdebiao
	 * @param:   @param specInfo
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}
	
	
	/**
	 * <p>商品价格</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	
	/**
	 * <p>商品价格</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsPrice
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	
	/**
	 * <p>商品数量</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public Integer getGoodsNum() {
		return goodsNum;
	}
	
	/**
	 * <p>商品数量</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsNum
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	
	/**
	 * <p>商品图片</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public String getGoodsImage() {
		return goodsImage;
	}
	
	/**
	 * <p>商品图片</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsImage
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	
	
	/**
	 * <p>评价状态 0为评价，1已评价</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public Integer getEvaluationStatus() {
		return evaluationStatus;
	}
	
	/**
	 * <p>评价状态 0为评价，1已评价</p>
	 * @author:  kongdebiao
	 * @param:   @param evaluationStatus
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setEvaluationStatus(Integer evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}
	
	
	/**
	 * <p>评价时间</p>
	 * @author:  kongdebiao
	 * @return:  Date 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public Date getEvaluationTime() {
		return evaluationTime;
	}
	
	/**
	 * <p>评价时间</p>
	 * @author:  kongdebiao
	 * @param:   @param evaluationTime
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}
	
	
	/**
	 * <p>商品成交价</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public BigDecimal getGoodsPayPrice() {
		return goodsPayPrice;
	}
	
	/**
	 * <p>商品成交价</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsPayPrice
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setGoodsPayPrice(BigDecimal goodsPayPrice) {
		this.goodsPayPrice = goodsPayPrice;
	}
	
	
	/**
	 * <p>买家ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>买家ID</p>
	 * @author:  kongdebiao
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>商品小计价格</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:35:23    
	 */
	public BigDecimal getGoodsAllPrice() {
		return goodsAllPrice;
	}
	
	/**
	 * <p>商品小计价格</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsAllPrice
	 * @return:  void 
	 * @Date :   2018-11-16 10:35:23   
	 */
	public void setGoodsAllPrice(BigDecimal goodsAllPrice) {
		this.goodsAllPrice = goodsAllPrice;
	}
	
	

}
