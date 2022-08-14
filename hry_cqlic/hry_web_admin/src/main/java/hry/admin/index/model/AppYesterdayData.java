/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-10-08 13:45:53 
 */
package hry.admin.index.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> AppYesterdayData </p>
 * @author:         liuchenghui
 * @Date :          2018-10-08 13:45:53  
 */
@Table(name="app_yesterday_data")
public class AppYesterdayData extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "totalTradeMoney")
	private BigDecimal totalTradeMoney;  //截止昨日总交易量（折合usdt）
	
	@Column(name= "newCustomer")
	private BigDecimal newCustomer;  //昨日新增客户
	
	@Column(name= "newTrades")
	private BigDecimal newTrades;  //昨日新增交易
	
	@Column(name= "newPost")
	private BigDecimal newPost;  //昨日新增充币
	
	@Column(name= "newGet")
	private BigDecimal newGet;  //昨日新增提币
	
	@Column(name= "newMining")
	private BigDecimal newMining;  //昨日新增挖矿
	
	@Column(name= "newDividends")
	private BigDecimal newDividends;  //昨日新增分红

	@Column(name= "saasId")
	private String saasId;  //
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-10-08 13:45:53    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-10-08 13:45:53   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  BigDecimal 
	 * @Date :   2018-10-08 13:45:53    
	 */
	public BigDecimal getTotalTradeMoney() {
		return totalTradeMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param totalTradeMoney
	 * @return:  void 
	 * @Date :   2018-10-08 13:45:53   
	 */
	public void setTotalTradeMoney(BigDecimal totalTradeMoney) {
		this.totalTradeMoney = totalTradeMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  BigDecimal 
	 * @Date :   2018-10-08 13:45:53    
	 */
	public BigDecimal getNewCustomer() {
		return newCustomer;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param newCustomer
	 * @return:  void 
	 * @Date :   2018-10-08 13:45:53   
	 */
	public void setNewCustomer(BigDecimal newCustomer) {
		this.newCustomer = newCustomer;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  BigDecimal 
	 * @Date :   2018-10-08 13:45:53    
	 */
	public BigDecimal getNewTrades() {
		return newTrades;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param newTrades
	 * @return:  void 
	 * @Date :   2018-10-08 13:45:53   
	 */
	public void setNewTrades(BigDecimal newTrades) {
		this.newTrades = newTrades;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  BigDecimal 
	 * @Date :   2018-10-08 13:45:53    
	 */
	public BigDecimal getNewPost() {
		return newPost;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param newPost
	 * @return:  void 
	 * @Date :   2018-10-08 13:45:53   
	 */
	public void setNewPost(BigDecimal newPost) {
		this.newPost = newPost;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  BigDecimal 
	 * @Date :   2018-10-08 13:45:53    
	 */
	public BigDecimal getNewGet() {
		return newGet;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param newGet
	 * @return:  void 
	 * @Date :   2018-10-08 13:45:53   
	 */
	public void setNewGet(BigDecimal newGet) {
		this.newGet = newGet;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  BigDecimal 
	 * @Date :   2018-10-08 13:45:53    
	 */
	public BigDecimal getNewMining() {
		return newMining;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param newMining
	 * @return:  void 
	 * @Date :   2018-10-08 13:45:53   
	 */
	public void setNewMining(BigDecimal newMining) {
		this.newMining = newMining;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  BigDecimal 
	 * @Date :   2018-10-08 13:45:53    
	 */
	public BigDecimal getNewDividends() {
		return newDividends;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param newDividends
	 * @return:  void 
	 * @Date :   2018-10-08 13:45:53   
	 */
	public void setNewDividends(BigDecimal newDividends) {
		this.newDividends = newDividends;
	}

	public String getSaasId () {
		return saasId;
	}

	public void setSaasId (String saasId) {
		this.saasId = saasId;
	}
}
