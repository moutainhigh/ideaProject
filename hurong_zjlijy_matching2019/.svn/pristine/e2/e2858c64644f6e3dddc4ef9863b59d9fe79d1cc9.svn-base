package hry.mall.integral.remote;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.common.util.DateUtils;
import hry.core.shiro.PasswordHelper;
import hry.mall.goods.model.vo.FeixiaohaoPriceVo;
import hry.mall.integral.dao.IntegralDetailDao;
import hry.mall.integral.model.*;
import hry.mall.integral.remote.model.ThirdpayRecord;
import hry.mall.integral.service.*;
import hry.mall.lend.person.model.AppPersonInfo;
import hry.mall.lend.person.service.AppPersonInfoService;
import hry.mall.member.commend.model.AppCommendUser;
import hry.mall.member.commend.service.AppCommendUserService;
import hry.mall.member.user.model.AppCustomer;
import hry.mall.member.user.service.AppCustomerService;
import hry.mall.order.model.Order;
import hry.mall.platform.model.MallConfig;
import hry.mall.platform.service.MallConfigService;
import hry.mall.third.remote.RemoteThirdPayService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.BaseConfUtil;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.UserRedisUtils;
import net.sf.json.JSONObject;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther zhouming
 * @date 2019/3/22 16:20
 * @Version 1.0
 */
public class RemoteRollServiceImpl implements RemoteRollService {

    @Resource
    private RechargeConfigService rechargeConfigService;

    @Resource
    private CustomerIntegralService customerIntegralService;

    @Resource
    private IntegralDetailService integralDetailService;
    @Resource
    private MallConfigService mallConfigService;
    @Resource
    private AppCustomerService appCustomerService;
    @Resource
    private IntegralCoinchangeService integralCoinchangeService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private AppCommendUserService appCommendUserService;

    @Resource
    private IntegralLevelService integralLevelService;

    @Resource
    private IntegralDetailDao integralDetailDao;

    @Resource
	private ShopTeamDividendConfigService shopTeamDividendConfigService;

    @Resource
	private IntegralCommissionService integralCommissionService;

    @Resource
	private AppPersonInfoService appPersonInfoService;
    
    @Resource
    private IntegralConfigService integralConfigService;

