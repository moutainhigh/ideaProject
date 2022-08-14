/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 16:33:44 
 */
package hry.admin.exchange.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExRobot;



/**
 * <p> ExRobotService </p>
 * @author:         liushilei
 * @Date :          2018-06-12 16:33:44 
 */
public interface ExRobotService  extends BaseService<ExRobot, Long>{
    public void startAuto( Long id ) throws Exception;
    public void updateExRobotToRedis();

    public void closeAutoByIds(Long[] ids);
}
