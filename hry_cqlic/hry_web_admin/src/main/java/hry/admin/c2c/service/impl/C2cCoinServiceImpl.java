/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 12:06:01 
 */
package hry.admin.c2c.service.impl;

import com.alibaba.fastjson.JSON;
import hry.admin.c2c.model.C2cCoin;
import hry.admin.c2c.service.C2cCoinService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> C2cCoinService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 12:06:01  
 */
@Service("c2cCoinService")
public class C2cCoinServiceImpl extends BaseServiceImpl<C2cCoin, Long> implements C2cCoinService{
	
	@Resource(name="c2cCoinDao")
	@Override
	public void setDao(BaseDao<C2cCoin, Long> dao) {
		super.dao = dao;
	}


	@Override
	public void flushRedis() {
		QueryFilter filter = new QueryFilter(C2cCoin.class);
		filter.addFilter("isOpen=", 1);
		List<C2cCoin> list = find(filter);
		ArrayList<String> c2cs = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (C2cCoin cc : list) {
				c2cs.add(cc.getCoinCode());
			}
		}
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		redisService.save("cn:c2clist", JSON.toJSONString(c2cs));
		redisService.save("cn:c2cCoinList", JSON.toJSONString(list));
	}
}
