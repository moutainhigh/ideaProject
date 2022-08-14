/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-07-31 11:49:55 
 */
package hry.mall.platform.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.core.mvc.service.base.BaseService;
import hry.mall.platform.model.Advertisement;



/**
 * <p> AdvertisementService </p>
 * @author:         luyue
 * @Date :          2019-07-31 11:49:55 
 */
public interface AdvertisementService  extends BaseService<Advertisement, Long>{
	/**
	 * 查询广告列表
	 * @param map
	 * @return
	 */
	public List<Advertisement>  findByPage(Map<String,String> map);
	/**
	 * 查询广告点击浏览量
	 * @param map
	 * @return
	 */
	public Long  findCount(Map<String,String> map);
	/**
	 * 某人获得奖励数
	 * @param map
	 * @return
	 */
	public BigDecimal  findGetCount(Map<String,String> map);
}
