/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:00:54 
 */
package hry.cm4.customerminer.service.impl;

import hry.cm4.customerminer.dao.Cm4CustomerMinerProfitDao;
import hry.cm4.customerminer.model.Cm4CustomerMiner;
import hry.cm4.customerminer.model.Cm4CustomerMinerProfit;
import hry.cm4.customerminer.service.Cm4CustomerMinerProfitService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p> Cm4CustomerMinerProfitService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:00:54  
 */
@Service("cm4CustomerMinerProfitService")
public class Cm4CustomerMinerProfitServiceImpl extends BaseServiceImpl<Cm4CustomerMinerProfit, Long> implements Cm4CustomerMinerProfitService{
	
	@Resource(name="cm4CustomerMinerProfitDao")
	@Override
	public void setDao(BaseDao<Cm4CustomerMinerProfit, Long> dao) {
		super.dao = dao;
	}
	@Override
	public void insertProit(Cm4CustomerMiner cmCustomerMiner, Integer status, String coinCode, BigDecimal profitProportion, String saasId,BigDecimal coinNum) {
		// TODO Auto-generated method stub
		Cm4CustomerMinerProfit cmCustomerMinerProfit = new Cm4CustomerMinerProfit();
		cmCustomerMinerProfit.setCustomerId(cmCustomerMiner.getCustomerId());
		cmCustomerMinerProfit.setCustomerMinerId(cmCustomerMiner.getId());
		cmCustomerMinerProfit.setMinerCode(cmCustomerMiner.getMinerCode());
		cmCustomerMinerProfit.setMinerName(cmCustomerMiner.getMinerName());
		cmCustomerMinerProfit.setMinerPrice(cmCustomerMiner.getMinerPrice());
		cmCustomerMinerProfit.setOrderId(cmCustomerMiner.getOrderId());
		cmCustomerMinerProfit.setProfit1(coinNum);
		cmCustomerMinerProfit.setProfit2(cmCustomerMiner.getProfit3());
		cmCustomerMinerProfit.setStatus(status);
		cmCustomerMinerProfit.setTransactionNum(cmCustomerMiner.getTransactionNum());
		cmCustomerMinerProfit.setProfitCoinCode(coinCode);
		cmCustomerMinerProfit.setProfitProportion(profitProportion);
		cmCustomerMinerProfit.setSaasId(saasId);
		super.save(cmCustomerMinerProfit);
	}

	@Override
	public BigDecimal getTeamProfitSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm4CustomerMinerProfitDao)dao).getTeamProfitSumByCustomerId(customerId);
	}

	@Override
	public BigDecimal getProfitSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm4CustomerMinerProfitDao)dao).getProfitSumByCustomerId(customerId);
	}


}
