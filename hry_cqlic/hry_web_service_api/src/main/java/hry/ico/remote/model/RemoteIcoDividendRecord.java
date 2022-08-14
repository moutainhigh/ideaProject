/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 20:56:02 
 */
package hry.ico.remote.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> IcoDividendRecord </p>
 * @author:         houz
 * @Date :          2019-01-14 20:56:02  
 */
public class RemoteIcoDividendRecord extends BaseModel {
	
	

	private Long id;  //
	
	private Long customer_id;  //账户id
	
	private String customer_email;  //账户邮箱
	
	private String customer_mobile;  //账户手机
	
	private String customer_level;  //账户等级
	
	private BigDecimal hedgeQuantity;  //锁仓量
	
	private BigDecimal grossCommission;  //手续费总量
	
	private Long coid_id;  //币id
	
	private String coinCode;  //币种(CNY)
	
	private String coinName;  //虚拟币种汉语名称(比特币 莱特币..)

	private BigDecimal dividend_radix;  //分红数量

	private String dividend_num;  //分红流水号
	
	private String status;  //状态(1 分红发放中，2 成功 ，3 失败)

	private String rebate_ratio;  //返佣占比（带百分号）

	private String additionRatio;  //加成比例


	public String getRebate_ratio() {
		return rebate_ratio;
	}

	public void setRebate_ratio(String rebate_ratio) {
		this.rebate_ratio = rebate_ratio;
	}

	public String getAdditionRatio() {
		return additionRatio;
	}

	public void setAdditionRatio(String additionRatio) {
		this.additionRatio = additionRatio;
	}
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>账户id</p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public Long getCustomer_id() {
		return customer_id;
	}
	
	/**
	 * <p>账户id</p>
	 * @author:  houz
	 * @param:   @param customer_id
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	
	
	/**
	 * <p>账户邮箱</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public String getCustomer_email() {
		return customer_email;
	}
	
	/**
	 * <p>账户邮箱</p>
	 * @author:  houz
	 * @param:   @param customer_email
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	
	
	/**
	 * <p>账户手机</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public String getCustomer_mobile() {
		return customer_mobile;
	}
	
	/**
	 * <p>账户手机</p>
	 * @author:  houz
	 * @param:   @param customer_mobile
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}
	
	
	/**
	 * <p>账户等级</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public String getCustomer_level() {
		return customer_level;
	}
	
	/**
	 * <p>账户等级</p>
	 * @author:  houz
	 * @param:   @param customer_level
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setCustomer_level(String customer_level) {
		this.customer_level = customer_level;
	}
	
	
	/**
	 * <p>锁仓量</p>
	 * @author:  houz
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public BigDecimal getHedgeQuantity() {
		return hedgeQuantity;
	}
	
	/**
	 * <p>锁仓量</p>
	 * @author:  houz
	 * @param:   @param hedgeQuantity
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setHedgeQuantity(BigDecimal hedgeQuantity) {
		this.hedgeQuantity = hedgeQuantity;
	}
	
	
	/**
	 * <p>手续费总量</p>
	 * @author:  houz
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public BigDecimal getGrossCommission() {
		return grossCommission;
	}
	
	/**
	 * <p>手续费总量</p>
	 * @author:  houz
	 * @param:   @param grossCommission
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setGrossCommission(BigDecimal grossCommission) {
		this.grossCommission = grossCommission;
	}
	
	
	/**
	 * <p>币id</p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public Long getCoid_id() {
		return coid_id;
	}
	
	/**
	 * <p>币id</p>
	 * @author:  houz
	 * @param:   @param coid_id
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setCoid_id(Long coid_id) {
		this.coid_id = coid_id;
	}
	
	
	/**
	 * <p>币种(CNY)</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种(CNY)</p>
	 * @author:  houz
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>虚拟币种汉语名称(比特币 莱特币..)</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public String getCoinName() {
		return coinName;
	}
	
	/**
	 * <p>虚拟币种汉语名称(比特币 莱特币..)</p>
	 * @author:  houz
	 * @param:   @param coinName
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	
	/**
	 * <p>分红数量</p>
	 * @author:  houz
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public BigDecimal getDividend_radix() {
		return dividend_radix;
	}
	
	/**
	 * <p>分红数量</p>
	 * @author:  houz
	 * @param:   @param dividend_radix
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setDividend_radix(BigDecimal dividend_radix) {
		this.dividend_radix = dividend_radix;
	}
	
	
	/**
	 * <p>分红流水号</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public String getDividend_num() {
		return dividend_num;
	}
	
	/**
	 * <p>分红流水号</p>
	 * @author:  houz
	 * @param:   @param dividend_num
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setDividend_num(String dividend_num) {
		this.dividend_num = dividend_num;
	}
	
	
	/**
	 * <p>状态(1 分红发放中，2 成功 ，3 失败)</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-14 20:56:02    
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * <p>状态(1 分红发放中，2 成功 ，3 失败)</p>
	 * @author:  houz
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-01-14 20:56:02   
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
