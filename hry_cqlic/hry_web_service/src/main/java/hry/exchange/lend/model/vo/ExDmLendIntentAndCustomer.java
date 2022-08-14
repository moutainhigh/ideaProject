/**
 * 
 */
package hry.exchange.lend.model.vo;

/**
 * @author lvna
 *
 */
public class ExDmLendIntentAndCustomer {
	
	private Long id;  // id
	private String userName;  // 用户名
	private String trueName; // 用户真实姓名
	private String lendCoinType;  // 申请类型
	private String lendCount;  // 申请数量 或 申请金额
	private String intentType;  // 利息类型 (interest利息principal本金)
	private String factTime;  // 还款时间
	private String repayCount;  // 还的数量  
	

	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLendCoinType() {
		return lendCoinType;
	}
	public void setLendCoinType(String lendCoinType) {
		this.lendCoinType = lendCoinType;
	}
	public String getLendCount() {
		return lendCount;
	}
	public void setLendCount(String lendCount) {
		this.lendCount = lendCount;
	}
	public String getIntentType() {
		return intentType;
	}
	public void setIntentType(String intentType) {
		this.intentType = intentType;
	}
	public String getFactTime() {
		return factTime;
	}
	public void setFactTime(String factTime) {
		this.factTime = factTime;
	}
	public String getRepayCount() {
		return repayCount;
	}
	public void setRepayCount(String repayCount) {
		this.repayCount = repayCount;
	}
	
	
	
}
