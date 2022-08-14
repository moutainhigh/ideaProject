/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:07:10 
 */
package hry.financail.financing.model;



import hry.bean.BaseModel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppFinancialProductType </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:07:10  
 */
@Table(name="app_financial_product_type")
public class AppFinancialProductType extends BaseModel implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "incomeType")
	private String incomeType;  //收益类型
	
	@Column(name= "remarks")
	private String remarks;  //备注
	
	@Column(name= "status")
	private Integer status;  //状态  1开启  0关闭
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-03 11:07:10    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:10   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>收益类型</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:07:10    
	 */
	public String getIncomeType() {
		return incomeType;
	}
	
	/**
	 * <p>收益类型</p>
	 * @author:  jidn
	 * @param:   @param incomeType
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:10   
	 */
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:07:10    
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  jidn
	 * @param:   @param remarks
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:10   
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	/**
	 * <p>状态  1开启  0关闭</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:07:10    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>状态  1开启  0关闭</p>
	 * @author:  jidn
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:10   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
