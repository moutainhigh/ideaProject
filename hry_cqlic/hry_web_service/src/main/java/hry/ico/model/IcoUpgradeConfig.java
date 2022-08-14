/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-12 19:48:59 
 */
package hry.ico.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoUpgradeConfig </p>
 * @author:         denghf
 * @Date :          2019-01-12 19:48:59  
 */
@Table(name="ico_upgrade_config")
public class IcoUpgradeConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "gradeName")
	private String gradeName;  //等级名称
	
	@Column(name= "upConditions")
	private Integer upConditions;  //升级条件:1.大于等于
	
	@Column(name= "conditionPara")
	private Long conditionPara;  //条件参数
	
	@Column(name= "additionRatio")
	private String additionRatio;  //加成比例


	@Column(name= "sort")
	private Integer sort;  //等级大小排序

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2019-01-12 19:48:59    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-12 19:48:59   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-12 19:48:59    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  denghf
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-01-12 19:48:59   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>升级条件:1.大于等于</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2019-01-12 19:48:59    
	 */
	public Integer getUpConditions() {
		return upConditions;
	}
	
	/**
	 * <p>升级条件:1.大于等于</p>
	 * @author:  denghf
	 * @param:   @param upConditions
	 * @return:  void 
	 * @Date :   2019-01-12 19:48:59   
	 */
	public void setUpConditions(Integer upConditions) {
		this.upConditions = upConditions;
	}
	
	
	/**
	 * <p>条件参数</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-12 19:48:59    
	 */
	public Long getConditionPara() {
		return conditionPara;
	}
	
	/**
	 * <p>条件参数</p>
	 * @author:  denghf
	 * @param:   @param conditionPara
	 * @return:  void 
	 * @Date :   2019-01-12 19:48:59   
	 */
	public void setConditionPara(Long conditionPara) {
		this.conditionPara = conditionPara;
	}
	
	
	/**
	 * <p>加成比例</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-12 19:48:59    
	 */
	public String getAdditionRatio() {
		return additionRatio;
	}
	
	/**
	 * <p>加成比例</p>
	 * @author:  denghf
	 * @param:   @param additionRatio
	 * @return:  void 
	 * @Date :   2019-01-12 19:48:59   
	 */
	public void setAdditionRatio(String additionRatio) {
		this.additionRatio = additionRatio;
	}
	
	

}
