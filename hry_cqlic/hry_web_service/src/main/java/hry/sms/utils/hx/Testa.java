/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月24日 上午9:27:24
 */
package hry.sms.utils.hx;

import org.apache.log4j.Logger;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月24日 上午9:27:24 
 */
public class Testa {

	private static Logger logger = Logger.getLogger(Testa.class);
	public static void main(String[] args){
		
		for(int i = 0 ; i < 10 ; i++){
			logger.error(i);
		}
		MyRunnable myRunnable = new MyRunnable();
		Thread thread = new Thread(myRunnable);
		thread.start();
		
		logger.error("11");
		
	}
	
}
