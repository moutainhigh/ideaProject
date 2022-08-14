/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年7月27日 上午10:48:52
 */
package hry.trade.model;

import hry.util.properties.PropertiesUtils;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年7月27日 上午10:48:52 
 */
//common是只有正常交易的客户，theSeat是既有正常交易也有坐市的交易的客户（）
public class DifCustomer {
	  public static String differetCustomer=PropertiesUtils.APP.getProperty("app.differetCustomer");//做成配置文件,如果是坐市商的话就用theSeat
	  public static String exEntrustIsUseMongo=PropertiesUtils.APP.getProperty("app.exEntrustIsUseMongo");
	  //是否开启闭盘定时器
	  public static String isClosePlate=PropertiesUtils.APP.getProperty("app.isClosePlate");
	
	   
	   
	   public static Boolean isexEntrustIsUseMongo(){
		   return true;
	   }
	   public static Boolean isexEntrustIsUseMongomatch(){
				return true;
	   }
	   public static Boolean isexEntrustIsUseMongodeph(){
			return true;
       }
	   public static Boolean isexOrderIsMemory(){
				return true;
	     }
	
}
