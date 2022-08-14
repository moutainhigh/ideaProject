/**
 * Copyright:    String
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-07-31 11:49:55 
 */
package hry.mall.platform.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.mall.platform.model.Advertisement;


/**
 * <p> AdvertisementDao </p>
 * @author:         luyue
 * @Date :          2019-07-31 11:49:55  
 */
public interface AdvertisementDao extends  BaseDao<Advertisement, Long> {
	public List<Advertisement>  findByPage(Map<String,String> map);
	
	public Long  findCount(Map<String,String> map);
	
	public BigDecimal  findGetCount(Map<String,String> map);
}
