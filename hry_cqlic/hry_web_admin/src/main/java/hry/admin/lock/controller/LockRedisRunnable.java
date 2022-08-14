package hry.admin.lock.controller;

import hry.admin.exchange.service.ExDmTransactionService;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * @author lenovo
 *
 */
public class LockRedisRunnable implements Runnable {
	
	private final Logger log = Logger.getLogger(LockRedisRunnable.class);
	
	private Map<String, Object> paramMap;
	
	public LockRedisRunnable (Map<String, Object> paramMap) {
		super();
		this.paramMap = paramMap;
	}

	@Override
	public void run() {
		try {
			// 因redis服务2秒才会将账户金额增减信息写入数据库，所以延迟5秒执行锁仓管理
			Thread.sleep(5000);
			ExDmTransactionService exDmTransactionService = (ExDmTransactionService) ContextUtil.getBean("exDmTransactionService");
			exDmTransactionService.lockManagementHandler(paramMap);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.info("锁仓失败");
		}
		
	}

}
