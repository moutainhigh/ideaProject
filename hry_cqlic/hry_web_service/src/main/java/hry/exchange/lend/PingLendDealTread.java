/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年8月22日 下午3:33:28
 */
package hry.exchange.lend;

import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.service.ExDmLendService;

import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年8月22日 下午3:33:28 
 */
public class PingLendDealTread implements Runnable {
	
	private Long customerId;  //客户的id


	
	public PingLendDealTread(Long customerId){
		this.customerId=customerId;
	}
	
	@Override
	public void run() {
		 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 QueryFilter filter=new QueryFilter(ExDmLend.class);
         filter.addFilter("status=", 1);
         filter.addFilter("customerId=", customerId);
         ExDmLendService exDmLendService = (ExDmLendService) ContextUtil.getBean("exDmLendService");
         List <ExDmLend> listExDmLend= exDmLendService.find(filter);
         for(ExDmLend ld:listExDmLend){
        	 exDmLendService.repayment(ld.getId(), "all", null);
         }
		
	}
	
	

}
