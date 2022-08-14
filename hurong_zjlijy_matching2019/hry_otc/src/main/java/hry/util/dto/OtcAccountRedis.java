/**
 * Copyright:   
 * @author:      xubb
 * @version:     V1.0 
 * @Date:        2019-07-04 10:00:54 
 */
package hry.util.dto;


import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> OtcAccount </p>
 * @author:         xubb
 * @Date :          2019-07-04 10:00:54  
 */
@Table(name="otc_account")
public class OtcAccountRedis implements Serializable {
	
	
	private Long id;  //
	
	private Long customerId;  //
	
	private String coinCode;  //币种
	
	private BigDecimal hotMoney;  //可用
	
	private BigDecimal coldMoney;  //冻结


	private Double hotMoneyDouble;  //可用总额

	private Double coldMoneyDouble;  //冻结总额

	private Date modified;

	public BigDecimal ordinaryAccountBalance;//普通账户余额

	private Integer keepDecimal;//保留小数位

	public BigDecimal valuation;//估值

	/**
	 * <p></p>
	 * @author:  xubb
	 * @return:  Long 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  xubb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  xubb
	 * @return:  Long 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  xubb
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  xubb
	 * @return:  String 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  xubb
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>可用</p>
	 * @author:  xubb
	 * @return:  BigDecimal 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p>可用</p>
	 * @author:  xubb
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p>冻结</p>
	 * @author:  xubb
	 * @return:  BigDecimal 
	 * @Date :   2019-07-04 10:00:54    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p>冻结</p>
	 * @author:  xubb
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2019-07-04 10:00:54   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}

	public Double getHotMoneyDouble () {
		return hotMoneyDouble;
	}

	public void setHotMoneyDouble (Double hotMoneyDouble) {
		this.hotMoneyDouble = hotMoneyDouble;
	}

	public Double getColdMoneyDouble () {
		return coldMoneyDouble;
	}

	public void setColdMoneyDouble (Double coldMoneyDouble) {
		this.coldMoneyDouble = coldMoneyDouble;
	}

	public Date getModified () {
		return modified;
	}

	public void setModified (Date modified) {
		this.modified = modified;
	}

	public BigDecimal getOrdinaryAccountBalance () {
		return ordinaryAccountBalance;
	}

	public void setOrdinaryAccountBalance (BigDecimal ordinaryAccountBalance) {
		this.ordinaryAccountBalance = ordinaryAccountBalance;
	}

	public Integer getKeepDecimal () {
		return keepDecimal;
	}

	public void setKeepDecimal (Integer keepDecimal) {
		this.keepDecimal = keepDecimal;
	}

	public BigDecimal getValuation () {
		return valuation;
	}

	public void setValuation (BigDecimal valuation) {
		this.valuation = valuation;
	}
}
