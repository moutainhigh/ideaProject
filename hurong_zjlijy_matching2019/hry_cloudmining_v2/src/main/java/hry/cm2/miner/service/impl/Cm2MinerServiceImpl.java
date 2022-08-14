/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 15:56:47 
 */
package hry.cm2.miner.service.impl;

import hry.cm2.miner.dao.Cm2MinerDao;
import hry.cm2.miner.model.Cm2Miner;
import hry.cm2.miner.service.Cm2MinerService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> Cm2MinerService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 15:56:47  
 */
@Service("cm2MinerService")
public class Cm2MinerServiceImpl extends BaseServiceImpl<Cm2Miner, Long> implements Cm2MinerService{
	
	@Resource(name="cm2MinerDao")
	@Override
	public void setDao(BaseDao<Cm2Miner, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void updateMinerSurplusNum(Long id, int orderNum) {
		// TODO Auto-generated method stub
		((Cm2MinerDao)dao).updateMinerSurplusNum(id, orderNum);
	}
	

}
