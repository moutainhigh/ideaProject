/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 16:16:58 
 */
package hry.klg.assetsrecord.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgAssetsRecord </p>
 * @author:         yaozhuo
 * @Date :          2019-04-29 16:16:58  
 */
@Table(name="klg_assets_record")
public class KlgAssetsRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "accountId")
	private Long accountId;  //币账户Id
	
	@Column(name= "serialNumber")
	private String serialNumber;  //流水号
	
	@Column(name= "accountType")
	private Integer accountType;  //账户类型 1.热账户 2.冷账户
	
	@Column(name= "coinCode")
	private String coinCode;  //币种Code
	
	@Column(name= "changeCount")
	private BigDecimal changeCount;  //交易量
	
	@Column(name= "changeType")
	private Integer changeType;  //变动类型 1加 2减
	
	@Column(name= "triggerPoint")
	private Integer triggerPoint;  //触发点
	
	@Column(name= "triggerSerialNumber")
	private String triggerSerialNumber;  //触发点流水号
	
	@Column(name= "remark")
	private String remark;  //备注
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  yaozhuo
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币账户Id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>币账户Id</p>
	 * @author:  yaozhuo
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozhuo
	 * @param:   @param serialNumber
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
	/**
	 * <p>账户类型 1.热账户 2.冷账户</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public Integer getAccountType() {
		return accountType;
	}
	
	/**
	 * <p>账户类型 1.热账户 2.冷账户</p>
	 * @author:  yaozhuo
	 * @param:   @param accountType
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	
	
	/**
	 * <p>币种Code</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种Code</p>
	 * @author:  yaozhuo
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>交易量</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public BigDecimal getChangeCount() {
		return changeCount;
	}
	
	/**
	 * <p>交易量</p>
	 * @author:  yaozhuo
	 * @param:   @param changeCount
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setChangeCount(BigDecimal changeCount) {
		this.changeCount = changeCount;
	}
	
	
	/**
	 * <p>变动类型 1加 2减</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public Integer getChangeType() {
		return changeType;
	}
	
	/**
	 * <p>变动类型 1加 2减</p>
	 * @author:  yaozhuo
	 * @param:   @param changeType
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}
	
	
	/**
	 * <p>触发点</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public Integer getTriggerPoint() {
		return triggerPoint;
	}
	
	/**
	 * <p>触发点</p>
	 * @author:  yaozhuo
	 * @param:   @param triggerPoint
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setTriggerPoint(Integer triggerPoint) {
		this.triggerPoint = triggerPoint;
	}
	
	
	/**
	 * <p>触发点流水号</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public String getTriggerSerialNumber() {
		return triggerSerialNumber;
	}
	
	/**
	 * <p>触发点流水号</p>
	 * @author:  yaozhuo
	 * @param:   @param triggerSerialNumber
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setTriggerSerialNumber(String triggerSerialNumber) {
		this.triggerSerialNumber = triggerSerialNumber;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-29 16:16:58    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  yaozhuo
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-04-29 16:16:58   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 
	 * @param customerId 用户id
	 * @param accountId 币账户id
	 * @param serialNumber 流水号
	 * @param accountType 账户类型 1.热账户 2.冷账户
	 * @param coinCode 币种
	 * @param changeCount 变动金额
	 * @param changeType 变动类型 1加 2减
	 * @param triggerPoint 触发点
	 * @param triggerSerialNumber 触发点订单号
	 * @param remark 备注
	 */
	public KlgAssetsRecord(Long customerId, Long accountId, String serialNumber, Integer accountType, String coinCode,
			BigDecimal changeCount, Integer changeType, Integer triggerPoint, String triggerSerialNumber,
			String remark) {
		super();
		this.customerId = customerId;
		this.accountId = accountId;
		this.serialNumber = serialNumber;
		this.accountType = accountType;
		this.coinCode = coinCode;
		this.changeCount = changeCount;
		this.changeType = changeType;
		this.triggerPoint = triggerPoint;
		this.triggerSerialNumber = triggerSerialNumber;
		this.remark = remark;
	}

	public KlgAssetsRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
