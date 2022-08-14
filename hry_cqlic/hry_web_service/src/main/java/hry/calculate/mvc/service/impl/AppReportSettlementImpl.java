/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月5日 上午10:42:10
 */
package hry.calculate.mvc.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppTransaction;
import hry.account.remote.RemoteAppAccountService;
import hry.account.remote.RemoteAppAccountSureoldService;
import hry.account.remote.RemoteAppTransactionService;
import hry.bean.PageResult;
import hry.calculate.mvc.dao.AppReportSettlementDao;
import hry.calculate.mvc.po.OperationAccountFundInfoLog;
import hry.calculate.mvc.service.AppReportSettlementCulService;
import hry.calculate.mvc.service.AppReportSettlementService;
import hry.calculate.mvc.service.AppReportSettlementcoinService;
import hry.calculate.settlement.model.AppReportSettlement;
import hry.calculate.settlement.model.AppReportSettlementcoin;
import hry.change.remote.account.RemoteExDigitalmoneyAccountService;
import hry.change.remote.dmtransaction.RemoteExDmTransactionService;
import hry.change.remote.exEntrust.RemoteExEntrustService;
import hry.change.remote.exEntrust.RemoteExOrderService;
import hry.change.remote.lend.RemoteExDmLendService;
import hry.core.constant.CodeConstant;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.remote.RemoteAppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmLendIntent;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.DifCustomer;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.model.ExOrderInfo;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.date.DateUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.serialize.Mapper;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月5日 上午10:42:10 
 */
