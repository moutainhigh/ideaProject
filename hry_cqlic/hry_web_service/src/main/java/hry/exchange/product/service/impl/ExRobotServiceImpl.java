/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      songb
 * @version:     V1.0 
 * @Date:        2018-05-04 11:37:43 
 */
package hry.exchange.product.service.impl;

import hry.exchange.product.dao.ExRobotDao;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.model.ExRobot;
import hry.exchange.product.service.ExRobotService;
import hry.redis.common.utils.RedisService;
import hry.web.app.service.AppConfigService;
import hry.web.cache.CacheManageCallBack;
import hry.web.cache.CacheManageService;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.serialize.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExRobotService </p>
 * @author:         songb
 * @Date :          2018-05-04 11:37:43  
 */
@Service("exRobotService")
public class ExRobotServiceImpl extends BaseServiceImpl<ExRobot, Long> implements ExRobotService,CacheManageService{
	
	public static String exRobotKey = "HRY:EXCHANGE:exRobot";
	
	@Resource(name="exRobotDao")
	@Override
	public void setDao(BaseDao<ExRobot, Long> dao) {
		super.dao = dao;
	}
	
	@Resource(name = "redisService")
	public RedisService redisService;

	@Override
	public void startAuto( Long id ) throws Exception {
		((ExRobotDao)dao).startAuto( id , 1);
	}

	@Override
	public void closeAutoByIds(Long[] ids) throws Exception {
		((ExRobotDao)dao).closeAutoByIds(ids,0);
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
	public void initCache(CacheManageCallBack cacheManageCallBack) {
		updateExRobotToRedis();
		cacheManageCallBack.callback(ExRobotService.class, exRobotKey, "自动交易信息缓存");
	}
	
}
