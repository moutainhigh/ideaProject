/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:07:49 
 */
package hry.financail.financing.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppFinancialUser </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:07:49  
 */
@Table(name="app_financial_user")
public class AppFinancialUser extends BaseModel implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "productId")
	private Long productId;  //用户购买的理财产品
	
	@Column(name= "coinCode")
	private String coinCode;  //用户购买产品所使用的币种
	
	@Column(name= "coinMoney")
	private BigDecimal coinMoney;  //金额
	
	@Column(name= "redPacketsMoney")
	private BigDecimal redPacketsMoney;  //红包
	
	@Column(name= "poundage")
	private BigDecimal poundage;  //手续费
	
	@Column(name= "expectedIncome")
	private BigDecimal expectedIncome;  //预期收入
	
	@Column(name= "realIncome")
	private BigDecimal realIncome;  //实际收入
	
	@Column(name= "states")
	private Integer states;  //状态  0未发放  1已发放
	
	@Column(name= "isRedeem")
	private Integer isRedeem;  //是否赎回  0未赎回   1 申请赎回  2已赎回  3已拒绝
	
	@Column(name= "operator")
	private String operator;  //操作人
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "isDelete")
	private Integer isDelete;  //是否已删除  0未删除   1 已删除
	
	@Column(name= "redeemTime")
	private Date redeemTime;  //赎回回复时间
	
	@Column(name= "endTime")
	private Date endTime;  //结束时间

	@Column(name= "redeemPoundage")
	private BigDecimal redeemPoundage;  //赎回手续费

	@Column(name= "ifRecommend")
	private Integer ifRecommend;//是否统计了推荐关系奖励 0未统计  1已统计

	public Integer getIfRecommend() {
		return ifRecommend;
	}

	public void setIfRecommend(Integer ifRecommend) {
		this.ifRecommend = ifRecommend;
	}

	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  jidn
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>用户购买的理财产品</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public Long getProductId() {
		return productId;
	}
	
	/**
	 * <p>用户购买的理财产品</p>
	 * @author:  jidn
	 * @param:   @param productId
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
	/**
	 * <p>用户购买产品所使用的币种</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>用户购买产品所使用的币种</p>
	 * @author:  jidn
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>金额</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public BigDecimal getCoinMoney() {
		return coinMoney;
	}
	
	/**
	 * <p>金额</p>
	 * @author:  jidn
	 * @param:   @param coinMoney
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setCoinMoney(BigDecimal coinMoney) {
		this.coinMoney = coinMoney;
	}
	
	
	/**
	 * <p>红包</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public BigDecimal getRedPacketsMoney() {
		return redPacketsMoney;
	}
	
	/**
	 * <p>红包</p>
	 * @author:  jidn
	 * @param:   @param redPacketsMoney
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setRedPacketsMoney(BigDecimal redPacketsMoney) {
		this.redPacketsMoney = redPacketsMoney;
	}
	
	
	/**
	 * <p>手续费</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public BigDecimal getPoundage() {
		return poundage;
	}
	
	/**
	 * <p>手续费</p>
	 * @author:  jidn
	 * @param:   @param poundage
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}
	
	
	/**
	 * <p>预期收入</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public BigDecimal getExpectedIncome() {
		return expectedIncome;
	}
	
	/**
	 * <p>预期收入</p>
	 * @author:  jidn
	 * @param:   @param expectedIncome
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setExpectedIncome(BigDecimal expectedIncome) {
		this.expectedIncome = expectedIncome;
	}
	
	
	/**
	 * <p>实际收入</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public BigDecimal getRealIncome() {
		return realIncome;
	}
	
	/**
	 * <p>实际收入</p>
	 * @author:  jidn
	 * @param:   @param realIncome
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setRealIncome(BigDecimal realIncome) {
		this.realIncome = realIncome;
	}
	
	
	/**
	 * <p>状态  0未发放  1已发放</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>状态  0未发放  1已发放</p>
	 * @author:  jidn
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	/**
	 * <p>是否赎回  0未赎回   1 申请赎回  2已赎回  3已拒绝</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public Integer getIsRedeem() {
		return isRedeem;
	}
	
	/**
	 * <p>是否赎回  0未赎回   1 申请赎回  2已赎回  3已拒绝</p>
	 * @author:  jidn
	 * @param:   @param isRedeem
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setIsRedeem(Integer isRedeem) {
		this.isRedeem = isRedeem;
	}
	
	
	/**
	 * <p>操作人</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * <p>操作人</p>
	 * @author:  jidn
	 * @param:   @param operator
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  jidn
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>是否已删除  0未删除   1 已删除</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	
	/**
	 * <p>是否已删除  0未删除   1 已删除</p>
	 * @author:  jidn
	 * @param:   @param isDelete
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
	/**
	 * <p>赎回回复时间</p>
	 * @author:  jidn
	 * @return:  Date 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public Date getRedeemTime() {
		return redeemTime;
	}
	
	/**
	 * <p>赎回回复时间</p>
	 * @author:  jidn
	 * @param:   @param redeemTime
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setRedeemTime(Date redeemTime) {
		this.redeemTime = redeemTime;
	}
	
	
	/**
	 * <p>结束时间</p>
	 * @author:  jidn
	 * @return:  Date 
	 * @Date :   2019-04-03 11:07:49    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>结束时间</p>
	 * @author:  jidn
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:49   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getRedeemPoundage() {
		return redeemPoundage;
	}

	public void setRedeemPoundage(BigDecimal redeemPoundage) {
		this.redeemPoundage = redeemPoundage;
	}
}
