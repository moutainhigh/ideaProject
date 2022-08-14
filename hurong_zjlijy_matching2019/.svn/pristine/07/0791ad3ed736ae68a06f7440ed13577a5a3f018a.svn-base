package hry.mall.integral.remote;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.common.util.DateUtils;
import hry.core.shiro.PasswordHelper;
import hry.mall.integral.model.*;
import hry.mall.integral.remote.model.IntegralDetailVo;
import hry.mall.integral.service.*;
import hry.mall.lend.person.model.AppPersonInfo;
import hry.mall.lend.person.service.AppPersonInfoService;
import hry.mall.member.commend.model.AppCommendUser;
import hry.mall.member.commend.service.AppCommendUserService;
import hry.mall.member.user.model.AppCustomer;
import hry.mall.member.user.service.AppCustomerService;
import hry.mall.order.model.Order;
import hry.mall.order.service.OrderService;
import hry.manage.remote.RemoteMallService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RemoteIntegralDetailServiceImpl implements RemoteIntegralDetailService {

	@Resource
	private IntegralAccountService integralAccountService;
	@Resource
	private IntegralDetailService integralDetailService;
	@Resource
	private IntegralRebateConfigService integralRebateConfigService;
	@Resource
	private IntegralConfigService integralConfigService;
	@Resource
	private IntegralTaskMiningService integralTaskMiningService;
	@Resource
	private IntegralLevelService integralLevelService;
	@Resource
	private OrderService orderService;
	@Resource
	private CustomerIntegralService customerIntegralService;
	@Resource(name="transactionManager")
	private DataSourceTransactionManager transactionManager;
	@Resource
	private AppCommendUserService appCommendUserService;
	@Resource
	private AppCustomerService appCustomerService;
	@Resource
	private AppPersonInfoService appPersonInfoService;
	@Resource
	private IntegralTransferOrderService integralTransferOrderService;

    @Override
    public JsonResult integralDetails(long memberId) {
		JsonResult jsonResult = new JsonResult();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<>();
		QueryFilter queryCustomerInt = new QueryFilter(CustomerIntegral.class);
		queryCustomerInt.addFilter("memberId=",memberId);
		CustomerIntegral customerIntegral = customerIntegralService.get(queryCustomerInt);
		if(null != customerIntegral){
			BigDecimal availableIntegral = customerIntegral.getAvailableIntegral();
			BigDecimal freezeIntegral = customerIntegral.getFreezeIntegral();
			BigDecimal totalIntegral = customerIntegral.getTotalIntegral();
			//查询平台币
			//1、查询比账户
			String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
			ExDigitalmoneyAccountRedis dmAccount=UserRedisUtils.getAccountRedis(String.valueOf(memberId), platCoin);

			map.put("totalIntegral",dmAccount.getColdMoney().add(dmAccount.getHotMoney()));
			map.put("freezeIntegral",dmAccount.getColdMoney());
			map.put("availableIntegral",dmAccount.getHotMoney());
			// 新添额度相关字段字段
			map.put("baseQuota", customerIntegral.getBaseQuota()); // 基础额度
			map.put("recommendQuota", customerIntegral.getRecommendQuota()); // 推广额度
			map.put("totalQuota", customerIntegral.getTotalQuota()); // 总额度
			map.put("maxQuota", customerIntegral.getMaxQuota()); // 额度上限
			// 用户等级名称
			IntegralLevel level = integralLevelService.get(customerIntegral.getLevelId());
			if (level != null) {
				map.put("levelName", level.getName()); // 等级名称
				map.put("levelImg", level.getImage()); // 等级图片
			}
			IntegralConfig integralConfig = integralConfigService.get((long) 1);
			//计算积分币商城
			BigDecimal coinPrice = integralConfig.getCoinPrice();
			//约等于多少人民币
			BigDecimal RMBTotalIntegral = totalIntegral.multiply(coinPrice);
			map.put("RMBTotalIntegral",RMBTotalIntegral);
			hry.mall.member.commend.model.AppCommendUser appCommendUser = appCommendUserService.findPageBySqlIntegral(memberId);
			//购物挖矿总值
			BigDecimal shopCount = appCommendUser.getShopCount();
		/*	QueryFilter queryCommendUser = new QueryFilter(AppCommendUser.class);
			queryCommendUser.addFilter("sid_like","%"+memberId+"%");
			List<AppCommendUser> appCommendUsers = appCommendUserService.find(queryCommendUser);*/

			//团队总人数
			//	int allNumber = appCommendUsers.size();
			int allNumber = appCommendUserService.findUserCount(memberId);
			map.put("shopCount",shopCount);
			map.put("allNumber",allNumber);
			// 本日新增推荐人数
			String todaySta = DateUtils.getDateStr(new Date(), "yyyy-MM-dd") + " 00:00:00";
			String todayEnd = DateUtils.getDateStr(new Date(), "yyyy-MM-dd") + " 23:59:59";
			int todayNumber = appCommendUserService.findDateUserCount(todaySta, todayEnd, memberId);
			map.put("todayNumber", todayNumber);

			// 本月新增推荐人数
			String monthSta = DateUtils.firstDayOfCurrentMonth("yyyy-MM-dd") + " 00:00:00";
			String monthEnd = DateUtils.lastDayOfCurrentMonth("yyyy-MM-dd") + " 23:59:59";
			int monthNumber = appCommendUserService.findDateUserCount(monthSta, monthEnd, memberId);
			map.put("monthNumber", monthNumber);

			//查询购物今日新增
			Map<String,Object> hashMap = new HashMap<>();
			QueryFilter queryFilter = new QueryFilter(IntegralTaskMining.class);
			queryFilter.addFilter("taskKey=","shopKey");
			IntegralTaskMining integralTaskMining = integralTaskMiningService.get(queryFilter);
			hashMap.put("ShopKeyId",integralTaskMining.getId());
			QueryFilter query = new QueryFilter(IntegralTaskMining.class);
			query.addFilter("taskKey=","recommendShopKey");
			IntegralTaskMining taskMining = integralTaskMiningService.get(query);
			hashMap.put("recommendShopKeyId",taskMining.getId());
			hashMap.put("memberId",memberId);
			String dateStr = DateUtils.getDateStr(new Date(), "yyyy-MM-dd");
			hashMap.put("todayTime", dateStr+" 00:00:00");
			hashMap.put("todayEndTime", dateStr+" 23:59:59");
			hashMap.put("monthTime", DateUtils.firstDayOfCurrentMonth("yyyy-MM-dd HH:mm:ss"));
			hashMap.put("monthEndTime", DateUtils.lastDayOfCurrentMonth("yyyy-MM-dd") + " 23:59:59");
			Map<String, Object> map1 = integralDetailService.findbyKey(hashMap);

			if (null != map1 && map1.size() > 0) {
				map.put("todayShopIntegral",map1.get("todayShopIntegral"));
				map.put("monthShopIntegral",map1.get("monthShopIntegral"));
				map.put("todayTeamIntegral",map1.get("todayTeamIntegral"));
				map.put("monthTeamIntegral",map1.get("monthTeamIntegral"));
			} else {
				map.put("todayShopIntegral", "0");
				map.put("monthShopIntegral", "0");
				map.put("todayTeamIntegral", "0");
				map.put("monthTeamIntegral", "0");
			}
			return  jsonResult.setSuccess(true).setObj(map);
		}

		return jsonResult.setSuccess(false).setObj(map);
	}

    @Override
    public FrontPage tradingRecord(Map<String,String> map) {
		Page<IntegralDetail> page = PageFactory.getPage(map);
        QueryFilter queryFilter = new QueryFilter(IntegralDetail.class);
        queryFilter.addFilter("memberId=",map.get("id"));
        queryFilter.addFilter("detailStatus=",1);
        List<IntegralDetail> integralDetails = integralDetailService.find(queryFilter);
        IntegralConfig integralConfig = integralConfigService.get((long) 1);
        String integralCode = integralConfig.getIntegralCode();
		for (int i = 0; i <integralDetails.size() ; i++) {
			integralDetails.get(i).setIntegralCode(integralCode);
		}
        return new FrontPage(integralDetails, page.getTotal(), page.getPages(), page.getPageSize());
    }
    @Override
    public FrontPage frozenRecord(Map<String,String> map) {
		Page<IntegralDetail> page = PageFactory.getPage(map);
        QueryFilter queryFilter = new QueryFilter(IntegralDetail.class);
        queryFilter.addFilter("memberId=",map.get("id"));
        queryFilter.addFilter("detailStatus=",0);
        List<IntegralDetail> integralDetails = integralDetailService.find(queryFilter);
        return new FrontPage(integralDetails, page.getTotal(), page.getPages(), page.getPageSize());
    }

	@Override
	public JsonResult addIntegralDetail(long orderId, long memberId, String accountKey, String taskKey, Integer rewardType) {
        System.out.println("=======================================进入积分发放页面购物orderId="+orderId);
    	integralDetailService.addIntegralDetail1(orderId, memberId, accountKey, taskKey, rewardType);
    	return new JsonResult().setSuccess(true).setMsg("积分发放成功");
	}


	@Override
	public JsonResult toAccounts(String customerId,String amount, String mobilePhone, String accountPwd) {
		JsonResult result = new JsonResult();
		QueryFilter filter = new QueryFilter(AppCustomer.class);
		filter.addFilter("id=",customerId);
		AppCustomer appCustomer = appCustomerService.get(filter);
		QueryFilter filter4 = new QueryFilter(AppPersonInfo.class);
		filter4.addFilter("customerId=",appCustomer.getId());
		AppPersonInfo appPersonInfo = appPersonInfoService.get(filter4);
		//如果支付密码密码为空，则提示用户去维护支付密码
		if(null==appCustomer.getAccountPassWord() || "".equals(appCustomer.getAccountPassWord())){
			result.setSuccess(false).setMsg("zhifumimakong"); //支付密码为空,请先去维护支付密码
			return result;
		}
		//校验支付密码是否正确
		PasswordHelper passwordHelper = new PasswordHelper();
		String encryString = passwordHelper.encryString(accountPwd, appCustomer.getSalt());
		if(!encryString.equals(appCustomer.getAccountPassWord())){
			result.setSuccess(false).setMsg("zhifumimacuowu"); //支付密码错误
			return result;
		}

		//校验转让数量是否大于0
		if(new BigDecimal(amount).compareTo(new BigDecimal(0))<=0){
			result.setSuccess(false).setMsg("zrslbunengweiling"); //转让数量需大于0
			return result;
		}
		//判断用户的转让金额是否符合设置要求
		JsonResult transfer = integralTransferOrderService.isTransfer(Long.valueOf(customerId), new BigDecimal(amount));
		if(!transfer.getSuccess()){
			return transfer;
		}

		//用户余额是否充足
		QueryFilter filter1 = new QueryFilter(CustomerIntegral.class);
		filter1.addFilter("memberId=",appCustomer.getId());
		CustomerIntegral customerIntegral = customerIntegralService.get(filter1);
		//用户余额需大于等于要转出的
		if(customerIntegral.getAvailableIntegral().compareTo(new BigDecimal(amount))<0){
			result.setSuccess(false).setMsg("dianziquanyuebuzu"); //电子券余额不足
			return result;
		}

		//判断用户是否有使用额度
		boolean isFlag = integralDetailService.getThisMonthRoll(customerId,new BigDecimal(amount));
		if(!isFlag){
			result.setSuccess(false).setMsg("dzqycgshiyongxianzhi"); //电子券使用超出限额
			return result;
		}

		//查询收券人是否存在
		QueryFilter apfilter = new QueryFilter(AppPersonInfo.class);
		apfilter.addFilter("mobilePhone=",mobilePhone);
		AppPersonInfo jsAppPersonInfo = appPersonInfoService.get(apfilter);
		if(jsAppPersonInfo == null ) {
			result.setSuccess(false).setMsg("shouquanrenbucunzai"); //收券人不存在
			return result;
		}
		QueryFilter filter2 = new QueryFilter(AppPersonInfo.class);
		filter2.addFilter("id=", jsAppPersonInfo.getCustomerId());
		//查询用户表
		AppCustomer jsAppCustomer = appCustomerService.get(filter2);
		if(jsAppCustomer == null){
			result.setSuccess(false).setMsg("shouquanrenbucunzai"); //收券人不存在
			return result;
		}
		QueryFilter filter3 = new QueryFilter(CustomerIntegral.class);
		filter3.addFilter("memberId=", jsAppCustomer.getId());
		CustomerIntegral jsCustomerIntegral = customerIntegralService.get(filter3);


		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
		try {
			//手续费
			BigDecimal feeAmount = this.getFee(Long.valueOf(customerId),amount);
			String requestNo = SNUtil.create15();

			//保存转让订单
			IntegralTransferOrder transferOrder = new IntegralTransferOrder();
			transferOrder.setTransferMemberId(appCustomer.getId());
			transferOrder.setTransferMobilePhone(appPersonInfo.getMobilePhone());
			transferOrder.setAmount(new BigDecimal(amount));
			transferOrder.setActualAmount(new BigDecimal(amount).subtract(feeAmount));
			transferOrder.setFeeAmount(feeAmount);
			transferOrder.setReceiveMemberId(jsAppPersonInfo.getCustomerId());
			transferOrder.setReceiveMobilePhone(jsAppPersonInfo.getMobilePhone());
			transferOrder.setReceiveAmount(new BigDecimal(amount).subtract(feeAmount));
			transferOrder.setRequestNo(requestNo);
			transferOrder.setState(2);//成功
			integralTransferOrderService.save(transferOrder);


			//转让人减
			Map<String,String> map1 = new HashMap<>();
			map1.put("id",customerIntegral.getId().toString());
			map1.put("type",CustomerIntegral.TYPE_SUB);
			map1.put("integralCount",new BigDecimal(amount).subtract(feeAmount).toString());
			map1.put("tradingDetail","【转】"+mobilePhone);
			map1.put("businessType","3");//转出
			map1.put("requestNo",requestNo);//流水号
			boolean flag1 = customerIntegralService.updateInteger(map1);
			if(!flag1){
				transactionManager.rollback(status);
				result.setSuccess(false).setMsg("zhuanrangshibai"); //转让失败
				return result;
			}

			//转让人减(手续费)
			Map<String,String> map3 = new HashMap<>();
			map3.put("id",customerIntegral.getId().toString());
			map3.put("type",CustomerIntegral.TYPE_SUB);
			map3.put("integralCount",feeAmount.toString());
			map3.put("tradingDetail","【转：手续费】"+mobilePhone);
			map3.put("businessType","5");//转出
			map3.put("requestNo",requestNo);//流水号
			boolean flag3 = customerIntegralService.updateInteger(map3);
			if(!flag3){
				transactionManager.rollback(status);
				result.setSuccess(false).setMsg("zhuanrangshibai"); //转让失败
				return result;
			}

			//接收人加
			Map<String,String> map2 = new HashMap<>();
			map2.put("id",jsCustomerIntegral.getId().toString());
			map2.put("type",CustomerIntegral.TYPE_ADD);
			map2.put("integralCount",new BigDecimal(amount).subtract(feeAmount).toString());
			map2.put("tradingDetail","【收】"+appPersonInfo.getMobilePhone());
			map2.put("businessType","4");//转入
			map2.put("requestNo",requestNo);//流水号
			boolean flag2 = customerIntegralService.updateInteger(map2);
			if(!flag2){
				transactionManager.rollback(status);
				result.setSuccess(false).setMsg("zhuanrangshibai"); //转让失败
				return result;
			}
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			result.setSuccess(false).setMsg("zhuanrangshibai"); //转让失败
			return result;
		}
		result.setSuccess(true).setMsg("zhuanrangchenggong"); //转让成功
		return result;
	}

	@Override
	public BigDecimal getFee(Long memberId,String amount) {
		QueryFilter filter5 = new QueryFilter(CustomerIntegral.class);
		filter5.addFilter("memberId=",memberId);
		CustomerIntegral customerIntegral1 = customerIntegralService.get(filter5);

		QueryFilter filter6 = new QueryFilter(IntegralLevel.class);
		filter6.addFilter("id=",customerIntegral1.getLevelId());
		IntegralLevel integralLevel = integralLevelService.get(filter6);

		BigDecimal feeRate = integralLevel.getFeeRate().divide(new BigDecimal(100));//转让手续费费率
		//计算手续费并四舍五入 保留两位小数
		BigDecimal feeAmount = new BigDecimal(amount).multiply(feeRate).setScale(2,BigDecimal.ROUND_HALF_UP);
		return feeAmount;
	}


	@Override
	public JsonResult addIntegralDetai(IntegralDetailVo integralDetailVo) {
		// TODO Auto-generated method stub
		IntegralDetail detail=new IntegralDetail();
		detail=(IntegralDetail) BeanToBean.convertBean2Bean(integralDetailVo, detail);
		IntegralAccount account=null;
		//积分兑换，则查询积分回收账户
		if(null!=detail.getRewardType() && 4==detail.getRewardType()){
			QueryFilter filter = new QueryFilter(IntegralAccount.class);
			filter.addFilter("account_key=", "integralRecovery");
			List<IntegralAccount> list=integralAccountService.find(filter);
			if(null!=list && list.size()>0){
			    account=list.get(0);
				detail.setRewardId(account.getId());//积分账户id
			}
		}
		detail.setRewardDate(new Date());
		detail.setFinishDate(new Date());
		detail.setEstimateRewardDate(new Date());
		detail.setDetailStatus(1);//已发放
		integralDetailService.save(detail);
		//2、更改用户的积分账户
		this.handAccout(detail);
		//3、更改积分账户数量
		if(null!=account && !"".equals(account)){
			account.setReamining_total(account.getReamining_total().add(detail.getCallbackintegralCount()));
			integralAccountService.update(account);
		}
		return new JsonResult().setSuccess(true).setMsg("处理成功");
	}
	/**
	 * 根据明细操作积分账户
	 * @param detail
	 */
	public void handAccout(IntegralDetail  detail){
		CustomerIntegral integral=	customerIntegralService.get(detail.getAccountId());
		//积分购物，则减掉相应积分
		if(4==detail.getRewardType()){
			integral.setAvailableIntegral(integral.getAvailableIntegral().subtract(detail.getCallbackintegralCount()));
			integral.setTotalIntegral(integral.getTotalIntegral().subtract(detail.getCallbackintegralCount()));
			customerIntegralService.update(integral);
		}
	}

	@Override
	public JsonResult performingTaskRecommendKey(Long memberId,String taskKey){
		System.out.println("进入发放推荐注册奖励方法");
		QueryFilter queryFilter = new QueryFilter(AppCommendUser.class);
		queryFilter.addFilter("uId=",memberId);
		hry.mall.member.commend.model.AppCommendUser appCommendUser = appCommendUserService.get(queryFilter);
		if(null!=appCommendUser.getPid()){
			JsonResult jsonResult = integralTaskMiningService.performingTasks(appCommendUser.getPid(), taskKey);
			return jsonResult;
		}
		System.out.println("发放推荐注册奖励方法结束");
		return  new JsonResult().setSuccess(true);
	}


}
