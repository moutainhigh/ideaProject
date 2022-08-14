/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-17 15:10:57 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> IcoTransferAccounts </p>
 * @author:         lzy
 * @Date :          2019-01-17 15:10:57  
 */
@Table(name="ico_transfer_accounts")
public class IcoTransferAccounts extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "serialNumber")
	private String serialNumber;  //流水号
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "toCustomerId")
	private Long toCustomerId;  //转入方用户Id
	
	@Column(name= "coinCode")
	private String coinCode;  //账转币种
	
	@Column(name= "money")
	private BigDecimal money;  //转账金额
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Transient
	private String publicKey;//来源

	@Transient
	private String toPublicKey;//到账

	@Transient
	private String account;//来源account

	@Transient
	private String toAccount;//到账account

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getToPublicKey() {
		return toPublicKey;
	}

	public void setToPublicKey(String toPublicKey) {
		this.toPublicKey = toPublicKey;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-17 15:10:57    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-17 15:10:57   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-17 15:10:57    
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  lzy
	 * @param:   @param serialNumber
	 * @return:  void 
	 * @Date :   2019-01-17 15:10:57   
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-17 15:10:57    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-01-17 15:10:57   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>转入方用户Id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-17 15:10:57    
	 */
	public Long getToCustomerId() {
		return toCustomerId;
	}
	
	/**
	 * <p>转入方用户Id</p>
	 * @author:  lzy
	 * @param:   @param toCustomerId
	 * @return:  void 
	 * @Date :   2019-01-17 15:10:57   
	 */
	public void setToCustomerId(Long toCustomerId) {
		this.toCustomerId = toCustomerId;
	}
	
	
	/**
	 * <p>账转币种</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-17 15:10:57    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>账转币种</p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-17 15:10:57   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>转账金额</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-17 15:10:57    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>转账金额</p>
	 * @author:  lzy
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-01-17 15:10:57   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-17 15:10:57    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  lzy
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-01-17 15:10:57   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
