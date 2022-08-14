/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:56:38 
 */
package hry.cm2.customerminer.service.impl;

import hry.cm2.customerminer.dao.Cm2CustomerMinerProfitDao;
import hry.cm2.customerminer.model.Cm2CustomerMiner;
import hry.cm2.customerminer.model.Cm2CustomerMinerProfit;
import hry.cm2.customerminer.service.Cm2CustomerMinerProfitService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p> Cm2CustomerMinerProfitService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:56:38  
 */
@Service("cm2CustomerMinerProfitService")
public class Cm2CustomerMinerProfitServiceImpl extends BaseServiceImpl<Cm2CustomerMinerProfit, Long> implements Cm2CustomerMinerProfitService{
	
	@Resource(name="cm2CustomerMinerProfitDao")
	@Override
	public void setDao(BaseDao<Cm2CustomerMinerProfit, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void insertProit(Cm2CustomerMiner cmCustomerMiner, Integer status) {
		// TODO Auto-generated method stub
		Cm2CustomerMinerProfit cmCustomerMinerProfit = new Cm2CustomerMinerProfit();
		cmCustomerMinerProfit.setCustomerId(cmCustomerMiner.getCustomerId());
		cmCustomerMinerProfit.setCustomerMinerId(cmCustomerMiner.getId());
		cmCustomerMinerProfit.setMinerCode(cmCustomerMiner.getMinerCode());
		cmCustomerMinerProfit.setMinerName(cmCustomerMiner.getMinerName());
		cmCustomerMinerProfit.setMinerPrice(cmCustomerMiner.getMinerPrice());
		cmCustomerMinerProfit.setOrderId(cmCustomerMiner.getOrderId());
		cmCustomerMinerProfit.setProfit1(cmCustomerMiner.getDayProfit());
		cmCustomerMinerProfit.setProfit2(cmCustomerMiner.getProfit3());
		cmCustomerMinerProfit.setStatus(status);
		cmCustomerMinerProfit.setTransactionNum(cmCustomerMiner.getTransactionNum());
		super.save(cmCustomerMinerProfit);
	}

	@Override
	public BigDecimal getTeamProfitSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm2CustomerMinerProfitDao)dao).getTeamProfitSumByCustomerId(customerId);
	}

	@Override
	public BigDecimal getProfitSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm2CustomerMinerProfitDao)dao).getProfitSumByCustomerId(customerId);
	}
	

}
