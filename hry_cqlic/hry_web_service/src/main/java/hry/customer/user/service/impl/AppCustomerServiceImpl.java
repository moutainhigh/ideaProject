/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午4:21:38
 */
package hry.customer.user.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.account.fund.model.AppAccount;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.shiro.PasswordHelper;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import hry.customer.commend.model.AppCommendUser;
import hry.customer.commend.service.AppCommendUserService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.dao.AppCustomerDao;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.model.AppCustomerSimple;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExProductService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.manage.remote.model.RemoteResult;
import hry.mq.producer.MessageAccountUtil;
import hry.redis.common.utils.RedisService;
import util.UserRedisUtils;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月24日 下午4:21:38 
 */
@Service("appCustomerService")
public class AppCustomerServiceImpl extends BaseServiceImpl<AppCustomer, Long> implements AppCustomerService {
	@Resource
	private ExDmTransactionService exDmTransactionService;

	@Resource(name = "appCustomerDao")
	@Override
	public void setDao(BaseDao<AppCustomer, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private RedisService redisService;
	@Resource
	private AppPersonInfoService appPersonInfoService;
	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCustomer> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String surname = filter.getRequest().getParameter("surname");
		String trueName = filter.getRequest().getParameter("trueName");
		String cardId = filter.getRequest().getParameter("cardId");
		
		String referralCode = filter.getRequest().getParameter("referralCode");
		String type = filter.getRequest().getParameter("type");
		String userName_in=filter.getRequest().getParameter("userName_in");
		String isDelete = filter.getRequest().getParameter("isDelete");
		String isReal = filter.getRequest().getParameter("isReal");
		String isAdmin = filter.getRequest().getParameter("isAdmin");
		String isGag = filter.getRequest().getParameter("isGag");
		
		String unstates = (String)filter.getRequest().getAttribute("unstates");
		String states = (String)filter.getRequest().getAttribute("states");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
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
		if(!StringUtils.isEmpty(type)){
			map.put("type", "%"+type+"%");
		}
		if(!StringUtils.isEmpty(isDelete)){
			map.put("isDelete", isDelete);
		}
		if(!StringUtils.isEmpty(isAdmin)){
			map.put("isAdmin", isAdmin);
		}
		if(!StringUtils.isEmpty(isGag)){
			((AppCustomerDao)dao).updateGag();
			if(!"-1".equals(isGag)){
				map.put("isGag", isGag);
			}
		}
		
		if(!StringUtils.isEmpty(isReal)){
			
			switch (isReal){
			 case "0": map.put("states", '0'); break;
			 case "1": map.put("states", 1); break;
			 case "2": map.put("states", 2); break;
			 case "3": map.put("states", 3); break;
			}
		}else{
			if(!StringUtils.isEmpty(unstates)){
				map.put("unstates", unstates);
			}
			if(!StringUtils.isEmpty(states)){
				map.put("states", states);
			}
		}
		
		
		((AppCustomerDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		for (Object object:pageResult.getRows()) {
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
		}
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}

	@Override
	public List<AppCustomerSimple> findAppCustomerSimple(HttpServletRequest request) {
		return ((AppCustomerDao)dao).findAppCustomerSimple(null);
	}

	//禁用某个用户方法
	@Override
	public JsonResult storpCustomer(Long id,boolean type) {
		JsonResult jsonResult = new JsonResult();
		AppCustomer customer = super.get(id);
//		Integer delete = customer.getIsDelete();
		if(type){

				redisService.delete("mobile:"+customer.getUuid());
				customer.setIsDelete(1);
				customer.setUuid("");
				super.update(customer);

				UserRedisUtils.userRedisToSession(customer, redisService);// by -- lixin 2018-04-27 20:36:05
				jsonResult.setMsg("已成功禁用此用户");
				jsonResult.setSuccess(true);
				return jsonResult;
		}else{
			AppPersonInfo appPersonInfo=appPersonInfoService.getByCustomerId(id);
			redisService.delete("loginErrorCount:" + appPersonInfo.getEmail().toLowerCase());
			redisService.delete("loginErrorCount:" + appPersonInfo.getMobilePhone());
			customer.setIsDelete(0);
			UserRedisUtils.userRedisToSession(customer, redisService);// by -- lixin 2018-04-27 20:36:05
			super.update(customer);
			jsonResult.setMsg("解除禁用成功");
			jsonResult.setSuccess(true);
			return jsonResult;
		}
		
		
	}

	@Override
	public JsonResult lockCustomer(Date time, Long id,boolean type) {
		JsonResult result = new JsonResult();
		
		AppCustomer customer = super.get(id);
		Integer delete = customer.getIsDelete();
		if(type){
			if(delete !=1){
				customer.setLockTime(time);
				customer.setIsLock(1);
				super.update(customer);
				UserRedisUtils.userRedisToSession(customer, redisService);// by -- lixin 2018-04-27 20:36:05
				result.setMsg("锁定该用户成功");
				result.setSuccess(true);
				return result;
			}
			result.setMsg("该用户已被禁用不能锁定");
			result.setSuccess(false);
		}else{
			if(delete !=1){
				  if(customer.getIsLock()==0){
					   result.setMsg("该用户未锁定");
						result.setSuccess(false);
						return result;
				  }else{
					  customer.setIsLock(0);
						super.update(customer);
						UserRedisUtils.userRedisToSession(customer, redisService);// by -- lixin 2018-04-27 20:36:05
						result.setMsg("解锁该用户成功");
						result.setSuccess(true);
						return result;
				  }
				
			}
			result.setMsg("该用户已被禁用不能解锁");
			result.setSuccess(false);
		}
		
		return result;
	}

	
	
	@Override
	public List<AppCustomer> findById(Long id) {
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		
		if(null!=id&&!"".equals(id)){
			map.put("id", id);
			List<AppCustomer> list=	((AppCustomerDao)dao).findById(map);
			return list;
		}
		
		return null;
	}


	
	@Override
	public List<AppCustomer> getRealNameCustomer() {
		
		List<AppCustomer> list=	((AppCustomerDao)dao).getRealNameCustomer();
		return list;
	}

	
	@Override
	public List<AppCustomer> getFundChangeCustomers(Map<String, Object> map) {
		return ((AppCustomerDao)dao).getFundChangeCustomers(map);
	}

	@Override
	public int getHasAuthNum() {
		return ((AppCustomerDao)dao).getHasAuthNum();
	}


	@Override
	public AppCustomer getByCustomerId(String username) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=username && !"".equals(username)){
			map.put("username", username);
			List<AppCustomer> list=((AppCustomerDao)dao).getByCustomerId(map);
			return list==null?null:list.get(0);
		}
		return null;
	}

