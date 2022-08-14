package hry.cm4.remote;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.cm4.customerminer.model.Cm4CustomerMiner;
import hry.cm4.customerminer.service.Cm4CustomerMinerService;
import hry.cm4.deal.CmDealUtil;
import hry.cm4.dto.Accountadd;
import hry.cm4.dto.CmAccountRedis;
import hry.cm4.enums.CmAccountRemarkEnum;
import hry.cm4.log.service.Cm4ExceptionLogService;
import hry.cm4.miner.model.Cm4Miner;
import hry.cm4.miner.service.Cm4MinerService;
import hry.cm4.order.model.Cm4Order;
import hry.cm4.order.service.Cm4OrderService;
import hry.cm4.remote.model.CmOrderRemote;
import hry.mq.producer.DealMsgUtil;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.utils.BaseConfUtil;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

public class RemoteCmOrderServiceImpl implements RemoteCmOrderService{
	
	@Resource
	private Cm4MinerService cmMinerService;
	@Resource
	private RedisService redisService;
	@Resource
	private Cm4OrderService cmOrderService;
	@Resource
	private Cm4CustomerMinerService cmCustomerMinerService;
	@Resource
	private Cm4ExceptionLogService cmExceptionLogService;
	@Resource
	private MessageProducer messageProducer;

	@Override
	public JsonResult getorder(Map<String, String> params) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		
		
		try {
			String minerId = params.get("minerId");//矿机id
			Integer orderNum = Integer.valueOf(params.get("orderNum"));//购买数量
			Long customerId = Long.valueOf(params.get("customerId"));//用户id
			
			Boolean flag = redisService.lock("CM:BUYMINER" );
			if (!flag) {
				return jsonResult.setSuccess(false).setMsg("cm_xitongfanmang");//系统繁忙
			}
			
	        /**查询购买的矿机信息**/
			Cm4Miner cmMiner = cmMinerService.get(Long.valueOf(minerId));
			if(StringUtils.isEmpty(cmMiner)){
				redisService.unLock("CM:BUYMINER");
				System.out.println("矿机信息错误");
	            jsonResult.setMsg("cm_miner_exist");
	            jsonResult.setSuccess(false);
	            return jsonResult;
			}
			
			/**判断矿机设置的最大购买和最下购买数量*/
			if(orderNum<=0){
				redisService.unLock("CM:BUYMINER");
				return jsonResult.setSuccess(false).setMsg("cm_goumaishuliangbunengwei0");//购买数量不能为0
			}else if(orderNum<cmMiner.getMinNum()){
				redisService.unLock("CM:BUYMINER");
				return jsonResult.setSuccess(false).setMsg("cm_xiaoyizuixiaogoumaishuliang");//小于最小购买数量
			}else if(orderNum>cmMiner.getMaxNum()){
				redisService.unLock("CM:BUYMINER");
				return jsonResult.setSuccess(false).setMsg("cm_chaochuzuidagoumaishuliang");//超出最大购买数量
			}else if(orderNum>cmMiner.getSurplusNum()){
				redisService.unLock("CM:BUYMINER");
				return jsonResult.setSuccess(false).setMsg("cm_shengyushuliangbuzu");//剩余数量不足
			}
			
			/**2019-10-10 16:16:00   此处最大购买数量改为，用户持有当前矿机有效台数的数量**/
			//查询用户持有当前矿机数量
			int minerSum = cmCustomerMinerService.getMinigNumByCustomerIdAndMinerId(customerId,Long.valueOf(minerId));
			int minerAll = minerSum + orderNum;
			if(minerAll>cmMiner.getMaxNum()){
				redisService.unLock("CM:BUYMINER");
				return jsonResult.setSuccess(false).setMsg("cm_chaochuzuidagoumaishuliang");//超出最大购买数量
			}
			
			/**查询用户待运行+运行中的矿机数*/
			int miningSum = cmCustomerMinerService.getMinigNumByCustomerId(customerId);
			/** 查询有效矿机最大数 */
			String cloudminingsum = BaseConfUtil.getConfigSingle("cloudminingsum", "configCache:cloudminingConfig");
			Integer isum = Integer.valueOf(cloudminingsum);
			if(miningSum>=isum){
				redisService.unLock("CM:BUYMINER");
				return jsonResult.setSuccess(false).setMsg("cm_chaochuchiyoukuangjizuida");//超出持有矿机最大
			}
			
			/**计算订单总价格*/
			BigDecimal orderPrice = cmMiner.getMinerPrice().multiply(new BigDecimal(orderNum));

			/** 查询用户平台币账户信息，判断是否充足 */
			CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(customerId, cmMiner.getPayCoinCode());
			BigDecimal hotMoney = cmAccountRedis.getHotMoney();
			if (hotMoney.compareTo(orderPrice) < 0) {
				/**币账户jiab*/
				/*List<Accountadd> accountaddList = new ArrayList<>();
				Accountadd accountadd = new Accountadd(customerId, cmMiner.getPayCoinCode(), cmAccountRedis.getId(),
						new BigDecimal(100000000), 1, CmAccountRemarkEnum.TYPE999999999.getIndex(),
						"11");
				accountaddList.add(accountadd);
				DealMsgUtil.sendAccountaddList(accountaddList);*/
				redisService.unLock("CM:BUYMINER");
				return jsonResult.setSuccess(false).setMsg("cm_zhanghuzijinbuzu");//账户资金不足
			}
			
			/**插入订单信息 */
			Cm4Order cmOrder = this.insertOrder(cmMiner, customerId, orderNum, orderPrice);
			/**插入用户矿机信息 */
			this.insertCustomerMiner(cmMiner, customerId, orderNum, cmOrder);
			/**币账户减少*/
			List<Accountadd> accountaddList = new ArrayList<>();
			Accountadd accountadd = new Accountadd(customerId, cmMiner.getPayCoinCode(), cmAccountRedis.getId(),
					new BigDecimal("-" + orderPrice), 1, CmAccountRemarkEnum.TYPE3.getIndex(),
					cmOrder.getTransactionNum());
			accountaddList.add(accountadd);
			DealMsgUtil.sendAccountaddList(accountaddList);
			/**矿机剩余减少*/
			cmMinerService.updateMinerSurplusNum(cmMiner.getId(), orderNum);
			
			//发送更新用户已经所有上级等级消息
			messageProducer.toCmBuyAndcloseMinerUpdateGrade(customerId.toString());
			
			redisService.unLock("CM:BUYMINER");
			jsonResult.setSuccess(true);
			jsonResult.setMsg("cm_goumaichenggong");//购买成功
			return jsonResult;
		} catch (Exception e) {
			// TODO: handle exception
			redisService.unLock("CM:BUYMINER");
			e.printStackTrace();
			cmExceptionLogService.insertlog(e, "RemoteCmOrderServiceImpl.getorder", JSON.toJSONString(params));
		}
		
