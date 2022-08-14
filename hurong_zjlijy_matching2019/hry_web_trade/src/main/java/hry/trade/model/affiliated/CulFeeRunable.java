package hry.trade.model.affiliated;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hry.util.sys.ContextUtil;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.redis.model.EntrustByUser;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tk.mybatis.mapper.util.StringUtil;

public class CulFeeRunable implements Runnable {


	private List<ExOrderInfo> listExOrderInfo;
	public CulFeeRunable(List<ExOrderInfo> listExOrderInfo){
		this.listExOrderInfo=listExOrderInfo;
	}
	@Override
	public void run() {

		setTotalFee(listExOrderInfo);
	}
	public static void setTotalFee(List<ExOrderInfo> listExOrderInfo){
		JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
		Jedis jedis = jedisPool.getResource();
		try{
			for(ExOrderInfo exOrderInfo :listExOrderInfo){
				getTotalFeeByOrderNum(exOrderInfo,jedis);
			}
		} catch (Exception e){
		} finally {
			jedis.close();
		}

	}
	/**
	 * 根据订单号 查询卖方和买方手续费是否查过限制额数
	 * @param orderNum
	 * @return customerId,transactionTime
	 */
	public static void getTotalFeeByOrderNum(ExOrderInfo exOrderInfo,Jedis jedis) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BigDecimal buyFee = new BigDecimal(0);
		BigDecimal sellFee = new BigDecimal(0);
		try {
			String config = jedis.get("configCache:all");
			JSONObject parseObject = JSONObject.parseObject(config);
			String limitTurnover = (String) parseObject.get("limitTurnover");//限制成交额
			String coinCodeForRmb = (String) parseObject.get("coinCodeForRmb");//基础币

			String endTime = sdf.format(new Date())+" 23:59:59";
			Date start = new Date();
			Date end = sdf1.parse(endTime);
			//获取毫秒数
			Long startLong = start.getTime();
			Long endLong = end.getTime();
			//计算时间差,单位毫秒
			Long ms = endLong-startLong;
			//时间差转换为 \天\时\分\秒
			Long surplus = ms /1000;

			if(coinCodeForRmb.equals(exOrderInfo.getCoinCode())){
				//计算卖单手续费
				sellFee = sellFee.multiply(exOrderInfo.getTransactionSellFee()).multiply(exOrderInfo.getTransactionPrice());
				//计算买单手续费
				buyFee = exOrderInfo.getTransactionBuyFee();
			} else {
				//计算卖单手续费
				if(!coinCodeForRmb.equals(exOrderInfo.getFixPriceCoinCode())){
					sellFee = getCurrentExchangPrice(exOrderInfo.getFixPriceCoinCode(),coinCodeForRmb,jedis);
					sellFee = sellFee.multiply(exOrderInfo.getTransactionSellFee());
				} else {
					sellFee = exOrderInfo.getTransactionSellFee();
				}

				//计算买单手续费
				if(!coinCodeForRmb.equals(exOrderInfo.getFixPriceCoinCode())){
					buyFee = getCurrentExchangPrice(exOrderInfo.getFixPriceCoinCode(),coinCodeForRmb,jedis);
					buyFee = buyFee.multiply(exOrderInfo.getTransactionBuyFee()).multiply(exOrderInfo.getTransactionPrice());
				} else {
					buyFee = exOrderInfo.getTransactionBuyFee().multiply(exOrderInfo.getTransactionPrice());
				}
			}


			//判断是否是和自己成交的订单
			if(exOrderInfo.getBuyCustomId().equals(exOrderInfo.getSellCustomId())){
				String ifLimit = jedis.get("Mining:Limit"+exOrderInfo.getBuyCustomId().toString());
				String limitFee = jedis.hget("Mining:LimitFee",exOrderInfo.getBuyCustomId().toString());
				if(StringUtils.isEmpty(ifLimit)){//是否已经存在
					BigDecimal decimal = buyFee.add(sellFee);
					if(!StringUtil.isEmpty(limitFee)){
						decimal = decimal.add(new BigDecimal(limitFee));
					}
					if(decimal.compareTo(new BigDecimal(limitTurnover)) >= 0){//如果超过限制额，则存储订单时间 同时删除掉改用户的手续费累计
						//存的是距离当天23:59:59 还有多少秒  过了这个时间则自动删除
						jedis.set("Mining:Limit"+exOrderInfo.getBuyCustomId().toString(),sdf1.format(exOrderInfo.getTransactionTime()));
						jedis.expire("Mining:Limit"+exOrderInfo.getBuyCustomId().toString(),surplus.intValue());
						jedis.hdel("Mining:LimitFee",exOrderInfo.getBuyCustomId().toString());
					} else {
						jedis.hset("Mining:LimitFee",exOrderInfo.getBuyCustomId().toString(),decimal.toString());
					}
				}
			} else {//如果买方卖方不是一个人，则分开计算
				String buyifLimit = jedis.get("Mining:Limit"+exOrderInfo.getBuyCustomId().toString());
				String sellifLimit = jedis.get("Mining:Limit"+exOrderInfo.getSellCustomId().toString());
				String limitBuyFee = jedis.hget("Mining:LimitFee",exOrderInfo.getBuyCustomId().toString());
				String limitSellFee = jedis.hget("Mining:LimitFee",exOrderInfo.getSellCustomId().toString());
				//买方
				if(StringUtil.isEmpty(buyifLimit)){
					BigDecimal BuyFee = new BigDecimal(0);
					if(!StringUtil.isEmpty(limitBuyFee)){
						BuyFee = buyFee.add(new BigDecimal(limitBuyFee));
					} else {
                        BuyFee = buyFee;
                    }
					if(BuyFee.compareTo(new BigDecimal(limitTurnover)) >= 0){//如果超过限制额，则存储订单时间 同时删除掉改用户的手续费累计
						//存的是距离当天23:59:59 还有多少秒  过了这个时间则自动删除
						jedis.set("Mining:Limit"+exOrderInfo.getBuyCustomId().toString(),sdf1.format(exOrderInfo.getTransactionTime()));
						jedis.expire("Mining:Limit"+exOrderInfo.getBuyCustomId().toString(),surplus.intValue());
						jedis.hdel("Mining:LimitFee",exOrderInfo.getBuyCustomId().toString());
					} else {
						jedis.hset("Mining:LimitFee",exOrderInfo.getBuyCustomId().toString(),BuyFee.toString());
					}
				}
				//卖方
				if(StringUtil.isEmpty(sellifLimit)){
					BigDecimal SellFee = new BigDecimal(0);
					if(!StringUtil.isEmpty(limitSellFee)){
						SellFee = sellFee.add(new BigDecimal(limitSellFee));
					} else {
                        SellFee = sellFee;
                    }
					if(SellFee.compareTo(new BigDecimal(limitTurnover)) >= 0){//如果超过限制额，则存储订单时间 同时删除掉改用户的手续费累计
						//存的是距离当天23:59:59 还有多少秒  过了这个时间则自动删除
						jedis.set("Mining:Limit"+exOrderInfo.getSellCustomId().toString(),sdf1.format(exOrderInfo.getTransactionTime()));
						jedis.expire("Mining:Limit"+exOrderInfo.getSellCustomId().toString(),surplus.intValue());
						jedis.hdel("Mining:LimitFee",exOrderInfo.getSellCustomId().toString());
					} else {
						jedis.hset("Mining:LimitFee",exOrderInfo.getSellCustomId().toString(),SellFee.toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static BigDecimal getCurrentExchangPrice(String coinCode,String fixPriceCoinCode,Jedis jedis){
		String header = coinCode + "_" + fixPriceCoinCode;
		String key=header + ":" + ExchangeDataCacheRedis.CurrentExchangPrice;
		String v = jedis.get(key);
		if(StringUtil.isEmpty(v)||new BigDecimal(v).compareTo(new BigDecimal("0"))==0){
			return null;
		}else{
			return new BigDecimal(v);
		}

	}
}
