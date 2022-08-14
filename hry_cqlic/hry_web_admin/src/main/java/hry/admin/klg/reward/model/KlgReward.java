/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-24 18:13:04 
 */
package hry.admin.klg.reward.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgReward </p>
 * @author:         yaozhuo
 * @Date :          2019-05-24 18:13:04  
 */
@Table(name="klg_reward")
public class KlgReward extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //流水号
	
	@Column(name= "sellTransactionNum")
	private String sellTransactionNum;  //卖单流水号
	
	@Column(name= "accountId")
	private Long accountId;  //数字货币账户id
	
	@Column(name= "coinCode")
	private String coinCode;  //奖励币种
	
	@Column(name= "rewardMoney")
	private BigDecimal rewardMoney;  //奖励数量
	
	@Column(name= "rewardType")
	private Integer rewardType;  //奖励类型 1见点奖 2级差奖
	
	@Column(name= "saasId")
	private String saasId;  //saasId
	
	@Column(name= "remark")
	private String remark;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozhuo
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozhuo
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>卖单流水号</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public String getSellTransactionNum() {
		return sellTransactionNum;
	}
	
	/**
	 * <p>卖单流水号</p>
	 * @author:  yaozhuo
	 * @param:   @param sellTransactionNum
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setSellTransactionNum(String sellTransactionNum) {
		this.sellTransactionNum = sellTransactionNum;
	}
	
	
	/**
	 * <p>数字货币账户id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>数字货币账户id</p>
	 * @author:  yaozhuo
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>奖励币种</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>奖励币种</p>
	 * @author:  yaozhuo
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>奖励数量</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public BigDecimal getRewardMoney() {
		return rewardMoney;
	}
	
	/**
	 * <p>奖励数量</p>
	 * @author:  yaozhuo
	 * @param:   @param rewardMoney
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setRewardMoney(BigDecimal rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	
	
	/**
	 * <p>奖励类型 1见点奖 2级差奖</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public Integer getRewardType() {
		return rewardType;
	}
	
	/**
	 * <p>奖励类型 1见点奖 2级差奖</p>
	 * @author:  yaozhuo
	 * @param:   @param rewardType
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	
	
	/**
	 * <p>saasId</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p>saasId</p>
	 * @author:  yaozhuo
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-05-24 18:13:04    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-24 18:13:04   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
