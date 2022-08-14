/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:46:43 
 */
package hry.mall.goods.service.impl;
import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.SpecValue;
import hry.mall.goods.service.SpecValueService;
import org.springframework.stereotype.Service;

/**
 * <p> SpecValueService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:46:43  
 */
@Service("specValueService")
public class SpecValueServiceImpl extends BaseServiceImpl<SpecValue, Long> implements SpecValueService {
	
	@Resource(name="specValueDao")
	@Override
	public void setDao(BaseDao<SpecValue, Long> dao) {
		super.dao = dao;
	}
	

}
