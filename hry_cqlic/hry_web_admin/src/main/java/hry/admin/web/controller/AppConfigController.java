/**
 * Copyright:
 *
 * @author: liushilei
 * @version: V1.0
 * @Date: 2018-06-14 11:18:59
 */
package hry.admin.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
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
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright:   互融云
 *
 * @author: liushilei
 * @version: V1.0
 * @Date: 2018-06-14 11:18:59
 */
@Controller
@RequestMapping("/web/appconfig")
public class AppConfigController extends BaseController<AppConfig, Long> {
    private static Logger logger = Logger.getLogger(AppConfigController.class);

    @Resource(name = "appConfigService")
    @Override
    public void setService(BaseService<AppConfig, Long> service) {
        super.service = service;
    }

    @Autowired
    private RedisService redisService;

    @Resource(name = "appDicService")
    private AppDicService appDicService;


    @RequestMapping(value = "/see/{id}")
    public ModelAndView see(@PathVariable Long id) {
        AppConfig appConfig = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/web/appconfigsee");
        mav.addObject("model", appConfig);
        return mav;
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, AppConfig appConfig) {
        return super.save(appConfig);
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee(@PathVariable Long id) {
        AppConfig appConfig = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/web/appconfigmodify");
        mav.addObject("model", appConfig);
        return mav;
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public JsonResult modify(HttpServletRequest request, AppConfig appConfig) {
        return super.update(appConfig);
    }


    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove(String ids) {
        return super.deleteBatch(ids);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(AppConfig.class, request);
        return super.findPage(filter);
    }


    @MethodName(name = "加载appConfig数据")
    @RequestMapping(value = "/page/{typeKey}", method = RequestMethod.GET)
    public ModelAndView page(HttpServletRequest request, @PathVariable String typeKey) {
        ModelAndView mav = new ModelAndView("/admin/web/appconfigpage");

        QueryFilter filter = new QueryFilter(AppConfig.class);
        filter.addFilter("ishidden=", "0");
        filter.setOrderby("created asc");
        List<AppConfig> list = new ArrayList<AppConfig>();
        ArrayList<AppConfig> AppConfiglist = new ArrayList<AppConfig>();
        if ("interfaceConfigCS".equals(typeKey)) {
            AppConfiglist.clear();
            // 客服配置管理
            filter.addFilter("typekey=", "interfaceConfig");
            list = super.find(filter);
            for (AppConfig appConfig : list) {
                if (appConfig.getConfigid() == 136 || appConfig.getConfigid() == 129) {
                    AppConfiglist.add(appConfig);
                }
            }
        } else {
            AppConfiglist.clear();
            filter.addFilter("typekey_in", typeKey.replaceAll("-", ","));
            list = super.find(filter);
            if (list != null && list.size() > 0) {
                if ("registerConfig".equals(typeKey)) {
                    QueryFilter filter1 = new QueryFilter(AppDic.class);
                    filter1.addFilter("pkey=", "sysLanguage");
                    List<AppDic> languageList = appDicService.find(filter1);
                    for (AppDic appDicOnelevel : languageList) {
                        boolean flag = true;
                        for (AppConfig appConfig : list) {
                            if (("reg_" + appDicOnelevel.getValue()).equals(appConfig.getConfigkey())) {
                                flag = false;
                            }
                        }
                        if (flag) {
                            AppConfig appConfig = new AppConfig();
                            appConfig.setConfigkey("reg_" + appDicOnelevel.getValue());
                            appConfig.setTypekey(typeKey);
                            appConfig.setIshidden(0);
                            appConfig.setTypename(appDicOnelevel.getName());
                            appConfig.setConfigname(appDicOnelevel.getName());
                            appConfig.setDatatype(4);
                            AppConfiglist.add(appConfig);
                        }
                    }
                } else {
                    for (AppConfig appConfig : list) {
                        if (appConfig.getConfigid() != 122
                                && appConfig.getConfigid() != 123
                                && appConfig.getConfigid() != 160
                                && appConfig.getConfigid() != 136
                                && appConfig.getConfigid() != 129
                                && appConfig.getConfigid() != 172
                                && appConfig.getConfigid() != 116) {
                            AppConfiglist.add(appConfig);
                        }
                    }
                }
            }
        }

        mav.addObject("configList", AppConfiglist);
        mav.addObject("typeKey", typeKey);
        return mav;
    }

    @RequestMapping(value = "/toBasePage/{typeKey}")
    public ModelAndView toBasePage(@PathVariable String typeKey) {
        ModelAndView mav = new ModelAndView("/admin/web/appconfigbase");
        // 初始化基本信息数据-同步其他语种
        initAppConfigData();

        QueryFilter qf = new QueryFilter(AppDic.class);
        qf.addFilter("pkey=", "sysLanguage");
        qf.setOrderby("created");
        List<AppDic> langList = appDicService.find(qf);
        mav.addObject("langList", langList);
        // 根据typekey进行查询
        QueryFilter qfOne = new QueryFilter(AppConfig.class);
        qfOne.addFilter("ishidden=", "0");
        qfOne.addFilter("typekey=", "baseConfig" + langList.get(0).getValue());
        qfOne.setOrderby("created asc");
        List<AppConfig> oneList = super.find(qfOne);
        List<AppConfig> AppConfiglist = new ArrayList<AppConfig>();
        if (oneList != null && oneList.size() > 0) {
            // 存在该语种配置
            AppConfiglist.clear();
            for (AppConfig appConfig : oneList) {
                if (appConfig.getConfigid() != 122
                        && appConfig.getConfigid() != 123
                        && appConfig.getConfigid() != 160
                        && appConfig.getConfigid() != 136
                        && appConfig.getConfigid() != 129
                        && appConfig.getConfigid() != 172
                        && appConfig.getConfigid() != 116) {
                    AppConfiglist.add(appConfig);
                }
            }
        }
        mav.addObject("configList", AppConfiglist);
        mav.addObject("typeKeyFlag", typeKey);
        return mav;
    }

    @MethodName(name = "获取基本信息数据")
    @RequestMapping(value = "/getBaseConfigData")
    @ResponseBody
    public List<AppConfig> getBaseConfigData(HttpServletRequest request) {
        String typeKey = request.getParameter("typeKey");
        // 根据typekey进行查询
        QueryFilter qfOne = new QueryFilter(AppConfig.class);
        qfOne.addFilter("ishidden=", "0");
        qfOne.addFilter("typekey=", typeKey);
        qfOne.setOrderby("created asc");
        List<AppConfig> oneList = super.find(qfOne);
        List<AppConfig> AppConfiglist = new ArrayList<AppConfig>();
        if (oneList != null && oneList.size() > 0) {
            // 存在该语种配置
            AppConfiglist.clear();
            for (AppConfig appConfig : oneList) {
                if (appConfig.getConfigid() != 122
                        && appConfig.getConfigid() != 123
                        && appConfig.getConfigid() != 160
                        && appConfig.getConfigid() != 136
                        && appConfig.getConfigid() != 129
                        && appConfig.getConfigid() != 172
                        && appConfig.getConfigid() != 116) {
                    AppConfiglist.add(appConfig);
                }
            }
        }
        return AppConfiglist;
    }

    /**
     * 初始化基本配置数据
     */
    private void initAppConfigData() {
        // 基础配置信息
        QueryFilter baseConfig = new QueryFilter(AppConfig.class);
        baseConfig.addFilter("ishidden=", "0");
        baseConfig.addFilter("typekey=", "baseConfig");
        baseConfig.setOrderby("created asc");
        List<AppConfig> baseConfigList = super.find(baseConfig);
        // 语种信息
        QueryFilter sysLang = new QueryFilter(AppDic.class);
        sysLang.addFilter("pkey=", "sysLanguage");
        sysLang.setOrderby("created");
        List<AppDic> langList = appDicService.find(sysLang);

        if (baseConfigList != null && baseConfigList.size() > 0) {
            for (AppConfig bc : baseConfigList) {
                for (AppDic dic : langList) {
                    QueryFilter qf = new QueryFilter(AppConfig.class);
                    qf.addFilter("ishidden=", "0");
                    qf.addFilter("typekey=", "baseConfig" + dic.getValue());
                    qf.addFilter("configkey=", bc.getConfigkey());
                    AppConfig ac = service.get(qf);
                    if (ac == null) {
                        AppConfig appConfig = new AppConfig();
                        appConfig.setConfigdescription(bc.getConfigdescription());
                        appConfig.setConfigkey(bc.getConfigkey());
                        appConfig.setConfigname(bc.getConfigname());
                        appConfig.setDatatype(bc.getDatatype());
                        appConfig.setDescription(bc.getDescription());
                        appConfig.setIshidden(bc.getIshidden());
                        appConfig.setTypekey("baseConfig" + dic.getValue());
                        appConfig.setTypename(bc.getTypename());
                        appConfig.setValue(null);
                        if ("zh_CN".equals(dic.getValue())) {
                            appConfig.setValue(bc.getValue());
                        }
                        service.save(appConfig);
                    }
                }
            }
        }

    }

    @MethodName(name = "保存appconfig配置")
    @RequestMapping(value = "/save")
    @ResponseBody
    @CommonLog(name = "参数配置")
    public JsonResult save(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String typeKey = request.getParameter("typeKey");
        String jsonData = request.getParameter("jsonData");
        if (!StringUtils.isEmpty(jsonData) && !StringUtils.isEmpty(typeKey)) {
            QueryFilter queryFilter = new QueryFilter(AppConfig.class);
            if ("interfaceConfigCS".equals(typeKey)) {
                typeKey = "interfaceConfig";
            }
            queryFilter.addFilter("typekey=", typeKey);
            List<AppConfig> list = service.find(queryFilter);
            JSONObject jsonObject = JSON.parseObject(jsonData);

            //修改的是注册协议
            if ("registerConfig".equals(typeKey)) {
                QueryFilter filter1 = new QueryFilter(AppDic.class);
                filter1.addFilter("pkey=", "sysLanguage");
                List<AppDic> languageList = appDicService.find(filter1);
                for (AppDic appDicOnelevel : languageList) {
                    boolean flag = true;
                    for (AppConfig appConfig : list) {
                        if (("reg_" + appDicOnelevel.getValue()).equals(appConfig.getConfigkey())) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        AppConfig appConfig = new AppConfig();
                        appConfig.setConfigkey("reg_" + appDicOnelevel.getValue());
                        appConfig.setTypekey(typeKey);
                        appConfig.setIshidden(0);
                        appConfig.setTypename(appDicOnelevel.getName());
                        appConfig.setConfigname(appDicOnelevel.getName());
                        appConfig.setDatatype(4);
                        appConfig.setValue(jsonObject.getString("reg_" + appDicOnelevel.getValue()));
                        if (!"".equals(jsonObject.getString("reg_" + appDicOnelevel.getValue()))) {
                            service.save(appConfig);
                        }
                    }
                }
            }

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


    @MethodName(name = "测试交易对实时价格")
    @RequestMapping(value = "/testInterfaceOauth")
    @ResponseBody
    @CommonLog(name = "测试交易对实时价格")
    public JsonResult testInterfaceOauth(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode("BTC");
        fxhParam.setFixCoin("USDT");

        setApiConfig(redisService, fxhParam, "configCache:klinedataconfig");
        //获取平台名称
        String appname = getAppName(redisService, "configCache:extraParamConfig", "managerName");
        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", UUIDUtil.getUUID());
        map.put("belonged", appname);
        map.put("frontWord", fxhParam.getCoinCode());
        map.put("afterWord", fxhParam.getFixCoin());

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            logger.error("请求参数:" + paramMap);
            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            logger.error("返回参数" + returnMsg);
            if (StringUtils.isEmpty(returnMsg)) {
                jsonResult.setMsg("连接失败");
                jsonResult.setSuccess(false);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    logger.error("解密后价格" + price);

                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(price);
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(returnMsg);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;

    }

    @MethodName(name = "测试获取k线接口需要加密")
    @RequestMapping(value = "/testMoreRealTime")
    @ResponseBody
    @CommonLog(name = "测试获取k线接口")
    public JsonResult testMoreRealTime(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode("BTC,USDT");

        setApiConfig(redisService, fxhParam, "configCache:realTimePairPriceConfig");
        //获取平台名称
        String appname = getAppName(redisService, "configCache:extraParamConfig", "managerName");
        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", UUIDUtil.getUUID());
        map.put("belonged", appname);
        map.put("frontWord", fxhParam.getCoinCode());

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            logger.error("请求参数:" + paramMap);
            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            logger.error("返回参数" + returnMsg);
            if (StringUtils.isEmpty(returnMsg)) {
                jsonResult.setMsg("连接失败");
                jsonResult.setSuccess(false);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    logger.error("解密后价格" + price);

                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(price);
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(returnMsg);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;

    }

    @MethodName(name = "测试获取历史数据k线接口需要加密")
    @RequestMapping(value = "/testInterfaceHistoryOauth")
    @ResponseBody
    @CommonLog(name = "测试获取k线接口")
    public JsonResult testInterfaceHistoryOauth(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode("BTC");
        fxhParam.setFixCoin("USDT");

        setApiConfig(redisService, fxhParam, "configCache:hisklinedataconfig");
        //获取平台名称
        String appname = getAppName(redisService, "configCache:extraParamConfig", "managerName");
        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", UUIDUtil.getUUID());
        map.put("belonged", appname);
        map.put("frontWord", fxhParam.getCoinCode());
        map.put("afterWord", fxhParam.getFixCoin());

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>(16);
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            logger.error("请求参数:" + paramMap);
            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            logger.error("返回参数" + returnMsg);
            if (StringUtils.isEmpty(returnMsg)) {
                jsonResult.setMsg("连接失败");
                jsonResult.setSuccess(false);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    logger.error("解密后价格" + price);

                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(price);
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(returnMsg);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;

    }


    @MethodName(name = "测试单个币（非交易对）实时价格")
    @RequestMapping(value = "/testInterfaceRealTime")
    @ResponseBody
    @CommonLog(name = "测试单个币（非交易对）实时价格")
    public JsonResult testInterfaceRealTime(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode("USDT");

        setApiConfig(redisService, fxhParam, "configCache:realtimepriceconfig");
        //获取平台名称
        String appname = getAppName(redisService, "configCache:extraParamConfig", "managerName");

        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", UUIDUtil.getUUID());
        map.put("belonged", appname);
        map.put("frontWord", fxhParam.getCoinCode());

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>(16);
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            logger.error("返回参数" + returnMsg);
            if (StringUtils.isEmpty(returnMsg)) {
                jsonResult.setMsg("连接失败");
                jsonResult.setSuccess(false);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());

                    logger.error("解密后价格" + price);

                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(price);
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(returnMsg);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }


    @MethodName(name = "测试获取k线接口")
    @RequestMapping(value = "/testInterface")
    @ResponseBody
    @CommonLog(name = "测试获取k线接口")
    public JsonResult testInterface(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode("USDT");
        fxhParam.setFixCoin("BTC");

        setApiConfig(redisService, fxhParam, "configCache:klinedataconfig");
        //获取平台名称
        String appname = getAppName(redisService, "configCache:extraParamConfig", "managerName");
        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", UUIDUtil.getUUID());
        map.put("belonged", appname);
        map.put("frontWord", fxhParam.getCoinCode());
        map.put("afterWord", fxhParam.getFixCoin());

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", JSONObject.toJSONString(map));

            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            logger.error("返回参数" + returnMsg);
            if (StringUtils.isEmpty(returnMsg)) {
                jsonResult.setMsg("连接失败");
                jsonResult.setSuccess(false);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    //String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    String price = jsonObject.getString("data");
                    logger.error("解密后价格" + price);

                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(returnMsg);
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(returnMsg);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;

    }


    @MethodName(name = "测试历史数据接口")
    @RequestMapping(value = "/testHisKlineData")
    @ResponseBody
    @CommonLog(name = "测试历史数据接口")
    public JsonResult testHisKlineData(HttpServletRequest request) {
        {
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
            String time = format.format(new Date(System.currentTimeMillis()));
            String randomStr = RandomStringUtils.random(4, false, true);

            JsonResult jsonResult = new JsonResult();
            //最新价
            RedisService redisService = SpringUtil.getBean("redisService");
            String start = sim.format(new Date(System.currentTimeMillis() - 130000));
            String end = sim.format(new Date(System.currentTimeMillis() - 120000));

            FXHParam fxhParam = new FXHParam();
            fxhParam.setCoinCode("BTC");
            fxhParam.setFixCoin("USDT");
            fxhParam.setStartTime(start);
            fxhParam.setEndTime(end);

            setApiConfig(redisService, fxhParam, "configCache:hisklinedataconfig");
            //获取平台名称
            String appname = getAppName(redisService, "configCache:extraParamConfig", "managerName");
            Map<String, Object> map = new HashMap<>(16);
            map.put("ordernumber", time + randomStr);
            map.put("belonged", appname);
            map.put("word", "BTC_USDT");
            map.put("startdate", fxhParam.getStartTime());
            map.put("enddate", fxhParam.getEndTime());

            try {
                String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("companyCode", fxhParam.getCompanyCode());
                paramMap.put("sign", sign);

                String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
                if (StringUtils.isEmpty(returnMsg)) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(returnMsg);
                } else {
                    JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                    if ("8888".equals(jsonObject.getString("resultCode"))) {
                        String result = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                        jsonResult.setSuccess(true);
                        jsonResult.setMsg(result);
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(returnMsg);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonResult;
        }

    }

    private static void setApiConfig(RedisService redisService, FXHParam fxhParam, String s) {
        JSONObject.parseArray(redisService.get(s)).forEach(app -> {
            AppConfig appConfig = JSONObject.parseObject(app.toString(), AppConfig.class);
            switch (appConfig.getConfigkey()) {
                case "klinedataurl":
                    fxhParam.setPayUrl(appConfig.getValue());
                    break;
                case "businessNumber":
                    fxhParam.setCompanyCode(appConfig.getValue());
                    break;
                case "publickey":
                    fxhParam.setPublicKey(appConfig.getValue());
                    break;
                case "privatekey":
                    fxhParam.setPrivateKey(appConfig.getValue());
                    break;
                case "testCoin":
                    fxhParam.setCoinCode(appConfig.getValue());
                    break;
            }
        });
    }

    @MethodName(name = "测试短信接口")
    @RequestMapping(value = "/smsConfigTest")
    @ResponseBody
    @CommonLog(name = "测试短信接口")
    public JsonResult smsConfigTest(HttpServletRequest request) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        String time = format.format(new Date(System.currentTimeMillis()));
        String randomStr = RandomStringUtils.random(4, false, true);

        JsonResult jsonResult = new JsonResult();
        //最新价
        RedisService redisService = SpringUtil.getBean("redisService");


        FXHParam fxhParam = getFxhParam(redisService);

        //获取平台名称
        String appname = getAppName(redisService, "configCache:extraParamConfig", "managerName");

        //获取模板签名
        String str = "";
        String state = "0";
        if(!fxhParam.getPhone().contains("+")){
            str = getAppName(redisService, "configCache:noticeConfig", "sms_take_phone");
        }else {
            str = getAppName(redisService, "configCache:noticeConfig", "sms_en_phone");
            state="1";
        }

        int start = str.indexOf("【");
        int end = str.indexOf("】");
        String msg = str.substring(end+1);
        String templateId = getAppName(redisService,"configCache:noticeConfig","sms_take_phone_key");
        msg = msg.replace("HRY_CODE","123456");

        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", time + randomStr);
        map.put("belonged", appname);
        map.put("phone", fxhParam.getPhone());
        map.put("msg", msg);
        map.put("state", state);


        //根据短信类型取相应的短信id



        String signName = str.substring(start+1,end);
        map.put("signName", signName);
        map.put("templateCode", templateId);

        Map<String, String> templateParam = new HashMap<>();

        templateParam.put("HRY_CODE", "123456");
        map.put("templateParam", JSONObject.toJSONString(templateParam));


        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            if (StringUtils.isEmpty(returnMsg)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(returnMsg);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    String result = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(jsonObject.getString("reason"));
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(returnMsg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;

    }

    private FXHParam getFxhParam(RedisService redisService) {
        FXHParam fxhParam = new FXHParam();


        JSONObject.parseArray(redisService.get("configCache:smsConfig")).forEach(app -> {
            AppConfig appConfig = JSONObject.parseObject(app.toString(), AppConfig.class);
            switch (appConfig.getConfigkey()) {
                case "klinedataurl":
                    fxhParam.setPayUrl(appConfig.getValue());
                    break;
                case "businessNumber":
                    fxhParam.setCompanyCode(appConfig.getValue());
                    break;
                case "publickey":
                    fxhParam.setPublicKey(appConfig.getValue());
                    break;
                case "privatekey":
                    fxhParam.setPrivateKey(appConfig.getValue());
                    break;
                case "sms_username":
                    fxhParam.setPhone(appConfig.getValue());
                    break;
            }
        });
        return fxhParam;
    }

    //获取平台名称
    public String getAppName(RedisService redisService, String s, String managerName) {
        //获取平台名称
        AtomicReference<String> name = new AtomicReference<>("");
        JSONArray configarray = JSONObject.parseArray(redisService.get(s));
        configarray.forEach(app -> {
            JSONObject appConfig = JSONObject.parseObject(app.toString());
            if (managerName.equals(appConfig.getString("configkey"))) {
                name.set(appConfig.getString("value"));
            }
        });
        return name.get();
    }

    @MethodName(name = "测试实名认证接口")
    @RequestMapping(value = "/juheConfigTest")
    @ResponseBody
    @CommonLog(name = "测试实名认证接口")
    public JsonResult juheConfigTest(HttpServletRequest request) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        String time = format.format(new Date(System.currentTimeMillis()));
        String randomStr = RandomStringUtils.random(4, false, true);

        JsonResult jsonResult = new JsonResult();
        //最新价
        RedisService redisService = SpringUtil.getBean("redisService");
        String start = sim.format(new Date(System.currentTimeMillis() - 13000));
        String end = sim.format(new Date(System.currentTimeMillis()));

        FXHParam fxhParam = new FXHParam();


        JSONObject.parseArray(redisService.get("configCache:juheConfig")).forEach(app -> {
            AppConfig appConfig = JSONObject.parseObject(app.toString(), AppConfig.class);
            switch (appConfig.getConfigkey()) {
                case "klinedataurl":
                    fxhParam.setPayUrl(appConfig.getValue());
                    break;
                case "businessNumber":
                    fxhParam.setCompanyCode(appConfig.getValue());
                    break;
                case "publickey":
                    fxhParam.setPublicKey(appConfig.getValue());
                    break;
                case "privatekey":
                    fxhParam.setPrivateKey(appConfig.getValue());
                    break;
                case "juhe_name":
                    fxhParam.setUserName(appConfig.getValue());
                    break;
                case "juhe_cardId":
                    fxhParam.setCardId(appConfig.getValue());
                    break;
            }
        });
        //获取平台名称
        String appname = getAppName(redisService, "configCache:extraParamConfig", "managerName");
        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", time + randomStr);
        map.put("belonged", appname);
        map.put("name",fxhParam.getUserName());
        map.put("idCard", fxhParam.getCardId());
        map.put("type", "nophoto");

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            if (StringUtils.isEmpty(returnMsg)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(returnMsg);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    String result = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(result);
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(returnMsg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

}
