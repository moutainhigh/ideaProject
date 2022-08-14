/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-23 14:23:08 
 */
package hry.klg.platform.service.impl;

import hry.klg.model.RulesConfig;
import hry.klg.platform.model.KlgPlatformAccount;
import hry.klg.platform.model.KlgPlatformAccountRecord;
import hry.klg.platform.service.KlgPlatformAccountRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p> KlgPlatformAccountRecordService </p>
 * @author:         lzy
 * @Date :          2019-04-23 14:23:08  
 */
@Service("klgPlatformAccountRecordService")
public class KlgPlatformAccountRecordServiceImpl extends BaseServiceImpl<KlgPlatformAccountRecord, Long> implements KlgPlatformAccountRecordService{

	@Resource
	private RedisService redisService;

	@Resource(name="klgPlatformAccountRecordDao")
	@Override
	public void setDao(BaseDao<KlgPlatformAccountRecord, Long> dao) {
		super.dao = dao;
	}
}
