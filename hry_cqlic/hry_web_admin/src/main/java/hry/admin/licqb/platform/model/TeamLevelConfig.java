/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:43:18 
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
 * <p> TeamLevelConfig </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:43:18  
 */
@Table(name="lc_team_level_config")
public class TeamLevelConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "teamLevelName")
	private String teamLevelName;  //社区等级名称
	
	@Column(name= "teamSort")
	private Integer teamSort;  //社区等级排序
	
	@Column(name= "directRecommendNum")
	private Integer directRecommendNum;  //直推荐人数
	
	@Column(name= "nextRecommendNum")
	private Integer nextRecommendNum;  //下级及以上用户数
	
	@Column(name= "ownAsset")
	private BigDecimal ownAsset;  //个人资产(USDT)
	
	@Column(name= "teamPerformance")
	private BigDecimal teamPerformance;  //团队业绩(USDT)
	
	@Column(name= "everyMonthTeamRatio")
	private BigDecimal everyMonthTeamRatio;  //每月团队新增业绩(%)
	
	@Column(name= "teamAwardNum")
	private BigDecimal teamAwardNum;  //社区奖励数量(平台币)
	
	@Column(name= "weekGrantRatio")
	private BigDecimal weekGrantRatio;  //周发放比例(%)
	
	@Column(name= "monthGrantRatio")
	private BigDecimal monthGrantRatio;  //月发放比例(%)
	
	@Column(name= "yearGrantRatio")
	private BigDecimal yearGrantRatio;  //月发放比例(%)
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>社区等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public String getTeamLevelName() {
		return teamLevelName;
	}
	
	/**
	 * <p>社区等级名称</p>
	 * @author:  zhouming
	 * @param:   @param teamLevelName
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setTeamLevelName(String teamLevelName) {
		this.teamLevelName = teamLevelName;
	}
	
	
	/**
	 * <p>社区等级排序</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public Integer getTeamSort() {
		return teamSort;
	}
	
	/**
	 * <p>社区等级排序</p>
	 * @author:  zhouming
	 * @param:   @param teamSort
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setTeamSort(Integer teamSort) {
		this.teamSort = teamSort;
	}
	
	
	/**
	 * <p>直推荐人数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public Integer getDirectRecommendNum() {
		return directRecommendNum;
	}
	
	/**
	 * <p>直推荐人数</p>
	 * @author:  zhouming
	 * @param:   @param directRecommendNum
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setDirectRecommendNum(Integer directRecommendNum) {
		this.directRecommendNum = directRecommendNum;
	}
	
	
	/**
	 * <p>下级及以上用户数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public Integer getNextRecommendNum() {
		return nextRecommendNum;
	}
	
	/**
	 * <p>下级及以上用户数</p>
	 * @author:  zhouming
	 * @param:   @param nextRecommendNum
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setNextRecommendNum(Integer nextRecommendNum) {
		this.nextRecommendNum = nextRecommendNum;
	}
	
	
	/**
	 * <p>个人资产(USDT)</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public BigDecimal getOwnAsset() {
		return ownAsset;
	}
	
	/**
	 * <p>个人资产(USDT)</p>
	 * @author:  zhouming
	 * @param:   @param ownAsset
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setOwnAsset(BigDecimal ownAsset) {
		this.ownAsset = ownAsset;
	}
	
	
	/**
	 * <p>团队业绩(USDT)</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public BigDecimal getTeamPerformance() {
		return teamPerformance;
	}
	
	/**
	 * <p>团队业绩(USDT)</p>
	 * @author:  zhouming
	 * @param:   @param teamPerformance
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setTeamPerformance(BigDecimal teamPerformance) {
		this.teamPerformance = teamPerformance;
	}
	
	
	/**
	 * <p>每月团队新增业绩(%)</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public BigDecimal getEveryMonthTeamRatio() {
		return everyMonthTeamRatio;
	}
	
	/**
	 * <p>每月团队新增业绩(%)</p>
	 * @author:  zhouming
	 * @param:   @param everyMonthTeamRatio
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setEveryMonthTeamRatio(BigDecimal everyMonthTeamRatio) {
		this.everyMonthTeamRatio = everyMonthTeamRatio;
	}
	
	
	/**
	 * <p>社区奖励数量(平台币)</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public BigDecimal getTeamAwardNum() {
		return teamAwardNum;
	}
	
	/**
	 * <p>社区奖励数量(平台币)</p>
	 * @author:  zhouming
	 * @param:   @param teamAwardNum
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setTeamAwardNum(BigDecimal teamAwardNum) {
		this.teamAwardNum = teamAwardNum;
	}
	
	
	/**
	 * <p>周发放比例(%)</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public BigDecimal getWeekGrantRatio() {
		return weekGrantRatio;
	}
	
	/**
	 * <p>周发放比例(%)</p>
	 * @author:  zhouming
	 * @param:   @param weekGrantRatio
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setWeekGrantRatio(BigDecimal weekGrantRatio) {
		this.weekGrantRatio = weekGrantRatio;
	}
	
	
	/**
	 * <p>月发放比例(%)</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public BigDecimal getMonthGrantRatio() {
		return monthGrantRatio;
	}
	
	/**
	 * <p>月发放比例(%)</p>
	 * @author:  zhouming
	 * @param:   @param monthGrantRatio
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setMonthGrantRatio(BigDecimal monthGrantRatio) {
		this.monthGrantRatio = monthGrantRatio;
	}
	
	
	/**
	 * <p>月发放比例(%)</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-12 17:43:18    
	 */
	public BigDecimal getYearGrantRatio() {
		return yearGrantRatio;
	}
	
	/**
	 * <p>月发放比例(%)</p>
	 * @author:  zhouming
	 * @param:   @param yearGrantRatio
	 * @return:  void 
	 * @Date :   2019-08-12 17:43:18   
	 */
	public void setYearGrantRatio(BigDecimal yearGrantRatio) {
		this.yearGrantRatio = yearGrantRatio;
	}
	
	

}
