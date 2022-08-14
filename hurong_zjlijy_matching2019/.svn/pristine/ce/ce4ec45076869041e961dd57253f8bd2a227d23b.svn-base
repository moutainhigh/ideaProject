/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-16 14:03:55 
 */
package hry.financail.financing.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> AppFinancialRecommend </p>
 * @author:         jidn
 * @Date :          2019-04-16 14:03:55  
 */
@Table(name="app_financial_recommend")
public class AppFinancialRecommend extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "pid")
	private Long pid;  //父节点
	
	@Column(name= "uid")
	private Long uid;  //用户id
	
	@Column(name= "vipLevel")
	private Integer vipLevel;  //vip等级 默认0  一共有 0,1,2,3,4,5

	@Column(name= "rewardRatio")
	private BigDecimal rewardRatio;  //奖励比例

	@Column(name= "level")
	private Integer level;//层级

	@Column(name= "pendingMoney")
	private BigDecimal pendingMoney;//待发放金额

	@Column(name= "issuedMoney")
	private BigDecimal issuedMoney;//已发放金额

	@Transient
	private Integer recommendNumber;//推荐人数
	@Transient
	private Integer financialOderNumber;//理财订单数量
	@Transient
	private Integer directReferrerLevelNumber;//直接推荐人和自己等级相等的人的数量
	@Transient
	private String email;
	@Transient
	private String mobilePhone;

	public BigDecimal getPendingMoney() {
		return pendingMoney;
	}

	public void setPendingMoney(BigDecimal pendingMoney) {
		this.pendingMoney = pendingMoney;
	}

	public BigDecimal getIssuedMoney() {
		return issuedMoney;
	}

	public void setIssuedMoney(BigDecimal issuedMoney) {
		this.issuedMoney = issuedMoney;
	}

	public Integer getDirectReferrerLevelNumber() {
		return directReferrerLevelNumber;
	}

	public void setDirectReferrerLevelNumber(Integer directReferrerLevelNumber) {
		this.directReferrerLevelNumber = directReferrerLevelNumber;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public BigDecimal getRewardRatio() {
		return rewardRatio;
	}

	public void setRewardRatio(BigDecimal rewardRatio) {
		this.rewardRatio = rewardRatio;
	}

	public Integer getRecommendNumber() {
		return recommendNumber;
	}

	public void setRecommendNumber(Integer recommendNumber) {
		this.recommendNumber = recommendNumber;
	}

	public Integer getFinancialOderNumber() {
		return financialOderNumber;
	}

	public void setFinancialOderNumber(Integer financialOderNumber) {
		this.financialOderNumber = financialOderNumber;
	}

	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-16 14:03:55    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-16 14:03:55   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>父节点</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-16 14:03:55    
	 */
	public Long getPid() {
		return pid;
	}
	
	/**
	 * <p>父节点</p>
	 * @author:  jidn
	 * @param:   @param pid
	 * @return:  void 
	 * @Date :   2019-04-16 14:03:55   
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-16 14:03:55    
	 */
	public Long getUid() {
		return uid;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  jidn
	 * @param:   @param uid
	 * @return:  void 
	 * @Date :   2019-04-16 14:03:55   
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	
	/**
	 * <p>vip等级 默认0  一共有 0,1,2,3,4,5</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-16 14:03:55    
	 */
	public Integer getVipLevel() {
		return vipLevel;
	}
	
	/**
	 * <p>vip等级 默认0  一共有 0,1,2,3,4,5</p>
	 * @author:  jidn
	 * @param:   @param vipLevel
	 * @return:  void 
	 * @Date :   2019-04-16 14:03:55   
	 */
	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}
	
	

}
