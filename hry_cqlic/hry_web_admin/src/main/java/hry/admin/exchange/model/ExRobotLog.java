/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-13 11:25:18 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExRobotLog </p>
 * @author:         tianpengyu
 * @Date :          2018-09-13 11:25:18  
 */
@Table(name="ex_robot_log")
public class ExRobotLog extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "fixCoin")
	private String fixCoin;  //
	
	@Column(name= "openTime")
	private Date openTime;  //
	
	@Column(name= "closeTime")
	private Date closeTime;  //
	
	@Column(name= "buyTotalNum")
	private BigDecimal buyTotalNum;  //
	
	@Column(name= "sellTotalNum")
	private BigDecimal sellTotalNum;  //
	
	@Column(name= "phone")
	private String phone;  //
	
	@Column(name= "email")
	private String email;  //

	@Column(name= "remark")
	private String remark;  //

	@Column(name= "type")
	private Integer type;  //

	@Column(name= "robotid")
	private String robotId;  //


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRobotId() {
		return robotId;
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public String getFixCoin() {
		return fixCoin;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fixCoin
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setFixCoin(String fixCoin) {
		this.fixCoin = fixCoin;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Date 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public Date getOpenTime() {
		return openTime;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param openTime
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Date 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public Date getCloseTime() {
		return closeTime;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param closeTime
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public BigDecimal getBuyTotalNum() {
		return buyTotalNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param buyTotalNum
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setBuyTotalNum(BigDecimal buyTotalNum) {
		this.buyTotalNum = buyTotalNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public BigDecimal getSellTotalNum() {
		return sellTotalNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param sellTotalNum
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setSellTotalNum(BigDecimal sellTotalNum) {
		this.sellTotalNum = sellTotalNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param phone
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-13 11:25:18    
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param email
	 * @return:  void 
	 * @Date :   2018-09-13 11:25:18   
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
