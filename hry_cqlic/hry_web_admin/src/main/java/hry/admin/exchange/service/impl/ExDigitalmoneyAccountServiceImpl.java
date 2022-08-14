/**
 * Copyright:
 * @author:      liushilei
 * @version:     V1.0
 * @Date:        2018-06-13 10:56:33
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.dao.ExDigitalmoneyAccountDao;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.model.ExProduct;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.mq.producer.MessageAccountUtil;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> ExDigitalmoneyAccountService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:56:33
 */
@Service("exDigitalmoneyAccountService")
public class ExDigitalmoneyAccountServiceImpl extends BaseServiceImpl<ExDigitalmoneyAccount, Long> implements ExDigitalmoneyAccountService{
	private final static Logger log = Logger.getLogger(ExDigitalmoneyAccountServiceImpl.class);

	@Resource(name="exDigitalmoneyAccountDao")
	@Override
	public void setDao(BaseDao<ExDigitalmoneyAccount, Long> dao) {
		super.dao = dao;
	}

	@Resource
	ExDmTransactionService exDmTransactionService;

	@Override
	public void saveRecordAfterAudit (ExDigitalmoneyAccount eda, int type, ExProduct exProduct) {

		String remark = "实名送"+exProduct.getGiveCoin()+"个币!";
		if(type==2) {
			remark = "邀请送"+exProduct.getCommendCoin()+"个币!";
		}

		ExDmTransaction exDmTransaction = new ExDmTransaction();
		exDmTransaction.setAccountId(eda.getId());
		exDmTransaction.setCoinCode(eda.getCoinCode());
		exDmTransaction.setCreated(new Date());
		exDmTransaction.setCustomerId(eda.getCustomerId());
		exDmTransaction.setCustomerName(eda.getUserName());
		exDmTransaction.setTime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setTimereceived(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setBlocktime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		exDmTransaction.setFee(new BigDecimal(0));
		exDmTransaction.setTransactionMoney(exProduct.getGiveCoin());
		exDmTransaction.setStatus(2);
		exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
		// 充币
		exDmTransaction.setTransactionType(1);
		exDmTransaction.setUserId(eda.getCustomerId());
		exDmTransaction.setWebsite(eda.getWebsite());
		exDmTransaction.setCurrencyType(eda.getCurrencyType());
		exDmTransaction.setRemark(remark);
		exDmTransactionService.save(exDmTransaction);

		// 发送消息
		MessageAccountUtil.addCoin(eda.getId(), exProduct.getGiveCoin(), exDmTransaction.getTransactionNum());

	}
	@Override
	public PageResult findPageBySql(QueryFilter filter) {



		//创建PageResult对象

		//----------------------查询开始------------------------------

		Map<String,Object> map = PageFactory.getMap(filter);

		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String trueName = filter.getRequest().getParameter("trueName");
		String publicKey = filter.getRequest().getParameter("publicKey");
		String surname = filter.getRequest().getParameter("surname");

		boolean hasparam = false;
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode",  coinCode);
			hasparam = true;
		}
		if(!StringUtils.isEmpty(publicKey)){
			map.put("publicKey", "%"+publicKey+"%");
			hasparam = true;
		}

		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
			hasparam = true;
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
			hasparam = true;
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
			hasparam = true;
		}
		if(!StringUtils.isEmpty(surname)){
			map.put("surname", "%"+surname+"%");
			hasparam = true;
		}


		map.put("hasparam",hasparam);

		long s1=System.currentTimeMillis();
		Long count = ((ExDigitalmoneyAccountDao)dao).findPageBySqlCount(map);
		log.info("会员货币账户管理sql所用时间 ：" + (System.currentTimeMillis()-s1) + "毫秒");
		List<ExDigitalmoneyAccount>  list = ((ExDigitalmoneyAccountDao)dao).findPageBySql(map);
		log.info("会员货币账户管理sql所用时间 ：" + (System.currentTimeMillis()-s1) + "毫秒");
		return  new PageResult(list, count,map.get("pageNumber"),map.get("pageSize"));

	}

}
