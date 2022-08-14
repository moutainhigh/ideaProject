/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-07-31 11:49:55 
 */
package hry.mall.platform.service.impl;

import hry.mall.platform.dao.AdvertisementDao;
import hry.mall.platform.model.Advertisement;
import hry.mall.platform.service.AdvertisementService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AdvertisementService </p>
 * @author:         luyue
 * @Date :          2019-07-31 11:49:55  
 */
@Service("advertisementService")
public class AdvertisementServiceImpl extends BaseServiceImpl<Advertisement, Long> implements AdvertisementService{
	
	@Resource(name="advertisementDao")
	@Override
	public void setDao(BaseDao<Advertisement, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Advertisement> findByPage(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<Advertisement> list=((AdvertisementDao)dao).findByPage(map);
		return list;
	}

	@Override
	public Long findCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		Long count=((AdvertisementDao)dao).findCount(map);
		return count;
	}

	@Override
	public BigDecimal findGetCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		BigDecimal count=((AdvertisementDao)dao).findGetCount(map);
		return count;
	}

}
