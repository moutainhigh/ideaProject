/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:02:38 
 */
package hry.cm5.miner.service.impl;

import hry.cm5.miner.model.Cm5MinerGoods;
import hry.cm5.miner.service.Cm5MinerGoodsService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> Cm5MinerGoodsService </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:02:38  
 */
@Service("cm5MinerGoodsService")
public class Cm5MinerGoodsServiceImpl extends BaseServiceImpl<Cm5MinerGoods, Long> implements Cm5MinerGoodsService{
	
	@Resource(name="cm5MinerGoodsDao")
	@Override
	public void setDao(BaseDao<Cm5MinerGoods, Long> dao) {
		super.dao = dao;
	}
	

}
