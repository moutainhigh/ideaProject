/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-05-31 09:52:20 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> ShopTeamLevel </p>
 * @author:         houzhen
 * @Date :          2019-05-31 09:52:20  
 */
@Table(name="shop_team_level")
public class ShopTeamLevel extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "number")
	private Integer number;  //排序号
	
	@Column(name= "name")
	private String name;  //等级名称
	
	@Column(name= "teamCount")
	private Integer teamCount;  //团队人数
	
	@Column(name= "directPush")
	private Integer directPush;  //直推人数
	
	@Column(name= "integralLevelId")
	private Long integralLevelId;  //直推会员等级id
	
	@Column(name= "integralLevelNumber")
	private Integer integralLevelNumber;  //直推会员等级序号
	
	@Column(name= "teamLevelId")
	private Long teamLevelId;  //直推会员团队等级id
	
	@Column(name= "teamLevelNumber")
	private Integer teamLevelNumber;  //直推会员团队等级序号

	@Column(name= "teamPerformance")
	private BigDecimal teamPerformance;  //团队业绩

	public BigDecimal getTeamPerformance() {
		return teamPerformance;
	}

	public void setTeamPerformance(BigDecimal teamPerformance) {
		this.teamPerformance = teamPerformance;
	}

	/**
	 * <p>主键id</p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-05-31 09:52:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  houzhen
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-31 09:52:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>排序号</p>
	 * @author:  houzhen
	 * @return:  Integer 
	 * @Date :   2019-05-31 09:52:20    
	 */
	public Integer getNumber() {
		return number;
	}
	
	/**
	 * <p>排序号</p>
	 * @author:  houzhen
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-05-31 09:52:20   
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-05-31 09:52:20    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  houzhen
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-05-31 09:52:20   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>团队人数</p>
	 * @author:  houzhen
	 * @return:  Integer 
	 * @Date :   2019-05-31 09:52:20    
	 */
	public Integer getTeamCount() {
		return teamCount;
	}
	
	/**
	 * <p>团队人数</p>
	 * @author:  houzhen
	 * @param:   @param teamCount
	 * @return:  void 
	 * @Date :   2019-05-31 09:52:20   
	 */
	public void setTeamCount(Integer teamCount) {
		this.teamCount = teamCount;
	}
	
	
	/**
	 * <p>直推人数</p>
	 * @author:  houzhen
	 * @return:  Integer 
	 * @Date :   2019-05-31 09:52:20    
	 */
	public Integer getDirectPush() {
		return directPush;
	}
	
	/**
	 * <p>直推人数</p>
	 * @author:  houzhen
	 * @param:   @param directPush
	 * @return:  void 
	 * @Date :   2019-05-31 09:52:20   
	 */
	public void setDirectPush(Integer directPush) {
		this.directPush = directPush;
	}
	
	
	/**
	 * <p>直推会员等级id</p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-05-31 09:52:20    
	 */
	public Long getIntegralLevelId() {
		return integralLevelId;
	}
	
	/**
	 * <p>直推会员等级id</p>
	 * @author:  houzhen
	 * @param:   @param integralLevelId
	 * @return:  void 
	 * @Date :   2019-05-31 09:52:20   
	 */
	public void setIntegralLevelId(Long integralLevelId) {
		this.integralLevelId = integralLevelId;
	}
	
	
	/**
	 * <p>直推会员等级序号</p>
	 * @author:  houzhen
	 * @return:  Integer 
	 * @Date :   2019-05-31 09:52:20    
	 */
	public Integer getIntegralLevelNumber() {
		return integralLevelNumber;
	}
	
	/**
	 * <p>直推会员等级序号</p>
	 * @author:  houzhen
	 * @param:   @param integralLevelNumber
	 * @return:  void 
	 * @Date :   2019-05-31 09:52:20   
	 */
	public void setIntegralLevelNumber(Integer integralLevelNumber) {
		this.integralLevelNumber = integralLevelNumber;
	}
	
	
	/**
	 * <p>直推会员团队等级id</p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-05-31 09:52:20    
	 */
	public Long getTeamLevelId() {
		return teamLevelId;
	}
	
	/**
	 * <p>直推会员团队等级id</p>
	 * @author:  houzhen
	 * @param:   @param teamLevelId
	 * @return:  void 
	 * @Date :   2019-05-31 09:52:20   
	 */
	public void setTeamLevelId(Long teamLevelId) {
		this.teamLevelId = teamLevelId;
	}
	
	
	/**
	 * <p>直推会员团队等级序号</p>
	 * @author:  houzhen
	 * @return:  Integer 
	 * @Date :   2019-05-31 09:52:20    
	 */
	public Integer getTeamLevelNumber() {
		return teamLevelNumber;
	}
	
	/**
	 * <p>直推会员团队等级序号</p>
	 * @author:  houzhen
	 * @param:   @param teamLevelNumber
	 * @return:  void 
	 * @Date :   2019-05-31 09:52:20   
	 */
	public void setTeamLevelNumber(Integer teamLevelNumber) {
		this.teamLevelNumber = teamLevelNumber;
	}
	
	

}
