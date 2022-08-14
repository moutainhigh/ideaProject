package hry.admin.mining.controller;

import com.alibaba.fastjson.JSONObject;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.mining.model.ExMiningTransaction;
import hry.admin.mining.model.ExMiningUnfreeze;
import hry.admin.mining.model.MingTimer;
import hry.admin.mining.service.MingTimerService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.calculate.remote.mining.RemoteExMiningRewardTimerService;
import hry.core.annotation.base.MethodName;
import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.redis.common.utils.RedisService;
import hry.reward.model.ExMining;
import hry.reward.model.Timer;
import hry.reward.model.page.FrontPage;
import hry.reward.model.page.RemoteResult;
import hry.util.DataUtil;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright:   北京互融时代软件有限公司
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2018-06-25 13:01:36 
 */
@Controller
@RequestMapping("/mining/exmining")
public class ExMiningController  {
	private static Logger logger = Logger.getLogger(ExMiningController.class);
	@Resource
	private AppPersonInfoService appPersonInfoService;

	/**
	 * 进入挖矿产出发放列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEminingList")
	public ModelAndView toEminingList(HttpServletRequest request){
		String status=request.getParameter("status");
		if(null==status){
			status="0";
		}
		ModelAndView mav = new ModelAndView("/admin/mining/exmininglist");
		mav.addObject("status", status);

		return mav;
	}
	@SuppressWarnings({ "unchecked" })
	@MethodName(name = "产出发放列表ExMining ，status=1是已发放。0是未发放")
	@RequestMapping("/list")
	@ResponseBody
	public FrontPage list(HttpServletRequest request){
		HashMap<String, String> requestMap = DataUtil.getParameterMap(request);
		requestMap.put("start",requestMap.get("offset"));
		requestMap.put("length",requestMap.get("limit"));

		RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
		FrontPage pageResult = remoteExMiningRewardTimerService.findMiningPageBySql(requestMap);
		List<ExMining> list = pageResult.getRows();
		for(ExMining em : list){
			QueryFilter filter = new QueryFilter(AppPersonInfo.class);
			filter.addFilter("customerId_EQ", em.getCustomerId());
			AppPersonInfo appPersonInfo = appPersonInfoService.get(filter);
			em.setCardId(appPersonInfo.getCardId());
			logger.error("TotalFee = [" + em.getTotalFee() + "]");
		}
		pageResult.setRows(list);
		return pageResult;
	}
	
	@MethodName(name = "手动发放币")
	@RequestMapping("/manual")
	@ResponseBody
	public JsonResult manual(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		try {
			RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
			RedisService redisService =(RedisService)ContextUtil.getBean("redisService");
			String autoReturn = redisService.get("Mining:autoReturn");//查看返币方式 是手动还是自动  0自动 1手动
			if("1".equals(autoReturn)){
				String idss = request.getParameter("ids");
				remoteExMiningRewardTimerService.manualReturnCurrency(idss);
				RemoteResult remoteResult=remoteExMiningRewardTimerService.manualReturnBonus(idss);
				jsonResult.setSuccess(remoteResult.getSuccess());
			} else{
				jsonResult.setSuccess(false);
				jsonResult.setMsg("当前为自动发放");
			}
			
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("发放失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	@MethodName(name = "设置定时器")
	@RequestMapping("/setTimer")
	@ResponseBody
	public JsonResult setTimer(HttpServletRequest request,MingTimer timer){
		JsonResult jsonResult = new JsonResult();
		try {
			RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
			String type = request.getParameter("type");
			String json = JSONObject.toJSONString(timer);
			RedisService redisService =(RedisService)ContextUtil.getBean("redisService");
			MingTimerService mingTimerService = (MingTimerService)ContextUtil.getBean("mingTimerService");
			//数据库中只能有一条数据。
			List<MingTimer> timerServiceAll = mingTimerService.findAll();
			if(timerServiceAll.size()>0){
				timer.setId(1);
				mingTimerService.update(timer);
			}else{
				mingTimerService.save(timer);
			}

			String platformStartTimer = timer.getPlatformStartTimer();//挖矿开始时间
			String bonusReturnStart = timer.getBonusReturnStart();//分红开始时间
			if("0".equals(type)){
				redisService.save("Mining:Timer0",json);
				redisService.save("Mining:autoReturn", timer.getPlatformType());//挖矿奖励手动还是自动 0自动1手动
				if("0".equals(timer.getMiningIfStart())){
					remoteExMiningRewardTimerService.timerPlatCoinAvg(timer.getPlatformAvgTimer()+timer.getPlatformAvgTimerType(),platformStartTimer);//设置计算平台币均价定时器
//对接区块链定时器
//					remoteExMiningRewardTimerService.timercointoMining(timer.getPlatformAvgTimer()+timer.getPlatformAvgTimerType(), platformStartTimer);//对接区块链定时器
					if(!StringUtils.isEmpty(timer.getPlatformType()) && "0".equals(timer.getPlatformType())){
						redisService.save("Mining:platformReturnTimer",timer.getPlatformReturnTimer()+timer.getPlatformReturnTimerType());
						remoteExMiningRewardTimerService.timerReturnCurrency(timer.getPlatformReturnTimer()+timer.getPlatformReturnTimerType(),platformStartTimer);//设置挖矿奖励定时器
					}
				} else {
					remoteExMiningRewardTimerService.removeTimerPlatCoinAvg();
					remoteExMiningRewardTimerService.removeTimerReturnCurrency();
				}
					
			} else if("1".equals(type)){
				redisService.save("Mining:Timer1",json);
				redisService.save("Mining:autoBonusReturn", timer.getBonusReturnType());//挖矿奖励手动还是自动 0自动1手动
				if("0".equals(timer.getBonusIfStart())){
					//设置持币者记录定时器
					remoteExMiningRewardTimerService.timerReturnBonus(timer.getBonusRecordTimer()+timer.getBonusRecordTimerType(),bonusReturnStart);
					if(!StringUtils.isEmpty(timer.getBonusReturnType()) && "0".equals(timer.getBonusReturnType())){
						redisService.save("Mining:bonusReturnTimer", timer.getBonusReturnTimer()+timer.getBonusReturnTimerType());//设置设置持有者时间
						//设置分红发放定时器
						remoteExMiningRewardTimerService.timerBonusgGrant(timer.getBonusReturnTimer()+timer.getBonusReturnTimerType(),bonusReturnStart);
					}
				} else {
					remoteExMiningRewardTimerService.removeTimerBonusgGrant();
					remoteExMiningRewardTimerService.removeTimerReturnBonus();
				}
			}else { //锁仓解冻定时器
				// 将锁仓释放定时器配置信息记录到redis中
				redisService.save("Mining:Timer2", json);
				// 判断是否开启锁仓释放定时器 0-是，1-否
                QuartzManager.removeJob("timingUnlockAccountCold");
				if ("1".equals(timer.getUnlockIfStart())) {
					ScheduleJob timingUnlockAccountCold = new ScheduleJob();
					timingUnlockAccountCold.setSpringId("exDmUnlockHistoryService");
					timingUnlockAccountCold.setMethodName("timingUnlockAccountCold");
					QuartzManager.addJob("timingUnlockAccountCold", timingUnlockAccountCold, QuartzJob.class, "0 5 0 * * ?");
				}
			}
			
			jsonResult.setSuccess(true);
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			e.printStackTrace();
		}
		return jsonResult;
	}

	/**
	 * 进入定时器设置页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toTimer")
	public ModelAndView toTimer(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/mining/ExminingTimerset");
		try {
			RedisService redisService =(RedisService)ContextUtil.getBean("redisService");
			String miningTimer = redisService.get("Mining:Timer0");
			String miningTimer1 = redisService.get("Mining:Timer1");
			String miningTimer2 = redisService.get("Mining:Timer2");
			Timer timer = JSONObject.parseObject(miningTimer, Timer.class);
			Timer timer1 = JSONObject.parseObject(miningTimer1, Timer.class);
			Timer timer2 = JSONObject.parseObject(miningTimer2, Timer.class);
			mav.addObject("timer", timer);
			mav.addObject("timer1", timer1);
			mav.addObject("timer2", timer2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	
	@SuppressWarnings("unchecked")
	@MethodName(name = "导入解冻列表ExMiningUnfreeze")
	@RequestMapping("/unfreezelist")
	@ResponseBody
	public FrontPage unfreezelist(HttpServletRequest request) throws Exception{
		HashMap<String, String> requestMap = DataUtil.getParameterMap(request);
		RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
		FrontPage pageResult = remoteExMiningRewardTimerService.findMiningUnfreezePageBySql(requestMap);
		List<ExMiningUnfreeze> rows = pageResult.getRows();
		for(ExMiningUnfreeze emu: rows){
			QueryFilter filter = new QueryFilter(AppPersonInfo.class);
			filter.addFilter("customerId_EQ", emu.getCustomerId());
			AppPersonInfo appPersonInfo = appPersonInfoService.get(filter);
			emu.setAppPersonInfo(appPersonInfo);
		}
		return remoteExMiningRewardTimerService.findMiningUnfreezePageBySql(requestMap);
	}
	
	@MethodName(name = "区块链列表ExMiningtoCoin")
	@RequestMapping("/tocoinlist")
	@ResponseBody
	public FrontPage miningtoCoinlist(HttpServletRequest request){
		HashMap<String, String> requestMap = DataUtil.getParameterMap(request);
		RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
		return remoteExMiningRewardTimerService.findMiningCoinPageBySql(requestMap);
	}
	
	@SuppressWarnings("unchecked")
	@MethodName(name = "挖矿分红发放记录表")
	@RequestMapping("/transactionlist")
	@ResponseBody
	public PageResult transactionlist(HttpServletRequest request){
		HashMap<String, String> requestMap = DataUtil.getParameterMap(request);
		RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
		PageResult pageResult = remoteExMiningRewardTimerService.findMiningTransactionPageBySql(requestMap);
		List<ExMiningTransaction> rows = pageResult.getRows();
		for(ExMiningTransaction emt : rows){
			QueryFilter filter = new QueryFilter(AppPersonInfo.class);
			filter.addFilter("customerId_EQ", emt.getCutomerId());
			AppPersonInfo appPersonInfo = appPersonInfoService.get(filter);
			emt.setCardId(appPersonInfo.getCardId());
		}
		return pageResult;
	}
	
	@MethodName(name = "查询分表信息")
	@RequestMapping("/subTablelist")
	@ResponseBody
	public JsonResult subTablelist(HttpServletRequest request){
		JsonResult result = new JsonResult();
		HashMap<String, String> requestMap = DataUtil.getParameterMap(request);
		RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
		RemoteResult remoteResult = remoteExMiningRewardTimerService.findSubTable(requestMap);
		result.setObj(remoteResult.getObj());
		return result;
	}
	@MethodName(name = "查看定时器状态")
	@RequestMapping("/seeTimerstatus")
	@ResponseBody
	public JsonResult seeTimerstatus(HttpServletRequest request){
		JsonResult result = new JsonResult();
		Map<String,String> timerMap = new HashMap<String,String>();
		RedisService redisService =(RedisService)ContextUtil.getBean("redisService");
		String holdBonusStr = redisService.getMap("Mining:Exception","holdBonus");//持币定时器状态
		String grantBonusStr = redisService.getMap("Mining:Exception","grantBonus");//分红发放定时器状态
		String returnMiningStr = redisService.getMap("Mining:Exception","returnMining");//挖矿返还定时器状态
		String avgPriceMiningStr = redisService.getMap("Mining:Exception","avgPriceMining");//计算均价定时器状态
		if(!StringUtils.isEmpty(holdBonusStr)){
			String ifSuccess = holdBonusStr.split("#")[1];
			if("success".equals(ifSuccess)){
				timerMap.put("holdBonus","正常#"+holdBonusStr.split("#")[2]);
			} else {
				timerMap.put("holdBonus",holdBonusStr.split("#")[1]+"#"+holdBonusStr.split("#")[2]);
			}
		}
		if(!StringUtils.isEmpty(grantBonusStr)){
			String ifSuccess = grantBonusStr.split("#")[1];
			if("success".equals(ifSuccess)){
				timerMap.put("grantBonus","正常#"+grantBonusStr.split("#")[2]);
			} else {
				timerMap.put("grantBonus",grantBonusStr.split("#")[1]+"#"+grantBonusStr.split("#")[2]);
			}
		}
		if(!StringUtils.isEmpty(returnMiningStr)){
			String ifSuccess = returnMiningStr.split("#")[1];
			if("success".equals(ifSuccess)){
				timerMap.put("returnMining","正常#"+returnMiningStr.split("#")[2]);
			} else {
				timerMap.put("returnMining",returnMiningStr.split("#")[1]+"#"+returnMiningStr.split("#")[2]);
			}
		}
		if(!StringUtils.isEmpty(avgPriceMiningStr)){
			String ifSuccess = avgPriceMiningStr.split("#")[1];
			if("success".equals(ifSuccess)){
				timerMap.put("avgPriceMining","正常#"+avgPriceMiningStr.split("#")[2]);
			} else {
				timerMap.put("avgPriceMining",avgPriceMiningStr.split("#")[1]+"#"+avgPriceMiningStr.split("#")[2]);
			}
		}
		if(timerMap.size()>0 ){
			result.setSuccess(true);
			result.setObj(timerMap);
		}
		return result;
	}
}
