/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 16:07:54 
 */
package hry.customer.commend.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p> AppCommendDeploy </p>
 * @author:         menwei
 * @Date :          2017-11-28 16:07:54  
 */
@Table(name="app_commend_deploy")
public class AppCommendDeploy extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id

	@Column(name="commendName")
	private String commendName;
	
	@Column(name= "rankRatio")
	private BigDecimal rankRatio;  //推荐奖励比例
	
	@Column(name= "standardValue")
	private BigDecimal standardValue;  //奖励最小值
	
	@Column(name= "transactionNumber")
	private Integer transactionNumber;  //交易笔数限制
	
	@Column(name= "costName")
	private String costName;  //costName
	
	@Column(name= "states")
	private Integer states;  //1买2卖
	
	@Column(name= "maxHierarchy")
	private Integer maxHierarchy;  //最大奖励层级
	
	@Column(name= "minHierarchy")
	private Integer minHierarchy;  //最小奖励层级
	
	
	@Column(name= "reserveMoney")
	private BigDecimal reserveMoney;  //平台扣取

	public void setMaxHierarchy(Integer maxHierarchy) {
		this.maxHierarchy = maxHierarchy;
	}

	public String getCommendName() {
		return commendName;
	}

	public void setCommendName(String commendName) {
		this.commendName = commendName;
	}

	public BigDecimal getReserveMoney() {
		return reserveMoney;
	}

	public void setReserveMoney(BigDecimal reserveMoney) {
		this.reserveMoney = reserveMoney;
	}



	public Integer getMaxHierarchy() {
		return maxHierarchy;
	}

	public void rankRatio(Integer maxHierarchy) {
		this.maxHierarchy = maxHierarchy;
	}

	public Integer getMinHierarchy() {
		return minHierarchy;
	}

	public void setMinHierarchy(Integer minHierarchy) {
		this.minHierarchy = minHierarchy;
	}

	/**
	 * <p>id</p>
	 * @author:  menwei
	 * @return:  Long 
	 * @Date :   2017-11-28 16:07:54    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  menwei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-11-28 16:07:54   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	public BigDecimal getRankRatio() {
		return rankRatio;
	}

	public void setRankRatio(BigDecimal rankRatio) {
		this.rankRatio = rankRatio;
	}

	public BigDecimal getStandardValue() {
		return standardValue;
	}

	public void setStandardValue(BigDecimal standardValue) {
		this.standardValue = standardValue;
	}

	/**
	 * <p>transactionNumber</p>
	 * @author:  menwei
	 * @return:  Integer 
	 * @Date :   2017-11-28 16:07:54    
	 */
	public Integer getTransactionNumber() {
		return transactionNumber;
	}
	
	/**
	 * <p>transactionNumber</p>
	 * @author:  menwei
	 * @param:   @param transactionNumber
	 * @return:  void 
	 * @Date :   2017-11-28 16:07:54   
	 */
	public void setTransactionNumber(Integer transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	
	
	/**
	 * <p>costName</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-11-28 16:07:54    
	 */
	public String getCostName() {
		return costName;
	}
	
	/**
	 * <p>costName</p>
	 * @author:  menwei
	 * @param:   @param costName
	 * @return:  void 
	 * @Date :   2017-11-28 16:07:54   
	 */
	public void setCostName(String costName) {
		this.costName = costName;
	}
	
	
	/**
	 * <p>states</p>
	 * @author:  menwei
	 * @return:  Integer 
	 * @Date :   2017-11-28 16:07:54    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>states</p>
	 * @author:  menwei
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2017-11-28 16:07:54   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	

}
