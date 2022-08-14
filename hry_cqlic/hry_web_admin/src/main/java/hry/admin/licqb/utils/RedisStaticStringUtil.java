package hry.admin.licqb.utils;

/**
 * 
 * @author lenovo
 *
 */
public class RedisStaticStringUtil {
	
	/**卖单买单匹配提交锁*/
	public static final String KLG_MATCHING_SUBMISSION = "KLG_MATCHING_SUBMISSION";
	
	/**用户支付剩余款，订单上锁,后面加订单流水号*/
	public static final String KLG_BUY_PAY_TRANSACTIONNUM = "KLG_BUY_PAY_";
	
	/**买单支付剩余账户锁*/
	public static final String KLG_BUY_PAY_SURPLUS_ACCOUNT = "KLG_BUY_PAY_SURPLUS_ACCOUNT";
	
	/**卖单卖出剩余账户锁*/
	public static final String KLG_SELL_PAY_SURPLUS_ACCOUNT = "KLG_SELL_PAY_SURPLUS_ACCOUNT";

}
