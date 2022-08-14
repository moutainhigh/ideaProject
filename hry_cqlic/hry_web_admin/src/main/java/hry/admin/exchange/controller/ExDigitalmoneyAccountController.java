/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:56:33 
 */
package hry.admin.exchange.controller;


import com.alibaba.fastjson.JSON;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.lock.controller.LockRedisRunnable;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.constant.StringConstant;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.core.thread.ThreadPool;
import hry.trade.redis.model.Accountadd;
import hry.util.CoinInterfaceUtil;
import hry.util.GoogleAuthenticatorUtil;
import hry.util.QueryFilter;
import hry.util.file.FileUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
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
import java.math.BigDecimal;
import java.util.*;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:56:33 
 */
@Controller
@RequestMapping("/exchange/exdigitalmoneyaccount")
public class ExDigitalmoneyAccountController extends BaseController<ExDigitalmoneyAccount, Long> {
	
	@Resource(name = "exDigitalmoneyAccountService")
	@Override
	public void setService(BaseService<ExDigitalmoneyAccount, Long> service) {
		super.service = service;
	}

	@Resource
	private ExDmTransactionService exDmTransactionService;

	@Resource
	private MessageProducer messageProducer;
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExDigitalmoneyAccount exDigitalmoneyAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exdigitalmoneyaccountsee");
		mav.addObject("model", exDigitalmoneyAccount);
		return mav;
	}
	

	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class, request);
		PageResult findPageBySql = ((ExDigitalmoneyAccountService) service).findPageBySql(filter);
		return findPageBySql;
	}


	@RequestMapping("/refreshUserCoin")
	@MethodName(name = "单个用户刷币")
	@ResponseBody
	public JsonResult refreshUserCoin(HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			String id = request.getParameter("id");
			ExDigitalmoneyAccount exDigitalmoneyAccount = service.get(Long.valueOf(id));
			// 币种code
			String coinCode = request.getParameter("coinCode").toString();
			// 数量
			String count = request.getParameter("count").toString();

			String s = CoinInterfaceUtil.refreshUserCoin(coinCode, exDigitalmoneyAccount.getAccountNum().toLowerCase(), count);

			result.setCode(StringConstant.SUCCESS);
			result.setMsg("刷新成功，请等待到账！");
			result.setObj(s);
			result.setSuccess(true);

		} catch (Exception e) {
			result.setCode(StringConstant.FAIL);
			result.setMsg("ExDigitalmoneyAccountController refreshUserCoin Err:" + e.getMessage());
			result.setSuccess(false);
		}

		return result;

	}

    @RequestMapping("/refreshETC")
    @MethodName(name = "根据hash和type刷新以太坊，以太零等币")
    @ResponseBody
    public JsonResult refreshETC(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {

            String hash = request.getParameter("hash");
            String type = request.getParameter("type");
            String s = CoinInterfaceUtil.refreshETC(hash, type);

            result.setCode(StringConstant.SUCCESS);
            result.setMsg("刷新成功，请等待到账！");

            result.setSuccess(true);

        } catch (Exception e) {
            result.setCode(StringConstant.FAIL);
            result.setMsg("ExDigitalmoneyAccountController refreshUserCoin Err:" + e.getMessage());
            result.setSuccess(false);
        }

        return result;

    }


	@RequestMapping(value ="/recharge",method = RequestMethod.POST)
	@MethodName(name = "手动充币")
	@ResponseBody
	@CommonLog(name = "手动充币")
	public JsonResult recharge(HttpServletRequest request) {
		String id = request.getParameter("id");
		String number = request.getParameter("number");
		JsonResult jsonResult = new JsonResult();
		String googleCode = request.getParameter("google_code");

		if(googleCode ==null || "".equals(googleCode)){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("谷歌验证码不能为空");
			return jsonResult;
		}

		long t = System.currentTimeMillis();
		GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
		long code = Long.parseLong(googleCode);
		String googleKey = FileUtil.getGoogleKey();
		boolean r = ga.check_code( googleKey,code, t);
		if(!r){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("谷歌验证码错误");
			return jsonResult;
		}

		if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(number)) {
			if (new BigDecimal(number).compareTo(new BigDecimal("0")) < 0) {
				jsonResult.setMsg("充币数量必须大于0");
				jsonResult.setSuccess(false);
				return jsonResult;
			}
			ExDigitalmoneyAccount account = service.get(Long.valueOf(id));
			//创建订单
			ExDmTransaction exDmTransaction = new ExDmTransaction();
			exDmTransaction.setAccountId(account.getId());
			exDmTransaction.setCoinCode(account.getCoinCode());
			exDmTransaction.setCreated(new Date());
			exDmTransaction.setCurrencyType(account.getCurrencyType());
			exDmTransaction.setWebsite(account.getWebsite());
			exDmTransaction.setCustomerId(account.getCustomerId());
			exDmTransaction.setCustomerName(account.getUserName());
			exDmTransaction.setFee(new BigDecimal(0));
			exDmTransaction.setTransactionMoney(new BigDecimal(number));
			exDmTransaction.setStatus(1);
			exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
			// 充币
			exDmTransaction.setTransactionType(1);
			exDmTransaction.setUserId(ContextUtil.getCurrentUserId());
			exDmTransaction.setStatus(Integer.valueOf(2));// 充值成功
			exDmTransaction.setRemark("手动充"+exDmTransaction.getTransactionMoney()+"个币");
			exDmTransaction.setOptType(4);
			exDmTransactionService.save(exDmTransaction);
			jsonResult.setMsg("充值成功");
			jsonResult.setSuccess(true);

			// 发送mq通知缓存
			Accountadd accountadd = new Accountadd();
			accountadd.setAccountId(exDmTransaction.getAccountId());
			accountadd.setMoney(exDmTransaction.getTransactionMoney());
			accountadd.setMonteyType(1);
			accountadd.setAcccountType(1);
			accountadd.setRemarks(23);
			accountadd.setTransactionNum(exDmTransaction.getTransactionNum());
			List<Accountadd> list = new ArrayList<Accountadd>();
			list.add(accountadd);
			messageProducer.toAccount(JSON.toJSONString(list));

			// 调用锁仓管理操作---此处测试专用,测完删除
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("customerId", account.getCustomerId());
			paramMap.put("accountId", account.getId());
			paramMap.put("coinCode", account.getCoinCode());
			paramMap.put("transactionNum", exDmTransaction.getTransactionNum());
			ThreadPool.exe(new LockRedisRunnable(paramMap));
			// -----------------------end------------------------------

		} else {
			jsonResult.setMsg("参数不能为空");
		}

		return jsonResult;
	}


    @RequestMapping(value ="/getcoin",method = RequestMethod.POST)
    @MethodName(name = "手动提币")
    @ResponseBody
	@CommonLog(name = "手动提币")
    public JsonResult getcoin(HttpServletRequest request) {
        String id = request.getParameter("id");
        String number = request.getParameter("number");
        JsonResult jsonResult = new JsonResult();
        if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(number)) {
            ExDigitalmoneyAccount account = service.get(Long.valueOf(id));
            if(account.getHotMoney().compareTo(BigDecimal.valueOf(0))<1){
                jsonResult.setMsg("账户可用币小于等于0");
                return jsonResult;
            }
            if(account.getColdMoney().compareTo(BigDecimal.valueOf(0))<0){
                jsonResult.setMsg("账户冻结币小于0");
                return jsonResult;
            }
            if(account.getHotMoney().compareTo(new BigDecimal(number))<0) {
				jsonResult.setMsg("账户可用币小于提币数量");
				return jsonResult;
			}
            ExDmTransaction exDmTransaction = new ExDmTransaction();
            exDmTransaction.setAccountId(account.getId());
            exDmTransaction.setCoinCode(account.getCoinCode());
            exDmTransaction.setCreated(new Date());
            exDmTransaction.setCurrencyType(account.getCurrencyType());
            exDmTransaction.setWebsite(account.getWebsite());
            exDmTransaction.setCustomerId(account.getCustomerId());
            exDmTransaction.setCustomerName(account.getUserName());
            exDmTransaction.setFee(new BigDecimal(0));
            exDmTransaction.setTransactionMoney(new BigDecimal(number));
            exDmTransaction.setStatus(1);
            exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
            // 提币
            exDmTransaction.setTransactionType(2);
            exDmTransaction.setRemark("手动提"+exDmTransaction.getTransactionMoney()+"个币");
            exDmTransaction.setOptType(5);
            exDmTransaction.setUserId(ContextUtil.getCurrentUserId());
            exDmTransaction.setStatus(Integer.valueOf(2));// 提币成功
            exDmTransactionService.save(exDmTransaction);
            jsonResult.setMsg("提币成功");
            jsonResult.setSuccess(true);

            // 发送mq通知缓存
            Accountadd accountadd = new Accountadd();
            accountadd.setAccountId(exDmTransaction.getAccountId());
            accountadd.setMoney(exDmTransaction.getTransactionMoney().multiply(new BigDecimal(-1)));
            accountadd.setMonteyType(1);
            accountadd.setAcccountType(1);
            accountadd.setRemarks(25);
            accountadd.setTransactionNum(exDmTransaction.getTransactionNum());
            List<Accountadd> list = new ArrayList<Accountadd>();
            list.add(accountadd);
            messageProducer.toAccount(JSON.toJSONString(list));

        } else {
            jsonResult.setMsg("参数不能为空");
        }

        return jsonResult;
    }

    @MethodName(name = "手动锁仓")
	@CommonLog(name = "手动锁仓")
	@RequestMapping(value = "/manualLock", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult manualLock(HttpServletRequest request) {
		String coinCode = request.getParameter("coinCode"); // 币种类型
		String customerId = request.getParameter("customerId"); // 用户id
		String coldNum = request.getParameter("coldNum"); // 锁仓数量
		String lockCycle = request.getParameter("lockCycle"); // 锁仓期
		String releaseMethod = request.getParameter("releaseMethod"); // 释放方式
		String releaseMethodVal = request.getParameter("releaseMethodVal"); // 释放方式值
		String accountId = request.getParameter("accountId"); // 资金账户id

		// 调用锁仓管理操作
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("coinCode", coinCode);
		paramMap.put("customerId", customerId);
		paramMap.put("coldNum", coldNum);
		paramMap.put("lockCycle", lockCycle);
		paramMap.put("releaseMethod", releaseMethod);
		paramMap.put("releaseMethodVal", releaseMethodVal);
		paramMap.put("accountId", accountId);
		paramMap.put("optType", "manualLock");
		JsonResult jsonResult = exDmTransactionService.manualLockHandler(paramMap);
		return jsonResult;
	}
	
}