@Service("appReportSettlementService")
public class AppReportSettlementImpl extends BaseServiceImpl<AppReportSettlement, Long> implements
		AppReportSettlementService  {
	private static Logger logger = Logger.getLogger(AppReportSettlementImpl.class);
	@Resource(name="appReportSettlementDao")
	@Override
	public void setDao(BaseDao<AppReportSettlement, Long> dao) {
		super.dao = dao;
	}
	
	@Resource(name = "remoteAppCustomerService")
	public RemoteAppCustomerService appCustomerService;
	@Resource(name = "remoteExEntrustService")
	public RemoteExEntrustService exEntrustService;
	@Resource(name = "remoteExOrderService")
	public RemoteExOrderService exOrderInfoService;
	@Resource(name = "remoteExDigitalmoneyAccountService")
	public RemoteExDigitalmoneyAccountService remoteExDigitalmoneyAccountService;
	@Resource(name = "appReportSettlementcoinService")
	public AppReportSettlementcoinService appReportSettlementcoinService;
	@Resource(name = "remoteExDmTransactionService")
	public RemoteExDmTransactionService remoteExDmTransactionService;
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
	
	/**
	 * 闭市计算每个用户结算数据信息（上次结算到此次结算日）
	 */
	public String[] settlementByCustomerId(AppCustomer l,Date comeDate,Date nowDate,Date endDate
			 ,String currencyType,String website ) {
		String[] relt = new String[2];
	
		String beginDateString=DateUtil.dateToString(endDate, StringConstant.DATE_FORMAT_FULL);
		String endDateString=DateUtil.dateToString(nowDate, StringConstant.DATE_FORMAT_FULL);
	    String productListsb=  productListsb(website);
		AppReportSettlement exSettlementFinance	=new AppReportSettlement();
		exSettlementFinance.setUserCode(l.getUserCode());
		exSettlementFinance.setStutus(-1);
		exSettlementFinance.setCustomerId(l.getId());
		exSettlementFinance.setSettleDate(comeDate);
		exSettlementFinance.setCurrencyType(currencyType);
		exSettlementFinance.setWebsite(website);
		exSettlementFinance.setUserName(l.getUserName());
		exSettlementFinance.setStartSettleDate(endDate);
		exSettlementFinance.setEndSettleDate(nowDate);
		
		RemoteAppPersonInfoService remoteAppPersonInfoService = (RemoteAppPersonInfoService)ContextUtil.getBean("remoteAppPersonInfoService");
		AppPersonInfo appPersonInfo = remoteAppPersonInfoService.getByCustomerId(l.getId());
		exSettlementFinance.setTrueName(appPersonInfo.getTrueName());
		exSettlementFinance.setCustomerType(appPersonInfo.getCustomerType());
		//充值额,提现额，提现手续费
		RemoteAppTransactionService remoteAppTransactionService = (RemoteAppTransactionService)ContextUtil.getBean("remoteAppTransactionService");
		//查出所以充值提现的成功数据
		List<AppTransaction>  rechargeList=remoteAppTransactionService.record(l.getId(),null,null,beginDateString,endDateString,currencyType,website);
		BigDecimal rechargeMoney=new BigDecimal("0");
		BigDecimal withdrawMoney=new BigDecimal("0");
		BigDecimal withdrawFee=new BigDecimal("0");
		for(AppTransaction at:rechargeList){
			if(at.getTransactionType()==1||at.getTransactionType()==3 ||at.getTransactionType()==5){
				rechargeMoney=rechargeMoney.add(at.getTransactionMoney());
			}else{
				withdrawMoney=withdrawMoney.add(at.getTransactionMoney());
				withdrawFee=withdrawFee.add(at.getFee());
			}
			
		}
		exSettlementFinance.setRechargeMoney(rechargeMoney);
		exSettlementFinance.setWithdrawMoney(withdrawMoney);
		exSettlementFinance.setWithdrawFee(withdrawFee);
		
		
		//买成交额,卖成交额，成交手续费
		RemoteQueryFilter transactionMoneyfilter=new RemoteQueryFilter(ExOrderInfo.class);
		transactionMoneyfilter.addFilter("customerId=", l.getId());
		transactionMoneyfilter.addFilter("transactionTime>=",beginDateString);
		transactionMoneyfilter.addFilter("transactionTime<",endDateString);
		transactionMoneyfilter.addFilter("currencyType=",currencyType);
		transactionMoneyfilter.addFilter("website=",website);
		List<ExOrderInfo> buyTransactionMoneylist=exOrderInfoService.find(transactionMoneyfilter);
		BigDecimal buyTransactionMoney=new BigDecimal("0");
		BigDecimal sellTransactionMoney=new BigDecimal("0");
		BigDecimal transactionFee=new BigDecimal("0");
		for(ExOrderInfo e:buyTransactionMoneylist){
			if(e.getType()==1){
				buyTransactionMoney=buyTransactionMoney.add(e.getTransactionSum());
			}else{
				sellTransactionMoney=sellTransactionMoney.add(e.getTransactionSum());
			}
			transactionFee=transactionFee.add(e.getTransactionFee());
		}
		exSettlementFinance.setBuyTransactionMoney(buyTransactionMoney);
		exSettlementFinance.setSellTransactionMoney(sellTransactionMoney);
		exSettlementFinance.setTransactionFee(transactionFee);
		
		
		//期初金额，期末金额
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService)ContextUtil.getBean("remoteAppAccountService");
		
		AppAccount appAccount=remoteAppAccountService.getByCustomerIdAndType(l.getId(),currencyType,website);
		if(null==appAccount){
			logger.error(l.getId()+currencyType+website+"资金账号为空");
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="资金账号为空";
			return relt;
		}else{
			//期末资金
		    exSettlementFinance.setEndMoney(appAccount.getHotMoney().add(appAccount.getColdMoney()));
			//期末净资产
			BigDecimal rMBNetAsset=remoteExDigitalmoneyAccountService.getRMBNetAsset(l.getId(),null, currencyType, website);
			exSettlementFinance.setEndNetAsset(rMBNetAsset);
		}
		QueryFilter fiar=new QueryFilter(AppReportSettlement.class);
		fiar.addFilter("customerId=",l.getId());
		fiar.addFilter("currencyType=",currencyType);
		fiar.addFilter("website=",website);
		List<AppReportSettlement> fiarsclist= this.find(fiar);
		if(null==fiarsclist||fiarsclist.size()==0){
			exSettlementFinance.setBeginMoney(new BigDecimal("0"));  //期初资产
			exSettlementFinance.setBeginNetAsset(new BigDecimal("0"));//期初净资产
		}else{
			exSettlementFinance.setBeginMoney(fiarsclist.get(fiarsclist.size()-1).getEndMoney());  //期初资产
			
			exSettlementFinance.setBeginNetAsset(fiarsclist.get(fiarsclist.size()-1).getEndNetAsset());//期末净资产
		}
		
		
		//借入杠杆资金
		Map<String,BigDecimal> getLendByExDmLend=appReportSettlementCulService.getLendByExDmLend(l.getId(),currencyType, currencyType, website, beginDateString, endDateString);
		BigDecimal daylendMoney=getLendByExDmLend.get("lendMoney");
		exSettlementFinance.setDaylendMoney(daylendMoney);
		exSettlementFinance.setLendMoney(daylendMoney);
		
		//已偿还利息
		Map<String,BigDecimal> getRepayByExDmLendIntent=appReportSettlementCulService.getRepayByExDmLendIntent(l.getId(), currencyType,"interest", website, beginDateString, endDateString);
		BigDecimal repayInterestMoney=getRepayByExDmLendIntent.get("repaylendMoney");
		exSettlementFinance.setRepayInterestMoney(repayInterestMoney);
		
		//已偿本金
		Map<String,BigDecimal> getRepayByExDmLendIntent1=appReportSettlementCulService.getRepayByExDmLendIntent(l.getId(), currencyType,"principal", website, beginDateString, endDateString);
		BigDecimal dayrepaylendMoney=getRepayByExDmLendIntent1.get("repaylendMoney");
		exSettlementFinance.setRepaylendMoney(dayrepaylendMoney);
		
		//已还融资
	    exSettlementFinance.setRepaylendMoney(repayInterestMoney.add(dayrepaylendMoney));
		
		//未还利息
		Map<String,BigDecimal> getLendByExDmLend1=appReportSettlementCulService.getLendByExDmLend(l.getId(), currencyType,currencyType, website, null, null);
		BigDecimal notInterestMoney=getLendByExDmLend1.get("notInterestMoney");
		BigDecimal lendRate=getLendByExDmLend1.get("lendRate");
		exSettlementFinance.setNotInterestMoney(notInterestMoney);
		exSettlementFinance.setLendRate(lendRate);
		
		//期初借款金额
		if(null==fiarsclist||fiarsclist.size()==0){
			exSettlementFinance.setBeginLendMoney(new BigDecimal("0"));
		}else{
			BigDecimal endLendMoney=fiarsclist.get(fiarsclist.size()-1).getEndLendMoney();
			exSettlementFinance.setBeginLendMoney(null!=endLendMoney?endLendMoney:new BigDecimal("0"));  //期初资产
		}
		//期末杆资金
		exSettlementFinance.setLendMoney(appAccount.getLendMoney());
		exSettlementFinance.setEndLendMoney(appAccount.getLendMoney());
		
		//盈亏
		BigDecimal profitAndLossMoney=new BigDecimal("0");
		profitAndLossMoney=exSettlementFinance.getEndNetAsset().subtract(exSettlementFinance.getBeginNetAsset());
		profitAndLossMoney=profitAndLossMoney.subtract(exSettlementFinance.getRechargeMoney()).add(exSettlementFinance.getWithdrawMoney());
		
		//计算币
		RemoteQueryFilter edafilter=new RemoteQueryFilter(ExDigitalmoneyAccount.class);
		edafilter.addFilter("customerId=", l.getId());
		edafilter.addFilter("currencyType=",currencyType);
		edafilter.addFilter("website=",website);
		edafilter.addFilter("coinCode_in",productListsb);
		edafilter.addFilter("currencyType=",currencyType);
		edafilter.addFilter("website=",website);
		List<ExDigitalmoneyAccount> listeda=remoteExDigitalmoneyAccountService.find(edafilter);
		for(ExDigitalmoneyAccount eda:listeda){
			AppReportSettlementcoin coin=new AppReportSettlementcoin();
			coin.setUserCode(l.getUserCode());
			coin.setStutus(-1);
			coin.setCustomerId(l.getId());
			coin.setSettleDate(nowDate);
			coin.setStartSettleDate(endDate);
			coin.setEndSettleDate(nowDate);
			coin.setCoinCode(eda.getCoinCode());
			coin.setCurrencyType(currencyType);
			coin.setWebsite(website);
			coin.setAccountId(eda.getId());
			coin.setTrueName(appPersonInfo.getTrueName());
			coin.setCustomerType(appPersonInfo.getCustomerType());
			coin.setUserName(l.getUserName());
			
			
			//计算币的买成交量，卖成交量
			transactionMoneyfilter.addFilter("coinCode=", eda.getCoinCode());
			List<ExOrderInfo> Transactioncountlist=exOrderInfoService.find(transactionMoneyfilter);
			BigDecimal buyTransactioncount=new BigDecimal("0");
			BigDecimal sellTransactioncount=new BigDecimal("0");
			for(ExOrderInfo e:Transactioncountlist){
				if(e.getType()==1){
					buyTransactioncount=buyTransactioncount.add(e.getTransactionCount());
				}else{
					sellTransactioncount=sellTransactioncount.add(e.getTransactionCount());
				}
			}
			coin.setBuyTransactionCount(buyTransactioncount);
			coin.setSellTransactionCount(sellTransactioncount);
			
			
		    //币的期末持仓，期末资产
			coin.setEndCoinCount(eda.getHotMoney().add(eda.getColdMoney()));//期末持仓
			String currentExchangPrices=ExchangeDataCache.getStringData(eda.getWebsite()+":"+eda.getCurrencyType()+":"+eda.getCoinCode()+":"+ExchangeDataCache.CurrentExchangPrice);
			BigDecimal currentExchangPrice=new BigDecimal("0");
			if(!StringUtil.isEmpty(currentExchangPrices)){
				currentExchangPrice=new BigDecimal(currentExchangPrices);
			}
			if(null!=currentExchangPrice&&currentExchangPrice.compareTo(new BigDecimal("0"))!=0){
				coin.setEndMoney(coin.getEndCoinCount().multiply(currentExchangPrice)); //期末资产
			}
			
			//币的期初持仓，期初资产
			QueryFilter fi=new QueryFilter(AppReportSettlementcoin.class);
			fi.addFilter("customerId=",l.getId());
			fi.addFilter("coinCode=",eda.getCoinCode());
			fi.addFilter("currencyType=",currencyType);
			fi.addFilter("website=",website);
			List<AppReportSettlementcoin> arsclist= appReportSettlementcoinService.find(fi);
			if(null==arsclist||arsclist.size()==0){
				coin.setBeginCoinCount(new BigDecimal("0"));  //期初持仓
				coin.setBeginMoney(new BigDecimal("0"));  //期初资产
			}else{
				coin.setBeginCoinCount(arsclist.get(arsclist.size()-1).getEndCoinCount());  //期初持仓
				coin.setBeginMoney(arsclist.get(arsclist.size()-1).getEndMoney());  //期初资产
			}
	
			
			//币的转入转出量
			RemoteQueryFilter edtfilter=new RemoteQueryFilter(ExDmTransaction.class);
			edtfilter.addFilter("customerId=", l.getId());
			edtfilter.addFilter("status=", 2);
			edtfilter.addFilter("coinCode=", eda.getCoinCode());
			edtfilter.addFilter("modified>=",beginDateString);
			edtfilter.addFilter("modified<",endDateString);
			edtfilter.addFilter("currencyType=",currencyType);
			edtfilter.addFilter("website=",website);
			List<ExDmTransaction> edtlist=remoteExDmTransactionService.find(edtfilter);
			BigDecimal inCoinCount=new BigDecimal("0");
			BigDecimal outCoinCount=new BigDecimal("0");
			BigDecimal inCoinFee=new BigDecimal("0");
			BigDecimal outCoinFee=new BigDecimal("0");
			for(ExDmTransaction edt:edtlist){
				if(edt.getTransactionType()==1){
					inCoinCount=inCoinCount.add(edt.getTransactionMoney());
					inCoinFee=inCoinFee.add(null==edt.getFee()?new BigDecimal("0"):edt.getFee());
				}else{
					outCoinCount=outCoinCount.add(edt.getTransactionMoney());
					outCoinFee=outCoinFee.add(null==edt.getFee()?new BigDecimal("0"):edt.getFee());
				}
			}
			coin.setInCoinFee(inCoinFee);
			coin.setOutCoinFee(outCoinFee);
			coin.setInCoinCount(inCoinCount);
			coin.setOutCoinCount(outCoinCount);
		
			//融币
			RemoteQueryFilter listedlFilter=new RemoteQueryFilter(ExDmLend.class);
			listedlFilter.addFilter("customerId=", l.getId());
			listedlFilter.addFilter("lendTime>=",beginDateString);
			listedlFilter.addFilter("lendTime<",endDateString);
			listedlFilter.addFilter("lendCoin=",currencyType);
			listedlFilter.addFilter("currencyType=",currencyType);
			listedlFilter.addFilter("website=",website);
			List<ExDmLend> listedlcoin=remoteExDmLendService.find(listedlFilter);
			BigDecimal lendCoin=new BigDecimal("0");
			for(ExDmLend e:listedlcoin){
				lendCoin=lendCoin.add(e.getLendCount());
			}
			coin.setLendMoney(lendCoin);
			
			//已还融币
			RemoteQueryFilter edlicoinFilter=new RemoteQueryFilter(ExDmLendIntent.class);
			edlicoinFilter.addFilter("customerId=", l.getId());
			edlicoinFilter.addFilter("factTime>=",beginDateString);
			edlicoinFilter.addFilter("factTime<",endDateString);
			edlicoinFilter.addFilter("lendCoin=",currencyType);
			edlicoinFilter.addFilter("currencyType=",currencyType);
			edlicoinFilter.addFilter("website=",website);
			List<ExDmLendIntent> listedlicoin=remoteExDmLendService.findintent(edlicoinFilter);
			BigDecimal repaylendcoin=new BigDecimal("0");
			for(ExDmLendIntent e:listedlicoin){
				repaylendcoin=repaylendcoin.add(e.getRepayCount());
			}
			coin.setRepaylendMoney(repaylendcoin);
			
			profitAndLossMoney=profitAndLossMoney.add((coin.getOutCoinCount().subtract(coin.getInCoinCount())).multiply(currentExchangPrice));
			
			

			
			//借入杠杆资金
			Map<String,BigDecimal> getLendByExDmLendcoin=appReportSettlementCulService.getLendByExDmLend(l.getId(), coin.getCoinCode(),currencyType, website, beginDateString, endDateString);
			BigDecimal daylendMoneycoin=getLendByExDmLendcoin.get("lendMoney");
			coin.setDaylendMoney(daylendMoneycoin);
			coin.setLendMoney(daylendMoneycoin);
			
			//已偿还利息
			Map<String,BigDecimal> getRepayByExDmLendIntentcoin=appReportSettlementCulService.getRepayByExDmLendIntent(l.getId(), currencyType,"interest", website, beginDateString, endDateString);
			BigDecimal repayInterestMoneycoin=getRepayByExDmLendIntentcoin.get("repaylendMoney");
			coin.setRepayInterestMoney(repayInterestMoneycoin);
			
			//已偿本金
			Map<String,BigDecimal> getRepayByExDmLendIntent1coin=appReportSettlementCulService.getRepayByExDmLendIntent(l.getId(), currencyType,"principal", website, beginDateString, endDateString);
			BigDecimal dayrepaylendMoneycoin=getRepayByExDmLendIntent1coin.get("repaylendMoney");
			coin.setRepaylendMoney(dayrepaylendMoneycoin);
			
			//已还融资
			coin.setRepaylendMoney(repayInterestMoneycoin.add(dayrepaylendMoneycoin));
			
			//未还利息
			Map<String,BigDecimal> getLendByExDmLend1coin=appReportSettlementCulService.getLendByExDmLend(l.getId(), coin.getCoinCode(),currencyType, website, null, null);
			BigDecimal notInterestMoneycoin=getLendByExDmLend1.get("notInterestMoney");
			BigDecimal lendRatecoin=getLendByExDmLend1coin.get("lendRate");
			coin.setNotInterestMoney(notInterestMoneycoin);
			coin.setLendRate(lendRatecoin);
			
			//期初借款金额
			if(null==fiarsclist||fiarsclist.size()==0){
				coin.setBeginLendMoney(new BigDecimal("0"));
			}else{
				coin.setBeginLendMoney(fiarsclist.get(fiarsclist.size()-1).getEndLendMoney());  //期初资产
			}
			//期末杆资金
			coin.setLendMoney(coin.getLendMoney());
			coin.setEndLendMoney(coin.getLendMoney());
			
			appReportSettlementcoinService.save(coin);
			
		}
		
		
		
		
		
		//盈亏
		
		exSettlementFinance.setProfitAndLossMoney(profitAndLossMoney);
		
		//保存结算单
		this.save(exSettlementFinance);
		relt[0]=CodeConstant.CODE_SUCCESS;
		relt[1]="";
		return relt;
}
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
    

	@Override
	public AppReportSettlement getLastSettlementByCustomerId(Long customerId,
			String currencyType, String website) {
		AppReportSettlementDao appReportSettlementDao =(AppReportSettlementDao)this.dao;
		List<AppReportSettlement> list=appReportSettlementDao.getEndDate(customerId,currencyType,website);
		if(null!=null || list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	//最后一次收盘之后
	public void closePlateDeal(){
		//对所有可撤（未成和部成）的委托全部撤单（此时所有的冻结数据都归零）
		remoteExEntrustService.cancelAllExEntrust();
		//数据库用“沉淀”的成交明细刷新一次。
		
		
	}
	public void test(){
		QuartzManager.removeJob("");
		
	}
	@Override
	public Integer getIsShowExSettlementFinance(Long customerId,
			String currencyType, String website) {
		if(!DifCustomer.getIsCommon()){
			QueryFilter filter=new QueryFilter(AppReportSettlement.class);
			 filter.addFilter("customerId=", customerId);
			 filter.addFilter("currencyType=", currencyType);
			 filter.addFilter("website=", website);
			 filter.addFilter("stutus=", 0);
			 filter.setNosaas();
			 List<AppReportSettlement> list=this.find(filter);
			 if(null!=list&&list.size()>0){
				 AppReportSettlement exSettlementFinance=  list.get(list.size()-1);
				 if(exSettlementFinance.getStutus()==0){
					 return 1;
				 }else{
					 return 0;
				 }
				
			 }else{
				 return 0;
			 }
			 
		}else{
			return 0;
		}
		
	}
	@Override
	public Integer confirmExSettlementFinance(Long settid,
			String currencyType, String website) {
		QueryFilter filter=new QueryFilter(AppReportSettlement.class);
		filter.addFilter("id<=", settid);
		filter.addFilter("customerId=", this.get(settid).getCustomerId());
		filter.addFilter("stutus=", 0);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
	
		List<AppReportSettlement> list=this.find(filter);
		for(AppReportSettlement appReportSettlement:list){
			 appReportSettlement.setStutus(1);
			 this.update(appReportSettlement);
			 
			 String beginDateString=DateUtil.dateToString(appReportSettlement.getStartSettleDate(), StringConstant.DATE_FORMAT_FULL);
			 String endDateString=DateUtil.dateToString(appReportSettlement.getEndSettleDate(), StringConstant.DATE_FORMAT_FULL);
			 List<AppReportSettlementcoin>  listcoin=	 this.selectExSettlementcoin(appReportSettlement.getCustomerId(),currencyType,website,beginDateString,endDateString);
			 for(AppReportSettlementcoin l:listcoin){
				 l.setStutus(1);
				 appReportSettlementcoinService.update(l);
		    }
		}    
		
		return null;
	}
	@Override
	public AppReportSettlementcoin selectExSettlementcoinBycoincode(
			Long customerId, String coinCode,String currencyType, String website,String beginDateString,String endDateString) {
		QueryFilter filter=new QueryFilter(AppReportSettlementcoin.class);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
		filter.addFilter("startSettleDate=", beginDateString);
		filter.addFilter("endSettleDate=", endDateString);
		if(null!=coinCode){
			filter.addFilter("coinCode=", coinCode);
		}
		List<AppReportSettlementcoin> list=appReportSettlementcoinService.find(filter);
		if(null!=list&&list.size()>0){
			return list.get(list.size()-1);
		}else{
			return null;
		}
	}
	@Override
	public List<AppReportSettlementcoin> selectExSettlementcoin(
			Long customerId, String currencyType, String website,String beginDateString,String endDateString) {
		QueryFilter filter=new QueryFilter(AppReportSettlementcoin.class);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
		filter.addFilter("startSettleDate=", beginDateString);
		filter.addFilter("endSettleDate=", endDateString);
		List<AppReportSettlementcoin> list=appReportSettlementcoinService.find(filter);
		return list;
	}


	@Override
   public void changeClosePlateTime(String openAndclosePlateTime){
    	String[] openAndclosePlateTimearr=openAndclosePlateTime.split(",");
    	 String[]	arrtime=openAndclosePlateTimearr[openAndclosePlateTimearr.length-1].split(":");
		 Integer fen=Integer.valueOf(arrtime[1])+2;//往后延2分钟
		 Integer miao=Integer.valueOf(arrtime[2]);
		 if(fen>60){
			fen=59;
			miao=59;
		 }
	    String jobtime=miao.toString()+" "+fen.toString()+" " +arrtime[0]+"  * * ?" ;
	    QuartzManager.removeJob("jobclosePlateDealExEntrust");
	    //收盘时间往后延2分钟  处理交易数据=======================================================
		ScheduleJob jobclosePlateDealExEntrust = new ScheduleJob();
		jobclosePlateDealExEntrust.setSpringId("appReportSettlementNoTrService");
		jobclosePlateDealExEntrust.setMethodName("closePlateDealExEntrust");
		QuartzManager.addJob("jobclosePlateDealExEntrust", jobclosePlateDealExEntrust, QuartzJob.class,jobtime);

	}
	@Override
	public void changeCloseTime(String closeTime){
		if(!DifCustomer.getIsCommon()){
			if(!StringUtil.isEmpty(closeTime)){
			    String[]	arrtime=closeTime.split(":");
				 String jobtime=arrtime[2]+" "+arrtime[1]+" " +arrtime[0]+"  * * ?" ;
				 
				    QuartzManager.removeJob("jobcloseFinaceSettle");
					ScheduleJob jobcloseFinaceSettle = new ScheduleJob();
					jobcloseFinaceSettle.setSpringId("appReportSettlementNoTrService");
					jobcloseFinaceSettle.setMethodName("closeFinaceSettle");                //0 0 1 * * ?  这凌晨一点
					QuartzManager.addJob("jobcloseFinaceSettle", jobcloseFinaceSettle, QuartzJob.class,jobtime);//
			}
			
		}
		
	}

	@Override
	public Integer platformconfirmExSettlement(
			String currencyType, String website) {
		/*AppReportSettlement appReportSettlement=getLastSettlement();
		String beginDateString=DateUtil.dateToString(appReportSettlement.getStartSettleDate(), StringConstant.DATE_FORMAT_FULL);
		String endDateString=DateUtil.dateToString(appReportSettlement.getEndSettleDate(), StringConstant.DATE_FORMAT_FULL);
		*/
		 QueryFilter filter=new QueryFilter(AppReportSettlement.class);
		 filter.addFilter("stutus<", 0);
		 List<AppReportSettlement> list=this.find(filter);
		 for(AppReportSettlement l:list){
			 l.setStutus(0);
			 this.update(l);
			 
			 
		 }
		 QueryFilter filtercoin=new QueryFilter(AppReportSettlementcoin.class);
		 filter.addFilter("stutus<", 0);
		 List<AppReportSettlementcoin> listcoin=appReportSettlementcoinService.find(filtercoin);
		 for(AppReportSettlementcoin c:listcoin){
			 c.setStutus(0);
			 appReportSettlementcoinService.update(c);
			 
			 
		 }
		 
		return 1;
	}

	
	//重新结算
	@Override
	public void settlementByCustomerIds(String[] ids) {
		int i=0;
		while(i<ids.length){
			Long id=Long.valueOf(ids[i]);
			AppReportSettlement appReportSettlement=this.get(id);
			//如果这个结算单已经被客户确认过了，就不需要再重新生成
			if(appReportSettlement.getStutus()==1){
				return;
			}
			QueryFilter filter=new QueryFilter(AppReportSettlementcoin.class);
			filter.addFilter("customerId=", appReportSettlement.getCustomerId());
			filter.addFilter("currencyType=", appReportSettlement.getCurrencyType());
			filter.addFilter("website=", appReportSettlement.getWebsite());
			filter.addFilter("startSettleDate=", appReportSettlement.getStartSettleDate());
			filter.addFilter("endSettleDate=", appReportSettlement.getEndSettleDate());
			
			appReportSettlementcoinService.delete(filter);
			this.delete(id);
			//结算截至到现在时间
			this.settlementByCustomerId(appCustomerService.getById(appReportSettlement.getCustomerId()),new Date(),new Date(), appReportSettlement.getStartSettleDate(),
					appReportSettlement.getCurrencyType(), appReportSettlement.getWebsite());
			i++;
		}
	}
	
	
	/**
	 * 查询setterlement 分页 
	 * 
	 */
	@Override
	public PageResult findPageByStates(QueryFilter filter,Integer i,String userName) {
		
		PageResult pageResult = new PageResult();
		Page<AppReportSettlement> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		
	 	((AppReportSettlementDao) dao).findPageByStates(i,userName);
		
		pageResult.setRows(page.getResult());
		// 设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		
		return pageResult ;
	}
	

	@Override
	public String haveProblemAccount() {
		
		return null;
	}

	


	@Override
	public void postAuditByCustomer(String[] ids) {
		int i=0;
		while(i<ids.length){
			Long id=Long.valueOf(ids[i]);
			AppReportSettlement appReportSettlement=this.get(id);
			//如果这个结算单已经被客户确认过了，就不需要平台再确认
			if(appReportSettlement.getStutus()==1){
				return;
			}
			appReportSettlement.setStutus(0);
			this.update(appReportSettlement);
		    i++;
		}
		
	}
	
	//=====start核算============
	@Override
	public List<Map<String, Object>> culAccountByCustomersErrorInfosureold(
			String[] ids,Boolean iserrorright) {
		List<Map<String,Object>>  listErrorInfo=new ArrayList<Map<String,Object>>();
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
			for (String website : mapLoadWeb.keySet()) {
			    String currencyType=mapLoadWeb.get(website);
			    int i=0;
				while(i<ids.length){
					Long id=Long.valueOf(ids[i]);
					AppCustomer appCustomer=appCustomerService.getById(id);
					Map<String,Object> map=appReportSettlementCulService.culAccountByCustomer(appCustomer.getId(),currencyType, website,false,iserrorright); 
			    	if(null!=map){
			    		map.put("customerId", id);
				        map.put("createTime", new Date());
			    		listErrorInfo.add(map);
			    	}
					i++;
				}
			}
			
			//redisService.setList("user_fund_check", listErrorInfo);
			
			
			
			return listErrorInfo;
		
	}
	@Override
	public void culAccountByCustomerssureold(String[] ids) {
		List<Map<String,Object>>  listErrorInfo=new ArrayList<Map<String,Object>>();
		int i=0;
		StringBuffer userNames=new StringBuffer("");
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
			for (String website : mapLoadWeb.keySet()) {
			    String currencyType=mapLoadWeb.get(website);
				while(i<ids.length){
					Long id=Long.valueOf(ids[i]);
					AppCustomer appCustomer=appCustomerService.getById(id);
					
					 Map<String,Object> map= appReportSettlementCulService.culAccountByCustomer(appCustomer.getId(),currencyType,website,true,false);
					 
					 if(null!=map){
						    map.put("customerId", id);
				    		map.put("createTime", new Date());
				    		listErrorInfo.add(map);
				    	}
				//	 userNames.append(appCustomer.getUserName()+",");
					 userNames.append(appCustomer.getUserName());
					 i++;
				}
			}
		//保存操作日志
			if(listErrorInfo.size()>0){
				OperationAccountFundInfoLog operationAccountFundInfoLog=new OperationAccountFundInfoLog();
				operationAccountFundInfoLog.setWebsite("cn");
				operationAccountFundInfoLog.setCurrencyType("cny");
				operationAccountFundInfoLog.setOperatorName(ContextUtil.getCurrentUser().getUsername());
				operationAccountFundInfoLog.setUserName(userNames.toString());
				operationAccountFundInfoLog.setContext(Mapper.objectToJson(listErrorInfo));
				operationAccountFundInfoLog.setCreatDate(new Date());
				List<OperationAccountFundInfoLog> operationAccountFundInfoLoglist = JSON.parseArray(redisService.get("operation_accountfundinfo_log"), OperationAccountFundInfoLog.class);
				if (operationAccountFundInfoLoglist == null) {
					operationAccountFundInfoLoglist = new ArrayList<OperationAccountFundInfoLog>();
				}
				operationAccountFundInfoLoglist.add(0, operationAccountFundInfoLog);
				redisService.save("operation_accountfundinfo_log", JSON.toJSONString(operationAccountFundInfoLoglist));
				
			}
					
	}
	
	//=====end核算============
	/*@Override
	public void culAccountAllCustomersureold() {
		List<Map<String,Object>>  listErrorInfo=new ArrayList<Map<String,Object>>();
		long start=System.currentTimeMillis();
		logger.error(start);
		MongoTemplate mongoTemplate = (MongoTemplate) ContextUtil.getBean("mongoTemplate");
	    DBCollection course = mongoTemplate.getDb().getCollection("user_fund_check_all");
	    DBObject query1 = new BasicDBObject();  
	    DBCursor cursor = course.find(query1);
	    List<AppCustomer> list=new ArrayList<AppCustomer>();
	    while(cursor.hasNext()) {
	    	   DBObject document = cursor.next(); 
	    	   logger.error(document.toString());
	    	   AppCustomer appCustomer=new AppCustomer();
	    	   appCustomer.setId(Long.valueOf(document.get("customerId").toString()));
	    	   list.add(appCustomer);
	    }
	    Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		for (String website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(website);

		    for(AppCustomer l:list){
		    	 Map<String,Object> map=appReportSettlementCulService.culAccountByCustomer(l.getId(),currencyType, website,true);
		    	 if(null!=map){
			    		listErrorInfo.add(map);
			    	}
		    } 
		}
		
		//保存操作日志
		MongoUtil<OperationAccountFundInfoLog, Long> mongoUtil = new MongoUtil<OperationAccountFundInfoLog, Long>(
				OperationAccountFundInfoLog.class,"operation_accountfundinfo_log");
		OperationAccountFundInfoLog operationAccountFundInfoLog=new OperationAccountFundInfoLog();
		operationAccountFundInfoLog.setWebsite("cn");
		operationAccountFundInfoLog.setCurrencyType("cny");
		operationAccountFundInfoLog.setOperatorName(ContextUtil.getCurrentUser().getUsername());
		operationAccountFundInfoLog.setUserName("all");
		operationAccountFundInfoLog.setContext(Mapper.objectToJson(listErrorInfo));
		operationAccountFundInfoLog.setCreatDate(new Date());
		mongoUtil.save(operationAccountFundInfoLog);
		long end=System.currentTimeMillis();
		logger.error(end);
		logger.error("全部余额核算到数据库=="+(end-start));
	}*/

	/*@Override
	public List<Map<String,Object>>  culAccountAllCustomerErrorInfo(Integer days) {
		long start=System.currentTimeMillis();
		logger.error(start);
		MongoTemplate mongoTemplate = (MongoTemplate) ContextUtil.getBean("mongoTemplate");
		mongoTemplate.dropCollection("user_fund_check_all");
		Map<String,Object> mapp =new HashMap<String,Object>();
		mapp.put("endTime", DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL));
		if(days==null){
			mapp.put("beginTime", "2016-01-01 18:24:48");
		}else{
			mapp.put("beginTime", DateUtil.dateToString(DateUtil.addDay(new Date(),(0-days)), StringConstant.DATE_FORMAT_FULL));
		}
		
		List<AppCustomer> list=appCustomerService.getFundChangeCustomers(mapp);
		logger.error("计算条数=="+(null!=list?list.size():"0"));
	    Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
	    List<Map<String,Object>>  listErrorInfo=new ArrayList<Map<String,Object>>();
		for (String website : mapLoadWeb.keySet()) {
		    String currencyType=mapLoadWeb.get(website);

		    for(AppCustomer l:list){
		    	Map<String,Object> map=appReportSettlementCulService.culAccountByCustomer(l.getId(),currencyType, website,false); 
		    	if(null!=map){
		    		map.put("customerId", l.getId());
		    		map.put("createTime", new Date());
		    		listErrorInfo.add(map);
		    	}
		    	
		     } 
		//    return listErrorInfo;
		}
		
		long end=System.currentTimeMillis();
		logger.error(end);
		logger.error("余额核算时间=="+(end-start));
		mongoTemplate.insert(listErrorInfo, "user_fund_check_all");
		return listErrorInfo;
	}*/
	/*@Override
	public Map<String,Object>  culAccountByCustomerErrorInfo(Long customerId,
			String currencyType, String website) {
		    Map<String,Object> map=appReportSettlementCulService.culAccountByCustomer(customerId,currencyType, website,false); 
		   return map;
	}*/
	/*@Override
	public List<Map<String, Object>> culAccountByCustomersErrorInfo(
			String[] ids) {
		List<Map<String,Object>>  listErrorInfo=new ArrayList<Map<String,Object>>();
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
			for (String website : mapLoadWeb.keySet()) {
			    String currencyType=mapLoadWeb.get(website);
			    int i=0;
				while(i<ids.length){
					Long id=Long.valueOf(ids[i]);
					AppCustomer appCustomer=appCustomerService.getById(id);
					Map<String,Object> map=appReportSettlementCulService.culAccountByCustomer(appCustomer.getId(),currencyType, website,false); 
			    	if(null!=map){
			    		listErrorInfo.add(map);
			    	}
					i++;
				}
			}
			
			MongoTemplate mongoTemplate = (MongoTemplate) ContextUtil.getBean("mongoTemplate");
			mongoTemplate.insert(listErrorInfo, "user_fund_check");
			return listErrorInfo;
		
	}*/
	
	/*@Override
	public void culAccountByCustomers(String[] ids) {
		List<Map<String,Object>>  listErrorInfo=new ArrayList<Map<String,Object>>();
		int i=0;
		StringBuffer userNames=new StringBuffer("");
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
			for (String website : mapLoadWeb.keySet()) {
			    String currencyType=mapLoadWeb.get(website);
				while(i<ids.length){
					Long id=Long.valueOf(ids[i]);
					AppCustomer appCustomer=appCustomerService.getById(id);
					
					 Map<String,Object> map= appReportSettlementCulService.culAccountByCustomer(appCustomer.getId(),currencyType,website,true);
					 
					 if(null!=map){
				    		listErrorInfo.add(map);
				    	}
					 userNames.append(appCustomer.getUserName()+",");
					 i++;
				}
			}
		//保存操作日志
		MongoUtil<OperationAccountFundInfoLog, Long> mongoUtil = new MongoUtil<OperationAccountFundInfoLog, Long>(
				OperationAccountFundInfoLog.class,"operation_accountfundinfo_log");
		OperationAccountFundInfoLog operationAccountFundInfoLog=new OperationAccountFundInfoLog();
		operationAccountFundInfoLog.setWebsite("cn");
		operationAccountFundInfoLog.setCurrencyType("cny");
		operationAccountFundInfoLog.setOperatorName(ContextUtil.getCurrentUser().getUsername());
		operationAccountFundInfoLog.setUserName(userNames.toString());
		operationAccountFundInfoLog.setContext(Mapper.objectToJson(listErrorInfo));
		operationAccountFundInfoLog.setCreatDate(new Date());
		mongoUtil.save(operationAccountFundInfoLog);
					
	}*/

	/*@Override
	public Map<String,Object> culAccountByCustomer(Long customerId,String currencyType, String website,Boolean isSave) {
	    
		if(null==isSave){
			isSave=false;
		}
		AppCustomer appCustomer=appCustomerService.getById(customerId);
		  String productListsb=  productListsb(website);
		Map<String,Object> map=new HashMap<String,Object>();
		List<AccountFundInfo>  newlist=new ArrayList<AccountFundInfo>();
		List<AccountFundInfo>  oldlist=new ArrayList<AccountFundInfo>();
	  
		 if(null==appCustomer){
			  return  null;
		  }
		AppAccount appAccount=remoteAppAccountService.getByCustomerIdAndType(customerId, currencyType, website);
		  if(null==appAccount){
			  return  null;
		  }
		AccountFundInfo afundrmb=new AccountFundInfo();
		afundrmb.setCoinCode(currencyType);
		afundrmb.setUserName(appCustomer.getUserName());
		afundrmb.setHotMoney(appAccount.getHotMoney());
		afundrmb.setColdMoney(appAccount.getColdMoney());
		afundrmb.setLendMoney(appAccount.getLendMoney());
		
		afundrmb.setWebsite(website);
		afundrmb.setCurrencyType(currencyType);
		oldlist.add(afundrmb);
		
		
		
		RemoteQueryFilter edafilter=new RemoteQueryFilter(ExDigitalmoneyAccount.class);
		edafilter.addFilter("customerId=", customerId);
		edafilter.addFilter("currencyType=",currencyType);
		edafilter.addFilter("website=",website);
		edafilter.addFilter("coinCode_in",productListsb);
		List<ExDigitalmoneyAccount> listeda=remoteExDigitalmoneyAccountService.find(edafilter);
		for(ExDigitalmoneyAccount eda:listeda){
			AccountFundInfo afundcoin=new AccountFundInfo();
			afundcoin.setCoinCode(eda.getCoinCode());
			afundcoin.setUserName(appCustomer.getUserName());
			afundcoin.setHotMoney(eda.getHotMoney());
			afundcoin.setColdMoney(eda.getColdMoney());
			afundcoin.setLendMoney(eda.getLendMoney());
			
			afundcoin.setWebsite(website);
			afundcoin.setCurrencyType(currencyType);
			 oldlist.add(afundcoin);
		}
		
		
		
		//充值额,提现额，提现手续费
		RemoteAppTransactionService remoteAppTransactionService = (RemoteAppTransactionService)ContextUtil.getBean("remoteAppTransactionService");
		//查出所有充值提现状态成功的列
		List<AppTransaction>  rechargeList=remoteAppTransactionService.record(customerId,null,"2",null,null,currencyType,website);
		BigDecimal rechargeMoney=new BigDecimal("0");
		BigDecimal withdrawMoney=new BigDecimal("0");
		BigDecimal withdrawFee=new BigDecimal("0");
		for(AppTransaction at:rechargeList){
			if(at.getTransactionType()==1||at.getTransactionType()==3 ||at.getTransactionType()==5){
				rechargeMoney=rechargeMoney.add(at.getTransactionMoney());
			}else{
				withdrawMoney=withdrawMoney.add(at.getTransactionMoney());
				withdrawFee=withdrawFee.add(at.getFee());
			}
			
		}
		
		
		
		
		//提现2,4审核1,4中的记录
		List<AppTransaction>  withdrawListcold=remoteAppTransactionService.record(customerId,"2,4","1,4",null,null,currencyType,website);
		BigDecimal withdrawcoldMoney=new BigDecimal("0");
		for(AppTransaction at:withdrawListcold){
			 withdrawcoldMoney=withdrawcoldMoney.add(at.getTransactionMoney());
		}
		
		
		
		//买成交额,卖成交额，成交手续费
		RemoteQueryFilter transactionMoneyfilter=new RemoteQueryFilter(ExEntrust.class);
		transactionMoneyfilter.addFilter("customerId=", customerId);
		transactionMoneyfilter.addFilter("currencyType=",currencyType);
		transactionMoneyfilter.addFilter("website=",website);
		List<ExEntrust> buyTransactionMoneylist=remoteExEntrustService.find(transactionMoneyfilter);
		BigDecimal buyTransactionMoney=new BigDecimal("0");
		BigDecimal sellTransactionMoney=new BigDecimal("0");
		BigDecimal transactionFee=new BigDecimal("0");
		BigDecimal coldEntrustMoney=new BigDecimal("0"); //委托冻结
		for(ExEntrust e:buyTransactionMoneylist){
			if(e.getType()==1){
				buyTransactionMoney=buyTransactionMoney.add(e.getTransactionSum());//买成交额，是支出
				if(e.getStatus()<2){
					coldEntrustMoney=coldEntrustMoney.add(e.getEntrustSum().subtract(e.getTransactionSum()));
				}
			}else{
				sellTransactionMoney=sellTransactionMoney.add(e.getTransactionSum());
			}
			transactionFee=transactionFee.add(e.getTransactionFee());
			
			
		}
		
		Map<String,BigDecimal> getRepayByExDmLendIntent=appReportSettlementCulService.getRepayByExDmLendIntent(customerId, currencyType,"interest", website, null, null);
		BigDecimal repaylendMoney=getRepayByExDmLendIntent.get("repaylendMoney");   
		BigDecimal LendMoney=appAccount.getLendMoney();        
		
		BigDecimal  hotMoney=rechargeMoney.subtract(withdrawMoney)
				.add(sellTransactionMoney).subtract(buyTransactionMoney).subtract(transactionFee)
				.subtract(coldEntrustMoney).add(LendMoney).subtract(repaylendMoney);
				
		
		BigDecimal coldMoney=coldEntrustMoney; //总冻结金额
        if(withdrawcoldMoney.compareTo(hotMoney.add(coldEntrustMoney).subtract(LendMoney))>0){
        	logger.error(customerId+"提现大于资产需要驳回(withdrawcoldMoney:"+withdrawcoldMoney+","+hotMoney.add(coldEntrustMoney).subtract(LendMoney));
        	//驳回
		}
        coldMoney=coldEntrustMoney.add(withdrawcoldMoney);//总冻结=委托冻结+提现冻结
		hotMoney=hotMoney.subtract(withdrawcoldMoney);
		AccountFundInfo newafundrmb=new AccountFundInfo();
		newafundrmb.setCoinCode(currencyType);
		newafundrmb.setUserName(appCustomer.getUserName());
		newafundrmb.setHotMoney( hotMoney);
		newafundrmb.setColdMoney(coldMoney);
		newafundrmb.setLendMoney(LendMoney);
		
		newafundrmb.setWebsite(website);
		newafundrmb.setCurrencyType(currencyType);
		newlist.add(newafundrmb);
		
		
	
		//计算币
		
		for(ExDigitalmoneyAccount eda:listeda){
			
			
			//计算币的买成交量，卖成交量
			transactionMoneyfilter.addFilter("coinCode=", eda.getCoinCode());
			List<ExEntrust> Transactioncountlist=remoteExEntrustService.find(transactionMoneyfilter);
			BigDecimal buyTransactioncount=new BigDecimal("0");
			BigDecimal sellTransactioncount=new BigDecimal("0");
			BigDecimal edcoldEntrustCount=new BigDecimal("0");//委托冻结币
			for(ExEntrust e:Transactioncountlist){
				if(e.getType()==1){
					//买币收入币
					buyTransactioncount=buyTransactioncount.add(e.getEntrustCount().subtract(e.getSurplusEntrustCount()));
				}else{
					//卖币支出币
					sellTransactioncount=sellTransactioncount.add(e.getEntrustCount().subtract(e.getSurplusEntrustCount()));
				
					if(e.getStatus()<2){
						
						edcoldEntrustCount=edcoldEntrustCount.add(e.getSurplusEntrustCount());
					}
				}
				
				
			}
			
			
			
			//币的转入转出量
			RemoteQueryFilter edtfilter=new RemoteQueryFilter(ExDmTransaction.class);
			edtfilter.addFilter("customerId=", customerId);
			edtfilter.addFilter("status=", 2);
			edtfilter.addFilter("coinCode=", eda.getCoinCode());
			edtfilter.addFilter("currencyType=",currencyType);
			edtfilter.addFilter("website=",website);
			List<ExDmTransaction> edtlist=remoteExDmTransactionService.find(edtfilter);
			BigDecimal inCoinCount=new BigDecimal("0");
			BigDecimal outCoinCount=new BigDecimal("0");
			BigDecimal inCoinFee=new BigDecimal("0");
			BigDecimal outCoinFee=new BigDecimal("0");
			for(ExDmTransaction edt:edtlist){
				if(edt.getTransactionType()==1){
					inCoinCount=inCoinCount.add(edt.getTransactionMoney());
					inCoinFee=inCoinFee.add(null==edt.getFee()?new BigDecimal("0"):edt.getFee());
				}else{
					outCoinCount=outCoinCount.add(edt.getTransactionMoney());
					outCoinFee=outCoinFee.add(null==edt.getFee()?new BigDecimal("0"):edt.getFee());
				}
			}
	
			ExDigitalmoneyAccount edappAccount=remoteExDigitalmoneyAccountService.getByCustomerIdAndType(customerId, eda.getCoinCode(), currencyType, website);
			BigDecimal edLendMoney=edappAccount.getLendMoney();  
			Map<String,BigDecimal> getRepaycoinExDmLendIntent=appReportSettlementCulService.getRepaycoinExDmLendIntent(customerId, eda.getCoinCode(), currencyType, website, null, null);
			BigDecimal repaylendcoin=getRepaycoinExDmLendIntent.get("repaylendcoin"); 
			if(appCustomer.getUserName().equals("18606930927")&&eda.getCoinCode().equals("HTB")){
				logger.error("inCoinCount="+inCoinCount);
				logger.error("outCoinCount="+outCoinCount);
				logger.error("inCoinFee="+inCoinFee);
				logger.error("buyTransactioncount="+buyTransactioncount);
				logger.error("sellTransactioncount="+sellTransactioncount);
				logger.error("edcoldEntrustCount="+edcoldEntrustCount);
			}
			BigDecimal  edhotMoney=inCoinCount.subtract(inCoinFee).subtract(outCoinCount)
					.add(buyTransactioncount).subtract(sellTransactioncount)
					.subtract(edcoldEntrustCount).add(edLendMoney).subtract(repaylendcoin);
			
			BigDecimal edcoldCount=edcoldEntrustCount;//总冻结金额
			
			//提币2审核中1，4所有记录
			RemoteQueryFilter withdrawedtfilter=new RemoteQueryFilter(ExDmTransaction.class);
			withdrawedtfilter.addFilter("customerId=", customerId);
			withdrawedtfilter.addFilter("status_in", "1,4");
			withdrawedtfilter.addFilter("transactionType_in", "2");
			withdrawedtfilter.addFilter("coinCode=", eda.getCoinCode());
			withdrawedtfilter.addFilter("currencyType=",currencyType);
			withdrawedtfilter.addFilter("website=",website);
			List<ExDmTransaction> widthedtlist=remoteExDmTransactionService.find(withdrawedtfilter);
			BigDecimal withdrawcoldcount=new BigDecimal("0");
			for(ExDmTransaction at:widthedtlist){
				withdrawcoldcount=withdrawcoldcount.add(at.getTransactionMoney());
			}
			 if(withdrawcoldcount.compareTo(edhotMoney.add(edcoldEntrustCount).subtract(edLendMoney))>0){
		        	logger.error(customerId+"币提现大于资产需要驳回:(withdrawcoldcount:"+withdrawcoldcount+","+edhotMoney.add(edcoldEntrustCount).subtract(edLendMoney));
			 }
			edcoldCount=edcoldEntrustCount.add(withdrawcoldcount);
			edhotMoney=edhotMoney.subtract(withdrawcoldcount);
			AccountFundInfo newafundcoin=new AccountFundInfo();
			newafundcoin.setCoinCode(eda.getCoinCode());
			newafundcoin.setUserName(appCustomer.getUserName());
			newafundcoin.setHotMoney(edhotMoney);
			newafundcoin.setColdMoney(edcoldCount);
			newafundcoin.setLendMoney(edLendMoney);
			
			newafundcoin.setWebsite(website);
			newafundcoin.setCurrencyType(currencyType);
			 newlist.add(newafundcoin);
			 
		}
		
		int i=0;
		Boolean flag=true;
		while(i<newlist.size()){
			if(!oldlist.get(i).toString().equals(newlist.get(i).toString())){
				flag=false;
				break;
			}
			if(oldlist.get(i).toString().contains("-")){
				flag=false;
				break;
			}
			i++;
		}
		if(!flag){
			map.put("oldAccountFundInfolist", oldlist);   
			map.put("newAccountFundInfolist", newlist); 
			if(isSave){
				//保存资金账户
				appAccount.setHotMoney(hotMoney);
				appAccount.setColdMoney(coldMoney);
				appAccount.setLendMoney(LendMoney);
				remoteAppAccountService.updateAccount(appAccount);
				
				//保存币账户
				for(ExDigitalmoneyAccount eda:listeda){
					for(AccountFundInfo newafundcoin:newlist){
						if(eda.getCoinCode().equals(newafundcoin.getCoinCode())&&
								eda.getCurrencyType().equals(newafundcoin.getCurrencyType())
								&&eda.getWebsite().equals(newafundcoin.getWebsite())){
							
							eda.setHotMoney(newafundcoin.getHotMoney());
							eda.setColdMoney(newafundcoin.getColdMoney());
							eda.setLendMoney(newafundcoin.getLendMoney());
							remoteExDigitalmoneyAccountService.updateAccount(eda);
						}
							
					
				 } 
					
				}
				
				
			   //如果有数据改变并且保存到数据库的话就要保存日志
				MongoUtil<UpdateAccountFundInfoLog, Long> mongoUtil = new MongoUtil<UpdateAccountFundInfoLog, Long>(
						UpdateAccountFundInfoLog.class,"update_accountfundinfo_log");
				UpdateAccountFundInfoLog updateAccountFundInfoLog=new UpdateAccountFundInfoLog();
				updateAccountFundInfoLog.setWebsite(website);
				updateAccountFundInfoLog.setCurrencyType(currencyType);
				updateAccountFundInfoLog.setOperatorName(ContextUtil.getCurrentUser().getUsername());
				updateAccountFundInfoLog.setUserName(appCustomer.getUserName());
				updateAccountFundInfoLog.setNewAccountFundInfo(Mapper.objectToJson(newlist));
				updateAccountFundInfoLog.setOldAccountFundInfo(Mapper.objectToJson(oldlist));
				updateAccountFundInfoLog.setCreatDate(new Date());
				mongoUtil.save(updateAccountFundInfoLog);
				
			}
			return map;
		}else{
			//资金正确
			//保存币账户
				for(AccountFundInfo newafundcoin:newlist){
					AppAccountSureold	eda=remoteAppAccountSureoldService.getBycustomerIdAndcoinCode(customerId,appCustomer.getUserName(), newafundcoin.getCoinCode(), currencyType, website);
				    if(null !=eda){
				    	eda.setHotMoney(newafundcoin.getHotMoney());
						eda.setColdMoney(newafundcoin.getColdMoney());
						eda.setLendMoney(newafundcoin.getLendMoney());
						eda.setSureTime(new Date());
						remoteAppAccountSureoldService.updateAccount(eda);
				    }else{
				    	 eda=new AppAccountSureold();
				    	 eda.setCustomerId(customerId);
				    	 eda.setUserName(appCustomer.getUserName());
				    	 eda.setCoinCode(newafundcoin.getCoinCode());
				    	 eda.setCurrencyType(currencyType);
				    	 eda.setWebsite(website);
				    	 eda.setAccountId(Long.valueOf("1"));
				    	 eda.setHotMoney(newafundcoin.getHotMoney());
				    	 eda.setColdMoney(newafundcoin.getColdMoney());
						 eda.setLendMoney(newafundcoin.getLendMoney());
						 eda.setSureTime(new Date());
						 remoteAppAccountSureoldService.saveAccount(eda);
					
				}
			}
	
			
			
			return null;
		}
		
		
		
	}*/
	/*@Override
	public void platformconfirmExSettlementfinace(String currencyType,
			String website) {
		AppReportSettlement appReportSettlement=getLastSettlement();
		String beginDateString=DateUtil.dateToString(appReportSettlement.getStartSettleDate(), StringConstant.DATE_FORMAT_FULL);
		String endDateString=DateUtil.dateToString(appReportSettlement.getEndSettleDate(), StringConstant.DATE_FORMAT_FULL);
		
		 QueryFilter filter=new QueryFilter(AppReportSettlement.class);
		 filter.addFilter("startSettleDate=", beginDateString);
		 filter.addFilter("endSettleDate=", endDateString);
		 List<AppReportSettlement> list=this.find(filter);
		 for(AppReportSettlement l:list){
			 l.setStutus(0);
			 this.update(l);
			 
			 
		 }
	}*/
	/*@Override
	public void platformconfirmExSettlementcoin(String coinCode,String currencyType,String website) { 
		AppReportSettlement appReportSettlement=getLastSettlement();
		
		String beginDateString=DateUtil.dateToString(appReportSettlement.getStartSettleDate(), StringConstant.DATE_FORMAT_FULL);
		String endDateString=DateUtil.dateToString(appReportSettlement.getEndSettleDate(), StringConstant.DATE_FORMAT_FULL);
	
		QueryFilter filtercoin=new QueryFilter(AppReportSettlementcoin.class);
			 filtercoin.addFilter("startSettleDate=", beginDateString);
			 filtercoin.addFilter("endSettleDate=", endDateString);
			 filtercoin.addFilter("coinCode=", coinCode);
			 List<AppReportSettlementcoin> listcoin=appReportSettlementcoinService.find(filtercoin);
			 for(AppReportSettlementcoin c:listcoin){
				 c.setStutus(0);
				 appReportSettlementcoinService.update(c);
              }
			 
	}*/
}
