package hry.trade.robot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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

import hry.bean.JsonResult;
import hry.core.constant.StringConstant;
import hry.core.thread.ThreadPool;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.product.model.ExCointoCoin;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.Mapper;
import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.MQmanager.MQEnter;
import hry.trade.account.service.ExDigitalmoneyAccountService;
import hry.trade.entrust.dao.CommonDao;
import hry.trade.entrust.dao.ExEntrustDao;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.impl.UserRedisInitUtil;
import hry.trade.model.Coin;
import hry.trade.model.CoinKeepDecimal;
import hry.trade.model.TradeRedis;
import hry.trade.mq.service.MessageProducer;
import hry.trade.redis.model.EntrustByUser;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.trade.robot.model.Coin2;
import hry.trade.robot.model.ExRobot;
import hry.trade.robot.model.ExRobotDeep;
import hry.trade.robot.model.TransactionOrder;
import hry.trade.robot.service.RobotDepthDetail2Service;
import hry.trade.robot.service.RobotDepthDetailService;
import hry.trade.robot.service.RobotDepthService;
import hry.trade.robot.service.RobotService;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import tk.mybatis.mapper.util.StringUtil;

@Service("robotDepthService")
public class RobotDepthServiceImpl implements RobotDepthService {
	@Resource
	private RedisService redisService;
	@Resource
	public CommonDao commonDao;
	@Resource
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	public ExEntrustDao exEntrustDao;
	@Resource
	public RobotDepthDetail2Service robotDepthDetail2Service;
	public static String  time=null;
  
    
	public List<Map<String, List<EntrustTrade>>> findExEntrustBycust(Long customerId) {
		List<ExCointoCoin> list =commonDao.getExointocoinValid();
		List<Map<String, List<EntrustTrade>>> listml = new ArrayList<Map<String, List<EntrustTrade>>>();
		Map<String, List<EntrustTrade>> entrustedmap = new HashMap<String, List<EntrustTrade>>();
		Map<String, List<EntrustTrade>> entrustingmap = new HashMap<String, List<EntrustTrade>>();
		for (ExCointoCoin ec : list) {
			Map<String, Object> maping = new HashMap<String, Object>();
			maping.put("customerId", customerId);
			maping.put("coinCode", ec.getCoinCode());
			maping.put("fixPriceCoinCode", ec.getFixPriceCoinCode());
			maping.put("counting", EntrustByUser.ingMAXsize);
			List<ExEntrust> listing = exEntrustDao.getExIngBycustomerId2(maping);
			String listings = Mapper.objectToJson(listing);
			List<EntrustTrade> entrustTradinglist = JSON.parseArray(listings, EntrustTrade.class);
			entrustingmap.put(ec.getCoinCode() + "_" + ec.getFixPriceCoinCode(), entrustTradinglist);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", customerId);
			map.put("coinCode", ec.getCoinCode());
			map.put("fixPriceCoinCode", ec.getFixPriceCoinCode());
			map.put("counted", EntrustByUser.edMAXsize);
			List<ExEntrust> listied = exEntrustDao.getExEdBycustomerId2(map);
			String listeds = Mapper.objectToJson(listied);
			List<EntrustTrade> entrustTradedlist = JSON.parseArray(listeds, EntrustTrade.class);
			entrustedmap.put(ec.getCoinCode() + "_" + ec.getFixPriceCoinCode(), entrustTradedlist);

		}
		listml.add(entrustedmap);
		listml.add(entrustingmap);
		return listml;
	}
    @Override
    public void repairdeep() {
    	String isStop = redisService.get("deal:stop");
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(isStop)){
			System.out.println("repairdeep出现异常deal:stop="+isStop);
			return ;
		}
    	List<ExRobotDeep> listExRobotDeep = commonDao.getExRobotDeepList(1);
    	HashSet<Long> set = new HashSet<Long>();
		for (ExRobotDeep exRobot : listExRobotDeep) {
			   set.add(exRobot.getCustomerId());
		}
    	
