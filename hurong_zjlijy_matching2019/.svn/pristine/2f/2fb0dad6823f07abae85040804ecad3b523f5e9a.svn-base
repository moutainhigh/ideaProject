/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-08 15:28:23 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IntegralDistributionGrade </p>
 * @author:         kongdb
 * @Date :          2019-01-08 15:28:23  
 */
@Table(name="shop_integral_distribution_grade")
public class IntegralDistributionGrade extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //索引id
	
	@Column(name= "gradeName")
	private String gradeName;  //等级名称
	
	@Column(name= "gradeLevel")
	private Integer gradeLevel;  //数字级别
	
	@Column(name= "integralScale")
	private BigDecimal integralScale;  //积分币比例
	
	@Column(name= "computingScale")
	private BigDecimal computingScale;  //算力比例
	
	@Column(name= "operator")
	private String operator;  //操作人
	
	
	
	
	/**
	 * <p>索引id</p>
	 * @author:  kongdb
	 * @return:  Long 
	 * @Date :   2019-01-08 15:28:23    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>索引id</p>
	 * @author:  kongdb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-08 15:28:23   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-08 15:28:23    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  kongdb
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-01-08 15:28:23   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>数字级别</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-08 15:28:23    
	 */
	public Integer getGradeLevel() {
		return gradeLevel;
	}
	
	/**
	 * <p>数字级别</p>
	 * @author:  kongdb
	 * @param:   @param gradeLevel
	 * @return:  void 
	 * @Date :   2019-01-08 15:28:23   
	 */
	public void setGradeLevel(Integer gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	
	
	/**
	 * <p>积分币比例</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-08 15:28:23    
	 */
	public BigDecimal getIntegralScale() {
		return integralScale;
	}
	
	/**
	 * <p>积分币比例</p>
	 * @author:  kongdb
	 * @param:   @param integralScale
	 * @return:  void 
	 * @Date :   2019-01-08 15:28:23   
	 */
	public void setIntegralScale(BigDecimal integralScale) {
		this.integralScale = integralScale;
	}
	
	
	/**
	 * <p>算力比例</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-08 15:28:23    
	 */
	public BigDecimal getComputingScale() {
		return computingScale;
	}
	
	/**
	 * <p>算力比例</p>
	 * @author:  kongdb
	 * @param:   @param computingScale
	 * @return:  void 
	 * @Date :   2019-01-08 15:28:23   
	 */
	public void setComputingScale(BigDecimal computingScale) {
		this.computingScale = computingScale;
	}
	
	
	/**
	 * <p>操作人</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-08 15:28:23    
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * <p>操作人</p>
	 * @author:  kongdb
	 * @param:   @param operator
	 * @return:  void 
	 * @Date :   2019-01-08 15:28:23   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	

}
