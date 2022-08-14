/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:37:17 
 */
package hry.admin.licqb.platform.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> LevelConfig </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:37:17  
 */
@Table(name="lc_level_config")
public class LevelConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "levelName")
	private String levelName;  //等级名称
	
	@Column(name= "sort")
	private Integer sort;  //等级排序
	
	@Column(name= "directRecommendNum")
	private Integer directRecommendNum;  //直推荐人数
	
	@Column(name= "nextRecommendNum")
	private Integer nextRecommendNum;  //下级及以上用户数
	
	@Column(name= "teamPerformance")
	private BigDecimal teamPerformance;  //团队业绩(USDT)
	
	@Column(name= "levelAward")
	private BigDecimal levelAward;  //等级奖励(%)
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-12 17:37:17    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-12 17:37:17   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-12 17:37:17    
	 */
	public String getLevelName() {
		return levelName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  zhouming
	 * @param:   @param levelName
	 * @return:  void 
	 * @Date :   2019-08-12 17:37:17   
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	
	/**
	 * <p>等级排序</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:37:17    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>等级排序</p>
	 * @author:  zhouming
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2019-08-12 17:37:17   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>直推荐人数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:37:17    
	 */
	public Integer getDirectRecommendNum() {
		return directRecommendNum;
	}
	
	/**
	 * <p>直推荐人数</p>
	 * @author:  zhouming
	 * @param:   @param directRecommendNum
	 * @return:  void 
	 * @Date :   2019-08-12 17:37:17   
	 */
	public void setDirectRecommendNum(Integer directRecommendNum) {
		this.directRecommendNum = directRecommendNum;
	}
	
	
	/**
	 * <p>下级及以上用户数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:37:17    
	 */
	public Integer getNextRecommendNum() {
		return nextRecommendNum;
	}
	
	/**
	 * <p>下级及以上用户数</p>
	 * @author:  zhouming
	 * @param:   @param nextRecommendNum
	 * @return:  void 
	 * @Date :   2019-08-12 17:37:17   
	 */
	public void setNextRecommendNum(Integer nextRecommendNum) {
		this.nextRecommendNum = nextRecommendNum;
	}
	
	
	/**
	 * <p>团队业绩(USDT)</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-12 17:37:17    
	 */
	public BigDecimal getTeamPerformance() {
		return teamPerformance;
	}
	
	/**
	 * <p>团队业绩(USDT)</p>
	 * @author:  zhouming
	 * @param:   @param teamPerformance
	 * @return:  void 
	 * @Date :   2019-08-12 17:37:17   
	 */
	public void setTeamPerformance(BigDecimal teamPerformance) {
		this.teamPerformance = teamPerformance;
	}
	
	
	/**
	 * <p>等级奖励(%)</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-12 17:37:17    
	 */
	public BigDecimal getLevelAward() {
		return levelAward;
	}
	
	/**
	 * <p>等级奖励(%)</p>
	 * @author:  zhouming
	 * @param:   @param levelAward
	 * @return:  void 
	 * @Date :   2019-08-12 17:37:17   
	 */
	public void setLevelAward(BigDecimal levelAward) {
		this.levelAward = levelAward;
	}
	
	

}
