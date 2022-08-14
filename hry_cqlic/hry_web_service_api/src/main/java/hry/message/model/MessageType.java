package hry.message.model;

public enum MessageType {
    Message_Buy_Remind("购买平台消息提醒","message_buy_remind","1001"),
    Message_Lock_Remind("锁仓消息提醒","message_lock_remind","1002"),
    Message_Release_Remind("释放提醒","message_release_remind","1003"),

    Message_RoolIn_Reminder("到账提醒","message_rollIn_reminder","2001"),
    Message_RollOut_Reminder("转出提醒","message_rollOut_reminder","2002"),

    Message_Reward_Distribution("奖励发放提醒","message_reward_distribution","3003"),

    /***下面key 项目基础用的是AppMessageServiceImpl中的*/
    Message_User_Reg("用户注册","message_user_reg","101"),
    Message_Up_Pwd("修改密码成功","message_up_pwd","102"),
    Message_Work_Acceptance("工单受理","message_work_acceptance","103"),
    Message_Coin_Charging("充币成功","message_coin_charging","104"),
    Message_Withdraw_Money("提币成功","message_withdraw_money","105"),
    Message_Real_Name("实名通过","message_real_name","106"),

    /***李超钱包特有站内信*/
    MESSAGE_LC_ECOFUND_SUCCESS("生态基金申请通过","message_lc_ecofund_success", "201"),
    MESSAGE_LC_ECOFUND_REFUSE("生态基金申请拒绝","message_lc_ecofund_refuse", "202"),
    MESSAGE_LC_ECOENTER_SUCCESS("生态入驻申请通过","message_lc_ecoenter_success", "203"),
    MESSAGE_LC_ECOENTER_REFUSE("生态入驻申请拒绝","message_lc_ecoenter_refuse", "204"),
    MESSAGE_LC_ECOENTER_PAY_SUCCESS("生态入驻支付核实通过","message_lc_ecoenter_pay_success", "205"),
    MESSAGE_LC_ECOENTER_PAY_REFUSE("生态入驻支付核实拒绝","message_lc_ecoenter_pay_refuse", "206"),




	/**klg  message key*/
    MESSAGE_KLG_ACCOUNT_CONFIRMOUT("我的资产-确认转账","message_klg_account_confirmout","501"),
    MESSAGE_KLG_ACCOUNT_OUTFAIL("我的资产-转出失败","message_klg_account_outfail","502"),
    MESSAGE_KLG_ACCOUNT_OUTSUCCESS("我的资产-转出成功","message_klg_account_outsuccess","503"),
    MESSAGE_KLG_SELL_DEAL("卖单已成交","message_klg_sell_deal","504"),
    MESSAGE_KLG_SELL_QUEUE_SUCCESS("卖单排队成功","message_klg_sell_queue_success","505"),
    MESSAGE_KLG_SELL_QUEUE("卖单预约成功","message_klg_sell_queue","506"),
    MESSAGE_KLG_BUY_ROB_FAIL("抢单失败","message_klg_buy_rob_fail","507"),
    MESSAGE_KLG_BUY_ROB_SUCCESS("抢单成功","message_klg_buy_rob_success","508"),
    MESSAGE_KLG_BUY_CANCEL("买单作废","message_klg_buy_cancel","509"),
    MESSAGE_KLG_BUY_DEAL("买单成交","message_klg_buy_deal","510"),
    MESSAGE_KLG_BUY_QUEUE_SUCCESS("买单排队成功","message_klg_buy_queue_success","511"),
	MESSAGE_KLG_BANGZHENGJINPAY("买单支付保证金","message_klg_bangzhengjinpay","512"),
    MESSAGE_KLG_ACCOUNT_OUT_APPLY_SUCCESS("转出到交易所申请成功","message_klg_out_apply_success","513"),
	MESSAGE_VERIFICATION_CODE("发短信验证码","message_verification_code","514");



    private String name;  //用于获取说明，
    private String index;  //获取数值
    private String key;// 模板key
    private MessageType(String name, String key, String index) {
        this.name = name;
        this.index = index;
        this.key = key;
    }

    /**
     * 根据key获取对应站内信
     * @param key
     * @return
     */
    public static String getIndex(String key){
        for (MessageType ms:MessageType.values()){
            if(ms.getKey().equals(key)){
                return ms.index;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }
}