    @Override
    public JsonResult rollCharge(String memberId, String mobile, String rechargeId, String password) {
        JsonResult jsonResult = null;
		QueryFilter userFiler = new QueryFilter(AppCustomer.class);
		AppCustomer user = appCustomerService.get(Long.valueOf(memberId));
		if (user.getAccountPassWord() == null || "".equals(user.getAccountPassWord())) {
			return new JsonResult().setSuccess(false).setMsg("zhifumimakong");
		}
		//验证交易密码
		PasswordHelper passwordHelper = new PasswordHelper();
		String encryAccountPw = passwordHelper.encryString(password, user.getSalt());
		if (!encryAccountPw.equals(user.getAccountPassWord())) {
			return new JsonResult().setSuccess(false).setMsg("zhifumimacuowu");
		}

        // 获取充值配置
        RechargeConfig rechargeConfig = rechargeConfigService.get(Long.valueOf(rechargeId));
        BigDecimal money = rechargeConfig.getMoney(); // 充值面额
        BigDecimal integralCount = rechargeConfig.getIntegralCount(); // 所需电子券数量
        String sporderid = "P" + DateUtils.getDateStr("yyyyMMddHHmmssSSS"); // 话费充值流水记录

        // 修改用户积分账户信息
        QueryFilter filter = new QueryFilter(CustomerIntegral.class);
        filter.addFilter("memberId=", memberId);
        CustomerIntegral customerIntegral = customerIntegralService.get(filter);

        // 判断消耗的电子券是否超过最大限额
        boolean isFlag = integralDetailService.getThisMonthRoll(memberId, integralCount);
        if (!isFlag) {
            return new JsonResult(false).setMsg("dzqycgshiyongxianzhi");
        }
       //验证账户币余额是否充足
           String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
 	       Map<String, String> vmap=new HashMap<String,String>();
	       vmap.put("memberId", memberId);
	       vmap.put("coinCode", platCoin);
	       BigDecimal count=customerIntegralService.platCoinCount(integralCount);
	       vmap.put("coinCount", count.toString());
	       jsonResult=customerIntegralService.validateExaccount(vmap);
	       if(!jsonResult.getSuccess()){
	    	   return jsonResult;
	       }
	    // 剩余积分修改
           customerIntegral.setAvailableIntegral(customerIntegral.getAvailableIntegral().subtract(integralCount));

        // 判断电子券是否充足
     /*   if (customerIntegral.getAvailableIntegral() != null && customerIntegral.getAvailableIntegral().compareTo(integralCount) == 1){
            // 剩余积分修改
            customerIntegral.setAvailableIntegral(customerIntegral.getAvailableIntegral().subtract(integralCount));
        } else {
            return new JsonResult(false).setMsg("dianziquanyuebuzu");
        }*/

        // 冻结积分修改
        customerIntegral.setFreezeIntegral(customerIntegral.getFreezeIntegral().add(integralCount));
        customerIntegral.setModified(new Date());
        customerIntegralService.update(customerIntegral);

        // 创建积分信息实体
        IntegralDetail detail = new IntegralDetail();
        detail.setMemberId(Long.valueOf(memberId));
        detail.setAccountId(customerIntegral.getId());
        detail.setRequestNo(sporderid); // 交易订单 -- 流水号
        detail.setCallbackintegralCount(integralCount); //消耗电子券数
        detail.setTradingType(1); // 交易状态 1:交易中 2：交易完成 3：交易失败;
        detail.setTradingDetail("话费充值");
		detail.setBusinessType(2); // 1：兑换 2：话费充值 3：转出 4：转入 5：手续费
		detail.setCoinCode(platCoin);
		detail.setCoinCount(count);
		detail.setFactCoinCount(count);
        integralDetailService.save(detail);
		// 第三方话费充值
        RemoteThirdPayService remoteThirdPayService = SpringUtil.getBean("remoteThirdPayService");
        JsonResult payResult = remoteThirdPayService.telephoneRecharge(memberId, sporderid, mobile, money.toString());
        if (payResult.getSuccess()) {

			RedisService redisService = SpringUtil.getBean("redisService");
			String lock = "submitOrder:" + customerIntegral.getMemberId();
			if (redisService.lock(lock)) {//加锁
				try{
					JSONObject obj = JSONObject.fromObject(payResult.getObj());
					ThirdpayRecord record = (ThirdpayRecord)JSONObject.toBean(obj,ThirdpayRecord.class);
					// 注: 如第三方返回的充值中 则由定时器继续查询充值是否成功--- timerPayService
					jsonResult = updateIntegralBus(record);
				}catch (Exception e){
					e.printStackTrace();
					jsonResult.setSuccess(false).setMsg("huafeichongzhishibai");
				}finally {
					redisService.unLock(lock);//解锁
				}
			}else {
				jsonResult.setSuccess(false).setMsg("huafeichongzhishibai"); //
			}

			// 消费返利---返电子券   3.0取消分红
			//rollRebate(memberId,integralCount);
        } else {
            jsonResult = new JsonResult(false).setMsg("huafeichongzhishibai");
        }
        return jsonResult;
    }

    /**
	 * 消费电子券返利
	 * @param memberId 用户id
	 * @param integralCount 消耗电子券数量
	 * **/
    private JsonResult rollRebate(String memberId, BigDecimal integralCount){
    	// 查询用户推荐信息
		QueryFilter filter = new QueryFilter(AppCommendUser.class);
		filter.addFilter("uid=", memberId);
		AppCommendUser appCommendUser = appCommendUserService.get(filter);
		// 获取父级树
		String sid = appCommendUser.getSid();
		if (sid != null && !"".equals(sid)) {
			String[] pidList = sid.split(",");
			// 返利开始
			// 根据父级数存储的结构（,1-44,2-43,3-42,） 则数组从1开始
			for (int i=1; i<pidList.length; i++) {
				// 获取父级id
				String pid = pidList[i].split("-")[1];
				// 获取用有效期用户等级身份信息(注：1、组长 2、服务中心；只有是组长或者是服务中心才能获取对应的返利)
                QueryFilter filter1 = new QueryFilter(CustomerIntegral.class);
                filter1.addFilter("memberId=",pid);
                filter1.addFilter("startDate <=", new Date());
				filter1.addFilter("endDate >=", new Date());
				CustomerIntegral customerIntegral = customerIntegralService.get(filter1);
				if (customerIntegral != null) {
					// 判断父级用户是否为服务中心
					if (customerIntegral.getTeamNumber() == 2) {
						// 返利实现
						impRollRebate(customerIntegral,memberId,"4",integralCount);
						// 根据需求当父节点有服务中心时，则停止该节点向上的返佣
						break;
					}
					// 判断父级用户是否为组长
					if (customerIntegral.getTeamNumber() == 1) {
						// 返利实现
						impRollRebate(customerIntegral,memberId,"3",integralCount);
					}
				} else {
					System.out.println("#####----:+"+customerIntegral.getMemberId()+"该用户分红配置不存在或者会员过期！");
				}
				// 返利向上返五级
				if (i==5) {
					break;
				}
			}
		}
    	return new JsonResult(true).setMsg("消费优惠券返利成功！");
	}


