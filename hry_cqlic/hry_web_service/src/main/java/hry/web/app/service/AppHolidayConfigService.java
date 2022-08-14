/**
 * 
 */
package hry.web.app.service;

import hry.core.mvc.service.base.BaseService;
import hry.web.app.model.AppHolidayConfig;

/**
 * @author lvna
 *
 */
public interface AppHolidayConfigService extends
		BaseService<AppHolidayConfig, Long> {

	public Boolean judgeHoliday(String date);
	
	/**
	 * 初始化节假日缓存
	 */
	public void initCache();
	
	
	
}
