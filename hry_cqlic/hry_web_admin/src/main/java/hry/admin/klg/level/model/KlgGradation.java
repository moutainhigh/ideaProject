/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:24 
 */
package hry.admin.klg.level.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgGradation </p>
 * @author:         lzy
 * @Date :          2019-04-11 17:30:24  
 */
@Table(name="klg_gradation")
public class KlgGradation extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "saasId")
	private String saasId;  //

	@Column(name= "name")
	private String name;  //name

	@Column(name= "gradation")
	private BigDecimal gradation;  //级差数
	
	@Column(name= "minLevelSort")
	private Integer minLevelSort;  //最小会员等级
	
	@Column(name= "levelNum")
	private Integer levelNum;  //星级个数


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-11 17:30:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-11 17:30:24    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:24   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>级差数</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-11 17:30:24    
	 */
	public BigDecimal getGradation() {
		return gradation;
	}
	
	/**
	 * <p>级差数</p>
	 * @author:  lzy
	 * @param:   @param gradation
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:24   
	 */
	public void setGradation(BigDecimal gradation) {
		this.gradation = gradation;
	}
	
	
	/**
	 * <p>最小会员等级</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:30:24    
	 */
	public Integer getMinLevelSort() {
		return minLevelSort;
	}
	
	/**
	 * <p>最小会员等级</p>
	 * @author:  lzy
	 * @param:   @param minLevelSort
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:24   
	 */
	public void setMinLevelSort(Integer minLevelSort) {
		this.minLevelSort = minLevelSort;
	}
	
	
	/**
	 * <p>星级个数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:30:24    
	 */
	public Integer getLevelNum() {
		return levelNum;
	}
	
	/**
	 * <p>星级个数</p>
	 * @author:  lzy
	 * @param:   @param levelNum
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:24   
	 */
	public void setLevelNum(Integer levelNum) {
		this.levelNum = levelNum;
	}
	
	

}
