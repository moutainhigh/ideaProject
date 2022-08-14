/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-03-01 14:17:02 
 */
package hry.admin.dic.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.dic.model.AppDic;



/**
 * <p> SysDicService </p>
 * @author:         liushilei
 * @Date :          2017-03-01 14:17:02  
 */
public interface AppDicService  extends BaseService<AppDic, Long>{
	
	/**
	 * 通过pkey 和value查询名称
	 * @param pkey
	 * @param value
	 * @return
	 */
	String getNameByPkeyAndValue(String pkey, String value);

	void flushRedis();

	public void initAreaCache();

}
