package hry.admin.licqb.platform.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.licqb.platform.RulesConfig;
import hry.admin.web.model.AppConfig;
import hry.admin.web.service.AppConfigService;
import hry.bean.JsonResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.JsoupUtil;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhouming
 * @date 2019/8/9 11:48
 * @Version 1.0
 */
@Controller
@RequestMapping("/platform/config")
public class PlatformConfigController extends BaseController<AppConfig, Long> {

    @Resource
    private RedisService redisService;
    @Resource
    private AppConfigService appConfigService;

    @Override
    public void setService(BaseService baseService) {

    }


    @RequestMapping(value = "/getRuleConfig")
    public ModelAndView getRuleConfig() {
        ModelAndView mav = new ModelAndView("/admin/licqb/platform/ruleconfig");
        // 根据typekey进行查询 --- 平台发币信息
        List<AppConfig> rulesCoinKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesCoinKey));
        // 根据typekey进行查询 --- 提币时间配置
        List<AppConfig> extractTimeKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.ExtractTimeKey));
        // 根据typekey进行查询 --- 兑换时间配置
        List<AppConfig> exchangeTimeKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.ExchangeTimeKey));
        // 根据typekey进行查询 --- 理财时间配置
        List<AppConfig> investTimeKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.InvestTimeKey));
        // 根据typekey进行查询 --- 投资区间配置
        List<AppConfig> investRangeKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.InvestRangeKey));
        // 根据typekey进行查询 --- 静态收益设置
        List<AppConfig> staticGainsKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.StaticGainsKey));
        // 根据typekey进行查询 --- 见点奖设置
        List<AppConfig> seeAwardKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.SeeAwardKey));
        // 根据typekey进行查询 --- 管理奖设置
        List<AppConfig> manageAwardKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.ManageAwardKey));
        // 根据typekey进行查询 --- 生态入驻规则设置
        List<AppConfig> ecologenterKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.EcologenterKey));

        mav.addObject("rulesCoinKey", rulesCoinKey);
        mav.addObject("extractTimeKey", extractTimeKey);
        mav.addObject("exchangeTimeKey", exchangeTimeKey);
        mav.addObject("investTimeKey", investTimeKey);
        mav.addObject("investRangeKey", investRangeKey);
        mav.addObject("staticGainsKey", staticGainsKey);
        mav.addObject("seeAwardKey", seeAwardKey);
        mav.addObject("manageAwardKey", manageAwardKey);
        mav.addObject("ecologenterKey", ecologenterKey);
        return  mav;
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
            queryFilter.addFilter("typekey_like", "%"+RulesConfig.PlatformKey+"%");
            List<AppConfig> list = appConfigService.find(queryFilter);
            JSONObject jsonObject = JSON.parseObject(jsonData);
            Map map = JSON.parseObject(jsonData,Map.class);
            redisService.saveMap(hry.admin.klg.level.model.RulesConfig.RulesCoinLikeKey,map);//平台规则配置
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
