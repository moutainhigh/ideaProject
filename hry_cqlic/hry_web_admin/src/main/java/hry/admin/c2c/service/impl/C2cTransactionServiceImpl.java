/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:46 
 */
package hry.admin.c2c.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import hry.admin.c2c.dao.C2cTransactionDao;
import hry.admin.c2c.model.AppBusinessman;
import hry.admin.c2c.model.AppBusinessmanBank;
import hry.admin.c2c.model.C2cTransaction;
import hry.admin.c2c.service.AppBusinessmanBankService;
import hry.admin.c2c.service.AppBusinessmanService;
import hry.admin.c2c.service.C2cTransactionService;
import hry.admin.customer.model.AppBankCard;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.service.AppBankCardService;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.lock.controller.LockRedisRunnable;
import hry.admin.mq.producer.service.MessageProducer;
import hry.admin.web.service.AppMessageService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.trade.redis.model.Accountadd;
import hry.util.MsgTypeEnum;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> C2cTransactionService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:34:46  
 */
@Service("c2cTransactionService")
public class C2cTransactionServiceImpl extends BaseServiceImpl<C2cTransaction, Long> implements C2cTransactionService{

	private static Object lock = new Object();
	
	@Resource(name="c2cTransactionDao")
	@Override
	public void setDao(BaseDao<C2cTransaction, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Resource
	private ExDmTransactionService exDmTransactionService;

	@Resource
	private MessageProducer messageProducer;

	@Resource
	private AppBusinessmanBankService appBusinessmanBankService;

	@Resource
	private AppBusinessmanService appBusinessmanService;

	@Resource
	private AppBankCardService appBankCardService;

	@Resource
	private AppMessageService appMessageService;

	@Resource
	private AppCustomerService appCustomerService;

	@Override
	public PageResult findPageBySql(QueryFilter filter) {


		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<C2cTransaction> page = PageFactory.getPage(filter);


		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------

		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");

		String coinCode = (String) filter.getRequest().getParameter("coinCode");
		String randomNum = (String) filter.getRequest().getParameter("randomNum");

		String businessmanId=filter.getRequest().getParameter("businessmanId");
		String transactionType = filter.getRequest().getParameter("transactionType");
		String status = filter.getRequest().getParameter("status");
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", "%"+coinCode+"%");
		}
		if(!StringUtils.isEmpty(randomNum)){
			map.put("randomNum", "%"+randomNum+"%");
		}
		if(!StringUtils.isEmpty(businessmanId)){
			map.put("businessmanId", businessmanId);
		}
		if(!StringUtils.isEmpty(transactionType)){
			map.put("transactionType", transactionType);
		}
		if(!StringUtils.isEmpty(status)){
			map.put("status", status);
		}

		((C2cTransactionDao)dao).findPageBySql(map);

		//----------------------分页查询底部外壳------------------------------

		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}




	@Override
	public JsonResult confirm(Long id) {
		JsonResult jsonResult = new JsonResult();
		synchronized (lock) {
			C2cTransaction c2cTransaction = super.get(id);
			if (c2cTransaction.getStatus().intValue() == 3) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("交易已取消");
				return jsonResult;
			}
			if (c2cTransaction.getStatus().intValue() == 2) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("交易已成功");
				return jsonResult;
			}

