/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:49:07 
 */
package hry.admin.licqb.award.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> OutInfo </p>
 * @author:         zhouming
 * @Date :          2019-08-13 13:49:07  
 */
@Table(name="lc_out_info")
public class OutInfo extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id

	@Column(name= "accountId")
	private Long accountId;  //数币账户Id

	@Column(name= "customerId")
	private Long customerId;  //用户id

	@Column(name= "coinCode")
	private String coinCode;  //币种代码

	@Column(name= "baseMoney")
	private BigDecimal baseMoney; //投资额

	@Column(name= "baseMaxMoney")
	private BigDecimal baseMaxMoney; // 最大投资额

	@Column(name= "outMultiple")
	private Integer outMultiple; // 出局倍数

	@Column(name= "allMoney")
	private BigDecimal allMoney;  //出局总额度

	@Column(name= "coldMoney")
	private BigDecimal coldMoney;  //冻结总额度

	@Column(name= "hotMoney")
	private BigDecimal hotMoney;  //可用总额度

	@Column(name= "status")
	private Integer status;  //是否出局 0: 未出局 1:已出局

	@Transient
	private String receCode; //邀请码
	@Transient
	private String email; // 邮箱
	@Transient
	private String mobilePhone;// 手机号
	@Transient
	private String levelName; // 个人等级名称
	@Transient
	private String teamLevelName; // 社区等级名称
	@Transient
	private Integer sort; // 个人等级排序
	@Transient
	private Integer teamSort; // 社区等级排序
	@Transient
	private Long outInfoId; // 出局信息表Id
	@Transient
	private Integer level; // 代数

	@Transient
	private UserCommendAsset userCommendAsset; // 用户资产信息

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getOutInfoId() {
		return outInfoId;
	}

	public void setOutInfoId(Long outInfoId) {
		this.outInfoId = outInfoId;
	}

	public UserCommendAsset getUserCommendAsset() {
		return userCommendAsset;
	}

	public void setUserCommendAsset(UserCommendAsset userCommendAsset) {
		this.userCommendAsset = userCommendAsset;
	}

	public String getReceCode() {
		return receCode;
	}

	public void setReceCode(String receCode) {
		this.receCode = receCode;
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

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getTeamLevelName() {
		return teamLevelName;
	}

	public void setTeamLevelName(String teamLevelName) {
		this.teamLevelName = teamLevelName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getTeamSort() {
		return teamSort;
	}

	public void setTeamSort(Integer teamSort) {
		this.teamSort = teamSort;
	}

	public BigDecimal getBaseMoney() {
		return baseMoney;
	}

	public void setBaseMoney(BigDecimal baseMoney) {
		this.baseMoney = baseMoney;
	}

	public BigDecimal getBaseMaxMoney() {
		return baseMaxMoney;
	}

	public void setBaseMaxMoney(BigDecimal baseMaxMoney) {
		this.baseMaxMoney = baseMaxMoney;
	}

	public Integer getOutMultiple() {
		return outMultiple;
	}

	public void setOutMultiple(Integer outMultiple) {
		this.outMultiple = outMultiple;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long
	 * @Date :   2019-08-13 13:49:07
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2019-08-13 13:49:07
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long
	 * @Date :   2019-08-13 13:49:07
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void
	 * @Date :   2019-08-13 13:49:07
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @return:  String
	 * @Date :   2019-08-13 13:49:07
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void
	 * @Date :   2019-08-13 13:49:07
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}


	/**
	 * <p>出局总额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal
	 * @Date :   2019-08-13 13:49:07
	 */
	public BigDecimal getAllMoney() {
		return allMoney;
	}

	/**
	 * <p>出局总额</p>
	 * @author:  zhouming
	 * @param:   @param allMoney
	 * @return:  void
	 * @Date :   2019-08-13 13:49:07
	 */
	public void setAllMoney(BigDecimal allMoney) {
		this.allMoney = allMoney;
	}


	/**
	 * <p>冻结总额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal
	 * @Date :   2019-08-13 13:49:07
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}

	/**
	 * <p>冻结总额</p>
	 * @author:  zhouming
	 * @param:   @param coldMoney
	 * @return:  void
	 * @Date :   2019-08-13 13:49:07
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}


	/**
	 * <p>可用总额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal
	 * @Date :   2019-08-13 13:49:07
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}

	/**
	 * <p>可用总额</p>
	 * @author:  zhouming
	 * @param:   @param hotMoney
	 * @return:  void
	 * @Date :   2019-08-13 13:49:07
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}


	/**
	 * <p>是否出局 0: 未出局 1:已出局</p>
	 * @author:  zhouming
	 * @return:  Integer
	 * @Date :   2019-08-13 13:49:07
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * <p>是否出局 0: 未出局 1:已出局</p>
	 * @author:  zhouming
	 * @param:   @param status
	 * @return:  void
	 * @Date :   2019-08-13 13:49:07
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	

}
