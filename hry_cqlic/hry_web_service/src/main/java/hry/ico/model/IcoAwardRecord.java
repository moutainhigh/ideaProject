/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-26 10:52:06 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoAwardRecord </p>
 * @author:         lzy
 * @Date :          2019-01-26 10:52:06  
 */
@Table(name="ico_award_record")
public class IcoAwardRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customer_id")
	private Long customer_id;  //账户id
	
	@Column(name= "customer_email")
	private String customer_email;  //账户邮箱
	
	@Column(name= "customer_mobile")
	private String customer_mobile;  //账户手机
	
	@Column(name= "account_id")
	private String account_id;  //流水id
	
	@Column(name= "award_type")
	private String award_type;  //推荐类型（1 首持奖励， 2  推荐奖励）
	
	@Column(name= "coid_id")
	private Long coid_id;  //币id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种（CNY）
	
	@Column(name= "coinName")
	private String coinName;  //虚拟币种汉语名称（ 比特币 莱特币...）
	
	@Column(name= "referrals_id")
	private Long referrals_id;  //下级id
	
	@Column(name= "referrals_email")
	private String referrals_email;  //被推荐人邮箱
	
	@Column(name= "referrals_mobile")
	private String referrals_mobile;  //被推荐人手机号
	
	@Column(name= "award_radix")
	private BigDecimal award_radix;  //奖励基数
	
	@Column(name= "award_quantity")
	private BigDecimal award_quantity;  //奖励数量
	
	@Column(name= "award_num")
	private String award_num;  //奖励流水号
	
	@Column(name= "status")
	private String status;  //状态(1 奖励发放中，2 成功 ，3 失败)
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>账户id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public Long getCustomer_id() {
		return customer_id;
	}
	
	/**
	 * <p>账户id</p>
	 * @author:  lzy
	 * @param:   @param customer_id
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	
	
	/**
	 * <p>账户邮箱</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getCustomer_email() {
		return customer_email;
	}
	
	/**
	 * <p>账户邮箱</p>
	 * @author:  lzy
	 * @param:   @param customer_email
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	
	
	/**
	 * <p>账户手机</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getCustomer_mobile() {
		return customer_mobile;
	}
	
	/**
	 * <p>账户手机</p>
	 * @author:  lzy
	 * @param:   @param customer_mobile
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}
	
	
	/**
	 * <p>流水id</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getAccount_id() {
		return account_id;
	}
	
	/**
	 * <p>流水id</p>
	 * @author:  lzy
	 * @param:   @param account_id
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	
	/**
	 * <p>推荐类型（1 首持奖励， 2  推荐奖励）</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getAward_type() {
		return award_type;
	}
	
	/**
	 * <p>推荐类型（1 首持奖励， 2  推荐奖励）</p>
	 * @author:  lzy
	 * @param:   @param award_type
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setAward_type(String award_type) {
		this.award_type = award_type;
	}
	
	
	/**
	 * <p>币id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public Long getCoid_id() {
		return coid_id;
	}
	
	/**
	 * <p>币id</p>
	 * @author:  lzy
	 * @param:   @param coid_id
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setCoid_id(Long coid_id) {
		this.coid_id = coid_id;
	}
	
	
	/**
	 * <p>币种（CNY）</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种（CNY）</p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>虚拟币种汉语名称（ 比特币 莱特币...）</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getCoinName() {
		return coinName;
	}
	
	/**
	 * <p>虚拟币种汉语名称（ 比特币 莱特币...）</p>
	 * @author:  lzy
	 * @param:   @param coinName
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	
	/**
	 * <p>下级id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public Long getReferrals_id() {
		return referrals_id;
	}
	
	/**
	 * <p>下级id</p>
	 * @author:  lzy
	 * @param:   @param referrals_id
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setReferrals_id(Long referrals_id) {
		this.referrals_id = referrals_id;
	}
	
	
	/**
	 * <p>被推荐人邮箱</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getReferrals_email() {
		return referrals_email;
	}
	
	/**
	 * <p>被推荐人邮箱</p>
	 * @author:  lzy
	 * @param:   @param referrals_email
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setReferrals_email(String referrals_email) {
		this.referrals_email = referrals_email;
	}
	
	
	/**
	 * <p>被推荐人手机号</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getReferrals_mobile() {
		return referrals_mobile;
	}
	
	/**
	 * <p>被推荐人手机号</p>
	 * @author:  lzy
	 * @param:   @param referrals_mobile
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setReferrals_mobile(String referrals_mobile) {
		this.referrals_mobile = referrals_mobile;
	}
	
	
	/**
	 * <p>奖励基数</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public BigDecimal getAward_radix() {
		return award_radix;
	}
	
	/**
	 * <p>奖励基数</p>
	 * @author:  lzy
	 * @param:   @param award_radix
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setAward_radix(BigDecimal award_radix) {
		this.award_radix = award_radix;
	}
	
	
	/**
	 * <p>奖励数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public BigDecimal getAward_quantity() {
		return award_quantity;
	}
	
	/**
	 * <p>奖励数量</p>
	 * @author:  lzy
	 * @param:   @param award_quantity
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setAward_quantity(BigDecimal award_quantity) {
		this.award_quantity = award_quantity;
	}
	
	
	/**
	 * <p>奖励流水号</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getAward_num() {
		return award_num;
	}
	
	/**
	 * <p>奖励流水号</p>
	 * @author:  lzy
	 * @param:   @param award_num
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setAward_num(String award_num) {
		this.award_num = award_num;
	}
	
	
	/**
	 * <p>状态(1 奖励发放中，2 成功 ，3 失败)</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-26 10:52:06    
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * <p>状态(1 奖励发放中，2 成功 ，3 失败)</p>
	 * @author:  lzy
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-01-26 10:52:06   
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
