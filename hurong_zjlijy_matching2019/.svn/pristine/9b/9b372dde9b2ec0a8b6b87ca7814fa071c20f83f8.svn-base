/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-01-16 15:41:31 
 */
package hry.mall.integral.service.impl;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.dao.CustomerIntegralDao;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.IntegralCommission;
import hry.mall.integral.model.IntegralConfig;
import hry.mall.integral.model.IntegralDetail;
import hry.mall.integral.model.vo.CoinRewardMessage;
import hry.mall.integral.service.CustomerIntegralService;
import hry.mall.integral.service.IntegralCommissionService;
import hry.mall.integral.service.IntegralConfigService;
import hry.mall.integral.service.IntegralDetailService;
import hry.mall.order.model.vo.ExDigitalmoneyAccount;
import hry.mall.order.service.EvaluateGoodsService;
import hry.manage.remote.RemoteMallExDmTransactionService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.ExDmTransactionManage;
import hry.manage.remote.model.User;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.BaseConfUtil;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.UserRedisUtils;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> CustomerIntegralService </p>
 * @author:         zhouming
 * @Date :          2019-01-16 15:41:31  
 */
@Service("customerIntegralService")
public class CustomerIntegralServiceImpl extends BaseServiceImpl<CustomerIntegral, Long> implements CustomerIntegralService {
	
