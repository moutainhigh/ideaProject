/**
 * Copyright:
 *
 * @author: tianpengyu
 * @version: V1.0
 * @Date: 2018-07-06 14:36:50
 */
package hry.admin.exchange.controller;


import com.alibaba.fastjson.JSON;
import hry.admin.customer.model.AppBankCard;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppBankCardService;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.exchange.model.AppTransaction;
import hry.admin.exchange.service.AppTransactionService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.trade.redis.model.Accountadd;
import hry.util.GoogleAuthenticatorUtil;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright:   互融云
 *
 * @author: tianpengyu
 * @version: V1.0
 * @Date: 2018-07-06 14:36:50
 */
@Controller
@RequestMapping("/exchange/apptransaction")
public class AppTransactionController extends BaseController<AppTransaction, Long> {

    @Resource(name = "appTransactionService")
    @Override
    public void setService(BaseService<AppTransaction, Long> service) {
        super.service = service;
    }

    @Resource
    private AppBankCardService appBankCardService;

    @Resource
    private AppPersonInfoService appPersonInfoService;

    @Resource
    private AppCustomerService appCustomerService;

    @Resource
    private MessageProducer messageProducer;

    @RequestMapping(value = "/see/{id}")
    public ModelAndView see(@PathVariable Long id) {
        AppTransaction appTransaction = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/apptransactionsee");
        mav.addObject("model", appTransaction);
        return mav;
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, AppTransaction appTransaction) {
        return super.save(appTransaction);
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee(@PathVariable Long id) {
        AppTransaction appTransaction = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/apptransactionmodify");
        mav.addObject("model", appTransaction);
        return mav;
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public JsonResult modify(HttpServletRequest request, AppTransaction appTransaction) {
        return super.update(appTransaction);
    }


    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove(String ids) {
        return super.deleteBatch(ids);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(AppTransaction.class, request);
        filter.setOrderby("created desc");
        PageResult findPage = ((AppTransactionService) service).findPageBySql(filter);
        @SuppressWarnings("unchecked")
        List<AppTransaction> rows = findPage.getRows();
        if (rows != null && rows.size() > 0) {
            for (AppTransaction at : rows) {
                QueryFilter queryFilter = new QueryFilter(AppBankCard.class);
                queryFilter.addFilter("cardNumber=", at.getCustromerAccountNumber());
                AppBankCard appBankCard = appBankCardService.get(queryFilter);
                if (appBankCard != null) {
                    at.setBankName(appBankCard.getCardBank());
                    at.setBankProvince(appBankCard.getBankProvince());
                    at.setBankAddress(appBankCard.getBankAddress());
                    at.setSubBank(appBankCard.getSubBank());
                    at.setSubBankNum(appBankCard.getSubBankNum());
                }
                AppPersonInfo appPersonInfo = appPersonInfoService.get(
                        new QueryFilter(AppPersonInfo.class).addFilter("customerId=", at.getCustomerId()));
                if (appPersonInfo != null) {
                    at.setEmail(appPersonInfo.getEmail());
                    at.setPhone(appPersonInfo.getMobilePhone());
                }
            }
        }

        return findPage;
    }


    @MethodName(name = "确认充值")
    @RequestMapping("/confirm")
    @ResponseBody
    @MyRequiresPermissions
    @CommonLog(name = "充值审核-通过")
    public JsonResult confirm(HttpServletRequest request) {

        String googleCode = request.getParameter("google_code");

        JsonResult jsonResult = new JsonResult();
        if ("".equals(googleCode)) {
            return jsonResult.setSuccess(false).setMsg("谷歌验证码不能为空");
        }
        long t = System.currentTimeMillis();
        GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
        long code = Long.parseLong(googleCode);
        //String googleKey = FileUtil.getGoogleKey();
        String googleKey = "D2OBC4BNVENC2YET";
        boolean r = ga.check_code(googleKey, code, t);
        if (!r) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("谷歌验证码错误");
            return jsonResult;
        }

        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            {
                AppTransaction appTransaction = service.get(Long.valueOf(id));

                if (appTransaction != null && appTransaction.getStatus() != null && appTransaction.getStatus().intValue() < 2) {
                    //不等于已完成订单
                    appTransaction.setStatus(Integer.valueOf(2));

                    Accountadd accountadd = new Accountadd();
                    accountadd.setAccountId(appTransaction.getAccountId());
                    accountadd.setMoney(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()));
                    accountadd.setMonteyType(1);
                    accountadd.setAcccountType(0);
                    accountadd.setRemarks(21);
                    accountadd.setTransactionNum(appTransaction.getTransactionNum());
                    List<Accountadd> list = new ArrayList<Accountadd>();
                    list.add(accountadd);
                    messageProducer.toAccount(JSON.toJSONString(list));

                    appTransaction.setStatus(Integer.valueOf(2));
                    super.update(appTransaction);

                    jsonResult.setMsg("确认成功");
                    jsonResult.setSuccess(true);
                }
            }

        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("请选择确认数据");
        }
        return jsonResult;
    }

    @MethodName(name = "充值,提现无效处理")
    @RequestMapping("/invalid")
    @ResponseBody
    @MyRequiresPermissions(value = {"/winvalid", "/dinvalid"})
    @CommonLog(name = "充值审核-驳回")
    public synchronized JsonResult invalid(HttpServletRequest request) {
        String id = request.getParameter("id");
        String reason = request.getParameter("reason");
        JsonResult jsonResult = new JsonResult();
        if (!StringUtils.isEmpty(id)) {
            AppTransaction appTransaction = service.get(Long.valueOf(id));
            AppPersonInfo personInfo = appPersonInfoService.getByCustomerId(appTransaction.getCustomerId());
            AppCustomer appCustomer = appCustomerService.get(appTransaction.getCustomerId());

            if (appTransaction.getStatus() == 2 || appTransaction.getStatus() == 3) {
                jsonResult.setSuccess(true);
                jsonResult.setMsg("已经处理过了");
                return jsonResult;
            }
            appTransaction.setStatus(3);
            appTransaction.setRejectionReason(reason);
            //线上提现，线下提现
            if (appTransaction.getTransactionType() == 2 || appTransaction.getTransactionType() == 4) {
                //String[] arr = appAccountService.unfreezeAccountThem(appTransaction.getAccountId(), appTransaction.getTransactionMoney(), appTransaction.getTransactionNum()+"-reject", "人民币提现驳回 冷账户减少金额",null,null);

                boolean flag = withdrawReject(appTransaction);

                if (flag) {

                    AppPersonInfo appPersonInfo = appPersonInfoService.getByCustomerId(appCustomer.getId());
                    if (appPersonInfo != null && !StringUtils.isEmpty(appPersonInfo.getMobilePhone()) && !StringUtils.isEmpty(appPersonInfo.getCountry())) {
                        //发送提现驳回短信通知(提现驳回)
                        SmsParam smsParam = new SmsParam();
                        smsParam.setHryMobilephone(appPersonInfo.getCountry() + appPersonInfo.getMobilePhone());
                        smsParam.setHrySmstype("sms_rmbwithdraw_invalid");
                        smsParam.setHryCode("RMB");
                        SmsSendUtil.sendSmsCode(smsParam);
                    }

                    jsonResult.setSuccess(true);
                    jsonResult.setMsg("处理成功");
                } else {
                    jsonResult.setSuccess(true);
                    jsonResult.setMsg("解冻金额失败");
                }
            } else {
                AppPersonInfo appPersonInfo = appPersonInfoService.getByCustomerId(appCustomer.getId());
                if (appPersonInfo != null && !StringUtils.isEmpty(appPersonInfo.getMobilePhone()) && !StringUtils.isEmpty(appPersonInfo.getCountry())) {
                    //发送提现驳回短信通知(提现驳回)
                    SmsParam smsParam = new SmsParam();
                    smsParam.setHryMobilephone(appPersonInfo.getCountry() + appPersonInfo.getMobilePhone());
                    smsParam.setHrySmstype("sms_rmbwithdraw_invalid");
                    smsParam.setHryCode("RMB");
                    SmsSendUtil.sendSmsCode(smsParam);
                }
                service.update(appTransaction);
                jsonResult.setSuccess(true);
                jsonResult.setMsg("处理成功");
            }

        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("请选择数据");
        }
        return jsonResult;
    }

    public boolean withdrawReject(AppTransaction appTransaction) {
        try {
            Accountadd accountadd = new Accountadd();
            accountadd.setAccountId(appTransaction.getAccountId());
            accountadd.setMoney(appTransaction.getTransactionMoney().multiply(new BigDecimal(-1)));
            accountadd.setMonteyType(2);
            accountadd.setAcccountType(0);
            accountadd.setRemarks(38);
            accountadd.setTransactionNum(appTransaction.getTransactionNum());

            Accountadd accountadd1 = new Accountadd();
            accountadd1.setAccountId(appTransaction.getAccountId());
            accountadd1.setMoney(appTransaction.getTransactionMoney());
            accountadd1.setMonteyType(1);
            accountadd1.setAcccountType(0);
            accountadd1.setRemarks(38);
            accountadd1.setTransactionNum(appTransaction.getTransactionNum());
            List<Accountadd> list = new ArrayList<Accountadd>();
            list.add(accountadd);
            list.add(accountadd1);
            messageProducer.toAccount(JSON.toJSONString(list));

            super.update(appTransaction);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @MethodName(name = "撤销")
    @RequestMapping("/undo")
    @ResponseBody
    public JsonResult undo(HttpServletRequest request) {

        String id = request.getParameter("id");
        BaseManageUser user = ContextUtil.getCurrentUser();
        JsonResult jsonResult = new JsonResult();
        if (!StringUtils.isEmpty(id)) {

            boolean confirm = ((AppTransactionService) service).undo(Long.valueOf(id), user.getUsername());
            if (confirm) {
                jsonResult.setSuccess(true);
                jsonResult.setMsg("撤销成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("撤销失败");
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("请选择撤销数据");
        }
        return jsonResult;
    }
}
