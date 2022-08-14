/**

 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
package hry.trade.entrust.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExEntrustPlan;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExEntrustPlanService extends BaseService<ExEntrustPlan, Long> {
	/**
	 * 
	 * <p> 定时计数大盘参数</p>
	 * @author:         Gao Mimi
	 * @param:    
	 * @return: void 
	 * @Date :          2016年7月7日 下午3:03:22   
	 * @throws:
	 */
	 public void  planEntrust();
	 /**
	  * 
	  * <p> 选择条件是否符合大盘</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: Boolean 
	  * @Date :          2016年8月2日 下午5:32:31   
	  * @throws:
	  */
	 public Boolean isAccordwithMarket(ExEntrustPlan p);
	/* *//**
	  * 
	  * <p>添加预埋</p>
	  * @author:         Gao Mimi
	  * @param:    @param p
	  * @return: void 
	  * @Date :          2016年8月2日 下午6:32:24   
	  * @throws:
	  *//*
	 public void saveExEntrustPlan(ExEntrustPlan p);*/
	 /**
	  * 
	  * <p> 拼条件</p>
	  * @author:         Gao Mimi
	  * @param:    @param p
	  * @param:    @return
	  * @return: String 
	  * @Date :          2016年8月8日 下午6:23:55   
	  * @throws:
	  */
	 public String pingCondition(ExEntrustPlan p);
}
