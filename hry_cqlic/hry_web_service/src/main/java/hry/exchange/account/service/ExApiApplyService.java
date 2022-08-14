/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月12日 上午10:15:13
 */
package hry.exchange.account.service;

import java.util.List;
import java.util.Map;

import hry.core.mvc.service.base.BaseService;
import hry.exchange.account.model.ExApiApply;


/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月12日 上午10:15:13 
 */
public interface ExApiApplyService extends BaseService<ExApiApply, Long>{

	   /**
	    * 
	    * <p> 查询某个用户申请的所有的访问api的key</p>
	    * @author:         Zhang Xiaofang
	    * @param:    @param customerId
	    * @param:    @return
	    * @return: List<ExApiApply> 
	    * @Date :          2016年5月12日 上午11:44:05   
	    * @throws:
	    */
	   public List<ExApiApply> findList(Long customerId);
	   
	   
	   /**
	    * 
	    * <p> 为某个用户创建一个访问api的key</p>
	    * @author:         Zhang Xiaofang
	    * @param:    @param customerId
	    * @param:    @return
	    * @return: boolean 
	    * @Date :          2016年5月12日 上午11:45:42   
	    * @throws:
	    */
	   public Map<String,String> createKey(ExApiApply exApiApply);
}
