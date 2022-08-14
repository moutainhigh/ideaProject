package hry.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p> AppFinancialRecommend </p>
 * @author:         jidn
 * @Date :          2019-04-16 14:03:55  
 */
public class AppFinancialRecommend implements Serializable {
	
	
	private Long id;  //
	
	private Long pid;  //父节点
	
	private Long uid;  //用户id
	
	private Integer vipLevel;  //vip等级 默认0  一共有 0,1,2,3,4,5

	private BigDecimal rewardRatio;  //奖励比例

	private Integer level;//层级

	private BigDecimal pendingMoney;//待发放金额

	private BigDecimal issuedMoney;//已发放金额

	private Date created;

	private Integer recommendNumber;//推荐人数
	private Integer financialOderNumber;//理财订单数量
	private Integer directReferrerLevelNumber;//直接推荐人和自己等级相等的人的数量
	private String email;
	private String mobilePhone;
	private String ifRealName;
	private Integer productCount;


	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

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
