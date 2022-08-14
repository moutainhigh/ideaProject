/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-01-16 15:41:30 
 */
package hry.mall.integral.model;
import hry.bean.BaseModel;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> CustomerIntegral </p>
 * @author:         zhouming
 * @Date :          2019-01-16 15:41:30  
 */
@Table(name="shop_customer_integral")
public class CustomerIntegral extends BaseModel {

	public static final String TYPE_ADD = "add";//加积分
	public static final String TYPE_SUB = "sub";//减积分
	public static final String TYPE_FREEZE = "freeze";//冻结积分
	public static final String TYPE_FREEZE_SUCCESS = "freeze_success";//冻结转成功（冻结减，总积分减）
	public static final String TYPE_FREEZE_FAIL = "freeze_fail";//冻结转失败（冻结减，总积分加，可用积分加）

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键

	@Column(name= "memberId")
	private Long memberId;  //用户Id

	@Column(name= "integralType")
	private String integralType;  //积分类型

	@Column(name= "integralName")
	private String integralName;  //积分名称

	@Column(name= "integralPrice")
	private BigDecimal integralPrice;  //积分价值

	@Column(name= "totalIntegral")
	private BigDecimal totalIntegral;  //总积分值

	@Column(name= "availableIntegral")
	private BigDecimal availableIntegral;  //可用积分值

	@Column(name= "freezeIntegral")
	private BigDecimal freezeIntegral;  //冻结积分值

	@Column(name= "remark")
	private String remark;  //

	@Column(name= "saasId")
	private String saasId;  //saasId

	@Column(name= "levelId")
	private Long levelId;  //用户等级id

	@Column(name= "levelNumber")
	private Integer levelNumber; // 等级级别

	@Column(name= "totalQuota")
	private BigDecimal totalQuota; // 总额度

	@Column(name= "baseQuota")
	private BigDecimal baseQuota; // 基础额度

	@Column(name= "recommendQuota")
	private BigDecimal recommendQuota; // 推广额度

	@Column(name= "maxQuota")
	private BigDecimal maxQuota; // 最大限额

	@Column(name= "teamId")
	private Long teamId;  //团队身份Id

	@Column(name= "teamNumber")
	private Integer teamNumber; //团队身份级别

	@Column(name= "startDate")
	private Date startDate;  //开始日期

	@Column(name= "endDate")
	private Date endDate;  //结束日期

	@Column(name= "distributeDate")
	private Date distributeDate;  //分红计划分发时间

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDistributeDate() {
		return distributeDate;
	}

	public void setDistributeDate(Date distributeDate) {
		this.distributeDate = distributeDate;
	}

	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @return:  Long
	 * @Date :   2019-01-16 15:41:30
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long
	 * @Date :   2019-01-16 15:41:30
	 */
	public Long getMemberId() {
		return memberId;
	}

	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param memberId
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


	/**
	 * <p>积分类型</p>
	 * @author:  zhouming
	 * @return:  String
	 * @Date :   2019-01-16 15:41:30
	 */
	public String getIntegralType() {
		return integralType;
	}

	/**
	 * <p>积分类型</p>
	 * @author:  zhouming
	 * @param:   @param integralType
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setIntegralType(String integralType) {
		this.integralType = integralType;
	}


	/**
	 * <p>积分名称</p>
	 * @author:  zhouming
	 * @return:  String
	 * @Date :   2019-01-16 15:41:30
	 */
	public String getIntegralName() {
		return integralName;
	}

	/**
	 * <p>积分名称</p>
	 * @author:  zhouming
	 * @param:   @param integralName
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setIntegralName(String integralName) {
		this.integralName = integralName;
	}


	/**
	 * <p>积分价值</p>
	 * @author:  zhouming
	 * @return:  BigDecimal
	 * @Date :   2019-01-16 15:41:30
	 */
	public BigDecimal getIntegralPrice() {
		return integralPrice;
	}

	/**
	 * <p>积分价值</p>
	 * @author:  zhouming
	 * @param:   @param integralPrice
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setIntegralPrice(BigDecimal integralPrice) {
		this.integralPrice = integralPrice;
	}


	/**
	 * <p>总积分值</p>
	 * @author:  zhouming
	 * @return:  BigDecimal
	 * @Date :   2019-01-16 15:41:30
	 */
	public BigDecimal getTotalIntegral() {
		return totalIntegral;
	}

	/**
	 * <p>总积分值</p>
	 * @author:  zhouming
	 * @param:   @param totalIntegral
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setTotalIntegral(BigDecimal totalIntegral) {
		this.totalIntegral = totalIntegral;
	}


	/**
	 * <p>可用积分值</p>
	 * @author:  zhouming
	 * @return:  BigDecimal
	 * @Date :   2019-01-16 15:41:30
	 */
	public BigDecimal getAvailableIntegral() {
		return availableIntegral;
	}

	/**
	 * <p>可用积分值</p>
	 * @author:  zhouming
	 * @param:   @param availableIntegral
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setAvailableIntegral(BigDecimal availableIntegral) {
		this.availableIntegral = availableIntegral;
	}


	/**
	 * <p>冻结积分值</p>
	 * @author:  zhouming
	 * @return:  BigDecimal
	 * @Date :   2019-01-16 15:41:30
	 */
	public BigDecimal getFreezeIntegral() {
		return freezeIntegral;
	}

	/**
	 * <p>冻结积分值</p>
	 * @author:  zhouming
	 * @param:   @param freezeIntegral
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setFreezeIntegral(BigDecimal freezeIntegral) {
		this.freezeIntegral = freezeIntegral;
	}


	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String
	 * @Date :   2019-01-16 15:41:30
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * <p>saasId</p>
	 * @author:  zhouming
	 * @return:  String
	 * @Date :   2019-01-16 15:41:30
	 */
	public String getSaasId() {
		return saasId;
	}

	/**
	 * <p>saasId</p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void
	 * @Date :   2019-01-16 15:41:30
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}


	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public Integer getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	public BigDecimal getTotalQuota() {
		return totalQuota;
	}

	public void setTotalQuota(BigDecimal totalQuota) {
		this.totalQuota = totalQuota;
	}

	public BigDecimal getBaseQuota() {
		return baseQuota;
	}

	public void setBaseQuota(BigDecimal baseQuota) {
		this.baseQuota = baseQuota;
	}

	public BigDecimal getRecommendQuota() {
		return recommendQuota;
	}

	public void setRecommendQuota(BigDecimal recommendQuota) {
		this.recommendQuota = recommendQuota;
	}

	public BigDecimal getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(BigDecimal maxQuota) {
		this.maxQuota = maxQuota;
	}
}
