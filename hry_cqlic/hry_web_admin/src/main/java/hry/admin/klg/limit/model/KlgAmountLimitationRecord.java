/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 14:48:45 
 */
package hry.admin.klg.limit.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgAmountLimitationRecord </p>
 * @author:         lzy
 * @Date :          2019-04-18 14:48:45  
 */
@Table(name="klg_amount_limitation_record")
public class KlgAmountLimitationRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "state")
	private Integer state;  //账户类型
	
	@Column(name= "money")
	private BigDecimal money;  //金额
	
	@Column(name= "operatorId")
	private Long operatorId;  //操作人

	@Column(name= "operatorName")
	private String operatorName;  //操作人名

	@Column(name= "type")
	private Integer type;  //类型

	@Column(name= "remark")
	private String remark;  //备注

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-18 14:48:45    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-18 14:48:45   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>账户类型</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-18 14:48:45    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>账户类型</p>
	 * @author:  lzy
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-04-18 14:48:45   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>金额</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:48:45    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>金额</p>
	 * @author:  lzy
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-04-18 14:48:45   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p>操作人</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-18 14:48:45    
	 */
	public Long getOperatorId() {
		return operatorId;
	}
	
	/**
	 * <p>操作人</p>
	 * @author:  lzy
	 * @param:   @param operatorId
	 * @return:  void 
	 * @Date :   2019-04-18 14:48:45   
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	
	
	/**
	 * <p>类型</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-18 14:48:45    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型</p>
	 * @author:  lzy
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-04-18 14:48:45   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
