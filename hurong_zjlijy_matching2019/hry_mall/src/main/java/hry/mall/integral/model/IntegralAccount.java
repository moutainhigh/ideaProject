/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 10:53:05 
 */
package hry.mall.integral.model;

import hry.bean.BaseModel;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IntegralAccount </p>
 * @author:         kongdb
 * @Date :          2019-01-07 10:53:05  
 */
@Table(name="shop_integral_account")
public class IntegralAccount extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "account_name")
	private String account_name;  //账户名称
	
	@Column(name= "account_key")
	private String account_key;  //资产key
	
	@Column(name= "account_type")
	private Integer account_type;  //类型、0系统生成 1后台添加
	
	@Column(name= "issue_total")
	private BigDecimal issue_total;  //发放总量
	
	@Column(name= "reamining_total")
	private BigDecimal reamining_total;  //资产余额
	
	@Column(name= "consume_total")
	private BigDecimal consume_total;  //投放总量
	
	@Column(name= "remark")
	private String remark;  //备注
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  kongdb
	 * @return:  Long 
	 * @Date :   2019-01-07 10:53:05    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  kongdb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-07 10:53:05   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>账户名称</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 10:53:05    
	 */
	public String getAccount_name() {
		return account_name;
	}
	
	/**
	 * <p>账户名称</p>
	 * @author:  kongdb
	 * @param:   @param account_name
	 * @return:  void 
	 * @Date :   2019-01-07 10:53:05   
	 */
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	
	
	/**
	 * <p>资产key</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 10:53:05    
	 */
	public String getAccount_key() {
		return account_key;
	}
	
	/**
	 * <p>资产key</p>
	 * @author:  kongdb
	 * @param:   @param account_key
	 * @return:  void 
	 * @Date :   2019-01-07 10:53:05   
	 */
	public void setAccount_key(String account_key) {
		this.account_key = account_key;
	}
	
	
	/**
	 * <p>类型、0系统生成 1后台添加</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-07 10:53:05    
	 */
	public Integer getAccount_type() {
		return account_type;
	}
	
	/**
	 * <p>类型、0系统生成 1后台添加</p>
	 * @author:  kongdb
	 * @param:   @param account_type
	 * @return:  void 
	 * @Date :   2019-01-07 10:53:05   
	 */
	public void setAccount_type(Integer account_type) {
		this.account_type = account_type;
	}
	
	
	/**
	 * <p>发放总量</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 10:53:05    
	 */
	public BigDecimal getIssue_total() {
		return issue_total;
	}
	
	/**
	 * <p>发放总量</p>
	 * @author:  kongdb
	 * @param:   @param issue_total
	 * @return:  void 
	 * @Date :   2019-01-07 10:53:05   
	 */
	public void setIssue_total(BigDecimal issue_total) {
		this.issue_total = issue_total;
	}
	
	
	/**
	 * <p>资产余额</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 10:53:05    
	 */
	public BigDecimal getReamining_total() {
		return reamining_total;
	}
	
	/**
	 * <p>资产余额</p>
	 * @author:  kongdb
	 * @param:   @param reamining_total
	 * @return:  void 
	 * @Date :   2019-01-07 10:53:05   
	 */
	public void setReamining_total(BigDecimal reamining_total) {
		this.reamining_total = reamining_total;
	}
	
	
	/**
	 * <p>投放总量</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-07 10:53:05    
	 */
	public BigDecimal getConsume_total() {
		return consume_total;
	}
	
	/**
	 * <p>投放总量</p>
	 * @author:  kongdb
	 * @param:   @param consume_total
	 * @return:  void 
	 * @Date :   2019-01-07 10:53:05   
	 */
	public void setConsume_total(BigDecimal consume_total) {
		this.consume_total = consume_total;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2019-01-07 10:53:05    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  kongdb
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-01-07 10:53:05   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
