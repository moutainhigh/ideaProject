/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      songb
 * @version:     V1.0 
 * @Date:        2018-05-04 11:37:43 
 */
package hry.exchange.product.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.exchange.product.model.ExRobot;



/**
 * <p> ExRobotService </p>
 * @author:         songb
 * @Date :          2018-05-04 11:37:43  
 */
public interface ExRobotService  extends BaseService<ExRobot, Long>{

	void startAuto(Long id)throws Exception ;

	void closeAutoByIds(Long[] ids) throws Exception;

	void updateExRobotToRedis();

}
