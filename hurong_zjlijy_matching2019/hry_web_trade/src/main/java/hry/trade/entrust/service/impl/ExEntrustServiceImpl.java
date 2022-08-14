/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.user.model.AppCustomer;
import hry.exchange.product.model.ExCointoCoin;
import hry.front.redis.model.UserRedis;
import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.MQmanager.MQEnter;
import hry.trade.entrust.dao.CommonDao;
import hry.trade.entrust.dao.ExEntrustDao;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.init.TradeInitData;
import hry.trade.model.Coin;
import hry.trade.model.CoinKeepDecimal;
import hry.trade.model.TradeRedis;
import hry.trade.mq.service.MessageProducer;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.trade.websoketContext.model.MarketDepths;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Gao Mimi
 * @Date : 2016年4月12日 下午4:45:50
 */
@Service("exEntrustService")
public class ExEntrustServiceImpl extends BaseServiceImpl<ExEntrust, Long> implements ExEntrustService {

	@Resource(name = "exEntrustDao")
	@Override
	public void setDao(BaseDao<ExEntrust, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private ExOrderInfoService exOrderInfoService;
	@Resource
	private ExOrderService exOrderService;
	@Resource
	public RedisService redisService;
	@Resource
	public CommonDao commonDao;
	@Resource
	public ExEntrustDao exEntrustDao;


	@Override
	public void tradeInit() {
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");

		redisTradeService.delkeys(":buy:");
		redisTradeService.delkeys(":sell:");
		//	redisService.delkeys(":EntrustByUser:");
		// 初始化交易数据==========================================================================================
		List<ExCointoCoin> listExCointoCoin = commonDao.getExointocoinAll();
		for (ExCointoCoin exCointoCoin : listExCointoCoin) {
			String header = this.getHeader(exCointoCoin.getCoinCode(), exCointoCoin.getFixPriceCoinCode());
			// 买
			QueryFilter filterbuy = new QueryFilter(ExEntrust.class);
			filterbuy.addFilter("status<", 2);
			filterbuy.addFilter("type=", 1);
			filterbuy.addFilter("coinCode=", exCointoCoin.getCoinCode());
			filterbuy.addFilter("fixPriceCoinCode=", exCointoCoin.getFixPriceCoinCode());
			filterbuy.setOrderby("entrustPrice desc");
			List<ExEntrust> listbuy = this.find(filterbuy);
			List<EntrustTrade> listbuypricesame = new ArrayList<EntrustTrade>();
			String buyonePricekey = header + ":" + ExchangeDataCacheRedis.BuyOnePrice; // 买一
			if (null != listbuy && listbuy.size() > 0) {
				BigDecimal sameprice = listbuy.get(0).getEntrustPrice();
				EntrustTrade entrustTrade = null;
				for (ExEntrust l : listbuy) {
					String exentrust = JSON.toJSONString(l);
					entrustTrade = JSON.parseObject(exentrust, EntrustTrade.class);
					if (l.getEntrustPrice().compareTo(sameprice) == 0) {
						listbuypricesame.add(entrustTrade);
					} else {
						String key = getHeader(entrustTrade) + ":buy:" + sameprice.setScale(10, BigDecimal.ROUND_HALF_EVEN).toString();
						redisTradeService.save(key, JSON.toJSONString(listbuypricesame));
						listbuypricesame = new ArrayList<EntrustTrade>();
						listbuypricesame.add(entrustTrade);
						sameprice = l.getEntrustPrice();
					}
				}
				String key = header + ":buy:" +sameprice.setScale(10, BigDecimal.ROUND_HALF_EVEN).toString();
				redisTradeService.save(key, JSON.toJSONString(listbuypricesame));


				redisService.save(buyonePricekey, JSON.toJSONString( listbuy.get(0).getEntrustPrice()));
			}else{
				redisService.delete(buyonePricekey);
			}


			// 卖
			QueryFilter filtersell = new QueryFilter(ExEntrust.class);
			filtersell.addFilter("status<", 2);
			filtersell.addFilter("type=", 2);
			filtersell.addFilter("coinCode=", exCointoCoin.getCoinCode());
			filtersell.addFilter("fixPriceCoinCode=", exCointoCoin.getFixPriceCoinCode());
			filtersell.setOrderby("entrustPrice asc");
			List<ExEntrust> listsell = this.find(filtersell);
			List<EntrustTrade> listsellpricesame = new ArrayList<EntrustTrade>();
			String sellonePricekey = header + ":" + ExchangeDataCacheRedis.SellOnePrice; // 对手单的卖一
			if (null != listsell && listsell.size() > 0) {
				BigDecimal sameprice = listsell.get(0).getEntrustPrice();
				EntrustTrade entrustTrade = null;
				for (ExEntrust l : listsell) {
					String exentrust = JSON.toJSONString(l);
					entrustTrade = JSON.parseObject(exentrust, EntrustTrade.class);
					if (l.getEntrustPrice().compareTo(sameprice) == 0) {
						listsellpricesame.add(entrustTrade);
					} else {
						String key = getHeader(entrustTrade) + ":sell:" +sameprice.setScale(10, BigDecimal.ROUND_HALF_EVEN).toString();
						redisTradeService.save(key, JSON.toJSONString(listsellpricesame));
						listsellpricesame = new ArrayList<EntrustTrade>();
						listsellpricesame.add(entrustTrade);
						sameprice = l.getEntrustPrice();
					}

				}

				String key = header + ":sell:" + sameprice.setScale(10, BigDecimal.ROUND_HALF_EVEN).toString();
				redisTradeService.save(key, JSON.toJSONString(listsellpricesame));

				redisService.save(sellonePricekey, JSON.toJSONString( listsell.get(0).getEntrustPrice()));
			}else{
				redisService.delete(sellonePricekey);
			}


		}
		//初始化到redis成功
		TradeInitData.setIsInit(true);

	}
	@Override
	public void cancelAutoAddExEntrust(){

		Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
			List<ExCointoCoin> listExCointoCoin = commonDao.getExointocoinValid();
			for (ExCointoCoin exCointoCoin : listExCointoCoin) {
				String autoUsernames = exCointoCoin.getAutoUsername();
				Long customerId = exCointoCoin.getCustomerId();
				Integer isSratAuto = exCointoCoin.getIsSratAuto();
				Integer isFromChbtc = exCointoCoin.getIsFromChbtc();


				if (isSratAuto.equals(1)) {
					String[] autoUsernameArr=null;
					if (null == customerId) {
						autoUsernameArr=autoUsernames.split(",");
					}
					if(null==autoUsernameArr){break;}
					for(String autoUsername:autoUsernameArr){
						if (null == customerId) {
							AppCustomer customer = commonDao.getAppUserByuserName(autoUsername);
							if (null == autoUsername) {
								System.out.println("填写的手机号有误，请检查重试！");
								break;
							} else {
								customerId = customer.getId();
								exCointoCoin.setCustomerId(customerId);
							}
						}
						if (isFromChbtc.equals(0)) {

							EntrustTrade entrustTrade=new EntrustTrade();
							entrustTrade.setCustomerId(customerId);
							entrustTrade.setCoinCode(exCointoCoin.getCoinCode());
							entrustTrade.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
							entrustTrade.setCancelKeepN(10);
							// 序列化
							String str = JSON.toJSONString(entrustTrade);
							MessageProducer messageProducer =(MessageProducer)ContextUtil.getBean("messageProducer");
							// 发送消息
							messageProducer.toTrade(str);

						} else {

						}
					}
				}
			}

		}


	}

