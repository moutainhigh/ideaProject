/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.account.fund.dao.AppAccountDao;
import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppColdAccountRecord;
import hry.account.fund.model.AppHotAccountRecord;
import hry.account.fund.model.AppOurAccount;
import hry.account.fund.model.vo.AppAccountVo;
import hry.account.fund.service.AppAccountRecordService;
import hry.account.fund.service.AppAccountService;
import hry.account.fund.service.AppColdAccountRecordService;
import hry.account.fund.service.AppHotAccountRecordService;
import hry.account.fund.service.AppOurAccountService;
import hry.core.constant.CodeConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.util.QueryFilter;

import hry.util.message.MessageConstant;
import hry.util.message.MessageUtil;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.impl.AccountColdRecordRunable;
import hry.exchange.account.service.impl.AccountHotRecordRunable;
import hry.mq.producer.service.MessageProducer;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
@Service("appAccountService")
public class AppAccountServiceImpl extends BaseServiceImpl<AppAccount, Long> implements AppAccountService{
	private static Logger logger = Logger.getLogger(AppAccountServiceImpl.class);
	@Resource(name="appAccountDao")
	@Override
	public void setDao(BaseDao<AppAccount, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private AppColdAccountRecordService  appColdAccountRecordService;
	@Resource
	private AppHotAccountRecordService  appHotAccountRecordService;
	
	@Resource(name = "appOurAccountService")
	private AppOurAccountService appOurAccountService;
	
	@Resource(name = "appAccountRecordService")
	private AppAccountRecordService appAccountRecordService;
	
	@Resource
	private MessageProducer messageProducer;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String[] updateAccount(AppAccount account){
		String[] relt=new String[2];  
		//try{
			long start1 = System.currentTimeMillis();
			this.update(account);
			long end1 = System.currentTimeMillis();
			logger.error("更新账户a：" + (end1 - start1) + "毫秒");
		    relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="成功";
	//	}catch(Exception e){
			
		//	relt[0]=CodeConstant.CODE_FAILED;
		//	relt[1]="失败";
		//}
		return relt;
	}
	
	public String[] commonret(AppAccount account){
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
	public String[] freezeAccountSelf(Long id,BigDecimal freezeMoney,String transactionNum,String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2]; 
		AppAccount account =this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		//热钱包的金额小于冻结金额则失败
		if(account.getHotMoney().compareTo(freezeMoney)<0){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;
		}
		//重新计算冷热钱包的总额
		account.setColdMoney(account.getColdMoney().add(freezeMoney));
		account.setHotMoney(account.getHotMoney().subtract(freezeMoney));
		//委托买单的isculAppAccount=null
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
	
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//冷钱包增加一条收入的记录
			AppColdAccountRecord coldAccountRecord=createColdRecord(1,account,freezeMoney,transactionNum,remark);
			//热钱包增加一条支出的记录
			AppHotAccountRecord hotAccountRecord=createHotRecord(2,account,freezeMoney,transactionNum,remark);
		//	String ids="cold"+coldAccountRecord.getId().toString()+","+"hot"+hotAccountRecord.getId();
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appHotAccountRecordService.save(hotAccountRecord);
				appColdAccountRecordService.save(coldAccountRecord);
				
			} else {
				AccountColdRecordRunable accountColdRecordRunable = new AccountColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(accountColdRecordRunable);
				
				AccountHotRecordRunable accountHotRecordRunable = new AccountHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(accountHotRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		}else{
			return relt;
		}
	}
	
	@Override
	public String[] unfreezeAccountSelf(Long id, BigDecimal unfreezeMoney,String transactionNum,String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2]; 
		AppAccount account =this.get(id);
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
			AppColdAccountRecord coldAccountRecord=createColdRecord(2,account,unfreezeMoney,transactionNum,remark);
			//热钱包增加一条收入的记录
			AppHotAccountRecord hotAccountRecord=createHotRecord(1,account,unfreezeMoney,transactionNum,remark);
		//	String ids="cold"+coldAccountRecord.getId().toString()+","+"hot"+hotAccountRecord.getId();
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appHotAccountRecordService.save(hotAccountRecord);
				appColdAccountRecordService.save(coldAccountRecord);
				
			} else {
				AccountColdRecordRunable accountColdRecordRunable = new AccountColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(accountColdRecordRunable);
				
				AccountHotRecordRunable accountHotRecordRunable = new AccountHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(accountHotRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		}else{
			return relt;
		}
	}

