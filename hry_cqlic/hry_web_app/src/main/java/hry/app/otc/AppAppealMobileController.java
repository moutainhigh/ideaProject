package hry.app.otc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.otc.remote.model.OtcAppTransactionRemote;
import hry.util.AWSUtil;
import hry.util.AzureUtil;
import io.swagger.annotations.ApiParam;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;

import hry.bean.JsonResult;
import hry.util.properties.PropertiesUtils;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.otc.remote.RemoteAdvertisementService;
import hry.otc.remote.model.AppAppealRemote;
import hry.util.OssUtil;
import hry.util.common.SpringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/otc/user/appAppeal")
@RequiresAuthentication
@Api(value = "App操作类", description = "OTC - 申述", tags = "OTC - 申述")
public class AppAppealMobileController {

    @Resource
    private RemoteAdvertisementService remoteAdvertisementService;

    @RequestMapping(value = "/index", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "跳转申诉页面", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号, transactionMode：交易方式(1在线购买,2在线出售,3本地购买)")
    public ApiJsonResult index(HttpServletRequest request, @ApiParam(name = "tradeNum", value = "订单号", required = true) @RequestParam String tradeNum,
                               @ApiParam(name = "transactionMode", value = "交易方式(1在线购买,2在线出售,3本地购买)", required = true) @RequestParam String transactionMode) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("tradeNum", tradeNum);

            map.put("transactionMode", transactionMode);
        }
        return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/addAppeal", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "新增述求", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号, transactionMode：1买家发起,2卖家发起, appeal：诉求标题, content：诉求详细信息, thingUrl：附件")
    public JsonResult addAppeal(HttpServletRequest request, @ApiParam(name = "transactionMode", value = "1买家发起,2卖家发起", required = true)@RequestParam String transactionMode,
                                @ApiParam(name = "tradeNum", value = "订单号", required = true)@RequestParam String tradeNum,
                                @ApiParam(name = "appeal", value = "诉求标题", required = true)@RequestParam String appeal,
                                @ApiParam(name = "content", value = "诉求详细信息", required = true) @RequestParam String content,
                                @ApiParam(name = "thingUrl", value = "附件", required = true) @RequestParam String thingUrl) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            if (StringUtil.isNotEmpty(tradeNum)) {
                if (StringUtil.isNotEmpty(thingUrl)) {
                    thingUrl = thingUrl.substring(0, thingUrl.length() - 1);
                }
                JsonResult addAppeal = remoteAdvertisementService.addAppeal(user.getCustomerId(), tradeNum, appeal, content, thingUrl, transactionMode);
                if (addAppeal.getSuccess()) {
                    return new JsonResult().setSuccess(true);
                }
                return new JsonResult().setSuccess(false).setMsg(addAppeal.getMsg());
            }
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("dingdanyichang"));
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }


    @RequestMapping(value = "/appealInfor", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "跳转订单流程页面 - 查询", httpMethod = "POST", response = JsonResult.class, notes = "transactionNum：订单号")
    public ApiJsonResult appealInfor(HttpServletRequest request, @ApiParam(name = "transactionNum", value = "订单号", required = true) @RequestParam String transactionNum) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            Map<String, Object> map = new HashMap<String, Object>();

            if (StringUtil.isNotEmpty(transactionNum)) {
                JsonResult appealInfor = remoteAdvertisementService.appealInfor(transactionNum);
                map = (Map<String, Object>) appealInfor.getObj();

                if (map != null && map.size() > 0) {
                    map.put("tradeNum", transactionNum);
                    map.put("app", map.get("app"));
                    OtcAppTransactionRemote otcappTransactionRemote = (OtcAppTransactionRemote) map.get("app");
                    if (StringUtil.isNotEmpty(otcappTransactionRemote.getStateZHCN())) {
                        otcappTransactionRemote.setStateZHCN(SpringUtil.diff(otcappTransactionRemote.getStateZHCN()));
                    }
                    if (StringUtil.isNotEmpty(otcappTransactionRemote.getPayType())) {
                        String[] split = otcappTransactionRemote.getPayType().split(",");
                        String str = "";
                        for (int i = 0; i < split.length; i++) {
                            str += SpringUtil.diff(split[i]) + ",";
                        }
                        if (str.length() > 1) {
                            str = str.substring(0, str.length() - 1);
                        }
                        otcappTransactionRemote.setPayType(str);
                    }

                    map.put("payTypeRemake", map.get("payTypeRemake"));
                    map.put("appAppealRemote", map.get("appAppealRemote"));
                    map.put("isFixed", map.get("isFixed"));
                    map.put("paymentTerm", map.get("paymentTerm"));
                    map.put("releaseId", map.get("releaseId"));
                    map.put("tradeMoney", map.get("tradeMoney"));
                    //备注
                    map.put("remark", map.get("remark"));
                }
                //支付信息
                if (StringUtil.isNotEmpty(map.get("releaseId").toString())) {
                    JsonResult orderAccounting = remoteAdvertisementService.orderAccounting(transactionNum, Long.valueOf(map.get("releaseId").toString()));
                    Map<String, Object> mapPayment = (Map<String, Object>) orderAccounting.getObj();
                    if (mapPayment != null && mapPayment.size() > 0) {
                        map.put("bank", mapPayment.get("bank"));
                        map.put("alipay", mapPayment.get("alipay"));
                        map.put("wechat", mapPayment.get("wechat"));
                    }
                }
            }
            return new ApiJsonResult().setSuccess(true).setObj(map);
        }
        return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/appealBuySell", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "买方回复", httpMethod = "POST", response = JsonResult.class, notes = "transactionNum：订单号")
    public ApiJsonResult appealBuySell(HttpServletRequest request, @ApiParam(name = "transactionNum", value = "订单号", required = true) @RequestParam String transactionNum) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            AppAppealRemote appAppealRemote = remoteAdvertisementService.getAppAppealByNum(transactionNum);

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("tradeNum", transactionNum);
            map.put("appAppealRemote", appAppealRemote);

            return new ApiJsonResult().setSuccess(true).setObj(map);
        }
        return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    public static void main(String[] args) {
        System.out.println(28 % 12);
    }


    /**
     * 申诉方取消订单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/cancelAppeal", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "申诉方取消订单", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号")
    public ApiJsonResult cancelAppeal(HttpServletRequest request, @ApiParam(name = "tradeNum", value = "订单号", required = true) @RequestParam String tradeNum) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            if (StringUtil.isNotEmpty(tradeNum)) {
                JsonResult jsonResult = remoteAdvertisementService.cancelAppeal(tradeNum);
                if (jsonResult != null && jsonResult.getSuccess()) {
                    return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff("quxiaoshensuchenggong"));
                } else {
                    return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff("quxiaoshensushibai"));
                }
            }
            return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("jiaoyidanhaoweikong"));
        }
        return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/addpicture", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "图片上传", httpMethod = "POST", response = JsonResult.class, notes = "")
    public ApiJsonResult addpicture(@ApiParam(name = "file", value = "文件域", required = true) @RequestParam(value = "file", required = false) MultipartFile file,
                                 HttpServletRequest request) {
        if (file == null || file.isEmpty()) {
            return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingshangchuantupian"));
        }
        String path = request.getSession().getServletContext().getRealPath("/hryfile/personalAsset/");
        if (!path.isEmpty()) {
            path = path.replace("\\front", "").replace("\\ROOT", "").replace("\\root", "");
            path = path.replace("/front", "").replace("/ROOT", "").replace("/root", "");
        }

        String fileName = file.getOriginalFilename();

        String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
                : null;
        String turefileName = System.currentTimeMillis() + "." + type;

        System.out.println(path);
        File targetFile = new File(path, turefileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 获取图片地址
        String url = "";
        // 保存
        try {
            //OssUtil.upload(file.getInputStream(),"hryfile/personalAsset/" + turefileName,false);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            //压缩文件
            Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.8f).toOutputStream(byteArrayOutputStream);
            //上传流
            InputStream ossStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();

            String img_server_type = hry.util.PropertiesUtils.APP.getProperty("app.img.server.type");
            switch (img_server_type) {
                case "oss": // 阿里云oss
                    OssUtil.upload(ossStream, "hryfile/" + turefileName, false);
                    //url = OssUtil.getUrl("hryfile/" + turefileName);
                    break;
                case "aws": // 亚马逊aws
                    AWSUtil.uploadToS3(ossStream, "hryfile/" + turefileName);
                    //url = "hryfile/" + turefileName;
                    break;
                case "azure": // 微软azure
                    AzureUtil.upload(ossStream, "hryfile/" + turefileName);
                    //url = "hryfile/" + turefileName;
                    break;
                default: // 默认阿里云oss
                    OssUtil.upload(ossStream, "hryfile/" + turefileName, true);
                    //url = OssUtil.getUrl("hryfile/" + turefileName);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ApiJsonResult().setCode("hryfile/" + turefileName).setSuccess(true);
    }

    @RequestMapping(value = "/getPicture", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取图片", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号")
    public ApiJsonResult getPicture(HttpServletRequest request, @ApiParam(name = "url", value = "图片相对路径", required = true) @RequestParam String url) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            if (StringUtil.isNotEmpty(url)) {
                return new ApiJsonResult().setSuccess(true).setCode(OssUtil.getUrl(url));
            }
            return new ApiJsonResult().setSuccess(false).setCode("");
        }
        return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }
}
