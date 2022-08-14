/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年9月20日 下午3:38:37
 */
package hry.calculate.mvc.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年9月20日 下午3:38:37 
 */
public class OperationAccountFundInfoLog {
	
	private String context;
	private String userName;
	private String currencyType;  //交易类型
	private String website;//站点类别默认cn
	private String operatorName;
	private Date CreatDate;
	
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getContext() {
		return context;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setContext(String context) {
		this.context = context;
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
	public String getCurrencyType() {
		return currencyType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getWebsite() {
		return website;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public String getOperatorName() {
		return operatorName;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getCreatDate() {
		return CreatDate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setCreatDate(Date creatDate) {
		CreatDate = creatDate;
	}

	
	
}
