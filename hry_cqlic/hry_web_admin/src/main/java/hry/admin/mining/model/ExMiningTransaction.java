/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2018-08-03 13:45:09 
 */
package hry.admin.mining.model;

import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p> ExMiningTransaction </p>
 * @author:         jidn
 * @Date :          2018-08-03 13:45:09  
 */
@Table(name="ex_mining_transaction")
public class ExMiningTransaction extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //transactionNum
	
	@Column(name= "money")
	private BigDecimal money;  //派发总量
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "source")
	private String source;  //来源记录 ID 最小~最大
	
	@Column(name= "cutomerId")
	private Long cutomerId;  //用户ID
	
	@Column(name= "type")
	private Integer type;  //类型 0 挖矿 1分红
	
	@Column(name= "coinCode")
	private String coinCode;//币种代码
	@Transient
	private String cardId;
	
	
	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2018-08-03 13:45:09    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-08-03 13:45:09   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>transactionNum</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-08-03 13:45:09    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>transactionNum</p>
	 * @author:  jidn
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-08-03 13:45:09   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>派发总量</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-08-03 13:45:09    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>派发总量</p>
	 * @author:  jidn
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2018-08-03 13:45:09   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-08-03 13:45:09    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  jidn
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-08-03 13:45:09   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>来源记录 ID 最小~最大</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-08-03 13:45:09    
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * <p>来源记录 ID 最小~最大</p>
	 * @author:  jidn
	 * @param:   @param source
	 * @return:  void 
	 * @Date :   2018-08-03 13:45:09   
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2018-08-03 13:45:09    
	 */
	public Long getCutomerId() {
		return cutomerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  jidn
	 * @param:   @param cutomerId
	 * @return:  void 
	 * @Date :   2018-08-03 13:45:09   
	 */
	public void setCutomerId(Long cutomerId) {
		this.cutomerId = cutomerId;
	}
	
	
	/**
	 * <p>类型 0 挖矿 1分红</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2018-08-03 13:45:09    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型 0 挖矿 1分红</p>
	 * @author:  jidn
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-08-03 13:45:09   
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
