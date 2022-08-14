/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zenghao
 * @version:     V1.0 
 * @Date:        2016-11-24 16:36:09 
 */
package hry.exchange.subscription.service.impl;

import hry.account.fund.model.AppAccount;
import hry.account.remote.RemoteAppAccountService;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * <p> ExBuybackRecordService </p>
 * @author:         zenghao
 * @Date :          2016-11-24 16:36:09  
 */
@Service("exBuybackRecordService")
public class ExBuybackRecordServiceImpl extends BaseServiceImpl<ExBuybackRecord, Long> implements ExBuybackRecordService{
	@Resource
	private ExSubscriptionPlanService exSubscriptionPlanService;
	@Resource
	private ExSubscriptionRecordService exSubscriptionRecordService;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource(name="exBuybackRecordDao")
	@Override
	public void setDao(BaseDao<ExBuybackRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public JsonResult saveBuybackRecord(ExSubscriptionRecord subRecord,
			BigDecimal backNum) {
		JsonResult jr = new JsonResult();
		ExBuybackRecord buyRecord = new ExBuybackRecord();
		//认购记录id
		buyRecord.setRecordId(subRecord.getId());
		//客户id
		buyRecord.setCustomerId(subRecord.getCustomerId());
		//用户名
		buyRecord.setUserName(subRecord.getUserName());
		//真实姓名
		buyRecord.setTrueName(subRecord.getTrueName());
		//币种代码
		buyRecord.setCoinCode(subRecord.getCoinCode());
		//币种名称
		buyRecord.setCoinName(subRecord.getCoinName());
		//认购期数
		buyRecord.setPeriod(subRecord.getPeriod());
		//认购价格
		buyRecord.setBackPrice(subRecord.getPrice());
		//回购数量
		buyRecord.setBackAmount(backNum);
		//订单号
		buyRecord.setTransactionNum(IdGenerate.transactionNum(NumConstant.App_Transaction));
		//状态
		buyRecord.setState(0);
		//认购数量
		buyRecord.setAmount(subRecord.getAmount());
		//认购单号
		buyRecord.setSubTransactionNum(subRecord.getTransactionNum());
		//回购总金额
		buyRecord.setBuyTotalAmount(backNum.multiply(subRecord.getPrice()));
		//回购手续费
		buyRecord.setRepurchaseFee(subRecord.getRepurchaseFee());
		//回购手续费金额
		BigDecimal feeMoney = subRecord.getRepurchaseFee().divide(new BigDecimal("100"),2,RoundingMode.HALF_UP);
		buyRecord.setFeeMoney(buyRecord.getBuyTotalAmount().multiply(feeMoney));
		this.save(buyRecord);
		//回购申请中数量
		subRecord.setApplyBackNum(subRecord.getApplyBackNum().add(backNum));
		exSubscriptionRecordService.update(subRecord);
		return jr;
	}
	/**
	 * 通过回购
	 */
	@Override
	public JsonResult passedRecord(Long id) {
		JsonResult jr = new JsonResult();
		ExBuybackRecord exBuybackRecord = this.get(id);
		if(exBuybackRecord!=null&&exBuybackRecord.getState()==0){
			//得到币账户信息
			ExDigitalmoneyAccount account = exDigitalmoneyAccountService.getByCustomerIdAndType(exBuybackRecord.getCustomerId(), exBuybackRecord.getCoinCode(), ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
			//得到账户余额信息
			RemoteAppAccountService remoteappaccount = (RemoteAppAccountService)ContextUtil.getBean("remoteAppAccountService");
			AppAccount appaccount = remoteappaccount.getByCustomerIdAndType(account.getCustomerId(),ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
			//认购记录信息
			ExSubscriptionRecord subRecord = exSubscriptionRecordService.get(exBuybackRecord.getRecordId());
			if(null!=account&&null!=appaccount&&null!=remoteappaccount&&null!=subRecord){
				//认购计划信息
				ExSubscriptionPlan plan = exSubscriptionPlanService.get(subRecord.getPlanId());
				if(plan!=null){
					//计算回购金额
					BigDecimal incomeMoney = exBuybackRecord.getBackPrice().multiply(exBuybackRecord.getBackAmount());
					//计算回购手续费金额
					//BigDecimal feeMoney = incomeMoney.multiply(plan.getRepurchaseFee().divide(new BigDecimal("100")));
					//增加账户余额
					remoteappaccount.inComeToHotAccount(appaccount.getId(), incomeMoney.subtract(exBuybackRecord.getFeeMoney()), IdGenerate.transactionNum(NumConstant.App_Transaction), "回购", null,null);
					//减少币种数量
					exDigitalmoneyAccountService.unfreezeAccountThem(account.getId(), exBuybackRecord.getBackAmount(), IdGenerate.transactionNum(NumConstant.App_Transaction), "回购", null,null);
					//增加认购记录表的已回购数量
					subRecord.setBackAmount(subRecord.getBackAmount().add(exBuybackRecord.getBackAmount()));
					//更新认购记录表的状态。1部分回购2全部回购
					if(subRecord.getAmount().compareTo(subRecord.getBackAmount())==0){
						subRecord.setState(2);
					}else{
						subRecord.setState(1);
					}
					//减少认购申请中的数量
					subRecord.setApplyBackNum(subRecord.getApplyBackNum().subtract(exBuybackRecord.getBackAmount()));
					exSubscriptionRecordService.update(subRecord);
					//更新回购记录表状态为1
					exBuybackRecord.setState(1);
					this.update(exBuybackRecord);
					//更新认购计划表
					if(plan.getState()==2){
						plan.setPurchaseNumber(plan.getPurchaseNumber().subtract(exBuybackRecord.getBackAmount()));
						plan.setSurplusNumber(plan.getSurplusNumber().add(exBuybackRecord.getBackAmount()));
						exSubscriptionPlanService.update(plan);
					}
					jr.setSuccess(true);
				}else{
					jr.setMsg("认购计划信息错误");
					jr.setSuccess(false);
				}
			}else{
				jr.setMsg("账户信息错误");
				jr.setSuccess(false);
			}
		}else{
			jr.setMsg("回购记录不存在，或者已经通过或驳回了");
			jr.setSuccess(false);
		}
		return jr;
	}

	@Override
	public JsonResult rejectRecord(HttpServletRequest request) {
		JsonResult jr = new JsonResult();
		Long id = Long.valueOf(request.getParameter("id"));
		String remarks = request.getParameter("remarks");
		ExBuybackRecord exBuybackRecord = this.get(id);
		if(exBuybackRecord!=null){
			//更新回购记录状态
			exBuybackRecord.setState(2);
			exBuybackRecord.setRemarks(remarks);
			this.update(exBuybackRecord);
			//更新币账户信息
			ExDigitalmoneyAccount account = exDigitalmoneyAccountService.getByCustomerIdAndType(exBuybackRecord.getCustomerId(), exBuybackRecord.getCoinCode(), ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
			exDigitalmoneyAccountService.unfreezeAccountSelf(account.getId(),  exBuybackRecord.getBackAmount(), IdGenerate.transactionNum(NumConstant.App_Transaction), "驳回回购",null,null);
			//减少回购申请中的数量	
			ExSubscriptionRecord subRecord = exSubscriptionRecordService.get(exBuybackRecord.getRecordId());
			subRecord.setApplyBackNum(subRecord.getApplyBackNum().subtract(exBuybackRecord.getBackAmount()));
			exSubscriptionRecordService.update(subRecord);
			jr.setSuccess(true);
		}else{
			jr.setMsg("未查询到回购记录");
			jr.setSuccess(false);
		}
		return jr;
	}
}
