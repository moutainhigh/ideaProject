/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.account.fund.dao.AppTransactionDao;
import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppAccountRecord;
import hry.account.fund.model.AppOurAccount;
import hry.account.fund.model.AppTransaction;
import hry.account.fund.service.*;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.manage.remote.model.AppTransactionManage;
import hry.manage.remote.model.base.FrontPage;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
@Service("appTransactionService")
public class AppTransactionServiceImpl extends BaseServiceImpl<AppTransaction, Long> implements AppTransactionService{
	private static Logger logger = Logger.getLogger(AppTransactionServiceImpl.class);
	@Resource(name="appTransactionDao")
	@Override
	public void setDao(BaseDao<AppTransaction, Long> dao) {
		super.dao = dao;
	}
	
	@Resource
	private AppAccountService appAccountService;
	@Resource
	private AppHotAccountRecordService appHotAccountRecordService;
	@Resource
	private AppColdAccountRecordService appColdAccountRecordService;
	@Resource
	private AppOurAccountService appOurAccountService;
	@Resource
	private AppAccountRecordService appAccountRecordService;
	@Resource
	private ExDmTransactionService exDmTransactionService;
	
	
	@Resource
	private MessageProducer messageProducer;

	@Override
	public synchronized boolean confirm(Long id,String userName) {
		AppTransaction appTransaction = super.get(id);
		if(appTransaction!=null&&appTransaction.getStatus()!=null&&appTransaction.getStatus().intValue()<2){
			//不等于已完成订单
//			if(Integer.valueOf(2).compareTo(appTransaction.getStatus())!=0){
//				AppAccount appAccount = appAccountService.get(appTransaction.getAccountId());
//				//人民币账户增加一条热钱包记录，并增加一部分钱
//				String[] arr = appAccountService.inComeToHotAccount(appAccount.getId(), appTransaction.getTransactionMoney(), appTransaction.getTransactionNum(), "人民币充值",null,null);
//				//我方账户添加记录并增加一部分钱
//				this.inOurFavour(id, userName);
//				if(CodeConstant.CODE_SUCCESS.equals(arr[0])){
//				//增加手续费的支出
//				String[]  str = appAccountService.payFromHotAccount(appAccount.getId(), appTransaction.getFee(), appTransaction.getTransactionNum()+"-fee", "人民币充值手续费",null,null);
//					if(CodeConstant.CODE_SUCCESS.equals(str[0])){
//						// 给我方账户添加记录并增加一部分钱
//						this.inOurFavourFee(id, userName);
//					}
//					//2已完成
//					appTransaction.setStatus(Integer.valueOf(2));
//					super.update(appTransaction);
//					return true;
//				}
//			}
			Accountadd accountadd = new Accountadd();
			accountadd.setAccountId(appTransaction.getAccountId());
			accountadd.setMoney(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()));
			accountadd.setMonteyType(1);
			accountadd.setAcccountType(0);
			accountadd.setRemarks(21);
			accountadd.setTransactionNum(appTransaction.getTransactionNum());
			List<Accountadd> list = new ArrayList<Accountadd>();
			list.add(accountadd);
			messageProducer.toAccount(JSON.toJSONString(list));
			
			
			appTransaction.setStatus(Integer.valueOf(2));
			super.update(appTransaction);
			return true;
			
			
		}
		return false;
	}
	
	public boolean withdrawReject(AppTransaction appTransaction){
		try {
			Accountadd accountadd = new Accountadd();
			accountadd.setAccountId(appTransaction.getAccountId());
			accountadd.setMoney(appTransaction.getTransactionMoney().multiply(new BigDecimal(-1)));
			accountadd.setMonteyType(2);
			accountadd.setAcccountType(0);
			accountadd.setRemarks(38);
			accountadd.setTransactionNum(appTransaction.getTransactionNum());
			
			Accountadd accountadd1 = new Accountadd();
			accountadd1.setAccountId(appTransaction.getAccountId());
			accountadd1.setMoney(appTransaction.getTransactionMoney());
			accountadd1.setMonteyType(1);
			accountadd1.setAcccountType(0);
			accountadd1.setRemarks(38);
			accountadd1.setTransactionNum(appTransaction.getTransactionNum());
			List<Accountadd> list= new ArrayList<Accountadd>();
			list.add(accountadd);
			list.add(accountadd1);
			messageProducer.toAccount(JSON.toJSONString(list));
			
			super.update(appTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean dmWithdrawReject(ExDmTransaction exDmTransaction){
		try {
			//冷账户减
			Accountadd accountadd = new Accountadd();
			accountadd.setAccountId(exDmTransaction.getAccountId());
			accountadd.setMoney(exDmTransaction.getTransactionMoney().multiply(new BigDecimal(-1)));
			accountadd.setMonteyType(2);
			accountadd.setAcccountType(1);
			accountadd.setRemarks(37);
			accountadd.setTransactionNum(exDmTransaction.getTransactionNum());
			
			//热账户加
			Accountadd accountadd1 = new Accountadd();
			accountadd1.setAccountId(exDmTransaction.getAccountId());
			accountadd1.setMoney(exDmTransaction.getTransactionMoney());
			accountadd1.setMonteyType(1);
			accountadd1.setAcccountType(1);
			accountadd1.setRemarks(37);
			accountadd1.setTransactionNum(exDmTransaction.getTransactionNum());
			List<Accountadd> list= new ArrayList<Accountadd>();
			list.add(accountadd);
			list.add(accountadd1);
			String jsonString = JSON.toJSONString(list);
			logger.error("消息发送前:"+jsonString);
			messageProducer.toAccount(jsonString);
			
			exDmTransaction.setStatus(3);
			exDmTransactionService.update(exDmTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean confirmwithdraw(Long id,String userName) {
		AppTransaction appTransaction = super.get(id);
		
		//交易订单不等于已完成状态
		if((appTransaction.getStatus()) != 2){
			//修改账户冻结金额
//			AppAccount appAccount = appAccountService.get(appTransaction.getAccountId());
//						
//			String[] arr = appAccountService.unfreezeAccountThem(appAccount.getId(), appTransaction.getTransactionMoney().subtract(appTransaction.getFee()), appTransaction.getTransactionNum(), "人民币提现金额解除冻结",null,null);
//
//			if(CodeConstant.CODE_SUCCESS.equals(arr[0])){
//		
//				// 处理自己平台上的记录
//				boolean b = this.inOurFavour(id, userName);
//				if(b){
//					//手续费
//					String[] str = appAccountService.unfreezeAccountThem(appAccount.getId(), appTransaction.getFee(), appTransaction.getTransactionNum()+"-fee", "人民币提现手续费解除冻结",null,null);
//					
//					if(CodeConstant.CODE_SUCCESS.equals(str[0])){
//						// 给我方账户添加记录并增加一部分钱
//						this.inOurFavourFee(id, userName);
//					}
//					//2已完成
//					appTransaction.setStatus(Integer.valueOf(2));
//					appTransaction.setReadyStates(1);
//					super.update(appTransaction);
//					
//					try {                 
//					} catch (Exception e) {
//						logger.error("根据提现订单处理佣金时 发送消息失败    订单号为 ："+appTransaction.getTransactionNum());
//					}
//					return true;
//				}else{
//					logger.error("我方账户出现了问题。。。有可能是我方账户被删除了");
//					String coldid=arr[1];
//					logger.error("coldid="+coldid);
//					if(coldid.startsWith("cold")){
//						coldid=  coldid.replace("cold","");
//						appColdAccountRecordService.delete(Long.valueOf(coldid.trim()));
//					  }
//					//失败的话就把冻结的金额给加回来
//					appAccount.setColdMoney(appAccount.getColdMoney().add(appTransaction.getTransactionMoney()));
//					appAccountService.updateAccount(appAccount);
//					return false;
//				}
//			}
			
			
			Accountadd accountadd = new Accountadd();
			accountadd.setAccountId(appTransaction.getAccountId());
			accountadd.setMoney(appTransaction.getTransactionMoney().multiply(new BigDecimal(-1)));
			accountadd.setMonteyType(2);
			accountadd.setAcccountType(0);
			accountadd.setRemarks(22);
			accountadd.setTransactionNum(appTransaction.getTransactionNum());
			List<Accountadd> list= new ArrayList<Accountadd>();
			list.add(accountadd);
			messageProducer.toAccount(JSON.toJSONString(list));
			
			appTransaction.setStatus(Integer.valueOf(2));
			appTransaction.setReadyStates(1);
			super.update(appTransaction);
			return true;
			
		}
		return false;
	}

	
	/**
	 * 通过订单id 给我方账号添加或减少money
	 * 返回true/false
	 */
	@Override
	public boolean inOurFavour(Long id,String auditor) {
		
		AppTransaction appTransaction = super.get(id);
		
		if(2 != appTransaction.getStatus()){
			QueryFilter filter = new QueryFilter(AppOurAccount.class);
			String s= appTransaction.getOurAccountNumber();
			filter.addFilter("accountNumber=", s);
			List<AppOurAccount> list = appOurAccountService.find(filter);
			
			if(list.size()>0){
				AppOurAccount appOurAccount = list.get(0);
				AppAccountRecord appAccountRecord = new AppAccountRecord();
				BigDecimal newMoney = appOurAccount.getAccountMoney();
				
				Integer intg = appTransaction.getTransactionType();
				if( intg== 1 ){
					//Source  1 表示线上
					appAccountRecord.setSource(1);
					//RecordType  1 表示充值 2 表示提现
					appAccountRecord.setRecordType(intg);
					newMoney = appOurAccount.getAccountMoney().add(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()));
					appAccountRecord.setTransactionMoney(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()));
				}if( intg ==2 ){
					//Source  1 表示线上
					appAccountRecord.setSource(1);
					//RecordType  1 表示充值 2 表示提现
					appAccountRecord.setRecordType(intg);
					newMoney = appOurAccount.getAccountMoney().subtract(appTransaction.getTransactionMoney());
					appAccountRecord.setTransactionMoney(appTransaction.getTransactionMoney());
				}if(intg ==3){
					//Source  0 表示线下
					appAccountRecord.setSource(0);
					//RecordType  1 表示充值
					appAccountRecord.setRecordType(1);
					newMoney = appOurAccount.getAccountMoney().add(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()));
					appAccountRecord.setTransactionMoney(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()));
				}if(intg ==4){
					//Source  0 表示线下
					appAccountRecord.setSource(0);
					//RecordType   2 表示提现
					appAccountRecord.setRecordType(2);
					newMoney = appOurAccount.getAccountMoney().subtract(appTransaction.getTransactionMoney());
					appAccountRecord.setTransactionMoney(appTransaction.getTransactionMoney());
				}if(intg ==5){//支付宝充值
					//Source  0 表示线下
					appAccountRecord.setSource(0);
					//RecordType  1 表示充值
					appAccountRecord.setRecordType(1);
					newMoney = appOurAccount.getAccountMoney().add(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()));
					appAccountRecord.setTransactionMoney(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()));
				}
				
				appOurAccount.setAccountMoney(newMoney);
				appOurAccountService.update(appOurAccount);
				
				appAccountRecord.setAppAccountId(appOurAccount.getId());
				appAccountRecord.setAppAccountNum(appOurAccount.getAccountNumber());
				appAccountRecord.setCustomerId(appTransaction.getCustomerId());
				appAccountRecord.setCustomerName(appTransaction.getUserName());
				
				
				appAccountRecord.setTransactionNum(appTransaction.getTransactionNum());
				appAccountRecord.setStatus(5);
				appAccountRecord.setRemark(appTransaction.getRemark());
				appAccountRecord.setCurrencyName(appTransaction.getCurrencyType());
				appAccountRecord.setCurrencyType(appTransaction.getCurrencyType());
				appAccountRecord.setWebsite(appTransaction.getWebsite());
				appAccountRecord.setOperationTime(new Date());
				appAccountRecord.setAuditor(auditor);
				appAccountRecord.setCustomerAccount(appTransaction.getCustromerAccountNumber());
		
				appAccountRecordService.save(appAccountRecord);
				return true;
			
			}
			
		}
		
		return false;
		
	}

	@Override
	public boolean readyWithdraw(Long id) {
		AppTransaction appTransaction = super.get(id);
		Integer i = appTransaction.getStatus();
		if(1==i){
			appTransaction.setStatus(4);
			super.update(appTransaction);
			return true;
		}
		return false;
	}
	
	
	/**
	 * 通过订单id 给我方账号添加或减少money  ---用户手续费
	 * 返回true/false
	 */
	public boolean inOurFavourFee(Long id,String auditor) {
		
		AppTransaction appTransaction = super.get(id);
		
		if(2 != appTransaction.getStatus()){
			QueryFilter filter = new QueryFilter(AppOurAccount.class);
			String s= appTransaction.getOurAccountNumber();
			filter.addFilter("accountNumber=", s);
			List<AppOurAccount> list = appOurAccountService.find(filter);
			
			if(list.size()>0){
				
				AppOurAccount appOurAccount = list.get(0);
				AppAccountRecord appAccountRecord = new AppAccountRecord();
				BigDecimal newMoney = appOurAccount.getAccountMoney();
				
				Integer intg = appTransaction.getTransactionType();
				if( intg== 1 ){//充值
					//Source  1 表示线上
					appAccountRecord.setSource(1);
					
					appAccountRecord.setRecordType(3);
					newMoney = appOurAccount.getAccountMoney().add(appTransaction.getFee());

				}if( intg ==2 ){//提现
					//Source  1 表示线上
					appAccountRecord.setSource(1);
					
					appAccountRecord.setRecordType(4);
					newMoney = appOurAccount.getAccountMoney().add(appTransaction.getFee());
				}if(intg ==3){//线下充值
					//Source  0 表示线下
					appAccountRecord.setSource(0);
					//RecordType   2 表示充值
					appAccountRecord.setRecordType(3);
					newMoney = appOurAccount.getAccountMoney().add(appTransaction.getFee());
				}if(intg ==4){//线下提现
					//Source  0 表示线下
					appAccountRecord.setSource(0);
					//RecordType   2 表示提现
					appAccountRecord.setRecordType(4);
					newMoney = appOurAccount.getAccountMoney().add(appTransaction.getFee());
				}if(intg ==5){//支付宝充值
					//Source  0 表示线下
					appAccountRecord.setSource(0);
					//RecordType   2 表示充值
					appAccountRecord.setRecordType(3);
					newMoney = appOurAccount.getAccountMoney().add(appTransaction.getFee());
				}
				
				appOurAccount.setAccountMoney(newMoney);
				appOurAccountService.update(appOurAccount);
				
				appAccountRecord.setAppAccountId(appOurAccount.getId());
				appAccountRecord.setAppAccountNum(appOurAccount.getAccountNumber());
				appAccountRecord.setCustomerId(appTransaction.getCustomerId());
				appAccountRecord.setCustomerName(appTransaction.getUserName());
				
				appAccountRecord.setTransactionMoney(appTransaction.getFee());
				appAccountRecord.setTransactionNum(appTransaction.getTransactionNum()+"-fee");
				appAccountRecord.setStatus(5);
				appAccountRecord.setRemark(appTransaction.getRemark());
				appAccountRecord.setCurrencyName(appTransaction.getCurrencyType());
				appAccountRecord.setCurrencyType(appTransaction.getCurrencyType());
				appAccountRecord.setWebsite(appTransaction.getWebsite());
				appAccountRecord.setOperationTime(new Date());
				appAccountRecord.setAuditor(auditor);
				appAccountRecord.setCustomerAccount(appTransaction.getCustromerAccountNumber());
		
				appAccountRecordService.save(appAccountRecord);
				return true;
			
			}
			
		}
		
		return false;
		
	}

	
	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		

		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<ExDmLend> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<>();


		String  status= filter.getRequest().getParameter("status_EQ");
		String transactionNum = filter.getRequest().getParameter("transactionNum_LIKE");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		String userName = filter.getRequest().getParameter("userName_LIKE");
		String currencyType = filter.getRequest().getParameter("currencyType_EQ");
		String transactionType = filter.getRequest().getParameter("transactionType_in");
		String ourAccountNumber = filter.getRequest().getParameter("ourAccountNumber_LIKE");


		if(!StringUtils.isEmpty(status)){
			map.put("status", status);
		}
		if(!StringUtils.isEmpty(transactionNum)){
			map.put("transactionNum", "%"+transactionNum+"%");
		}
		if(!StringUtils.isEmpty(modified_s)){
			map.put("modified_s", modified_s);
		}
		if(!StringUtils.isEmpty(modified_e)){
			map.put("modified_e", modified_e);
		}
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", "%"+userName+"%");
		}
		if(!StringUtils.isEmpty(currencyType)){
			map.put("currencyType", currencyType);
		}
		if(!StringUtils.isEmpty(transactionType)){
			if(transactionType.split(",").length>=0){
				map.put("transactionType", transactionType.split(","));
			}else{
				map.put("transactionType", new String[]{transactionType});
			}
			
			
		}
		if(!StringUtils.isEmpty(ourAccountNumber)){
			map.put("ourAccountNumber", ourAccountNumber);
		}
	    map.put("orderby", "atr.created desc");
		((AppTransactionDao)dao).findPageBySql(map);
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		
	  return pageResult;
	}

	
	
	@Override
	public BigDecimal countByDate(String[] type,String beginDate,String endDate,String[] status,String userName) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(status)){
			map.put("status", status);
		}
		if(!StringUtils.isEmpty(beginDate)){
			map.put("createdG", beginDate);
		}
		if(!StringUtils.isEmpty(endDate)){
			map.put("createdL", endDate);
		}
		
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", userName);
		}
		
		if(!StringUtils.isEmpty(type)){
		
		   map.put("transactionType",type);
		
		}
		
		BigDecimal money=new BigDecimal(0);
		money=((AppTransactionDao)dao).countByDate(map);
		if(null!=money&&!"".equals(money)){
			return money;
		}else{
			return  new BigDecimal(0.00);
		}
		
	}

	@Override
	public synchronized boolean undo(Long id, String name) {

		AppTransaction appTransaction = super.get(id);
		AppAccount appAccount = appAccountService.get(appTransaction.getAccountId());
		
		if (appTransaction != null && appTransaction.getStatus()==2){
			if(appAccount.getHotMoney().compareTo(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()))>=0){

				appTransaction.setStatus(Integer.valueOf(3));
				appTransaction.setRejectionReason("充值成功后撤销");
				super.update(appTransaction);
				
				
				Accountadd accountadd = new Accountadd();
				accountadd.setAccountId(appTransaction.getAccountId());
				accountadd.setMoney(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()).multiply(new BigDecimal(-1)));
				accountadd.setMonteyType(1);
				accountadd.setAcccountType(0);
				accountadd.setRemarks(22);
				accountadd.setTransactionNum(appTransaction.getTransactionNum());
				List<Accountadd> list = new ArrayList<Accountadd>();
				list.add(accountadd);
				messageProducer.toAccount(JSON.toJSONString(list));
				
				return true;
			
			}
		}
	
		return false;

	}
	
	
	/**
	 * 定时任务去查询闪付接口的提现订单
	 */
	@Override
	public void queryShanfuWithdrawOrder() {}
	
	/**
	 * 充值交易分页
	 */
	public FrontPage findTransaction(Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		List<AppTransactionManage> list = ((AppTransactionDao)dao).findTransaction(params);
		FrontPage frontPage = new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
		return frontPage;
	}

	@Override
	public PageResult listPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<ExDmLend> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		String  status= filter.getRequest().getParameter("status_EQ");
		String transactionNum = filter.getRequest().getParameter("transactionNum_LIKE");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		String userName = filter.getRequest().getParameter("userName_LIKE");
		String currencyType = filter.getRequest().getParameter("currencyType_EQ");
		String transactionType = filter.getRequest().getParameter("transactionType_in");
		String ourAccountNumber = filter.getRequest().getParameter("ourAccountNumber_LIKE");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(status)){
			map.put("status", status);
		}
		if(!StringUtils.isEmpty(transactionNum)){
			map.put("transactionNum", "%"+transactionNum+"%");
		}
		if(!StringUtils.isEmpty(modified_s)){
			map.put("modified_s", modified_s);
		}
		if(!StringUtils.isEmpty(modified_e)){
			map.put("modified_e", modified_e);
		}
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", "%"+userName+"%");
		}
		if(!StringUtils.isEmpty(currencyType)){
			map.put("currencyType", currencyType);
		}
		if(!StringUtils.isEmpty(transactionType)){
			if(transactionType.split(",").length>=0){
				map.put("transactionType", transactionType.split(","));
			}else{
				map.put("transactionType", new String[]{transactionType});
			}
		}
		if(!StringUtils.isEmpty(ourAccountNumber)){
			map.put("ourAccountNumber", ourAccountNumber);
		}
	    map.put("orderby", "atr.created desc");
		((AppTransactionDao)dao).listPageBySql(map);
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		
	  return pageResult;
	}

}
