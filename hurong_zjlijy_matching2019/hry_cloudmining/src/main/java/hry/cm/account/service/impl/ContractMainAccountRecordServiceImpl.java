/**
 * Copyright:
 * @author:      gaomimi
 * @version:     V1.0
 * @Date:        2019-01-21 18:07:33
 */
package hry.cm.account.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import hry.cm.account.model.ContractMainAccountRecord;
import hry.cm.account.service.CmAccountService;
import hry.cm.account.service.ContractMainAccountRecordService;
import hry.cm.dto.Accountadd;
import hry.cm.dto.AccountaddTrade;
import hry.cm.enums.CmAccountRemarkEnum;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mq.producer.service.MessageProducer;

/**
 * <p> ContractMainAccountRecordService </p>
 * @author:         gaomimi
 * @Date :          2019-01-21 18:07:33
 */
@Service("contractMainAccountRecordService")
public class ContractMainAccountRecordServiceImpl extends BaseServiceImpl<ContractMainAccountRecord, Long> implements ContractMainAccountRecordService {

	@Resource(name="contractMainAccountRecordDao")
	@Override
	public void setDao(BaseDao<ContractMainAccountRecord, Long> dao) {
		super.dao = dao;
	}
	@Resource
	public CmAccountService cmAccountService;
	@Resource
	private MessageProducer messageProducer;
	@Override
	public  void mainTocontract(Long customerId, Long mainAccountId,Long contractAccountId,String coinCode, BigDecimal count){

			//在调用这个方法的地方做一个转账余额的判断,加锁判断，不然容易变成负数
			ContractMainAccountRecord cmar=new ContractMainAccountRecord();
			cmar.setCoinCode(coinCode);
			cmar.setCustomerId(customerId);
			cmar.setRecordType(1);
			cmar.setRemark("cloudmining");
			cmar.setTransactionMoney(count);
			cmar.setOrderNum( "cloudmining"+ UUID.randomUUID().toString().replace("-", ""));
			//主账户发送消息（支出）
			// 发送mq通知缓存
			AccountaddTrade accountaddTrade = new AccountaddTrade();
			accountaddTrade.setAccountId(mainAccountId);
			accountaddTrade.setMoney(count.multiply(new BigDecimal(-1)));
			accountaddTrade.setMonteyType(1);
			accountaddTrade.setAcccountType(1);
			accountaddTrade.setRemarks(114);
			accountaddTrade.setTransactionNum(cmar.getOrderNum());
			List<AccountaddTrade> listaccountaddTrade = new ArrayList<AccountaddTrade>();
			listaccountaddTrade.add(accountaddTrade);
			messageProducer.toAccount(JSON.toJSONString(listaccountaddTrade));

			//Cm账号发送消息(收入)
			List<Accountadd> aaddlists = new ArrayList<Accountadd>();
			Accountadd accountadd3 = new Accountadd(customerId,coinCode,contractAccountId, count, 1, CmAccountRemarkEnum.TYPE1.getIndex(),cmar.getOrderNum() );
			aaddlists.add(accountadd3);
		    cmAccountService.accountaddQueue(JSON.toJSONString(aaddlists));
			this.save(cmar);
		}
	@Override
	public void contractTomain(Long customerId, Long mainAccountId,Long contractAccountId,String coinCode, BigDecimal count){
		//在调用这个方法的地方做一个转账余额的判断,加锁判断，不然容易变成负数
		ContractMainAccountRecord cmar=new ContractMainAccountRecord();
		cmar.setCoinCode(coinCode);
		cmar.setCustomerId(customerId);
		cmar.setRecordType(2);
		cmar.setRemark("cloudmining");
		cmar.setTransactionMoney(count);
		cmar.setOrderNum( "cloudmining-"+UUID.randomUUID().toString().replace("-", ""));
		//主账户发送消息（支出）
		// 发送mq通知缓存
		AccountaddTrade accountaddTrade = new AccountaddTrade();
		accountaddTrade.setAccountId(mainAccountId);
		accountaddTrade.setMoney(count);
		accountaddTrade.setMonteyType(1);
		accountaddTrade.setAcccountType(1);
		accountaddTrade.setRemarks(115);
		accountaddTrade.setTransactionNum(cmar.getOrderNum());
		List<AccountaddTrade> listaccountaddTrade = new ArrayList<AccountaddTrade>();
		listaccountaddTrade.add(accountaddTrade);
		messageProducer.toAccount(JSON.toJSONString(listaccountaddTrade));

		//Cm账号发送消息(收入)
		List<Accountadd> aaddlists = new ArrayList<Accountadd>();
		Accountadd accountadd3 = new Accountadd(customerId,coinCode,contractAccountId, count.multiply(new BigDecimal("-1")), 1, CmAccountRemarkEnum.TYPE2.getIndex(),cmar.getOrderNum() );
		aaddlists.add(accountadd3);
		cmAccountService.accountaddQueue(JSON.toJSONString(aaddlists));
		this.save(cmar);

	}

}


