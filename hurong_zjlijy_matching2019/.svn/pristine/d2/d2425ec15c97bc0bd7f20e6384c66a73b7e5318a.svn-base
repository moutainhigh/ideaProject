/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-05-31 09:53:19 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> ShopTeamDividendConfig </p>
 * @author:         houzhen
 * @Date :          2019-05-31 09:53:19  
 */
@Table(name="shop_team_dividend_config")
public class ShopTeamDividendConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "teamLevelId")
	private Long teamLevelId;  //直推会员团队等级id
	
	@Column(name= "teamLevelNumber")
	private Integer teamLevelNumber;  //直推会员团队等级序号
	
	@Column(name= "integralLevelId")
	private Long integralLevelId;  //直推会员等级id
	
	@Column(name= "integralLevelNumber")
	private Integer integralLevelNumber;  //直推会员等级序号
	
	@Column(name= "dividendRatio")
	private BigDecimal dividendRatio;  //分红比例
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-05-31 09:53:19    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  houzhen
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-31 09:53:19   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>直推会员团队等级id</p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-05-31 09:53:19    
	 */
	public Long getTeamLevelId() {
		return teamLevelId;
	}
	
	/**
	 * <p>直推会员团队等级id</p>
	 * @author:  houzhen
	 * @param:   @param teamLevelId
	 * @return:  void 
	 * @Date :   2019-05-31 09:53:19   
	 */
	public void setTeamLevelId(Long teamLevelId) {
		this.teamLevelId = teamLevelId;
	}
	
	
	/**
	 * <p>直推会员团队等级序号</p>
	 * @author:  houzhen
	 * @return:  Integer 
	 * @Date :   2019-05-31 09:53:19    
	 */
	public Integer getTeamLevelNumber() {
		return teamLevelNumber;
	}
	
	/**
	 * <p>直推会员团队等级序号</p>
	 * @author:  houzhen
	 * @param:   @param teamLevelNumber
	 * @return:  void 
	 * @Date :   2019-05-31 09:53:19   
	 */
	public void setTeamLevelNumber(Integer teamLevelNumber) {
		this.teamLevelNumber = teamLevelNumber;
	}
	
	
	/**
	 * <p>直推会员等级id</p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-05-31 09:53:19    
	 */
	public Long getIntegralLevelId() {
		return integralLevelId;
	}
	
	/**
	 * <p>直推会员等级id</p>
	 * @author:  houzhen
	 * @param:   @param integralLevelId
	 * @return:  void 
	 * @Date :   2019-05-31 09:53:19   
	 */
	public void setIntegralLevelId(Long integralLevelId) {
		this.integralLevelId = integralLevelId;
	}
	
	
	/**
	 * <p>直推会员等级序号</p>
	 * @author:  houzhen
	 * @return:  Integer 
	 * @Date :   2019-05-31 09:53:19    
	 */
	public Integer getIntegralLevelNumber() {
		return integralLevelNumber;
	}
	
	/**
	 * <p>直推会员等级序号</p>
	 * @author:  houzhen
	 * @param:   @param integralLevelNumber
	 * @return:  void 
	 * @Date :   2019-05-31 09:53:19   
	 */
	public void setIntegralLevelNumber(Integer integralLevelNumber) {
		this.integralLevelNumber = integralLevelNumber;
	}
	
	
	/**
	 * <p>分红比例</p>
	 * @author:  houzhen
	 * @return:  BigDecimal 
	 * @Date :   2019-05-31 09:53:19    
	 */
	public BigDecimal getDividendRatio() {
		return dividendRatio;
	}
	
	/**
	 * <p>分红比例</p>
	 * @author:  houzhen
	 * @param:   @param dividendRatio
	 * @return:  void 
	 * @Date :   2019-05-31 09:53:19   
	 */
	public void setDividendRatio(BigDecimal dividendRatio) {
		this.dividendRatio = dividendRatio;
	}
	
	

}
