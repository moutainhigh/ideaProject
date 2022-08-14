/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:43:00 
 */
package hry.admin.customer.service.impl;

import com.github.pagehelper.Page;
import hry.admin.commend.model.AppCommendUser;
import hry.admin.commend.service.AppCommendUserService;
import hry.admin.customer.dao.AppCustomerDao;
import hry.admin.customer.model.AppAccount;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.model.ExProduct;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.exchange.service.ExProductService;
import hry.admin.mq.producer.MessageAccountUtil;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.UserRedisUtils;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppCustomerService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 09:43:00  
 */
@Service("appCustomerService")
public class AppCustomerServiceImpl extends BaseServiceImpl<AppCustomer, Long> implements AppCustomerService {

	@Resource(name = "appCustomerDao")
	@Override
	public void setDao(BaseDao<AppCustomer, Long> dao) {
		super.dao = dao;
	}

	@Resource
	RedisService redisService;

	@Resource
	AppPersonInfoService appPersonInfoService;

	@Resource
	private ExDmTransactionService exDmTransactionService;

	@Resource
	private AppCommendUserService appCommendUserService;

	//禁用某个用户方法
	@Override
	public JsonResult storpCustomer(Long id, boolean type) {
		JsonResult jsonResult = new JsonResult();
		AppCustomer customer = super.get(id);
//		Integer delete = customer.getIsDelete();
		if (type) {

			redisService.delete("mobile:" + customer.getUuid());
			customer.setIsDelete(1);
			customer.setUuid("");
			super.update(customer);
			UserRedisUtils.userRedisToSession(customer, redisService);// by -- lixin 2018-04-27 20:36:05

			//禁用用户后，删除用户token
			redisService.delete("JWT:token:app:sign:" + customer.getUsername());
			//推送单点登录订阅消息
			redisService.publish("websocketLogin", customer.getId().toString());

			jsonResult.setMsg("已成功禁用此用户");
			jsonResult.setSuccess(true);
			return jsonResult;
		} else {
			QueryFilter queryFilter = new QueryFilter(AppPersonInfo.class);
			queryFilter.addFilter("customerId=",customer.getId());
			AppPersonInfo appPersonInfo = appPersonInfoService.get(queryFilter);
			if (appPersonInfo != null){
				redisService.delete("loginErrorCount:" + appPersonInfo.getEmail().toLowerCase());
				redisService.delete("loginErrorCount:" + appPersonInfo.getMobilePhone());
			}

			customer.setIsDelete(0);
			UserRedisUtils.userRedisToSession(customer, redisService);// by -- lixin 2018-04-27 20:36:05
			super.update(customer);
			jsonResult.setMsg("解除禁用成功");
			jsonResult.setSuccess(true);
			return jsonResult;
		}


	}


