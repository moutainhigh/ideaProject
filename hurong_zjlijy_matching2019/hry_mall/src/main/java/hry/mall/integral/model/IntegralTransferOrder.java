/**
 * Copyright:   
 * @author:      huanggh
 * @version:     V1.0 
 * @Date:        2019-03-28 11:28:09 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> IntegralTransferOrder </p>
 * @author:         huanggh
 * @Date :          2019-03-28 11:28:09  
 */
@Table(name="shop_integral_transferOrder")
public class IntegralTransferOrder extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "transferMemberId")
	private Long transferMemberId;  //转让人
	
	@Column(name= "transferMobilePhone")
	private String transferMobilePhone;  //转让人手机号
	
	@Column(name= "amount")
	private BigDecimal amount;  //转让数量
	
	@Column(name= "actualAmount")
	private BigDecimal actualAmount;  //实际到账数量
	
	@Column(name= "feeAmount")
	private BigDecimal feeAmount;  //转让手续费
	
	@Column(name= "receiveMemberId")
	private Long receiveMemberId;  //受让人
	
	@Column(name= "receiveMobilePhone")
	private String receiveMobilePhone;  //受让人手机号
	
	@Column(name= "receiveAmount")
	private BigDecimal receiveAmount;  //受让人到账数量
	
	@Column(name= "requestNo")
	private String requestNo;  //流水号
	
	@Column(name= "state")
	private Integer state;  //状态  1:失败 2：成功'
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  huanggh
	 * @return:  Long 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  huanggh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>转让人</p>
	 * @author:  huanggh
	 * @return:  Long 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public Long getTransferMemberId() {
		return transferMemberId;
	}
	
	/**
	 * <p>转让人</p>
	 * @author:  huanggh
	 * @param:   @param transferMemberId
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setTransferMemberId(Long transferMemberId) {
		this.transferMemberId = transferMemberId;
	}
	
	
	/**
	 * <p>转让人手机号</p>
	 * @author:  huanggh
	 * @return:  String 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public String getTransferMobilePhone() {
		return transferMobilePhone;
	}
	
	/**
	 * <p>转让人手机号</p>
	 * @author:  huanggh
	 * @param:   @param transferMobilePhone
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setTransferMobilePhone(String transferMobilePhone) {
		this.transferMobilePhone = transferMobilePhone;
	}
	
	
	/**
	 * <p>转让数量</p>
	 * @author:  huanggh
	 * @return:  BigDecimal 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * <p>转让数量</p>
	 * @author:  huanggh
	 * @param:   @param amount
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	/**
	 * <p>实际到账数量</p>
	 * @author:  huanggh
	 * @return:  BigDecimal 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public BigDecimal getActualAmount() {
		return actualAmount;
	}
	
	/**
	 * <p>实际到账数量</p>
	 * @author:  huanggh
	 * @param:   @param actualAmount
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	
	
	/**
	 * <p>转让手续费</p>
	 * @author:  huanggh
	 * @return:  BigDecimal 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public BigDecimal getFeeAmount() {
		return feeAmount;
	}
	
	/**
	 * <p>转让手续费</p>
	 * @author:  huanggh
	 * @param:   @param feeAmount
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
	
	
	/**
	 * <p>受让人</p>
	 * @author:  huanggh
	 * @return:  Long 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public Long getReceiveMemberId() {
		return receiveMemberId;
	}
	
	/**
	 * <p>受让人</p>
	 * @author:  huanggh
	 * @param:   @param receiveMemberId
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setReceiveMemberId(Long receiveMemberId) {
		this.receiveMemberId = receiveMemberId;
	}
	
	
	/**
	 * <p>受让人手机号</p>
	 * @author:  huanggh
	 * @return:  String 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public String getReceiveMobilePhone() {
		return receiveMobilePhone;
	}
	
	/**
	 * <p>受让人手机号</p>
	 * @author:  huanggh
	 * @param:   @param receiveMobilePhone
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setReceiveMobilePhone(String receiveMobilePhone) {
		this.receiveMobilePhone = receiveMobilePhone;
	}
	
	
	/**
	 * <p>受让人到账数量</p>
	 * @author:  huanggh
	 * @return:  BigDecimal 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}
	
	/**
	 * <p>受让人到账数量</p>
	 * @author:  huanggh
	 * @param:   @param receiveAmount
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  huanggh
	 * @return:  String 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public String getRequestNo() {
		return requestNo;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  huanggh
	 * @param:   @param requestNo
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	
	
	/**
	 * <p>状态</p>
	 * @author:  huanggh
	 * @return:  Integer 
	 * @Date :   2019-03-28 11:28:09    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>状态</p>
	 * @author:  huanggh
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-03-28 11:28:09   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	

}
