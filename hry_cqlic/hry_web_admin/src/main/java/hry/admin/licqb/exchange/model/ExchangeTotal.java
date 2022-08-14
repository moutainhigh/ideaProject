/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:37:14 
 */
package hry.admin.licqb.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExchangeTotal </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:37:14  
 */
@Table(name="lc_exchange_total")
public class ExchangeTotal extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "gainNum")
	private BigDecimal gainNum;  //兑换总数
	
	@Column(name= "extractNum")
	private BigDecimal extractNum;  //提取总数
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-12-17 16:37:14    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-12-17 16:37:14   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:37:14    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2020-12-17 16:37:14   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>兑换总数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:37:14    
	 */
	public BigDecimal getGainNum() {
		return gainNum;
	}
	
	/**
	 * <p>兑换总数</p>
	 * @author:  zhouming
	 * @param:   @param gainNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:37:14   
	 */
	public void setGainNum(BigDecimal gainNum) {
		this.gainNum = gainNum;
	}
	
	
	/**
	 * <p>提取总数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-12-17 16:37:14    
	 */
	public BigDecimal getExtractNum() {
		return extractNum;
	}
	
	/**
	 * <p>提取总数</p>
	 * @author:  zhouming
	 * @param:   @param extractNum
	 * @return:  void 
	 * @Date :   2020-12-17 16:37:14   
	 */
	public void setExtractNum(BigDecimal extractNum) {
		this.extractNum = extractNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:37:14    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-12-17 16:37:14   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
