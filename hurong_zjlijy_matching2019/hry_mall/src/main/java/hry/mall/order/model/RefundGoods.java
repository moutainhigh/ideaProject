/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:39:04 
 */
package hry.mall.order.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> RefundGoods </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:39:04  
 */
@Table(name="shop_refund_goods")
public class RefundGoods extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //退货商品表索引id
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
	@Column(name= "orderSn")
	private String orderSn;  //订单编号
	
	@Column(name= "refundId")
	private Long refundId;  //退货ID
	
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
	
	@Column(name= "goodsNum")
	private Integer goodsNum;  //商品数量
	
	@Column(name= "goodsImage")
	private String goodsImage;  //商品图片
	
	@Column(name= "goodsReturnnum")
	private Integer goodsReturnnum;  //退货数量
	
	@Column(name= "refundAmount")
	private BigDecimal refundAmount;  //退款金额
	
	@Column(name= "goodsPrice")
	private BigDecimal goodsPrice;  //商品单价
	
	@Column(name= "goodsPayPrice")
	private BigDecimal goodsPayPrice;  //商品成交价
	
	@Column(name= "goodsAllPrice")
	private BigDecimal goodsAllPrice;  //商品小计价格

	@Column(name= "specGoodsSerial")
	private String specGoodsSerial;  //规格商品虚列号

	public String getSpecGoodsSerial() {
		return specGoodsSerial;
	}

	public void setSpecGoodsSerial(String specGoodsSerial) {
		this.specGoodsSerial = specGoodsSerial;
	}
	
	
	
	/**
	 * <p>退货商品表索引id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>退货商品表索引id</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  kongdebiao
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>订单编号</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public String getOrderSn() {
		return orderSn;
	}
	
	/**
	 * <p>订单编号</p>
	 * @author:  kongdebiao
	 * @param:   @param orderSn
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	
	/**
	 * <p>退货ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public Long getRefundId() {
		return refundId;
	}
	
	/**
	 * <p>退货ID</p>
	 * @author:  kongdebiao
	 * @param:   @param refundId
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}
	
	
	/**
	 * <p>商品id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品id</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>商品名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * <p>商品名称</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsName
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	/**
	 * <p>品牌id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public Long getBrandId() {
		return brandId;
	}
	
	/**
	 * <p>品牌id</p>
	 * @author:  kongdebiao
	 * @param:   @param brandId
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	
	
	/**
	 * <p>品牌名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public String getBrandName() {
		return brandName;
	}
	
	/**
	 * <p>品牌名称</p>
	 * @author:  kongdebiao
	 * @param:   @param brandName
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
	/**
	 * <p>商品规格id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public Long getGoodsSpecId() {
		return goodsSpecId;
	}
	
	/**
	 * <p>商品规格id</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsSpecId
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setGoodsSpecId(Long goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}
	
	
	/**
	 * <p>规格描述</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public String getSpecInfo() {
		return specInfo;
	}
	
	/**
	 * <p>规格描述</p>
	 * @author:  kongdebiao
	 * @param:   @param specInfo
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}
	
	
	/**
	 * <p>商品数量</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public Integer getGoodsNum() {
		return goodsNum;
	}
	
	/**
	 * <p>商品数量</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsNum
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	
	/**
	 * <p>商品图片</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public String getGoodsImage() {
		return goodsImage;
	}
	
	/**
	 * <p>商品图片</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsImage
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	
	
	/**
	 * <p>退货数量</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public Integer getGoodsReturnnum() {
		return goodsReturnnum;
	}
	
	/**
	 * <p>退货数量</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsReturnnum
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setGoodsReturnnum(Integer goodsReturnnum) {
		this.goodsReturnnum = goodsReturnnum;
	}
	
	
	/**
	 * <p>退款金额</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	
	/**
	 * <p>退款金额</p>
	 * @author:  kongdebiao
	 * @param:   @param refundAmount
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	
	/**
	 * <p>商品单价</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	
	/**
	 * <p>商品单价</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsPrice
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	
	/**
	 * <p>商品成交价</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public BigDecimal getGoodsPayPrice() {
		return goodsPayPrice;
	}
	
	/**
	 * <p>商品成交价</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsPayPrice
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setGoodsPayPrice(BigDecimal goodsPayPrice) {
		this.goodsPayPrice = goodsPayPrice;
	}
	
	
	/**
	 * <p>商品小计价格</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:39:04    
	 */
	public BigDecimal getGoodsAllPrice() {
		return goodsAllPrice;
	}
	
	/**
	 * <p>商品小计价格</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsAllPrice
	 * @return:  void 
	 * @Date :   2018-11-16 10:39:04   
	 */
	public void setGoodsAllPrice(BigDecimal goodsAllPrice) {
		this.goodsAllPrice = goodsAllPrice;
	}
	
	

}
