/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:38:54 
 */
package hry.mall.retailstore.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.CutomerStore;
import hry.mall.retailstore.service.CutomerStoreService;
import org.springframework.stereotype.Service;

/**
 * <p> CutomerStoreService </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:38:54  
 */
@Service("cutomerStoreService")
public class CutomerStoreServiceImpl extends BaseServiceImpl<CutomerStore, Long> implements CutomerStoreService {
	
	@Resource(name="cutomerStoreDao")
	@Override
	public void setDao(BaseDao<CutomerStore, Long> dao) {
		super.dao = dao;
	}
	

}
