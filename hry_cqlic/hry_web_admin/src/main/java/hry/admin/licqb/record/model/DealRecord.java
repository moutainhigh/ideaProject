/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-14 15:22:23 
 */
package hry.admin.licqb.record.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> DealRecord </p>
 * @author:         zhouming
 * @Date :          2019-08-14 15:22:23  
 */
@Table(name="lc_deal_record")
public class DealRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id

	@Column(name= "outInfoId")
	private Long outInfoId; //出局信息表Id

	@Column(name= "accountId")
	private Long accountId;  //数币账户Id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //流水号
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "dealType")
	private Integer dealType;  //交易类型 1：静态收益 2：见点奖 3：管理奖 4：级别奖 5：共建社区奖励 6：出局 7：兑入 12:兑出 8：投入  9:周释放10:月释放 11:年释放 13:理财 14:提币 15:充币
	
	@Column(name= "ratio")
	private BigDecimal ratio;  //比例
	
	@Column(name= "dealMoney")
	private BigDecimal dealMoney;  //交易金额
	
	@Column(name= "codeValue")
	private BigDecimal codeValue;  //当前币价值（USDT）
	
	@Column(name= "predictExpendlimit")
	private BigDecimal predictExpendlimit;  //预计消耗额度
	
	@Column(name= "actualExpendlimit")
	private BigDecimal actualExpendlimit;  //实际消耗额度

	@Column(name= "predictMoney")
	private BigDecimal predictMoney; // 预计释放基数金额

	@Column(name= "actualMoney")
	private BigDecimal actualMoney;  //实际基数

	@Column(name= "serviceCharge")
	private BigDecimal serviceCharge; // 手续费

	@Column(name= "dealStatus")
	private Integer dealStatus; // 1：交易中（待审核）2：交易完成 3：交易失败

	@Column(name= "remark")
	private String remark; // 备注

	@Transient
	private String email; // 用户邮箱

	@Transient
	private String mobilePhone; // 用户电话

	public BigDecimal getPredictMoney() {
		return predictMoney;
	}

	public void setPredictMoney(BigDecimal predictMoney) {
		this.predictMoney = predictMoney;
	}

	public BigDecimal getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(BigDecimal actualMoney) {
		this.actualMoney = actualMoney;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Long getOutInfoId() {
		return outInfoId;
	}

	public void setOutInfoId(Long outInfoId) {
		this.outInfoId = outInfoId;
	}

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>数币账户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>数币账户Id</p>
	 * @author:  zhouming
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  zhouming
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>交易类型 1：静态收益 2：见点奖 3：管理奖 4：级别奖 5：共建社区奖励 6：出局 7：兑换</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public Integer getDealType() {
		return dealType;
	}
	
	/**
	 * <p>交易类型 1：静态收益 2：见点奖 3：管理奖 4：级别奖 5：共建社区奖励 6：出局 7：兑换</p>
	 * @author:  zhouming
	 * @param:   @param dealType
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}
	
	
	/**
	 * <p>比例</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public BigDecimal getRatio() {
		return ratio;
	}
	
	/**
	 * <p>比例</p>
	 * @author:  zhouming
	 * @param:   @param ratio
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	
	
	/**
	 * <p>交易金额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public BigDecimal getDealMoney() {
		return dealMoney;
	}
	
	/**
	 * <p>交易金额</p>
	 * @author:  zhouming
	 * @param:   @param dealMoney
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setDealMoney(BigDecimal dealMoney) {
		this.dealMoney = dealMoney;
	}
	
	
	/**
	 * <p>当前币价值（USDT）</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public BigDecimal getCodeValue() {
		return codeValue;
	}
	
	/**
	 * <p>当前币价值（USDT）</p>
	 * @author:  zhouming
	 * @param:   @param codeValue
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setCodeValue(BigDecimal codeValue) {
		this.codeValue = codeValue;
	}
	
	
	/**
	 * <p>预计消耗额度</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public BigDecimal getPredictExpendlimit() {
		return predictExpendlimit;
	}
	
	/**
	 * <p>预计消耗额度</p>
	 * @author:  zhouming
	 * @param:   @param predictExpendlimit
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setPredictExpendlimit(BigDecimal predictExpendlimit) {
		this.predictExpendlimit = predictExpendlimit;
	}
	
	
	/**
	 * <p>实际消耗额度</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-14 15:22:23    
	 */
	public BigDecimal getActualExpendlimit() {
		return actualExpendlimit;
	}
	
	/**
	 * <p>实际消耗额度</p>
	 * @author:  zhouming
	 * @param:   @param actualExpendlimit
	 * @return:  void 
	 * @Date :   2019-08-14 15:22:23   
	 */
	public void setActualExpendlimit(BigDecimal actualExpendlimit) {
		this.actualExpendlimit = actualExpendlimit;
	}
	
	

}
