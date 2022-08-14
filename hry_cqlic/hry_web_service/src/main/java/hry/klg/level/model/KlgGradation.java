/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-05-17 14:49:58 
 */
package hry.klg.level.model;


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
 * @Date :          2019-05-17 14:49:58  
 */
@Table(name="klg_gradation")
public class KlgGradation extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "gradation")
	private BigDecimal gradation;  //级差数
	
	@Column(name= "minLevelSort")
	private Integer minLevelSort;  //最小会员等级
	
	@Column(name= "levelNum")
	private Integer levelNum;  //星级个数
	
	@Column(name= "name")
	private String name;  //名称
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-05-17 14:49:58    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-17 14:49:58   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-05-17 14:49:58    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-05-17 14:49:58   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>级差数</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-05-17 14:49:58    
	 */
	public BigDecimal getGradation() {
		return gradation;
	}
	
	/**
	 * <p>级差数</p>
	 * @author:  lzy
	 * @param:   @param gradation
	 * @return:  void 
	 * @Date :   2019-05-17 14:49:58   
	 */
	public void setGradation(BigDecimal gradation) {
		this.gradation = gradation;
	}
	
	
	/**
	 * <p>最小会员等级</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-05-17 14:49:58    
	 */
	public Integer getMinLevelSort() {
		return minLevelSort;
	}
	
	/**
	 * <p>最小会员等级</p>
	 * @author:  lzy
	 * @param:   @param minLevelSort
	 * @return:  void 
	 * @Date :   2019-05-17 14:49:58   
	 */
	public void setMinLevelSort(Integer minLevelSort) {
		this.minLevelSort = minLevelSort;
	}
	
	
	/**
	 * <p>星级个数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-05-17 14:49:58    
	 */
	public Integer getLevelNum() {
		return levelNum;
	}
	
	/**
	 * <p>星级个数</p>
	 * @author:  lzy
	 * @param:   @param levelNum
	 * @return:  void 
	 * @Date :   2019-05-17 14:49:58   
	 */
	public void setLevelNum(Integer levelNum) {
		this.levelNum = levelNum;
	}
	
	
	/**
	 * <p>名称</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-05-17 14:49:58    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>名称</p>
	 * @author:  lzy
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-05-17 14:49:58   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}