	/**
	 * 返利实现
	 * @param customerIntegral 父级用户账户对象
	 * @param memberId 用户id
	 * @param rebateType 返利类别 3：服务奖励(组长的团奖) 4：服务奖励(服务中心的团奖)
	 * @param integralCount 优惠券数量
	 * */
	private JsonResult impRollRebate(CustomerIntegral customerIntegral,String memberId,String rebateType, BigDecimal integralCount){
		// 获取对应用户等级的分红比例
		QueryFilter filter = new QueryFilter(ShopTeamDividendConfig.class);
		filter.addFilter("integralLevelId=", customerIntegral.getLevelId());
		filter.addFilter("teamLevelId=", customerIntegral.getTeamId());
		ShopTeamDividendConfig config = shopTeamDividendConfigService.get(filter);
		if (config != null) {
			// 分红比例
			BigDecimal ratio = config.getDividendRatio();
			// 所分红的电子卷
			BigDecimal rollCount = integralCount.multiply(ratio).multiply(new BigDecimal("0.01"));

			// 获取用户等级
			QueryFilter filter1 = new QueryFilter(CustomerIntegral.class);
			filter1.addFilter("memberId=", memberId);
			CustomerIntegral sonIntegral = customerIntegralService.get(filter1);
			// 获取用户基础信息
			QueryFilter qfAppPersonInfo = new QueryFilter(AppPersonInfo.class);
			qfAppPersonInfo.addFilter("customerId=",memberId);
			AppPersonInfo appPersonInfo = appPersonInfoService.get(qfAppPersonInfo);

			// 保存返利明细记录
			String number = integralCoinchangeService.createNumber();
			IntegralCommission comm = new IntegralCommission();
			comm.setMemberId(customerIntegral.getMemberId());
			comm.setLevelId(customerIntegral.getLevelId());
			comm.setLevelNumber(customerIntegral.getLevelNumber());
			comm.setTeamId(customerIntegral.getTeamId());
			comm.setTeamNumber(customerIntegral.getTeamNumber());
			comm.setRecommendId(Long.parseLong(memberId));
			comm.setRecommendPhone(appPersonInfo.getMobilePhone());
			comm.setRecommendLevelId(sonIntegral.getLevelId());
			comm.setRecommendLevelNumber(sonIntegral.getLevelNumber());
			comm.setType(rebateType);
			comm.setTransType("1");
			comm.setCount(rollCount);
			comm.setRate(ratio);
			comm.setNumber(number);
			comm.setMoney(integralCount);
			integralCommissionService.save(comm);
			System.out.println("#####----:保存返利明细记录成功！");

			// 操作积分账户
			Map<String,String> map = new HashMap<>();
			map.put("id", customerIntegral.getId().toString());
			map.put("type", CustomerIntegral.TYPE_ADD);
			map.put("integralCount", rollCount.toString());
			map.put("tradingDetail", "返利");
			map.put("requestNo", number);
			map.put("businessType", "6");
			boolean flag1 = customerIntegralService.updateInteger(map);
			if(flag1){
				System.out.println("#####----:操作积分账户成功！");
			}
		} else {
			System.out.println("#####----:+"+customerIntegral.getMemberId()+"该用户分红配置不存在！");
		}
		return new JsonResult(true).setMsg("操作明细成功！");
	}


