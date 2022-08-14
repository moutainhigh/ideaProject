package hry.customer.area.service;

import hry.area.model.Area;
import hry.core.mvc.service.base.BaseService;

import java.util.List;

public interface AreaService  extends BaseService<Area, Long>{


	/**
	 * 查所有的省
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @return
	 * @return: List<Area> 
	 * @Date :          2017年3月17日 上午11:57:21   
	 * @throws:
	 */
	List<Area> findProvince();

	/**
	 * 根据省ID查市
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param valueOf
	 * @param:    @return
	 * @return: List<Area> 
	 * @Date :          2017年3月17日 下午12:08:21   
	 * @throws:
	 */
	List<Area> findCity(Long valueOf);
	/**
	 * 根据市ID查县
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param valueOf
	 * @param:    @return
	 * @return: List<Area> 
	 * @Date :          2017年3月17日 下午12:08:21   
	 * @throws:
	 */
	List<Area> findCounty(Long valueOf);

}
