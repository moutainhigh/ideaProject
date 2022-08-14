package hry.trade.robot.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import hry.core.constant.StringConstant;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.MQmanager.MQEnter;
import hry.trade.account.service.ExDigitalmoneyAccountService;
import hry.trade.entrust.dao.CommonDao;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.TradeService;
import hry.trade.entrust.service.impl.UserRedisInitUtil;
import hry.trade.model.Coin;
import hry.trade.model.CoinKeepDecimal;
import hry.trade.model.TradeRedis;
import hry.trade.mq.service.MessageProducer;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.trade.robot.model.Coin2;
import hry.trade.robot.model.ExRobot;
import hry.trade.robot.service.RobotKlineService;
import hry.trade.robot.service.RobotService;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tk.mybatis.mapper.util.StringUtil;

@Service("robotKlineService")
public class RobotKlineServiceImpl implements RobotKlineService {
	@Resource
	public CommonDao commonDao;
	@Resource
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	private TradeService tradeService;
	public BigDecimal getTime() {
		BigDecimal[] a = getFloatNum1(new BigDecimal("1500"), new BigDecimal("90"));
		return a[1];
	}

	public Integer[] getCoinToCoinKeep(String coinCode, String fixPriceCoinCode,Jedis jedis) {

		int keepDecimalForCoin = 4;
		int keepDecimalForCurrency = 4;

		CoinKeepDecimal coinKeepDecimal = null;
		String str = jedis.get("cn:coinInfoList");
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

	/**
	 * 自动刷单
	 */
	@Override
	public void klineAutoAddExEntrust4() {
		long start=System.currentTimeMillis();
		System.out.println("进入k线机器人");
		System.out.println("量的基数也做个变化");
        if(culcount<12){
            culcount++;
        }else{
            culcount=0;
        }
        BigDecimal fl=getFloat();

		BigDecimal baseCountFloat=getBaseCount();//基础下单量一分钟变一次
		long sleeptiem = getTime().longValue();

		try {
			Thread.sleep(sleeptiem);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<ExRobot> listExCointoCoin = commonDao.getExRobotList(1);
		int max = 60;// 最多能走10个机器人，1秒1单那就是总共1秒10单
		int maxjs = 1;
		for (ExRobot exRobot : listExCointoCoin) {
			if (maxjs > max) {
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
					JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
					Jedis jedis=jedisPool.getResource();
					try {
						for (int i = 0; i < 1; i++) {
							//下单基准量为0就不下单
							if(null==exRobot.getAutoCount()||exRobot.getAutoCount().compareTo(new BigDecimal("0"))==0){
								System.out.println("下单量上线为空或者为0");
								return;
							}
							BigDecimal autoPriceSJ = getPrcie(exRobot.getAtuoPriceType(), exRobot.getUpFloatPer(),
									exRobot.getCoinCode(), exRobot.getFixPriceCoinCode(), autoPrice, autoPriceFloat,jedis);
							if (null != autoPriceSJ) {
								// 生成刷单数量2
							//	BigDecimal trueNumSJ = getFloatNum1(autoCount, autoCountFloat)[1];
								String coinCodeSJ = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode();
								// 交易币账户
								ExDigitalmoneyAccountRedis coinAccountRedisJyb = getCoinAccountRedis(customerId,exRobot.getCoinCode());
								// 定价币账户
								ExDigitalmoneyAccountRedis coinAccountRedisDjb = getCoinAccountRedis(customerId,exRobot.getFixPriceCoinCode());
								BigDecimal trueNumSJ = getFloatNum2(exRobot.getAutoCount().multiply(baseCountFloat), new BigDecimal("99"))[1];
								double  double1 =Math.random();

								if (double1 <0.5) {// 这里向下浮动吧下卖单
									// 卖
									BigDecimal autoPriceSJSell =getSellEntrustPrice(autoPriceSJ,fl);
									EntrustTrade exEntrustSell = addExEntrust(fixPriceType, 2, customerId, autoPriceSJSell,autoUsername, coinCodeSJ, trueNumSJ, "cny", "cn",jedis);
									//	if (null!=exEntrustSell&&exEntrustSell.getEntrustCount().compareTo(coinAccountRedisJyb.getHotMoney()) == -1) {
									exEntrustSell.setAppointOpponentType(1);
									if(exEntrustSell.getEntrustPrice().compareTo(new BigDecimal("0"))>0){
										tradeService.appointMatchExtrust(exEntrustSell,jedis);
									}else{

										//	System.out.println("coinCodeSJ=" + coinCodeSJ+"----exEntrustBuy.getEntrustPrice()=="+exEntrustSell.getEntrustPrice());
									}

									//	}
								} else {
									// 买
									BigDecimal autoPriceSJBuy = getBuyEntrustPrice(autoPriceSJ,fl);
									EntrustTrade exEntrustBuy = addExEntrust(fixPriceType, 1, customerId, autoPriceSJBuy,autoUsername, coinCodeSJ, trueNumSJ, "cny", "cn",jedis);
									//	if (null!=exEntrustBuy&&exEntrustBuy.getEntrustSum().compareTo(coinAccountRedisDjb.getHotMoney()) == -1)
									exEntrustBuy.setAppointOpponentType(1);
									if(exEntrustBuy.getEntrustPrice().compareTo(new BigDecimal("0"))>0){
										tradeService.appointMatchExtrust(exEntrustBuy,jedis);
									}else{
										//System.out.println("coinCodeSJ=" + coinCodeSJ+"----exEntrustBuy.getEntrustPrice()=="+exEntrustBuy.getEntrustPrice());
									}

								}

							}


						}

					}finally {
						jedis.close();
					}
				}

			}

		}

		long end=System.currentTimeMillis();
		System.out.println("k线机器人执行的时间"+(end -start));
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
    public static int culcount=0;
    public static BigDecimal culcountFloat=new BigDecimal("0");
    public BigDecimal getFloat(){
        BigDecimal fl = culcountFloat;
        if(culcount==0){
            double numberSell = Math.random();

            if (numberSell < 0.33) {// 这里向下浮动吧
                fl=new BigDecimal("0.01");
            } else if (numberSell < 0.66) {
                fl=new BigDecimal("0.02");
            } else if (numberSell <= 1) {
                fl=new BigDecimal("0.03");
            }
            culcountFloat=fl;
        }else{
            fl=culcountFloat;
        }
        System.out.println("fl=" + fl+"---culcount="+culcount);
        return fl;
    }
	public static BigDecimal baseCountFloat1=new BigDecimal(Math.random());
    public BigDecimal getBaseCount(){
		BigDecimal fl = baseCountFloat1;
		if(culcount==0){
			fl=new BigDecimal(Math.random());
			baseCountFloat1=fl;
		}else{
			fl=baseCountFloat1;
		}
		return fl;
	}
    public BigDecimal getBuyEntrustPrice(BigDecimal autoPriceSJ,BigDecimal fl){


        autoPriceSJ=getFloatUp(autoPriceSJ,fl)[1];
        return autoPriceSJ;
    }

    public BigDecimal getSellEntrustPrice(BigDecimal autoPriceSJ,BigDecimal fl){
        BigDecimal autoPriceSJSell = new BigDecimal("0");

        //return autoPriceSJSell;
        autoPriceSJ=getFloatDown(autoPriceSJ,fl)[1];
        return autoPriceSJ;
    }

	//向上的概率高一点
	public BigDecimal[] getFloatNum3(BigDecimal number, BigDecimal floatNum) {
		BigDecimal[] price = new BigDecimal[2];
		BigDecimal truePrice = number;
		// 随便给个数字，让其compare不等于0
		price[0] = new BigDecimal(2);
		// 获取浮动值 刷币价格 * (浮动比例 * 随机小数 )
		double num = Math.random();// 获取随机小数
		BigDecimal fudongNum = number.multiply(floatNum).divide(new BigDecimal("100")).multiply(new BigDecimal(num));
		double num1 = Math.random();// 决定向上还是向下浮动
		if (num1 < 0.46) {// 这里向下浮动吧
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
	//只向下浮动
	public BigDecimal[] getFloatNum2(BigDecimal number, BigDecimal floatNum) {
		BigDecimal[] price = new BigDecimal[2];
		BigDecimal truePrice = number;
		// 随便给个数字，让其compare不等于0
		price[0] = new BigDecimal(2);
		// 获取浮动值 刷币价格 * (浮动比例 * 随机小数 )
		double num = Math.random();// 获取随机小数
		BigDecimal fudongNum = number.multiply(floatNum).divide(new BigDecimal("100")).multiply(new BigDecimal(num));

		// int number1 = (int) (Math.random() * 2);
		int number1 = 0;
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
	public EntrustTrade addExEntrust(Integer fixPriceType, Integer type, Long customerId, BigDecimal price,
									 String autoUsername, String coinCode, BigDecimal entrustCount, String currencyType, String website,Jedis jedis) {

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

		Integer[] keepDec = getCoinToCoinKeep(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode(),jedis);
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
		exEntrust.setTrueName("机器人");
		ExEntrustService exEntrustService = (ExEntrustService) ContextUtil.getBean("exEntrustService");
		initExEntrust(exEntrust,jedis);

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
							   BigDecimal number, BigDecimal floatNum,Jedis jedis) {
		if (atuoPriceType.intValue() == 1) { // 1按定价浮动2，按市价浮动3按第三方价格下单
			return getFloatNum1(number, floatNum)[1];
		} else if (atuoPriceType.intValue() == 2) {
	/*		String currentPrice = TradeRedis.getStringData(
					coinCode + "_" + fixPriceCoinCode + ":" + ExchangeDataCacheRedis.CurrentExchangPrice);
			BigDecimal priceend = null;
		
			if (!StringUtil.isEmpty(currentPrice)) {
				
				BigDecimal[] price = getFloatNum3(new BigDecimal(currentPrice), new BigDecimal("0.05")); //下单价格浮动写死
				if (price[0].compareTo(new BigDecimal(1)) == 0) {
					upFloatPer=((upFloatPer.subtract(new BigDecimal(1))).multiply(new BigDecimal("0.1"))).add(upFloatPer);
					priceend = price[1].multiply(upFloatPer);
				} else {
					priceend = price[1];
				}
				
				priceend = price[1];
				BigDecimal[] isHalfOver = isOver(priceend, coinCode, fixPriceCoinCode);
				if (isHalfOver[0].compareTo(new BigDecimal("2")) == 0) { // 超过了跌幅一半
					priceend = isHalfOver[1].multiply(new BigDecimal(1.01));

				} 
				
				BigDecimal[] isOver = isOver(priceend, coinCode, fixPriceCoinCode);
				if (isOver[0].compareTo(new BigDecimal("1")) == 0) { // 超过了涨幅
					priceend = isOver[1].multiply(new BigDecimal(0.99));
					System.out.println("超过了涨幅:"+isOver[1]);
				} else if (isOver[0].compareTo(new BigDecimal("2")) == 0) { // 超过了跌幅
					priceend = isOver[1].multiply(new BigDecimal(1.01));

				}
				
				return priceend;
			}*/








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

				//如果价格不再买一卖一之间也不行，就随机再买一卖一之间找个值
				String keyFront = coinCode + "_" + fixPriceCoinCode;
				String buyonePricekey = keyFront + ":" + ExchangeDataCacheRedis.BuyOnePrice; // 买一
				String sellonePricekey = keyFront + ":" + ExchangeDataCacheRedis.SellOnePrice; // 卖一
				String buyonePriceStr=TradeRedis.getStringData(buyonePricekey);
				String sellonePriceStr=TradeRedis.getStringData(sellonePricekey);
				if(StringUtil.isNotEmpty(buyonePriceStr)&&StringUtil.isNotEmpty(sellonePriceStr)){

					BigDecimal buyonePrice=  new BigDecimal(buyonePriceStr);
					BigDecimal sellonePrice=  new BigDecimal(sellonePriceStr);
					if(priceend.compareTo(buyonePrice)==-1||priceend.compareTo(sellonePrice)==1){
						 priceend=(buyonePrice.add(sellonePrice)).divide(new BigDecimal(2),10,BigDecimal.ROUND_DOWN);

					}
				}

                //不能超过涨跌幅
				BigDecimal[] isOver = isOver(priceend, coinCode, fixPriceCoinCode,jedis);
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
			String currentPricestr =jedis
					.get(coinCode + "_" + fixPriceCoinCode + ":thirdApi:currentExchangPrice");
			if (!StringUtil.isEmpty(currentPricestr)) {
				return new BigDecimal(currentPricestr);
			} else {

				return null;
			}

		}
		return null;
	}

	public void initExEntrust(EntrustTrade exEntrust,Jedis jedis) {
		Coin productCommon = getCoinFromreds(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode(),jedis);
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
	public String getFinanceByKey(String key,Jedis jedis) {
		String val = "";
		String string = jedis.get(StringConstant.CONFIG_CACHE + ":financeConfig");
		JSONArray obj = JSON.parseArray(string);
		for (Object o : obj) {
			JSONObject oo = JSON.parseObject(o.toString());
			if (oo.getString("configkey").equals(key)) {
				val = oo.getString("value");
			}
		}
		return val;
	}
	public BigDecimal[] isHalfOver(BigDecimal price, String coinCode, String fixPriceCoinCode,Jedis jedis) {
		BigDecimal[] isOver = new BigDecimal[2];
		BigDecimal yesterdayPrice = getYearDayPrice(coinCode, fixPriceCoinCode,jedis);
		Coin productCommon = getCoinFromreds(coinCode, fixPriceCoinCode,jedis);
		if (productCommon != null) {
			if (productCommon.getRose() != null

			) {
				BigDecimal rose = productCommon.getRose().multiply(new BigDecimal(0.3));// 涨幅一半
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
				BigDecimal rose = productCommon.getDecline().multiply(new BigDecimal(0.3));// 涨幅一半;
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
	public BigDecimal[] isOver(BigDecimal price, String coinCode, String fixPriceCoinCode,Jedis jedis) {
		BigDecimal[] isOver = new BigDecimal[2];
		BigDecimal yesterdayPrice = getYearDayPrice(coinCode, fixPriceCoinCode,jedis);
		Coin productCommon = getCoinFromreds(coinCode, fixPriceCoinCode,jedis);
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
					return isOver;
				}
			}

		}
		isOver[0] = new BigDecimal("0");
		return isOver;
	}

	public BigDecimal getYearDayPrice(String coinCode1, String fixPriceCoinCode,Jedis jedis) {

		// 昨日收盘价
		String coinStr = jedis.get("cn:coinInfoList2");
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

	public Coin getCoinFromreds(String coinCode, String fixPriceCoinCode,Jedis jedis) {

		String str = jedis.get("cn:coinInfoList");
		if (!StringUtils.isEmpty(str)) {
			List<Coin> coins = JSON.parseArray(str, Coin.class);
			if (coins != null && coins.size() > 0) {
				for (Coin coin : coins) {
					if (coinCode.equals(coin.getCoinCode()) && fixPriceCoinCode.equals(coin.getFixPriceCoinCode())) {
						return coin;
					}
					if(coin.getRose().compareTo(new BigDecimal("20"))==1){
						coin.setRose(new BigDecimal("20"));
					}
					if(coin.getDecline().compareTo(new BigDecimal("20"))==1){
						coin.setDecline(new BigDecimal("20"));

					}
				}
			}
		}

		System.out.println("cn:coinInfoList为空，机器人没手续费");
		return null;

	}
}
