package hry.cm2.remote;

public interface RemoteCmListenerService {
	
	/**
	 * 定时任务矿机产币,按天产币
	 */
	void minerCoinageByDay();
	
	/**
	 * 矿机到期,定时任务
	 */
	void minerWaitAndCloseTiming();

	/**
	 * 矿场分红
	 * */
	void minercampsByDay();
	
	/**
	 * 矿工动态收益
	 */
	void profitone();
	
	/**
	 * 发放矿场收益定时任务
	 */
	void grantProfit();
}
