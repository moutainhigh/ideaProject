/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-06 14:36:50 
 */
package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.admin.customer.model.AppAccount;
import hry.admin.customer.service.AppAccountService;
import hry.admin.exchange.dao.AppTransactionDao;
import hry.admin.exchange.model.AppTransaction;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.AppTransactionService;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.listener.ServerManagement;
import hry.trade.redis.model.Accountadd;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppTransactionService </p>
 * @author:         tianpengyu
 * @Date :          2018-07-06 14:36:50  
 */
@Service("appTransactionService")
public class AppTransactionServiceImpl extends BaseServiceImpl<AppTransaction, Long> implements AppTransactionService{
	private static Logger logger = Logger.getLogger(AppTransactionServiceImpl.class);
	@Resource(name="appTransactionDao")
	@Override
	public void setDao(BaseDao<AppTransaction, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private MessageProducer messageProducer;
	@Resource
	private AppAccountService appAccountService;

	@Resource
	private ExDmTransactionService exDmTransactionService;


	@Override
	public boolean dmWithdrawReject(ExDmTransaction exDmTransaction){
		try {
			//冷账户减
			Accountadd accountadd = new Accountadd();
			accountadd.setAccountId(exDmTransaction.getAccountId());
			accountadd.setMoney(exDmTransaction.getTransactionMoney().multiply(new BigDecimal(-1)));
			accountadd.setMonteyType(2);
			accountadd.setAcccountType(1);
			accountadd.setRemarks(37);
			accountadd.setTransactionNum(exDmTransaction.getTransactionNum());

			//热账户加
			Accountadd accountadd1 = new Accountadd();
			accountadd1.setAccountId(exDmTransaction.getAccountId());
			accountadd1.setMoney(exDmTransaction.getTransactionMoney());
			accountadd1.setMonteyType(1);
			accountadd1.setAcccountType(1);
			accountadd1.setRemarks(37);
			accountadd1.setTransactionNum(exDmTransaction.getTransactionNum());
			List<Accountadd> list= new ArrayList<Accountadd>();
			list.add(accountadd);
			list.add(accountadd1);
			String jsonString = JSON.toJSONString(list);
			logger.error("消息发送前:"+jsonString);
			messageProducer.toAccount(jsonString);

			exDmTransaction.setStatus(3);
			exDmTransactionService.update(exDmTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		Page<AppTransaction> page = PageFactory.getPage(filter);

		Map<String,Object> map = new HashMap<>();
		for (Map.Entry<String, String[]> entry : filter.getRequest().getParameterMap().entrySet()){
			if(entry.getKey().toUpperCase().contains("_LIKE")){
				map.put(entry.getKey().split("_")[0],"%"+entry.getValue()[0]+"%");
				continue;
			}
			map.put(entry.getKey(),entry.getValue()[0]);
		}

		String  status= filter.getRequest().getParameter("status");
		String transactionNum = filter.getRequest().getParameter("transactionNum_LIKE");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		String userName = filter.getRequest().getParameter("userName_LIKE");
		String currencyType = filter.getRequest().getParameter("currencyType_EQ");
		String transactionType = filter.getRequest().getParameter("transactionType_in");
		String ourAccountNumber = filter.getRequest().getParameter("ourAccountNumber_LIKE");
		String email = filter.getRequest().getParameter("email");
		String mobile = filter.getRequest().getParameter("mobile");
		String surname = filter.getRequest().getParameter("surname");
		String trueName = filter.getRequest().getParameter("trueName");


		if(!StringUtils.isEmpty(surname)){
			map.put("surName", "%"+surname+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}

		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobile)){
			map.put("mobilePhone", "%"+mobile+"%");
		}

		if(!StringUtils.isEmpty(status)){
			map.put("status", status);
		}
		if(!StringUtils.isEmpty(transactionNum)){
			map.put("transactionNum", "%"+transactionNum+"%");
		}
		if(!StringUtils.isEmpty(modified_s)){
			map.put("modified_s", modified_s);
		}
		if(!StringUtils.isEmpty(modified_e)){
			map.put("modified_e", modified_e);
		}
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", "%"+userName+"%");
		}
		if(!StringUtils.isEmpty(currencyType)){
			map.put("currencyType", currencyType);
		}
		if(!StringUtils.isEmpty(transactionType)){
			if(transactionType.split(",").length>=0){
				map.put("transactionType", transactionType.split(","));
			}else{
				map.put("transactionType", new String[]{transactionType});
			}


		}
		if(!StringUtils.isEmpty(ourAccountNumber)){
			map.put("ourAccountNumber", ourAccountNumber);
		}
		map.put("orderby", "atr.created desc");
		((AppTransactionDao)dao).findPageBySql(map);

		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public synchronized boolean undo(Long id, String name) {

		AppTransaction appTransaction = super.get(id);
		AppAccount appAccount = appAccountService.get(appTransaction.getAccountId());

		if (appTransaction != null && appTransaction.getStatus()==2){
			if(appAccount.getHotMoney().compareTo(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()))>=0){

				appTransaction.setStatus(Integer.valueOf(3));
				appTransaction.setRejectionReason("充值成功后撤销");
				super.update(appTransaction);


				Accountadd accountadd = new Accountadd();
				accountadd.setAccountId(appTransaction.getAccountId());
				accountadd.setMoney(appTransaction.getTransactionMoney().subtract(appTransaction.getFee()).multiply(new BigDecimal(-1)));
				accountadd.setMonteyType(1);
				accountadd.setAcccountType(0);
				accountadd.setRemarks(22);
				accountadd.setTransactionNum(appTransaction.getTransactionNum());
				List<Accountadd> list = new ArrayList<Accountadd>();
				list.add(accountadd);
				messageProducer.toAccount(JSON.toJSONString(list));

				return true;

			}
		}

		return false;

	}

}
