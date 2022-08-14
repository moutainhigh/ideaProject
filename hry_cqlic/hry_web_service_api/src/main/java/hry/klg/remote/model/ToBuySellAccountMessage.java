package hry.klg.remote.model;

import java.math.BigDecimal;

public class ToBuySellAccountMessage {
	
	private BigDecimal changeMoney;//变动金额
	
	private Integer changeType;//变动类型 1加 2减
	
	private Integer messageType;//消息类型 1.匹配 2.调控 3.吃单 4.抢单 5.支付 6.买卖剩余账户转出
	
	//账户类型 1.buySurplusAccount买单支付剩余账户  2.sellSurplusAccount卖单卖出剩余 
	private String acconutType; 
	
	//产生变动的订单号
	private String transactionNum; 
	
	//备注
	private String remark;


	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public BigDecimal getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(BigDecimal changeMoney) {
		this.changeMoney = changeMoney;
	}

	public Integer getChangeType() {
		return changeType;
	}

	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}

	public String getAcconutType() {
		return acconutType;
	}

	public void setAcconutType(String acconutType) {
		this.acconutType = acconutType;
	}

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	/**
	 * 
	 * @param changeMoney  变动金额
	 * @param changeType 变动类型 1加 2减
	 * @param messageType 消息类型 1.匹配 2.调控 3.吃单 4.抢单 5.支付 6.买卖剩余账户转出
	 * @param acconutType 账户类型 1.buySurplusAccount买单支付剩余账户  2.sellSurplusAccount卖单卖出剩余 
	 * @param transactionNum 产生变动的订单号
	 * @param remark 备注
	 */
	public ToBuySellAccountMessage(BigDecimal changeMoney, Integer changeType, Integer messageType, String acconutType,
			String transactionNum, String remark) {
		super();
		this.changeMoney = changeMoney;
		this.changeType = changeType;
		this.messageType = messageType;
		this.acconutType = acconutType;
		this.transactionNum = transactionNum;
		this.remark = remark;
	}

	/**
	 * @param acconutType 账户类型 1.buySurplusAccount买单支付剩余账户  2.sellSurplusAccount卖单卖出剩余 
	 * @param transactionNum 产生变动的订单号
	 * @param remark 备注
	 *//*
	public ToBuySellAccountMessage(String acconutType, String transactionNum, String remark) {
		super();
		this.acconutType = acconutType;
		this.transactionNum = transactionNum;
		this.remark = remark;
		this.isRegulation = 1;
	}
	
	
	*//**
	 * @param changeMoney 变动金额
	 * @param changeType 变动类型 1加 2减
	 * @param isRegulation 是否调控 1否  2是
	 * @param acconutType 账户类型 1.buySurplusAccount买单支付剩余账户  2.sellSurplusAccount卖单卖出剩余 
	 * @param transactionNum 产生变动的订单号
	 * @param remark 备注
	 *//*
	public ToBuySellAccountMessage(BigDecimal changeMoney, Integer changeType, Integer isRegulation, String acconutType,
			String transactionNum, String remark) {
		super();
		this.changeMoney = changeMoney;
		this.changeType = changeType;
		this.isRegulation = isRegulation;
		this.acconutType = acconutType;
		this.transactionNum = transactionNum;
		this.remark = remark;
	}*/

	public ToBuySellAccountMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
