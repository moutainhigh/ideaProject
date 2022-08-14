/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:12:49
 */
package hry.exchange.account.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.account.fund.model.AppAccount;
import hry.account.fund.service.AppAccountRecordService;
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
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;

import hry.util.message.MessageConstant;
import hry.util.message.MessageUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.exchange.account.dao.ExDigitalmoneyAccountDao;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.ExDmColdAccountRecord;
import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.exchange.account.model.vo.DigitalmoneyAccountAndProduct;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.account.service.ExDmColdAccountRecordService;
import hry.exchange.account.service.ExDmHotAccountRecordService;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.service.ExDmLendService;
import hry.exchange.product.model.ExProduct;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.mq.producer.MessageAccountUtil;
import hry.trade.entrust.ExchangeDataCache;
import hry.web.remote.RemoteAppConfigService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:12:49
 */
@Service("exDigitalmoneyAccountService")
public class ExDigitalmoneyAccountServiceImpl extends
		BaseServiceImpl<ExDigitalmoneyAccount, Long> implements
		ExDigitalmoneyAccountService {
	private static Logger logger = Logger.getLogger(ExDigitalmoneyAccountServiceImpl.class);
	
	@Resource(name = "exDmTransactionService")
	public ExDmTransactionService exDmTransactionService;
	
	
	@Resource(name = "exDigitalmoneyAccountDao")
	@Override
	public void setDao(BaseDao<ExDigitalmoneyAccount, Long> dao) {
		super.dao = dao;
	}
	
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	private ExDmColdAccountRecordService  exDmColdAccountRecordService;
	@Resource
	private ExDmHotAccountRecordService  exDmHotAccountRecordService;
	@Resource
	private ExDmLendService  exDmLendService;
	@Resource
	private AppColdAccountRecordService  appColdAccountRecordService;
	@Resource
	private AppHotAccountRecordService  appHotAccountRecordService;
	
	@Resource(name = "appOurAccountService")
	private AppOurAccountService appOurAccountService;
	
	@Resource(name = "appAccountRecordService")
	private AppAccountRecordService appAccountRecordService;
	
	@Override
	public ExDigitalmoneyAccount findByOrderId(Long id) {
		ExDmTransaction exDmTransaction = exDmTransactionService.get(id);
		Long id2 = exDmTransaction.getAccountId();
		ExDigitalmoneyAccount exDigitalmoneyAccount = super.get(id2);
		return exDigitalmoneyAccount;
	}

	@Override
	public ExDigitalmoneyAccount getByCustomerIdAndType(Long customerId,String coinCode,String currencyType,String website) {
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("customerId=", customerId);
		if(!StringUtil.isEmpty(currencyType)){
			filter.addFilter("currencyType=", currencyType);
		}
		if(!StringUtil.isEmpty(website)){
			filter.addFilter("website=", website);
		}
		filter.addFilter("coinCode=", coinCode);
		return this.get(filter);
	}
	
	
	/**
	 *  用户id   币的代码      站点信息  
	 *  
	 *  返回用户的虚拟账户表    
	 * 
	 */
	@Override
	public List<ExDigitalmoneyAccount> getByCustomerId(Long customerId,String currencyType,String website) {
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
		return this.find(filter);
	}
	@Override
	public String[] updateAccount(ExDigitalmoneyAccount account){
		String[] relt=new String[2];  
		//try{
		long start1 = System.currentTimeMillis();
		  this.update(account);
			long end1 = System.currentTimeMillis();
			logger.error("更新账户e：" + (end1 - start1) + "毫秒");
		  relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="成功";
		/*}catch(Exception e){
			
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="失败";
		}*/
		
		return relt;
/*		String[] relt=new String[2];  
		ExDigitalmoneyAccountDao exDigitalmoneyAccountDao=(ExDigitalmoneyAccountDao)dao;
		int a=exDigitalmoneyAccountDao.updateByVersion(account.getColdMoney(), account.getHotMoney(),account.getLendMoney(), account.getVersion()+1, account.getCustomerId(), account.getCoinCode(),account.getCurrencyType(),account.getWebsite(),account.getVersion());
		if(a==0){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="并发出现异常";
			logger.error("并发出现异常：exdmaccountid===" + account.getId()+ "，时间=="+DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL));
			logger.error("并发出现异常：exdmaccountid===" + account.getId()+ "，时间=="+DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL));
			return relt;
			
		}else if(a==1){
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="成功";
			return relt;
		}else{
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="账户异常，有两个以上的账户";
			return relt;
		}*/
		
		
		
	}
	/**
	 * 
	 * <p> 暂时不用</p>
	 * @author:         Gao Mimi
	 * @param:    @param account
	 * @param:    @param freezeMoney
	 * @param:    @param n
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年5月10日 下午5:22:51   
	 * @throws:
	 */
	public String[] updateAccount1(ExDigitalmoneyAccount account, BigDecimal freezeMoney,int n){
		
		String[] relt=new String[2];  
		ExDigitalmoneyAccountDao exDigitalmoneyAccountDao=(ExDigitalmoneyAccountDao)dao;
		int a=exDigitalmoneyAccountDao.updateByVersion(account.getColdMoney(), account.getHotMoney(), account.getLendMoney(), account.getVersion()+1, account.getCustomerId(),account.getCoinCode(),account.getCurrencyType(),account.getWebsite(), account.getVersion());
	//	logger.error("a===="+a);
		logger.error("account.getVersion()===="+account.getVersion());
		if(a==0){
			//热钱包的金额小于冻结金额则失败
			if(account.getHotMoney().compareTo(freezeMoney)<0){
				relt[0]=CodeConstant.CODE_FAILED;
				relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
				return relt;
			}else{
				
				if(n==10){
					relt[0]=CodeConstant.CODE_FAILED;
					relt[1]="并发出现异常";
					logger.error("relt【0】===="+relt[0]);
					
					return relt;
				}
				n++;
		//		dao.  清理缓存？？？？？？？？？？？？？
				account=dao.selectByPrimaryKey(account.getId());
				updateAccount1(account,freezeMoney,n);
			}
			
			/*relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;*/
		}else if(a==1){
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;
		}else{
			
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="账户异常，有两个以上的账户";
			return relt;
		}
		
		
		relt[0]=CodeConstant.CODE_FAILED;
		relt[1]="并发出现异常";
		logger.error("relt【0】===="+relt[0]);
		return relt;
		
	}
	public String[] commonret(ExDigitalmoneyAccount account){
		String[] relt=new String[2];  
		if(null==account){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.NOEXIST_ARG,"账户");
			return relt;
			
		}else{
			
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;
		}
		
	}
	@Override
	public String[] freezeAccountSelf(Long id, BigDecimal freezeMoney,String transactionNum,String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2];
		logger.error("===AAA"+id);
		ExDigitalmoneyAccount account=this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		//热钱包的币量小于冻结币量则失败
		if(account.getHotMoney().compareTo(freezeMoney)<0){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;
		}
		//重新计算冷热钱包的总额
		account.setColdMoney(account.getColdMoney().add(freezeMoney));
		account.setHotMoney(account.getHotMoney().subtract(freezeMoney));
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//冷钱包增加一条收入的记录
			ExDmColdAccountRecord coldAccountRecord=createColdRecord(1,account,freezeMoney,transactionNum,remark);
			//热钱包增加一条支出的记录
			ExDmHotAccountRecord hotAccountRecord=createHotRecord(2,account,freezeMoney,transactionNum,remark);
			
		    if(null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)){
				
				exDmColdAccountRecordService.save(coldAccountRecord);
				exDmHotAccountRecordService.save(hotAccountRecord);
			}else{
				
				DmColdRecordRunable dmColdRecordRunable = new DmColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(dmColdRecordRunable);
				
				DmHotRecordRunable dmHotRecordRunable = new DmHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(dmHotRecordRunable);
			}
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;
		}else{
			return relt;
		}
		
	}
	@Override
	public String[] unfreezeAccountSelf(Long id, BigDecimal unfreezeMoney,String transactionNum,String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2]; 
		ExDigitalmoneyAccount account=this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		if(account.getColdMoney().compareTo(unfreezeMoney)<0){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;
		}
		//重新计算冷热钱包的总额
		account.setColdMoney(account.getColdMoney().subtract(unfreezeMoney));
		account.setHotMoney(account.getHotMoney().add(unfreezeMoney));
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
		//冷钱包增加一条支出的记录
			ExDmColdAccountRecord coldAccountRecord=createColdRecord(2,account,unfreezeMoney,transactionNum,remark);
			//热钱包增加一条收入的记录
			ExDmHotAccountRecord hotAccountRecord=createHotRecord(1,account,unfreezeMoney,transactionNum,remark);
	          if(null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)){
				
				exDmColdAccountRecordService.save(coldAccountRecord);
				exDmHotAccountRecordService.save(hotAccountRecord);
			}else{
				
				DmColdRecordRunable dmColdRecordRunable = new DmColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(dmColdRecordRunable);
				
				DmHotRecordRunable dmHotRecordRunable = new DmHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(dmHotRecordRunable);
			}
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;
		}else{
			return relt;
		}
		
		
	}

	@Override
	public String[] unfreezeAccountThem(Long id,BigDecimal unfreezeMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2];
		ExDigitalmoneyAccount account=this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		if(account.getColdMoney().compareTo(unfreezeMoney)<0){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;
		}
		//重新计算冷钱包的总额
		account.setColdMoney(account.getColdMoney().subtract(unfreezeMoney));
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
	 
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//冷钱包增加一条支出的记录
			ExDmColdAccountRecord coldAccountRecord=createColdRecord(2,account,unfreezeMoney,transactionNum,remark);
		    if(null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)){
		    	exDmColdAccountRecordService.save(coldAccountRecord);
			}else{
				DmColdRecordRunable dmColdRecordRunable = new DmColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(dmColdRecordRunable);
			}
			//dao.updateByPrimaryKey(account);
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;
		}else{
			return relt;
		
		}	
	}

	
	/**
	 * 
	 * 给自己的账号保增加一笔钱  
	 * 
	 */
	@Override
	public String[] inComeToHotAccount(Long id,BigDecimal incomeMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2];
		ExDigitalmoneyAccount account=this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		//重新计算冷热钱包的总额
		account.setHotMoney(account.getHotMoney().add(incomeMoney));
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//热钱包增加一条收入的记录
			ExDmHotAccountRecord hotAccountRecord=createHotRecord(1,account,incomeMoney,transactionNum,remark);
		    if(null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)){
					exDmHotAccountRecordService.save(hotAccountRecord);
			}else{
				DmHotRecordRunable dmHotRecordRunable = new DmHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(dmHotRecordRunable);
			}
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;
		}else{
			return relt;
		}	
	}

	@Override
	public String[] payFromHotAccount(Long id,BigDecimal payMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2];  
		ExDigitalmoneyAccount account=this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		if(account.getHotMoney().compareTo(payMoney)<0){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;
		}   
		//重新计算冷热钱包的总额
		account.setHotMoney(account.getHotMoney().subtract(payMoney));
		
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//热钱包增加一条减少的记录
			ExDmHotAccountRecord hotAccountRecord=createHotRecord(2,account,payMoney,transactionNum,remark);
		    if(null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)){
				exDmHotAccountRecordService.save(hotAccountRecord);
			}else{
				DmHotRecordRunable dmHotRecordRunable = new DmHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(dmHotRecordRunable);
			}
			
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;
		}else{
			return relt;
		
		}	
	}
	
	@Override
	public String[] payFromHotAccountNegative(Long id,BigDecimal payMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord ) {
		String[] relt=new String[2];  
		ExDigitalmoneyAccount account=this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		if(account.getHotMoney().compareTo(payMoney)<0){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;
		}   
		//重新计算冷热钱包的总额
		account.setHotMoney(account.getHotMoney().subtract(payMoney));
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//热钱包增加一条减少的记录
			ExDmHotAccountRecord hotAccountRecord=createHotRecord(2,account,payMoney,transactionNum,remark);
			
		    if(null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)){
				exDmHotAccountRecordService.save(hotAccountRecord);
			}else{
				DmHotRecordRunable dmHotRecordRunable = new DmHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(dmHotRecordRunable);
			}
			
			
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;
		}else{
			return relt;
		
		}	
	}
	
	public ExDmColdAccountRecord createColdRecord(Integer recordType,ExDigitalmoneyAccount exDigitalmoneyAccount, BigDecimal freezeMoney,String transactionNum,String remark){
		//资金池5(完成)，托管0(处理中)，将来通过配置文件来获取判断
		Integer status=5;
		
		ExDmColdAccountRecord coldAccountRecord=new ExDmColdAccountRecord();
		coldAccountRecord.setAccountId(exDigitalmoneyAccount.getId());
		coldAccountRecord.setCurrencyType(exDigitalmoneyAccount.getCurrencyType());
		coldAccountRecord.setWebsite(exDigitalmoneyAccount.getWebsite());
		coldAccountRecord.setCoinCode(exDigitalmoneyAccount.getCoinCode());
		coldAccountRecord.setCustomerId(exDigitalmoneyAccount.getCustomerId());
		coldAccountRecord.setSaasId(exDigitalmoneyAccount.getSaasId());
		coldAccountRecord.setUserName(exDigitalmoneyAccount.getUserName());
		coldAccountRecord.setTransactionNum(transactionNum);
		coldAccountRecord.setRecordType(recordType);
		coldAccountRecord.setStatus(status);
		coldAccountRecord.setTransactionMoney(freezeMoney);
		coldAccountRecord.setRemark(remark);
		return coldAccountRecord;
		
	}
	public ExDmHotAccountRecord createHotRecord(Integer recordType,ExDigitalmoneyAccount account, BigDecimal freezeMoney,String transactionNum,String remark){
		//资金池5(完成)，托管0(处理中)将来通过配置文件来获取判断
		Integer status=5;
		ExDmHotAccountRecord hotAccountRecord=new ExDmHotAccountRecord();
		hotAccountRecord.setAccountId(account.getId());
		hotAccountRecord.setCurrencyType(account.getCurrencyType());
		hotAccountRecord.setWebsite(account.getWebsite());
		hotAccountRecord.setCoinCode(account.getCoinCode());
		
		hotAccountRecord.setCustomerId(account.getCustomerId());
		hotAccountRecord.setSaasId(account.getSaasId());
		hotAccountRecord.setUserName(account.getUserName());
		hotAccountRecord.setTransactionNum(transactionNum);
		hotAccountRecord.setRecordType(recordType);
		hotAccountRecord.setStatus(status);
		hotAccountRecord.setTransactionMoney(freezeMoney);
		hotAccountRecord.setRemark(remark);
		return hotAccountRecord;
		
	}

	@Override
	public HashMap<String, BigDecimal> getAllNetAsset(Long customerId,String currencyType,String website) {
		HashMap<String,BigDecimal> map=new HashMap<String,BigDecimal>();
		BigDecimal rMBappAccountNetAsse=getRMBNetAsset(customerId,currencyType,website);
		map.put("RMBAccountNetAsse", rMBappAccountNetAsse);
		List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
		for(ExDigitalmoneyAccount l:list){
			BigDecimal coinAccountNetAsse=new BigDecimal("0");
			BigDecimal currentExchangPrice=new BigDecimal(ExchangeDataCache.getStringData(l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+":"+ExchangeDataCache.CurrentExchangPrice));
			if(null!=currentExchangPrice&&currentExchangPrice.compareTo(new BigDecimal("0"))!=0){
					coinAccountNetAsse=rMBappAccountNetAsse.divide(currentExchangPrice, 4, BigDecimal.ROUND_HALF_DOWN);
				
			}
			map.put(l.getCurrencyType()+"AccountNetAsse", coinAccountNetAsse);
		}
		return map;
	}
	@Override
	public HashMap<String, BigDecimal> getByTypeNetAsset(Long customerId,String coinCode,String currencyType,String website) {
		HashMap<String,BigDecimal> map=new HashMap<String,BigDecimal>();
		BigDecimal rMBappAccountNetAsse=getRMBNetAsset(customerId,currencyType,website);
		map.put("RMBAccountNetAsse", rMBappAccountNetAsse);
		
		BigDecimal coinAccountNetAsse=new BigDecimal("0");
		String stringData = ExchangeDataCache.getStringData(website+":"+currencyType+":"+coinCode+":"+ExchangeDataCache.CurrentExchangPrice);
		
		BigDecimal currentExchangPrice = null;
		if(!StringUtils.isEmpty(stringData)){
			currentExchangPrice=new BigDecimal(stringData);
		}
		if(null!=currentExchangPrice&&currentExchangPrice.compareTo(new BigDecimal("0"))!=0){
			coinAccountNetAsse=rMBappAccountNetAsse.divide(currentExchangPrice, 4, BigDecimal.ROUND_HALF_DOWN);
		}
		map.put(currencyType+"AccountNetAsse", coinAccountNetAsse);
		return map;
	}
	
	
	@Override
	public BigDecimal getRMBNetAsset(Long customerId,String currencyType,String website) {
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		RpcContext.getContext().setAttachment("saasId", saasId);
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(customerId, currencyType,website);
		if(appAccount!=null){
	        if(null==appAccount.getLendMoney()){
	        	appAccount.setLendMoney(new BigDecimal("0"));
	        }
			//BigDecimal rMBappAccountNetAsse=appAccount.getHotMoney().add(appAccount.getColdMoney()).subtract(appAccount.getLendMoney());
	        BigDecimal rMBappAccountNetAsse=new BigDecimal("0");
			List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
			for(ExDigitalmoneyAccount l:list){
				if(null==l.getLendMoney()){
					l.setLendMoney(new BigDecimal("0"));
				}
				String currencyType2 = l.getCurrencyType();
				String upperCase = currencyType2.toUpperCase();
				BigDecimal a=l.getHotMoney().add(l.getColdMoney()).subtract(l.getLendMoney());
				String stringData = ExchangeDataCache.getStringData(l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+"_"+upperCase+":"+ExchangeDataCache.CurrentExchangPrice);
				logger.error("let========="+l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+"_"+upperCase+":"+ExchangeDataCache.CurrentExchangPrice);
				if(!StringUtils.isEmpty(stringData)){
					BigDecimal currentExchangPrice=new BigDecimal(ExchangeDataCache.getStringData(l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+"_"+upperCase+":"+ExchangeDataCache.CurrentExchangPrice));
					if(null!=currentExchangPrice){
						rMBappAccountNetAsse=rMBappAccountNetAsse.add(a.multiply(currentExchangPrice));
					}
				}
				
				
			}
			return rMBappAccountNetAsse;
		
		}else{
			return new BigDecimal(0);
		}
		
	}
	
	
	
	
	@Override
	public BigDecimal getCoinClod(Long customerId,String currencyType,String website) {
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		RpcContext.getContext().setAttachment("saasId", saasId);
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(customerId, currencyType,website);
		if(appAccount!=null){
	        if(null==appAccount.getLendMoney()){
	        	appAccount.setLendMoney(new BigDecimal("0"));
	        }
			//BigDecimal rMBappAccountNetAsse=appAccount.getHotMoney().add(appAccount.getColdMoney()).subtract(appAccount.getLendMoney());
	        BigDecimal coinCold=new BigDecimal("0");
			List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
			for(ExDigitalmoneyAccount l:list){
				if(null==l.getLendMoney()){
					l.setLendMoney(new BigDecimal("0"));
				}
				String currencyType2 = l.getCurrencyType();
				String upperCase = currencyType2.toUpperCase();
				BigDecimal a=l.getColdMoney().subtract(l.getLendMoney());
				String stringData = ExchangeDataCache.getStringData(l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+"_"+upperCase+":"+ExchangeDataCache.CurrentExchangPrice);
				logger.error("let========="+l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+"_"+upperCase+":"+ExchangeDataCache.CurrentExchangPrice);
				if(!StringUtils.isEmpty(stringData)){
					BigDecimal currentExchangPrice=new BigDecimal(ExchangeDataCache.getStringData(l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+"_"+upperCase+":"+ExchangeDataCache.CurrentExchangPrice));
					if(null!=currentExchangPrice){
						coinCold=coinCold.add(a.multiply(currentExchangPrice));
					}
				}
				
				
			}
			return coinCold;
		
		}else{
			return new BigDecimal(0);
		}
		
	}
	@Override
	public BigDecimal getCoinNetAsset(Long customerId,String coinCode,String currencyType,String website) {
		BigDecimal rMBappAccountNetAsse=getRMBNetAsset(customerId,currencyType,website);
		BigDecimal currentExchangPrice =new BigDecimal(ExchangeDataCache.getStringData(website+":"+currencyType+":"+coinCode+":"+ExchangeDataCache.CurrentExchangPrice));
		if(currentExchangPrice.compareTo(new BigDecimal("0"))==0){
			return new BigDecimal("0");
		}else{
			
			BigDecimal coinAccountNetAsse=rMBappAccountNetAsse.divide(currentExchangPrice);
			return coinAccountNetAsse;
		}
		
		
	}
	@Override
	public BigDecimal   getRMBLendMoneySum(Long customerId,String currencyType,String website){
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		RpcContext.getContext().setAttachment("saasId", saasId);
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(customerId,currencyType,website);
		if(appAccount!=null){
			BigDecimal lendSum=appAccount.getLendMoney();  //负债
			List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
			
		/*	//加未偿还的利息
			ExDmLend exDmLend=exDmLendService.getLendBycustmer(currencyType, customerId,currencyType,website);
			lendSum=lendSum.add(exDmLend.getNotrepayInterestCount());*/
			for(ExDigitalmoneyAccount l:list){
				BigDecimal a=l.getLendMoney();
				/*//加未偿还的利息
				 exDmLend=exDmLendService.getLendBycustmer(l.getCoinCode(), customerId,currencyType,website);
				a=a.add(exDmLend.getNotrepayInterestCount());*/
				String stringData = ExchangeDataCache.getStringData(l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+":"+ExchangeDataCache.CurrentExchangPrice);
				if(!org.apache.commons.lang3.StringUtils.isEmpty(stringData)){
					BigDecimal currentExchangPrice=new BigDecimal(ExchangeDataCache.getStringData(l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+":"+ExchangeDataCache.CurrentExchangPrice));
					lendSum=lendSum.add(a.multiply(currentExchangPrice));
				}
			}
			return lendSum;
		}else{
			return new BigDecimal(0);
		}
	}
	
	public BigDecimal   getRMBLendMoneyInteretSum(Long customerId,String currencyType,String website){
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		RpcContext.getContext().setAttachment("saasId", saasId);
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(customerId,currencyType,website);
		if(appAccount!=null){
			List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
			
			//加未偿还的利息
			BigDecimal lendSum=new BigDecimal("0");
			ExDmLend exDmLend=exDmLendService.getLendBycustmer(currencyType, customerId,currencyType,website);
			lendSum=lendSum.add(exDmLend.getNotrepayInterestCount());
			for(ExDigitalmoneyAccount l:list){
				BigDecimal a=new BigDecimal("0");
				//加未偿还的利息
				 exDmLend=exDmLendService.getLendBycustmer(l.getCoinCode(), customerId,currencyType,website);
				a=a.add(exDmLend.getNotrepayInterestCount());
				String stringData = ExchangeDataCache.getStringData(l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+":"+ExchangeDataCache.CurrentExchangPrice);
				if(!org.apache.commons.lang3.StringUtils.isEmpty(stringData)){
					BigDecimal currentExchangPrice=new BigDecimal(ExchangeDataCache.getStringData(l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode()+":"+ExchangeDataCache.CurrentExchangPrice));
					lendSum=lendSum.add(a.multiply(currentExchangPrice));
				}
			}
			return lendSum;
		}else{
			return new BigDecimal(0);
		}
	}
	@Override
	public HashMap<String, BigDecimal> getByAllInteret(Long customerId,String coinCode, String currencyType, String website) {
		HashMap<String,BigDecimal> map=new HashMap<String,BigDecimal>();
		ExDmLend exDmLend=exDmLendService.getLendBycustmer(currencyType, customerId,currencyType,website);
		map.put("RMBAccountInteret", exDmLend.getNotrepayInterestCount());
		List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
		for(ExDigitalmoneyAccount l:list){
			ExDmLend exDmLend1=exDmLendService.getLendBycustmer(currencyType, customerId,currencyType,website);
			map.put(l.getCurrencyType()+"AccountInteret", exDmLend1.getNotrepayInterestCount());
		}
		return map;
	}
	@Override
	public BigDecimal getCanLendMoney(Long customerId,String coinCode,String currencyType,String website) {
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		BigDecimal rMBAccountNetAsse =this.getRMBNetAsset(customerId,currencyType,website);  //净资产
		
		//借钱至少需要净资产达到多少才可以借钱
		String lendleastMoneys=remoteAppConfigService.getFinanceByKey("leastMoneyCanLend");
		BigDecimal  lendleastMoney=new BigDecimal(StringUtil.isEmpty(lendleastMoneys)?"1000":lendleastMoneys); //杠杆的倍数，做成配置文件
		if(rMBAccountNetAsse.compareTo(lendleastMoney)==-1){
			return new BigDecimal("0");
		}
		
		BigDecimal lendSum=getRMBLendMoneySum(customerId,currencyType,website);//总负债
		
		String lendTimes=remoteAppConfigService.getFinanceLendByKey("lendTimes");
		BigDecimal  times=new BigDecimal(StringUtil.isEmpty(lendTimes)?"3":lendTimes).subtract(new BigDecimal("1")); //杠杆的倍数，做成配置文件
		BigDecimal surMBAccountNetAsse=rMBAccountNetAsse.multiply(times).subtract(lendSum);
		
		if(surMBAccountNetAsse.compareTo(new BigDecimal("0"))==-1){
			surMBAccountNetAsse=new BigDecimal("0");
		}
		//如果借钱的话就直接返回净资产
		 Map<String,String> mapLoadWeb=PropertiesUtils.getLoadWeb();
		for (String websiteweb : mapLoadWeb.keySet()) {
		    String currencyTypeweb=mapLoadWeb.get(websiteweb);
			    if(currencyType.equals(currencyTypeweb)){
					 return surMBAccountNetAsse;
				}
		    }
		if(currencyType.equals("RMB")){
			 return surMBAccountNetAsse;
		}
		
		BigDecimal currentExchangPrice=new BigDecimal(ExchangeDataCache.getStringData(website+":"+currencyType+":"+coinCode+":"+ExchangeDataCache.CurrentExchangPrice));
		BigDecimal canLendMoney= surMBAccountNetAsse.divide(currentExchangPrice, 4, BigDecimal.ROUND_HALF_DOWN);
		
		return canLendMoney;
	}
	 @Override
	 public HashMap<String,BigDecimal> getAllCanLendMoney(Long customerId,String coinCode,String currencyType,String website){
        HashMap<String,BigDecimal> map=new HashMap<String,BigDecimal>();
		map.put("RMBCanLendMoney", getCanLendMoney(customerId,coinCode,currencyType,website));
		List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
		for(ExDigitalmoneyAccount l:list){
			BigDecimal canLendMoney=getCanLendMoney(customerId,coinCode,currencyType,website);
			map.put(l.getCurrencyType()+"CanLendMoney", canLendMoney);
		}
		 return map;
	 }
	
	@Override
	public HashMap<String,BigDecimal> getAllLendMoney(Long customerId,String currencyType,String website) {
	    HashMap<String,BigDecimal> map=new HashMap<String,BigDecimal>();
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		RpcContext.getContext().setAttachment("saasId", saasId);
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(customerId, currencyType,website);
		map.put("RMBLendMoney", appAccount.getLendMoney());
		List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
		for(ExDigitalmoneyAccount l:list){
			map.put(l.getCurrencyType()+"LendMoney", appAccount.getLendMoney());
		}
		
		return map;
	}
	

	/**
	 * 根据用户的id 返回用户最高可提现的金额
	 */
	@Override
	public BigDecimal getCanWithdrawMoney(Long customerId,String coinCode,String currencyType,String website) {
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(customerId, currencyType,website);
		BigDecimal hotMoney=appAccount.getHotMoney(); //表面上可取的人民币
		BigDecimal lendSum=getRMBLendMoneySum(customerId,currencyType,website);//总负债
		if(lendSum.compareTo(new BigDecimal("0"))==0){ //如果没借过钱就可以取可用人民币
			return hotMoney;
		}
		BigDecimal netAsseToLend=this.netAsseToLend(customerId,currencyType,website);
		//杠杆的比例1.1，放文件
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String lengPings=remoteAppConfigService.getFinanceLendByKey("lengPing");
		BigDecimal  lengPing=new BigDecimal(StringUtil.isEmpty(lengPings)?"110":lengPings); //杠杆的倍数，做成配置文件
		if(netAsseToLend.compareTo(lengPing)<1){
			return new BigDecimal("0");  //如果杠杆比已经达到了110说明就不让取钱了
		}
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		RpcContext.getContext().setAttachment("saasId", saasId);
		
		BigDecimal rMBAccountNetAsse =this.getRMBNetAsset(customerId,currencyType,website);  //净资产
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
	public BigDecimal getCanWithdrawCoin(Long customerId,String coinCode,String currencyType,String website) {
		
		ExDigitalmoneyAccount eda=this.getByCustomerIdAndType(customerId, coinCode, currencyType,website);
		BigDecimal hotMoney=eda.getHotMoney();//表面上可取的币
		BigDecimal lendSum=getRMBLendMoneySum(customerId,currencyType,website);//总负债
		if(lendSum.compareTo(new BigDecimal("0"))==0){ //如果没借过钱就可以取可用币币
			return hotMoney;
		}
		
		BigDecimal netAsseToLend=this.netAsseToLend(customerId,currencyType,website);
		//杠杆的比例1.1，放文件
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String lengPings=remoteAppConfigService.getFinanceLendByKey("lengPing");
		BigDecimal  lengPing=new BigDecimal(StringUtil.isEmpty(lengPings)?"110":lengPings); //杠杆的倍数，做成配置文件
		if(netAsseToLend.compareTo(lengPing)<1){
			return new BigDecimal("0");  //如果杠杆比已经达到了110说明就不让取钱了
		}
	
		BigDecimal  coinNetAsset=this.getCoinNetAsset(customerId,coinCode,currencyType,website); //净资产
		if(hotMoney.compareTo(coinNetAsset)==1){//取最小值
			return coinNetAsset;
        }else{
        	return hotMoney;
        }
	}
	@Override
	public BigDecimal netAsseToLend(Long customerId,String currencyType,String website) {
		BigDecimal rmblendSum=this.getRMBLendMoneySum(customerId,currencyType,website);//折合RMB总负债
		if(rmblendSum.compareTo(new BigDecimal("0"))==0){
			return new BigDecimal("100000");
		}
		BigDecimal rMBAccountNetAsse=this.getRMBNetAsset(customerId,currencyType,website);//折合RMB净资产
		BigDecimal sumzichan=rMBAccountNetAsse.add(rmblendSum);//总资产=净资产+负债
		
		
		return	sumzichan.divide(rmblendSum, 4, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal("100"));
		
	}

	/**
	 * 查询用户的名字查询用户虚拟账号包括产品的信息
	 */
	@Override
	public List<DigitalmoneyAccountAndProduct> findDigitalAndProduct(String website, String cutomerName,Integer isMarket){
		ExDigitalmoneyAccountDao exDigitalmoneyAccountDao = (ExDigitalmoneyAccountDao)dao;
		List<DigitalmoneyAccountAndProduct> findNewProductByCustomer = exDigitalmoneyAccountDao.findNewProductByCustomer(website,cutomerName,isMarket);
		return findNewProductByCustomer;
	}
	
	/**
	 * 
	 * <p> 所有未还的利息 
	 * </p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @param currencyType
	 * @param:    @param website
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2016年8月9日 下午6:58:24   
	 * @throws:
	 */
	public BigDecimal getNotrepayInterestCount(Long customerId,String currencyType,String website){
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		RpcContext.getContext().setAttachment("saasId", saasId);
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(customerId,currencyType,website);
		if(appAccount!=null){
			List<ExDigitalmoneyAccount> list=this.getByCustomerId(customerId,currencyType,website);
			//加未偿还的利息
			ExDmLend exDmLend=exDmLendService.getLendBycustmer(currencyType, customerId,currencyType,website);
			BigDecimal lendSum=exDmLend.getNotrepayInterestCount();
			for(ExDigitalmoneyAccount l:list){
				BigDecimal a=l.getLendMoney();
				//加未偿还的利息
				 exDmLend=exDmLendService.getLendBycustmer(l.getCoinCode(), customerId,currencyType,website);
				a=a.add(exDmLend.getNotrepayInterestCount());
				
			}
			return lendSum;
		}else{
			return new BigDecimal(0);
		}
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<ExDigitalmoneyAccount> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String trueName = filter.getRequest().getParameter("trueName");
		String publicKey = filter.getRequest().getParameter("publicKey");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode",  coinCode);
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(publicKey)){
			map.put("publicKey", "%"+publicKey+"%");
		}
		
		((ExDigitalmoneyAccountDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	
	
	}

	@Override
	public List<ExDigitalmoneyAccount> getlistByCustomerId(Long customerId) {
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("customerId=", customerId);
		return this.find(filter);
	}




	@Override
	public JsonResult disableMoney(Long id, String disableMoey) {
		JsonResult jr = new JsonResult();
		ExDigitalmoneyAccount account=this.get(id);
		if(account.getHotMoney().compareTo(new BigDecimal(disableMoey).add(account.getDisableMoney()))>=0){
			account.setDisableMoney(new BigDecimal(disableMoey).add(account.getDisableMoney()));
			this.update(account);
			jr.setSuccess(true);
		}else{
			jr.setMsg("禁用币数量不能超过可用币数量");
			jr.setSuccess(false);
		}
		return jr;
	}

	@Override
	public String[] unfreezeAccountSelfCancelExEntrust(Long id, BigDecimal unfreezeMoney, String transactionNum,
			String remark, Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2]; 
		ExDigitalmoneyAccount account=this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		if(account.getColdMoney().compareTo(unfreezeMoney)<0){
			/*relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;*/
			logger.error(transactionNum+"余额不足("+account.getColdMoney()+"-"+unfreezeMoney+")");
		}
		//重新计算冷热钱包的总额
		account.setColdMoney(account.getColdMoney().subtract(unfreezeMoney));
		account.setHotMoney(account.getHotMoney().add(unfreezeMoney));
		if (null == isculAppAccount || (null != isculAppAccount && isculAppAccount == 1)) {
			relt = updateAccount(account);
		} else {
			relt[0] = CodeConstant.CODE_SUCCESS;
		}
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
		//冷钱包增加一条支出的记录
			ExDmColdAccountRecord coldAccountRecord=createColdRecord(2,account,unfreezeMoney,transactionNum,remark);
			//热钱包增加一条收入的记录
			ExDmHotAccountRecord hotAccountRecord=createHotRecord(1,account,unfreezeMoney,transactionNum,remark);
			if(null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)){
				
				exDmColdAccountRecordService.save(coldAccountRecord);
				exDmHotAccountRecordService.save(hotAccountRecord);
			}else{
				
				DmColdRecordRunable dmColdRecordRunable = new DmColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(dmColdRecordRunable);
				
				DmHotRecordRunable dmHotRecordRunable = new DmHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(dmHotRecordRunable);
			}
			
			relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="";
			return relt;
		}else{
			return relt;
		}
		
		
	}

	@Override
	public void saveMoney(BigDecimal money, String agentName, String website, String currencyType, String operator,String fixPriceCoinCode) {
		// TODO Auto-generated method stub
	QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		
		filter.addFilter("userName=",agentName);
		//filter.addFilter("website=", website);
		if(!StringUtil.isEmpty(fixPriceCoinCode)){
		filter.addFilter("coinCode=", fixPriceCoinCode);
		}
		
		ExDigitalmoneyAccount exDigitalmoneyAccount = super.get(filter);
		 
		BigDecimal money2 = exDigitalmoneyAccount.getHotMoney();
		
		exDigitalmoneyAccount.setHotMoney(money2.add(money));
		
		super.update(exDigitalmoneyAccount);
	
		//appOurAccountService.postMoneyToAgent(money, website, currencyType);
		
		//AppOurAccount ourAccount = appOurAccountService.getAppOurAccount(website, currencyType);
		
	}

	@Override
	public void saveRecord(ExDigitalmoneyAccount eda,int type) {
		Integer opyType=6;
		String remark = "注册送"+eda.getHotMoney()+"个币!";
		if(type==2) {
			remark = "邀请送"+eda.getHotMoney()+"个币!";
			opyType=8;
		}
		
		ExDmTransaction exDmTransaction = new ExDmTransaction();
		exDmTransaction.setAccountId(eda.getId());
		exDmTransaction.setCoinCode(eda.getCoinCode());
		exDmTransaction.setCreated(new Date());
		exDmTransaction.setCustomerId(eda.getCustomerId());
		exDmTransaction.setCustomerName(eda.getUserName());
		exDmTransaction.setTime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setTimereceived(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setBlocktime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setFee(new BigDecimal(0));
		exDmTransaction.setTransactionMoney(eda.getHotMoney());
		exDmTransaction.setStatus(2);
		exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
		// 充币
		exDmTransaction.setTransactionType(1);
		exDmTransaction.setUserId(eda.getCustomerId());
		exDmTransaction.setWebsite(eda.getWebsite());
		exDmTransaction.setCurrencyType(eda.getCurrencyType());
		exDmTransaction.setRemark(remark);
		exDmTransaction.setOptType(opyType);
		exDmTransactionService.save(exDmTransaction);
		
		
		ExDmHotAccountRecord hotAccountRecord=new ExDmHotAccountRecord();
		hotAccountRecord.setAccountId(eda.getId());
		hotAccountRecord.setCurrencyType(eda.getCurrencyType());
		hotAccountRecord.setWebsite(eda.getWebsite());
		hotAccountRecord.setCoinCode(eda.getCoinCode());
		
		hotAccountRecord.setCustomerId(eda.getCustomerId());
		hotAccountRecord.setSaasId(eda.getSaasId());
		hotAccountRecord.setUserName(eda.getUserName());
		hotAccountRecord.setTransactionNum(exDmTransaction.getTransactionNum());
		hotAccountRecord.setRecordType(1);
		hotAccountRecord.setStatus(5);
		hotAccountRecord.setTransactionMoney(eda.getHotMoney());
		hotAccountRecord.setRemark(remark);
		
		exDmHotAccountRecordService.save(hotAccountRecord);
		
		
		
		
	}

	@Override
	public void saveMoney(BigDecimal money, Long custromerId, String fixPriceCoinCode) {
	QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		
		filter.addFilter("customerId=",custromerId);
		if(!StringUtil.isEmpty(fixPriceCoinCode)){
		filter.addFilter("coinCode=", fixPriceCoinCode);
		}
		
		ExDigitalmoneyAccount exDigitalmoneyAccount = super.get(filter);
		 
		BigDecimal money2 = exDigitalmoneyAccount.getHotMoney();
		
		exDigitalmoneyAccount.setHotMoney(money2.add(money));
		
		super.update(exDigitalmoneyAccount);
	
		
		
	}
	
	@Override
	public PageResult findPageBySqlList(QueryFilter filter) {

		//----------------------分页查询头部外壳------------------------------
		// 分页参数处理
		String startStr = filter.getRequest().getParameter("start");
		String lengthStr = filter.getRequest().getParameter("length");
		Integer startpage = Integer.valueOf(startStr);
		Integer lengthpage = Integer.valueOf(lengthStr);
		if( lengthpage == null || lengthpage == 0 ){
			lengthpage = 10;
		}
		startpage = startpage/lengthpage;
		// 分页参数处理结束
		
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);
		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();
	    Integer start = startpage * lengthpage;
		map.put("start", start);
		map.put("end", lengthpage);
		
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String trueName = filter.getRequest().getParameter("trueName");
		String publicKey = filter.getRequest().getParameter("publicKey");
		
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode",  coinCode);
		}
		if(!StringUtils.isEmpty(publicKey)){
			map.put("publicKey", "%"+publicKey+"%");
		}
		
		Map<String,Object> mapcustomer = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			mapcustomer.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			mapcustomer.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			mapcustomer.put("trueName", "%"+trueName+"%");
		}
		if(mapcustomer.size()>0){
			List<String> listpersoninfo = ((ExDigitalmoneyAccountDao)dao).findCustomerByCondition(mapcustomer);
			if(listpersoninfo.size()>0){
				map.put("customerId", listpersoninfo);
			}else{
				List<ExDigitalmoneyAccount> list = new ArrayList<ExDigitalmoneyAccount>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
			}
		}
		
		Long count = ((ExDigitalmoneyAccountDao)dao).findPageBySqlCount(map);
		List<ExDigitalmoneyAccount>  list = ((ExDigitalmoneyAccountDao)dao).findPageBySqlList(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);
	
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	
	}

	@Override
	public void saveRecordAfterAudit (ExDigitalmoneyAccount eda, int type, ExProduct exProduct) {
		Integer opyType=6;
		String remark = "注册送"+exProduct.getGiveCoin()+"个币!";
		if(type==2) {
			remark = "邀请送"+exProduct.getCommendCoin()+"个币!";
			opyType=8;
		}

		ExDmTransaction exDmTransaction = new ExDmTransaction();
		exDmTransaction.setAccountId(eda.getId());
		exDmTransaction.setCoinCode(eda.getCoinCode());
		exDmTransaction.setCreated(new Date());
		exDmTransaction.setCustomerId(eda.getCustomerId());
		exDmTransaction.setCustomerName(eda.getUserName());
		exDmTransaction.setTime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setTimereceived(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setBlocktime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setFee(new BigDecimal(0));
		exDmTransaction.setTransactionMoney(exProduct.getGiveCoin());
		exDmTransaction.setStatus(2);
		exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
		// 充币
		exDmTransaction.setTransactionType(1);
		exDmTransaction.setUserId(eda.getCustomerId());
		exDmTransaction.setWebsite(eda.getWebsite());
		exDmTransaction.setCurrencyType(eda.getCurrencyType());
		exDmTransaction.setRemark(remark);
		exDmTransaction.setOptType(opyType);
		exDmTransactionService.save(exDmTransaction);

		// 发送消息
		MessageAccountUtil.addCoin(eda.getId(), exProduct.getGiveCoin(), exDmTransaction.getTransactionNum());

	}





}
