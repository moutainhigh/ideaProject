/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年4月1日 下午7:26:24
 */
package hry.exchange.account.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppOurAccount;
import hry.account.fund.model.AppTransaction;
import hry.account.remote.RemoteAppAccountService;
import hry.account.remote.RemoteAppOurAccountService;
import hry.account.remote.RemoteAppTransactionService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.exchange.account.service.ExAmineOrderService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.account.service.ExDmColdAccountRecordService;
import hry.exchange.account.service.ExDmHotAccountRecordService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年4月1日 下午7:26:24
 */

@Service("examineOrderService")
public class ExAmineOrderServiceImpl implements ExAmineOrderService {
	private static Logger logger = Logger.getLogger(ExAmineOrderServiceImpl.class);
	@Resource(name = "exDmColdAccountRecordService")
	public ExDmColdAccountRecordService exDmColdAccountRecordService;

	@Resource(name = "exDmHotAccountRecordService")
	public ExDmHotAccountRecordService exDmHotAccountRecordService;

	@Resource(name = "exDmTransactionService")
	public ExDmTransactionService exDmTransactionService;

	@Resource(name = "exDigitalmoneyAccountService")
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Resource
	private MessageProducer messageProducer;
	
	@Override 
	public String pasePutOrder(Long id) {
		ExDmTransaction exDmTransaction = exDmTransactionService.get(id);
		Integer i = exDmTransaction.getStatus();
		if (i.intValue() == 2) {
			return "NO";
		}
		Long accountId=exDmTransaction.getAccountId();
		if(accountId!=null){
			ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(accountId);
			Integer tp = exDmTransaction.getTransactionType();
			//充币
			if (tp == 1) {
				//要发消息
				return "NO";
				
			}
			//提币
			if (tp == 2) {


				

				
				//----发送mq消息----start
				//冷账户增加
				Accountadd accountadd2 = new Accountadd();
				accountadd2.setAccountId(exDigitalmoneyAccount.getId());
				accountadd2.setMoney(exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()).multiply(new BigDecimal(-1)));
				accountadd2.setMonteyType(2);
				accountadd2.setAcccountType(1);
				accountadd2.setRemarks(33);
				accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());
				
				//手续费 -- 冷
				Accountadd accountaddf2 = new Accountadd();
				accountaddf2.setAccountId(exDigitalmoneyAccount.getId());
				accountaddf2.setMoney(exDmTransaction.getFee().multiply(new BigDecimal(-1)));
				accountaddf2.setMonteyType(2);
				accountaddf2.setAcccountType(1);
				accountaddf2.setRemarks(34);
				accountaddf2.setTransactionNum(exDmTransaction.getTransactionNum());
				
				List<Accountadd> list = new ArrayList<Accountadd>();
				list.add(accountadd2);
				list.add(accountaddf2);
			
				//----发送mq消息----end
				
				
				
				// 修改订单
				exDmTransaction.setStatus(2);
				exDmTransaction.setUserId(exDigitalmoneyAccount.getCustomerId());
				exDmTransactionService.update(exDmTransaction);
				RemoteAppOurAccountService remoteAppOurAccountService = (RemoteAppOurAccountService) ContextUtil.getBean("remoteAppOurAccountService");
				//我方提币账户
				AppOurAccount ourAccount=remoteAppOurAccountService.findAppOurAccount(ContextUtil.getWebsite(),exDmTransaction.getCoinCode(), Integer.valueOf("1"));

				remoteAppOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "提币记录", "");
				
				remoteAppOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "提币手续费记录", "fee");

				
				messageProducer.toAccount(JSON.toJSONString(list));
				return "OK";
			} else {
				return "NO";
			}
		}else{
			return "NO";
		}
	}
	@Override
	public List<ExDmTransaction> getlistByapply(Long customerId,String currencyType,String website) {

		Map<String,BigDecimal> map=new HashMap<String,BigDecimal>();
		//提币2审核中1，4所有记录
		QueryFilter withdrawedtfilter=new QueryFilter(ExDmTransaction.class);
		withdrawedtfilter.addFilter("customerId=", customerId);
		withdrawedtfilter.addFilter("status_in", "1,4");
		withdrawedtfilter.addFilter("transactionType_in", "2");
		withdrawedtfilter.addFilter("currencyType=",currencyType);
		withdrawedtfilter.addFilter("website=",website);
		List<ExDmTransaction> widthedtlist=exDmTransactionService.find(withdrawedtfilter);
		return widthedtlist;
	
	}
	
	
	@Override
	public String pasePutOrderToAppAccount(Long id) {
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		RemoteAppTransactionService remoteAppTransactionService = (RemoteAppTransactionService) ContextUtil.getBean("remoteAppTransactionService");
		ExDmTransaction exDmTransaction = exDmTransactionService.get(id);
		Integer i = exDmTransaction.getStatus();
		if (i == 2) {
			return "NO";
		}

		ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.findByOrderId(id);

		Integer tp = exDmTransaction.getTransactionType();
		if (tp == 1) {
			AppAccount appAccount=remoteAppAccountService.getByCustomerIdAndType(exDmTransaction.getCustomerId(), exDmTransaction.getCurrencyType(), exDmTransaction.getWebsite());
		
			remoteAppAccountService.inComeToHotAccount(appAccount.getId(), exDmTransaction.getTransactionMoney(), exDmTransaction.getTransactionNum(),"币转钱",null,null);
			
				// 修改订单
			exDmTransaction.setStatus(2);
			exDmTransaction.setUserId(exDigitalmoneyAccount.getCustomerId());
			exDmTransactionService.update(exDmTransaction);
			
			
			//关键的:把币转成资金要生成一条资金充值业务单子
			AppTransaction appTransaction=new AppTransaction();
			appTransaction.setAccountId(appAccount.getId());
			appTransaction.setCurrencyType(exDmTransaction.getCurrencyType());
			appTransaction.setWebsite(exDmTransaction.getWebsite());
			appTransaction.setCustomerId(exDmTransaction.getCustomerId());
			appTransaction.setRemark("币转钱");
			appTransaction.setStatus(2);
			appTransaction.setTransactionMoney(exDmTransaction.getTransactionMoney());
			appTransaction.setTransactionType(3);
			appTransaction.setTransactionNum(exDmTransaction.getTransactionNum());
			appTransaction.setUserName(exDmTransaction.getCustomerName());
			appTransaction.setUserId(exDmTransaction.getCustomerId());
			appTransaction.setFee(new BigDecimal("0"));
			remoteAppTransactionService.save(appTransaction);

			return "OK";
		}
		if (tp == 2) {
			AppAccount appAccount=remoteAppAccountService.getByCustomerIdAndType(exDmTransaction.getCustomerId(), exDmTransaction.getCurrencyType(), exDmTransaction.getWebsite());
			
			logger.error("手动提币");
			remoteAppAccountService.unfreezeAccountThem(appAccount.getId(), exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()), exDmTransaction.getTransactionNum(),"提币金额",null,null);
			//收费费
			remoteAppAccountService.unfreezeAccountThem(appAccount.getId(), exDmTransaction.getFee(), exDmTransaction.getTransactionNum(),"提币手续费",null,null);
			//修改订单状态
			AppTransaction appTransaction=remoteAppTransactionService.getByTransactionNum(exDmTransaction.getTransactionNum());
			appTransaction.setStatus(2);
			remoteAppTransactionService.update(appTransaction);
			// 修改订单
			exDmTransaction.setStatus(2);
			exDmTransaction.setUserId(exDigitalmoneyAccount.getCustomerId());
			exDmTransactionService.update(exDmTransaction);
			return "OK";
		} else {
			return "NO";
		}

	}

	@Override
	public String paseStopeOrderToAppAccount(Long id) {

		ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.findByOrderId(id);
		ExDmTransaction exDmTransaction = exDmTransactionService.get(id);
		Integer i = exDmTransaction.getStatus();

		if (i != 1) {
			return "NO";
		}
		Integer tp = exDmTransaction.getTransactionType();
		if (tp == 1) {
			exDmTransaction.setStatus(3);
			exDmTransaction.setUserId(exDigitalmoneyAccount.getCustomerId());
			exDmTransactionService.update(exDmTransaction);
			return "OK";
		}
		if (tp == 2) {

			RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
			RemoteAppTransactionService remoteAppTransactionService = (RemoteAppTransactionService) ContextUtil.getBean("remoteAppTransactionService");
			AppAccount appAccount=remoteAppAccountService.getByCustomerIdAndType(exDmTransaction.getCustomerId(), exDmTransaction.getCurrencyType(), exDmTransaction.getWebsite());
			remoteAppAccountService.unfreezeAccountSelf(appAccount.getId(), exDmTransaction.getTransactionMoney(), exDmTransaction.getTransactionNum(), "驳回解冻", null,null);
			
			AppTransaction appTransaction=remoteAppTransactionService.getByTransactionNum(exDmTransaction.getTransactionNum());
			appTransaction.setStatus(3);
			remoteAppTransactionService.update(appTransaction);;
			// 修改订单
			exDmTransaction.setStatus(3);
			exDmTransaction.setUserId(exDigitalmoneyAccount.getCustomerId());
			exDmTransactionService.update(exDmTransaction);

			return "OK";
		} else {
			return "NO";
		}

	}

	@Override
	public String paseRefuseOrder(Long id) {
		return null;
	}

	@Override
	public String rechargeCoin(ExDmTransaction exTxs) {
		logger.error("充币记录保存完成，开始处理热钱包流水记录及修改账户");
		StringBuffer ret = new StringBuffer("{\"success\":\"true\",\"msg\":");
		try {
			Integer i = exTxs.getStatus();
			if (i == 2) {
				ret.append("\"该订单已通过\"");
			}

			ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.getByCustomerIdAndType(
					exTxs.getCustomerId(), exTxs.getCoinCode(), exTxs.getCurrencyType(), exTxs.getWebsite());
			if(exDigitalmoneyAccount==null){
				logger.error("未查询到钱包账户");
				return null;
			}

			BigDecimal hotMoney = exDigitalmoneyAccount.getHotMoney();
			BigDecimal transactionMoney = exTxs.getTransactionMoney();
			BigDecimal k = hotMoney.add(transactionMoney);
			exDigitalmoneyAccount.setHotMoney(k);
			// 修改可用余额
			exDigitalmoneyAccountService.update(exDigitalmoneyAccount);

			// 保存可用余额流水
			ExDmHotAccountRecord exDmHotAccountRecord = new ExDmHotAccountRecord();
			exDmHotAccountRecord.setAccountId(exDigitalmoneyAccount.getId());
			exDmHotAccountRecord.setCustomerId(exTxs.getCustomerId());
			exDmHotAccountRecord.setUserName(exDigitalmoneyAccount.getUserName());
			exDmHotAccountRecord.setRecordType(1);
			exDmHotAccountRecord.setTransactionMoney(exTxs.getTransactionMoney());
			exDmHotAccountRecord.setStatus(5);
			exDmHotAccountRecord.setRemark("可用余额流水已记录成功 ");
			exDmHotAccountRecord.setCurrencyType(exTxs.getCurrencyType());
			exDmHotAccountRecord.setCoinCode(exTxs.getCoinCode());
			exDmHotAccountRecord.setWebsite(exTxs.getWebsite());
			exDmHotAccountRecord.setTransactionNum(exTxs.getTransactionNum());
			exDmHotAccountRecord.setSaasId(RpcContext.getContext().getAttachment("saasId"));
			exDmHotAccountRecordService.save(exDmHotAccountRecord);

			// 修改订单
			exTxs.setStatus(2);
			exTxs.setUserId(exDigitalmoneyAccount.getCustomerId());
			exDmTransactionService.update(exTxs);
			ret.append("\"记账成功\"");
		} catch (Exception e) {
			e.printStackTrace();
			ret.append("\"异常 :" + e.getMessage() + "  \"");
		} finally {
			ret.append("}");
		}
		return ret.toString();
	}



}
