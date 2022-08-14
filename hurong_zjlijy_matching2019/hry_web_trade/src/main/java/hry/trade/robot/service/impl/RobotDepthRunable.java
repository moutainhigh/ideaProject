package hry.trade.robot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import hry.core.constant.StringConstant;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.MQmanager.MQEnter;
import hry.trade.account.service.ExDigitalmoneyAccountService;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.impl.UserRedisInitUtil;
import hry.trade.model.Coin;
import hry.trade.model.CoinKeepDecimal;
import hry.trade.model.TradeRedis;
import hry.trade.redis.model.EntrustByUser;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.trade.robot.model.Coin2;
import hry.trade.robot.model.ExRobot;
import hry.trade.robot.model.ExRobotDeep;
import hry.trade.robot.model.TransactionOrder;
import hry.trade.robot.service.ConmonApiService;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import tk.mybatis.mapper.util.StringUtil;

public class RobotDepthRunable implements Runnable {
	
	
	private ExRobotDeep exRobot;
	private String autoUsername;
	public RobotDepthRunable(ExRobotDeep exRobot,String autoUsername){
		this.exRobot=exRobot;
	}
	@Override
	public void run() {
		String key = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode();
		Boolean[] arr=isCanEntrustBykline(key);
		
		
	    RedisUtil redisUtilEntrustByUser = new RedisUtil(EntrustByUser.class);
		EntrustByUser ebu = (EntrustByUser) redisUtilEntrustByUser.get(exRobot.getCustomerId().toString());
		List<EntrustTrade> listing=new ArrayList<EntrustTrade>();
		if(null!=ebu){
			Map<String, List<EntrustTrade>> getEntrustingmap=ebu.getEntrustingmap();
			 listing=getEntrustingmap.get(key);
		}
		if(arr[0]){
			this.buyEntrust(exRobot,autoUsername,listing);
		}
		if(arr[1]){
			this.sellEntrust(exRobot,autoUsername,listing);
		}
	
	}
	
	public void buyEntrust(ExRobotDeep exRobot,String autoUsername,List<EntrustTrade> listing){
		   RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		   String key = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode();
		  
		
			String currentPrice = TradeRedis.getStringData(key+ ":" + ExchangeDataCacheRedis.CurrentExchangPrice);
			if(null==currentPrice){
				currentPrice= redisService.get(key + ":thirdApi:currentExchangPrice");
			}
		   //1\撤单
		
			int ingCount=0;
			if(null==listing){
				listing=new ArrayList<EntrustTrade>();
			}
			ArrayList<EntrustTrade> entrustTradeinglist=new ArrayList<EntrustTrade>();
			for(EntrustTrade ing:listing){
				if(ing.getType().intValue()==1){
					 if(ing.getEntrustPrice().compareTo(new BigDecimal(currentPrice).multiply((new BigDecimal("100").subtract(exRobot.getBuyOneDiffRate())).divide(new BigDecimal("100"))))==1){
						 //撤单
						 EntrustTrade entrustTrade= newCreateCancelEntrustTrade(ing);
						 MQEnter.pushExEntrustMQ(entrustTrade);
					 }else{
				    	 entrustTradeinglist.add(ing);
				    	 ingCount++;
				     }
			     }
			}	
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		   //2、深度已经够了不需要再下
		   Integer buyMarketDeeped= buyMarketDeep(key);
		   if(buyMarketDeeped>=exRobot.getBuyDeep()){
			   Integer cancelcount=ingCount-(exRobot.getBuyDeep()-(buyMarketDeeped-ingCount));
			   if(cancelcount>0){
				   int j=0;
				   while(j<cancelcount){
					   if(j<entrustTradeinglist.size()){
						   EntrustTrade entrustTrade= newCreateCancelEntrustTrade(entrustTradeinglist.get(j));
						   MQEnter.pushExEntrustMQ(entrustTrade);
					   }else{
						   return;
					   }
					  
					   j++;
				   }
				   
			   }else{
			     return;
			   }
		   }
		   if(ingCount>exRobot.getBuyDeep()){
			   return;
		   }
		    //3、如果深度不够需要再下
		    Integer kbuy=exRobot.getBuyDeep()-buyMarketDeeped;
		    // 交易币账户
			ExDigitalmoneyAccountRedis coinAccountRedisJyb = getCoinAccountRedis(exRobot.getCustomerId(),exRobot.getCoinCode());
			// 定价币账户
			ExDigitalmoneyAccountRedis coinAccountRedisDjb = getCoinAccountRedis(exRobot.getCustomerId(),exRobot.getFixPriceCoinCode());
			if(kbuy>0){
				//补充新的买单
				for(int k=0;k<kbuy;k++){
					BigDecimal autoPriceSJBuy = new BigDecimal("0");
					BigDecimal autoPriceSJBuyMax=new BigDecimal(currentPrice).multiply(new BigDecimal("100").subtract(exRobot.getBuyOneDiffRate())).divide(new BigDecimal("100"));
					autoPriceSJBuy=getFloatDown(autoPriceSJBuyMax,new BigDecimal("30"))[1];
					if(autoPriceSJBuy.compareTo(new BigDecimal("0"))==0){
						System.out.println("key=" + key+"----currentPrice=="+currentPrice);
						break;
					}
					BigDecimal trueNumSJ=coinAccountRedisDjb.getHotMoney().multiply(exRobot.getEveryEntrustCount()).divide(new BigDecimal("100"))
							.divide(autoPriceSJBuy, 10, BigDecimal.ROUND_DOWN);
					if(trueNumSJ.compareTo(exRobot.getStopLine())==1){
						trueNumSJ=getFloatDown(exRobot.getStopLine(),new BigDecimal(50))[1];
					}
					EntrustTrade exEntrustBuy = addExEntrust(exRobot.getBuyOneDiffRate(),exRobot.getFixPriceType(), 1, exRobot.getCustomerId(), autoPriceSJBuy,autoUsername, key, trueNumSJ, "cny", "cn");
					MQEnter.pushExEntrustMQ(exEntrustBuy);
				}
			}
	     
	  }

