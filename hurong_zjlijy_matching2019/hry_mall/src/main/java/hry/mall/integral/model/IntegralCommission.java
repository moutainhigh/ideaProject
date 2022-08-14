/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-31 14:33:30 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> IntegralCommission </p>
 * @author:         luyue
 * @Date :          2019-05-31 14:33:30  
 */
@Table(name="shop_integral_commission")
public class IntegralCommission extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id(返佣者id)
	
	@Column(name= "levelId")
	private Long levelId;  //返佣者等级Id
	
	@Column(name= "levelNumber")
	private Integer levelNumber;  //返佣者等级级别
	
	@Column(name= "teamId")
	private Long teamId;  //团队身份Id
	
	@Column(name= "teamNumber")
	private Integer teamNumber;  //团队身份级别
	
	@Column(name= "recommendId")
	private Long recommendId;  //被推荐人id
	
	@Column(name= "recommendPhone")
	private String recommendPhone;  //被推荐人手机号
	
	@Column(name= "recommendLevelId")
	private Long recommendLevelId;  //被推荐等级Id
	
	@Column(name= "recommendLevelNumber")
	private Integer recommendLevelNumber;  //被推荐等级级别
	
	@Column(name= "type")
	private String type;  //1直推奖励，2拓展(作为二级推荐人奖励)，3服务奖励(组长的团奖)，4福利奖励(服务中心的团奖)，5静态分红
	
	@Column(name= "transType")
	private String transType;  //加减类型，1增加，2减少
	
	@Column(name= "count")
	private BigDecimal count;  //电子券数量
	
	@Column(name= "rate")
	private BigDecimal rate;  //比例
	
	@Column(name= "money")
	private BigDecimal money;  //激活金额
	
	@Column(name= "number")
	private String number;  //编号
	
	@Column(name= "status")
	private Integer status;  //1成功
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Transient
	private String commissionName;//奖励 名称

	public String getCommissionName() {
		return commissionName;
	}

	public void setCommissionName(String commissionName) {
		this.commissionName = commissionName;
	}

	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id(返佣者id)</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id(返佣者id)</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>返佣者等级Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Long getLevelId() {
		return levelId;
	}
	
	/**
	 * <p>返佣者等级Id</p>
	 * @author:  luyue
	 * @param:   @param levelId
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	
	/**
	 * <p>返佣者等级级别</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Integer getLevelNumber() {
		return levelNumber;
	}
	
	/**
	 * <p>返佣者等级级别</p>
	 * @author:  luyue
	 * @param:   @param levelNumber
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	
	/**
	 * <p>团队身份Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Long getTeamId() {
		return teamId;
	}
	
	/**
	 * <p>团队身份Id</p>
	 * @author:  luyue
	 * @param:   @param teamId
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
	
	/**
	 * <p>团队身份级别</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Integer getTeamNumber() {
		return teamNumber;
	}
	
	/**
	 * <p>团队身份级别</p>
	 * @author:  luyue
	 * @param:   @param teamNumber
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}
	
	
	/**
	 * <p>被推荐人id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Long getRecommendId() {
		return recommendId;
	}
	
	/**
	 * <p>被推荐人id</p>
	 * @author:  luyue
	 * @param:   @param recommendId
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setRecommendId(Long recommendId) {
		this.recommendId = recommendId;
	}
	
	
	/**
	 * <p>被推荐人手机号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public String getRecommendPhone() {
		return recommendPhone;
	}
	
	/**
	 * <p>被推荐人手机号</p>
	 * @author:  luyue
	 * @param:   @param recommendPhone
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}
	
	
	/**
	 * <p>被推荐等级Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Long getRecommendLevelId() {
		return recommendLevelId;
	}
	
	/**
	 * <p>被推荐等级Id</p>
	 * @author:  luyue
	 * @param:   @param recommendLevelId
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setRecommendLevelId(Long recommendLevelId) {
		this.recommendLevelId = recommendLevelId;
	}
	
	
	/**
	 * <p>被推荐等级级别</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Integer getRecommendLevelNumber() {
		return recommendLevelNumber;
	}
	
	/**
	 * <p>被推荐等级级别</p>
	 * @author:  luyue
	 * @param:   @param recommendLevelNumber
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setRecommendLevelNumber(Integer recommendLevelNumber) {
		this.recommendLevelNumber = recommendLevelNumber;
	}
	
	
	/**
	 * <p>1直推奖励，2拓展(作为二级推荐人奖励)，3服务奖励(组长的团奖)，4服务奖励(组长的团奖)，5静态分红</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>1直推奖励，2拓展(作为二级推荐人奖励)，3服务奖励(组长的团奖)，4服务奖励(组长的团奖)，5静态分红</p>
	 * @author:  luyue
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>加减类型，1增加，2减少</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public String getTransType() {
		return transType;
	}
	
	/**
	 * <p>加减类型，1增加，2减少</p>
	 * @author:  luyue
	 * @param:   @param transType
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	
	/**
	 * <p>电子券数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public BigDecimal getCount() {
		return count;
	}
	
	/**
	 * <p>电子券数量</p>
	 * @author:  luyue
	 * @param:   @param count
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	
	
	/**
	 * <p>比例</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public BigDecimal getRate() {
		return rate;
	}
	
	/**
	 * <p>比例</p>
	 * @author:  luyue
	 * @param:   @param rate
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	
	/**
	 * <p>激活金额</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>激活金额</p>
	 * @author:  luyue
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p>编号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * <p>编号</p>
	 * @author:  luyue
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	/**
	 * <p>1成功</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>1成功</p>
	 * @author:  luyue
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-31 14:33:30    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-31 14:33:30   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
