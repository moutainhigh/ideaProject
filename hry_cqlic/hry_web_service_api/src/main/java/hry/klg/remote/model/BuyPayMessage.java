package hry.klg.remote.model;

import java.math.BigDecimal;

public class BuyPayMessage {
	
	private Long customerId;//用户id
	
	private Long klgBuyTransactionId; //买单订单id
	
	private BigDecimal robBuySmeNum; //抢单数量

	public BigDecimal getRobBuySmeNum() {
		return robBuySmeNum;
	}

	public void setRobBuySmeNum(BigDecimal robBuySmeNum) {
		this.robBuySmeNum = robBuySmeNum;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Long getKlgBuyTransactionId() {
		return klgBuyTransactionId;
	}

	public void setKlgBuyTransactionId(Long klgBuyTransactionId) {
		this.klgBuyTransactionId = klgBuyTransactionId;
	}

	public BuyPayMessage(Long customerId, Long klgBuyTransactionId, BigDecimal robBuySmeNum) {
		super();
		this.customerId = customerId;
		this.klgBuyTransactionId = klgBuyTransactionId;
		this.robBuySmeNum = robBuySmeNum;
	}



}
