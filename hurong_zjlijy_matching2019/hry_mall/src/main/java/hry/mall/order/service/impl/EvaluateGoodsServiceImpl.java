/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-27 11:56:50 
 */
package hry.mall.order.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.order.dao.EvaluateGoodsDao;
import hry.mall.order.model.EvaluateGoods;
import hry.mall.order.model.vo.ExDigitalmoneyAccount;
import hry.mall.order.service.EvaluateGoodsService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> EvaluateGoodsService </p>
 * @author:         luyue
 * @Date :          2018-11-27 11:56:50  
 */
@Service("evaluateGoodsService")
public class EvaluateGoodsServiceImpl extends BaseServiceImpl<EvaluateGoods, Long> implements EvaluateGoodsService {
	
	@Resource(name="evaluateGoodsDao")
	@Override
	public void setDao(BaseDao<EvaluateGoods, Long> dao) {
		super.dao = dao;
	}

	//分页查询商品评价
	public List<EvaluateGoods> findEvaluateList(Map<String, Object> params){
		return ((EvaluateGoodsDao)dao).findEvaluateList(params);
	}

	@Override
	public int countEvaluateGoods(Map<String, Object> params) {
		return ((EvaluateGoodsDao)dao).countEvaluateGoods(params);
	}

	@Override
	public ExDigitalmoneyAccount getExDigitalmoneyAccount(Map<String, Object> params ){
		return ((EvaluateGoodsDao)dao).getExDigitalmoneyAccount(params);
	}

}
