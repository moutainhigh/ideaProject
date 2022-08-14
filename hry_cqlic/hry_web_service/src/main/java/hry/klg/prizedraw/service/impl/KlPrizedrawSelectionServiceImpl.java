/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-11 14:52:37 
 */
package hry.klg.prizedraw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.klg.prizedraw.dao.KlPrizedrawSelectionDao;
import hry.klg.prizedraw.model.KlPrizedrawSelection;
import hry.klg.prizedraw.service.KlPrizedrawSelectionService;

/**
 * <p> KlPrizedrawSelectionService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-11 14:52:37  
 */
@Service("klPrizedrawSelectionService")
public class KlPrizedrawSelectionServiceImpl extends BaseServiceImpl<KlPrizedrawSelection, Long> implements KlPrizedrawSelectionService{
	
	@Resource(name="klPrizedrawSelectionDao")
	@Override
	public void setDao(BaseDao<KlPrizedrawSelection, Long> dao) {
		super.dao = dao;
	}

}
