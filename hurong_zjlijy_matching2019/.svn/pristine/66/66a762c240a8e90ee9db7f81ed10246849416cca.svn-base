/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:11:12 
 */
package hry.cm5.customer.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm5TeamLevelConfig </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:11:12  
 */
@Table(name="cm5_teamLevel_config")
public class Cm5TeamLevelConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "levelSort")
	private Integer levelSort;  //等级排序
	
	@Column(name= "levelName")
	private String levelName;  //等级名称
	
	@Column(name= "performanceCode")
	private String performanceCode;  //业绩币种 默认US
	
	@Column(name= "nextPerformance")
	private BigDecimal nextPerformance;  //直推业绩
	
	@Column(name= "downPerformance")
	private BigDecimal downPerformance;  //伞下业绩
	
	@Column(name= "selfTeamLevel")
	private Long selfTeamLevel;  //本人等级要求
	
	@Column(name= "downWireNum")
	private Integer downWireNum;  //伞下线数 (注: 直推人数)
	
	@Column(name= "downTeamLevel")
	private Long downTeamLevel;  //伞下等级
	
	@Column(name= "addPipeRatio")
	private String addPipeRatio;  //管道算力增加比例
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>等级排序</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public Integer getLevelSort() {
		return levelSort;
	}
	
	/**
	 * <p>等级排序</p>
	 * @author:  zhouming
	 * @param:   @param levelSort
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setLevelSort(Integer levelSort) {
		this.levelSort = levelSort;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public String getLevelName() {
		return levelName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  zhouming
	 * @param:   @param levelName
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	
	/**
	 * <p>业绩币种 默认US</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public String getPerformanceCode() {
		return performanceCode;
	}
	
	/**
	 * <p>业绩币种 默认US</p>
	 * @author:  zhouming
	 * @param:   @param performanceCode
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setPerformanceCode(String performanceCode) {
		this.performanceCode = performanceCode;
	}
	
	
	/**
	 * <p>直推业绩</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public BigDecimal getNextPerformance() {
		return nextPerformance;
	}
	
	/**
	 * <p>直推业绩</p>
	 * @author:  zhouming
	 * @param:   @param nextPerformance
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setNextPerformance(BigDecimal nextPerformance) {
		this.nextPerformance = nextPerformance;
	}
	
	
	/**
	 * <p>伞下业绩</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public BigDecimal getDownPerformance() {
		return downPerformance;
	}
	
	/**
	 * <p>伞下业绩</p>
	 * @author:  zhouming
	 * @param:   @param downPerformance
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setDownPerformance(BigDecimal downPerformance) {
		this.downPerformance = downPerformance;
	}
	
	
	/**
	 * <p>本人等级要求</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public Long getSelfTeamLevel() {
		return selfTeamLevel;
	}
	
	/**
	 * <p>本人等级要求</p>
	 * @author:  zhouming
	 * @param:   @param selfTeamLevel
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setSelfTeamLevel(Long selfTeamLevel) {
		this.selfTeamLevel = selfTeamLevel;
	}
	
	
	/**
	 * <p>伞下线数 (注: 直推人数)</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public Integer getDownWireNum() {
		return downWireNum;
	}
	
	/**
	 * <p>伞下线数 (注: 直推人数)</p>
	 * @author:  zhouming
	 * @param:   @param downWireNum
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setDownWireNum(Integer downWireNum) {
		this.downWireNum = downWireNum;
	}
	
	
	/**
	 * <p>伞下等级</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public Long getDownTeamLevel() {
		return downTeamLevel;
	}
	
	/**
	 * <p>伞下等级</p>
	 * @author:  zhouming
	 * @param:   @param downTeamLevel
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setDownTeamLevel(Long downTeamLevel) {
		this.downTeamLevel = downTeamLevel;
	}
	
	
	/**
	 * <p>管道算力增加比例</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public String getAddPipeRatio() {
		return addPipeRatio;
	}
	
	/**
	 * <p>管道算力增加比例</p>
	 * @author:  zhouming
	 * @param:   @param addPipeRatio
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setAddPipeRatio(String addPipeRatio) {
		this.addPipeRatio = addPipeRatio;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:11:12    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-01-08 14:11:12   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
