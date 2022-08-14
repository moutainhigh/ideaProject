package hry.mall.integral.remote;

import java.math.BigDecimal;
import java.util.*;
import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.common.util.DateUtils;
import hry.mall.integral.model.*;

import hry.mall.integral.remote.model.CustomerIntegralVo;
import hry.mall.integral.service.*;
import hry.util.BeanToBean;
import hry.util.QueryFilter;

import hry.util.date.DateUtil;
import org.springframework.util.StringUtils;

public class RemoteIntegralConfigServiceImpl implements RemoteIntegralConfigService {
	
	@Resource
	public IntegralConfigService integralConfigService;

	@Resource
	private CustomerIntegralService customerIntegralService;

	@Resource
	private IntegralTaskMiningService integralTaskMiningService;

	@Resource
	private IntegralDetailService integralDetailService;

	@Resource
	private IntegralLevelService integralLevelService;

	@Override
	public String findIntegralCode() {
		// TODO Auto-generated method stub
		String code="";
	      List<IntegralConfig> clist=integralConfigService.findAll();
	        if(null!=clist && clist.size()>0){
	        	IntegralConfig config=clist.get(0);
	        	code=config.getIntegralCode();
	        }
	        return code;
	}

	@Override
	public void saveCustomerIntegral(Map<String, Object> params) {
		CustomerIntegral customerIntegral = new CustomerIntegral();
		if (!StringUtils.isEmpty(params.get("memberId"))){
			//用户Id
			Long memberId = Long.parseLong(params.get("memberId").toString());
			customerIntegral.setMemberId(memberId);
		}

		if (!StringUtils.isEmpty(params.get("integralType"))) {
			//积分类型
			String integralType = params.get("integralType").toString();
			customerIntegral.setIntegralType(integralType);
		}

		if (!StringUtils.isEmpty(params.get("integralName"))) {
			//积分名称
            String integralName = params.get("integralName").toString();
            customerIntegral.setIntegralName(integralName);
		}

		if (!StringUtils.isEmpty(params.get("integralPrice"))) {
			//积分价值
            BigDecimal integralPrice = new BigDecimal(params.get("integralPrice").toString());
            customerIntegral.setIntegralPrice(integralPrice);
		}

		if (!StringUtils.isEmpty(params.get("totalIntegral"))) {
			//总积分值
            BigDecimal totalIntegral = new BigDecimal(params.get("totalIntegral").toString());
            customerIntegral.setTotalIntegral(totalIntegral);
		}

		if (!StringUtils.isEmpty(params.get("availableIntegral"))) {
			//可用积分值
            BigDecimal availableIntegral = new BigDecimal(params.get("availableIntegral").toString());
            customerIntegral.setAvailableIntegral(availableIntegral);
		}
		if (!StringUtils.isEmpty(params.get("freezeIntegral"))) {
			//冻结积分值
            BigDecimal freezeIntegral = new BigDecimal(params.get("freezeIntegral").toString());
            customerIntegral.setFreezeIntegral(freezeIntegral);
        }
		if (!StringUtils.isEmpty(params.get("remark"))) {
			//备注
            String remark = params.get("remark").toString();
            customerIntegral.setRemark(remark);
		}
        customerIntegralService.save(customerIntegral);
	}

	@Override
	public BigDecimal getCustomerIntegral(long memberId) {
		//查询积分币值
		QueryFilter queryFilter =new QueryFilter(CustomerIntegral.class);
		queryFilter.addFilter("memberId=",memberId);
		CustomerIntegral customerIntegral = customerIntegralService.get(queryFilter);
		//查询积分币币值
		BigDecimal coinPrice = new BigDecimal(0);
		List<IntegralConfig> clist=integralConfigService.findAll();
		if(null!=clist && clist.size()>0){
			IntegralConfig config=clist.get(0);
			coinPrice=config.getCoinPrice();
		}
		BigDecimal cnyIntegral = coinPrice.multiply(customerIntegral.getTotalIntegral());
		return cnyIntegral;
	}

	@Override
	public CustomerIntegralVo findCustomerIntegral(Map<String, String> map) {
		// TODO Auto-generated method stub
		String memberId=map.get("memberId");
		String integralType=map.get("integralType");
		QueryFilter filter=new QueryFilter(CustomerIntegral.class);
		filter.addFilter("memberId=", Long.valueOf(memberId));
		filter.addFilter("integralType=", integralType);
		filter.setOrderby("id desc");
		List<CustomerIntegral> list=customerIntegralService.find(filter);
		if(null!=list && list.size()>0){
			CustomerIntegral i=list.get(0);
			CustomerIntegralVo vo=new CustomerIntegralVo();
			vo=(CustomerIntegralVo) BeanToBean.convertBean2Bean(i, vo);
			return vo;
		}
		return null;
	}

