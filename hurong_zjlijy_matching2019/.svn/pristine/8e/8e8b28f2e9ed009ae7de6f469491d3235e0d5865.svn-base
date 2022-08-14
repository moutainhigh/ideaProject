/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-07-31 15:43:25 
 */
package hry.cm.dividend.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> cmFeeRecord </p>
 * @author:         zhouming
 * @Date :          2019-07-31 15:43:25  
 */
@Table(name="cm_fee_record")
public class CmFeeRecord extends BaseModel {
	
	
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
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-07-31 15:43:25    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-31 15:43:25   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-07-31 15:43:25    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-07-31 15:43:25   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>手续费</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-07-31 15:43:25    
	 */
	public BigDecimal getFeeMoney() {
		return feeMoney;
	}
	
	/**
	 * <p>手续费</p>
	 * @author:  zhouming
	 * @param:   @param feeMoney
	 * @return:  void 
	 * @Date :   2019-07-31 15:43:25   
	 */
	public void setFeeMoney(BigDecimal feeMoney) {
		this.feeMoney = feeMoney;
	}
	
	
	/**
	 * <p>手续费类型 1.币币交易手续费 2.提币手续费 3.OTC手续费  4.C2C手续费</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-07-31 15:43:25    
	 */
	public Integer getFeeType() {
		return feeType;
	}
	
	/**
	 * <p>手续费类型 1.币币交易手续费 2.提币手续费 3.OTC手续费  4.C2C手续费</p>
	 * @author:  zhouming
	 * @param:   @param feeType
	 * @return:  void 
	 * @Date :   2019-07-31 15:43:25   
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-07-31 15:43:25    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-07-31 15:43:25   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
