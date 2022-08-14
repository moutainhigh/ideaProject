/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年8月25日 下午5:18:49
 */
package hry.exchange.product.service.impl;

import hry.core.constant.StringConstant;
import hry.util.sys.ContextUtil;
import hry.exchange.product.model.ExCointoCoin;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.model.ProductCommon;
import hry.exchange.product.service.ExCointoCoinService;
import hry.exchange.product.service.ExProductService;
import hry.exchange.product.service.ProductCommonService;
import hry.manage.remote.model.Coin;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.service.ExEntrustService;
import hry.web.remote.RemoteAppConfigService;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年8月25日 下午5:18:49 
 */
@Service("productCommonService")
public class ProductCommonServiceImpl implements  ProductCommonService {
	@Resource
	public ExProductService exProductService;
	@Resource
	private ExCointoCoinService exCointoCoinService;
	@Resource
	private  RedisService redisService;
	@Resource
	public ExEntrustService exEntrustService;
/*	public ExCointoCoin getExCointoCoin(String coinCode,String fixPriceCoinCode){
		ExCointoCoin ecc=null;
		String productListStr = ExchangeDataCache.getStringData("cn:"+StringConstant.COININFOLIST );
		if (!StringUtils.isEmpty(productListStr)) {
				List<ExCointoCoin> exCointoCoinList = JSON.parseArray(productListStr, ExCointoCoin.class);
				 if(null!=exCointoCoinList){
				    	for(ExCointoCoin e:exCointoCoinList){
				    		if(e.getCoinCode().equals(coinCode)&&e.getFixPriceCoinCode().equals(fixPriceCoinCode)){
				    			ecc=e;
				    			break;
				    		}
				    	}
				    	if(null!=ecc){
				    		return ecc;
				    	}
				 }
		}
		List<ExCointoCoin> exCointoCoinlist=exCointoCoinService.getBylist(coinCode, fixPriceCoinCode, null);
		if(null!=exCointoCoinlist&&exCointoCoinlist.size()>0){
			ExCointoCoin exCointoCoin=exCointoCoinlist.get(0);
			return exCointoCoin;
		}
		
		return null;
	}
	public ExProduct getExprodct(String coinCode){
		ExProduct ecc=null;
		String productListStr = ExchangeDataCache.getStringData("cn:"+StringConstant.COININFOLIST );
		if (!StringUtils.isEmpty(productListStr)) {
				List<ExProduct> exCointoCoinList = JSON.parseArray(productListStr, ExProduct.class);
				 if(null!=exCointoCoinList){
				    	for(ExProduct e:exCointoCoinList){
				    		if(e.getCoinCode().equals(coinCode)){
				    			ecc=e;
				    			break;
				    		}
				    	}
				    	if(null!=ecc){
				    		return ecc;
				    	}
				 }
		}
		ExProduct ex=exProductService.findByallCoinCode(coinCode);
		if(null!=ex){
			
			return ex;
		}
		
		return null;
	}*/
/*	@Override
	public ProductCommon getProductCommon(String coinCode,String fixPriceCoinCode) {
		ProductCommon productCommon=new ProductCommon();
		ExCointoCoin exCointoCoin=getExCointoCoin(coinCode, fixPriceCoinCode);
		if(null==exCointoCoin){
			return null;
		}
		productCommon.setBuyFeeRate(exCointoCoin.getBuyFeeRate());
		productCommon.setBuyMinMoney(exCointoCoin.getBuyMinMoney());
		productCommon.setSellFeeRate(exCointoCoin.getSellFeeRate());
		productCommon.setOneTimeOrderNumMin(exCointoCoin.getSellMinCoin());
		productCommon.setPriceLimits(exCointoCoin.getPriceLimits());
		productCommon.setRose(exCointoCoin.getRose());
		productCommon.setDecline(productCommon.getDecline());
		productCommon.setAveragePrice(exCointoCoin.getAveragePrice());
		productCommon.setOneTimeOrderNum(exCointoCoin.getOneTimeOrderNum());
		if(exCointoCoin.getFixPriceType()==0){ //真实货币
			  Integer keepDecimalForRmb=exProductService.getkeepDecimalForRmb();
			  productCommon.setKeepDecimalFixPrice(Integer.valueOf(keepDecimalForRmb));
			
		}else{
			//定价币的位数
			ExProduct exfixprice=exProductService.findByallCoinCode(fixPriceCoinCode);
			if(null==exfixprice){
				return null;
			}
			productCommon.setKeepDecimalFixPrice(null!=exfixprice.getKeepDecimalForCoin()?exfixprice.getKeepDecimalForCoin():4);
		}
		
		
		//交易币
		ExProduct ex=exProductService.findByallCoinCode(coinCode);
		if(null==ex){
			return null;
		}
		productCommon.setKeepDecimalForCoin(null!=ex.getKeepDecimalForCoin()?ex.getKeepDecimalForCoin():4);//交易币的保留位数
		productCommon.setCoinCode(ex.getCoinCode());
		productCommon.setIsRecommend(ex.getIsRecommend());
		productCommon.setName(ex.getName());
		productCommon.setPicturePath(ex.getPicturePath());
		productCommon.setSort(ex.getSort());
		productCommon.setSplitMinCoin(ex.getSplitMinCoin());
		productCommon.setTransactionType(ex.getTransactionType());
		productCommon.setOpenBell(ex.getOpenBell());
		return productCommon;
	}*/
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
		productCommon.setOpenTibi(ex.getOpenTibi());
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
			String openedExchangPrice=ExchangeDataCache.getStringData(header+":"+ExchangeDataCache.OpenedExchangPrice);
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
