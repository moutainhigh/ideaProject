/**
 * Copyright:
 * @author:      lzy
 * @version:     V1.0
 * @Date:        2019-04-16 19:48:29
 */
package hry.admin.klg.limit.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> KlgCustomerLevel </p>
 * @author:         lzy
 * @Date :          2019-04-16 19:48:29
 */
@Table(name="klg_customer_level")
public class KlgCustomerLevel extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "customerId")
	private Long customerId;  //用户id

	@Column(name= "levelId")
	private Long levelId;  //等级id

	@Column(name= "levelName")
	private String levelName;  //等级名称

	@Column(name= "sort")
	private Integer sort;  //等级排序

	@Column(name= "nowSort")
	private Integer nowSort;  //当前等级排序

	@Column(name= "gradation")
	private BigDecimal gradation;  //级别差

	@Column(name= "fixedGradation")
	private BigDecimal fixedGradation;  //指定级差奖励

	@Column(name= "pointAlgebra")
	private Integer pointAlgebra;  //见点代数

	@Column(name= "rewardNum")
	private BigDecimal rewardNum;  //奖金额度

	public Integer getNowSort() {
		return nowSort;
	}

	public void setNowSort(Integer nowSort) {
		this.nowSort = nowSort;
	}

	@Transient // 不与数据库映射的字段
	private String mobilePhone;   //用户手机号

	@Transient // 不与数据库映射的字段
	private String email;      	//用户email

	public BigDecimal getFixedGradation() {
		return fixedGradation;
	}

	public void setFixedGradation(BigDecimal fixedGradation) {
		this.fixedGradation = fixedGradation;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long
	 * @Date :   2019-04-16 19:48:29
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2019-04-16 19:48:29
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @return:  Long
	 * @Date :   2019-04-16 19:48:29
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void
	 * @Date :   2019-04-16 19:48:29
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	/**
	 * <p>等级id</p>
	 * @author:  lzy
	 * @return:  Long
	 * @Date :   2019-04-16 19:48:29
	 */
	public Long getLevelId() {
		return levelId;
	}

	/**
	 * <p>等级id</p>
	 * @author:  lzy
	 * @param:   @param levelId
	 * @return:  void
	 * @Date :   2019-04-16 19:48:29
	 */
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}


	/**
	 * <p>等级名称</p>
	 * @author:  lzy
	 * @return:  String
	 * @Date :   2019-04-16 19:48:29
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * <p>等级名称</p>
	 * @author:  lzy
	 * @param:   @param levelName
	 * @return:  void
	 * @Date :   2019-04-16 19:48:29
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


	/**
	 * <p>等级排序</p>
	 * @author:  lzy
	 * @return:  Integer
	 * @Date :   2019-04-16 19:48:29
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * <p>等级排序</p>
	 * @author:  lzy
	 * @param:   @param sort
	 * @return:  void
	 * @Date :   2019-04-16 19:48:29
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}


	/**
	 * <p>级别差</p>
	 * @author:  lzy
	 * @return:  BigDecimal
	 * @Date :   2019-04-16 19:48:29
	 */
	public BigDecimal getGradation() {
		return gradation;
	}

	/**
	 * <p>级别差</p>
	 * @author:  lzy
	 * @param:   @param gradation
	 * @return:  void
	 * @Date :   2019-04-16 19:48:29
	 */
	public void setGradation(BigDecimal gradation) {
		this.gradation = gradation;
	}


	/**
	 * <p>见点代数</p>
	 * @author:  lzy
	 * @return:  Integer
	 * @Date :   2019-04-16 19:48:29
	 */
	public Integer getPointAlgebra() {
		return pointAlgebra;
	}

	/**
	 * <p>见点代数</p>
	 * @author:  lzy
	 * @param:   @param pointAlgebra
	 * @return:  void
	 * @Date :   2019-04-16 19:48:29
	 */
	public void setPointAlgebra(Integer pointAlgebra) {
		this.pointAlgebra = pointAlgebra;
	}


	/**
	 * <p>奖金额度</p>
	 * @author:  lzy
	 * @return:  BigDecimal
	 * @Date :   2019-04-16 19:48:29
	 */
	public BigDecimal getRewardNum() {
		return rewardNum;
	}

	/**
	 * <p>奖金额度</p>
	 * @author:  lzy
	 * @param:   @param rewardNum
	 * @return:  void
	 * @Date :   2019-04-16 19:48:29
	 */
	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}



}
