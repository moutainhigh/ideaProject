/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      GaoMimi
 * @version:     V1.0 
 * @Date:        2016-10-11 14:37:42 
 */
package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppAccountSureold </p>
 * @author:         GaoMimi
 * @Date :          2016-10-11 14:37:42  
 */
@Table(name="app_account_sureold")
public class AppAccountSureold extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "customerId")
	private Long customerId;  //前端用户主键
	
	@Column(name= "accountId")
	private Long accountId;  //前端用户主键
	
	@Column(name= "userName")
	private String userName;  //登录名
	
	@Column(name= "coinCode")
	private String coinCode;  //资金种类
	
	@Column(name= "sureTime")
	private Date sureTime;  //创建时间
	
	@Column(name= "hotMoney")
	private BigDecimal hotMoney;  //可用总额
	
	@Column(name= "coldMoney")
	private BigDecimal coldMoney;  //冻结总额
	
	@Column(name= "lendMoney")
	private BigDecimal lendMoney;  //lendMoney
	
	@Column(name= "currencyType")
	private String currencyType;  //币种（CNY）
	
	@Column(name= "website")
	private String website;  //website
	
	public AppAccountSureold(){
		super();
	}
	
	public AppAccountSureold(Date sureTime, String coinCode,BigDecimal hotMoney,BigDecimal coldMoney,BigDecimal lendMoney){
		if(null!=sureTime){
			this.sureTime=sureTime;
		}else{
			this.sureTime=new Date();
		}
		
		this.coinCode=coinCode;
		this.hotMoney=hotMoney;
		this.coldMoney=coldMoney;
		this.lendMoney=lendMoney;
		
	}
	/**
	 * <p>id</p>
	 * @author:  GaoMimi
	 * @return:  Long 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  GaoMimi
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>前端用户主键</p>
	 * @author:  GaoMimi
	 * @return:  Long 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>前端用户主键</p>
	 * @author:  GaoMimi
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>前端用户主键</p>
	 * @author:  GaoMimi
	 * @return:  Long 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>前端用户主键</p>
	 * @author:  GaoMimi
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>登录名</p>
	 * @author:  GaoMimi
	 * @return:  String 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>登录名</p>
	 * @author:  GaoMimi
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

	
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	/**
	 * <p>创建时间</p>
	 * @author:  GaoMimi
	 * @return:  Date 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public Date getSureTime() {
		return sureTime;
	}
	
	/**
	 * <p>创建时间</p>
	 * @author:  GaoMimi
	 * @param:   @param sureTime
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setSureTime(Date sureTime) {
		this.sureTime = sureTime;
	}
	
	
	/**
	 * <p>可用总额</p>
	 * @author:  GaoMimi
	 * @return:  BigDecimal 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p>可用总额</p>
	 * @author:  GaoMimi
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p>冻结总额</p>
	 * @author:  GaoMimi
	 * @return:  BigDecimal 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p>冻结总额</p>
	 * @author:  GaoMimi
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	
	/**
	 * <p>lendMoney</p>
	 * @author:  GaoMimi
	 * @return:  BigDecimal 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public BigDecimal getLendMoney() {
		return lendMoney;
	}
	
	/**
	 * <p>lendMoney</p>
	 * @author:  GaoMimi
	 * @param:   @param lendMoney
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}
	
	
	/**
	 * <p>币种（CNY）</p>
	 * @author:  GaoMimi
	 * @return:  String 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p>币种（CNY）</p>
	 * @author:  GaoMimi
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p>website</p>
	 * @author:  GaoMimi
	 * @return:  String 
	 * @Date :   2016-10-11 14:37:42    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p>website</p>
	 * @author:  GaoMimi
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2016-10-11 14:37:42   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	

}
