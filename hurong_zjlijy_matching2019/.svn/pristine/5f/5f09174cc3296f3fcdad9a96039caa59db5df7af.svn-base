/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-11-26 16:44:25 
 */
package hry.mall.goods.service.impl;

import hry.mall.goods.model.CoinPay;
import hry.mall.goods.service.CoinPayService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> CoinPayService </p>
 * @author:         zhouming
 * @Date :          2019-11-26 16:44:25  
 */
@Service("coinPayService")
public class CoinPayServiceImpl extends BaseServiceImpl<CoinPay, Long> implements CoinPayService{
	
	@Resource(name="coinPayDao")
	@Override
	public void setDao(BaseDao<CoinPay, Long> dao) {
		super.dao = dao;
	}
	

}
