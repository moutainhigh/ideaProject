/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service.impl;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppAgencyTranAwardRecord;
import hry.account.fund.model.AppCoinRewardRecord;
import hry.account.fund.model.AppOurAccount;
import hry.account.fund.service.AppAccountRecordService;
import hry.account.fund.service.AppAccountService;
import hry.account.fund.service.AppAgencyTranAwardRecordService;
import hry.account.fund.service.AppCoinRewardRecordService;
import hry.account.fund.service.AppOurAccountService;
import hry.account.remote.RemoteAppOurAccountService;
import hry.change.remote.exEntrust.RemoteExExOrderService;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;

import hry.util.sys.ContextUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.remote.RemoteAppPersonInfoService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.trade.entrust.model.ExOrderInfo;
import hry.web.remote.RemoteAppConfigService;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
@Service("appOurAccountService")
public class AppOurAccountServiceImpl extends BaseServiceImpl<AppOurAccount, Long> implements AppOurAccountService{
	private static Logger logger = Logger.getLogger(AppOurAccountServiceImpl.class);
	@Resource(name="appOurAccountDao")
	@Override
	public void setDao(BaseDao<AppOurAccount, Long> dao) {
		super.dao = dao;
	}
	
	@Resource(name = "appAccountRecordService")
	public AppAccountRecordService appAccountRecordService;
	
	@Resource(name = "exDigitalmoneyAccountService")
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	
	@Resource(name = "appPersonInfoService")
	public AppPersonInfoService appPersonInfoService;
	
	@Resource(name = "appCoinRewardRecordService")
	public AppCoinRewardRecordService appCoinRewardRecordService;
	
	
	
	/**
	 * 
	 * 添加一个我方账户(包括币账户)    
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月19日 下午3:23:39
	 */
	@Override
	public JsonResult saveOurAccount(AppOurAccount appOurAccount,Integer k){
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);
		String type = appOurAccount.getAccountType();//账户类型
		String ii = appOurAccount.getOpenAccountType();
		if(null != type && ii != null){
				Integer j = appOurAccount.getIsShow();
				if(1== j){//主账户
					QueryFilter filter = new QueryFilter(AppOurAccount.class);
					String cType = appOurAccount.getCurrencyType();
					filter.addFilter("currencyType=", cType);
					filter.addFilter("isShow=", 1);
					if("1".equals(type)){
						filter.addFilter("openAccountType=", Integer.valueOf(ii));
					}
					filter.addFilter("website=", appOurAccount.getWebsite());
					filter.addFilter("accountType=", appOurAccount.getAccountType());
					List<AppOurAccount> list = super.find(filter);
					for(int i = 0; i<list.size();i++){
						AppOurAccount account = list.get(i);
						account.setIsShow(0);
						super.update(account);
					}
					if(0 == k){
						super.save(appOurAccount);
						jsonResult.setMsg("添加成功");
						return jsonResult;
					}if(1==k){
						super.update(appOurAccount);
						jsonResult.setMsg("修改成功");
						return jsonResult;
					}
				}else{
					if(0 == k){
						super.save(appOurAccount);
						jsonResult.setMsg("直接添加成功");
						return jsonResult;
					}if(1 == k){
						super.update(appOurAccount);
						jsonResult.setMsg("直接修改成功");
						return jsonResult;
					}
				}
		}
		
