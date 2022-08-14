package hry.manage.remote.model;

import hry.bean.BaseModel;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class ExDmTransfColdDetailManage extends BaseModel {

	private Long id;  //id
	
	private String fromAddress;  //fromAddress
	
	private String toAddress;  //toAddress
	
	private BigDecimal amount;  //amount
	
	private String operator;  //操作人员
	
	private String tx;  //流水号
	
	private String coinCode;
	
	
	public ExDmTransfColdDetailManage() {
		super();
	}

	public ExDmTransfColdDetailManage(Long id, String fromAddress, String toAddress, BigDecimal amount, String operator,
			String tx, String coinCode) {
		super();
		this.id = id;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.amount = amount;
		this.operator = operator;
		this.tx = tx;
		this.coinCode = coinCode;
	}


	/**
	 * <p>id</p>
	 * @author:  shangxl
	 * @return:  Long 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  shangxl
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>fromAddress</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	
	/**
	 * <p>fromAddress</p>
	 * @author:  shangxl
	 * @param:   @param fromAddress
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	
	/**
	 * <p>toAddress</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public String getToAddress() {
		return toAddress;
	}
	
	/**
	 * <p>toAddress</p>
	 * @author:  shangxl
	 * @param:   @param toAddress
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	
	
	/**
	 * <p>amount</p>
	 * @author:  shangxl
	 * @return:  BigDecimal 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * <p>amount</p>
	 * @author:  shangxl
	 * @param:   @param amount
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	/**
	 * <p>操作人员</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * <p>操作人员</p>
	 * @author:  shangxl
	 * @param:   @param operator
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-11-13 19:17:02    
	 */
	public String getTx() {
		return tx;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  shangxl
	 * @param:   @param tx
	 * @return:  void 
	 * @Date :   2017-11-13 19:17:02   
	 */
	public void setTx(String tx) {
		this.tx = tx;
	}
	
	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
}
