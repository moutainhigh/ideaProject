/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0
 * @Date:        2018-10-18 14:47:26 
 */
package hry.admin.lend.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> ExLendConfig </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:47:26  
 */
@Table(name="ex_lend_config")
public class ExLendConfig extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "multiple")
	private BigDecimal multiple;  //杠杆倍数

	@Column(name= "coinCode")
	private String coinCode;  //交易对

	@Column(name= "dayRatio")
	private BigDecimal dayRatio;  //杠杆日利率%

	@Column(name= "warningRatio")
	private BigDecimal warningRatio;  //预警线%

	@Column(name= "pingRatio")
	private BigDecimal pingRatio;  //平仓线%

	@Column(name= "outRatio")
	private BigDecimal outRatio;  //转出线%

	@Column(name= "coinLendMax")
	private BigDecimal coinLendMax;  //交易币借款上限

	@Column(name= "coinLendMin")
	private BigDecimal coinLendMin;  //交易币借款下限

	@Column(name= "priLendMax")
	private BigDecimal priLendMax;  //定价币借款上限

	@Column(name= "priLendMin")
	private BigDecimal priLendMin;  //定价币借款下限

	@Column(name= "status")
	private Integer status;  //0关闭 1开启	2入回收站

	@Transient
	private BigDecimal keepDecimalForCoin;  //定价币位数

	@Transient
	private BigDecimal keepDecimalForCurrency;  //交易币位数

	@Transient
	private BigDecimal initPrice;

	public BigDecimal getInitPrice() {
		return initPrice;
	}

	public void setInitPrice(BigDecimal initPrice) {
		this.initPrice = initPrice;
	}

	/**
	 * <p></p>
	 * @author:  HeC
	 * @return:  Long
	 * @Date :   2018-10-18 14:47:26    
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p></p>
	 * @author:  HeC
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p>杠杆倍数</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26    
	 */
	public BigDecimal getMultiple() {
		return multiple;
	}

	/**
	 * <p>杠杆倍数</p>
	 * @author:  HeC
	 * @param:   @param multiple
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setMultiple(BigDecimal multiple) {
		this.multiple = multiple;
	}


	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @return:  String
	 * @Date :   2018-10-18 14:47:26    
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @param:   @param coinCode
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}


	/**
	 * <p>杠杆日利率%</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26    
	 */
	public BigDecimal getDayRatio() {
		return dayRatio;
	}

	/**
	 * <p>杠杆日利率%</p>
	 * @author:  HeC
	 * @param:   @param dayRatio
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setDayRatio(BigDecimal dayRatio) {
		this.dayRatio = dayRatio;
	}


	/**
	 * <p>预警线%</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26    
	 */
	public BigDecimal getWarningRatio() {
		return warningRatio;
	}

	/**
	 * <p>预警线%</p>
	 * @author:  HeC
	 * @param:   @param warningRatio
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setWarningRatio(BigDecimal warningRatio) {
		this.warningRatio = warningRatio;
	}


	/**
	 * <p>平仓线%</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26    
	 */
	public BigDecimal getPingRatio() {
		return pingRatio;
	}

	/**
	 * <p>平仓线%</p>
	 * @author:  HeC
	 * @param:   @param pingRatio
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setPingRatio(BigDecimal pingRatio) {
		this.pingRatio = pingRatio;
	}


	/**
	 * <p>转出线%</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26    
	 */
	public BigDecimal getOutRatio() {
		return outRatio;
	}

	/**
	 * <p>转出线%</p>
	 * @author:  HeC
	 * @param:   @param outRatio
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setOutRatio(BigDecimal outRatio) {
		this.outRatio = outRatio;
	}


	/**
	 * <p>交易币借款上限</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26    
	 */
	public BigDecimal getCoinLendMax() {
		return coinLendMax;
	}

	/**
	 * <p>交易币借款上限</p>
	 * @author:  HeC
	 * @param:   @param coinLendMax
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setCoinLendMax(BigDecimal coinLendMax) {
		this.coinLendMax = coinLendMax;
	}


	/**
	 * <p>交易币借款下限</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26    
	 */
	public BigDecimal getCoinLendMin() {
		return coinLendMin;
	}

	/**
	 * <p>交易币借款下限</p>
	 * @author:  HeC
	 * @param:   @param coinLendMin
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setCoinLendMin(BigDecimal coinLendMin) {
		this.coinLendMin = coinLendMin;
	}


	/**
	 * <p>定价币借款上限</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26    
	 */
	public BigDecimal getPriLendMax() {
		return priLendMax;
	}

	/**
	 * <p>定价币借款上限</p>
	 * @author:  HeC
	 * @param:   @param priLendMax
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setPriLendMax(BigDecimal priLendMax) {
		this.priLendMax = priLendMax;
	}


	/**
	 * <p>定价币借款下限</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26    
	 */
	public BigDecimal getPriLendMin() {
		return priLendMin;
	}

	/**
	 * <p>定价币借款下限</p>
	 * @author:  HeC
	 * @param:   @param priLendMin
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setPriLendMin(BigDecimal priLendMin) {
		this.priLendMin = priLendMin;
	}


	/**
	 * <p>0关闭 1开启</p>
	 * @author:  HeC
	 * @return:  Integer
	 * @Date :   2018-10-18 14:47:26    
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * <p>0关闭 1开启</p>
	 * @author:  HeC
	 * @param:   @param status
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * <p>定价币位数</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26
	 */
	public BigDecimal getKeepDecimalForCoin() {
		return keepDecimalForCoin;
	}

	/**
	 * <p>定价币位数</p>
	 * @author:  HeC
	 * @param:   @param keepDecimalForCoin
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26
	 */
	public void setKeepDecimalForCoin(BigDecimal keepDecimalForCoin) {
		this.keepDecimalForCoin = keepDecimalForCoin;
	}


	/**
	 * <p>交易币位数</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:47:26
	 */
	public BigDecimal getKeepDecimalForCurrency() {
		return keepDecimalForCurrency;
	}

	/**
	 * <p>交易币位数</p>
	 * @author:  HeC
	 * @param:   @param keepDecimalForCurrency
	 * @return:  void
	 * @Date :   2018-10-18 14:47:26
	 */
	public void setKeepDecimalForCurrency(BigDecimal keepDecimalForCurrency) {
		this.keepDecimalForCurrency = keepDecimalForCurrency;
	}


}
