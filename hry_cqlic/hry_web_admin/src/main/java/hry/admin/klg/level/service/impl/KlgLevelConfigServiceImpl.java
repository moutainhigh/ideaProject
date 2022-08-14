/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:43 
 */
package hry.admin.klg.level.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.admin.klg.level.dao.KlgLevelConfigDao;
import hry.admin.klg.level.model.KlgLevelConfig;
import hry.admin.klg.level.service.KlgLevelConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

/**
 * <p> KlgLevelConfigService </p>
 * @author:         lzy
 * @Date :          2019-04-11 17:30:43  
 */
@Service("klgLevelConfigService")
public class KlgLevelConfigServiceImpl extends BaseServiceImpl<KlgLevelConfig, Long> implements KlgLevelConfigService{
	
	@Resource(name="klgLevelConfigDao")
	@Override
	public void setDao(BaseDao<KlgLevelConfig, Long> dao) {
		super.dao = dao;
	}
	
	/**
	 * 获取用户当前等级的LevelConfig配置信息
	 * @param customerId
	 * @return
	 */
	@Override
	public KlgLevelConfig getLevelConfigByCustomerId(Long customerId) {
		return ((KlgLevelConfigDao)dao).getLevelConfigByCustomerId(customerId);
	}

}
