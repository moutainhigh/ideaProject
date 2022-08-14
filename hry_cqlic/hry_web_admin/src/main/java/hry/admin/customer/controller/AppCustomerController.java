/**
 * Copyright:
 *
 * @author: liushilei
 * @version: V1.0
 * @Date: 2018-06-13 09:43:00
 */
package hry.admin.customer.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.customer.dao.AppCustomerDao;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.exchange.model.ExApiApply;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.exchange.service.ExApiApplyService;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.exchange.service.ExProductService;
import hry.admin.web.service.AppMessageService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.bus.BusRequestUtil;
import hry.bus.model.BusJsonResult;
import hry.bus.model.BusUpdateCustomerTO;
import hry.core.annotation.CommonLog;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.core.shiro.PasswordHelper;
import hry.redis.common.utils.RedisService;
import hry.util.MsgTypeEnum;
import hry.util.OssUtil;
import hry.util.QueryFilter;
import hry.util.UserRedisUtils;
import hry.util.properties.PropertiesUtils;
import hry.util.rsa.RSAUtils;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Copyright:   互融云
 *
 * @author: liushilei
 * @version: V1.0
 * @Date: 2018-06-13 09:43:00
 */
@Controller
@RequestMapping("/customer/appcustomer")
public class AppCustomerController extends BaseController<AppCustomer, Long> {

    @Resource(name = "appCustomerService")
    @Override
    public void setService(BaseService<AppCustomer, Long> service) {
        super.service = service;
    }

    @Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;

    @Resource
    AppPersonInfoService appPersonInfoService;
    @Resource
    private AppMessageService appMessageService;

    @Resource
    private ExApiApplyService exApiApplyService;

    @Resource
    RedisService redisService;
    @Resource
    ExProductService exProductService;

    @Resource
    private AppCustomerDao appCustomerDao;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("toCustomer")
    public ModelAndView login(HttpServletRequest request,String isReal) {
        ModelAndView mav = new ModelAndView();
        if("1".equals(isReal)){//已实名
            mav.setViewName("/admin/customer/appcustomerauditlist");
        }else{
            mav.setViewName("/admin/customer/appcustomerlist");
        }
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            //私钥存入session
            String privateKey = RSAUtils.getPrivateKey(keyMap);
            request.getSession().setAttribute("RSA_privateKey_resetPassword",privateKey);
            //公钥返回给业务
            String publicKey = RSAUtils.getPublicKey(keyMap);
            mav.addObject("RSA_publicKey",publicKey);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return mav;
    }
    @RequestMapping(value = "/see/{id}")
    public ModelAndView see(@PathVariable Long id) {
        AppCustomer appCustomer = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/customer/appcustomersee");
        mav.addObject("model", appCustomer);
        return mav;
    }

