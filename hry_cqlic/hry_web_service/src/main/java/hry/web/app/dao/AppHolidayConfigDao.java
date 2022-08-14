/**
 * 
 */
package hry.web.app.dao;


import hry.core.mvc.dao.base.BaseDao;
import hry.web.app.model.AppHolidayConfig;

import org.apache.ibatis.annotations.Param;

/**
 * @author lvna
 *
 */
public interface AppHolidayConfigDao extends BaseDao<AppHolidayConfig, Long> {
	
	public Integer judgeHoliday(@Param(value = "date") String date);
	
	
}
