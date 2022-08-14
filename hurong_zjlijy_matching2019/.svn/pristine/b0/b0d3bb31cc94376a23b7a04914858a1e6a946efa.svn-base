/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0
 * @Date:        2019-01-08 17:32:03
 */
package hry.mall.integral.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.dao.IntegralDetailDao;
import hry.mall.integral.model.*;
import hry.mall.integral.model.vo.CoinRewardMessage;
import hry.mall.integral.service.*;
import hry.mall.order.model.Order;
import hry.mall.order.service.OrderService;
import hry.manage.remote.RemoteMallService;
import hry.manage.remote.model.AppCustomer;
import hry.manage.remote.model.mall.AppCommendUser;
import hry.manage.remote.model.mall.AppPersonInfo;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.BaseConfUtil;
import hry.util.BeanToBean;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.UserRedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> IntegralDetailService </p>
 * @author:         kongdb
 * @Date :          2019-01-08 17:32:03
 */
@Service("integralDetailService")
public class IntegralDetailServiceImpl extends BaseServiceImpl<IntegralDetail, Long> implements IntegralDetailService {

	
	@Resource(name="integralDetailDao")
	@Override
	public void setDao(BaseDao<IntegralDetail, Long> dao) {
		super.dao = dao;
	}

	@Resource
	IntegralAccountService integralAccountService;

	@Resource
	IntegralTaskMiningService taskMiningService;

	@Resource
	IntegralDetailService integralDetailService;

	@Resource
	IntegralRebateConfigService integralRebateConfigService;

	@Resource
	IntegralConfigService integralConfigService;

	@Resource
	IntegralDistributionGradeService distributionGradeService;

	@Resource
	IntegralSignService integralSignService;

	@Resource
	CustomerIntegralService customerIntegralService;

	@Resource
	OrderService orderService;
	
