/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-07-17 10:48:11 
 */
package hry.social.manage.remote.model.miningreward;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> SocialMiningRuleConfig </p>
 * @author:         javalx
 * @Date :          2019-07-17 10:48:11  
 */
@Table(name="social_mining_rule_config")
public class SocialMiningRuleConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "coinCode")
	private String coinCode;  //奖励币种
	
	@Column(name= "image")
	private String image;  //图片
	
	@Column(name= "keepRatio")
	private BigDecimal keepRatio;  //本人保底率
	
	@Column(name= "gatherRatio")
	private BigDecimal gatherRatio;  //好友收取率
	
	@Column(name= "rewardType")
	private Integer rewardType;  //奖励类型(1:算力奖励 2:任务奖励 3:矿机奖励)
	
	@Column(name= "stealStatus")
	private Integer stealStatus;  //是否允许偷取（0否，1是）
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-07-17 10:48:11    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-17 10:48:11   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>奖励币种</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-07-17 10:48:11    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>奖励币种</p>
	 * @author:  javalx
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-07-17 10:48:11   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>图片</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-07-17 10:48:11    
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * <p>图片</p>
	 * @author:  javalx
	 * @param:   @param image
	 * @return:  void 
	 * @Date :   2019-07-17 10:48:11   
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	/**
	 * <p>本人保底率</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-07-17 10:48:11    
	 */
	public BigDecimal getKeepRatio() {
		return keepRatio;
	}
	
	/**
	 * <p>本人保底率</p>
	 * @author:  javalx
	 * @param:   @param keepRatio
	 * @return:  void 
	 * @Date :   2019-07-17 10:48:11   
	 */
	public void setKeepRatio(BigDecimal keepRatio) {
		this.keepRatio = keepRatio;
	}
	
	
	/**
	 * <p>好友收取率</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-07-17 10:48:11    
	 */
	public BigDecimal getGatherRatio() {
		return gatherRatio;
	}
	
	/**
	 * <p>好友收取率</p>
	 * @author:  javalx
	 * @param:   @param gatherRatio
	 * @return:  void 
	 * @Date :   2019-07-17 10:48:11   
	 */
	public void setGatherRatio(BigDecimal gatherRatio) {
		this.gatherRatio = gatherRatio;
	}
	
	
	/**
	 * <p>奖励类型(1:算力奖励 2:任务奖励 3:矿机奖励)</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-07-17 10:48:11    
	 */
	public Integer getRewardType() {
		return rewardType;
	}
	
	/**
	 * <p>奖励类型(1:算力奖励 2:任务奖励 3:矿机奖励)</p>
	 * @author:  javalx
	 * @param:   @param rewardType
	 * @return:  void 
	 * @Date :   2019-07-17 10:48:11   
	 */
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	
	
	/**
	 * <p>是否允许偷取（0否，1是）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-07-17 10:48:11    
	 */
	public Integer getStealStatus() {
		return stealStatus;
	}
	
	/**
	 * <p>是否允许偷取（0否，1是）</p>
	 * @author:  javalx
	 * @param:   @param stealStatus
	 * @return:  void 
	 * @Date :   2019-07-17 10:48:11   
	 */
	public void setStealStatus(Integer stealStatus) {
		this.stealStatus = stealStatus;
	}
	
	

}
