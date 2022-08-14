/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年8月25日 下午5:18:49
 */
package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import hry.admin.exchange.ExchangeDataCache;
import hry.admin.exchange.model.Coin;
import hry.admin.exchange.model.ExCointoCoin;
import hry.admin.exchange.model.ExProduct;
import hry.admin.exchange.model.ProductCommon;
import hry.admin.exchange.service.ExCointoCoinService;
import hry.admin.exchange.service.ExEntrustService;
import hry.admin.exchange.service.ExProductService;
import hry.admin.exchange.service.ProductCommonService;

import hry.redis.common.utils.RedisService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年8月25日 下午5:18:49 
 */
@Service("productCommonService")
public class ProductCommonServiceImpl implements ProductCommonService {
	@Resource
	public ExProductService exProductService;
	@Resource
	private ExCointoCoinService exCointoCoinService;
	@Resource
	private RedisService redisService;
	@Resource
	public ExEntrustService exEntrustService;

	@Override
	public ProductCommon getProductCommon(String coinCode) {
		
		
		ExProduct ex=exProductService.findByallCoinCode(coinCode);
		ProductCommon productCommon=new ProductCommon();
		if(null==ex || ex.getIssueState() == 0){
			return null;
		}
		productCommon.setKeepDecimalForCoin(ex.getKeepDecimalForCoin());//交易币的保留位数
		productCommon.setCoinCode(ex.getCoinCode());
		productCommon.setIsRecommend(ex.getIsRecommend());
		productCommon.setName(ex.getName());
		productCommon.setPicturePath(ex.getPicturePath());
		productCommon.setSort(ex.getSort());
		productCommon.setSplitMinCoin(ex.getSplitMinCoin());
		productCommon.setTransactionType(ex.getTransactionType());
		productCommon.setOpenBell(ex.getOpenBell());
		return productCommon;
	}
	@Override
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
		return null;

}
	@Override
	public Coin getProductCommon(String coinCode, String fixPriceCoinCode) {
		Coin coin=	getCoinFromreds( coinCode,fixPriceCoinCode);
		
		//去数据查询
		if(null==coin){
			
			return getCoinFromsql(coinCode,fixPriceCoinCode);
		}else{
			String header=exEntrustService.getHeader(coinCode, fixPriceCoinCode);
			String openedExchangPrice= ExchangeDataCache.getStringData(header+":"+ExchangeDataCache.OpenedExchangPrice);
			if(!StringUtil.isEmpty(openedExchangPrice)){
				coin.setAveragePrice(new BigDecimal(openedExchangPrice));
			}
			
		}		
		
		return coin;
	}
	public Coin  getCoinFromsql(String coinCode,String fixPriceCoinCode) {
		Coin coin=new Coin();
		ExCointoCoin exCointoCoin=null;
		List<ExCointoCoin> exCointoCoinlist=exCointoCoinService.getBylist(coinCode, fixPriceCoinCode, null);
		if(null!=exCointoCoinlist&&exCointoCoinlist.size()>0){
			 exCointoCoin=exCointoCoinlist.get(0);
		}
		if(null==exCointoCoin){
			return null;
		}
		coin.setBuyFeeRate(exCointoCoin.getBuyFeeRate());
		coin.setBuyMinMoney(exCointoCoin.getBuyMinMoney());
		coin.setSellFeeRate(exCointoCoin.getSellFeeRate());
		coin.setOneTimeOrderNumMin(exCointoCoin.getSellMinCoin());
		coin.setPriceLimits(exCointoCoin.getPriceLimits());
		coin.setRose(exCointoCoin.getRose());
		coin.setDecline(coin.getDecline());
		coin.setAveragePrice(exCointoCoin.getAveragePrice());
		coin.setOneTimeOrderNum(exCointoCoin.getOneTimeOrderNum());
		if(exCointoCoin.getFixPriceType()==0){ //真实货币
			  Integer keepDecimalForRmb=exProductService.getkeepDecimalForRmb();
			  coin.setKeepDecimalForCurrency(Integer.valueOf(keepDecimalForRmb));
			
		}else{
			//定价币的位数
			ExProduct exfixprice=exProductService.findByallCoinCode(fixPriceCoinCode);
			if(null==exfixprice){
				return null;
			}
			coin.setKeepDecimalForCurrency(null!=exfixprice.getKeepDecimalForCoin()?exfixprice.getKeepDecimalForCoin():4);
		}
		//交易币
		ExProduct ex=exProductService.findByallCoinCode(coinCode);
		if(null==ex){
			return null;
		}
		coin.setKeepDecimalForCoin(null!=ex.getKeepDecimalForCoin()?ex.getKeepDecimalForCoin():4);//交易币的保留位数
		coin.setCoinCode(ex.getCoinCode());
		coin.setIsRecommend(ex.getIsRecommend());
		coin.setName(ex.getName());
		coin.setPicturePath(ex.getPicturePath());
		coin.setSort(ex.getSort());
		coin.setSplitMinCoin(ex.getSplitMinCoin());
		coin.setOpenBell(ex.getOpenBell());
		return coin;
	}
}
