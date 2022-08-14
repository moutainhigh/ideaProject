package hry.trade.robot.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import hry.core.constant.CodeConstant;
import hry.core.mvc.model.page.JsonResult;
import hry.customer.user.model.AppCustomer;
import hry.exchange.product.model.ExCointoCoin;
import hry.front.redis.model.UserRedis;
import hry.redis.common.utils.RedisService;
import hry.trade.MQmanager.MQEnter;
import hry.trade.entrust.dao.CommonDao;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.impl.UserRedisInitUtil;
import hry.trade.model.Coin;
import hry.trade.model.CoinKeepDecimal;
import hry.trade.model.CommenApiUrl;
import hry.trade.model.TradeRedis;
import hry.trade.mq.service.MessageProducer;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.trade.robot.model.Coin2;
import hry.trade.robot.model.ExRobot;
import hry.trade.robot.service.ConmonApiService;
import hry.trade.robot.service.RobotService;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import tk.mybatis.mapper.util.StringUtil;

@Service("robotService")
public class RobotServiceImpl implements RobotService {
	@Resource
	private RedisService redisService;
	@Resource
	public CommonDao commonDao;

	@Override
	public void klineCancelAutoAddExEntrust4() {

		List<ExRobot> listExCointoCoin = commonDao.getExRobotList(1);
		for (ExRobot exRobot : listExCointoCoin) {
			String autoUsernames = exRobot.getAutoUsername();
			Long customerId = exRobot.getCustomerId();
			Integer isSratAuto = exRobot.getIsSratAuto();

			if (isSratAuto.equals(1)) {
				String[] autoUsernameArr = null;
				if (null != autoUsernames) {
					autoUsernameArr = autoUsernames.split(",");
				}
				if (null == autoUsernameArr) {
					break;
				}
				for (String autoUsername : autoUsernameArr) {
					if (null == customerId) {
						AppCustomer customer = commonDao.getAppUserByuserName(autoUsername);
						if (null == autoUsername) {
							System.out.println("填写的手机号有误，请检查重试！");
							break;
						} else {
							customerId = customer.getId();
							exRobot.setCustomerId(customerId);
						}
					}

					EntrustTrade entrustTrade = new EntrustTrade();
					entrustTrade.setCustomerId(customerId);
					entrustTrade.setCoinCode(exRobot.getCoinCode());
					entrustTrade.setFixPriceCoinCode(exRobot.getFixPriceCoinCode());
					entrustTrade.setCancelKeepN(10);
					// 序列化
					String str = JSON.toJSONString(entrustTrade);
					MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
					// 发送消息
					messageProducer.toTrade(str);

				}
			}
		}

	}

	public static int entrustType = 1;

	public BigDecimal getTime() {
		BigDecimal[] a = getFloatNum1(new BigDecimal("1500"), new BigDecimal("90"));
		return a[1];
	}

