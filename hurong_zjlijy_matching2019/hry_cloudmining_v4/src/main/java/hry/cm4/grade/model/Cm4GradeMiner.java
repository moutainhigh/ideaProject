/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:02:31 
 */
package hry.cm4.grade.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm4GradeMiner </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:02:31  
 */
@Table(name="cm4_grade_miner")
public class Cm4GradeMiner extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "grade")
	private Integer grade;  //等级
	
	@Column(name= "gradeName")
	private String gradeName;  //等级名称
	
	@Column(name= "gradeCondition")
	private BigDecimal gradeCondition;  //升级条件(USDT自投要求)
	
	@Column(name= "teamInvestment")
	private BigDecimal teamInvestment;  //伞下USDT投入要求
	
	@Column(name= "hierarchy")
	private Integer hierarchy;  //奖励层级
	
	@Column(name= "effectiveDirectNum")
	private Integer effectiveDirectNum;  //有效直推人数
	
	@Column(name= "profitProportion")
	private BigDecimal profitProportion;  //动态收益比例
	
	@Column(name= "profitProportion2")
	private BigDecimal profitProportion2;  //动态收益比例2
	
	@Column(name= "cappingMultiple")
	private BigDecimal cappingMultiple;  //封顶倍数
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>等级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public Integer getGrade() {
		return grade;
	}
	
	/**
	 * <p>等级</p>
	 * @author:  yaozh
	 * @param:   @param grade
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  yaozh
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>升级条件(USDT自投要求)</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public BigDecimal getGradeCondition() {
		return gradeCondition;
	}
	
	/**
	 * <p>升级条件(USDT自投要求)</p>
	 * @author:  yaozh
	 * @param:   @param gradeCondition
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setGradeCondition(BigDecimal gradeCondition) {
		this.gradeCondition = gradeCondition;
	}
	
	
	/**
	 * <p>伞下USDT投入要求</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public BigDecimal getTeamInvestment() {
		return teamInvestment;
	}
	
	/**
	 * <p>伞下USDT投入要求</p>
	 * @author:  yaozh
	 * @param:   @param teamInvestment
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setTeamInvestment(BigDecimal teamInvestment) {
		this.teamInvestment = teamInvestment;
	}
	
	
	/**
	 * <p>奖励层级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public Integer getHierarchy() {
		return hierarchy;
	}
	
	/**
	 * <p>奖励层级</p>
	 * @author:  yaozh
	 * @param:   @param hierarchy
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}
	
	
	/**
	 * <p>有效直推人数</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public Integer getEffectiveDirectNum() {
		return effectiveDirectNum;
	}
	
	/**
	 * <p>有效直推人数</p>
	 * @author:  yaozh
	 * @param:   @param effectiveDirectNum
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setEffectiveDirectNum(Integer effectiveDirectNum) {
		this.effectiveDirectNum = effectiveDirectNum;
	}
	
	
	/**
	 * <p>动态收益比例</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public BigDecimal getProfitProportion() {
		return profitProportion;
	}
	
	/**
	 * <p>动态收益比例</p>
	 * @author:  yaozh
	 * @param:   @param profitProportion
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
	}
	
	
	/**
	 * <p>动态收益比例2</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public BigDecimal getProfitProportion2() {
		return profitProportion2;
	}
	
	/**
	 * <p>动态收益比例2</p>
	 * @author:  yaozh
	 * @param:   @param profitProportion2
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setProfitProportion2(BigDecimal profitProportion2) {
		this.profitProportion2 = profitProportion2;
	}
	
	
	/**
	 * <p>封顶倍数</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public BigDecimal getCappingMultiple() {
		return cappingMultiple;
	}
	
	/**
	 * <p>封顶倍数</p>
	 * @author:  yaozh
	 * @param:   @param cappingMultiple
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setCappingMultiple(BigDecimal cappingMultiple) {
		this.cappingMultiple = cappingMultiple;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:02:31    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-11-21 10:02:31   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
