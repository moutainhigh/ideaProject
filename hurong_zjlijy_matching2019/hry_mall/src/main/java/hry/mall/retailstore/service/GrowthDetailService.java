/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-07 11:10:09 
 */
package hry.mall.retailstore.service;

import java.util.Map;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.retailstore.model.CustomerGrowth;
import hry.mall.retailstore.model.GrowthDetail;


/**
 * <p> GrowthDetailService </p>
 * @author:         luyue
 * @Date :          2019-05-07 11:10:09 
 */
public interface GrowthDetailService  extends BaseService<GrowthDetail, Long> {
	
	public JsonResult handleGrowth(Map<String, Object> paraMap);
	/**
	 * 保存成长值记录、账户信息
	 * @param map
	 * @return
	 */
	public  Boolean handleGrowthDetail(Map<String, Object> map) ;
	
	public String createNumber();
	/**
	 * 判断是否升级
	 * @param customerGrowth
	 * @return
	 */
	public CustomerGrowth promote(CustomerGrowth customerGrowth);


}
