/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-11-26 17:36:27 
 */
package hry.mall.order.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> OrderCoin </p>
 * @author:         luyue
 * @Date :          2019-11-26 17:36:27  
 */
@Table(name="shop_order_coin")
public class OrderCoin extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //订单索引id
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
	@Column(name= "coinCount")
	private BigDecimal coinCount;  //数字币支付数量
	
	@Column(name= "coinCode")
	private String coinCode;  //虚拟币币种
	
	@Column(name= "goodsSpecId")
	private Long goodsSpecId;  //商品规格id
	
	@Column(name= "payId")
	private Long payId;  //数币支付方式id
	
	@Column(name= "rebateId")
	private Long rebateId;  //返佣方式id
	
	@Column(name= "paidCount")
	private BigDecimal paidCount;  //已支付数币数量
	
	@Column(name= "unpaidCount")
	private BigDecimal unpaidCount;  //待支付数币数量
	
	@Column(name= "platCount")
	private BigDecimal platCount;  //平台抽成数量
	
	@Column(name= "oneLevelCount")
	private BigDecimal oneLevelCount;  //一级返利数量
	
	@Column(name= "twoLevelCount")
	private BigDecimal twoLevelCount;  //二级返利数量
	
	@Column(name= "storeCount")
	private BigDecimal storeCount;  //商户结算数量
	
	@Column(name= "remark")
	private String remark;  //订单支付表编号

	@Column(name= "advanceCount")
	private BigDecimal advanceCount;  //预支付数币数量

	@Column(name= "tailCount")
	private BigDecimal tailCount;  //尾款支付数币数量

	@Column(name= "balanceCode")
	private String balanceCode;  //结算或返佣币种

	public BigDecimal getAdvanceCount() {
		return advanceCount;
	}

	public void setAdvanceCount(BigDecimal advanceCount) {
		this.advanceCount = advanceCount;
	}

	public BigDecimal getTailCount() {
		return tailCount;
	}

	public void setTailCount(BigDecimal tailCount) {
		this.tailCount = tailCount;
	}

	public String getBalanceCode() {
		return balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}

	/**
	 * <p>订单索引id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>订单索引id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>数字币支付数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public BigDecimal getCoinCount() {
		return coinCount;
	}
	
	/**
	 * <p>数字币支付数量</p>
	 * @author:  luyue
	 * @param:   @param coinCount
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setCoinCount(BigDecimal coinCount) {
		this.coinCount = coinCount;
	}
	
	
	/**
	 * <p>虚拟币币种</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>虚拟币币种</p>
	 * @author:  luyue
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>商品规格id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public Long getGoodsSpecId() {
		return goodsSpecId;
	}
	
	/**
	 * <p>商品规格id</p>
	 * @author:  luyue
	 * @param:   @param goodsSpecId
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setGoodsSpecId(Long goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}
	
	
	/**
	 * <p>数币支付方式id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public Long getPayId() {
		return payId;
	}
	
	/**
	 * <p>数币支付方式id</p>
	 * @author:  luyue
	 * @param:   @param payId
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setPayId(Long payId) {
		this.payId = payId;
	}
	
	
	/**
	 * <p>返佣方式id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public Long getRebateId() {
		return rebateId;
	}
	
	/**
	 * <p>返佣方式id</p>
	 * @author:  luyue
	 * @param:   @param rebateId
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setRebateId(Long rebateId) {
		this.rebateId = rebateId;
	}
	
	
	/**
	 * <p>已支付数币数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public BigDecimal getPaidCount() {
		return paidCount;
	}
	
	/**
	 * <p>已支付数币数量</p>
	 * @author:  luyue
	 * @param:   @param paidCount
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setPaidCount(BigDecimal paidCount) {
		this.paidCount = paidCount;
	}
	
	
	/**
	 * <p>待支付数币数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public BigDecimal getUnpaidCount() {
		return unpaidCount;
	}
	
	/**
	 * <p>待支付数币数量</p>
	 * @author:  luyue
	 * @param:   @param unpaidCount
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setUnpaidCount(BigDecimal unpaidCount) {
		this.unpaidCount = unpaidCount;
	}
	
	
	/**
	 * <p>平台抽成数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public BigDecimal getPlatCount() {
		return platCount;
	}
	
	/**
	 * <p>平台抽成数量</p>
	 * @author:  luyue
	 * @param:   @param platCount
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setPlatCount(BigDecimal platCount) {
		this.platCount = platCount;
	}
	
	
	/**
	 * <p>一级返利数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public BigDecimal getOneLevelCount() {
		return oneLevelCount;
	}
	
	/**
	 * <p>一级返利数量</p>
	 * @author:  luyue
	 * @param:   @param oneLevelCount
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setOneLevelCount(BigDecimal oneLevelCount) {
		this.oneLevelCount = oneLevelCount;
	}
	
	
	/**
	 * <p>二级返利数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public BigDecimal getTwoLevelCount() {
		return twoLevelCount;
	}
	
	/**
	 * <p>二级返利数量</p>
	 * @author:  luyue
	 * @param:   @param twoLevelCount
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setTwoLevelCount(BigDecimal twoLevelCount) {
		this.twoLevelCount = twoLevelCount;
	}
	
	
	/**
	 * <p>商户结算数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public BigDecimal getStoreCount() {
		return storeCount;
	}
	
	/**
	 * <p>商户结算数量</p>
	 * @author:  luyue
	 * @param:   @param storeCount
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setStoreCount(BigDecimal storeCount) {
		this.storeCount = storeCount;
	}
	
	
	/**
	 * <p>订单支付表编号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-11-26 17:36:27    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>订单支付表编号</p>
	 * @author:  luyue
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-11-26 17:36:27   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
