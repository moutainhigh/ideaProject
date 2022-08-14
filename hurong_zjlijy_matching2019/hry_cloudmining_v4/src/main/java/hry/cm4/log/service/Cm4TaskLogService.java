/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:04:52 
 */
package hry.cm4.log.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm4.log.model.Cm4TaskLog;

import java.util.Date;


/**
 * <p> Cm4TaskLogService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:04:52 
 */
public interface Cm4TaskLogService  extends BaseService<Cm4TaskLog, Long>{
    public void saveLog(String functionName, Integer isException, Date startDate, Date endDate);

}
