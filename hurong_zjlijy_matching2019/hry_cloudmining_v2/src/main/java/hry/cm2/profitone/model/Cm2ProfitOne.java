/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-15 09:46:21 
 */
package hry.cm2.profitone.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm2ProfitOne </p>
 * @author:         yaozh
 * @Date :          2019-10-15 09:46:21  
 */
@Table(name="cm2_profit_one")
public class Cm2ProfitOne extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //收益人ID
	
	@Column(name= "customerTeamId")
	private Long customerTeamId;  //产出币层级的推荐人id
	
	@Column(name= "grade")
	private Integer grade;  //等级
	
	@Column(name= "gradeName")
	private String gradeName;  //等级名称
	
	@Column(name= "profitProportion")
	private BigDecimal profitProportion;  //动态收益比例
	
	@Column(name= "cappingMultiple")
	private BigDecimal cappingMultiple;  //封顶倍数
	
	@Column(name= "customerProduce")
	private BigDecimal customerProduce;  //收益人当天产出总数
	
	@Column(name= "subordinateProduce")
	private BigDecimal subordinateProduce;  //下级当天产出总数
	
	@Column(name= "subordinateDynamicProfit")
	private BigDecimal subordinateDynamicProfit;  //下级当天动态收益
	
	@Column(name= "profit1")
	private BigDecimal profit1;  //应发收益
	
	@Column(name= "profit2")
	private BigDecimal profit2;  //实发收益
	
	@Column(name= "profit3")
	private BigDecimal profit3;  //烧伤收益
	
	@Column(name= "status")
	private Integer status;  //发放状态 1.未发放 2已发放
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "transactionNum")
	private String transactionNum;  //流水号
	
	@Column(name= "type")
	private Integer type;  //收益类型 1.拿一代静态收益 2拿一代动态收益
	
	@Column(name= "hierarchy")
	private Integer hierarchy;  //层级


	public Integer getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>收益人ID</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>收益人ID</p>
	 * @author:  yaozh
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>产出币层级的推荐人id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public Long getCustomerTeamId() {
		return customerTeamId;
	}
	
	/**
	 * <p>产出币层级的推荐人id</p>
	 * @author:  yaozh
	 * @param:   @param customerTeamId
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setCustomerTeamId(Long customerTeamId) {
		this.customerTeamId = customerTeamId;
	}
	
	
	/**
	 * <p>等级</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public Integer getGrade() {
		return grade;
	}
	
	/**
	 * <p>等级</p>
	 * @author:  yaozh
	 * @param:   @param grade
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  yaozh
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>动态收益比例</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public BigDecimal getProfitProportion() {
		return profitProportion;
	}
	
	/**
	 * <p>动态收益比例</p>
	 * @author:  yaozh
	 * @param:   @param profitProportion
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
	}
	
	
	/**
	 * <p>封顶倍数</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public BigDecimal getCappingMultiple() {
		return cappingMultiple;
	}
	
	/**
	 * <p>封顶倍数</p>
	 * @author:  yaozh
	 * @param:   @param cappingMultiple
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setCappingMultiple(BigDecimal cappingMultiple) {
		this.cappingMultiple = cappingMultiple;
	}
	
	
	/**
	 * <p>收益人当天产出总数</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public BigDecimal getCustomerProduce() {
		return customerProduce;
	}
	
	/**
	 * <p>收益人当天产出总数</p>
	 * @author:  yaozh
	 * @param:   @param customerProduce
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setCustomerProduce(BigDecimal customerProduce) {
		this.customerProduce = customerProduce;
	}
	
	
	/**
	 * <p>下级当天产出总数</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public BigDecimal getSubordinateProduce() {
		return subordinateProduce;
	}
	
	/**
	 * <p>下级当天产出总数</p>
	 * @author:  yaozh
	 * @param:   @param subordinateProduce
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setSubordinateProduce(BigDecimal subordinateProduce) {
		this.subordinateProduce = subordinateProduce;
	}
	
	
	/**
	 * <p>下级当天动态收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public BigDecimal getSubordinateDynamicProfit() {
		return subordinateDynamicProfit;
	}
	
	/**
	 * <p>下级当天动态收益</p>
	 * @author:  yaozh
	 * @param:   @param subordinateDynamicProfit
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setSubordinateDynamicProfit(BigDecimal subordinateDynamicProfit) {
		this.subordinateDynamicProfit = subordinateDynamicProfit;
	}
	
	
	/**
	 * <p>应发收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public BigDecimal getProfit1() {
		return profit1;
	}
	
	/**
	 * <p>应发收益</p>
	 * @author:  yaozh
	 * @param:   @param profit1
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setProfit1(BigDecimal profit1) {
		this.profit1 = profit1;
	}
	
	
	/**
	 * <p>实发收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public BigDecimal getProfit2() {
		return profit2;
	}
	
	/**
	 * <p>实发收益</p>
	 * @author:  yaozh
	 * @param:   @param profit2
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setProfit2(BigDecimal profit2) {
		this.profit2 = profit2;
	}
	
	
	/**
	 * <p>烧伤收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public BigDecimal getProfit3() {
		return profit3;
	}
	
	/**
	 * <p>烧伤收益</p>
	 * @author:  yaozh
	 * @param:   @param profit3
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setProfit3(BigDecimal profit3) {
		this.profit3 = profit3;
	}
	
	
	/**
	 * <p>发放状态 1.未发放 2已发放</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>发放状态 1.未发放 2已发放</p>
	 * @author:  yaozh
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozh
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>收益类型 1.拿一代静态收益 2拿一代动态收益</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-15 09:46:21    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>收益类型 1.拿一代静态收益 2拿一代动态收益</p>
	 * @author:  yaozh
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-10-15 09:46:21   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
