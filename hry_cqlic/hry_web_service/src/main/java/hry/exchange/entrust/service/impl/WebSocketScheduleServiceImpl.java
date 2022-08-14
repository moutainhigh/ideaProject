/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */

package hry.exchange.entrust.service.impl;

import hry.util.properties.PropertiesUtils;
import hry.exchange.entrust.service.ExExEntrustService;
import hry.exchange.entrust.service.WebSocketScheduleService;
import hry.exchange.product.model.ProductCommon;
import hry.exchange.product.service.ProductCommonService;
import hry.manage.remote.model.Coin;
import hry.trade.entrust.DifCustomer;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.websoketContext.PushData;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;


/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("webSocketScheduleService")
public class WebSocketScheduleServiceImpl implements WebSocketScheduleService {
	@Resource
	private ExOrderService exOrderService;
	@Resource
	private ExEntrustService entrustService;
	@Resource
	private ExExEntrustService exExEntrustService;
	@Resource
	private ProductCommonService productCommonService;
/*	//首页
	@Override
	public void pushindex(){
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(Website);
		    String productListStr = ExchangeDataCache.getStringData(Website+":productList");
		    if(!StringUtils.isEmpty(productListStr)){
				List<String> productList = JSON.parseArray(productListStr,String.class);
				for(String coinCode : productList){
					ProductCommon productCommon =exExEntrustService.getProductCommon(coinCode);
					if(productCommon!=null){
					    String header=Website+":"+currencyType+":"+productCommon.getCoinCode();
						//推送首页
						StringBuffer sb=new StringBuffer("{\"coinCode\":\"" +productCommon.getCoinCode() +"\",\"name\":\"" +(null==productCommon.getName()?"":productCommon.getName())+ productCommon.getCoinCode() +"\",\"sort\":\"" +(null==productCommon.getSort()?"1":productCommon.getSort())+"\",\"picturePath\":\"" +(null==productCommon.getPicturePath()?"":productCommon.getPicturePath()) +"\",\"isRecommend\":\"" + productCommon.getIsRecommend() + "\",\"data\":"+exOrderService.getTotal(header)+"}");
						PushData.pushIndex(sb.toString(),header);
					}
		    }
		  }
		}
	}
	
	//交易大厅主要数据
	@Override
	public void pushMarket() {
		Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
			String currencyType = mapLoadWeb.get(Website);
			String productListStr = ExchangeDataCache.getStringData(Website + ":productList");
			if (!StringUtils.isEmpty(productListStr)) {
				List<String> productList = JSON.parseArray(productListStr, String.class);
				for (String coinCode : productList) {
					ProductCommon productCommon = exExEntrustService.getProductCommon(coinCode);
					String header = Website + ":" + currencyType + ":" + productCommon.getCoinCode();
					String customerType = DifCustomer.getPushData(header);
					// 委托-----行情板块
					PushData.pushEntrusMarket(entrustService.getExEntrustChangeMarket(header, "0", 18, customerType),
							header);
					// 成交订单
					PushData.pushNewListRecordMarketDesc(exOrderService.getNewListMarket(header, 60, "desc"), header);
				}
			}
		}
	}
	//交易大厅深度数据
	@Override
	public void pushEntrusDephMarket(){
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(Website);
		    String productListStr = ExchangeDataCache.getStringData(Website+":productList");
		    if(!StringUtils.isEmpty(productListStr)){
				List<String> productList = JSON.parseArray(productListStr,String.class);
				for(String coinCode : productList){
					ProductCommon productCommon =exExEntrustService.getProductCommon(coinCode);
				    String header=Website+":"+currencyType+":"+productCommon.getCoinCode();
				    String customerType =DifCustomer.getPushData(header);
				    BigDecimal  depth=new BigDecimal("0.1");
					if(productCommon.getCoinCode().equals("BTC")){
						depth=BigDecimal.ONE;
					}
					for(Integer j=1;j<=5;j++){
						BigDecimal depth1=depth.multiply(new BigDecimal(j));
						PushData.pushEntrusDephMarket(entrustService.getExEntrustChangeDephMarket(header, customerType, 5,depth1),header,j.toString());
					}
		    }
		  }
		}
	}
	
	//个人中心坐市页面的委托
	@Override
	public void pushtheSeat(){
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(Website);
		    String productListStr = ExchangeDataCache.getStringData(Website+":productList");
		    if(!StringUtils.isEmpty(productListStr)){
				List<String> productList = JSON.parseArray(productListStr,String.class);
				for(String coinCode : productList){
					ProductCommon productCommon =exExEntrustService.getProductCommon(coinCode);
				    String header=Website+":"+currencyType+":"+productCommon.getCoinCode();
				  //是坐市并且产品也是坐市的才会去算
					if(!DifCustomer.getIsCommon()){
						if(DifCustomer.getProductTransactionType(header).equals("2")){
							String customerType1 =DifCustomer.getPushData1(header);
							PushData.pushtheSeatEntrustCenter(entrustService.getExEntrustChangeMarket(header, "0",18,customerType1),header);
						
							BigDecimal depth=new BigDecimal("0.1");
							if(productCommon.getCoinCode().equals("BTC")){
								depth=new BigDecimal("1");
							}
							for(Integer j=1;j<=5;j++){
								BigDecimal depth1=depth.multiply(new BigDecimal(j));
								PushData.pushtheSeatEntrustDephCenter(entrustService.getExEntrustChangeDephMarket(header, customerType1, 5,depth1),header,j.toString());
								 
							}
						}
						
					
					}
		    }
		  }
		}
	}*/
	//首页
	@Override
	public void pushindex(){
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(Website);
		    String productListStr = ExchangeDataCache.getStringData(Website+":productFixList");
		    if(!StringUtils.isEmpty(productListStr)){
				List<String> productList = JSON.parseArray(productListStr,String.class);
				for(String coinCodetwo : productList){
					String[] coinCodeArr=coinCodetwo.split("_");
					String coinCode=coinCodeArr[0];
					 String fixPriceCoinCode=coinCodeArr[1];
						Coin productCommon = productCommonService.getProductCommon(coinCode,fixPriceCoinCode);
							if(productCommon!=null){
						
						String header=entrustService.getHeader(Website, currencyType, coinCode, fixPriceCoinCode);
						//推送首页
						StringBuffer sb=new StringBuffer("{\"coinCode\":\"" +productCommon.getCoinCode() + "\",\"fixPriceCoinCode\":\""+fixPriceCoinCode+"\",\"name\":\"" +(null==productCommon.getName()?"":productCommon.getName())+ coinCode +"\",\"sort\":\"" +(null==productCommon.getSort()?"1":productCommon.getSort())+"\",\"picturePath\":\"" +(null==productCommon.getPicturePath()?"":productCommon.getPicturePath()) +"\",\"isRecommend\":\"" + productCommon.getIsRecommend() + "\",\"data\":"+exOrderService.getTotal(header)+"}");
						PushData.pushIndex(sb.toString(),header);
					}
		    }
		  }
		}
	}
	
	//交易大厅主要数据
	@Override
	public void pushMarket() {
		Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
			String currencyType = mapLoadWeb.get(Website);
			String productListStr = ExchangeDataCache.getStringData(Website + ":productFixList");
			if (!StringUtils.isEmpty(productListStr)) {
				List<String> productList = JSON.parseArray(productListStr, String.class);
				for(String coinCodetwo : productList){
				
					String[] coinCodeArr=coinCodetwo.split("_");
					String coinCode=coinCodeArr[0];
					 String fixPriceCoinCode=coinCodeArr[1];
					String header=entrustService.getHeader(Website, currencyType, coinCode, fixPriceCoinCode);
					String ismatch=ExchangeDataCache.getStringData(header + ":" + ExchangeDataCache.IsMatch);
					if(StringUtil.isEmpty(ismatch)||ismatch.equals("1") ){
						String customerType = DifCustomer.getPushData(header);
						// 委托-----行情板块
						PushData.pushEntrusMarket(entrustService.getExEntrustChangeMarket(header, "0", 18, customerType),
								header);
						// 成交订单
						PushData.pushNewListRecordMarketDesc(exOrderService.getNewListMarket(header, 60, "desc"), header);
						
						ExchangeDataCache.setStringData(header + ":" + ExchangeDataCache.IsMatch, "0");
					}
					
				}
			}
		}
	}
	//交易大厅深度数据
	@Override
	public void pushEntrusDephMarket(){/*
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(Website);
		    String productListStr = ExchangeDataCache.getStringData(Website+":productFixList");
		    if(!StringUtils.isEmpty(productListStr)){
				List<String> productList = JSON.parseArray(productListStr,String.class);
				for(String coinCodetwo : productList){
					String[] coinCodeArr=coinCodetwo.split("_");
					String coinCode=coinCodeArr[0];
					 String fixPriceCoinCode=coinCodeArr[1];
						Coin productCommon = productCommonService.getProductCommon(coinCode,fixPriceCoinCode);
							String header=entrustService.getHeader(Website, currencyType, coinCode, fixPriceCoinCode);
				    String customerType =DifCustomer.getPushData(header);
				    BigDecimal  depth=new BigDecimal("0.1");
					if(productCommon.getCoinCode().equals("BTC")){
						depth=BigDecimal.ONE;
					}
					for(Integer j=1;j<=5;j++){
						BigDecimal depth1=depth.multiply(new BigDecimal(j));
						PushData.pushEntrusDephMarket(entrustService.getExEntrustChangeDephMarket(header, customerType, 5,depth1),header,j.toString());
					}
		    }
		  }
		}
	*/}
	
	//个人中心坐市页面的委托
	@Override
	public void pushtheSeat(){
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(Website);
		    String productListStr = ExchangeDataCache.getStringData(Website+":productFixList");
		    if(!StringUtils.isEmpty(productListStr)){
				List<String> productList = JSON.parseArray(productListStr,String.class);
				for(String coinCodetwo : productList){
					String[] coinCodeArr=coinCodetwo.split("_");
					String coinCode=coinCodeArr[0];
					String fixPriceCoinCode=coinCodeArr[1];
					Coin productCommon = productCommonService.getProductCommon(coinCode,fixPriceCoinCode);
					String header=entrustService.getHeader(Website, currencyType, coinCode, fixPriceCoinCode);
				  //是坐市并且产品也是坐市的才会去算
					if(!DifCustomer.getIsCommon()){
						if(DifCustomer.getProductTransactionType(header).equals("2")){
							String customerType1 =DifCustomer.getPushData1(header);
							PushData.pushtheSeatEntrustCenter(entrustService.getExEntrustChangeMarket(header, "0",18,customerType1),header);
						
							BigDecimal depth=new BigDecimal("0.1");
							if(productCommon.getCoinCode().equals("BTC")){
								depth=new BigDecimal("1");
							}
							for(Integer j=1;j<=5;j++){
								BigDecimal depth1=depth.multiply(new BigDecimal(j));
								PushData.pushtheSeatEntrustDephCenter(entrustService.getExEntrustChangeDephMarket(header, customerType1, 5,depth1),header,j.toString());
								 
							}
						}
						
					
					}
		    }
		  }
		}
	}	 
}