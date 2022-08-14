/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:48:57 
 */
package hry.admin.commend.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppCommendDeploy </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:48:57  
 */
@Table(name="app_commend_deploy")
public class AppCommendDeploy extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "commendName")
	private String commendName;  //
	
	@Column(name= "RankRatio")
	private BigDecimal RankRatio;  //
	
	@Column(name= "StandardValue")
	private BigDecimal StandardValue;  //
	
	@Column(name= "transactionNumber")
	private Integer transactionNumber;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "costName")
	private String costName;  //
	
	@Column(name= "states")
	private Integer states;  //
	
	@Column(name= "MinHierarchy")
	private Integer MinHierarchy;  //
	
	@Column(name= "MaxHierarchy")
	private Integer MaxHierarchy;  //
	
	@Column(name= "reserveMoney")
	private String reserveMoney;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public String getCommendName() {
		return commendName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param commendName
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setCommendName(String commendName) {
		this.commendName = commendName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public BigDecimal getRankRatio() {
		return RankRatio;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param RankRatio
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setRankRatio(BigDecimal RankRatio) {
		this.RankRatio = RankRatio;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public BigDecimal getStandardValue() {
		return StandardValue;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param StandardValue
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setStandardValue(BigDecimal StandardValue) {
		this.StandardValue = StandardValue;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public Integer getTransactionNumber() {
		return transactionNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param transactionNumber
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setTransactionNumber(Integer transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public String getCostName() {
		return costName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param costName
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setCostName(String costName) {
		this.costName = costName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public Integer getMinHierarchy() {
		return MinHierarchy;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param MinHierarchy
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setMinHierarchy(Integer MinHierarchy) {
		this.MinHierarchy = MinHierarchy;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public Integer getMaxHierarchy() {
		return MaxHierarchy;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param MaxHierarchy
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setMaxHierarchy(Integer MaxHierarchy) {
		this.MaxHierarchy = MaxHierarchy;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:48:57    
	 */
	public String getReserveMoney() {
		return reserveMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param reserveMoney
	 * @return:  void 
	 * @Date :   2018-06-25 19:48:57   
	 */
	public void setReserveMoney(String reserveMoney) {
		this.reserveMoney = reserveMoney;
	}
	
	

}
