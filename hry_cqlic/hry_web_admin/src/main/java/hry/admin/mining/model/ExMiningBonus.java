
package hry.admin.mining.model;

import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2018-06-27 19:52:24 
 */
@Table(name="ex_mining_bonus")
public class ExMiningBonus extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "cardNumber")
	private String cardNumber;  //身份证号
	
	@Column(name= "holdPlatformCount")
	private BigDecimal holdPlatformCount;  //持平台币数量
	
	@Column(name= "holdRatio")
	private BigDecimal holdRatio;  //持平台币占比
	
	@Column(name= "coinCode")
	private String coinCode;  //手续费币种
	
	@Column(name= "totalFee")
	private BigDecimal totalFee;  //手续费数量
	
	@Column(name= "bonusNumber")
	private BigDecimal bonusNumber;  //应分数量
	
	@Column(name= "status")
	private Integer status;  //状态 0 待分红 1已分红
	
	@Column(name= "customerId")
	private Long customerId; //客户customerId
	
	@Transient
	private Long accountId;//账户ID
	
	@Transient
	private String sourceId;
	
	
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2018-06-27 19:52:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-27 19:52:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>身份证号</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-06-27 19:52:24    
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * <p>身份证号</p>
	 * @author:  jidn
	 * @param:   @param cardNumber
	 * @return:  void 
	 * @Date :   2018-06-27 19:52:24   
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
	/**
	 * <p>持平台币数量</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-06-27 19:52:24    
	 */
	public BigDecimal getHoldPlatformCount() {
		return holdPlatformCount;
	}
	
	/**
	 * <p>持平台币数量</p>
	 * @author:  jidn
	 * @param:   @param holdPlatformCount
	 * @return:  void 
	 * @Date :   2018-06-27 19:52:24   
	 */
	public void setHoldPlatformCount(BigDecimal holdPlatformCount) {
		this.holdPlatformCount = holdPlatformCount;
	}
	
	
	/**
	 * <p>持平台币占比</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-06-27 19:52:24    
	 */
	public BigDecimal getHoldRatio() {
		return holdRatio;
	}
	
	/**
	 * <p>持平台币占比</p>
	 * @author:  jidn
	 * @param:   @param holdRatio
	 * @return:  void 
	 * @Date :   2018-06-27 19:52:24   
	 */
	public void setHoldRatio(BigDecimal holdRatio) {
		this.holdRatio = holdRatio;
	}
	
	
	/**
	 * <p>手续费币种</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-06-27 19:52:24    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>手续费币种</p>
	 * @author:  jidn
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-27 19:52:24   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>手续费数量</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-06-27 19:52:24    
	 */
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	
	/**
	 * <p>手续费数量</p>
	 * @author:  jidn
	 * @param:   @param totalFee
	 * @return:  void 
	 * @Date :   2018-06-27 19:52:24   
	 */
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	
	
	/**
	 * <p>应分数量</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-06-27 19:52:24    
	 */
	public BigDecimal getBonusNumber() {
		return bonusNumber;
	}
	
	/**
	 * <p>应分数量</p>
	 * @author:  jidn
	 * @param:   @param bonusNumber
	 * @return:  void 
	 * @Date :   2018-06-27 19:52:24   
	 */
	public void setBonusNumber(BigDecimal bonusNumber) {
		this.bonusNumber = bonusNumber;
	}
	
	
	/**
	 * <p>状态 0 待分红 1已分红</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2018-06-27 19:52:24    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>状态 0 待分红 1已分红</p>
	 * @author:  jidn
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-27 19:52:24   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
