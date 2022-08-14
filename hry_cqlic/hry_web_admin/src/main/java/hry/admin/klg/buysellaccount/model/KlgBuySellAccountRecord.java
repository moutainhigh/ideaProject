/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-22 17:02:55 
 */
package hry.admin.klg.buysellaccount.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgBuySellAccountRecord </p>
 * @author:         yaozhuo
 * @Date :          2019-04-22 17:02:55  
 */
@Table(name="klg_buysell_account_record")
public class KlgBuySellAccountRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "accountName")
	private String accountName;  //账户名称
	
	@Column(name= "changeMoney")
	private BigDecimal changeMoney;  //变动金额
	
	@Column(name= "changeType")
	private Integer changeType;  //变动类型：1加 2减
	
	@Column(name= "triggered")
	private String triggered;  //触发点
	
	@Column(name= "orderNum")
	private String orderNum;  //触发点订单号
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-22 17:02:55    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-22 17:02:55   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>账户名称</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-22 17:02:55    
	 */
	public String getAccountName() {
		return accountName;
	}
	
	/**
	 * <p>账户名称</p>
	 * @author:  yaozhuo
	 * @param:   @param accountName
	 * @return:  void 
	 * @Date :   2019-04-22 17:02:55   
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
	/**
	 * <p>变动金额</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-22 17:02:55    
	 */
	public BigDecimal getChangeMoney() {
		return changeMoney;
	}
	
	/**
	 * <p>变动金额</p>
	 * @author:  yaozhuo
	 * @param:   @param changeMoney
	 * @return:  void 
	 * @Date :   2019-04-22 17:02:55   
	 */
	public void setChangeMoney(BigDecimal changeMoney) {
		this.changeMoney = changeMoney;
	}
	
	
	/**
	 * <p>变动类型：1加 2减</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-04-22 17:02:55    
	 */
	public Integer getChangeType() {
		return changeType;
	}
	
	/**
	 * <p>变动类型：1加 2减</p>
	 * @author:  yaozhuo
	 * @param:   @param changeType
	 * @return:  void 
	 * @Date :   2019-04-22 17:02:55   
	 */
	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}
	
	
	/**
	 * <p>触发点</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-22 17:02:55    
	 */
	public String getTriggered() {
		return triggered;
	}
	
	/**
	 * <p>触发点</p>
	 * @author:  yaozhuo
	 * @param:   @param triggered
	 * @return:  void 
	 * @Date :   2019-04-22 17:02:55   
	 */
	public void setTriggered(String triggered) {
		this.triggered = triggered;
	}
	
	
	/**
	 * <p>触发点订单号</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-22 17:02:55    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p>触发点订单号</p>
	 * @author:  yaozhuo
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2019-04-22 17:02:55   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-22 17:02:55    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  yaozhuo
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-04-22 17:02:55   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-22 17:02:55    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-04-22 17:02:55   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
