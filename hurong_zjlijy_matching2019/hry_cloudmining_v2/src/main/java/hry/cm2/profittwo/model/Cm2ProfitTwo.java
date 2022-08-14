/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-15 09:46:50 
 */
package hry.cm2.profittwo.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm2ProfitTwo </p>
 * @author:         yaozh
 * @Date :          2019-10-15 09:46:50  
 */
@Table(name="cm2_profit_two")
public class Cm2ProfitTwo extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //收益人ID
	
	@Column(name= "customerTeamId")
	private Long customerTeamId;  //产出动态收益层级的推荐人id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //流水号
	
	@Column(name= "grade")
	private Integer grade;  //收益人矿场等级
	
	@Column(name= "gradeName")
	private String gradeName;  //收益人等级名称
	
	@Column(name= "proportion")
	private BigDecimal proportion;  //收益比例
	
	@Column(name= "profit1")
	private BigDecimal profit1;  //分发收益基数
	
	@Column(name= "profit2")
	private BigDecimal profit2;  //同级收益
	
	@Column(name= "profit3")
	private BigDecimal profit3;  //获取收益
	
	@Column(name= "status")
	private Integer status;  //发放状态 1.未发放 2已发放
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "downProportion")
	private BigDecimal downProportion;  //下级收益比例(减去的收益比例）
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>收益人ID</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>收益人ID</p>
	 * @author:  yaozh
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>产出动态收益层级的推荐人id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public Long getCustomerTeamId() {
		return customerTeamId;
	}
	
	/**
	 * <p>产出动态收益层级的推荐人id</p>
	 * @author:  yaozh
	 * @param:   @param customerTeamId
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setCustomerTeamId(Long customerTeamId) {
		this.customerTeamId = customerTeamId;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozh
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>收益人矿场等级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public Integer getGrade() {
		return grade;
	}
	
	/**
	 * <p>收益人矿场等级</p>
	 * @author:  yaozh
	 * @param:   @param grade
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	/**
	 * <p>收益人等级名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>收益人等级名称</p>
	 * @author:  yaozh
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>收益比例</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public BigDecimal getProportion() {
		return proportion;
	}
	
	/**
	 * <p>收益比例</p>
	 * @author:  yaozh
	 * @param:   @param proportion
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}
	
	
	/**
	 * <p>分发收益基数</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public BigDecimal getProfit1() {
		return profit1;
	}
	
	/**
	 * <p>分发收益基数</p>
	 * @author:  yaozh
	 * @param:   @param profit1
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setProfit1(BigDecimal profit1) {
		this.profit1 = profit1;
	}
	
	
	/**
	 * <p>同级收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public BigDecimal getProfit2() {
		return profit2;
	}
	
	/**
	 * <p>同级收益</p>
	 * @author:  yaozh
	 * @param:   @param profit2
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setProfit2(BigDecimal profit2) {
		this.profit2 = profit2;
	}
	
	
	/**
	 * <p>获取收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public BigDecimal getProfit3() {
		return profit3;
	}
	
	/**
	 * <p>获取收益</p>
	 * @author:  yaozh
	 * @param:   @param profit3
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setProfit3(BigDecimal profit3) {
		this.profit3 = profit3;
	}
	
	
	/**
	 * <p>发放状态 1.未发放 2已发放</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>发放状态 1.未发放 2已发放</p>
	 * @author:  yaozh
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>下级收益比例(减去的收益比例）</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:50    
	 */
	public BigDecimal getDownProportion() {
		return downProportion;
	}
	
	/**
	 * <p>下级收益比例(减去的收益比例）</p>
	 * @author:  yaozh
	 * @param:   @param downProportion
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:50   
	 */
	public void setDownProportion(BigDecimal downProportion) {
		this.downProportion = downProportion;
	}
	
	

}
