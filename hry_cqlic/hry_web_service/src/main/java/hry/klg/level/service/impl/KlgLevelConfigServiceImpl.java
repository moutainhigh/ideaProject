/**
 * Copyright:
 * @author:      lzy
 * @version:     V1.0
 * @Date:        2019-04-17 14:31:17
 */
package hry.klg.level.service.impl;

import hry.klg.level.dao.KlgLevelConfigDao;
import hry.klg.level.model.KlgLevelConfig;
import hry.klg.level.service.KlgLevelConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.klg.model.RemoteLevelConfig;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

/**
 * <p> KlgLevelConfigService </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:31:17
 */
@Service("klgLevelConfigService")
public class KlgLevelConfigServiceImpl extends BaseServiceImpl<KlgLevelConfig, Long> implements KlgLevelConfigService{

	@Resource(name="klgLevelConfigDao")
	@Override
	public void setDao(BaseDao<KlgLevelConfig, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 根据等级Id获取配置
	 * @param leveId
	 * @return
	 */
	@Override
	public KlgLevelConfig getLevelConfigByLevelId(Long leveId) {
		if(true){
			return	super.get(leveId);
		}
		return null;
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

	/**
	 * 根据等级排序的数量获取可获得等级
	 * @param count
	 * @param sort
	 * @return
	 */
	@Override
	public KlgLevelConfig getLevelConfigBySortAndCount(Integer count, Integer sort) {
		QueryFilter queryFilter=new QueryFilter(KlgLevelConfig.class);
		queryFilter.addFilter("recommendNum<=",count);//推荐人数
		queryFilter.addFilter("recommendSort=",sort);//推荐星级别
		KlgLevelConfig 	levelConfig=super.get(queryFilter);
		return levelConfig;
	}


	@Override
	public void alculationCustomerGradation() {

	}
}
