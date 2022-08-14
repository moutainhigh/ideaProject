package hry.exchange.remote.subscription;

import hry.account.fund.model.AppAccount;
import hry.account.remote.RemoteAppAccountService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.shiro.PasswordHelper;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.remote.RemoteAppTradeFactorageService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.subscription.model.ExBuybackRecord;
import hry.exchange.subscription.model.ExSubscriptionPlan;
import hry.exchange.subscription.model.ExSubscriptionRecord;
import hry.exchange.subscription.service.ExBuybackRecordService;
import hry.exchange.subscription.service.ExSubscriptionPlanService;
import hry.exchange.subscription.service.ExSubscriptionRecordService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

public class RemoteExSubscriptionServiceImpl implements RemoteExSubscriptionService{
	@Resource
	public ExBuybackRecordService exBuybackRecordService;
	@Resource
	public ExSubscriptionRecordService exSubscriptionRecordService;
	@Resource
	public ExSubscriptionPlanService exSubscriptionPlanService;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Override
	public ExSubscriptionPlan remoteListPlan(Long customerId,String website, String currencyType) {
		// TODO Auto-generated method stub
		ExSubscriptionPlan  subPlan = null;
		try {
			  //查询是有已看上认购的记录
			  subPlan = exPlanList(null);
			if(null!=subPlan){
				subPlan = subPlanInfo(subPlan,customerId,website,currencyType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subPlan;
	}
	/**
	 * 查询我要认购信息
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param plan
	 * @param:    @return
	 * @return: ExSubscriptionPlan 
	 * @Date :          2016年12月2日 下午5:13:06   
	 * @throws:
	 */
	public ExSubscriptionPlan subPlanInfo(ExSubscriptionPlan plan,Long customerId,String website, String currencyType){
		//得到币账户信息
		ExDigitalmoneyAccount digaccount = exDigitalmoneyAccountService.getByCustomerIdAndType(customerId, plan.getCoinCode(), currencyType, website);
		//得到账户余额信息
		RemoteAppAccountService remoteappaccount = (RemoteAppAccountService)ContextUtil.getBean("remoteAppAccountService");
		AppAccount appaccount = remoteappaccount.getByCustomerIdAndType(customerId,currencyType, website);
		if(null!=digaccount&&null!=appaccount){
			//账户可用余额
			plan.setApphotMoney(appaccount.getHotMoney());
			//当前币个数
			plan.setDigitalMoney(digaccount.getHotMoney());
			//判断但当前认购价是否超过认购最高价
			if(plan.getCurrentPrice().compareTo(plan.getHighestPrice())>0){
				plan.setState(3);
				exSubscriptionPlanService.update(plan);
			}
			
			//最多可以购买的金额 得到已经认购的价格
			List<ExSubscriptionRecord> totalMoney = exSubscriptionRecordService.subTotalNum(plan.getId(), customerId);
			BigDecimal realTimeMoey = plan.getMaximumNumber().subtract(totalMoney.get(0).getMaxNum());
			if(appaccount.getHotMoney().compareTo(realTimeMoey)<0){
				plan.setTotalNum(appaccount.getHotMoney());
			}else{
				plan.setTotalNum(realTimeMoey);
			}
			//当前已认购金额
			plan.setBuySubscribedNum(totalMoney.get(0).getMaxNum());
			//认购进度
			/*BigDecimal progress = plan.getPurchaseNumber().divide(plan.getOpenNumber(),2,RoundingMode.HALF_UP);
			if(plan.getPurchaseNumber().compareTo(new BigDecimal("0"))>0&&progress.compareTo(new BigDecimal("0"))==0){
				//防止有认购记录认购进度为0 
				plan.setProgress(new BigDecimal("0.01"));
			}else{
				plan.setProgress(progress);
			}*/
			//最多可以认购数量 1，账户余额/认购价 2.最大可认购数量
			/*BigDecimal buyNum1 = appaccount.getHotMoney().divide(currenMoney,0, RoundingMode.DOWN);
			//得到已认购数量
			List<ExSubscriptionRecord> buyamount = exSubscriptionRecordService.subTotalNum(plan.getId(), customerId);
			if(buyamount.size()>0){
				//当期已认购个数
				plan.setBuySubscribedNum(buyamount.get(0).getMaxNum().setScale(0).intValue());
				//最大可认购数量 -已经认购数量
				BigDecimal buyNum2 = plan.getMaximumNumber().subtract(buyamount.get(0).getMaxNum()).setScale(0);
				if(buyNum1.compareTo(buyNum2)>=0){
					plan.setTotalNum(buyNum2);
				}else{
					plan.setTotalNum(buyNum1.setScale(0, BigDecimal.ROUND_HALF_UP));
				}
			}*/
		}
		
		return plan;
	}
	/**
	 * 新增认购记录 数量==金额  currentPrice币当前认购金额。buyTotalNum用户认购总金额
	 */
	@Override
	public JsonResult remoteAddRecord(Long customerId, Long planId,
			String currentPrice, String buyTotalNum,String currencyType, String website,String accountPassword) {
		JsonResult  jr = new JsonResult();
		try {
			RemoteAppCustomerService remoteAppCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
			AppCustomer appCustomer = remoteAppCustomerService.getById(customerId);
			PasswordHelper passwordHelper = new PasswordHelper();
			String newAccountPassWord = passwordHelper.encryString(accountPassword, appCustomer.getSalt());
			if(!appCustomer.getAccountPassWord().equals(newAccountPassWord)){
				jr.setSuccess(false);
				jr.setMsg("交易密码输入错误!");
				return jr;
			}
			//认购计划信息
			ExSubscriptionPlan plan = exSubscriptionPlanService.get(planId);
			//得到币账户信息
			ExDigitalmoneyAccount digaccount = exDigitalmoneyAccountService.getByCustomerIdAndType(customerId, plan.getCoinCode(), currencyType, website);
			//得到账户余额信息
			RemoteAppAccountService remoteappaccount = (RemoteAppAccountService)ContextUtil.getBean("remoteAppAccountService");
			AppAccount appaccount = remoteappaccount.getByCustomerIdAndType(customerId,currencyType, website);
			if(null!=plan&&null!=digaccount&&null!=appaccount){
				if(appaccount.getHotMoney().compareTo(new BigDecimal(buyTotalNum))<0){
					jr.setSuccess(false);
					jr.setMsg("账户余额不足，请先充值！");
					return jr;
				}
				//判断认购数量是否大于等于最低认购数量  数量==金额
				if(plan.getMinimumNumber().compareTo(new BigDecimal(buyTotalNum))>0){
					jr.setSuccess(false);
					jr.setMsg("认购金额不能低于："+plan.getMinimumNumber().setScale(2, RoundingMode.DOWN)+"元");
					return jr;
				}
				if(plan.getMaximumNumber().compareTo(new BigDecimal(buyTotalNum))<0){
					jr.setSuccess(false);
					jr.setMsg("认购金额不能大于："+plan.getMaximumNumber().setScale(2, RoundingMode.DOWN)+"元");
					return jr;
				}
				//得到已认购数量
				List<ExSubscriptionRecord> buyamount = exSubscriptionRecordService.subTotalNum(plan.getId(), customerId);
				//最多可以人认购金额
				BigDecimal mostNum = plan.getMaximumNumber().subtract(buyamount.get(0).getMaxNum());
				if(mostNum.compareTo(new BigDecimal(buyTotalNum))<0){
					jr.setSuccess(false);
					jr.setMsg("认购总金额已达到上限，当前最多可再认购："+mostNum.setScale(2, RoundingMode.DOWN)+"元");
					return jr;
				}
				//生成认购记录
				String transactionNum = IdGenerate.transactionNum(NumConstant.App_Transaction);
				jr = exSubscriptionRecordService.saveSubRecord(plan, appaccount, currentPrice, buyTotalNum,transactionNum);
				if(jr.getSuccess()){
					//币账户增加余额
					BigDecimal number = new BigDecimal(buyTotalNum).divide(new BigDecimal(currentPrice),2, RoundingMode.FLOOR);
					exDigitalmoneyAccountService.inComeToHotAccount(digaccount.getId(), number, IdGenerate.transactionNum(NumConstant.App_Transaction), "认购", null,null);
					//账户余额减少
					remoteappaccount.payFromHotAccount(appaccount.getId(), new BigDecimal(buyTotalNum), IdGenerate.transactionNum(NumConstant.App_Transaction), "认购", null,null);
					//计算认购返佣金额
					RemoteAppTradeFactorageService remoteAppTradeFactorageService = (RemoteAppTradeFactorageService)ContextUtil.getBean("remoteAppTradeFactorageService");
					remoteAppTradeFactorageService.dealCommissionBySubscription(plan, customerId, new BigDecimal(buyTotalNum),transactionNum);
					//认购终止判断,
					plan = exSubscriptionPlanService.get(planId);
					//认购结束的判断，如果 最低认购人民币金额>当前认购价格*可认购数量，则认购结束
					BigDecimal newMoney = new BigDecimal(currentPrice).multiply(plan.getSurplusNumber());
					if(newMoney.compareTo(plan.getMinimumNumber())<0){
						plan.setState(3);
						exSubscriptionPlanService.update(plan);
						jr.setMsg("第"+plan.getPeriod()+"期认购结束，请等待交易市场开启");
					}else{
						jr.setMsg("认购成功，请在账户中查看");
					}
				}
			}else{
				jr.setSuccess(false);
				jr.setMsg("用户账户信息错误，请重新认购");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}
	/**
	 * 用户认购记录
	 */
	@Override
	public PageResult findListRecordPage(RemoteQueryFilter remoteFilter, Long customerId) {
		QueryFilter filter = remoteFilter.getQueryFilter();
		filter.addFilter("customerId=", customerId);
		return exSubscriptionRecordService.findPageResult(filter);
	}
	/**
	 * 用户回购记录
	 */
	@Override
	public PageResult findListBackRecordPage(RemoteQueryFilter remoteFilter,
			Long customerId) {
		QueryFilter filter = remoteFilter.getQueryFilter();
		filter.addFilter("customerId=", customerId);
		return exBuybackRecordService.findPageResult(filter);
	}
	/**
	 * 回购申请
	 */
	@Override
	public JsonResult buyBackRecord(String id,String buyBackNum) {
		// TODO Auto-generated method stub
		return exSubscriptionRecordService.saveBuyBack(id,buyBackNum);
	}
	/**
	 * 用户撤销回购
	 */
	@Override
	public JsonResult buyBackRevoke(Long customreId, Long backId) {
		JsonResult jr = new JsonResult();
		ExBuybackRecord backRecord = exBuybackRecordService.get(backId);
		if(null!=backRecord){
			if(backRecord.getCustomerId().longValue()==customreId.longValue()){
				backRecord.setState(3);
				exBuybackRecordService.update(backRecord);
				//减少回购申请中的数量
				ExSubscriptionRecord subRecord = exSubscriptionRecordService.get(backRecord.getRecordId());
				subRecord.setApplyBackNum(subRecord.getApplyBackNum().subtract(backRecord.getBackAmount()));
				exSubscriptionRecordService.update(subRecord);
				//冻结的币解冻
				ExDigitalmoneyAccount account = exDigitalmoneyAccountService.getByCustomerIdAndType(backRecord.getCustomerId(), backRecord.getCoinCode(), ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
				exDigitalmoneyAccountService.unfreezeAccountSelf(account.getId(),  backRecord.getBackAmount(), IdGenerate.transactionNum(NumConstant.App_Transaction), "用户撤销回购",null,null);
				
				jr.setSuccess(true);
			}else{
				jr.setSuccess(false);
				jr.setMsg("操作错误");
			}
		}else{
			jr.setSuccess(false);
			jr.setMsg("回购记录错误");
		}
		return jr;
	}
	@Override
	public List<ExSubscriptionRecord> remotePlanNum() {
		// TODO Auto-generated method stub
		 List<ExSubscriptionRecord> record = exSubscriptionRecordService.remotePlanNum();
		 ExSubscriptionPlan plan = exPlanList("2");
		 if(null!=plan){
			 for(ExSubscriptionRecord r:record){
				 r.setPrice(plan.getCurrentPrice());
			 }
		 }
		 return record;
	}
	/**
	 * 得到认购中的计划。并计算当前认购价
	 */
	@Override
	public ExSubscriptionPlan exPlanList(String state) {
		ExSubscriptionPlan plan = null;
		QueryFilter filter = new QueryFilter(ExSubscriptionPlan.class);
		if(null!=state&&!state.equals("")){
			filter.addFilter("state=", state);
		}else{
		    List<String>  liststr = new ArrayList<String>();   
		    liststr.add("1");
		    liststr.add("2");
			filter.addFilter("state_in", liststr);//查询认购中的和已发布但是已经到可以认购时间。
			
		}
		List<ExSubscriptionPlan> list = exSubscriptionPlanService.find(filter);
		for(ExSubscriptionPlan p :list){
			if(p.getState()==2){
				plan = p;
				break;
			}else{
				//判断是否达到可以认购的时间
				int result=DateUtil.compareDate(p.getStartTime(), new Date(), 1);
				if(result>=0){
					p.setState(2);//修改状态为已开始认购
					exSubscriptionPlanService.update(p);
					plan = p;
					break;
				}
			}
		}
		//计算认购价
		BigDecimal currenMoney = new BigDecimal("0");
		if(null!=plan){
			//当前认购价 。初始认购价+（当前已认购数量/涨价基数）*涨幅
			List<ExSubscriptionRecord> recordsize =  exSubscriptionRecordService.sumCurrenNum(plan.getId());
			BigDecimal recordSum = recordsize.get(0).getCurrenNum();
			//认购数量、涨价基数大于0
			if(recordSum.compareTo(new BigDecimal(0))>0&&null!=plan.getPriceBase()&&plan.getPriceBase().compareTo(new BigDecimal("0"))>0){
				BigDecimal increaseMoney = recordSum.divide(plan.getPriceBase(),0, RoundingMode.DOWN).multiply(plan.getRose());
				currenMoney =plan.getInitialPrice().add(increaseMoney);
			}else{
				currenMoney = plan.getInitialPrice();
			}
			plan.setCurrentPrice(currenMoney);
		}
		return plan;
	}
	
}
