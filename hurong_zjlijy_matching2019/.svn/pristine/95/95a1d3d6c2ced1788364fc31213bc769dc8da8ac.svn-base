package hry.mall.third.remote;

import com.github.pagehelper.Page;
import hry.bean.JsonResult;
import hry.lend.model.page.FrontPage;
import hry.mall.lend.person.model.AppPersonInfo;
import hry.mall.lend.person.service.AppPersonInfoService;
import hry.mall.platform.model.ThirdpayRecord;
import hry.mall.platform.service.ThirdpayRecordService;
import hry.mall.third.remote.service.WeixingPayService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RemoteThirdPayServiceImpl implements RemoteThirdPayService {

    @Resource
    private WeixingPayService weixingPayService;

    @Resource
    private ThirdpayRecordService thirdpayRecordService;
    @Resource
    private AppPersonInfoService appPersonInfoService;


    @Override
    public JsonResult getMerchantBalance() {
        return weixingPayService.getMerchantBalance();
    }

    @Override
    public JsonResult telephoneRecharge(String memberId, String sporderid, String mobile, String price) {
        JsonResult jsonResult = null;
        //获取用户信息
        QueryFilter f = new QueryFilter(AppPersonInfo.class);
        f.addFilter("customerId=", memberId);
        AppPersonInfo appPersonInfo = appPersonInfoService.get(f);

        //转载交易记录实体
        ThirdpayRecord record = new ThirdpayRecord();
        record.setMemberId(appPersonInfo.getCustomerId());
        record.setLoginName(appPersonInfo.getMobilePhone()); // 手机号
        record.setThirdPayConfig("weixing"); // 第三方标识 微星
        record.setInterfaceType("httpPost");
        record.setDealMoney(new BigDecimal(price));// 交易金额
        record.setRequestTime(new Date()); // 接口请求时间
        record.setStatus(1); // 0=开始请求，1=已发起请求，2=交易成功，3=交易失败，4=第三方交易成功但平台处理业务数据异常
        record.setRecordNumber(sporderid); // 接口流水号
        record.setPhone(mobile); // 充值号码
        //测试成功---上线时放开
        // 上线
        price = new BigDecimal(price).stripTrailingZeros().toPlainString(); //去除多余的0
        JsonResult result = weixingPayService.telephoneRecharge(mobile, sporderid, price);
        //JsonResult result = new JsonResult(true).setObj(0).setMsg("处理中");
        if (result.getSuccess()) {
            record.setCode(result.getObj().toString());
            record.setCodeMsg(result.getMsg());
            thirdpayRecordService.save(record);
            //注意：这里使用主动请求是否操纵成功 原因:因客户账号已绑定过回调地址
            /*主动请求是否充值成功----start----*/

            //测试成功---上线时放开
            // 上线
            try {
            	Thread.sleep(2000);
			} catch (Exception e) {
			}
            
            JsonResult result1 = weixingPayService.getPayOrderQuery(sporderid);
            //JsonResult result1 = new JsonResult(true).setObj("1").setMsg("充值成功");
            // 第三方返回状态码 1:充值成功 2:充值中 9:充值失败
            if ("1".equals(result1.getObj())) {
                record.setStatus(2); // 充值成功
                record.setCode(result1.getObj().toString());
                record.setCodeMsg(result1.getMsg());
            } else if ("9".equals(result1.getObj())){
                record.setStatus(3); // 充值失败
                record.setCode(result1.getObj().toString());
                record.setCodeMsg(result1.getMsg());
            } else {
                record.setCode(result1.getObj().toString());
                record.setCodeMsg(result1.getMsg());
            }
            thirdpayRecordService.update(record);
            /*主动请求是否充值成功----end----*/
            JSONObject json = JSONObject.fromObject(record);
            jsonResult = new JsonResult(true).setObj(json);
        } else {
            jsonResult = new JsonResult(false).setMsg("话费充值失败");
        }
        return jsonResult;
    }

    @Override
    public JsonResult getPayOrderQuery(String sporderid) {
        return weixingPayService.getPayOrderQuery(sporderid);
    }

    @Override
    public JsonResult payBallBack(String userid, String sporderid, String status, String sign) {
        JsonResult jsonResult = null;
        // 数据校验
        JsonResult checkResult = weixingPayService.checkParamData(userid, sporderid, status, sign);
        if (checkResult.getSuccess()) {
            QueryFilter filter = new QueryFilter(ThirdpayRecord.class);
            filter.addFilter("recordNumber=",sporderid);
            ThirdpayRecord record = thirdpayRecordService.get(filter);
            // 第三方返回状态码 1:充值成功 2:充值中 9:充值失败
            if ("1".equals(status)) {
                record.setStatus(2); // 充值成功
                record.setCode(status);
                record.setCodeMsg("充值成功");
            } else if ("9".equals(status)){
                record.setStatus(3); // 充值失败
                record.setCode(status);
                record.setCodeMsg("充值失败");
            }
            record.setReturnTime(new Date()); // 回调时间
            thirdpayRecordService.update(record);
            jsonResult = new JsonResult(true);
        } else {
            jsonResult = checkResult;
        }
        return jsonResult;
    }

    @Override
    public FrontPage findPayOrderRecord(Map<String, String> paramMap) {
        Page<ThirdpayRecord> page = PageFactory.getPage(paramMap);
        String memberId = "";
        QueryFilter filter = new QueryFilter(ThirdpayRecord.class);
        if (StringUtil.isNull(paramMap.get("memberId"))) {
            memberId = paramMap.get("memberId");
            filter.addFilter("memberId=", memberId);
        }
        List<ThirdpayRecord> records = thirdpayRecordService.find(filter);
        if (records != null && records.size() > 0) {
            return new FrontPage(records, page.getTotal(), page.getPages(), page.getPageSize());
        } else {
            return null;
        }
    }
}
