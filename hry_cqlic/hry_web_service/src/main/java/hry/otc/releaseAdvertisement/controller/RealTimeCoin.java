package hry.otc.releaseAdvertisement.controller;//package hry.app.otc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import hry.bean.FrontPage;
import hry.util.sys.ContextUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import hry.otc.remote.RemoteAdvertisementService;
import hry.otc.remote.model.OtcAppTransactionRemote;
import hry.redis.common.utils.RedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import tk.mybatis.mapper.util.StringUtil;

@Component
@EnableScheduling
public class RealTimeCoin {
	@Resource
	private RedisService redisService;
	static Integer offset = 0;
    static Integer limit=10;
	/**
	 * 30秒钟跑一次取消订单
	 */
	@Scheduled(fixedRate = 30000)
	public void cancelTransaction() {
		findPageWait();
	}

	public static void main(String[] args) {
		Jedis j = new Jedis("192.168.10.128",6379);
		j.auth("Credit2016Admin");

		Set<Tuple> tuples = j.zrangeWithScores("BCH_ETH:newklinedata:TransactionOrder_BCH_ETH_5", 0, 0);
		for (Tuple tuple : tuples) {
			System.out.println(tuple.getElement() + ":" + new BigDecimal(tuple.getScore()).toString());
		}
		System.out.println(tuples);

	}

	private void findPageWait() {
		//获取索引为0的第一个节点，如果条件不满足，就没必要往后循环了，否则就循环处理
		int start = 0;
		while (true){
			Set<Tuple> tuples = redisService.zrangeWithScores("otc:tradeNum", start, start);
			if(tuples.size() > 0){
				String tradeNum = "";
				String score = "";
				for (Tuple tuple : tuples) {
					tradeNum = tuple.getElement().toString();
					score = new BigDecimal(tuple.getScore()).toString();
				}

				if(System.currentTimeMillis() / 1000 > (Long.valueOf(score))){ // 过期
					RemoteAdvertisementService remoteAdvertisementService = (RemoteAdvertisementService)ContextUtil.getBean("remoteAdvertisementService");
					remoteAdvertisementService.recoveryReleaseAdvertisement(tradeNum,0L);// 取消订单

					// 删除该订单
					redisService.zrem("otc:tradeNum", tradeNum);
				}else{
					break;
				}

				start++;
			}
			break;
		}
	}

	/**
	 * bithumb网BTC价格
	 */
	/*@Scheduled(fixedRate = 300000)
	public void bithumbBTCPrice() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RedisService redisService = SpringUtil.getBean("redisService");
		String sendUrl = "";
		try {
			String url = PropertiesUtils.APP.getProperty("bithumb_btc");
			sendUrl = WebClient.getWebContentByGet(url, "utf-8", 60000);
			JSONObject jsonv = JSON.parseObject(sendUrl);
			String key = PropertiesUtils.APP.getProperty("bithumb_price");
			JSONObject tick = (JSONObject) jsonv.get("data");
			String price = tick.getString(key);
			// 1韩元=0.0009341美元
			if (price != null) {
				BigDecimal p = new BigDecimal(price);
				String koreaUsa = PropertiesUtils.APP.getProperty("korea_usa");
				BigDecimal usd = new BigDecimal(koreaUsa);
				price = usdtToRmb().multiply(p.multiply(usd).setScale(4, BigDecimal.ROUND_HALF_UP)).toString();
				redisService.save("bithumb_btc_price", price);
				System.out.println("-------------------" + sd.format(new Date()) + "-----当前BTC价格：" + price
						+ "美元----------------------------");
			}

		} catch (Exception e) {
			System.out.println("访问bithumb网出错");
		}
	}*/

	/**
	 * bithumb网LTC价格
	 */
	/*@Scheduled(fixedRate = 300000)
	public void bithumbLTCPrice() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RedisService redisService = SpringUtil.getBean("redisService");
		String sendUrl = "";
		try {
			String url = PropertiesUtils.APP.getProperty("bithumb_ltc");
			sendUrl = WebClient.getWebContentByGet(url, "utf-8", 60000);
			JSONObject jsonv = JSON.parseObject(sendUrl);
			String key = PropertiesUtils.APP.getProperty("bithumb_price");
			JSONObject tick = (JSONObject) jsonv.get("data");
			String price = tick.getString(key);
			// 1韩元=0.0009341美元
			if (price != null) {
				BigDecimal p = new BigDecimal(price);
				String koreaUsa = PropertiesUtils.APP.getProperty("korea_usa");
				BigDecimal usd = new BigDecimal(koreaUsa);
				price = usdtToRmb().multiply(p.multiply(usd).setScale(4, BigDecimal.ROUND_HALF_UP)).toString();
				redisService.save("bithumb_ltc_price", price);
				System.out.println("-------------------" + sd.format(new Date()) + "-----当前LTC价格：" + price
						+ "美元----------------------------");
			}
		} catch (Exception e) {
			System.out.println("访问bithumb网出错");
		}
	}*/

