/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-12 11:32:00 
 */
package hry.social.manage.remote.model.vip;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> SocialVipTradeRecord </p>
 * @author:         javalx
 * @Date :          2019-06-12 11:32:00  
 */
@Table(name="social_vip_trade_record")
public class SocialVipTradeRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //序号
	
	@Column(name= "orderNum")
	private String orderNum;  //交易ID
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	@Column(name= "additionRatio")
	private BigDecimal additionRatio;  //加成比例
	
	@Column(name= "oldVipId")
	private Long oldVipId;  //原会员ID

	@Column(name= "oldVipName")
	private String oldVipName;  //原会员等级

	@Column(name= "oldVipNum")
	private String oldVipNum;  //原会员编号

	@Column(name= "oldEndTime")
	private Date oldEndTime;  //原会员到期时间
	
	@Column(name= "nowVipId")
	private Long nowVipId;  //新会员ID
	
	@Column(name= "nowVipName")
	private String nowVipName;  //新会员等级

	@Column(name= "nowVipNum")
	private String nowVipNum;  //新会员编号

	@Column(name= "nowEndTime")
	private Date nowEndTime;  //到期时间
	
	@Column(name= "states")
	private Integer states;  //类型 1升级，2续购
	
	@Column(name= "price")
	private BigDecimal price;  //总价格（CNY）
	
	@Column(name= "coinCode")
	private String coinCode;  //支付币种
	
	@Column(name= "payNum")
	private BigDecimal payNum;  //支付数量（个）
	
	@Column(name= "coinMarket")
	private String coinMarket;  //支付时市值（CNY）
	
	@Column(name= "payAmount")
	private BigDecimal payAmount;  //支付币总金额（CNY）


	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOldVipNum() {
		return oldVipNum;
	}

	public void setOldVipNum(String oldVipNum) {
		this.oldVipNum = oldVipNum;
	}

	public String getNowVipNum() {
		return nowVipNum;
	}

	public void setNowVipNum(String nowVipNum) {
		this.nowVipNum = nowVipNum;
	}

	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>交易ID</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public String getOrder_num() {
		return orderNum;
	}
	
	/**
	 * <p>交易ID</p>
	 * @author:  javalx
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setOrder_num(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>加成比例</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public BigDecimal getAdditionRatio() {
		return additionRatio;
	}
	
	/**
	 * <p>加成比例</p>
	 * @author:  javalx
	 * @param:   @param additionRatio
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setAdditionRatio(BigDecimal additionRatio) {
		this.additionRatio = additionRatio;
	}
	
	
	/**
	 * <p>原会员ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public Long getOldVipId() {
		return oldVipId;
	}
	
	/**
	 * <p>原会员ID</p>
	 * @author:  javalx
	 * @param:   @param oldVipId
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setOldVipId(Long oldVipId) {
		this.oldVipId = oldVipId;
	}
	
	
	/**
	 * <p>原会员等级</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public String getOldVipName() {
		return oldVipName;
	}
	
	/**
	 * <p>原会员等级</p>
	 * @author:  javalx
	 * @param:   @param oldVipName
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setOldVipName(String oldVipName) {
		this.oldVipName = oldVipName;
	}
	
	
	/**
	 * <p>原会员到期时间</p>
	 * @author:  javalx
	 * @return:  Date 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public Date getOldEndTime() {
		return oldEndTime;
	}
	
	/**
	 * <p>原会员到期时间</p>
	 * @author:  javalx
	 * @param:   @param oldEndTime
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setOldEndTime(Date oldEndTime) {
		this.oldEndTime = oldEndTime;
	}
	
	
	/**
	 * <p>新会员ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public Long getNowVipId() {
		return nowVipId;
	}
	
	/**
	 * <p>新会员ID</p>
	 * @author:  javalx
	 * @param:   @param nowVipId
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setNowVipId(Long nowVipId) {
		this.nowVipId = nowVipId;
	}
	
	
	/**
	 * <p>新会员等级</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public String getNowVipName() {
		return nowVipName;
	}
	
	/**
	 * <p>新会员等级</p>
	 * @author:  javalx
	 * @param:   @param nowVipName
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setNowVipName(String nowVipName) {
		this.nowVipName = nowVipName;
	}
	
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @return:  Date 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public Date getNowEndTime() {
		return nowEndTime;
	}
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @param:   @param nowEndTime
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setNowEndTime(Date nowEndTime) {
		this.nowEndTime = nowEndTime;
	}
	
	
	/**
	 * <p>类型 1升级，2续购</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>类型 1升级，2续购</p>
	 * @author:  javalx
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	/**
	 * <p>总价格（CNY）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * <p>总价格（CNY）</p>
	 * @author:  javalx
	 * @param:   @param price
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	/**
	 * <p>支付币种</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>支付币种</p>
	 * @author:  javalx
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>支付数量（个）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public BigDecimal getPayNum() {
		return payNum;
	}
	
	/**
	 * <p>支付数量（个）</p>
	 * @author:  javalx
	 * @param:   @param payNum
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setPayNum(BigDecimal payNum) {
		this.payNum = payNum;
	}
	
	
	/**
	 * <p>支付时市值（CNY）</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public String getCoinMarket() {
		return coinMarket;
	}
	
	/**
	 * <p>支付时市值（CNY）</p>
	 * @author:  javalx
	 * @param:   @param coinMarket
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setCoinMarket(String coinMarket) {
		this.coinMarket = coinMarket;
	}
	
	
	/**
	 * <p>支付币总金额（CNY）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-12 11:32:00    
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	
	/**
	 * <p>支付币总金额（CNY）</p>
	 * @author:  javalx
	 * @param:   @param payAmount
	 * @return:  void 
	 * @Date :   2019-06-12 11:32:00   
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	
	

}
