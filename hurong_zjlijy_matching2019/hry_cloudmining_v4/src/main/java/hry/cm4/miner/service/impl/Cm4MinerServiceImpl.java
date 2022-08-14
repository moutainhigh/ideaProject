/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:03:31 
 */
package hry.cm4.miner.service.impl;

import hry.cm4.miner.dao.Cm4MinerDao;
import hry.cm4.miner.model.Cm4Miner;
import hry.cm4.miner.service.Cm4MinerService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> Cm4MinerService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:03:31  
 */
@Service("cm4MinerService")
public class Cm4MinerServiceImpl extends BaseServiceImpl<Cm4Miner, Long> implements Cm4MinerService{
	
	@Resource(name="cm4MinerDao")
	@Override
	public void setDao(BaseDao<Cm4Miner, Long> dao) {
		super.dao = dao;
	}
	@Override
	public void updateMinerSurplusNum(Long id, int orderNum) {
		// TODO Auto-generated method stub
		((Cm4MinerDao)dao).updateMinerSurplusNum(id, orderNum);
	}

}