		for (Long customerId : set) {  
		      System.out.println("customerId=="+customerId); 
		      //缓存委托历史记录和当前记录
		 		RedisUtil<EntrustByUser>  redisUtilEntrustByUser = new RedisUtil<EntrustByUser>(EntrustByUser.class);
		 		List<Map<String, List<EntrustTrade>>> listml=findExEntrustBycust(customerId);
		 		EntrustByUser ebu = new EntrustByUser();
		 		ebu.setCustomerId(customerId);
		 		ebu.setEntrustedmap(listml.get(0));
		 		ebu.setEntrustingmap(listml.get(1));
		 		redisUtilEntrustByUser.put(ebu, customerId.toString());
		}  
	     
	 		
    	
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
	/**
	 * 自动刷单
	 */
    public static int entrustType = 1;
	@Override
	public void deepRobot() {
		

		long start=System.currentTimeMillis();
		System.out.println("deepRobot进入深度机器人");
		String isStop = redisService.get("deal:stop");
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(isStop)){
			System.out.println("出现异常deal:stop="+isStop);
			return ;
		}
		
		
		long sleeptiem = getTime().longValue();

		try {
			Thread.sleep(sleeptiem);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<ExRobotDeep> listExRobotDeep = commonDao.getExRobotDeepList(1);
		int max = 60;// 最多能走10个机器人，1秒1单那就是总共1秒10单
		int maxjs = 1;
		for (ExRobotDeep exRobot : listExRobotDeep) {
			if (maxjs > max) {
				return;
			}
			
			
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
					maxjs++;
					String key = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode();
				/*	Boolean[] arr=isCanEntrustBykline(key);
					if(arr[0]){
						buyEntrust(exRobot,autoUsername);
					}
					if(arr[1]){
						sellEntrust(exRobot,autoUsername);
					}*/
			/*		BigDecimal[] a = getFloatNum1(new BigDecimal("80"), new BigDecimal("90"));
					try {
						Thread.sleep(a[1].longValue());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					robotDepthDetail2Service.deepRobot(exRobot,autoUsername,entrustType);
					
				}

			}
		}
		if (entrustType == 1) {
			// 生成刷单价格1
			entrustType = 2;
		} else {
			entrustType = 1;
		}
		long end=System.currentTimeMillis();
		System.out.println("s深度机器人执行的时间"+(end -start));
	}
	  public List<TransactionOrder>  get1MinKLine(String coinkey){
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		  String table = coinkey+":klinedata:TransactionOrder_" + coinkey + "_" + 1;
		  List<TransactionOrder> periodKData = JSON.parseArray(redisService.get(table), TransactionOrder.class);
		  return periodKData;
	  }
	public BigDecimal getTime() {
		BigDecimal[] a = getFloatNum1(new BigDecimal("1500"), new BigDecimal("90"));
		return a[1];
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


	


	@Override
	public void deepCancelAutoAddExEntrust() {
		String isStop = redisService.get("deal:stop");
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(isStop)){
			System.out.println("deepCancelAutoAddExEntrust出现异常deal:stop="+isStop);
			return ;
		}
		List<ExRobotDeep> listExRobotDeep = commonDao.getExRobotDeepList(1);
		for (ExRobotDeep exRobot : listExRobotDeep) {
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
					if(null!=exRobot.getBuyDeep()&&null!=exRobot.getSellDeep()){
						
						entrustTrade.setCancelKeepN(exRobot.getBuyDeep()+exRobot.getSellDeep());
						if(entrustTrade.getCancelKeepN()>30){
							
							entrustTrade.setCancelKeepN(30);
						}
					}else{
						entrustTrade.setCancelKeepN(10);
					}
					// 序列化
					String str = JSON.toJSONString(entrustTrade);
					MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
					// 发送消息
					messageProducer.toTrade(str);
				}
			}
		}

	}

	
}
