/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 16:39:22 
 */
package hry.otc.releaseAdvertisement.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExCoinFee </p>
 * @author:         denghf
 * @Date :          2018-06-29 16:39:22  
 */
@Table(name="ex_coin_fee")
public class ExCoinFee extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "coinId")
	private Long coinId;  //币的ID
	
	@Column(name= "coinCode")
	private String coinCode;  //币种名称
	
	@Column(name= "coinName")
	private String coinName;  //币种代码
	
	@Column(name= "userName")
	private String userName;  //用户名
	
	@Column(name= "status")
	private Integer status;  //1提币 2购买 3出售
	
	@Column(name= "volume")
	private BigDecimal volume;  //成交量
	
	@Column(name= "feeType")
	private Integer feeType;  //手续费类型 0固定费率 1百分比费率
	
	@Column(name= "fee")
	private BigDecimal fee;  //手续费
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币的ID</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public Long getCoinId() {
		return coinId;
	}
	
	/**
	 * <p>币的ID</p>
	 * @author:  denghf
	 * @param:   @param coinId
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setCoinId(Long coinId) {
		this.coinId = coinId;
	}
	
	
	/**
	 * <p>币种名称</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种名称</p>
	 * @author:  denghf
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public String getCoinName() {
		return coinName;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  denghf
	 * @param:   @param coinName
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	
	/**
	 * <p>用户名</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>用户名</p>
	 * @author:  denghf
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>1提币 2购买 3出售</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>1提币 2购买 3出售</p>
	 * @author:  denghf
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>成交量</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public BigDecimal getVolume() {
		return volume;
	}
	
	/**
	 * <p>成交量</p>
	 * @author:  denghf
	 * @param:   @param volume
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	
	/**
	 * <p>手续费类型 0固定费率 1百分比费率</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public Integer getFeeType() {
		return feeType;
	}
	
	/**
	 * <p>手续费类型 0固定费率 1百分比费率</p>
	 * @author:  denghf
	 * @param:   @param feeType
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	
	
	/**
	 * <p>手续费</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-06-29 16:39:22    
	 */
	public BigDecimal getFee() {
		return fee;
	}
	
	/**
	 * <p>手续费</p>
	 * @author:  denghf
	 * @param:   @param fee
	 * @return:  void 
	 * @Date :   2018-06-29 16:39:22   
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	

}
