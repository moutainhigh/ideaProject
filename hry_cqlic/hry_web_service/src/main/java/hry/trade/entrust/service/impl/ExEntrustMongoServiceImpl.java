/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service.impl;

import com.alibaba.fastjson.JSON;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.ExEntrustSimple;
import hry.trade.entrust.service.ExEntrustMongoService;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.exEntrustOneDay.model.ExentrustOneday;
import hry.trade.exEntrustOneDay.service.ExentrustOnedayService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("exEntrustMongoService")
public class ExEntrustMongoServiceImpl implements ExEntrustMongoService {
	@Resource
	public RedisService redisService;
	@Resource
	public ExEntrustService exEntrustService;
	@Resource
	public ExentrustOnedayService exentrustOnedayService;
	 
	public static List<ExEntrustSimple> commonbuyenlist=null;
	public static List<ExEntrustSimple> commonsellenlist=null;
	
	
	public void geMongotbuyExEntrustIng(Map<String, Object> map) {
		String website= map.get("website").toString();
		String coinCode= map.get("coinCode").toString();
		String fixPriceCoinCode= map.get("fixPriceCoinCode").toString();
		String currencyType= map.get("currencyType").toString();
		List<Integer> customerTypelist= (List<Integer>)map.get("customerType");
		StringBuffer customerType=new StringBuffer();
		for(Integer l:customerTypelist){
			customerType.append(l+",");
		}
		customerType.deleteCharAt(customerType.length()-1);
		
		QueryFilter filter=new QueryFilter(ExentrustOneday.class);
		filter.addFilter("coinCode=", coinCode);
		filter.addFilter("customerType_in", customerType.toString());
		filter.addFilter("entrustWay=", 1);
		filter.addFilter("website=", website);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("coinCode=", coinCode);
		filter.addFilter("fixPriceCoinCode=", fixPriceCoinCode);
		filter.addFilter("type=", 1);
		
	
		filter.setOrderby("entrustPriceDouble desc");
		List<ExentrustOneday> list=exentrustOnedayService.find(filter);
		String tablename=exEntrustService.getHeader(website, currencyType, coinCode, fixPriceCoinCode)+":buy_entrust";
		redisService.save(tablename, JSON.toJSONString(list));
	}
	
	
	public void geMongotsellExEntrustIng(Map<String, Object> map) {
		String website= map.get("website").toString();
		String coinCode= map.get("coinCode").toString();
		String currencyType= map.get("currencyType").toString();
		String fixPriceCoinCode= map.get("fixPriceCoinCode").toString();
		List<Integer> customerTypelist= (List<Integer>)map.get("customerType");
		StringBuffer customerType=new StringBuffer();
		for(Integer l:customerTypelist){
			customerType.append(l+",");
		}
		customerType.deleteCharAt(customerType.length()-1);
		QueryFilter filter=new QueryFilter(ExentrustOneday.class);
		filter.addFilter("customerType_in", customerType.toString());
		filter.addFilter("entrustWay=", 1);
		filter.addFilter("type=", 2);
		filter.addFilter("website=", website);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("coinCode=", coinCode);
		filter.addFilter("fixPriceCoinCode=", fixPriceCoinCode);
		filter.setOrderby("entrustPriceDouble asc");
		List<ExentrustOneday> list=exentrustOnedayService.find(filter); 
		String tableName=exEntrustService.getHeader(website, currencyType, coinCode, fixPriceCoinCode)+":sell_entrust";
	    redisService.save(tableName, JSON.toJSONString(list));
	}
	@Override
	public List<ExEntrustSimple> geMongotbuyExEntrustChange(Map<String, Object> map) {
//		geMongotbuyExEntrustIng(map);
		
		String website= map.get("website").toString();
		String coinCode= map.get("coinCode").toString();
		String currencyType= map.get("currencyType").toString();
		String fixPriceCoinCode= map.get("fixPriceCoinCode").toString();
		String header=exEntrustService.getHeader(website, currencyType, coinCode, fixPriceCoinCode);
		String tablename=header+":buy_entrust";
		List<ExEntrustSimple> buyenlist=new ArrayList<ExEntrustSimple>();
		buyenlist=JSON.parseArray(redisService.get(tablename), ExEntrustSimple.class);
	    
		List<ExEntrustSimple> newelist=	new ArrayList<ExEntrustSimple>();
		Integer count=Integer.valueOf(map.get("count").toString());
		if(null!=buyenlist&&buyenlist.size()!=0){
			    for(int j=0;j<count;j++){//关键在这儿
				    if(buyenlist.size()==0){
					  break;//关键在这儿
				    }
				    ExEntrustSimple ej=new ExEntrustSimple();
			    	ej=buyenlist.get(0);
			    	buyenlist.remove(0);
			    	newelist.add(ej);
			    	while(buyenlist.size()>0){
			    		ExEntrustSimple ed=buyenlist.get(0);
			    		//如果价格相同，那么数量相加
				    	if(ej.getEntrustPrice().compareTo(ed.getEntrustPrice())==0){
				    		ej.setSurplusEntrustCount(ej.getSurplusEntrustCount().add(ed.getSurplusEntrustCount()));
				    		buyenlist.remove(ed);
				    	}else{
				    		break;//如果价格不相等跳出累加、继续下一个（卖2、卖3）
				    	}
			    	}
			    }
			    return newelist;
		}
	  
		return newelist;
	}