	@Override
	public AppCustomer getByPhone(String mobile) {
		// TODO Auto-generated method stub
				Map<String, Object> map=new HashMap<String, Object>();
				if(null!=mobile && !"".equals(mobile)){
					map.put("phone", mobile);
					List<AppCustomer> list=((AppCustomerDao)dao).getByPhone(map);
					return list==null?null:list.get(0);
				}
				return null;
	}

	@Override
	public int commendCount(String userName) {
		// TODO Auto-generated method stub
		return ((AppCustomerDao)dao).commendCount(userName);

	}
	
	@Override
	public PageResult findPageBySqlList(QueryFilter filter) {
		
		//----------------------分页查询头部外壳------------------------------
		// 分页参数处理
		String startStr = filter.getRequest().getParameter("start");
		String lengthStr = filter.getRequest().getParameter("length");
		Integer startpage = Integer.valueOf(startStr);
		Integer lengthpage = Integer.valueOf(lengthStr);
		if( lengthpage == null || lengthpage == 0 ){
			lengthpage = 10;
		}
		startpage = startpage/lengthpage;
		// 分页参数处理结束
		
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);

		Map<String,Object> map = new HashMap<String,Object>();
	    Integer start = startpage * lengthpage;
		map.put("start", start);
		map.put("end", lengthpage);
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String trueName = filter.getRequest().getParameter("trueName");
		String cardId = filter.getRequest().getParameter("cardId");
		