			if (c2cTransaction.getTransactionType() == 1) {// 买确认,充币,可用增加
				ExDigitalmoneyAccount account = exDigitalmoneyAccountService.get(c2cTransaction.getAccountId());
				ExDmTransaction exDmTransaction = new ExDmTransaction();
				exDmTransaction.setAccountId(account.getId());
				exDmTransaction.setCoinCode(account.getCoinCode());
				exDmTransaction.setCreated(new Date());
				exDmTransaction.setCurrencyType(account.getCurrencyType());
				exDmTransaction.setWebsite(account.getWebsite());
				exDmTransaction.setCustomerId(account.getCustomerId());
				exDmTransaction.setCustomerName(account.getUserName());
				exDmTransaction.setFee(new BigDecimal(0));
				exDmTransaction.setTransactionMoney(c2cTransaction.getTransactionCount().subtract(c2cTransaction.getFee()));
				exDmTransaction.setStatus(2);
				exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
				// 充币
				exDmTransaction.setTransactionType(1);
				exDmTransaction.setUserId(ContextUtil.getCurrentUserId());
				exDmTransaction.setRemark("C2C买币");
				exDmTransaction.setOptType(9);
				exDmTransactionService.save(exDmTransaction);

				// 保存关联关系
				c2cTransaction.setTransactionId(exDmTransaction.getId());
				super.update(c2cTransaction);

				// 发送mq消息
				Accountadd accountadd = new Accountadd();
				accountadd.setAccountId(c2cTransaction.getAccountId());
				accountadd.setMoney(c2cTransaction.getTransactionCount().subtract(c2cTransaction.getFee()));
				accountadd.setMonteyType(1);
				accountadd.setAcccountType(1);
				accountadd.setRemarks(49);
				accountadd.setTransactionNum(c2cTransaction.getTransactionNum());
				List<Accountadd> list = new ArrayList<Accountadd>();
				list.add(accountadd);
				messageProducer.toAccount(JSON.toJSONString(list));

				// 调用锁仓管理操作---此处测试专用,测完删除
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("customerId", account.getCustomerId());
				paramMap.put("coinCode", account.getCoinCode());
				paramMap.put("transactionNum", c2cTransaction.getTransactionNum());
				paramMap.put("accountId", account.getId());
				ThreadPool.exe(new LockRedisRunnable(paramMap));
				// -----------------------end------------------------------

			} else if (c2cTransaction.getTransactionType() == 2) {// 卖确认,扣币,冻结减少

				// 币订单状态改为成功
				ExDmTransaction exDmTransaction = exDmTransactionService.get(c2cTransaction.getTransactionId());
				exDmTransaction.setStatus(2);
				exDmTransactionService.update(exDmTransaction);

				Accountadd accountadd = new Accountadd();
				accountadd.setAccountId(c2cTransaction.getAccountId());
				accountadd.setMoney(c2cTransaction.getTransactionCount().multiply(new BigDecimal(-1)));
				accountadd.setMonteyType(2);
				accountadd.setAcccountType(1);
				accountadd.setRemarks(51);
				accountadd.setTransactionNum(c2cTransaction.getTransactionNum());
				List<Accountadd> list = new ArrayList<Accountadd>();
				list.add(accountadd);
				messageProducer.toAccount(JSON.toJSONString(list));

			}
			c2cTransaction.setCheckUser(ContextUtil.getCurrentUserName());
			c2cTransaction.setStatus(2);
			super.update(c2cTransaction);
			//发送站内信
			AppCustomer appCustomer = appCustomerService.get(c2cTransaction.getCustomerId());
			appMessageService.sysSendMsg(appCustomer, MsgTypeEnum.C2C);


			//发送前台的消息通知
//			RemoteAppCustomerService appCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
//			AppCustomer customer = appCustomerService.getById(c2cTransaction.getCustomerId());
//			this.sendFrontMessage(customer,c2cTransaction.getTransactionNum());
		}
		jsonResult.setSuccess(true);

		return jsonResult;
	}