	/**
	 * 实名认证之后送币
	 *
	 * @param id
	 */
	@Override
	public void giveCoin(Long id) {
		// 查出全部发行的产品例表
		ExProductService exProductService = (ExProductService) ContextUtil.getBean("exProductService");
		ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");
		QueryFilter filter = new QueryFilter(ExProduct.class);
		filter.addFilter("issueState=", Integer.valueOf(1));
		filter.setOrderby("isRecommend DESC");
		List<ExProduct> list = exProductService.find(filter);

		for (ExProduct exProduct : list) {
			QueryFilter filterAppAccount = new QueryFilter(AppAccount.class);
			filterAppAccount.addFilter("customerId=", id);
			filterAppAccount.addFilter("coinCode=", exProduct.getCoinCode());
			ExDigitalmoneyAccount digitalmoneyAccount = exDigitalmoneyAccountService.get(filterAppAccount);
			if (digitalmoneyAccount != null) {
				//给用户送币
				if (exProduct.getGiveCoin() != null && exProduct.getGiveCoin().compareTo(new BigDecimal(0)) > 0) {

					// 保存交易记录发送消息
					exDigitalmoneyAccountService.saveRecordAfterAudit(digitalmoneyAccount, 1, exProduct);
				}
			}

		}
		//给推荐人推荐送币
		AppCustomer appCustomer=super.get(id);
		String referralCode=appCustomer.getCommendCode();
		// 推荐送币
		if (!StringUtils.isEmpty(referralCode)) {
			AppCommendUserService appCommendUserService = (AppCommendUserService) ContextUtil.getBean("appCommendUserService");
			AppCommendUser appCommendUser = appCommendUserService.get(new QueryFilter(AppCommendUser.class).addFilter("receCode=", referralCode));
			AppCustomer customer = super.get(new QueryFilter(AppCustomer.class).addFilter("username=", appCommendUser.getUsername()));
			commendCoin(customer.getId());
		}



	}
	/**
	 * 邀请送币
	 *
	 * @param id
	 *            邀请人
	 */
	private void commendCoin(Long id) {
		// 查出全部发行的产品例表
		ExProductService exProductService = (ExProductService) ContextUtil.getBean("exProductService");
		ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");
		QueryFilter filter = new QueryFilter(ExProduct.class);
		filter.addFilter("issueState=", Integer.valueOf(1));
		filter.setOrderby("isRecommend DESC");
		List<ExProduct> list = exProductService.find(filter);

		for (ExProduct exProduct : list) {
			QueryFilter filterAppAccount = new QueryFilter(AppAccount.class);
			filterAppAccount.addFilter("customerId=", id);
			filterAppAccount.addFilter("coinCode=", exProduct.getCoinCode());
			ExDigitalmoneyAccount _digitalmoneyAccount = exDigitalmoneyAccountService.get(filterAppAccount);
			if (_digitalmoneyAccount != null) {
				if (exProduct.getCommendCoin() != null && exProduct.getCommendCoin().compareTo(new BigDecimal(0)) > 0) {

					// 记录订单
					ExDmTransaction exDmTransaction = new ExDmTransaction();
					exDmTransaction.setAccountId(_digitalmoneyAccount.getId());
					exDmTransaction.setCoinCode(_digitalmoneyAccount.getCoinCode());
					exDmTransaction.setCreated(new Date());
					exDmTransaction.setCustomerId(_digitalmoneyAccount.getCustomerId());
					exDmTransaction.setCustomerName(_digitalmoneyAccount.getUserName());
					exDmTransaction.setTime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
					exDmTransaction.setTimereceived(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
					exDmTransaction.setBlocktime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
					exDmTransaction.setFee(new BigDecimal(0));
					exDmTransaction.setTransactionMoney( exProduct.getCommendCoin());
					exDmTransaction.setStatus(2);
					exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
					// 充币
					exDmTransaction.setTransactionType(1);
					exDmTransaction.setUserId(_digitalmoneyAccount.getCustomerId());
					exDmTransaction.setWebsite(_digitalmoneyAccount.getWebsite());
					exDmTransaction.setCurrencyType(_digitalmoneyAccount.getCurrencyType());
					exDmTransaction.setRemark("邀请送" + exProduct.getCommendCoin() + "个币!");
					exDmTransaction.setOptType(8);
					exDmTransactionService.save(exDmTransaction);

					// 发送消息
					MessageAccountUtil.addCoin(_digitalmoneyAccount.getId(), exProduct.getCommendCoin(), exDmTransaction.getTransactionNum());

				}
			}
		}
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {


		//----------------------分页查询头部外壳------------------------------
		Page<AppCustomer> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------

		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String surname = filter.getRequest().getParameter("surname");
		String trueName = filter.getRequest().getParameter("trueName");
		String cardId = filter.getRequest().getParameter("cardId");
		String commonLanguage = filter.getRequest().getParameter("commonLanguage");

		String referralCode = (String) filter.getRequest().getParameter("referralCode");
		String type = (String) filter.getRequest().getParameter("type");
		String userName_in=filter.getRequest().getParameter("userName_in");
		String isDelete = filter.getRequest().getParameter("isDelete");
		String isReal = filter.getRequest().getParameter("isReal");
		String isAdmin = filter.getRequest().getParameter("isAdmin");
		String isGag = filter.getRequest().getParameter("isGag");
		String idIn = filter.getRequest().getParameter("ids");

		String unstates = (String)filter.getRequest().getParameter("unstates");
		String states = (String)filter.getRequest().getParameter("states");

		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(commonLanguage)){
			map.put("commonLanguage", commonLanguage);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(surname)){
			map.put("surname", "%"+surname+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(cardId)){
			map.put("cardId", "%"+cardId+"%");
		}
		if(!StringUtils.isEmpty(referralCode)){
			map.put("referralCode", "%"+referralCode+"%");
		}
		if(!StringUtils.isEmpty(userName_in)){
			map.put("userName_in", userName_in.split(","));
		}
		if(!StringUtils.isEmpty(idIn)){
			map.put("id_in", idIn.split(","));
		}
		if(!StringUtils.isEmpty(type)){
			map.put("type", "%"+type+"%");
		}
		if(!StringUtils.isEmpty(isDelete)){
			map.put("isDelete", isDelete);
		}
		if(!StringUtils.isEmpty(isAdmin)){
			map.put("isAdmin", isAdmin);
		}

		if(!StringUtils.isEmpty(isReal)){
			map.put("isReal", isReal);
		}

		if(!StringUtils.isEmpty(unstates)){
			map.put("unstates", unstates);
		}
		if(!StringUtils.isEmpty(states)){
			map.put("states", states);
		}

		((AppCustomerDao)dao).findPageBySql(map);

		for (Object object:page) {
			AppCustomer appCustomer=(AppCustomer)object;
			AppPersonInfo appPersonInfo=(AppPersonInfo)appCustomer.getAppPersonInfo();
			String i=redisService.get("loginErrorCount:" + appPersonInfo.getEmail().toLowerCase());
			String j=redisService.get("loginErrorCount:" + appPersonInfo.getMobilePhone());
			if(!StringUtils.isEmpty(i)) {
				int loginCount = Integer.parseInt(i);
				if (loginCount >= 5) {
					appCustomer.setIsDelete(1);
				}
			}
			if(!StringUtils.isEmpty(j)){
				int loginCount = Integer.parseInt(j);
				if (loginCount >= 5) {
					appCustomer.setIsDelete(1);
				}
			}
			// 李超业务---上级推荐人
			QueryFilter filter1 = new QueryFilter(AppCommendUser.class);
			filter1.addFilter("uid=", appCustomer.getId());
			AppCommendUser user = appCommendUserService.get(filter1);
			QueryFilter filter2 = new QueryFilter(AppPersonInfo.class);
			filter2.addFilter("customerId=",user.getPid());
			AppPersonInfo personInfo = appPersonInfoService.get(filter2);
			appCustomer.setpEmail(personInfo != null ? personInfo.getEmail():null);
		}
		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}

	/**
	 * 获取昨日新增客户数
	 * @return
	 */
	@Override
	public String getYesterdayCoutomers (){
		Map<String, Object> map = new HashMap<>();
		Long sum = ((AppCustomerDao)dao).getYesterdayCoutomers(map);
		return sum.toString();
	}


	@Override
	public PageResult findListGroupByDay(QueryFilter filter) {
		// TODO Auto-generated method stub
		//----------------------分页查询头部外壳------------------------------
		Page<AppCustomer> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		Map<String,Object> map = new HashMap<String,Object>();
		((AppCustomerDao)dao).findListGroupByDay(map);
		
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}


	@Override
	public JsonResult stropUserDynamic(Long id, boolean type) {
		JsonResult jsonResult = new JsonResult();
		AppCustomer customer = super.get(id);
		if (type) {
			customer.setIsGag(0);
		} else {
			customer.setIsGag(1);
		}
		super.update(customer);
		jsonResult.setMsg("操作成功");
		jsonResult.setSuccess(true);
		return jsonResult;
	}
}
