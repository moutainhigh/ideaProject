/**
 * Copyright:
 * @author:      liuchenghui
 * @version:     V1.0
 * @Date:        2018-10-08 13:45:53
 */
package hry.admin.index.service.impl;

import hry.admin.index.model.AppYesterdayData;
import hry.admin.index.service.AppYesterdayDataService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p> AppYesterdayDataService </p>
 * @author:         liuchenghui
 * @Date :          2018-10-08 13:45:53
 */
@Service("appYesterdayDataService")
public class AppYesterdayDataServiceImpl extends BaseServiceImpl<AppYesterdayData, Long> implements AppYesterdayDataService{

	@Resource
	private RedisService redisService;

	@Resource(name="appYesterdayDataDao")
	@Override
	public void setDao(BaseDao<AppYesterdayData, Long> dao) {
		super.dao = dao;
	}


	@Override
	public void initRedis () {
		// 获取数据库中的数据
		List<AppYesterdayData> yesterdayDataList = super.findAll();
		AppYesterdayData appYesterdayData = null;
		if (yesterdayDataList != null && yesterdayDataList.size() > 0) {
			appYesterdayData = yesterdayDataList.get(0);
		}
		if (appYesterdayData != null) {
			// 截止昨日交易量
			redisService.save("HomePage:yesterday_trade", appYesterdayData.getTotalTradeMoney().setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
			// 昨日新增用户
			redisService.save("HomePage:new_customer", appYesterdayData.getNewCustomer().setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
			// 昨日新增交易量
			redisService.save("HomePage:new_trade", appYesterdayData.getNewTrades().setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
			// 昨日充币数
			redisService.save("HomePage:new_postCoin", appYesterdayData.getNewPost().setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
			// 昨日提币数
			redisService.save("HomePage:new_getCoin", appYesterdayData.getNewGet().setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
			// 昨日挖矿数
			redisService.save("HomePage:new_mining", appYesterdayData.getNewMining().setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
			// 昨日分红数
			redisService.save("HomePage:new_dividend", appYesterdayData.getNewDividends().setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
		} else {
			// 截止昨日交易量
			redisService.save("HomePage:yesterday_trade", "0");
			// 昨日新增用户
			redisService.save("HomePage:new_customer", "0");
			// 昨日新增交易量
			redisService.save("HomePage:new_trade", "0");
			// 昨日充币数
			redisService.save("HomePage:new_postCoin", "0");
			// 昨日提币数
			redisService.save("HomePage:new_getCoin", "0");
			// 昨日挖矿数
			redisService.save("HomePage:new_mining", "0");
			// 昨日分红数
			redisService.save("HomePage:new_dividend", "0");
		}
	}

	/**
	 * 不足用0在左边补位
	 * @param str
	 * @param strLength
	 * @return
	 */
	private String addZeroForNum (String str, int strLength) {
		int strLen = str.length();
		StringBuffer sb = null;
		while (strLen < strLength) {
			sb = new StringBuffer();
			sb.append("0").append(str);// 左补0
			str = sb.toString();
			strLen = str.length();
		}
		return str;
	}
}
