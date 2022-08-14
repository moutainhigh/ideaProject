/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-15 14:53:32 
 */
package hry.licqb.record.service;

import hry.core.mvc.service.base.BaseService;
import hry.licqb.record.model.TaskLog;

import java.util.Date;


/**
 * <p> TaskLogService </p>
 * @author:         zhouming
 * @Date :          2019-08-15 14:53:32 
 */
public interface TaskLogService  extends BaseService<TaskLog, Long>{

    public void saveLog(String functionName, Integer isException, Date startDate, Date endDate);

}
