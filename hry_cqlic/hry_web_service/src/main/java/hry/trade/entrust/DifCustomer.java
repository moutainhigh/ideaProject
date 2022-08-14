/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年7月27日 上午10:48:52
 */
package hry.trade.entrust;

import hry.core.constant.StringConstant;
import hry.util.date.DateUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.exchange.product.model.ProductCommon;
import hry.exchange.product.service.ProductCommonService;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.model.ExEntrust;
import hry.web.remote.RemoteAppConfigService;

import java.util.Date;
import java.util.Map;

import tk.mybatis.mapper.util.StringUtil;

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
	  //匹配客户的类型(根据产品不同，有的产品需要坐市机制，有的需要竞价机制)
	   public static String getMatchCustomerType(ExEntrust exEntrust){
		   String customerType="1,2,3";
		   if(differetCustomer.equals("common")){
			   customerType="1,2,3";
		   }else if(differetCustomer.equals("theSeat")){
			  String productTransactionType= getProductTransactionType(exEntrust.getCoinCode()); //得到产品交易模式
			    if(productTransactionType.equals("1")){ //普通竞价模式
			    	   customerType="1,2,3";
			    }else{//坐市模式
			    	 if(exEntrust.getCustomerType().equals(2)){ //乙客户
						   customerType="1,3";  //匹配客户甲丙
			    	 }else{ //甲客户或丙客户
					   customerType="2";  //匹配客户乙
			    	 }
			    }
		   }
		   return customerType;
	   }
	   //行情中心推送的委托数据的客户类型
	   public static String getPushData(String header){
		   
		   String customerType="1,2,3";
		   if(differetCustomer.equals("common")){
			   customerType="1,2,3";
		   }else if(differetCustomer.equals("theSeat")){
			   String productTransactionType= getProductTransactionType(header); //得到产品交易模式
			   if(productTransactionType.equals("1")){ //普通竞价模式
		    	   customerType="1,2,3";
			    }else{//坐市模式
			    	customerType="2";  //推送客户乙
			    }
		   }
		   return customerType;
	   }
	   //个人中心的委卖委买数据
	   public static String getPushData1(String header){
		   String customerType="1,2,3";
		   if(differetCustomer.equals("common")){
			   customerType="1,2,3";
		   }else if(differetCustomer.equals("theSeat")){
			   String productTransactionType= getProductTransactionType(header); //得到产品交易模式
			   if(productTransactionType.equals("1")){ //普通竞价模式
		    	   customerType="1,2,3";
			    }else{//坐市模式
			    	customerType="1,3";  //推送客户甲丙
			    }
		  
		   }
		   return customerType;
	   }
	   //
	   public static Boolean getIsCommon(){
		   if(differetCustomer.equals("common")){
			  return true;
		   }else if(differetCustomer.equals("theSeat")){
			  return false;
		   }
		return null;
	   }
	   //
	   public   static String customerTypeName(Integer customerType){
		   if(customerType==1){
			  return "A";
		   }else if(customerType==2){
			   return "B";
		   }else {
			   return "C";
		   }
	   }
	   public static String getProductTransactionType (String coinCode){
		   
		 //根据产品的不同有需要坐市的，也有不需要坐市的
		   
		   ProductCommonService productCommonService =(ProductCommonService) ContextUtil.getBean("productCommonService"); 
			ProductCommon productCommon =productCommonService.getProductCommon(coinCode);
			
			if(null==productCommon||null==productCommon.getTransactionType()){
			   return "1";
		   }
		   return productCommon.getTransactionType().toString();
	   }
	   
	   
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
	   //当产品表里面的坐市竞价状态发生了变化的话就要清空
	   public static void clearOnlyentrustNum(String coinCode){
			 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
				for (String website : mapLoadWeb.keySet()) {
					   String currencyType=mapLoadWeb.get(website);
					String header=website+":"+currencyType+":"+coinCode;
					ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.OnlyentrustNum, "");
					ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.NoentrustNum, "");
				}
	   }
	   //是否在开盘闭盘之间，如果在开盘闭盘之间可以撮合匹配
	   public static Boolean isInOpenplateAndCloseplate(){
			if(DifCustomer.getIsCommon()){
				return true;
			}
			RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
			String openAndclosePlateTime=remoteAppConfigService.getFinanceByKey("openAndclosePlateTime");
			openAndclosePlateTime=StringUtil.isEmpty(openAndclosePlateTime)?"09:00:00,12:00:00,14:00:00,23:00:00":openAndclosePlateTime; //
		    if(!StringUtil.isEmpty(openAndclosePlateTime)){	
		      String[]	arrtime=openAndclosePlateTime.split(",");
		      int i=0;
		      while(i<arrtime.length){
			  	    String  nowdate=DateUtil.dateToString(new Date(),StringConstant.DATE_FORMAT_TIME);
				    String[]  nowdatearr=  nowdate.split(":");
				    Integer sumnowdatearr=Integer.valueOf(nowdatearr[0])*3600+Integer.valueOf(nowdatearr[1])*60+Integer.valueOf(nowdatearr[2]);
				    	
				    String[]  openingTimearr=  arrtime[i].split(":");
				    Integer sumopeningTimearr=Integer.valueOf(openingTimearr[0])*3600+Integer.valueOf(openingTimearr[1])*60+Integer.valueOf(openingTimearr[2]);
				   
				    String[]  closeTimearr=  arrtime[i+1].split(":");
				    Integer sumcloseTimearr=Integer.valueOf(closeTimearr[0])*3600+Integer.valueOf(closeTimearr[1])*60+Integer.valueOf(closeTimearr[2]);
				    
				     i=i+2;
				    if(sumnowdatearr>=sumopeningTimearr&&sumnowdatearr<=sumcloseTimearr){
				    	return true;
				    }
		      }
		      return false;
		    }
		    return true;
		
	}
	   
	   //是否在开市闭盘之间，如果在开市闭盘之间可以委托
	   public static Boolean isInOpenAndCloseplate(){
		    if(DifCustomer.getIsCommon()){
				   return true;
			    }
			    RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
				String openingTime=remoteAppConfigService.getFinanceByKey("openingTime");
				String closeTime=remoteAppConfigService.getFinanceByKey("closeTime");
				openingTime=StringUtil.isEmpty(openingTime)?"08:00:00":openingTime; //
				closeTime=StringUtil.isEmpty(closeTime)?"23:59:59":closeTime; //
				String openAndclosePlateTime=remoteAppConfigService.getFinanceByKey("openAndclosePlateTime");
				openAndclosePlateTime=StringUtil.isEmpty(openAndclosePlateTime)?"09:00:00,12:00:00,14:00:00,23:00:00":openAndclosePlateTime; //
			    if(!StringUtil.isEmpty(openAndclosePlateTime)){	
			      String[]	arrtime=openAndclosePlateTime.split(",");
			      closeTime=arrtime[arrtime.length-1];
			    }
			    if(openingTime.equals(closeTime)){
			    	
			    	return true;
			    }else{
			    	
			    String  nowdate=DateUtil.dateToString(new Date(),StringConstant.DATE_FORMAT_TIME);
			    String[]  nowdatearr=  nowdate.split(":");
			    Integer sumnowdatearr=Integer.valueOf(nowdatearr[0])*3600+Integer.valueOf(nowdatearr[1])*60+Integer.valueOf(nowdatearr[2]);
			    	
			    String[]  closeTimearr=  closeTime.split(":");  //闭盘
			    Integer sumcloseTimearr=Integer.valueOf(closeTimearr[0])*3600+Integer.valueOf(closeTimearr[1])*60+Integer.valueOf(closeTimearr[2]);
			    
			    
			    String[]  openingTimearr=  openingTime.split(":");  //开市
			    Integer sumopeningTimearr=Integer.valueOf(openingTimearr[0])*3600+Integer.valueOf(openingTimearr[1])*60+Integer.valueOf(openingTimearr[2]);
			    
			    if(sumnowdatearr>=sumopeningTimearr&&sumnowdatearr<=sumcloseTimearr){
			    	return true;
			    }else{
			    	
			    	return false;
			    }
			    
			    }
				
		   }
	   //是否在闭盘闭市之间，如果在闭盘闭市之间不允许充值提现，融资
	   public static Boolean isInClosePlateAndClose(){
		   
			    RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
				String closeTime=remoteAppConfigService.getFinanceByKey("closeTime");
				closeTime=StringUtil.isEmpty(closeTime)?"23:59:59":closeTime; //闭市
				String openAndclosePlateTime=remoteAppConfigService.getFinanceByKey("openAndclosePlateTime");
				openAndclosePlateTime=StringUtil.isEmpty(openAndclosePlateTime)?"09:00:00,12:00:00,14:00:00,23:00:00":openAndclosePlateTime; //
			    String closePlateTime="23:00:00";
				if(!StringUtil.isEmpty(openAndclosePlateTime)){	
			      String[]	arrtime=openAndclosePlateTime.split(",");
			      closePlateTime=arrtime[arrtime.length-1];
			    }
			    String  nowdate=DateUtil.dateToString(new Date(),StringConstant.DATE_FORMAT_TIME);
			    String[]  nowdatearr=  nowdate.split(":");
			    Integer sumnowdatearr=Integer.valueOf(nowdatearr[0])*3600+Integer.valueOf(nowdatearr[1])*60+Integer.valueOf(nowdatearr[2]);
			    	
			    String[]  closeTimearr=  closeTime.split(":");  //闭市
			    Integer sumcloseTimearr=Integer.valueOf(closeTimearr[0])*3600+Integer.valueOf(closeTimearr[1])*60+Integer.valueOf(closeTimearr[2]);
			    
			    
			    String[]  closePlateTimearr=  closePlateTime.split(":");  //闭盘
			    Integer sumclosePlateTime=Integer.valueOf(closePlateTimearr[0])*3600+Integer.valueOf(closePlateTimearr[1])*60+Integer.valueOf(closePlateTimearr[2]);
			    
			    if(sumnowdatearr>=sumclosePlateTime&&sumnowdatearr<=sumcloseTimearr){
			    	return true;
			    }else{
			    	
			    	return false;
			    }
			    
				
		   }  
	   
		public static void  setRedisStringData(String key,String value){
			String  nowdate=DateUtil.dateToString(new Date(),StringConstant.DATE_FORMAT_TIME);
			String[]  nowdatearr=  nowdate.split(":");
			Integer sumnowdatearr=Integer.valueOf(nowdatearr[0])*3600+Integer.valueOf(nowdatearr[1])*60+Integer.valueOf(nowdatearr[2]);
			 RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
			redisService.save(key, value, 86400-sumnowdatearr);
		}
		public static String  getEntrustOne(){
			   String customerType="1,2,3";
			   if(differetCustomer.equals("common")){
				   customerType="1,2,3";
			   }else if(differetCustomer.equals("theSeat")){
				   customerType="2";  //全查乙类委托
			   }
			   return customerType;
		   }
		
		/**
		 * 判断是否需要开启闭盘定时器
		 * <p> TODO</p>
		 * @author:         Shangxl
		 * @param:    @return
		 * @return: boolean 
		 * @Date :          2017年6月9日 下午3:11:02   
		 * @throws:
		 */
		public static boolean isClosePlate(){
			if(isClosePlate!=null&&!"".equals(isClosePlate)&&isClosePlate.equals("true")){
				return true;
			}
			return false;
		}
}