	  public void sellEntrust(ExRobotDeep exRobot,String autoUsername,List<EntrustTrade> listing){
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		   String key = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode();
			String currentPrice = TradeRedis.getStringData(key+ ":" + ExchangeDataCacheRedis.CurrentExchangPrice);
			if(null==currentPrice){
				currentPrice= redisService.get(key + ":thirdApi:currentExchangPrice");
			}
		   //1\撤单
			ArrayList<EntrustTrade> entrustTradeinglist=new ArrayList<EntrustTrade>();
			int ingCount=0;
			if(null==listing){
				listing=new ArrayList<EntrustTrade>();
			}
			for(EntrustTrade ing:listing){
				if(ing.getType().intValue()==2){
					 if(ing.getEntrustPrice().compareTo(new BigDecimal(currentPrice).multiply((new BigDecimal("100").add(exRobot.getSellOneDiffRate())).divide(new BigDecimal("100"))))==-1){
						 //撤单
						 EntrustTrade entrustTrade= newCreateCancelEntrustTrade(ing);
						 MQEnter.pushExEntrustMQ(entrustTrade);
					 }else{
				    	 entrustTradeinglist.add(ing);
				    	 ingCount++;
				     }
			     }
			}	
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		   //2、深度已经够了不需要再下
		   Integer sellMarketDeep= sellMarketDeep(key);
		   if(sellMarketDeep>=exRobot.getSellDeep()){
			   Integer cancelcount=ingCount-(exRobot.getSellDeep()-(sellMarketDeep-ingCount));
			   if(cancelcount>0){
				   int j=0;
				   while(j<cancelcount){
					   if(j<entrustTradeinglist.size()){
						   EntrustTrade entrustTrade= newCreateCancelEntrustTrade(entrustTradeinglist.get(j));
						   MQEnter.pushExEntrustMQ(entrustTrade);
					   }else{
						   return;
					   }
					  
					   j++;
				   }
				   
			   }else{
			     return;
			   }
		   }
		   if(ingCount>exRobot.getSellDeep()*3){
			   return;
		   }
		    //3、如果深度不够需要再下
		    Integer ksell=exRobot.getSellDeep()-sellMarketDeep;
		    // 交易币账户
			ExDigitalmoneyAccountRedis coinAccountRedisJyb = getCoinAccountRedis(exRobot.getCustomerId(),exRobot.getCoinCode());
			// 定价币账户
			ExDigitalmoneyAccountRedis coinAccountRedisDjb = getCoinAccountRedis(exRobot.getCustomerId(),exRobot.getFixPriceCoinCode());
			if(ksell>0){
				//补充新的买单
				for(int k=0;k<ksell;k++){
					BigDecimal autoPriceSJSell = new BigDecimal("0");
					BigDecimal autoPriceSJSellMin=new BigDecimal(currentPrice).multiply(new BigDecimal("100").add(exRobot.getSellOneDiffRate())).divide(new BigDecimal("100"));
					
					autoPriceSJSell=getFloatUp(autoPriceSJSellMin,new BigDecimal("30"))[1];
					if(autoPriceSJSell.compareTo(new BigDecimal("0"))==0){
						System.out.println("key=" + key+"----currentPrice=="+currentPrice);
						break;
					}
					BigDecimal trueNumSJ=coinAccountRedisJyb.getHotMoney().multiply(exRobot.getEveryEntrustCount()).divide(new BigDecimal("100"));
					//BigDecimal trueNumSJ = new BigDecimal("1");
					if(trueNumSJ.multiply(autoPriceSJSell).compareTo(new BigDecimal("1000000000"))==1){
						 trueNumSJ=coinAccountRedisDjb.getHotMoney().multiply(exRobot.getEveryEntrustCount()).divide(new BigDecimal("100"))
								.divide(autoPriceSJSell, 10, BigDecimal.ROUND_DOWN);
					}
					if(trueNumSJ.compareTo(exRobot.getStopLine())==1){
						trueNumSJ=getFloatDown(exRobot.getStopLine(),new BigDecimal(50))[1];
					}
					EntrustTrade exEntrustSell = addExEntrust(exRobot.getSellOneDiffRate(),exRobot.getFixPriceType(), 2, exRobot.getCustomerId(), autoPriceSJSell,autoUsername, key, trueNumSJ, "cny", "cn");
					
					MQEnter.pushExEntrustMQ(exEntrustSell);
				}
			}
	    
	 }
		public BigDecimal[] getFloatDown(BigDecimal number, BigDecimal floatNum) {
			BigDecimal[] price = new BigDecimal[2];
			BigDecimal truePrice = number;
			// 随便给个数字，让其compare不等于0
			price[0] = new BigDecimal(2);
			// 获取浮动值 刷币价格 * (浮动比例 * 随机小数 )
			double num = Math.random();// 获取随机小数
			BigDecimal fudongNum = number.multiply(floatNum).divide(new BigDecimal("100")).multiply(new BigDecimal(num));

			// 这里向下浮动吧
			truePrice = number.subtract(fudongNum);
			price[0] = new BigDecimal(-1); // 向下
			price[1] = truePrice;
			if (null == truePrice) {
				System.out.println("truePrice=" + truePrice);
			}
			return price;
		}
		public BigDecimal[] getFloatUp(BigDecimal number, BigDecimal floatNum) {
			BigDecimal[] price = new BigDecimal[2];
			BigDecimal truePrice = number;
			// 随便给个数字，让其compare不等于0
			price[0] = new BigDecimal(2);
			// 获取浮动值 刷币价格 * (浮动比例 * 随机小数 )
			double num = Math.random();// 获取随机小数
			BigDecimal fudongNum = number.multiply(floatNum).divide(new BigDecimal("100")).multiply(new BigDecimal(num));

	       // 这里向上浮动吧
			truePrice = number.add(fudongNum);
			price[0] = new BigDecimal(1); // 向上
			price[1] = truePrice;
			if (null == truePrice) {
				System.out.println("truePrice=" + truePrice);
			}
			return price;
		}
	  public Integer buyMarketDeep(String coinkey){
		  
		    coinkey=coinkey + ":buy";
			RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
			Set<String> keys = redisTradeService.noPerkeys(coinkey + ":");
		    if(null==keys){
		    	return 0;
		    }else{
		    	return keys.size();
		    }
		 
		  
	  }
	  public Integer sellMarketDeep(String coinkey){
		  
		    coinkey=coinkey + ":sell";
			RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
			Set<String> keys = redisTradeService.noPerkeys(coinkey + ":");
		    if(null==keys){
		    	return 0;
		    }else{
		    	return keys.size();
		    }
		 
		  
	}
	  public Boolean isTobSellDeep(String coinkey,Integer sellDeep){
		  
		    coinkey=coinkey + ":sell";
			RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
			Set<String> keys = redisTradeService.noPerkeys(coinkey + ":");
		    if(null==keys){
		    	return false;
		    }
		    if(keys.size()<sellDeep){
		    	return false;
		    }else{
		    	return true;
		    }
		  
	}

	 
	  public Boolean[] isCanEntrustBykline(String coinkey){
		  Boolean[] ar=new Boolean[2];
		  ar[0]=true;
		  ar[1]=true;
		  //买
		  List<TransactionOrder>  list= get1MinKLine(coinkey);
		  if(null==list||list.size()<3){
			  ar[0]=true;
			  ar[1]=true;
			  return ar;
		  }
		  if(list.size()==3){
			  if(list.get(0).getStartPrice().compareTo(list.get(0).getEndPrice())==1
						&&list.get(1).getStartPrice().compareTo(list.get(1).getEndPrice())==1
						&&list.get(2).getStartPrice().compareTo(list.get(2).getEndPrice())==1
					){
				  ar[0]=false;
			  }
			  
		  }
	      if(list.size()>=4){
	    	  int i=0;
	    	  int k=0;
	    	  while(i<4){
	    		  if(list.get(i).getStartPrice().compareTo(list.get(i).getEndPrice())==1)k++;
	    		  i++;
	            }
			  if(k>=3){
				  ar[0]=false;
			  }else{
				  ar[0]=true;
			  }
		  }
		  //卖
	      if(list.size()==3){
			  if(list.get(0).getStartPrice().compareTo(list.get(0).getEndPrice())==-1
						&&list.get(1).getStartPrice().compareTo(list.get(1).getEndPrice())==-1
						&&list.get(2).getStartPrice().compareTo(list.get(2).getEndPrice())==-1
					){
				  ar[1]=false;
			  }
			  
		  }
	      if(list.size()>=4){
	    	  int i=0;
	    	  int k=0;
	    	  while(i<4){
	    		  if(list.get(i).getStartPrice().compareTo(list.get(i).getEndPrice())==-1)k++;
	    		  i++;
	            }
			  if(k>=3){
				  ar[1]=false;
			  }else{
				  ar[1]=true;
			  }
	      }
		  
		  return ar;
		  
	  }
	  public List<TransactionOrder>  get1MinKLine(String coinkey){
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		  String table = coinkey+":klinedata:TransactionOrder_" + coinkey + "_" + 1;
		  List<TransactionOrder> periodKData = JSON.parseArray(redisService.get(table), TransactionOrder.class);
		  return periodKData;
	  }
	  public EntrustTrade newCreateCancelEntrustTrade(EntrustTrade ing){
		   EntrustTrade entrustTrade=new EntrustTrade();
		   entrustTrade.setEntrustNum(ing.getEntrustNum());
		   entrustTrade.setCoinCode(ing.getCoinCode());
		   entrustTrade.setType(ing.getType());
		   entrustTrade.setFixPriceCoinCode(ing.getFixPriceCoinCode());
		   entrustTrade.setEntrustPrice(ing.getEntrustPrice());
		   return entrustTrade;
	  }
		public BigDecimal[] getFloatNum1(BigDecimal number, BigDecimal floatNum) {
			BigDecimal[] price = new BigDecimal[2];
			BigDecimal truePrice = number;
			// 随便给个数字，让其compare不等于0
			price[0] = new BigDecimal(2);
			// 获取浮动值 刷币价格 * (浮动比例 * 随机小数 )
			double num = Math.random();// 获取随机小数
			BigDecimal fudongNum = number.multiply(floatNum).divide(new BigDecimal("100")).multiply(new BigDecimal(num));

			int number1 = (int) (Math.random() * 2);
			if (number1 == 0) {// 这里向下浮动吧
				truePrice = number.subtract(fudongNum);
				price[0] = new BigDecimal(-1); // 向下
			} else {// 这里向上浮动吧
				truePrice = number.add(fudongNum);
				price[0] = new BigDecimal(1); // 向上
			}
			price[1] = truePrice;
			if (null == truePrice) {
				System.out.println("truePrice=" + truePrice);
			}
			return price;
		}

