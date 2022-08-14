/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 17:50:20 
 */
package hry.mall.integral.service.impl;

import javax.annotation.Resource;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.IntegralAccount;
import hry.mall.integral.model.IntegralDetail;
import hry.mall.integral.model.IntegralTaskMining;
import hry.mall.integral.service.CustomerIntegralService;
import hry.mall.integral.service.IntegralAccountService;
import hry.mall.integral.service.IntegralDetailService;
import hry.mall.integral.service.IntegralTaskMiningService;
import hry.mall.lend.person.model.AppPersonInfo;
import hry.mall.lend.person.service.AppPersonInfoService;
import hry.mall.member.user.model.AppCustomer;
import hry.mall.member.user.service.AppCustomerService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> IntegralTaskMiningService </p>
 * @author:         kongdb
 * @Date :          2019-01-07 17:50:20  
 */
@Service("integralTaskMiningService")
public class IntegralTaskMiningServiceImpl extends BaseServiceImpl<IntegralTaskMining, Long> implements IntegralTaskMiningService {
	
	@Resource(name="integralTaskMiningDao")
	@Override
	public void setDao(BaseDao<IntegralTaskMining, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private IntegralAccountService integralAccountService;
	@Resource
	private IntegralDetailService integralDetailService;
	@Resource
	private AppCustomerService appCustomerService;
	@Resource
	private CustomerIntegralService customerIntegralService;
	@Resource
	private AppPersonInfoService appPersonInfoService;
	@Resource
	private IntegralTaskMiningService taskMiningService;


	@Override
	public JsonResult performingTasks(Long memberId, String taskKey){

		JsonResult jsonResult = new JsonResult();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		try {
			//用户信息
			IntegralDetail integralDetail = new IntegralDetail();
			AppCustomer appCustomer = appCustomerService.get(memberId);
			//查询任务奖励账户的余额
			QueryFilter queryAccount =  new QueryFilter(IntegralAccount.class);
			queryAccount.addFilter("account_key=","taskAccount");
			IntegralAccount integralAccount = integralAccountService.get(queryAccount);
			if (null != integralAccount) {
				String orderSn = sdf.format(new Date());
				//1>根据任务key查询是单次任务还是循环任务
				QueryFilter queryTask =  new QueryFilter(IntegralTaskMining.class);
				queryTask.addFilter("taskKey=",taskKey);
				IntegralTaskMining taskMining = taskMiningService.get(queryTask);
				if (null != taskMining) {
					//任务是否开启状态
					Integer taskStatus = taskMining.getTaskStatus();
					if (1 == taskStatus) {
						Integer taskType = taskMining.getTaskType();
						//单次任务
						if (0 == taskType) {
							jsonResult = this.addTaskIntegralDetail(integralDetail, integralAccount, appCustomer, taskMining, orderSn);
							if (jsonResult.getSuccess()){
								jsonResult.setMsg("任务执行成功");//任务执行成功
							}
						}
					}else {
						jsonResult.setSuccess( false);
						jsonResult.setMsg("renwuweikaiqi");//任务未开启
						return jsonResult;
					}
				}else{
					jsonResult.setSuccess(false);
					jsonResult.setMsg("renwuweikaiqi");
				}
			}else {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("renwuzhanghuweipeizhi");//任务账户未配置
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setMsg("wangluoyichang");
		}

		return jsonResult;
	}




	public JsonResult addTaskIntegralDetail(IntegralDetail integralDetail,IntegralAccount integralAccount,
											AppCustomer appCustomer,IntegralTaskMining taskMining,String orderSn){
		JsonResult jsonResult = new JsonResult();
		try {
			BigDecimal computingCount = taskMining.getComputingCount();
			BigDecimal integralCount = taskMining.getIntegralCount();
			if (orderSn.equals("8888")){
				integralCount = taskMining.getAdditionalRewards();
			}
			//判断任务类型的余额是否大于应发余额
			BigDecimal reaminingTotal = integralAccount.getReamining_total();
			BigDecimal consumeTotal = integralAccount.getConsume_total();
			int compareTo = reaminingTotal.compareTo(integralCount);
			if(compareTo!=-1){
				//添加账户积分余额
				QueryFilter queryCusIntegral = new QueryFilter(CustomerIntegral.class);
				queryCusIntegral.addFilter("memberId=",appCustomer.getId());
				CustomerIntegral customerIntegral = customerIntegralService.get(queryCusIntegral);
				BigDecimal availableIntegral = customerIntegral.getAvailableIntegral().add(integralCount);
				BigDecimal totalIntegral = customerIntegral.getTotalIntegral().add(integralCount);
				//IntegralGrade integralGrade = integralGradeService.findGradeByIntegration(availableComputing.toString());
				integralAccount.setReamining_total(reaminingTotal.subtract(integralCount));
				integralAccount.setIssue_total(consumeTotal.subtract(integralAccount.getReamining_total()));
				integralAccountService.update(integralAccount);
				customerIntegral.setAvailableIntegral(availableIntegral);
				customerIntegral.setTotalIntegral(totalIntegral);
				customerIntegralService.update(customerIntegral);
				//添加积分算力明细
				integralDetail.setMemberId(appCustomer.getId());
				integralDetail.setAccountId(customerIntegral.getId());
				integralDetail.setRewardId(integralAccount.getId());
				//integralDetail.setComputingCount(appCustomer.getAvailableComputing());
				integralDetail.setIntegralCount( customerIntegral.getTotalIntegral());
				integralDetail.setRewardType(2);
				integralDetail.setRewardintegralCount(integralCount);
				integralDetail.setRewardComputingCount(computingCount);
				integralDetail.setRewardDate(new Date());
				integralDetail.setEstimateRewardDate(new Date());
				integralDetail.setFinishDate(new Date());
				integralDetail.setTaskName(taskMining.getTaskName());
				integralDetail.setTaskId(taskMining.getId());
				integralDetail.setDetailStatus(1);
				QueryFilter queryFilter = new QueryFilter(AppPersonInfo.class);
				queryFilter.addFilter("customerId=",appCustomer.getId());
				AppPersonInfo appPersonInfo = appPersonInfoService.get(queryFilter);
				if(appPersonInfo.getMobilePhone()== null || appPersonInfo.getMobilePhone().length()==0){
					integralDetail.setBuyerName(appPersonInfo.getEmail());
				}else {
					integralDetail.setBuyerName(appPersonInfo.getMobilePhone());
				}
				integralDetail.setMemberTruename(appPersonInfo.getTrueName());
				integralDetail.setCardNumber(appPersonInfo.getCardId());
				integralDetail.setRewardLevel("0级");
				integralDetail.setOrderSn(orderSn);
				integralDetail.setOrderMoney(taskMining.getIntegralCount());

				integralDetail.setBuyerId(appCustomer.getId());
				if(appPersonInfo.getMobilePhone()== null  || appPersonInfo.getMobilePhone().length()==0){
					integralDetail.setMemberName(appPersonInfo.getEmail());
				}else {
					integralDetail.setMemberName(appPersonInfo.getMobilePhone());
				}
				integralDetail.setTradingType(2);
				integralDetail.setTradingDetail(taskMining.getTaskName());
				integralDetailService.save(integralDetail);
				jsonResult.setSuccess(true);
				jsonResult.setMsg("添加明细成功");
			}else {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("账户余额不足");
			}
		}catch (Exception e){
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setMsg("服务器错误");
		}
		return jsonResult;

	}

}
