/**
 * Copyright:   北京互融时代软件有限公司
 * @author:       Gao Mimi 
 * @version:      V1.0 
 * @Date:        2016年5月23日 下午6:28:57
 */
package hry.exchange.lend.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseExModel;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月23日 下午6:28:57
 */
@SuppressWarnings("serial")
@Table(name = "ex_dm_ping")
public class ExDmPing extends BaseExModel {

	// 主键
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 借款单号
	@Column(name = "pingNum")
	private String pingNum;
	// 借款人
	@Column(name = "customerId")
	private Long customerId;
	@Column(name = "userName")
	private String userName;
	@Column(name = "trueName")
	private String trueName;
	
	// 客户编号
	@Column(name = "userCode")
	private String userCode;
	//1平仓中2平仓完成
	@Column(name = "status")
	private Integer status;


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTrueName() {
		return trueName;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserName() {
		return userName;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserCode() {
		return userCode;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}



	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}



	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getPingNum() {
		return pingNum;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setPingNum(String pingNum) {
		this.pingNum = pingNum;
	}



	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getCustomerId() {
		return customerId;
	}



	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}





	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getStatus() {
		return status;
	}



	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "EcEntrust [id=" + id + ", pingNum=" + pingNum
				+ ", customerId=" + customerId +"status=" + status
			+ "]";
	}

}
