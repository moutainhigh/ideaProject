/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 14:42:27 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IntegralConfig </p>
 * @author:         kongdb
 * @Date :          2019-01-07 14:42:27  
 */
@Table(name="shop_integral_config")
public class IntegralConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id 积分算力id
	
	@Column(name= "issueTotalMoney")
	private BigDecimal issueTotalMoney;  //发行总量
	
	@Column(name= "issueBalanceMoney")
	private BigDecimal issueBalanceMoney;  //平台积分币余量
	
	@Column(name= "issueBirculationMoney")
	private BigDecimal issueBirculationMoney;  //平台积分币流通总量
	
	@Column(name= "name")
	private String name;  //积分名称：
	
	@Column(name= "integralCode")
	private String integralCode;  //积分代码
	
	@Column(name= "logo")
	private String logo;  //数字积分币Logo：
	
	@Column(name= "coinPriceType")
	private Integer coinPriceType;  //价格来源：0.定价 1 市价
	
	@Column(name= "coinPrice")
	private BigDecimal coinPrice;  //币值
	
	@Column(name= "poundageType")
	private String poundageType;  //转账提现手续费类型 0.固定手续费 1. 百分比手续费
	
	@Column(name= "poundage")
	private BigDecimal poundage;  //转账提现手续费
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "isToSocial")
	private Short isToSocial;  //是否推送给社交
	
	public Short getIsToSocial() {
		return isToSocial;
	}

	public void setIsToSocial(Short isToSocial) {
		this.isToSocial = isToSocial;
	}

	/**
	 * <p>id 积分算力id</p>
	 * @author:  kongdb
	 * @return:  Long 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id 积分算力id</p>
	 * @author:  kongdb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>发行总量</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public BigDecimal getIssueTotalMoney() {
		return issueTotalMoney;
	}
	
	/**
	 * <p>发行总量</p>
	 * @author:  kongdb
	 * @param:   @param issueTotalMoney
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setIssueTotalMoney(BigDecimal issueTotalMoney) {
		this.issueTotalMoney = issueTotalMoney;
	}
	
	
	/**
	 * <p>平台积分币余量</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public BigDecimal getIssueBalanceMoney() {
		return issueBalanceMoney;
	}
	
	/**
	 * <p>平台积分币余量</p>
	 * @author:  kongdb
	 * @param:   @param issueBalanceMoney
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setIssueBalanceMoney(BigDecimal issueBalanceMoney) {
		this.issueBalanceMoney = issueBalanceMoney;
	}
	
	
	/**
	 * <p>平台积分币流通总量</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public BigDecimal getIssueBirculationMoney() {
		return issueBirculationMoney;
	}
	
	/**
	 * <p>平台积分币流通总量</p>
	 * @author:  kongdb
	 * @param:   @param issueBirculationMoney
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setIssueBirculationMoney(BigDecimal issueBirculationMoney) {
		this.issueBirculationMoney = issueBirculationMoney;
	}
	
	
	/**
	 * <p>积分名称：</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>积分名称：</p>
	 * @author:  kongdb
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>积分代码</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public String getIntegralCode() {
		return integralCode;
	}
	
	/**
	 * <p>积分代码</p>
	 * @author:  kongdb
	 * @param:   @param integralCode
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setIntegralCode(String integralCode) {
		this.integralCode = integralCode;
	}
	
	
	/**
	 * <p>数字积分币Logo：</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public String getLogo() {
		return logo;
	}
	
	/**
	 * <p>数字积分币Logo：</p>
	 * @author:  kongdb
	 * @param:   @param logo
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
	/**
	 * <p>价格来源：0.定价 1 市价</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public Integer getCoinPriceType() {
		return coinPriceType;
	}
	
	/**
	 * <p>价格来源：0.定价 1 市价</p>
	 * @author:  kongdb
	 * @param:   @param coinPriceType
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setCoinPriceType(Integer coinPriceType) {
		this.coinPriceType = coinPriceType;
	}
	
	
	/**
	 * <p>币值</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public BigDecimal getCoinPrice() {
		return coinPrice;
	}
	
	/**
	 * <p>币值</p>
	 * @author:  kongdb
	 * @param:   @param coinPrice
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setCoinPrice(BigDecimal coinPrice) {
		this.coinPrice = coinPrice;
	}
	
	
	/**
	 * <p>转账提现手续费类型 0.固定手续费 1. 百分比手续费</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public String getPoundageType() {
		return poundageType;
	}
	
	/**
	 * <p>转账提现手续费类型 0.固定手续费 1. 百分比手续费</p>
	 * @author:  kongdb
	 * @param:   @param poundageType
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setPoundageType(String poundageType) {
		this.poundageType = poundageType;
	}
	
	
	/**
	 * <p>转账提现手续费</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public BigDecimal getPoundage() {
		return poundage;
	}
	
	/**
	 * <p>转账提现手续费</p>
	 * @author:  kongdb
	 * @param:   @param poundage
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 14:42:27    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  kongdb
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-01-07 14:42:27   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
