package hry.mall.integral.remote;

import com.alibaba.fastjson.JSON;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.core.shiro.PasswordHelper;
import hry.mall.integral.model.*;
import hry.mall.integral.service.*;
import hry.mall.lend.person.model.AppPersonInfo;
import hry.mall.lend.person.service.AppPersonInfoService;
import hry.mall.member.commend.model.AppCommendUser;
import hry.mall.member.commend.service.AppCommendUserService;
import hry.mall.member.user.model.AppCustomer;
import hry.mall.member.user.service.AppCustomerService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.BaseConfUtil;
import hry.util.QueryFilter;
import hry.util.SNUtil;
import hry.util.SpringUtil;
import hry.util.date.DateUtil;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class RemoteIntegralLevelServiceImpl implements RemoteIntegralLevelService {
	@Resource
	public IntegralLevelService integralLevelService;
	@Resource
	public AppCommendUserService appCommendUserService;
	@Resource
	private IntegralConfigService integralConfigService;
	@Resource
	private IntegralCommissionService integralCommissionService;
	@Resource
	private CustomerIntegralService customerIntegralService;
	@Resource
	private AppCustomerService appCustomerService;
	@Resource
	private AppPersonInfoService appPersonInfoService;
	@Resource(name="transactionManager")
	private DataSourceTransactionManager transactionManager;
	@Resource
	private LevelRecordService levelRecordService;
	@Resource
	private ShopTeamLevelService shopTeamLevelService;
	@Resource
	private ShopMemberPerformanceService shopMemberPerformanceService;


	@Override
	public JsonResult levelList() {
		// TODO Auto-generated method stub
		List<IntegralLevel> list=integralLevelService.findAll();
		return new JsonResult().setSuccess(true).setObj(list);
	}

	@Override
	public JsonResult findByCustomerId(long memberId) {
		JsonResult jsonResult = new JsonResult();
		try {

			AppCommendUser appCommendUser = appCommendUserService.findPageBySqlIntegral(memberId);
			Map<String, String> params = new HashMap<>();
			params.put("id",memberId+"");
			params.put("number","1");
			params.put("limit","1");
			params.put("offset","0");
			FrontPage page = appCommendUserService.findConmmendForntPageBySql(params);
			appCommendUser.setOneNumber(page.getTotal().intValue());
			params.put("number","2");
			FrontPage page1 = appCommendUserService.findConmmendForntPageBySql(params);
			appCommendUser.setTwoNumber(page1.getTotal().intValue());
			params.put("number","3");
			FrontPage page2 = appCommendUserService.findConmmendForntPageBySql(params);
			appCommendUser.setThreeNumber(page2.getTotal().intValue());
			// 获取团队人数
			//ApiJsonResult result = integralConfigService.getUserRecommendedNumber(memberId,5);
			//获取团队业绩
			QueryFilter queryFilter = new QueryFilter(ShopMemberPerformance.class);
			queryFilter.addFilter("memberId=",memberId);
			ShopMemberPerformance shopMemberPerformance = shopMemberPerformanceService.get(queryFilter);
			// 团队业绩
			appCommendUser.setTeamAllPerformance(shopMemberPerformance.getTeamAllPerformance());

			appCommendUser.setTeamAllPerformance(shopMemberPerformance.getTeamAllPerformance());
			QueryFilter filter = new QueryFilter(IntegralCommission.class);
			filter.addFilter("memberId=", memberId);
			//filter.addFilter("type_in", "3,4");
			List<IntegralCommission> integralCommissionList = integralCommissionService.find(filter);
			// 团队奖励 //累积收益：直推+拓展+静态+团队分红。
			BigDecimal allTeamAward = new BigDecimal("0");
			// 团队名称
			String teamLevelName = "0";
			if (integralCommissionList != null && integralCommissionList.size() > 0) {
				for (IntegralCommission comm : integralCommissionList) {
					allTeamAward = allTeamAward.add(comm.getCount());
				}
			}
			appCommendUser.setAllTeamAward(allTeamAward);
			QueryFilter filter1 = new QueryFilter(CustomerIntegral.class);
			filter1.addFilter("memberId=",memberId);
			CustomerIntegral customerIntegral = customerIntegralService.get(filter1);
			if (customerIntegral.getTeamId() != null) {
				teamLevelName = customerIntegral.getTeamNumber().toString();
			}
			appCommendUser.setTeamLevelName(teamLevelName);
			jsonResult.setSuccess(true).setObj(appCommendUser);
			return jsonResult;
		}catch (Exception e){
			e.printStackTrace();
		}
		return jsonResult.setSuccess(false);

	}



	@Override
	public JsonResult buyLevel(Long memberId,BigDecimal account,String accountPwd) {
		/*
		0.校验对应的会员
		1.校验密码，
		2.判断用户是否已有会员，并校验只上不下
		4.电子劵扣款
		5.修改用户会员等级
		6.添加用户升级记录
		8.给一级二级返佣
		*/
		JsonResult result = new JsonResult();
		IntegralLevel levelByAccount = integralLevelService.getLevelByAccount(account);
		if (null ==levelByAccount){
			result.setSuccess(false).setMsg("meiyouduiyingdengji"); //没有对应的会员
			return result;
		}

		//1校验密码
		QueryFilter filter = new QueryFilter(AppCustomer.class);
		filter.addFilter("id=",memberId);
		AppCustomer appCustomer = appCustomerService.get(filter);
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
		//总购买金额不超过10W
		BigDecimal userPerformance = levelRecordService.getUserPerformance(memberId);
		if(null==userPerformance){
			userPerformance=new BigDecimal("0");
		}
		BigDecimal count = userPerformance.add(account);
		if(count.compareTo(new BigDecimal("100000"))>0){
			result.setSuccess(false).setMsg("goumaijineguoda"); //购买金额过大
			return result;
		}

		// 校验只上不下
		//查询用户账户
		QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
		queryFilter.addFilter("memberId=", memberId);
		//与用户一对一
		CustomerIntegral customerIntegral = customerIntegralService.find(queryFilter).get(0);
		IntegralLevel integralLevelOld=null;
		if(null != customerIntegral.getLevelId()){
			integralLevelOld = integralLevelService.get(customerIntegral.getLevelId());
			// 校验只上不下
			if(integralLevelOld.getNumber() > levelByAccount.getNumber()){
				result.setSuccess(false).setMsg("goumaigenggaodengji"); //只能购买更高的等级
				return result;
			}
		}else{
			result.setSuccess(false).setMsg("req_error"); //没有默认会员
			return result;
		}

	/*	BigDecimal availableIntegral = customerIntegral.getAvailableIntegral();
		int i = availableIntegral.compareTo(account);
		if(i<0){
			result.setSuccess(false).setMsg("dianziquanyuebuzu");//余额不足
			return result;
		}*/
		//验证账户币余额是否充足
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
	       Map<String, String> vmap=new HashMap<String,String>();
	       vmap.put("memberId", memberId.toString());
	       vmap.put("coinCode", platCoin);
	       BigDecimal coincount=customerIntegralService.platCoinCount(account);
	       vmap.put("coinCount", coincount.toString());
	       result=customerIntegralService.validateExaccount(vmap);
	       if(!result.getSuccess()){
	    	   return result;
	       }

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
		try {
			//5.添加用户升级记录
			LevelRecord levelRecord = new LevelRecord();
			//判断用户是否已有会员
			if (null != customerIntegral.getLevelId()){
				levelRecord.setOldLevelId(integralLevelOld.getId());
				levelRecord.setOldLevelNumber(integralLevelOld.getNumber());
			}
			String requestNo = SNUtil.create15();
			levelRecord.setNumber(requestNo);
			levelRecord.setMemberId(memberId);
			levelRecord.setLevelId(levelByAccount.getId());
			levelRecord.setLevelNumber(levelByAccount.getNumber());
			levelRecord.setMoney(account);
			levelRecord.setStatus(1);
			levelRecord.setRemark("购买会员");
			levelRecord.setStartDate(new Date());
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR, levelByAccount.getValidityPeriod());
			Date time = calendar.getTime();
			levelRecord.setEndDate(time);
			//设置分红时间  客户要求分红时间为1天
			Date date = DateUtil.addDay(new Date(), 1);
			levelRecord.setDistributeDate(date);
			levelRecordService.save(levelRecord);

            RedisService redisService = SpringUtil.getBean("redisService");
            String lock = "submitOrder:" + customerIntegral.getMemberId();
            if (redisService.lock(lock)) {//加锁
                try{
                    //4.电子劵扣款
                    Map<String, String> map1 = new HashMap<>();
                    map1.put("id", customerIntegral.getId().toString());
                    map1.put("type", CustomerIntegral.TYPE_SUB);
                    map1.put("integralCount", account.toString());
                    map1.put("tradingDetail", "端口开通");
                    map1.put("businessType", "7");//转出
                    map1.put("requestNo", requestNo);//流水号
                    boolean flag1 = customerIntegralService.updateInteger(map1);
                    if (!flag1) {
                        transactionManager.rollback(status);
                        result.setSuccess(false).setMsg("kaitongshibai"); //开通失败
                        return result;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    transactionManager.rollback(status);
                    result.setSuccess(false).setMsg("kaitongshibai"); //开通失败
                    return result;
                }finally {
                    redisService.unLock(lock);//解锁
                }
            }else{
				transactionManager.rollback(status);
				result.setSuccess(false).setMsg("kaitongshibai"); //开通失败
			}

            redisService.unLock(lock);//解锁

			//修改自己的团队业绩
			QueryFilter queryFilter2 = new QueryFilter(ShopMemberPerformance.class);
			queryFilter2.addFilter("memberId=",memberId);
			ShopMemberPerformance shopMemberPerformance = shopMemberPerformanceService.get(queryFilter2);
			shopMemberPerformance.setAllPerformance(shopMemberPerformance.getAllPerformance().add(account));
			shopMemberPerformance.setTeamAllPerformance(shopMemberPerformance.getTeamAllPerformance().add(account));
			shopMemberPerformanceService.update(shopMemberPerformance);
			//更新自己的团队等级
			shopTeamLevelService.updateUserTeamLevel(memberId);

			//6。修改用户会员等级和到期时间
			//需要查出扣款后的用户账户表
			CustomerIntegral newCustomerIntegral1 = customerIntegralService.get(customerIntegral.getId());
			newCustomerIntegral1.setLevelId(levelByAccount.getId());
			newCustomerIntegral1.setLevelNumber(levelByAccount.getNumber());
			newCustomerIntegral1.setStartDate(new Date());
			newCustomerIntegral1.setEndDate(time);
			customerIntegralService.update(newCustomerIntegral1);


			//创建线程 8.给一级二级返佣 9.维护5级内的上级的身份  改为3级返佣。10级维护（暂不维护。定时器维护）
//			UserByLevelRunable userByLevelRunable = new UserByLevelRunable(memberId, levelByAccount.getId(), account, 3, 10);
//			ThreadPool.exe(userByLevelRunable);

			//通过消息返佣
			MessageProducer messageProducer = SpringUtil.getBean("messageProducer");
			messageProducer.distributeRecommendRewards(JSON.toJSONString(levelRecord));

			transactionManager.commit(status);
		}catch (Exception e){
			e.printStackTrace();
			transactionManager.rollback(status);

			return new JsonResult().setSuccess(false).setMsg("kaitongshibai");//开通失败

		}

		return new JsonResult().setSuccess(true).setMsg("kaitongchenggong");//开通成功

	}

	@Override
	public JsonResult seeUserLevel(long memberId){
		AppCustomer appCustomer = appCustomerService.get(memberId);
		HashMap<String, Object> map = new HashMap<>();
		map.put("nickNameOtc",appCustomer.getNickNameOtc());//昵称
		map.put("userFace",appCustomer.getUserFace());//头像
		//查询用户账户
		QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
		queryFilter.addFilter("memberId=", memberId);
		List<CustomerIntegral> customerIntegrals = customerIntegralService.find(queryFilter);
		//与用户一对一
		CustomerIntegral customerIntegral = customerIntegrals.get(0);
		map.put("isLevel","0");//无会员

		QueryFilter queryFilter1 = new QueryFilter(IntegralLevel.class);
		queryFilter1.setOrderby("number asc");
		List<IntegralLevel> list=integralLevelService.find(queryFilter1);
		if(null != list && list.size()>0){
			IntegralLevel integralLevel1 = list.get(0);
			//判断用户是否已有非默认会员且未过期
			if (null != customerIntegral.getLevelId()&& customerIntegral.getLevelId()!=integralLevel1.getId() && null != customerIntegral.getEndDate() && !customerIntegral.getEndDate().before(new Date())){
				IntegralLevel integralLevel = integralLevelService.get(customerIntegral.getLevelId());
				map.put("levelName",integralLevel.getName());
				map.put("levelNum",integralLevel.getNumber());
				map.put("lowestPrice",integralLevel.getLowestPrice());
				map.put("highestPrice",integralLevel.getHighestPrice());
				map.put("endDate",customerIntegral.getEndDate());
				map.put("isLevel","1");//是入门级会员
			}
			//是否有入门会员
			if(null != list && list.size()>1){
                IntegralLevel integralLevel2 = list.get(1);
                if(customerIntegral.getLevelNumber()!=null){
                    if(customerIntegral.getLevelNumber()>integralLevel2.getNumber()){
                        map.put("isLevel","2");//有入门级以上会员
                    }
                }
				//先删除默认。后删除入门
				list.remove(0);
			}
			//先删除默认。后删除入门
			list.remove(0);

		}
		map.put("integralLevels",list);//所有会员等级集合

		return new JsonResult().setSuccess(true).setObj(map);
	}


	@Override
	public JsonResult getLevelByAccount(BigDecimal account){
		if (null ==account){
			return new JsonResult().setSuccess(false).setMsg("buyAccount_not_null");
		}
		IntegralLevel integralLevel = integralLevelService.getLevelByAccount(account);
		if (null ==integralLevel){
			return new JsonResult().setSuccess(false).setMsg("meiyouduiyingdengji"); //没有对应的会员
		}
		return new JsonResult().setSuccess(true).setObj(integralLevel);
	}


	@Override
	public JsonResult addLevelRecord(Map<String, Object> map){
		String mobilePhone = map.get("mobilePhone")==null? null :map.get("mobilePhone").toString();
		String number = map.get("number")==null?null:map.get("number").toString();
		String oldLevelNumber = map.get("oldLevelNumber")==null?null:map.get("oldLevelNumber").toString();
		String levelNumber = map.get("levelNumber")==null?null:map.get("levelNumber").toString();
		String money = map.get("money")==null?null:map.get("money").toString();
		String remark = map.get("remark")==null?null:map.get("remark").toString();
		String startDate = map.get("startDate")==null?null:map.get("startDate").toString();
		String endDate = map.get("endDate")==null?null:map.get("endDate").toString();
		String distributeDate = map.get("distributeDate")==null?null:map.get("distributeDate").toString();

		if(!StringUtils.isEmpty(mobilePhone)) {
			// 查询此用户有没有注册
			QueryFilter filter = new QueryFilter(AppPersonInfo.class);
			filter.addFilter("mobilePhone=", mobilePhone);
			AppPersonInfo appPersonInfo = appPersonInfoService.get(filter);
			if (appPersonInfo == null) {
				System.out.println("手机号为："+mobilePhone+"的用户没注册");
				return new JsonResult().setSuccess(false).setMsg(mobilePhone);
			}
			LevelRecord levelRecord = new LevelRecord();
			levelRecord.setMemberId(appPersonInfo.getCustomerId());
			//判断用户是否已有会员
			if (!StringUtils.isEmpty(oldLevelNumber)){
				QueryFilter queryFilter = new QueryFilter(IntegralLevel.class);
				queryFilter.addFilter("number=",oldLevelNumber);
				IntegralLevel integralLevelOld = integralLevelService.get(queryFilter);
				if(integralLevelOld !=null){
					levelRecord.setOldLevelId(integralLevelOld.getId());
				}
				levelRecord.setOldLevelNumber(Integer.valueOf(oldLevelNumber));
			}
			if (!StringUtils.isEmpty(levelNumber)){
				QueryFilter queryFilter = new QueryFilter(IntegralLevel.class);
				queryFilter.addFilter("number=",levelNumber);
				IntegralLevel integralLevel = integralLevelService.get(queryFilter);
				//维护用户等级
				QueryFilter queryFilter1 = new QueryFilter(CustomerIntegral.class);
				queryFilter1.addFilter("memberId=",appPersonInfo.getCustomerId());
				CustomerIntegral customerIntegral = customerIntegralService.get(queryFilter1);
				customerIntegral.setLevelId(integralLevel.getId());
				customerIntegral.setLevelNumber(integralLevel.getNumber());
				customerIntegralService.update(customerIntegral);

				if(integralLevel !=null){
					levelRecord.setLevelId(integralLevel.getId());
				}
				levelRecord.setLevelNumber(Integer.valueOf(levelNumber));
			}

			try{
				levelRecord.setNumber(number);
				levelRecord.setMoney(new BigDecimal(money));
				levelRecord.setStatus(1);
				levelRecord.setRemark(remark);
				SimpleDateFormat sDateFormat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); //加上时间
				//SimpleDateFormat sDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
				levelRecord.setStartDate(sDateFormat.parse(startDate));
				levelRecord.setEndDate(sDateFormat.parse(endDate));
				//设置分红时间  客户要求分红时间为1天
				levelRecord.setDistributeDate(sDateFormat.parse(distributeDate));
				levelRecordService.save(levelRecord);
				return new JsonResult().setSuccess(true);
			}catch (Exception e){
				e.printStackTrace();
				return new JsonResult().setSuccess(false).setMsg("手机号为:"+mobilePhone+"生成记录时异常");
			}

		}

		return new JsonResult().setSuccess(false).setMsg("手机号为空");

	}

}
