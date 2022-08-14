/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-08 17:02:30 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExErc20 </p>
 * @author:         tianpengyu
 * @Date :          2018-08-08 17:02:30  
 */
@Table(name="ex_erc20")
public class ExErc20 extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "contractAddress")
	private String contractAddress;  //
	
	@Column(name= "myprecision")
	private String myprecision;  //币的精度
	
	@Column(name= "addCoinType")
	private String addCoinType;  //上币类型， 内部外部
	
	@Column(name= "operator")
	private String operator;  //操作人
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-08-08 17:02:30    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-08-08 17:02:30   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-08 17:02:30    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-08-08 17:02:30   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-08 17:02:30    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-08-08 17:02:30   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-08 17:02:30    
	 */
	public String getContractAddress() {
		return contractAddress;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param contractAddress
	 * @return:  void 
	 * @Date :   2018-08-08 17:02:30   
	 */
	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-08 17:02:30    
	 */
	public String getMyprecision() {
		return myprecision;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param myprecision
	 * @return:  void 
	 * @Date :   2018-08-08 17:02:30   
	 */
	public void setMyprecision(String myprecision) {
		this.myprecision = myprecision;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-08 17:02:30    
	 */
	public String getAddCoinType() {
		return addCoinType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param addCoinType
	 * @return:  void 
	 * @Date :   2018-08-08 17:02:30   
	 */
	public void setAddCoinType(String addCoinType) {
		this.addCoinType = addCoinType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-08 17:02:30    
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param operator
	 * @return:  void 
	 * @Date :   2018-08-08 17:02:30   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	

}
