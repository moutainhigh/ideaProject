/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-16 16:55:41 
 */
package hry.admin.klg.limit.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgAmountLimitation </p>
 * @author:         lzy
 * @Date :          2019-04-16 16:55:41  
 */
@Table(name="klg_amount_limitation")
public class KlgAmountLimitation extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "state")
	private Integer state;  //是否限额
	
	@Column(name= "money")
	private BigDecimal money;  //金额
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "type")
	private Integer type;  //类型

	@Column(name= "morningLimit")
	private BigDecimal morningLimit;  //上午额度

	@Column(name= "afternoonLimit")
	private BigDecimal afternoonLimit;  //下午额度

	@Column(name= "totalMoney")
	private BigDecimal totalMoney;  //总金额

	@Column(name= "pmTime")
	private String pmTime;  //下午开盘时间

	public BigDecimal getMorningLimit() {
		return morningLimit;
	}

	public void setMorningLimit(BigDecimal morningLimit) {
		this.morningLimit = morningLimit;
	}

	public BigDecimal getAfternoonLimit() {
		return afternoonLimit;
	}

	public void setAfternoonLimit(BigDecimal afternoonLimit) {
		this.afternoonLimit = afternoonLimit;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getPmTime() {
		return pmTime;
	}

	public void setPmTime(String pmTime) {
		this.pmTime = pmTime;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-16 16:55:41    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-16 16:55:41   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>是否限额</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-16 16:55:41    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>是否限额</p>
	 * @author:  lzy
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-04-16 16:55:41   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>金额</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-16 16:55:41    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>金额</p>
	 * @author:  lzy
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-04-16 16:55:41   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-16 16:55:41    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-16 16:55:41   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>类型</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-16 16:55:41    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型</p>
	 * @author:  lzy
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-04-16 16:55:41   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