    @RequestMapping(value = "/auditsee/{id}")
    public ModelAndView auditsee(@PathVariable Long id) {
        AppCustomer appCustomer = service.get(id);
        AppPersonInfo appPersonInfo = appPersonInfoService.get(new QueryFilter(AppPersonInfo.class).addFilter("customerId=", appCustomer.getId()));
        String img_server_type = PropertiesUtils.APP.getProperty("app.img.server.type");
        switch (img_server_type) {
            case "oss": // 阿里云oss
                appPersonInfo.setPersonBank(OssUtil.getUrl(appPersonInfo.getPersonBank()));
                appPersonInfo.setPersonCard(OssUtil.getUrl(appPersonInfo.getPersonCard()));
                appPersonInfo.setFrontpersonCard(OssUtil.getUrl(appPersonInfo.getFrontpersonCard()));
                break;
            case "aws": // 亚马逊aws
                appPersonInfo.setPersonBank("https://lmz-exchange-public.s3.ap-east-1.amazonaws.com/" + appPersonInfo.getPersonBank());
                appPersonInfo.setPersonCard("https://lmz-exchange-public.s3.ap-east-1.amazonaws.com/" + appPersonInfo.getPersonCard());
                appPersonInfo.setFrontpersonCard("https://lmz-exchange-public.s3.ap-east-1.amazonaws.com/" + appPersonInfo.getFrontpersonCard());
                break;
            case "azure": // 微软azure
                appPersonInfo.setPersonBank("https://xunq.blob.core.windows.net/hry-exchange-public/" + appPersonInfo.getPersonBank());
                appPersonInfo.setPersonCard("https://xunq.blob.core.windows.net/hry-exchange-public/" + appPersonInfo.getPersonCard());
                appPersonInfo.setFrontpersonCard("https://xunq.blob.core.windows.net/hry-exchange-public/" + appPersonInfo.getFrontpersonCard());
                break;
            default: // 默认阿里云oss
                appPersonInfo.setPersonBank(OssUtil.getUrl(appPersonInfo.getPersonBank()));
                appPersonInfo.setPersonCard(OssUtil.getUrl(appPersonInfo.getPersonCard()));
                appPersonInfo.setFrontpersonCard(OssUtil.getUrl(appPersonInfo.getFrontpersonCard()));
                break;
        }
        appCustomer.setAppPersonInfo(appPersonInfo);
        ModelAndView mav = new ModelAndView("/admin/customer/realname");
        mav.addObject("model", appCustomer);
        return mav;
    }

    //查看已经实名过的用户信息
    @RequestMapping(value = "/auditedsee/{id}")
    @ResponseBody
    public JsonResult auditedsee(@PathVariable Long id) {
        JsonResult jsonResult = new JsonResult();
        AppCustomer appCustomer = service.get(id);
        AppPersonInfo appPersonInfo = appPersonInfoService.get(new QueryFilter(AppPersonInfo.class).addFilter("customerId=", appCustomer.getId()));
        String img_server_type = PropertiesUtils.APP.getProperty("app.img.server.type");
        switch (img_server_type) {
            case "oss": // 阿里云oss
                appPersonInfo.setPersonBank(OssUtil.getUrl(appPersonInfo.getPersonBank(),true));
                appPersonInfo.setPersonCard(OssUtil.getUrl(appPersonInfo.getPersonCard(),true));
                appPersonInfo.setFrontpersonCard(OssUtil.getUrl(appPersonInfo.getFrontpersonCard(),true));
                break;
            case "aws": // 亚马逊aws
                appPersonInfo.setPersonBank("https://lmz-exchange-public.s3.ap-east-1.amazonaws.com/" + appPersonInfo.getPersonBank());
                appPersonInfo.setPersonCard("https://lmz-exchange-public.s3.ap-east-1.amazonaws.com/" + appPersonInfo.getPersonCard());
                appPersonInfo.setFrontpersonCard("https://lmz-exchange-public.s3.ap-east-1.amazonaws.com/" + appPersonInfo.getFrontpersonCard());
                break;
            case "azure": // 微软azure
                appPersonInfo.setPersonBank("https://xunq.blob.core.windows.net/hry-exchange-public/" + appPersonInfo.getPersonBank());
                appPersonInfo.setPersonCard("https://xunq.blob.core.windows.net/hry-exchange-public/" + appPersonInfo.getPersonCard());
                appPersonInfo.setFrontpersonCard("https://xunq.blob.core.windows.net/hry-exchange-public/" + appPersonInfo.getFrontpersonCard());
                break;
            default: // 默认阿里云oss
                appPersonInfo.setPersonBank(OssUtil.getUrl(appPersonInfo.getPersonBank()));
                appPersonInfo.setPersonCard(OssUtil.getUrl(appPersonInfo.getPersonCard()));
                appPersonInfo.setFrontpersonCard(OssUtil.getUrl(appPersonInfo.getFrontpersonCard()));
                break;
        }
        appCustomer.setAppPersonInfo(appPersonInfo);
        jsonResult.setSuccess(true);
        jsonResult.setObj(appCustomer);
        return jsonResult;
    }


