/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 18:28:34 
 */
package hry.social.manage.remote.model.mill;


import hry.bean.BaseModel;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * <p> SocialMillTradeRecord </p>
 * @author:         javalx
 * @Date :          2019-06-11 18:28:34  
 */
@Table(name="social_mill_trade_record")
public class SocialMillTradeRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //序号
	
	@Column(name= "orderNum")
	private String orderNum;  //交易ID
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	@Column(name= "millId")
	private Long millId;  //矿机ID
	
	@Column(name= "serialNum")
	private String serialNum;  //矿机型号
	
	@Column(name= "rewardType")
	private Integer rewardType;  //奖励方式 1：奖励算力，2：奖励数币
	
	@Column(name= "rewardNum")
	private BigDecimal rewardNum;  //奖励数量
	
	@Column(name= "cnyPrice")
	private BigDecimal cnyPrice;  //购买单价（CNY）
	
	@Column(name= "num")
	private BigDecimal num;  //购买量（个）
	
	@Column(name= "cnyAmount")
	private BigDecimal cnyAmount;  //购买总价（CNY）
	
	@Column(name= "coinCode")
	private String coinCode;  //奖励币种

	@Column(name= "payCoin")
	private String payCoin;  //支付币种
	
	@Column(name= "payNum")
	private BigDecimal payNum;  //支付数量（个）
	
	@Column(name= "coinMarket")
	private String coinMarket;  //支付币种市值（CNY）
	
	@Column(name= "payAmount")
	private BigDecimal payAmount;  //支付币总金额（CNY）
	
	@Column(name= "endTime")
	private Date endTime;  //到期时间

	@Transient
	private String expire;  //到期天数

	@Transient
	private String name;  //名称

	@Transient
	private String img;  //图片

	@Transient
	private Integer timeUnit;  //图片

	@Transient
	private Integer validNum;  //时效数

	public String getPayCoin() {
		return payCoin;
	}

	public void setPayCoin(String payCoin) {
		this.payCoin = payCoin;
	}

	public Integer getValidNum() {
		return validNum;
	}

	public void setValidNum(Integer validNum) {
		this.validNum = validNum;
	}

	public Integer getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(Integer timeUnit) {
		this.timeUnit = timeUnit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>交易ID</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p>交易ID</p>
	 * @author:  javalx
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>矿机ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public Long getMillId() {
		return millId;
	}
	
	/**
	 * <p>矿机ID</p>
	 * @author:  javalx
	 * @param:   @param millId
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setMillId(Long millId) {
		this.millId = millId;
	}
	
	
	/**
	 * <p>矿机型号</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public String getSerialNum() {
		return serialNum;
	}
	
	/**
	 * <p>矿机型号</p>
	 * @author:  javalx
	 * @param:   @param serialNum
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	
	
	/**
	 * <p>奖励方式 1：奖励算力，2：奖励数币</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public Integer getRewardType() {
		return rewardType;
	}
	
	/**
	 * <p>奖励方式 1：奖励算力，2：奖励数币</p>
	 * @author:  javalx
	 * @param:   @param rewardType
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	
	
	/**
	 * <p>奖励数量</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public BigDecimal getRewardNum() {
		return rewardNum;
	}
	
	/**
	 * <p>奖励数量</p>
	 * @author:  javalx
	 * @param:   @param rewardNum
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}
	
	
	/**
	 * <p>购买单价（CNY）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public BigDecimal getCnyPrice() {
		return cnyPrice;
	}
	
	/**
	 * <p>购买单价（CNY）</p>
	 * @author:  javalx
	 * @param:   @param cnyPrice
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setCnyPrice(BigDecimal cnyPrice) {
		this.cnyPrice = cnyPrice;
	}
	
	
	/**
	 * <p>购买量（个）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public BigDecimal getNum() {
		return num;
	}
	
	/**
	 * <p>购买量（个）</p>
	 * @author:  javalx
	 * @param:   @param num
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	
	
	/**
	 * <p>购买总价（CNY）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public BigDecimal getCnyAmount() {
		return cnyAmount;
	}
	
	/**
	 * <p>购买总价（CNY）</p>
	 * @author:  javalx
	 * @param:   @param cnyAmount
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setCnyAmount(BigDecimal cnyAmount) {
		this.cnyAmount = cnyAmount;
	}
	
	
	/**
	 * <p>支付币种</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>支付币种</p>
	 * @author:  javalx
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>支付数量（个）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public BigDecimal getPayNum() {
		return payNum;
	}
	
	/**
	 * <p>支付数量（个）</p>
	 * @author:  javalx
	 * @param:   @param payNum
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setPayNum(BigDecimal payNum) {
		this.payNum = payNum;
	}
	
	
	/**
	 * <p>支付币种市值（CNY）</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public String getCoinMarket() {
		return coinMarket;
	}
	
	/**
	 * <p>支付币种市值（CNY）</p>
	 * @author:  javalx
	 * @param:   @param coinMarket
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setCoinMarket(String coinMarket) {
		this.coinMarket = coinMarket;
	}
	
	
	/**
	 * <p>支付币总金额（CNY）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	
	/**
	 * <p>支付币总金额（CNY）</p>
	 * @author:  javalx
	 * @param:   @param payAmount
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @return:  Date 
	 * @Date :   2019-06-11 18:28:34    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-06-11 18:28:34   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

}
