/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 15:26:55 
 */
package hry.cm.grade.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> CmGradeMiner </p>
 * @author:         yaozh
 * @Date :          2019-08-01 15:26:55  
 */
@Table(name="cm_grade_miner")
public class CmGradeMiner extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "grade")
	private Integer grade;  //等级
	
	@Column(name= "gradeName")
	private String gradeName;  //等级名称
	
	@Column(name= "gradeCondition")
	private BigDecimal gradeCondition;  //升级条件(自投要求)
	
	@Column(name= "profitProportion")
	private BigDecimal profitProportion;  //动态收益比例1 一代静态收益比例
	
	@Column(name= "profitProportion2")
	private BigDecimal profitProportion2;  //动态收益比例2  一代动态收益比例
	
	@Column(name= "cappingMultiple")
	private BigDecimal cappingMultiple;  //封顶倍数
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	public BigDecimal getProfitProportion2() {
		return profitProportion2;
	}

	public void setProfitProportion2(BigDecimal profitProportion2) {
		this.profitProportion2 = profitProportion2;
	}

	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-08-01 15:26:55    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-01 15:26:55   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>等级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-08-01 15:26:55    
	 */
	public Integer getGrade() {
		return grade;
	}
	
	/**
	 * <p>等级</p>
	 * @author:  yaozh
	 * @param:   @param grade
	 * @return:  void 
	 * @Date :   2019-08-01 15:26:55   
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-08-01 15:26:55    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  yaozh
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-08-01 15:26:55   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>升级条件(自投要求)</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-08-01 15:26:55    
	 */
	public BigDecimal getGradeCondition() {
		return gradeCondition;
	}
	
	/**
	 * <p>升级条件(自投要求)</p>
	 * @author:  yaozh
	 * @param:   @param gradeCondition
	 * @return:  void 
	 * @Date :   2019-08-01 15:26:55   
	 */
	public void setGradeCondition(BigDecimal gradeCondition) {
		this.gradeCondition = gradeCondition;
	}
	
	
	/**
	 * <p>动态收益比例</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-08-01 15:26:55    
	 */
	public BigDecimal getProfitProportion() {
		return profitProportion;
	}
	
	/**
	 * <p>动态收益比例</p>
	 * @author:  yaozh
	 * @param:   @param profitProportion
	 * @return:  void 
	 * @Date :   2019-08-01 15:26:55   
	 */
	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
	}
	
	
	/**
	 * <p>封顶倍数</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-08-01 15:26:55    
	 */
	public BigDecimal getCappingMultiple() {
		return cappingMultiple;
	}
	
	/**
	 * <p>封顶倍数</p>
	 * @author:  yaozh
	 * @param:   @param cappingMultiple
	 * @return:  void 
	 * @Date :   2019-08-01 15:26:55   
	 */
	public void setCappingMultiple(BigDecimal cappingMultiple) {
		this.cappingMultiple = cappingMultiple;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-08-01 15:26:55    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-08-01 15:26:55   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-08-01 15:26:55    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-08-01 15:26:55   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
