package hry.klg.enums;

public enum TriggerPointEnum {
    SeeRewards("见点奖励",1),
    GradedReward("级差奖励",2),
    BuyPayTailMoney("买方支付尾款",3),
    EatBuy("平台吃买单",4),
    EatSell("平台吃卖单",5),
    TransferAccounts("转账",6),
    Purchase("买入",7),
    SellOut("卖出",8),
    RobOrder("抢单",9),
    OvertimeOrder("超时订单",10),
    AppointmentPurchase("预约买入冻结",11),
    AppointmentSell("预约卖出冻结",12),
    BuyovertimeInterest("买单超时利息",13),
    TransferToExchange("转出到交易所",14),
    ExchangeToTransfer("交易所转入",16),
    Bonus("大奖奖金",15),
	OtherType("其他",9999);
	private String name;
    private Integer key;
    private TriggerPointEnum( String name,Integer key) {
        this.name = name;
        this.key = key;
    }
    public Integer getKey() {
        return key;
    }
    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