	public  static int entrustType=1;

	public BigDecimal getTime(){
		BigDecimal[] a=getFloatNum1(new BigDecimal("1500"),new BigDecimal("90"));
		return a[1];
	}

	public Integer[] getCoinToCoinKeep(String coinCode,String fixPriceCoinCode){

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
		Integer[] keepDec=new Integer[2];
		keepDec[0]=keepDecimalForCurrency;
		keepDec[1]=keepDecimalForCoin;

		return keepDec;
	}

	/**
	 * 自动刷单
	 */
	@Override
	public void autoAddExEntrust() {
		long sleeptiem=getTime().longValue();

		try {
			Thread.sleep(sleeptiem);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
			List<ExCointoCoin> listExCointoCoin = commonDao.getExointocoinValid();
			int max=50;// 最多能走50个机器人，1秒1单那就是总共1秒10单
			int maxjs=1;
			for (ExCointoCoin exCointoCoin : listExCointoCoin) {

				if(maxjs>max){
					if(entrustType==1){
						// 生成刷单价格1
						entrustType=2;
					}else{
						entrustType=1;
					}
					return;
				}
				String autoUsernames = exCointoCoin.getAutoUsername();
				Long customerId = exCointoCoin.getCustomerId();
				BigDecimal autoCount = exCointoCoin.getAutoCount();
				BigDecimal autoCountFloat = exCointoCoin.getAutoCountFloat();
				BigDecimal autoPrice = exCointoCoin.getAutoPrice();
				BigDecimal autoPriceFloat = exCointoCoin.getAutoPriceFloat();
				Integer isSratAuto = exCointoCoin.getIsSratAuto();
				Integer isFromChbtc = exCointoCoin.getIsFromChbtc();
				Integer fixPriceType = exCointoCoin.getFixPriceType();
				if (isSratAuto.equals(1)) {
					String[] autoUsernameArr=null;
					if (null == customerId) {
						autoUsernameArr=autoUsernames.split(",");
					}
					if(null==autoUsernameArr){break;}
					for(String autoUsername:autoUsernameArr){

						AppCustomer customer = commonDao.getAppUserByuserName(autoUsername);
						if (null == autoUsername) {
							System.out.println("填写的手机号有误，请检查重试！");
							break;
						} else {
							customerId = customer.getId();
							exCointoCoin.setCustomerId(customerId);
						}
						maxjs++;
						if (isFromChbtc.equals(0)) {
							// Coin
							// productCommon=productCommonService.getProductCommon(exCointoCoin.getCoinCode(),
							// exCointoCoin.getFixPriceCoinCode());

							for (int i = 0; i < 1; i++) {
								if(entrustType==1){
									// 生成刷单价格1
									BigDecimal buyautoPrice = getPrcie(exCointoCoin.getAtuoPriceType(),exCointoCoin.getUpFloatPer(),
											exCointoCoin.getCoinCode(),exCointoCoin.getFixPriceCoinCode(),autoPrice, autoPriceFloat);
									//		buyautoPrice = buyautoPrice.setScale(8, BigDecimal.ROUND_HALF_DOWN);
									// 生成刷单数量1
									BigDecimal buytrueNum = getFloatNum1(autoCount, autoCountFloat)[1];
									//	buytrueNum = buytrueNum.setScale(8, BigDecimal.ROUND_HALF_DOWN);
									String coinCodebuy = exCointoCoin.getCoinCode() + "_" + exCointoCoin.getFixPriceCoinCode();
									addExEntrust(fixPriceType, 1, customerId, buyautoPrice, autoUsername, coinCodebuy, buytrueNum, "cny", "cn");
								}else{
									// 生成刷单价格2
									BigDecimal sellautoPrice = getPrcie(exCointoCoin.getAtuoPriceType(),exCointoCoin.getUpFloatPer(),
											exCointoCoin.getCoinCode(),exCointoCoin.getFixPriceCoinCode(),autoPrice, autoPriceFloat);
									//	sellautoPrice = sellautoPrice.setScale(8, BigDecimal.ROUND_HALF_DOWN);
									// 生成刷单数量2
									BigDecimal selltrueNum = getFloatNum1(autoCount, autoCountFloat)[1];
									//		selltrueNum = selltrueNum.setScale(8, BigDecimal.ROUND_HALF_DOWN);
									String coinCodesell = exCointoCoin.getCoinCode() + "_" + exCointoCoin.getFixPriceCoinCode();
									addExEntrust(fixPriceType, 2, customerId, sellautoPrice, autoUsername, coinCodesell, selltrueNum, "cny", "cn");
								}


							}

						} else {

						}
					}


				}
			}
		}



		if(entrustType==1){
			// 生成刷单价格1
			entrustType=2;
		}else{
			entrustType=1;
		}

		//	System.out.println("entrustType=="+entrustType);

	}

