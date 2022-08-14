/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:43 
 */
package hry.admin.klg.level.controller;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.exchange.model.DateUtil;
import hry.admin.klg.level.model.RulesConfig;
import hry.admin.web.model.AppConfig;
import hry.admin.web.service.AppConfigService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.*;
import hry.admin.klg.level.model.KlgLevelConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.connection.RedisServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:43 
 */
@Controller
@RequestMapping("/klg/level/klglevelconfig")
public class KlgLevelConfigController extends BaseController<KlgLevelConfig, Long> {

	@Resource
	private RedisService redisService;
	@Resource
	private AppConfigService appConfigService;

	@Resource(name = "klgLevelConfigService")
	@Override
	public void setService(BaseService<KlgLevelConfig, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgLevelConfig klgLevelConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klglevelconfigsee");
		mav.addObject("model", klgLevelConfig);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgLevelConfig klgLevelConfig){
		QueryFilter filter = new QueryFilter(KlgLevelConfig.class);
		filter.addFilter("sort=", klgLevelConfig.getSort());
		KlgLevelConfig klgL = service.get(filter);
		if(klgL!=null){
			return new JsonResult().setSuccess(false).setMsg("排序序号已存在");
		}
		return super.save(klgLevelConfig);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgLevelConfig klgLevelConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klglevelconfigmodify");
		mav.addObject("model", klgLevelConfig);
		return mav;
	}

	//卖出奖励设置
	@RequestMapping(value="/sellmodify/{id}")
	public ModelAndView sellmodify(@PathVariable Long id){
		KlgLevelConfig klgLevelConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klgsellconfigmodify");
		mav.addObject("model", klgLevelConfig);
		return mav;
	}
	//升级规则设置
	@RequestMapping(value="/uplevelmodify/{id}")
	public ModelAndView uplevelmodify(@PathVariable Long id){
		KlgLevelConfig klgLevelConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/level/klguplevelconfigmodify");
		mav.addObject("model", klgLevelConfig);
		return mav;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgLevelConfig klgLevelConfig){
		
		KlgLevelConfig klgLevelC = service.get(klgLevelConfig.getId());
		if(klgLevelC.getSort()!=klgLevelConfig.getSort()){
			QueryFilter filter = new QueryFilter(KlgLevelConfig.class);
			filter.addFilter("sort=", klgLevelConfig.getSort());
			KlgLevelConfig klgL = service.get(filter);
			if(klgL!=null){
				return new JsonResult().setSuccess(false).setMsg("排序序号已存在");
			}
		}
		
		return super.update(klgLevelConfig);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgLevelConfig.class,request);
		return super.findPage(filter);
	}
	@RequestMapping(value = "/findAll")
	@ResponseBody
	public List<KlgLevelConfig> findAll(HttpServletRequest request) {

		return super.findAll();
	}



	@RequestMapping(value = "/getRuleConfig")
	public ModelAndView getRuleConfig() {
		ModelAndView mav = new ModelAndView("/admin/klg/klgrulesconfig");
		// 根据typekey进行查询
		List<AppConfig> rulesCoinKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesCoinKey));
		List<AppConfig> rulesTimeKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesTimeKey));
		List<AppConfig> rulesAwardKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesAwardKey));
		List<AppConfig> rulesPurchaseKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesPurchaseKey));
		List<AppConfig> luckDrawKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.LuckDrawKey));
		String isOpen=redisService.get(RulesConfig.RulesIsOpen);//是否开盘时间
		mav.addObject("isOpen", isOpen==null?0:1);//
		mav.addObject("rulesCoinKey", rulesCoinKey);
		mav.addObject("rulesTimeKey", rulesTimeKey);
		mav.addObject("rulesAwardKey", rulesAwardKey);
		mav.addObject("rulesPurchaseKey", rulesPurchaseKey);
		mav.addObject("luckDrawKey", luckDrawKey);
		return  mav;
	}
	//获取到期还剩多少时间
	public static int getEndLongTime(String endTime){
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(sdf.format(new java.util.Date())));
			Long now =cal.getTimeInMillis();//当前时间
			cal.setTime(sdf.parse(endTime));
			Long end=	cal.getTimeInMillis();//结束时间
			if(end>now){
				return new Long((end-now)/1000).intValue();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@MethodName(name = "保存系统规则配置")
	@RequestMapping(value = "/save")
	@ResponseBody
	@CommonLog(name = "保存系统规则配置")
	public JsonResult save(HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		String jsonData = request.getParameter("jsonData");
		if (!StringUtils.isEmpty(jsonData)) {
			QueryFilter queryFilter = new QueryFilter(AppConfig.class);
			queryFilter.addFilter("typekey_like", "%"+RulesConfig.RulesCoinLikeKey+"%");
			List<AppConfig> list = appConfigService.find(queryFilter);
			JSONObject jsonObject = JSON.parseObject(jsonData);
			Map map=JSON.parseObject(jsonData,Map.class);
			String isOpen=map.get("isOpen")==null?null:map.get("isOpen").toString();
			if("0".equals(isOpen)){//关闭
				redisService.delete(RulesConfig.RulesIsOpen);
			}if("1".equals(isOpen)){//开启
				String  endTime=map.get("endTime").toString();
				int time=getEndLongTime(endTime);
				if(time>0){
					redisService.save(RulesConfig.RulesIsOpen,"isOpen",time);
				}
			}
			redisService.saveMap(RulesConfig.RulesCoinLikeKey,map);//平台规则配置
			//循环
			for (AppConfig config : list) {
				Set<String> keys = jsonObject.keySet();
				Iterator<String> it = keys.iterator();
				while (it.hasNext()) {
					String next = it.next();
					if (config.getConfigkey().equals(next)) {
						if("write_proposal".equals(next)){
							config.setValue(jsonObject.getString(next));
						}else{
							config.setValue(JsoupUtil.clean(jsonObject.getString(next)));
						}
						if(config.getConfigkey().equals("coinCode")){
							if(StringUtil.isNull(config.getValue().trim())){ //系统规则配置 平台不许修改
								//continue;
							}
						}
						appConfigService.update(config);
					}
				}
			}

		}
		//修改完后更新缓存
		appConfigService.initCache();
		jsonResult.setSuccess(true);
		return jsonResult;
	}
}
