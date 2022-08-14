/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-12-14 15:06:35 
 */
package hry.customer.commend.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppCommendRank </p>
 * @author:         menwei
 * @Date :          2017-12-14 15:06:35  
 */
@Table(name="app_commend_rank")
public class AppCommendRank extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "userId")
	private Long userId;  //userId
	
	@Column(name= "userName")
	private String userName;  //userName
	
	@Column(name= "fixMoney")
	private BigDecimal fixMoney;  //fixMoney
	
	@Column(name= "fixCoin")
	private String fixCoin;  //fixCoin	币种
	
	@Column(name= "coinCode")
	private String coinCode;  //coinCode
	
	
	
	

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p>userId</p>
	 * @author:  menwei
	 * @return:  Long 
	 * @Date :   2017-12-14 15:06:35    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p>userId</p>
	 * @author:  menwei
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2017-12-14 15:06:35   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p>userName</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-12-14 15:06:35    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>userName</p>
	 * @author:  menwei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2017-12-14 15:06:35   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>fixMoney</p>
	 * @author:  menwei
	 * @return:  BigDecimal 
	 * @Date :   2017-12-14 15:06:35    
	 */
	public BigDecimal getFixMoney() {
		return fixMoney;
	}
	
	/**
	 * <p>fixMoney</p>
	 * @author:  menwei
	 * @param:   @param fixMoney
	 * @return:  void 
	 * @Date :   2017-12-14 15:06:35   
	 */
	public void setFixMoney(BigDecimal fixMoney) {
		this.fixMoney = fixMoney;
	}
	
	
	/**
	 * <p>fixCoin</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-12-14 15:06:35    
	 */
	public String getFixCoin() {
		return fixCoin;
	}
	
	/**
	 * <p>fixCoin</p>
	 * @author:  menwei
	 * @param:   @param fixCoin
	 * @return:  void 
	 * @Date :   2017-12-14 15:06:35   
	 */
	public void setFixCoin(String fixCoin) {
		this.fixCoin = fixCoin;
	}
	
	
	/**
	 * <p>coinCode</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-12-14 15:06:35    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>coinCode</p>
	 * @author:  menwei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2017-12-14 15:06:35   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	

}