		String referralCode = filter.getRequest().getParameter("referralCode");
		String type = filter.getRequest().getParameter("type");
		String userName_in=filter.getRequest().getParameter("userName_in");
		String isDelete = filter.getRequest().getParameter("isDelete");
		String isReal = filter.getRequest().getParameter("isReal");
		
		String isBlacklist = filter.getRequest().getParameter("isBlacklist");
		
		if(!StringUtils.isEmpty(isDelete)){
			map.put("isDelete", isDelete);
		}
		if(!StringUtils.isEmpty(referralCode)){
			map.put("referralCode", "%"+referralCode+"%");
		}
		if(!StringUtils.isEmpty(type)){
			map.put("type", "%"+type+"%");
		}
		if(!StringUtils.isEmpty(userName_in)){
			map.put("userName_in", userName_in.split(","));
		}
		if(!StringUtils.isEmpty(isBlacklist)){
			map.put("isBlacklist", isBlacklist);
		}
		if(!StringUtils.isEmpty(isReal)){
			switch (isReal){
			 case "0": map.put("states", '0'); break;
			 case "1": map.put("states", 1); break;
			 case "2": map.put("states", 2); break;
			 case "3": map.put("states", 3); break;
			}
		}
		
		Map<String,Object> mapperson = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			mapperson.put("email", email);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			mapperson.put("mobilePhone", mobilePhone);
		}
		if(!StringUtils.isEmpty(trueName)){
			mapperson.put("trueName", trueName);
		}
		if(!StringUtils.isEmpty(cardId)){
			mapperson.put("cardId", cardId);
		}
		
		if(mapperson.size()>0){
			List<String> listpersoninfo = ((AppCustomerDao)dao).findCustomerByCondition(mapperson);
			if(listpersoninfo.size()>0){
				map.put("customerId", listpersoninfo);
			}else{
				List<AppCustomer> list = new ArrayList<AppCustomer>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
			}
		}
		
		Long count = ((AppCustomerDao)dao).findPageBySqlCount(map);
		List<AppCustomer> list = ((AppCustomerDao)dao).findPageBySqlList(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);
	
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}

	/**
	 * 实名认证之后送币
	 * @param id
	 */
	@Override
	public void giveCoin (Long id) {
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
					exDigitalmoneyAccountService.saveRecordAfterAudit(digitalmoneyAccount, 1,exProduct);
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
	 * 获取审核密码
	 * @return
	 */
	@Override
	public RemoteResult getAuditpassword () {
		RemoteResult jsonResult = new RemoteResult();
		RedisService redisService = (RedisService)ContextUtil.getBean("redisService");
		String password = redisService.get("auditpassword");
		if(!StringUtils.isEmpty(password)) {
			jsonResult.setSuccess(true);
			jsonResult.setObj(password);
		}
		return jsonResult;
	}

	/**
	 * 设置审核密码
	 * @param map
	 * @return
	 */
	@Override
	public RemoteResult setAuditpassword(Map<String, String> map){
		RemoteResult jsonResult = new RemoteResult();
		//审核密码
		String setAuditpassword = map.get("auditpassword");
		if(StringUtils.isEmpty(setAuditpassword)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("密码不能为空");
			return jsonResult;
		}

		PasswordHelper passwordHelper = new PasswordHelper();
		String encryString = passwordHelper.encryString(setAuditpassword, "setAuditpassword");
		RedisService redisService = (RedisService)ContextUtil.getBean("redisService");
		redisService.save("auditpassword", encryString);

		jsonResult.setSuccess(true);
		jsonResult.setMsg("设置成功");
		return jsonResult;
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

}
