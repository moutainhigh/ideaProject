/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zenghao
 * @version:     V1.0 
 * @Date:        2016-11-22 18:36:28 
 */
package hry.exchange.subscription.service.impl;

import hry.account.fund.model.AppAccount;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.subscription.dao.ExSubscriptionPlanDao;
import hry.exchange.subscription.dao.ExSubscriptionRecordDao;
import hry.exchange.subscription.model.ExBuybackRecord;
import hry.exchange.subscription.model.ExSubscriptionPlan;
import hry.exchange.subscription.model.ExSubscriptionRecord;
import hry.exchange.subscription.service.ExBuybackRecordService;
import hry.exchange.subscription.service.ExSubscriptionPlanService;
import hry.exchange.subscription.service.ExSubscriptionRecordService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * <p> ExSubscriptionRecordService </p>
 * @author:         zenghao
 * @Date :          2016-11-22 18:36:28  
 */
@Service("exSubscriptionRecordService")
public class ExSubscriptionRecordServiceImpl extends BaseServiceImpl<ExSubscriptionRecord, Long> implements ExSubscriptionRecordService{
	@Resource
	private ExSubscriptionPlanService  exSubscriptionPlanService;
	@Resource
	private ExDigitalmoneyAccountService  exDigitalmoneyAccountService;
	@Resource
	private ExBuybackRecordService  exBuybackRecordService;
	@Resource(name="exSubscriptionRecordDao")
	@Override
	public void setDao(BaseDao<ExSubscriptionRecord, Long> dao) {
		super.dao = dao;
	}
	/**
	 * 回购申请
	 */
	@Override
	public JsonResult saveBuyBack(String id,String buyBackNum) {
		// TODO Auto-generated method stub
		JsonResult jr = new JsonResult();
		ExSubscriptionRecord record = this.get(Long.valueOf(id));
		try {
			//得到币账户信息
			ExDigitalmoneyAccount account = exDigitalmoneyAccountService.getByCustomerIdAndType(record.getCustomerId(), record.getCoinCode(), ContextUtil.getCurrencyType(), ContextUtil.getWebsite());
				ExSubscriptionPlan plan = exSubscriptionPlanService.get(record.getPlanId());
				//状态是否小于等于1
				if(record.getState()<=1){
					//判断是否超过回购有效期
					if(plan.getRepurchasePeriod()!=null){
						Date newDate = DateUtil.addDaysToDate(record.getTime(), plan.getRepurchasePeriod());
						int result = DateUtil.compareDate(newDate, new Date(),0);
						if(result==-1){
							record.setState(3);
							super.update(record);
							jr.setSuccess(false);
							jr.setMsg("已经超过回购有效期，不能回购");
							return jr;
						}
					}
						//判断认购数量是否大于等于未回购数量
						if(record.getAmount().subtract(record.getBackAmount()).compareTo(new BigDecimal(buyBackNum))>=0){
							//判断账户币余额是否大于等于当前申请回购数量
							if(account.getHotMoney().compareTo(new BigDecimal(buyBackNum))>=0){
								//查询回购申请中的数量
								QueryFilter filter = new QueryFilter(ExBuybackRecord.class);
								filter.addFilter("customerId=", record.getCustomerId());
								filter.addFilter("state=", "0");
								filter.addFilter("recordId=", record.getId());
								List<ExBuybackRecord> buyRecord =exBuybackRecordService.find(filter);
								BigDecimal buyNum = new BigDecimal("0");
								for(ExBuybackRecord bbr:buyRecord){
									buyNum = buyNum.add(bbr.getBackAmount());
								}
								//最大可回购数量=  单笔认购数量- 单笔已回购数量- 单笔回购申请中数量 
								BigDecimal maxNum = record.getAmount().subtract(record.getBackAmount()).subtract(buyNum);
								if(maxNum.compareTo(new BigDecimal(buyBackNum))>=0){
									String[] relt=new String[2];  
									//生成回购记录
									exBuybackRecordService.saveBuybackRecord(record, new BigDecimal(buyBackNum));
									//冻结币账户 
									relt = exDigitalmoneyAccountService.freezeAccountSelf(account.getId(), new BigDecimal(buyBackNum),IdGenerate.transactionNum(NumConstant.App_Transaction), "回购申请",null,null);
									jr.setSuccess(true);
								}else{
									jr.setSuccess(false);
									jr.setMsg("最大可回购数量为："+maxNum.setScale(0));
								}
							}else{
								jr.setSuccess(false);
								jr.setMsg("账户币余额不足,不允许回购");
							}
						}else{
							record.setState(3);
							this.update(record);
							jr.setSuccess(false);
							jr.setMsg("当前申请回购数量不能超过未回购数量");
						}
				}else{
					jr.setSuccess(false);
					jr.setMsg("已经全部回购");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	@Override
	public List<ExSubscriptionRecord> sumCurrenNum(Long planId) {
		// TODO Auto-generated method stub
		ExSubscriptionRecordDao subDao = (ExSubscriptionRecordDao)dao;
		return subDao.sumCurrenNum(planId);
	}

	@Override
	public List<ExSubscriptionRecord> subTotalNum(Long planId,Long customerId) {
		// TODO Auto-generated method stub
		ExSubscriptionRecordDao subDao = (ExSubscriptionRecordDao)dao;
		return subDao.subTotalNum(planId,customerId);
	}

	@Override
	public JsonResult saveSubRecord(ExSubscriptionPlan plan,AppAccount appaccount,String currentPrice, String buyTotalNum,String transactionNum) {
		JsonResult jr = new JsonResult();
		ExSubscriptionRecord record = new ExSubscriptionRecord();
		//认购计划id
		record.setPlanId(plan.getId());
		//客户id
		record.setCustomerId(appaccount.getCustomerId());
		//账户名
		record.setUserName(appaccount.getUserName());
		//币种代码
		record.setCoinCode(plan.getCoinCode());
		//币种名称 
		record.setCoinName(plan.getCoinName());
		//认购期数
		record.setPeriod(plan.getPeriod());
		//认购时间
		record.setTime(new Date());
		//认购价格
		record.setPrice(new BigDecimal(currentPrice));
		//认购数量
		BigDecimal number = new BigDecimal(buyTotalNum).divide(new BigDecimal(currentPrice),4, RoundingMode.FLOOR);
		record.setAmount(number);
		//认购总金额
		record.setTotalMoney(new BigDecimal(buyTotalNum));
		//回购数量
		record.setBackAmount(new BigDecimal(0));
		//认购单号
		record.setTransactionNum(transactionNum);
		//状态
		record.setState(0);
		//回购手续费
		record.setRepurchaseFee(plan.getRepurchaseFee());
		//回购截止时间
		Date newDate = DateUtil.addDaysToDate(record.getTime(), plan.getRepurchasePeriod());
		record.setBackEndTime(newDate);
		this.save(record);
		//更新计划表 已认购数量和剩余认购数量
		plan.setPurchaseNumber(plan.getPurchaseNumber().add(number));
		plan.setSurplusNumber(plan.getOpenNumber().subtract(plan.getPurchaseNumber()));
		exSubscriptionPlanService.update(plan);
		jr.setSuccess(true);
		return jr;
	}

	@Override
	public List<ExSubscriptionRecord> remotePlanNum() {
		ExSubscriptionRecordDao subDao = (ExSubscriptionRecordDao)dao;
		return subDao.remotePlanNum();
	}
}
