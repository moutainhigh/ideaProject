/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月24日 下午7:52:08
 */
package hry.calculate.mvc.po;

import java.io.Serializable;

/**
 * @author Wu shuiming
 * @date 2016年8月24日 下午7:52:08
 */
@SuppressWarnings("serial")
public class CalculateParameter implements Serializable{
	
	private String startDate;
	
	private String endDate;
	
	private Integer customerType;
	
	private String code;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CalculateParameter(String startDate, String endDate,
			Integer customerType, String code) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.customerType = customerType;
		this.code = code;
	}

	public CalculateParameter() {
		super();
	}

	
	
	

}