	/**
	 * 用户签到操作
	 * */
	@Override
	public JsonResult userHandleSign(Map<String, String> map) {
		JsonResult jsonResult = new JsonResult();
		// 初始化用户签到列表
		JSONObject result = initUserSignList(map);
		if ((boolean)result.get("success")) {
			result.remove("success");
			// 校验今天是否已签到
			JSONArray array = JSONArray.parseArray(result.get("signList").toString());
			String day = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			for (int i = 0; i < array.size(); i++) {
				if (array.getJSONObject(i).get("date").equals(day)) {
					JSONObject object = array.getJSONObject(i);
					if ("1".equals(object.get("isSign").toString())) {
						return jsonResult.setSuccess(false).setMsg("您今天已完成签到！").setCode("1001");
					}
				}
			}
			// 签到奖励
			BigDecimal dayAward = new BigDecimal(result.getString("dayAward"));
			// 判断签到是否获取额外奖励
			BigDecimal extraAward = new BigDecimal("0");
			String today = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			// 获取最后日期
			JSONObject lastObject = array.getJSONObject(array.size() - 1);
			if (today.equals(lastObject.get("date").toString())) {
				extraAward = new BigDecimal(result.getString("extraAward"));
			}
			// 执行签到操作
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("integralCount", dayAward.add(extraAward).toString());
			paramMap.put("computingCount", "0");
			paramMap.put("taskName", map.get("taskName"));
			paramMap.put("taskId", map.get("taskId"));
			paramMap.put("type", "2");
			paramMap.put("memberId", map.get("memberId"));
			paramMap.put("taskKey", "dailyAttendance");
			jsonResult = integralDetailService.issueIntegral(paramMap);
			if (jsonResult.getSuccess()) {
				return new JsonResult(true).setMsg("签到成功");
			} else {
				return new JsonResult(false).setMsg("签到失败").setCode("1002");
			}
		} else {
			return jsonResult.setSuccess(false).setMsg("每日签到任务配置错误").setObj("1000");
		}
	}

	/**
	 * 用户签到记录
	 * */
	@Override
	public JsonResult findSignInList(Map<String, String> map) {
		JSONObject result = initUserSignList(map);
		if ((boolean)result.get("success")) {
			result.remove("success");
			return new JsonResult(true).setObj(result);
		} else {
			return new JsonResult(false).setMsg("每日签到任务配置错误").setObj("1000");
		}
	}