		public EntrustTrade addExEntrust(BigDecimal oneDiffRate,Integer fixPriceType, Integer type, Long customerId, BigDecimal price,
				String autoUsername, String coinCode, BigDecimal entrustCount, String currencyType, String website) {

			long start1 = System.currentTimeMillis();
			EntrustTrade exEntrust = new EntrustTrade();
			String[] rtd = coinCode.split("_");
			if (rtd.length == 1) {
				return null;
			} else {
				exEntrust.setFixPriceCoinCode(rtd[1]);
				exEntrust.setCoinCode(rtd[0]);
			}
			exEntrust.setFixPriceType(fixPriceType);
			exEntrust.setType(type);
			if (null == price || null == entrustCount) {
				System.out.println(exEntrust.getCoinCode() + "===" + exEntrust.getFixPriceCoinCode() + "==" + type);
				return null;
			}
			if (price.compareTo(new BigDecimal(0)) <= 0 || entrustCount.compareTo(new BigDecimal(0)) <= 0) {
				return null;
			}

			Integer[] keepDec = getCoinToCoinKeep(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode());
			int keepDecimalForCoin = keepDec[1];
			int keepDecimalForCurrency = keepDec[0];

			// double num = Math.random();// 获取随机小数
			double num = 0.6;
			if (num > 0.5) {
				exEntrust.setEntrustWay(1);// 1.限价--> 表示以固定的价格 , 2.市价--->

				exEntrust.setEntrustPrice(price.setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_DOWN));
				exEntrust.setEntrustCount(entrustCount.setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_DOWN));
			} else {
				exEntrust.setEntrustWay(2);// 1.限价--> 表示以固定的价格 , 2.市价--->
				if (exEntrust.getType().intValue() == 1) {
					exEntrust.setEntrustSum(price.multiply(entrustCount));
					exEntrust.setEntrustSum(
							exEntrust.getEntrustSum().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_DOWN));

					exEntrust.setEntrustCount(new BigDecimal("0"));
					exEntrust.setEntrustPrice(new BigDecimal("0"));
				} else {
					// 卖

					exEntrust.setEntrustCount(entrustCount.setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_DOWN));
					exEntrust.setEntrustSum(new BigDecimal("0"));
					exEntrust.setEntrustPrice(new BigDecimal("0"));
				}
			}

			exEntrust.setCustomerId(customerId);
			// exEntrust.setUserCode(userCode);
			// exEntrust.setCustomerType(1);
			// exEntrust.setMatchPriority(5);
			// exEntrust.setCurrencyType(currencyType);
			// exEntrust.setWebsite(website);
			exEntrust.setSource(2);
			exEntrust.setUserName(autoUsername);
			exEntrust.setTrueName("深度机器人");
			exEntrust.setOneDiffRate(oneDiffRate);
			ExEntrustService exEntrustService = (ExEntrustService) ContextUtil.getBean("exEntrustService");
			initExEntrust(exEntrust);

			return exEntrust;
		}

		/**
		 * 根据浮动比例获取浮动值
		 * <p>
		 * TODO
		 * </p>
		 *
		 * @author: Zhang Lei
		 * @param: @param
		 *             number 固定值
		 * @param: @param
		 *             floatNum 浮动比例
		 * @param: @return
		 * @return: BigDecimal
		 * @Date : 2017年2月10日 下午2:57:24
		 * @throws:
		 */

		public BigDecimal getPrcie(Integer atuoPriceType, BigDecimal upFloatPer, String coinCode, String fixPriceCoinCode,
				BigDecimal number, BigDecimal floatNum) {
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
			if (atuoPriceType.intValue() == 1) { // 1按定价浮动2，按市价浮动3按第三方价格下单
				return getFloatNum1(number, floatNum)[1];
			} else if (atuoPriceType.intValue() == 2) {

				String currentPrice = TradeRedis.getStringData(
						coinCode + "_" + fixPriceCoinCode + ":" + ExchangeDataCacheRedis.CurrentExchangPrice);
				BigDecimal priceend = null;
				if (!StringUtil.isEmpty(currentPrice)) {
					BigDecimal[] price = getFloatNum1(new BigDecimal(currentPrice), floatNum);
					if (price[0].compareTo(new BigDecimal(1)) == 0) {
						priceend = price[1].multiply(upFloatPer);
					} else {
						priceend = price[1];
					}
					BigDecimal[] isOver = isOver(priceend, coinCode, fixPriceCoinCode);
					if (isOver[0].compareTo(new BigDecimal("1")) == 0) { // 超过了涨幅
						priceend = isOver[1].multiply(new BigDecimal(0.99));
					} else if (isOver[0].compareTo(new BigDecimal("2")) == 0) { // 超过了跌幅
						priceend = isOver[1].multiply(new BigDecimal(1.01));

					}
					return priceend;
				} else {

					return null;
				}

			} else if (atuoPriceType.intValue() == 3) {
				String currentPricestr = redisService
						.get(coinCode + "_" + fixPriceCoinCode + ":thirdApi:currentExchangPrice");
				if (!StringUtil.isEmpty(currentPricestr)) {
					return getFloatNum1(new BigDecimal(currentPricestr), floatNum)[1];
				} else {

					return null;
				}

			}
			return null;
		}

		public void initExEntrust(EntrustTrade exEntrust) {
			Coin productCommon = getCoinFromreds(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode());
			String saasId = PropertiesUtils.APP.getProperty("app.saasId");
			if (null == exEntrust.getEntrustTime()) {
				exEntrust.setEntrustTime(new Date());
			}

			exEntrust.setEntrustNum(IdGenerate.transactionNum(NumConstant.Ex_Entrust));
			// String transactionNum =
			// IdGenerate.transactionNum(NumConstant.Ex_Entrust);
			String robotIsFee = PropertiesUtils.APP.getProperty("app.robotIsFee");
			if (exEntrust.getType() == 1) {
				exEntrust.setEntrustNum("WB" + UUID.randomUUID());
				if (!StringUtil.isEmpty(robotIsFee) && robotIsFee.equals("true")) {
					exEntrust.setTransactionFeeRate(
							null == productCommon.getBuyFeeRate() ? new BigDecimal("0") : productCommon.getBuyFeeRate());
				} else {
					exEntrust.setTransactionFeeRate(new BigDecimal("0"));
				}

			} else {
				exEntrust.setEntrustNum("WS" + UUID.randomUUID());
				if (!StringUtil.isEmpty(robotIsFee) && robotIsFee.equals("true")) {
					exEntrust.setTransactionFeeRate(
							null == productCommon.getSellFeeRate() ? new BigDecimal("0") : productCommon.getSellFeeRate());
				} else {
					exEntrust.setTransactionFeeRate(new BigDecimal("0"));
				}
			}
			if (!StringUtil.isEmpty(robotIsFee) && robotIsFee.equals("true")) {
				/*
				 * AppPersonInfo
				 * appPersonInfo=commonDao.getAppPersonInfoBycustomerId(exEntrust.
				 * getCustomerId());
				 * exEntrust.setIsOpenCoinFee(appPersonInfo.getIsOpenCoinFee());
				 */
				if (null != exEntrust.getIsOpenCoinFee() && exEntrust.getIsOpenCoinFee().intValue() == 1) {
					String coinFeeDiscount = getBasefinanceConfigByKey("coinFeeDiscount");
					String platCoin = getBasefinanceConfigByKey("platCoin");
					if(!StringUtil.isEmpty(coinFeeDiscount)&&!StringUtil.isEmpty(platCoin)){
						exEntrust.setTransactionFeeRateDiscount(coinFeeDiscount.toString());
						exEntrust.setPlatCoin(platCoin);
					}else{
						exEntrust.setIsOpenCoinFee(0);
					}
				} else {
					exEntrust.setIsOpenCoinFee(0);
				}
			} else {

				exEntrust.setIsOpenCoinFee(0);
			}
			exEntrust.setTransactionFeePlat(new BigDecimal("0"));
			exEntrust.setTransactionFee(new BigDecimal("0"));
			exEntrust.setCustomerId(exEntrust.getCustomerId());
			UserRedis userRedis = UserRedisInitUtil.getUserRedis(exEntrust.getCustomerId());

			// 获得缓存中所有的币账户id
			if (exEntrust.getFixPriceType().equals(0)) { // 真实货币
				exEntrust.setAccountId(userRedis.getAccountId());
			} else {
				exEntrust.setAccountId(userRedis.getDmAccountId(exEntrust.getFixPriceCoinCode()));
			}
			exEntrust.setCoinAccountId(userRedis.getDmAccountId(exEntrust.getCoinCode()));
			/*
			 * exEntrust.setAccountId(Long.valueOf("22434"));
			 * exEntrust.setCoinAccountId(Long.valueOf("52687"));
			 */
			exEntrust.setStatus(0);
			exEntrust.setTransactionSum(
					null == exEntrust.getTransactionSum() ? new BigDecimal("0") : exEntrust.getTransactionSum());
			exEntrust.setEntrustSum((null != exEntrust.getEntrustPrice() && null != exEntrust.getEntrustCount())
					&& !new BigDecimal("0").equals(exEntrust.getEntrustPrice())
					&& !new BigDecimal("0").equals(exEntrust.getEntrustCount())
							? exEntrust.getEntrustPrice().multiply(exEntrust.getEntrustCount())
							: exEntrust.getEntrustSum());
			exEntrust.setEntrustCount(
					null == exEntrust.getEntrustCount() ? new BigDecimal("0") : exEntrust.getEntrustCount());
			exEntrust.setSurplusEntrustCount(exEntrust.getEntrustCount());
			if (null == exEntrust.getEntrustCount()) {
				exEntrust.setEntrustCount(new BigDecimal("0"));
			}
			if (null == exEntrust.getEntrustPrice()) {
				exEntrust.setEntrustPrice(new BigDecimal("0"));
			}

			if (null == exEntrust.getEntrustWay()) {

				exEntrust.setEntrustWay(1);// 默认限价
			}
			if (null == exEntrust.getFloatDownPrice()) {
				exEntrust.setFloatDownPrice(new BigDecimal("0"));
				if (exEntrust.getEntrustWay() == 1 && exEntrust.getType() == 1) {
					exEntrust.setFloatDownPrice(exEntrust.getEntrustPrice());
				}
				if (exEntrust.getEntrustWay() == 1 && exEntrust.getType() == 2) {
					exEntrust.setFloatUpPrice((new BigDecimal("999999")));
				}
			}
			if (null == exEntrust.getFloatUpPrice()) {

				exEntrust.setFloatUpPrice(new BigDecimal("0"));
			}

		}
		public String getBasefinanceConfigByKey(String key) {
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
			String val="";
			String string=redisService.get(StringConstant.CONFIG_CACHE+":basefinanceConfig");
			JSONArray obj=JSON.parseArray(string);
			for(Object o:obj){
			JSONObject	 oo=JSON.parseObject(o.toString());
			if(oo.getString("configkey").equals(key)){
				val=oo.getString("value");
			}
			}
			return val;
		}
		public String getFinanceByKey(String key) {
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
			String val = "";
			String string = redisService.get(StringConstant.CONFIG_CACHE + ":financeConfig");
			JSONArray obj = JSON.parseArray(string);
			for (Object o : obj) {
				JSONObject oo = JSON.parseObject(o.toString());
				if (oo.getString("configkey").equals(key)) {
					val = oo.getString("value");
				}
			}
			return val;
		}

		public BigDecimal[] isOver(BigDecimal price, String coinCode, String fixPriceCoinCode) {
			BigDecimal[] isOver = new BigDecimal[2];
			BigDecimal yesterdayPrice = getYearDayPrice(coinCode, fixPriceCoinCode);
			Coin productCommon = getCoinFromreds(coinCode, fixPriceCoinCode);
			if (productCommon != null) {
				if (productCommon.getRose() != null

				) {
					BigDecimal rose = productCommon.getRose();// 涨幅
					BigDecimal zhangjia = yesterdayPrice.multiply(
							(new BigDecimal("100").add(rose)).divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_DOWN));
					if (zhangjia.compareTo(price) < 0) {
						isOver[0] = new BigDecimal("1");
						isOver[1] = zhangjia;
						return isOver;
					}
				}
				if (productCommon.getDecline() != null

						&& null != yesterdayPrice) {
					BigDecimal rose = productCommon.getDecline();
					BigDecimal diejia = yesterdayPrice.multiply((new BigDecimal("100").subtract(rose))
							.divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_DOWN));
					if (diejia.compareTo(price) > 0) {
						isOver[0] = new BigDecimal("2");
						isOver[1] = diejia;
					}
				}

			}
			isOver[0] = new BigDecimal("0");
			return isOver;
		}

		public BigDecimal getYearDayPrice(String coinCode1, String fixPriceCoinCode) {
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
			// 昨日收盘价
			String coinStr = redisService.get("cn:coinInfoList2");
			String coinCode = coinCode1 + "_" + fixPriceCoinCode;
			BigDecimal yesterdayPrice = null;
			if (!StringUtils.isEmpty(coinStr)) {
				List<Coin2> coins = JSON.parseArray(coinStr, Coin2.class);
				for (Coin2 c : coins) {
					if (coinCode.equals(c.getCoinCode() + "_" + c.getFixPriceCoinCode())) {
						if (!StringUtils.isEmpty(c.getYesterdayPrice())) {
							yesterdayPrice = new BigDecimal(c.getYesterdayPrice());

						}
					}
				}
			}
			return yesterdayPrice;

		}

		public Coin getCoinFromreds(String coinCode, String fixPriceCoinCode) {
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
			String str = redisService.get("cn:coinInfoList");
			if (!StringUtils.isEmpty(str)) {
				List<Coin> coins = JSON.parseArray(str, Coin.class);
				if (coins != null && coins.size() > 0) {
					for (Coin coin : coins) {
						if (coinCode.equals(coin.getCoinCode()) && fixPriceCoinCode.equals(coin.getFixPriceCoinCode())) {
							return coin;
						}
					}
				}
			}

			System.out.println("cn:coinInfoList为空，机器人没手续费");
			return null;

		}
		
		public BigDecimal getTime() {
			BigDecimal[] a = getFloatNum1(new BigDecimal("1500"), new BigDecimal("90"));
			return a[1];
		}

		public Integer[] getCoinToCoinKeep(String coinCode, String fixPriceCoinCode) {

			int keepDecimalForCoin = 4;
			int keepDecimalForCurrency = 4;

			CoinKeepDecimal coinKeepDecimal = null;
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
			String str = redisService.get("cn:coinInfoList");
			if (!StringUtils.isEmpty(str)) {
				List<CoinKeepDecimal> coins = JSON.parseArray(str, CoinKeepDecimal.class);
				if (coins != null && coins.size() > 0) {
					for (CoinKeepDecimal coin : coins) {
						if (coinCode.equals(coin.getCoinCode()) && fixPriceCoinCode.equals(coin.getFixPriceCoinCode())) {
							coinKeepDecimal = coin;
						}
					}
				}
			}
			if (null != coinKeepDecimal) {
				keepDecimalForCoin = coinKeepDecimal.getKeepDecimalForCoin();
				keepDecimalForCurrency = coinKeepDecimal.getKeepDecimalForCurrency();
			}
			Integer[] keepDec = new Integer[2];
			keepDec[0] = keepDecimalForCurrency;
			keepDec[1] = keepDecimalForCoin;

			return keepDec;
		}

		/**
		 * 获得当前币种账户信息
		 * 
		 * @param userId
		 * @param coinCode
		 * @return
		 */
		public ExDigitalmoneyAccountRedis getCoinAccountRedis(Long userId, String coinCode) {
			ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");
			
			// 获取个人可用币总数
			RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
			UserRedis userRedis = redisUtil.get(userId.toString());
			if(null!=userRedis){
				// 获得缓存中所有的币账户id
				Map<String, Long> map = userRedis.getDmAccountId();
				Set<String> keySet = map.keySet();

				ExDigitalmoneyAccountRedis dmAccount = null;
				for (String key : keySet) {
					if (key.equals(coinCode)) {
						RedisUtil<ExDigitalmoneyAccountRedis> a = new RedisUtil<ExDigitalmoneyAccountRedis>(
								ExDigitalmoneyAccountRedis.class);
						dmAccount = a.get(userRedis.getDmAccountId(key).toString());
					}
				}
				return dmAccount;
			}else{
				QueryFilter qf=new QueryFilter(ExDigitalmoneyAccount.class);
				qf.addFilter("customerId=", userId);
				ExDigitalmoneyAccount exDigitalmoneyAccount=exDigitalmoneyAccountService.find(qf).get(0);
				ExDigitalmoneyAccountRedis coinaccount = exDigitalmoneyAccountService.getExDigitalmoneyAccountByRedis(exDigitalmoneyAccount.getId().toString());
				return coinaccount;
			}
			
		}
	
}