	@Resource
    private MessageProducer messageProducer;

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		String memberName = filter.getRequest().getParameter("memberName");
		String rewardType = filter.getRequest().getParameter("rewardType");
		String memberTruename = filter.getRequest().getParameter("memberTruename");
		String orderDate = filter.getRequest().getParameter("orderDate");
		String rewardDate = filter.getRequest().getParameter("rewardDate");
		String detailStatus = filter.getRequest().getParameter("detailStatus");
		String rewardLevel = filter.getRequest().getParameter("rewardLevel");
		String taskName = filter.getRequest().getParameter("taskName");
		String finishDate = filter.getRequest().getParameter("finishDate");
		if (!StringUtils.isEmpty(rewardType)) {
			filter.addFilter("rewardType=", rewardType);
		}
		if (!StringUtils.isEmpty(memberName)) {
			filter.addFilter("memberName_like","%"+memberName+"%");
		}
		if (!StringUtils.isEmpty(memberTruename)) {
			filter.addFilter("memberTruename_like", "%"+memberTruename+"%");
		}
		if (!StringUtils.isEmpty(detailStatus)) {
			filter.addFilter("detailStatus=", detailStatus);
		}
		if (!StringUtils.isEmpty(taskName)) {
			filter.addFilter("taskName_like", "%"+taskName+"%");
		}
		if (!StringUtils.isEmpty(rewardLevel)) {
			filter.addFilter("rewardLevel_like", "%"+rewardLevel+"%");
		}
		if (!StringUtils.isEmpty(orderDate)) {
			filter.addFilter("orderDate>", orderDate + "00:00:00");
		}
		if (!StringUtils.isEmpty(rewardDate)) {
			filter.addFilter("orderDate<", orderDate + "59:59:59");
		}
		if (!StringUtils.isEmpty(rewardDate)) {
			filter.addFilter("rewardDate>", rewardDate + "00:00:00");
		}
		if (!StringUtils.isEmpty(rewardDate)) {
			filter.addFilter("rewardDate<", rewardDate + "59:59:59");
		}
		if (!StringUtils.isEmpty(finishDate)) {
			filter.addFilter("finishDate>", finishDate + "00:00:00");
		}
		if (!StringUtils.isEmpty(finishDate)) {
			filter.addFilter("finishDate<", finishDate + "59:59:59");
		}
		Page<IntegralDetail> page = integralDetailService.findPage(filter);
		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}

	@Override
	public Map<String, Object> findbyKey(Map<String, Object> map) {
		return ((IntegralDetailDao)dao).findbyKey(map);
	}

	@Override
	public Map<String,Object> findByRewardType(Map<String, Object> map) {
		return ((IntegralDetailDao)dao).findByRewardType(map);
	}
	
	@Override
	public JsonResult addIntegralDetai(IntegralDetail detail) {

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
	
	//------积分方法重写------2019-07-24 by luyue
	
	//保存积分明细
		public IntegralDetail saveDetail(Map<String, String> map){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			//用户id
			String memberId=map.get("memberId");
			BigDecimal integralCount=new BigDecimal(map.get("integralCount"));//积分数量
			//积分数量>0再处理
			if(integralCount.compareTo(new BigDecimal("0"))>0){
				BigDecimal computingCount=new BigDecimal(map.get("computingCount"));//算力值
				//返积分类型------2、任务返积分；0、购物返积分；5、购物分销返积分
			    String type=map.get("type");
				//1、查询用户信息
				RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");
				List<AppCustomer> appCustomerList = remoteMallService.findById(Long.valueOf(memberId));
				AppCustomer appCustomer = appCustomerList.get(0);
				//2、查询积分账户
				QueryFilter queryCusIntegral = new QueryFilter(CustomerIntegral.class);
				queryCusIntegral.addFilter("memberId=",appCustomer.getId());
				CustomerIntegral customerIntegral = customerIntegralService.get(queryCusIntegral);
				//3、保存积分明细
				IntegralDetail integralDetail=new IntegralDetail();
				integralDetail.setMemberId(appCustomer.getId());
				integralDetail.setAccountId(customerIntegral.getId());
				integralDetail.setIntegralCount( customerIntegral.getTotalIntegral());
				
				integralDetail.setRewardintegralCount(integralCount);
				integralDetail.setRewardComputingCount(computingCount);
				integralDetail.setRewardDate(new Date());
				integralDetail.setEstimateRewardDate(new Date());
				integralDetail.setFinishDate(new Date());
				AppPersonInfo appPersonInfo = new AppPersonInfo();
				BeanToBean.convertBean2Bean(appCustomer.getAppPersonInfo(),appPersonInfo);
				if( null!=appPersonInfo.getMobilePhone()  && !"".equals(appPersonInfo.getMobilePhone())){
					integralDetail.setBuyerName(appPersonInfo.getMobilePhone());
					integralDetail.setMemberName(appPersonInfo.getMobilePhone());
				}else {
					integralDetail.setBuyerName(appPersonInfo.getEmail());
					integralDetail.setMemberName(appPersonInfo.getEmail());
				}
				integralDetail.setMemberTruename(appPersonInfo.getSurname()+appPersonInfo.getTrueName());
				integralDetail.setCardNumber(appPersonInfo.getCardId());
				integralDetail.setRewardLevel("0级");
				integralDetail.setBuyerId(appCustomer.getId());
				integralDetail.setOrderSn(sdf.format(new Date()));
				String taskName=map.get("taskName");
				String taskId=map.get("taskId");
				integralDetail.setTaskName(taskName);
				integralDetail.setTaskId(Long.valueOf(taskId));
				//4、任务返积分
				if("2".equals(type)){//任务返积分
					String taskKey=map.get("taskKey");
					integralDetail.setDetailStatus(1);
					integralDetail.setRewardType(2);
					//判断该任务是否推荐奖励任务
					if(taskKey.equals("recommendKey")){
						String recommendMemberId=map.get("recommendMemberId");	
						integralDetail.setRewardLevel("1级");
						//保存被推荐人的信息，及该奖励是因为谁注册得到的
						integralDetail.setBuyerId(Long.valueOf(recommendMemberId));
						List<AppCustomer> appCustomerList1 = remoteMallService.findById(Long.valueOf(recommendMemberId));
						AppCustomer appCustomer1 = appCustomerList1.get(0);
						AppPersonInfo appPersonInfo1 = (AppPersonInfo)appCustomer1.getAppPersonInfo();
						integralDetail.setBuyerId(appCustomer1.getId());
						if( null!=appPersonInfo1.getMobilePhone()  && !"".equals(appPersonInfo1.getMobilePhone())){
							integralDetail.setBuyerName(appPersonInfo1.getMobilePhone());
						}else {
							integralDetail.setBuyerName(appPersonInfo1.getEmail());
						}
					}
				}//5、处理订单相关返积分---购物返积分以及推荐购物返积分
				 else {
					String orderId=map.get("orderId");
					Order order=orderService.get(Long.valueOf(orderId));
					integralDetail.setOrderSn(order.getOrderSn());
					integralDetail.setOrderDate(order.getCreated());
					integralDetail.setOrderId(order.getId());
					integralDetail.setOrderMoney(order.getOrderAmount());
					integralDetail.setBuyerId(order.getMemberId());
					integralDetail.setBuyerName(order.getUserName());
					String currentTime=map.get("currentTime");
					Date date = new Date(Long.valueOf(currentTime));
					integralDetail.setEstimateRewardDate(date);	
					if("0".equals(type)){//6、购物返积分保存明细
						integralDetail.setRewardType(0);
						integralDetail.setDetailStatus(0);
					}else if("5".equals(type)){//购物分销(给推荐人)返积分保存明细
						integralDetail.setRewardType(5);
						integralDetail.setDetailStatus(0);
						String number=map.get("nuber");
						integralDetail.setRewardLevel(number+"级");
					}
				}  
				integralDetailService.save(integralDetail);
				//10、更改积分账户
				if("2".equals(type)){//任务积分立即到账
					BigDecimal availableIntegral = customerIntegral.getAvailableIntegral().add(integralCount);//余额增加
					BigDecimal totalIntegral = customerIntegral.getTotalIntegral().add(integralCount);//总额增加
					customerIntegral.setAvailableIntegral(availableIntegral);
					customerIntegral.setTotalIntegral(totalIntegral);
				}else{
					//购物返积分需要先冻结
					BigDecimal freezeIntegral = customerIntegral.getFreezeIntegral().add(integralCount);
					BigDecimal totalIntegral = customerIntegral.getTotalIntegral().add(integralCount);
					customerIntegral.setFreezeIntegral(freezeIntegral);
					customerIntegral.setTotalIntegral(totalIntegral);
					customerIntegralService.update(customerIntegral);
				}
				customerIntegralService.update(customerIntegral);
				//如果积分是立即到账，再改变账户或者推送到社交
		       if(1==integralDetail.getDetailStatus()){
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
		        BigDecimal count=coinPrice.multiply(integralCount).divide(platformMarkPrice, 10, BigDecimal.ROUND_HALF_UP);
		        integralDetail.setCoinCode(platCoin);//平台币种
		        integralDetail.setCoinCount(count);//推送平台币数量
		        integralDetail.setFactCoinCount(count);//实际到账数量
		        integralDetailService.update(integralDetail);
				//是否推送给社交
				if(1==integralConfig.getIsToSocial()){
					//(2)推送给社交
					CoinRewardMessage message=new CoinRewardMessage();
					message.setCoinCode(platCoin);
					message.setCustomerId(Long.valueOf(memberId));
					message.setRewardNum(count);
					message.setBusinessNum(integralDetail.getId().toString());
					message.setBusinessType("1");
				    Integer	rewardType=2; //奖励类型 1:算力奖励 2:任务奖励 3:矿机奖励 4:消费奖励 5:分销奖励
				    if("0".equals(type)){
				    	rewardType=4;
				    }else if("5".equals(type)){
				    	rewardType=5;
				    }
				    message.setRewardType(rewardType);
				    messageProducer.toSocialReward(JSON.toJSONString(message));
				}else{
					//(3)无需推送社交，直接操作币账户
				/*		ExDigitalmoneyAccountRedis dmAccount=UserRedisUtils.getAccountRedis(memberId, platCoin);
					     // 热账户减少
				        Accountadd accountadd = new Accountadd();
				        accountadd.setAccountId(dmAccount.getId());
				        accountadd.setMoney(count);
				        accountadd.setMonteyType(1);//1是操作热账户，其他是操作冻结账户
				        accountadd.setAcccountType(1);
				        accountadd.setRemarks(513);//商城奖励
				        accountadd.setTransactionNum("JFJL"+integralDetail.getId().toString());//积分奖励
				        List<Accountadd> list = new ArrayList<Accountadd>();
				        list.add(accountadd);
				        messageProducer.toAccount(JSON.toJSONString(list));*/
			    	//封装参数，操作币业务流水和账户
						Map<String,String> map2=new HashMap<String,String>();
						map2.put("coinCode", platCoin);
						map2.put("memberId", memberId.toString());
						map2.put("transactionType", "1");// 交易类型(1币收入 ，2币支出)
						map2.put("coinCount", count.toString());
						map2.put("OrderNo", "JFJL"+integralDetail.getId().toString());
						map2.put("optType", "513");//商城奖励
						customerIntegralService.handCoin(map2); 
				}	
					
				}
				
				return integralDetail;
			}
			return null;
		}
	  //处理任务积分
		public JsonResult handTaskIntegral(Map<String, String> map){
			JsonResult jsonObject = new JsonResult();
			try {
				//1、查询任务
				String taskKey=map.get("taskKey").toString();//任务key
				QueryFilter queryTask =  new QueryFilter(IntegralTaskMining.class);
				queryTask.addFilter("taskKey=",taskKey);
				IntegralTaskMining taskMining = taskMiningService.get(queryTask);
				if(null==taskMining){
					jsonObject.setSuccess(false).setMsg("无该任务");
					return jsonObject;
				}
				//2、判断任务是否开启状态
					if (  1 == taskMining.getTaskStatus()) {
						//单次任务
						if (0 == taskMining.getTaskType()) {
							BigDecimal integralCount=new BigDecimal("0");//积分数量
							BigDecimal computingCount=new BigDecimal("0");//算力值
							if(null!=taskMining.getIntegralCount()){
								integralCount=taskMining.getIntegralCount();//任务积分值
							}
							if(null!=taskMining.getComputingCount()){
								computingCount=taskMining.getComputingCount();//算力值
							}
							map.put("integralCount", integralCount.toString());
							map.put("computingCount", computingCount.toString());
							map.put("taskName", taskMining.getTaskName());
							map.put("taskId", taskMining.getId().toString());
							map.put("type", "2");
					       //3、保存积分 明细以及更改用户积分账户
							   this.saveDetail(map);
							//4、如果是注册返积分，查询该用户是否 有直接推荐人，如有，则给直推人返积分
								if (taskKey.equals("register")) {
									RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");
								    Long memberId=Long.valueOf(map.get("memberId"));
									//查询用户是否有推荐码
									AppCommendUser appCommendUser = remoteMallService.findCommendUser(memberId);
									//新注册用户发送分销奖励
									if (null != appCommendUser.getPid()) {
									QueryFilter queryRecommend =  new QueryFilter(IntegralTaskMining.class);
									queryRecommend.addFilter("taskKey=","recommendKey");
									IntegralTaskMining recommendTask = taskMiningService.get(queryRecommend);
									map.put("recommendMemberId", map.get(memberId));
									map.put("memberId", appCommendUser.getPid().toString());
									map.put("integralCount", recommendTask.toString());
									map.put("computingCount", recommendTask.toString());
									map.put("taskName", recommendTask.getTaskImg());
									map.put("taskId", recommendTask.getId().toString());
								    this.saveDetail(map);
								}
							}
						  jsonObject.setSuccess(true).setMsg("添加成功");	
								
						}
					}else {
						jsonObject.setSuccess(false).setMsg("请开启此任务");
						return jsonObject;
					}
			}catch (Exception e){
				e.printStackTrace();
				jsonObject.setSuccess(false).setMsg( "服务器错误");
			}
			return jsonObject;
		}
        /**
         * 取代原方法，作为处理积分任务入口---by luyue
         */
		@Override
		public JsonResult addIntegralDetail1(Long orderId, Long memberId, String accountKey, String taskKey,
				Integer rewardType) {
			// TODO Auto-generated method stub
			JsonResult jsonObject = new JsonResult();
			try {
				Map<String, String> map =new HashMap<String, String>();
				map.put("memberId", memberId.toString());
				map.put("taskKey", taskKey);
					//2.根据奖励类型查询配置  ---2为任务返积分
					if (rewardType == 2) {
						jsonObject=this.handTaskIntegral(map);
					} else if (rewardType == 0) {
						//查询挖矿购物配置
						map.put("orderId", orderId.toString());
						jsonObject=this.handShopIntegralDetail(map);
					} else if (rewardType == 3) {
  
					} else {

					}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return jsonObject;
		}
		
		public JsonResult handShopIntegralDetail(Map<String,String> map){
			JsonResult jsonObject = new JsonResult();
			try {
				Boolean flag=true;
				//1、查询购物返利配置
				IntegralRebateConfig shopConfig = integralRebateConfigService.get((long)1);
				if(0==shopConfig.getStatus()){
					flag=false;
				  jsonObject.setSuccess(false).setMsg( "购物返积分任务未开启");	
				}
				//2、查询积分币信息
				IntegralConfig integralConfig = integralConfigService.get((long)1);
				//积分币值
				BigDecimal coinPrice = integralConfig.getCoinPrice();
				BigDecimal ling =new BigDecimal("0");
				//----如果没有配置币值，则默认为1，以防下面程序报错
				if(coinPrice.compareTo(ling)==0){
					coinPrice=new BigDecimal("1");
				}
				//订单低于shopPrice不返算力
				BigDecimal shopPrice = shopConfig.getShopPrice();
				//订单最高返
				BigDecimal shopMostintegral = shopConfig.getShopMostintegral();
				//订单商品金额
				String orderId=map.get("orderId");
				Order order=orderService.get(Long.valueOf(orderId));
				BigDecimal orderPrice = order.getOrderAmount();
				BigDecimal integralCount=new BigDecimal("0");//返积分数量默认为0
				//3、订单金额小于最低金额不返积分
				if(orderPrice.compareTo(shopPrice)<0){
					flag=false;
					jsonObject.setSuccess(false).setMsg("订单金额过低不返积分");
				}
					 integralCount = orderPrice.multiply(shopConfig.getShopRebateRate()).divide(coinPrice, 0, BigDecimal.ROUND_DOWN).divide(new BigDecimal(100.00));
					//---如果计算所得积分值大于最大值，则取最大值
					if(integralCount.compareTo(shopMostintegral)>=0){
						integralCount = shopMostintegral;
					}
						map.put("integralCount", integralCount.toString());
						map.put("computingCount", "0");
						map.put("taskName", "购物返积分");
						map.put("taskId", "0");
						map.put("type", "0");
						//预计发放时间
						long currentTime = System.currentTimeMillis() + shopConfig.getShopAfter() * 24 * 60 * 60 * 1000;
						map.put("currentTime", Long.valueOf(currentTime).toString());
						//4、保存积分明细及更改用户积分账户啊
						if(flag){
							this.saveDetail(map);
						}
						//5、查询配置了几级分销返利
						List<IntegralDistributionGrade> list = distributionGradeService.findAll();
						//如果购物返积分>0并且配置了几级分销返利，才进行金粉返利
						if(integralCount.compareTo(new BigDecimal("0"))>0 && null!=list && list.size()>0){
							Map<String,String> map1=new HashMap<String,String>();
							map1.put("integralCount", integralCount.toString());
							map1.put("orderId", orderId);
							map1.put("memberId", order.getMemberId().toString());
							map1.put("currentTime", Long.valueOf(currentTime).toString());
							this.handDistributionDetail(map1);
							
						}
						if(flag){
							jsonObject.setSuccess(true).setMsg("成功");		
						}
						
						
			}catch (Exception e){
				e.printStackTrace();
			}
			return jsonObject;

		}
		/**
		 * 处理分销返利逻辑
		 * @param map
		 * @return
		 */
		public JsonResult handDistributionDetail(Map<String,String> map){
			JsonResult jsonResult=new JsonResult();
			//1、查询配置了几级分销返利
			List<IntegralDistributionGrade> list = distributionGradeService.findAll();
		//	IntegralDistributionGrade grade=list.get(0);
			String memberId=map.get("memberId");
			//查询下单用户是否有推荐人
			RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");
			AppCommendUser appCommendUser = remoteMallService.findCommendUser(Long.valueOf(memberId));
			BigDecimal count=new BigDecimal(map.get("integralCount"));
			BigDecimal integralCount=null;
		    if(null!=appCommendUser.getPid() && 0!=appCommendUser.getPid() ){
		    	String sid=appCommendUser.getSid();//,1-34,2-31,
		    	String [] arry=sid.split(",");
				//2、暂定从1级开始返，不从1级开始，
				for(int i=0;i<list.size();i++){
					IntegralDistributionGrade grade=list.get(i);
					Integer number=grade.getGradeLevel();
					integralCount = count.multiply(grade.getIntegralScale()).divide(new BigDecimal(100.00));
					//3、奖励等级+1<=字符串数组的长度，代表用户才有第几级的推荐人，例如，第2级，数组元素个数最少为3，不然没有2级推荐人
					if(number+1<=arry.length){
						String[] ss=arry[number].split("-");
						String cusid=ss[1];//几级推荐人id
						//4、封装参数、保存明细
						Map<String,String> map1=new HashMap<String,String>();
						map1.put("memberId", cusid);
						map1.put("integralCount", integralCount.toString());
						map1.put("computingCount", "0");
						map1.put("taskName", "购物分销返利");
						map1.put("taskId", "0");
						map1.put("type", "5");
						map1.put("orderId", map.get("orderId"));
						map1.put("number", number.toString());
						map1.put("currentTime",map.get("currentTime"));
						//5、调用保存明细方法
						this.saveDetail(map1);
					}else{
						break;
					}
				}	
		    }
		    jsonResult.setSuccess(true).setMsg("操作成功");
			return jsonResult;
		}
		
	
	//-------重写结束---------

	@Override
	public JsonResult issueIntegral(Map<String, String> paramMap) {
		try {
			this.saveDetail(paramMap);
			System.out.println("签到积分发放成功===");
			return new JsonResult(true).setMsg("签到积分发放成功");
		} catch (Exception e) {
			System.out.println("积分发放失败==="+e.getLocalizedMessage());
			return new JsonResult(false).setMsg("积分发放失败");
		}
	}

	@Override
	public void handleIntegral(String message) {
		// TODO Auto-generated method stub
		try{
			System.out.println("我处理积分了===");
		   if (null == message) {
	                return;
	         }
	        CoinRewardMessage crm = JSON.parseObject(message, CoinRewardMessage.class);
	        String id=crm.getBusinessNum();
	        String type=crm.getBusinessType();
	        BigDecimal num=crm.getRewardNum();
	        //1代表id是积分明细表的id
	        if("1".equals(type)){
	        	IntegralDetail detail =integralDetailService.get(Long.valueOf(id));
	        	detail.setFactCoinCount(num);
	        	integralDetailService.update(detail);
	        }
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Boolean getThisMonthRoll(String memberId, BigDecimal price) {
		Boolean flag = true;
		//2期取消额度限制 统一返回true
		/*BigDecimal thisRoll = ((IntegralDetailDao)dao).getThisMonthRoll(memberId);
		if (thisRoll == null)
			thisRoll = new BigDecimal("0");
		QueryFilter filter = new QueryFilter(CustomerIntegral.class);
		filter.addFilter("memberId=",memberId);
		CustomerIntegral customerIntegral = customerIntegralService.get(filter);
		BigDecimal newRoll = thisRoll.add(price);
		if (customerIntegral.getTotalQuota().compareTo(newRoll) == -1 || customerIntegral.getMaxQuota().compareTo(newRoll) == -1) {
			flag = false;
		}*/
		return flag;
	}
}