	/**
	 * 初始化用户签到记录
	 * */
	private JSONObject initUserSignList (Map<String, String> map) {
		JSONObject resultObj = new JSONObject();
		// 查询是否配置每日签到
		QueryFilter filter = new QueryFilter(IntegralTaskMining.class);
		// taskType 0 单次任务 1 循环任务
		filter.addFilter("taskType = ",1);
		filter.addFilter("taskKey = ", "dailyAttendance");
		filter.addFilter("isDel = ",0);
		filter.addFilter("taskStatus = ",1);
		IntegralTaskMining task = integralTaskMiningService.find(filter).get(0);
		if (task != null && task.getAccumulativeTimes() > 0) {
			// 业务需要返回任务参数
			map.put("taskName", task.getTaskName());
			map.put("taskId", task.getId().toString());

			// 预计累计次数
			int number = task.getAccumulativeTimes();
			// 已经签到天数
			int alreadySignDay = 0;
			// 获取用户的签到记录
			QueryFilter signFilter = new QueryFilter(IntegralDetail.class);
			signFilter.addFilter("memberId =",map.get("memberId"));
			signFilter.addFilter("taskId =",task.getId());
			signFilter.addFilter("created <=", DateUtils.getDateAddDays(new Date(),-1,DateUtils.DEFAULT_FORMAT_YYYY_MM_DD) + "23:59:59");
			signFilter.addFilter("created >=", DateUtils.getDateAddDays(new Date(),-7,DateUtils.DEFAULT_FORMAT_YYYY_MM_DD) + "00:00:00");
			signFilter.setOrderby("created DESC");
			List<IntegralDetail> detailList = integralDetailService.find(signFilter);
			// 获取重昨天开始连续的集合
			// 签到中断条件 1、昨天无签到记录
			//            2、签到已满一个轮回
			if (detailList != null && detailList.size() > 0) {
				IntegralDetail detail = detailList.get(0);
				String dateStr = DateUtil.dateToString(detail.getCreated(),"yyyy-MM-dd");
				String yesterday = DateUtils.getDateAddDays(new Date(),-1,DateUtils.DEFAULT_FORMAT_YYYY_MM_DD);
				if (dateStr.equals(yesterday) && detailList.size() != number) {
					// 获取连续签到记录
					getContinuousList(detailList);
					alreadySignDay = detailList.size();
				} else {
					// 签到中断、重新签到
					detailList = new ArrayList<>();
				}
			} else {
				// 签到中断、重新签到
				detailList = new ArrayList<>();
			}
			// 获取当前天签到记录
			QueryFilter nowSignFilter = new QueryFilter(IntegralDetail.class);
			nowSignFilter.addFilter("memberId =",map.get("memberId"));
			nowSignFilter.addFilter("taskId =",task.getId());
			nowSignFilter.addFilter("created <=", DateUtil.dateToString(new Date(), "yyyy-MM-dd 23:59:59"));
			nowSignFilter.addFilter("created >=", DateUtil.dateToString(new Date(), "yyyy-MM-dd 00:00:00"));
			nowSignFilter.setOrderby("created DESC");
			List<IntegralDetail> details = integralDetailService.find(nowSignFilter);
			if (details != null && details.size() > 0) {
				IntegralDetail detail = details.get(0);
				detailList.add(0,detail);
				alreadySignDay ++;
			}

			JSONArray jsonArray = findSignCycle(detailList,number-detailList.size(),task.getIntegralCount());
			;
			resultObj.put("success", true);
			// 用户签到记录
			resultObj.put("signList", jsonArray);
			// 额外奖励需连签天数
			resultObj.put("wantSignDay", number);
			// 已经签到天数
			resultObj.put("alreadySignDay", alreadySignDay);
			// 日签到奖励
			resultObj.put("dayAward", task.getIntegralCount());
			// 连续签到额外奖励
			resultObj.put("extraAward",task.getAdditionalRewards());
			return resultObj;
		} else {
			resultObj.put("success", false);
			resultObj.put("msg","每日签到任务配置错误");
			return resultObj;
		}
	}

	/**
	 * 获取连续签到记录集合
	 * */
	private void getContinuousList(List<IntegralDetail> detailList) {
		if (detailList.size() >= 1) {
			int allNumber = detailList.size();
			for (int i = 1; i < detailList.size(); i++) {
				IntegralDetail detail = detailList.get(i);
				String dateStr = DateUtil.dateToString(detail.getCreated(),"yyyy-MM-dd");
				String yesterday = DateUtils.getDateAddDays(new Date(),-i-1,DateUtils.DEFAULT_FORMAT_YYYY_MM_DD);
				if (!dateStr.equals(yesterday)) {
					// 中断删掉后面所有
					for (int j = i; j < allNumber; j++) {
						detailList.remove(i);
					}
					break;
				}
			}
		}
	}

	/**
	 * 补充签到周期
	 * @param detailList 已签到记录
	 * @param number 补充条数
	 * @param dayAward 日签到奖励
	 * @param addAward 额外签到奖励
	 * */
	private JSONArray findSignCycle(List<IntegralDetail> detailList, int number, BigDecimal dayAward) {
		JSONArray array = new JSONArray();
		// date 日期；isSign 是否签到（0：未签到 1：已签到）； dayAward 签到奖励
		if (detailList != null) {
			for (int i = detailList.size()-1; i >= 0; i--) {
				JSONObject obj = new JSONObject();
				obj.put("date", DateUtil.dateToString(detailList.get(i).getCreated(),"yyyy-MM-dd"));
				obj.put("isSign", 1);
				obj.put("dayAward", dayAward);
				array.add(obj);
			}
		}
		// 补齐
		for (int j = 0; j < number; j++) {
			JSONObject obj = new JSONObject();
			if (detailList.size() > 0) {
				obj.put("date", DateUtils.getDateAddDays(detailList.get(0).getCreated(),j+1,DateUtils.DEFAULT_FORMAT_YYYY_MM_DD));
			} else {
				obj.put("date", DateUtils.getDateAddDays(new Date(),j,DateUtils.DEFAULT_FORMAT_YYYY_MM_DD));
			}
			obj.put("isSign", 0);
			obj.put("dayAward", dayAward);
			array.add(obj);
		}
		return array;
	}