    @MethodName(name = "申请APIkey")
    @RequestMapping(value = "/applyApi")
    @ResponseBody
    public JsonResult applyApi(HttpServletRequest request) {
        String id = request.getParameter("id");
        String ip = request.getParameter("ip");
        JsonResult jsonResult = new JsonResult();
        ExApiApply exApiApply = new ExApiApply();
        exApiApply.setCustomerId(Long.valueOf(id));
        exApiApply.setIpAddress(ip);

        Map<String, String> map = exApiApplyService.createKey(exApiApply);

        List<ExApiApply> findList = exApiApplyService.findList(Long.valueOf(id));
        if (findList.size() > 1) {
            for (int i = 1; i < findList.size(); i++) {
                exApiApplyService.delete(findList.get(i).getId());
            }
        }

        jsonResult.setSuccess(true);
        jsonResult.setMsg(findList.get(0).getAccessKey());

        return jsonResult;
    }

    @CommonLog(name = "用户管理-审核拒绝")
    @RequestMapping(value = "/auditback/{id}")
    @ResponseBody
    @MyRequiresPermissions
    public JsonResult auditback(@PathVariable Long id) {
        JsonResult jsonResult = new JsonResult();
        AppCustomer appCustomer = service.get(id);
        Integer states = appCustomer.getStates();

        if (appCustomer.getStates() == 0) {
            jsonResult.setMsg("用户未实名");
            jsonResult.setSuccess(false);
        } else if (states == 1) {
            appCustomer.setStates(3);
            service.update(appCustomer);
            AppPersonInfo appPersonInfo = appPersonInfoService.get(new QueryFilter(AppPersonInfo.class).addFilter("customerId=", id));
            appPersonInfo.setTrueName("");
            appPersonInfo.setSurname("");
            appPersonInfo.setCardId("");
            appPersonInfo.setPersonBank("");
            appPersonInfo.setPersonCard("");
            appPersonInfo.setFrontpersonCard("");
            appPersonInfoService.update(appPersonInfo);
            jsonResult.setSuccess(true);
        } else if (appCustomer.getStates() == 2) {
            jsonResult.setMsg("用户已通过");
            jsonResult.setSuccess(false);
        } else if (appCustomer.getStates() == 3) {
            jsonResult.setMsg("用户已驳回");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, AppCustomer appCustomer) {
        return super.save(appCustomer);
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee(@PathVariable Long id) {
        AppCustomer appCustomer = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/customer/appcustomermodify");
        mav.addObject("model", appCustomer);
        return mav;
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public JsonResult modify(HttpServletRequest request, AppCustomer appCustomer) {
        return super.update(appCustomer);
    }


    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove(String ids) {
        return super.deleteBatch(ids);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(AppCustomer.class, request);
        PageResult findPageBySql = ((AppCustomerService) service).findPageBySql(filter);
        return findPageBySql;
    }

    @RequestMapping(value = "/listByIds")
    @ResponseBody
    public PageResult listIds(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(AppCustomer.class, request);
        PageResult findPageBySql = ((AppCustomerService) service).findPageBySql(filter);
        return findPageBySql;
    }

    @CommonLog(name = "用户管理-邮箱激活")
    @RequestMapping(value = "/isHasemail/{id}")
    @ResponseBody
    public JsonResult isHasemail(@PathVariable Long id) {
        JsonResult jsonResult = new JsonResult();
        AppCustomer appCustomer = service.get(id);
        QueryFilter queryFilter = new QueryFilter(AppPersonInfo.class);

        AppPersonInfo appPersonInfo = appPersonInfoService.getByCustomerId(appCustomer.getId());

        if (appCustomer.getHasEmail() != null && appCustomer.getHasEmail().intValue() == 1) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("已经激活");
            return jsonResult;
        }
        if(appPersonInfo != null){
            if (StringUtils.isEmpty(appPersonInfo.getEmail())) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("未绑定邮箱无法激活");
                return jsonResult;
            }

            if (appCustomer.getHasEmail() != null && appCustomer.getHasEmail().intValue() == 0) {
                appCustomer.setHasEmail(1);
                service.update(appCustomer);
                RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
                UserRedisUtils.userRedisToSession(appCustomer, redisService);
                jsonResult.setSuccess(true);
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("激活失败");
            }
        }else{
            jsonResult.setSuccess(false);
            jsonResult.setMsg("用户数据错误，激活失败");
        }

        return jsonResult;
    }


    @CommonLog(name = "用户管理-实名审核")
    @RequestMapping(value = "/audit/{id}")
    @ResponseBody
    public JsonResult audit(@PathVariable Long id) {
        JsonResult jsonResult = new JsonResult();
        AppCustomer appCustomer = service.get(id);
        if (appCustomer.getStates() == 0) {
            jsonResult.setMsg("用户未实名");
            jsonResult.setSuccess(false);
        } else if (appCustomer.getStates() == 1) {
            appCustomer.setStates(2);
            appCustomer.setIsReal(1);
            service.update(appCustomer);
            //发送站内内信
            appMessageService.sysSendMsg(appCustomer, MsgTypeEnum.REALNAME);
            //实名认证之后送币
            // 注册送币
            AppCustomerService appCustomerService = (AppCustomerService) ContextUtil.getBean("appCustomerService");
            appCustomerService.giveCoin(appCustomer.getId());

            QueryFilter queryFilter1 = new QueryFilter(ExDigitalmoneyAccount.class);
            queryFilter1.addFilter("customerId=",appCustomer.getId());
            ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(queryFilter1);


            QueryFilter queryFilter = new QueryFilter(AppPersonInfo.class);
            queryFilter.addFilter("customerId=", appCustomer.getId());
            // 人民币账户
            AppPersonInfo appPersonInfo = appPersonInfoService.get(queryFilter);

            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            String trueName = appPersonInfo.getTrueName();
            String surname = appPersonInfo.getSurname();
            appCustomer.setTrueName(trueName);
            appCustomer.setSurname(surname);
            exDigitalmoneyAccount.setTrueName(trueName);
            exDigitalmoneyAccount.setSurname(surname);
            UserRedisUtils.userRedisToSession(appCustomer, redisService);// by -- lixin 2018-04-27 20:36:05

            String config = redisService.get("configCache:all");
            JSONObject parseObject = JSONObject.parseObject(config);
            //appAccountService.openAccount(appCustomer, appPersonInfo, parseObject.get("language_code").toString(), ContextUtil.getWebsite());
            // 开通币账户

            exProductService.openDmAccount(appCustomer, appPersonInfo, ContextUtil.getWebsite(), parseObject.get("language_code").toString());

            //对接总线更新用户信息
            if ("true".equals(PropertiesUtils.APP.getProperty("app.blockbus"))) {

                String busNumber = appCustomerDao.getBusNumber(appCustomer.getId());
                if(!StringUtils.isEmpty(busNumber)){
                    System.out.println("总线实名开始=========");
                    BusUpdateCustomerTO busUpdateCustomerTO = new BusUpdateCustomerTO();
                    busUpdateCustomerTO.setBusCustomerNumber(busNumber);
                    busUpdateCustomerTO.setCustomerLocalNumber(appCustomer.getId().toString());
                    busUpdateCustomerTO.setIsReal(1);
                    busUpdateCustomerTO.setTrueName(appPersonInfo.getTrueName());
                    busUpdateCustomerTO.setSurName(appPersonInfo.getSurname());
                    busUpdateCustomerTO.setCardid(appPersonInfo.getCardId());
                    busUpdateCustomerTO.setCountry(appPersonInfo.getCountry());
                    //更新用户信息接口
                    BusJsonResult<BusUpdateCustomerTO> result= BusRequestUtil.updateCustomer(busUpdateCustomerTO);
                    System.out.println("总线实名通过回调========"+ JSON.toJSONString(result));
                }


            }

            jsonResult.setSuccess(true);
        } else if (appCustomer.getStates() == 2) {
            jsonResult.setMsg("用户已通过");
            jsonResult.setSuccess(false);
        } else if (appCustomer.getStates() == 3) {
            jsonResult.setMsg("用户已驳回");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }


    @CommonLog(name = "用户管理-批量通过")
    @RequestMapping(value = "/moreAudit")
    @ResponseBody
    @MyRequiresPermissions
    public JsonResult moreAudit(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String ids = request.getParameter("ids");
        String id[] = ids.split(",");
        for (int i = 0; i < id.length; i++) {
            Long intId = Long.valueOf(id[i]);
            AppCustomer appCustomer = service.get(intId);

            if (appCustomer.getStates() == 1) {
                appCustomer.setStates(2);
                appCustomer.setIsReal(1);
            } else  {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("所选数据包含未实名或已驳回数据，不能批量通过");
                return jsonResult;
            }


            service.update(appCustomer);

            //实名认证之后送币
            // 注册送币
            AppCustomerService appCustomerService = (AppCustomerService) ContextUtil.getBean("appCustomerService");
            appCustomerService.giveCoin(appCustomer.getId());


            QueryFilter queryFilter = new QueryFilter(AppPersonInfo.class);
            queryFilter.addFilter("customerId=", appCustomer.getId());
            // 人民币账户
            AppPersonInfo appPersonInfo = appPersonInfoService.get(queryFilter);

            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            String trueName = appPersonInfo.getTrueName();
            String surname = appPersonInfo.getSurname();
            appCustomer.setTrueName(trueName);
            appCustomer.setSurname(surname);
            UserRedisUtils.userRedisToSession(appCustomer, redisService);// by -- lixin 2018-04-27 20:36:05

            String config = redisService.get("configCache:all");
            JSONObject parseObject = JSONObject.parseObject(config);
            //appAccountService.openAccount(appCustomer, appPersonInfo, parseObject.get("language_code").toString(), ContextUtil.getWebsite());
            // 开通币账户

            exProductService.openDmAccount(appCustomer, appPersonInfo, ContextUtil.getWebsite(), parseObject.get("language_code").toString());
            jsonResult.setSuccess(true);
            //对接总线更新用户信息
            if ("true".equals(PropertiesUtils.APP.getProperty("app.blockbus"))) {
                String busNumber = appCustomerDao.getBusNumber(appCustomer.getId());
                if(!StringUtils.isEmpty(busNumber)){
                    System.out.println("批量总线实名开始=========");
                    BusUpdateCustomerTO busUpdateCustomerTO = new BusUpdateCustomerTO();
                    busUpdateCustomerTO.setBusCustomerNumber(busNumber);
                    busUpdateCustomerTO.setCustomerLocalNumber(appCustomer.getId().toString());
                    busUpdateCustomerTO.setIsReal(1);
                    busUpdateCustomerTO.setTrueName(appPersonInfo.getTrueName());
                    busUpdateCustomerTO.setSurName(appPersonInfo.getSurname());
                    busUpdateCustomerTO.setCardid(appPersonInfo.getCardId());
                    busUpdateCustomerTO.setCountry(appPersonInfo.getCountry());
                    //更新用户信息接口
                    BusJsonResult<BusUpdateCustomerTO> result= BusRequestUtil.updateCustomer(busUpdateCustomerTO);
                    System.out.println("批量总线实名通过回调========"+ JSON.toJSONString(result));
                }
            }


        }


        return jsonResult;
    }

    @CommonLog(name = "用户管理-重置密码")
    @RequestMapping(value = "/setpw", method = RequestMethod.POST)
    @ResponseBody
    @MyRequiresPermissions
    public JsonResult setpw(HttpServletRequest request,Long id, String password) {
        JsonResult result = new JsonResult();
        try {
            AppCustomer appCustomer = service.get(id);
            PasswordHelper passwordHelper = new PasswordHelper();
            //获得rsa私钥
            String RSA_privateKey = (String) request.getSession().getAttribute("RSA_privateKey_resetPassword");
            if(StringUtils.isEmpty(RSA_privateKey)){
                return new JsonResult().setMsg("登录超时，刷新页面!").setCode("1000");
            }
            //解密
            password = RSAUtils.decryptDataOnJava(password,RSA_privateKey);
            String encryString = passwordHelper.encryString(password, appCustomer.getSalt());
            appCustomer.setPassWord(encryString);
            service.update(appCustomer);
            //解密完成后删除私钥
            request.getSession().removeAttribute("RSA_privateKey_resetPassword");
            result.setSuccess(true);
            //发送站内内信
            appMessageService.sysSendMsg(appCustomer, MsgTypeEnum.MODIFYPWD);
        } catch (Exception e) {
            result.setSuccess(false);
        }
        return result;
    }




    @CommonLog(name = "用户管理-清除实名信息")
    @RequestMapping(value = "/auditall/{id}")
    @ResponseBody
    public JsonResult auditall(@PathVariable Long id) {
        JsonResult jsonResult = new JsonResult();
        AppCustomer appCustomer = service.get(id);
        appCustomer.setStates(0);
        appCustomer.setIsReal(0);
        service.update(appCustomer);

        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        UserRedisUtils.userRedisToSession(appCustomer, redisService);// by -- lixin 2018-04-27 20:36:05

        AppPersonInfo appPersonInfo = appPersonInfoService.getByCustomerId(id);
        appPersonInfo.setTrueName("");
        appPersonInfo.setSurname("");
        appPersonInfo.setCardId("");
        appPersonInfo.setPersonBank("");
        appPersonInfo.setPersonCard("");
        appPersonInfo.setFrontpersonCard("");
        appPersonInfoService.update(appPersonInfo);
        jsonResult.setSuccess(true);
        //对接总线更新用户信息
        if ("true".equals(PropertiesUtils.APP.getProperty("app.blockbus"))) {
            String busNumber = appCustomerDao.getBusNumber(appCustomer.getId());
            if(!StringUtils.isEmpty(busNumber)){
                BusUpdateCustomerTO busUpdateCustomerTO = new BusUpdateCustomerTO();
                busUpdateCustomerTO.setBusCustomerNumber(busNumber);
                busUpdateCustomerTO.setCustomerLocalNumber(appCustomer.getId().toString());
                busUpdateCustomerTO.setIsReal(0);
                busUpdateCustomerTO.setTrueName("");
                busUpdateCustomerTO.setSurName("");
                busUpdateCustomerTO.setCardid("");
                //更新用户信息接口
                BusJsonResult<BusUpdateCustomerTO> result= BusRequestUtil.updateCustomer(busUpdateCustomerTO);
                System.out.println("清空实名总线实名回调========"+ JSON.toJSONString(result));
            }

        }
        return jsonResult;
    }

    @CommonLog(name = "用户管理-交易关闭")
    @RequestMapping(value = "/fnCloseChange/{id}")
    @ResponseBody
    public JsonResult close(@PathVariable Long id) {
        JsonResult jsonResult = new JsonResult();
        AppCustomer appCustomer = service.get(id);
        if (appCustomer != null) {
            appCustomer.setIsChange(Integer.valueOf(1));
            service.update(appCustomer);

            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            UserRedisUtils.userRedisToSession(appCustomer, redisService);

            jsonResult.setSuccess(true);
            return jsonResult;
        } else {
            jsonResult.setMsg("不存在该用户");
        }
        return jsonResult;
    }


    @CommonLog(name = "用户管理-交易开启")
    @RequestMapping(value = "/openChange/{id}")
    @ResponseBody
    public JsonResult open(@PathVariable Long id) {
        JsonResult jsonResult = new JsonResult();
        AppCustomer appCustomer = service.get(id);
        if (appCustomer != null) {
            if(appCustomer.getIsChange().intValue()==0){
                jsonResult.setMsg("已经是开启状态了");
                jsonResult.setSuccess(false);
                return jsonResult;
            }
            appCustomer.setIsChange(Integer.valueOf(0));
            service.update(appCustomer);

            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            UserRedisUtils.userRedisToSession(appCustomer, redisService);
            jsonResult.setSuccess(true);
            return jsonResult;
        } else {
            jsonResult.setMsg("不存在该用户");
        }
        return jsonResult;
    }

    @CommonLog(name = "用户管理-解禁")
    @RequestMapping(value = "/permissible/{id}", method = RequestMethod.GET)
    @ResponseBody
    @MyRequiresPermissions
    public JsonResult permissible(@PathVariable Long id) {
        AppCustomerService appCustomerService = (AppCustomerService) service;
        AppCustomer appCustomer = appCustomerService.get(id);
        AppPersonInfo appPersonInfo=appPersonInfoService.getByCustomerId(id);
        String i=redisService.get("loginErrorCount:" + appPersonInfo.getEmail().toLowerCase());
        String j=redisService.get("loginErrorCount:" + appPersonInfo.getMobilePhone());
        if(!StringUtils.isEmpty(i)) {
            int loginCount = Integer.parseInt(i);
            if (loginCount >= 5) {
                appCustomer.setIsDelete(1);
            }
        }
        if(!StringUtils.isEmpty(j)){
            int loginCount = Integer.parseInt(j);
            if (loginCount >= 5) {
                appCustomer.setIsDelete(1);
            }
        }
        if(appCustomer.getIsDelete().intValue()==0){
            return new JsonResult().setSuccess(false).setMsg("未禁用状态不可解禁");
        }
        JsonResult result = appCustomerService.storpCustomer(id, false);
        return result;
    }


    @CommonLog(name = "用户管理-禁用")
    @RequestMapping(value = "/forbidden/{id}", method = RequestMethod.GET)
    @ResponseBody
    @MyRequiresPermissions
    public JsonResult forbidden(@PathVariable Long id) {
        AppCustomerService appCustomerService = (AppCustomerService) service;
        JsonResult result = appCustomerService.storpCustomer(id, true);
        return result;
    }

    @CommonLog(name = "动态收益-开启")
    @RequestMapping(value = "/openDynamic/{id}", method = RequestMethod.GET)
    @ResponseBody
    @MyRequiresPermissions
    public JsonResult openDynamic (@PathVariable Long id) {
        return ((AppCustomerService) service).stropUserDynamic(id, true);
    }
    @CommonLog(name = "动态收益-关闭")
    @RequestMapping(value = "/closeDynamic/{id}", method = RequestMethod.GET)
    @ResponseBody
    @MyRequiresPermissions
    public JsonResult closeDynamic (@PathVariable Long id) {
        return  ((AppCustomerService) service).stropUserDynamic(id, false);
    }

    @CommonLog(name = "用户管理-重置安全策略")
    @RequestMapping(value = "/resetSecurity/{id}")
    @ResponseBody
    public JsonResult resetSecurity(@PathVariable Long id) {
        JsonResult jsonResult = new JsonResult();
        AppCustomer appCustomer = service.get(id);
        appCustomer.setSafeLoginType(1);
        appCustomer.setSafeTixianType(1);
        appCustomer.setSafeTradeType(1);
        service.update(appCustomer);
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        UserRedisUtils.userRedisToSession(appCustomer, redisService);
        jsonResult.setSuccess(true);
        return jsonResult;
    }
    
    @RequestMapping(value = "/findListGroupByDay")
    @ResponseBody
    public PageResult findListGroupByDay(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(AppCustomer.class, request);
        PageResult findPageBySql = ((AppCustomerService) service).findListGroupByDay(filter);
        return findPageBySql;
    }

}
