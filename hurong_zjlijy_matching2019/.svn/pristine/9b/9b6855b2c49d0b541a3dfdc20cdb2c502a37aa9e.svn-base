/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:43:59 
 */
package hry.mall.goods.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.Brand;
import hry.mall.goods.service.BrandService;
import org.springframework.stereotype.Service;

/**
 * <p> BrandService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:43:59  
 */
@Service("brandService")
public class BrandServiceImpl extends BaseServiceImpl<Brand, Long> implements BrandService {
	
	@Resource(name="brandDao")
	@Override
	public void setDao(BaseDao<Brand, Long> dao) {
		super.dao = dao;
	}
	

}
