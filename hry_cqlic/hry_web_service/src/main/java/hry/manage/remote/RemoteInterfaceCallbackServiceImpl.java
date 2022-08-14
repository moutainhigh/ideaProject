package hry.manage.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.change.remote.account.RemoteExAmineOrderService;
import hry.change.remote.dmtransaction.RemoteExDmTransactionService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.manage.remote.model.LmcTransfer;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import hry.util.date.DateUtil;
import hry.util.http.InterfaceState;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.md5.Md5Encrypt;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 第三方回调接口
 * <p> TODO</p>
 * @author:         Shangxl 
 * @Date :          2017年7月31日 上午10:31:41
 */
public class RemoteInterfaceCallbackServiceImpl implements RemoteInterfaceCallbackService{
	private static Logger logger = Logger.getLogger(RemoteInterfaceCallbackServiceImpl.class);
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	private ExDmTransactionService exDmTransactionService;
	@Resource
	private MessageProducer messageProducer;
	/**
	 * lmc转账回调（充币、提币）
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param transfer
	 * @param:    @return
	 * @return: String 
	 * @Date :          2017年7月31日 上午10:36:36   
	 * @throws:
	 */
	@Override
	public String lmcTransferCallBack(LmcTransfer transfer) throws RuntimeException{
		logger.error("调用充币回调接口");
		logger.error("请求参数："+JSON.toJSON(transfer));
		JSONObject result = new JSONObject();
		//秘钥
		String Secret = PropertiesUtils.APP.getProperty("app.LMC_Secret");
		//充值回调
		if(transfer!=null){
			if("I".equals(transfer.getTransfer_type())){
				String[] s = { transfer.getTransfer_id(), transfer.getStatus(), transfer.getTxid(), transfer.getRequest_time(),
						transfer.getReason(), transfer.getSymbol(), transfer.getFrom(), transfer.getTo(), transfer.getCoins(),
						transfer.getFee(), transfer.getTransfer_type(),transfer.getType(),transfer.getAccount_id(),Secret };
				// 判断参数有效
				if(StringUtil.containEmpty(s)){
					result.put("code", InterfaceState.INVALID_PARAMETER);
					result.put("data", "");
					result.put("msg", "parameter invalid");
					return result.toJSONString();
				}
				logger.error("参数auth_code=" + transfer.getAuth_code());
				// 数组字典排序
				String auth_code = StringUtil.stringSort(s, "_");
				logger.error("解析加密前：auth_code=" + auth_code);
				auth_code = Md5Encrypt.md5(auth_code);
				logger.error("解析加密后：auth_code=" + auth_code);
				// 请求参数无效、加密解析错误
				if (!auth_code.equals(transfer.getAuth_code())) {
					result.put("code", InterfaceState.CHECK_FAILED);
					result.put("data", "false");
					result.put("msg", "app_key invalid");
					return result.toJSONString();
				}
				logger.error("充币回调成功，处理平台数据");
				// 处理平台币账户资金
				String[] arr = this.updateOrder(transfer);
				// 平台处理完成之后返回给第三方
				result.put("code", arr[0]);
				result.put("data", arr[1]);
				result.put("msg", arr[2]);
				return result.toJSONString();
			}
			//提币回调
			else if("O".equals(transfer.getTransfer_type())){
				result.put("code", InterfaceState.SUCCESS);
				result.put("data", "true");
				result.put("msg", "success");
				return result.toJSONString();
			}
		}
		//I、O之外类型为无效请求
		result.put("code", InterfaceState.INVALID_PARAMETER);
		result.put("data", "");
		result.put("msg", "parameter invalid");
		return result.toJSONString();
	}
	
	
	/**
	 * * <p> 记录用户充币订单 并且记账</p>
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param transfer
	 * @param:    @param coinType
	 * @param:    @return
	 * @Date :          2017年8月1日 下午5:51:56   
	 * @throws:
	 */
	private String[] updateOrder(LmcTransfer transfer) throws RuntimeException{
		String[] result=new String[3];
		String address=transfer.getTo();//公钥

		RemoteExAmineOrderService remoteExAmineOrderService=(RemoteExAmineOrderService)ContextUtil.getBean("remoteExAmineOrderService");
		RemoteExDmTransactionService remoteExDmTxService = (RemoteExDmTransactionService) ContextUtil.getBean("remoteExDmTransactionService");
		
		QueryFilter filter=new QueryFilter(ExDigitalmoneyAccount.class);
//		filter.addFilter("userName=", transfer.getAccount_id());
		filter.addFilter("publicKey=", transfer.getTo());
		ExDigitalmoneyAccount exDigitalmoneyAccount=exDigitalmoneyAccountService.get(filter);
		
		ExDmTransaction exDmTransaction = new ExDmTransaction();
		exDmTransaction.setAccountId(exDigitalmoneyAccount.getId());
		exDmTransaction.setCoinCode(transfer.getSymbol());
		exDmTransaction.setCreated(new Date());
		exDmTransaction.setCurrencyType(exDigitalmoneyAccount.getCurrencyType());
		exDmTransaction.setCustomerId(exDigitalmoneyAccount.getCustomerId());
		exDmTransaction.setCustomerName(exDigitalmoneyAccount.getUserName());
		exDmTransaction.setTime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setTimereceived(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setInAddress(address);
		exDmTransaction.setConfirmations("999");//确认节点数
		exDmTransaction.setBlocktime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setFee(new BigDecimal(transfer.getFee()));
		exDmTransaction.setTransactionMoney(new BigDecimal(transfer.getCoins()));
		exDmTransaction.setStatus(2);
		exDmTransaction.setOrderNo(transfer.getTxid());
		exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
		// 充币
		exDmTransaction.setTransactionType(1);
		exDmTransaction.setUserId(exDigitalmoneyAccount.getCustomerId());
		exDmTransaction.setWebsite(exDigitalmoneyAccount.getWebsite());
		exDmTransaction.setOptType(1);
		ExDmTransaction txs=remoteExDmTxService.getTransaction(exDmTransaction.getCustomerName(), exDmTransaction.getOrderNo());
		if (null == txs) {
			logger.error("保存充币记录");
			// 保存交易订单
			
//			remoteExDmTxService.saveTransaction(exDmTransaction);
//			txs = remoteExDmTxService.getTransaction(exDmTransaction.getCustomerName(),exDmTransaction.getOrderNo());
//			// 修改资产信息
//			remoteExAmineOrderService.chargeAccount(txs);
			
			exDmTransactionService.save(exDmTransaction);
			
			Accountadd accountadd = new Accountadd();
			accountadd.setAccountId(exDigitalmoneyAccount.getId());
			accountadd.setMoney(exDmTransaction.getTransactionMoney());
			accountadd.setMonteyType(1);//热账户
			accountadd.setAcccountType(1);//币账户 
			accountadd.setRemarks(31);
			accountadd.setTransactionNum(accountadd.getTransactionNum());
			List<Accountadd> list = new ArrayList<Accountadd>();
			list.add(accountadd);
			messageProducer.toAccount(JSON.toJSONString(list));
			
			
			txs = null;
			result[0] = InterfaceState.SUCCESS;
			result[1] = "true";
			result[2] = "success";
		} else {
			logger.error("此充值订单已处理：" + exDmTransaction.getOrderNo());
			result[0] = InterfaceState.TX_ALREADY_EXISTS;
			result[1] = "false";
			result[2] = "tx already exists";
		}
		return result;
	}
}
