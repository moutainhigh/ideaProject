/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午1:45:20
 */
package hry.exchange.product.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.exchange.product.model.ExProductParameter;

/**
 * 
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午1:47:26
 */
public interface ExProductParameterService extends BaseService<ExProductParameter, Long> {


	public JsonResult saveExProductParameter(ExProductParameter exProductParameter,
                                             Integer type);
	
	
}
