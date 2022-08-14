/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-24 18:40:03 
 */
package hry.mall.retailstore.model;
import hry.bean.BaseModel;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> CouponDetail </p>
 * @author:         zhouming
 * @Date :          2019-05-24 18:40:03  
 */
@Table(name="shop_coupon_detail")
public class CouponDetail extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "couponId")
	private Long couponId;  //优惠券id
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id
	
	@Column(name= "memberName")
	private String memberName;  //用户姓名
	
	@Column(name= "receiveType")
	private Integer receiveType;  //1、主动领取，2后台发送
	
	@Column(name= "receiveTime")
	private Date receiveTime;  //领取/发放时间
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
	@Column(name= "orderSn")
	private String orderSn;  //订单编号
	
	@Column(name= "useTime")
	private Date useTime;  //使用时间
	
	@Column(name= "status")
	private Integer status;  //优惠券状态，0 未使用，1已使用
	
	@Column(name= "startDate")
	private Date startDate;  //开始日期
	
	@Column(name= "endDate")
	private Date endDate;  //结束日期
	
	@Column(name= "type")
	private String type;  //优惠券类型
	
	@Column(name= "faceValue")
	private BigDecimal faceValue;  //面额
	
    @Transient
    private String  useName; //使用限制
    
    @Transient
    private BigDecimal useMoney;  //限用金额
    
	
	public String getUseName() {
		return useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>优惠券id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public Long getCouponId() {
		return couponId;
	}
	
	/**
	 * <p>优惠券id</p>
	 * @author:  zhouming
	 * @param:   @param couponId
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>用户姓名</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public String getMemberName() {
		return memberName;
	}
	
	/**
	 * <p>用户姓名</p>
	 * @author:  zhouming
	 * @param:   @param memberName
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
	/**
	 * <p>1、主动领取，2后台发送</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public Integer getReceiveType() {
		return receiveType;
	}
	
	/**
	 * <p>1、主动领取，2后台发送</p>
	 * @author:  zhouming
	 * @param:   @param receiveType
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setReceiveType(Integer receiveType) {
		this.receiveType = receiveType;
	}
	
	
	/**
	 * <p>领取/发放时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public Date getReceiveTime() {
		return receiveTime;
	}
	
	/**
	 * <p>领取/发放时间</p>
	 * @author:  zhouming
	 * @param:   @param receiveTime
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  zhouming
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>订单编号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public String getOrderSn() {
		return orderSn;
	}
	
	/**
	 * <p>订单编号</p>
	 * @author:  zhouming
	 * @param:   @param orderSn
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	
	/**
	 * <p>使用时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public Date getUseTime() {
		return useTime;
	}
	
	/**
	 * <p>使用时间</p>
	 * @author:  zhouming
	 * @param:   @param useTime
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	
	
	/**
	 * <p>优惠券状态，0 未使用，1已使用</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:40:03    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>优惠券状态，0 未使用，1已使用</p>
	 * @author:  zhouming
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-05-24 18:40:03   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
