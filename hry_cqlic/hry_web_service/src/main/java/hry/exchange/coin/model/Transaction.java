/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月3日 下午3:06:14
 */
package hry.exchange.coin.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;



/**
 * <p> 数字货币交易数据转换器</p>
 * @author:         Yuan Zhicheng 
 * @Date :          2016年5月13日 下午3:06:14 
 */


@SuppressWarnings("serial")
@Table(name = "coin_transaction")
public class Transaction extends BaseModel{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	/**
	 * 账号
	 */
	@Column(name = "account")
	private String account="";

	/**
	 * 转账地址
	 */
	@Column(name = "address")
	private String address="";

	/**
	 * 转账金额
	 */
	@Column(name = "amount")
	private double amount=0.00;
	/**
	 * 区块hash
	 */
	@Column(name = "blockHash")
	private String blockHash="";

	/**
	 * 区块索引
	 */
	@Column(name = "blockIndex")
	private int blockIndex=0;

	/**
	 * 区块时间
	 */
	@Column(name = "blockTime")
	private String blockTime=null;

	/**
	 * 类型
	 */
	@Column(name = "category")
	private String category="";
	@Column(name = "comment")
	private String comment="";
	@Column(name = "commentTo")
	private String commentTo="";

	/**
	 * 确认数量
	 */
	@Column(name = "confirmations")
	private int confirmations=0;
	
	/**
	 * 是否生成订单 0  未生成 1已经生成
	 */
	@Column(name = "isCreateOrder")
	private int isCreateOrder=0;
	
	/**
	 * 是否转出 0 没转出  1已转出 默认 0
	 */
	@Column(name = "isRollOut")
	private int isRollOut=0;
	
	/**
	 * 备注
	 */
	@Column(name = "remark1")
	private String remark1="";
	/**
	 * 备注
	 */
	@Column(name = "remark2")
	private String remark2="";
	/**
	 * 备注
	 */
	@Column(name = "remark3")
	private String remark3="";
	
	
	

	/**
	 * <p> TODO</p>
	 * @return:     int
	 */
	public int getIsRollOut() {
		return isRollOut;
	}

	/** 
	 * <p> TODO</p>
	 * @return: int
	 */
	public void setIsRollOut(int isRollOut) {
		this.isRollOut = isRollOut;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark1() {
		return remark1;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark2() {
		return remark2;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark3() {
		return remark3;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	/**
	 * <p> TODO</p>
	 * @return:     int
	 */
	public int getIsCreateOrder() {
		return isCreateOrder;
	}

	/** 
	 * <p> TODO</p>
	 * @return: int
	 */
	public void setIsCreateOrder(int isCreateOrder) {
		this.isCreateOrder = isCreateOrder;
	}

	/**
	 * 手续费
	 */
	@Column(name = "fee")
	private double fee=0.00;

	/**
	 * 时间
	 */
	@Column(name = "time")
	private String time=null;

	/**
	 * 接收时间
	 */
	@Column(name = "timeReceived")
	private String timeReceived=null;

	/**
	 * 交易订单号
	 */
	@Column(name = "txId")
	private String txId="";
	
	/**
	 * 交易订单号_交易类型   设置唯一索引
	 */
	@Column(name = "txIdType")
	private String txIdType="";
	
	/**
	 * 币种类型
	 */
	@Column(name = "coinType")
	private String coinType="";
	
	

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCoinType() {
		return coinType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String 
	 */
	public String getTxIdType() {
		return txIdType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTxIdType(String txIdType) {
		this.txIdType = txIdType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getAccount() {
		return account;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getAddress() {
		return address;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * <p> TODO</p>
	 * @return:     double
	 */
	public double getAmount() {
		return amount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: double
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBlockHash() {
		return blockHash;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}

	/**
	 * <p> TODO</p>
	 * @return:     int
	 */
	public int getBlockIndex() {
		return blockIndex;
	}

	/** 
	 * <p> TODO</p>
	 * @return: int
	 */
	public void setBlockIndex(int blockIndex) {
		this.blockIndex = blockIndex;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCategory() {
		return category;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getComment() {
		return comment;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCommentTo() {
		return commentTo;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCommentTo(String commentTo) {
		this.commentTo = commentTo;
	}

	/**
	 * <p> TODO</p>
	 * @return:     int
	 */
	public int getConfirmations() {
		return confirmations;
	}

	/** 
	 * <p> TODO</p>
	 * @return: int
	 */
	public void setConfirmations(int confirmations) {
		this.confirmations = confirmations;
	}

	/**
	 * <p> TODO</p>
	 * @return:     double
	 */
	public double getFee() {
		return fee;
	}

	/** 
	 * <p> TODO</p>
	 * @return: double
	 */
	public void setFee(double fee) {
		this.fee = fee;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTxId() {
		return txId;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBlockTime() {
		return blockTime;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTime() {
		return time;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTimeReceived() {
		return timeReceived;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBlockTime(String blockTime) {
		this.blockTime = blockTime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTimeReceived(String timeReceived) {
		this.timeReceived = timeReceived;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTxId(String txId) {
		this.txId = txId;
	}
	
	
	
}
