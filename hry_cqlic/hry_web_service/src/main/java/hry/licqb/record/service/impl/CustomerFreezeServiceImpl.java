/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-13 15:29:30 
 */
package hry.licqb.record.service.impl;

import com.alibaba.fastjson.JSON;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.front.redis.model.UserRedis;
import hry.licqb.record.dao.CustomerFreezeDao;
import hry.licqb.record.model.CustomerFreeze;
import hry.licqb.record.service.CustomerFreezeService;
import hry.licqb.util.AccountUtil;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import org.springframework.stereotype.Service;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> CustomerFreezeService </p>
 * @author:         zhouming
 * @Date :          2020-01-13 15:29:30  
 */
@Service("customerFreezeService")
public class CustomerFreezeServiceImpl extends BaseServiceImpl<CustomerFreeze, Long> implements CustomerFreezeService{

	@Resource
	private MessageProducer messageProducer;

	@Resource(name="customerFreezeDao")
	@Override
	public void setDao(BaseDao<CustomerFreeze, Long> dao) {
		super.dao = dao;
	}

	@Override
	public BigDecimal getFreezeMoney(Long customerId) {
		QueryFilter filter = new QueryFilter(CustomerFreeze.class);
		filter.addFilter("customerId=", customerId);
		CustomerFreeze customerFreeze = super.get(filter);
		if (customerFreeze == null) {
			customerFreeze = new CustomerFreeze();
			customerFreeze.setCustomerId(customerId);
			customerFreeze.setFreezeMoney(new BigDecimal(0));
		}
		return customerFreeze.getFreezeMoney();
	}

	@Override
	public void updateFreezeMoney(Long customerId, BigDecimal freezeMoney) {
		QueryFilter filter = new QueryFilter(CustomerFreeze.class);
		filter.addFilter("customerId=", customerId);
		CustomerFreeze customerFreeze = super.get(filter);
		if (customerFreeze == null) {
			customerFreeze = new CustomerFreeze();
			customerFreeze.setCustomerId(customerId);
			customerFreeze.setFreezeMoney(freezeMoney);
			super.save(customerFreeze);
		} else {
			customerFreeze.setFreezeMoney(customerFreeze.getFreezeMoney().add(freezeMoney));
			super.update(customerFreeze);
		}
	}

	@Override
	public void clearFreezeMoney(Long customerId) {
		QueryFilter filter = new QueryFilter(CustomerFreeze.class);
		filter.addFilter("customerId=", customerId);
		CustomerFreeze customerFreeze = super.get(filter);
		customerFreeze.setFreezeMoney(new BigDecimal(0));
		super.update(customerFreeze);
	}

	@Override
	public void againPutIntoFreeze(Long customerId) {
		CustomerFreeze customerFreeze = ((CustomerFreezeDao)dao).getCustomerFreeze(customerId);
		if (customerFreeze != null) {
			/*给对应账户进行热账户加减*/
			// 差额
			BigDecimal money = customerFreeze.getFreezeMoney().subtract(customerFreeze.getColdMoney());
			// 获取币账户信息--查redis缓存
			RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
			UserRedis userRedis = redisUtil.get(customerId.toString());
			// bk账户补扣参数
			String transactionNum = IdGenerate.transactionNum("bk");
			// 获得币账户
			ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("USDT").toString(), "USDT");
			List<Accountadd> list = new ArrayList<Accountadd>();
			// 热账户减少---锁仓冻结（55）
			list.add(AccountUtil.getAccountAdd(exaccount.getId(), new BigDecimal("-" + money), 1, 1,55,
					transactionNum));
			// 冷账户增加---锁仓冻结（55）
			list.add(AccountUtil.getAccountAdd(exaccount.getId(), new BigDecimal("+" + money), 2, 1, 55,
					transactionNum));
			messageProducer.toAccount(JSON.toJSONString(list));
		}
	}

	@Override
	public CustomerFreeze getCustomerFreeze(Long customerId) {
		CustomerFreeze customerFreeze = ((CustomerFreezeDao)dao).getCustomerFreeze(customerId);
		return customerFreeze;
	}
}