	@Override
	public List<ExEntrustSimple> geMongosellExEntrustChange(Map<String, Object> map) {
		String website= map.get("website").toString();
		String coinCode= map.get("coinCode").toString();
		String currencyType= map.get("currencyType").toString();
		String fixPriceCoinCode= map.get("fixPriceCoinCode").toString();
		String header=exEntrustService.getHeader(website, currencyType, coinCode, fixPriceCoinCode);
		String tablename=header+":sell_entrust";
	//    geMongotsellExEntrustIng(map);
	    
		List<ExEntrustSimple> sellenlist=	new ArrayList<ExEntrustSimple>();
		sellenlist=JSON.parseArray(redisService.get(tablename), ExEntrustSimple.class);
		List<ExEntrustSimple> newelist=	new ArrayList<ExEntrustSimple>();
		Integer count=Integer.valueOf(map.get("count").toString());
		if(null!=sellenlist&&sellenlist.size()!=0){
		    for(int j=0;j<count;j++){//关键在这儿
			    if(sellenlist.size()==0){
				  break;//关键在这儿
			    }
			    ExEntrustSimple ej=sellenlist.get(0);
		    	sellenlist.remove(0);
		    	newelist.add(ej);
		    	while(sellenlist.size()>0){
		    		ExEntrustSimple ed=sellenlist.get(0);
			    	if(ej.getEntrustPrice().compareTo(ed.getEntrustPrice())==0){
			    		ej.setSurplusEntrustCount(ej.getSurplusEntrustCount().add(ed.getSurplusEntrustCount()));
			    		sellenlist.remove(ed);
			    	}else{
			    		break;//关键在这儿
			    	}
		    	}
		    }
		}
		return newelist;
	}

/*	@Override
	public List<ExEntrustSimple> geMongoExEntrustBuyDeph(Map<String, Object> map) {
		BigDecimal startPrice = new BigDecimal(map.get("startPrice").toString());
		BigDecimal depthjg = new BigDecimal(map.get("depthjg").toString());

		String website = map.get("website").toString();
		String coinCode = map.get("coinCode").toString();
		String currencyType = map.get("currencyType").toString();
		String fixPriceCoinCode= map.get("fixPriceCoinCode").toString();
		String header=exEntrustService.getHeader(website, currencyType, coinCode, fixPriceCoinCode);
		String tablename =header + ":buy_entrust";
		List<ExEntrustSimple> buyenlist = new ArrayList<ExEntrustSimple>();
		buyenlist = redisService.getList1(tablename);
		List<ExEntrustSimple> newelist = new ArrayList<ExEntrustSimple>();
		if (null != buyenlist && buyenlist.size() != 0) {
			for (int j = 0; j < 5; j++) {// 关键在这儿

				if (buyenlist.size() == 0) {
					ExEntrustSimple ej = new ExEntrustSimple();
					newelist.add(ej);
					startPrice = startPrice.subtract(depthjg);
					ej.setEntrustPriceDouble(startPrice.doubleValue());
					ej.setSurplusEntrustCount(new BigDecimal("0"));
					break;// 关键在这儿
				}
				ExEntrustSimple ej = new ExEntrustSimple();
				newelist.add(ej);
				BigDecimal endPrice = startPrice;
				startPrice = startPrice.subtract(depthjg);
				ej.setEntrustPriceDouble(startPrice.doubleValue());
				ej.setSurplusEntrustCount(new BigDecimal("0"));
				while (buyenlist.size() > 0) {
					ExEntrustSimple ed = buyenlist.get(0);
					if (j == 0) {
						if (new BigDecimal(ed.getEntrustPriceDouble()).compareTo(startPrice) >= 0) {
							ej.setSurplusEntrustCount(ej.getSurplusEntrustCount().add(ed.getSurplusEntrustCount()));
							buyenlist.remove(ed);
						} else {
							break;// 关键在这儿
						}
					} else {
						if (new BigDecimal(ed.getEntrustPriceDouble()).compareTo(startPrice) >= 0
								&& new BigDecimal(ed.getEntrustPriceDouble()).compareTo(endPrice) < 0) {
							ej.setSurplusEntrustCount(ej.getSurplusEntrustCount().add(ed.getSurplusEntrustCount()));
							buyenlist.remove(ed);
						} else {
							break;// 关键在这儿
						}
					}

				}
			}
		}
		return newelist;
	}

	@Override
	public List<ExEntrustSimple> ggeMongoExEntrustSellDeph(Map<String, Object> map) {
		String website= map.get("website").toString();
		String coinCode= map.get("coinCode").toString();
		String currencyType= map.get("currencyType").toString();
		String fixPriceCoinCode= map.get("fixPriceCoinCode").toString();
		String header=exEntrustService.getHeader(website, currencyType, coinCode, fixPriceCoinCode);
		String tablename=header+":sell_entrust";
		BigDecimal startPrice= new BigDecimal(map.get("startPrice").toString());
		BigDecimal depthjg=new BigDecimal( map.get("depthjg").toString());
		
		List<ExEntrustSimple> sellenlist=new ArrayList<ExEntrustSimple>();
		sellenlist=redisService.getList1(tablename);
		List<ExEntrustSimple> newelist=	new ArrayList<ExEntrustSimple>();
		if(null!=sellenlist&&sellenlist.size()!=0){
		    for(int j=0;j<5;j++){//关键在这儿
		    	
			    if(sellenlist.size()==0){

			    	ExEntrustSimple ej=new ExEntrustSimple();
			    	newelist.add(ej);
			    	startPrice=startPrice.add(depthjg);
			    	ej.setEntrustPriceDouble(startPrice.doubleValue());
			    	ej.setSurplusEntrustCount(new BigDecimal("0"));
				  break;//关键在这儿
			    
			    }
			    ExEntrustSimple ej=new ExEntrustSimple();
		    	newelist.add(ej);
		    	BigDecimal endPrice=startPrice;
		    	           startPrice=startPrice.add(depthjg);
		    	ej.setEntrustPriceDouble(startPrice.doubleValue());
		    	ej.setSurplusEntrustCount(new BigDecimal("0"));
		    	while(sellenlist.size()>0){
		    		ExEntrustSimple ed=sellenlist.get(0);
		    		if(j==0){
		    			if(new BigDecimal(ed.getEntrustPriceDouble()).compareTo(startPrice)<=0){
				    		ej.setSurplusEntrustCount(ej.getSurplusEntrustCount().add(ed.getSurplusEntrustCount()));
				    		sellenlist.remove(ed);
				    	}else{
				    		break;//关键在这儿
				    	}
		    		}else{
		    			if(new BigDecimal(ed.getEntrustPriceDouble()).compareTo(endPrice)>=0&&new BigDecimal(ed.getEntrustPriceDouble()).compareTo(startPrice)<0){
				    		ej.setSurplusEntrustCount(ej.getSurplusEntrustCount().add(ed.getSurplusEntrustCount()));
				    		sellenlist.remove(ed);
				    	}else{
				    		break;//关键在这儿
				    	}
		    		}
			    	
		    	}
		    }
		}
		return newelist;
	}
	*/
}
