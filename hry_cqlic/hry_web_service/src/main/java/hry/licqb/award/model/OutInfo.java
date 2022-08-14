/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:53:38 
 */
package hry.licqb.award.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> OutInfo </p>
 * @author:         zhouming
 * @Date :          2019-08-13 13:53:38  
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

	@Column(name= "saasid")
	private String saasid;

	@Transient
	private Integer sort; // 个人等级
	@Transient
	private Integer teamSort; // 社区等级
	@Transient
	private Integer level; // 代数
	@Transient
	public Integer isTeamAward; // 是否发放社区奖励 0 不发放 1 发放

	public String getSaasid() {
		return saasid;
	}

	public void setSaasid(String saasid) {
		this.saasid = saasid;
	}

	public Integer getIsTeamAward() {
		return isTeamAward;
	}

	public void setIsTeamAward(Integer isTeamAward) {
		this.isTeamAward = isTeamAward;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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
	 * @Date :   2019-08-13 13:53:38    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-13 13:53:38   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-13 13:53:38    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-13 13:53:38   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-13 13:53:38    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-08-13 13:53:38   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>出局总额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-13 13:53:38    
	 */
	public BigDecimal getAllMoney() {
		return allMoney;
	}
	
	/**
	 * <p>出局总额</p>
	 * @author:  zhouming
	 * @param:   @param allMoney
	 * @return:  void 
	 * @Date :   2019-08-13 13:53:38   
	 */
	public void setAllMoney(BigDecimal allMoney) {
		this.allMoney = allMoney;
	}
	
	
	/**
	 * <p>冻结总额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-13 13:53:38    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p>冻结总额</p>
	 * @author:  zhouming
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2019-08-13 13:53:38   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	
	/**
	 * <p>可用总额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-13 13:53:38    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p>可用总额</p>
	 * @author:  zhouming
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2019-08-13 13:53:38   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p>是否出局 0: 未出局 1:已出局</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-13 13:53:38    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>是否出局 0: 未出局 1:已出局</p>
	 * @author:  zhouming
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-08-13 13:53:38   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