    /**
     * 根据流水处理业务
     * */
    @Override
    public JsonResult updateIntegralBus(ThirdpayRecord record) {

        // 根据三方流水记录更改用户账号记录
        QueryFilter filter = new QueryFilter(CustomerIntegral.class);
        filter.addFilter("memberId=", record.getMemberId());
        CustomerIntegral customerIntegral = customerIntegralService.get(filter);

        //根据流水获取积分详情
        QueryFilter detailFilter = new QueryFilter(IntegralDetail.class);
        detailFilter.addFilter("requestNo=", record.getRecordNumber());
        IntegralDetail detail = integralDetailService.get(detailFilter);

        // 2=交易成功，3=交易失败
        if (record.getStatus() == 2 && detail.getTradingType() == 1) {
            BigDecimal total = customerIntegral.getTotalIntegral().subtract(detail.getCallbackintegralCount());
            //总
            customerIntegral.setTotalIntegral(total);
            customerIntegral.setFreezeIntegral(customerIntegral.getFreezeIntegral().subtract(detail.getCallbackintegralCount()));
            detail.setTradingType(2); // 交易状态 1:交易中 2：交易完成 3：交易失败;
            //充值成功，进行扣币操作
            this.withhold(detail);
        } else if (record.getStatus() == 3 && detail.getTradingType() == 1) {
            //剩余
            BigDecimal avail = customerIntegral.getAvailableIntegral().add(detail.getCallbackintegralCount());
            customerIntegral.setAvailableIntegral(avail);
            customerIntegral.setFreezeIntegral(customerIntegral.getFreezeIntegral().subtract(detail.getCallbackintegralCount()));
            detail.setTradingType(3); // 交易状态 1:交易中 2：交易完成 3：交易失败;
        }
        detail.setFinishDate(new Date()); // 完成时间
        customerIntegralService.update(customerIntegral);
        // 更新积分详情
        integralDetailService.update(detail);
        //添加交易记录
        return  new JsonResult(true).setMsg("chongzhichulichenggong");
    }
    
    
    
    /**
     * 操作币账户
     * @param detail
     * @return
     */
	public Boolean withhold(IntegralDetail detail){
		//封装参数，操作币业务流水和账户
		Map<String,String> map=new HashMap<String,String>();
		map.put("coinCode", detail.getCoinCode());
		map.put("memberId", detail.getMemberId().toString());
		map.put("transactionType", "2");// 交易类型(1币收入 ，2币支出)
		map.put("coinCount", detail.getCoinCount().toString());
		map.put("OrderNo", "HFCZ"+detail.getId().toString());
		map.put("optType", "510");//购买商品
		customerIntegralService.handCoin(map);
		return true;
	}


