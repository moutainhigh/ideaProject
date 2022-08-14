/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-07 11:10:09 
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
 * <p> GrowthDetail </p>
 * @author:         luyue
 * @Date :          2019-05-07 11:10:09  
 */
@Table(name="shop_growth_detail")
public class GrowthDetail extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //索引id
	
	@Column(name= "memberId")
	private Long memberId;  //用户id
	
	@Column(name= "accountId")
	private Long accountId;  //成长值账户id
	
	@Column(name= "rewardId")
	private Long rewardId;  //奖励类型id
	
	@Column(name= "rewardType")
	private Integer rewardType;  //奖励类型 1购物 2 完成任务
	
	@Column(name= "detailType")
	private Integer detailType;  //加减类型，1增加，2减少，3冻结
	
	@Column(name= "count")
	private BigDecimal count;  //成长分值
	
	@Column(name= "balanceCount")
	private BigDecimal balanceCount;  //操作前账户成长值分
	
	@Column(name= "status")
	private Integer status;  //状态( 0处理中   3成功  5失败)
	
	@Column(name= "name")
	private String name;  //操作名目
	
	@Column(name= "orderId")
	private Long orderId;  //订单Id
	
	@Column(name= "orderSn")
	private String orderSn;  //订单编号
	
	@Column(name= "number")
	private String number;  //明细编号
	
	@Column(name= "remark")
	private String remark;  //备注
	
	
	
	
	/**
	 * <p>索引id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>索引id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>成长值账户id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>成长值账户id</p>
	 * @author:  luyue
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>奖励类型id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public Long getRewardId() {
		return rewardId;
	}
	
	/**
	 * <p>奖励类型id</p>
	 * @author:  luyue
	 * @param:   @param rewardId
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setRewardId(Long rewardId) {
		this.rewardId = rewardId;
	}
	
	
	/**
	 * <p>奖励类型 1购物 2 完成任务</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public Integer getRewardType() {
		return rewardType;
	}
	
	/**
	 * <p>奖励类型 1购物 2 完成任务</p>
	 * @author:  luyue
	 * @param:   @param rewardType
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	
	
	/**
	 * <p>加减类型，1增加，2减少</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public Integer getDetailType() {
		return detailType;
	}
	
	/**
	 * <p>加减类型，1增加，2减少</p>
	 * @author:  luyue
	 * @param:   @param detailType
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}
	
	
	/**
	 * <p>成长分值</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public BigDecimal getCount() {
		return count;
	}
	
	/**
	 * <p>成长分值</p>
	 * @author:  luyue
	 * @param:   @param count
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	
	
	/**
	 * <p>操作前账户成长值分</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public BigDecimal getBalanceCount() {
		return balanceCount;
	}
	
	/**
	 * <p>操作前账户成长值分</p>
	 * @author:  luyue
	 * @param:   @param balanceCount
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setBalanceCount(BigDecimal balanceCount) {
		this.balanceCount = balanceCount;
	}
	
	
	/**
	 * <p>状态( 0处理中 1冻结 3成功  5失败)</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>状态( 0处理中 1冻结 3成功  5失败)</p>
	 * @author:  luyue
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>操作名目</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>操作名目</p>
	 * @author:  luyue
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>订单Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单Id</p>
	 * @author:  luyue
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>订单编号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public String getOrderSn() {
		return orderSn;
	}
	
	/**
	 * <p>订单编号</p>
	 * @author:  luyue
	 * @param:   @param orderSn
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	
	/**
	 * <p>明细编号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * <p>明细编号</p>
	 * @author:  luyue
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-07 11:10:09    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-07 11:10:09   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