	@Resource(name="customerIntegralDao")
	@Override
	public void setDao(BaseDao<CustomerIntegral, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private IntegralDetailService integralDetailService;
	
	@Resource
	private IntegralConfigService integralConfigService;
	
	@Resource
    private MessageProducer messageProducer;
	
	@Resource
	private IntegralCommissionService integralCommissionService;
	@Resource
	private EvaluateGoodsService evaluateGoodsService;
	@Override
	public List<Map<String,Object>> findByTotal() {
		return ((CustomerIntegralDao)dao).findByTotal();
	}


	@Override
	public boolean updateInteger(Map<String, String> map) {
		if(!map.containsKey("id") || !map.containsKey("type")){
			return false;
		}
		String id = map.get("id");//用户积分账户Id
		//操作类型  add：加积分  sub：减积分  freeze：冻结积分  freeze_success：冻结转成功（冻结减，总积分减）
		//          freeze_fail：冻结转失败（冻结减，总积分加，可用积分加） 请查看CustomerIntegral中常量
		String type = map.get("type");
		String detailId = "";//积分明细ID
		String integralCount = "";//积分数量
		String requestNo = "";//流水号  用来做业务表与流水表关联
		String tradingDetail = "";//交易详情
		String businessType = "0";//业务类型

		if(map.containsKey("requestNo")){
			requestNo = map.get("requestNo");
		}
		if(map.containsKey("tradingDetail")){
			tradingDetail = map.get("tradingDetail");
		}
		if(map.containsKey("businessType")){
			businessType = map.get("businessType");
		}

		//加 减 冻结
		if(CustomerIntegral.TYPE_SUB.equals(type) || CustomerIntegral.TYPE_FREEZE.equals(type) || CustomerIntegral.TYPE_ADD.equals(type)){
			if(!map.containsKey("integralCount")){
				return false;
			}
			integralCount = map.get("integralCount");//积分数量
		}else{
			if(!map.containsKey("detailId")){
				return false;
			}
			detailId = map.get("detailId");//积分明细ID
		}

		//加 减 冻结
		if (CustomerIntegral.TYPE_SUB.equals(type) || CustomerIntegral.TYPE_FREEZE.equals(type) || CustomerIntegral.TYPE_ADD.equals(type)) {
			//查询积分账户
			CustomerIntegral customerIntegral = super.get(Long.valueOf(id));
			if(type.equals(CustomerIntegral.TYPE_SUB) || type.equals(CustomerIntegral.TYPE_FREEZE)){//当减或者冻结时，需判断可用积分值是否充足
//				if(customerIntegral.getAvailableIntegral().compareTo(new BigDecimal(integralCount))<0){
//					return false;//操作失败
//				}
				//验证账户币余额是否充足
				String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
				Map<String, String> vmap=new HashMap<String,String>();
				vmap.put("memberId", customerIntegral.getMemberId().toString());
				vmap.put("coinCode", platCoin);
				BigDecimal coincount=this.platCoinCount(new BigDecimal(integralCount));
				vmap.put("coinCount", coincount.toString());
				JsonResult result = this.validateExaccount(vmap);
				if(!result.getSuccess()){
					return result.getSuccess();
				}

			}
			//操作积分账户
			if(type.equals(CustomerIntegral.TYPE_ADD)){//加积分
				customerIntegral.setTotalIntegral(customerIntegral.getTotalIntegral().add(new BigDecimal(integralCount)));//总积分值  加
				customerIntegral.setAvailableIntegral(customerIntegral.getAvailableIntegral().add(new BigDecimal(integralCount)));//可用积分值  加
			}else if(type.equals(CustomerIntegral.TYPE_SUB)){//减积分
				customerIntegral.setTotalIntegral(customerIntegral.getTotalIntegral().subtract(new BigDecimal(integralCount)));//总积分值  减
				customerIntegral.setAvailableIntegral(customerIntegral.getAvailableIntegral().subtract(new BigDecimal(integralCount)));//可用积分值 减
			}else{//冻结积分
				customerIntegral.setAvailableIntegral(customerIntegral.getAvailableIntegral().subtract(new BigDecimal(integralCount)));//可用积分值 减
				customerIntegral.setFreezeIntegral(customerIntegral.getFreezeIntegral().add(new BigDecimal(integralCount)));//冻结积分值 加
			}

			//增加积分明细表流水
			IntegralDetail integralDetail = new IntegralDetail();
			integralDetail.setMemberId(customerIntegral.getMemberId());//用户ID
			integralDetail.setIntegralCount(new BigDecimal(integralCount));//积分值
			if(type.equals(CustomerIntegral.TYPE_SUB) || type.equals(CustomerIntegral.TYPE_FREEZE)){//减和冻结
				integralDetail.setCallbackintegralCount(new BigDecimal(integralCount));//积分回收
			}else{//加
				integralDetail.setRewardintegralCount(new BigDecimal(integralCount));//积分发放
			}
			//交易状态  1:交易中 2：交易完成 3：交易失败
			int tradingType = 2;
			if(type.equals(CustomerIntegral.TYPE_FREEZE)){//冻结
				tradingType = 1;
			}
			integralDetail.setTradingType(tradingType);
			if(requestNo != null && !"".equals(requestNo)){
				integralDetail.setRequestNo(requestNo);//流水号
			}
			if(tradingDetail != null && !"".equals(tradingDetail)){
				integralDetail.setTradingDetail(tradingDetail);//交易详情
			}
			if(businessType != null && !"".equals(businessType)){
				integralDetail.setBusinessType(Integer.valueOf(businessType));//业务类型
			}
			integralDetail.setFinishDate(new Date());//完成时间
			integralDetail.setDetailStatus(1);//原有代表状态改为1
			super.update(customerIntegral);
			integralDetailService.save(integralDetail);
			//如果是积分增加操作，则要判断是否推送到社交
			if(CustomerIntegral.TYPE_ADD.equals(type) &&  "99".equals(businessType)){//会员初始化币账户
				this.initializationCurrencyAccount(integralDetail);
			}else if(CustomerIntegral.TYPE_ADD.equals(type)){
				this.handIntegralCoin(integralDetail);
			}else if(CustomerIntegral.TYPE_SUB.equals(type) && "7".equals(businessType) ){ //会员升级扣币
				this.handSubIntegralCoin(integralDetail);
			}else if(CustomerIntegral.TYPE_FREEZE.equals(type)){
				//冻结积分（热账户减。冷账户加）
				this.freezeIntegralCoin(integralDetail);
			}
			return true;
		} else {//冻结转成功  冻结转失败
			IntegralDetail integralDetail = integralDetailService.get(Long.valueOf(detailId));
			if(integralDetail == null){
				return  false;
			}
			//修改积分账户表
			CustomerIntegral customerIntegral = super.get(Long.valueOf(id));
			if(type.equals(CustomerIntegral.TYPE_FREEZE_SUCCESS)){//冻结转成功
				customerIntegral.setTotalIntegral(customerIntegral.getTotalIntegral().subtract(integralDetail.getIntegralCount()));//总积分值  减
				customerIntegral.setFreezeIntegral(customerIntegral.getFreezeIntegral().subtract(integralDetail.getIntegralCount()));//冻结积分值 减
				integralDetail.setTradingType(2);//2：交易完成
				integralDetail.setFinishDate(new Date());//完成时间
			}else{//冻结转失败
				customerIntegral.setAvailableIntegral(customerIntegral.getAvailableIntegral().add(integralDetail.getIntegralCount()));//可用积分值  加
				customerIntegral.setFreezeIntegral(customerIntegral.getFreezeIntegral().subtract(integralDetail.getIntegralCount()));//冻结积分值 减
				integralDetail.setTradingType(3);//3：交易失败
				integralDetail.setFinishDate(new Date());//完成时间
			}
			super.update(customerIntegral);
			integralDetailService.save(integralDetail);
			return true;
		}
	}


	@Override
	public void handIntegralCoin(IntegralDetail integralDetail) {
		// TODO Auto-generated method stub
			Boolean flag=true;
			String  type="0";
			if(6==integralDetail.getBusinessType()){
			QueryFilter filter =new QueryFilter(IntegralCommission.class);
			filter.addFilter("number=", integralDetail.getRequestNo());
			List <IntegralCommission> list=integralCommissionService.find(filter);
			 if(null!=list && list.size()>0){
				 IntegralCommission  commission=list.get(0);
				 type=commission.getType();
				 if("3".equals(commission.getType()) || "4".equals(commission.getType()) ){
					 flag=false;
				 }
			 }
			}
			//如果积分是立即到账，再改变账户或者推送到社交
		       if(flag && 1==integralDetail.getDetailStatus()){
		    	//11、推给社交或者操作币账户
				String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
			     // 获取 平台币市价(RMB)
		        String platformMarkPriceStr = BaseConfUtil.getConfigSingle("platformMarkPrice", "configCache:basefinanceConfig");
		        BigDecimal platformMarkPrice = new BigDecimal(platformMarkPriceStr);
		    	//(1)、查询购物返利配置
				IntegralConfig integralConfig = integralConfigService.get((long)1);
				//积分币值
				BigDecimal coinPrice = integralConfig.getCoinPrice();
				//计算积分对应平台币的数量
		        BigDecimal count=coinPrice.multiply(integralDetail.getRewardintegralCount()).divide(platformMarkPrice, 10, BigDecimal.ROUND_HALF_UP);
		        integralDetail.setCoinCode(platCoin);//平台币种
		        integralDetail.setCoinCount(count);//推送平台币数量
		        integralDetail.setFactCoinCount(count);//实际到账数量
		        integralDetailService.update(integralDetail);
				//广告奖励的单独操作币账户
		        if(9==integralDetail.getBusinessType()){
		    		//封装参数，操作币业务流水和账户
					Map<String,String> map2=new HashMap<String,String>();
					map2.put("coinCode", platCoin);
					map2.put("memberId", integralDetail.getMemberId().toString());
					map2.put("transactionType", "1");// 交易类型(1币收入 ，2币支出)
					map2.put("coinCount", count.toString());
					map2.put("OrderNo", "GGJL"+integralDetail.getId().toString());
					map2.put("optType", "514");//广告奖励
					this.handCoin(map2);
		        }
		        else{
				//是否推送给社交
				if(1==integralConfig.getIsToSocial()){
					//(2)推送给社交
					CoinRewardMessage message=new CoinRewardMessage();
					message.setCoinCode(platCoin);
					message.setCustomerId(Long.valueOf(integralDetail.getMemberId()));
					message.setRewardNum(count);
					message.setBusinessNum(integralDetail.getId().toString());
					message.setBusinessType("1");
				    Integer	rewardType=2; //奖励类型 1:算力奖励 2:任务奖励 3:矿机奖励 4:消费奖励 5:分销奖励会员静态   6   hystatic_reward（自购会员的流量补贴）
				//    会员分享   7   hydynamic_reward（购买会员直接推荐）
				//    会员拓展   8   hybonus_reward（购买会员间接推荐）
				//    会员分红   9   hybonus_reward（购买会员团队收入分红）
				    if("1".equals(type)){
				    	rewardType=7;
				    }else if("2".equals(type)){
				    	rewardType=8;
				    }else if("5".equals(type)){
				    	rewardType=6;
				    }
		            //胡一茗有哪些奖励               
				    message.setRewardType(rewardType);
				    messageProducer.toSocialReward(JSON.toJSONString(message));
				}else{
					//(3)无需推送社交，直接操作币账户
				/*		ExDigitalmoneyAccountRedis dmAccount=UserRedisUtils.getAccountRedis(integralDetail.getMemberId().toString(), platCoin);
					     // 热账户减少
				        Accountadd accountadd = new Accountadd();
				        accountadd.setAccountId(dmAccount.getId());
				        accountadd.setMoney(count);
				        accountadd.setMonteyType(1);//1是操作热账户，其他是操作冻结账户
				        accountadd.setAcccountType(1);
				        accountadd.setRemarks(511); //会员奖励
				        accountadd.setTransactionNum("JFJL"+integralDetail.getId().toString());//积分奖励
				        List<Accountadd> list = new ArrayList<Accountadd>();
				        list.add(accountadd);
				        messageProducer.toAccount(JSON.toJSONString(list));*/
				        
						//封装参数，操作币业务流水和账户
						Map<String,String> map2=new HashMap<String,String>();
						map2.put("coinCode", platCoin);
						map2.put("memberId", integralDetail.getMemberId().toString());
						map2.put("transactionType", "1");// 交易类型(1币收入 ，2币支出)
						map2.put("coinCount", count.toString());
						map2.put("OrderNo", "JFJL"+integralDetail.getId().toString());
						map2.put("optType", "511");//广告奖励
						this.handCoin(map2);
				  }	
				}
				}
	
	}


	@Override
	public BigDecimal platCoinCount(BigDecimal count) {
		// TODO Auto-generated method stub
	     // 获取 平台币市价(RMB)
       String platformMarkPriceStr = BaseConfUtil.getConfigSingle("platformMarkPrice", "configCache:basefinanceConfig");
       BigDecimal platformMarkPrice = new BigDecimal(platformMarkPriceStr);
   	  //(1)、查询购物返利配置
		IntegralConfig integralConfig = integralConfigService.get((long)1);
		//积分币值
		BigDecimal coinPrice = integralConfig.getCoinPrice();
		//计算积分对应平台币的数量
        BigDecimal platcount=coinPrice.multiply(count).divide(platformMarkPrice, 10, BigDecimal.ROUND_HALF_UP);
		return platcount;
	}


	@Override
	public JsonResult validateExaccount(Map<String, String> map) {
		// TODO Auto-generated method stub
		JsonResult result =new JsonResult();
		String memberId=map.get("memberId");
		String coinCode=map.get("coinCode");
		String coinCount=map.get("coinCount");
		//1、查询比账户
		ExDigitalmoneyAccountRedis dmAccount=UserRedisUtils.getAccountRedis(memberId, coinCode);
		if(null==dmAccount){
			return result.setSuccess(false).setMsg("meiyoubizhanghu");//没有相应币账户信息
		}
		//如果币账户余额小于需支付金额
		if(dmAccount.getHotMoney().compareTo(new BigDecimal(coinCount))<0){
			return result.setSuccess(false).setMsg("zhanghuyuebuzu");//账户余额不足
		}
		return result.setSuccess(true);
	}


	@Override
	public void handSubIntegralCoin(IntegralDetail integralDetail) {
		// TODO Auto-generated method stub
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
		BigDecimal platcount=this.platCoinCount(integralDetail.getCallbackintegralCount());
		integralDetail.setCoinCode(platCoin);
		integralDetail.setCoinCount(platcount);
		integralDetail.setFactCoinCount(platcount);
		integralDetailService.update(integralDetail);
		//封装参数，操作币业务流水和账户
		Map<String,String> map=new HashMap<String,String>();
		map.put("coinCode", platCoin);
		map.put("memberId", integralDetail.getMemberId().toString());
		map.put("transactionType", "2");// 交易类型(1币收入 ，2币支出)
		map.put("coinCount", platcount.toString());
		map.put("OrderNo", "GMHY"+integralDetail.getId().toString());
		map.put("optType", "509");
        this.handCoin(map);
	}

	/**
	 * 冻结平台币
	 * @param integralDetail
	 */
	@Override
	public void freezeIntegralCoin(IntegralDetail integralDetail){
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
		BigDecimal platcount=integralDetail.getIntegralCount();
		integralDetail.setCoinCode(platCoin);
		integralDetail.setCoinCount(platcount);
		integralDetail.setFactCoinCount(platcount);
		integralDetailService.update(integralDetail);
		ExDigitalmoneyAccountRedis dmAccount=UserRedisUtils.getAccountRedis(integralDetail.getMemberId().toString(), integralDetail.getCoinCode());


		RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
		RemoteMallExDmTransactionService remoteMallExDmTransactionService = SpringUtil.getBean("remoteMallExDmTransactionService");
		User user = remoteManageService.getUserByCustomerId(integralDetail.getMemberId());
		String commonLanguage = user.getCommonLanguage();
		commonLanguage = StringUtils.isEmpty(commonLanguage) ? "zh_CN" : commonLanguage;
		// 交易所流水
		ExDmTransactionManage exDmTransaction = new ExDmTransactionManage();
		exDmTransaction.setCustomerId(integralDetail.getMemberId());
		String transactionNum = NumConstant.Ex_Dm_Transaction;
		exDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
		exDmTransaction.setAccountId(dmAccount.getId());
		exDmTransaction.setTransactionType(2);  // 交易类型(1币收入 ，2币支出)
		exDmTransaction.setTransactionMoney(platcount); // 币的数量
		exDmTransaction.setCustomerName(user.getNickName());
		exDmTransaction.setStatus(1);   // 状态 1待审核 --2已完成 -- 3以否决
		exDmTransaction.setCoinCode(platCoin);
		exDmTransaction.setCurrencyType(commonLanguage);
		exDmTransaction.setFee(BigDecimal.ZERO);
		exDmTransaction.setOrderNo(integralDetail.getRequestNo());
		exDmTransaction.setOptType(Integer.valueOf(80));//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
		// 保存订单
		remoteMallExDmTransactionService.saveExDmTransactionExDmTransaction(exDmTransaction);

		// 热账户减少
		Accountadd accountadd = new Accountadd();
		accountadd.setAccountId(dmAccount.getId());
		accountadd.setMoney((platcount).multiply(new BigDecimal(-1)));
		accountadd.setMonteyType(1);//1是操作热账户，其他是操作冻结账户
		accountadd.setAcccountType(1);
		accountadd.setRemarks(80);//币转出申请,可用减少,冻结增加
		accountadd.setTransactionNum("RZHTX"+integralDetail.getId().toString());//热账户提现
		List<Accountadd> list = new ArrayList<Accountadd>();
		list.add(accountadd);
		messageProducer.toAccount(JSON.toJSONString(list));
		//冷账户增加
		Accountadd accountadd2 = new Accountadd();
		accountadd2.setAccountId(dmAccount.getId());
		accountadd2.setMoney(platcount);
		accountadd2.setMonteyType(2);//1是操作热账户，其他是操作冻结账户
		accountadd2.setAcccountType(1);
		accountadd2.setRemarks(80);//币转出申请,可用减少,冻结增加
		accountadd2.setTransactionNum("LZHTX"+integralDetail.getId().toString());//冷账户提现
		List<Accountadd> list2 = new ArrayList<Accountadd>();
		list2.add(accountadd2);
		messageProducer.toAccount(JSON.toJSONString(list2));

	}
	/**
	 * 数据导入初始化币账户
	 * @param integralDetail
	 */
	@Override
	public void initializationCurrencyAccount(IntegralDetail integralDetail){
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
		BigDecimal platcount=this.platCoinCount(integralDetail.getIntegralCount());
		integralDetail.setCoinCode(platCoin);
		integralDetail.setCoinCount(platcount);
		integralDetail.setFactCoinCount(platcount);
		integralDetailService.update(integralDetail);
		Map<String, Object> map = new HashMap<>();
		map.put("customerId",integralDetail.getMemberId());
		map.put("coinCode",integralDetail.getCoinCode());
		//未登录没存Redis
		ExDigitalmoneyAccount exDigitalmoneyAccount = evaluateGoodsService.getExDigitalmoneyAccount(map);

		// 热账户增加
		Accountadd accountadd = new Accountadd();
		accountadd.setAccountId(exDigitalmoneyAccount.getId());
		accountadd.setMoney(platcount);
		accountadd.setMonteyType(1);//1是操作热账户，其他是操作冻结账户
		accountadd.setAcccountType(1);
		accountadd.setRemarks(27);//    TYPE27("手动充值外部接口", 27),
		accountadd.setTransactionNum("BZHCSH"+integralDetail.getId().toString());//币账户初始化金额
		List<Accountadd> list = new ArrayList<Accountadd>();
		list.add(accountadd);
		messageProducer.toAccount(JSON.toJSONString(list));

	}

	//查询出没有默认会员的用户
	@Override
	public List<CustomerIntegral> findNoDefaultLevel(){
		return ((CustomerIntegralDao)dao).findNoDefaultLevel();
	}


	@Override
	public void handCoin(Map<String, String> map) {
		// TODO Auto-generated method stub
		String coinCode=map.get("coinCode");
		String smemberId=map.get("memberId");
		String transactionType=map.get("transactionType");// 交易类型(1币收入 ，2币支出)
		String coinCount=map.get("coinCount");//币数量
		String orderNo=map.get("OrderNo");//业务编号
		String optType=map.get("optType");//类别TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
		Long memberId=Long.valueOf(smemberId);
		ExDigitalmoneyAccountRedis dmAccount=UserRedisUtils.getAccountRedis(smemberId, coinCode);
			
		 RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
		 RemoteMallExDmTransactionService remoteMallExDmTransactionService = SpringUtil.getBean("remoteMallExDmTransactionService");
	     User user = remoteManageService.getUserByCustomerId(memberId);
         String commonLanguage = user.getCommonLanguage();
         commonLanguage = StringUtils.isEmpty(commonLanguage) ? "zh_CN" : commonLanguage;
         // 交易所流水
         ExDmTransactionManage exDmTransaction = new ExDmTransactionManage();
         exDmTransaction.setCustomerId(memberId);
         String transactionNum = NumConstant.Ex_Dm_Transaction;
         exDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
         exDmTransaction.setAccountId(dmAccount.getId());
         exDmTransaction.setTransactionType(Integer.valueOf(transactionType));  // 交易类型(1币收入 ，2币支出)
         exDmTransaction.setTransactionMoney(new BigDecimal(coinCount));
         exDmTransaction.setCustomerName(user.getNickName());
         exDmTransaction.setStatus(2);   // 状态 1待审核 --2已完成 -- 3以否决
         exDmTransaction.setCoinCode(coinCode);
         exDmTransaction.setCurrencyType(commonLanguage);
         exDmTransaction.setFee(BigDecimal.ZERO);
         exDmTransaction.setOrderNo(orderNo);
         exDmTransaction.setOptType(Integer.valueOf(optType));//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
         // 保存订单
         remoteMallExDmTransactionService.saveExDmTransactionExDmTransaction(exDmTransaction);
		
	     // 热账户减少
	   Accountadd accountadd = new Accountadd();
	   accountadd.setAccountId(dmAccount.getId());
	   if("1".equals(transactionType)){//1为收入
		   accountadd.setMoney((new BigDecimal(coinCount)));
	   }else if("2".equals(transactionType)){//2为支出
		   accountadd.setMoney((new BigDecimal(coinCount)).multiply(new BigDecimal(-1)));
	   }
	   accountadd.setMonteyType(1);//1是操作热账户，其他是操作冻结账户
	   accountadd.setAcccountType(1);
	   accountadd.setRemarks(Integer.valueOf(optType));//类型
	   accountadd.setTransactionNum(orderNo);//业务编号
	   List<Accountadd> list = new ArrayList<Accountadd>();
	   list.add(accountadd);
	   messageProducer.toAccount(JSON.toJSONString(list));
		
	}
}