	/**
	 * bithumb网ETH价格
	 */
	/*@Scheduled(fixedRate = 300000)
	public void bithumbETHPrice() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RedisService redisService = SpringUtil.getBean("redisService");
		String sendUrl = "";
		try {
			String url = PropertiesUtils.APP.getProperty("bithumb_eth");
			sendUrl = WebClient.getWebContentByGet(url, "utf-8", 60000);
			JSONObject jsonv = JSON.parseObject(sendUrl);
			String key = PropertiesUtils.APP.getProperty("bithumb_price");
			JSONObject tick = (JSONObject) jsonv.get("data");
			String price = tick.getString(key);
			// 1韩元=0.0009341美元
			if (price != null) {
				BigDecimal p = new BigDecimal(price);
				String koreaUsa = PropertiesUtils.APP.getProperty("korea_usa");
				BigDecimal usd = new BigDecimal(koreaUsa);
				price = usdtToRmb().multiply(p.multiply(usd).setScale(4, BigDecimal.ROUND_HALF_UP)).toString();
				redisService.save("bithumb_eth_price", price);
				System.out.println("-------------------" + sd.format(new Date()) + "-----当前ETH价格：" + price
						+ "美元----------------------------");
			}
		} catch (Exception e) {
			System.out.println("访问bithumb网出错");
		}
	}*/


	/**
	 * huobi网BTC价格
	 */
	/*@Scheduled(fixedRate = 300000)
	public void huobiBTCPrice() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RedisService redisService = SpringUtil.getBean("redisService");
		String sendUrl = "";
		try {
			String url = PropertiesUtils.APP.getProperty("huobi_btc");
			sendUrl = WebClient.getWebContentByGet(url, "utf-8", 60000);
			JSONObject jsonv = JSON.parseObject(sendUrl);
			String key = PropertiesUtils.APP.getProperty("huobi_price");
			JSONObject tick = (JSONObject) jsonv.get("tick");
			JSONArray data = (JSONArray) tick.get("data");
			String price = data.getJSONObject(0).getString(key);

			if (price != null) {
				BigDecimal p = new BigDecimal(price);
				price = usdtToRmb().multiply(p.setScale(4, BigDecimal.ROUND_HALF_UP)).toString();
				redisService.save("huobi_btc_price", price);
				System.out.println("-------------------" + sd.format(new Date()) + "-----huobi当前BTC价格：" + price
						+ "美元----------------------------");
			}

		} catch (Exception e) {
			System.out.println("访问huobi网出错");
		}
	}*/

	/**
	 * huobi网BTC价格
	 */
	/*@Scheduled(fixedRate = 300000)
	public void huobiLTCPrice() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RedisService redisService = SpringUtil.getBean("redisService");
		String sendUrl = "";
		try {
			String url = PropertiesUtils.APP.getProperty("huobi_ltc");
			sendUrl = WebClient.getWebContentByGet(url, "utf-8", 60000);
			JSONObject jsonv = JSON.parseObject(sendUrl);
			String key = PropertiesUtils.APP.getProperty("huobi_price");
			JSONObject tick = (JSONObject) jsonv.get("tick");
			JSONArray data = (JSONArray) tick.get("data");
			String price = data.getJSONObject(0).getString(key);
			if (price != null) {
				BigDecimal p = new BigDecimal(price);
				price = usdtToRmb().multiply(p.setScale(4, BigDecimal.ROUND_HALF_UP)).toString();
				redisService.save("huobi_ltc_price", price);
				System.out.println("-------------------" + sd.format(new Date()) + "-----huobi当前LTC价格：" + price
						+ "美元----------------------------");
			}
		} catch (Exception e) {
			System.out.println("访问huobi网出错");
		}
	}*/

	/**
	 * huobi网ETH价格
	 */
	/*@Scheduled(fixedRate = 300000)
	public void huobiETHPrice() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RedisService redisService = SpringUtil.getBean("redisService");
		String sendUrl = "";
		try {
			String url = PropertiesUtils.APP.getProperty("huobi_eth");
			sendUrl = WebClient.getWebContentByGet(url, "utf-8", 60000);
			JSONObject jsonv = JSON.parseObject(sendUrl);
			String key = PropertiesUtils.APP.getProperty("huobi_price");
			JSONObject tick = (JSONObject) jsonv.get("tick");
			JSONArray data = (JSONArray) tick.get("data");
			String price = data.getJSONObject(0).getString(key);
			if (price != null) {
				BigDecimal p = new BigDecimal(price);
				price = usdtToRmb().multiply(p.setScale(4, BigDecimal.ROUND_HALF_UP)).toString();
				redisService.save("huobi_eth_price", price);
				System.out.println("-------------------" + sd.format(new Date()) + "-----huobi当前ETH价格：" + price
						+ "美元----------------------------");
			}
		} catch (Exception e) {
			System.out.println("访问huobi网出错");
		}
	}*/

	/*public static void main(String[] args) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// RedisService redisService = SpringUtil.getBean("redisService");
		String sendUrl = "";
		try {
			String url = PropertiesUtils.APP.getProperty("huobi_ltc");
			sendUrl = WebClient.getWebContentByGet(url, "utf-8", 60000);
			JSONObject jsonv = JSON.parseObject(sendUrl);
			String key = PropertiesUtils.APP.getProperty("huobi_price");
			JSONObject tick = (JSONObject) jsonv.get("tick");
			JSONArray data = (JSONArray) tick.get("data");
			String price = data.getJSONObject(0).getString(key);
			if (price != null) {
				BigDecimal p = new BigDecimal(price);
				price = p.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
				// redisService.save("huobi_ltc_price", price);
				System.out.println("-------------------" + sd.format(new Date()) + "-----huobi当前LTC价格：" + price
						+ "美元----------------------------");
			}
		} catch (Exception e) {
			System.out.println("访问huobi网出错");
		}
	}*/
}