	/**
	 * 调用该方法
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @author: Zhang Lei
	 * @param: @param
	 *             type 买还是卖类型 1 买 2 卖
	 * @param: @param
	 *             customerId 用户ID
	 * @param: @param
	 *             price 价格（机器人随机浮动范围内生成）
	 * @param: @param
	 *             userCode 用户code
	 * @param: @param
	 *             coinCode 币种
	 * @param: @param
	 *             entrustCount 数量
	 * @param: @param
	 *             currencyType 固定cny
	 * @param: @param
	 *             website 固定cn
	 * @return: void
	 * @Date : 2017年2月9日 下午4:25:01
	 * @throws:
	 */
	@Override
	public String addExEntrust(Integer fixPriceType, Integer type, Long customerId, BigDecimal price, String autoUsername, String coinCode, BigDecimal entrustCount, String currencyType, String website) {
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
		if(null==price||null==entrustCount){
			System.out.println(exEntrust.getCoinCode()+"==="+exEntrust.getFixPriceCoinCode()+"=="+type);
			return "";
		}
		if(price.compareTo(new BigDecimal(0))<=0 || entrustCount.compareTo(new BigDecimal(0))<=0){
			return "";
		}

		Integer[] keepDec= getCoinToCoinKeep(exEntrust.getCoinCode(),exEntrust.getFixPriceCoinCode());
		int keepDecimalForCoin = keepDec[1];
		int keepDecimalForCurrency =keepDec[0];

		//	double num = Math.random();// 获取随机小数
		double num = 0.6;
		if(num>0.5){
			exEntrust.setEntrustWay(1);// 1.限价--> 表示以固定的价格 , 2.市价--->
			exEntrust.setEntrustPrice(price.setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_DOWN));
			exEntrust.setEntrustCount(entrustCount.setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_DOWN));
		}else{
			exEntrust.setEntrustWay(2);// 1.限价--> 表示以固定的价格 , 2.市价--->
			if(exEntrust.getType().intValue()==1){
				exEntrust.setEntrustSum(price.multiply(entrustCount));
				exEntrust.setEntrustSum(exEntrust.getEntrustSum().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_DOWN));

				exEntrust.setEntrustCount(new BigDecimal("0"));
				exEntrust.setEntrustPrice(new BigDecimal("0"));
			}else{
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
		exEntrust.setTransactionFeePlat(new BigDecimal("0"));
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
		if(null!=exEntrust.getIsOpenCoinFee()&&exEntrust.getIsOpenCoinFee().intValue()==1){
			String coinFeeDiscount=getFinanceByKey("coinFeeDiscount");
			String platCoin=getFinanceByKey("platCoin");
			exEntrust.setTransactionFeeRateDiscount(coinFeeDiscount.toString());
			exEntrust.setPlatCoin(platCoin);

		}else{
			exEntrust.setIsOpenCoinFee(0);

		}
	}
	public String getFinanceByKey(String key) {
		String val="";
		String string=redisService.get(StringConstant.CONFIG_CACHE+":financeConfig");
		JSONArray obj=JSON.parseArray(string);
		for(Object o:obj){
			JSONObject	 oo=JSON.parseObject(o.toString());
			if(oo.getString("configkey").equals(key)){
				val=oo.getString("value");
			}
		}
		return val;
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

	public  BigDecimal getPrcie(Integer atuoPriceType,BigDecimal upFloatPer,String coinCode,String fixPriceCoinCode,BigDecimal number, BigDecimal floatNum){
		if(atuoPriceType.intValue()==1){	//1按定价浮动2，按市价浮动
			return getFloatNum1(number,floatNum)[1];
		}else{

			String  currentPrice=	TradeRedis.getStringData(coinCode + "_" + fixPriceCoinCode + ":" + ExchangeDataCacheRedis.CurrentExchangPrice);
			if(!StringUtil.isEmpty(currentPrice)){
				BigDecimal[] price=getFloatNum1(new BigDecimal(currentPrice),floatNum);
				if(price[0].compareTo(new BigDecimal(1))==0){
					return price[1].multiply(upFloatPer);
				}else{
					return price[1];
				}
			}else{

				return null;
			}

		}
	}

	public BigDecimal[] getFloatNum1(BigDecimal number, BigDecimal floatNum) {
		BigDecimal[] price=new  BigDecimal[2];
		BigDecimal truePrice = number;
		// 获取浮动值 刷币价格 * (浮动比例 * 随机小数 )
		double num = Math.random();// 获取随机小数
		BigDecimal fudongNum = number.multiply(floatNum).divide(new BigDecimal("100")).multiply(new BigDecimal(num));

		int number1 = (int) (Math.random() * 2);
		if (number1 == 0) {// 这里向下浮动吧
			truePrice = number.subtract(fudongNum);
			price[0]=new BigDecimal(-1); //向下
		} else {// 这里向上浮动吧
			truePrice = number.add(fudongNum);
			price[0]=new BigDecimal(1); //向上
		}
		price[1]=truePrice;
		if(null==truePrice){
			System.out.println("truePrice="+truePrice);
		}
		return price;
	}

	@Override
	public String getHeader(String coinCode, String fixPriceCoinCode) {
		String header = coinCode + "_" + fixPriceCoinCode;
		return header;
	}

	@Override
	public String getHeader(EntrustTrade exEntrust) {
		String header = exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode();
		return header;
	}

	@Override
	public String getHeader(ExOrderInfo exEntrust) {
		String header = exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode();
		return header;
	}

	@Override
	public ExEntrust getExEntrustByentrustNum(String entrustNum) {
		QueryFilter filter = new QueryFilter(ExEntrust.class);
		filter.addFilter("entrustNum=", entrustNum);
		filter.setSaasId("hurong_system");
		return this.get(filter);

	}

	@Override
	public String[] getExEntrustChangeMarket(String coinCode, String fixPriceCoinCode,Integer n,Integer n100) {
		MarketDepths marketDepths = new MarketDepths();
		Map<String, List<BigDecimal[]>> map = new HashMap<String, List<BigDecimal[]>>();

		MarketDepths marketDepths100 = new MarketDepths();
		Map<String, List<BigDecimal[]>> map100 = new HashMap<String, List<BigDecimal[]>>();
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

		List<BigDecimal[]> bids = new ArrayList<BigDecimal[]>();
		List<BigDecimal[]> bids100 = new ArrayList<BigDecimal[]>();
		EntrustTrade sellexEntrust = new EntrustTrade();
		sellexEntrust.setCoinCode(coinCode);
		sellexEntrust.setFixPriceCoinCode(fixPriceCoinCode);
		sellexEntrust.setType(2);
		List<BigDecimal> keyslistbuy = TradeRedis.getMatchkeysmarket(sellexEntrust);// 查所有的keys
		int i = 0;
		while (i < keyslistbuy.size()) {



			BigDecimal keybig = keyslistbuy.get(i);
			String keyall = TradeRedis.getHeaderMatch(sellexEntrust) + ":" + keybig.toString();
			List<EntrustTrade> list = TradeRedis.getMatchEntrustTradeBykey(keyall);
			BigDecimal entrustPrice = new BigDecimal("0");
			BigDecimal surplusEntrustCount = new BigDecimal("0");
			if (null != list) {
				for (EntrustTrade entrustTrade : list) {
					entrustPrice = entrustTrade.getEntrustPrice();
					surplusEntrustCount = surplusEntrustCount.add(entrustTrade.getSurplusEntrustCount());
				}
				BigDecimal[] array = new BigDecimal[2];
				array[0] = entrustPrice.setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN);
				array[1] = surplusEntrustCount.setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN);
				if (i <= n){
					bids.add(array);
				}
				bids100.add(array);
			}

			if (i == n100)
				break;
			i++;
		}
		map100.put("bids", bids100);
		map.put("bids", bids);
		if (keyslistbuy.size() > 0) {
			String keybuy = TradeRedis.getHeaderFront(sellexEntrust) + ":" + ExchangeDataCacheRedis.BuyOnePrice;
			String v = redisService.get(keybuy);
			if (StringUtil.isEmpty(v)) {
				redisService.save(keybuy, JSON.toJSONString(bids.get(0)[0]));
			}
		}
		// 卖单
		List<BigDecimal[]> asks = new ArrayList<BigDecimal[]>();
		List<BigDecimal[]> asks100 = new ArrayList<BigDecimal[]>();
		EntrustTrade byllexEntrust = new EntrustTrade();
		byllexEntrust.setCoinCode(coinCode);
		byllexEntrust.setFixPriceCoinCode(fixPriceCoinCode);
		byllexEntrust.setType(1);
		List<BigDecimal> keyslistsell = TradeRedis.getMatchkeysmarket(byllexEntrust);// 查所有的keys
		int k = 0;
		while (k < keyslistsell.size()) {
			BigDecimal keybig = keyslistsell.get(k);
			String keyall = TradeRedis.getHeaderMatch(byllexEntrust) + ":" + keybig.toString();
			List<EntrustTrade> list = TradeRedis.getMatchEntrustTradeBykey(keyall);
			BigDecimal entrustPrice = new BigDecimal("0");
			BigDecimal surplusEntrustCount = new BigDecimal("0");
			if (null != list) {
				for (EntrustTrade entrustTrade : list) {
					entrustPrice = entrustTrade.getEntrustPrice();
					surplusEntrustCount = surplusEntrustCount.add(entrustTrade.getSurplusEntrustCount());
				}
				BigDecimal[] array = new BigDecimal[2];
				array[0] = entrustPrice.setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN);
				array[1] = surplusEntrustCount.setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN);
				if(k <= n){
					asks.add(array);
				}
				asks100.add(array);
			}
			if (k == n100)
				break;
			k++;
		}
		map100.put("asks", asks100);
		map.put("asks", asks);
		if (keyslistsell.size() > 0) {
			String keysell = TradeRedis.getHeaderFront(byllexEntrust) + ":" + ExchangeDataCacheRedis.SellOnePrice;
			String v = redisService.get(keysell);
			if (StringUtil.isEmpty(v)) {
				redisService.save(keysell, JSON.toJSONString(asks.get(0)[0]));
			}

		}
		marketDepths.setDepths(map);
		marketDepths100.setDepths(map100);
		String[] st=new String[2];
		st[0]=JSON.toJSONString(marketDepths).toString();
		st[1]=JSON.toJSONString(marketDepths100).toString();
		return st;
	}

	public BigDecimal getByn(int n) {
		BigDecimal bd = new BigDecimal(1);
		for (int i = 0; i < n; i++) {
			bd = bd.multiply(new BigDecimal(10));
		}
		return bd;
	}

	@Override
	public String getExEntrustChangeDephMarket(String coinCode, String fixPriceCoinCode, Integer n, BigDecimal jj) {
		MarketDepths marketDepths = new MarketDepths();
		Map<String, List<BigDecimal[]>> map = new HashMap<String, List<BigDecimal[]>>();
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
		int keepDecimalForCurrencysubone = keepDecimalForCurrency - 1;
		if (keepDecimalForCurrencysubone < 0) {
			keepDecimalForCurrencysubone = 0;
		}
		BigDecimal depth = new BigDecimal(1).divide(getByn(keepDecimalForCurrencysubone), keepDecimalForCurrencysubone, BigDecimal.ROUND_DOWN);
		depth = depth.multiply(jj);
		List<BigDecimal[]> bids = new ArrayList<BigDecimal[]>();
		EntrustTrade sellexEntrust = new EntrustTrade();
		sellexEntrust.setCoinCode(coinCode);
		sellexEntrust.setFixPriceCoinCode(fixPriceCoinCode);
		sellexEntrust.setType(2);
		List<BigDecimal> keyslistbuy = TradeRedis.getMatchkeysmarket(sellexEntrust);// 查所有的keys
		if (null != keyslistbuy && keyslistbuy.size() > 0) {
			BigDecimal maxPrice = new BigDecimal(keyslistbuy.get(0).toString());
			if (maxPrice.compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal startPrice = maxPrice.setScale(depth.scale(), BigDecimal.ROUND_DOWN);
				startPrice = startPrice.subtract(depth);
				int i = 0;
				int flag = 0;
				for (int j = 0; j < 5; j++) {
					if (j > 0) {
						startPrice = startPrice.subtract(depth);
					}
					if (flag == 1) {
						break;
					}
					if (startPrice.compareTo(new BigDecimal(0)) <= 0) {
						flag = 1;
						startPrice = new BigDecimal(0);
					}
					BigDecimal surplusEntrustCount = new BigDecimal("0");
					while (i < keyslistbuy.size()) {
						BigDecimal keybig = keyslistbuy.get(i);
						if (keybig.compareTo(startPrice) >= 0) {
							String keyall = TradeRedis.getHeaderMatch(sellexEntrust) + ":" + keybig.toString();
							List<EntrustTrade> list = TradeRedis.getMatchEntrustTradeBykey(keyall);
							if (null != list) {
								for (EntrustTrade entrustTrade : list) {
									surplusEntrustCount = surplusEntrustCount.add(entrustTrade.getSurplusEntrustCount());
								}
							}
							i++;
						} else {
							break;
						}

					}

					BigDecimal[] array = new BigDecimal[2];
					array[0] = startPrice.setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN);
					array[1] = surplusEntrustCount.setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN);
					bids.add(array);
				}
			}
		}
		map.put("bids", bids);

		// 卖单
		List<BigDecimal[]> asks = new ArrayList<BigDecimal[]>();
		EntrustTrade byllexEntrust = new EntrustTrade();
		byllexEntrust.setCoinCode(coinCode);
		byllexEntrust.setFixPriceCoinCode(fixPriceCoinCode);
		byllexEntrust.setType(1);
		List<BigDecimal> keyslistsell = TradeRedis.getMatchkeysmarket(byllexEntrust);// 查所有的keys
		if (null != keyslistsell && keyslistsell.size() > 0) {
			BigDecimal minPrice = new BigDecimal(keyslistsell.get(0).toString());
			if (minPrice.compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal startPrice = minPrice.setScale(depth.scale(), BigDecimal.ROUND_DOWN);
				startPrice = startPrice.add(depth);
				int k = 0;
				for (int j = 0; j < 5; j++) {
					if (j > 0) {
						startPrice = startPrice.add(depth);
					}
					BigDecimal surplusEntrustCount = new BigDecimal("0");
					while (k < keyslistsell.size()) {
						BigDecimal keybig = keyslistsell.get(k);
						if (keybig.compareTo(startPrice) < 1) {
							String keyall = TradeRedis.getHeaderMatch(byllexEntrust) + ":" + keybig.toString();
							List<EntrustTrade> list = TradeRedis.getMatchEntrustTradeBykey(keyall);
							if (null != list) {
								for (EntrustTrade entrustTrade : list) {
									surplusEntrustCount = surplusEntrustCount.add(entrustTrade.getSurplusEntrustCount());
								}
							}
							k++;
						} else {
							break;
						}

					}

					BigDecimal[] array = new BigDecimal[2];
					array[0] = startPrice.setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN);
					array[1] = surplusEntrustCount.setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN);
					asks.add(array);
				}
			}

		}
		map.put("asks", asks);
		marketDepths.setDepths(map);
		return JSON.toJSONString(marketDepths).toString();
	}

}
