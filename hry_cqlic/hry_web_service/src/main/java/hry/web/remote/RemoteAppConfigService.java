/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年4月29日 下午3:03:50
 */
package hry.web.remote;

import hry.core.mvc.model.AppConfig;
import hry.bean.JsonResult;
import hry.web.app.model.vo.PrepaidAndPaceCommissionVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年4月29日 下午3:03:50 
 */
public interface RemoteAppConfigService {
       public String  getConfigInfo(String name, String type);
       public List<AppConfig>  getConfigInfo(String type);
       public List<AppConfig>  getConfigInfoByKey(String key);
       public String  getValueByKey(String key);
       public String  getFinanceByKey(String key);
	   /**
	    * 
	    * 一次返回人名币充值提现手续费率
	    * 
	    * @author:    Wu Shuiming
	    * @version:   V1.0 
	    * @date:      2016年7月28日 下午5:20:10
	    */
       public PrepaidAndPaceCommissionVo getPrepaidAndPaceCommissionVo();
       
       
       
       /**
        * 
        * 根据配置类型获取相应的数据
        * @author:         Zhang Xiaofang
        * @param:    @param type
        * @param:    @return
        * @return: JsonResult 
        * @Date :          2016年9月6日 下午3:39:34   
        * @throws:
        */
       public JsonResult  dataByType(String type);

   	
   	/**
   	 * 
   	 * <p> 设置key的值到数据库</p>
   	 * @author:         Gao Mimid
   	 * @param:    @param key
   	 * @param:    @return
   	 * @return: AppConfig 
   	 * @Date :          2015年10月10日 下午6:57:23   
   	 * @throws:
   	 */
   	public void setBykeyToDB(String key, String value);
   	/**
   	 * 
   	 * <p> 得到key的值从数据库</p>
   	 * @author:         Gao Mimid
   	 * @param:    @param key
   	 * @param:    @return
   	 * @return: AppConfig 
   	 * @Date :          2015年10月10日 下午6:57:23   
   	 * @throws:
   	 */
   	public String getBykeyfromDB(String key);
   	/**
   	 * 
   	 * <p> TODO</p>
   	 * @author:         Shangxl
   	 * @param:    @param configkey
   	 * @param:    @return
   	 * @return: AppConfig 
   	 * @Date :          2017年3月21日 下午4:26:54   
   	 * @throws:
   	 */
	public AppConfig getAppConfigByConfigKey(String configkey);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param appConfig
	 * @return: void 
	 * @Date :          2017年3月21日 下午4:32:44   
	 * @throws:
	 */
	public void updateAppConfig(AppConfig appConfig);
	
	/**
	 * 根据typeKey查找list
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param typeKey
	 * @param:    @return
	 * @return: List<AppConfig> 
	 * @Date :          2017年5月11日 下午2:36:13   
	 * @throws:
	 */
	public List<AppConfig> findByTypeKey(String typeKey);

	/**
	 * 查询杠杆参数
	 * @param lendTimes
	 * @return
	 */
    String getFinanceLendByKey(String lendTimes);

	BigDecimal getCurrentCoinMoney(String coinCode, String usdt);
}
