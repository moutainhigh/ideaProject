/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 10:07:04 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p> ExCointoMoney </p>
 * @author:         tianpengyu
 * @Date :          2018-08-22 10:07:04  
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
	private String isSynC2C;  //c2c是否同步
	
	@Column(name= "state")
	private Integer state;  //状态 1启用   0未启用

	@Column(name= "creator")
	private String creator;  //创建人

	@Column(name= "created")
	private Date created;  //

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-08-22 10:07:04    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-08-22 10:07:04   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-22 10:07:04    
	 */
	public String getLan() {
		return lan;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param lan
	 * @return:  void 
	 * @Date :   2018-08-22 10:07:04   
	 */
	public void setLan(String lan) {
		this.lan = lan;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-22 10:07:04    
	 */
	public String getExchange() {
		return exchange;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param exchange
	 * @return:  void 
	 * @Date :   2018-08-22 10:07:04   
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-22 10:07:04    
	 */
	public String getRate() {
		return rate;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param rate
	 * @return:  void 
	 * @Date :   2018-08-22 10:07:04   
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-22 10:07:04    
	 */
	public String getCoinSymbol() {
		return coinSymbol;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinSymbol
	 * @return:  void 
	 * @Date :   2018-08-22 10:07:04   
	 */
	public void setCoinSymbol(String coinSymbol) {
		this.coinSymbol = coinSymbol;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-22 10:07:04    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-08-22 10:07:04   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-22 10:07:04    
	 */
	public String getIsSynC2C() {
		return isSynC2C;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param isSynC2C
	 * @return:  void 
	 * @Date :   2018-08-22 10:07:04   
	 */
	public void setIsSynC2C(String isSynC2C) {
		this.isSynC2C = isSynC2C;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-08-22 10:07:04    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-08-22 10:07:04   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	

}
