/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月5日 上午10:42:10
 */
package hry.calculate.mvc;

import com.alibaba.fastjson.JSON;
import hry.account.fund.model.AppOurAccount;
import hry.account.fund.model.AppPlatformSettlementRecord;
import hry.account.fund.service.AppOurAccountService;
import hry.account.fund.service.AppPlatformSettlementRecordService;
import hry.account.remote.RemoteAppAccountSureoldService;
import hry.calculate.mvc.dao.AppReportSettlementDao;
import hry.calculate.mvc.service.AppReportSettlementCulService;
import hry.calculate.mvc.service.AppReportSettlementService;
import hry.calculate.mvc.service.AppReportSettlementcoinService;
import hry.calculate.settlement.model.AppReportSettlement;
import hry.change.remote.exEntrust.RemoteExEntrustService;
import hry.change.remote.exEntrust.RemoteExExOrderService;
import hry.change.remote.lend.RemoteExDmLendService;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.product.model.ExCointoCoin;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExCointoCoinService;
import hry.exchange.remote.account.RemoteExProductService;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.date.DateUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月5日 上午10:42:10 
 */
@Service("appReportSettlementNoTrService")
public class AppReportSettlementNorTrService    {
	private static Logger logger = Logger.getLogger(AppReportSettlementNorTrService.class);
	
	@Resource(name = "remoteAppCustomerService")
	public RemoteAppCustomerService appCustomerService;
	
	@Resource(name = "appReportSettlementcoinService")
	public AppReportSettlementcoinService appReportSettlementcoinService;
	@Resource
	public RemoteExEntrustService remoteExEntrustService;
	@Resource
	public RemoteExDmLendService remoteExDmLendService;
	@Resource
	private  RedisService redisService;
	@Resource
	public RemoteAppAccountSureoldService remoteAppAccountSureoldService;
	@Resource
	public AppReportSettlementCulService appReportSettlementCulService;
	@Resource
	public AppReportSettlementService appReportSettlementService;
	@Resource
	public AppReportSettlementDao appReportSettlementDao;
	@Resource
	public RemoteExExOrderService remoteExExOrderService;
	@Resource
	public ExCointoCoinService exCointoCoinService;
	@Resource
	public ExEntrustService  exEntrustService;
	public String productListsb(String website){
		
		String productListStr = ExchangeDataCache.getStringData(website+":productList");
	    List<String> productList = JSON.parseArray(productListStr,String.class);
	    StringBuffer  productListsb=new StringBuffer();
	    if(null!=productList&&productList.size()>0){
	    	int a=0;
	    	while(a<productList.size()){
	    		productListsb.append(productList.get(a));
                 if(a<productList.size()-1){
                	 productListsb.append(",");
	    		}
	    		a++;
	    	}
	    	}
	    return productListsb.toString();
	}

	/**
	 * 闭市所有用户生成结算单
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    
	 * @return: void 
	 * @Date :          2017年4月7日 下午5:04:40   
	 * @throws:
	 */
	public void settlement() {
		RemoteQueryFilter filter=new RemoteQueryFilter(AppCustomer.class);
		filter.addFilter("isDelete=", 0);
		filter.addFilter("isReal=", 1);
		List<AppCustomer> list=appCustomerService.find(filter);
		logger.error("开始生成结算单啦");
	
		
		Date comeDate=new Date();
	    Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		for (String website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(website);

		    for(AppCustomer customer:list){
		    	Date nowDate=new Date();
		    	AppReportSettlement last=getLastSettlementByCustomerId(customer.getId(),currencyType,website);
		    	Date endDate=null;
				if(null==last){
					endDate=DateUtil.addDay(nowDate, -10);
				}else{
					endDate=last.getEndSettleDate();
				}
				appReportSettlementService.settlementByCustomerId(customer,comeDate, nowDate, endDate, currencyType, website);
		    	
		    } 
		}
		
		logger.error("结算生成结算单啦");
	}
    

