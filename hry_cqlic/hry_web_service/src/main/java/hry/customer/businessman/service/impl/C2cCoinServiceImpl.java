/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0
 * @Date:        2017-12-07 14:06:38 
 */
package hry.customer.businessman.service.impl;

import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.businessman.model.C2cCoin;
import hry.customer.businessman.service.C2cCoinService;

import java.math.BigDecimal;

/**
 * <p> c2cCoinService </p>
 * @author:         liushilei
 * @Date :          2017-12-07 14:06:38
 */
@Service("c2cCoinService")
public class C2cCoinServiceImpl extends BaseServiceImpl<C2cCoin, Long> implements C2cCoinService{

	@Resource(name="c2cCoinDao")
	@Override
	public void setDao(BaseDao<C2cCoin, Long> dao) {
		super.dao = dao;
	}
	@Override
	public BigDecimal getCoinPrice(String coin, String type){
		if(type.equals("1")) {
			return this.getbuyPrice(coin) ;
		}
		return this.getSellPrice(coin);

	}
	@Override
	public BigDecimal getSellPrice(String coin){
		return this.getByCoin(coin).getSellPrice();
	}
	@Override
	public BigDecimal getbuyPrice(String coin){
		return this.getByCoin(coin).getBuyPrice();
	}
	@Override
	public C2cCoin getByCoin(String coin){
		return this.get(new QueryFilter(C2cCoin.class).addFilter("coinCode=",coin));
	}
}
