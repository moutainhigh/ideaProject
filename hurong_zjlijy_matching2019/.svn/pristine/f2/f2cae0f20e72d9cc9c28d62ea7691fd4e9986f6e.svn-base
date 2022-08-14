/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:38:34 
 */
package hry.mall.order.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p> Refund </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:38:34  
 */
@Table(name="shop_refund")
public class Refund extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //记录ID
	
	@Column(name= "orderId")
	private Long orderId;  //订单ID
	
	@Column(name= "orderSn")
	private String orderSn;  //订单编号
	
	@Column(name= "refundSn")
	private String refundSn;  //服务单号
	
	@Column(name= "memberId")
	private Long memberId;  //买家ID
	
	@Column(name= "memberName")
	private String memberName;  //联系人
	
	@Column(name= "memberMobile")
	private String memberMobile;  //买家电话
	
	@Column(name= "refundAmount")
	private BigDecimal refundAmount;  //退款金额
	
	@Column(name= "goodsImage")
	private String goodsImage;  //商品图片
	
	@Column(name= "refundState")
	private Integer refundState;  //申请状态:1待处理,2退货中,3已完成,4已拒绝
	
	@Column(name= "returnType")
	private Integer returnType;  //退货类型:1为不用退货,2为需要退货
	
	@Column(name= "goodsState")
	private Integer goodsState;  //物流状态:1为待发货,2为待收货,3为未收到,4为已收货
	
	@Column(name= "sellerTime")
	private Date sellerTime;  //处理时间
	
	@Column(name= "buyerMessage")
	private String buyerMessage;  //退货申请原因
	
	@Column(name= "reasonInfo")
	private String reasonInfo;  //退货原因描述
	
	@Column(name= "picInfo")
	private String picInfo;  //图片
	
	@Column(name= "isFreight")
	private Integer isFreight;  //是否退运费:0不退运费,1退运费 
	
	@Column(name= "refundMoney")
	private BigDecimal refundMoney;  //确认退货金额
	
	@Column(name= "receivingPoint")
	private String receivingPoint;  //收货点名称
	
	@Column(name= "shippingExpressId")
	private Long shippingExpressId;  //物流公司ID
	
	@Column(name= "shippingExpressName")
	private String shippingExpressName;  //物流公司名称
	
	@Column(name= "shippingCode")
	private String shippingCode;  //物流单号
	
	@Column(name= "receivePerson")
	private String receivePerson;  //收货人

	@Column(name= "receiveMoble")
	private String receiveMoble;  //收货人电话

	@Column(name= "receiveAddress")
	private String receiveAddress;  //收货地址

	@Column(name= "zipCode")
	private String zipCode;  //邮编地址

	@Column(name= "receiveTime")
	private Date receiveTime;  //收货时间
	
	@Column(name= "sellerMessage")
	private String sellerMessage;  //根据申请状态卖家备注

	@Column(name= "userName")
	private String userName;  //用户账号

	@Column(name= "refundWay")
	private String refundWay;  //退款方式

	@Column(name= "refundType")
	private String refundType;  //退款类型:1取消订单

	@Column(name= "sellerName")
	private String sellerName;  //卖家处理人

	@Column(name= "refuseReason")
	private String refuseReason;  //拒绝原因
	@Column(name= "receiveMessage")
	private String receiveMessage;  //收货备注
	
	@Column(name= "refundCoinCode")
	private String refundCoinCode;  //虚拟币币种
	
	@Column(name= "refundCoinCount")
	private BigDecimal refundCoinCount;//退货数字币支付数量
	
	@Column(name= "refundCoinRate")
	private BigDecimal refundCoinRate;  //虚拟币币种
	
	@Column(name= "isBuyRate")
	private Integer isBuyRate;  //'退货计算数币比率 1 购买比率 ，2 当前比率',

	@Transient
	private List<RefundGoods> refundGoodsList;
    
	public Integer getIsBuyRate() {
		return isBuyRate;
	}

	public void setIsBuyRate(Integer isBuyRate) {
		this.isBuyRate = isBuyRate;
	}

	public String getRefundCoinCode() {
		return refundCoinCode;
	}

	public void setRefundCoinCode(String refundCoinCode) {
		this.refundCoinCode = refundCoinCode;
	}

	public BigDecimal getRefundCoinCount() {
		return refundCoinCount;
	}

	public void setRefundCoinCount(BigDecimal refundCoinCount) {
		this.refundCoinCount = refundCoinCount;
	}

	public BigDecimal getRefundCoinRate() {
		return refundCoinRate;
	}

	public void setRefundCoinRate(BigDecimal refundCoinRate) {
		this.refundCoinRate = refundCoinRate;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<RefundGoods> getRefundGoodsList() {
		return refundGoodsList;
	}

	public void setRefundGoodsList(List<RefundGoods> refundGoodsList) {
		this.refundGoodsList = refundGoodsList;
	}

	public String getReceiveMessage() {
		return receiveMessage;
	}

	public void setReceiveMessage(String receiveMessage) {
		this.receiveMessage = receiveMessage;
	}

	public String getReceiveMoble() {
		return receiveMoble;
	}

	public void setReceiveMoble(String receiveMoble) {
		this.receiveMoble = receiveMoble;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	/**
	 * <p>记录ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>记录ID</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单ID</p>
	 * @author:  kongdebiao
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>订单编号</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getOrderSn() {
		return orderSn;
	}
	
	/**
	 * <p>订单编号</p>
	 * @author:  kongdebiao
	 * @param:   @param orderSn
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	
	/**
	 * <p>服务单号</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getRefundSn() {
		return refundSn;
	}
	
	/**
	 * <p>服务单号</p>
	 * @author:  kongdebiao
	 * @param:   @param refundSn
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setRefundSn(String refundSn) {
		this.refundSn = refundSn;
	}
	
	
	/**
	 * <p>买家ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>买家ID</p>
	 * @author:  kongdebiao
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>联系人</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getMemberName() {
		return memberName;
	}
	
	/**
	 * <p>联系人</p>
	 * @author:  kongdebiao
	 * @param:   @param memberName
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
	/**
	 * <p>买家电话</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getMemberMobile() {
		return memberMobile;
	}
	
	/**
	 * <p>买家电话</p>
	 * @author:  kongdebiao
	 * @param:   @param memberMobile
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	
	
	/**
	 * <p>退款金额</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	
	/**
	 * <p>退款金额</p>
	 * @author:  kongdebiao
	 * @param:   @param refundAmount
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	
	/**
	 * <p>商品图片</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getGoodsImage() {
		return goodsImage;
	}
	
	/**
	 * <p>商品图片</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsImage
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	
	
	/**
	 * <p>申请状态:1待处理,2退货中,3已完成,4已拒绝</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Integer getRefundState() {
		return refundState;
	}
	
	/**
	 * <p>申请状态:1待处理,2退货中,3已完成,4已拒绝</p>
	 * @author:  kongdebiao
	 * @param:   @param refundState
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}
	
	
	/**
	 * <p>退货类型:1为不用退货,2为需要退货</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Integer getReturnType() {
		return returnType;
	}
	
	/**
	 * <p>退货类型:1为不用退货,2为需要退货</p>
	 * @author:  kongdebiao
	 * @param:   @param returnType
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}
	
	
	/**
	 * <p>物流状态:1为待发货,2为待收货,3为未收到,4为已收货</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Integer getGoodsState() {
		return goodsState;
	}
	
	/**
	 * <p>物流状态:1为待发货,2为待收货,3为未收到,4为已收货</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsState
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setGoodsState(Integer goodsState) {
		this.goodsState = goodsState;
	}
	
	
	/**
	 * <p>处理时间</p>
	 * @author:  kongdebiao
	 * @return:  Date 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Date getSellerTime() {
		return sellerTime;
	}
	
	/**
	 * <p>处理时间</p>
	 * @author:  kongdebiao
	 * @param:   @param sellerTime
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setSellerTime(Date sellerTime) {
		this.sellerTime = sellerTime;
	}
	
	
	/**
	 * <p>退货申请原因</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getBuyerMessage() {
		return buyerMessage;
	}
	
	/**
	 * <p>退货申请原因</p>
	 * @author:  kongdebiao
	 * @param:   @param buyerMessage
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	
	
	/**
	 * <p>退货原因描述</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getReasonInfo() {
		return reasonInfo;
	}
	
	/**
	 * <p>退货原因描述</p>
	 * @author:  kongdebiao
	 * @param:   @param reasonInfo
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setReasonInfo(String reasonInfo) {
		this.reasonInfo = reasonInfo;
	}
	
	
	/**
	 * <p>图片</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getPicInfo() {
		return picInfo;
	}
	
	/**
	 * <p>图片</p>
	 * @author:  kongdebiao
	 * @param:   @param picInfo
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo;
	}
	
	
	/**
	 * <p>是否退运费:0不退运费,1退运费 </p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Integer getIsFreight() {
		return isFreight;
	}
	
	/**
	 * <p>是否退运费:0不退运费,1退运费 </p>
	 * @author:  kongdebiao
	 * @param:   @param isFreight
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setIsFreight(Integer isFreight) {
		this.isFreight = isFreight;
	}
	
	
	/**
	 * <p>确认退货金额</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public BigDecimal getRefundMoney() {
		return refundMoney;
	}
	
	/**
	 * <p>确认退货金额</p>
	 * @author:  kongdebiao
	 * @param:   @param refundMoney
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}
	
	
	/**
	 * <p>收货点名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getReceivingPoint() {
		return receivingPoint;
	}
	
	/**
	 * <p>收货点名称</p>
	 * @author:  kongdebiao
	 * @param:   @param receivingPoint
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setReceivingPoint(String receivingPoint) {
		this.receivingPoint = receivingPoint;
	}
	
	
	/**
	 * <p>物流公司ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Long getShippingExpressId() {
		return shippingExpressId;
	}
	
	/**
	 * <p>物流公司ID</p>
	 * @author:  kongdebiao
	 * @param:   @param shippingExpressId
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setShippingExpressId(Long shippingExpressId) {
		this.shippingExpressId = shippingExpressId;
	}
	
	
	/**
	 * <p>物流公司名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getShippingExpressName() {
		return shippingExpressName;
	}
	
	/**
	 * <p>物流公司名称</p>
	 * @author:  kongdebiao
	 * @param:   @param shippingExpressName
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setShippingExpressName(String shippingExpressName) {
		this.shippingExpressName = shippingExpressName;
	}
	
	
	/**
	 * <p>物流单号</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getShippingCode() {
		return shippingCode;
	}
	
	/**
	 * <p>物流单号</p>
	 * @author:  kongdebiao
	 * @param:   @param shippingCode
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	
	
	/**
	 * <p>收货人</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getReceivePerson() {
		return receivePerson;
	}
	
	/**
	 * <p>收货人</p>
	 * @author:  kongdebiao
	 * @param:   @param receivePerson
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	
	
	/**
	 * <p>收货时间</p>
	 * @author:  kongdebiao
	 * @return:  Date 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public Date getReceiveTime() {
		return receiveTime;
	}
	
	/**
	 * <p>收货时间</p>
	 * @author:  kongdebiao
	 * @param:   @param receiveTime
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	
	/**
	 * <p>根据申请状态卖家备注</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:38:34    
	 */
	public String getSellerMessage() {
		return sellerMessage;
	}
	
	/**
	 * <p>根据申请状态卖家备注</p>
	 * @author:  kongdebiao
	 * @param:   @param sellerMessage
	 * @return:  void 
	 * @Date :   2018-11-16 10:38:34   
	 */
	public void setSellerMessage(String sellerMessage) {
		this.sellerMessage = sellerMessage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRefundWay() {
		return refundWay;
	}

	public void setRefundWay(String refundWay) {
		this.refundWay = refundWay;
	}

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}


}