		return jsonResult;
	}
	
	/**
	 * 添加订单
	 * @param cmMiner
	 * @param customerId
	 * @param orderNum
	 * @param orderPrice
	 * @return
	 */
	private Cm4Order insertOrder(Cm4Miner cmMiner,Long customerId,Integer orderNum,BigDecimal orderPrice){
		Cm4Order cmOrder = ObjectUtil.bean2bean(cmMiner, Cm4Order.class);
		cmOrder.setId(null);
		cmOrder.setBuyNumber(orderNum);
		cmOrder.setCustomerId(customerId);
		cmOrder.setMinerId(cmMiner.getId());
		cmOrder.setOrderPrice(orderPrice);
		cmOrder.setTransactionNum(IdGenerate.transactionNum("CM"));
		/**计算矿机运行时间*/
		Integer effectiveTimeLength = cmMiner.getEffectiveTimeLength();//有效时长
		
		Date nowDate = new Date();
		Date startDate=hry.util.date.DateUtil.addDay(nowDate,1);//矿机0点后开始运行
		Date endDate=hry.util.date.DateUtil.addDay(nowDate,effectiveTimeLength);//矿机结束时间
		//改变时分秒
		startDate=this.changeTimeAndSeconds(startDate,1);
		endDate=this.changeTimeAndSeconds(endDate,2);
		
		cmOrder.setStartDate(startDate);
		cmOrder.setEndDate(endDate);
		cmOrderService.save(cmOrder);
		return cmOrder;
	}
	
	/**
	 * 添加用户矿机
	 * @param cmMiner
	 * @param customerId
	 * @param orderNum
	 */
	private void insertCustomerMiner(Cm4Miner cmMiner,Long customerId,Integer orderNum,Cm4Order cmOrder){
		Cm4CustomerMiner cmCustomerMiner = ObjectUtil.bean2bean(cmMiner, Cm4CustomerMiner.class);
		cmCustomerMiner.setId(null);
		cmCustomerMiner.setCustomerId(customerId);
		cmCustomerMiner.setMinerId(cmMiner.getId());
		cmCustomerMiner.setStatus(1);
		cmCustomerMiner.setOrderId(cmOrder.getId());
		cmCustomerMiner.setTransactionNum(cmOrder.getTransactionNum());
		cmCustomerMiner.setStartDate(cmOrder.getStartDate());
		cmCustomerMiner.setEndDate(cmOrder.getEndDate());
		List<Cm4CustomerMiner> list = new ArrayList<>();
		
		for(int i=0;i<orderNum;i++){
			Cm4CustomerMiner c = ObjectUtil.bean2bean(cmCustomerMiner, Cm4CustomerMiner.class);
			list.add(c);
		}
		cmCustomerMinerService.saveAll(list);
	}

	@Override
	public FrontPage findOrderList(Map<String, String> params) {
		// TODO Auto-generated method stub
		Long customerId = Long.valueOf(params.get("customerId"));//用户id
		String dateMonthStr = params.get("dateMonth");//月份
		Page page = PageFactory.getPage(params);
		QueryFilter filter = new QueryFilter(Cm4Order.class);
		filter.addFilter("customerId=", customerId);
		if(Integer.valueOf(dateMonthStr)!=0){
			filter.addFilter("month(created)=", Integer.valueOf(dateMonthStr));
		}
		
		List<Cm4Order> list = cmOrderService.find(filter);
		List<CmOrderRemote> beanList = ObjectUtil.beanList(list, CmOrderRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}
	/**
	 * 改变时间时分秒为00:00:00
	 * @param date
	 * @return
	 */
	private Date changeTimeAndSeconds(Date date,int type){
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		// 将时分秒,毫秒域清零
		if(type==1){
			cal1.set(Calendar.HOUR_OF_DAY, 0);
			cal1.set(Calendar.MINUTE, 0);
			cal1.set(Calendar.SECOND, 0);
			cal1.set(Calendar.MILLISECOND, 0);
		}else{
			cal1.set(Calendar.HOUR_OF_DAY, 23);
			cal1.set(Calendar.MINUTE, 58);
			cal1.set(Calendar.SECOND, 59);
			cal1.set(Calendar.MILLISECOND, 59);
		}
		
		return  cal1.getTime();
	}
	

}
