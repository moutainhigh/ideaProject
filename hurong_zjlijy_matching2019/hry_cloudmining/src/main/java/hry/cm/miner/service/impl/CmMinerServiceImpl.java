/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-26 17:32:36 
 */
package hry.cm.miner.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.cm.miner.dao.CmMinerDao;
import hry.cm.miner.model.CmMiner;
import hry.cm.miner.service.CmMinerService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

/**
 * <p> CmMinerService </p>
 * @author:         yaozh
 * @Date :          2019-07-26 17:32:36  
 */
@Service("cmMinerService")
public class CmMinerServiceImpl extends BaseServiceImpl<CmMiner, Long> implements CmMinerService{
	
	@Resource(name="cmMinerDao")
	@Override
	public void setDao(BaseDao<CmMiner, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void updateMinerSurplusNum(Long id, int orderNum) {
		// TODO Auto-generated method stub
		((CmMinerDao)dao).updateMinerSurplusNum(id, orderNum);
	}
	

}
