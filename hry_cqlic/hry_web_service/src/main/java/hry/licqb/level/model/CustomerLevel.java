/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-16 14:33:37 
 */
package hry.licqb.level.model;


import hry.bean.BaseModel;


import javax.persistence.*;

/**
 * <p> CustomerLevel </p>
 * @author:         zhouming
 * @Date :          2019-08-16 14:33:37  
 */
@Table(name="lc_customer_level")
public class CustomerLevel extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "levelId")
	private Long levelId;  //等级id
	
	@Column(name= "levelName")
	private String levelName;  //个人等级名称
	
	@Column(name= "sort")
	private Integer sort;  //个人等级排序
	
	@Column(name= "teamLevelId")
	private Long teamLevelId;  //社区等级id
	
	@Column(name= "teamLevelName")
	private String teamLevelName;  //社区等级名称
	
	@Column(name= "teamSort")
	private Integer teamSort;  //社区等级排序
	
	@Column(name= "isTeamAward")
	private Integer isTeamAward;  //是否发放社区奖励 0: 否 1:是

	@Transient
	private Integer isInvested; // 是否投资过

	public Integer getIsInvested() {
		return isInvested;
	}

	public void setIsInvested(Integer isInvested) {
		this.isInvested = isInvested;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-16 14:33:37    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-16 14:33:37   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-16 14:33:37    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-16 14:33:37   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>等级id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-16 14:33:37    
	 */
	public Long getLevelId() {
		return levelId;
	}
	
	/**
	 * <p>等级id</p>
	 * @author:  zhouming
	 * @param:   @param levelId
	 * @return:  void 
	 * @Date :   2019-08-16 14:33:37   
	 */
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	
	/**
	 * <p>个人等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-16 14:33:37    
	 */
	public String getLevelName() {
		return levelName;
	}
	
	/**
	 * <p>个人等级名称</p>
	 * @author:  zhouming
	 * @param:   @param levelName
	 * @return:  void 
	 * @Date :   2019-08-16 14:33:37   
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	
	/**
	 * <p>个人等级排序</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-16 14:33:37    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>个人等级排序</p>
	 * @author:  zhouming
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2019-08-16 14:33:37   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>社区等级id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-16 14:33:37    
	 */
	public Long getTeamLevelId() {
		return teamLevelId;
	}
	
	/**
	 * <p>社区等级id</p>
	 * @author:  zhouming
	 * @param:   @param teamLevelId
	 * @return:  void 
	 * @Date :   2019-08-16 14:33:37   
	 */
	public void setTeamLevelId(Long teamLevelId) {
		this.teamLevelId = teamLevelId;
	}
	
	
	/**
	 * <p>社区等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-16 14:33:37    
	 */
	public String getTeamLevelName() {
		return teamLevelName;
	}
	
	/**
	 * <p>社区等级名称</p>
	 * @author:  zhouming
	 * @param:   @param teamLevelName
	 * @return:  void 
	 * @Date :   2019-08-16 14:33:37   
	 */
	public void setTeamLevelName(String teamLevelName) {
		this.teamLevelName = teamLevelName;
	}
	
	
	/**
	 * <p>社区等级排序</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-16 14:33:37    
	 */
	public Integer getTeamSort() {
		return teamSort;
	}
	
	/**
	 * <p>社区等级排序</p>
	 * @author:  zhouming
	 * @param:   @param teamSort
	 * @return:  void 
	 * @Date :   2019-08-16 14:33:37   
	 */
	public void setTeamSort(Integer teamSort) {
		this.teamSort = teamSort;
	}
	
	
	/**
	 * <p>是否发放社区奖励</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-16 14:33:37    
	 */
	public Integer getIsTeamAward() {
		return isTeamAward;
	}
	
	/**
	 * <p>是否发放社区奖励</p>
	 * @author:  zhouming
	 * @param:   @param isTeamAward
	 * @return:  void 
	 * @Date :   2019-08-16 14:33:37   
	 */
	public void setIsTeamAward(Integer isTeamAward) {
		this.isTeamAward = isTeamAward;
	}
	
	

}
