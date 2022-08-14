/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-09 17:55:31 
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

/**
 * <p> GroupDetail </p>
 * @author:         luyue
 * @Date :          2019-05-09 17:55:31  
 */
@Table(name="shop_group_detail")
public class GroupDetail extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //订单索引id
	
	@Column(name= "groupId")
	private Long groupId;  //团id
	
	@Column(name= "memberId")
	private Long memberId;  //团员id
	
	@Column(name= "memberName")
	private String memberName;  //团员姓名
	
	@Column(name= "isFunder")
	private Integer isFunder;  //是否团长，1是0否
	
	@Column(name= "image")
	private String image;  //头像图片
	
	@Column(name= "discount")
	private BigDecimal discount;  //优惠总金额
	
	@Column(name= "amount")
	private BigDecimal amount;  //订单实付金额
	
	@Column(name= "totalPrice")
	private BigDecimal totalPrice;  //订单总价格
	
	@Column(name= "provinceId")
	private String provinceId;  //省id
	
	@Column(name= "cityId")
	private String cityId;  //市id
	
	@Column(name= "countyId")
	private String countyId;  //县id
	
	@Column(name= "street")
	private String street;  //街道
	
	@Column(name= "detailAddress")
	private String detailAddress;  //详细地址
	
	@Column(name= "cellphone")
	private String cellphone;  //手机号
	
	@Column(name= "receiveName")
	private String receiveName;  //收货人姓名
	
	@Column(name= "zipCode")
	private String zipCode;  //邮政编码
	
	@Column(name= "paymentId")
	private String paymentId;  //支付通道id
	
	@Column(name= "status")
	private Integer status;  //团状态：0 待支付 1 已支付,看后续是否有退团等操作,2已下单
	
	@Column(name= "goodsPrice")
	private BigDecimal goodsPrice;  //商品原价
	
	@Column(name= "count")
	private Integer count;  //购买数量
	
	@Column(name= "activityPrice")
	private BigDecimal activityPrice; //商品购买价格-商品活动价
	
	@Column(name= "remark")
	private String remark;  //备注信息
  
	   @Column(name = "payType")
	    private String payType;  //支付方式，1人民币，2数字币，3混合币
	    
	    @Column(name = "coinCode")
	    private String coinCode;  //虚拟币币种
	    
	    @Column(name = "rmbMoney")
	    private BigDecimal rmbMoney;  //人民币支付金额
	    
	    @Column(name = "coinCount")
	    private BigDecimal coinCount;  //数字币支付数量
	    
	    @Column(name = "coinRate")
	    private BigDecimal coinRate;  //对数字币比例
	    
	    @Column(name = "coinPayStatus")
	    private Short coinPayStatus;  //虚拟币支付状态,1是成功，2失败
	    
	    @Column(name = "rmbPayStatus")
	    private Short rmbPayStatus;  //人民币支付状态，1是成功，2失败
	    
	    @Column(name = "rmbFeeMoney")
	    private BigDecimal rmbFeeMoney;  //人民币支付手续费金额
	    
	    @Column(name = "coinFeeCount")
	    private BigDecimal coinFeeCount;  //数字币支付手续费数量
	    
	    @Column(name = "rmbFeeRate")
	    private BigDecimal rmbFeeRate;  //人民币支付手续费率
	    
	    @Column(name = "coinFeeRate")
	    private BigDecimal coinFeeRate;  //数字币支付手续费率
	    
	    @Column(name = "payEndTime")
	    private Date payEndTime;  //支付截止时间，逾期未支付，订单关闭
	    
	    @Column(name = "cancelType")
	    private Short cancelType;  //取消/关闭类别，0客户主动关闭，1系统关闭
	    
	    @Column(name = "shippingFee")
	    private BigDecimal shippingFee;  //运费价格
	
	    @Column(name = "paymentTime")
	    private Date paymentTime;  //支付时间
	    
	    @Column(name = "isRobot")
	    private Short isRobot;  //是否机器人凑团
	    
		@Column(name= "orderId")
		private Long orderId;  //生成的订单id
	    
		
	   public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

	public Short getIsRobot() {
			return isRobot;
		}

		public void setIsRobot(Short isRobot) {
			this.isRobot = isRobot;
		}

	   public Date getPaymentTime() {
			return paymentTime;
		}

		public void setPaymentTime(Date paymentTime) {
			this.paymentTime = paymentTime;
		}

	public BigDecimal getShippingFee() {
			return shippingFee;
		}

		public void setShippingFee(BigDecimal shippingFee) {
			this.shippingFee = shippingFee;
		}

	public String getPayType() {
			return payType;
		}

		public void setPayType(String payType) {
			this.payType = payType;
		}

		public String getCoinCode() {
			return coinCode;
		}

		public void setCoinCode(String coinCode) {
			this.coinCode = coinCode;
		}

		public BigDecimal getRmbMoney() {
			return rmbMoney;
		}

		public void setRmbMoney(BigDecimal rmbMoney) {
			this.rmbMoney = rmbMoney;
		}

		public BigDecimal getCoinCount() {
			return coinCount;
		}

		public void setCoinCount(BigDecimal coinCount) {
			this.coinCount = coinCount;
		}

		public BigDecimal getCoinRate() {
			return coinRate;
		}

		public void setCoinRate(BigDecimal coinRate) {
			this.coinRate = coinRate;
		}

		public Short getCoinPayStatus() {
			return coinPayStatus;
		}

		public void setCoinPayStatus(Short coinPayStatus) {
			this.coinPayStatus = coinPayStatus;
		}

		public Short getRmbPayStatus() {
			return rmbPayStatus;
		}

		public void setRmbPayStatus(Short rmbPayStatus) {
			this.rmbPayStatus = rmbPayStatus;
		}

		public BigDecimal getRmbFeeMoney() {
			return rmbFeeMoney;
		}

		public void setRmbFeeMoney(BigDecimal rmbFeeMoney) {
			this.rmbFeeMoney = rmbFeeMoney;
		}

		public BigDecimal getCoinFeeCount() {
			return coinFeeCount;
		}

		public void setCoinFeeCount(BigDecimal coinFeeCount) {
			this.coinFeeCount = coinFeeCount;
		}

		public BigDecimal getRmbFeeRate() {
			return rmbFeeRate;
		}

		public void setRmbFeeRate(BigDecimal rmbFeeRate) {
			this.rmbFeeRate = rmbFeeRate;
		}

		public BigDecimal getCoinFeeRate() {
			return coinFeeRate;
		}

		public void setCoinFeeRate(BigDecimal coinFeeRate) {
			this.coinFeeRate = coinFeeRate;
		}

		public Date getPayEndTime() {
			return payEndTime;
		}

		public void setPayEndTime(Date payEndTime) {
			this.payEndTime = payEndTime;
		}

		public Short getCancelType() {
			return cancelType;
		}

		public void setCancelType(Short cancelType) {
			this.cancelType = cancelType;
		}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * <p>订单索引id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>订单索引id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>活动id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public Long getGroupId() {
		return groupId;
	}
	
	/**
	 * <p>活动id</p>
	 * @author:  luyue
	 * @param:   @param groupId
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	
	/**
	 * <p>团员id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>团员id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>团员姓名</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getMemberName() {
		return memberName;
	}
	
	/**
	 * <p>团员姓名</p>
	 * @author:  luyue
	 * @param:   @param memberName
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
	/**
	 * <p>是否团长，1是0否</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public Integer getIsFunder() {
		return isFunder;
	}
	
	/**
	 * <p>是否团长，1是0否</p>
	 * @author:  luyue
	 * @param:   @param isFunder
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setIsFunder(Integer isFunder) {
		this.isFunder = isFunder;
	}
	
	
	/**
	 * <p>头像图片</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * <p>头像图片</p>
	 * @author:  luyue
	 * @param:   @param image
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	/**
	 * <p>优惠总金额</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public BigDecimal getDiscount() {
		return discount;
	}
	
	/**
	 * <p>优惠总金额</p>
	 * @author:  luyue
	 * @param:   @param discount
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	
	/**
	 * <p>订单实付金额</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * <p>订单实付金额</p>
	 * @author:  luyue
	 * @param:   @param amount
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	/**
	 * <p>订单总价格</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * <p>订单总价格</p>
	 * @author:  luyue
	 * @param:   @param totalPrice
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	/**
	 * <p>省id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getProvinceId() {
		return provinceId;
	}
	
	/**
	 * <p>省id</p>
	 * @author:  luyue
	 * @param:   @param provinceId
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	
	/**
	 * <p>市id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getCityId() {
		return cityId;
	}
	
	/**
	 * <p>市id</p>
	 * @author:  luyue
	 * @param:   @param cityId
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	
	/**
	 * <p>县id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getCountyId() {
		return countyId;
	}
	
	/**
	 * <p>县id</p>
	 * @author:  luyue
	 * @param:   @param countyId
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	
	
	/**
	 * <p>街道</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * <p>街道</p>
	 * @author:  luyue
	 * @param:   @param street
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	/**
	 * <p>详细地址</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getDetailAddress() {
		return detailAddress;
	}
	
	/**
	 * <p>详细地址</p>
	 * @author:  luyue
	 * @param:   @param detailAddress
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getCellphone() {
		return cellphone;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @param:   @param cellphone
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	/**
	 * <p>收货人姓名</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getReceiveName() {
		return receiveName;
	}
	
	/**
	 * <p>收货人姓名</p>
	 * @author:  luyue
	 * @param:   @param receiveName
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	
	
	/**
	 * <p>邮政编码</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * <p>邮政编码</p>
	 * @author:  luyue
	 * @param:   @param zipCode
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	/**
	 * <p>支付通道id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public String getPaymentId() {
		return paymentId;
	}
	
	/**
	 * <p>支付通道id</p>
	 * @author:  luyue
	 * @param:   @param paymentId
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
	
	/**
	 * <p>团状态：0 待支付 1 已支付,看后续是否有退团等操作</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>团状态：0 待支付 1 已支付,看后续是否有退团等操作</p>
	 * @author:  luyue
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>购买价格</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	
	/**
	 * <p>购买价格</p>
	 * @author:  luyue
	 * @param:   @param goodsPrice
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	
	/**
	 * <p>购买数量</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:55:31    
	 */
	public Integer getCount() {
		return count;
	}
	
	/**
	 * <p>购买数量</p>
	 * @author:  luyue
	 * @param:   @param count
	 * @return:  void 
	 * @Date :   2019-05-09 17:55:31   
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}
	
	

	

}
