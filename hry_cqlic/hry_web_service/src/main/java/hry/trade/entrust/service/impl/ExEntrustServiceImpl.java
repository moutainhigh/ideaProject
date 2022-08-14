/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.account.fund.model.AppAccount;
import hry.account.remote.RemoteAppAccountService;
import hry.bean.PageResult;
import hry.change.remote.account.RemoteExDigitalmoneyAccountService;
import hry.change.remote.exEntrust.RemoteExExEntrustService;
import hry.core.constant.CodeConstant;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.lend.service.ExDmPingService;
import hry.exchange.product.model.ExCointoCoin;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExCointoCoinService;
import hry.exchange.product.service.ExProductService;
import hry.exchange.product.service.ProductCommonService;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.Coin2;
import hry.manage.remote.model.Entrust;
import hry.manage.remote.model.base.FrontPage;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.MQmanager.MQEnter;
import hry.trade.entrust.DifCustomer;
import hry.trade.entrust.ExEntrustSimple;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.dao.ExEntrustDao;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustMongoService;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.exEntrustOneDay.dao.ExentrustOnedayDao;
import hry.trade.exEntrustOneDay.model.ExentrustOneday;
import hry.trade.exEntrustOneDay.service.ExentrustOnedayService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.websoketContext.PushData;
import hry.trade.websoketContext.model.MarketDepths;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.web.app.model.AppHolidayConfig;
import hry.web.remote.RemoteAppConfigService;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
	private static Logger logger = Logger.getLogger(ExEntrustServiceImpl.class);
	private static final String BUY = "buy_entrust";
	private static final String SELL = "sell_entrust";

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
	private ExEntrustMongoService exEntrustMongoService;
	@Resource
	public RedisService redisService;
	@Resource
	public ProductCommonService productCommonService;
	@Resource
	public ExProductService exProductService;
	@Resource
	private ExentrustOnedayService exentrustOnedayService;
	@Resource
	private ExentrustOnedayDao exentrustOnedayDao;
	@Resource
	private AppCustomerService appCustomerService;
	@Resource
	private ExCointoCoinService exCointoCoinService;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private  ExDmPingService exDmPingService;
	@Override
	public void cancelAllExEntrust(EntrustTrade entrustTrade1) {
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		QueryFilter queryfilter = new QueryFilter(ExEntrust.class);
		queryfilter.addFilter("status<", 2); 
		queryfilter.addFilter("customerId=", entrustTrade1.getCustomerId());
		queryfilter.setSaasId(saasId);
		List<ExEntrust> list = this.find(queryfilter); 
		for (ExEntrust l :list) {
			EntrustTrade entrustTrade=new EntrustTrade();
			entrustTrade.setEntrustNum(l.getEntrustNum());
			entrustTrade.setCoinCode(l.getCoinCode());
			entrustTrade.setType(l.getType());
			entrustTrade.setFixPriceCoinCode(l.getFixPriceCoinCode());
			entrustTrade.setEntrustPrice(BigDecimal.valueOf(Double.parseDouble(l.getEntrustPrice().toString())).stripTrailingZeros());
			this.cancelExEntrust(entrustTrade, "手动全部撤单");
		}


	}
	@Override
	public FrontPage findPage(Map<String, String> params) {
		List<Entrust> list = ((ExEntrustDao) dao).findFrontPageBySql(params);
		return new FrontPage(list, list.size(), Integer.parseInt(params.get("page")), Integer.parseInt(params.get("pageSize")));
	}
	@Override
	public String[] saveExEntrustTrade(EntrustTrade exEntrust) {
		if (null == exEntrust.getEntrustTime()) {
			exEntrust.setEntrustTime(new Date());
		}
		exEntrust.setCustomerId(exEntrust.getCustomerId());
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
		Coin productCommon = productCommonService.getProductCommon(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode());
		if (exEntrust.getType() == 1) {
			exEntrust.setEntrustNum("WB" + UUID.randomUUID().toString().replace("-", ""));
			exEntrust.setTransactionFeeRate(null == productCommon.getBuyFeeRate() ? new BigDecimal("0") : productCommon.getBuyFeeRate());
			exEntrust.setTransactionFee(new BigDecimal("0"));
			exEntrust.setTransactionFeePlat(new BigDecimal("0"));
		} else {
			exEntrust.setEntrustNum("WS" + UUID.randomUUID().toString().replace("-", ""));
			exEntrust.setTransactionFeeRate(null == productCommon.getSellFeeRate() ? new BigDecimal("0") : productCommon.getSellFeeRate());
			exEntrust.setTransactionFee(new BigDecimal("0"));
			exEntrust.setTransactionFeePlat(new BigDecimal("0"));
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
		if(null==exEntrust.getEntrustSum()){
			exEntrust.setEntrustSum(new BigDecimal("0"));
		}
		if(null==exEntrust.getEntrustCount()){
			exEntrust.setEntrustCount(new BigDecimal("0"));
		}
		if(null==exEntrust.getEntrustPrice()){
			exEntrust.setEntrustPrice(new BigDecimal("0"));
		}
		if(null!=exEntrust.getIsOpenCoinFee()&&exEntrust.getIsOpenCoinFee().intValue()==1){
			String coinFeeDiscount=getBasefinanceConfigByKey("coinFeeDiscount");
			String platCoin=getBasefinanceConfigByKey("platCoin");
			if(!StringUtil.isEmpty(coinFeeDiscount)&&!StringUtil.isEmpty(platCoin)){
				exEntrust.setTransactionFeeRateDiscount(coinFeeDiscount.toString());
				exEntrust.setPlatCoin(platCoin);
			}else{
				exEntrust.setIsOpenCoinFee(0);
			}
			

		}else{
			exEntrust.setIsOpenCoinFee(0);

		}

		/*		// 查redis缓存
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(exEntrust.getCustomerId().toString());
		// 获得缓存中所有的币账户id
		if (exEntrust.getFixPriceType().equals(0)) { // 真实货币
			exEntrust.setAccountId(userRedis.getAccountId());
		} else {
		    exEntrust.setAccountId(userRedis.getDmAccountId(exEntrust.getFixPriceCoinCode()));
		}
		exEntrust.setCoinAccountId(userRedis.getDmAccountId(exEntrust.getCoinCode()));*/
		
		return null;
	}
	public String getBasefinanceConfigByKey(String key) {
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

	public  String getRMBCode() {
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String language_code = remoteAppConfigService.getFinanceByKey("language_code");
		return language_code;
	}
	public int culDecimal(String coinCode) {
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		Integer keepDecimalForRmb=4;
		if(coinCode.equals(getRMBCode())){ //如果是法币
			String keepDecimalForRmbs = remoteAppConfigService.getFinanceByKey("keepDecimalForRmb");
			keepDecimalForRmb = Integer.valueOf(keepDecimalForRmbs);
			if (null == keepDecimalForRmb)
				keepDecimalForRmb = 4;
		}else{
			ExProductService exProductService = (ExProductService) ContextUtil.getBean("exProductService");
			ExProduct ex = exProductService.findByallCoinCode(coinCode);
			keepDecimalForRmb = ex.getKeepDecimalForCoin();
			if (null == keepDecimalForRmb)
				keepDecimalForRmb = 4;

		}
		return keepDecimalForRmb;
	}
	@Override
	public String[] saveExEntrust(ExEntrust exEntrust) {
		long start = System.currentTimeMillis();
		// 买家冻结金额entrustSum
		// 卖家冻结比特币entrustCount
		String[] relt = new String[2];
		if (!DifCustomer.isInOpenAndCloseplate()) {
			relt[0] = CodeConstant.CODE_FAILED;
			relt[1] = "已经闭市或还没开市";
		}

		this.initExEntrust(exEntrust);
		if (exEntrust.getEntrustPrice().compareTo(new BigDecimal("0")) == 0 && exEntrust.getEntrustCount().compareTo(new BigDecimal("0")) == 0 && exEntrust.getEntrustSum().compareTo(new BigDecimal("0")) == 0) {
			relt[0] = CodeConstant.CODE_FAILED;
			relt[1] = "都为0";
			return relt;
		}
		if (exEntrust.getEntrustWay() == 1 && exEntrust.getEntrustCount().compareTo(new BigDecimal("0")) == 0 && exEntrust.getEntrustSum().compareTo(new BigDecimal("0")) == 0) {
			relt[0] = CodeConstant.CODE_FAILED;
			relt[1] = "都为0";
			return relt;
		}

		RemoteExDigitalmoneyAccountService remoteExDigitalmoneyAccountService = (RemoteExDigitalmoneyAccountService) ContextUtil.getBean("remoteExDigitalmoneyAccountService");
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		// 虚拟币
		if (exEntrust.getProjectType() == null) {
			if (exEntrust.getType() == 1) {// 如果买
				if (exEntrust.getFixPriceType() == 0) { // 定价为真是货币
					AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
					if (appAccount.getHotMoney().compareTo(exEntrust.getEntrustSum()) == -1) {
						relt[0] = CodeConstant.CODE_FAILED;
						relt[1] = "余额不足";
						return relt;
					}
				} else { // 定价为虚拟货币
					ExDigitalmoneyAccount exDigitalmoneyAccount = remoteExDigitalmoneyAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getFixPriceCoinCode(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
					if (exDigitalmoneyAccount.getHotMoney().compareTo(exEntrust.getEntrustSum()) == -1) {
						relt[0] = CodeConstant.CODE_FAILED;
						relt[1] = "余额不足";
						return relt;
					}

				}

			} else {// 如果卖
				ExDigitalmoneyAccount exDigitalmoneyAccount = remoteExDigitalmoneyAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getCoinCode(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
				if (exDigitalmoneyAccount.getHotMoney().compareTo(exEntrust.getEntrustCount()) == -1) {
					relt[0] = CodeConstant.CODE_FAILED;
					relt[1] = "余额不足";
					return relt;
				}
			}
		}

		try {
			// 委托单放进mq；
			MQEnter.pushExEntrustMQ(exEntrust);
		} catch (Exception e) {
			logger.error("委托放进mq失败");
			// 判断下余额的情况
			relt[0] = CodeConstant.CODE_FAILED;
			relt[1] = "系统错误";
			return relt;
		}

		// 判断下余额的情况
		relt[0] = CodeConstant.CODE_SUCCESS;
		relt[1] = "都为0";
		long end = System.currentTimeMillis();
		logger.error("委托初始化，耗时：" + (end - start));
		return relt;
	}

	// 委托、
	@Override
	public String[] mqsaveExEntrust(ExEntrust exEntrust) {
		String[] relt = new String[2];
		// 根据委托单类型（买、卖）判断账户金额是否满足委托条件、
		// 冻结金额或者币、增加冷热钱包记录
		long start = System.currentTimeMillis();
		relt = this.deductMoney(exEntrust);
		long end = System.currentTimeMillis();
		logger.error("委托mq中11，耗时：" + (end - start));
		if (relt[0].equals(CodeConstant.CODE_SUCCESS)) {
			long start1 = System.currentTimeMillis();
			logger.error("委托单号：" + exEntrust.getEntrustNum());
			// 数据库保存委托单
			this.save(exEntrust);
			// 保存到redis中
			// saveExtrustToRedis(exEntrust);
			// 保存到数据库临时表中
			String entrust = JSON.toJSONString(exEntrust);
			ExentrustOneday oneday = JSON.parseObject(entrust, ExentrustOneday.class);
			exentrustOnedayService.save(oneday);
			long end1 = System.currentTimeMillis();
			logger.error("委托mq中222，耗时：" + (end1 - start1));
			return relt;
		} else {
			return relt;
		}
	}

	@Override
	@Deprecated
	public void saveExtrustToMongo(
			ExEntrust exEntrust) {
	}



	/**
	 * 买家是限价的话，可以匹配的 （一）（1）现价相等的卖家（2）市价的卖家。(3)普通价格优先的卖家 （二） 所有的卖家进行时间的正序排列
	 */
	@Override
	public List<ExentrustOneday> listMatchByBuyLimitedPrice(String saasId, ExEntrust buyexEntrust, String customerType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", buyexEntrust.getId());
		map.put("website", buyexEntrust.getWebsite());
		map.put("currencyType", buyexEntrust.getCurrencyType());
		map.put("coinCode", buyexEntrust.getCoinCode());
		map.put("fixPriceCoinCode", buyexEntrust.getFixPriceCoinCode());
		map.put("lentrustPrice", buyexEntrust.getEntrustPrice().add(buyexEntrust.getFloatUpPrice()));
		map.put("gentrustPrice", buyexEntrust.getEntrustPrice().subtract(buyexEntrust.getFloatDownPrice()));
		return exentrustOnedayDao.listMatchByBuyLimitedPrice(map);

	}

	/**
	 * 买家是市价的话，可以匹配的（一）所有的买家都可以 （二） 按价格越低，时间越早越先配对
	 */
	@Override
	public List<ExentrustOneday> listMatchByBuyMarketPrice(String saasId, ExEntrust buyexEntrust, String customerType) {
		QueryFilter filter = new QueryFilter(ExEntrust.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", buyexEntrust.getId());
		map.put("website", buyexEntrust.getWebsite());
		map.put("currencyType", buyexEntrust.getCurrencyType());
		map.put("coinCode", buyexEntrust.getCoinCode());
		map.put("fixPriceCoinCode", buyexEntrust.getFixPriceCoinCode());
		return exentrustOnedayDao.listMatchByBuyMarketPrice(map);

	}

	/**
	 * 卖家是限价的话，可以匹配的（一）（1）现价相等的买家（2）市价的买家。 （二） 所有的买家进行时间的正序排列
	 */
	@Override
	public List<ExentrustOneday> listMatchBySellLimitedPrice(String saasId, ExEntrust sellentrust, String customerType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", sellentrust.getId());
		map.put("website", sellentrust.getWebsite());
		map.put("currencyType", sellentrust.getCurrencyType());
		map.put("coinCode", sellentrust.getCoinCode());
		map.put("fixPriceCoinCode", sellentrust.getFixPriceCoinCode());
		map.put("lentrustPrice", sellentrust.getEntrustPrice().add(sellentrust.getFloatUpPrice()));
		map.put("gentrustPrice", sellentrust.getEntrustPrice().subtract(sellentrust.getFloatDownPrice()));
		return exentrustOnedayDao.listMatchBySellLimitedPrice(map);

	}

	/**
	 * 卖家是市价的话，可以匹配的（一）所有的买家都可以 （二） 按价格越低，时间越早越先配对
	 */
	@Override
	public List<ExentrustOneday> listMatchBySellMarketPrice(String saasId, ExEntrust sellentrust, String customerType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", sellentrust.getId());
		map.put("website", sellentrust.getWebsite());
		map.put("currencyType", sellentrust.getCurrencyType());
		map.put("coinCode", sellentrust.getCoinCode());
		map.put("fixPriceCoinCode", sellentrust.getFixPriceCoinCode());
		return exentrustOnedayDao.listMatchBySellMarketPrice(map);
	}

	@Override
	public void pushEntrust(String header, Integer type, String customerType) {

		// 推送委托变动信息

		PushData.pushEntrust(this.getExEntrustChange(header, type, "0", 18, customerType), header);
		// 行情板块
		PushData.pushEntrusMarket(this.getExEntrustChangeMarket(header, "0", 200, customerType), header);

	}

	/*
	 * @Override // customerType=1,2,3 // depth=0 // n=18 // 行情模块推送数据获取，买单，卖单
	 * public String getExEntrustChangeMarket(String header, String depth,
	 * Integer n, String customerType) { MarketDepths marketDepths = new
	 * MarketDepths(); Map<String, List<BigDecimal[]>> map = new HashMap<String,
	 * List<BigDecimal[]>>();
	 * 
	 * // 买单 List<BigDecimal[]> bids = new ArrayList<BigDecimal[]>(); String[]
	 * headarry = header.split(":"); Map<String, Object> seramap = new
	 * HashMap<String, Object>(); seramap.put("website", headarry[0]);
	 * seramap.put("currencyType", headarry[1]); seramap.put("coinCode",
	 * headarry[2].split("_")[0]); seramap.put("fixPriceCoinCode",
	 * headarry[2].split("_")[1]); seramap.put("count", n); List<Integer>
	 * listjia = new ArrayList<Integer>(); if (null != customerType) { String[]
	 * arry = customerType.split(","); for (int a = 0; a < arry.length; a++) {
	 * listjia.add(Integer.valueOf(arry[a])); } } seramap.put("customerType",
	 * listjia); BigDecimal buyOnePrice = BigDecimal.ZERO;
	 * 
	 * Coin productCommon
	 * =productCommonService.getProductCommon(headarry[2].split("_")[0],
	 * headarry[2].split("_")[1]); int keepDecimalForCoin = 4; int
	 * keepDecimalForCurrency = 2; if (null != productCommon) {
	 * keepDecimalForCoin = (null != productCommon.getKeepDecimalForCoin() ?
	 * productCommon.getKeepDecimalForCoin() : 4); keepDecimalForCurrency =
	 * (null != productCommon.getKeepDecimalForCurrency() ?
	 * productCommon.getKeepDecimalForCurrency() : 2); }
	 * 
	 * List<ExEntrustSimple> listbuy1 =
	 * exEntrustMongoService.geMongotbuyExEntrustChange(seramap); if (null !=
	 * listbuy1 && listbuy1.size() != 0) { buyOnePrice = new
	 * BigDecimal(listbuy1.get(0).getEntrustPriceDouble()); for (ExEntrustSimple
	 * l : listbuy1) { if (new
	 * BigDecimal(l.getEntrustPriceDouble()).compareTo(buyOnePrice) == 1) {
	 * buyOnePrice = new BigDecimal(l.getEntrustPriceDouble()); } BigDecimal[]
	 * array = new BigDecimal[2]; array[0] = new
	 * BigDecimal(l.getEntrustPriceDouble()).setScale(keepDecimalForCurrency,
	 * BigDecimal.ROUND_HALF_EVEN); array[1] =
	 * l.getSurplusEntrustCount().setScale(keepDecimalForCoin,
	 * BigDecimal.ROUND_HALF_EVEN); bids.add(array); } }
	 * 
	 * ExchangeDataCache.setStringData(header + ":" +
	 * ExchangeDataCache.BuyOnePrice,
	 * buyOnePrice.setScale(keepDecimalForCurrency,
	 * BigDecimal.ROUND_HALF_EVEN).toString()); map.put("bids", bids);
	 * 
	 * // 卖单 List<BigDecimal[]> asks = new ArrayList<BigDecimal[]>(); BigDecimal
	 * sellOnePrice = BigDecimal.ZERO;
	 * 
	 * List<ExEntrustSimple> listsell = new ArrayList<ExEntrustSimple>();
	 * listsell = exEntrustMongoService.geMongosellExEntrustChange(seramap);
	 * 
	 * if (null != listsell && listsell.size() != 0) { sellOnePrice = new
	 * BigDecimal(listsell.get(0).getEntrustPriceDouble()); for (ExEntrustSimple
	 * l : listsell) { if (new
	 * BigDecimal(l.getEntrustPriceDouble()).compareTo(sellOnePrice) == -1) {
	 * sellOnePrice = new BigDecimal(l.getEntrustPriceDouble()); } BigDecimal[]
	 * array = new BigDecimal[2]; array[0] = new
	 * BigDecimal(l.getEntrustPriceDouble()).setScale(keepDecimalForCurrency,
	 * BigDecimal.ROUND_HALF_EVEN); array[1] =
	 * l.getSurplusEntrustCount().setScale(keepDecimalForCoin,
	 * BigDecimal.ROUND_HALF_EVEN); asks.add(array); } }
	 * 
	 * ExchangeDataCache.setStringData(header + ":" +
	 * ExchangeDataCache.SellOnePrice,
	 * sellOnePrice.setScale(keepDecimalForCurrency,
	 * BigDecimal.ROUND_HALF_EVEN).toString()); map.put("asks", asks);
	 * marketDepths.setDepths(map); //如果相等就推送之前的
	 * if(sellOnePrice.compareTo(buyOnePrice)<0){ String key = header +
	 * ":pushEntrusMarket"; RedisService redisService = (RedisService)
	 * ContextUtil.getBean("redisService"); String preData =
	 * redisService.get(key); return preData; } return
	 * JSON.toJSONString(marketDepths).toString(); }
	 */
	// customerType=1,2,3
	// depth=0
	// n=18
	// 行情模块推送数据获取，买单，卖单
	@Override
	public String getExEntrustChangeMarket(String header, String depth, Integer n, String customerType) {
		MarketDepths marketDepths = new MarketDepths();
		Map<String, List<BigDecimal[]>> map = new HashMap<String, List<BigDecimal[]>>();

		// 买单
		List<BigDecimal[]> bids = new ArrayList<BigDecimal[]>();
		String[] headarry = header.split(":");
		Map<String, Object> seramap = new HashMap<String, Object>();
		seramap.put("website", headarry[0]);
		seramap.put("currencyType", headarry[1]);
		seramap.put("coinCode", headarry[2].split("_")[0]);
		seramap.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		/*
		 * if (null != customerType) { List<Integer> listjia = new
		 * ArrayList<Integer>(); String[] arry = customerType.split(","); for
		 * (int a = 0; a < arry.length; a++) {
		 * listjia.add(Integer.valueOf(arry[a])); } seramap.put("customerType",
		 * listjia); }
		 */

		BigDecimal buyOnePrice = BigDecimal.ZERO;

		Coin productCommon = productCommonService.getProductCommon(headarry[2].split("_")[0], headarry[2].split("_")[1]);
		int keepDecimalForCoin = 4;
		int keepDecimalForCurrency = 2;
		if (null != productCommon) {
			keepDecimalForCoin = (null != productCommon.getKeepDecimalForCoin() ? productCommon.getKeepDecimalForCoin() : 4);
			keepDecimalForCurrency = (null != productCommon.getKeepDecimalForCurrency() ? productCommon.getKeepDecimalForCurrency() : 2);
		}

		List<ExEntrustSimple> listbuy1 = exentrustOnedayDao.getExEntrustBuyChangeMarket(seramap);
		if (null != listbuy1 && listbuy1.size() != 0) {
			buyOnePrice = listbuy1.get(0).getEntrustPrice();
			for (ExEntrustSimple l : listbuy1) {
				if (l.getEntrustPrice().compareTo(buyOnePrice) == 1) {
					buyOnePrice = l.getEntrustPrice();
				}
				BigDecimal[] array = new BigDecimal[2];
				array[0] = l.getEntrustPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN);
				array[1] = l.getSurplusEntrustCount().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN);
				bids.add(array);
			}
		}

		ExchangeDataCache.setStringData(header + ":" + ExchangeDataCache.BuyOnePrice, buyOnePrice.setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN).toString());
		map.put("bids", bids);

		// 卖单
		List<BigDecimal[]> asks = new ArrayList<BigDecimal[]>();
		BigDecimal sellOnePrice = BigDecimal.ZERO;

		List<ExEntrustSimple> listsell = exentrustOnedayDao.getExEntrustSellChangeMarket(seramap);
		if (null != listsell && listsell.size() != 0) {
			sellOnePrice = listsell.get(0).getEntrustPrice();
			for (ExEntrustSimple l : listsell) {
				if (l.getEntrustPrice().compareTo(sellOnePrice) == -1) {
					sellOnePrice = l.getEntrustPrice();
				}
				BigDecimal[] array = new BigDecimal[2];
				array[0] = l.getEntrustPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN);
				array[1] = l.getSurplusEntrustCount().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN);
				asks.add(array);
			}
		}

		ExchangeDataCache.setStringData(header + ":" + ExchangeDataCache.SellOnePrice, sellOnePrice.setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN).toString());
		map.put("asks", asks);
		marketDepths.setDepths(map);
		// 如果相等就推送之前的
		/*
		 * if(sellOnePrice.compareTo(BigDecimal.ZERO)!=0&&buyOnePrice.compareTo(
		 * BigDecimal.ZERO)!=0&&sellOnePrice.compareTo(buyOnePrice)<0){ String
		 * key = header + ":pushEntrusMarket"; RedisService redisService =
		 * (RedisService) ContextUtil.getBean("redisService"); String preData =
		 * redisService.get(key); return preData; }
		 */
		return JSON.toJSONString(marketDepths).toString();
	}

	@Override
	// 深度数据
	public String getExEntrustChangeDephMarket(String header, String customerType, Integer n, BigDecimal depth) {

		MarketDepths marketDepths = new MarketDepths();
		Map<String, List<BigDecimal[]>> map = new HashMap<String, List<BigDecimal[]>>();
		List<BigDecimal[]> bids = new ArrayList<BigDecimal[]>();
		String[] headarry = header.split(":");
		Map<String, Object> seramap = new HashMap<String, Object>();
		seramap.put("website", headarry[0]);
		seramap.put("currencyType", headarry[1]);
		seramap.put("coinCode", headarry[2].split("_")[0]);
		seramap.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		List<Integer> listjia = new ArrayList<Integer>();
		if (null != customerType) {
			String[] arry = customerType.split(",");
			for (int a = 0; a < arry.length; a++) {
				listjia.add(Integer.valueOf(arry[a]));
			}
		}
		seramap.put("customerType", listjia);
		seramap.put("n", n);
		Coin productCommon = productCommonService.getProductCommon(headarry[2].split("_")[0], headarry[2].split("_")[1]);
		// 金额保留小数位
		int keepDecimalForCoin = 4;
		int keepDecimalForCurrency = 2;
		if (null != productCommon) {
			keepDecimalForCoin = (null != productCommon.getKeepDecimalForCoin() ? productCommon.getKeepDecimalForCoin() : 4);
			keepDecimalForCurrency = (null != productCommon.getKeepDecimalForCurrency() ? productCommon.getKeepDecimalForCurrency() : 2);
		}

		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("website", headarry[0]);
		queryMap.put("currencyType", headarry[1]);
		queryMap.put("coinCode", headarry[2].split("_")[0]);
		queryMap.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		queryMap.put("customerType", customerType);
		queryMap.put("type", 1);
		queryMap.put("entrustWay", 1);
		queryMap.put("status_lt", "2");
		queryMap.put("flag", "max");
		BigDecimal maxPrice = exentrustOnedayService.getMaxOrMinEntrustPrice(queryMap);
		// 需要获取最大值
		if (maxPrice.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal startPrice = maxPrice.setScale(depth.scale(), BigDecimal.ROUND_DOWN);
			if (depth.scale() == 0) {
				startPrice = startPrice.subtract(depth).add(new BigDecimal("1"));
			}
			if (depth.scale() == 1) {
				startPrice = startPrice.subtract(depth).add(new BigDecimal("0.1"));
			}

			seramap.put("maxPrice", maxPrice);
			seramap.put("startPrice", startPrice);
			seramap.put("depthjg", depth);
			// 查询临时挂单表，获取深度数据,查询数据用的ExentrustOneday，返回结果到Exentrust（改造少）
			List<ExEntrust> listbuy = new ArrayList<ExEntrust>();
			listbuy = exentrustOnedayService.getExEntrustBuyDeph(seramap);

			if (null != listbuy && listbuy.size() != 0) {
				for (ExEntrust l : listbuy) {
					if (l.getEntrustPrice().compareTo(BigDecimal.ZERO) > 0) {
						BigDecimal[] array = new BigDecimal[2];
						array[0] = l.getEntrustPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN);
						array[1] = (null != l.getSurplusEntrustCount()) ? l.getSurplusEntrustCount().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN) : new BigDecimal(0);
						bids.add(array);
					}
				}

			}

		}
		map.put("bids", bids);

		List<BigDecimal[]> asks = new ArrayList<BigDecimal[]>();
		// 获取最小卖单
		Map<String, Object> queryMap2 = new HashMap<String, Object>();
		queryMap2.put("website", headarry[0]);
		queryMap2.put("currencyType", headarry[1]);
		queryMap.put("coinCode", headarry[2].split("_")[0]);
		queryMap2.put("customerType", customerType);
		queryMap2.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		queryMap2.put("type", 2);
		queryMap2.put("entrustWay", 1);
		queryMap2.put("status_lt", "2");
		queryMap2.put("flag", "min");
		BigDecimal minPrice = exentrustOnedayService.getMaxOrMinEntrustPrice(queryMap2);

		if (minPrice.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal startPrice = minPrice.setScale(depth.scale(), BigDecimal.ROUND_DOWN);
			startPrice = startPrice.add(depth);
			if (depth.scale() == 0) {
				startPrice = startPrice.add(depth).subtract(new BigDecimal("1"));
			}
			if (depth.scale() == 1) {
				startPrice = startPrice.add(depth).subtract(new BigDecimal("0.1"));
			}
			seramap.put("minPrice", minPrice);
			seramap.put("startPrice", startPrice);
			seramap.put("depthjg", depth);

			// 从临时挂单表中获取卖单深度数据
			List<ExEntrust> listsell = new ArrayList<ExEntrust>();
			listsell = exentrustOnedayService.getExEntrustSellDeph(seramap);
			if (null != listsell && listsell.size() != 0) {
				for (ExEntrust l : listsell) {
					if (l.getEntrustPrice().compareTo(BigDecimal.ZERO) > 0) {
						BigDecimal[] array = new BigDecimal[2];
						array[0] = l.getEntrustPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN);
						array[1] = (null != l.getSurplusEntrustCount()) ? l.getSurplusEntrustCount().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN) : new BigDecimal(0);
						asks.add(array);
					}
				}
			}
		}

		map.put("asks", asks);
		marketDepths.setDepths(map);
		return JSON.toJSONString(marketDepths).toString();

	}

	@Override
	public String getExEntrustChange(String header, Integer type, String depth, Integer n, String customerType) {
		ExEntrustDao exEntrustDao = (ExEntrustDao) dao;
		String[] headarry = header.split(":");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("website", headarry[0]);
		map.put("currencyType", headarry[1]);
		map.put("coinCode", headarry[2]);
		StringBuffer sb = new StringBuffer();
		if (type.equals(1) && depth.equals("0")) {
			sb.append("{\"type\":1,\"data\":[");
			map.put("count", n);
			// map.put("customerType", customerType);
			List<Integer> listjia = new ArrayList<Integer>();
			if (null != customerType) {
				String[] arry = customerType.split(",");
				for (int a = 0; a < arry.length; a++) {
					listjia.add(Integer.valueOf(arry[a]));
				}
			}
			map.put("customerType", listjia);
			List<ExEntrust> listbuy = exEntrustDao.getbuyExEntrustChange(map);
			if (null != listbuy && listbuy.size() != 0) {
				for (ExEntrust l : listbuy) {
					sb.append("{\"entrustPrice\":\"" + l.getEntrustPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\",");
					sb.append("\"entrustSum\":\"" + l.getEntrustSum().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\",");
					sb.append("\"entrustCount\":\"" + l.getSurplusEntrustCount().setScale(4, BigDecimal.ROUND_HALF_EVEN) + "\"},");
				}
			} else {
				sb.append(",");

			}

		}
		if (type.equals(2) && depth.equals("0")) {
			sb.append("{\"type\":2,\"data\":[");
			map.put("count", 5);
			List<Integer> listjia = new ArrayList<Integer>();
			if (null != customerType) {
				String[] arry = customerType.split(",");
				for (int a = 0; a < arry.length; a++) {
					listjia.add(Integer.valueOf(arry[a]));
				}
			}
			map.put("customerType", listjia);
			List<ExEntrust> listsell = exEntrustDao.getsellExEntrustChange(map);
			if (null != listsell && listsell.size() != 0) {
				for (ExEntrust l : listsell) {
					sb.append("{\"entrustPrice\":\"" + l.getEntrustPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\",");
					sb.append("\"entrustSum\":\"" + l.getEntrustSum().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\",");
					sb.append("\"entrustCount\":\"" + l.getSurplusEntrustCount().setScale(4, BigDecimal.ROUND_HALF_EVEN) + "\"},");
				}
			} else {
				sb.append(",");

			}

		}
		String sub = "";
		if (sb.length() != 1) {
			sub = sb.substring(0, sb.length() - 1);
		}
		sub = sub + "]}";
		return sub;
	}

	@Override
	public ExEntrust getExEntrust(String entrustNums, String coinCode, String saasId) {

		QueryFilter filter = new QueryFilter(ExEntrust.class);
		filter.setSaasId(saasId);
		filter.addFilter("entrustNum=", entrustNums);
		if (null != coinCode) {
			filter.addFilter("coinCode=", coinCode);
		}

		return this.get(filter);

	}

	@Override
	public List<ExOrderInfo> getMatchDetail(String entrustNum, String coinCode,String type) {
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		QueryFilter filter = new QueryFilter(ExOrderInfo.class);
		filter.setSaasId(saasId);
		filter.addFilter("coinCode=", coinCode);
		if(type.equals("1")){
			filter.addFilter("buyEntrustNum=", entrustNum);
		}else{
			filter.addFilter("sellEntrustNum=", entrustNum);
		}
		
		
		List<ExOrderInfo> list = exOrderInfoService.find(filter);
		return list;

	}

	@Override
	public void initExEntrust(ExEntrust exEntrust) {
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		if (null == exEntrust.getEntrustTime()) {
			exEntrust.setEntrustTime(new Date());
		}

		exEntrust.setEntrustNum(IdGenerate.transactionNum(NumConstant.Ex_Entrust));
		String transactionNum = IdGenerate.transactionNum(NumConstant.Ex_Entrust);
		if (!DifCustomer.getIsCommon()) {
			if (exEntrust.getType() == 1) {
				exEntrust.setEntrustNum("WB" + transactionNum + DifCustomer.customerTypeName(exEntrust.getCustomerType()));
			} else {
				exEntrust.setEntrustNum("WS" + transactionNum + DifCustomer.customerTypeName(exEntrust.getCustomerType()));
			}

		} else {
			exEntrust.setEntrustNum(transactionNum);
		}
		exEntrust.setCustomerId(exEntrust.getCustomerId());
		RpcContext.getContext().setAttachment("saasId", saasId);
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
		exEntrust.setAccountId(appAccount.getId());
		exEntrust.setSaasId(saasId);
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
		Coin productCommon = productCommonService.getProductCommon(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode());
		if (exEntrust.getType() == 1) {
			exEntrust.setTransactionFeeRate(null == productCommon.getBuyFeeRate() ? new BigDecimal("0") : productCommon.getBuyFeeRate());
		} else {
			exEntrust.setTransactionFeeRate(null == productCommon.getSellFeeRate() ? new BigDecimal("0") : productCommon.getSellFeeRate());
		}

		exEntrust.setTransactionFee(new BigDecimal("0"));
		if (null == exEntrust.getCustomerType()) {
			exEntrust.setCustomerType(1);// 默认普通客户
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
		if (null == exEntrust.getMatchPriority()) {
			exEntrust.setMatchPriority(1);
		}
		// exEntrust.setEntrustPriceDouble(exEntrust.getEntrustPrice().doubleValue());
		/*
		 * if (null == exEntrust.getUserName()) { RemoteAppCustomerService
		 * remoteAppCustomerService = (RemoteAppCustomerService) ContextUtil
		 * .getBean("remoteAppCustomerService"); AppCustomer appCustomer =
		 * remoteAppCustomerService.getById(exEntrust.getCustomerId());
		 * exEntrust.setUserName(appCustomer.getUserName());
		 * RemoteAppPersonInfoService remoteAppPersonInfoService =
		 * (RemoteAppPersonInfoService) ContextUtil
		 * .getBean("remoteAppPersonInfoService"); AppPersonInfo appPersonInfo =
		 * remoteAppPersonInfoService.getByCustomerId(exEntrust.getCustomerId())
		 * ; exEntrust.setTrueName(appPersonInfo.getTrueName());
		 * exEntrust.setUserCode(appCustomer.getUserCode()); }
		 */
		List<ExCointoCoin> listExCointoCoin = exCointoCoinService.getBylist(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode(), null);
		if (null != listExCointoCoin && listExCointoCoin.size() > 0) {
			exEntrust.setFixPriceType(listExCointoCoin.get(0).getFixPriceType());
		}

	}

	/**
	 * 取消委托单
	 */
	@Override
	public String[] cancelExEntrust(EntrustTrade entrustTrade, String remark) {
		String[] rt= exDmPingService.checkPing(entrustTrade.getCustomerId());
		String[] relt = new String[2];
		if(rt[0].equals(CodeConstant.CODE_FAILED)){
			relt[1] = "";
			return rt;
		}
		 String appName = PropertiesUtils.APP.getProperty("app.appName");
		 if(appName != null && "DFC".equals(appName)){
			 QueryFilter queryFilter = new QueryFilter(ExEntrust.class);
				queryFilter.addFilter("entrustNum=", entrustTrade.getEntrustNum());
				List<ExEntrust> entrusts = find(queryFilter);
				if(entrusts != null && entrusts.size()>0){
					ExEntrust exEntrust = entrusts.get(0);
					if(exEntrust.getSource() == 8){
						relt[0] = CodeConstant.CODE_FAILED;
						relt[1] = "huigoudanbuyunxuchedan";
						return relt;
					}
				}
		 }
		
		// 序列化
		String str = JSON.toJSONString(entrustTrade);
		// 发送消息
		messageProducer.toTrade(str);
		
		relt[0] = CodeConstant.CODE_SUCCESS;
		relt[1] = "";
		return relt;

	}
	public static String getHeader(String coinCode, String fixPriceCoinCode, Integer type) {
		if (null == type) {
			String header = coinCode + "_" + fixPriceCoinCode;
			return header;
		} else if (type.equals(2)) {
			String header = coinCode + "_" + fixPriceCoinCode + ":sell";
			return header;
		} else {
			String header = coinCode + "_" + fixPriceCoinCode + ":buy";
			return header;
		}
	}
	@Override
	public String[] cancelExEntrustnokey(ExEntrust exEntrust,String remark) {
		Boolean flag=false;
		String key =getHeader(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode(), exEntrust.getType()) + ":" + exEntrust.getEntrustPrice();
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		String entrustredis =redisTradeService.get(key);
		if (StringUtil.isEmpty(entrustredis) || entrustredis.equals("[]")) {
			logger.error("撤销失败，keylist为空" + key);
			flag=true;
		}else{
			int i=0;
			List<EntrustTrade> list = JSON.parseArray(entrustredis, EntrustTrade.class);
			for (EntrustTrade l : list) {
				if (exEntrust.getEntrustNum().equals(l.getEntrustNum())) {
					i++;
					break;
				}
			}
			if(i==0){//没找到在key值里面
				logger.error("撤销失败，此委托单已不在key值里面" + key+"==exEntrust.getEntrustNum()");
				flag=true;
			}
		}
		if (exEntrust.getStatus() >= 2) {
			String[] relt = new String[2];
			relt[0] = CodeConstant.CODE_FAILED;
			relt[1] = "数据库已经完成了，不用再撤销";
			return relt;
		}
		//key里面没有的情况下走这
		if(flag){
		
			if (exEntrust.getStatus().equals(1)) {
				exEntrust.setStatus(3);
			} else if (exEntrust.getStatus().equals(0)) {
				exEntrust.setStatus(4);
			}
			List<Accountadd> aaddlists = new ArrayList<Accountadd>();
			String transactionNum = exEntrust.getEntrustNum();
			if (exEntrust.getType().equals(1)) {
				// 如果是真实货币
				if (exEntrust.getFixPriceType().equals(0)) {
					BigDecimal unfreezeMoney = exEntrust.getEntrustSum().subtract(exEntrust.getTransactionSum());
					Accountadd accountadd1 = getAccountadd(0, exEntrust.getAccountId(), unfreezeMoney, 1, 12, transactionNum);
					aaddlists.add(accountadd1);
					Accountadd accountadd2 = getAccountadd(0, exEntrust.getAccountId(), fu(unfreezeMoney), 2, 12, transactionNum);
					aaddlists.add(accountadd2);

				} else {
					BigDecimal unfreezeMoney = exEntrust.getEntrustSum().subtract(exEntrust.getTransactionSum());
					Accountadd accountadd1 = getAccountadd(1, exEntrust.getAccountId(), unfreezeMoney, 1, 12, transactionNum);
					aaddlists.add(accountadd1);
					Accountadd accountadd2 = getAccountadd(1, exEntrust.getAccountId(), fu(unfreezeMoney), 2, 12, transactionNum);
					aaddlists.add(accountadd2);

				}
			}
			// 卖币都一样
			if (exEntrust.getType().equals(2)) {
				BigDecimal unfreezeMoney = exEntrust.getSurplusEntrustCount();
				Accountadd accountadd1 = getAccountadd(1, exEntrust.getCoinAccountId(), unfreezeMoney, 1, 12, transactionNum);
				aaddlists.add(accountadd1);
				Accountadd accountadd2 = getAccountadd(1, exEntrust.getCoinAccountId(), fu(unfreezeMoney), 2, 12, transactionNum);
				aaddlists.add(accountadd2);
			}
			this.update(exEntrust);
			messageProducer.toAccount(JSON.toJSONString(aaddlists));
			String[] relt = new String[2];
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "撤销成功";
			return relt;
		}else{
			String[] relt = new String[2];
			relt[0] = CodeConstant.CODE_FAILED;
			relt[1] = "buy,sell key有值，请用正常的撤销";
			return relt;
			
		}
		

	}
	public BigDecimal fu(BigDecimal money) {
		return new BigDecimal("0").subtract(money);
	}
	public Accountadd getAccountadd(Integer acccountType, Long accountId, BigDecimal money, Integer monteyType, Integer remarks, String transactionNum) {
		Accountadd accountadd = new Accountadd();
		accountadd.setAcccountType(acccountType);
		accountadd.setMoney(money);
		accountadd.setAccountId(accountId);
		accountadd.setMonteyType(monteyType);
		accountadd.setTransactionNum(transactionNum);
		accountadd.setRemarks(remarks);
		// accountadd.setRemarks(remarks);
		return accountadd;
	}

	// 撤单、
	@Override
	public String[] mqcancelExEntrust(ExEntrust entrust) {
		String remark = entrust.getOnlyMatchExEntrusts();
		ExEntrust exEntrust = this.getExEntrust(entrust.getEntrustNum(), entrust.getCoinCode(), null);
		String[] relt = new String[2];
		if (exEntrust.getStatus() >= 2) {
			logger.error("撤销失败，因为已经之前已经撤销过");
			return relt;
		}
		if (exEntrust.getStatus().equals(1)) {
			exEntrust.setStatus(3);
		} else if (exEntrust.getStatus().equals(0)) {
			exEntrust.setStatus(4);
		}
		// 虚拟币
		if (exEntrust.getProjectType() == null) {

			if (exEntrust.getType().equals(1)) {
				// 如果是真实货币
				if (exEntrust.getFixPriceType().equals(0)) {
					RpcContext.getContext().setAttachment("saasId", exEntrust.getSaasId());
					RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
					AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
					relt = remoteAppAccountService.unfreezeAccountSelfCancelExEntrust(appAccount.getId(), exEntrust.getEntrustSum().subtract(exEntrust.getTransactionSum()), exEntrust.getEntrustNum(), "撤销委托，解冻到自己钱包" + remark, null, 0);
					if (relt[0].equals(CodeConstant.CODE_FAILED)) {
						logger.error(remark + "--撤销 出现异常(" + relt[1] + ")：entrustNum===" + exEntrust.getEntrustNum() + "，时间==" + DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL));
						logger.error(remark + "--撤销 出现异常(" + relt[1] + ")：entrustNum===" + exEntrust.getEntrustNum() + "，时间==" + DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL));
					}

				} else {

					if (exEntrust.getType().equals(1)) {
						ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");
						ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getFixPriceCoinCode(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
						relt = exDigitalmoneyAccountService.unfreezeAccountSelfCancelExEntrust(exDigitalmoneyAccount.getId(), exEntrust.getEntrustSum().subtract(exEntrust.getTransactionSum()), exEntrust.getEntrustNum(), "撤销委托，解冻到自己钱包。" + remark, null, 0);
						if (relt[0].equals(CodeConstant.CODE_FAILED)) {
							logger.error(remark + "--撤销 出现异常(" + relt[1] + ")：entrustNum===" + exEntrust.getEntrustNum() + "，时间==" + DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL));
							logger.error(remark + "--撤销 出现异常(" + relt[1] + ")：entrustNum===" + exEntrust.getEntrustNum() + "，时间==" + DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL));
						}
					}

				}
			}
			// 卖币都一样
			if (exEntrust.getType().equals(2)) {
				ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");
				ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getCoinCode(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
				relt = exDigitalmoneyAccountService.unfreezeAccountSelfCancelExEntrust(exDigitalmoneyAccount.getId(), exEntrust.getSurplusEntrustCount(), exEntrust.getEntrustNum(), "撤销委托，解冻到自己钱包。" + remark, null, 0);
				if (relt[0].equals(CodeConstant.CODE_FAILED)) {
					logger.error(remark + "--撤销 出现异常(" + relt[1] + ")：entrustNum===" + exEntrust.getEntrustNum() + "，时间==" + DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL));
					logger.error(remark + "--撤销 出现异常(" + relt[1] + ")：entrustNum===" + exEntrust.getEntrustNum() + "，时间==" + DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL));
				}
			}
		} else if (exEntrust.getProjectType() == 2) {// 现货
			RemoteExExEntrustService remoteExEntrustService = (RemoteExExEntrustService) ContextUtil.getBean("remoteExExEntrustService");
			relt = remoteExEntrustService.canceldeductMoney(exEntrust);
		}

		// 如果资金出现异常的话就不走下面这段
		if (relt[0].equals(CodeConstant.CODE_SUCCESS)) {
			this.update(exEntrust);
			String header = this.getHeader(exEntrust);
			ExchangeDataCache.setStringData(header + ":" + ExchangeDataCache.IsMatch, "1"); // 要推送

			if (!DifCustomer.getIsCommon()) {
				// 判断是否要清楚指定配对
				try {
					isCanelOnlyentrustNum(exEntrust);
				} catch (Exception e) {
					logger.error("清除配对报错");
				}
			}

			// 从挂单临时表中删除
			exentrustOnedayService.delByEntrustNumber(exEntrust.getId());
			// this.delExEntrust(exEntrust);

			logger.error(remark + "撤销 成功->entrustNum:" + exEntrust.getEntrustNum() + ",userName:" + exEntrust.getUserName());
		} else {
			logger.error(remark + "撤销 失败->entrustNum:" + exEntrust.getEntrustNum() + ",userName:" + exEntrust.getUserName());
		}
		relt[0] = CodeConstant.CODE_SUCCESS;
		relt[1] = "";
		return relt;
	}

	@Override
	public void conflictExEntrust(ExEntrust exEntrust) {
		/*
		 * QueryFilter queryfilter = new QueryFilter(ExEntrust.class); if
		 * (exEntrust.getType().equals(1)) { queryfilter.addFilter("type=", 2);
		 * queryfilter.addFilter("customerType=", exEntrust.getCustomerType());
		 * queryfilter.addFilter("currencyType=", exEntrust.getCurrencyType());
		 * queryfilter.addFilter("website=", exEntrust.getWebsite());
		 * queryfilter.addFilter("coinCode=", exEntrust.getCoinCode());
		 * queryfilter.addFilter("status<", 2);
		 * queryfilter.addFilter("entrustPrice<=", exEntrust.getEntrustPrice());
		 * queryfilter.setSaasId(exEntrust.getSaasId());
		 * 
		 * } else {
		 * 
		 * queryfilter.addFilter("type=", 1);
		 * queryfilter.addFilter("customerType=", exEntrust.getCustomerType());
		 * queryfilter.addFilter("currencyType=", exEntrust.getCurrencyType());
		 * queryfilter.addFilter("website=", exEntrust.getWebsite());
		 * queryfilter.addFilter("coinCode=", exEntrust.getCoinCode());
		 * queryfilter.addFilter("status<", 2);
		 * queryfilter.addFilter("entrustPrice>=", exEntrust.getEntrustPrice());
		 * queryfilter.setSaasId(exEntrust.getSaasId());
		 * 
		 * } List<ExEntrust> list = this.find(queryfilter); for (ExEntrust l :
		 * list) {
		 * 
		 * cancelExEntrust(l.getEntrustNum(), l.getCustomerId(), "冲突撤单：单号" +
		 * exEntrust.getEntrustNum()); }
		 */}

	@Override
	public void cancelAllExEntrust() {

		cancel(":buy:");
		cancel(":sell:");
	}

	public void cancel(String key) {

		Set<String> keys = redisService.keys(key);
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String keystr = iterator.next();
			String v=redisService.get(keystr);
			List<EntrustTrade> list = JSON.parseArray(v, EntrustTrade.class);
			if (null != list && list.size() > 0) {
				for (EntrustTrade e : list) {
					EntrustTrade t = new EntrustTrade();
					t.setEntrustNum(e.getEntrustNum());
					t.setEntrustPrice(e.getEntrustPrice());
					t.setType(e.getType());
					t.setCoinCode(e.getCoinCode());
					t.setFixPriceCoinCode(e.getFixPriceCoinCode());
					// 序列化
					String str = JSON.toJSONString(t);
					// 发送消息
					messageProducer.toTrade(str);
				}

			}

		}

	}

	public boolean isMatch() {
		// 收盘开盘之间不能匹配
		boolean ismatch = true;
		return ismatch;

	}

	@Override
	public Map<String, BigDecimal> getExEntrustmMostPrice(String header, int type, List<Integer> customerType) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] headarry = header.split(":");
		map.put("website", headarry[0]);
		map.put("currencyType", headarry[1]);
		map.put("coinCode", headarry[2].split("_")[0]);
		map.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		map.put("customerType", customerType);

		map.put("type", type);
		ExEntrustDao dao = (ExEntrustDao) this.dao;
		Map<String, BigDecimal> mapres = new HashMap<String, BigDecimal>();
		mapres.put("minEntrustPrice", BigDecimal.ZERO);
		mapres.put("maxEntrustPrice", BigDecimal.ZERO);
		mapres.put("entrustCountsum", BigDecimal.ZERO);
		Map<String, BigDecimal> mapres1 = dao.getExEntrustmMostPrice(map);
		if (null != mapres1) {
			return mapres1;
		}
		return mapres;
	}

	@Override
	public List<ExEntrust> getExEntrustByPrice(String coincode, int type, String customerType, BigDecimal entrustPrice) {
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		QueryFilter filter = new QueryFilter(ExEntrust.class);
		filter.setSaasId(saasId);
		filter.addFilter("type=", type);
		filter.addFilter("customerType_in", customerType);
		filter.addFilter("coinCode=", coincode);
		filter.addFilter("status<", 2);
		if (type == 1) {
			filter.addFilter("entrustPrice>=", entrustPrice);
		} else {
			filter.addFilter("entrustPrice<=", entrustPrice);
		}
		filter.setOrderby("entrustCount desc");
		return this.find(filter);
	}

	@Override
	public void calculationMarketParameter() {

		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
		for (String website : mapLoadWeb.keySet()) {
			String productListStr = ExchangeDataCache.getStringData(website + ":productList");
			String currencyType = mapLoadWeb.get(website);
			if (!StringUtils.isEmpty(productListStr)) {
				List<String> productList = JSON.parseArray(productListStr, String.class);
				for (String coinCode : productList) {
					String header = website + ":" + currencyType + ":" + coinCode;
					List<Integer> listjia = new ArrayList<Integer>();
					listjia.add(1);
					listjia.add(3);
					Map<String, BigDecimal> map1 = this.getExEntrustmMostPrice(header, 2, listjia);
					Map<String, BigDecimal> map2 = this.getExEntrustmMostPrice(header, 1, listjia);
					// 甲委卖量
					BigDecimal sellcommonentrustCountsum = map1.get("entrustCountsum");
					// 甲委买量
					BigDecimal buycommonentrustCountsum = map2.get("entrustCountsum");
					// 甲委比
					BigDecimal commononEntrustProportion = new BigDecimal("0");
					if ((buycommonentrustCountsum.add(sellcommonentrustCountsum)).compareTo(new BigDecimal("0")) != 0) {
						commononEntrustProportion = (buycommonentrustCountsum.subtract(sellcommonentrustCountsum)).divide((buycommonentrustCountsum.add(sellcommonentrustCountsum)), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));

					}

					List<Integer> listyi = new ArrayList<Integer>();
					listyi.add(2);
					// 乙委量卖
					Map<String, BigDecimal> map3 = this.getExEntrustmMostPrice(header, 2, listyi);
					BigDecimal sellyientrustCountsum = map3.get("entrustCountsum");
					// 乙委量买
					Map<String, BigDecimal> map4 = this.getExEntrustmMostPrice(header, 1, listyi);
					BigDecimal buyyientrustCountsum = map4.get("entrustCountsum");
					// 乙委比
					BigDecimal yiEntrustProportion = new BigDecimal("0");
					if ((buyyientrustCountsum.add(sellyientrustCountsum)).compareTo(new BigDecimal("0")) != 0) {
						yiEntrustProportion = (buyyientrustCountsum.subtract(sellyientrustCountsum)).divide((buyyientrustCountsum.add(sellyientrustCountsum)), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));

					}
					redisService.save(header + ":plan:commonon:sellcommonentrustCountsum", sellcommonentrustCountsum.toString());
					redisService.save(header + ":plan:commonon:buycommonentrustCountsum", buycommonentrustCountsum.toString());
					redisService.save(header + ":plan:commonon:commononEntrustProportion", commononEntrustProportion.toString());
					redisService.save(header + ":plan:yi:sellyientrustCountsum", sellyientrustCountsum.toString());
					redisService.save(header + ":plan:yi:buyyientrustCountsum", buyyientrustCountsum.toString());
					redisService.save(header + ":plan:yi:yiEntrustProportion", yiEntrustProportion.toString());
				}
			}
		}
	}

	@Override
	public void cancelAllcustomerIdExEntrust(Long customerId, String currencyType, String website) {
		/*
		 * 
		 * String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		 * QueryFilter queryfilter = new QueryFilter(ExEntrust.class);
		 * queryfilter.addFilter("status<", 2);
		 * queryfilter.addFilter("customerId=", customerId);
		 * queryfilter.addFilter("currencyType=", currencyType);
		 * queryfilter.addFilter("website=", website);
		 * queryfilter.setSaasId(saasId); List<ExEntrust> list =
		 * this.find(queryfilter); for (ExEntrust l : list) {
		 * cancelExEntrust(l.getEntrustNum(), l.getCustomerId(),
		 * "cancelAllcustomerIdExEntrust"); }
		 * 
		 */}

	@Override
	public void cancelAlltypeExEntrust(Integer type, String currencyType, String website, String customerType, Long customerId) {
		/*
		 * 
		 * String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		 * QueryFilter queryfilter = new QueryFilter(ExEntrust.class);
		 * queryfilter.addFilter("status<", 2); queryfilter.addFilter("type=",
		 * type); queryfilter.addFilter("currencyType=", currencyType);
		 * queryfilter.addFilter("website=", website);
		 * queryfilter.setSaasId(saasId); if (null != customerType) {
		 * queryfilter.addFilter("customerType_in", customerType); } if (null !=
		 * customerId) { queryfilter.addFilter("customerId=", customerId); }
		 * List<ExEntrust> list = this.find(queryfilter); for (ExEntrust l :
		 * list) { cancelExEntrust(l.getEntrustNum(), l.getCustomerId(),
		 * "cancelAlltypeExEntrust"); }
		 * 
		 */}

	@Override
	public void cancelConditionExEntrust(Map<String, String> seramap) {
		/*
		 * String currencyType = seramap.get("currencyType"); String website =
		 * seramap.get("website"); String coinCode = seramap.get("coinCode");
		 * String entrustCountl = seramap.get("entrustCountl"); String
		 * entrustCountg = seramap.get("entrustCountg"); String entrustPricel =
		 * seramap.get("entrustPricel"); String entrustPriceg =
		 * seramap.get("entrustPriceg"); String customerType =
		 * seramap.get("customerType"); String saasId =
		 * PropertiesUtils.APP.getProperty("app.saasId"); QueryFilter
		 * queryfilter = new QueryFilter(ExEntrust.class);
		 * queryfilter.addFilter("status<", 2);
		 * queryfilter.addFilter("currencyType=", currencyType);
		 * queryfilter.addFilter("website=", website);
		 * queryfilter.addFilter("coinCode=", coinCode); if (null !=
		 * entrustCountl) { queryfilter.addFilter("entrustCount<=",
		 * entrustCountl); } if (null != entrustCountg) {
		 * queryfilter.addFilter("entrustCount>=", entrustCountg); } if (null !=
		 * entrustPricel) { queryfilter.addFilter("entrustPrice<=",
		 * entrustPricel); } if (null != entrustPriceg) {
		 * queryfilter.addFilter("entrustPrice>=", entrustPriceg); }
		 * 
		 * if (null != customerType) { queryfilter.addFilter("customerType_in",
		 * customerType); } queryfilter.setSaasId(saasId); List<ExEntrust> list
		 * = this.find(queryfilter); for (ExEntrust l : list) {
		 * cancelExEntrust(l.getEntrustNum(), l.getCustomerId(), ""); }
		 * 
		 */}

	@Override
	public void removeExEntrustByTime(Date date) {
		QueryFilter queryfilter = new QueryFilter(ExEntrust.class);
		queryfilter.addFilter("entrustTime<", DateUtil.dateToString(date, StringConstant.DATE_FORMAT_YMD));
		this.delete(queryfilter);

	}

	@Override
	public List<ExEntrust> getExEntrustlist(String entrustNums) {

		QueryFilter filter = new QueryFilter(ExEntrust.class);
		filter.addFilter("entrustNum_in", entrustNums);
		return this.find(filter);

	}

	@Override
	public void dealEntrustMatch() {/*
									 * //如果mq没宕的话就不管了 try{ RabbitTemplate
									 * mqTemplate
									 * =(RabbitTemplate)ContextUtil.getBean
									 * ("amqpTemplate"); String s =
									 * String.valueOf(new Date().getTime());
									 * mqTemplate .convertAndSend("logExchange",
									 * "hy.web.req" ,s+"--mq没宕");
									 * }catch(Exception e){
									 * logger.error("mq宕了");
									 * //如果mq宕了得话就for循环的匹配
									 * 
									 * Map<String,String>
									 * mapLoadWeb=PropertiesUtils.getLoadWeb();
									 * for (String Website :
									 * mapLoadWeb.keySet()) { String
									 * currencyType=mapLoadWeb.get(Website);
									 * String productListStr =
									 * ExchangeDataCache.
									 * getStringData(Website+":productList");
									 * if(!StringUtils.isEmpty(productListStr)){
									 * List<String> productList =
									 * JSON.parseArray
									 * (productListStr,String.class); for(String
									 * coinCode : productList){ String
									 * header=Website
									 * +":"+currencyType+":"+coinCode; String
									 * sellOnePrices
									 * =redisService.get(header+":"+
									 * ExchangeDataCache.SellOnePrice); String
									 * buyOnePrices=redisService.get(header+":"+
									 * ExchangeDataCache.BuyOnePrice);
									 * if(!StringUtil
									 * .isEmpty(sellOnePrices)&&!StringUtil
									 * .isEmpty(buyOnePrices) ){ BigDecimal
									 * buyOnePrice =new
									 * BigDecimal(buyOnePrices); BigDecimal
									 * sellOnePrice =new
									 * BigDecimal(sellOnePrices);
									 * if(buyOnePrice.compareTo(new
									 * BigDecimal("0"
									 * ))!=0&&sellOnePrice.compareTo(new
									 * BigDecimal("0"))!=0){
									 * if(buyOnePrice.compareTo
									 * (sellOnePrice)>=0){ String
									 * tablename=Website
									 * +currencyType+coinCode+"_buy_entrust";
									 * MongoUtil<ExEntrust, Long> mongoUtil =
									 * new MongoUtil<ExEntrust, Long>(
									 * ExEntrust.class,tablename);
									 * MongoQueryFilter mongoQueryFilter = new
									 * MongoQueryFilter();
									 * mongoQueryFilter.setOrderby
									 * ("entrustPriceDouble","desc");
									 * List<ExEntrust>
									 * list=mongoUtil.find(mongoQueryFilter);
									 * for(ExEntrust l:list){
									 * exOrderInfoService.matchExtrustToOrder(l,
									 * l.getSaasId()); } }
									 * 
									 * } } }
									 * 
									 * 
									 * }
									 * 
									 * }
									 * 
									 * }
									 */
	}

	@Override
	public void cancelCustAllExEntrust(EntrustTrade entrustTrade1) { 
		
		  EntrustTrade entrustTrade=new EntrustTrade();
		  entrustTrade.setCoinCode(entrustTrade1.getCoinCode());
		  entrustTrade.setFixPriceCoinCode(entrustTrade1.getFixPriceCoinCode());
		  entrustTrade.setCustomerId(entrustTrade1.getCustomerId());
		  this.cancelExEntrust(entrustTrade, "dfsaf");
		
		}

	@Override
	public void cancelCustAllCoinCodeExEntrust(Long customerId, String currencyType, String website, String CoinCode) {
		/*
		 * 
		 * String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		 * QueryFilter queryfilter = new QueryFilter(ExEntrust.class);
		 * queryfilter.addFilter("status<", 2);
		 * queryfilter.addFilter("customerId=", customerId);
		 * queryfilter.addFilter("currencyType=", currencyType);
		 * queryfilter.addFilter("website=", website);
		 * queryfilter.setSaasId(saasId); List<ExEntrust> list =
		 * this.find(queryfilter); for (ExEntrust l : list) {
		 * cancelExEntrust(l.getEntrustNum(), l.getCustomerId(),
		 * "cancelCustAllCoinCodeExEntrust"); }
		 * 
		 */}

	@Override
	public void isCanelOnlyentrustNum(ExEntrust exEntrust) {
		if (!DifCustomer.getIsCommon()) {
			String header = this.getHeader(exEntrust);
			String onlyentrustNum = ExchangeDataCache.getStringData(header + ":" + ExchangeDataCache.OnlyentrustNum);
			String noMatchExEntrusts = ExchangeDataCache.getStringData(header + ":" + ExchangeDataCache.NoentrustNum);

			if (exEntrust.getStatus() >= 2) {
				String entrustNum = exEntrust.getEntrustNum();
				// 配对
				if (!StringUtil.isEmpty(onlyentrustNum)) {
					if (onlyentrustNum.contains(entrustNum + ",")) {
						onlyentrustNum = onlyentrustNum.replace(entrustNum + ",", "");
					} else if (onlyentrustNum.contains("," + entrustNum)) {
						onlyentrustNum = onlyentrustNum.replace("," + entrustNum, "");
					} else if (onlyentrustNum.contains(entrustNum)) {
						onlyentrustNum = onlyentrustNum.replace(entrustNum, "");
					}
					DifCustomer.setRedisStringData(header + ":" + ExchangeDataCache.OnlyentrustNum, null == onlyentrustNum ? "" : onlyentrustNum);
				}

				// 屏蔽
				if (!StringUtil.isEmpty(noMatchExEntrusts)) {
					if (noMatchExEntrusts.contains(entrustNum + ",")) {
						noMatchExEntrusts = noMatchExEntrusts.replace(entrustNum + ",", "");
					} else if (noMatchExEntrusts.contains("," + entrustNum)) {
						noMatchExEntrusts = noMatchExEntrusts.replace("," + entrustNum, "");
					} else if (noMatchExEntrusts.contains(entrustNum)) {
						noMatchExEntrusts = noMatchExEntrusts.replace(entrustNum, "");
					}
					DifCustomer.setRedisStringData(header + ":" + ExchangeDataCache.NoentrustNum, null == onlyentrustNum ? "" : noMatchExEntrusts);

				}
			}

		}

	}

	/*
	 * @Override public void deleteMongoExEntrust(ExEntrust exEntrust) {
	 * if(exEntrust.getType()==1){ MongoUtil<ExEntrust, Long> mongoUtil = new
	 * MongoUtil<ExEntrust, Long>(
	 * ExEntrust.class,exEntrust.getWebsite()+exEntrust.getCurrencyType()
	 * +exEntrust.getCoinCode()+"_buy_entrust"); MongoQueryFilter
	 * mongoQueryFilter = new MongoQueryFilter();
	 * mongoQueryFilter.setSaasId(exEntrust.getSaasId());
	 * mongoQueryFilter.addFilter("entrustNum=", exEntrust.getEntrustNum());
	 * mongoUtil.delete(mongoQueryFilter);
	 * 
	 * 
	 * }else if(exEntrust.getType()==2){ MongoUtil<ExEntrust, Long> mongoUtil =
	 * new MongoUtil<ExEntrust, Long>(
	 * ExEntrust.class,exEntrust.getWebsite()+exEntrust.getCurrencyType()
	 * +exEntrust.getCoinCode()+"_sell_entrust"); MongoQueryFilter
	 * mongoQueryFilter = new MongoQueryFilter();
	 * mongoQueryFilter.setSaasId(exEntrust.getSaasId());
	 * mongoQueryFilter.addFilter("entrustNum=", exEntrust.getEntrustNum());
	 * mongoUtil.delete(mongoQueryFilter); }
	 * 
	 * }
	 */
	@Override
	public BigDecimal getTransactionFeeing(ExEntrust exEntrust) {
		BigDecimal fee = BigDecimal.ZERO;
		String productListStr = ExchangeDataCache.getStringData("cn:productList");
		if (!StringUtils.isEmpty(productListStr)) {
			List<String> productList = JSON.parseArray(productListStr, String.class);
			for (String coinCode : productList) {
				QueryFilter filter = new QueryFilter(ExentrustOneday.class);
				filter.addFilter("customerId=", exEntrust.getCustomerId());
				filter.addFilter("coinCode=", coinCode);
				filter.addFilter("type=", 1);
				List<ExentrustOneday> list = exentrustOnedayService.find(filter);
				if (null != list) {
					for (ExentrustOneday l : list) {
						fee = fee.add(l.getEntrustSum().subtract(l.getTransactionSum()).multiply(l.getTransactionFeeRate()).divide(new BigDecimal(100)));
					}
				}
			}
		}
		return fee;
	}

	@Override
	public String[] deductMoney(ExEntrust exEntrust) {
		String[] relt = new String[2];
		if (exEntrust.getType().equals(1)) {// 买单
			RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
			if (exEntrust.getFixPriceType().equals(0)) { // 定价真实货币
				AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
				relt = remoteAppAccountService.freezeAccountSelf(appAccount.getId(), exEntrust.getEntrustSum(), exEntrust.getEntrustNum(), "委托买" + exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode(), null, 0);

			} else {// 定价虚拟货币
				RemoteExDigitalmoneyAccountService remoteExDigitalmoneyAccountService = (RemoteExDigitalmoneyAccountService) ContextUtil.getBean("remoteExDigitalmoneyAccountService");
				ExDigitalmoneyAccount exDigitalmoneyAccount = remoteExDigitalmoneyAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getFixPriceCoinCode(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
				relt = remoteExDigitalmoneyAccountService.freezeAccountSelf(exDigitalmoneyAccount.getId(), exEntrust.getEntrustSum(), exEntrust.getEntrustNum(), "委托买" + exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode(), null, 0);

			}

		} else if (exEntrust.getType().equals(2)) {// 只有金额
			RemoteExDigitalmoneyAccountService remoteExDigitalmoneyAccountService = (RemoteExDigitalmoneyAccountService) ContextUtil.getBean("remoteExDigitalmoneyAccountService");
			ExDigitalmoneyAccount exDigitalmoneyAccount = remoteExDigitalmoneyAccountService.getByCustomerIdAndType(exEntrust.getCustomerId(), exEntrust.getCoinCode(), exEntrust.getCurrencyType(), exEntrust.getWebsite());
			relt = remoteExDigitalmoneyAccountService.freezeAccountSelf(exDigitalmoneyAccount.getId(), exEntrust.getEntrustCount(), exEntrust.getEntrustNum(), "委托卖" + exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode(), null, 0);

		}

		return relt;
	}

	/*
	 * @Override public void delExEntrust(ExEntrust oneday) { String tableName =
	 * this.getHeader(oneday) + ":"; if (oneday.getType() == 1) { tableName =
	 * tableName + BUY; } else if (oneday.getType() == 2) { tableName =
	 * tableName + SELL; } List<ExEntrust> list =
	 * JSON.parseArray(redisService.get(tableName), ExEntrust.class); for
	 * (ExEntrust l : list) { if
	 * (l.getEntrustNum().equals(oneday.getEntrustNum())) { list.remove(l);
	 * break; } } redisService.save(tableName, JSON.toJSONString(list)); }
	 */

	/*
	 * @Override public void addExEntrust(ExEntrust oneday) { String tableName =
	 * oneday.getWebsite() + ":" + oneday.getCurrencyType() + ":" +
	 * oneday.getCoinCode()+"_"+oneday.getFixPriceCoinCode() + ":"; if
	 * (oneday.getType() == 1) { tableName = tableName + BUY; } else if
	 * (oneday.getType() == 2) { tableName = tableName + SELL; } List<ExEntrust>
	 * list = JSON.parseArray(redisService.get(tableName), ExEntrust.class);
	 * list.add(0, oneday); redisService.save(tableName,
	 * JSON.toJSONString(list)); }
	 */

	@Override
	public String getHeader(String website, String currencyType, String coinCode, String fixPriceCoinCode) {
		if(null==website){
			String header =  coinCode + "_" + fixPriceCoinCode;
			return header;
		}else{
		String header = website + ":" + currencyType + ":" + coinCode + "_" + fixPriceCoinCode;
		return header;
		}
	}

	@Override
	public String getHeader(String coinCode, String fixPriceCoinCode) {
		String header = "cn:cny:" + coinCode + "_" + fixPriceCoinCode;
		return header;
	}

	@Override
	public String getHeader(ExEntrust exEntrust) {
		String header = exEntrust.getWebsite() + ":" + exEntrust.getCurrencyType() + ":" + exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode();
		return header;
	}

	@Override
	public String getHeader(ExOrderInfo exEntrust) {
		String header = exEntrust.getWebsite() + ":" + exEntrust.getCurrencyType() + ":" + exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode();
		return header;
	}

	@Override
	public FrontPage findFrontPage(Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		// 查询方法
		List<Entrust> list = ((ExEntrustDao) dao).findFrontPageBySql(params);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());

	}

	@Override
	public String[] saveExEntrustCheck(EntrustTrade exEntrust) {
		String[] rt = new String[2];
		/**
		 * 全局的判断
		 */
		// 返回false，说明是节假日或闭盘时间
		if (!(isOpenTrade(new Date()))) {
			rt[0] = CodeConstant.CODE_FAILED;
			rt[1] = "now_is_error";
			return rt;
		}
		if (!DifCustomer.isInOpenplateAndCloseplate()) {
			rt[0] = CodeConstant.CODE_FAILED;
			rt[1] = "system_is_close";
			return rt;
		}

		/**
		 * 针对交易币种和交易区的判断
		 */
		// HRB/MGB或者HRB/cny不能交易
		Coin productCommon = productCommonService.getCoinFromreds(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode());
		if (null == productCommon) {
			rt[0] = CodeConstant.CODE_FAILED;
			rt[1] = exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode() + "::cannot_trade";
			return rt;
		}
		if (exEntrust.getEntrustWay() == 1) {// 如果等于限价的话
			if (null != productCommon.getKeepDecimalForCurrency() && null != productCommon.getKeepDecimalForCoin()) {
				String entrustPricearr = exEntrust.getEntrustPrice().toString();
				if (entrustPricearr.indexOf(".") > 0) {
					entrustPricearr = entrustPricearr.replaceAll("0+?$", "");// 去掉后面无用的零
					entrustPricearr = entrustPricearr.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
				}
				int bitpos = entrustPricearr.indexOf(".");
				if ((bitpos != -1) && ((entrustPricearr.length() - bitpos - 1) > productCommon.getKeepDecimalForCurrency())) {

					rt[0] = CodeConstant.CODE_FAILED;
					rt[1] = "weituoijiagexiaoyudengyu~" + productCommon.getKeepDecimalForCurrency();
					return rt;
				}
				String entrustCountcearr = exEntrust.getEntrustCount().toString();
				int bitposcount = entrustCountcearr.indexOf(".");
				if (entrustCountcearr.indexOf(".") > 0) {
					entrustCountcearr = entrustCountcearr.replaceAll("0+?$", "");// 去掉后面无用的零
					entrustCountcearr = entrustCountcearr.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
				}
				if ((bitposcount != -1) && ((entrustCountcearr.length() - bitposcount - 1) > productCommon.getKeepDecimalForCoin())) {

					rt[0] = CodeConstant.CODE_FAILED;
					rt[1] = "weituoshuliangxiaoyudengyu~" + productCommon.getKeepDecimalForCoin();
					return rt;
				}
			}
		}

		if (exEntrust.getEntrustWay() == 2 && exEntrust.getType() == 2) {// 如果是市价并且是卖
			if ( null != productCommon.getKeepDecimalForCoin()) {
				String entrustCountcearr = exEntrust.getEntrustCount().toString();
				int bitposcount = entrustCountcearr.indexOf(".");
				if (entrustCountcearr.indexOf(".") > 0) {
					entrustCountcearr = entrustCountcearr.replaceAll("0+?$", "");// 去掉后面无用的零
					entrustCountcearr = entrustCountcearr.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
				}
				if ((bitposcount != -1) && ((entrustCountcearr.length() - bitposcount - 1) > productCommon.getKeepDecimalForCoin())) {
					rt[0] = CodeConstant.CODE_FAILED;
					rt[1] = "weituoshuliangxiaoyudengyu~" + productCommon.getKeepDecimalForCoin();
					return rt;
				}
			}
		}
		if (exEntrust.getEntrustWay() == 2 && exEntrust.getType() == 1) {// 如果是市价并且是买
			if (null != productCommon.getKeepDecimalForCurrency() ) {
				String entrustCountcearr = exEntrust.getEntrustSum().toPlainString();
				int bitposcount = entrustCountcearr.indexOf(".");
				if (entrustCountcearr.indexOf(".") > 0) {
					entrustCountcearr = entrustCountcearr.replaceAll("0+?$", "");// 去掉后面无用的零
					entrustCountcearr = entrustCountcearr.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
				}
				if ((bitposcount != -1) && ((entrustCountcearr.length() - bitposcount - 1) > productCommon.getKeepDecimalForCurrency())) {
					rt[0] = CodeConstant.CODE_FAILED;
					rt[1] = "weituozongshuxiaoyudengyu~" + productCommon.getKeepDecimalForCurrency();
					return rt;
				}
			}
		}
		// 限价的情况下，判断价格限制
	    BigDecimal yesterdayPrice = getYearDayPrice(exEntrust.getCoinCode(),exEntrust.getFixPriceCoinCode());
		// 如果没有昨日收盘价，则取币的发行价来限制价格
		if (yesterdayPrice == null) {
			cachePublishCoin(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode());
			yesterdayPrice = getYearDayPrice(exEntrust.getCoinCode(),exEntrust.getFixPriceCoinCode());
		}
		if (exEntrust.getEntrustWay() == 1 && productCommon != null) {
			if ( productCommon.getRose()!=null
					&& exEntrust.getEntrustPrice() != null 
					&& null!=yesterdayPrice
					) {
				BigDecimal rose = productCommon.getRose();// 涨幅
				BigDecimal zhangjia = yesterdayPrice.multiply((new BigDecimal("100").add(rose)).divide(new BigDecimal(100),8,BigDecimal.ROUND_HALF_DOWN));
				if (zhangjia.compareTo(exEntrust.getEntrustPrice()) < 0) {
					rt[0] = CodeConstant.CODE_FAILED;
					rt[1]="jiagechaoguozhangdiefanwei";
					return rt;
				}
			}
			if ( productCommon.getDecline()!=null
					&& exEntrust.getEntrustPrice() != null 
					&& null!=yesterdayPrice
					) {
				BigDecimal rose = productCommon.getDecline();
				BigDecimal diejia =yesterdayPrice.multiply((new BigDecimal("100").subtract(rose)).divide(new BigDecimal(100),8,BigDecimal.ROUND_HALF_DOWN));
				if (diejia.compareTo(exEntrust.getEntrustPrice()) > 0) {
					rt[0] = CodeConstant.CODE_FAILED;
					rt[1]="jiagechaoguozhangdiefanwei";
					return rt;
				}
			}
			// 加一个单笔最大下单量控制
			BigDecimal oneTimeOrderNum = productCommon.getOneTimeOrderNum();
			BigDecimal entrustCount = exEntrust.getEntrustCount();// 下单量
			if (exEntrust.getEntrustWay() == 1 && entrustCount != null && entrustCount.compareTo(BigDecimal.ZERO) > 0) {// 下单量大于0
				if (entrustCount.compareTo(oneTimeOrderNum) > 0) {// 下单量大于币的单笔最大限制数量
					rt[0] = CodeConstant.CODE_FAILED;
					rt[1] = "xiadanshulaingchaguozuida~" + oneTimeOrderNum.setScale(8).toString();
					return rt;
				}
			}
			//最小下单量控制
			BigDecimal sellMinCoin = productCommon.getSellMinCoin();
			if (exEntrust.getEntrustWay() == 1 && entrustCount != null && entrustCount.compareTo(BigDecimal.ZERO) > 0) {// 下单量大于0
				if (sellMinCoin.compareTo(entrustCount) > 0) {// 下单量大于币的单笔最大限制数量
					rt[0] = CodeConstant.CODE_FAILED;
					rt[1] = "xiadanshuliangxiaoyuzuixiao~" + sellMinCoin.setScale(8).toString();
					return rt;
				}
			}

		}
		// 判断交易可用币余额
		/*
		 * ExDigitalmoneyAccountService AccountService =
		 * (ExDigitalmoneyAccountService)
		 * ContextUtil.getBean("exDigitalmoneyAccountService");
		 * ExDigitalmoneyAccount account =
		 * AccountService.getByCustomerIdAndType(exEntrust.getCustomerId(),
		 * exEntrust.getCoinCode(), ContextUtil.getCurrencyType(),
		 * ContextUtil.getWebsite()); if (null == account) { rt[0] =
		 * CodeConstant.CODE_FAILED; rt[1] = "交易币账户不存在！！！"; return rt; } // 如果卖
		 * if (exEntrust.getType() == 2 &&
		 * account.getHotMoney().subtract(account.getDisableMoney()).compareTo(
		 * exEntrust.getEntrustCount()) < 0) { rt[0] = CodeConstant.CODE_FAILED;
		 * rt[1] = "交易币可用币不足"; return rt; }
		 */
		rt[0] = CodeConstant.CODE_SUCCESS;
		return rt;
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

	/**
	 * 缓存币种交易的发行价
	 * @param coinCode
	 * @param fixPriceCoinCode
	 */
	private void cachePublishCoin (String coinCode, String fixPriceCoinCode) {
		// 获取当前币种交易的信息
		QueryFilter qf = new QueryFilter(ExCointoCoin.class);
		qf.addFilter("coinCode=", coinCode);
		qf.addFilter("fixPriceCoinCode=", fixPriceCoinCode);
		qf.addFilter("state=", 1);
		ExCointoCoin exCointoCoin = exCointoCoinService.get(qf);

		String coinsKey = "cn:coinInfoList2";
		String conStr = ExchangeDataCache.getStringData(coinsKey);
		if (exCointoCoin != null) {
			if (!StringUtils.isEmpty(conStr)) {
				String code = coinCode + fixPriceCoinCode;
				List<Coin2> list = JSONObject.parseArray(conStr, Coin2.class);
				boolean flag = false;
				for (Coin2 c : list) {
					if (code.equals(c.getCoinCode() + c.getFixPriceCoinCode())) {
						c.setYesterdayPrice(exCointoCoin.getAveragePrice().toString());
						flag = true;
					}
				}
				if (!flag) {
					Coin2 coin2 = new Coin2();
					coin2.setCoinCode(coinCode);
					coin2.setFixPriceCoinCode(fixPriceCoinCode);
					coin2.setYesterdayPrice(exCointoCoin.getAveragePrice().toString());
					list.add(coin2);
				}
				ExchangeDataCache.setStringData(coinsKey, JSON.toJSONString(list));
			} else {
				List<Coin2> list = new ArrayList<Coin2>();
				Coin2 coin2 = new Coin2();
				coin2.setCoinCode(coinCode);
				coin2.setFixPriceCoinCode(fixPriceCoinCode);
				coin2.setYesterdayPrice(exCointoCoin.getAveragePrice().toString());
				list.add(coin2);
				ExchangeDataCache.setStringData(coinsKey, JSON.toJSONString(list));
			}
		}
	}
	/**
	 * <p>
	 * 判断当前是否是节假日，或是开盘时间
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @return
	 * @return: boolean
	 * @Date : 2016年9月21日 下午6:15:31
	 * @throws:
	 */
	public static boolean isOpenTrade(Date date) {
		// 判断周末是否开盘
		// 判断周末是否开盘
		String isWeekend = getCnfigValue("isWeekend");// 0 开启 1关闭
		if ("1".equals(isWeekend)) {// 关闭
			// 继续判断今天是否是周六或周日
			boolean flag = isWeekend(new Date());
			if (flag) {
				return false;
			}
		}

		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String str = redisService.get("appholidayConfig");
		if (!StringUtils.isEmpty(str)) {
			// 判断否是节假日
			List<AppHolidayConfig> list = JSON.parseArray(str, AppHolidayConfig.class);
			if (list != null && list.size() > 0) {
				for (AppHolidayConfig ahc : list) {
					if (date.getTime() > ahc.getBeginDate().getTime() && date.getTime() < ahc.getEndDate().getTime()) {
						return false;
					}
				}
			}
		}

		// 计算是否是开闭盘
		String financeByKey = "";
		String string = redisService.get(StringConstant.CONFIG_CACHE + ":financeConfig");
		JSONArray obj = JSON.parseArray(string);
		for (Object o : obj) {
			JSONObject oo = JSON.parseObject(o.toString());
			if (oo.getString("configkey").equals("openAndclosePlateTime")) {
				financeByKey = oo.getString("value");
			}
		}

		if (!org.springframework.util.StringUtils.isEmpty(financeByKey)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int hours = calendar.get(Calendar.HOUR_OF_DAY);
			int minutes = calendar.get(Calendar.MINUTE);
			String[] split = financeByKey.split(",");
			boolean flag = true;
			for (int i = 0; i < split.length; i++) {
				if (i % 2 == 0) {
					int h = new Integer(split[i].split(":")[0]).intValue();
					int m = new Integer(split[i].split(":")[1]).intValue();
					if (hours == h) {
						if (minutes < m) {
							flag = false;
						}
					}
					if (hours < h) {
						flag = false;
					}
				}
				if (i % 2 == 1) {
					int h = new Integer(split[i].split(":")[0]).intValue();
					int m = new Integer(split[i].split(":")[1]).intValue();

					if (hours == h) {
						if (minutes > m) {
							flag = false;
						}
					}
					if (hours > h) {
						flag = false;
					}
				}

				if (!flag) {
					return flag;
				}
			}

			return flag;
		} else {// 如果缓存为空 直接返回true 让K线正常执行
			return true;
		}

	}

	/**
	 * 获取app_config配置
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Zhang Lei
	 * @param: @param
	 *             type
	 * @param: @return
	 * @return: String
	 * @Date : 2016年12月8日 上午10:50:01
	 * @throws:
	 */
	public static String getCnfigValue(String type) {
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		return remoteAppConfigService.getValueByKey(type);
	}

	/**
	 * 判断是否是周末
	 * 
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ExEntrust> findFirstCoin() {
		ExEntrustDao exEntrustDao = (ExEntrustDao)dao;
		return exEntrustDao.getFirstCoin();
	}

	@Override
	public List<String> getFirstCoinNum() {
		ExEntrustDao exEntrustDao = (ExEntrustDao)dao;
		return exEntrustDao.getFirstCoinNum();
	}
	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCustomer> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String trueName = filter.getRequest().getParameter("trueName");
		String surname = filter.getRequest().getParameter("surname");
		
		String type=filter.getRequest().getParameter("type");
		String entrustType=filter.getRequest().getParameter("entrustType");
		String entrustWay=filter.getRequest().getParameter("entrustWay");
		String coinCode=filter.getRequest().getParameter("coinCode");
		String lentrustTime=filter.getRequest().getParameter("lentrustTime");
		String gentrustTime=filter.getRequest().getParameter("gentrustTime");
		String fixPriceCoinCode=filter.getRequest().getParameter("fixPriceCoinCode");
		String source=filter.getRequest().getParameter("source");
		
		
		if(!StringUtil.isEmpty(source)){
			map.put("source", source);
		}
		
		if(!StringUtil.isEmpty(type)){
			map.put("type", type);
		}
		if(!StringUtil.isEmpty(entrustWay)){
			map.put("entrustWay", entrustWay);
		}
		if(!StringUtil.isEmpty(entrustType)){
			map.put("entrustType", entrustType);
		}
		if(!StringUtil.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		if(!StringUtil.isEmpty(lentrustTime)){
			filter.addFilter("entrustTime>=",lentrustTime);
		}
		if(!StringUtil.isEmpty(gentrustTime)){
			filter.addFilter("entrustTime<=",gentrustTime);
		}
		if(!StringUtil.isEmpty(fixPriceCoinCode)){
			map.put("fixPriceCoinCode", fixPriceCoinCode);
		}
		
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(surname)){
			map.put("surname", "%"+surname+"%");
		}
		
		
		
		((ExEntrustDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}
	
	@Override
	public PageResult findPageBySqlList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		// 分页参数处理
		String startStr = filter.getRequest().getParameter("start");
		String lengthStr = filter.getRequest().getParameter("length");
		Integer startpage = Integer.valueOf(startStr);
		Integer lengthpage = Integer.valueOf(lengthStr);
		if( lengthpage == null || lengthpage == 0 ){
			lengthpage = 10;
		}
		startpage = startpage/lengthpage;
		// 分页参数处理结束
		
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);
		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();
	    Integer start = startpage * lengthpage;
		//Integer end = (startpage + 1) * lengthpage;
		map.put("start", start);
		map.put("end", lengthpage);
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String trueName = filter.getRequest().getParameter("trueName");
		String surname = filter.getRequest().getParameter("surname");
		
		String type=filter.getRequest().getParameter("type");
		String entrustType=filter.getRequest().getParameter("entrustType");
		String entrustWay=filter.getRequest().getParameter("entrustWay");
		String coinCode=filter.getRequest().getParameter("coinCode");
		String lentrustTime=filter.getRequest().getParameter("lentrustTime");
		String gentrustTime=filter.getRequest().getParameter("gentrustTime");
		String fixPriceCoinCode=filter.getRequest().getParameter("fixPriceCoinCode");
		String source=filter.getRequest().getParameter("source");
		String entrustNum = filter.getRequest().getParameter("entrustNum");
		
		
		if(!StringUtil.isEmpty(source)){
			map.put("source", source);
		}
		if(!StringUtil.isEmpty(type)){
			map.put("type", type);
		}
		if(!StringUtil.isEmpty(entrustWay)){
			map.put("entrustWay", entrustWay);
		}
		if(!StringUtil.isEmpty(entrustType)){
			map.put("entrustType", entrustType);
		}
		if(!StringUtil.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		if(!StringUtil.isEmpty(lentrustTime)){
			map.put("entrustTimeEgt",lentrustTime);
		}
		if(!StringUtil.isEmpty(gentrustTime)){
			map.put("entrustTimeElt",gentrustTime);
		}
		if(!StringUtil.isEmpty(fixPriceCoinCode)){
			map.put("fixPriceCoinCode", fixPriceCoinCode);
		}
		if(!StringUtil.isEmpty(entrustNum)){
			map.put("entrustNum", entrustNum);
		}
		
		Map<String,Object> mapcustomer = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			mapcustomer.put("email", email);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			mapcustomer.put("mobilePhone", mobilePhone);
		}
		if(!StringUtils.isEmpty(trueName)){
			mapcustomer.put("trueName", trueName);
		}
		if(!StringUtils.isEmpty(surname)){
			mapcustomer.put("surname", surname);
		}
		if(mapcustomer.size()>0){
			List<String> listpersoninfo = ((ExEntrustDao)dao).findCustomerByCondition(mapcustomer);
			if(listpersoninfo != null && listpersoninfo.size()>0){
				map.put("customerId", listpersoninfo);
			}else{
				List<ExEntrust> list = new ArrayList<ExEntrust>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
			}
		}
		
		Long count = ((ExEntrustDao)dao).findPageBySqlCount(map);
		List<ExEntrust>  list = ((ExEntrustDao)dao).findPageBySqlList(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);
	
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}

}
