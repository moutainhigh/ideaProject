/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-09-25 13:58:51 
 */
package hry.admin.mining.service.impl;

import com.alibaba.fastjson.JSONObject;
import hry.admin.mining.model.MingTimer;
import hry.admin.mining.service.MingTimerService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.redis.common.utils.RedisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> MingTimerService </p>
 * @author:         sunyujie
 * @Date :          2018-09-25 13:58:51  
 */
@Service("mingTimerService")
public class MingTimerServiceImpl extends BaseServiceImpl<MingTimer, Long> implements MingTimerService{
	
	@Resource(name="mingTimerDao")
	@Override
	public void setDao(BaseDao<MingTimer, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private RedisService redisService;

	@Override
	public void initRedis() {
		List<MingTimer> timerServiceAll = super.findAll();
		if(timerServiceAll.size()>0){
			redisService.save("Mining:Timer0", JSONObject.toJSONString(timerServiceAll.get(0)));
			redisService.save("Mining:Timer1", JSONObject.toJSONString(timerServiceAll.get(0)));
			redisService.save("Mining:Timer2", JSONObject.toJSONString(timerServiceAll.get(0)));
		}
	}
}