//	private void sendFrontMessage(AppCustomer customer, String transactionNum) {
//		//获取提币模板消息
//		QueryFilter amcQueryFilter = new QueryFilter(AppMessageCategory.class);
//		amcQueryFilter.addFilter( "keywords=", "2" );//2
//		AppMessageCategory appMessageCategory = appMessageCategoryService.get(amcQueryFilter);
//
//		QueryFilter amtQueryFilter = new QueryFilter(AppMessageTemplate.class);
//		amtQueryFilter.addFilter( "templateId=", appMessageCategory.getId() );//5为提币
//		AppMessageTemplate appMessageTemplate = appMessageTemplateService.get(amtQueryFilter);
//
//		//封装通知的消息
//		appMessageService.saveC2CMessage(customer,appMessageCategory,appMessageTemplate,transactionNum);
//	}

	@Override
	public JsonResult close(Long id) {

		JsonResult jsonResult = new JsonResult();

		synchronized (lock) {
			C2cTransaction c2cTransaction = super.get(id);

			if (c2cTransaction.getStatus().intValue() == 3) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("交易已关闭");
				return jsonResult;
			}
			if (c2cTransaction.getStatus().intValue() == 2) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("交易已成功");
				return jsonResult;
			}

			if (c2cTransaction.getTransactionType() == 1) {// 买确认
				c2cTransaction.setStatus(3);
				super.update(c2cTransaction);
				jsonResult.setSuccess(true);

			} else if (c2cTransaction.getTransactionType() == 2) {// 卖确认

				// 订单改为撤销
				ExDmTransaction exDmTransaction = exDmTransactionService.get(c2cTransaction.getTransactionId());
				exDmTransaction.setStatus(3);
				exDmTransactionService.update(exDmTransaction);

				List<Accountadd> list = new ArrayList<Accountadd>();
				// 减少冻结，
				Accountadd accountadd = new Accountadd();
				accountadd.setAccountId(c2cTransaction.getAccountId());
				accountadd.setMoney(c2cTransaction.getTransactionCount().multiply(new BigDecimal(-1)));
				accountadd.setMonteyType(2);
				accountadd.setAcccountType(1);
				accountadd.setRemarks(53);
				accountadd.setTransactionNum(c2cTransaction.getTransactionNum());

				// 增加可用
				Accountadd accountadd2 = new Accountadd();
				accountadd2.setAccountId(c2cTransaction.getAccountId());
				accountadd2.setMoney(c2cTransaction.getTransactionCount().subtract(c2cTransaction.getFee()));
				accountadd2.setMonteyType(1);
				accountadd2.setAcccountType(1);
				accountadd2.setRemarks(53);
				accountadd2.setTransactionNum(c2cTransaction.getTransactionNum());
				list.add(accountadd);
				list.add(accountadd2);
				messageProducer.toAccount(JSON.toJSONString(list));

				c2cTransaction.setStatus(3);
				super.update(c2cTransaction);
				jsonResult.setSuccess(true);

			}
		}

		return jsonResult;
	}

	@Override
	public String getC2cOrderDetail(String transactionNum) {


		QueryFilter filter = new QueryFilter(C2cTransaction.class);
		filter.addFilter("transactionNum=", transactionNum);
		C2cTransaction c2cTransaction = super.get(filter);

		JSONObject obj = new JSONObject();
		// 汇款随机码
		obj.put("randomNum", c2cTransaction.getRandomNum());
		// 订单状态
		obj.put("status", c2cTransaction.getStatus());
		// 转账金额
		obj.put("transactionMoney", c2cTransaction.getTransactionMoney());
		// 交易币种
		obj.put("coinCode", c2cTransaction.getCoinCode());
		// 交易状态
		obj.put("status2", c2cTransaction.getStatus2());
		// 订单类型
		obj.put("transactionType", c2cTransaction.getTransactionType());
		// 订单号
		obj.put("transactionNum", c2cTransaction.getTransactionNum());
		// 用户登录名
		obj.put("userName", c2cTransaction.getUserName());
		// 交易数量
		obj.put("transactionCount", c2cTransaction.getTransactionCount());
		// 交易单价
		obj.put("transactionPrice", c2cTransaction.getTransactionPrice());

		if (c2cTransaction.getTransactionType() == 1) {// 买
			AppBusinessman appBusinessman = appBusinessmanService.get(c2cTransaction.getBusinessmanId());
			if (appBusinessman != null) {
				obj.put("businessmanTrueName", appBusinessman.getTrueName());
			}

			AppBusinessmanBank appBusinessmanBank = appBusinessmanBankService.get(c2cTransaction.getBusinessmanBankId());
			if (appBusinessmanBank != null) {
				// 开户行名称
				obj.put("bankname", appBusinessmanBank.getBankname());
				// 卡号
				obj.put("bankcard", appBusinessmanBank.getBankcard());
				// 持卡人
				obj.put("bankowner", appBusinessmanBank.getBankowner());
			}
		} else if (c2cTransaction.getTransactionType() == 2) {// 卖
			AppBankCard appBankCard = appBankCardService.get(c2cTransaction.getCustomerBankId());
			if (appBankCard != null) {

				// 开户行名称
				obj.put("bankname", appBankCard.getCardBank() + appBankCard.getSubBank());
				// 卡号
				obj.put("bankcard", appBankCard.getCardNumber());
				// 银行卡所在地
				obj.put("bankAddress", appBankCard.getBankProvince() + appBankCard.getBankAddress());
				// 持卡人
				//obj.put("bankowner", appBankCard.getCardName());
				obj.put("bankowner", appBankCard.getSurname()+appBankCard.getTrueName());

			}
		}

		return obj.toJSONString();

	}

	@Override
	public List<C2cTransaction> getC2cList(HttpServletRequest request) {
		String type = request.getParameter("type").toString();
		String id = request.getParameter("id").toString();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type",type);//1查询交易商 2查询交易商银行卡
		map.put("id",id);
		return ((C2cTransactionDao)dao).getC2cList(map);
	}
}
