/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:43 
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
 * <p> KlgLevelConfig </p>
 * @author:         lzy
 * @Date :          2019-04-11 17:30:43  
 */
@Table(name="klg_level_config")
public class KlgLevelConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "levelName")
	private String levelName;  //等级名称
	
	@Column(name= "sort")
	private Integer sort;  //等级排序

	@Column(name= "buyNum")
	private BigDecimal buyNum;  //购买限制

	@Column(name= "buyNums")
	private String buyNums;  //购买限制  2019年6月3日改为多选

	@Column(name= "bonusMultiple")
	private BigDecimal bonusMultiple;  //奖金倍数
	
	@Column(name= "pointAlgebra")
	private Integer pointAlgebra;  //见点代数
	
	@Column(name= "sellTime")
	private Integer sellTime;  //基础卖出时长
	
	@Column(name= "candyNum")
	private BigDecimal candyNum;  //基础糖果比率
	
	@Column(name= "addCandyNum")
	private BigDecimal addCandyNum;  //递增糖果比率
	
	@Column(name= "maxSellTime")
	private Integer maxSellTime;  //最高卖出时长

	@Column(name= "maxRewardTime")
	private Integer maxRewardTime;  //最高奖励时长


	@Column(name= "recommendNum")
	private Integer recommendNum;  //推荐人数
	
	@Column(name= "recommendSort")
	private Integer recommendSort;  //推荐星级别
	
	@Column(name= "luckDrawCount")
	private Integer luckDrawCount;  //抽奖次数限制
	
	

	public Integer getLuckDrawCount() {
		return luckDrawCount;
	}

	public void setLuckDrawCount(Integer luckDrawCount) {
		this.luckDrawCount = luckDrawCount;
	}

	public Integer getMaxRewardTime() {
		return maxRewardTime;
	}

	public void setMaxRewardTime(Integer maxRewardTime) {
		this.maxRewardTime = maxRewardTime;
	}

	public String getBuyNums() {
		return buyNums;
	}

	public void setBuyNums(String buyNums) {
		this.buyNums = buyNums;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public String getLevelName() {
		return levelName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  lzy
	 * @param:   @param levelName
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	
	/**
	 * <p>等级排序</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>等级排序</p>
	 * @author:  lzy
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>购买限制</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public BigDecimal getBuyNum() {
		return buyNum;
	}
	
	/**
	 * <p>购买限制</p>
	 * @author:  lzy
	 * @param:   @param buyNum
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setBuyNum(BigDecimal buyNum) {
		this.buyNum = buyNum;
	}
	
	
	/**
	 * <p>奖金倍数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public BigDecimal getBonusMultiple() {
		return bonusMultiple;
	}
	
	/**
	 * <p>奖金倍数</p>
	 * @author:  lzy
	 * @param:   @param bonusMultiple
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setBonusMultiple(BigDecimal bonusMultiple) {
		this.bonusMultiple = bonusMultiple;
	}
	
	
	/**
	 * <p>见点代数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public Integer getPointAlgebra() {
		return pointAlgebra;
	}
	
	/**
	 * <p>见点代数</p>
	 * @author:  lzy
	 * @param:   @param pointAlgebra
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setPointAlgebra(Integer pointAlgebra) {
		this.pointAlgebra = pointAlgebra;
	}
	
	
	/**
	 * <p>基础卖出时长</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public Integer getSellTime() {
		return sellTime;
	}
	
	/**
	 * <p>基础卖出时长</p>
	 * @author:  lzy
	 * @param:   @param sellTime
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setSellTime(Integer sellTime) {
		this.sellTime = sellTime;
	}
	
	
	/**
	 * <p>基础糖果比率</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public BigDecimal getCandyNum() {
		return candyNum;
	}
	
	/**
	 * <p>基础糖果比率</p>
	 * @author:  lzy
	 * @param:   @param candyNum
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setCandyNum(BigDecimal candyNum) {
		this.candyNum = candyNum;
	}
	
	
	/**
	 * <p>递增糖果比率</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public BigDecimal getAddCandyNum() {
		return addCandyNum;
	}
	
	/**
	 * <p>递增糖果比率</p>
	 * @author:  lzy
	 * @param:   @param addCandyNum
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setAddCandyNum(BigDecimal addCandyNum) {
		this.addCandyNum = addCandyNum;
	}
	
	
	/**
	 * <p>最高卖出时长</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public Integer getMaxSellTime() {
		return maxSellTime;
	}
	
	/**
	 * <p>最高卖出时长</p>
	 * @author:  lzy
	 * @param:   @param maxSellTime
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setMaxSellTime(Integer maxSellTime) {
		this.maxSellTime = maxSellTime;
	}
	
	
	/**
	 * <p>推荐人数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public Integer getRecommendNum() {
		return recommendNum;
	}
	
	/**
	 * <p>推荐人数</p>
	 * @author:  lzy
	 * @param:   @param recommendNum
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setRecommendNum(Integer recommendNum) {
		this.recommendNum = recommendNum;
	}
	
	
	/**
	 * <p>推荐星级别</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-11 17:30:43    
	 */
	public Integer getRecommendSort() {
		return recommendSort;
	}
	
	/**
	 * <p>推荐星级别</p>
	 * @author:  lzy
	 * @param:   @param recommendSort
	 * @return:  void 
	 * @Date :   2019-04-11 17:30:43   
	 */
	public void setRecommendSort(Integer recommendSort) {
		this.recommendSort = recommendSort;
	}
	
	

}
