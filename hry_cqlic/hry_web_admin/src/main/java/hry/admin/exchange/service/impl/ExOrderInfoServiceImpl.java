/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:26:42 
 */
package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.admin.exchange.dao.ExOrderInfoDao;
import hry.admin.exchange.model.Coin2;
import hry.admin.exchange.model.ExOrderInfo;
import hry.admin.exchange.service.ExOrderInfoService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExOrderInfoService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 11:26:42  
 */
@Service("exOrderInfoService")
public class ExOrderInfoServiceImpl extends BaseServiceImpl<ExOrderInfo, Long> implements ExOrderInfoService{
	@Resource
	private RedisService redisService;

	@Resource(name="exOrderInfoDao")
	@Override
	public void setDao(BaseDao<ExOrderInfo, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {


		//创建PageResult对象
		Page<ExOrderInfo> page = PageFactory.getPage(filter);


		//----------------------查询开始------------------------------

		String coinCode = filter.getRequest().getParameter("coinCode");
		String fixPriceCoinCode = filter.getRequest().getParameter("fixPriceCoinCode");
		String orderNum = filter.getRequest().getParameter("orderNum");
		String source = filter.getRequest().getParameter("source");

		String transactionCount_LT = (String) filter.getRequest().getParameter("transactionCount_LT");
		String transactionCount_GT = (String) filter.getRequest().getParameter("transactionCount_GT");
		String buyEmail=filter.getRequest().getParameter("buyEmail");
		String buyMobilePhone = filter.getRequest().getParameter("buyMobilePhone");
		String buyEntrustNum = filter.getRequest().getParameter("buyEntrustNum");

		String sellEmail=filter.getRequest().getParameter("sellEmail");
		String sellMobilePhone = filter.getRequest().getParameter("sellMobilePhone");
		String sellEntrustNum = filter.getRequest().getParameter("sellEntrustNum");

		String transactionPrice_LT = filter.getRequest().getParameter("transactionPrice_LT");
		String transactionSum_LT = filter.getRequest().getParameter("transactionSum_LT");
		String transactionTime_LT = filter.getRequest().getParameter("transactionTime_LT");

		String transactionPrice_GT = filter.getRequest().getParameter("transactionPrice_GT");
		String transactionSum_GT = filter.getRequest().getParameter("transactionSum_GT");
		String transactionTime_GT = filter.getRequest().getParameter("transactionTime_GT");

		Map<String,Object> map = new HashMap<>();


		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode+"%");
		}
		if(!StringUtils.isEmpty(fixPriceCoinCode)){
			map.put("fixPriceCoinCode", fixPriceCoinCode+"%");
		}
		if(!StringUtils.isEmpty(orderNum)){
			map.put("orderNum", orderNum+"%");
		}
		if(!StringUtils.isEmpty(source)){
			map.put("source", source);
		}
		if(!StringUtils.isEmpty(transactionCount_LT)){
			map.put("transactionCount_LT", transactionCount_LT);
		}
		if(!StringUtils.isEmpty(transactionCount_GT)){
			map.put("transactionCount_GT", transactionCount_GT);
		}
		if(!StringUtils.isEmpty(buyEmail)){
			map.put("buyEmail", buyEmail+"%");
		}
		if(!StringUtils.isEmpty(buyMobilePhone)){
			map.put("buyMobilePhone", buyMobilePhone+"%");
		}
		if(!StringUtils.isEmpty(buyEntrustNum)){
			map.put("buyEntrustNum", buyEntrustNum+"%");
		}
		if(!StringUtils.isEmpty(sellEmail)){
			map.put("sellEmail", sellEmail+"%");
		}
		if(!StringUtils.isEmpty(sellMobilePhone)){
			map.put("sellMobilePhone", sellMobilePhone+"%");
		}
		if(!StringUtils.isEmpty(sellEntrustNum)){
			map.put("sellEntrustNum", sellEntrustNum+"%");
		}

		if(!StringUtils.isEmpty(transactionPrice_LT)){
			map.put("transactionPrice_LT",transactionPrice_LT);
		}
		if(!StringUtils.isEmpty(transactionSum_LT)){
			map.put("transactionSum_LT",transactionSum_LT);
		}
		if(!StringUtils.isEmpty(transactionTime_LT)){
			map.put("transactionTime_LT",transactionTime_LT);
		}

		if(!StringUtils.isEmpty(transactionPrice_GT)){
			map.put("transactionPrice_GT",transactionPrice_GT);
		}
		if(!StringUtils.isEmpty(transactionSum_GT)){
			map.put("transactionSum_GT",transactionSum_GT);
		}
		if(!StringUtils.isEmpty(transactionTime_GT)){
			map.put("transactionTime_GT",transactionTime_GT);
		}


