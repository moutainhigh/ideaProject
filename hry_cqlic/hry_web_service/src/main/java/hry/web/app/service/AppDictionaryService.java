/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月27日  17:57:57
 */
package hry.web.app.service;



import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.web.app.model.AppDictionary;
/**
 * <p> TODO</p>
 * @author:     Gao Mimi     
 * @Date :      2015年10月27日  17:57:57
 */
public interface AppDictionaryService extends BaseService<AppDictionary, Long> {
	/**
	 * 
	 * <p> 删除字典项</p>
	 * @author:         Gao Mimi
	 * @param:    @param id
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年6月17日 下午4:15:34   
	 * @throws:
	 */
	public JsonResult removeDic(String id);
	/**
	 * 
	 * <p> 添加字典项</p>
	 * @author:         Gao Mimi
	 * @param:    @param appDictionary
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年6月17日 下午4:15:55   
	 * @throws:
	 */
	public String addDic(AppDictionary appDictionary);
	/**
	 * 
	 * <p> 上移或者下移字段项</p>
	 * @author:         Gao Mimi
	 * @param:    @param id
	 * @param:    @param type
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年6月17日 下午4:16:10   
	 * @throws:
	 */
	public JsonResult move(String id, String type);
	/**
	 * 
	 * <p> 缓存到redis里</p>
	 * @author:         Gao Mimi
	 * @param:    @param pDicKey
	 * @return: void 
	 * @Date :          2016年6月17日 下午4:16:36   
	 * @throws:
	 */
	public void dicToredis(String pDicKey);
}


