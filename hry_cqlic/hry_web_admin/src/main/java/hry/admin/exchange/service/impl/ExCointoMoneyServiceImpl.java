/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 10:07:04 
 */
package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSONObject;
import hry.admin.exchange.model.ExCointoMoney;
import hry.admin.exchange.service.ExCointoMoneyService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> ExCointoMoneyService </p>
 * @author:         tianpengyu
 * @Date :          2018-08-22 10:07:04  
 */
@Service("exCointoMoneyService")
public class ExCointoMoneyServiceImpl extends BaseServiceImpl<ExCointoMoney, Long> implements ExCointoMoneyService{
	
	@Resource(name="exCointoMoneyDao")
	@Override
	public void setDao(BaseDao<ExCointoMoney, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private RedisService redisService;

	@Override
	public void initRedis(){
		QueryFilter queryFilter = new QueryFilter(ExCointoMoney.class);
		queryFilter.addFilter("state=","1");

		List<ExCointoMoney> list = find(queryFilter);
		if(null != list){
			for(ExCointoMoney exCointoMoney : list){

                    redisService.save("CointoMoney:"+ exCointoMoney.getLan(), JSONObject.toJSONString(exCointoMoney));

			}
		}

	}
	

}
