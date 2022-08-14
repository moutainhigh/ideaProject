/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午5:38:24
 */
package hry.klg.transfer.controller;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.bus.BusRequestUtil;
import hry.bus.model.BusUpdateCustomerTO;
import hry.core.annotation.NoLogin;
import hry.core.annotation.base.MethodName;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.core.thread.ThreadPool;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExApiApply;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExApiApplyService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.purse.CoinInterfaceUtil;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.manage.remote.MsgTypeEnum;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.sms.sdk.controller.LockRedisRunnable;
import hry.sms.sdk.service.SdkService;
import hry.sms.send.model.AppSmsSend;
import hry.sms.send.service.AppSmsSendService;
import hry.trade.model.AccountRemarkEnum;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.UUIDUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import hry.util.sys.ContextUtil;
import hry.util.urlencode.URLEncodeUtils;
import hry.web.message.service.AppMessageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;


/**
 * <p>
 * TODO
 * </p>
 *
 */
@Controller
@RequestMapping("/transfer")
public class KlgTransferAccountController {
	private static Logger logger = Logger.getLogger(KlgTransferAccountController.class);
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	private AppPersonInfoService appPersonInfoService;

	@Resource
	private AppCustomerService appCustomerService;

	@Resource
	private AppMessageService appMessageService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

		/**
		 * 防止XSS攻击，并且带去左右空格功能
		 */
		binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
	}
	@MethodName(name = "返回充币地址")
	@RequestMapping(value = "/getCoinAddressByUserName")
	@NoLogin
	@ResponseBody
	public RemoteResult getCoinAddressByUserName(HttpServletRequest request) {
		System.out.println("KLG====返回充币地址");
		RemoteResult remoteResult=new RemoteResult();
//		String customerId=request.getParameter("customerId");//用户ID
		String busNumber=request.getParameter("busNumber");//busNumber 对接总线俩系统唯一标识
        if(busNumber==null||"".equals(busNumber)){
            return remoteResult.setSuccess(false).setMsg("busNumber is null");// !
        }
		String coinCode=request.getParameter("coinCode");//币种
        AppCustomer appCustomer= appCustomerService.get(new QueryFilter(AppCustomer.class).addFilter("busNumber=",busNumber));
        if(appCustomer==null){
            return remoteResult.setSuccess(false).setMsg("busNumber is null");// !
        }
		QueryFilter queryFilter=new QueryFilter(ExDigitalmoneyAccount.class);
		queryFilter.addFilter("coinCode=",coinCode);
		queryFilter.addFilter("customerId=",appCustomer.getId());
		ExDigitalmoneyAccount exDigitalmoneyAccount=exDigitalmoneyAccountService.get(queryFilter);
		if(exDigitalmoneyAccount==null){
			return remoteResult.setSuccess(false).setMsg("accountId is null").setCode("404");// !
		}
		if (!StringUtils.isEmpty(exDigitalmoneyAccount.getPublicKey())) {
			return remoteResult.setSuccess(true).setMsg("success").setObj(exDigitalmoneyAccount.getPublicKey());// !
		}
		try {
			String ss = CoinInterfaceUtil.create(exDigitalmoneyAccount.getUserName(), exDigitalmoneyAccount.getAccountNum().toLowerCase(), exDigitalmoneyAccount.getCoinCode());
			JSONObject parseObject = null;
			if (!StringUtils.isEmpty(ss)) {
				parseObject = JSONObject.parseObject(ss);
			}
			String address = "";
			if (parseObject != null) {
				address = parseObject.get("address").toString();
			}

			if ("true".equals(PropertiesUtils.APP.getProperty("app.blockbus"))) {
				address = "blockbus" + UUIDUtil.getUUID().substring(0,7);
			}

			if (!StringUtils.isEmpty(address)) {
				//-------对接区块链总线-----------开始
				String busCustomerNumber = "";
				if ("true".equals(PropertiesUtils.APP.getProperty("app.blockbus"))) {
//					AppCustomer appCustomer = appCustomerService.get(exDigitalmoneyAccount.getCustomerId());
					BusRequestUtil.putCoinAddress(appCustomer.getBusNumber(), exDigitalmoneyAccount.getCoinCode(), address);
				}
				//-------对接区块链总线-----------结束

				remoteResult.setSuccess(true);
				remoteResult.setObj(address);
				remoteResult.setMsg("success");
				exDigitalmoneyAccount.setPublicKey(address);
				exDigitalmoneyAccountService.update(exDigitalmoneyAccount);
				logger.info("手机号为：" + exDigitalmoneyAccount.getUserName() + ",币地址：" + address);
			} else {
				remoteResult.setMsg("createerror");// 生成失败
				logger.error("开通" + exDigitalmoneyAccount.getCoinCode() + "钱包出错");
			}
		} catch (Exception e) {
			logger.error("远程调用开通钱包失败");
			e.printStackTrace();
		}
		  return remoteResult;
	}
}
