/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;


import hry.redis.common.utils.RedisService;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExEntrustPlan;
import hry.trade.entrust.service.ExEntrustPlanService;
import hry.trade.entrust.service.ExEntrustService;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;



/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("exEntrustPlanService")
public class ExEntrustPlanServiceImpl extends BaseServiceImpl<ExEntrustPlan, Long>
		implements ExEntrustPlanService {

	@Resource(name = "exEntrustPlanDao")
	@Override
	public void setDao(BaseDao<ExEntrustPlan, Long> dao) {
		super.dao = dao;
	}
	@Resource(name = "exEntrustService")
	public ExEntrustService  exEntrustService;
	@Resource
	private  RedisService redisService;
	public void planEntrust() {
		
	    String saasId = PropertiesUtils.APP.getProperty("app.saasId");
	    QueryFilter filter=new QueryFilter(ExEntrustPlan.class);
		filter.addFilter("isValid=", 0);
		List<ExEntrustPlan> list=this.find(filter);
		for(ExEntrustPlan p:list){
			Boolean stutus=	isAccordwithMarket(p);
			if(stutus){
				//预埋委托
				if(p.getIsPreEntrust()==1){
					createExEntrust(p);
				}else{//预埋撤销
					cancellExEntrust(p);
					 p.setIsValid(1);
			         this.update(p);
				}
				
			}
            
		}
		
	
	}
		
		
	public void cancellExEntrust(ExEntrustPlan p){

		Boolean isEntrustNums = p.getIsEntrustNums();
		String entrustNums = p.getEntrustNums();
		Boolean allSellEntrust = p.getAllSellEntrust();
		Boolean allBuyEntrust = p.getAllBuyEntrust();
		
		String customerType=p.getCustomerType().toString();
		Long customerId=null;
		if(p.getCustomerType().equals(2)){
		}else{
			customerId=p.getCustomerId();
			
		}
		if(null!=allSellEntrust){
			exEntrustService.cancelAlltypeExEntrust(2, p.getCurrencyType(), p.getWebsite(),customerType,customerId);
		}
        if(null!=allBuyEntrust){
        	exEntrustService.cancelAlltypeExEntrust(1,  p.getCurrencyType(), p.getWebsite(),customerType,customerId);
		}
        if(null!=isEntrustNums){
 			//exEntrustService.cancelExEntrust(entrustNums,null,"预埋");
        }
        Boolean isEntrustCount = p.getIsEntrustCount();
        Boolean isEntrustPrice =p.getIsEntrustPrice();
        
    	BigDecimal entrustCountl = p.getEntrustCountl();
     	BigDecimal entrustCountg = p.getEntrustCountg();
		BigDecimal entrustPriceg = p.getEntrustPriceg();
		BigDecimal entrustPricel = p.getEntrustPricel();
		
		Map<String,String> seramap=new HashMap<String,String>();
		seramap.put("website", p.getWebsite());
    	seramap.put("currencyType", p.getCurrencyType());
    	seramap.put("coinCode", p.getCoinCode());
    	seramap.put("customerType", customerType);
    	if(null!=customerId){
    		seramap.put("customerId", customerId.toString());
    	}
    	
        if(null!=isEntrustCount&&null!=isEntrustPrice){
        	
        	
            seramap.put("entrustCountl", entrustCountl.toString());
    		seramap.put("entrustCountg", entrustCountg.toString());
    		seramap.put("entrustPricel", entrustPricel.toString());
    		seramap.put("entrustPriceg", entrustPriceg.toString());
 			exEntrustService.cancelConditionExEntrust(seramap);
        }else{
        	  if(null!=isEntrustCount){
                seramap.put("entrustCountl", entrustCountl.toString());
          		seramap.put("entrustCountg", entrustCountg.toString());
          		exEntrustService.cancelConditionExEntrust(seramap);
              }
        	  if(null!=isEntrustPrice){
        		seramap.put("entrustPricel", entrustPricel.toString());
        		seramap.put("entrustPriceg", entrustPriceg.toString());
        		exEntrustService.cancelConditionExEntrust(seramap);
                }
            
        }
          
        
	
		
	}
	@Override
	public Boolean isAccordwithMarket(ExEntrustPlan p){
		 String header=p.getWebsite()+":"+p.getCurrencyType()+":"+p.getCoinCode();
		if(null!=p.getIspriceType()){ //选择了第一行条件
			  BigDecimal currentExchangPrice=new BigDecimal("0.00");//最新成交价
			   String currentExchangPrices=redisService.get(header+":"+ExchangeDataCache.CurrentExchangPrice);
		 		if(null!=currentExchangPrices){
		 			currentExchangPrice=new BigDecimal(currentExchangPrices).setScale(2,BigDecimal.ROUND_DOWN);
		 		}
		 		BigDecimal buyOnePrice=new BigDecimal("0.00");//买一
		 		String buyOnePrices=redisService.get(header+":"+ExchangeDataCache.BuyOnePrice);
		 		if(null!=buyOnePrices){
		 			buyOnePrice=new BigDecimal(buyOnePrices).setScale(2,BigDecimal.ROUND_DOWN);
		 		}
		 		String sellOnePrices=redisService.get(header+":"+ExchangeDataCache.SellOnePrice);
		 		BigDecimal sellOnePrice=new BigDecimal("0.00"); //卖一
		 		if(null!=sellOnePrices){
		 			sellOnePrice= new BigDecimal(sellOnePrices).setScale(2,BigDecimal.ROUND_DOWN);
		 		}
		 		BigDecimal compareobject=currentExchangPrice;
		 	   // 1,最新价2，委买一3，委卖一
		 		if(p.getPriceType()==1){
		 			 compareobject=currentExchangPrice;
		 		}else if(p.getPriceType()==2){
		 			 compareobject=buyOnePrice;
		 		}if(p.getPriceType()==3){
		 			 compareobject=sellOnePrice;
		 		}
		 		Boolean stutus=cul(compareobject,p.getPriceValue(),p.getPriceCompare());
		 	    if(!stutus){
		 	    	return false;
		 	    }		
			
		}
		
      if(null!=p.getIscommonCountType()){
      	//
      	String sellcommonentrustCountsums=redisService.get(header+":plan:commonon:sellcommonentrustCountsum");
	 		BigDecimal sellcommonentrustCountsum=new BigDecimal("0.00"); 
	 		if(null!=sellcommonentrustCountsums){
	 			sellcommonentrustCountsum= new BigDecimal(sellcommonentrustCountsums).setScale(2,BigDecimal.ROUND_DOWN);
	 		}
	 		String buycommonentrustCountsums=redisService.get(header+":plan:commonon:buycommonentrustCountsum");
	 		BigDecimal buycommonentrustCountsum=new BigDecimal("0.00"); 
	 		if(null!=buycommonentrustCountsums){
	 			buycommonentrustCountsum= new BigDecimal(buycommonentrustCountsums).setScale(2,BigDecimal.ROUND_DOWN);
	 		}
	 		BigDecimal compareobject=sellcommonentrustCountsum;
	 	   // 1,甲委买量，2甲委卖量
      	if(p.getCommonCountType()==1){
      		compareobject=buycommonentrustCountsum;
      	}else{
      		compareobject=sellcommonentrustCountsum;
      	}
      	Boolean stutus=cul(compareobject,p.getCommonCountValue(),p.getCommonCountCompare());
	 	    if(!stutus){
	 	    	return false;
	 	    }
		}
      if(null!=p.getMakerCountType()){
      	//
      	String sellcommonentrustCountsums=redisService.get(header+":plan:yi:sellyientrustCountsum");
	 		BigDecimal sellcommonentrustCountsum=new BigDecimal("0.00"); 
	 		if(null!=sellcommonentrustCountsums){
	 			sellcommonentrustCountsum= new BigDecimal(sellcommonentrustCountsums).setScale(2,BigDecimal.ROUND_DOWN);
	 		}
	 		String buycommonentrustCountsums=redisService.get(header+":plan:yi:buyyientrustCountsum");
	 		BigDecimal buycommonentrustCountsum=new BigDecimal("0.00"); 
	 		if(null!=buycommonentrustCountsums){
	 			buycommonentrustCountsum= new BigDecimal(buycommonentrustCountsums).setScale(2,BigDecimal.ROUND_DOWN);
	 		}
	 		BigDecimal compareobject=sellcommonentrustCountsum;
	 		// 1,乙委买量，2乙委卖量
      	if(p.getCommonCountType()==1){
      		compareobject=buycommonentrustCountsum;
      	}else{
      		compareobject=sellcommonentrustCountsum;
      	}
      	Boolean stutus=cul(compareobject,p.getCommonCountValue(),p.getCommonCountCompare());
	 	    if(!stutus){
	 	    	return false;
	 	    }
		}
      if(null!=p.getCommonProportionType()){

	 		String buycommonentrustCountsums=redisService.get(header+":plan:commonon:commononEntrustProportion");
	 		BigDecimal buycommonentrustCountsum=new BigDecimal("0.00"); 
	 		if(null!=buycommonentrustCountsums){
	 			buycommonentrustCountsum= new BigDecimal(buycommonentrustCountsums).setScale(2,BigDecimal.ROUND_DOWN);
	 		}else{
	 			
	 			return false;
	 		}
	 	   // 1,甲委比
	 		BigDecimal compareobject=buycommonentrustCountsum;
           	Boolean stutus=cul(compareobject,p.getCommonCountValue(),p.getCommonCountCompare());
	 	    if(!stutus){
	 	    	return false;
	 	    }
		
		}
      if(null!=p.getMakerProportionType()){


	 		String buycommonentrustCountsums=redisService.get(header+":plan:yi:yiEntrustProportion");
	 		BigDecimal buycommonentrustCountsum=new BigDecimal("0.00"); 
	 		if(null!=buycommonentrustCountsums){
	 			buycommonentrustCountsum= new BigDecimal(buycommonentrustCountsums).setScale(2,BigDecimal.ROUND_DOWN);
	 		}else{
	 			
	 			return false;
	 		}
	 	   // 1,乙委比
	 		BigDecimal compareobject=buycommonentrustCountsum;
        	Boolean stutus=cul(compareobject,p.getCommonCountValue(),p.getCommonCountCompare());
	 	    if(!stutus){
	 	    	return false;
	 	    }
		
		
		}
      if(null!=p.getTimeType()){
      	//1时间
		  String nowtime=	DateUtil.getFormatDateTime(new Date(), StringConstant.DATE_FORMAT_TIME);
		  String[] nowtimearr=  nowtime.split(":");
		  BigDecimal compareobject=(new BigDecimal(nowtimearr[0]).multiply(new BigDecimal(3600))).add(new BigDecimal(nowtimearr[1]).multiply(new BigDecimal(60)).add(new BigDecimal(nowtimearr[2])));
		  String[] timeValuearr=  p.getTimeValue().split(":");
		  BigDecimal timeValueavalue=(new BigDecimal(timeValuearr[0]).multiply(new BigDecimal(3600))).add(new BigDecimal(timeValuearr[1]).multiply(new BigDecimal(60)).add(new BigDecimal(timeValuearr[2])));
		  Boolean stutus=cul(compareobject,timeValueavalue,p.getTimeCompare());
	 	    if(!stutus){
	 	    	return false;
	 	    }
      }
      return true;
	}
	public Boolean cul(BigDecimal compareobject,BigDecimal value,Integer compare){
		
		
		  // 1>,  2<,  3=,  4<=  ,5>=
			if(compare==1){
				if(compareobject.compareTo(value)==1){
					return true;
				}else{
					return false;
				}
				
			}else if(compare==2){
            if(compareobject.compareTo(value)==-1){
            	return true;
				}else{
					return false;
				}
				
			}else if(compare==3){
				 if(compareobject.compareTo(value)==0){
					 return true;
	 				}else{
	 					return false;
	 				}
				
			}else if(compare==4){
				if(compareobject.compareTo(value)<=0){
					return true;
				}else{
					return false;
				}
				
			}else if(compare==5){
            if(compareobject.compareTo(value)>=0){
            	return true;
				}else{
					return false;
				}
			}
			return true;
	}
	public void createExEntrust(ExEntrustPlan p){
	       String hearder=p.getWebsite()+":"+p.getCurrencyType()+":"+p.getCoinCode();
	       String currentExchangPrices=ExchangeDataCache.getStringData(hearder+":"+ExchangeDataCache.CurrentExchangPrice);
		   BigDecimal currentExchangPrice = new BigDecimal(currentExchangPrices);
           ExEntrust exEntrust =new ExEntrust();
           exEntrust.setEntrustPrice(currentExchangPrice.add(p.getFloatPrice()));
           exEntrust.setEntrustCount(p.getEntrustCount());
           exEntrust.setEntrustWay(1);//?
           exEntrust.setType(p.getType());
           exEntrust.setCustomerId(p.getCustomerId());
           exEntrust.setWebsite(p.getWebsite());
           exEntrust.setCurrencyType(p.getCurrencyType());
           exEntrust.setUserCode(p.getUserCode());
           exEntrust.setCustomerType(p.getCustomerType());
           exEntrust.setCoinCode(p.getCoinCode());
           exEntrust.setMatchPriority(1);
           exEntrustService.saveExEntrust(exEntrust);
           
           p.setEntrustNum(exEntrust.getEntrustNum());
           p.setIsValid(1);
           this.update(p);
	}
	// 1>,  2<,  3=,  4<=  ,5>=
	public String  compareString(Integer vaule){
		
		if(vaule==1){
			return ">";
		}else if(vaule==2){
			return "<";
		}else if(vaule==3){
			return "=";
		}else if(vaule==4){
			return "<=";
		}else if(vaule==5){
			return ">=";
		}
		return "null";
	}
	@Override
	public String pingCondition(ExEntrustPlan p){
		StringBuffer sb=new StringBuffer();
		if(null!=p.getIspriceType()){ //选择了第一行条件
		 	   // 1,最新价2，委买一3，委卖一
		 		if(p.getPriceType()==1){
		 			sb.append("最新价");
		 		}else if(p.getPriceType()==2){
		 			sb.append("委买一");
		 		}else if(p.getPriceType()==3){
		 			sb.append("委卖一");
		 		}
		 		sb.append(compareString(p.getPriceCompare()));
		 		sb.append(p.getPriceValue());
		 		sb.append(",");
		}
		
     if(null!=p.getIscommonCountType()){
    	// 1,甲委买量，2甲委卖量
    	 
    	 if(p.getCommonCountType()==1){
	 			sb.append("甲委买量");
	 		}else if(p.getCommonCountType()==2){
	 			sb.append("甲委卖量");
	 		}
	 		sb.append(compareString(p.getCommonCountCompare()));
	 		sb.append(p.getCommonCountValue());
	 		sb.append(",");
     }
     if(null!=p.getIsmakerCountType()){
    	 
    	// 1,乙委买量，2乙委卖量
    	 
    	 if(p.getCommonCountType()==1){
	 			sb.append("乙委买量");
	 		}else if(p.getCommonCountType()==2){
	 			sb.append("乙委卖");
	 		}
	 		sb.append(compareString(p.getMakerCountCompare()));
	 		sb.append(p.getMakerCountValue());
	 		sb.append(",");
     }
     if(null!=p.getIscommonProportionType()){
    	// 1,甲委比
    	 sb.append("甲委比");
    	 sb.append(compareString(p.getCommonCountCompare()));
	 	 sb.append(p.getCommonCountValue());
	 	sb.append(",");
    	 
     }
     if(null!=p.getIsmakerProportionType()){

     	// 1,乙委比
     	 sb.append("乙委比");
     	 sb.append(compareString(p.getMakerProportionCompare()));
 	 	 sb.append(p.getMakerProportionValue());
 	 	sb.append(",");
     	 
      
     }
     if(null!=p.getTimeType()){
    	 
    	 sb.append("时间");
     	 sb.append(compareString(p.getTimeCompare()));
 	 	 sb.append(p.getTimeValue());
 	 	sb.append(",");
     	 
     }
     //预埋撤单
     if(p.getIsPreEntrust()==0){
    	 if(null!=p.getIsEntrustPrice()){
    		 if(null!=p.getEntrustPriceg()){
        		 sb.append("委价≥"+p.getEntrustPriceg()+",");
             }
        	 if(null!=p.getEntrustPricel()){
        		 sb.append("委价≤"+p.getEntrustPricel()+",");
             }
    	 }
    	 if(null!=p.getIsEntrustCount()){
    		 if(null!=p.getEntrustCountg()){
        		 sb.append("委量≥"+p.getEntrustCountg()+",");
             }
        	 if(null!=p.getEntrustCountl()){
        		 sb.append("委量≤"+p.getEntrustCountl()+",");
             }
    	 }
    	
    	 if(null!=p.getIsEntrustNums()){
    		 if(null!=p.getEntrustNums()){
        		 sb.append("委托号："+p.getEntrustNums()+",");
             }
        
    	 }
    	 if(null!=p.getAllBuyEntrust()){
        		 sb.append("全部承接单,");
        
    	 }
    	 if(null!=p.getAllSellEntrust()){
    		 sb.append("全部派发单,");
    
	 }
     }
    if(sb.toString().endsWith(",")){
    	return sb.toString().substring(0,  sb.toString().length()-1);
    }
     return sb.toString();
	}
}
