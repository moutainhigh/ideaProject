/**
 * Copyright:
 * @author:      lzy
 * @version:     V1.0
 * @Date:        2019-04-17 14:29:49
 */
package hry.klg.level.model;


import hry.bean.BaseModel;


import javax.annotation.Resource;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> KlgTeamlevel </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:29:49
 */
@Table(name="klg_teamlevel")
public class KlgTeamlevel extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "customerId")
	private Long customerId;  //用户Id

	@Column(name= "parentId")
	private Long parentId;  //推荐人Id

	@Column(name= "level")
	private Integer level;  //层级

	@Column(name= "saasId")
	private String saasId;  //


	@Transient
	private Integer sort;  //等级排序
	@Transient
	private Integer nowSort;  //等级排序
	@Transient
	private BigDecimal gradation;  //级别差
	@Transient
	private Integer pointAlgebra;  //见点代数
	@Transient
	private BigDecimal rewardNum;  //奖金额度
	@Transient
	private BigDecimal fixedGradation;  //  改完后台指定级差 （如果有指定则按照此级差计算）

	public Integer getNowSort() {
		return nowSort;
	}

	public void setNowSort(Integer nowSort) {
		this.nowSort = nowSort;
	}

	public BigDecimal getFixedGradation() {
		return fixedGradation;
	}

	public void setFixedGradation(BigDecimal fixedGradation) {
		this.fixedGradation = fixedGradation;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public BigDecimal getGradation() {
		return gradation;
	}

	public void setGradation(BigDecimal gradation) {
		this.gradation = gradation;
	}

	public Integer getPointAlgebra() {
		return pointAlgebra;
	}

	public void setPointAlgebra(Integer pointAlgebra) {
		this.pointAlgebra = pointAlgebra;
	}

	public BigDecimal getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long
	 * @Date :   2019-04-17 14:29:49
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2019-04-17 14:29:49
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @return:  Long
	 * @Date :   2019-04-17 14:29:49
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void
	 * @Date :   2019-04-17 14:29:49
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	/**
	 * <p>推荐人Id</p>
	 * @author:  lzy
	 * @return:  Long
	 * @Date :   2019-04-17 14:29:49
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * <p>推荐人Id</p>
	 * @author:  lzy
	 * @param:   @param parentId
	 * @return:  void
	 * @Date :   2019-04-17 14:29:49
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	/**
	 * <p>层级</p>
	 * @author:  lzy
	 * @return:  Integer
	 * @Date :   2019-04-17 14:29:49
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * <p>层级</p>
	 * @author:  lzy
	 * @param:   @param level
	 * @return:  void
	 * @Date :   2019-04-17 14:29:49
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}


	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String
	 * @Date :   2019-04-17 14:29:49
	 */
	public String getSaasId() {
		return saasId;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param saasId
	 * @return:  void
	 * @Date :   2019-04-17 14:29:49
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}



}
