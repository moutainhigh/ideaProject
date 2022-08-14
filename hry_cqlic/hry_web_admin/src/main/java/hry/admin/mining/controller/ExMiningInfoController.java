
package hry.admin.mining.controller;

import hry.bean.PageResult;
import hry.calculate.remote.mining.RemoteExMiningRewardTimerService;
import hry.core.annotation.base.MethodName;
import hry.reward.model.page.FrontPage;
import hry.util.DataUtil;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Copyright:   北京互融时代软件有限公司
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2018-06-25 17:28:10 
 */
@Controller
@RequestMapping("/mining/exmininginfo")
public class ExMiningInfoController  {
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@MethodName(name = "列表ExMiningInfo")
	@RequestMapping("/list")
	@ResponseBody
	public FrontPage list(HttpServletRequest request){
		HashMap requestMap = DataUtil.getParameterMap(request);
		requestMap.put("start",requestMap.get("offset"));
		requestMap.put("length",requestMap.get("limit"));
		RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
		return remoteExMiningRewardTimerService.findMiningInfoPageBySql(requestMap);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@MethodName(name = "列表ExMiningInfo")
	@RequestMapping("/miningInfolist")
	@ResponseBody
	public FrontPage miningInfolist(HttpServletRequest request){
		HashMap requestMap = DataUtil.getParameterMap(request);
		RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
		return remoteExMiningRewardTimerService.findMiningInfoPageBySql(requestMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@MethodName(name = "列表ExMiningInfo")
	@RequestMapping("/bonusInfoList")
	@ResponseBody
	public FrontPage bonusInfoList(HttpServletRequest request){
		HashMap requestMap = DataUtil.getParameterMap(request);
		RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService)ContextUtil.getBean("remoteExMiningRewardTimerService");
		return remoteExMiningRewardTimerService.findBonusInfoPageBySql(requestMap);
	}
	
	
	
	
}
