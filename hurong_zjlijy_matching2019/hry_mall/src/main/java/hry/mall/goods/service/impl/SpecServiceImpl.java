/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:46:10 
 */
package hry.mall.goods.service.impl;
import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.dao.SpecDao;
import hry.mall.goods.model.Spec;
import hry.mall.goods.service.SpecService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> SpecService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:46:10  
 */
@Service("specService")
public class SpecServiceImpl extends BaseServiceImpl<Spec, Long> implements SpecService {

	@Resource(name="specDao")
	@Override
	public void setDao(BaseDao<Spec, Long> dao) {
		super.dao = dao;
	}


	@Override
	public List<Spec> findSpecList(Map<String, Object> paramMap) {
		return ((SpecDao)dao).findSpecList(paramMap);
	}

	@Override
	public Spec findSpecById(Long specId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id",specId);
		Spec spec = null;
		List<Spec> specList = ((SpecDao)dao).findSpecList(paramMap);
		if (specList != null && specList.size() > 0) {
			spec = specList.get(0);
		}
		return spec;
	}
}
