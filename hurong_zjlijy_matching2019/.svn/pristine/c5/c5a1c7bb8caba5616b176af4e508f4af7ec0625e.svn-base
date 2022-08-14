/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-01 13:37:58 
 */
package hry.cm.dividend.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> CmDividendRecord </p>
 * @author:         zhouming
 * @Date :          2019-08-01 13:37:58  
 */
@Table(name="cm_dividend_record")
public class CmDividendRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "cmCustomerId")
	private Long cmCustomerId;  //矿机会员表Id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "dividendGross")
	private BigDecimal dividendGross;  //加权分红总量
	
	@Column(name= "ratio")
	private String ratio;  //分红比例
	
	@Column(name= "dividend")
	private BigDecimal dividend;  //个人加权分红量
	
	@Column(name= "status")
	private Integer status;  //分红是否领取 1未领取 2已领取
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-01 13:37:58    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-01 13:37:58   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-01 13:37:58    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-01 13:37:58   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>矿机会员表Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-01 13:37:58    
	 */
	public Long getCmCustomerId() {
		return cmCustomerId;
	}
	
	/**
	 * <p>矿机会员表Id</p>
	 * @author:  zhouming
	 * @param:   @param cmCustomerId
	 * @return:  void 
	 * @Date :   2019-08-01 13:37:58   
	 */
	public void setCmCustomerId(Long cmCustomerId) {
		this.cmCustomerId = cmCustomerId;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-01 13:37:58    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-08-01 13:37:58   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>加权分红总量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-01 13:37:58    
	 */
	public BigDecimal getDividendGross() {
		return dividendGross;
	}
	
	/**
	 * <p>加权分红总量</p>
	 * @author:  zhouming
	 * @param:   @param dividendGross
	 * @return:  void 
	 * @Date :   2019-08-01 13:37:58   
	 */
	public void setDividendGross(BigDecimal dividendGross) {
		this.dividendGross = dividendGross;
	}
	
	
	/**
	 * <p>分红比例</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-01 13:37:58    
	 */
	public String getRatio() {
		return ratio;
	}
	
	/**
	 * <p>分红比例</p>
	 * @author:  zhouming
	 * @param:   @param ratio
	 * @return:  void 
	 * @Date :   2019-08-01 13:37:58   
	 */
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	
	
	/**
	 * <p>个人加权分红量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-08-01 13:37:58    
	 */
	public BigDecimal getDividend() {
		return dividend;
	}
	
	/**
	 * <p>个人加权分红量</p>
	 * @author:  zhouming
	 * @param:   @param dividend
	 * @return:  void 
	 * @Date :   2019-08-01 13:37:58   
	 */
	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}
	
	
	/**
	 * <p>分红是否领取 1未领取 2已领取</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-01 13:37:58    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>分红是否领取 1未领取 2已领取</p>
	 * @author:  zhouming
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-08-01 13:37:58   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
