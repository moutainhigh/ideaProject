/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-12-06 18:43:30 
 */
package hry.customer.businessman.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppBusinessman </p>
 * @author:         liushilei
 * @Date :          2017-12-06 18:43:30  
 */
@Table(name="app_businessman")
public class AppBusinessman extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "userId")
	private Long userId;  //userId
	
	@Column(name= "trueName")
	private String trueName;  //真实姓名
	
	@Column(name= "type")
	private Integer type;  //type   A类==0   B类==1
	
	@Column(name= "feeType")
	private Integer feeType;  //feeType   固定费率==0   百分比费率==1
	
	@Column(name= "fee")
	private BigDecimal fee;  //fee
	
	@Column(name= "price")
	private BigDecimal price;  //价格
	
	@Column(name= "nationality")
	private String nationality;  //nationality
	
	@Column(name= "changeCoin")
	private String changeCoin;  //changeCoin
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2017-12-06 18:43:30    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-12-06 18:43:30   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>userId</p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2017-12-06 18:43:30    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p>userId</p>
	 * @author:  liushilei
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2017-12-06 18:43:30   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p>真实姓名</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-12-06 18:43:30    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p>真实姓名</p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2017-12-06 18:43:30   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p>type</p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2017-12-06 18:43:30    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>type</p>
	 * @author:  liushilei
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2017-12-06 18:43:30   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>feeType</p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2017-12-06 18:43:30    
	 */
	public Integer getFeeType() {
		return feeType;
	}
	
	/**
	 * <p>feeType</p>
	 * @author:  liushilei
	 * @param:   @param feeType
	 * @return:  void 
	 * @Date :   2017-12-06 18:43:30   
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	
	
	/**
	 * <p>fee</p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2017-12-06 18:43:30    
	 */
	public BigDecimal getFee() {
		return fee;
	}
	
	/**
	 * <p>fee</p>
	 * @author:  liushilei
	 * @param:   @param fee
	 * @return:  void 
	 * @Date :   2017-12-06 18:43:30   
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	
	/**
	 * <p>nationality</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-12-06 18:43:30    
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * <p>nationality</p>
	 * @author:  liushilei
	 * @param:   @param nationality
	 * @return:  void 
	 * @Date :   2017-12-06 18:43:30   
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	/**
	 * <p>changeCoin</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-12-06 18:43:30    
	 */
	public String getChangeCoin() {
		return changeCoin;
	}
	
	/**
	 * <p>changeCoin</p>
	 * @author:  liushilei
	 * @param:   @param changeCoin
	 * @return:  void 
	 * @Date :   2017-12-06 18:43:30   
	 */
	public void setChangeCoin(String changeCoin) {
		this.changeCoin = changeCoin;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
	

}
