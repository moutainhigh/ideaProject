/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-17 15:51:36 
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
 * <p> Cm2GradeHierarchy </p>
 * @author:         yaozh
 * @Date :          2019-10-17 15:51:36  
 */
@Table(name="cm2_grade_hierarchy")
public class Cm2GradeHierarchy extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "hierarchy")
	private Integer hierarchy;  //层级
	
	@Column(name= "profitProportion")
	private BigDecimal profitProportion;  //收益比例
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-17 15:51:36    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-10-17 15:51:36   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>层级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-17 15:51:36    
	 */
	public Integer getHierarchy() {
		return hierarchy;
	}
	
	/**
	 * <p>层级</p>
	 * @author:  yaozh
	 * @param:   @param hierarchy
	 * @return:  void 
	 * @Date :   2019-10-17 15:51:36   
	 */
	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}
	
	
	/**
	 * <p>收益比例</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-17 15:51:36    
	 */
	public BigDecimal getProfitProportion() {
		return profitProportion;
	}
	
	/**
	 * <p>收益比例</p>
	 * @author:  yaozh
	 * @param:   @param profitProportion
	 * @return:  void 
	 * @Date :   2019-10-17 15:51:36   
	 */
	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-17 15:51:36    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-10-17 15:51:36   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-17 15:51:36    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-10-17 15:51:36   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
