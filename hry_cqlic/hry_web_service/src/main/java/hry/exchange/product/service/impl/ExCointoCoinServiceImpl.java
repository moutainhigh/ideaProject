/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      gaomimi
 * @version:     V1.0 
 * @Date:        2017-07-06 19:40:34 
 */
package hry.exchange.product.service.impl;

import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.model.AppConfig;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.exchange.product.dao.ExCointoCoinDao;
import hry.exchange.product.model.ExCointoCoin;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.model.ExProductVo;
import hry.exchange.product.service.ExCointoCoinService;
import hry.exchange.product.service.ExProductService;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.Coin2;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.ExchangeDataCache;
import hry.web.app.service.AppConfigService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tk.mybatis.mapper.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * <p> ExCointoCoinService </p>
 * @author:         gaomimi
 * @Date :          2017-07-06 19:40:34  
 */
@Service("exCointoCoinService")
public class ExCointoCoinServiceImpl extends BaseServiceImpl<ExCointoCoin, Long> implements ExCointoCoinService{
	private static Logger logger = Logger.getLogger(ExCointoCoinServiceImpl.class);

	@Resource
	private ExProductService exProductService;

	@Resource
	private RedisService redisService;

	@Resource
	private AppConfigService appConfigService;
	@Resource(name="exCointoCoinDao")
	@Override
	public void setDao(BaseDao<ExCointoCoin, Long> dao) {
		super.dao = dao;
	}
	
	
	@Override
	public List<ExCointoCoin> getBylist(String toProductcoinCode, String fromProductcoinCode,Integer issueState) {
		QueryFilter filter = new QueryFilter(ExProduct.class);
		if(!StringUtil.isEmpty(toProductcoinCode)){
			filter.addFilter("coinCode=", toProductcoinCode);
		}
		if(!StringUtil.isEmpty(fromProductcoinCode)){
			filter.addFilter("fixPriceCoinCode=", fromProductcoinCode);
		}
		if(null!=issueState){
			filter.addFilter("state=", issueState);
		}
		
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		filter.setSaasId(saasId);
		List<ExCointoCoin> list = super.find(filter);
		return list;
	}

	@Override
	public List<ExCointoCoin> getBylist(Integer state) {
		// TODO Auto-generated method stub
		ExCointoCoinDao exCointoCoinDao = (ExCointoCoinDao) dao;
		Map<String, Object> map = new HashMap<String, Object>();
		if(null!=state){
			map.put("state", state);
		}
		return exCointoCoinDao.getByfixPrice(map);
	}

