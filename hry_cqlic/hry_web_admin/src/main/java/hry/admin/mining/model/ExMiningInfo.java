/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2018-06-25 17:28:10 
 */
package hry.admin.mining.model;

import hry.bean.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p> ExMiningInfo </p>
 * @author:         jidn
 * @Date :          2018-06-25 17:28:10  
 */
@Table(name="ex_mining_info")
public class ExMiningInfo extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "feeSum")
	private BigDecimal feeSum;  //产生的总手续费
	
	@Column(name= "returnFee")
	private BigDecimal returnFee;  //返还的手续费
	
	@Column(name= "platformCoinAvg")
	private BigDecimal platformCoinAvg;  //返还时 平台币的均价
	
	@Column(name= "coinCode")
	private String coinCode;//币种名称
	
	@Column(name= "type")
	private Integer type;//类型 0挖矿 1分红
	
	
	
	
	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2018-06-25 17:28:10    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-25 17:28:10   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>产生的总手续费</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 17:28:10    
	 */
	public BigDecimal getFeeSum() {
		return feeSum;
	}
	
	/**
	 * <p>产生的总手续费</p>
	 * @author:  jidn
	 * @param:   @param feeSum
	 * @return:  void 
	 * @Date :   2018-06-25 17:28:10   
	 */
	public void setFeeSum(BigDecimal feeSum) {
		this.feeSum = feeSum;
	}
	
	
	/**
	 * <p>返还的手续费</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 17:28:10    
	 */
	public BigDecimal getReturnFee() {
		return returnFee;
	}
	
	/**
	 * <p>返还的手续费</p>
	 * @author:  jidn
	 * @param:   @param returnFee
	 * @return:  void 
	 * @Date :   2018-06-25 17:28:10   
	 */
	public void setReturnFee(BigDecimal returnFee) {
		this.returnFee = returnFee;
	}
	
	
	/**
	 * <p>返还时 平台币的均价</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 17:28:10    
	 */
	public BigDecimal getPlatformCoinAvg() {
		return platformCoinAvg;
	}
	
	/**
	 * <p>返还时 平台币的均价</p>
	 * @author:  jidn
	 * @param:   @param platformCoinAvg
	 * @return:  void 
	 * @Date :   2018-06-25 17:28:10   
	 */
	public void setPlatformCoinAvg(BigDecimal platformCoinAvg) {
		this.platformCoinAvg = platformCoinAvg;
	}
	
	

}
