/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:08:42 
 */
package hry.cm2.log.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm2.log.model.Cm2TaskLog;

import java.util.Date;


/**
 * <p> Cm2TaskLogService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:08:42 
 */
public interface Cm2TaskLogService  extends BaseService<Cm2TaskLog, Long>{

    public void saveLog(String functionName, Integer isException, Date startDate, Date endDate);



}
