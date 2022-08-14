/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-28 11:56:37 
 */
package hry.model.social.traderecord;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> SocialCoinTradeRecord </p>
 * @author:         javalx
 * @Date :          2019-06-28 11:56:37  
 */
@Table(name="social_coin_trade_record")
public class SocialCoinTradeRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "category")
	private Integer category;  //收支类型（0：收入 ， 1：支出）
	
	@Column(name= "type")
	private Integer type;  //类型（0：奖励，1：充币，2：提币，3：支付）
	
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "states")
	private Integer states;  //状态（0：待审核，1：已冻结，2：已完成，3：已拒绝）
	
	@Column(name= "source")
	private String source;  //交易来源
	
	@Column(name= "number")
	private BigDecimal number;  //交易数量
	
	@Column(name= "customerId")
	private Long customerId;  //前端用户id
	
	@Column(name= "sourceNum")
	private String sourceNum;  //溯源标识
	
	
	
	
	/**
	 * <p></p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-28 11:56:37    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-28 11:56:37   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>收支类型（0：收入 ， 1：支出）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-28 11:56:37    
	 */
	public Integer getCategory() {
		return category;
	}
	
	/**
	 * <p>收支类型（0：收入 ， 1：支出）</p>
	 * @author:  javalx
	 * @param:   @param category
	 * @return:  void 
	 * @Date :   2019-06-28 11:56:37   
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}
	
	
	/**
	 * <p>类型（0：奖励，1：充币，2：提币，3：支付）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-28 11:56:37    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型（0：奖励，1：充币，2：提币，3：支付）</p>
	 * @author:  javalx
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-06-28 11:56:37   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-28 11:56:37    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  javalx
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-06-28 11:56:37   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>状态（0：待审核，1：已冻结，2：已完成，3：已拒绝）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-28 11:56:37    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>状态（0：待审核，1：已冻结，2：已完成，3：已拒绝）</p>
	 * @author:  javalx
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2019-06-28 11:56:37   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	/**
	 * <p>交易来源</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-28 11:56:37    
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * <p>交易来源</p>
	 * @author:  javalx
	 * @param:   @param source
	 * @return:  void 
	 * @Date :   2019-06-28 11:56:37   
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	
	/**
	 * <p>交易数量</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-28 11:56:37    
	 */
	public BigDecimal getNumber() {
		return number;
	}
	
	/**
	 * <p>交易数量</p>
	 * @author:  javalx
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-06-28 11:56:37   
	 */
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	
	
	/**
	 * <p>前端用户id</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-28 11:56:37    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>前端用户id</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-28 11:56:37   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>溯源标识</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-28 11:56:37    
	 */
	public String getSourceNum() {
		return sourceNum;
	}
	
	/**
	 * <p>溯源标识</p>
	 * @author:  javalx
	 * @param:   @param sourceNum
	 * @return:  void 
	 * @Date :   2019-06-28 11:56:37   
	 */
	public void setSourceNum(String sourceNum) {
		this.sourceNum = sourceNum;
	}
	
	

}