	/**
	 * 获取用户的最后一条结算单对象
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerId
	 * @param:    @param currencyType
	 * @param:    @param website
	 * @param:    @return
	 * @return: AppReportSettlement 
	 * @Date :          2017年4月7日 下午5:05:09   
	 * @throws:
	 */
	public AppReportSettlement getLastSettlementByCustomerId(Long customerId,
			String currencyType, String website) {
		List<AppReportSettlement> list=appReportSettlementDao.getEndDate(customerId,currencyType,website);
		if(null!=null || list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	//最后一次收盘之后
	public void closePlateDeal(){
		//1，计算均价   (改为凌晨1点的定时任务  2017/04/19)
		averagePrice();
		//对所有可撤（未成和部成）的委托全部撤单（此时所有的冻结数据都归零）
	//	remoteExEntrustService.cancelAllExEntrust();
		//数据库用“沉淀”的成交明细刷新一次。
		
		//清除一个月以前得mongodb的日志数据
//		deleteLogAmonthAgo();
		
		//闭盘计算当日的收入总额和手续费收入总额
		calculationTodayMoney();
	}
	
	/**
	 * 闭盘计算上个结算时间到现在的收入总额和手续费收入总额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    
	 * @return: void 
	 * @Date :          2017年4月7日 下午1:50:14   
	 * @throws:
	 */
	private void calculationTodayMoney() {
		logger.error("闭盘计算上个结算时间到现在的收入总额和手续费收入总额");
		AppOurAccountService accountService=(AppOurAccountService) ContextUtil.getBean("appOurAccountService");
		AppPlatformSettlementRecordService recordService=(AppPlatformSettlementRecordService) ContextUtil.getBean("appPlatformSettlementRecordService");
		
		//需要查询,获取主账户
		AppOurAccount account=accountService.getAppOurAccount("cn", "cny", 0);
		if(account==null){
			logger.error("未查询到主资金账户！!!!!!!!");
		}else{
			//先获取开始结束时间，查到上次结算数据时就取上次结算的时间
			//没查到就取两天前
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate=new Date();
			String beginDate="";
			String endDate=format.format(nowDate);
			QueryFilter qf=new QueryFilter(AppPlatformSettlementRecord.class);
			qf.setOrderby("created desc");
			AppPlatformSettlementRecord record=recordService.get(qf);
			if(record==null){
				logger.error("未查询到平台结算对象，日期取两天前就行");
				beginDate=getAgoTwoDayString();
			}else{
				logger.error("查询到了上次平台结算对象，日期为："+record.getSettlementDay());
				beginDate=format.format(record.getSettlementDay());//上次计算时间
			}
			//计算当日的收入总额，充值总金额-提现总金额
			//充值总金额
			BigDecimal rechargeMoney=recordService.getRechargeMoney(beginDate,endDate);
			//提现总金额
			BigDecimal withdrawalsMoney=recordService.getWithdrawalsMoney(beginDate,endDate);
			//计算当日的手续费收入总额，交易买手续费+交易卖手续费+充值手续费+提现手续费
			//交易买手续费+交易卖手续费
			BigDecimal tradeFeeMoney=recordService.getTradeFeeMoney(beginDate,endDate);
			//充值手续费+提现手续费
			BigDecimal tranFeeMoney=recordService.getTranFeeMoney(beginDate,endDate);
			logger.error(endDate+"结算，查询到的上次结算数据时就取上次结算的时间的，充值总金额："+rechargeMoney
					+",提现总金额:"+withdrawalsMoney+",交易买卖手续费总金额:"+tradeFeeMoney+",充值提现手续费总金额:"+tradeFeeMoney);
			
			//保存结算记录信息
			AppPlatformSettlementRecord settlementRecord=new AppPlatformSettlementRecord();
			settlementRecord.setSettlementDay(nowDate);
			settlementRecord.setAccountMoneyNewBefore(account.getAccountMoneyNew());//结算前
			if(rechargeMoney!=null&&!"".equals(rechargeMoney)&&withdrawalsMoney!=null&&!"".equals(withdrawalsMoney)&&tradeFeeMoney!=null&&!"".equals(tradeFeeMoney)){
			settlementRecord.setAccountMoneyNewAfter(account.getAccountMoneyNew().add(rechargeMoney).subtract(withdrawalsMoney));//结算后
			settlementRecord.setRechargeMoneyOneSettlement(rechargeMoney);
			
			settlementRecord.setWithdrawalsMoneyOneSettlement(withdrawalsMoney);
			settlementRecord.setTransactionFeeOneSettlement(tradeFeeMoney);
			settlementRecord.setRechargeOrWithdrawalsFeeOneSettlement(tranFeeMoney);
			recordService.save(settlementRecord);
			
			//查完以后需要保存，更新账户总额，此次结算新增账户额（充值-提现），新增手续费额
			account.setAccountMoneyNew(account.getAccountMoneyNew().add(rechargeMoney).subtract(withdrawalsMoney));
			account.setTodayAddedMoney(rechargeMoney.subtract(withdrawalsMoney));
			account.setTodayAddedFee(tradeFeeMoney.add(tranFeeMoney));
			account.setAccountFee(account.getAccountFee().add(tradeFeeMoney.add(tranFeeMoney)));
			accountService.update(account);
			}
		}
	}
	

	
	/**
	 * 获取两天前的时间
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param s
	 * @param:    @return
	 * @return: String 
	 * @Date :          2017年4月6日 下午5:18:44   
	 * @throws:
	 */
    public static String getAgoTwoDayString(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Calendar c = Calendar.getInstance();
        //前两天
        c.setTime(new Date());
        c.add(Calendar.DATE, -2);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }
    /**
     * 
     * <p> TODO</p>
     * @author:         Zhang Lei
     * @param:    @param s
     * @param:    @return
     * @return: String 
     * @Date :          2017年4月6日 下午5:18:44   
     * @throws:
     */
    public static String getLastAmonthDayString(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Calendar c = Calendar.getInstance();
    	//过去一月
    	c.setTime(new Date());
    	c.add(Calendar.MONTH, -1);
    	Date m = c.getTime();
    	String mon = format.format(m);
    	return mon;
    }
    /**
     * 一个月前的日期
     * <p> TODO</p>
     * @author:         Zhang Lei
     * @param:    @param s
     * @param:    @return
     * @return: String 
     * @Date :          2017年4月6日 下午5:18:44   
     * @throws:
     */
    public static Date getLastAmonthDay(){
    	Calendar c = Calendar.getInstance();
    	//过去一月
    	c.setTime(new Date());
    	c.add(Calendar.MONTH, -1);
    	Date m = c.getTime();
    	return m;
    }
	
	/**
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * 					金科取最后一笔成交价为明日开盘价
	 * 					存入币种的priceLimits字段中
	 * @param:    
	 * @return: void 
	 * @Date :          2016年12月1日 下午3:58:22   
	 * @throws:
	 */
	public void averagePrice(){
		logger.error("取最后一笔成交价为明日开盘价存入币种的AveragePrice字段中");
		List<ExCointoCoin> listExCointoCoin=exCointoCoinService.getBylist(null,null,1);
				for (ExCointoCoin exCointoCoin : listExCointoCoin) {
					  String header =exEntrustService.getHeader("cn", "cny", exCointoCoin.getCoinCode(), exCointoCoin.getFixPriceCoinCode());
					  String currentExchangPrice=ExchangeDataCache.getStringData(header+":"+ExchangeDataCache.CurrentExchangPrice);
					 if(null!=currentExchangPrice){
						 BigDecimal currentExchangPricebig=new BigDecimal(currentExchangPrice);
						 exCointoCoin.setAveragePrice(currentExchangPricebig);
						 exCointoCoinService.update(exCointoCoin);
					   	//更新开盘价
						logger.error("更新开盘价");
						ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.OpenedExchangPrice,currentExchangPricebig.toString());
	
						 }
				}
		}
		
		
		
	public void closePlateDealExEntrust() {
		closePlateDeal();
		//settlement();
	}
	
	
	
	//=======================金科币凌晨1点定时计算================================================================
	/**
	 * 自动计算最后一笔成交价和开盘价
	 */
	public void autoCalculationOpenprice() {
		logger.error("金科系统改造，非标准版");
		logger.error("定时任务自动计算最后一笔成交价和开盘价："+new Date());
		String productListStr = ExchangeDataCache.getStringData("cn:productList");
		if (!StringUtils.isEmpty(productListStr)) {
			logger.error("定时任务产品的str:"+productListStr);
				List<String> productList = JSON.parseArray(productListStr, String.class);
				for (String coinCode : productList) {
					ExOrderInfoService exOrderService = (ExOrderInfoService) ContextUtil.getBean("exOrderInfoService");
					ExOrderInfo exOrderInfo =  exOrderService.getAveragePriceYesterday(coinCode);
					
					logger.error("定时任务币的种类："+coinCode+",查询到的昨天最后一笔流水为："+exOrderInfo);
					//保存成交价
					if(null!=exOrderInfo&&null!=exOrderInfo.getTransactionPrice()){
						RemoteExProductService remoteExProductService = (RemoteExProductService) ContextUtil.getBean("remoteExProductService");
						ExProduct prodect=remoteExProductService.findByCoinCode(coinCode, null);
						
						if(null!=exOrderInfo&&null!=exOrderInfo.getTransactionPrice()){
							 List<ExCointoCoin> list=exCointoCoinService.getBylist(exOrderInfo.getCoinCode(), exOrderInfo.getFixPriceCoinCode(),null);
							if(null!=list&list.size()>0){
								ExCointoCoin exCointoCoin=list.get(0);
								 exCointoCoin.setAveragePrice(exOrderInfo.getTransactionPrice());
								 exCointoCoinService.update(exCointoCoin);
							}
						}
						//更新开盘价
						logger.error("定时任务更新开盘价");
						ExchangeDataCache.setStringData("cn:cny:"+coinCode+":openedExchangPrice", exOrderInfo.getTransactionPrice().setScale(5, BigDecimal.ROUND_HALF_UP).toString());
					}
			}
		}
	}
	
	//=======================start核算====================
	
	public List<Map<String,Object>>  culSureOldAccountAllCustomerErrorInfo(Integer days) {
		return null;
	}	

}