	@Override
	public String[] unfreezeAccountThem(Long id,BigDecimal unfreezeMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2];
		AppAccount account =this.get(id);
		//判断人民币账户是否存在
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		if(account.getColdMoney().compareTo(unfreezeMoney)<0){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;
		}
		if(new BigDecimal("0").compareTo(unfreezeMoney)==0){
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
			AppColdAccountRecord coldAccountRecord=createColdRecord(2,account,unfreezeMoney,transactionNum,remark);
		//	String ids="cold"+coldAccountRecord.getId().toString();
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appColdAccountRecordService.save(coldAccountRecord);
				
			} else {
				AccountColdRecordRunable accountColdRecordRunable = new AccountColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(accountColdRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		}else{
			return relt;
		
		}	
	}

	@Override
	public String[] inComeToHotAccount(Long id,BigDecimal incomeMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2];  
		AppAccount account =this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		//重新计算冷热钱包的总额
		account.setHotMoney(account.getHotMoney().add(incomeMoney));
		//isculAppAccount=null
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
	
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//热钱包增加一条收入的记录
			AppHotAccountRecord hotAccountRecord=createHotRecord(1,account,incomeMoney,transactionNum,remark);
		//	String ids="hot"+hotAccountRecord.getId();
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appHotAccountRecordService.save(hotAccountRecord);
				
			} else {
				
				AccountHotRecordRunable accountHotRecordRunable = new AccountHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(accountHotRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		}else{
			return relt;
		}	
	}

	@Override
	public String[] payFromHotAccount(Long id,BigDecimal payMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2];  
		AppAccount account =this.get(id);
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
			//热钱包增加一条收入的记录
			AppHotAccountRecord hotAccountRecord=createHotRecord(2,account,payMoney,transactionNum,remark);
		//	String ids="hot"+hotAccountRecord.getId();
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appHotAccountRecordService.save(hotAccountRecord);
			} else {
				AccountHotRecordRunable accountHotRecordRunable = new AccountHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(accountHotRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		}else{
			return relt;
		
		}	
	}
	@Override
	public String[] payFromHotAccountNegative(Long id, BigDecimal payMoney,
			String transactionNum, String remark, Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		
		String[] relt=new String[2];  
		AppAccount account =this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
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
			//热钱包增加一条支出的记录
			AppHotAccountRecord hotAccountRecord=createHotRecord(2,account,payMoney,transactionNum,remark);
		//	appHotAccountRecordService.save(hotAccountRecord);
		//	String ids="hot"+hotAccountRecord.getId();
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appHotAccountRecordService.save(hotAccountRecord);
				
			} else {
				
				AccountHotRecordRunable accountHotRecordRunable = new AccountHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(accountHotRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		}else{
			return relt;
		
		}	
	}
	
	public AppColdAccountRecord createColdRecord(Integer recordType,AppAccount account, BigDecimal freezeMoney,String transactionNum,String remark){
		//资金池5(完成)，托管0(处理中)，将来通过配置文件来获取判断
		Integer status=5;
		
		AppColdAccountRecord coldAccountRecord=new AppColdAccountRecord();
		coldAccountRecord.setAccountId(account.getId());
		coldAccountRecord.setWebsite(account.getWebsite());
		coldAccountRecord.setCurrencyType(account.getCurrencyType());
		coldAccountRecord.setCustomerId(account.getCustomerId());
		coldAccountRecord.setSaasId(account.getSaasId());
		coldAccountRecord.setUserName(account.getUserName());
		coldAccountRecord.setTransactionNum(transactionNum);
		coldAccountRecord.setRecordType(recordType);
		coldAccountRecord.setStatus(status);
		coldAccountRecord.setTransactionMoney(freezeMoney);
		coldAccountRecord.setRemark(remark);
		return coldAccountRecord;
		
	}
	public AppHotAccountRecord createHotRecord(Integer recordType,AppAccount account, BigDecimal freezeMoney,String transactionNum,String remark){
		//资金池5(完成)，托管0(处理中)将来通过配置文件来获取判断
		Integer status=5;
		AppHotAccountRecord hotAccountRecord=new AppHotAccountRecord();
		hotAccountRecord.setAccountId(account.getId());
		hotAccountRecord.setWebsite(account.getWebsite());
		hotAccountRecord.setCurrencyType(account.getCurrencyType());
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
	
	/**
	 * 
	 * 给某个人的账户添加一笔钱
	 * 
	 * 参数  operator 操作人
	 * 
	 */
	@Override
	public void saveMoney(BigDecimal money, String agentName,String website,String currencyType,String operator) {
		
		QueryFilter filter = new QueryFilter(AppAccount.class);
		
		filter.addFilter("userName=",agentName);
		filter.addFilter("website=", website);
	//	filter.addFilter("currencyType=", currencyType);
		
		AppAccount appAccount = super.get(filter);
		
		BigDecimal money2 = appAccount.getHotMoney();
		
		appAccount.setHotMoney(money2.add(money));
		
		super.update(appAccount);
		
		appHotAccountRecordService.addHotRecord(money, appAccount, 1, 5);
	
		appOurAccountService.postMoneyToAgent(money, website, currencyType);
		
		AppOurAccount ourAccount = appOurAccountService.getAppOurAccount(website, currencyType);
		
		appAccountRecordService.addOurRecord(money, ourAccount, appAccount, 2, 5,operator);
	
	}
	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppAccount> page = null;
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
		String trueName = filter.getRequest().getParameter("trueName");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		
		((AppAccountDao)dao).findPageBySql(map);
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
	public String[] unfreezeAccountSelfCancelExEntrust(Long id, BigDecimal unfreezeMoney, String transactionNum,
			String remark, Integer isculAppAccount,Integer isImmediatelySaveRecord) {

		String[] relt = new String[2];
		AppAccount account = this.get(id);
		relt = commonret(account);
		if (relt[0].equals(CodeConstant.CODE_FAILED)) {
			return relt;
		}
		// 剩余委托金额
		if (account.getColdMoney().compareTo(unfreezeMoney) < 0) {
			logger.error(transactionNum + "余额不足(" + account.getColdMoney() + "-" + unfreezeMoney + ")");
		}
		// 重新计算冷热钱包的总额
		account.setColdMoney(account.getColdMoney().subtract(unfreezeMoney));
		account.setHotMoney(account.getHotMoney().add(unfreezeMoney));
		if (null == isculAppAccount || (null != isculAppAccount && isculAppAccount == 1)) {
			relt = updateAccount(account);
		} else {
			relt[0] = CodeConstant.CODE_SUCCESS;
		}

		if (relt[0].equals(CodeConstant.CODE_SUCCESS)) {
			

			// 冷钱包增加一条支出的记录
			AppColdAccountRecord coldAccountRecord = createColdRecord(2, account, unfreezeMoney, transactionNum, remark);
			// 热钱包增加一条收入的记录
			AppHotAccountRecord hotAccountRecord = createHotRecord(1, account, unfreezeMoney, transactionNum, remark);
			//		String ids = "cold" + coldAccountRecord.getId().toString() + "," + "hot" + hotAccountRecord.getId();
		
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appHotAccountRecordService.save(hotAccountRecord);
				appColdAccountRecordService.save(coldAccountRecord);
				
			} else {
				AccountColdRecordRunable accountColdRecordRunable = new AccountColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(accountColdRecordRunable);
				
				AccountHotRecordRunable accountHotRecordRunable = new AccountHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(accountHotRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		} else {
			return relt;
		}
	}
	
	
	@Override
	public String[] unfreezeAccountThemBuyTranstion(Long id,
			BigDecimal unfreezeMoney, String transactionNum, String remark,
			Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		
		String[] relt=new String[2];
		AppAccount account =this.get(id);
		relt=commonret(account);
		if(relt[0].equals(CodeConstant.CODE_FAILED)){
			return relt;
		}
		if(account.getColdMoney().compareTo(unfreezeMoney)<0){
			logger.error(transactionNum+"余额不足("+account.getColdMoney()+"-"+unfreezeMoney+")"+"目前账户让他继续成交，但要变成负数");
		} 
		//重新计算冷钱包的总额、匹配成功之后isculAppAccount=0
		account.setColdMoney(account.getColdMoney().subtract(unfreezeMoney));
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
	
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//冷钱包增加一条支出的记录
			AppColdAccountRecord coldAccountRecord=createColdRecord(2,account,unfreezeMoney,transactionNum,remark);
		//	appColdAccountRecordService.save(coldAccountRecord);
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appColdAccountRecordService.save(coldAccountRecord);
			} else {
				AccountColdRecordRunable accountColdRecordRunable = new AccountColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(accountColdRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		}else{
			return relt;
		}	
	}
	
	
	
	/**
	 * 
	 * 查询用户的账户信息
	 * 
	 */
	@Override
	public AppAccountVo findAccountByUserName(String userName, String website) {
		
		AppAccountDao appAccountDao = (AppAccountDao)dao;
		List<AppAccount> list = ((AppAccountDao)dao).findListAccount(userName, website);
		AppAccountVo appAccountVo = new AppAccountVo();
		if(list.size()>0){
			appAccountVo.setAppAccount(list.get(0));
			List<ExDigitalmoneyAccount> list2 = appAccountDao.findDigitalmoneyAccount(userName, website);
			appAccountVo.setList(list2);
		}
		
		return appAccountVo;
	}
	@Override
	public String[] unfreezeAccountSelfLendMoney(Long id,
			BigDecimal unfreezeMoney, String transactionNum, String remark,
			Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2]; 
		AppAccount account =this.get(id);
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
		account.setLendMoney(account.getLendMoney().add(unfreezeMoney));
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
	
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//冷钱包增加一条支出的记录          
			AppColdAccountRecord coldAccountRecord=createColdRecord(2,account,unfreezeMoney,transactionNum,remark);
		//	String ids="cold"+coldAccountRecord.getId().toString();
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appColdAccountRecordService.save(coldAccountRecord);
				
			} else {
				AccountColdRecordRunable accountColdRecordRunable = new AccountColdRecordRunable(coldAccountRecord);
				ThreadPool.exe(accountColdRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		}else{
			return relt;
		}
	}
	
	@Override
	public String[] LendMoneyToHotMoneyAccountSelf(Long id,
			BigDecimal unfreezeMoney, String transactionNum, String remark,
			Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		String[] relt=new String[2]; 
		AppAccount account =this.get(id);
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
		account.setHotMoney(account.getHotMoney().add(unfreezeMoney));
		account.setLendMoney(account.getLendMoney().subtract(unfreezeMoney));
		if(null==isculAppAccount||(null!=isculAppAccount&&isculAppAccount==1)){
			relt= updateAccount(account);
		}else{
			relt[0]=CodeConstant.CODE_SUCCESS;
		}
	
		if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
			//热钱包增加一条收入的记录
			AppHotAccountRecord hotAccountRecord=createHotRecord(1,account,unfreezeMoney,transactionNum,remark);
		//	String ids="hot"+hotAccountRecord.getId();
			if (null == isImmediatelySaveRecord || (null != isImmediatelySaveRecord && isImmediatelySaveRecord == 1)) {
				appHotAccountRecordService.save(hotAccountRecord);
				
			} else {
				
				AccountHotRecordRunable accountHotRecordRunable = new AccountHotRecordRunable(hotAccountRecord);
				ThreadPool.exe(accountHotRecordRunable);
			}
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "ids";
			return relt;
		}else{
			return relt;
		}
	}
	

	
	/**
	 * 金科添加，查找所有的代理商
	 */
	@Override
	public PageResult findAgentPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppAccount> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
		String trueName = filter.getRequest().getParameter("trueName_like");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		
		((AppAccountDao)dao).findAgentPageBySql(map);
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
	public void saveMoney(BigDecimal money, Long custromerId, String fixPriceCoinCode) {
		/*
		AppTransaction appTransaction=new AppTransaction();
		appTransaction.setTrueName(appTransaction.getTrueName());
		
		appTransaction.setAccountId(custromerId);
		appTransaction.setCurrencyType(appAccount.getCurrencyType());
		appTransaction.setCustomerId(appAccount.getCustomerId());
		appTransaction.setRecordType(type);
		appTransaction.setRemark("给  "+appAccount.getUserName()+" 转了一笔流水");
		appTransaction.setStatus(states);
		appTransaction.setTransactionMoney(money);
		appTransaction.setUserName(appAccount.getUserName());
		appTransaction.setWebsite(appAccount.getWebsite());
		appTransaction.setTransactionNum("000000000");
		
		super.save(appHotAccountRecord);
		
		//----发送mq消息----start
		//热账户减少
		Accountadd accountadd = new Accountadd();
		accountadd.setAccountId(custromerId);
		accountadd.setMoney(money);
		accountadd.setMonteyType(1);
		accountadd.setRemarks(32);
		accountadd.setAcccountType(0);
		accountadd.setTransactionNum(transactionNum("01"));
		*/
		
		//冷账户
	/*	Accountadd accountadd2 = new Accountadd();
		accountadd2.setAccountId(custromerId);
		accountadd2.setMoney(money.multiply(new BigDecimal(-1)));
		accountadd2.setMonteyType(2);
		accountadd2.setAcccountType(0);
		accountadd2.setRemarks(32);
		accountadd2.setTransactionNum(transactionNum("01"));
		list.add(accountadd2);
		List<Accountadd> list = new ArrayList<Accountadd>();
		list.add(accountadd);
		messageProducer.toAccount(JSON.toJSONString(list));
		//----发送mq消息----end
		*/
		
	}
	
	
	
	@Override
	public void saveMoneyCoin(BigDecimal money, Long custromerId, String fixPriceCoinCode) {
		//----发送mq消息----start
		//热账户减少
/*		Accountadd accountadd = new Accountadd();
		accountadd.setAccountId(custromerId);
		accountadd.setMoney(money);
		accountadd.setMonteyType(1);
		accountadd.setRemarks(32);
		accountadd.setAcccountType(1);
		accountadd.setTransactionNum(transactionNum("01"));
		
		List<Accountadd> list = new ArrayList<Accountadd>();
		list.add(accountadd);
		messageProducer.toAccount(JSON.toJSONString(list));*/
		//----发送mq消息----end
		
	/*	
		QueryFilter filter = new QueryFilter(AppAccount.class);
		
		filter.addFilter("customerId=",custromerId);
		
		AppAccount appAccount = super.get(filter);
		
		BigDecimal money2 = appAccount.getHotMoney();
		
		appAccount.setHotMoney(money2.add(money));
		appHotAccountRecordService.addHotRecord(money, appAccount, 1, 5);
	*/
		
		
		
		
	}

	public static String transactionNum(String bussType){
		 SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
		 String time = format.format(new Date(System.currentTimeMillis()));
		 String randomStr = RandomStringUtils.random(4, false, true);
		 if(null==bussType){
			 bussType="00";
		 }
		 return bussType+time+randomStr;
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
		
		//----------------------查询开始------------------------------
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String trueName = filter.getRequest().getParameter("trueName");
		
		Map<String,Object> mapcustomer = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			mapcustomer.put("email", email);
		}
		if(!StringUtils.isEmpty(trueName)){
			mapcustomer.put("trueName", trueName);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			mapcustomer.put("mobilePhone", mobilePhone);
		}
		if(mapcustomer.size()>0){
			List<String> listpersoninfo = ((AppAccountDao)dao).findCustomerByCondition(mapcustomer);
			if(listpersoninfo.size()>0){
				map.put("customerId", listpersoninfo);
			}else{
				List<AppAccount> list = new ArrayList<AppAccount>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
			}
		}
		
		Long count = ((AppAccountDao)dao).findPageBySqlCount(map);
		List<AppAccount>  list = ((AppAccountDao)dao).findPageBySqlList(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);
	
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	
	}
}
