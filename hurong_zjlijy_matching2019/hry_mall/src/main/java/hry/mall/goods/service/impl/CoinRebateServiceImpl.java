/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-11-26 16:44:58 
 */
package hry.mall.goods.service.impl;

import hry.mall.goods.model.CoinRebate;
import hry.mall.goods.service.CoinRebateService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> CoinRebateService </p>
 * @author:         zhouming
 * @Date :          2019-11-26 16:44:58  
 */
@Service("coinRebateService")
public class CoinRebateServiceImpl extends BaseServiceImpl<CoinRebate, Long> implements CoinRebateService{
	
	@Resource(name="coinRebateDao")
	@Override
	public void setDao(BaseDao<CoinRebate, Long> dao) {
		super.dao = dao;
	}
	

}