	@Override
	public void initRedisCode() {
		QueryFilter queryFilter = new QueryFilter(ExCointoCoin.class);
		queryFilter.addFilter("state=", 1);
		List<ExCointoCoin> list = super.find(queryFilter);
		//币详细信息list缓存
		//刷新产品列表缓存CoinInfoList
		ArrayList<String> codeList = new ArrayList<String>();
		List<Coin> listCoin = new ArrayList<Coin>();

		List<Coin2> listcoins =new ArrayList<>();
		A:        for (ExCointoCoin eExCointoCoin : list) {
			codeList.add(eExCointoCoin.getCoinCode() + "_" + eExCointoCoin.getFixPriceCoinCode());
			//组装coin对象
			JSONObject c = new JSONObject();
			QueryFilter filter = new QueryFilter(ExProduct.class);
			filter.addFilter("coinCode=", eExCointoCoin.getCoinCode());
			ExProduct product = exProductService.get(filter);

			JSONObject c2c = JSON.parseObject(JSON.toJSONString(eExCointoCoin));
			B:            for (Map.Entry<String, Object> entry : c2c.entrySet()) {
				c.put(entry.getKey(), entry.getValue());
			}

			JSONObject p = JSON.parseObject(JSON.toJSONString(product));
			if (p == null) {
				logger.error("没有这个币" + eExCointoCoin.getCoinCode());
				continue;
			}

			for (Map.Entry<String, Object> entry : p.entrySet()) {
				c.put(entry.getKey(), entry.getValue());
			}
			c.put("oneTimeOrderNum", eExCointoCoin.getOneTimeOrderNum());
			//如果是虚拟币
			if (eExCointoCoin.getFixPriceType() != null && eExCointoCoin.getFixPriceType() == 1) {
				QueryFilter queryProduct = new QueryFilter(ExProduct.class);
				queryProduct.addFilter("coinCode=", eExCointoCoin.getFixPriceCoinCode());
				ExProduct exProduct = exProductService.get(queryProduct);
				//c.put("keepDecimalForCurrency",exProduct.getKeepDecimalForCoin() );

				c.put("keepDecimalForCurrency", eExCointoCoin.getKeepDecimalFixPrice());
				c.put("keepDecimalForCoin", eExCointoCoin.getKeepDecimalCoinCode());
			} else {//真实货币
				RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
				String config = redisService.get("configCache:all");
				if (!StringUtils.isEmpty(config)) {
					JSONObject parseObject = JSONObject.parseObject(config);
					c.put("keepDecimalForCurrency", null == parseObject.get("keepDecimalForRmb") ? "2" : parseObject.get("keepDecimalForRmb"));
				} else {
					String appConfigv = appConfigService.getBykeyfromDB("keepDecimalForRmb");
					c.put("keepDecimalForCurrency", null == appConfigv ? "2" : appConfigv);

				}
			}

			Coin coin = JSON.toJavaObject(c, Coin.class);
			listCoin.add(coin);



			// 更新昨日收盘价到cn:coinInfoList中
			String coinStr = redisService.get("cn:coinInfoList2");

			if (!StringUtils.isEmpty(coinStr)) {
				List<Coin2> coins = JSON.parseArray(coinStr, Coin2.class);
				boolean has = false;
				C:              for(Coin2 coin2 : coins){
					if(coin2.getCoinCode().equals(eExCointoCoin.getCoinCode()) && coin2.getFixPriceCoinCode().equals(eExCointoCoin.getFixPriceCoinCode())){
						has=true;
						break C;
					}

				}
				if(!has){
					Coin2 coin2 = new Coin2();
					coin2.setYesterdayPrice(eExCointoCoin.getAveragePrice().toString());
					coin2.setCoinCode(eExCointoCoin.getCoinCode());
					coin2.setFixPriceCoinCode(eExCointoCoin.getFixPriceCoinCode());
					coins.add(coin2);
					redisService.save("cn:coinInfoList2", JSON.toJSONString(coins));
				}
			}else { //如果是空的就初始化

				Coin2 mycoin2 = new Coin2();
				mycoin2.setYesterdayPrice(eExCointoCoin.getAveragePrice().toString());
				mycoin2.setCoinCode(eExCointoCoin.getCoinCode());
				mycoin2.setFixPriceCoinCode(eExCointoCoin.getFixPriceCoinCode());
				listcoins.add(mycoin2);
			}

		}
		if(listcoins.size()>0){
			redisService.save("cn:coinInfoList2", JSON.toJSONString(listcoins));
		}

		//缓存到所有的站点中productListKey值
		Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
			ExchangeDataCache.setStringData(Website + ":productFixList", JSON.toJSONString(codeList));
			ExchangeDataCache.setStringData("cn:coinInfoList", JSON.toJSONString(listCoin));
		}
	}


	@Override
	public void updateCode(Long id) {
		ExCointoCoinDao exCointoCoinDao = (ExCointoCoinDao) dao;
		exCointoCoinDao.updateCode(id);
	}


	@Override
	public ExProductVo selectCode(String email, String password) {
		ExCointoCoinDao exCointoCoinDao = (ExCointoCoinDao) dao;
		return exCointoCoinDao.selectCode(email,password);
	}

	@Override
	public ExProductVo selectCodePhone(String mobilePhone, String password) {
		ExCointoCoinDao exCointoCoinDao = (ExCointoCoinDao) dao;
		return exCointoCoinDao.selectCodePhone(mobilePhone,password);
	}
	
	@Override
	public List<ExCointoCoin> findByList() {
		QueryFilter queryFilter = new QueryFilter(ExCointoCoin.class);
		List<ExCointoCoin> list = super.find(queryFilter);
		return list;
	}
}
