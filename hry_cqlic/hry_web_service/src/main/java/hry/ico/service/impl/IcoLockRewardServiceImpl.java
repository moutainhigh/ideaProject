/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-24 16:33:40 
 */
package hry.ico.service.impl;

import hry.ico.model.IcoLockReward;
import hry.ico.service.IcoLockRewardService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoLockRewardService </p>
 * @author:         lzy
 * @Date :          2019-01-24 16:33:40  
 */
@Service("icoLockRewardService")
public class IcoLockRewardServiceImpl extends BaseServiceImpl<IcoLockReward, Long> implements IcoLockRewardService{
	
	@Resource(name="icoLockRewardDao")
	@Override
	public void setDao(BaseDao<IcoLockReward, Long> dao) {
		super.dao = dao;
	}
	

}
