/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 17:04:27 
 */
package hry.cm.grade.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> CmGradeRecord </p>
 * @author:         yaozh
 * @Date :          2019-08-01 17:04:27  
 */
@Table(name="cm_grade_record")
public class CmGradeRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "oldGrade")
	private Integer oldGrade;  //原等级
	
	@Column(name= "oldGradeName")
	private String oldGradeName;  //旧等级名称
	
	@Column(name= "newGrade")
	private Integer newGrade;  //新等级
	
	@Column(name= "newGradeName")
	private String newGradeName;  //新等级名称
	
	@Column(name= "gradeType")
	private Integer gradeType;  //等级类型 1矿工等级  2矿场等级
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-08-01 17:04:27    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-01 17:04:27   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-08-01 17:04:27    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozh
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-01 17:04:27   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>原等级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-08-01 17:04:27    
	 */
	public Integer getOldGrade() {
		return oldGrade;
	}
	
	/**
	 * <p>原等级</p>
	 * @author:  yaozh
	 * @param:   @param oldGrade
	 * @return:  void 
	 * @Date :   2019-08-01 17:04:27   
	 */
	public void setOldGrade(Integer oldGrade) {
		this.oldGrade = oldGrade;
	}
	
	
	/**
	 * <p>旧等级名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-08-01 17:04:27    
	 */
	public String getOldGradeName() {
		return oldGradeName;
	}
	
	/**
	 * <p>旧等级名称</p>
	 * @author:  yaozh
	 * @param:   @param oldGradeName
	 * @return:  void 
	 * @Date :   2019-08-01 17:04:27   
	 */
	public void setOldGradeName(String oldGradeName) {
		this.oldGradeName = oldGradeName;
	}
	
	
	/**
	 * <p>新等级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-08-01 17:04:27    
	 */
	public Integer getNewGrade() {
		return newGrade;
	}
	
	/**
	 * <p>新等级</p>
	 * @author:  yaozh
	 * @param:   @param newGrade
	 * @return:  void 
	 * @Date :   2019-08-01 17:04:27   
	 */
	public void setNewGrade(Integer newGrade) {
		this.newGrade = newGrade;
	}
	
	
	/**
	 * <p>新等级名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-08-01 17:04:27    
	 */
	public String getNewGradeName() {
		return newGradeName;
	}
	
	/**
	 * <p>新等级名称</p>
	 * @author:  yaozh
	 * @param:   @param newGradeName
	 * @return:  void 
	 * @Date :   2019-08-01 17:04:27   
	 */
	public void setNewGradeName(String newGradeName) {
		this.newGradeName = newGradeName;
	}
	
	
	/**
	 * <p>等级类型 1矿工等级  2矿场等级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-08-01 17:04:27    
	 */
	public Integer getGradeType() {
		return gradeType;
	}
	
	/**
	 * <p>等级类型 1矿工等级  2矿场等级</p>
	 * @author:  yaozh
	 * @param:   @param gradeType
	 * @return:  void 
	 * @Date :   2019-08-01 17:04:27   
	 */
	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-08-01 17:04:27    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-08-01 17:04:27   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-08-01 17:04:27    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-08-01 17:04:27   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
