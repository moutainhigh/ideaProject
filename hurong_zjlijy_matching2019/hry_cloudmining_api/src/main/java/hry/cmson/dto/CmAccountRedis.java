/**
 * Copyright:
 * @author:      gaomimi
 * @version:     V1.0
 * @Date:        2019-03-21 13:45:15
 */
package hry.cmson.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> ContractedOneAccount </p>
 * @author:         gaomimi
 * @Date :          2019-03-21 13:45:15
 */

public class CmAccountRedis implements Serializable {



	private Long id;  //

	private String coinCode;  //

	private Long customerId;  //

	private BigDecimal hotMoney;  //热账户

	private BigDecimal coldMoney;  //冷账户

	private Integer type;  //

	private Date modified;
	
	private BigDecimal emptyCoinMoney;  //空投币剩余

	private BigDecimal emptyCoinMoneyAll;  //空投币总额
	
	public BigDecimal valuation;//估值
	
	public BigDecimal ordinaryAccountBalance;//普通账户余额

	private Integer keepDecimal;//保留小数位
	
	

	public BigDecimal getOrdinaryAccountBalance() {
		return ordinaryAccountBalance;
	}

	public void setOrdinaryAccountBalance(BigDecimal ordinaryAccountBalance) {
		this.ordinaryAccountBalance = ordinaryAccountBalance;
	}

	public Integer getKeepDecimal() {
		return keepDecimal;
	}

	public void setKeepDecimal(Integer keepDecimal) {
		this.keepDecimal = keepDecimal;
	}

	public BigDecimal getValuation() {
		return valuation;
	}

	public void setValuation(BigDecimal valuation) {
		this.valuation = valuation;
	}

	public BigDecimal getEmptyCoinMoney() {
		return emptyCoinMoney;
	}

	public void setEmptyCoinMoney(BigDecimal emptyCoinMoney) {
		this.emptyCoinMoney = emptyCoinMoney;
	}

	public BigDecimal getEmptyCoinMoneyAll() {
		return emptyCoinMoneyAll;
	}

	public void setEmptyCoinMoneyAll(BigDecimal emptyCoinMoneyAll) {
		this.emptyCoinMoneyAll = emptyCoinMoneyAll;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getHotMoneyDouble() {
		return hotMoneyDouble;
	}

	public void setHotMoneyDouble(Double hotMoneyDouble) {
		this.hotMoneyDouble = hotMoneyDouble;
	}

	public Double getColdMoneyDouble() {
		return coldMoneyDouble;
	}

	public void setColdMoneyDouble(Double coldMoneyDouble) {
		this.coldMoneyDouble = coldMoneyDouble;
	}

	public Date getModified () {
		return modified;
	}

	public void setModified (Date modified) {
		this.modified = modified;
	}

	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @return:  Long
	 * @Date :   2019-03-21 13:45:15
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2019-03-21 13:45:15
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @return:  String
	 * @Date :   2019-03-21 13:45:15
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @param:   @param coinCode
	 * @return:  void
	 * @Date :   2019-03-21 13:45:15
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}



	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @return:  BigDecimal
	 * @Date :   2019-03-21 13:45:15
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}

	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @param:   @param hotMoney
	 * @return:  void
	 * @Date :   2019-03-21 13:45:15
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}


	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @return:  BigDecimal
	 * @Date :   2019-03-21 13:45:15
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}

	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @param:   @param coldMoney
	 * @return:  void
	 * @Date :   2019-03-21 13:45:15
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}

	public Double getEmptyCoinMoneyDouble() {
		return emptyCoinMoneyDouble;
	}

	public void setEmptyCoinMoneyDouble(Double emptyCoinMoneyDouble) {
		this.emptyCoinMoneyDouble = emptyCoinMoneyDouble;
	}

	public Double getEmptyCoinMoneyAllDouble() {
		return emptyCoinMoneyAllDouble;
	}

	public void setEmptyCoinMoneyAllDouble(Double emptyCoinMoneyAllDouble) {
		this.emptyCoinMoneyAllDouble = emptyCoinMoneyAllDouble;
	}

	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @return:  Integer
	 * @Date :   2019-03-21 13:45:15
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @param:   @param type
	 * @return:  void
	 * @Date :   2019-03-21 13:45:15
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	private Double hotMoneyDouble;  //可用总额

	private Double coldMoneyDouble;  //冻结总额
	private Double emptyCoinMoneyDouble;  //可用总额

	private Double emptyCoinMoneyAllDouble;  //冻结总额


}