		jsonResult.setSuccess(false);
		jsonResult.setMsg("类型必填");
		return jsonResult;
	}
	
	
	/**
	 * 返回我方账号的账户 (平台账户唯一一个)
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月5日 下午2:05:20
	 */
	@Override
	public AppOurAccount getAppOurAccount(String website,String currencyType){
		
		QueryFilter filter = new QueryFilter(AppOurAccount.class);
		
		filter.addFilter("isShow=",1);
		filter.addFilter("website=", website);
		filter.addFilter("currencyType=", currencyType);
		
		AppOurAccount ourAccount = super.get(filter);
		
		return ourAccount;
	}
	
	/**
	 * 我方账户减一笔钱
	 * @author:    Wu Shuiming
	 * @version:   V1.0             
	 * @date:      2016年8月5日 下午2:08:33
	 */
	@Override
	public void postMoneyToAgent(BigDecimal money, String website,String currencyType){
		AppOurAccount ourAccount = this.getAppOurAccount(website, currencyType);
		if(null != null ){
			BigDecimal money2 = ourAccount.getAccountMoney();
			ourAccount.setAccountMoney(money.subtract(money2));
			super.update(ourAccount);
		}
	}
	
	/**
	 * 给我方账户增加一笔钱   
	 * 
	 */
	@Override
	public void setMoneyToAgent(BigDecimal money,String website,String currencyType){
		
		AppOurAccount ourAccount = this.getAppOurAccount(website, currencyType);
		if(ourAccount != null){
			BigDecimal money2 = ourAccount.getAccountMoney();
			
			ourAccount.setAccountMoney(money.add(money2));
			super.update(ourAccount);
		}
	}


	/**
	 * 
	 * 插询 我方币种账户的数据 需要指定的是
	 * 
	 * type ： 0  充值时的 账户   1 提现是的账户  
	 *  
	 * 
	 */
	@Override
	public AppOurAccount getAppOurAccount(String website, String currencyType, Integer type) {
		QueryFilter filter = new QueryFilter(AppOurAccount.class);
		filter.addFilter("isShow=", 1);
		filter.addFilter("openAccountType=", type);
		filter.addFilter("website=", website);
		filter.addFilter("currencyType=", currencyType);
		return super.get(filter);
	}
	
	
	

	/**
	 * 
	 * 给我方币种 账户  增加一笔 或减少一笔钱 
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午4:44:00
	 */
	@Override
	public Boolean changeBitForOurAccount(BigDecimal bitCount, AppOurAccount ourAccount){
		
		if(null != ourAccount){
			BigDecimal totalCount = ourAccount.getAccountMoney();
			//0 充币 1 提币   2.ico充币  3.ico提币
			String types = ourAccount.getOpenAccountType();
			Integer type = Integer.valueOf(types);
			if (bitCount != null) {
				if (type == 0 || type == 2) {
					ourAccount.setAccountMoney(totalCount.add(bitCount));
					super.update(ourAccount);
					return true;
				}
				if (type == 1 || type == 3) {
					ourAccount.setAccountMoney(totalCount.subtract(bitCount));
					super.update(ourAccount);
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	
	@Override
	public Boolean changeBitForOurWithdrawAccount(BigDecimal bitCount,
			AppOurAccount ourAccount) {
		BigDecimal totalCount = ourAccount.getAccountMoney();
		
		if(bitCount != null ){
	        ourAccount.setAccountMoney(totalCount.add(bitCount));
			super.update(ourAccount);
			return true;
		}
		
		return false;
	}


	@Override
	public PageResult findPageResultToOurAccount(QueryFilter filter) {
		PageResult p = new PageResult();
		Page<AppOurAccount> page = super.findPage(filter);
		

		// 设置分页数据
		p.setRows(page.getResult());
		// 设置总记录数
		p.setRecordsTotal(page.getTotal());

		p.setDraw(filter.getDraw());
		p.setPage(filter.getPage());
		p.setPageSize(filter.getPageSize());

		return p;
	}


	/**
	 * 给推荐人推荐奖励（用户买币达到阈值）
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param custromerId         卖币人
	 * @param:    @param agentsCustromerId	   卖币人的推荐人
	 * @param:    @param needGiveNum
	 * @return: void 
	 * @Date :          2017年3月9日 上午11:22:37   
	 * @throws:
	 */
	@Override
	public boolean sendCoinToAgent(ExOrderInfo orderInfo,Long agentsCustromerId, int needGiveNum,String coinCode) {
		boolean res=false;
		//推荐人
		AppPersonInfo agentsPerson=appPersonInfoService.getByCustomerId(agentsCustromerId);
		try {
			//先获取推荐人的币账户
			QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
			filter.addFilter("customerId=",agentsCustromerId);
			filter.addFilter("coinCode=",coinCode);
			ExDigitalmoneyAccount agentsAccount=exDigitalmoneyAccountService.get(filter);
			if(agentsAccount!=null){
				//给推荐人币账户增加对应的币
				agentsAccount.setHotMoney(agentsAccount.getHotMoney().add(new BigDecimal(needGiveNum)));
				exDigitalmoneyAccountService.update(agentsAccount);
				
				res=true;
			}
		} catch (Exception e) {
			logger.error("给推荐人币账户增加对应的币,并保存派发记录异常！！！");
			logger.error(e.getMessage());
		}
		
		//保存记录
		AppCoinRewardRecord record = new AppCoinRewardRecord();
		record.setCustomerId(agentsCustromerId);//得币人，也就是推荐人
		record.setCustomerName(agentsPerson.getTrueName());
		record.setCustomerMobil(agentsPerson.getMobilePhone());
		record.setRecordType(2);//类型     1实名得币  2推荐奖励得币  
		record.setRecordNum(new BigDecimal(needGiveNum));
		record.setCoverCustomerId(orderInfo.getBuyCustomId());
		//买币人（被推荐人）
		AppPersonInfo coverPerson=appPersonInfoService.getByCustomerId(orderInfo.getBuyCustomId());
		record.setCoverCustomerMobile(coverPerson.getMobilePhone());
		record.setCoverCustomerName(coverPerson.getTrueName());
		if(res){
			record.setStatus(0);//流水状态(0成功  1失败)
		}else{
			record.setStatus(1);//流水状态(0成功  1失败)
			record.setFailMsg("未查询到推荐人币账户，或者给推荐人派发推荐奖励异常！！！");
		}
		record.setExOrderInfoId(orderInfo.getId());
		
		appCoinRewardRecordService.save(record);
		
		return res;
	}

	
	/**
	 * 用户注册实名后给用户奖励一个币(废弃)
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerId
	 * @return: void 
	 * @Date :          2017年3月9日 下午3:10:25   
	 * @throws:
	 */
	@Override
	public void sendAuthRewardToCustomer(Long customerId,String coinCode) {
		AppPersonInfo person=appPersonInfoService.getByCustomerId(customerId);
		boolean res=false;
		try {
			//获取币账户
			QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
			filter.addFilter("customerId=",customerId);
			filter.addFilter("coinCode=",coinCode);
			ExDigitalmoneyAccount agentsAccount=exDigitalmoneyAccountService.get(filter);
			//然后加一个
			if(agentsAccount!=null){
				//给币账户增加对应的币
				agentsAccount.setHotMoney(agentsAccount.getHotMoney().add(new BigDecimal("1")));
				exDigitalmoneyAccountService.update(agentsAccount);
				res=true;
			}
		} catch (Exception e) {
			logger.error("给币账户增加实名奖励的币,并保存派发记录异常！！！");
			logger.error(e.getMessage());
		}
		
		//保存记录
		AppCoinRewardRecord record = new AppCoinRewardRecord();
		record.setCustomerId(customerId);//得币人
		record.setCustomerName(person.getTrueName());
		record.setCustomerMobil(person.getMobilePhone());
		record.setRecordType(1);//类型     1实名得币  2推荐奖励得币  
		if(res){
			record.setStatus(0);//流水状态(0成功  1失败)
		}else{
			record.setStatus(1);//流水状态(0成功  1失败)
			record.setFailMsg("未查询到币账户，或者派发实名奖励币异常！！！");
		}
		
		appCoinRewardRecordService.save(record);
	}

	/**
	 * 卖币人给推荐人或者省市县返佣
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param personInfo    给哪个人返
	 * @param:    @param money         返多少
	 * @param:    @param orderInfo     交易信息
	 * @return: void 
	 * @Date :          2017年3月9日 下午3:38:16   
	 * @throws:
	 */
	public void sendTradefeeToAgent(AppPersonInfo personInfo,BigDecimal money,ExOrderInfo orderInfo){
		boolean res=false;
		AppAgencyTranAwardRecordService awardRecordService=(AppAgencyTranAwardRecordService)ContextUtil.getBean("appAgencyTranAwardRecordService");
		
		try {
			//先获取资金账户
			AppAccountService accountService=(AppAccountService)ContextUtil.getBean("appAccountService");
			QueryFilter qf=new QueryFilter(AppAccount.class);
			qf.addFilter("customerId=", personInfo.getCustomerId());
			AppAccount account=accountService.get(qf);
			
			if(account!=null){
				account.setRewardMoney(account.getRewardMoney().add(money));
				accountService.update(account);
				res=true;
			}
		} catch (Exception e) {
			logger.error("卖币人给推荐人或者省市县返佣异常！！！");
			logger.error(e.getMessage());
		}
		
		//保存返佣记录
		AppAgencyTranAwardRecord awardRecord=new AppAgencyTranAwardRecord();
		awardRecord.setAgentId(personInfo.getId());
		awardRecord.setExOrderInfoId(orderInfo.getId());
		awardRecord.setAwardMoney(money);//返佣金额
		awardRecord.setSellPersonId(appPersonInfoService.getByCustomerId(orderInfo.getSellCustomId()).getId());
		if(res){
			awardRecord.setStatus(0);//流水状态(0成功  1失败)
			awardRecord.setFailMsg("派发成功！");
		}else{
			awardRecord.setStatus(1);//流水状态(0成功  1失败)
			awardRecord.setFailMsg("未查询到推荐人资金账户，或者给推荐人派发交易返佣异常！！！");
		}
		
		awardRecordService.save(awardRecord);
	}


	/**
	 * 金科比地址生成成功后，再判断一下实名返币是否成功，没成功继续返
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    
	 * @return: void 
	 * @Date :          2017年3月23日 下午7:39:13   
	 * @throws:
	 */
	@Override
	public void judgeAuthRewardState(String userName,Long exdmaccountId) {
		logger.error("金科比地址生成成功后，给实名人返还一个币:"+userName);
		//金科的必须实名，这里需要给实名奖励1个币（只给前10万实名的人）
		RemoteAppCustomerService remoteAppCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
		int authRewardNum=Integer.valueOf(getConfigValue("authRewardNum"));//配置的阈值
		//获取所有实名的人
		int hasAuthNum=remoteAppCustomerService.getHasAuthNum();
		logger.error("配置的实名阈值:"+authRewardNum +",所有实名的人数量:"+hasAuthNum);
		QueryFilter personFilter=new QueryFilter(AppPersonInfo.class);
		personFilter.addFilter("mobilePhone=", userName);
		AppPersonInfo person=appPersonInfoService.get(personFilter);
		
		//这个时候查询的实名人数应该包括该客户（更改实名状态要比这个方法提前）
		if(hasAuthNum<=authRewardNum){
			boolean res=false;
			try {
				//获取币账户
				ExDigitalmoneyAccount agentsAccount=exDigitalmoneyAccountService.get(exdmaccountId);
				//然后加一个
				if(agentsAccount!=null){
					//给币账户增加对应的币
					agentsAccount.setHotMoney(agentsAccount.getHotMoney().add(new BigDecimal("1")));
					exDigitalmoneyAccountService.update(agentsAccount);
					res=true;
				}
			} catch (Exception e) {
				logger.error("给币账户增加实名奖励的币,并保存派发记录异常！！！");
				logger.error(e.getMessage());
			}
			
			//保存记录
			AppCoinRewardRecord record = new AppCoinRewardRecord();
			record.setCustomerId(person.getCustomerId());//得币人
			record.setCustomerName(person.getTrueName());
			record.setCustomerMobil(person.getMobilePhone());
			record.setRecordType(1);//类型     1实名得币  2推荐奖励得币  
			record.setRecordNum(new BigDecimal(1));//一个币
			if(res){
				record.setStatus(0);//流水状态(0成功  1失败)
				record.setFailMsg("派发成功！");
			}else{
				record.setStatus(1);//流水状态(0成功  1失败)
				record.setFailMsg("未查询到币账户，或者派发实名奖励币异常！！！");
			}
			
			appCoinRewardRecordService.save(record);
		}
		
	}
		
	
	
	/**
	 * 获取app_config配置
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param type
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年12月8日 上午10:50:01   
	 * @throws:
	 */
	public static String  getConfigValue(String type) {
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String value= remoteAppConfigService.getValueByKey(type);
		return  value;
	}


	@Override
	public void tradeIncomeFee(String orderNum) {
		RemoteExExOrderService remoteOrderServiceService = (RemoteExExOrderService)ContextUtil.getBean("remoteExExOrderService");
		
		List<ExOrderInfo> list = remoteOrderServiceService.findByOrderNum(orderNum);
		
		if(null != list && list.size()>0 ){
		    // 平台收取手续费
			ExOrderInfo exOrderInfo=list.get(0);
			if (exOrderInfo.getTransactionBuyFee().compareTo(BigDecimal.ZERO) > 0
					|| exOrderInfo.getTransactionSellFee().compareTo(BigDecimal.ZERO) > 0) {
				try {
					// 我方账号收取手续费
					RemoteAppOurAccountService remoteAppOurAccountService = (RemoteAppOurAccountService) ContextUtil
							.getBean("remoteAppOurAccountService");
					remoteAppOurAccountService.addMoneyForOurAccount(exOrderInfo);
				} catch (Exception e) {
					logger.error("订单号：" + exOrderInfo.getOrderNum() + "我方账户收取费用失败");
				}
			}
		
	}
	}
	
}