    @Override
    public void updateToUserIntegral(String referralCode) {
        QueryFilter filter = new QueryFilter(AppCustomer.class);
        filter.addFilter("referralCode=", referralCode);
        List<AppCustomer> find = appCustomerService.find(filter);

        if (find != null && find.size() > 0) {
            // 获取当前推荐人的一级推荐总人数 （必须是已实名）
            AppCustomer appCustomer = find.get(0);
			Integer oneNumber = appCommendUserService.findIsRealLen("1-"+appCustomer.getId());

			// 获取用户账户信息
            QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
            queryFilter.addFilter("memberId=", appCustomer.getId());
            CustomerIntegral customerIntegral = customerIntegralService.get(queryFilter);

            BigDecimal willQuota = null; // 将要获得的推广额度
			// 推广额度增加
			IntegralLevel nowLevel = integralLevelService.get(customerIntegral.getLevelId());
			willQuota = nowLevel.getRecommendQuota().add(customerIntegral.getRecommendQuota()); // 将要获得的推广额度
            // 是否达到下一等级标准
            QueryFilter levelFilter = new QueryFilter(CustomerIntegral.class);
            levelFilter.addFilter("number > ", customerIntegral.getLevelNumber());
            levelFilter.setOrderby("number asc");
            List<IntegralLevel> levelList = integralLevelService.find(levelFilter);
         
            if (levelList != null && levelList.size() > 0) {
                // 判断是否达到该等级条件
                IntegralLevel level = levelList.get(0);
                if (oneNumber >= level.getRecommendCOunt()) { // 达到该等级
                    customerIntegral.setLevelId(level.getId());
                    customerIntegral.setLevelNumber(level.getNumber());
                    //升级后的基础额度大于现有基础额度，则改为新的，不然为旧的
                    if(level.getBaseQuota().compareTo(customerIntegral.getBaseQuota())>0){
                    	 customerIntegral.setBaseQuota(level.getBaseQuota());
                    }
                  //升级后的最大额度大于现有基础额度，则改为新的，不然为旧的
                    if(level.getMaxQuota().compareTo(customerIntegral.getMaxQuota())>0){
                    	customerIntegral.setMaxQuota(level.getMaxQuota());//提升最大额度
                    }
                    
                    // 升级后推广额度增加
                //    willQuota = customerIntegral.getRecommendQuota().add(level.getRecommendQuota());

                }
            }
        
            customerIntegral.setRecommendQuota(willQuota);
            customerIntegral.setTotalQuota(customerIntegral.getBaseQuota().add(customerIntegral.getRecommendQuota()));
            customerIntegralService.update(customerIntegral);
        }

    }
	@Override
	public JsonResult coinchangeInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		JsonResult result=new JsonResult();
	    //1、判断是否是电子券兑换时间
			MallConfig config1=this.isChangeTime();
			if(!config1.getIsChangeTime()){
				  result.setSuccess(false).setMsg("feiduihuanshijian"+"&&&"+config1.getStartDate()+"-"+config1.getEndDate()).setCode("1111"); //非电子券兑换时间
	              return result; 
			}
		//1、获得用户信息
		 AppCustomer appCustomer = null;
		 appCustomer=appCustomerService.get(Long.valueOf(map.get("memberId")));
		 if(null!=appCustomer){
			//2、获得系统配置的电子券兑换币种，兑换折扣
			String coinCode=""; 
			BigDecimal discountRate= new BigDecimal("1");
			List<MallConfig> list=mallConfigService.findAll();
			if(null!=list && list.size()>0){
				MallConfig config=	list.get(0);
				discountRate=config.getDiscountRate(); 
				coinCode=config.getCoinCode();
			} 
			//3、判断后台是否配置币种
			if("".equals(coinCode)){
				 result.setSuccess(false).setMsg("meiyouduihuanbizhong"); //没有配置兑换币种，请联系平台
				 return result; 
			}
			//4、查询币账户
			ExDigitalmoneyAccountRedis dmAccount= UserRedisUtils.getAccountRedis(map.get("memberId"), coinCode);
			if(null==dmAccount){
				return result.setSuccess(false).setMsg("meiyoubizhanghu");//没有相应币账户信息
			}
			//5、根据币账户余额进行计算
			IntegralCoinchange change=new IntegralCoinchange();
			change.setCoinCode(coinCode);
			change.setDiscountRate(discountRate);
			change.setTotalMoney(dmAccount.getHotMoney());
			//6、获得币率
			BigDecimal coinRate=this.getCoinRate(coinCode);
			//7、计算一个币和账户币余额对应兑换电子券数量
			BigDecimal oneCount=coinRate.divide(discountRate, 2, RoundingMode.HALF_UP);
			BigDecimal totalCount=coinRate.multiply(dmAccount.getHotMoney()).divide(discountRate, 2, RoundingMode.HALF_UP);
			change.setTotalCount(totalCount);
			change.setOneCount(oneCount);
			result.setSuccess(true).setObj(change);
		 }else{
			 result.setSuccess(false).setMsg("user_no_exist"); //用户 不存在
			 return result; 
		 }
		return result;
	}
	@Override
	public BigDecimal getCoinRate(String coinCode) {
		// TODO Auto-generated method stub
		BigDecimal codePrice = new BigDecimal("0");
		RedisService redisService = SpringUtil.getBean("redisService");
		String result = redisService.get("cn:newFeixiaohaoPrice");
		if (result != null && !"".equals(result)) {
			List<FeixiaohaoPriceVo> list = JSON.parseArray(result, FeixiaohaoPriceVo.class);
			for(FeixiaohaoPriceVo feixiaohaoPriceVo:list){
				String name = feixiaohaoPriceVo.getName();
				if(name.equals(coinCode)){
					codePrice = new BigDecimal(feixiaohaoPriceVo.getPrice());
					return codePrice;
				}
			}
		} else{
			System.out.println("未查询到redis中"+coinCode+"行情信息");
			return codePrice;
		}
		return codePrice;
	}
	@Override
	public JsonResult coinchangeConfirm(Map<String, String> map) {
		// TODO Auto-generated method stub
		JsonResult result=new JsonResult();
		
	   //1、判断是否是电子券兑换时间
		MallConfig config1=this.isChangeTime();
		if(!config1.getIsChangeTime()){
			  result.setSuccess(false).setMsg("feiduihuanshijian"+"&&&"+config1.getStartDate()+"-"+config1.getEndDate()).setCode("1111"); //非电子券兑换时间
              return result; 
		}
		//2、获得用户信息
		 AppCustomer appCustomer = null;
		 appCustomer=appCustomerService.get(Long.valueOf(map.get("memberId")));
		 if(null!=appCustomer){
			 //如果支付密码密码为空，则提示用户去维护支付密码
			 if(null==appCustomer.getAccountPassWord() || "".equals(appCustomer.getAccountPassWord())){
				  result.setSuccess(false).setMsg("zhifumimakong"); //支付密码为空,请先去维护支付密码
	              return result; 
			  }
			    PasswordHelper passwordHelper = new PasswordHelper();
	            String encryString = passwordHelper.encryString(map.get("password"), appCustomer.getSalt());
	            if(!encryString.equals(appCustomer.getAccountPassWord())){
	            	 result.setSuccess(false).setMsg("zhifumimacuowu"); //支付密码错误
	            	 return result;
	            }
			 
			 //3、查找该用户今天是否有兑换记录
			 QueryFilter filter = new QueryFilter(IntegralCoinchange.class);
			 filter.addFilter("memberId=", Long.valueOf(map.get("memberId")));
			 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			 String date=df.format(new Date());
			 filter.addFilter("changeDate=", date);
			 List<IntegralCoinchange> clist=integralCoinchangeService.find(filter);
			 if(null!=clist && clist.size()>0){
				 result.setSuccess(false).setMsg("jintianyiduihuan"); //您今天已经兑换过了，请明天再来！
				 return result;
			 }

			//4、获得系统配置的电子券兑换币种，兑换折扣
			String coinCode="";
			BigDecimal discountRate= new BigDecimal("1");
			List<MallConfig> list=mallConfigService.findAll();
			if(null!=list && list.size()>0){
				MallConfig config=	list.get(0);
				discountRate=config.getDiscountRate();
				coinCode=config.getCoinCode();
			}
			//5、判断后台是否配置币种
			if("".equals(coinCode)){
				 result.setSuccess(false).setMsg("meiyouduihuanbizhong"); //后台没有配置兑换币种，请联系平台
				 return result;
			}
			//6、查询币账户
			ExDigitalmoneyAccountRedis dmAccount=UserRedisUtils.getAccountRedis(map.get("memberId"), coinCode);
			if(null==dmAccount){
				return result.setSuccess(false).setMsg("meiyoubizhanghu");//没有相应币账户信息
			}
			//7、获得用户的兑换数量，计算需要的数字币数量
			BigDecimal count=new BigDecimal(map.get("count"));
			BigDecimal money=count.multiply(discountRate);	//计算需要的人民币的数量
			BigDecimal coinRate=this.getCoinRate(coinCode);//获得币率
			BigDecimal coinCount=money.divide(coinRate, 10, BigDecimal.ROUND_HALF_UP);//计算需要的数币数量
			//8、判断币账户余额是否充足,如果币账户余额小于需支付金额
			if(dmAccount.getHotMoney().compareTo(coinCount)<0){
				return result.setSuccess(false).setMsg("zhanghuyuebuzu");//账户余额不足
			}
			//9、保存兑换业务数据
			IntegralCoinchange change=new IntegralCoinchange();
			String number=integralCoinchangeService.createNumber();
			change.setNumber(number);
			change.setCoinCode(coinCode);
			change.setDiscountRate(discountRate);
			change.setChangeDate(df.format(new Date()));
			change.setMemberId(Long.valueOf(map.get("memberId")));
			change.setCoinCount(coinCount);
			change.setCount(count);
			change.setCoinRate(coinRate);
			integralCoinchangeService.save(change);
  			//10、保存积分增加记录
			QueryFilter qf=new QueryFilter(CustomerIntegral.class);
			qf.addFilter("memberId=", appCustomer.getId());
			List<CustomerIntegral> ilist=customerIntegralService.find(qf);
			if(null!=ilist && ilist.size()>0){
				CustomerIntegral i=ilist.get(0);
				Map<String,String> map1 = new HashMap<>();
				   map1.put("id",i.getId().toString());
				   map1.put("type",CustomerIntegral.TYPE_ADD);
				   map1.put("integralCount",count.toString());
				   map1.put("tradingDetail","兑换");
				   map1.put("requestNo",number);
				   map1.put("businessType","1");
				   boolean flag1 = customerIntegralService.updateInteger(map1);
				   if(!flag1){
					   result.setSuccess(false).setMsg("duihuanshibai"); //兑换失败
					   return result; 
				   }
			}
			else{
				result.setSuccess(false).setMsg("wudianziquanzhanghu"); //无电子券账户，请联系平台管理员
				   return result; 
			}
			//11、进行扣币
			if(this.withhold(change)){
				change.setStatus(Short.valueOf("1"));
				integralCoinchangeService.update(change);
				result.setSuccess(true).setObj(change).setMsg("duihuanchenggong");//兑换成功
			}
		 }else{
			 result.setSuccess(false).setMsg("user_no_exist"); //用户 不存在
			 return result;
		 }
		return result;
	}
	public Boolean withhold(IntegralCoinchange change){
		ExDigitalmoneyAccountRedis dmAccount=UserRedisUtils.getAccountRedis(change.getMemberId().toString(), change.getCoinCode());
	     // 热账户减少
        Accountadd accountadd = new Accountadd();
        accountadd.setAccountId(dmAccount.getId());
        accountadd.setMoney((change.getCoinCount()).multiply(new BigDecimal(-1)));
        accountadd.setMonteyType(1);//1是操作热账户，其他是操作冻结账户
        accountadd.setAcccountType(1);
        accountadd.setRemarks(50);
        accountadd.setTransactionNum(change.getId().toString());
        List<Accountadd> list = new ArrayList<Accountadd>();
        list.add(accountadd);
        messageProducer.toAccount(JSON.toJSONString(list));
		return true;
	}

    @Override
    public FrontPage findRollDetail(Map<String, String> map) {
        Page<IntegralDetail> page = PageFactory.getPage(map);
        QueryFilter filter = new QueryFilter(IntegralDetail.class);
        filter.addFilter("memberId=", map.get("memberId"));
        filter.addFilter("tradingType=", 2); // 查询交易完成的明细
		filter.setOrderby("created desc");
        List<IntegralDetail> details = integralDetailService.find(filter);
        return new FrontPage(details, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult findUserRollAccount(Map<String, String> map) {
        String memberId = map.get("memberId");
        QueryFilter filter = new QueryFilter(CustomerIntegral.class);
        filter.addFilter("memberId=", memberId);
        CustomerIntegral customerIntegral = customerIntegralService.find(filter).get(0);
        return new JsonResult().setSuccess(true).setObj(customerIntegral);
    }

    @Override
    public BigDecimal findUserThismonthRoll(String memberId) {
        BigDecimal thisRoll = integralDetailDao.getThisMonthRoll(memberId);
        return thisRoll;
    }
    
	public MallConfig isChangeTime() {
		// TODO Auto-generated method stub
		List<MallConfig> list=mallConfigService.findAll();
		String startDate="";
		String endDate="";
		JsonResult  result=new JsonResult();
			MallConfig config=	list.get(0);
			startDate=config.getStartDate(); 
			endDate=config.getEndDate();
			config.setIsChangeTime(false);
			//判断当前是否是电子券兑换时间
			if(null!=startDate && !"".equals(startDate) && null!=endDate && !"".equals(endDate)){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				   String s=formatter.format(new Date());
				   String sfm=s.substring(11,16);
				   if(sfm.compareTo(startDate)>=0 && sfm.compareTo(endDate)<=0){
						config.setIsChangeTime(true);
				}   
			}
		
		return config;
	}
	@Override
	public JsonResult calculateCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		JsonResult result=new JsonResult();
			//1、获得用户信息
			 AppCustomer appCustomer = null;
			 appCustomer=appCustomerService.get(Long.valueOf(map.get("memberId")));
			 if(null!=appCustomer){
				//2、获得系统配置的电子券兑换币种，兑换折扣
					String coinCode="";
					BigDecimal discountRate= new BigDecimal("1");
					List<MallConfig> list=mallConfigService.findAll();
					if(null!=list && list.size()>0){
						MallConfig config=	list.get(0);
						discountRate=config.getDiscountRate();
						coinCode=config.getCoinCode();
					}
					//3、获得用户的兑换数量，计算需要的数字币数量
					BigDecimal count=new BigDecimal(map.get("count"));
					BigDecimal money=count.multiply(discountRate);	//计算需要的人民币的数量
					BigDecimal coinRate=this.getCoinRate(coinCode);//获得币率
					BigDecimal coinCount=money.divide(coinRate, 10, BigDecimal.ROUND_HALF_UP);//计算需要的数币数量
					 result.setSuccess(true).setObj(coinCount); 
			 }else{
				 result.setSuccess(false).setMsg("user_no_exist"); //用户 不存在
				 return result;
			 }
			return result;
	}


	@Override
	public FrontPage findConmmendForntPageBySql(Map<String, String> params) throws Exception {
		return appCommendUserService.findConmmendForntPageBySql(params);
	}


	@Override
	public JsonResult memberDetail(Map<String, String> map) {
		// TODO Auto-generated method stub
		JsonResult result=new JsonResult();
		String memberId=map.get("memberId");
	    AppCustomer appCustomer = null;
		appCustomer=appCustomerService.get(Long.valueOf(map.get("memberId")));
		if(null!=appCustomer){
			//1、查询电子券账户
			QueryFilter qf=new QueryFilter(CustomerIntegral.class);
			qf.addFilter("memberId=", memberId);
			List<CustomerIntegral> list=customerIntegralService.find(qf);
			if(null!=list && list.size()>0){
				CustomerIntegral integral=list.get(0);
				if(null!=integral.getLevelId() && 0!=integral.getLevelId()){
				  IntegralLevel level=integralLevelService.get(integral.getLevelId());
				  if(null!=level && !"".equals(level)){
					  level.setNickNameOtc(appCustomer.getNickNameOtc());
					  level.setUserFace(appCustomer.getUserFace());
					  level.setEndDate(integral.getEndDate());
					  QueryFilter  filter=new QueryFilter(ShopTeamDividendConfig.class);
					  filter.addFilter("integralLevelId=", level.getId());
					  List<ShopTeamDividendConfig> slist=shopTeamDividendConfigService.find(filter);
					  for(ShopTeamDividendConfig config:slist){
						  if(1==config.getTeamLevelNumber()){
							  level.setFuwuRate(config.getDividendRatio()); 
						  }
						  if(2==config.getTeamLevelNumber()){
							  level.setFuliRate(config.getDividendRatio());
						  }
					  }
					  result.setSuccess(true).setObj(level); 
				      return result;  
				  }
				  else{
					  result.setSuccess(false).setMsg("wuxiangyingdengji"); //没有相应会员等级，联系平台管理员
				      return result;
				  }
				}else{
					  IntegralLevel level1=new IntegralLevel ();
					  level1.setNickNameOtc(appCustomer.getNickNameOtc());
					  level1.setUserFace(appCustomer.getUserFace());
					  level1.setName("未开通");
					//  result.setSuccess(false).setMsg("bushihuiyuan"); //您目前不是会员，请去升级
					  result.setSuccess(true).setObj(level1);
					 return result;
				}
			}
		}else{
			 result.setSuccess(false).setMsg("user_no_exist"); //用户 不存在
			 return result;
		}
	
		
		return result;
	}

	@Override
	public FrontPage commissionRecord(Map<String, String> map) {
		// TODO Auto-generated method stub
	    Page<IntegralCommission> page = PageFactory.getPage(map);
		String memberId=map.get("memberId");
	    List<IntegralCommission>  list=integralCommissionService.findRecord(Long.valueOf(memberId), 1);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	    
	}

}
