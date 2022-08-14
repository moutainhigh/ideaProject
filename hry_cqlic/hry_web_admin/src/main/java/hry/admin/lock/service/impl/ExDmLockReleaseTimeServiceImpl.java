/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-11-01 18:38:24 
 */
package hry.admin.lock.service.impl;

import hry.admin.lock.dao.ExDmLockReleaseTimeDao;
import hry.admin.lock.model.ExDmLockReleaseTime;
import hry.admin.lock.service.ExDmLockReleaseTimeService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExDmLockReleaseTimeService </p>
 * @author:         liuchenghui
 * @Date :          2018-11-01 18:38:24  
 */
@Service("exDmLockReleaseTimeService")
public class ExDmLockReleaseTimeServiceImpl extends BaseServiceImpl<ExDmLockReleaseTime, Long> implements ExDmLockReleaseTimeService{
	
	@Resource(name="exDmLockReleaseTimeDao")
	@Override
	public void setDao(BaseDao<ExDmLockReleaseTime, Long> dao) {
		super.dao = dao;
	}


	@Override
	public List<ExDmLockReleaseTime> getReleaseTime (Map<String, Object> map) {
		return ((ExDmLockReleaseTimeDao)dao).getReleaseTime(map);
	}

	// 自动-更新释放记录
	@Override
	public void updReleaseTimeForAuto (Long recordId) {
		((ExDmLockReleaseTimeDao)dao).updReleaseTimeForAuto(recordId);
	}

	// 手动-更新释放记录
	@Override
	public void updReleaseTimeForManual(Long recordId) {
		((ExDmLockReleaseTimeDao)dao).updReleaseTimeForManual(recordId);
	}
}
