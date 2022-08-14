package hry.customer.area.service.impl;

import java.util.List;

import hry.area.model.Area;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.area.dao.AreaDao;
import hry.customer.area.service.AreaService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("areaService")
public class AreaServiceImpl extends BaseServiceImpl<Area, Long> implements AreaService{
	
	@Resource(name="areaDao")
	@Override
	public void setDao(BaseDao<Area, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 查所有的省
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @return
	 * @return: List<Area> 
	 * @Date :          2017年3月17日 上午11:57:21   
	 * @throws:
	 */
	@Override
	public List<Area> findProvince() {
		return ((AreaDao)dao).findProvince();
	}

	@Override
	public List<Area> findCity(Long provinceId) {
		return ((AreaDao)dao).findCity(provinceId);
	}

	@Override
	public List<Area> findCounty(Long cityId) {
		return ((AreaDao)dao).findCounty(cityId);
	}

	
	
}