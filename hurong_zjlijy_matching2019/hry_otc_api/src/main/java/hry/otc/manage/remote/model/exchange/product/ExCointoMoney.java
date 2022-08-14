/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2019-01-17 15:25:20 
 */
package hry.otc.manage.remote.model.exchange.product;

import hry.otc.manage.remote.model.core.BaseModel;

import javax.persistence.*;

/**
 * <p> ExCointoMoney </p>
 * @author:         tianpengyu
 * @Date :          2019-01-17 15:25:20  
 */
@Table(name="ex_cointo_money")
public class ExCointoMoney extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "lan")
	private String lan;  //语种
	
	@Column(name= "exchange")
	private String exchange;  //货币兑换
	
	@Column(name= "rate")
	private String rate;  //汇率
	
	@Column(name= "coinSymbol")
	private String coinSymbol;  //币种符号
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代号
	
	@Column(name= "isSynC2C")
	private String isSynC2C;  //C2C是否同步
	
	@Column(name= "state")
	private Integer state;  //状态 1启用   0未启用
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2019-01-17 15:25:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-17 15:25:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>语种</p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2019-01-17 15:25:20    
	 */
	public String getLan() {
		return lan;
	}
	
	/**
	 * <p>语种</p>
	 * @author:  tianpengyu
	 * @param:   @param lan
	 * @return:  void 
	 * @Date :   2019-01-17 15:25:20   
	 */
	public void setLan(String lan) {
		this.lan = lan;
	}
	
	
	/**
	 * <p>货币兑换</p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2019-01-17 15:25:20    
	 */
	public String getExchange() {
		return exchange;
	}
	
	/**
	 * <p>货币兑换</p>
	 * @author:  tianpengyu
	 * @param:   @param exchange
	 * @return:  void 
	 * @Date :   2019-01-17 15:25:20   
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
	
	/**
	 * <p>汇率</p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2019-01-17 15:25:20    
	 */
	public String getRate() {
		return rate;
	}
	
	/**
	 * <p>汇率</p>
	 * @author:  tianpengyu
	 * @param:   @param rate
	 * @return:  void 
	 * @Date :   2019-01-17 15:25:20   
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	
	/**
	 * <p>币种符号</p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2019-01-17 15:25:20    
	 */
	public String getCoinSymbol() {
		return coinSymbol;
	}
	
	/**
	 * <p>币种符号</p>
	 * @author:  tianpengyu
	 * @param:   @param coinSymbol
	 * @return:  void 
	 * @Date :   2019-01-17 15:25:20   
	 */
	public void setCoinSymbol(String coinSymbol) {
		this.coinSymbol = coinSymbol;
	}
	
	
	/**
	 * <p>币种代号</p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2019-01-17 15:25:20    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代号</p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-17 15:25:20   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>C2C是否同步</p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2019-01-17 15:25:20    
	 */
	public String getIsSynC2C() {
		return isSynC2C;
	}
	
	/**
	 * <p>C2C是否同步</p>
	 * @author:  tianpengyu
	 * @param:   @param isSynC2C
	 * @return:  void 
	 * @Date :   2019-01-17 15:25:20   
	 */
	public void setIsSynC2C(String isSynC2C) {
		this.isSynC2C = isSynC2C;
	}
	
	
	/**
	 * <p>状态 1启用   0未启用</p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2019-01-17 15:25:20    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>状态 1启用   0未启用</p>
	 * @author:  tianpengyu
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-01-17 15:25:20   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	

}
