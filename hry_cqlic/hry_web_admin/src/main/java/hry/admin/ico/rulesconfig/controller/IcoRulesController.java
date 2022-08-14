package hry.admin.ico.rulesconfig.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.ico.rulesconfig.model.IcoRuleDetailed;
import hry.admin.ico.rulesconfig.model.RulesConfig;
import hry.admin.ico.rulesconfig.service.IcoRuleDetailedService;
import hry.admin.web.model.AppConfig;
import hry.admin.web.service.AppConfigService;
import hry.bean.JsonResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.ico.remote.RemoteIcoService;
import hry.redis.common.utils.RedisService;
import hry.util.JsoupUtil;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
 * appConfig  平台规则
 */
@Controller
@RequestMapping("/ico/rulesconfig")
public class IcoRulesController extends BaseController<AppConfig, Long> {
    private static Logger logger = Logger.getLogger(IcoRulesController.class);

    @Resource(name = "appConfigService")
    @Override
    public void setService(BaseService<AppConfig, Long> service) {
        super.service = service;
    }

    @Autowired
    private RedisService redisService;
    @Resource
    private IcoRuleDetailedService icoRuleDetailedService;

    @RequestMapping(value = "/getRuleConfig")
    public ModelAndView getRuleConfig() {
        ModelAndView mav = new ModelAndView("/admin/ico/icorulesconfig");

        // 根据typekey进行查询
        List<AppConfig> rulesCoinKey = super.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesCoinKey));
        List<AppConfig> rulesICOKey = super.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesICOKey));
        List<AppConfig> rulesCommonKey = super.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesCommonKey));
        List<AppConfig> rulesCommissionKey = super.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesCommissionKey));
        List<AppConfig> rulesOperateKey = super.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.RulesOperateKey));
        String surplusAvailable = redisService.get(RulesConfig.PLATFORM_NUMBER);//平台币剩余数量
        String issueQuantity = redisService.get(RulesConfig.PLATFORM_CIRCULATION);//发行数量
        mav.addObject("rulesCoinKey", rulesCoinKey);
        mav.addObject("issueQuantity", issueQuantity);
        mav.addObject("surplusAvailable", surplusAvailable);
        mav.addObject("rulesICOKey", rulesICOKey);
        mav.addObject("rulesCommonKey", rulesCommonKey);
        mav.addObject("rulesCommissionKey", rulesCommissionKey);
        mav.addObject("rulesOperateKey", rulesOperateKey);
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
            queryFilter.addFilter("typekey_like", "%"+RulesConfig.RulesCoinLikeKey+"%");
            List<AppConfig> list = service.find(queryFilter);
            JSONObject jsonObject = JSON.parseObject(jsonData);
             Map map=JSON.parseObject(jsonData,Map.class);
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
                                   continue;
                            }
                        }
                        service.update(config);
                    }
                }
            }

        }
        //修改完后更新缓存
        ((AppConfigService) service).initCache();
        jsonResult.setSuccess(true);
        return jsonResult;

    }


}
