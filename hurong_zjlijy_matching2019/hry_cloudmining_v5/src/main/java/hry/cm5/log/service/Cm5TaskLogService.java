/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:15:34 
 */
package hry.cm5.log.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm5.log.model.Cm5TaskLog;

import java.util.Date;


/**
 * <p> Cm5TaskLogService </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:15:34 
 */
public interface Cm5TaskLogService  extends BaseService<Cm5TaskLog, Long>{

    public void saveLog(String functionName, Integer isException, Date startDate, Date endDate);

}
