package hry.app.otc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.manage.remote.RemoteAppTransactionManageService;
import hry.manage.remote.model.AppBankCardManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.util.common.SpringUtil;
import hry.util.properties.StringConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value="/otc/user/appbankcode")
@RequiresAuthentication
@Api(value= "App操作类", description ="银行卡管理",tags = "银行卡管理")
public class AppBankCodeController {

    @Resource
    private RedisService redisService;

    @Resource
    private RemoteAppTransactionManageService remoteAppTransactionManageService;

    @RequestMapping(value = "/findBankCard")
    @ApiOperation(value = "查询当前账户下的银行卡", httpMethod = "POST", response = JsonResult.class, notes = "查询当前账户下的银行卡")
    @ResponseBody
    public ApiJsonResult findBankCard(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            List<AppBankCardManage> list = remoteAppTransactionManageService.findByCustomerId(user.getCustomerId());
            return new ApiJsonResult().setSuccess(true).setObj(list);
        }
        return new ApiJsonResult().setSuccess(false).setMsg("请登录或重新登录");
    }

    @RequestMapping(value = "/removeBankCard")
    @ApiOperation(value = "删除银行卡", httpMethod = "POST", response = JsonResult.class, notes = "删除银行卡")
    @ResponseBody
    public ApiJsonResult removeBankCard(HttpServletRequest request, @ApiParam(name = "id", value = "银行卡Id", required = true) @RequestParam Long id){
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteResult remoteresult = remoteAppTransactionManageService.delete(id);
            String diff = SpringUtil.diff(remoteresult.getMsg().toString(),"zh_CN");
            jsonResult.setSuccess(remoteresult.getSuccess()).setMsg(SpringUtil.diff(remoteresult.getMsg().toString(),"zh_CN"));
            return jsonResult;
        }
        return jsonResult.setSuccess(false).setMsg("登录已超时");
    }


    /**
     * 设置默认的银行卡
     * @param request
     * @return
     */
    @RequestMapping(value = "/setDefaultBankCard")
    @ApiOperation(value = "设置默认的银行卡", httpMethod = "POST", response = JsonResult.class, notes = "设置默认的银行卡")
    @ResponseBody
    public ApiJsonResult setDefaultBankCard(HttpServletRequest request , @ApiParam(name = "id", value = "银行卡Id", required = true) Long id) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteResult remoteresult = remoteAppTransactionManageService.setDefaultBankCard(id);
            String diff = SpringUtil.diff(remoteresult.getMsg().toString(),"zh_CN");
            jsonResult.setSuccess(remoteresult.getSuccess()).setMsg(SpringUtil.diff(remoteresult.getMsg().toString(),"zh_CN"));
            return jsonResult;
        }
        return jsonResult.setSuccess(false).setMsg(SpringUtil.diff("shezhimorenshibai"));
    }

    public static void main(String[] args) {
        /*String aa = "{\"mobile\":\"13522221111\"}";
        JSONObject json = JSONObject.parseObject(aa);
        System.out.println(json.get("mobile"));*/

        int[] a = {16, 15, 10, 6, 9};

        for(int i=0; i<a.length-1; i++){
            boolean flag = false;
            for(int j= i+1; j<a.length; j++){
                if(a[i] > a[j]){
                    int t = a[j];
                    a[j] = a[i];
                    a[i] = t;
                    flag = true;
                }
            }
            if(!flag) break;
        }

        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }

    /**
     * 查询省
     * @return
     */
    @RequestMapping("/findArea")
    @ApiOperation(value = "查询省", httpMethod = "POST", response = JsonResult.class, notes = "查询省")
    @ResponseBody
    public ApiJsonResult findArea(HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            jsonResult.setObj(redisService.get(StringConstant.AREA_CACHE));
            jsonResult.setSuccess(true);

            return  jsonResult;
        }
        return jsonResult.setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    /**
     * 查询市
     * @return
     */
    @RequestMapping("/appcity/{key}")
    @ApiOperation(value = "查询市", httpMethod = "POST", response = JsonResult.class, notes = "key为省的key")
    @ResponseBody
    public ApiJsonResult appcity(HttpServletRequest request, @ApiParam(name = "key", value = "key为省的key", required = true) @PathVariable String key) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            String value1 = redisService.get(StringConstant.AREA_CACHE);
            JSONArray jsona = JSONArray.parseArray(value1);
            String json_n = "";
            for(int i=0;i<jsona.size();i++){
                String jsonvalur = jsona.getString(i);
                if(jsonvalur.contains(key)){
                    JSONObject jsono = jsona.getJSONObject(i);
                    json_n = jsono.get("cities").toString().replace("[", "").replace("]", "");
                }
            }
            jsonResult.setObj(json_n);
            jsonResult.setSuccess(true);
            return jsonResult;
        }
        return jsonResult.setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    /**
     * 保存银行卡
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveBankCard")
    @ApiOperation(value = "保存银行卡", httpMethod = "POST", response = JsonResult.class, notes = "bankname银行,p1省,c1市,subBank开户支行,subBankNum银行机构代码"
            + ",trueName持卡人,account银行卡号")
    @ResponseBody
    public ApiJsonResult saveBankCard(HttpServletRequest request,
                                      @ApiParam(name = "bankname", value = "银行", required = true) @RequestParam String bankname,
                                      @ApiParam(name = "p1", value = "省", required = true) @RequestParam String p1,
                                      @ApiParam(name = "c1", value = "市", required = true) @RequestParam String c1,
                                      @ApiParam(name = "subBank", value = "开户支行", required = true) @RequestParam String subBank,
                                      @ApiParam(name = "subBankNum", value = "银行机构代码", required = true) @RequestParam  String subBankNum,
                                      @ApiParam(name = "trueName", value = "持卡人", required = true) @RequestParam  String trueName,
                                      @ApiParam(name = "surName", value = "银行卡号", required = true) @RequestParam  String surName,
                                      @ApiParam(name = "account", value = "虚拟账户", required = false) @RequestParam String account,
                                      @ApiParam(name = "type", value = "类型 1银行卡 2支付宝 3微信", required = true) @RequestParam  Integer type) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            AppBankCardManage appBankCardManage = new AppBankCardManage();
            appBankCardManage.setUserName(user.getMobile());
            appBankCardManage.setCardBank(bankname);
            appBankCardManage.setBankProvince(p1);
            appBankCardManage.setBankAddress(c1);
            appBankCardManage.setSubBank(subBankNum);
            appBankCardManage.setSubBankNum(subBankNum);
            appBankCardManage.setType(type);
            //名
            appBankCardManage.setTrueName(trueName);
            //姓
            appBankCardManage.setSurName(surName);

            appBankCardManage.setCardNumber(account);
            try {
                RemoteResult remoteResult = remoteAppTransactionManageService.saveBankCard(user,appBankCardManage);
                return jsonResult.setSuccess(remoteResult.getSuccess()).setMsg(SpringUtil.diff(remoteResult.getMsg().toString(),"zh_CN")).setObj(remoteResult.getObj());
            } catch (Exception e) {
                return jsonResult.setMsg("远程调用出错");
            }
        }
        return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

}
