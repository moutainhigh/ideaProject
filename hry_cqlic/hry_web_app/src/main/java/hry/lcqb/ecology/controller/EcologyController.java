package hry.lcqb.ecology.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.lcqb.ecology.model.EcologPlate;
import hry.licqb.remote.RemoteEcologyService;
import hry.manage.remote.model.User;
import hry.util.UUIDUtil;
import hry.util.common.SpringUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author zhouming
 * @date 2020/6/5 9:58
 * @Version 1.0
 */
@Controller
@RequestMapping("/ecology")
@Api(value = "李超钱包---生态相关接口", description = "李超钱包---生态相关接口", tags = "李超钱包---生态相关接口")
public class EcologyController {

    @ApiOperation(value = "生态基金---我的申请列表", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---申请列表")
    @RequestMapping("/user/findEcofundList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public FrontPage findEcofundList(HttpServletRequest request,
                                      @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                      @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return null;
        }
        RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("limit", limit);
        paramMap.put("offset", offset);
        paramMap.put("customerId", user.getCustomerId().toString());
        return remoteEcologyService.findEcofundPageList(paramMap);
    }

    @ApiOperation(value = "生态基金---查看申请详情", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---查看申请详情")
    @RequestMapping("/user/findEcofundDetails")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult findEcofundDetails(HttpServletRequest request,
                                  @ApiParam(name = "id", value = "申请Id", required = true) @RequestParam("id") String id) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("customerId", user.getCustomerId().toString());
            paramMap.put("id", id);
            return remoteEcologyService.getEcofund(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    @ApiOperation(value = "生态基金---申请", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---申请")
    @RequestMapping("/user/applyEcofund")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult applyEcofund (HttpServletRequest request,
                                    @ApiParam(name = "activityName", value = "团队名称", required = true) @RequestParam("activityName") String activityName,
                                    @ApiParam(name = "activityDate", value = "活动时间", required = true) @RequestParam("activityDate") String activityDate,
                                    @ApiParam(name = "activityAddress", value = "活动地址", required = true) @RequestParam("activityAddress") String activityAddress,
                                    @ApiParam(name = "peopleCount", value = "活动人数", required = true) @RequestParam("peopleCount") String peopleCount,
                                    @ApiParam(name = "activityIntro", value = "活动简介", required = true) @RequestParam("activityIntro") String activityIntro,
                                    @ApiParam(name = "activityImage", value = "活动图片", required = true) @RequestParam("activityImage") String activityImage
                                    ) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("customerId", user.getCustomerId().toString());
            paramMap.put("activityName", activityName);
            paramMap.put("activityDate", activityDate);
            paramMap.put("activityAddress", activityAddress);
            paramMap.put("peopleCount", peopleCount);
            paramMap.put("activityIntro", activityIntro);
            paramMap.put("activityImage", activityImage);
            return remoteEcologyService.applyEcofund(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    @ApiOperation(value = "生态基金---补充申请", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---补充申请")
    @RequestMapping("/user/againApplyEcofund")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult againApplyEcofund (HttpServletRequest request,
                                    @ApiParam(name = "id", value = "申请id", required = true) @RequestParam("id") String id,
                                    @ApiParam(name = "againActivityDate", value = "活动时间", required = true) @RequestParam("againActivityDate") String againActivityDate,
                                    @ApiParam(name = "againActivityAddress", value = "活动地址", required = true) @RequestParam("againActivityAddress") String againActivityAddress,
                                    @ApiParam(name = "againPeopleCount", value = "活动人数", required = true) @RequestParam("againPeopleCount") String againPeopleCount,
                                    @ApiParam(name = "againActivityIntro", value = "活动简介", required = true) @RequestParam("againActivityIntro") String againActivityIntro,
                                    @ApiParam(name = "againActivityImage", value = "活动图片", required = true) @RequestParam("againActivityImage") String againActivityImage,
                                    @ApiParam(name = "againActivityVideo", value = "活动视频", required = true) @RequestParam("againActivityVideo") String againActivityVideo
    ) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("customerId", user.getCustomerId().toString());
            paramMap.put("id", id);
            paramMap.put("againActivityDate", againActivityDate);
            paramMap.put("againActivityAddress", againActivityAddress);
            paramMap.put("againPeopleCount", againPeopleCount);
            paramMap.put("againActivityIntro", againActivityIntro);
            paramMap.put("againActivityImage", againActivityImage);
            paramMap.put("againActivityVideo", againActivityVideo);
            return remoteEcologyService.applyEcofund(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    @ApiOperation(value = "生态基金---平台审核通过-用户待确认", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---平台审核通过-用户待确认")
    @RequestMapping("/user/userAudit")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult userAudit(HttpServletRequest request,
                                @ApiParam(name = "id", value = "申请id", required = true) @RequestParam("id") String id,
                                @ApiParam(name = "activityStatus", value = "状态 4 不同意 5 同意(资料待补充)", required = true) @RequestParam("activityStatus") String activityStatus
                                ) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("customerId", user.getCustomerId().toString());
            paramMap.put("id", id);
            paramMap.put("activityStatus", activityStatus);
            return remoteEcologyService.updateEcofund(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    @ApiOperation(value = "生态基金---获取用户未完成基金订单", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---获取用户未完成生态基金订单")
    @RequestMapping("/user/getUnEcofund")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult getUnEcofund(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            return remoteEcologyService.getUnEcofund(user.getCustomerId());
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }


    @ApiOperation(value = "视频上传", httpMethod = "POST", response = ApiJsonResult.class, notes = "视频上传")
    @RequestMapping(value = "/user/uploadVideo")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult uploadVideo(HttpServletRequest request,
                                  @ApiParam("file") MultipartFile file) throws IOException {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            JsonResult json = new JsonResult();
            json.setSuccess(true);
            if (file != null) {
                String[] split = file.getOriginalFilename().split("\\.");
                int fileNameHasDecimalPoints=split.length-1;
                String fileName =  UUIDUtil.getUUID()+"."+file.getOriginalFilename().split("\\.")[fileNameHasDecimalPoints];
                String[] urllist = AliyunOSSClientUtil.uploadObject2OSS(AliyunOSSClientUtil.getOSSClient(),file, fileName);
                String url = urllist[1];
                System.out.println("上传的路径："+url);
                json.setObj(url);
            }
            json.setSuccess(true);
            return json;
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~生态入驻接口~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @ApiOperation(value = "生态入驻---我的申请列表", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---申请列表")
    @RequestMapping("/user/findEcologEnterList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public FrontPage findEcologEnterList(HttpServletRequest request,
                                      @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                      @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return null;
        }
        RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("limit", limit);
        paramMap.put("offset", offset);
        paramMap.put("customerId", user.getCustomerId().toString());
        return remoteEcologyService.findEcologEnterPageList(paramMap);
    }

    @ApiOperation(value = "生态入驻---查看申请详情", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---查看申请详情")
    @RequestMapping("/user/findEcologEnterDetails")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult findEcologEnterDetails(HttpServletRequest request,
                                             @ApiParam(name = "id", value = "申请Id", required = true) @RequestParam("id") String id) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("customerId", user.getCustomerId().toString());
            paramMap.put("id", id);
            return remoteEcologyService.getEcologEnter(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    @ApiOperation(value = "生态入驻---版块列表", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---申请列表")
    @RequestMapping("/user/findEcologPlateList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public FrontPage findEcologPlateList(HttpServletRequest request,
                                      @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                      @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return null;
        }
        RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
        Locale locale = LocaleContextHolder.getLocale();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("limit", limit);
        paramMap.put("offset", offset);
        FrontPage page = remoteEcologyService.findEcologPlatePageList(paramMap);
        if (!"zh_CN".equals(locale.toString())) {
            List list = page.getRows();
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
            JSONArray newArray = new JSONArray();
            for (Object o : array) {
                JSONObject obj = JSONObject.parseObject(o.toString());
                if (obj.get("languageKey") != null) {
                    obj.put("plateName", SpringUtil.diff(obj.getString("languageKey")));
                }
                newArray.add(obj);
            }
            List<EcologPlate> newlist = JSONArray.parseArray(newArray.toJSONString(), EcologPlate.class);
            page.setRows(newlist);
        }
        return page;
    }

    @ApiOperation(value = "生态入驻---已入驻列表", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---申请列表")
    @RequestMapping("/user/findHasEnterList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public FrontPage findHasEnterList(HttpServletRequest request,
                                         @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                         @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
                                         @ApiParam(name = "plateId", value = "版块id", required = true) @RequestParam("plateId") String plateId) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return null;
        }
        RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("limit", limit);
        paramMap.put("offset", offset);
        paramMap.put("plateId", plateId);
        return remoteEcologyService.findHasEnterList(paramMap);
    }

    @ApiOperation(value = "生态入驻---申请", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态基金---申请")
    @RequestMapping("/user/applyEcologEnter")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult applyEcologEnter (HttpServletRequest request,
                                    @ApiParam(name = "plateId", value = "版块Id", required = true) @RequestParam("plateId") String plateId,
                                    @ApiParam(name = "enterLevel", value = "入驻等级 A 、B", required = true) @RequestParam("enterLevel") String enterLevel,
                                    @ApiParam(name = "enterName", value = "入驻名称", required = true) @RequestParam("enterName") String enterName,
                                    @ApiParam(name = "enterLogo", value = "入驻logo", required = true) @RequestParam("enterLogo") String enterLogo,
                                    @ApiParam(name = "downloadLink", value = "下载链接", required = true) @RequestParam("downloadLink") String downloadLink,
                                    @ApiParam(name = "enterApplyIntro", value = "入驻简介", required = true) @RequestParam("enterApplyIntro") String enterApplyIntro
    ) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("customerId", user.getCustomerId().toString());
            paramMap.put("plateId", plateId);
            paramMap.put("enterLevel", enterLevel);
            paramMap.put("enterName", enterName);
            paramMap.put("enterLogo", enterLogo);
            paramMap.put("downloadLink", downloadLink);
            paramMap.put("enterApplyIntro", enterApplyIntro);
            return remoteEcologyService.applyEcologEnter(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    @ApiOperation(value = "生态入驻---付款确认", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态入驻---付款确认")
    @RequestMapping("/user/affirmPayment")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult affirmPayment(HttpServletRequest request,
                                @ApiParam(name = "id", value = "申请id", required = true) @RequestParam("id") String id,
                                @ApiParam(name = "enterStatus", value = "状态 4 用户拒绝 5 已确认付款（待核实）", required = true) @RequestParam("enterStatus") String enterStatus,
                                @ApiParam(name = "acceptAddress", value = "收款地址", required = false) @RequestParam("acceptAddress") String acceptAddress,
                                @ApiParam(name = "paymentAddress", value = "付款地址", required = false) @RequestParam("paymentAddress") String paymentAddress
    ) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("customerId", user.getCustomerId().toString());
            paramMap.put("id", id);
            paramMap.put("enterStatus", enterStatus);
            paramMap.put("acceptAddress", acceptAddress);
            paramMap.put("paymentAddress", paymentAddress);
            return remoteEcologyService.updateEcologEnter(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    @ApiOperation(value = "生态入驻---校验是否可以续费", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态入驻---校验是否可以续费")
    @RequestMapping("/user/checkRenew")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult checkRenew(HttpServletRequest request,
                                @ApiParam(name = "id", value = "申请id", required = true) @RequestParam("id") String id
    ) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("customerId", user.getCustomerId().toString());
            paramMap.put("id", id);
            return remoteEcologyService.checkRenew(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    @ApiOperation(value = "生态入驻---续费确认", httpMethod = "POST", response = ApiJsonResult.class, notes = "生态入驻---续费确认")
    @RequestMapping("/user/affirmRenew")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult affirmRenew(HttpServletRequest request,
                                    @ApiParam(name = "id", value = "申请id", required = true) @RequestParam("id") String id,
                                    @ApiParam(name = "acceptAddress", value = "收款地址", required = true) @RequestParam("acceptAddress") String acceptAddress,
                                    @ApiParam(name = "paymentAddress", value = "付款地址", required = true) @RequestParam("paymentAddress") String paymentAddress
    ) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("customerId", user.getCustomerId().toString());
            paramMap.put("id", id);
            paramMap.put("acceptAddress", acceptAddress);
            paramMap.put("paymentAddress", paymentAddress);
            return remoteEcologyService.affirmRenew(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

}