	public static void main(String[] args) {
		System.out.println("++++++"+ DateUtils.getDateAddDays(new Date(),0,DateUtils.DEFAULT_FORMAT_YYYY_MM_DD));
		System.out.println("++++++++"+DateUtil.dateToString(new Date(), "yyyy-MM-dd"));


	}



	@Override
	public ApiJsonResult getUserRecommendedNumber(Long customerId, Integer series){
		return integralConfigService.getUserRecommendedNumber(customerId,series);
	}

	@Override
	public void saveCustomerIntegral2(Map<String, Object> params) {
		// TODO Auto-generated method stub

		CustomerIntegral customerIntegral = new CustomerIntegral();
		if (!StringUtils.isEmpty(params.get("memberId"))){
			//用户Id
			Long memberId = Long.parseLong(params.get("memberId").toString());
			QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
			queryFilter.addFilter("memberId=",memberId);
			List<CustomerIntegral> customerIntegrals = customerIntegralService.find(queryFilter);
			if(customerIntegrals !=null &&customerIntegrals.size()>0){
				System.out.println("用户id为："+memberId+"已有账户");
				customerIntegral=customerIntegrals.get(0);
			}
			customerIntegral.setMemberId(memberId);

			if (!StringUtils.isEmpty(params.get("integralType"))) {
				//积分类型
				String integralType = params.get("integralType").toString();
				customerIntegral.setIntegralType(integralType);
			}

			if (!StringUtils.isEmpty(params.get("integralName"))) {
				//积分名称
				String integralName = params.get("integralName").toString();
				customerIntegral.setIntegralName(integralName);
			}

			if (!StringUtils.isEmpty(params.get("integralPrice"))) {
				//积分价值
				BigDecimal integralPrice = new BigDecimal(params.get("integralPrice").toString());
				customerIntegral.setIntegralPrice(integralPrice);
			}

			if (!StringUtils.isEmpty(params.get("totalIntegral"))) {
				//总积分值
				BigDecimal totalIntegral = new BigDecimal(params.get("totalIntegral").toString());
				customerIntegral.setTotalIntegral(totalIntegral);
			}

			if (!StringUtils.isEmpty(params.get("availableIntegral"))) {
				//可用积分值
				BigDecimal availableIntegral = new BigDecimal(params.get("availableIntegral").toString());
				customerIntegral.setAvailableIntegral(availableIntegral);

			}
			if (!StringUtils.isEmpty(params.get("freezeIntegral"))) {
				//冻结积分值
				BigDecimal freezeIntegral = new BigDecimal(params.get("freezeIntegral").toString());
				customerIntegral.setFreezeIntegral(freezeIntegral);
			}
			if (!StringUtils.isEmpty(params.get("remark"))) {
				//备注
				String remark = params.get("remark").toString();
				customerIntegral.setRemark(remark);
			}

			/*----为账户添加积分等级及相关额度---*///胡一茗二期--会员等级只能购买，不初始化   //又改为初始化了
			QueryFilter filter = new QueryFilter(IntegralLevel.class);
			filter.addFilter("number=", 2);
			filter.setOrderby("number asc");
			List<IntegralLevel> integralLevelList = integralLevelService.find(filter);
			if (integralLevelList != null && integralLevelList.size() > 0){
				IntegralLevel integralLevel = integralLevelList.get(0); //取最小等级为默认等级
				customerIntegral.setLevelId(integralLevel.getId());
				customerIntegral.setLevelNumber(integralLevel.getNumber());
				customerIntegral.setTotalQuota(integralLevel.getBaseQuota());
				customerIntegral.setBaseQuota(integralLevel.getBaseQuota());
				customerIntegral.setRecommendQuota(new BigDecimal("0"));
				customerIntegral.setMaxQuota(integralLevel.getMaxQuota());
				customerIntegral.setStartDate(new Date());
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, integralLevel.getValidityPeriod());
				Date time = calendar.getTime();
				customerIntegral.setEndDate(time);
				Date date = DateUtil.addDay(new Date(), 1);
				customerIntegral.setDistributeDate(date);
			}

			if(customerIntegrals !=null &&customerIntegrals.size()>0){
				customerIntegralService.update(customerIntegral);
			}else{
				customerIntegralService.save(customerIntegral);
			}

		}

	}

}
