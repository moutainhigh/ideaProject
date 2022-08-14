package hry.mall.integral.remote;

import hry.bean.JsonResult;
import hry.core.shiro.PasswordHelper;
import hry.mall.integral.model.*;
import hry.mall.integral.service.*;
import hry.mall.member.user.model.AppCustomer;
import hry.mall.member.user.service.AppCustomerService;
import hry.mall.platform.model.MallConfig;
import hry.mall.platform.service.MallConfigService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.BaseConfUtil;
import hry.util.QueryFilter;
import hry.util.SNUtil;
import hry.util.UserRedisUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther zhouming
 * @date 2019/3/22 16:20
 * @Version 1.0
 */
public class RemoteShopWithdrawRecordServiceImpl implements RemoteShopWithdrawRecordService {

    @Resource
    private CustomerIntegralService customerIntegralService;
    @Resource
    private IntegralLevelService integralLevelService;
	@Resource
	private MallConfigService mallConfigService;
	@Resource
	private AppCustomerService appCustomerService;
	@Resource
	private ShopWithdrawRecordService shopWithdrawRecordService;
	@Resource(name="transactionManager")
	private DataSourceTransactionManager transactionManager;


	/**
	 * 准备申请提现
	 * **/
	@Override
	public JsonResult readyWithdrawalApplications(long memberId){
//		QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
//		queryFilter.addFilter("memberId=",memberId);
//		CustomerIntegral customerIntegral = customerIntegralService.get(queryFilter);
		List<Map<String, Object>> withdrawalMethod = shopWithdrawRecordService.getAppDic("withdrawalMethod");
		List<Map<String, Object>> withdrawalBank = shopWithdrawRecordService.getAppDic("withdrawalBank");

		//1、查询比账户
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
		ExDigitalmoneyAccountRedis dmAccount= UserRedisUtils.getAccountRedis(String.valueOf(memberId), platCoin);

		List<MallConfig> all = mallConfigService.findAll();
		MallConfig mallConfig = all.get(0);
		Map<String, Object> map = new HashMap<>();
		map.put("availableIntegral",dmAccount.getHotMoney());//用户可用余额
		map.put("withdrawalFee",mallConfig.getWithdrawalFee());//提现手续费率
		map.put("withdrawalMethod",withdrawalMethod);//提现可用方式
		map.put("withdrawalBank",withdrawalBank);//提现支持的银行
		return new JsonResult().setSuccess(true).setObj(map);
	}

	@Override
	public JsonResult submitWithdrawalApplication(Long memberId, Integer way, String bankName, String accountNumber, BigDecimal applicationAmount,String accountPwd, String markImg ){
		//1校验密码
		JsonResult result = new JsonResult();
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
		List<MallConfig> all = mallConfigService.findAll();
		MallConfig mallConfig = all.get(0);
		//手续费=申请提现金额*手续费率
		BigDecimal fee = applicationAmount.multiply(mallConfig.getWithdrawalFee()).divide(new BigDecimal("100"));

		QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
		queryFilter.addFilter("memberId=",memberId);
		CustomerIntegral customerIntegral = customerIntegralService.get(queryFilter);
		BigDecimal add = applicationAmount.add(fee);
		/*int i = customerIntegral.getAvailableIntegral().compareTo(add);
		//判断余额>提现金额+手续费
		if(i<0){
			result.setSuccess(false).setMsg("dianziquanyuebuzu");//余额不足
			return result;
		}*/

		//验证账户币余额是否充足
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
		Map<String, String> vmap=new HashMap<String,String>();
		vmap.put("memberId", memberId.toString());
		vmap.put("coinCode", platCoin);
		BigDecimal coincount=customerIntegralService.platCoinCount(add);
		vmap.put("coinCount", coincount.toString());
		result=customerIntegralService.validateExaccount(vmap);
		if(!result.getSuccess()){
			return result;
		}

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
		try {
			//生成提现申请记录
			ShopWithdrawRecord shopWithdrawRecord = new ShopWithdrawRecord();
			String requestNo = SNUtil.create15();
			shopWithdrawRecord.setWithdrawSn(requestNo);
			shopWithdrawRecord.setMemberId(memberId);
			shopWithdrawRecord.setWay(way);
			shopWithdrawRecord.setBankName(bankName);
			shopWithdrawRecord.setAccountNumber(accountNumber);
			shopWithdrawRecord.setApplicationAmount(applicationAmount);
			shopWithdrawRecord.setHandlingFee(fee);
			shopWithdrawRecord.setMarkImg(markImg);
			shopWithdrawRecord.setStatus(1);//状态（1待审核，2审核通过。3审核驳回）
			shopWithdrawRecord.setSource(3);  //数据来源（1 后台充值，2 后台扣减。3 用户提现）
			shopWithdrawRecordService.save(shopWithdrawRecord);

			//冻结金额
			//4.电子劵扣款
			Map<String, String> map1 = new HashMap<>();
			map1.put("id", customerIntegral.getId().toString());
			map1.put("type", CustomerIntegral.TYPE_FREEZE);
			map1.put("integralCount", add.toString());
			map1.put("tradingDetail", "申请提现");
			map1.put("businessType", "8");//
			map1.put("requestNo", requestNo);//流水号
			boolean flag1 = customerIntegralService.updateInteger(map1);
			if (!flag1) {
				transactionManager.rollback(status);
				result.setSuccess(false).setMsg("tijiaoshibai"); //提交失败
				return result;
			}

			transactionManager.commit(status);
		}catch (Exception e){
			e.printStackTrace();
			transactionManager.rollback(status);
			result.setSuccess(false).setMsg("tijiaoshibai"); //提交失败
			return result;
		}
		result.setSuccess(true).setMsg("tijiaochenggong"); //提交成功
		return result;
	}

	@Override
	public JsonResult isWithdraw(Long memberId, BigDecimal applicationAmount){
		JsonResult withdraw = shopWithdrawRecordService.isWithdraw(memberId, applicationAmount);
		return  withdraw;
	}

}
