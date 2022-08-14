/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-15 10:04:03 
 */
package hry.cm2.dividend.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm2FeeRecord </p>
 * @author:         yaozh
 * @Date :          2019-10-15 10:04:03  
 */
@Table(name="cm2_fee_record")
public class Cm2FeeRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "feeMoney")
	private BigDecimal feeMoney;  //手续费
	
	@Column(name= "feeType")
	private Integer feeType;  //手续费类型 1.币币交易手续费 2.提币手续费 3.OTC手续费  4.C2C手续费
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "status")
	private Integer status;  //发放状态 1未发放 2已发放 3不发放
	
	@Column(name= "bonusMoney")
	private BigDecimal bonusMoney;  //分红金额
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-15 10:04:03    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-10-15 10:04:03   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 10:04:03    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  yaozh
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-10-15 10:04:03   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>手续费</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 10:04:03    
	 */
	public BigDecimal getFeeMoney() {
		return feeMoney;
	}
	
	/**
	 * <p>手续费</p>
	 * @author:  yaozh
	 * @param:   @param feeMoney
	 * @return:  void 
	 * @Date :   2019-10-15 10:04:03   
	 */
	public void setFeeMoney(BigDecimal feeMoney) {
		this.feeMoney = feeMoney;
	}
	
	
	/**
	 * <p>手续费类型 1.币币交易手续费 2.提币手续费 3.OTC手续费  4.C2C手续费</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-15 10:04:03    
	 */
	public Integer getFeeType() {
		return feeType;
	}
	
	/**
	 * <p>手续费类型 1.币币交易手续费 2.提币手续费 3.OTC手续费  4.C2C手续费</p>
	 * @author:  yaozh
	 * @param:   @param feeType
	 * @return:  void 
	 * @Date :   2019-10-15 10:04:03   
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-15 10:04:03    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-10-15 10:04:03   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>发放状态 1未发放 2已发放 3不发放</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-15 10:04:03    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>发放状态 1未发放 2已发放 3不发放</p>
	 * @author:  yaozh
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-10-15 10:04:03   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>分红金额</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-15 10:04:03    
	 */
	public BigDecimal getBonusMoney() {
		return bonusMoney;
	}
	
	/**
	 * <p>分红金额</p>
	 * @author:  yaozh
	 * @param:   @param bonusMoney
	 * @return:  void 
	 * @Date :   2019-10-15 10:04:03   
	 */
	public void setBonusMoney(BigDecimal bonusMoney) {
		this.bonusMoney = bonusMoney;
	}
	
	

}
