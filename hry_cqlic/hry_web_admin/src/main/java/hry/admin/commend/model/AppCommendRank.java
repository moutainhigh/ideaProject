/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:49:44 
 */
package hry.admin.commend.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppCommendRank </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:49:44  
 */
@Table(name="app_commend_rank")
public class AppCommendRank extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "userId")
	private Long userId;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "fixMoney")
	private BigDecimal fixMoney;  //
	
	@Column(name= "fixCoin")
	private String fixCoin;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 19:49:44    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:44   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 19:49:44    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:44   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:49:44    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:44   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:49:44    
	 */
	public BigDecimal getFixMoney() {
		return fixMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fixMoney
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:44   
	 */
	public void setFixMoney(BigDecimal fixMoney) {
		this.fixMoney = fixMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:49:44    
	 */
	public String getFixCoin() {
		return fixCoin;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fixCoin
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:44   
	 */
	public void setFixCoin(String fixCoin) {
		this.fixCoin = fixCoin;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:49:44    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:44   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:49:44    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:44   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
