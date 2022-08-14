/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 17:08:11 
 */
package hry.cm2.grade.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm2GradeMinercamps </p>
 * @author:         yaozh
 * @Date :          2019-10-14 17:08:11  
 */
@Table(name="cm2_grade_minercamps")
public class Cm2GradeMinercamps extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "grade")
	private Integer grade;  //等级
	
	@Column(name= "gradeName")
	private String gradeName;  //等级名称
	
	@Column(name= "thisInvestment")
	private BigDecimal thisInvestment;  //自投要求
	
	@Column(name= "teamInvestment")
	private BigDecimal teamInvestment;  //团队投入要求
	
	@Column(name= "maxTeamInvestment")
	private BigDecimal maxTeamInvestment;  //最大线投入要求
	
	@Column(name= "teamProfitProportion")
	private BigDecimal teamProfitProportion;  //团队收入比例
	
	@Column(name= "isBonus")
	private Integer isBonus;  //是否加权分红 1否 2是
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>等级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public Integer getGrade() {
		return grade;
	}
	
	/**
	 * <p>等级</p>
	 * @author:  yaozh
	 * @param:   @param grade
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  yaozh
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>自投要求</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public BigDecimal getThisInvestment() {
		return thisInvestment;
	}
	
	/**
	 * <p>自投要求</p>
	 * @author:  yaozh
	 * @param:   @param thisInvestment
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setThisInvestment(BigDecimal thisInvestment) {
		this.thisInvestment = thisInvestment;
	}
	
	
	/**
	 * <p>团队投入要求</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public BigDecimal getTeamInvestment() {
		return teamInvestment;
	}
	
	/**
	 * <p>团队投入要求</p>
	 * @author:  yaozh
	 * @param:   @param teamInvestment
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setTeamInvestment(BigDecimal teamInvestment) {
		this.teamInvestment = teamInvestment;
	}
	
	
	/**
	 * <p>最大线投入要求</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public BigDecimal getMaxTeamInvestment() {
		return maxTeamInvestment;
	}
	
	/**
	 * <p>最大线投入要求</p>
	 * @author:  yaozh
	 * @param:   @param maxTeamInvestment
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setMaxTeamInvestment(BigDecimal maxTeamInvestment) {
		this.maxTeamInvestment = maxTeamInvestment;
	}
	
	
	/**
	 * <p>团队收入比例</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public BigDecimal getTeamProfitProportion() {
		return teamProfitProportion;
	}
	
	/**
	 * <p>团队收入比例</p>
	 * @author:  yaozh
	 * @param:   @param teamProfitProportion
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setTeamProfitProportion(BigDecimal teamProfitProportion) {
		this.teamProfitProportion = teamProfitProportion;
	}
	
	
	/**
	 * <p>是否加权分红 1否 2是</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public Integer getIsBonus() {
		return isBonus;
	}
	
	/**
	 * <p>是否加权分红 1否 2是</p>
	 * @author:  yaozh
	 * @param:   @param isBonus
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setIsBonus(Integer isBonus) {
		this.isBonus = isBonus;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 17:08:11    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-10-14 17:08:11   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
