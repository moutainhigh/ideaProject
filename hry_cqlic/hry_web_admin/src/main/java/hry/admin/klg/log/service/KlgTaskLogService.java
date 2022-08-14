/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-06 15:30:25 
 */
package hry.admin.klg.log.service;

import java.util.Date;

import hry.admin.klg.log.model.KlgTaskLog;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> KlgTaskLogService </p>
 * @author:         yaozhuo
 * @Date :          2019-05-06 15:30:25 
 */
public interface KlgTaskLogService  extends BaseService<KlgTaskLog, Long>{

	public void saveLog(String functionName,Integer isException,Date startDate,Date endDate);
}
