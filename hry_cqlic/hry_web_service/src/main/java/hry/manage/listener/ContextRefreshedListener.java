/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年7月18日 下午10:24:04
 */
package hry.manage.listener;

import hry.customer.agents.service.CommissionDetailService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Wu shuiming
 * @date 2016年7月18日 下午10:24:04
 */

	  
	
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{
	private static Logger logger = Logger.getLogger(ContextRefreshedListener.class);
	@Resource(name="commissionDetailService")
	public CommissionDetailService commissionDetailService;
	    @Override
	    public void onApplicationEvent(ContextRefreshedEvent event) {  
	        ApplicationContext context = event.getApplicationContext();  
	        if(context.getParent() == null){  
	            logger.error("Spring容器初始化完毕================================================");
	        }

	    }

}
