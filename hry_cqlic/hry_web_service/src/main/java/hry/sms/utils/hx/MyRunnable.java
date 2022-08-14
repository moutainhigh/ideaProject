/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月24日 上午9:27:38
 */
package hry.sms.utils.hx;

import org.apache.log4j.Logger;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月24日 上午9:27:38 
 */
public class MyRunnable implements Runnable {
	private static Logger logger = Logger.getLogger(MyRunnable.class);
	@Override
	public void run() {
		logger.error("hello");
	}

}
