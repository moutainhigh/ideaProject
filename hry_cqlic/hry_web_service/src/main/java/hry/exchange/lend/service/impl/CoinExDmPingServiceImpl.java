/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.lend.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.remote.RemoteAppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExAmineOrderService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.lend.PingLendDealTread;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmPing;
import hry.exchange.lend.service.ExDmLendIntentService;
import hry.exchange.lend.service.ExDmLendService;
import hry.exchange.lend.service.ExDmPingService;
import hry.exchange.lend.service.CoinExDmPingService;
import hry.exchange.lend.service.LendCoinAccountService;
import hry.exchange.product.service.ExProductService;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.service.ExEntrustService;
import hry.web.remote.RemoteAppConfigService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.util.StringUtil;


/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("coinExDmPingService")
public class CoinExDmPingServiceImpl extends BaseServiceImpl<ExDmPing, Long>
		implements CoinExDmPingService {
	private static Logger logger = Logger.getLogger(CoinExDmPingServiceImpl.class);
	@Resource(name = "exDmPingDao")
	@Override
	public void setDao(BaseDao<ExDmPing, Long> dao) {
		super.dao = dao;
	}
	@Resource
	public RedisService redisService;

	@Resource
	public ExAmineOrderService examineOrderService;
	@Resource
	private ExDmPingService  exDmPingService;
	@Resource
	private ExDmLendService  exDmLendService;
	@Resource
	private  ExDmLendIntentService exDmLendIntentService;
	@Resource
	private ExProductService exProductService;
	@Resource
	private ExEntrustService exEntrustService;
	@Resource
	private RemoteAppCustomerService remoteAppCustomerService;
	@Resource
	private LendCoinAccountService accountService;
	@Resource
	private ExDigitalmoneyAccountService  exDigitalmoneyAccountService;
	@Override
	public void jobRunTimeCulPingLendMoney() {
	//	logger.error("平仓定时");
		//这样的好处就是，币的涨跌，充值提，现都不用单独去加是否平仓或解除平仓
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		 for (String website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(website);
		    pingLend(website,currencyType);
		  }
   }
	@Override
	public void jobRunTimeCulEndPingLendMoney() {
		//这样的好处就是，币的涨跌，充值提，现都不用单独去加是否平仓或解除平仓
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		 for (String website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(website);
		    endPing(website,currencyType);
		  }
	}
	//定时把平仓完成的设置成完成状态
	public void endPing(String website,String currencyType){
		 List<ExDmPing> listp= getPingBystatus(1,currencyType,website);//已进入平仓流程的
		 for(ExDmPing l:listp){
			
			 Integer isPingWarehouse=isPingWarehouse(l.getCustomerId(),l.getUserCode(),currencyType,website);
			 if(isPingWarehouse==0){
				//如果超过了安全区域就把正在平仓流程结束掉
					 //取消所有的委托
					 exEntrustService.cancelAllcustomerIdExEntrust(l.getCustomerId(),currencyType,website);
			         l.setStatus(2);
					 this.update(l);
				 }
			 }
				
	}
	//定时计算需要平仓的
	public void pingLend(String website,String currencyType) { 
		Map<String,String> map=new HashMap<String,String>();
		map.put("website", website);
		map.put("currencyType", currencyType);
		map.put("lendCoinType", null);
		 List<ExDmLend> list=exDmLendService.getMayPingCustomer(map);
		 for(ExDmLend e:list){
			 List<ExDmPing> l= getBycustomerid(e.getCustomerId(),e.getUserCode(),1,currencyType,website);//已进入平仓流程的
			 if(null==l||l.size()==0){
			//	 logger.error("平e.getCustomerId()="+e.getCustomerId());
				 pingByCustomerId(e.getCustomerId(),e.getUserCode(),currencyType,website);
			 }
			
			 
			 
		 }
	}
	
	@Override
	public void pingByCustomerId(Long customerId,String userCode,String currencyType,String website) {
	Integer isPingWarehouse=isPingWarehouse(customerId,userCode,currencyType,website);
	   if(isPingWarehouse.equals(2)){
		   String key=website+":"+currencyType+":"+"pingTips:riskgmessage"+customerId;
			String ed=redisService.get(key);
			if(null==ed){
				//发短信提醒进入风险区
				SmsParam smsParam = new SmsParam();
				AppCustomer appCustomer =remoteAppCustomerService.getById(customerId);
				smsParam.setHryMobilephone(appCustomer.getUserName());
				smsParam.setHrySmstype(SmsSendUtil.CLOSE_POSITION_TIPS);
                SmsSendUtil.sendSmsCode(smsParam);
				redisService.save(key, "ed", 86400); //保存一天
			}
		   
	   }else if(isPingWarehouse.equals(1)){
		   
		   pingMoneyFlow(customerId,userCode,currencyType,website);//走平仓流程
		   
		 //发短信提醒已被强制平仓
		    SmsParam smsParam = new SmsParam();
			AppCustomer appCustomer =remoteAppCustomerService.getById(customerId);
			smsParam.setHryMobilephone(appCustomer.getUserName());
			smsParam.setHrySmstype(SmsSendUtil.CLOSE_POSITION);
            SmsSendUtil.sendSmsCode(smsParam);
			
		   
	   }		
		
		
	}
	
	
	
	@Override
	public Integer isPingWarehouse(Long customerId,String userCode,String currencyType,String website) {
		BigDecimal netAsseToLend=accountService.netAsseToLend(customerId,null,currencyType,website);
		//杠杆的比例1.1，放文件
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String lengPings=remoteAppConfigService.getFinanceLendByKey("lengPing");
		BigDecimal  lengPing=new BigDecimal(StringUtil.isEmpty(lengPings)?"0":lengPings); //110
		if(netAsseToLend.compareTo(lengPing)<1){
			return 1;
		}
		String lengRiskRates=remoteAppConfigService.getFinanceLendByKey("lengRiskRate");
		BigDecimal  lengRiskRate=new BigDecimal(StringUtil.isEmpty(lengRiskRates)?"0":lengRiskRates); //120
		if(netAsseToLend.compareTo(lengRiskRate)<1){
			return 2;
		}
		
		
		    
			
		return 0;
		
	}
	
	
	
	public void pingCoinFlow(Long customerId,String userCode) {
		ExDmPing exDmPing=new ExDmPing();
		exDmPing.setCustomerId(customerId);
		exDmPing.setUserCode(userCode);
		exDmPing.setStatus(1);
		exDmPing.setPingNum(IdGenerate.transactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Ping)));
		exDmPingService.save(exDmPing);
		
		QueryFilter filter = new QueryFilter(ExDmLend.class);
		filter.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
		filter.addFilter("customerId=", customerId);
		filter.addFilter("status<", 3);
		List<ExDmLend> list=exDmLendService.find(filter);
		for(ExDmLend l:list){
			ExDigitalmoneyAccount eda=exDigitalmoneyAccountService.getByCustomerIdAndType(customerId,null,l.getCurrencyType(),l.getWebsite());
		//	eda.getHotMoney().add()
		}
		
	}
   public void pingMoneyFlow(Long customerId,String userCode,String currencyType,String website) {
	   List<ExDmPing> pl= getBycustomerid(customerId,userCode,1,currencyType,website);//已进入平仓流程的
	   logger.error("pingMoneyFlow");
	   if(null==pl||pl.size()==0){
			 ExDmPing exDmPing=create(customerId,userCode,currencyType,website);
			 logger.error(" this.save(exDmPing)");
			 this.save(exDmPing);
			 
			//1所有委托全部取消
			 exEntrustService.cancelAllcustomerIdExEntrust(customerId,currencyType,website);
			//驳回所有的提币申请
//			 examineOrderService.stopeAlllistByapply(customerId, currencyType, website);
			//驳回所有的提现申请
		//	 examineOrderService.stopeAlllistByapply(customerId, currencyType, website);
			 try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//2全部币都卖出去，新建市价委托，并且优先级最高
			 List<ExDigitalmoneyAccount> list= exDigitalmoneyAccountService.getByCustomerId(customerId,currencyType,website);
			 logger.error("ExDigitalmoneyAccount l:list=="+customerId+"="+currencyType+"="+website);
			 logger.error("ExDigitalmoneyAccount l:list=="+list.size());
			 for(ExDigitalmoneyAccount l:list){
	        	 logger.error("平l.getHotMoney()="+l.getCoinCode()+"="+l.getHotMoney());
	        	 addExEntrust(2,l.getCustomerId(),userCode,l.getCoinCode(),l.getHotMoney(),currencyType,website);
	         }	
	         //3自动还钱（等币都卖完了就自动还钱，市价正常委托出去就里面会匹配所以稍微等会就可以还钱）
	         PingLendDealTread pingLendDealTread= new  PingLendDealTread(customerId);
	         hry.core.thread.ThreadPool.exe(pingLendDealTread);
		 }
		 
         
	}
   public void addExEntrust(Integer type,Long customerId,String userCode,String coinCode,BigDecimal entrustCount,String currencyType,String website){
	     
       ExEntrust exEntrust =new ExEntrust();
       exEntrust.setType(type);
       exEntrust.setEntrustPrice(new BigDecimal("0"));
       exEntrust.setEntrustCount(entrustCount);
       exEntrust.setEntrustSum(new BigDecimal("0"));
       exEntrust.setEntrustWay(2);//?
       exEntrust.setCustomerId(customerId);
       exEntrust.setUserCode(userCode);
       exEntrust.setCustomerType(1);
       exEntrust.setCoinCode(coinCode);
       exEntrust.setMatchPriority(5);
       exEntrust.setCurrencyType(currencyType);
       exEntrust.setWebsite(website);
       exEntrust.setSource(4);
       exEntrustService.saveExEntrust(exEntrust);
	}
   @Override
	public ExDmPing create(Long customerId,String userCode,String currencyType,String website) {
	   ExDmPing exDmPing=new ExDmPing();
	   exDmPing.setStatus(1);
	   exDmPing.setPingNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Ping));
	   exDmPing.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
	   exDmPing.setCustomerId(customerId);
	   exDmPing.setUserCode(userCode);
	   exDmPing.setCurrencyType(currencyType);
	   RemoteAppPersonInfoService remoteAppPersonInfoService = (RemoteAppPersonInfoService)ContextUtil.getBean("remoteAppPersonInfoService");
	   AppPersonInfo appPersonInfo1 = remoteAppPersonInfoService.getByCustomerId(customerId);
	   exDmPing.setTrueName(appPersonInfo1.getTrueName());
	   exDmPing.setWebsite(website);
		return exDmPing;
	}
	@Override
	public List<ExDmPing> getBycustomerid(Long customerId,String userCode, Integer status,String currencyType,String website) {
		 QueryFilter filter =new QueryFilter(ExDmPing.class);
		 filter.addFilter("customerId=", customerId);
		 filter.addFilter("status=", status);
		 filter.addFilter("currencyType=", currencyType);
		 filter.addFilter("website=", website);
		 filter.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
		 return exDmPingService.find(filter);
	}
	public List<ExDmPing> getPingBystatus(Integer status,String currencyType,String website) {
		 QueryFilter filter =new QueryFilter(ExDmPing.class);
		 filter.addFilter("status=", status);
		 filter.addFilter("currencyType=", currencyType);
		 filter.addFilter("website=", website);
		 filter.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
		 return exDmPingService.find(filter);
	}
	@Override
	public Boolean isPinging(Long customerId,String userCode,String currencyType,String website) {
		List<ExDmPing> list=getBycustomerid(customerId,userCode, 1,currencyType,website);
		if(null!=list&&list.size()!=0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void pingLendMoney(String website,String currencyType) { //币是跌了的币才会调用平仓测试的方法
		//（1）这个人必须是借了款rmb，（2）这个人必须是买了跌了的币
		Map<String,String> map=new HashMap<String,String>();
		map.put("website", website);
		map.put("currencyType", currencyType);
		map.put("lendCoinType", "money");
		 List<ExDmLend> list=exDmLendService.getMayPingCustomer(map);
		 for(ExDmLend e:list){
			 List<ExDmPing> l= getBycustomerid(e.getCustomerId(),e.getUserCode(), 1,currencyType,website);//已进入平仓流程的
			 if(null==l||l.size()==0){
				 pingByCustomerId(e.getCustomerId(),e.getUserCode(),currencyType,website);
			 }
			
			 
		 }
	}
	@Override
	public void pingLendCoin(String  currencyType,String website) {//币是涨了的币才会调用平仓测试的方法
		
		//（1）这个人必须是借了币，（2）这个人必须是买了涨了的币
		Map<String,String> map=new HashMap<String,String>();
		map.put("website", website);
		map.put("currencyType", currencyType);
		map.put("lendCoinType", "virtualCoin");
		 List<ExDmLend> list=exDmLendService.getMayPingCustomer(map);
		 for(ExDmLend e:list){
			 pingByCustomerId(e.getCustomerId(),e.getUserCode(),currencyType,website);
			 
		 }
		
	}
}
