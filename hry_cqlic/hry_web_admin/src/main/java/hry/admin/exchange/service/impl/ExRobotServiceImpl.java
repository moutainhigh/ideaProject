/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 16:33:44 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.dao.ExRobotDao;
import hry.admin.exchange.model.ExRobot;
import hry.admin.exchange.service.ExRobotService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.redis.common.utils.Mapper;
import hry.redis.common.utils.RedisService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExRobotService </p>
 * @author:         liushilei
 * @Date :          2018-06-12 16:33:44  
 */
@Service("exRobotService")
public class ExRobotServiceImpl extends BaseServiceImpl<ExRobot, Long> implements ExRobotService{
	public static String exRobotKey = "HRY:EXCHANGE:exRobot";

	@Resource(name="exRobotDao")
	@Override
	public void setDao(BaseDao<ExRobot, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private RedisService redisService;

	@Override
	public void startAuto( Long id ) throws Exception {
		((ExRobotDao)dao).startAuto( id , 1);
	}
	/**
	 * 更新redis里的缓存
	 *
	 */
	@Override
	public void updateExRobotToRedis() {

		Map<String, String> map = new HashMap<String, String>();
		List<ExRobot> list = this.findAll();

		if (null != list && list.size() > 0) {
			for (ExRobot exRobot : list) {
				String objectToJson = Mapper.objectToJson(exRobot);
				map.put(exRobot.getId().toString(), objectToJson);
			}

			redisService.saveMap( exRobotKey, map);
		}

	}

	@Override
	public void closeAutoByIds(Long[] ids)  {
		((ExRobotDao)dao).closeAutoByIds(ids,0);
	}
}