	public Integer[] getCoinToCoinKeep(String coinCode, String fixPriceCoinCode) {

		int keepDecimalForCoin = 4;
		int keepDecimalForCurrency = 4;

		CoinKeepDecimal coinKeepDecimal = null;
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
	 * 自动刷单
	 */
	@Override
	public void klineAutoAddExEntrust4() {
		long sleeptiem = getTime().longValue();

		try {
			Thread.sleep(sleeptiem);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			List<ExRobot> listExCointoCoin = commonDao.getExRobotList(1);
			int max = 30;// 最多能走10个机器人，1秒1单那就是总共1秒10单
			int maxjs = 1;
			for (ExRobot exRobot : listExCointoCoin) {

				if (maxjs > max) {
					if (entrustType == 1) {
						// 生成刷单价格1
						entrustType = 2;
					} else {
						entrustType = 1;
					}
					return;
				}
				String autoUsernames = exRobot.getAutoUsername();
				Long customerId = exRobot.getCustomerId();
				BigDecimal autoCount = exRobot.getAutoCount();
				BigDecimal autoCountFloat = exRobot.getAutoCountFloat();
				BigDecimal autoPrice = exRobot.getAutoPrice();
				BigDecimal autoPriceFloat = exRobot.getAutoPriceFloat();
				Integer isSratAuto = exRobot.getIsSratAuto();
				Integer fixPriceType = exRobot.getFixPriceType();
				if (isSratAuto.equals(1)) {
					String[] autoUsernameArr = null;
					if (null != autoUsernames) {
						autoUsernameArr = autoUsernames.split(",");
					}
					if (null == autoUsernameArr) {
						break;
					}
					for (String autoUsername : autoUsernameArr) {

						AppCustomer customer = commonDao.getAppUserByuserName(autoUsername);
						if (null == autoUsername) {
							System.out.println("填写的手机号有误，请检查重试！");
							break;
						} else {
							customerId = customer.getId();
							exRobot.setCustomerId(customerId);
						}
						maxjs++;

						for (int i = 0; i < 1; i++) {
						
							if (entrustType == 1) {
								// 生成刷单价格1
								BigDecimal buyautoPrice = getPrcie(exRobot.getAtuoPriceType(), exRobot.getUpFloatPer(),
												exRobot.getCoinCode(), exRobot.getFixPriceCoinCode(), autoPrice,autoPriceFloat);
								
								if(null!=buyautoPrice){
									// buyautoPrice = buyautoPrice.setScale(8, BigDecimal.ROUND_HALF_DOWN);
									// 生成刷单数量1
									BigDecimal buytrueNum = getFloatNum1(autoCount, autoCountFloat)[1];
									// buytrueNum = buytrueNum.setScale(8, BigDecimal.ROUND_HALF_DOWN);
									String coinCodebuy = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode();
									addExEntrust(fixPriceType, 1, customerId, buyautoPrice, autoUsername, coinCodebuy,
											buytrueNum, "cny", "cn");
								}else{
									
									System.out.println(exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode()+"机器人下单价格为空");
									
								}
								
							} else {
								// 生成刷单价格2
								BigDecimal sellautoPrice =getPrcie(exRobot.getAtuoPriceType(), exRobot.getUpFloatPer(),
												exRobot.getCoinCode(), exRobot.getFixPriceCoinCode(), autoPrice,autoPriceFloat);
								if(null!=sellautoPrice){
									// sellautoPrice = sellautoPrice.setScale(8, BigDecimal.ROUND_HALF_DOWN);
									// 生成刷单数量2
									BigDecimal selltrueNum = getFloatNum1(autoCount, autoCountFloat)[1];
									// selltrueNum = selltrueNum.setScale(8,
									// BigDecimal.ROUND_HALF_DOWN);
									String coinCodesell = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode();
									addExEntrust(fixPriceType, 2, customerId, sellautoPrice, autoUsername, coinCodesell,
											selltrueNum, "cny", "cn");
								}
							}

						}

					}

				}
			}

		if (entrustType == 1) {
			// 生成刷单价格1
			entrustType = 2;
		} else {
			entrustType = 1;
		}

		// System.out.println("entrustType=="+entrustType);

	}

	public BigDecimal[] getFloatNum1(BigDecimal number, BigDecimal floatNum) {
		BigDecimal[] price = new BigDecimal[2];
		BigDecimal truePrice = number;
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

	public String addExEntrust(Integer fixPriceType, Integer type, Long customerId, BigDecimal price,
			String autoUsername, String coinCode, BigDecimal entrustCount, String currencyType, String website) {
		long start1 = System.currentTimeMillis();
		EntrustTrade exEntrust = new EntrustTrade();
		String[] rtd = coinCode.split("_");
		if (rtd.length == 1) {
			return "";
		} else {
			exEntrust.setFixPriceCoinCode(rtd[1]);
			exEntrust.setCoinCode(rtd[0]);
		}
		exEntrust.setFixPriceType(fixPriceType);
		exEntrust.setType(type);
		if (null == price || null == entrustCount) {
			System.out.println(exEntrust.getCoinCode() + "===" + exEntrust.getFixPriceCoinCode() + "==" + type);
			return "";
		}
		if (price.compareTo(new BigDecimal(0)) <= 0 || entrustCount.compareTo(new BigDecimal(0)) <= 0) {
			return "";
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
		exEntrust.setTrueName("机器人");
		ExEntrustService exEntrustService = (ExEntrustService) ContextUtil.getBean("exEntrustService");
		initExEntrust(exEntrust);
		MQEnter.pushExEntrustMQ(exEntrust);
		return "";
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
		if (atuoPriceType.intValue() == 1) { // 1按定价浮动2，按市价浮动
			return getFloatNum1(number, floatNum)[1];
		} else if(atuoPriceType.intValue() == 2){

			String currentPrice = TradeRedis.getStringData(
					coinCode + "_" + fixPriceCoinCode + ":" + ExchangeDataCacheRedis.CurrentExchangPrice);
			BigDecimal priceend=null;
			if (!StringUtil.isEmpty(currentPrice)) {
				BigDecimal[] price = getFloatNum1(new BigDecimal(currentPrice), floatNum);
				if (price[0].compareTo(new BigDecimal(1)) == 0) {
					priceend= price[1].multiply(upFloatPer);
				} else {
					priceend= price[1];
				}
				BigDecimal[] isOver= isOver(priceend,coinCode,fixPriceCoinCode);
				if(isOver[0].compareTo(new BigDecimal("1"))==0){ //超过了涨幅
					priceend=isOver[1].multiply(new BigDecimal(0.99));
				}else if(isOver[0].compareTo(new BigDecimal("2"))==0){ //超过了跌幅
					priceend=isOver[1].multiply(new BigDecimal(1.01));
					
				}
			    return priceend;
			} else {

				return null;
			}

		} else if(atuoPriceType.intValue() == 3){
			String currentPricestr=redisService.get(coinCode+"_"+fixPriceCoinCode+":thirdApi:currentExchangPrice");
			if (!StringUtil.isEmpty(currentPricestr)) {
				return new BigDecimal(currentPricestr);
			} else {

				return null;
			}

		}
		return null;
	}

	public void initExEntrust(EntrustTrade exEntrust) {
		Coin productCommon = getCoinFromreds(exEntrust.getCoinCode(),
				exEntrust.getFixPriceCoinCode());
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
			if(!StringUtil.isEmpty(robotIsFee)&&robotIsFee.equals("true")){
				exEntrust.setTransactionFeeRate(
						null == productCommon.getBuyFeeRate() ? new BigDecimal("0") : productCommon.getBuyFeeRate());
			}else{
				exEntrust.setTransactionFeeRate(new BigDecimal("0"));
			}
			
		} else {
			exEntrust.setEntrustNum("WS" + UUID.randomUUID());
			if(!StringUtil.isEmpty(robotIsFee)&&robotIsFee.equals("true")){
				exEntrust.setTransactionFeeRate(
						null == productCommon.getSellFeeRate() ? new BigDecimal("0") : productCommon.getSellFeeRate());
			}else{
				exEntrust.setTransactionFeeRate(new BigDecimal("0"));
			}
		}
	//	exEntrust.setTransactionFeePlat(new BigDecimal("0"));
		exEntrust.setTransactionFee(new BigDecimal("0"));
		exEntrust.setCustomerId(exEntrust.getCustomerId());
		UserRedis userRedis=UserRedisInitUtil.getUserRedis(exEntrust.getCustomerId());
		
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
		exEntrust.setTransactionSum(null == exEntrust.getTransactionSum() ? new BigDecimal("0") : exEntrust.getTransactionSum());
		exEntrust.setEntrustSum((null != exEntrust.getEntrustPrice() && null != exEntrust.getEntrustCount()) && !new BigDecimal("0").equals(exEntrust.getEntrustPrice()) && !new BigDecimal("0").equals(exEntrust.getEntrustCount()) ? exEntrust.getEntrustPrice().multiply(exEntrust.getEntrustCount()) : exEntrust.getEntrustSum());
		exEntrust.setEntrustCount(null == exEntrust.getEntrustCount() ? new BigDecimal("0") : exEntrust.getEntrustCount());
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

	public BigDecimal[] isOver(BigDecimal price,String coinCode,String fixPriceCoinCode){
		BigDecimal[] isOver=new BigDecimal[2]; 
	    BigDecimal 	yesterdayPrice= getYearDayPrice(coinCode,fixPriceCoinCode);
		Coin productCommon = getCoinFromreds(coinCode,fixPriceCoinCode);
		if ( productCommon != null) {
			if ( productCommon.getRose()!=null
					
					) {
				BigDecimal rose = productCommon.getRose();// 涨幅
				BigDecimal zhangjia = yesterdayPrice.multiply((new BigDecimal("100").add(rose)).divide(new BigDecimal(100),8,BigDecimal.ROUND_HALF_DOWN));
				if (zhangjia.compareTo(price) < 0) {
					isOver[0]=new BigDecimal("1");
					isOver[1]=zhangjia;
					return isOver;
				}
			}
			if ( productCommon.getDecline()!=null
				
					&& null!=yesterdayPrice
					) {
				BigDecimal rose = productCommon.getDecline();
				BigDecimal diejia =yesterdayPrice.multiply((new BigDecimal("100").subtract(rose)).divide(new BigDecimal(100),8,BigDecimal.ROUND_HALF_DOWN));
				if (diejia.compareTo(price) > 0) {
					isOver[0]=new BigDecimal("2");
					isOver[1]=diejia;
				}
			}
			

		}
		isOver[0]=new BigDecimal("0");
		return isOver;
	}
	public BigDecimal getYearDayPrice(String coinCode1,String fixPriceCoinCode ){
		
		// 昨日收盘价
		String coinStr = redisService.get("cn:coinInfoList2");
		String coinCode = coinCode1 + "_" + fixPriceCoinCode;
		BigDecimal yesterdayPrice =null;
		if(!StringUtils.isEmpty(coinStr)){
			List<Coin2> coins = JSON.parseArray(coinStr, Coin2.class);
			for(Coin2 c :coins){
				if(coinCode.equals(c.getCoinCode()+"_"+c.getFixPriceCoinCode())){
					if(!StringUtils.isEmpty(c.getYesterdayPrice())){
						yesterdayPrice = new BigDecimal(c.getYesterdayPrice());
						
					}
				}
			}
		}
		return yesterdayPrice;

	}
	public Coin getCoinFromreds(String coinCode, String fixPriceCoinCode){
		
		String str = redisService.get("cn:coinInfoList");
		if(!StringUtils.isEmpty(str)){
			List<Coin> coins = JSON.parseArray(str, Coin.class);
			if(coins!=null&&coins.size()>0){
				for(Coin coin : coins){
					if(coinCode.equals(coin.getCoinCode())&&fixPriceCoinCode.equals(coin.getFixPriceCoinCode())){
						return coin;
					}
				}
			}
		}
		
		System.out.println("cn:coinInfoList为空，机器人没手续费");
		return null;

}
}
