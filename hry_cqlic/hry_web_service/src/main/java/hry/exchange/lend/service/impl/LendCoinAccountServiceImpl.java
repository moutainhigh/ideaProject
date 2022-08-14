/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gaomm
 * @version:      V1.0 
 * @Date:        
 */
package hry.exchange.lend.service.impl;

import hry.account.fund.model.AppAccount;
import hry.account.fund.service.AppAccountRecordService;
import hry.account.fund.service.AppAccountService;
import hry.account.fund.service.AppColdAccountRecordService;
import hry.account.fund.service.AppHotAccountRecordService;
import hry.account.fund.service.AppOurAccountService;
import hry.account.remote.RemoteAppAccountService;
import hry.core.constant.CodeConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.util.QueryFilter;
import hry.util.UUIDUtil;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;

import hry.util.message.MessageConstant;
import hry.util.message.MessageUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.service.LendCoinAccountService;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExProductService;
import hry.exchange.lend.service.ExDmLendService;
import hry.exchange.lend.service.ExDmPingService;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.web.remote.RemoteAppConfigService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tk.mybatis.mapper.util.StringUtil;
import util.UserRedisUtils;

import com.alibaba.dubbo.rpc.RpcContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Gaomm
 * 
 */
@Service("lendCoinAccountService")
public class LendCoinAccountServiceImpl implements
     LendCoinAccountService {

	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	private ExDmLendService  exDmLendService;
	@Resource
	private  AppPersonInfoService appPersonInfoService;
	@Resource
	private AppAccountService appAccountService;
	@Resource
	private  ExDmPingService exDmPingService;
	@Override
	public  String getRMBCode() {
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String language_code = remoteAppConfigService.getFinanceByKey("language_code");
		return language_code;
	}
	@Override
    public String getCoinCodeForRmb(){
    	RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		// 虚拟货币
		String coinCodeForRmb = remoteAppConfigService.getFinanceByKey("coinCodeForRmb");
		
		return coinCodeForRmb;
    }
	@Override
	public  BigDecimal getCurrentExchangPrice(String coinCode,String fixPriceCoinCode) {
		
		String header = coinCode + "_" + fixPriceCoinCode;
		String key=header + ":" + ExchangeDataCacheRedis.CurrentExchangPrice;
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v = redisService.get(key);
		if(StringUtil.isEmpty(v)||new BigDecimal(v).compareTo(new BigDecimal("0"))==0){
			return null;
		}else{
			return new BigDecimal(v);
		}

	}
	public AppAccount getAppAccount(Long customerId) {
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
	
		QueryFilter filter = new QueryFilter(AppAccount.class);
		filter.addFilter("customerId=", customerId);
		AppAccount account = appAccountService.get(filter);
		AppAccountRedis appAccountredis = UserRedisUtils.getAccount(userRedis.getAccountId().toString());
		account.setHotMoney(appAccountredis.getHotMoney());
		account.setColdMoney(appAccountredis.getColdMoney());
		return account;
	}
	
	//  折合成基础币净资产
	@Override
	public BigDecimal getRMBNetAsset(Long customerId,String coinCodeForRmb,String currencyType,String website) {
	        BigDecimal rMBappAccountNetAsse=new BigDecimal("0");
			List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
			for(ExDigitalmoneyAccount l:list){
				if(l.getCoinCode().equals(coinCodeForRmb)){
					BigDecimal a=l.getHotMoney().add(l.getColdMoney()).subtract(l.getLendMoney());
					rMBappAccountNetAsse=rMBappAccountNetAsse.add(a);
				}else{
					BigDecimal a=l.getHotMoney().add(l.getColdMoney()).subtract(l.getLendMoney());
					BigDecimal currentExchangPrice=getCurrentExchangPrice(l.getCoinCode(),coinCodeForRmb);
						if(null!=currentExchangPrice){
						rMBappAccountNetAsse=rMBappAccountNetAsse.add(a.multiply(currentExchangPrice));
					}
				}
				
			}
			
			AppAccount appAccount =getAppAccount(customerId);
			BigDecimal acc=appAccount.getHotMoney().add(appAccount.getColdMoney()).subtract(appAccount.getLendMoney());
			if(coinCodeForRmb.equals(getRMBCode())){
				rMBappAccountNetAsse=rMBappAccountNetAsse.add(acc);
				
			}else{
				BigDecimal currentExchangPrice=getCurrentExchangPrice(coinCodeForRmb,getRMBCode());
				if(null!=currentExchangPrice){
				  rMBappAccountNetAsse=rMBappAccountNetAsse.add(acc.divide(currentExchangPrice, 10, BigDecimal.ROUND_HALF_DOWN));
			    }
			   
			}
			
			 return rMBappAccountNetAsse;
		
		
	}
	
// 折合成某币净资产
	@Override
	public BigDecimal getCoinNetAsset(Long customerId,String coinCodeForRmb,String coinCode,String currencyType,String website) {
		
		if(null==coinCodeForRmb){
			coinCodeForRmb=getCoinCodeForRmb();
		}
		BigDecimal rMBappAccountNetAsse=getRMBNetAsset(customerId,coinCodeForRmb,currencyType,website);
		if(coinCode.equals(coinCodeForRmb)){
			return rMBappAccountNetAsse;
		}
		BigDecimal currentExchangPrice=getCurrentExchangPrice(coinCode,coinCodeForRmb);
		if(null==currentExchangPrice){
			return new BigDecimal("0");
		}else{
			BigDecimal coinAccountNetAsse=rMBappAccountNetAsse.divide(currentExchangPrice, 10, BigDecimal.ROUND_HALF_DOWN);
			return coinAccountNetAsse;
		}
		
		
	}
	//总负责折合成基础币
	@Override
	public BigDecimal   getRMBLendMoneySum(Long customerId,String coinCodeForRmb,String currencyType,String website){
			BigDecimal lendSum=new BigDecimal("0");  //负债
			List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
		/*	//加未偿还的利息
			ExDmLend exDmLend=exDmLendService.getLendBycustmer(currencyType, customerId,currencyType,website);
			lendSum=lendSum.add(exDmLend.getNotrepayInterestCount());*/
			if(null==list){
				return null;
			}
			for(ExDigitalmoneyAccount l:list){
				if(l.getCoinCode().equals(coinCodeForRmb)){
					lendSum=lendSum.add(l.getLendMoney());
				}else{
					BigDecimal a=l.getLendMoney();
					/*//加未偿还的利息
					 exDmLend=exDmLendService.getLendBycustmer(l.getCoinCode(), customerId,currencyType,website);
					a=a.add(exDmLend.getNotrepayInterestCount());*/
					BigDecimal currentExchangPrice=getCurrentExchangPrice(l.getCoinCode(),coinCodeForRmb);
					if(null!=currentExchangPrice){
						lendSum=lendSum.add(a.multiply(currentExchangPrice));
					}
				}
				
			}
			
			AppAccount appAccount =getAppAccount(customerId);
			BigDecimal acc=appAccount.getLendMoney();
			if(coinCodeForRmb.equals(getRMBCode())){
				lendSum=lendSum.add(acc);
			}else{
				BigDecimal currentExchangPrice=getCurrentExchangPrice(coinCodeForRmb,getRMBCode());
					if(null!=currentExchangPrice){
						lendSum=lendSum.add(acc.divide(currentExchangPrice, 10, BigDecimal.ROUND_HALF_DOWN));
				    }
			}
			return lendSum;
	}
	   //总负责折成某币
		@Override
		public BigDecimal   getCodeLendMoneySum(Long customerId,String coinCodeForRmb,String coinCode ,String currencyType,String website){
				BigDecimal lendSum=getRMBLendMoneySum(customerId,coinCodeForRmb,currencyType,website);  //负债
				if(coinCode.equals(coinCodeForRmb)){
					return lendSum;
				}
				BigDecimal currentExchangPrice=getCurrentExchangPrice(coinCode,coinCodeForRmb);
				if(null!=currentExchangPrice){
					return lendSum.divide(currentExchangPrice, 10, BigDecimal.ROUND_HALF_DOWN);
				}else{
					return new BigDecimal(0);
				}
	
		}
	//所欠利息折合成RMB
	public BigDecimal   getRMBLendMoneyInteretSum(Long customerId,String coinCodeForRmb,String currencyType,String website){
			List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
			//加未偿还的利息
			BigDecimal lendSum=new BigDecimal("0");
			for(ExDigitalmoneyAccount l:list){
				if(l.getCoinCode().equals(coinCodeForRmb)){
					BigDecimal a=new BigDecimal("0");
					//加未偿还的利息
					ExDmLend exDmLend=exDmLendService.getLendBycustmer(l.getCoinCode(), customerId,currencyType,website);
				   	a=a.add(exDmLend.getNotrepayInterestCount());
				   	lendSum=lendSum.add(a);
				}else{
					BigDecimal a=new BigDecimal("0");
					//加未偿还的利息
					ExDmLend exDmLend=exDmLendService.getLendBycustmer(l.getCoinCode(), customerId,currencyType,website);
					a=a.add(exDmLend.getNotrepayInterestCount());
					BigDecimal currentExchangPrice=getCurrentExchangPrice(l.getCoinCode(),coinCodeForRmb);
					if(null!=currentExchangPrice){
						lendSum=lendSum.add(a.multiply(currentExchangPrice));
					}
				}
				
			}
			return lendSum;
	}

	//某个币种能借多少
	@Override
	public BigDecimal getCanLendMoney(Long customerId,String coinCodeForRmb,String coinCode,String currencyType,String website) {
			if(null==coinCodeForRmb){
			coinCodeForRmb=getCoinCodeForRmb();
		}
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		BigDecimal rMBAccountNetAsse =this.getRMBNetAsset(customerId,coinCodeForRmb,currencyType,website);  //净资产
		
		
		//借钱至少需要净资产达到多少才可以借钱
		/*String lendleastMoneys=remoteAppConfigService.getFinanceByKey("leastMoneyCanLend");
		BigDecimal  lendleastMoney=new BigDecimal(StringUtil.isEmpty(lendleastMoneys)?"1000":lendleastMoneys); //杠杆的倍数，做成配置文件
		if(rMBAccountNetAsse.compareTo(lendleastMoney)==-1){
			return new BigDecimal("0");
		}*/
		
		BigDecimal lendSum=getRMBLendMoneySum(customerId,coinCodeForRmb,currencyType,website);//总负债
		
		AppPersonInfo appPersonInfo=appPersonInfoService.getByCustomerId(customerId);
		BigDecimal times=new BigDecimal("3");
		if(null!=appPersonInfo.getLendTimes()){
			times=appPersonInfo.getLendTimes();
		}else{
			String lendTimestr=remoteAppConfigService.getFinanceLendByKey("lendTimes");
			if(!StringUtil.isEmpty(lendTimestr)){
				times=new BigDecimal(lendTimestr);
			}
		}
		BigDecimal surMBAccountNetAsse=rMBAccountNetAsse.multiply(times).subtract(lendSum);
		int keepDecimalForRmb=this.culDecimal(coinCode);
		if(surMBAccountNetAsse.compareTo(new BigDecimal("0"))==-1){
			surMBAccountNetAsse=new BigDecimal("0");
			return surMBAccountNetAsse.setScale(keepDecimalForRmb, BigDecimal.ROUND_HALF_DOWN);
		}
		//如果借钱的话就直接返回净资产
	
		if(coinCode.equals(coinCodeForRmb)){
			 return surMBAccountNetAsse.setScale(keepDecimalForRmb, BigDecimal.ROUND_HALF_DOWN);
		}
		BigDecimal currentExchangPrice=getCurrentExchangPrice(coinCode,coinCodeForRmb);
		if(null!=currentExchangPrice){
		  BigDecimal canLendMoney= surMBAccountNetAsse.divide(currentExchangPrice, keepDecimalForRmb, BigDecimal.ROUND_HALF_DOWN);
			return canLendMoney;
		}
		return new BigDecimal("0");
	}
	

	/**
	 * 根据用户的id 返回用户最高可提现的金额
	 */
	@Override
	public BigDecimal getCanWithdrawMoney(Long customerId,String coinCodeForRmb,String coinCode,String currencyType,String website) {
		Boolean isLend= exDmPingService.isLend();
		if(!isLend){ //如果没有融资融币的功能就直接放回无穷大
			return new BigDecimal("999999999"); 
		}
		Boolean isPinging=exDmPingService.isPinging(customerId, null, null, null);
		if(isPinging){
			return new BigDecimal("0");  //正在平仓不让取
		}
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(customerId, currencyType,website);
		BigDecimal hotMoney=appAccount.getHotMoney(); //表面上可取的人民币
		BigDecimal lendSum=getRMBLendMoneySum(customerId,coinCodeForRmb,currencyType,website);//总负债
		if(lendSum.compareTo(new BigDecimal("0"))==0){ //如果没借过钱就可以取可用人民币
			return hotMoney;
		}
		BigDecimal netAsseToLend=this.netAsseToLend(customerId,coinCodeForRmb,currencyType,website);
		//杠杆的比例1.1，放文件
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String lengPings=remoteAppConfigService.getFinanceLendByKey("lengPing");
		BigDecimal  lengPing=new BigDecimal(StringUtil.isEmpty(lengPings)?"110":lengPings); //杠杆的倍数，做成配置文件
		if(netAsseToLend.compareTo(lengPing)<1){
			return new BigDecimal("0");  //如果杠杆比已经达到了110说明就不让取钱了
		}
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		RpcContext.getContext().setAttachment("saasId", saasId);
		
		BigDecimal rMBAccountNetAsse =this.getRMBNetAsset(customerId,coinCodeForRmb,currencyType,website);  //净资产
		if(hotMoney.compareTo(rMBAccountNetAsse)==1){//取最小值
			return rMBAccountNetAsse;
        }else{
        	return hotMoney;
        }
		
	}
	
	/**
	 * 根据用户的id 以及币的类型返回用户最高可取的币数量
	 */
	@Override
	public BigDecimal getCanWithdrawCoin(Long customerId,String coinCodeForRmb,String coinCode,String currencyType,String website) {
		
		ExDigitalmoneyAccount eda=this.getByCustomerIdAndType(customerId, coinCode, currencyType,website);
		BigDecimal hotMoney=eda.getHotMoney();//表面上可取的币
		BigDecimal lendSum=getRMBLendMoneySum(customerId,coinCodeForRmb,currencyType,website);//总负债
		if(lendSum.compareTo(new BigDecimal("0"))==0){ //如果没借过钱就可以取可用币币
			return hotMoney;
		}
		
		BigDecimal netAsseToLend=this.netAsseToLend(customerId,coinCodeForRmb,currencyType,website);
		//杠杆的比例1.1，放文件
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String lengPings=remoteAppConfigService.getFinanceLendByKey("lengPing");
		BigDecimal  lengPing=new BigDecimal(StringUtil.isEmpty(lengPings)?"110":lengPings); //杠杆的倍数，做成配置文件
		if(netAsseToLend.compareTo(lengPing)<1){
			return new BigDecimal("0");  //如果杠杆比已经达到了110说明就不让取钱了
		}
	
		BigDecimal  coinNetAsset=this.getCoinNetAsset(customerId,coinCodeForRmb,coinCode,currencyType,website); //净资产
		if(hotMoney.compareTo(coinNetAsset)==1){//取最小值
			return coinNetAsset;
        }else{
        	return hotMoney;
        }
	}
	//资产/杠杆：比例
	@Override
	public BigDecimal netAsseToLend(Long customerId,String coinCodeForRmb,String currencyType,String website) {
		if(null==coinCodeForRmb){
			RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
			// 虚拟货币
			 coinCodeForRmb = remoteAppConfigService.getFinanceByKey("coinCodeForRmb");
		}
		
		BigDecimal rmblendSum=this.getRMBLendMoneySum(customerId,coinCodeForRmb,currencyType,website);//折合RMB总负债
		if(null==rmblendSum){
			return new BigDecimal("100000");
		}
		if(rmblendSum.compareTo(new BigDecimal("0"))==0){
			return new BigDecimal("100000");
		}
		BigDecimal rMBAccountNetAsse=this.getRMBNetAsset(customerId,coinCodeForRmb,currencyType,website);//折合RMB净资产
		BigDecimal sumzichan=rMBAccountNetAsse.add(rmblendSum);//总资产=净资产+负债
		
		
		return	sumzichan.divide(rmblendSum, 10, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal("100"));
		
	}



	public List<ExDigitalmoneyAccount> getByCustomerId(Long customerId,String currencyType,String website) {
		
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
		if(null==userRedis){
			return  null;
		}
	    Map<String, Long> map=userRedis.getDmAccountId();
	    Iterator<Map.Entry<String, Long>> it = map.entrySet().iterator();
	    List<ExDigitalmoneyAccountRedis> list=new ArrayList<ExDigitalmoneyAccountRedis>();
	     while (it.hasNext()) {
	    	   Map.Entry<String, Long> entry = it.next();
	    	 ExDigitalmoneyAccountRedis appAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(entry.getKey()).toString(), entry.getKey());
	    	 list.add(appAccount);
	     }

	 	QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("customerId=", customerId);
		List<ExDigitalmoneyAccount> liste=exDigitalmoneyAccountService.find(filter);
		for(ExDigitalmoneyAccount e:liste){
			 ExDigitalmoneyAccountRedis appAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(e.getCoinCode()).toString(), e.getCoinCode());

			e.setHotMoney(appAccount.getHotMoney());
			e.setColdMoney(appAccount.getColdMoney());

		}
		return liste;
	}
	public ExDigitalmoneyAccount getByCustomerIdAndType(Long customerId,String coinCode,String currencyType,String website) {
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
	
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("customerId=", customerId);
		if(!StringUtil.isEmpty(currencyType)){
			filter.addFilter("currencyType=", currencyType);
		}
		filter.addFilter("coinCode=", coinCode);
		ExDigitalmoneyAccount e= exDigitalmoneyAccountService.get(filter);
	    ExDigitalmoneyAccountRedis appAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(e.getCoinCode()).toString(), e.getCoinCode());
		e.setHotMoney(appAccount.getHotMoney());
		e.setCoinCode(appAccount.getCoinCode());
		return e;
	}



	@Override
	public BigDecimal getRMBSum(Long customerId, String coinCodeForRmb, String currencyType, String website) {

        BigDecimal sum=new BigDecimal("0");
		List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
		for(ExDigitalmoneyAccount l:list){
			if(l.getCoinCode().equals(coinCodeForRmb)){
				BigDecimal a=l.getHotMoney().add(l.getColdMoney());
				sum=a;
			}else{
				BigDecimal a=l.getHotMoney().add(l.getColdMoney());
				BigDecimal currentExchangPrice=getCurrentExchangPrice(l.getCoinCode(),coinCodeForRmb);
					if(null!=currentExchangPrice){
						sum=sum.add(a.multiply(currentExchangPrice));
				}
			}
			
			
			
		}
		return sum;
	
	

	
	}



	@Override
	public BigDecimal getCoinCodeSum(Long customerId, String coinCodeForRmb, String coinCode, String currencyType, String website) {
		BigDecimal rmbsum= this.getRMBSum(customerId, coinCodeForRmb, currencyType, website);
		
		BigDecimal currentExchangPrice=getCurrentExchangPrice(coinCode,coinCodeForRmb);
		if(null!=currentExchangPrice){
			return rmbsum.divide(currentExchangPrice,10, BigDecimal.ROUND_HALF_DOWN);
	  }
		return new BigDecimal("0");
	}
	@Override
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
			if(null!=ex) {
				keepDecimalForRmb = ex.getKeepDecimalForCoin();
				if (null == keepDecimalForRmb)
					keepDecimalForRmb = 4;
			}

		}
		return keepDecimalForRmb;
	}
	

}