		((ExOrderInfoDao)dao).findPageBySql(map);

		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}


	@Override
	public PageResult findPageBySqlList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<ExOrderInfo> page = PageFactory.getPage(filter);


		Map<String,Object> map = new HashMap<String,Object>();

		//----------------------查询开始------------------------------

		String coinCode = filter.getRequest().getParameter("coinCode");
		String fixPriceCoinCode = filter.getRequest().getParameter("fixPriceCoinCode");
		String orderNum = filter.getRequest().getParameter("orderNum");
		String source = filter.getRequest().getParameter("source");

		String transactionCount_LT = (String) filter.getRequest().getParameter("transactionCount_LT");
		String transactionCount_GT = (String) filter.getRequest().getParameter("transactionCount_GT");
		String buyEmail=filter.getRequest().getParameter("buyEmail");
		String buyMobilePhone = filter.getRequest().getParameter("buyMobilePhone");
		String buyEntrustNum = filter.getRequest().getParameter("buyEntrustNum");

		String sellEmail=filter.getRequest().getParameter("sellEmail");
		String sellMobilePhone = filter.getRequest().getParameter("sellMobilePhone");
		String sellEntrustNum = filter.getRequest().getParameter("sellEntrustNum");

		String transactionPrice_LT = filter.getRequest().getParameter("transactionPrice_LT");
		String transactionSum_LT = filter.getRequest().getParameter("transactionSum_LT");
		String transactionTime_LT = filter.getRequest().getParameter("transactionTime_LT");

		String transactionPrice_GT = filter.getRequest().getParameter("transactionPrice_GT");
		String transactionSum_GT = filter.getRequest().getParameter("transactionSum_GT");
		String transactionTime_GT = filter.getRequest().getParameter("transactionTime_GT");

		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode+"%");
		}
		if(!StringUtils.isEmpty(fixPriceCoinCode)){
			map.put("fixPriceCoinCode", fixPriceCoinCode+"%");
		}
		if(!StringUtils.isEmpty(orderNum)){
			map.put("orderNum", orderNum+"%");
		}
		if(!StringUtils.isEmpty(source)){
			map.put("source", source);
		}
		if(!StringUtils.isEmpty(transactionCount_LT)){
			map.put("transactionCount_LT", transactionCount_LT);
		}
		if(!StringUtils.isEmpty(transactionCount_GT)){
			map.put("transactionCount_GT", transactionCount_GT);
		}
		if(!StringUtils.isEmpty(buyEntrustNum)){
			map.put("buyEntrustNum", buyEntrustNum+"%");
		}
		if(!StringUtils.isEmpty(sellEntrustNum)){
			map.put("sellEntrustNum", sellEntrustNum+"%");
		}

		if(!StringUtils.isEmpty(transactionPrice_LT)){
			map.put("transactionPrice_LT",transactionPrice_LT);
		}
		if(!StringUtils.isEmpty(transactionSum_LT)){
			map.put("transactionSum_LT",transactionSum_LT);
		}
		if(!StringUtils.isEmpty(transactionTime_LT)){
			map.put("transactionTime_LT",transactionTime_LT);
		}

		if(!StringUtils.isEmpty(transactionPrice_GT)){
			map.put("transactionPrice_GT",transactionPrice_GT);
		}
		if(!StringUtils.isEmpty(transactionSum_GT)){
			map.put("transactionSum_GT",transactionSum_GT);
		}
		if(!StringUtils.isEmpty(transactionTime_GT)){
			map.put("transactionTime_GT",transactionTime_GT);
		}

		Map<String,Object> mapbuy = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(buyEmail)){
			mapbuy.put("email", buyEmail);
		}
		if(!StringUtils.isEmpty(buyMobilePhone)){
			mapbuy.put("mobilePhone", buyMobilePhone);
		}


		Map<String,Object> mapsell = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(sellEmail)){
			mapsell.put("email", sellEmail);
		}
		if(!StringUtils.isEmpty(sellMobilePhone)){
			mapsell.put("mobilePhone", sellMobilePhone);
		}


		((ExOrderInfoDao)dao).findPageBySqlList(map);
		//----------------------查询结束------------------------------


		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	/**
	 * 获取昨日新增交易量
	 * @return
	 */
	@Override
	public BigDecimal getYesterdayTreads (String flag) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flag", flag);
		map.put("buyEntrustNum", "opponentExEntrust");
		map.put("sellEntrustNum", "opponentExEntrust");
		List<ExOrderInfo> orderList = ((ExOrderInfoDao)dao).getYesterdayTreads(map);
		BigDecimal sum = new BigDecimal(0);
		if (orderList != null && orderList.size() > 0) {
			for (ExOrderInfo order : orderList) {
				if ("USDT".equals(order.getFixPriceCoinCode())) {
					sum = sum.add(order.getTransactionSum());
				} else {
					BigDecimal usdtM = getCurrentExchangPrice(order.getFixPriceCoinCode(), "USDT");
					sum = sum.add(usdtM.multiply(order.getTransactionSum()));
				}
			}
		}
		sum = sum.setScale(0, BigDecimal.ROUND_HALF_UP);
		return sum;
	}

	/**
	 * @Function: MiningController.java
	 * @Description: 去对应币种与usdt的昨日收盘价格进行兑换
	 * @author: zjj
	 * @date: 2018年8月9日 上午11:43:12
	 */
	private BigDecimal getCurrentExchangPrice(String coincode, String fixPriceCoinCode) {
		String coinStr = redisService.get("cn:coinInfoList2");
		String coinCode = coincode + "_" + fixPriceCoinCode;
		BigDecimal yesterdayPrice = new BigDecimal(0);
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
}
